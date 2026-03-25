package com.macro.mall.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 订单退货申请查询参数
 * Created by macro on 2018/10/18.
 */
@Getter
@Setter
public class OmsReturnApplyQueryParam {
    private Long id;
    private String receiverKeyword;
    private Integer status;
    private String createTime;
    private String handleMan;
    private String handleTime;
}
