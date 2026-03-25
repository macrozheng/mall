package com.macro.mall.dto;

import com.macro.mall.model.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 创建和修改商品的请求参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode
public class PmsProductParam extends PmsProduct{
    private List<PmsProductLadder> productLadderList;
    private List<PmsProductFullReduction> productFullReductionList;
    private List<PmsMemberPrice> memberPriceList;
    private List<PmsSkuStock> skuStockList;
    private List<PmsProductAttributeValue> productAttributeValueList;
    private List<CmsSubjectProductRelation> subjectProductRelationList;
    private List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList;
}
