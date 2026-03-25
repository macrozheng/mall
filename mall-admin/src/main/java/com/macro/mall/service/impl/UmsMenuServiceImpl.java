package com.macro.mall.service.impl;

import com.macro.mall.common.util.SpecificationBuilder;
import com.macro.mall.dto.UmsMenuNode;
import com.macro.mall.repository.UmsMenuRepository;
import com.macro.mall.model.*;
import com.macro.mall.service.UmsMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台菜单管理Service实现类
 * Created by macro on 2020/2/2.
 */
@Service
public class UmsMenuServiceImpl implements UmsMenuService {
    @Autowired
    private UmsMenuRepository menuRepository;

    @Override
    public int create(UmsMenu umsMenu) {
        umsMenu.setCreateTime(new Date());
        updateLevel(umsMenu);
        menuRepository.save(umsMenu);
        return 1;
    }

    /**
     * 修改菜单层级
     */
    private void updateLevel(UmsMenu umsMenu) {
        if (umsMenu.getParentId() == 0) {
            //没有父菜单时为一级菜单
            umsMenu.setLevel(0);
        } else {
            //有父菜单时为父菜单的level+1
            UmsMenu parentMenu = menuRepository.findById(umsMenu.getParentId()).orElse(null);
            if (parentMenu != null) {
                umsMenu.setLevel(parentMenu.getLevel() + 1);
            } else {
                umsMenu.setLevel(0);
            }
        }
    }

    @Override
    public int update(Long id, UmsMenu umsMenu) {
        umsMenu.setId(id);
        updateLevel(umsMenu);
        menuRepository.save(umsMenu);
        return 1;
    }

    @Override
    public UmsMenu getItem(Long id) {
        return menuRepository.findById(id).orElse(null);
    }

    @Override
    public int delete(Long id) {
        menuRepository.deleteById(id);
        return 1;
    }

    @Override
    public List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum) {
        SpecificationBuilder<UmsMenu> builder = SpecificationBuilder.create();
        if (parentId != null) {
            builder.eq("parentId", parentId);
        }
        return menuRepository.findAll(builder.build());
    }

    @Override
    public List<UmsMenuNode> treeList() {
        List<UmsMenu> menuList = menuRepository.findAll();
        List<UmsMenuNode> result = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuList))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public int updateHidden(Long id, Integer hidden) {
        UmsMenu umsMenu = menuRepository.findById(id).orElse(null);
        if (umsMenu == null) {
            return 0;
        }
        umsMenu.setHidden(hidden);
        menuRepository.save(umsMenu);
        return 1;
    }

    /**
     * 将UmsMenu转化为UmsMenuNode并设置children属性
     */
    private UmsMenuNode covertMenuNode(UmsMenu menu, List<UmsMenu> menuList) {
        UmsMenuNode node = new UmsMenuNode();
        BeanUtils.copyProperties(menu, node);
        List<UmsMenuNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
