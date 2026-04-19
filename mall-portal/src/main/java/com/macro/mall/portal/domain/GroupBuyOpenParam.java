package com.macro.mall.portal.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * 开团入参
 */
public class GroupBuyOpenParam {

    @ApiModelProperty(value = "拼团活动ID", required = true)
    private Long activityId;

    @ApiModelProperty(value = "商品SKU ID", required = true)
    private Long productSkuId;

    @ApiModelProperty(value = "购买数量", required = true)
    private Integer quantity;

    @ApiModelProperty(value = "收货地址ID", required = true)
    private Long memberReceiveAddressId;

    public Long getActivityId() { return activityId; }
    public void setActivityId(Long activityId) { this.activityId = activityId; }
    public Long getProductSkuId() { return productSkuId; }
    public void setProductSkuId(Long productSkuId) { this.productSkuId = productSkuId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Long getMemberReceiveAddressId() { return memberReceiveAddressId; }
    public void setMemberReceiveAddressId(Long memberReceiveAddressId) { this.memberReceiveAddressId = memberReceiveAddressId; }
}
