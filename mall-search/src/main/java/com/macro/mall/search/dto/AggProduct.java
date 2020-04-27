package com.macro.mall.search.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel("商品搜索统计结果")
@lombok.Data
public class AggProduct{

   //Buckets

    //产品属性 brandName, productCategoryName
    @ApiModelProperty("产品共用属性，如brandName, productCategoryName")
    private List<ProductAttr>   productProps = new ArrayList<>();

    //扩展属性 productAttrsBuckets
    @ApiModelProperty("产品扩展属性，来自商品类型中属性和参数")
    private List<ProductAttr>   productAttrs = new ArrayList<>();

    @lombok.Data
    public static class ProductAttr{
        @ApiModelProperty("属性ID，来源于商品类型的属性和参数")
        private Long attrId;
        @ApiModelProperty("属性名称")
        private String attrName;
        @ApiModelProperty("属性值统计")
        private List<AttrValue> attrValues= new ArrayList<>();
    }

    @lombok.Data
    public static class AttrValue{
        @ApiModelProperty("属性值")
        private String attrValue;
        @ApiModelProperty("统计数")
        private Long count;
        public AttrValue(){
        }

        public AttrValue(String attrValue, Long count){
            this.attrValue = attrValue;
            this.count = count;
        }

    }

}
