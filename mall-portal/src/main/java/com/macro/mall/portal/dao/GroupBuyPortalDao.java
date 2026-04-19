package com.macro.mall.portal.dao;

import com.macro.mall.model.SmsGroupBuyActivity;
import com.macro.mall.model.SmsGroupBuyProduct;
import com.macro.mall.model.SmsGroupBuyRecord;
import com.macro.mall.model.SmsGroupBuyTeam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 拼团前台自定义Dao
 */
public interface GroupBuyPortalDao {

    /**
     * 查询进行中的活动列表
     */
    List<SmsGroupBuyActivity> listOngoingActivity();

    /**
     * 查询活动下的商品SKU列表
     */
    List<SmsGroupBuyProduct> listActivityProduct(@Param("activityId") Long activityId);

    /**
     * 根据 SKU 定位活动商品
     */
    SmsGroupBuyProduct getProductBySku(@Param("activityId") Long activityId,
                                       @Param("skuId") Long skuId);

    /**
     * 根据ID查询活动商品(带行锁,用于下单)
     */
    SmsGroupBuyProduct getProductForUpdate(@Param("id") Long id);

    /**
     * 原子锁定库存: group_stock >= n 时扣减 group_stock、增加 locked_stock
     * @return 受影响行数,0 表示库存不足
     */
    int lockStock(@Param("id") Long id, @Param("quantity") Integer quantity);

    /**
     * 原子释放锁定库存(团失败/取消时)
     */
    int releaseStock(@Param("id") Long id, @Param("quantity") Integer quantity);

    /**
     * 成团结算库存: locked_stock -= n, sold_count += n
     */
    int finalizeStock(@Param("id") Long id, @Param("quantity") Integer quantity);

    /**
     * 根据 team_no 查团
     */
    SmsGroupBuyTeam getTeamByNo(@Param("teamNo") String teamNo);

    /**
     * 原子递增团人数: 仅当 status=0 && current_num < target_num
     * @return 受影响行数
     */
    int incrementTeamCurrentNum(@Param("teamId") Long teamId);

    /**
     * 查询团内参团记录
     */
    List<SmsGroupBuyRecord> listRecordsByTeam(@Param("teamId") Long teamId);

    /**
     * 根据订单号查询参团记录
     */
    SmsGroupBuyRecord getRecordByOrderSn(@Param("orderSn") String orderSn);

    /**
     * 将团内已支付未成团的参团记录批量置为成团成功
     */
    int finalizeRecords(@Param("teamId") Long teamId);

    /**
     * 将团内已支付未成团的参团记录批量置为成团失败
     */
    int failRecords(@Param("teamId") Long teamId);

    /**
     * 统计会员在活动中的有效参团次数(未取消)
     */
    int countMemberJoin(@Param("activityId") Long activityId, @Param("memberId") Long memberId);

    /**
     * 查询进行中可参与的团列表
     */
    List<SmsGroupBuyTeam> listOngoingTeamByActivity(@Param("activityId") Long activityId);

    /**
     * 查询会员的参团记录
     */
    List<SmsGroupBuyRecord> listMemberRecords(@Param("memberId") Long memberId);

    /**
     * 累加活动开团计数
     */
    int incrementTotalGroupCount(@Param("activityId") Long activityId);

    /**
     * 累加活动成团计数
     */
    int incrementSuccessGroupCount(@Param("activityId") Long activityId);
}
