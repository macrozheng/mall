package com.macro.mall.dao;

import com.macro.mall.dto.OmsOrderDeliveryParam;
import com.macro.mall.dto.OmsOrderDetail;
import com.macro.mall.dto.OmsOrderQueryParam;
import com.macro.mall.model.OmsOrder;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 订单自定义查询Dao
 * Created by macro on 2018/10/12.
 */
public interface OmsOrderDao {
    /**
     * 条件查询订单
     */
    List<OmsOrder> getList(@Param("queryParam") OmsOrderQueryParam queryParam);

    /**
     * 批量发货
     */
    int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * 获取订单详情
     */
    OmsOrderDetail getDetail(@Param("id") Long id);

    /**
     * 根据日，周，月统计订单数量
     */
    Integer getOrdersCountByParam(@Param("queryParam") Map<String, String> queryParam);

    /**
     * 根据昨日，今日，周，月统计订单销售额
     */
    BigDecimal getOrdersAmountByParam(@Param("queryParam") Map<String, String> queryParam);

    /**
     * 根据订单状态统计订单数量
     */
    Integer getOrderCountByStatus(@Param("queryParam") Map<String, String> queryParam);
}
