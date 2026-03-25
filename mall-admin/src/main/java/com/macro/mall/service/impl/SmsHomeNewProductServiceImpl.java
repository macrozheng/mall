package com.macro.mall.service.impl;


import com.macro.mall.common.util.SpecificationBuilder;
import cn.hutool.core.util.StrUtil;
import com.macro.mall.repository.SmsHomeNewProductRepository;
import com.macro.mall.model.SmsHomeNewProduct;
import com.macro.mall.service.SmsHomeNewProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页新品推荐管理Service实现类
 * Created by macro on 2018/11/6.
 */
@Service
public class SmsHomeNewProductServiceImpl implements SmsHomeNewProductService {
    @Autowired
    private SmsHomeNewProductRepository homeNewProductRepository;
    @Override
    public int create(List<SmsHomeNewProduct> homeNewProductList) {
        for (SmsHomeNewProduct SmsHomeNewProduct : homeNewProductList) {
            SmsHomeNewProduct.setRecommendStatus(1);
            SmsHomeNewProduct.setSort(0);
            homeNewProductRepository.save(SmsHomeNewProduct);
        }
        return homeNewProductList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeNewProduct homeNewProduct = new SmsHomeNewProduct();
        homeNewProduct.setId(id);
        homeNewProduct.setSort(sort);
        homeNewProductRepository.save(homeNewProduct);
        return 1;
    }

    @Override
    public int delete(List<Long> ids) {
        homeNewProductRepository.deleteAllByIdInBatch(ids);
        return ids.size();
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        List<SmsHomeNewProduct> products = homeNewProductRepository.findAllById(ids);
        for (SmsHomeNewProduct product : products) {
            product.setRecommendStatus(recommendStatus);
        }
        homeNewProductRepository.saveAll(products);
        return products.size();
    }

    @Override
    public List<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        SpecificationBuilder<SmsHomeNewProduct> builder = SpecificationBuilder.create();
        if(!StrUtil.isEmpty(productName)){
            builder.like("productName", productName);
        }
        if(recommendStatus!=null){
            builder.eq("recommendStatus", recommendStatus);
        }
        return homeNewProductRepository.findAll(builder.build());
    }
}
