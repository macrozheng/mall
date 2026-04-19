package com.macro.mall.portal.domain;

import com.macro.mall.model.SmsGroupBuyRecord;
import com.macro.mall.model.SmsGroupBuyTeam;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 拼团团详情(用于分享页展示)
 */
public class GroupBuyTeamDetail {

    @ApiModelProperty(value = "团基本信息")
    private SmsGroupBuyTeam team;

    @ApiModelProperty(value = "已参团成员列表")
    private List<SmsGroupBuyRecord> members;

    @ApiModelProperty(value = "还差几人成团")
    private Integer lackNum;

    @ApiModelProperty(value = "距离截止还剩毫秒")
    private Long remainMillis;

    public SmsGroupBuyTeam getTeam() { return team; }
    public void setTeam(SmsGroupBuyTeam team) { this.team = team; }
    public List<SmsGroupBuyRecord> getMembers() { return members; }
    public void setMembers(List<SmsGroupBuyRecord> members) { this.members = members; }
    public Integer getLackNum() { return lackNum; }
    public void setLackNum(Integer lackNum) { this.lackNum = lackNum; }
    public Long getRemainMillis() { return remainMillis; }
    public void setRemainMillis(Long remainMillis) { this.remainMillis = remainMillis; }
}
