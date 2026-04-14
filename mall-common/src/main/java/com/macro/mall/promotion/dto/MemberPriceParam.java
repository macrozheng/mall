package com.macro.mall.promotion.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MemberPriceParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "SKU ID（可选）")
    private Long skuId;

    @ApiModelProperty(value = "会员等级ID")
    private Long memberLevelId;

    @ApiModelProperty(value = "会员等级名称")
    private String memberLevelName;

    @ApiModelProperty(value = "原价")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "会员价")
    private BigDecimal memberPrice;
}
