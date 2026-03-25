package com.macro.mall.dto;

import com.macro.mall.model.PmsProductCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 包含子级分类的商品分类
 * Created by macro on 2018/5/25.
 */
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {
    @Getter
    @Setter
    private List<PmsProductCategory> children;
}
