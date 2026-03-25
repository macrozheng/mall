package com.macro.mall.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 订单发货参数
 * Created by macro on 2018/10/12.
 */
@Getter
@Setter
public class OmsOrderDeliveryParam {
    private Long orderId;
    private String deliveryCompany;
    private String deliverySn;
}
