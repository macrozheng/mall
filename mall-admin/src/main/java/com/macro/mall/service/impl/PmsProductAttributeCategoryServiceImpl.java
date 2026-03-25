package com.macro.mall.service.impl;

import com.macro.mall.dto.PmsProductAttributeCategoryItem;
import com.macro.mall.repository.PmsProductAttributeCategoryRepository;
import com.macro.mall.model.PmsProductAttributeCategory;
import com.macro.mall.service.PmsProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品属性分类管理Service实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {
    @Autowired
    private PmsProductAttributeCategoryRepository productAttributeCategoryRepository;

    @Override
    public int create(String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        productAttributeCategoryRepository.save(productAttributeCategory);
        return 1;
    }

    @Override
    public int update(Long id, String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        productAttributeCategory.setId(id);
        productAttributeCategoryRepository.save(productAttributeCategory);
        return 1;
    }

    @Override
    public int delete(Long id) {
        productAttributeCategoryRepository.deleteById(id);
        return 1;
    }

    @Override
    public PmsProductAttributeCategory getItem(Long id) {
        return productAttributeCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum) {
                return productAttributeCategoryRepository.findAll();
    }

    @Override
    public List<PmsProductAttributeCategoryItem> getListWithAttr() {
        // TODO: 实现带属性的列表查询
        return new java.util.ArrayList<>();
    }
}
