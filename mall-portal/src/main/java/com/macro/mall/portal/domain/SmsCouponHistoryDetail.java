package com.macro.mall.portal.domain;

import com.macro.mall.model.SmsCoupon;
import com.macro.mall.model.SmsCouponHistory;
import com.macro.mall.model.SmsCouponProductCategoryRelation;
import com.macro.mall.model.SmsCouponProductRelation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 优惠券领取历史详情（包括优惠券信息和关联关系）
 * Created by macro on 2018/8/29.
 */
@Getter
@Setter
public class SmsCouponHistoryDetail extends SmsCouponHistory {
    private SmsCoupon coupon;
    private List<SmsCouponProductRelation> productRelationList;
    private List<SmsCouponProductCategoryRelation> categoryRelationList;
}
