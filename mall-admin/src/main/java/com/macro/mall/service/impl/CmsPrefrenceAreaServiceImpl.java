package com.macro.mall.service.impl;

import com.macro.mall.repository.CmsPrefrenceAreaRepository;
import com.macro.mall.model.CmsPrefrenceArea;
import com.macro.mall.service.CmsPrefrenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品优选管理Service实现类
 * Created by macro on 2018/6/1.
 */
@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {
    @Autowired
    private CmsPrefrenceAreaRepository prefrenceAreaRepository;

    @Override
    public List<CmsPrefrenceArea> listAll() {
        return prefrenceAreaRepository.findAll();
    }
}
