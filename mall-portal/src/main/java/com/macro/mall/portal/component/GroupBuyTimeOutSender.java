package com.macro.mall.portal.component;

import com.macro.mall.portal.domain.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 拼团超时延时消息发送者
 */
@Component
public class GroupBuyTimeOutSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupBuyTimeOutSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendTimeoutMessage(Long teamId, long delayMillis) {
        amqpTemplate.convertAndSend(
                QueueEnum.QUEUE_TTL_GROUP_BUY_TIMEOUT.getExchange(),
                QueueEnum.QUEUE_TTL_GROUP_BUY_TIMEOUT.getRouteKey(),
                teamId,
                message -> {
                    message.getMessageProperties().setExpiration(String.valueOf(delayMillis));
                    return message;
                }
        );
        LOGGER.info("send groupBuy timeout teamId:{} delayMillis:{}", teamId, delayMillis);
    }
}
