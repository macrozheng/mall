package com.macro.mall.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.common.api.CommonPage;
import com.macro.mall.mapper.OmsOrderItemMapper;
import com.macro.mall.mapper.OmsOrderMapper;
import com.macro.mall.mapper.PmsProductMapper;
import com.macro.mall.mapper.UmsMemberStatisticsInfoMapper;
import com.macro.mall.model.*;
import com.macro.mall.portal.domain.MemberReadHistory;
import com.macro.mall.portal.repository.MemberReadHistoryRepository;
import com.macro.mall.portal.service.PersonalizedRecommendationService;
import com.macro.mall.portal.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 个性化商品推荐服务实现类
 * Created by macro on 2024/5/20.
 */
@Service
public class PersonalizedRecommendationServiceImpl implements PersonalizedRecommendationService {

    @Autowired
    private MemberReadHistoryRepository memberReadHistoryRepository;
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private OmsOrderItemMapper orderItemMapper;
    @Autowired
    private OmsOrderMapper orderMapper;
    @Autowired
    private UmsMemberStatisticsInfoMapper memberStatisticsInfoMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 缓存键前缀
    private static final String USER_PREFERENCE_CACHE_KEY = "personalized:preference:";
    private static final String HOT_PRODUCTS_CACHE_KEY = "personalized:hot_products";
    private static final long USER_PREFERENCE_EXPIRE_TIME = 3600L; // 1小时
    private static final long HOT_PRODUCTS_EXPIRE_TIME = 600L; // 10分钟

