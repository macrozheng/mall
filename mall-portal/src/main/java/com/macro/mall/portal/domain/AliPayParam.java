package com.macro.mall.portal.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @auther macrozheng
 * @description 支付宝支付请求参数
 * @date 2023/9/8
 * @github https://github.com/macrozheng
 */
@Data
public class AliPayParam {
    /**
     * 商户订单号，商家自定义，保持唯一性
     */
    private String outTradeNo;
    /**
     * 商品的标题/交易标题/订单标题/订单关键字等
     */
    private String subject;
    /**
     * 订单总金额，单位为元，精确到小数点后两位
     */
    private BigDecimal totalAmount;
}
