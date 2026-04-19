package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

public class SmsGroupBuyProduct implements Serializable {
    private Long id;

    @ApiModelProperty(value = "拼团活动ID")
    private Long activityId;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "商品SKU ID")
    private Long productSkuId;

    @ApiModelProperty(value = "冗余商品名称")
    private String productName;

    @ApiModelProperty(value = "冗余商品图片")
    private String productPic;

    @ApiModelProperty(value = "冗余SKU编码")
    private String skuCode;

    @ApiModelProperty(value = "原价")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "拼团价")
    private BigDecimal groupPrice;

    @ApiModelProperty(value = "拼团活动库存")
    private Integer groupStock;

    @ApiModelProperty(value = "已锁定库存")
    private Integer lockedStock;

    @ApiModelProperty(value = "已售数量")
    private Integer soldCount;

    @ApiModelProperty(value = "单次下单限购数量")
    private Integer limitPerOrder;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    private static final long serialVersionUID = 1L;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getActivityId() { return activityId; }
    public void setActivityId(Long activityId) { this.activityId = activityId; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public Long getProductSkuId() { return productSkuId; }
    public void setProductSkuId(Long productSkuId) { this.productSkuId = productSkuId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getProductPic() { return productPic; }
    public void setProductPic(String productPic) { this.productPic = productPic; }
    public String getSkuCode() { return skuCode; }
    public void setSkuCode(String skuCode) { this.skuCode = skuCode; }
    public BigDecimal getOriginalPrice() { return originalPrice; }
    public void setOriginalPrice(BigDecimal originalPrice) { this.originalPrice = originalPrice; }
    public BigDecimal getGroupPrice() { return groupPrice; }
    public void setGroupPrice(BigDecimal groupPrice) { this.groupPrice = groupPrice; }
    public Integer getGroupStock() { return groupStock; }
    public void setGroupStock(Integer groupStock) { this.groupStock = groupStock; }
    public Integer getLockedStock() { return lockedStock; }
    public void setLockedStock(Integer lockedStock) { this.lockedStock = lockedStock; }
    public Integer getSoldCount() { return soldCount; }
    public void setSoldCount(Integer soldCount) { this.soldCount = soldCount; }
    public Integer getLimitPerOrder() { return limitPerOrder; }
    public void setLimitPerOrder(Integer limitPerOrder) { this.limitPerOrder = limitPerOrder; }
    public Integer getSort() { return sort; }
    public void setSort(Integer sort) { this.sort = sort; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [id=").append(id);
        sb.append(", activityId=").append(activityId);
        sb.append(", productSkuId=").append(productSkuId);
        sb.append(", groupPrice=").append(groupPrice);
        sb.append(", groupStock=").append(groupStock);
        sb.append(", lockedStock=").append(lockedStock);
        sb.append("]");
        return sb.toString();
    }
}
