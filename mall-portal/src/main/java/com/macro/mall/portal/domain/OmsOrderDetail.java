package com.macro.mall.portal.domain;

import com.macro.mall.model.OmsOrder;
import com.macro.mall.model.OmsOrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 包含商品信息的订单详情
 * Created by macro on 2018/9/4.
 */
@Getter
@Setter
public class OmsOrderDetail extends OmsOrder {
    private List<OmsOrderItem> orderItemList;
}
