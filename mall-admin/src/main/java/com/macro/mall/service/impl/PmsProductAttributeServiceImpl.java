package com.macro.mall.service.impl;

import com.macro.mall.mapper.PmsProductAttributeMapper;
import com.macro.mall.service.PmsProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品属性Service实现类
 */
@Service
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService{
    @Autowired
    private PmsProductAttributeMapper productAttributeMapper;
}
