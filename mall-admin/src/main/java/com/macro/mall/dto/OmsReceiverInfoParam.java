package com.macro.mall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 订单修改收货人信息参数
 * Created by macro on 2018/10/29.
 */
@Getter
@Setter
public class OmsReceiverInfoParam {
    @Schema(title =  "订单ID")
    private Long orderId;
    @Schema(title =  "收货人姓名")
    private String receiverName;
    @Schema(title =  "收货人电话")
    private String receiverPhone;
    @Schema(title =  "收货人邮编")
    private String receiverPostCode;
    @Schema(title =  "详细地址")
    private String receiverDetailAddress;
    @Schema(title =  "省份/直辖市")
    private String receiverProvince;
    @Schema(title =  "城市")
    private String receiverCity;
    @Schema(title =  "区")
    private String receiverRegion;
    @Schema(title =  "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    private Integer status;
}
