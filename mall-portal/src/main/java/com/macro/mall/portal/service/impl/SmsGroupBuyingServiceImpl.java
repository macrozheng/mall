package com.macro.mall.portal.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.exception.Asserts;
import com.macro.mall.common.service.RedisService;
import com.macro.mall.mapper.*;
import com.macro.mall.model.*;
import com.macro.mall.portal.dao.PortalOrderDao;
import com.macro.mall.portal.domain.*;
import com.macro.mall.portal.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SmsGroupBuyingServiceImpl implements SmsGroupBuyingService {

    @Autowired
    private SmsGroupActivityMapper groupActivityMapper;

    @Autowired
    private SmsGroupProductRelationMapper productRelationMapper;

    @Autowired
    private SmsGroupTeamMapper groupTeamMapper;

    @Autowired
    private SmsGroupMemberMapper groupMemberMapper;

    @Autowired
    private UmsMemberService memberService;

    @Autowired
    private UmsMemberReceiveAddressService memberReceiveAddressService;

    @Autowired
    private PmsProductMapper productMapper;

    @Autowired
    private OmsOrderMapper orderMapper;

    @Autowired
    private OmsOrderItemMapper orderItemMapper;

    @Autowired
    private PortalOrderDao portalOrderDao;

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.groupTeamId}")
    private String REDIS_KEY_GROUP_TEAM_ID;

    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Override
    public List<SmsGroupActivity> listActivity(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageNum);
        SmsGroupActivityExample example = new SmsGroupActivityExample();
        example.createCriteria()
                .andStatusEqualTo(1)
                .andStartTimeLessThanOrEqualTo(new Date())
                .andEndTimeGreaterThanOrEqualTo(new Date());
        example.setOrderByClause("sort asc, id desc");
        return groupActivityMapper.selectByExample(example);
    }

    @Override
    public GroupActivityDetail getActivityDetail(Long activityId) {
        SmsGroupActivity activity = groupActivityMapper.selectByPrimaryKey(activityId);
        if (activity == null) {
            Asserts.fail("拼团活动不存在");
        }
        if (activity.getStatus() != 1) {
            Asserts.fail("拼团活动未开启");
        }
        Date now = new Date();
        if (now.before(activity.getStartTime()) || now.after(activity.getEndTime())) {
            Asserts.fail("拼团活动未开始或已结束");
        }

        GroupActivityDetail detail = new GroupActivityDetail();
        BeanUtils.copyProperties(activity, detail);
        detail.setActivityId(activity.getId());
        detail.setActivityName(activity.getName());

        SmsGroupProductRelationExample relationExample = new SmsGroupProductRelationExample();
        relationExample.createCriteria().andGroupActivityIdEqualTo(activityId);
        relationExample.setOrderByClause("sort asc");
        List<SmsGroupProductRelation> relationList = productRelationMapper.selectByExample(relationExample);

        List<GroupProductItem> productItemList = new ArrayList<>();
        for (SmsGroupProductRelation relation : relationList) {
            PmsProduct product = productMapper.selectByPrimaryKey(relation.getProductId());
            GroupProductItem item = new GroupProductItem();
            BeanUtils.copyProperties(relation, item);
            if (product != null) {
                item.setProductName(product.getName());
                item.setProductPic(product.getPic());
            }
            productItemList.add(item);
        }
        detail.setProductList(productItemList);
        return detail;
    }

    @Override
    public GroupProductItem getProductDetail(Long groupProductId) {
        SmsGroupProductRelation relation = productRelationMapper.selectByPrimaryKey(groupProductId);
        if (relation == null) {
            Asserts.fail("拼团商品不存在");
        }

        SmsGroupActivity activity = groupActivityMapper.selectByPrimaryKey(relation.getGroupActivityId());
        if (activity == null || activity.getStatus() != 1) {
            Asserts.fail("拼团活动不存在或未开启");
        }

        PmsProduct product = productMapper.selectByPrimaryKey(relation.getProductId());
        GroupProductItem item = new GroupProductItem();
        BeanUtils.copyProperties(relation, item);
        if (product != null) {
            item.setProductName(product.getName());
            item.setProductPic(product.getPic());
        }
        return item;
    }

    @Override
    public List<GroupTeamDetail> listAvailableTeam(Long groupProductId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsGroupTeamExample teamExample = new SmsGroupTeamExample();
        teamExample.createCriteria()
                .andGroupProductIdEqualTo(groupProductId)
                .andStatusEqualTo(0)
                .andExpireTimeGreaterThan(new Date());
        teamExample.setOrderByClause("create_time desc");
        List<SmsGroupTeam> teamList = groupTeamMapper.selectByExample(teamExample);

        if (CollectionUtils.isEmpty(teamList)) {
            return new ArrayList<>();
        }

        SmsGroupProductRelation relation = productRelationMapper.selectByPrimaryKey(groupProductId);
        PmsProduct product = relation != null ? productMapper.selectByPrimaryKey(relation.getProductId()) : null;

        List<GroupTeamDetail> detailList = new ArrayList<>();
        for (SmsGroupTeam team : teamList) {
            GroupTeamDetail detail = new GroupTeamDetail();
            BeanUtils.copyProperties(team, detail);
            detail.setTeamId(team.getId());
            detail.setRemainTime(calculateRemainTime(team.getExpireTime()));
            if (relation != null) {
                detail.setGroupPrice(relation.getGroupPrice());
            }
            if (product != null) {
                detail.setProductName(product.getName());
                detail.setProductPic(product.getPic());
            }
            detail.setMemberList(getTeamMemberList(team.getId()));
            detailList.add(detail);
        }
        return detailList;
    }

    @Override
    public GroupTeamDetail getTeamDetail(Long teamId) {
        SmsGroupTeam team = groupTeamMapper.selectByPrimaryKey(teamId);
        if (team == null) {
            Asserts.fail("拼团队伍不存在");
        }

        SmsGroupProductRelation relation = productRelationMapper.selectByPrimaryKey(team.getGroupProductId());
        PmsProduct product = relation != null ? productMapper.selectByPrimaryKey(relation.getProductId()) : null;

        GroupTeamDetail detail = new GroupTeamDetail();
        BeanUtils.copyProperties(team, detail);
        detail.setTeamId(team.getId());
        detail.setRemainTime(calculateRemainTime(team.getExpireTime()));
        if (relation != null) {
            detail.setGroupPrice(relation.getGroupPrice());
        }
        if (product != null) {
            detail.setProductName(product.getName());
            detail.setProductPic(product.getPic());
        }
        detail.setMemberList(getTeamMemberList(team.getId()));
        return detail;
    }

    @Override
    @Transactional
    public Map<String, Object> createGroupOrder(GroupOrderParam param) {
        UmsMember currentMember = memberService.getCurrentMember();
        SmsGroupProductRelation relation = productRelationMapper.selectByPrimaryKey(param.getGroupProductId());
        if (relation == null) {
            Asserts.fail("拼团商品不存在");
        }

        SmsGroupActivity activity = groupActivityMapper.selectByPrimaryKey(relation.getGroupActivityId());
        if (activity == null || activity.getStatus() != 1) {
            Asserts.fail("拼团活动不存在或未开启");
        }

        Date now = new Date();
        if (now.before(activity.getStartTime()) || now.after(activity.getEndTime())) {
            Asserts.fail("拼团活动未开始或已结束");
        }

        SmsGroupMemberExample memberExample = new SmsGroupMemberExample();
        memberExample.createCriteria()
                .andMemberIdEqualTo(currentMember.getId())
                .andGroupActivityIdEqualTo(activity.getId())
                .andStatusIn(Arrays.asList(0, 1));
        long joinCount = groupMemberMapper.countByExample(memberExample);
        if (joinCount >= activity.getUseLimitCount()) {
            Asserts.fail("您已达到该活动的参加次数限制");
        }

        if (relation.getGroupStock() - relation.getLockStock() - relation.getSoldStock() < param.getQuantity()) {
            Asserts.fail("拼团商品库存不足");
        }

        SmsGroupTeam team;
        boolean isLeader = false;
        if (param.getTeamId() != null) {
            team = groupTeamMapper.selectByPrimaryKey(param.getTeamId());
            if (team == null) {
                Asserts.fail("拼团队伍不存在");
            }
            if (team.getStatus() != 0) {
                Asserts.fail("该拼团已结束或已取消");
            }
            if (now.after(team.getExpireTime())) {
                Asserts.fail("该拼团已过期");
            }
            if (team.getJoinCount() >= team.getGroupCount()) {
                Asserts.fail("该拼团队伍已满");
            }
            if (team.getLeaderId().equals(currentMember.getId())) {
                Asserts.fail("不能加入自己开的团");
            }

            SmsGroupMemberExample existedExample = new SmsGroupMemberExample();
            existedExample.createCriteria()
                    .andGroupTeamIdEqualTo(team.getId())
                    .andMemberIdEqualTo(currentMember.getId());
            if (groupMemberMapper.countByExample(existedExample) > 0) {
                Asserts.fail("您已加入该拼团");
            }
        } else {
            team = createNewTeam(activity, relation, currentMember);
            isLeader = true;
        }

        UmsMemberReceiveAddress address = memberReceiveAddressService.getItem(param.getMemberReceiveAddressId());
        if (address == null) {
            Asserts.fail("请选择收货地址");
        }

        PmsProduct product = productMapper.selectByPrimaryKey(relation.getProductId());

        SmsGroupMember groupMember = new SmsGroupMember();
        groupMember.setGroupTeamId(team.getId());
        groupMember.setGroupActivityId(activity.getId());
        groupMember.setGroupProductId(relation.getId());
        groupMember.setMemberId(currentMember.getId());
        groupMember.setMemberName(currentMember.getNickname());
        groupMember.setMemberIcon(currentMember.getIcon());
        groupMember.setIsLeader(isLeader);
        groupMember.setStatus(0);
        groupMember.setCreateTime(new Date());
        groupMemberMapper.insert(groupMember);

        OmsOrder order = new OmsOrder();
        order.setMemberId(currentMember.getId());
        order.setCreateTime(new Date());
        order.setMemberUsername(currentMember.getUsername());
        order.setPayType(param.getPayType());
        order.setSourceType(1);
        order.setStatus(0);
        order.setOrderType(2);
        order.setPromotionInfo("拼团活动：" + activity.getName());
        order.setReceiverName(address.getName());
        order.setReceiverPhone(address.getPhoneNumber());
        order.setReceiverPostCode(address.getPostCode());
        order.setReceiverProvince(address.getProvince());
        order.setReceiverCity(address.getCity());
        order.setReceiverRegion(address.getRegion());
        order.setReceiverDetailAddress(address.getDetailAddress());
        order.setConfirmStatus(0);
        order.setDeleteStatus(0);
        order.setOrderSn(generateOrderSn());
        order.setAutoConfirmDay(15);

        BigDecimal groupPrice = relation.getGroupPrice();
        BigDecimal totalAmount = groupPrice.multiply(new BigDecimal(param.getQuantity()));
        order.setTotalAmount(totalAmount);
        order.setFreightAmount(new BigDecimal(0));
        order.setPromotionAmount(new BigDecimal(0));
        order.setCouponAmount(new BigDecimal(0));
        order.setIntegrationAmount(new BigDecimal(0));
        order.setPayAmount(totalAmount);
        order.setIntegration(param.getQuantity() * 10);
        order.setGrowth(param.getQuantity() * 10);
        orderMapper.insert(order);

        OmsOrderItem orderItem = new OmsOrderItem();
        orderItem.setOrderId(order.getId());
        orderItem.setOrderSn(order.getOrderSn());
        if (product != null) {
            orderItem.setProductId(product.getId());
            orderItem.setProductName(product.getName());
            orderItem.setProductPic(product.getPic());
            orderItem.setProductBrand(product.getBrandName());
            orderItem.setProductSn(product.getProductSn());
            orderItem.setProductCategoryId(product.getProductCategoryId());
        }
        orderItem.setProductPrice(groupPrice);
        orderItem.setProductQuantity(param.getQuantity());
        orderItem.setPromotionName("拼团活动");
        orderItem.setPromotionAmount(new BigDecimal(0));
        orderItem.setCouponAmount(new BigDecimal(0));
        orderItem.setIntegrationAmount(new BigDecimal(0));
        orderItem.setRealAmount(groupPrice.multiply(new BigDecimal(param.getQuantity())));
        orderItem.setGiftIntegration(param.getQuantity() * 10);
        orderItem.setGiftGrowth(param.getQuantity() * 10);
        orderItemMapper.insert(orderItem);

        groupMember.setOrderId(order.getId());
        groupMember.setOrderSn(order.getOrderSn());
        groupMemberMapper.updateByPrimaryKeySelective(groupMember);

        if (!isLeader) {
            SmsGroupTeam updateTeam = new SmsGroupTeam();
            updateTeam.setId(team.getId());
            updateTeam.setJoinCount(team.getJoinCount() + 1);
            groupTeamMapper.updateByPrimaryKeySelective(updateTeam);
        }

        SmsGroupProductRelation updateRelation = new SmsGroupProductRelation();
        updateRelation.setId(relation.getId());
        updateRelation.setLockStock(relation.getLockStock() + param.getQuantity());
        productRelationMapper.updateByPrimaryKeySelective(updateRelation);

        Map<String, Object> result = new HashMap<>();
        result.put("order", order);
        result.put("orderItem", orderItem);
        result.put("teamId", team.getId());
        result.put("isLeader", isLeader);
        return result;
    }

    @Override
    @Transactional
    public void paySuccess(Long orderId) {
        OmsOrder order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            Asserts.fail("订单不存在");
        }
        if (order.getStatus() != 0) {
            Asserts.fail("订单状态不正确");
        }

        order.setStatus(1);
        order.setPaymentTime(new Date());
        orderMapper.updateByPrimaryKeySelective(order);

        SmsGroupMemberExample memberExample = new SmsGroupMemberExample();
        memberExample.createCriteria().andOrderIdEqualTo(orderId);
        List<SmsGroupMember> memberList = groupMemberMapper.selectByExample(memberExample);
        if (CollectionUtils.isEmpty(memberList)) {
            return;
        }

        SmsGroupMember groupMember = memberList.get(0);
        groupMember.setStatus(1);
        groupMember.setPayTime(new Date());
        groupMemberMapper.updateByPrimaryKeySelective(groupMember);

        SmsGroupTeam team = groupTeamMapper.selectByPrimaryKey(groupMember.getGroupTeamId());
        if (team == null) {
            return;
        }

        SmsGroupMemberExample paidMemberExample = new SmsGroupMemberExample();
        paidMemberExample.createCriteria()
                .andGroupTeamIdEqualTo(team.getId())
                .andStatusEqualTo(1);
        long paidCount = groupMemberMapper.countByExample(paidMemberExample);

        if (paidCount >= team.getGroupCount()) {
            SmsGroupTeam updateTeam = new SmsGroupTeam();
            updateTeam.setId(team.getId());
            updateTeam.setStatus(1);
            updateTeam.setCompleteTime(new Date());
            groupTeamMapper.updateByPrimaryKeySelective(updateTeam);

            SmsGroupProductRelation relation = productRelationMapper.selectByPrimaryKey(team.getGroupProductId());
            if (relation != null) {
                OmsOrderItemExample orderItemExample = new OmsOrderItemExample();
                orderItemExample.createCriteria().andOrderIdEqualTo(orderId);
                List<OmsOrderItem> orderItemList = orderItemMapper.selectByExample(orderItemExample);
                int quantity = orderItemList.stream().mapToInt(OmsOrderItem::getProductQuantity).sum();

                SmsGroupProductRelation updateRelation = new SmsGroupProductRelation();
                updateRelation.setId(relation.getId());
                updateRelation.setLockStock(relation.getLockStock() - quantity);
                updateRelation.setSoldStock(relation.getSoldStock() + quantity);
                productRelationMapper.updateByPrimaryKeySelective(updateRelation);
            }
        }
    }

    @Override
    public CommonPage<GroupTeamDetail> listMyGroup(Integer status, Integer pageNum, Integer pageSize) {
        UmsMember currentMember = memberService.getCurrentMember();
        PageHelper.startPage(pageNum, pageSize);

        SmsGroupMemberExample memberExample = new SmsGroupMemberExample();
        SmsGroupMemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andMemberIdEqualTo(currentMember.getId());
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        memberExample.setOrderByClause("create_time desc");
        List<SmsGroupMember> memberList = groupMemberMapper.selectByExample(memberExample);

        if (CollectionUtils.isEmpty(memberList)) {
            return CommonPage.restPage(new ArrayList<>());
        }

        List<Long> teamIds = memberList.stream()
                .map(SmsGroupMember::getGroupTeamId)
                .collect(Collectors.toList());
        SmsGroupTeamExample teamExample = new SmsGroupTeamExample();
        teamExample.createCriteria().andIdIn(teamIds);
        List<SmsGroupTeam> teamList = groupTeamMapper.selectByExample(teamExample);
        Map<Long, SmsGroupTeam> teamMap = teamList.stream()
                .collect(Collectors.toMap(SmsGroupTeam::getId, t -> t));

        List<GroupTeamDetail> detailList = new ArrayList<>();
        for (SmsGroupMember member : memberList) {
            SmsGroupTeam team = teamMap.get(member.getGroupTeamId());
            if (team != null) {
                GroupTeamDetail detail = new GroupTeamDetail();
                BeanUtils.copyProperties(team, detail);
                detail.setTeamId(team.getId());
                detail.setRemainTime(calculateRemainTime(team.getExpireTime()));
                detailList.add(detail);
            }
        }

        return CommonPage.restPage(detailList);
    }

    @Override
    @Transactional
    public void cancelTimeOutTeam() {
        Date now = new Date();
        SmsGroupTeamExample teamExample = new SmsGroupTeamExample();
        teamExample.createCriteria()
                .andStatusEqualTo(0)
                .andExpireTimeLessThan(now);
        List<SmsGroupTeam> timeoutTeamList = groupTeamMapper.selectByExample(teamExample);

        if (CollectionUtils.isEmpty(timeoutTeamList)) {
            return;
        }

        for (SmsGroupTeam team : timeoutTeamList) {
            SmsGroupTeam updateTeam = new SmsGroupTeam();
            updateTeam.setId(team.getId());
            updateTeam.setStatus(2);
            updateTeam.setCancelTime(now);
            groupTeamMapper.updateByPrimaryKeySelective(updateTeam);

            SmsGroupMemberExample memberExample = new SmsGroupMemberExample();
            memberExample.createCriteria().andGroupTeamIdEqualTo(team.getId());
            List<SmsGroupMember> memberList = groupMemberMapper.selectByExample(memberExample);

            for (SmsGroupMember member : memberList) {
                if (member.getStatus() == 1) {
                    SmsGroupMember updateMember = new SmsGroupMember();
                    updateMember.setId(member.getId());
                    updateMember.setStatus(2);
                    groupMemberMapper.updateByPrimaryKeySelective(updateMember);
                } else if (member.getStatus() == 0) {
                    SmsGroupMember updateMember = new SmsGroupMember();
                    updateMember.setId(member.getId());
                    updateMember.setStatus(3);
                    groupMemberMapper.updateByPrimaryKeySelective(updateMember);
                }
            }
        }
    }

    private SmsGroupTeam createNewTeam(SmsGroupActivity activity, SmsGroupProductRelation relation, UmsMember member) {
        SmsGroupTeam team = new SmsGroupTeam();
        team.setGroupActivityId(activity.getId());
        team.setGroupProductId(relation.getId());
        team.setTeamNumber(generateTeamNumber());
        team.setLeaderId(member.getId());
        team.setLeaderName(member.getNickname());
        team.setLeaderIcon(member.getIcon());
        team.setGroupCount(activity.getGroupCount());
        team.setJoinCount(1);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, activity.getGroupValidTime());
        team.setExpireTime(calendar.getTime());
        team.setStatus(0);
        team.setCreateTime(new Date());
        groupTeamMapper.insert(team);
        return team;
    }

    private Long calculateRemainTime(Date expireTime) {
        if (expireTime == null) {
            return 0L;
        }
        long remain = expireTime.getTime() - System.currentTimeMillis();
        return Math.max(0, remain / 1000);
    }

    private List<GroupMemberItem> getTeamMemberList(Long teamId) {
        SmsGroupMemberExample example = new SmsGroupMemberExample();
        example.createCriteria().andGroupTeamIdEqualTo(teamId);
        example.setOrderByClause("is_leader desc, create_time asc");
        List<SmsGroupMember> memberList = groupMemberMapper.selectByExample(example);

        List<GroupMemberItem> itemList = new ArrayList<>();
        for (SmsGroupMember member : memberList) {
            GroupMemberItem item = new GroupMemberItem();
            BeanUtils.copyProperties(member, item);
            itemList.add(item);
        }
        return itemList;
    }

    private String generateOrderSn() {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String key = REDIS_DATABASE + ":" + REDIS_KEY_GROUP_TEAM_ID + date;
        Long increment = redisService.incr(key, 1);
        sb.append(date);
        sb.append(String.format("%02d", 2));
        sb.append(String.format("%02d", 0));
        sb.append(String.format("%06d", increment));
        return sb.toString();
    }

    private String generateTeamNumber() {
        return "GROUP" + System.currentTimeMillis() + RandomUtil.randomNumbers(4);
    }
}
