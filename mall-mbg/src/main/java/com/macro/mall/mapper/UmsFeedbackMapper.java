package com.macro.mall.mapper;

import com.macro.mall.model.UmsFeedback;
import com.macro.mall.model.UmsFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsFeedbackMapper {
    long countByExample(UmsFeedbackExample example);

    int deleteByExample(UmsFeedbackExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsFeedback record);

    int insertSelective(UmsFeedback record);

    List<UmsFeedback> selectByExample(UmsFeedbackExample example);

    UmsFeedback selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsFeedback record, @Param("example") UmsFeedbackExample example);

    int updateByExample(@Param("record") UmsFeedback record, @Param("example") UmsFeedbackExample example);

    int updateByPrimaryKeySelective(UmsFeedback record);

    int updateByPrimaryKey(UmsFeedback record);
}