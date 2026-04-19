package com.macro.mall.portal.service.impl;

import com.macro.mall.common.exception.Asserts;
import com.macro.mall.common.service.RedisService;
import com.macro.mall.mapper.OmsOrderItemMapper;
import com.macro.mall.mapper.OmsOrderMapper;
import com.macro.mall.mapper.SmsGroupBuyActivityMapper;
import com.macro.mall.mapper.SmsGroupBuyRecordMapper;
import com.macro.mall.mapper.SmsGroupBuyTeamMapper;
import com.macro.mall.model.OmsOrder;
import com.macro.mall.model.OmsOrderItem;
import com.macro.mall.model.SmsGroupBuyActivity;
import com.macro.mall.model.SmsGroupBuyProduct;
import com.macro.mall.model.SmsGroupBuyRecord;
import com.macro.mall.model.SmsGroupBuyTeam;
import com.macro.mall.model.UmsMember;
import com.macro.mall.model.UmsMemberReceiveAddress;
import com.macro.mall.portal.component.GroupBuyTimeOutSender;
import com.macro.mall.portal.dao.GroupBuyPortalDao;
import com.macro.mall.portal.domain.GroupBuyJoinParam;
import com.macro.mall.portal.domain.GroupBuyOpenParam;
import com.macro.mall.portal.domain.GroupBuyOrderResult;
import com.macro.mall.portal.domain.GroupBuyTeamDetail;
import com.macro.mall.portal.service.GroupBuyLogService;
import com.macro.mall.portal.service.GroupBuyOrderService;
import com.macro.mall.portal.service.UmsMemberReceiveAddressService;
import com.macro.mall.portal.service.UmsMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 拼团下单Service实现
 */
