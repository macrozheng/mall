package com.macro.mall.portal.domain;

import com.macro.mall.model.PmsProduct;
import com.macro.mall.model.PmsProductAttribute;
import com.macro.mall.model.PmsSkuStock;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 购物车中带商品属性和SKU库存的商品对象
 * Created by macro on 2018/8/2.
 */
@Getter
@Setter
public class CartProduct extends PmsProduct {
    private List<PmsProductAttribute> productAttributeList;
    private List<PmsSkuStock> skuStockList;
}
