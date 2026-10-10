package com.macro.mall.portal.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 优惠券自定义Dao
 * Created by macro on 2018/8/27.
 */
public interface PortalSmsCouponDao {
    /**
     * 原子核销会员的优惠券：仅当领取记录未使用时置为已使用
     *
     * @return 影响行数（0表示该券已被使用，核销失败）
     */
    int claimCouponHistory(@Param("couponId") Long couponId, @Param("memberId") Long memberId);
}
