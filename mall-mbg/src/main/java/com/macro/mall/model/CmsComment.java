package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class CmsComment implements Serializable {
    private Long id;

    @ApiModelProperty(value = "父评论id 0表示根评论")
    private Integer parentId;

    @ApiModelProperty(value = "点赞数")
    private Integer likeNum;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "得分，用于降序排序")
    private Long score;

    @ApiModelProperty(value = "回复数量")
    private Integer repostNum;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "评论ID")
    private Long contentId;

    @ApiModelProperty(value = "属于的商品ID")
    private Long goodId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Integer getRepostNum() {
        return repostNum;
    }

    public void setRepostNum(Integer repostNum) {
        this.repostNum = repostNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", likeNum=").append(likeNum);
        sb.append(", createTime=").append(createTime);
        sb.append(", score=").append(score);
        sb.append(", repostNum=").append(repostNum);
        sb.append(", userId=").append(userId);
        sb.append(", contentId=").append(contentId);
        sb.append(", goodId=").append(goodId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}