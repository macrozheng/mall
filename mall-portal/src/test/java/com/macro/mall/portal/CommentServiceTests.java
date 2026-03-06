package com.macro.mall.portal;

import com.macro.mall.portal.dao.CommentDao;
import com.macro.mall.portal.domain.CommentParam;
import com.macro.mall.portal.service.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentServiceTests {
    @Autowired
    private CommentService commentService;
    @Test
    public void testCreateComment() {

        CommentParam commentParam = new CommentParam();
        commentParam.setParentId(0); // 根评论
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
        System.out.println(commentService.getSonComments(1));
    }
}
