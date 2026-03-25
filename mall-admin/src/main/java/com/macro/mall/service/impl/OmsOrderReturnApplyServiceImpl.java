package com.macro.mall.service.impl;

import com.macro.mall.common.util.SpecificationBuilder;
import com.macro.mall.dto.OmsOrderReturnApplyResult;
import com.macro.mall.dto.OmsReturnApplyQueryParam;
import com.macro.mall.dto.OmsUpdateStatusParam;
import com.macro.mall.repository.OmsOrderReturnApplyRepository;
import com.macro.mall.model.OmsOrderReturnApply;
import com.macro.mall.service.OmsOrderReturnApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 订单退货管理Service实现类
 * Created by macro on 2018/10/18.
 */
@Service
public class OmsOrderReturnApplyServiceImpl implements OmsOrderReturnApplyService {
    @Autowired
    private OmsOrderReturnApplyRepository returnApplyRepository;

    @Override
    public List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum) {
        // TODO: 实现复杂查询
        return returnApplyRepository.findAll();
    }

    @Override
    public int delete(List<Long> ids) {
        returnApplyRepository.deleteAllByIdInBatch(ids);
        return ids.size();
    }

    @Override
    public int updateStatus(Long id, OmsUpdateStatusParam statusParam) {
        Integer status = statusParam.getStatus();
        OmsOrderReturnApply returnApply = returnApplyRepository.findById(id).orElse(null);
        if (returnApply == null) {
            return 0;
        }
        if(status.equals(1)){
            //确认退货
            returnApply.setStatus(1);
            returnApply.setReturnAmount(statusParam.getReturnAmount());
            returnApply.setCompanyAddressId(statusParam.getCompanyAddressId());
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        }else if(status.equals(2)){
            //完成退货
            returnApply.setStatus(2);
            returnApply.setReceiveTime(new Date());
            returnApply.setReceiveMan(statusParam.getReceiveMan());
            returnApply.setReceiveNote(statusParam.getReceiveNote());
        }else if(status.equals(3)){
            //拒绝退货
            returnApply.setStatus(3);
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        }else{
            return 0;
        }
        returnApplyRepository.save(returnApply);
        return 1;
    }

    @Override
    public OmsOrderReturnApplyResult getItem(Long id) {
        // TODO: 实现退货详情查询
        OmsOrderReturnApply apply = returnApplyRepository.findById(id).orElse(null);
        if (apply == null) {
            return null;
        }
        OmsOrderReturnApplyResult result = new OmsOrderReturnApplyResult();
        // 复制属性
        return result;
    }
}
