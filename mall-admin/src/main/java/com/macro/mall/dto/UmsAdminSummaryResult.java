package com.macro.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author shiyue
 * @version 1.0 4/15/20
 */
@Getter
@Setter
public class UmsAdminSummaryResult {
    // 今日订单
    @ApiModelProperty(value = "今日订单")
    private Integer todayOrders;
    // 今日销售额
    @ApiModelProperty(value = "今日销售额")
    private String todaySales;
    // 昨日销售额
    @ApiModelProperty(value = "昨日销售额")
    private String lastDaySales;
    // 待发货
    @ApiModelProperty(value = "待发货")
    private Integer pendingShips;
    // 已发货
    @ApiModelProperty(value = "已发货")
    private Integer ships;
    // 已上架
    @ApiModelProperty(value = "已上架")
    private Integer onSale;
    // 已下架
    @ApiModelProperty(value = "已下架")
    private Integer offlineSales;
    // 全部商品
    @ApiModelProperty(value = "全部商品")
    private Integer allGoods;
    // 今日新增用户
    @ApiModelProperty(value = "今日新增用户")
    private Integer newTodayUser;
    // 昨日新增用户
    @ApiModelProperty(value = "昨日新增用户")
    private Integer lastDayUsers;
    // 近七日新增用户
    @ApiModelProperty(value = "近七日新增用户")
    private Integer sevenDayUsers;
    // 全部用户数
    @ApiModelProperty(value = "全部用户数")
    private Integer allUsers;
    // 月订单
    @ApiModelProperty(value = "月订单")
    private Integer monthlyOrders;
    // 周订单
    @ApiModelProperty(value = "周订单")
    private Integer weeklyOrders;
    // 月销售额
    @ApiModelProperty(value = "月销售额")
    private String monthlySales;
    // 周销售额
    @ApiModelProperty(value = "周销售额")
    private String weeklySales;

}
