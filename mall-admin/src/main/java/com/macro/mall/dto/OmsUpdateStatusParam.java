package com.macro.mall.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 确认收货请求参数
 * Created by macro on 2018/10/18.
 */
@Getter
@Setter
public class OmsUpdateStatusParam {
    private Long id;
    private Long companyAddressId;
    private BigDecimal returnAmount;
    private String handleNote;
    private String handleMan;
    private String receiveNote;
    private String receiveMan;
    private Integer status;
}
