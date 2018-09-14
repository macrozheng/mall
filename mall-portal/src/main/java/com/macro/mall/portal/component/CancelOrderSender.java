package com.macro.mall.portal.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的发出者
 * Created by macro on 2018/9/14.
 */
@Component
public class CancelOrderSender {
    private static Logger LOGGER =LoggerFactory.getLogger(CancelOrderSender.class);
    @Value("${rabbitmq.queue.name.cancelOrder}")
    private String QUEUE_NAME_CANCEL_ORDER;
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(Long orderId){
        amqpTemplate.convertAndSend(QUEUE_NAME_CANCEL_ORDER,orderId);
        LOGGER.info("send orderId:{}",orderId);
    }
}
