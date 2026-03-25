package com.macro.mall.service.impl;

import com.macro.mall.common.util.SpecificationBuilder;
import com.macro.mall.dto.*;
import com.macro.mall.repository.OmsOrderRepository;
import com.macro.mall.repository.OmsOrderOperateHistoryRepository;
import com.macro.mall.model.OmsOrder;
import com.macro.mall.model.OmsOrderOperateHistory;
import com.macro.mall.service.OmsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单管理Service实现类
 * Created by macro on 2018/10/11.
 */
@Service
public class OmsOrderServiceImpl implements OmsOrderService {
    @Autowired
    private OmsOrderOperateHistoryRepository orderOperateHistoryRepository;
    @Autowired
    private OmsOrderRepository orderRepository;

    @Override
    public List<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
        // TODO: 实现复杂查询
        return orderRepository.findAll();
    }

    @Override
    public int delivery(List<OmsOrderDeliveryParam> deliveryParamList) {
        //批量发货
        // TODO: 实现批量发货
        int count = deliveryParamList.size();
        //添加操作记录
        List<OmsOrderOperateHistory> operateHistoryList = deliveryParamList.stream()
                .map(omsOrderDeliveryParam -> {
                    OmsOrderOperateHistory history = new OmsOrderOperateHistory();
                    history.setOrderId(omsOrderDeliveryParam.getOrderId());
                    history.setCreateTime(new Date());
                    history.setOperateMan("后台管理员");
                    history.setOrderStatus(2);
                    history.setNote("完成发货");
                    return history;
                }).collect(Collectors.toList());
        orderOperateHistoryRepository.saveAll(operateHistoryList);
        return count;
    }

    @Override
    public int close(List<Long> ids, String note) {
        List<OmsOrder> orders = orderRepository.findAllById(ids);
        for (OmsOrder order : orders) {
            order.setStatus(4);
        }
        orderRepository.saveAll(orders);
        List<OmsOrderOperateHistory> historyList = ids.stream().map(orderId -> {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(orderId);
            history.setCreateTime(new Date());
            history.setOperateMan("后台管理员");
            history.setOrderStatus(4);
            history.setNote("订单关闭:"+note);
            return history;
        }).collect(Collectors.toList());
        orderOperateHistoryRepository.saveAll(historyList);
        return ids.size();
    }

    @Override
    public int delete(List<Long> ids) {
        List<OmsOrder> orders = orderRepository.findAllById(ids);
        for (OmsOrder order : orders) {
            order.setDeleteStatus(1);
        }
        orderRepository.saveAll(orders);
        return ids.size();
    }

    @Override
    public OmsOrderDetail detail(Long id) {
        // TODO: 实现订单详情查询
        OmsOrder order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return null;
        }
        // 复制订单基本属性到详情对象
        OmsOrderDetail detail = new OmsOrderDetail();
        detail.setId(order.getId());
        detail.setMemberId(order.getMemberId());
        detail.setCouponId(order.getCouponId());
        detail.setOrderSn(order.getOrderSn());
        detail.setCreateTime(order.getCreateTime());
        detail.setMemberUsername(order.getMemberUsername());
        detail.setTotalAmount(order.getTotalAmount());
        detail.setPayAmount(order.getPayAmount());
        detail.setFreightAmount(order.getFreightAmount());
        detail.setPromotionAmount(order.getPromotionAmount());
        detail.setIntegrationAmount(order.getIntegrationAmount());
        detail.setCouponAmount(order.getCouponAmount());
        detail.setDiscountAmount(order.getDiscountAmount());
        detail.setPayType(order.getPayType());
        detail.setSourceType(order.getSourceType());
        detail.setStatus(order.getStatus());
        detail.setOrderType(order.getOrderType());
        detail.setDeliveryCompany(order.getDeliveryCompany());
        detail.setDeliverySn(order.getDeliverySn());
        detail.setAutoConfirmDay(order.getAutoConfirmDay());
        detail.setIntegration(order.getIntegration());
        detail.setGrowth(order.getGrowth());
        detail.setPromotionInfo(order.getPromotionInfo());
        detail.setBillType(order.getBillType());
        detail.setBillHeader(order.getBillHeader());
        detail.setBillContent(order.getBillContent());
        detail.setBillReceiverPhone(order.getBillReceiverPhone());
        detail.setBillReceiverEmail(order.getBillReceiverEmail());
        detail.setReceiverName(order.getReceiverName());
        detail.setReceiverPhone(order.getReceiverPhone());
        detail.setReceiverPostCode(order.getReceiverPostCode());
        detail.setReceiverProvince(order.getReceiverProvince());
        detail.setReceiverCity(order.getReceiverCity());
        detail.setReceiverRegion(order.getReceiverRegion());
        detail.setReceiverDetailAddress(order.getReceiverDetailAddress());
        detail.setNote(order.getNote());
        detail.setConfirmStatus(order.getConfirmStatus());
        detail.setDeleteStatus(order.getDeleteStatus());
        detail.setUseIntegration(order.getUseIntegration());
        detail.setPaymentTime(order.getPaymentTime());
        detail.setDeliveryTime(order.getDeliveryTime());
        detail.setReceiveTime(order.getReceiveTime());
        detail.setCommentTime(order.getCommentTime());
        detail.setModifyTime(order.getModifyTime());
        return detail;
    }

    @Override
    public int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam) {
        OmsOrder order = orderRepository.findById(receiverInfoParam.getOrderId()).orElse(null);
        if (order == null) {
            return 0;
        }
        order.setReceiverName(receiverInfoParam.getReceiverName());
        order.setReceiverPhone(receiverInfoParam.getReceiverPhone());
        order.setReceiverPostCode(receiverInfoParam.getReceiverPostCode());
        order.setReceiverDetailAddress(receiverInfoParam.getReceiverDetailAddress());
        order.setReceiverProvince(receiverInfoParam.getReceiverProvince());
        order.setReceiverCity(receiverInfoParam.getReceiverCity());
        order.setReceiverRegion(receiverInfoParam.getReceiverRegion());
        order.setModifyTime(new Date());
        orderRepository.save(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(receiverInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(receiverInfoParam.getStatus());
        history.setNote("修改收货人信息");
        orderOperateHistoryRepository.save(history);
        return 1;
    }

    @Override
    public int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam) {
        OmsOrder order = orderRepository.findById(moneyInfoParam.getOrderId()).orElse(null);
        if (order == null) {
            return 0;
        }
        order.setFreightAmount(moneyInfoParam.getFreightAmount());
        order.setDiscountAmount(moneyInfoParam.getDiscountAmount());
        order.setModifyTime(new Date());
        orderRepository.save(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(moneyInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(moneyInfoParam.getStatus());
        history.setNote("修改费用信息");
        orderOperateHistoryRepository.save(history);
        return 1;
    }

    @Override
    public int updateNote(Long id, String note, Integer status) {
        OmsOrder order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return 0;
        }
        order.setNote(note);
        order.setModifyTime(new Date());
        orderRepository.save(order);
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(status);
        history.setNote("修改备注信息："+note);
        orderOperateHistoryRepository.save(history);
        return 1;
    }
}
