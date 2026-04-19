package com.macro.mall.dao;

import com.macro.mall.model.SmsGroupBuyActivity;
import com.macro.mall.model.SmsGroupBuyProduct;
import com.macro.mall.model.SmsGroupBuyTeam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 拼团活动管理自定义Dao
 */
public interface SmsGroupBuyActivityDao {
    /**
     * 分页查询拼团活动
     */
    List<SmsGroupBuyActivity> listActivity(@Param("keyword") String keyword,
                                           @Param("status") Integer status);

    /**
     * 判断活动是否存在进行中的团
     */
    int countOngoingTeam(@Param("activityId") Long activityId);

    /**
     * 活动下的商品列表
     */
    List<SmsGroupBuyProduct> listProductByActivity(@Param("activityId") Long activityId);

    /**
     * 团记录分页查询
     */
    List<SmsGroupBuyTeam> listTeam(@Param("activityId") Long activityId,
                                   @Param("status") Integer status,
                                   @Param("teamNo") String teamNo);
}
