package com.macro.mall.mapper;

import com.macro.mall.model.SmsGroupActivity;
import com.macro.mall.model.SmsGroupActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsGroupActivityMapper {
    long countByExample(SmsGroupActivityExample example);

    int deleteByExample(SmsGroupActivityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsGroupActivity record);

    int insertSelective(SmsGroupActivity record);

    List<SmsGroupActivity> selectByExample(SmsGroupActivityExample example);

    SmsGroupActivity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsGroupActivity record, @Param("example") SmsGroupActivityExample example);

    int updateByExample(@Param("record") SmsGroupActivity record, @Param("example") SmsGroupActivityExample example);

    int updateByPrimaryKeySelective(SmsGroupActivity record);

    int updateByPrimaryKey(SmsGroupActivity record);
}
