package com.macro.mall.service.impl;

import com.macro.mall.common.util.SpecificationBuilder;
import com.macro.mall.repository.UmsMemberLevelRepository;
import com.macro.mall.model.UmsMemberLevel;
import com.macro.mall.service.UmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员等级管理Service实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class UmsMemberLevelServiceImpl implements UmsMemberLevelService{
    @Autowired
    private UmsMemberLevelRepository memberLevelRepository;

    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        SpecificationBuilder<UmsMemberLevel> builder = SpecificationBuilder.create();
        if (defaultStatus != null) {
            builder.eq("defaultStatus", defaultStatus);
        }
        return memberLevelRepository.findAll(builder.build());
    }
}
