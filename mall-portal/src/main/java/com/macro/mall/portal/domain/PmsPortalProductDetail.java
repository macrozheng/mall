package com.macro.mall.portal.domain;

import com.macro.mall.model.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 前台商品详情
 * Created by macro on 2020/4/6.
 */
@Getter
@Setter
public class PmsPortalProductDetail{
    private PmsProduct product;
    private PmsBrand brand;
    private List<PmsProductAttribute> productAttributeList;
    private List<PmsProductAttributeValue> productAttributeValueList;
    private List<PmsSkuStock> skuStockList;
    private List<PmsProductLadder> productLadderList;
    private List<PmsProductFullReduction> productFullReductionList;
    private List<SmsCoupon> couponList;
}
