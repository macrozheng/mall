package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class SmsCouponExtension implements Serializable {
    private Long id;

    @ApiModelProperty(value = "优惠券ID")
    private Long couponId;

    @ApiModelProperty(value = "是否可叠加：0->不可叠加；1->可叠加")
    private Boolean stackable;

    @ApiModelProperty(value = "互斥优惠券ID列表，逗号分隔")
    private String exclusiveWithCoupons;

    @ApiModelProperty(value = "互斥营销活动ID列表，逗号分隔")
    private String exclusiveWithPromotions;

    @ApiModelProperty(value = "是否可与营销活动共用：0->不可；1->可以")
    private Boolean canUseWithPromotion;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Boolean getStackable() {
        return stackable;
    }

    public void setStackable(Boolean stackable) {
        this.stackable = stackable;
    }

    public String getExclusiveWithCoupons() {
        return exclusiveWithCoupons;
    }

    public void setExclusiveWithCoupons(String exclusiveWithCoupons) {
        this.exclusiveWithCoupons = exclusiveWithCoupons;
    }

    public String getExclusiveWithPromotions() {
        return exclusiveWithPromotions;
    }

    public void setExclusiveWithPromotions(String exclusiveWithPromotions) {
        this.exclusiveWithPromotions = exclusiveWithPromotions;
    }

    public Boolean getCanUseWithPromotion() {
        return canUseWithPromotion;
    }

    public void setCanUseWithPromotion(Boolean canUseWithPromotion) {
        this.canUseWithPromotion = canUseWithPromotion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", couponId=").append(couponId);
        sb.append(", stackable=").append(stackable);
        sb.append(", exclusiveWithCoupons=").append(exclusiveWithCoupons);
        sb.append(", exclusiveWithPromotions=").append(exclusiveWithPromotions);
        sb.append(", canUseWithPromotion=").append(canUseWithPromotion);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
