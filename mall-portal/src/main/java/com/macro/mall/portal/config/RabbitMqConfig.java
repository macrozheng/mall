package com.macro.mall.portal.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置
 * Created by macro on 2018/9/14.
 */
@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.queue.name.cancelOrder}")
    private String QUEUE_NAME_CANCEL_ORDER;
    /**
     * 超时取消订单的消息
     */
    @Bean
    public Queue cancelOrderQueue(){
        return new Queue(QUEUE_NAME_CANCEL_ORDER);
    }
}
