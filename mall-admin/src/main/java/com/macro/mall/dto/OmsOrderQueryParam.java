package com.macro.mall.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 订单查询参数
 * Created by macro on 2018/10/11.
 */
@Getter
@Setter
public class OmsOrderQueryParam {
    private String orderSn;
    private String receiverKeyword;
    private Integer status;
    private Integer orderType;
    private Integer sourceType;
    private String createTime;
}
