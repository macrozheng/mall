package com.macro.mall.promotion.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PromotionUseTypeEnum {
    
    ALL(0, "全场通用"),
    CATEGORY(1, "指定分类"),
    PRODUCT(2, "指定商品"),
    BRAND(3, "指定品牌");

    private final Integer code;
    private final String desc;

    public static PromotionUseTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (PromotionUseTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
