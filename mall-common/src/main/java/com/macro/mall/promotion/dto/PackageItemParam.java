package com.macro.mall.promotion.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PackageItemParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "SKU ID（可选，为空表示该商品所有SKU）")
    private Long skuId;

    @ApiModelProperty(value = "数量")
    private Integer quantity;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;
}
