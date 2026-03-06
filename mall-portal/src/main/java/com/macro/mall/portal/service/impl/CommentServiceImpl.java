package com.macro.mall.portal.service.impl;

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
import com.macro.mall.portal.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

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
    private final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");

    private long calculateScore(int likeNum, int replyNum) {
        YearMonth currentMonth = YearMonth.now();
        String monthStr = currentMonth.format(MONTH_FORMATTER);
        // 1. 获取当前年月数字（如 202603）
        long monthNum = Long.parseLong(monthStr);
        // 2. 年月左移32位（高位）
        long highPart = monthNum << 32;
        // 3. 低位：点赞数+回复数
        long lowPart = (long) likeNum + replyNum;
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

        // 3. 插入后：自增主键已自动回填到实体中
        Long newId = cmsCommentContent.getId();
        CmsComment cmsComment = new CmsComment();
        BeanUtils.copyProperties(commentParam,cmsComment);
        cmsComment.setContentId(newId);
        Long score = calculateScore(0,0);
        cmsComment.setScore(score);
        affectedRows = cmsCommentMapper.insertSelective(cmsComment);
        if (affectedRows != 1) {
            throw new RuntimeException("评论元信息插入失败");
        }
        return cmsComment.getId();
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
    public List<Comment> getSonComments(int parentId) {
        return commentDao.getSonComments(parentId);
    }

    @Override
    public List<Comment> getComments(Long goodId,int page, int limit) {
        return commentDao.getComments(goodId,page,limit);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void increaseCommentLike(Long commentId, Long userId) {
        commentDao.incrementLikeNumById(commentId);
        CmsUserCommentLike cmsUserCommentLike = new CmsUserCommentLike();
        cmsUserCommentLike.setUserId(userId);
        cmsUserCommentLike.setCommentId(commentId);
        cmsUserCommentLikeMapper.insertSelective(cmsUserCommentLike);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decreaseCommentLike(Long commentId, Long userId) {
        CmsUserCommentLikeExample cmsUserCommentLikeExample = new CmsUserCommentLikeExample();
        cmsUserCommentLikeExample.createCriteria().andUserIdEqualTo(userId);
        commentDao.decrementLikeNumById(commentId);
        cmsUserCommentLikeMapper.deleteByExample(cmsUserCommentLikeExample);
    }

    @Override
    public List<Long> getLikeIds(List<Long> commentIds) {
        return Collections.emptyList();
    }
}
