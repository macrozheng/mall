package com.macro.mall.portal.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode
public class GroupActivityDetail {
    @ApiModelProperty("活动ID")
    private Long activityId;

    @ApiModelProperty("活动名称")
    private String activityName;

    @ApiModelProperty("活动类型：0->普通拼团；1->老带新拼团；2->新人团")
    private Integer activityType;

    @ApiModelProperty("成团人数")
    private Integer groupCount;

    @ApiModelProperty("成团有效时间(小时)")
    private Integer groupValidTime;

    @ApiModelProperty("活动开始时间")
    private Date startTime;

    @ApiModelProperty("活动结束时间")
    private Date endTime;

    @ApiModelProperty("拼团商品列表")
    private List<GroupProductItem> productList;
}
