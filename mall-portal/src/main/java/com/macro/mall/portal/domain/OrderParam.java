package com.macro.mall.portal.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 生成订单时传入的参数
 * Created by macro on 2018/8/30.
 */
@Data
@EqualsAndHashCode
public class OrderParam {
    private Long memberReceiveAddressId;
    private Long couponId;
    private Integer useIntegration;
    private Integer payType;
    private List<Long> cartIds;
}
