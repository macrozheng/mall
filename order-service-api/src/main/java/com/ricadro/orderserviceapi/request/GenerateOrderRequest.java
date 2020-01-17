package com.ricadro.orderserviceapi.request;

import lombok.Data;

/**
 * @Author: ricadro
 * @Date: 2020/1/17 09:28
 */
@Data
public class GenerateOrderRequest extends BaseRequest {

    private Long memberReceiveAddressId;

    private Long couponId;

    private Integer useIntegration;

    private Integer payType;

}