@Service
public class GroupBuyOrderServiceImpl implements GroupBuyOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupBuyOrderServiceImpl.class);

    /** 订单类型:2=拼团订单(扩展自 OmsOrder.orderType) */
    private static final Integer ORDER_TYPE_GROUP_BUY = 2;

    @Autowired
    private UmsMemberService memberService;

    @Autowired
    private UmsMemberReceiveAddressService addressService;

    @Autowired
    private SmsGroupBuyActivityMapper activityMapper;

    @Autowired
    private SmsGroupBuyTeamMapper teamMapper;

    @Autowired
    private SmsGroupBuyRecordMapper recordMapper;

    @Autowired
    private OmsOrderMapper orderMapper;

    @Autowired
    private OmsOrderItemMapper orderItemMapper;

    @Autowired
    private GroupBuyPortalDao portalDao;

    @Autowired
    private GroupBuyLogService groupBuyLogService;

    @Autowired
    private GroupBuyTimeOutSender timeOutSender;

    @Autowired
    private RedisService redisService;

    @Override
    @Transactional
    public GroupBuyOrderResult launchGroup(GroupBuyOpenParam param) {
        validateOpenParam(param);
        UmsMember member = memberService.getCurrentMember();

        SmsGroupBuyActivity activity = activityMapper.selectByPrimaryKey(param.getActivityId());
        validateActivity(activity);

        SmsGroupBuyProduct product = portalDao.getProductBySku(activity.getId(), param.getProductSkuId());
        if (product == null) {
            Asserts.fail("该商品不在活动范围");
        }
        validateQuantityLimit(product, param.getQuantity());
        validateMemberLimit(activity, member);

        // 原子锁定库存
        int ok = portalDao.lockStock(product.getId(), param.getQuantity());
        if (ok <= 0) {
            Asserts.fail("活动库存不足");
        }

        // 创建团
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + activity.getValidHours() * 3600L * 1000L);
        // 若活动结束时间早于 valid 窗口,则取较早者为准
        if (activity.getEndTime() != null && activity.getEndTime().before(expireTime)) {
            expireTime = activity.getEndTime();
        }

        SmsGroupBuyTeam team = new SmsGroupBuyTeam();
        team.setTeamNo(generateTeamNo());
        team.setActivityId(activity.getId());
        team.setProductId(product.getProductId());
        team.setProductSkuId(product.getProductSkuId());
        team.setGroupPrice(product.getGroupPrice());
        team.setLeaderMemberId(member.getId());
        team.setLeaderNickname(member.getNickname());
        team.setTargetNum(activity.getGroupSize());
        team.setCurrentNum(0);
        team.setStatus(0);
        team.setStartTime(now);
        team.setExpireTime(expireTime);
        team.setVirtualFlag(0);
        teamMapper.insertSelective(team);

        // 下单
        OmsOrder order = buildOrder(member, activity, product, param.getQuantity(),
                param.getMemberReceiveAddressId(), team);
        orderMapper.insertSelective(order);
        insertOrderItem(order, product, param.getQuantity());

        // 创建参团记录(团长)
        SmsGroupBuyRecord record = buildRecord(team, member, order, product,
                param.getQuantity(), true);
        recordMapper.insertSelective(record);

        // 累加活动开团计数
        portalDao.incrementTotalGroupCount(activity.getId());

        // 发送超时延时消息
        long delayMillis = expireTime.getTime() - now.getTime();
        if (delayMillis > 0) {
            timeOutSender.sendTimeoutMessage(team.getId(), delayMillis);
        }

        // 日志
        groupBuyLogService.record(GroupBuyLogService.OP_OPEN_GROUP, GroupBuyLogService.SRC_MEMBER,
                team, record, null, 0, "开团下单");

        return buildResult(team, order, record);
    }

    @Override
    @Transactional
    public GroupBuyOrderResult joinGroup(GroupBuyJoinParam param) {
        if (param == null || param.getTeamNo() == null
                || param.getQuantity() == null || param.getQuantity() <= 0
                || param.getMemberReceiveAddressId() == null) {
            Asserts.fail("参数不完整");
        }
        UmsMember member = memberService.getCurrentMember();

        SmsGroupBuyTeam team = portalDao.getTeamByNo(param.getTeamNo());
        if (team == null) {
            Asserts.fail("团不存在");
        }
        if (!Integer.valueOf(0).equals(team.getStatus())) {
            Asserts.fail("该团已结束");
        }
        Date now = new Date();
        if (team.getExpireTime() != null && team.getExpireTime().before(now)) {
            Asserts.fail("该团已超时");
        }
        if (team.getCurrentNum() != null && team.getTargetNum() != null
                && team.getCurrentNum() >= team.getTargetNum()) {
            Asserts.fail("该团已满员");
        }

        SmsGroupBuyActivity activity = activityMapper.selectByPrimaryKey(team.getActivityId());
        validateActivity(activity);
        validateMemberLimit(activity, member);

        SmsGroupBuyProduct product = portalDao.getProductBySku(team.getActivityId(), team.getProductSkuId());
        if (product == null) {
            Asserts.fail("团商品已下架");
        }
        validateQuantityLimit(product, param.getQuantity());

        // 锁库存
        int ok = portalDao.lockStock(product.getId(), param.getQuantity());
        if (ok <= 0) {
            Asserts.fail("活动库存不足");
        }

        OmsOrder order = buildOrder(member, activity, product, param.getQuantity(),
                param.getMemberReceiveAddressId(), team);
        orderMapper.insertSelective(order);
        insertOrderItem(order, product, param.getQuantity());

        SmsGroupBuyRecord record = buildRecord(team, member, order, product,
                param.getQuantity(), false);
        try {
            recordMapper.insertSelective(record);
        } catch (org.springframework.dao.DuplicateKeyException e) {
            // uk_team_member 命中 → 用户已在该团内
            Asserts.fail("您已参与该团,请勿重复参团");
        }

        groupBuyLogService.record(GroupBuyLogService.OP_JOIN_GROUP, GroupBuyLogService.SRC_MEMBER,
                team, record, null, 0, "参团下单");

        return buildResult(team, order, record);
    }

    @Override
    @Transactional
    public void handlePaySuccess(String orderSn) {
        SmsGroupBuyRecord record = portalDao.getRecordByOrderSn(orderSn);
        if (record == null) {
            // 非拼团订单 → 忽略
            return;
        }
        if (!Integer.valueOf(0).equals(record.getJoinStatus())) {
            // 已处理过 → 幂等
            return;
        }

        Date now = new Date();
        SmsGroupBuyRecord update = new SmsGroupBuyRecord();
        update.setId(record.getId());
        update.setJoinStatus(1);
        update.setPayTime(now);
        recordMapper.updateByPrimaryKeySelective(update);

        int inc = portalDao.incrementTeamCurrentNum(record.getTeamId());
        if (inc <= 0) {
            // 团已满员/已结束仍收到支付 → 标记为特殊处理,后续由补偿任务走退款
            LOGGER.warn("groupBuy pay success but team cannot accept: orderSn={}, teamId={}",
                    orderSn, record.getTeamId());
            groupBuyLogService.record(GroupBuyLogService.OP_PAY_SUCCESS, GroupBuyLogService.SRC_PAY_CALLBACK,
                    null, record, 0, 1, "支付成功但团已结束,待人工退款");
            return;
        }

        SmsGroupBuyTeam team = teamMapper.selectByPrimaryKey(record.getTeamId());
        groupBuyLogService.record(GroupBuyLogService.OP_PAY_SUCCESS, GroupBuyLogService.SRC_PAY_CALLBACK,
                team, record, 0, 1, "支付成功");

        if (team != null && team.getCurrentNum() != null && team.getTargetNum() != null
                && team.getCurrentNum().equals(team.getTargetNum())) {
            finalizeTeamSuccess(team);
        }
    }

    @Override
    @Transactional
    public void handleTeamTimeout(Long teamId) {
        SmsGroupBuyTeam team = teamMapper.selectByPrimaryKey(teamId);
        if (team == null) {
            return;
        }
        if (!Integer.valueOf(0).equals(team.getStatus())) {
            // 非进行中 → 无需处理
            return;
        }
        if (team.getCurrentNum() != null && team.getTargetNum() != null
                && team.getCurrentNum() >= team.getTargetNum()) {
            // 边界:刚好凑齐但未来得及置成团,走成团
            finalizeTeamSuccess(team);
            return;
        }

        Date now = new Date();
        SmsGroupBuyTeam update = new SmsGroupBuyTeam();
        update.setId(teamId);
        update.setStatus(2);
        update.setCloseTime(now);
        teamMapper.updateByPrimaryKeySelective(update);

        // 批量更新团内参团记录
        portalDao.failRecords(teamId);

        // 释放库存 - 按团内所有有效记录的数量汇总释放
        List<SmsGroupBuyRecord> records = portalDao.listRecordsByTeam(teamId);
        int totalQty = 0;
        for (SmsGroupBuyRecord r : records) {
            if (r.getQuantity() != null && !Integer.valueOf(4).equals(r.getJoinStatus())) {
                totalQty += r.getQuantity();
            }
        }
        if (totalQty > 0) {
            SmsGroupBuyProduct product = portalDao.getProductBySku(team.getActivityId(), team.getProductSkuId());
            if (product != null) {
                portalDao.releaseStock(product.getId(), totalQty);
            }
        }

        groupBuyLogService.record(GroupBuyLogService.OP_GROUP_FAIL, GroupBuyLogService.SRC_SYSTEM,
                team, null, 0, 2, "成团超时失败,库存已释放");
    }

    @Override
    @Transactional
    public int cancelRecord(Long recordId, Long memberId) {
        SmsGroupBuyRecord record = recordMapper.selectByPrimaryKey(recordId);
        if (record == null) {
            Asserts.fail("记录不存在");
        }
        if (!record.getMemberId().equals(memberId)) {
            Asserts.fail("无权取消他人的参团记录");
        }
        if (!Integer.valueOf(0).equals(record.getJoinStatus())) {
            Asserts.fail("仅未支付参团可取消");
        }

        SmsGroupBuyRecord update = new SmsGroupBuyRecord();
        update.setId(recordId);
        update.setJoinStatus(4);
        update.setFinishTime(new Date());
        int count = recordMapper.updateByPrimaryKeySelective(update);

        // 释放库存
        SmsGroupBuyTeam team = teamMapper.selectByPrimaryKey(record.getTeamId());
        if (team != null) {
            SmsGroupBuyProduct product = portalDao.getProductBySku(team.getActivityId(), team.getProductSkuId());
            if (product != null && record.getQuantity() != null) {
                portalDao.releaseStock(product.getId(), record.getQuantity());
            }
            // 若团长取消且团仅 1 条记录 → 关闭团
            if (Integer.valueOf(1).equals(record.getIsLeader())) {
                Integer currentNum = team.getCurrentNum() == null ? 0 : team.getCurrentNum();
                if (currentNum == 0) {
                    SmsGroupBuyTeam closeTeam = new SmsGroupBuyTeam();
                    closeTeam.setId(team.getId());
                    closeTeam.setStatus(3);
                    closeTeam.setCloseTime(new Date());
                    teamMapper.updateByPrimaryKeySelective(closeTeam);
                }
            }
        }

        groupBuyLogService.record(GroupBuyLogService.OP_CANCEL, GroupBuyLogService.SRC_MEMBER,
                team, record, 0, 4, "用户取消参团");
        return count;
    }

    @Override
    public GroupBuyTeamDetail getTeamDetail(String teamNo) {
        SmsGroupBuyTeam team = portalDao.getTeamByNo(teamNo);
        if (team == null) {
            Asserts.fail("团不存在");
        }
        GroupBuyTeamDetail detail = new GroupBuyTeamDetail();
        detail.setTeam(team);
        detail.setMembers(portalDao.listRecordsByTeam(team.getId()));
        detail.setLackNum(Math.max(0, team.getTargetNum() - (team.getCurrentNum() == null ? 0 : team.getCurrentNum())));
        long remain = team.getExpireTime() == null ? 0L
                : Math.max(0L, team.getExpireTime().getTime() - System.currentTimeMillis());
        detail.setRemainMillis(remain);
        return detail;
    }

    @Override
    public List<SmsGroupBuyRecord> listMyRecords(Long memberId) {
        return portalDao.listMemberRecords(memberId);
    }

    // ----------------- 私有工具方法 -----------------

    private void validateOpenParam(GroupBuyOpenParam param) {
        if (param == null || param.getActivityId() == null || param.getProductSkuId() == null
                || param.getQuantity() == null || param.getQuantity() <= 0
                || param.getMemberReceiveAddressId() == null) {
            Asserts.fail("参数不完整");
        }
    }

    private void validateActivity(SmsGroupBuyActivity activity) {
        if (activity == null) {
            Asserts.fail("活动不存在");
        }
        if (!Integer.valueOf(1).equals(activity.getStatus())) {
            Asserts.fail("活动未上线");
        }
        Date now = new Date();
        if (activity.getStartTime() != null && activity.getStartTime().after(now)) {
            Asserts.fail("活动未开始");
        }
        if (activity.getEndTime() != null && activity.getEndTime().before(now)) {
            Asserts.fail("活动已结束");
        }
    }

    private void validateQuantityLimit(SmsGroupBuyProduct product, Integer quantity) {
        if (product.getLimitPerOrder() != null && product.getLimitPerOrder() > 0
                && quantity > product.getLimitPerOrder()) {
            Asserts.fail("超出单次下单限购数量");
        }
    }

    private void validateMemberLimit(SmsGroupBuyActivity activity, UmsMember member) {
        if (activity.getLimitPerMember() != null && activity.getLimitPerMember() > 0) {
            int existed = portalDao.countMemberJoin(activity.getId(), member.getId());
            if (existed >= activity.getLimitPerMember()) {
                Asserts.fail("已达本活动参团次数上限");
            }
        }
    }

    private OmsOrder buildOrder(UmsMember member, SmsGroupBuyActivity activity,
                                SmsGroupBuyProduct product, Integer quantity,
                                Long addressId, SmsGroupBuyTeam team) {
        UmsMemberReceiveAddress address = addressService.getItem(addressId);
        if (address == null) {
            Asserts.fail("收货地址无效");
        }
        BigDecimal payAmount = product.getGroupPrice().multiply(BigDecimal.valueOf(quantity));

        OmsOrder order = new OmsOrder();
        order.setMemberId(member.getId());
        order.setMemberUsername(member.getUsername());
        order.setCouponId(null);
        order.setOrderSn(generateOrderSn());
        order.setCreateTime(new Date());
        order.setTotalAmount(payAmount);
        order.setPayAmount(payAmount);
        order.setFreightAmount(BigDecimal.ZERO);
        order.setPromotionAmount(BigDecimal.ZERO);
        order.setIntegrationAmount(BigDecimal.ZERO);
        order.setCouponAmount(BigDecimal.ZERO);
        order.setDiscountAmount(BigDecimal.ZERO);
        order.setPayType(0);
        order.setSourceType(1);
        order.setStatus(0);
        order.setOrderType(ORDER_TYPE_GROUP_BUY);
        order.setGroupActivityId(activity.getId());
        order.setGroupTeamId(team.getId());
        order.setIntegration(0);
        order.setGrowth(0);
        order.setPromotionInfo("拼团活动:" + activity.getTitle());
        order.setReceiverName(address.getName());
        order.setReceiverPhone(address.getPhoneNumber());
        order.setReceiverPostCode(address.getPostCode());
        order.setReceiverProvince(address.getProvince());
        order.setReceiverCity(address.getCity());
        order.setReceiverRegion(address.getRegion());
        order.setReceiverDetailAddress(address.getDetailAddress());
        order.setConfirmStatus(0);
        order.setDeleteStatus(0);
        order.setUseIntegration(0);
        order.setAutoConfirmDay(7);
        return order;
    }

    private void insertOrderItem(OmsOrder order, SmsGroupBuyProduct product, Integer quantity) {
        OmsOrderItem item = new OmsOrderItem();
        item.setOrderId(order.getId());
        item.setOrderSn(order.getOrderSn());
        item.setProductId(product.getProductId());
        item.setProductPic(product.getProductPic());
        item.setProductName(product.getProductName());
        item.setProductPrice(product.getGroupPrice());
        item.setProductQuantity(quantity);
        item.setProductSkuId(product.getProductSkuId());
        item.setProductSkuCode(product.getSkuCode());
        item.setPromotionAmount(BigDecimal.ZERO);
        item.setCouponAmount(BigDecimal.ZERO);
        item.setIntegrationAmount(BigDecimal.ZERO);
        item.setRealAmount(product.getGroupPrice().multiply(BigDecimal.valueOf(quantity)));
        item.setGiftIntegration(0);
        item.setGiftGrowth(0);
        item.setPromotionName("拼团价");
        orderItemMapper.insertSelective(item);
    }

    private SmsGroupBuyRecord buildRecord(SmsGroupBuyTeam team, UmsMember member, OmsOrder order,
                                          SmsGroupBuyProduct product, Integer quantity, boolean isLeader) {
        SmsGroupBuyRecord record = new SmsGroupBuyRecord();
        record.setTeamId(team.getId());
        record.setActivityId(team.getActivityId());
        record.setMemberId(member.getId());
        record.setMemberNickname(member.getNickname());
        record.setMemberIcon(member.getIcon());
        record.setIsLeader(isLeader ? 1 : 0);
        record.setOrderId(order.getId());
        record.setOrderSn(order.getOrderSn());
        record.setPayAmount(order.getPayAmount());
        record.setQuantity(quantity);
        record.setJoinStatus(0);
        record.setJoinTime(new Date());
        return record;
    }

    private GroupBuyOrderResult buildResult(SmsGroupBuyTeam team, OmsOrder order, SmsGroupBuyRecord record) {
        GroupBuyOrderResult result = new GroupBuyOrderResult();
        result.setTeamId(team.getId());
        result.setTeamNo(team.getTeamNo());
        result.setOrderId(order.getId());
        result.setOrderSn(order.getOrderSn());
        result.setRecordId(record.getId());
        result.setPayAmount(order.getPayAmount());
        return result;
    }

    /**
     * 团成团结算: 置状态+关联记录+库存结算+活动统计
     */
    private void finalizeTeamSuccess(SmsGroupBuyTeam team) {
        Date now = new Date();
        SmsGroupBuyTeam update = new SmsGroupBuyTeam();
        update.setId(team.getId());
        update.setStatus(1);
        update.setSuccessTime(now);
        teamMapper.updateByPrimaryKeySelective(update);

        portalDao.finalizeRecords(team.getId());

        // 库存结算
        List<SmsGroupBuyRecord> records = portalDao.listRecordsByTeam(team.getId());
        int totalQty = 0;
        for (SmsGroupBuyRecord r : records) {
            if (r.getQuantity() != null && Integer.valueOf(2).equals(r.getJoinStatus())) {
                totalQty += r.getQuantity();
            }
        }
        if (totalQty > 0) {
            SmsGroupBuyProduct product = portalDao.getProductBySku(team.getActivityId(), team.getProductSkuId());
            if (product != null) {
                portalDao.finalizeStock(product.getId(), totalQty);
            }
        }

        portalDao.incrementSuccessGroupCount(team.getActivityId());

        groupBuyLogService.record(GroupBuyLogService.OP_GROUP_SUCCESS, GroupBuyLogService.SRC_SYSTEM,
                team, null, 0, 1, "成团成功");
    }

    private String generateOrderSn() {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String key = "mall:groupBuy:orderSn:" + date;
        Long increment = redisService.incr(key, 1);
        StringBuilder sb = new StringBuilder();
        sb.append(date).append("02").append("00"); // sourceType=02 (app) payType=00 (未支付)
        if (increment < 1_000_000) {
            sb.append(String.format("%06d", increment));
        } else {
            sb.append(increment);
        }
        return sb.toString();
    }

    private String generateTeamNo() {
        // 16位简短团号:日期 + 6位随机
        return new SimpleDateFormat("yyMMddHHmm").format(new Date())
                + String.format("%06d", (int) (Math.random() * 1_000_000));
    }
}
