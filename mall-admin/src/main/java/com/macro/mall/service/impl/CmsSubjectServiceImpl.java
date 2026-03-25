package com.macro.mall.service.impl;


import com.macro.mall.common.util.SpecificationBuilder;
import cn.hutool.core.util.StrUtil;
import com.macro.mall.repository.CmsSubjectRepository;
import com.macro.mall.model.CmsSubject;
import com.macro.mall.service.CmsSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品专题管理Service实现类
 * Created by macro on 2018/6/1.
 */
@Service
public class CmsSubjectServiceImpl implements CmsSubjectService {
    @Autowired
    private CmsSubjectRepository subjectRepository;

    @Override
    public List<CmsSubject> listAll() {
        return subjectRepository.findAll();
    }

    @Override
    public List<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize) {
        SpecificationBuilder<CmsSubject> builder = SpecificationBuilder.create();
        if (!StrUtil.isEmpty(keyword)) {
            builder.like("title", keyword);
        }
        return subjectRepository.findAll(builder.build());
    }
}
