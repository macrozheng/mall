package com.macro.mall.portal.domain;

import com.macro.mall.model.PmsProduct;
import com.macro.mall.model.PmsProductAttribute;
import com.macro.mall.model.PmsSkuStock;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 购物车中带规格和SKU的商品信息
 * Created by macro on 2018/8/2.
 */
@Getter
@Setter
public class CartProduct extends PmsProduct {
    @ApiModelProperty("商品属性列表")//包含SKU和商品颜色、手机容量、价格几列
    private List<PmsProductAttribute> productAttributeList;
    @ApiModelProperty("商品SKU库存列表")//包含SKU和对应的库存两列
    private List<PmsSkuStock> skuStockList;
}
