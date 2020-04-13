package com.macro.mall.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义优选和商品关系操作
 * Created by macro on 2018/4/26.
 */
public interface CmsPrefrenceAreaProductRelationDao {
    int insertList(@Param("list") List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList);
}
