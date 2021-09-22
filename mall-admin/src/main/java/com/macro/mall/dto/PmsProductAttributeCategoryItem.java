package com.macro.mall.dto;

import com.macro.mall.model.PmsProductAttribute;
import com.macro.mall.model.PmsProductAttributeCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 带有属性的商品属性分类
 * Created by macro on 2018/5/24.
 */
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {
    @Getter
    @Setter
    @ApiModelProperty(value = "商品属性列表")
    private List<PmsProductAttribute> productAttributeList;
}
