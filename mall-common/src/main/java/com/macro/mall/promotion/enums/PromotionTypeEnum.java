package com.macro.mall.promotion.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PromotionTypeEnum {
    
    FULL_REDUCTION(1, "满减"),
    FULL_DISCOUNT(2, "满折"),
    NTH_ITEM_DISCOUNT(3, "第N件优惠"),
    PACKAGE_PRICE(4, "套餐价"),
    SKU_SPECIAL_PRICE(5, "SKU特价"),
    MEMBER_PRICE(6, "会员专享"),
    COUPON(7, "优惠券");

    private final Integer code;
    private final String desc;

    public static PromotionTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (PromotionTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
