package com.macro.mall.portal.service.impl;

import com.macro.mall.mapper.SmsGroupBuyActivityMapper;
import com.macro.mall.model.SmsGroupBuyActivity;
import com.macro.mall.model.SmsGroupBuyProduct;
import com.macro.mall.model.SmsGroupBuyTeam;
import com.macro.mall.portal.dao.GroupBuyPortalDao;
import com.macro.mall.portal.service.GroupBuyActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 拼团活动前台Service实现
 */
@Service
public class GroupBuyActivityServiceImpl implements GroupBuyActivityService {

    @Autowired
    private SmsGroupBuyActivityMapper activityMapper;

    @Autowired
    private GroupBuyPortalDao portalDao;

    @Override
    public List<SmsGroupBuyActivity> listOngoing() {
        return portalDao.listOngoingActivity();
    }

    @Override
    public SmsGroupBuyActivity getDetail(Long activityId) {
        return activityMapper.selectByPrimaryKey(activityId);
    }

    @Override
    public List<SmsGroupBuyProduct> listProduct(Long activityId) {
        return portalDao.listActivityProduct(activityId);
    }

    @Override
    public List<SmsGroupBuyTeam> listOngoingTeam(Long activityId) {
        return portalDao.listOngoingTeamByActivity(activityId);
    }
}
