package com.macro.mall.promotion.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PromotionCartItemResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long productId;
    private Long skuId;
    private String productName;
    private String skuCode;
    private BigDecimal originalPrice;
    private BigDecimal finalPrice;
    private Integer quantity;
    private BigDecimal originalSubtotal;
    private BigDecimal finalSubtotal;
    private BigDecimal totalDiscount;
    private String discountDetail;
}
