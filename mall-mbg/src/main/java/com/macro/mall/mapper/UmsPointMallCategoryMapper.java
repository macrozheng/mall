package com.macro.mall.mapper;

import com.macro.mall.model.UmsPointMallCategory;
import com.macro.mall.model.UmsPointMallCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsPointMallCategoryMapper {
    long countByExample(UmsPointMallCategoryExample example);

    int deleteByExample(UmsPointMallCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsPointMallCategory record);

    int insertSelective(UmsPointMallCategory record);

    List<UmsPointMallCategory> selectByExample(UmsPointMallCategoryExample example);

    UmsPointMallCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsPointMallCategory record, @Param("example") UmsPointMallCategoryExample example);

    int updateByExample(@Param("record") UmsPointMallCategory record, @Param("example") UmsPointMallCategoryExample example);

    int updateByPrimaryKeySelective(UmsPointMallCategory record);

    int updateByPrimaryKey(UmsPointMallCategory record);
}
