package com.macro.mall.service.impl;


import com.macro.mall.common.util.SpecificationBuilder;
import cn.hutool.core.util.StrUtil;
import com.macro.mall.repository.SmsHomeBrandRepository;
import com.macro.mall.model.SmsHomeBrand;
import com.macro.mall.service.SmsHomeBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页品牌管理Service实现类
 * Created by macro on 2018/11/6.
 */
@Service
public class SmsHomeBrandServiceImpl implements SmsHomeBrandService {
    @Autowired
    private SmsHomeBrandRepository homeBrandRepository;
    @Override
    public int create(List<SmsHomeBrand> homeBrandList) {
        for (SmsHomeBrand smsHomeBrand : homeBrandList) {
            smsHomeBrand.setRecommendStatus(1);
            smsHomeBrand.setSort(0);
            homeBrandRepository.save(smsHomeBrand);
        }
        return homeBrandList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeBrand homeBrand = new SmsHomeBrand();
        homeBrand.setId(id);
        homeBrand.setSort(sort);
        homeBrandRepository.save(homeBrand);
        return 1;
    }

    @Override
    public int delete(List<Long> ids) {
        homeBrandRepository.deleteAllByIdInBatch(ids);
        return ids.size();
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        List<SmsHomeBrand> brands = homeBrandRepository.findAllById(ids);
        for (SmsHomeBrand brand : brands) {
            brand.setRecommendStatus(recommendStatus);
        }
        homeBrandRepository.saveAll(brands);
        return brands.size();
    }

    @Override
    public List<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        SpecificationBuilder<SmsHomeBrand> builder = SpecificationBuilder.create();
        if(!StrUtil.isEmpty(brandName)){
            builder.like("brandName", brandName);
        }
        if(recommendStatus!=null){
            builder.eq("recommendStatus", recommendStatus);
        }
        return homeBrandRepository.findAll(builder.build());
    }
}
