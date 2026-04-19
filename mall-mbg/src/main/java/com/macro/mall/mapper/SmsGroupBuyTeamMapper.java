package com.macro.mall.mapper;

import com.macro.mall.model.SmsGroupBuyTeam;

public interface SmsGroupBuyTeamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsGroupBuyTeam record);

    int insertSelective(SmsGroupBuyTeam record);

    SmsGroupBuyTeam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsGroupBuyTeam record);

    int updateByPrimaryKey(SmsGroupBuyTeam record);
}
