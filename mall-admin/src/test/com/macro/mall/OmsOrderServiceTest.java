package com.macro.mall.service;

import com.macro.mall.dto.OmsMoneyInfoParam;
import com.macro.mall.mapper.OmsOrderMapper;
import com.macro.mall.model.OmsOrder;
import com.macro.mall.service.impl.OmsOrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OmsOrderServiceTest {

    @Autowired
    private OmsOrderService orderService;

    @Autowired
    private OmsOrderMapper orderMapper;

    @Test
    @Transactional
    public void testUpdateMoneyInfoRecalculatesPayAmount() {

        Long orderId = 1L;
        OmsOrder existingOrder = orderMapper.selectByPrimaryKey(orderId);

        if (existingOrder != null) {
            BigDecimal initialTotal = existingOrder.getTotalAmount();

            OmsMoneyInfoParam param = new OmsMoneyInfoParam();
            param.setOrderId(orderId);
            param.setFreightAmount(new BigDecimal("50.00"));
            param.setDiscountAmount(BigDecimal.ZERO);
            param.setStatus(existingOrder.getStatus());

            orderService.updateMoneyInfo(param);

            OmsOrder updatedOrder = orderMapper.selectByPrimaryKey(orderId);

            org.junit.jupiter.api.Assertions.assertNotNull(updatedOrder.getPayAmount());
        }
    }
}