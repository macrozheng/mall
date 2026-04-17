package com.macro.mall.portal.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class GroupOrderParam {
    @ApiModelProperty("拼团活动ID")
    private Long activityId;

    @ApiModelProperty("拼团商品ID")
    private Long groupProductId;

    @ApiModelProperty("拼团队伍ID，为空表示新开团")
    private Long teamId;

    @ApiModelProperty("收货地址ID")
    private Long memberReceiveAddressId;

    @ApiModelProperty("支付方式")
    private Integer payType;

    @ApiModelProperty("购买数量")
    private Integer quantity;
}
