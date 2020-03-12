package com.macro.mall.dao;

import com.macro.mall.dto.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * 商品分类自定义Dao
 * Created by macro on 2018/5/25.
 */
public interface PmsProductCategoryDao {
    /**
     * 获取包括子分类的商品分类列表
     */
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
