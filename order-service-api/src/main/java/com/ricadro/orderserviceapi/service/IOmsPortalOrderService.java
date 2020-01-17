package com.ricadro.orderserviceapi.service;

import com.ricadro.orderserviceapi.request.GenerateOrderRequest;
import com.ricadro.orderserviceapi.response.BaseResponse;

/**
 * @Author: ricadro
 * @Date: 2020/1/17 10:17
 */
public interface IOmsPortalOrderService {
    /**
     * 根据用户购物车信息生成确认单信息
     */
//    ConfirmOrderResult generateConfirmOrder();

    /**
     * 根据提交信息生成订单
     */
    BaseResponse generateOrder(GenerateOrderRequest generateOrderRequest);

    /**
     * 支付成功后的回调
     */
    BaseResponse paySuccess(Long orderId);

    /**
     * 自动取消超时订单
     */
    BaseResponse cancelTimeOutOrder();

    /**
     * 取消单个超时订单
     */
    BaseResponse cancelOrder(Long orderId);

    /**
     * 发送延迟消息取消订单
     */
    BaseResponse sendDelayMessageCancelOrder(Long orderId);
}
