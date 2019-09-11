package com.macro.mall.portal.component;

import com.google.common.collect.Lists;
import com.macro.mall.model.OmsOrder;
import com.macro.mall.model.UmsMember;
import com.macro.mall.portal.dao.PortalOrderDao;
import com.macro.mall.portal.domain.OmsOrderDetail;
import com.macro.mall.portal.service.OmsPortalOrderService;
import com.macro.mall.portal.service.UmsMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.DelayQueue;

@Slf4j
@Component
public class OrderQueueManager {


    private DelayQueue<OrderDelayedTask> queue;

    // 守护线程
    private Thread daemonThread;

    @Autowired
    private PortalOrderDao portalOrderDao;

    @Autowired
    private UmsMemberService memberService;

    @Autowired
    private OmsPortalOrderService orderService;


    private static OrderQueueManager instance = new OrderQueueManager();

    private OrderQueueManager() {
        queue = new DelayQueue<>();
        init();
    }

    public static OrderQueueManager getInstance() {
        return instance;
    }

    /**
     * 初始化
     */
    public void init() {
        daemonThread = new Thread(() -> execute());
        daemonThread.setName("DelayQueueMonitor");
        daemonThread.start();
    }


    public void execute() {
        while (true) {
            log.info("当前延时队列的任务数量:{}", queue.size());
            try {
                OrderDelayedTask task = queue.take();
                if (null == task) {
                    continue;
                }
                OmsOrder omsOrder = task.getOmsOrder();
                portalOrderDao.updateOrderStatus(Lists.newArrayList(omsOrder.getId()), 4);
                OmsOrderDetail timeOutOrder = portalOrderDao.getDetail(omsOrder.getId());
                //解除订单商品库存锁定
                portalOrderDao.releaseSkuStockLock(timeOutOrder.getOrderItemList());
                //修改优惠券使用状态
                orderService.updateCouponStatus(timeOutOrder.getCouponId(), timeOutOrder.getMemberId(), 0);
                //返还使用积分
                if (timeOutOrder.getUseIntegration() != null) {
                    UmsMember member = memberService.getById(timeOutOrder.getMemberId());
                    memberService.updateIntegration(timeOutOrder.getMemberId(), member.getIntegration() + timeOutOrder.getUseIntegration());
                }
            } catch (InterruptedException e) {
                log.error("获取延时任务失败！", e.getMessage());
            }
        }
    }

    public void setQueue(OrderDelayedTask task) {
        queue.put(task);
    }

    public void removeQueue(OrderDelayedTask task) {
        queue.remove(task);
    }
}
