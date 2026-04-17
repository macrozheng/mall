package com.macro.mall.mapper;

import com.macro.mall.model.UmsIntegrationExpireRecord;
import com.macro.mall.model.UmsIntegrationExpireRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsIntegrationExpireRecordMapper {
    long countByExample(UmsIntegrationExpireRecordExample example);

    int deleteByExample(UmsIntegrationExpireRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsIntegrationExpireRecord record);

    int insertSelective(UmsIntegrationExpireRecord record);

    List<UmsIntegrationExpireRecord> selectByExample(UmsIntegrationExpireRecordExample example);

    UmsIntegrationExpireRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsIntegrationExpireRecord record, @Param("example") UmsIntegrationExpireRecordExample example);

    int updateByExample(@Param("record") UmsIntegrationExpireRecord record, @Param("example") UmsIntegrationExpireRecordExample example);

    int updateByPrimaryKeySelective(UmsIntegrationExpireRecord record);

    int updateByPrimaryKey(UmsIntegrationExpireRecord record);
}
