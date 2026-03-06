package com.macro.mall.portal.dao;

import com.macro.mall.portal.dto.Comment;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentDao {
    //  获取父节点下的所有评论
    public List<Comment> getSonComments(@Param("parentId") int parentId);
    //  分页
    public List<Comment> getComments(@Param("goodId") Long goodId, @Param("page") int page, @Param("pageSize") int pageSize);
    // 点赞增加
    public void incrementLikeNumById(@Param("commentId")  Long commentId);
    //    点赞减少
    public void decrementLikeNumById(@Param("commentId")  Long commentId);
}
