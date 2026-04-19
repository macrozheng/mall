package com.macro.mall.mapper;

import com.macro.mall.model.SmsGroupBuyActivity;

public interface SmsGroupBuyActivityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsGroupBuyActivity record);

    int insertSelective(SmsGroupBuyActivity record);

    SmsGroupBuyActivity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsGroupBuyActivity record);

    int updateByPrimaryKey(SmsGroupBuyActivity record);
}
