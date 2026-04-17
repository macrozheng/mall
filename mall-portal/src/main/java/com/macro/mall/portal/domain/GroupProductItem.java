package com.macro.mall.portal.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode
public class GroupProductItem {
    @ApiModelProperty("关联ID")
    private Long id;

    @ApiModelProperty("商品ID")
    private Long productId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品主图")
    private String productPic;

    @ApiModelProperty("拼团价格")
    private BigDecimal groupPrice;

    @ApiModelProperty("原价")
    private BigDecimal originalPrice;

    @ApiModelProperty("拼团库存")
    private Integer groupStock;

    @ApiModelProperty("已售库存")
    private Integer soldStock;
}
