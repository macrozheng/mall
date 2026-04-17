package com.macro.mall.mapper;

import com.macro.mall.model.UmsIntegrationLevel;
import com.macro.mall.model.UmsIntegrationLevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsIntegrationLevelMapper {
    long countByExample(UmsIntegrationLevelExample example);

    int deleteByExample(UmsIntegrationLevelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsIntegrationLevel record);

    int insertSelective(UmsIntegrationLevel record);

    List<UmsIntegrationLevel> selectByExample(UmsIntegrationLevelExample example);

    UmsIntegrationLevel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsIntegrationLevel record, @Param("example") UmsIntegrationLevelExample example);

    int updateByExample(@Param("record") UmsIntegrationLevel record, @Param("example") UmsIntegrationLevelExample example);

    int updateByPrimaryKeySelective(UmsIntegrationLevel record);

    int updateByPrimaryKey(UmsIntegrationLevel record);
}
