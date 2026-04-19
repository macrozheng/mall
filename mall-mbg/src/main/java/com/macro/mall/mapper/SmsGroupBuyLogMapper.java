package com.macro.mall.mapper;

import com.macro.mall.model.SmsGroupBuyLog;

public interface SmsGroupBuyLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsGroupBuyLog record);

    int insertSelective(SmsGroupBuyLog record);

    SmsGroupBuyLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsGroupBuyLog record);

    int updateByPrimaryKey(SmsGroupBuyLog record);
}
