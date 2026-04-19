package com.macro.mall.mapper;

import com.macro.mall.model.SmsGroupBuyProduct;

public interface SmsGroupBuyProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsGroupBuyProduct record);

    int insertSelective(SmsGroupBuyProduct record);

    SmsGroupBuyProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsGroupBuyProduct record);

    int updateByPrimaryKey(SmsGroupBuyProduct record);
}
