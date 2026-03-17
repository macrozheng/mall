package com.macro.mall.portal.dao;

import cn.hutool.log.Log;
import com.macro.mall.model.CmsUserCommentLike;
import com.macro.mall.portal.dto.Comment;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface CommentDao {
    //  获取父节点下的所有评论
    public List<Comment> getSonComments(@Param("parentId") Long parentId);
    //  分页
    public List<Comment> getComments(@Param("goodId") Long goodId, @Param("page") int page, @Param("pageSize") int pageSize);
    // 点赞增加
    public void incrementLikeNumById(@Param("commentId")  Long commentId);
    //    点赞减少
    public void decrementLikeNumById(@Param("commentId")  Long commentId);
//    查看用户是否对评论点赞
    public Boolean existsByCommentIdAndUserId(@Param("userId") Long userId,@Param("commentId")  Long commentId);

//    查找所有用户和点赞过的评论的关系
    public List<CmsUserCommentLike> selectUserCommentLikeAll();
//    批量更新点赞数量
    public void updateLikeNumBatch(@Param("likeMap") Map<Long,Integer> likeMap);

}
