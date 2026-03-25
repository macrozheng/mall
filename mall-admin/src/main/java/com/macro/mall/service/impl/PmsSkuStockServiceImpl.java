package com.macro.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.macro.mall.common.util.SpecificationBuilder;
import com.macro.mall.repository.PmsSkuStockRepository;
import com.macro.mall.model.PmsSkuStock;
import com.macro.mall.service.PmsSkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品SKU库存管理Service实现类
 * Created by macro on 2018/4/27.
 */
@Service
public class PmsSkuStockServiceImpl implements PmsSkuStockService {

    @Autowired
    private PmsSkuStockRepository skuStockRepository;

    @Override
    public List<PmsSkuStock> getList(Long pid, String keyword) {
        SpecificationBuilder<PmsSkuStock> builder = SpecificationBuilder.<PmsSkuStock>create()
            .eq("productId", pid);
        if (StrUtil.isNotEmpty(keyword)) {
            builder.like("skuCode", keyword);
        }
        return skuStockRepository.findAll(builder.build());
    }

    @Override
    public int update(Long pid, List<PmsSkuStock> skuStockList) {
        List<PmsSkuStock> filterSkuList = skuStockList.stream()
                .filter(item -> pid.equals(item.getProductId()))
                .collect(Collectors.toList());
        skuStockRepository.saveAll(filterSkuList);
        return filterSkuList.size();
    }
}
