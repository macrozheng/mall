package com.macro.mall.portal.component;

import com.macro.mall.portal.service.SmsGroupBuyingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GroupTimeOutCancelTask {
    private final Logger LOGGER = LoggerFactory.getLogger(GroupTimeOutCancelTask.class);

    @Autowired
    private SmsGroupBuyingService groupBuyingService;

    @Scheduled(cron = "0 0/5 * ? * ?")
    private void cancelTimeOutGroup() {
        groupBuyingService.cancelTimeOutTeam();
        LOGGER.info("超时拼团处理完成");
    }
}
