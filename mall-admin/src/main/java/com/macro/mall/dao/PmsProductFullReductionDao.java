package com.macro.mall.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义商品满减Dao
 * Created by macro on 2018/4/26.
 */
public interface PmsProductFullReductionDao {
    int insertList(@Param("list") List<PmsProductFullReduction> productFullReductionList);
}
