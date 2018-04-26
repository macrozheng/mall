package com.macro.mall.dto;

import com.macro.mall.model.*;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 创建和修改商品时使用的参数
 * Created by macro on 2018/4/26.
 */
public class PmsProductParam {
    @ApiModelProperty("商品信息")
    @NotNull(message = "商品信息不能为空")
    private PmsProduct product;
    @ApiModelProperty("商品阶梯价格设置")
    private List<PmsProductLadder> productLadderList;
    @ApiModelProperty("商品满减价格设置")
    private List<PmsProductFullReduction> pmsProductFullReductionList;
    @ApiModelProperty("商品会员价格设置")
    private List<PmsMemberPrice> pmsMemberPriceList;
    @ApiModelProperty("商品的sku库存信息")
    private List<PmsSkuStock> skuStockList;
    @ApiModelProperty("商品参数及自定义规格属性")
    private List<PmsProductAttributeValue> pmsProductAttributeValueList;
    @ApiModelProperty("专题和商品关系")
    private List<CmsSubjectProductRelation> subjectProductRelationList;
    @ApiModelProperty("优选专区和商品的关系")
    private List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList;

    public PmsProduct getProduct() {
        return product;
    }

    public void setProduct(PmsProduct product) {
        this.product = product;
    }

    public List<PmsProductLadder> getProductLadderList() {
        return productLadderList;
    }

    public void setProductLadderList(List<PmsProductLadder> productLadderList) {
        this.productLadderList = productLadderList;
    }

    public List<PmsProductFullReduction> getPmsProductFullReductionList() {
        return pmsProductFullReductionList;
    }

    public void setPmsProductFullReductionList(List<PmsProductFullReduction> pmsProductFullReductionList) {
        this.pmsProductFullReductionList = pmsProductFullReductionList;
    }

    public List<PmsMemberPrice> getPmsMemberPriceList() {
        return pmsMemberPriceList;
    }

    public void setPmsMemberPriceList(List<PmsMemberPrice> pmsMemberPriceList) {
        this.pmsMemberPriceList = pmsMemberPriceList;
    }

    public List<PmsSkuStock> getSkuStockList() {
        return skuStockList;
    }

    public void setSkuStockList(List<PmsSkuStock> skuStockList) {
        this.skuStockList = skuStockList;
    }

    public List<PmsProductAttributeValue> getPmsProductAttributeValueList() {
        return pmsProductAttributeValueList;
    }

    public void setPmsProductAttributeValueList(List<PmsProductAttributeValue> pmsProductAttributeValueList) {
        this.pmsProductAttributeValueList = pmsProductAttributeValueList;
    }

    public List<CmsSubjectProductRelation> getSubjectProductRelationList() {
        return subjectProductRelationList;
    }

    public void setSubjectProductRelationList(List<CmsSubjectProductRelation> subjectProductRelationList) {
        this.subjectProductRelationList = subjectProductRelationList;
    }

    public List<CmsPrefrenceAreaProductRelation> getPrefrenceAreaProductRelationList() {
        return prefrenceAreaProductRelationList;
    }

    public void setPrefrenceAreaProductRelationList(List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList) {
        this.prefrenceAreaProductRelationList = prefrenceAreaProductRelationList;
    }
}
