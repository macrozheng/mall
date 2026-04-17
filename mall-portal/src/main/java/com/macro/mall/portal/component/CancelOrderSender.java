package com.macro.mall.portal.component;

import com.macro.mall.portal.domain.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CancelOrderSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(CancelOrderSender.class);
    
    @Autowired(required = false)
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Long orderId, final long delayTimes) {
        if (amqpTemplate == null) {
            LOGGER.warn("RabbitMQ not available, cannot send cancel order message for orderId: {}", orderId);
            return;
        }
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(), QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(), orderId, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                return message;
            }
        });
        LOGGER.info("send orderId:{}", orderId);
    }
}
