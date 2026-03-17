package com.macro.mall.portal.controller;

import cn.hutool.core.annotation.MirrorFor;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.portal.domain.CommentParam;
import com.macro.mall.portal.dto.Comment;
import com.macro.mall.portal.dto.UserMemberHolder;
import com.macro.mall.portal.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "CommentController")
@Tag(name = "CommentController", description = "评论相关接口")
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ApiOperation("获取评论")
    @GetMapping("/getComments/{goodId}/{page}")
    public CommonResult<List<Comment>> getComments(@PathVariable Long goodId, @PathVariable int page, @RequestParam("limit") int limit){
        return CommonResult.success(commentService.getComments(goodId, UserMemberHolder.get().getId(), page,limit));
    }

    @ApiOperation("获取父评论下的子评论")
    @GetMapping("/getSonComments/{commentId}")
    public CommonResult<List<Comment>> getSonComments(@PathVariable Long commentId){
        return CommonResult.success(commentService.getSonComments(UserMemberHolder.get().getId(), commentId));
    }

    @ApiOperation("取消点赞")
    @PostMapping("/decreaseLike")
    public CommonResult decreaseLike(@RequestParam("commentId") Long commentId){
        commentService.decreaseCommentLike(commentId, UserMemberHolder.get().getId());
        return CommonResult.success(null);
    }

    @ApiOperation("点赞")
    @PostMapping("/increaseLike")
    public CommonResult increaseCommentLike(@RequestParam("commentId") Long commentId){
        commentService.increaseCommentLike(commentId, UserMemberHolder.get().getId());
        return CommonResult.success(null);
    }

    @ApiOperation("添加评论")
    @PostMapping("/addComment")
    public CommonResult addComment(@RequestBody CommentParam commentParam){
        commentService.createComment(commentParam);
        return CommonResult.success(null);
    }

    @ApiOperation("删除评论")
    @PostMapping("/deleteComment")
    public CommonResult deleteComment(@RequestParam("commentId") Long commentId){
        commentService.deleteComment(commentId);
        return CommonResult.success(null);
    }
}
