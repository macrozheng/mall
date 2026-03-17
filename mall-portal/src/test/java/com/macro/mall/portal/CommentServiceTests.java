package com.macro.mall.portal;

import com.macro.mall.portal.dao.CommentDao;
import com.macro.mall.portal.domain.CommentParam;
import com.macro.mall.portal.service.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class CommentServiceTests {
    @Autowired
    private CommentService commentService;
    @Test
    public void testCreateComment() {

        CommentParam commentParam = new CommentParam();
        commentParam.setParentId(0L); // 根评论
        commentParam.setContent("测试评论内容"); // 详情表内容
        commentParam.setPicUrl("https://test.jpg"); // 详情表图片
        commentParam.setUserId(1001L); // 用户ID
        commentParam.setGoodId(2001L); // 商品ID

        Long commentId = commentService.createComment(commentParam);

        // 3. 断言验证结果（核心：判断返回的主键是否有效）
        // 断言1：返回的主键非空
        Assertions.assertNotNull(commentId);
        // 断言2：返回的主键是正数（自增主键从1开始）
        Assertions.assertTrue(commentId > 0);

    }
    @Test
    public void testGetSonComments() {
        System.out.println(commentService.getSonComments(1L,1L));
    }
    @Test
    public void testGetComments() {
        System.out.println(commentService.getComments(2001L,1L,1,10));
    }
    @Test
    public void testGetLikeIds() {
        ArrayList<Long> longs = new ArrayList<>();
        longs.add(1L);
        longs.add(2L);
        longs.add(3L);
        longs.add(4L);
        longs.add(5L);
        System.out.println(commentService.getLikeIds(1L,longs));
    }

}
