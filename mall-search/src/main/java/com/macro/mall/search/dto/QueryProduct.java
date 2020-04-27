package com.macro.mall.search.dto;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

@ApiModel("商品搜索参数")
@lombok.Data
public class QueryProduct{
    /**
     * 关键词
     */
    @ApiModelProperty("搜索keyword")
    private String keyword;

    @ApiModelProperty("商品ID")
    private Long id;
    @ApiModelProperty("商品SN")
    private String productSn;

    @ApiModelProperty("品牌ID")
    private Long brandId;
    @ApiModelProperty("品牌名称")
    private String brandName;

    @ApiModelProperty("分类ID")
    private Long productCategoryId;
    @ApiModelProperty("分类名称")
    private String productCategoryName;

    /*
     * @Name: 规格参数
     * @Example: [
        {
            "attrName": "颜色",
            "attrValues": ["黑色"]
        },
        {
            "attrName": "内存",
            "attrValues": ["64G", "128G"]
        }
     ]
     * @Description:
     */
    @ApiModelProperty("扩展属性")
    private List<ProductAttr> productAttrs = Lists.newArrayList();

    @ApiModelProperty("价格区间-最低")
    private BigDecimal minPrice;
    @ApiModelProperty("价格区间-最高")
    private BigDecimal maxPrice;

    @ApiModelProperty("排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低")
    private Integer sort = 0;

    @ApiModelProperty("分页数据-页数")
    private Integer page = 1;
    @ApiModelProperty("分页数据-页大小")
    private Integer size = 10;

    @lombok.Data
    public static class ProductAttr{
        @ApiModelProperty("属性ID，来源于商品类型的属性和参数")
        private Long attrId;
        @ApiModelProperty("属性名称")
        private String attrName;
        @ApiModelProperty("属性值，可以多个")
        private List<String> attrValues;
    }

}
