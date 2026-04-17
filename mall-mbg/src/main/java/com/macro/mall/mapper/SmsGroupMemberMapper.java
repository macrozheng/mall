package com.macro.mall.mapper;

import com.macro.mall.model.SmsGroupMember;
import com.macro.mall.model.SmsGroupMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsGroupMemberMapper {
    long countByExample(SmsGroupMemberExample example);

    int deleteByExample(SmsGroupMemberExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsGroupMember record);

    int insertSelective(SmsGroupMember record);

    List<SmsGroupMember> selectByExample(SmsGroupMemberExample example);

    SmsGroupMember selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsGroupMember record, @Param("example") SmsGroupMemberExample example);

    int updateByExample(@Param("record") SmsGroupMember record, @Param("example") SmsGroupMemberExample example);

    int updateByPrimaryKeySelective(SmsGroupMember record);

    int updateByPrimaryKey(SmsGroupMember record);
}
