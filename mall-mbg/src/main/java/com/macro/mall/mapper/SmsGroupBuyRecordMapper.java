package com.macro.mall.mapper;

import com.macro.mall.model.SmsGroupBuyRecord;

public interface SmsGroupBuyRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsGroupBuyRecord record);

    int insertSelective(SmsGroupBuyRecord record);

    SmsGroupBuyRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsGroupBuyRecord record);

    int updateByPrimaryKey(SmsGroupBuyRecord record);
}
