package com.macro.mall.portal.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.json.JSONUtil;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.macro.mall.mapper.CmsCommentContentMapper;
import com.macro.mall.mapper.CmsCommentMapper;
import com.macro.mall.mapper.CmsUserCommentLikeMapper;
import com.macro.mall.model.CmsComment;
import com.macro.mall.model.CmsCommentContent;
import com.macro.mall.model.CmsUserCommentLike;
import com.macro.mall.model.CmsUserCommentLikeExample;
import com.macro.mall.portal.dao.CommentDao;
import com.macro.mall.portal.domain.CommentParam;
import com.macro.mall.portal.dto.Comment;
import com.macro.mall.portal.dto.LikeEvent;
import com.macro.mall.portal.service.CommentService;
import com.macro.mall.portal.util.LikeRingBuffer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private CmsCommentMapper cmsCommentMapper;
    @Autowired
    private CmsCommentContentMapper cmsCommentContentMapper;
    @Autowired
    private CmsUserCommentLikeMapper cmsUserCommentLikeMapper;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private LikeRingBuffer likeRingBuffer;

    private static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");
    private static final String ROOT_COMMENT_KEY_PREFIX = "comment:root:"; // 一级评论Key：comment:root:{good_id}
    private static final String SON_COMMENT_KEY_PREFIX = "comment:son:";
    private static final String HOT_COMMENT_KEY_PREFIX = "comment:hot:"; // 一级评论Key：comment:root:{good_id}
    private static final String COOL_COMMENT_KEY_PREFIX = "comment:cool:";




    private long calculateScore(Date date, int likeNum, int repostNum) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(
                date.toInstant(),
                ZoneId.systemDefault() // 系统默认时区（和数据库一致）
        );
        String yearMonthStr = localDateTime.format(MONTH_FORMATTER);
        long monthNum = Long.parseLong(yearMonthStr);
        // 2. 年月左移32位（高位）
        long highPart = monthNum << 32;
        // 3. 低位：点赞数+回复数
        long lowPart = (long) likeNum + repostNum;
        // 4. 复合值（高位+低位）
        return highPart + lowPart;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createComment(CommentParam commentParam) {
        CmsCommentContent cmsCommentContent = new CmsCommentContent();
        BeanUtils.copyProperties(commentParam,cmsCommentContent);
        int affectedRows = cmsCommentContentMapper.insertSelective(cmsCommentContent);
        if (affectedRows != 1) {
            throw new RuntimeException("评论详情插入失败");
        }

        Long newId = cmsCommentContent.getId();
        CmsComment cmsComment = new CmsComment();
        BeanUtils.copyProperties(commentParam,cmsComment);
        cmsComment.setContentId(newId);
        Date date = new Date();
        cmsComment.setCreateTime(date);

        long score = calculateScore(date,0,0);
        cmsComment.setScore(score);
        affectedRows = cmsCommentMapper.insertSelective(cmsComment);
        if (affectedRows != 1) {
            throw new RuntimeException("评论元信息插入失败");
        }

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentParam,comment);
        Long commentId = comment.getId();

        // 3. 同步到Redis
