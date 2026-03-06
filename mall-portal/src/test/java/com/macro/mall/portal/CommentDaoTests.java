package com.macro.mall.portal;

import com.macro.mall.portal.dao.CommentDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentDaoTests {
    @Autowired
    private CommentDao commentDao;
    @Test
    public void testGetComments() {
        System.out.println(commentDao.getComments(2001L,1, 10));
    }
    @Test
    public void testGetSonComments() {
        System.out.println(commentDao.getSonComments(1));
    }
}
