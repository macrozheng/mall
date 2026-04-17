package com.macro.mall.mapper;

import com.macro.mall.model.SmsGroupTeam;
import com.macro.mall.model.SmsGroupTeamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsGroupTeamMapper {
    long countByExample(SmsGroupTeamExample example);

    int deleteByExample(SmsGroupTeamExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsGroupTeam record);

    int insertSelective(SmsGroupTeam record);

    List<SmsGroupTeam> selectByExample(SmsGroupTeamExample example);

    SmsGroupTeam selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsGroupTeam record, @Param("example") SmsGroupTeamExample example);

    int updateByExample(@Param("record") SmsGroupTeam record, @Param("example") SmsGroupTeamExample example);

    int updateByPrimaryKeySelective(SmsGroupTeam record);

    int updateByPrimaryKey(SmsGroupTeam record);
}
