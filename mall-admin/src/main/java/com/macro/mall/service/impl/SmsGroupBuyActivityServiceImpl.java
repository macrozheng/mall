package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.common.exception.Asserts;
import com.macro.mall.dao.SmsGroupBuyActivityDao;
import com.macro.mall.mapper.SmsGroupBuyActivityMapper;
import com.macro.mall.model.SmsGroupBuyActivity;
import com.macro.mall.service.SmsGroupBuyActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 拼团活动管理Service实现
 */
@Service
public class SmsGroupBuyActivityServiceImpl implements SmsGroupBuyActivityService {

    @Autowired
    private SmsGroupBuyActivityMapper activityMapper;

    @Autowired
    private SmsGroupBuyActivityDao activityDao;

    @Override
    public int create(SmsGroupBuyActivity activity) {
        Date now = new Date();
        activity.setCreateTime(now);
        activity.setUpdateTime(now);
        if (activity.getStatus() == null) {
            activity.setStatus(0);
        }
        if (activity.getTotalGroupCount() == null) {
            activity.setTotalGroupCount(0);
        }
        if (activity.getSuccessGroupCount() == null) {
            activity.setSuccessGroupCount(0);
        }
        if (activity.getGroupSize() == null || activity.getGroupSize() < 2) {
            Asserts.fail("成团人数必须大于等于2");
        }
        if (activity.getValidHours() == null || activity.getValidHours() <= 0) {
            activity.setValidHours(24);
        }
        return activityMapper.insertSelective(activity);
    }

    @Override
    public int update(Long id, SmsGroupBuyActivity activity) {
        SmsGroupBuyActivity current = activityMapper.selectByPrimaryKey(id);
        if (current == null) {
            Asserts.fail("活动不存在");
        }
        if (Integer.valueOf(1).equals(current.getStatus())) {
            int ongoing = activityDao.countOngoingTeam(id);
            if (ongoing > 0) {
                Asserts.fail("活动存在进行中的团,禁止修改成团人数或价格等核心配置");
            }
        }
        activity.setId(id);
        activity.setUpdateTime(new Date());
        return activityMapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    public int delete(Long id) {
        int ongoing = activityDao.countOngoingTeam(id);
        if (ongoing > 0) {
            Asserts.fail("活动存在进行中的团,不可删除");
        }
        SmsGroupBuyActivity activity = activityMapper.selectByPrimaryKey(id);
        if (activity != null && Integer.valueOf(1).equals(activity.getStatus())) {
            Asserts.fail("请先下线活动再删除");
        }
        return activityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsGroupBuyActivity activity = new SmsGroupBuyActivity();
        activity.setId(id);
        activity.setStatus(status);
        activity.setUpdateTime(new Date());
        return activityMapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    public SmsGroupBuyActivity getItem(Long id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SmsGroupBuyActivity> list(String keyword, Integer status, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return activityDao.listActivity(keyword, status);
    }
}
