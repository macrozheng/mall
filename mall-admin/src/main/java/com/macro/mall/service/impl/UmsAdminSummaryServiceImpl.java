package com.macro.mall.service.impl;

import com.macro.mall.dao.OmsOrderDao;
import com.macro.mall.dao.PmsProductDao;
import com.macro.mall.dao.UmsMemberDao;
import com.macro.mall.dto.UmsAdminSummaryResult;
import com.macro.mall.service.UmsAdminSummaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shiyue
 * @version 1.0 4/15/20
 */
@Service
public class UmsAdminSummaryServiceImpl implements UmsAdminSummaryService {

    @Resource
    private UmsMemberDao umsMemberDao;

    @Resource
    private PmsProductDao pmsProductDao;

    @Resource
    private OmsOrderDao omsOrderDao;

    @Override
    public UmsAdminSummaryResult getAdminSummary() {

        UmsAdminSummaryResult umsAdminSummaryResult = new UmsAdminSummaryResult();
        // 昨日，今日，近七日，近30日(含今日)
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("yesterday", "昨日");
        int lastDayUsers = umsMemberDao.getUsersCountByParam(queryParam);
        BigDecimal lastDaySales = omsOrderDao.getOrdersAmountByParam(queryParam);
        queryParam.remove("yesterday");
        queryParam.put("today", "今日");
        BigDecimal todaySales = omsOrderDao.getOrdersAmountByParam(queryParam);
        Integer todayOrders = omsOrderDao.getOrdersCountByParam(queryParam);
        Integer newTodayUser = umsMemberDao.getUsersCountByParam(queryParam);
        queryParam.remove("today");
        queryParam.put("week", "周");
        Integer sevenDayUsers = umsMemberDao.getUsersCountByParam(queryParam);
        Integer weeklyOrders = omsOrderDao.getOrdersCountByParam(queryParam);
        BigDecimal weeklySales = omsOrderDao.getOrdersAmountByParam(queryParam);
        queryParam.remove("week");
        queryParam.put("month", "月");
        Integer monthlyOrders = omsOrderDao.getOrdersCountByParam(queryParam);
        BigDecimal monthlySales = omsOrderDao.getOrdersAmountByParam(queryParam);
        Integer allGoods = pmsProductDao.getProducts();
        Integer allUsers = umsMemberDao.getUsersCount();
        queryParam.remove("month");
        queryParam.put("ships", "已发货");
        Integer ships = omsOrderDao.getOrderCountByStatus(queryParam);
        queryParam.remove("ships");
        queryParam.put("pending", "待发货");
        Integer pendingShips = omsOrderDao.getOrderCountByStatus(queryParam);
        queryParam.remove("pending");
        queryParam.put("sales", "已上架");
        Integer onSale = pmsProductDao.getProductsByQueryParam(queryParam);
        queryParam.remove("sales");
        queryParam.put("pending", "已下架");
        Integer offlineSales = pmsProductDao.getProductsByQueryParam(queryParam);
        umsAdminSummaryResult.setTodayOrders(todayOrders);
        umsAdminSummaryResult.setTodaySales(todaySales != null ? todaySales.toString() : new BigDecimal("0.00").toString());
        umsAdminSummaryResult.setLastDaySales(lastDaySales != null ? lastDaySales.toString() : new BigDecimal("0.00").toString());
        umsAdminSummaryResult.setPendingShips(pendingShips);
        umsAdminSummaryResult.setShips(ships);
        umsAdminSummaryResult.setOnSale(onSale);
        umsAdminSummaryResult.setOfflineSales(offlineSales);
        umsAdminSummaryResult.setAllGoods(allGoods);
        umsAdminSummaryResult.setNewTodayUser(newTodayUser);
        umsAdminSummaryResult.setLastDayUsers(lastDayUsers);
        umsAdminSummaryResult.setSevenDayUsers(sevenDayUsers);
        umsAdminSummaryResult.setAllUsers(allUsers);
        umsAdminSummaryResult.setMonthlyOrders(monthlyOrders);
        umsAdminSummaryResult.setWeeklyOrders(weeklyOrders);
        umsAdminSummaryResult.setMonthlySales(monthlySales != null ? monthlySales.toString() : new BigDecimal("0.00").toString());
        umsAdminSummaryResult.setWeeklySales(weeklySales != null ? weeklySales.toString() : new BigDecimal("0.00").toString());

        return umsAdminSummaryResult;
    }
}
