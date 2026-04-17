package com.macro.mall.portal.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode
public class GroupTeamDetail {
    @ApiModelProperty("队伍ID")
    private Long teamId;

    @ApiModelProperty("团号")
    private String teamNumber;

    @ApiModelProperty("活动ID")
    private Long activityId;

    @ApiModelProperty("商品ID")
    private Long productId;

    @ApiModelProperty("团长ID")
    private Long leaderId;

    @ApiModelProperty("团长名称")
    private String leaderName;

    @ApiModelProperty("团长头像")
    private String leaderIcon;

    @ApiModelProperty("成团人数")
    private Integer groupCount;

    @ApiModelProperty("当前参与人数")
    private Integer joinCount;

    @ApiModelProperty("过期时间")
    private Date expireTime;

    @ApiModelProperty("剩余时间(秒)")
    private Long remainTime;

    @ApiModelProperty("状态：0->进行中；1->已成团；2->已过期；3->已取消")
    private Integer status;

    @ApiModelProperty("拼团价格")
    private BigDecimal groupPrice;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品主图")
    private String productPic;

    @ApiModelProperty("拼团成员列表")
    private List<GroupMemberItem> memberList;
}
