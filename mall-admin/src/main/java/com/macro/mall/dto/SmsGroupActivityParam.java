package com.macro.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode
public class SmsGroupActivityParam {
    @ApiModelProperty("活动名称")
    private String name;

    @ApiModelProperty("活动类型：0->普通拼团；1->老带新拼团；2->新人团")
    private Integer activityType;

    @ApiModelProperty("活动开始时间")
    private Date startTime;

    @ApiModelProperty("活动结束时间")
    private Date endTime;

    @ApiModelProperty("成团人数")
    private Integer groupCount;

    @ApiModelProperty("成团有效时间(小时)")
    private Integer groupValidTime;

    @ApiModelProperty("每人限购数量")
    private Integer limitCount;

    @ApiModelProperty("每人限参加次数")
    private Integer useLimitCount;

    @ApiModelProperty("最大参与人数：0->不限制")
    private Integer maxJoinCount;

    @ApiModelProperty("最低消费金额")
    private BigDecimal minAmount;

    @ApiModelProperty("活动状态：0->关闭；1->开启")
    private Integer status;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty("拼团商品列表")
    private List<SmsGroupProductRelationParam> productRelationList;
}
