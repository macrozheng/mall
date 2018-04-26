package com.macro.mall.service;

import com.macro.mall.dto.PmsProductParam;

/**
 * 商品管理Service
 * Created by macro on 2018/4/26.
 */
public interface PmsProductService {
    /**
     * 创建商品
     */
    int create(PmsProductParam productParam);
}
