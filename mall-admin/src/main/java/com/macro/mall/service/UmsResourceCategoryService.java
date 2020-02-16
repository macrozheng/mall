package com.macro.mall.service;

import com.macro.mall.model.UmsResourceCategory;

import java.util.List;

/**
 * 后台资源分类管理Service
 * Created by macro on 2020/2/5.
 */
public interface UmsResourceCategoryService {
    List<UmsResourceCategory> listAll();

    int create(UmsResourceCategory umsResourceCategory);

    int update(Long id, UmsResourceCategory umsResourceCategory);

    int delete(Long id);
}
