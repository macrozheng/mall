package com.macro.mall.service;

import com.macro.mall.dto.UmsPermissionNode;
import com.macro.mall.model.UmsPermission;

import java.util.List;

/**
 * 后台用户权限管理Service
 * Created by macro on 2018/9/29.
 */
public interface UmsPermissionService {
    /**
     * 添加权限
     */
    int create(UmsPermission permission);

    /**
     * 修改权限
     */
    int update(Long id,UmsPermission permission);

    /**
     * 批量删除权限
     */
    int delete(List<Long> ids);

    /**
     * 以层级结构返回所有权限
     */
    List<UmsPermissionNode> treeList();

    /**
     * 获取所有权限
     */
    List<UmsPermission> list();
}
