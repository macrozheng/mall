package com.macro.mall.promotion.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SkuSpecialPriceParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "SKU ID")
    private Long skuId;

    @ApiModelProperty(value = "SKU编码")
    private String skuCode;

    @ApiModelProperty(value = "原价")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "特价")
    private BigDecimal specialPrice;

    @ApiModelProperty(value = "限购数量")
    private Integer limitCount;
}