    @Override
    public CommonPage<PmsProduct> getPersonalizedRecommendations(Integer pageNum, Integer pageSize) {
        UmsMember currentMember = memberService.getCurrentMember();
        if (currentMember == null) {
            // 未登录用户返回全局热门商品
            return getHotProducts(pageNum, pageSize);
        }

        // 1. 获取用户画像（价格偏好等）
        UserPreference userPreference = getUserPreference(currentMember.getId());

        // 2. 获取候选商品池（月销量前20%）
        List<PmsProduct> candidateProducts = getCandidateProducts();
        if (CollectionUtils.isEmpty(candidateProducts)) {
            return CommonPage.restPage(new ArrayList<>());
        }

        // 3. 过滤已购买商品
        Set<Long> purchasedProductIds = getPurchasedProductIds(currentMember.getId());
        List<PmsProduct> filteredProducts = candidateProducts.stream()
                .filter(product -> !purchasedProductIds.contains(product.getId()))
                .collect(Collectors.toList());

        // 4. 计算推荐得分
        List<RecommendedProduct> scoredProducts = calculateRecommendationScores(filteredProducts, userPreference, currentMember.getId());

        // 5. 排序并分页
        scoredProducts.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));
        int start = (pageNum - 1) * pageSize;
        int end = Math.min(start + pageSize, scoredProducts.size());
        List<Long> productIds = scoredProducts.subList(start, end)
                .stream()
                .map(RecommendedProduct::getProductId)
                .collect(Collectors.toList());

        // 6. 获取商品详情并保持排序
        List<PmsProduct> recommendedProducts = getProductsByIds(productIds);

        return CommonPage.restPage(recommendedProducts);
    }

    /**
     * 获取用户偏好
     */
    private UserPreference getUserPreference(Long memberId) {
        // 先从缓存获取
        String cacheKey = USER_PREFERENCE_CACHE_KEY + memberId;
        UserPreference userPreference = (UserPreference) redisTemplate.opsForValue().get(cacheKey);
        if (userPreference != null) {
            return userPreference;
        }

        // 计算用户价格偏好
        BigDecimal avgOrderPrice = calculateAvgOrderPrice(memberId);
        BigDecimal priceLowerBound = avgOrderPrice.multiply(new BigDecimal(0.7)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal priceUpperBound = avgOrderPrice.multiply(new BigDecimal(1.3)).setScale(2, RoundingMode.HALF_UP);

        // 获取浏览历史中的价格分布
        Pageable pageable = PageRequest.of(0, 50); // 获取最近50条浏览记录
        Page<MemberReadHistory> readHistoriesPage = memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(memberId, pageable);
        List<MemberReadHistory> readHistories = readHistoriesPage.getContent();
        Set<Long> browsedProductIds = readHistories.stream()
                .map(MemberReadHistory::getProductId)
                .collect(Collectors.toSet());

        userPreference = new UserPreference();
        userPreference.setMemberId(memberId);
        userPreference.setAvgOrderPrice(avgOrderPrice);
        userPreference.setPriceLowerBound(priceLowerBound);
        userPreference.setPriceUpperBound(priceUpperBound);
        userPreference.setBrowsedProductIds(browsedProductIds);

        // 缓存用户偏好
        redisTemplate.opsForValue().set(cacheKey, userPreference, USER_PREFERENCE_EXPIRE_TIME);

        return userPreference;
    }

    /**
     * 计算用户平均订单价格
     */
    private BigDecimal calculateAvgOrderPrice(Long memberId) {
        UmsMemberStatisticsInfoExample example = new UmsMemberStatisticsInfoExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        List<UmsMemberStatisticsInfo> statisticsInfoList = memberStatisticsInfoMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(statisticsInfoList)) {
            UmsMemberStatisticsInfo statisticsInfo = statisticsInfoList.get(0);
            if (statisticsInfo.getOrderCount() != null && statisticsInfo.getOrderCount() > 0 && statisticsInfo.getConsumeAmount() != null) {
                return statisticsInfo.getConsumeAmount().divide(new BigDecimal(statisticsInfo.getOrderCount()), 2, RoundingMode.HALF_UP);
            }
        }

        // 如果没有订单记录，使用默认值或浏览历史计算
        return new BigDecimal(100.00); // 默认平均价格
    }

    /**
     * 获取候选商品池（月销量前20%）
     */
    private List<PmsProduct> getCandidateProducts() {
        // 先从缓存获取
        List<PmsProduct> hotProducts = (List<PmsProduct>) redisTemplate.opsForValue().get(HOT_PRODUCTS_CACHE_KEY);
        if (hotProducts != null && !hotProducts.isEmpty()) {
            return hotProducts;
        }

        // 查询月销量前20%的商品
        // 这里简化处理，实际应根据时间范围查询
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andPublishStatusEqualTo(1).andDeleteStatusEqualTo(0);
        example.setOrderByClause("sale desc");
        List<PmsProduct> allProducts = productMapper.selectByExample(example);

        // 取前20%
        int candidateSize = (int) Math.ceil(allProducts.size() * 0.2);
        if (candidateSize == 0 && !allProducts.isEmpty()) {
            candidateSize = 1;
        }
        hotProducts = allProducts.subList(0, Math.min(candidateSize, allProducts.size()));

        // 缓存热门商品
        redisTemplate.opsForValue().set(HOT_PRODUCTS_CACHE_KEY, hotProducts, HOT_PRODUCTS_EXPIRE_TIME);

        return hotProducts;
    }

    /**
     * 获取用户已购买商品ID
     */
    private Set<Long> getPurchasedProductIds(Long memberId) {
        // 先获取用户订单
        OmsOrderExample orderExample = new OmsOrderExample();
        orderExample.createCriteria().andMemberIdEqualTo(memberId);
        List<OmsOrder> orders = orderMapper.selectByExample(orderExample);
        if (CollectionUtils.isEmpty(orders)) {
            return new HashSet<>();
        }

        // 获取订单ID列表
        List<Long> orderIds = orders.stream()
                .map(OmsOrder::getId)
                .collect(Collectors.toList());

        // 获取订单商品
        OmsOrderItemExample orderItemExample = new OmsOrderItemExample();
        orderItemExample.createCriteria().andOrderIdIn(orderIds);
        List<OmsOrderItem> orderItems = orderItemMapper.selectByExample(orderItemExample);
        return orderItems.stream()
                .map(OmsOrderItem::getProductId)
                .collect(Collectors.toSet());
    }

    /**
     * 计算推荐得分
     */
    private List<RecommendedProduct> calculateRecommendationScores(List<PmsProduct> products, UserPreference userPreference, Long memberId) {
        List<RecommendedProduct> scoredProducts = new ArrayList<>();

        for (PmsProduct product : products) {
            double score = 0.0;

            // 1. 消费习惯匹配度（40%权重）
            double priceMatchScore = calculatePriceMatchScore(product.getPrice(), userPreference);
            score += priceMatchScore * 0.4;

            // 2. 商品热度得分（35%权重）
            double popularityScore = calculatePopularityScore(product);
            score += popularityScore * 0.35;

            // 3. 个性化协同过滤（25%权重）
            double collaborativeScore = calculateCollaborativeScore(product, userPreference, memberId);
            score += collaborativeScore * 0.25;

            RecommendedProduct recommendedProduct = new RecommendedProduct();
            recommendedProduct.setProductId(product.getId());
            recommendedProduct.setScore(score);
            scoredProducts.add(recommendedProduct);
        }

        return scoredProducts;
    }

    /**
     * 计算价格匹配得分
     */
    private double calculatePriceMatchScore(BigDecimal productPrice, UserPreference userPreference) {
        if (productPrice == null) {
            return 0.0;
        }

        BigDecimal lowerBound = userPreference.getPriceLowerBound();
        BigDecimal upperBound = userPreference.getPriceUpperBound();

        if (productPrice.compareTo(lowerBound) >= 0 && productPrice.compareTo(upperBound) <= 0) {
            return 1.0;
        } else if (productPrice.compareTo(lowerBound) < 0) {
            // 低于下限，距离越近得分越高
            BigDecimal diff = lowerBound.subtract(productPrice);
            return Math.max(0.0, 1.0 - diff.divide(lowerBound, 2, RoundingMode.HALF_UP).doubleValue());
        } else {
            // 高于上限，距离越近得分越高
            BigDecimal diff = productPrice.subtract(upperBound);
            return Math.max(0.0, 1.0 - diff.divide(upperBound, 2, RoundingMode.HALF_UP).doubleValue());
        }
    }

    /**
     * 计算商品热度得分
     */
    private double calculatePopularityScore(PmsProduct product) {
        if (product.getSale() == null) {
            return 0.0;
        }

        // 这里简化处理，实际应考虑近期销量趋势和增长率
        int sale = product.getSale();
        // 假设最大销量为10000，归一化到0-1
        return Math.min(1.0, sale / 10000.0);
    }

    /**
     * 计算协同过滤得分
     */
    private double calculateCollaborativeScore(PmsProduct product, UserPreference userPreference, Long memberId) {
        // 简化处理：基于浏览历史的商品类别匹配
        if (CollectionUtils.isEmpty(userPreference.getBrowsedProductIds())) {
            return 0.5; // 没有浏览历史时返回默认值
        }

        // 获取浏览过的商品类别
        Set<Long> browsedCategoryIds = new HashSet<>();
        for (Long productId : userPreference.getBrowsedProductIds()) {
            PmsProduct browsedProduct = productMapper.selectByPrimaryKey(productId);
            if (browsedProduct != null && browsedProduct.getProductCategoryId() != null) {
                browsedCategoryIds.add(browsedProduct.getProductCategoryId());
            }
        }

        // 如果商品类别在浏览历史中，得分较高
        if (browsedCategoryIds.contains(product.getProductCategoryId())) {
            return 1.0;
        } else {
            return 0.3;
        }
    }

    /**
     * 根据ID列表获取商品详情并保持排序
     */
    private List<PmsProduct> getProductsByIds(List<Long> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            return new ArrayList<>();
        }

        // 查询商品详情
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(productIds);
        List<PmsProduct> products = productMapper.selectByExample(example);

        // 保持原有序号
        Map<Long, PmsProduct> productMap = products.stream()
                .collect(Collectors.toMap(PmsProduct::getId, product -> product));

        List<PmsProduct> orderedProducts = new ArrayList<>();
        for (Long productId : productIds) {
            PmsProduct product = productMap.get(productId);
            if (product != null) {
                orderedProducts.add(product);
            }
        }

        return orderedProducts;
    }

    /**
     * 获取全局热门商品
     */
    private CommonPage<PmsProduct> getHotProducts(Integer pageNum, Integer pageSize) {
        List<PmsProduct> hotProducts = getCandidateProducts();
        int start = (pageNum - 1) * pageSize;
        int end = Math.min(start + pageSize, hotProducts.size());
        List<PmsProduct> pageProducts = hotProducts.subList(start, end);
        return CommonPage.restPage(pageProducts);
    }

    /**
     * 用户偏好内部类
     */
    private static class UserPreference {
        private Long memberId;
        private BigDecimal avgOrderPrice;
        private BigDecimal priceLowerBound;
        private BigDecimal priceUpperBound;
        private Set<Long> browsedProductIds;

        // getter和setter
        public Long getMemberId() {
            return memberId;
        }

        public void setMemberId(Long memberId) {
            this.memberId = memberId;
        }

        public BigDecimal getAvgOrderPrice() {
            return avgOrderPrice;
        }

        public void setAvgOrderPrice(BigDecimal avgOrderPrice) {
            this.avgOrderPrice = avgOrderPrice;
        }

        public BigDecimal getPriceLowerBound() {
            return priceLowerBound;
        }

        public void setPriceLowerBound(BigDecimal priceLowerBound) {
            this.priceLowerBound = priceLowerBound;
        }

        public BigDecimal getPriceUpperBound() {
            return priceUpperBound;
        }

        public void setPriceUpperBound(BigDecimal priceUpperBound) {
            this.priceUpperBound = priceUpperBound;
        }

        public Set<Long> getBrowsedProductIds() {
            return browsedProductIds;
        }

        public void setBrowsedProductIds(Set<Long> browsedProductIds) {
            this.browsedProductIds = browsedProductIds;
        }
    }

    /**
     * 推荐商品内部类
     */
    private static class RecommendedProduct {
        private Long productId;
        private double score;

        // getter和setter
        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }
    }
}