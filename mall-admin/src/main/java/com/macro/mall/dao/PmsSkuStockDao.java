package com.macro.mall.dao;

import com.macro.mall.model.PmsSkuStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义商品sku库存Dao
 * Created by macro on 2018/4/26.
 */
public interface PmsSkuStockDao {
    int insertList(@Param("list")List<PmsSkuStock> skuStockList);
}
