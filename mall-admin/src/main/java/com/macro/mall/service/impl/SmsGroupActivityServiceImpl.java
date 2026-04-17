package com.macro.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.macro.mall.dto.SmsGroupActivityParam;
import com.macro.mall.dto.SmsGroupProductRelationParam;
import com.macro.mall.mapper.SmsGroupActivityMapper;
import com.macro.mall.mapper.SmsGroupProductRelationMapper;
import com.macro.mall.mapper.SmsGroupTeamMapper;
import com.macro.mall.model.*;
import com.macro.mall.service.SmsGroupActivityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class SmsGroupActivityServiceImpl implements SmsGroupActivityService {

    @Autowired
    private SmsGroupActivityMapper groupActivityMapper;

    @Autowired
    private SmsGroupProductRelationMapper productRelationMapper;

    @Autowired
    private SmsGroupTeamMapper groupTeamMapper;

    @Override
    @Transactional
    public int create(SmsGroupActivityParam param) {
        SmsGroupActivity activity = new SmsGroupActivity();
        BeanUtils.copyProperties(param, activity);
        activity.setCreateTime(new Date());
        int count = groupActivityMapper.insert(activity);
        insertProductRelationList(activity.getId(), param.getProductRelationList());
        return count;
    }

    @Override
    @Transactional
    public int update(Long id, SmsGroupActivityParam param) {
        SmsGroupActivity activity = new SmsGroupActivity();
        BeanUtils.copyProperties(param, activity);
        activity.setId(id);
        int count = groupActivityMapper.updateByPrimaryKey(activity);
        SmsGroupProductRelationExample example = new SmsGroupProductRelationExample();
        example.createCriteria().andGroupActivityIdEqualTo(id);
        productRelationMapper.deleteByExample(example);
        insertProductRelationList(id, param.getProductRelationList());
        return count;
    }

    @Override
    public int delete(Long id) {
        SmsGroupProductRelationExample example = new SmsGroupProductRelationExample();
        example.createCriteria().andGroupActivityIdEqualTo(id);
        productRelationMapper.deleteByExample(example);
        return groupActivityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsGroupActivity activity = new SmsGroupActivity();
        activity.setId(id);
        activity.setStatus(status);
        return groupActivityMapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    public SmsGroupActivity getItem(Long id) {
        return groupActivityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SmsGroupActivity> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsGroupActivityExample example = new SmsGroupActivityExample();
        SmsGroupActivityExample.Criteria criteria = example.createCriteria();
        if (!StrUtil.isEmpty(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
        }
        example.setOrderByClause("sort asc, id desc");
        return groupActivityMapper.selectByExample(example);
    }

    @Override
    public List<SmsGroupProductRelation> getProductRelationList(Long activityId) {
        SmsGroupProductRelationExample example = new SmsGroupProductRelationExample();
        example.createCriteria().andGroupActivityIdEqualTo(activityId);
        example.setOrderByClause("sort asc");
        return productRelationMapper.selectByExample(example);
    }

    @Override
    public List<SmsGroupTeam> getTeamList(Long activityId, Integer status, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsGroupTeamExample example = new SmsGroupTeamExample();
        SmsGroupTeamExample.Criteria criteria = example.createCriteria();
        criteria.andGroupActivityIdEqualTo(activityId);
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        example.setOrderByClause("create_time desc");
        return groupTeamMapper.selectByExample(example);
    }

    private void insertProductRelationList(Long activityId, List<SmsGroupProductRelationParam> productRelationList) {
        if (CollectionUtils.isEmpty(productRelationList)) {
            return;
        }
        for (SmsGroupProductRelationParam param : productRelationList) {
            SmsGroupProductRelation relation = new SmsGroupProductRelation();
            BeanUtils.copyProperties(param, relation);
            relation.setGroupActivityId(activityId);
            relation.setLockStock(0);
            relation.setSoldStock(0);
            productRelationMapper.insert(relation);
        }
    }
}
