package com.macro.mall.service.impl;

import com.macro.mall.common.util.SpecificationBuilder;
import com.macro.mall.repository.OmsOrderReturnReasonRepository;
import com.macro.mall.model.OmsOrderReturnReason;
import com.macro.mall.service.OmsOrderReturnReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 订单原因管理Service实现类
 * Created by macro on 2018/10/17.
 */
@Service
public class OmsOrderReturnReasonServiceImpl implements OmsOrderReturnReasonService {
    @Autowired
    private OmsOrderReturnReasonRepository returnReasonRepository;

    @Override
    public int create(OmsOrderReturnReason returnReason) {
        returnReason.setCreateTime(new Date());
        returnReasonRepository.save(returnReason);
        return 1;
    }

    @Override
    public int update(Long id, OmsOrderReturnReason returnReason) {
        returnReason.setId(id);
        returnReasonRepository.save(returnReason);
        return 1;
    }

    @Override
    public int delete(List<Long> ids) {
        returnReasonRepository.deleteAllByIdInBatch(ids);
        return ids.size();
    }

    @Override
    public List<OmsOrderReturnReason> list(Integer pageSize, Integer pageNum) {
        return returnReasonRepository.findAll();
    }

    @Override
    public int updateStatus(List<Long> ids, Integer status) {
        if(!status.equals(0)&&!status.equals(1)){
            return 0;
        }
        List<OmsOrderReturnReason> reasons = returnReasonRepository.findAllById(ids);
        for (OmsOrderReturnReason reason : reasons) {
            reason.setStatus(status);
        }
        returnReasonRepository.saveAll(reasons);
        return ids.size();
    }

    @Override
    public OmsOrderReturnReason getItem(Long id) {
        return returnReasonRepository.findById(id).orElse(null);
    }
}
