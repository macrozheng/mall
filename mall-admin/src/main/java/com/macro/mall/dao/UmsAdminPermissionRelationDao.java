package com.macro.mall.dao;

import com.macro.mall.model.UmsAdminPermissionRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义用户权限关系管理Dao
 * Created by macro on 2018/10/8.
 */
public interface UmsAdminPermissionRelationDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<UmsAdminPermissionRelation> list);
}
