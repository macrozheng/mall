package com.macro.mall.promotion.engine;

import com.macro.mall.promotion.domain.PromotionCalcResult;
import com.macro.mall.promotion.domain.PromotionContext;

public interface PromotionEngine {
    
    PromotionCalcResult calculate(PromotionContext context);
    
    PromotionCalcResult tryCalculate(PromotionContext context);
}
