package com.macro.mall.service.impl;


import com.macro.mall.common.util.SpecificationBuilder;
import cn.hutool.core.util.StrUtil;
import com.macro.mall.repository.SmsHomeRecommendSubjectRepository;
import com.macro.mall.model.SmsHomeRecommendSubject;
import com.macro.mall.service.SmsHomeRecommendSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页专题推荐管理Service实现类
 * Created by macro on 2018/11/7.
 */
@Service
public class SmsHomeRecommendSubjectServiceImpl implements SmsHomeRecommendSubjectService {
    @Autowired
    private SmsHomeRecommendSubjectRepository smsHomeRecommendSubjectRepository;
    @Override
    public int create(List<SmsHomeRecommendSubject> recommendSubjectList) {
        for (SmsHomeRecommendSubject recommendSubject : recommendSubjectList) {
            recommendSubject.setRecommendStatus(1);
            recommendSubject.setSort(0);
            smsHomeRecommendSubjectRepository.save(recommendSubject);
        }
        return recommendSubjectList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendSubject recommendSubject = new SmsHomeRecommendSubject();
        recommendSubject.setId(id);
        recommendSubject.setSort(sort);
        smsHomeRecommendSubjectRepository.save(recommendSubject);
        return 1;
    }

    @Override
    public int delete(List<Long> ids) {
        smsHomeRecommendSubjectRepository.deleteAllByIdInBatch(ids);
        return ids.size();
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        List<SmsHomeRecommendSubject> subjects = smsHomeRecommendSubjectRepository.findAllById(ids);
        for (SmsHomeRecommendSubject subject : subjects) {
            subject.setRecommendStatus(recommendStatus);
        }
        smsHomeRecommendSubjectRepository.saveAll(subjects);
        return subjects.size();
    }

    @Override
    public List<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        SpecificationBuilder<SmsHomeRecommendSubject> builder = SpecificationBuilder.create();
        if(!StrUtil.isEmpty(subjectName)){
            builder.like("subjectName", subjectName);
        }
        if(recommendStatus!=null){
            builder.eq("recommendStatus", recommendStatus);
        }
        return smsHomeRecommendSubjectRepository.findAll(builder.build());
    }
}
