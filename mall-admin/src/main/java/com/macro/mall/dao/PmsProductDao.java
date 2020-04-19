package com.macro.mall.dao;

import com.macro.mall.dto.PmsProductResult;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


/**
 * 商品自定义Dao
 * Created by macro on 2018/4/26.
 */
public interface PmsProductDao {
    /**
     * 获取商品编辑信息
     */
    PmsProductResult getUpdateInfo(@Param("id") Long id);

    /**
     * 获取全部商品数量
     */
    Integer getProducts();

    /**
     * 获取上下架商品
     */
    Integer getProductsByQueryParam(@Param("queryParam") Map<String, String> queryParam);
}
