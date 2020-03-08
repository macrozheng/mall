package com.macro.mall.dao;

import com.macro.mall.dto.PmsProductAttributeCategoryItem;

import java.util.List;

/**
 * 自定义商品属性分类Dao
 * Created by macro on 2018/5/24.
 */
public interface PmsProductAttributeCategoryDao {
    /**
     * 获取包含商品属性的商品属性分类列表
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}
