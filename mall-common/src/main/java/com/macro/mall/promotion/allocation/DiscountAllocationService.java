package com.macro.mall.promotion.allocation;

import com.macro.mall.promotion.domain.PromotionCartItemResult;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public interface DiscountAllocationService {
    
    void allocateDiscount(BigDecimal totalDiscount, 
                          List<PromotionCartItemResult> items,
                          AllocationStrategy strategy);
    
    void allocateDiscountByAmount(BigDecimal totalDiscount,
                                   List<PromotionCartItemResult> items);
    
    void allocateDiscountByQuantity(BigDecimal totalDiscount,
                                     List<PromotionCartItemResult> items);
    
    void allocateDiscountEqually(BigDecimal totalDiscount,
                                  List<PromotionCartItemResult> items);
}
