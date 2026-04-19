package com.macro.mall.dto;

import com.macro.mall.model.SmsGroupBuyProduct;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 批量添加拼团商品入参
 */
public class SmsGroupBuyProductParam {
    @ApiModelProperty(value = "拼团活动ID", required = true)
    private Long activityId;

    @ApiModelProperty(value = "商品SKU列表")
    private List<SmsGroupBuyProduct> productList;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public List<SmsGroupBuyProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<SmsGroupBuyProduct> productList) {
        this.productList = productList;
    }
}
