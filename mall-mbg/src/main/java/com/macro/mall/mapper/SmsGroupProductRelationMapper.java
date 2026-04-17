package com.macro.mall.mapper;

import com.macro.mall.model.SmsGroupProductRelation;
import com.macro.mall.model.SmsGroupProductRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsGroupProductRelationMapper {
    long countByExample(SmsGroupProductRelationExample example);

    int deleteByExample(SmsGroupProductRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsGroupProductRelation record);

    int insertSelective(SmsGroupProductRelation record);

    List<SmsGroupProductRelation> selectByExample(SmsGroupProductRelationExample example);

    SmsGroupProductRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsGroupProductRelation record, @Param("example") SmsGroupProductRelationExample example);

    int updateByExample(@Param("record") SmsGroupProductRelation record, @Param("example") SmsGroupProductRelationExample example);

    int updateByPrimaryKeySelective(SmsGroupProductRelation record);

    int updateByPrimaryKey(SmsGroupProductRelation record);
}
