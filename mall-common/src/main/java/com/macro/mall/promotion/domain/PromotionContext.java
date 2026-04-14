package com.macro.mall.promotion.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PromotionContext implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long memberId;
    private Long memberLevelId;
    private List<PromotionCartItem> cartItems;
    private List<Long> selectedCouponIds;
    private Integer platform;
}
