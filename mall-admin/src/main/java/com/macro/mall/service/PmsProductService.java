package com.macro.mall.service;

import com.macro.mall.dto.PmsProductParam;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品管理Service
 * Created by macro on 2018/4/26.
 */
public interface PmsProductService {
    /**
     * 创建商品
     */
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    int create(PmsProductParam productParam);
}
