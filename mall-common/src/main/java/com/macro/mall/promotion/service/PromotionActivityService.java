package com.macro.mall.promotion.service;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.model.SmsPromotionActivity;
import com.macro.mall.promotion.dto.PromotionActivityParam;
import com.macro.mall.promotion.domain.PromotionCalcResult;
import com.macro.mall.promotion.domain.PromotionContext;

import java.util.List;

public interface PromotionActivityService {
    
    int create(PromotionActivityParam param);
    
    int update(Long id, PromotionActivityParam param);
    
    int delete(Long id);
    
    int deleteBatch(List<Long> ids);
    
    SmsPromotionActivity getById(Long id);
    
    CommonPage<SmsPromotionActivity> list(String name, Integer type, Integer status, 
                                            Integer pageNum, Integer pageSize);
    
    int updateStatus(Long id, Integer status);
    
    PromotionCalcResult calculate(PromotionContext context);
    
    PromotionCalcResult tryCalculate(PromotionContext context);
    
    PromotionCalcResult findOptimalCombination(PromotionContext context);
    
    List<PromotionCalcResult> findTopNCombinations(PromotionContext context, int n);
}
