package com.macro.mall.service.impl;

import com.macro.mall.common.util.SpecificationBuilder;
import cn.hutool.core.util.StrUtil;
import com.macro.mall.repository.UmsRoleRepository;
import com.macro.mall.repository.UmsRoleMenuRelationRepository;
import com.macro.mall.repository.UmsRoleResourceRelationRepository;
import com.macro.mall.model.*;
import com.macro.mall.service.UmsAdminCacheService;
import com.macro.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台角色管理Service实现类
 * Created by macro on 2018/9/30.
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {
    @Autowired
    private UmsRoleRepository roleRepository;
    @Autowired
    private UmsRoleMenuRelationRepository roleMenuRelationRepository;
    @Autowired
    private UmsRoleResourceRelationRepository roleResourceRelationRepository;
    @Autowired
    private UmsAdminCacheService adminCacheService;

    @Override
    public int create(UmsRole role) {
        role.setCreateTime(new Date());
        role.setAdminCount(0);
        role.setSort(0);
        roleRepository.save(role);
        return 1;
    }

    @Override
    public int update(Long id, UmsRole role) {
        role.setId(id);
        roleRepository.save(role);
        return 1;
    }

    @Override
    public int delete(List<Long> ids) {
        List<UmsRole> roles = roleRepository.findAllById(ids);
        roleRepository.deleteAll(roles);
        adminCacheService.delResourceListByRoleIds(ids);
        return ids.size();
    }

    @Override
    public List<UmsRole> list() {
        return roleRepository.findAll();
    }

    @Override
    public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        SpecificationBuilder<UmsRole> builder = SpecificationBuilder.create();
        if (!StrUtil.isEmpty(keyword)) {
            builder.like("name", keyword);
        }
        return roleRepository.findAll(builder.build());
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        // TODO: 实现查询菜单列表
        return new java.util.ArrayList<>();
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        // TODO: 实现根据角色ID查询菜单
        return new java.util.ArrayList<>();
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        // TODO: 实现根据角色ID查询资源
        return new java.util.ArrayList<>();
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        //先删除原有关系
        deleteRoleMenuRelations(roleId);
        //批量插入新关系
        for (Long menuId : menuIds) {
            UmsRoleMenuRelation relation = new UmsRoleMenuRelation();
            relation.setRoleId(roleId);
            relation.setMenuId(menuId);
            roleMenuRelationRepository.save(relation);
        }
        return menuIds.size();
    }

    private void deleteRoleMenuRelations(Long roleId) {
        SpecificationBuilder<UmsRoleMenuRelation> builder = SpecificationBuilder.create();
        builder.eq("roleId", roleId);
        List<UmsRoleMenuRelation> relations = roleMenuRelationRepository.findAll(builder.build());
        if (!relations.isEmpty()) {
            roleMenuRelationRepository.deleteAll(relations);
        }
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        //先删除原有关系
        deleteRoleResourceRelations(roleId);
        //批量插入新关系
        for (Long resourceId : resourceIds) {
            UmsRoleResourceRelation relation = new UmsRoleResourceRelation();
            relation.setRoleId(roleId);
            relation.setResourceId(resourceId);
            roleResourceRelationRepository.save(relation);
        }
        adminCacheService.delResourceListByRole(roleId);
        return resourceIds.size();
    }

    private void deleteRoleResourceRelations(Long roleId) {
        SpecificationBuilder<UmsRoleResourceRelation> builder = SpecificationBuilder.create();
        builder.eq("roleId", roleId);
        List<UmsRoleResourceRelation> relations = roleResourceRelationRepository.findAll(builder.build());
        if (!relations.isEmpty()) {
            roleResourceRelationRepository.deleteAll(relations);
        }
    }
}
