package com.macro.mall.mapper;

import com.macro.mall.model.UmsMemberSignIn;
import com.macro.mall.model.UmsMemberSignInExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberSignInMapper {
    long countByExample(UmsMemberSignInExample example);

    int deleteByExample(UmsMemberSignInExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberSignIn record);

    int insertSelective(UmsMemberSignIn record);

    List<UmsMemberSignIn> selectByExample(UmsMemberSignInExample example);

    UmsMemberSignIn selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMemberSignIn record, @Param("example") UmsMemberSignInExample example);

    int updateByExample(@Param("record") UmsMemberSignIn record, @Param("example") UmsMemberSignInExample example);

    int updateByPrimaryKeySelective(UmsMemberSignIn record);

    int updateByPrimaryKey(UmsMemberSignIn record);
}
