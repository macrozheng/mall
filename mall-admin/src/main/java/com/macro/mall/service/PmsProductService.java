package com.macro.mall.service;

import com.macro.mall.dto.PmsProductParam;

/**
 * 商品管理Service
 */
public interface PmsProductService {
    /**
     * 创建商品
     */
    int create(PmsProductParam productParam);
}
