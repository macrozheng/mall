package com.macro.mall.service;

import com.macro.mall.model.UmsResource;

import java.util.List;

/**
 * 后台资源管理Service
 * Created by macro on 2020/2/2.
 */
public interface UmsResourceService {
    int create(UmsResource umsResource);

    int update(Long id, UmsResource umsResource);

    UmsResource getItem(Long id);

    int delete(Long id);

    List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    List<UmsResource> listAll();
}
