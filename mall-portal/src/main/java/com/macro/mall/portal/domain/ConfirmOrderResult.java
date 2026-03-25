package com.macro.mall.portal.domain;

import com.macro.mall.model.UmsIntegrationConsumeSetting;
import com.macro.mall.model.UmsMemberReceiveAddress;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 确认单信息封装
 * Created by macro on 2018/8/30.
 */
@Getter
@Setter
public class ConfirmOrderResult {
    private List<CartPromotionItem> cartPromotionItemList;
    private List<UmsMemberReceiveAddress> memberReceiveAddressList;
    private List<SmsCouponHistoryDetail> couponHistoryDetailList;
    private UmsIntegrationConsumeSetting integrationConsumeSetting;
    private Integer memberIntegration;
    private CalcAmount calcAmount;

    @Getter
    @Setter
    public static class CalcAmount{
        private BigDecimal totalAmount;
        private BigDecimal freightAmount;
        private BigDecimal promotionAmount;
        private BigDecimal payAmount;
    }
}
