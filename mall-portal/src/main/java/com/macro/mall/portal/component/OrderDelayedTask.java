package com.macro.mall.portal.component;

import com.macro.mall.model.OmsOrder;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class OrderDelayedTask implements Delayed {


    private OmsOrder omsOrder;
    /**延时时间 单位 timeunit.milliseconds**/
    private long timeout;

    private long expire;

    public OrderDelayedTask(OmsOrder order, long timeout){
        this.omsOrder = order;
        this.timeout = timeout;
        this.expire = System.currentTimeMillis() + timeout;
    }

    public OmsOrder getOmsOrder() {
        return omsOrder;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(expire - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) -o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "OrderDelayedTask{" +
                "omsOrder=" + omsOrder +
                ", timeout=" + timeout +
                '}';
    }
}
