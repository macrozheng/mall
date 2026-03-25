package com.macro.mall.service.impl;


import com.macro.mall.common.util.SpecificationBuilder;
import cn.hutool.core.util.StrUtil;
import com.macro.mall.repository.SmsHomeRecommendProductRepository;
import com.macro.mall.model.SmsHomeRecommendProduct;
import com.macro.mall.service.SmsHomeRecommendProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页人气推荐管理Service实现类
 * Created by macro on 2018/11/7.
 */
@Service
public class SmsHomeRecommendProductServiceImpl implements SmsHomeRecommendProductService {
    @Autowired
    private SmsHomeRecommendProductRepository recommendProductRepository;
    @Override
    public int create(List<SmsHomeRecommendProduct> homeRecommendProductList) {
        for (SmsHomeRecommendProduct recommendProduct : homeRecommendProductList) {
            recommendProduct.setRecommendStatus(1);
            recommendProduct.setSort(0);
            recommendProductRepository.save(recommendProduct);
        }
        return homeRecommendProductList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendProduct recommendProduct = new SmsHomeRecommendProduct();
        recommendProduct.setId(id);
        recommendProduct.setSort(sort);
        recommendProductRepository.save(recommendProduct);
        return 1;
    }

    @Override
    public int delete(List<Long> ids) {
        recommendProductRepository.deleteAllByIdInBatch(ids);
        return ids.size();
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        List<SmsHomeRecommendProduct> products = recommendProductRepository.findAllById(ids);
        for (SmsHomeRecommendProduct product : products) {
            product.setRecommendStatus(recommendStatus);
        }
        recommendProductRepository.saveAll(products);
        return products.size();
    }

    @Override
    public List<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        SpecificationBuilder<SmsHomeRecommendProduct> builder = SpecificationBuilder.create();
        if(!StrUtil.isEmpty(productName)){
            builder.like("productName", productName);
        }
        if(recommendStatus!=null){
            builder.eq("recommendStatus", recommendStatus);
        }
        return recommendProductRepository.findAll(builder.build());
    }
}
