package com.macro.mall.promotion.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountTypeEnum {
    
    AMOUNT(1, "金额减免"),
    DISCOUNT_RATE(2, "折扣比例");

    private final Integer code;
    private final String desc;

    public static DiscountTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (DiscountTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
