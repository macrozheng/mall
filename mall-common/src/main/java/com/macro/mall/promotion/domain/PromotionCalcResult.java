package com.macro.mall.promotion.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PromotionCalcResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal originalAmount;
    private BigDecimal finalAmount;
    private BigDecimal totalDiscount;
    private List<PromotionAppliedResult> appliedPromotions;
    private List<PromotionCartItemResult> itemResults;
    private String calcExplanation;
}
