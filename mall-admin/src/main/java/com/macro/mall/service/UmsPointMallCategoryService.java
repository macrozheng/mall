package com.macro.mall.service;

import com.macro.mall.model.UmsIntegrationLevel;

import java.util.List;

public interface UmsIntegrationLevelService {

    List<UmsIntegrationLevel> listAll();

    UmsIntegrationLevel getById(Long id);

    int create(UmsIntegrationLevel integrationLevel);

    int update(Long id, UmsIntegrationLevel integrationLevel);

    int delete(Long id);

    int updateStatus(Long id, Integer status);

    UmsIntegrationLevel getByIntegration(Integer integration);
}
