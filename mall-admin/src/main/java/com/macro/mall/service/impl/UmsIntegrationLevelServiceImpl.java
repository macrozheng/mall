package com.macro.mall.service.impl;

import com.macro.mall.mapper.UmsIntegrationRuleMapper;
import com.macro.mall.model.UmsIntegrationRule;
import com.macro.mall.model.UmsIntegrationRuleExample;
import com.macro.mall.service.UmsIntegrationRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UmsIntegrationRuleServiceImpl implements UmsIntegrationRuleService {

    @Autowired
    private UmsIntegrationRuleMapper integrationRuleMapper;

    @Override
    public List<UmsIntegrationRule> listAll() {
        return integrationRuleMapper.selectByExample(new UmsIntegrationRuleExample());
    }

    @Override
    public UmsIntegrationRule getById(Long id) {
        return integrationRuleMapper.selectByPrimaryKey(id);
    }

    @Override
    public UmsIntegrationRule getByRuleType(Integer ruleType) {
        UmsIntegrationRuleExample example = new UmsIntegrationRuleExample();
        example.createCriteria().andRuleTypeEqualTo(ruleType);
        List<UmsIntegrationRule> list = integrationRuleMapper.selectByExample(example);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int create(UmsIntegrationRule integrationRule) {
        integrationRule.setCreateTime(new Date());
        integrationRule.setUpdateTime(new Date());
        return integrationRuleMapper.insertSelective(integrationRule);
    }

    @Override
    public int update(Long id, UmsIntegrationRule integrationRule) {
        integrationRule.setId(id);
        integrationRule.setUpdateTime(new Date());
        return integrationRuleMapper.updateByPrimaryKeySelective(integrationRule);
    }

    @Override
    public int delete(Long id) {
        return integrationRuleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        UmsIntegrationRule rule = new UmsIntegrationRule();
        rule.setId(id);
        rule.setStatus(status);
        rule.setUpdateTime(new Date());
        return integrationRuleMapper.updateByPrimaryKeySelective(rule);
    }
}
