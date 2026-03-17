package com.macro.mall.portal.service;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.portal.domain.CommentParam;
import com.macro.mall.portal.dto.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {
//  新建评论
    public Long createComment(CommentParam commentParam);
//  删除评论
    public void deleteComment(Long commentId);
//  获取父节点下的所有评论
    public List<Comment> getSonComments(Long userId,Long parentId);
//  分页
    public List<Comment> getComments(Long goodId,Long userId,int page, int limit);
//  对评论点赞
    public void increaseCommentLike(Long commentId,Long userId);
//  取消点赞
    public void decreaseCommentLike(Long commentId,Long userId);
//  查询用户是否对评论点赞
    public  List<Boolean> getLikeIds(Long id,List<Long> commentIds);
}
