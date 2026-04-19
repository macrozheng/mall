package com.macro.mall.portal;

import com.macro.mall.mapper.SmsGroupBuyActivityMapper;
import com.macro.mall.mapper.SmsGroupBuyProductMapper;
import com.macro.mall.mapper.SmsGroupBuyTeamMapper;
import com.macro.mall.model.SmsGroupBuyActivity;
import com.macro.mall.model.SmsGroupBuyProduct;
import com.macro.mall.model.SmsGroupBuyTeam;
import com.macro.mall.portal.dao.GroupBuyPortalDao;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 拼团 Dao 集成测试
 *
 * 默认 @Disabled。需真实 DB(已执行 mall_group_buy.sql)时移除 @Disabled 手动运行,
 * 用于验证原子库存/成团 SQL 行为。
 */
@Disabled("需要真实 MySQL 环境,并已执行 mall_group_buy.sql")
@SpringBootTest
public class GroupBuyPortalDaoTests {

    @Autowired private GroupBuyPortalDao portalDao;
    @Autowired private SmsGroupBuyActivityMapper activityMapper;
    @Autowired private SmsGroupBuyProductMapper productMapper;
    @Autowired private SmsGroupBuyTeamMapper teamMapper;

    private SmsGroupBuyProduct insertProduct(int stock) {
        SmsGroupBuyActivity activity = new SmsGroupBuyActivity();
        activity.setTitle("test");
        activity.setStartTime(new Date());
        activity.setEndTime(new Date(System.currentTimeMillis() + 86400_000L));
        activity.setGroupSize(3);
        activity.setValidHours(24);
        activity.setLimitPerMember(0);
        activity.setVirtualGroupFlag(0);
        activity.setAllowLeaderFree(0);
        activity.setStatus(1);
        activity.setTotalGroupCount(0);
        activity.setSuccessGroupCount(0);
        activity.setCreateTime(new Date());
        activityMapper.insertSelective(activity);

        SmsGroupBuyProduct p = new SmsGroupBuyProduct();
        p.setActivityId(activity.getId());
        p.setProductId(1L);
        p.setProductSkuId(1L);
        p.setOriginalPrice(new BigDecimal("100.00"));
        p.setGroupPrice(new BigDecimal("59.00"));
        p.setGroupStock(stock);
        p.setLockedStock(0);
        p.setSoldCount(0);
        p.setLimitPerOrder(5);
        p.setSort(0);
        productMapper.insertSelective(p);
        return p;
    }

    @Test
    @Transactional
    @Rollback
    public void lockStock_shouldSucceedWhenEnough() {
        SmsGroupBuyProduct p = insertProduct(10);
        int r = portalDao.lockStock(p.getId(), 3);
        assertEquals(1, r);
        SmsGroupBuyProduct after = productMapper.selectByPrimaryKey(p.getId());
        assertEquals(7, after.getGroupStock());
        assertEquals(3, after.getLockedStock());
    }

    @Test
    @Transactional
    @Rollback
    public void lockStock_shouldFailWhenInsufficient() {
        SmsGroupBuyProduct p = insertProduct(2);
        int r = portalDao.lockStock(p.getId(), 3);
        assertEquals(0, r);
        SmsGroupBuyProduct after = productMapper.selectByPrimaryKey(p.getId());
        assertEquals(2, after.getGroupStock());
        assertEquals(0, after.getLockedStock());
    }

    @Test
    @Transactional
    @Rollback
    public void releaseStock_shouldRestoreGroupStock() {
        SmsGroupBuyProduct p = insertProduct(10);
        portalDao.lockStock(p.getId(), 4);
        int r = portalDao.releaseStock(p.getId(), 4);
        assertEquals(1, r);
        SmsGroupBuyProduct after = productMapper.selectByPrimaryKey(p.getId());
        assertEquals(10, after.getGroupStock());
        assertEquals(0, after.getLockedStock());
    }

    @Test
    @Transactional
    @Rollback
    public void finalizeStock_shouldMoveToSoldCount() {
        SmsGroupBuyProduct p = insertProduct(10);
        portalDao.lockStock(p.getId(), 3);
        int r = portalDao.finalizeStock(p.getId(), 3);
        assertEquals(1, r);
        SmsGroupBuyProduct after = productMapper.selectByPrimaryKey(p.getId());
        assertEquals(7, after.getGroupStock());
        assertEquals(0, after.getLockedStock());
        assertEquals(3, after.getSoldCount());
    }

    @Test
    @Transactional
    @Rollback
    public void incrementTeamCurrentNum_shouldBeAtomicAndBounded() {
        SmsGroupBuyTeam team = new SmsGroupBuyTeam();
        team.setTeamNo("TEST" + System.currentTimeMillis());
        team.setActivityId(1L);
        team.setProductId(1L);
        team.setProductSkuId(1L);
        team.setGroupPrice(new BigDecimal("59.00"));
        team.setLeaderMemberId(1L);
        team.setTargetNum(2);
        team.setCurrentNum(0);
        team.setStatus(0);
        team.setStartTime(new Date());
        team.setExpireTime(new Date(System.currentTimeMillis() + 3600_000L));
        team.setVirtualFlag(0);
        teamMapper.insertSelective(team);

        assertEquals(1, portalDao.incrementTeamCurrentNum(team.getId()));
        assertEquals(1, portalDao.incrementTeamCurrentNum(team.getId()));
        // 已满员,不应再递增
        assertEquals(0, portalDao.incrementTeamCurrentNum(team.getId()));

        SmsGroupBuyTeam after = teamMapper.selectByPrimaryKey(team.getId());
        assertEquals(2, after.getCurrentNum());
    }
}
