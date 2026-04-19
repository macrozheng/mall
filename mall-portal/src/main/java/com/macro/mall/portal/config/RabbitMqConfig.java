package com.macro.mall.portal.config;

import com.macro.mall.portal.domain.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列相关配置
 * Created by macro on 2018/9/14.
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 订单消息实际消费队列所绑定的交换机
     */
    @Bean
    DirectExchange orderDirect() {
        return ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 订单延迟队列所绑定的交换机
     */
    @Bean
    DirectExchange orderTtlDirect() {
        return ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 订单实际消费队列
     */
    @Bean
    public Queue orderQueue() {
        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getName());
    }

    /**
     * 订单延迟队列（死信队列）
     */
    @Bean
    public Queue orderTtlQueue() {
        return QueueBuilder
                .durable(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getName())
                .withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_ORDER_CANCEL.getExchange())//到期后转发的交换机
                .withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey())//到期后转发的路由键
                .build();
    }

    /**
     * 将订单队列绑定到交换机
     */
    @Bean
    Binding orderBinding(DirectExchange orderDirect,Queue orderQueue){
        return BindingBuilder
                .bind(orderQueue)
                .to(orderDirect)
                .with(QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey());
    }

    /**
     * 将订单延迟队列绑定到交换机
     */
    @Bean
    Binding orderTtlBinding(DirectExchange orderTtlDirect,Queue orderTtlQueue){
        return BindingBuilder
                .bind(orderTtlQueue)
                .to(orderTtlDirect)
                .with(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey());
    }

    /**
     * 拼团超时实际消费队列所绑定的交换机
     */
    @Bean
    DirectExchange groupBuyDirect() {
        return ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_GROUP_BUY_TIMEOUT.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 拼团超时延迟队列所绑定的交换机
     */
    @Bean
    DirectExchange groupBuyTtlDirect() {
        return ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_TTL_GROUP_BUY_TIMEOUT.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 拼团超时实际消费队列
     */
    @Bean
    public Queue groupBuyQueue() {
        return new Queue(QueueEnum.QUEUE_GROUP_BUY_TIMEOUT.getName());
    }

    /**
     * 拼团超时延迟队列(死信队列)
     */
    @Bean
    public Queue groupBuyTtlQueue() {
        return QueueBuilder
                .durable(QueueEnum.QUEUE_TTL_GROUP_BUY_TIMEOUT.getName())
                .withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_GROUP_BUY_TIMEOUT.getExchange())
                .withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_GROUP_BUY_TIMEOUT.getRouteKey())
                .build();
    }

    @Bean
    Binding groupBuyBinding(DirectExchange groupBuyDirect, Queue groupBuyQueue) {
        return BindingBuilder
                .bind(groupBuyQueue)
                .to(groupBuyDirect)
                .with(QueueEnum.QUEUE_GROUP_BUY_TIMEOUT.getRouteKey());
    }

    @Bean
    Binding groupBuyTtlBinding(DirectExchange groupBuyTtlDirect, Queue groupBuyTtlQueue) {
        return BindingBuilder
                .bind(groupBuyTtlQueue)
                .to(groupBuyTtlDirect)
                .with(QueueEnum.QUEUE_TTL_GROUP_BUY_TIMEOUT.getRouteKey());
    }

}
