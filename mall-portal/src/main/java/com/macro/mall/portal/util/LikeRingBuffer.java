package com.macro.mall.portal.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.macro.mall.mapper.CmsUserCommentLikeMapper;
import com.macro.mall.model.CmsUserCommentLike;
import com.macro.mall.model.CmsUserCommentLikeExample;
import com.macro.mall.portal.dao.CommentDao;
import com.macro.mall.portal.dto.LikeEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadFactory;

@Component
public class LikeRingBuffer {
    private final Disruptor<LikeEvent> ringBuffer;
    private final int BUFFER_SIZE = 1024 * 1024;
    @Autowired
    LikeRingBuffer(SimpleBatchHandler simpleBatchHandler) {

        ringBuffer = new Disruptor<>(
                LikeEvent::new,
                BUFFER_SIZE,
                x -> {
                    Thread thread = new Thread(x);
                    thread.setDaemon(true); // 守护线程
                    thread.setName("processor");
                    return thread;
                },
                ProducerType.MULTI,
                new BlockingWaitStrategy()
        );

        ringBuffer.handleEventsWith(simpleBatchHandler);
        ringBuffer.start();
    }
    public void addEvent(LikeEvent likeEvent) {
        ringBuffer.getRingBuffer().tryPublishEvent((event, sequence) -> {
            BeanUtils.copyProperties(likeEvent, event);
        });
    }
}
@Component
class SimpleBatchHandler implements EventHandler<LikeEvent> {
    private int count = 0;
    private static final int BATCH_SIZE = 1;
    private static final ArrayList<LikeEvent> buffer = new ArrayList<>(5000);
    private static final HashMap<Long,Integer> likeMap = new HashMap<>(5000);
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private CmsUserCommentLikeMapper cmsUserCommentLikeMapper;
    @Override
    public void onEvent(LikeEvent event, long sequence, boolean endOfBatch) {
        count++;
        buffer.add(event);

//         达到5000条时，执行批量操作
        if (count >= BATCH_SIZE) {
            flush();
        }
    }
    @Transactional(rollbackFor = Exception.class)
    private void flush() {
        for (LikeEvent event : buffer) {
//            点赞计数
            Integer changeNum = likeMap.getOrDefault(event.getCommentId(),0)+event.getIncrement();
            likeMap.put(event.getCommentId(),changeNum);
//            插入点赞关系
            if(Convert.toInt(event.getIncrement())>0) {
                CmsUserCommentLike cmsUserCommentLike = new CmsUserCommentLike();
                BeanUtil.copyProperties(event, cmsUserCommentLike, true);
                cmsUserCommentLikeMapper.insertSelective(cmsUserCommentLike);
            }
//            删除点赞关系
            else {
                CmsUserCommentLikeExample cmsUserCommentLikeExample = new CmsUserCommentLikeExample();
                cmsUserCommentLikeExample.createCriteria().andUserIdEqualTo(event.getCommentId());
                cmsUserCommentLikeMapper.deleteByExample(cmsUserCommentLikeExample);
            }
        }
//        批量更新点赞数
        System.out.println(likeMap);
        commentDao.updateLikeNumBatch(likeMap);
//        清空数据结构，重新计数
        count = 0;
        buffer.clear();
        likeMap.clear();
    }
}

