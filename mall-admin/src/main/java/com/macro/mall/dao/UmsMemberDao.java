package com.macro.mall.dao;

import com.macro.mall.model.UmsMember;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UmsMemberDao {
    int deleteByPrimaryKey(Long id);

    int insert(UmsMember record);

    int insertSelective(UmsMember record);

    UmsMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsMember record);

    int updateByPrimaryKey(UmsMember record);

    /**
     * 获取全部用户
     */
    int getUsersCount();

    /**
     * 获取今日，昨日，本周，近一月内的用户
     */
    int getUsersCountByParam(@Param("queryParam")Map<String, String> queryParam);
}