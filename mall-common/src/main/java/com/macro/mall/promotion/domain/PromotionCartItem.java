package com.macro.mall.promotion.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PromotionCartItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long productId;
    private Long productSkuId;
    private String productSkuCode;
    private String productName;
    private String productPic;
    private BigDecimal price;
    private Integer quantity;
    private Long productCategoryId;
    private Long brandId;
    private Long memberLevelId;
    private String spData;
}
