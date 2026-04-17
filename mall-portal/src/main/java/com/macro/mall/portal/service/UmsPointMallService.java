package com.macro.mall.portal.service;

import com.macro.mall.model.UmsIntegrationExchangeRecord;
import com.macro.mall.model.UmsPointMallCategory;
import com.macro.mall.model.UmsPointMallProduct;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface UmsPointMallService {

    List<UmsPointMallCategory> getCategories();

    List<UmsPointMallProduct> getProducts(Long categoryId, Integer pageNum, Integer pageSize, String sortBy);

    UmsPointMallProduct getProductDetail(Long productId);

    @Transactional
    UmsIntegrationExchangeRecord exchangeProduct(Long productId, Integer quantity, Long addressId);

    List<UmsIntegrationExchangeRecord> getExchangeRecords(Integer pageNum, Integer pageSize, Integer status);

    UmsIntegrationExchangeRecord getExchangeRecordDetail(Long recordId);

    @Transactional
    void cancelExchange(Long recordId);

    void confirmReceive(Long recordId);

    List<UmsPointMallProduct> getHotProducts(Integer limit);

    List<UmsPointMallProduct> getNewProducts(Integer limit);

    List<UmsPointMallProduct> getRecommendProducts(Integer limit);
}
