package com.macro.mall.portal.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 优惠券自定义Dao
 * Created by macro on 2018/8/27.
 */
public interface PortalSmsCouponDao {
    /**
     * 原子扣减优惠券可领取数量并累加领取数量：仅当可领取数量(count)>=1时生效
     *
     * @return 影响行数（0表示已领完，扣减失败）
     */
    int deductCouponCount(@Param("couponId") Long couponId);
}
