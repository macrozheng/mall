package com.macro.mall.promotion.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RuleTypeEnum {
    
    FULL_REDUCTION_LADDER(1, "满减阶梯"),
    FULL_DISCOUNT_LADDER(2, "满折阶梯"),
    NTH_ITEM_DISCOUNT(3, "第N件优惠"),
    FIXED_AMOUNT(4, "固定金额"),
    FIXED_DISCOUNT(5, "固定折扣");

    private final Integer code;
    private final String desc;

    public static RuleTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (RuleTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
