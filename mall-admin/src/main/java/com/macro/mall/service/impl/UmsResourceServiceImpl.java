package com.macro.mall.service.impl;


import com.macro.mall.common.util.SpecificationBuilder;
import cn.hutool.core.util.StrUtil;
import com.macro.mall.repository.UmsResourceRepository;
import com.macro.mall.model.UmsResource;
import com.macro.mall.service.UmsAdminCacheService;
import com.macro.mall.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台资源管理Service实现类
 * Created by macro on 2020/2/2.
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {
    @Autowired
    private UmsResourceRepository resourceRepository;
    @Autowired
    private UmsAdminCacheService adminCacheService;
    @Override
    public int create(UmsResource umsResource) {
        umsResource.setCreateTime(new Date());
        resourceRepository.save(umsResource);
        return 1;
    }

    @Override
    public int update(Long id, UmsResource umsResource) {
        umsResource.setId(id);
        resourceRepository.save(umsResource);
        adminCacheService.delResourceListByResource(id);
        return 1;
    }

    @Override
    public UmsResource getItem(Long id) {
        return resourceRepository.findById(id).orElse(null);
    }

    @Override
    public int delete(Long id) {
        resourceRepository.deleteById(id);
        adminCacheService.delResourceListByResource(id);
        return 1;
    }

    @Override
    public List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        SpecificationBuilder<UmsResource> builder = SpecificationBuilder.create();
        if(categoryId!=null){
            builder.eq("categoryId", categoryId);
        }
        if(StrUtil.isNotEmpty(nameKeyword)){
            builder.like("name", nameKeyword);
        }
        if(StrUtil.isNotEmpty(urlKeyword)){
            builder.like("url", urlKeyword);
        }
        return resourceRepository.findAll(builder.build());
    }

    @Override
    public List<UmsResource> listAll() {
        return resourceRepository.findAll();
    }
}
