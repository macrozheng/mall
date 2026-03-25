package com.macro.mall.service.impl;

import com.macro.mall.common.util.SpecificationBuilder;
import com.macro.mall.repository.UmsResourceCategoryRepository;
import com.macro.mall.model.UmsResourceCategory;
import com.macro.mall.service.UmsResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台资源分类管理Service实现类
 * Created by macro on 2020/2/5.
 */
@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {
    @Autowired
    private UmsResourceCategoryRepository resourceCategoryRepository;

    @Override
    public List<UmsResourceCategory> listAll() {
        return resourceCategoryRepository.findAll();
    }

    @Override
    public int create(UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setCreateTime(new Date());
        resourceCategoryRepository.save(umsResourceCategory);
        return 1;
    }

    @Override
    public int update(Long id, UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setId(id);
        resourceCategoryRepository.save(umsResourceCategory);
        return 1;
    }

    @Override
    public int delete(Long id) {
        resourceCategoryRepository.deleteById(id);
        return 1;
    }
}
