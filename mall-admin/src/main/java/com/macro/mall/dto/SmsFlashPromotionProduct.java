package com.macro.mall.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 限时购及商品信息封装
 * Created by macro on 2018/11/16.
 */
public class SmsFlashPromotionProduct extends SmsFlashPromotionProductRelation{
    @Getter
    @Setter
    private PmsProduct product;
}
