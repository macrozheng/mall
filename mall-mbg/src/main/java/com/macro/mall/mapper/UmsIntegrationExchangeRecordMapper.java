package com.macro.mall.mapper;

import com.macro.mall.model.UmsIntegrationExchangeRecord;
import com.macro.mall.model.UmsIntegrationExchangeRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsIntegrationExchangeRecordMapper {
    long countByExample(UmsIntegrationExchangeRecordExample example);

    int deleteByExample(UmsIntegrationExchangeRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsIntegrationExchangeRecord record);

    int insertSelective(UmsIntegrationExchangeRecord record);

    List<UmsIntegrationExchangeRecord> selectByExample(UmsIntegrationExchangeRecordExample example);

    UmsIntegrationExchangeRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsIntegrationExchangeRecord record, @Param("example") UmsIntegrationExchangeRecordExample example);

    int updateByExample(@Param("record") UmsIntegrationExchangeRecord record, @Param("example") UmsIntegrationExchangeRecordExample example);

    int updateByPrimaryKeySelective(UmsIntegrationExchangeRecord record);

    int updateByPrimaryKey(UmsIntegrationExchangeRecord record);
}
