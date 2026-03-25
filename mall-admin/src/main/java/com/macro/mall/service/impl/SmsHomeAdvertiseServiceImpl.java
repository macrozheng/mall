package com.macro.mall.service.impl;


import com.macro.mall.common.util.SpecificationBuilder;
import cn.hutool.core.util.StrUtil;
import com.macro.mall.repository.SmsHomeAdvertiseRepository;
import com.macro.mall.model.SmsHomeAdvertise;
import com.macro.mall.service.SmsHomeAdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 首页广告管理Service实现类
 * Created by macro on 2018/11/7.
 */
@Service
public class SmsHomeAdvertiseServiceImpl implements SmsHomeAdvertiseService {
    @Autowired
    private SmsHomeAdvertiseRepository advertiseRepository;

    @Override
    public int create(SmsHomeAdvertise advertise) {
        advertise.setClickCount(0);
        advertise.setOrderCount(0);
        advertiseRepository.save(advertise);
        return 1;
    }

    @Override
    public int delete(List<Long> ids) {
        advertiseRepository.deleteAllByIdInBatch(ids);
        return ids.size();
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsHomeAdvertise record = new SmsHomeAdvertise();
        record.setId(id);
        record.setStatus(status);
        advertiseRepository.save(record);
        return 1;
    }

    @Override
    public SmsHomeAdvertise getItem(Long id) {
        return advertiseRepository.findById(id).orElse(null);
    }

    @Override
    public int update(Long id, SmsHomeAdvertise advertise) {
        advertise.setId(id);
        advertiseRepository.save(advertise);
        return 1;
    }

    @Override
    public List<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum) {
        SpecificationBuilder<SmsHomeAdvertise> builder = SpecificationBuilder.create();
        if (!StrUtil.isEmpty(name)) {
            builder.like("name", name);
        }
        if (type != null) {
            builder.eq("type", type);
        }
        if (!StrUtil.isEmpty(endTime)) {
            String startStr = endTime + " 00:00:00";
            String endStr = endTime + " 23:59:59";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = null;
            try {
                start = sdf.parse(startStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date end = null;
            try {
                end = sdf.parse(endStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (start != null && end != null) {
                builder.between("endTime", start, end);
            }
        }
        return advertiseRepository.findAll(builder.build());
    }
}
