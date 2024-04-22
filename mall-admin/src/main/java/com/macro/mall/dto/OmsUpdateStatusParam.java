package com.macro.mall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(title = "服务单号")
    private Long id;
    @Schema(title = "收货地址关联id")
    private Long companyAddressId;
    @Schema(title = "确认退款金额")
    private BigDecimal returnAmount;
    @Schema(title = "处理备注")
    private String handleNote;
    @Schema(title = "处理人")
    private String handleMan;
    @Schema(title = "收货备注")
    private String receiveNote;
    @Schema(title = "收货人")
    private String receiveMan;
    @Schema(title = "申请状态：1->退货中；2->已完成；3->已拒绝")
    private Integer status;
}
