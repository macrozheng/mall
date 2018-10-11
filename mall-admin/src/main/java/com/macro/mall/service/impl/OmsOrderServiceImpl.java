package com.macro.mall.service.impl;

import com.aliyun.oss.common.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.macro.mall.dto.OmsOrderDetail;
import com.macro.mall.dto.OmsOrderQueryParam;
import com.macro.mall.mapper.OmsOrderMapper;
import com.macro.mall.model.OmsOrder;
import com.macro.mall.model.OmsOrderExample;
import com.macro.mall.service.OmsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * 订单管理Service实现类
 * Created by macro on 2018/10/11.
 */
@Service
public class OmsOrderServiceImpl implements OmsOrderService {
    @Autowired
    private OmsOrderMapper orderMapper;
    @Override
    public List<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        OmsOrderExample example = new OmsOrderExample();
        OmsOrderExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteStatusEqualTo(0);
        if(!StringUtils.isEmpty(queryParam.getOrderSn())){
            criteria.andOrderSnEqualTo(queryParam.getOrderSn());
        }
        if(!StringUtils.isEmpty(queryParam.getReceiverKeyword())){
            criteria.andReceiverNameEqualTo(queryParam.getReceiverKeyword());
        }
        if(queryParam.getStatus()!=null){
            criteria.andStatusEqualTo(queryParam.getStatus());
        }
        if(queryParam.getSourceType()!=null){
            criteria.andSourceTypeEqualTo(queryParam.getSourceType());
        }
        if(queryParam.getOrderType()!=null){
            criteria.andOrderTypeEqualTo(queryParam.getOrderType());
        }
        return orderMapper.selectByExample(example);
    }

    @Override
    public int delivery(List<Long> ids) {
        return 0;
    }

    @Override
    public int close(List<Long> ids) {
        return 0;
    }

    @Override
    public int delete(List<Long> ids) {
        return 0;
    }

    @Override
    public OmsOrderDetail detail(Long id) {
        return null;
    }
}
