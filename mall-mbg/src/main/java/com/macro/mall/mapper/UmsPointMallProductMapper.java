package com.macro.mall.mapper;

import com.macro.mall.model.UmsPointMallProduct;
import com.macro.mall.model.UmsPointMallProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsPointMallProductMapper {
    long countByExample(UmsPointMallProductExample example);

    int deleteByExample(UmsPointMallProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsPointMallProduct record);

    int insertSelective(UmsPointMallProduct record);

    List<UmsPointMallProduct> selectByExample(UmsPointMallProductExample example);

    UmsPointMallProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsPointMallProduct record, @Param("example") UmsPointMallProductExample example);

    int updateByExample(@Param("record") UmsPointMallProduct record, @Param("example") UmsPointMallProductExample example);

    int updateByPrimaryKeySelective(UmsPointMallProduct record);

    int updateByPrimaryKey(UmsPointMallProduct record);
}
