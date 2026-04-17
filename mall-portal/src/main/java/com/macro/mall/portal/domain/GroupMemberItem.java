package com.macro.mall.portal.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode
public class GroupMemberItem {
    @ApiModelProperty("成员ID")
    private Long memberId;

    @ApiModelProperty("成员名称")
    private String memberName;

    @ApiModelProperty("成员头像")
    private String memberIcon;

    @ApiModelProperty("是否为团长")
    private Boolean isLeader;

    @ApiModelProperty("状态：0->待支付；1->已支付；2->已退款；3->已取消")
    private Integer status;

    @ApiModelProperty("加入时间")
    private Date createTime;
}
