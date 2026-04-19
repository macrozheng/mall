package com.macro.mall.portal.domain;

import lombok.Getter;

/**
 * 消息队列枚举类
 * Created by macro on 2018/9/14.
 */
@Getter
public enum QueueEnum {
    /**
     * 消息通知队列
     */
    QUEUE_ORDER_CANCEL("mall.order.direct", "mall.order.cancel", "mall.order.cancel"),
    /**
     * 消息通知ttl队列
     */
    QUEUE_TTL_ORDER_CANCEL("mall.order.direct.ttl", "mall.order.cancel.ttl", "mall.order.cancel.ttl"),
    /**
     * 拼团超时消费队列
     */
    QUEUE_GROUP_BUY_TIMEOUT("mall.groupBuy.direct", "mall.groupBuy.timeout", "mall.groupBuy.timeout"),
    /**
     * 拼团超时TTL延时队列
     */
    QUEUE_TTL_GROUP_BUY_TIMEOUT("mall.groupBuy.direct.ttl", "mall.groupBuy.timeout.ttl", "mall.groupBuy.timeout.ttl");

    /**
     * 交换名称
     */
    private final String exchange;
    /**
     * 队列名称
     */
    private final String name;
    /**
     * 路由键
     */
    private final String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
