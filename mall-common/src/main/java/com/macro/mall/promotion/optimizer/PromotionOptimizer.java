package com.macro.mall.promotion.optimizer;

import com.macro.mall.promotion.domain.PromotionCalcResult;
import com.macro.mall.promotion.domain.PromotionContext;

import java.util.List;

public interface PromotionOptimizer {
    
    PromotionCalcResult findOptimalCombination(PromotionContext context);
    
    List<PromotionCalcResult> findAllPossibleCombinations(PromotionContext context);
    
    List<PromotionCalcResult> findTopNCombinations(PromotionContext context, int n);
}
