package com.macro.mall.service;

import com.macro.mall.dto.SmsGroupActivityParam;
import com.macro.mall.model.SmsGroupActivity;
import com.macro.mall.model.SmsGroupProductRelation;
import com.macro.mall.model.SmsGroupTeam;

import java.util.List;

public interface SmsGroupActivityService {

    int create(SmsGroupActivityParam param);

    int update(Long id, SmsGroupActivityParam param);

    int delete(Long id);

    int updateStatus(Long id, Integer status);

    SmsGroupActivity getItem(Long id);

    List<SmsGroupActivity> list(String keyword, Integer pageSize, Integer pageNum);

    List<SmsGroupProductRelation> getProductRelationList(Long activityId);

    List<SmsGroupTeam> getTeamList(Long activityId, Integer status, Integer pageSize, Integer pageNum);
}
