package com.macro.mall.mapper;

import com.macro.mall.model.UmsIntegrationRule;
import com.macro.mall.model.UmsIntegrationRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsIntegrationRuleMapper {
    long countByExample(UmsIntegrationRuleExample example);

    int deleteByExample(UmsIntegrationRuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsIntegrationRule record);

    int insertSelective(UmsIntegrationRule record);

    List<UmsIntegrationRule> selectByExample(UmsIntegrationRuleExample example);

    UmsIntegrationRule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsIntegrationRule record, @Param("example") UmsIntegrationRuleExample example);

    int updateByExample(@Param("record") UmsIntegrationRule record, @Param("example") UmsIntegrationRuleExample example);

    int updateByPrimaryKeySelective(UmsIntegrationRule record);

    int updateByPrimaryKey(UmsIntegrationRule record);
}
