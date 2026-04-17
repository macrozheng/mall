package com.macro.mall.mapper;

import com.macro.mall.model.UmsIntegrationExpireSetting;
import com.macro.mall.model.UmsIntegrationExpireSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsIntegrationExpireSettingMapper {
    long countByExample(UmsIntegrationExpireSettingExample example);

    int deleteByExample(UmsIntegrationExpireSettingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsIntegrationExpireSetting record);

    int insertSelective(UmsIntegrationExpireSetting record);

    List<UmsIntegrationExpireSetting> selectByExample(UmsIntegrationExpireSettingExample example);

    UmsIntegrationExpireSetting selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsIntegrationExpireSetting record, @Param("example") UmsIntegrationExpireSettingExample example);

    int updateByExample(@Param("record") UmsIntegrationExpireSetting record, @Param("example") UmsIntegrationExpireSettingExample example);

    int updateByPrimaryKeySelective(UmsIntegrationExpireSetting record);

    int updateByPrimaryKey(UmsIntegrationExpireSetting record);
}
