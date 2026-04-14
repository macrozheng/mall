package com.macro.mall.promotion.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PackageParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "套餐名称")
    private String name;

    @ApiModelProperty(value = "套餐价格")
    private BigDecimal packagePrice;

    @ApiModelProperty(value = "原价总和")
    private BigDecimal originalTotalPrice;

    @ApiModelProperty(value = "限购数量")
    private Integer limitCount;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "套餐商品明细")
    private List<PackageItemParam> items;
}
