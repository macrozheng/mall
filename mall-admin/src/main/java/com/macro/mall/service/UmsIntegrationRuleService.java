package com.macro.mall.service;

import com.macro.mall.model.UmsIntegrationRule;

import java.util.List;

public interface UmsIntegrationRuleService {

    List<UmsIntegrationRule> listAll();

    UmsIntegrationRule getById(Long id);

    UmsIntegrationRule getByRuleType(Integer ruleType);

    int create(UmsIntegrationRule integrationRule);

    int update(Long id, UmsIntegrationRule integrationRule);

    int delete(Long id);

    int updateStatus(Long id, Integer status);
}
