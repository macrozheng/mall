package com.macro.mall.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品分类对应属性信息
 * Created by macro on 2018/5/23.
 */
@Data
@EqualsAndHashCode
public class ProductAttrInfo {
    private Long attributeId;
    private Long attributeCategoryId;
}
