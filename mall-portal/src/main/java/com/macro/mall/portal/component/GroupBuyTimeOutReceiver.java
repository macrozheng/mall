package com.macro.mall.portal.component;

import com.macro.mall.portal.service.GroupBuyOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 拼团超时消费者
 */
@Component
@RabbitListener(queues = "mall.groupBuy.timeout")
public class GroupBuyTimeOutReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupBuyTimeOutReceiver.class);

    @Autowired
    private GroupBuyOrderService groupBuyOrderService;

    @RabbitHandler
    public void handle(Long teamId) {
        try {
            groupBuyOrderService.handleTeamTimeout(teamId);
            LOGGER.info("handle groupBuy timeout teamId:{}", teamId);
        } catch (Exception e) {
            LOGGER.error("handle groupBuy timeout error teamId:{}", teamId, e);
        }
    }
}