//        setCommentToRedis(comment);

        return commentId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteComment(Long commentId) {
        CmsComment cmsComment = cmsCommentMapper.selectByPrimaryKey(commentId);
        if (cmsComment == null) {
            throw new RuntimeException("评论不存在！");
        }
        int affectedRows = cmsCommentMapper.deleteByPrimaryKey(commentId);
        if (affectedRows == 0) {
            throw new RuntimeException("评论元信息删除失败！");
        }
        affectedRows = cmsCommentContentMapper.deleteByPrimaryKey(cmsComment.getContentId());
        if (affectedRows == 0) {
            throw new RuntimeException("评论详情删除失败！");
        }
    }

    @Override
    public List<Comment> getSonComments(Long userId,Long parentId) {
        List<Long> sonCommentIds = redisUtils.zRange(SON_COMMENT_KEY_PREFIX+parentId,0,-1,Long.class);
        System.out.println("sonCommentIds:" + sonCommentIds);
        List<Comment> sonComments;
        List<Boolean> likeIds;
        if (!sonCommentIds.isEmpty()){
            sonComments = sonCommentIds.stream()
                    .map(x->{
                        Comment comment = JSONUtil.toBean((String) redisTemplate.opsForValue().get(COOL_COMMENT_KEY_PREFIX+x),Comment.class);
                        BeanUtil.fillBeanWithMap(redisTemplate.opsForHash().entries(HOT_COMMENT_KEY_PREFIX+x),comment,CopyOptions.create().ignoreNullValue());
                        return comment;
                    })
                    .collect(Collectors.toList());
            likeIds = getLikeIds(userId,sonCommentIds);
        }
        else{
            sonComments = commentDao.getSonComments(parentId);
            System.out.println(sonComments);
//            查出的数据放到redis中
            setCommentsToRedis(sonComments);
            likeIds = getLikeIds(userId,sonComments.stream().map(Comment::getId).collect(Collectors.toList()));
        }

        for (int i=0;i<likeIds.size();i++){
            sonComments.get(i).setLiked(likeIds.get(i));
        }
        return sonComments;
    }

    @Override
    public List<Comment> getComments(Long goodId,Long userId,int page, int limit) {
        String key = ROOT_COMMENT_KEY_PREFIX+goodId.toString();
        List<Comment> comments;
        List<Boolean> likeIds;
        if (!redisTemplate.hasKey(key)){
            List<Long> commentIds = redisUtils.zRange(key, (long) (page-1) *limit,limit,Long.class);
            comments = commentIds.stream()
                    .map(x->{
                        Comment comment = JSONUtil.toBean((String) redisTemplate.opsForValue().get(COOL_COMMENT_KEY_PREFIX+x),Comment.class);
                        BeanUtil.fillBeanWithMap(redisTemplate.opsForHash().entries(HOT_COMMENT_KEY_PREFIX+x),comment,CopyOptions.create().ignoreNullValue());
                        return comment;
                    })
                    .collect(Collectors.toList());
            likeIds = getLikeIds(userId,commentIds);
        }
        else{
            comments = commentDao.getComments(goodId,page,limit);
//            查出的数据放到redis中
            setCommentsToRedis(comments);
            likeIds = getLikeIds(userId,comments.stream().map(Comment::getId).collect(Collectors.toList()));
        }

        for (int i=0;i<likeIds.size();i++){
            comments.get(i).setLiked(likeIds.get(i));
        }
        return comments;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void increaseCommentLike(Long commentId, Long userId) {
        likeRingBuffer.addEvent(new LikeEvent(userId,commentId,1));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decreaseCommentLike(Long commentId, Long userId) {
//        查询布隆过滤器
        likeRingBuffer.addEvent(new LikeEvent(userId,commentId,-1));
    }

    @Override
    public List<Boolean> getLikeIds(Long userId,List<Long> commentIds) {
//        1.检查userLike布隆过滤器是否存在，不存在则创建
        if(!redisTemplate.hasKey("userCommentLike")){
            redisUtils.bfReserve("userCommentLike",0.01,1000000);
            List<String> values = commentDao.selectUserCommentLikeAll().stream().map(x->x.getUserId()+":"+x.getCommentId()).collect(Collectors.toList());
            redisUtils.bfMadd("userCommentLike",values);
        }
        List<String> itemsToSearch = commentIds.stream()
                .map(id -> userId + ":" + id)
                .collect(Collectors.toList());
//      2.先走布隆过滤器，对于可能存在的评论去数据库里查
        List<Boolean> userCommentLike = redisUtils.bfMExists("userCommentLike", itemsToSearch);
        for  (int i=0;i<userCommentLike.size();i++)
            if (userCommentLike.get(i))
                userCommentLike.set(i,commentDao.existsByCommentIdAndUserId(userId,commentIds.get(i)));
        return userCommentLike;
    }

    private void setCommentToRedis(Comment comment) {
        if (comment.getParentId() == 0) {
            String rootKey = ROOT_COMMENT_KEY_PREFIX + comment.getGoodId();
            redisTemplate.opsForZSet().add(rootKey, comment.getId(), comment.getScore());
        } else {
            String sonKey = SON_COMMENT_KEY_PREFIX +  comment.getGoodId() + ":" + comment.getParentId();
            redisTemplate.opsForZSet().add(sonKey, comment.getId(), comment.getScore());
        }

        Map<String, Object> hotMap = new HashMap<>(2); // 初始容量指定为2，性能更好

        hotMap.put("likeNum", 0);
        hotMap.put("repostNum", 0);

        Map<String, Object> coolMap = new HashMap<>(4);
        coolMap.put("createTime", comment.getCreateTime());
        coolMap.put("goodId", comment.getGoodId());
        coolMap.put("content", comment.getContent());
        coolMap.put("picUrl", comment.getPicUrl());

        String hotKey = HOT_COMMENT_KEY_PREFIX + comment.getId();
        String coolKey = COOL_COMMENT_KEY_PREFIX + comment.getId();
        redisTemplate.opsForHash().putAll(hotKey, hotMap);
        redisTemplate.opsForHash().putAll(coolKey, coolMap);
    }

    private void setCommentsToRedis(List<Comment> comments) {
        comments.forEach(this::setCommentToRedis);
    }
}
