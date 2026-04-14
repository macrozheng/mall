package com.macro.mall.promotion.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PromotionAppliedResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long promotionId;
    private String promotionName;
    private Integer promotionType;
    private String promotionTypeDesc;
    private BigDecimal discountAmount;
    private String discountDesc;
    private List<Long> appliedProductIds;
    private List<Long> appliedSkuIds;
}
