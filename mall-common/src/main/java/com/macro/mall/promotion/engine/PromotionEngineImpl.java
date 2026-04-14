package com.macro.mall.promotion.engine;

import com.macro.mall.model.*;
import com.macro.mall.promotion.domain.*;
import com.macro.mall.promotion.enums.DiscountTypeEnum;
import com.macro.mall.promotion.enums.PromotionTypeEnum;
import com.macro.mall.promotion.enums.RuleTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PromotionEngineImpl implements PromotionEngine {

    private static final BigDecimal ZERO = BigDecimal.ZERO;
    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    @Override
    public PromotionCalcResult calculate(PromotionContext context) {
        return doCalculate(context, false);
    }

    @Override
    public PromotionCalcResult tryCalculate(PromotionContext context) {
        return doCalculate(context, true);
    }

    private PromotionCalcResult doCalculate(PromotionContext context, boolean isTry) {
        List<PromotionCartItem> cartItems = context.getCartItems();
        if (CollectionUtils.isEmpty(cartItems)) {
            return createEmptyResult();
        }

        BigDecimal originalAmount = calculateOriginalAmount(cartItems);

        List<PromotionAppliedResult> appliedPromotions = new ArrayList<>();
        List<PromotionCartItemResult> itemResults = initializeItemResults(cartItems);

        applyBasePricePromotions(context, itemResults, appliedPromotions);

        applyOrderLevelPromotions(context, itemResults, appliedPromotions, originalAmount);

        applyCoupons(context, itemResults, appliedPromotions);

        BigDecimal finalAmount = calculateFinalAmount(itemResults);
        BigDecimal totalDiscount = originalAmount.subtract(finalAmount);

        String explanation = buildExplanation(appliedPromotions, originalAmount, finalAmount, totalDiscount);

        PromotionCalcResult result = new PromotionCalcResult();
        result.setOriginalAmount(originalAmount);
        result.setFinalAmount(finalAmount.max(ZERO));
        result.setTotalDiscount(totalDiscount.max(ZERO));
        result.setAppliedPromotions(appliedPromotions);
        result.setItemResults(itemResults);
        result.setCalcExplanation(explanation);

        return result;
    }

    private PromotionCalcResult createEmptyResult() {
        PromotionCalcResult result = new PromotionCalcResult();
        result.setOriginalAmount(ZERO);
        result.setFinalAmount(ZERO);
        result.setTotalDiscount(ZERO);
        result.setAppliedPromotions(new ArrayList<>());
        result.setItemResults(new ArrayList<>());
        result.setCalcExplanation("购物车为空，无优惠可计算");
        return result;
    }

    private BigDecimal calculateOriginalAmount(List<PromotionCartItem> cartItems) {
        return cartItems.stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(ZERO, BigDecimal::add);
    }

    private List<PromotionCartItemResult> initializeItemResults(List<PromotionCartItem> cartItems) {
        return cartItems.stream().map(item -> {
            PromotionCartItemResult result = new PromotionCartItemResult();
            result.setProductId(item.getProductId());
            result.setSkuId(item.getProductSkuId());
            result.setProductName(item.getProductName());
            result.setSkuCode(item.getProductSkuCode());
            result.setOriginalPrice(item.getPrice());
            result.setFinalPrice(item.getPrice());
            result.setQuantity(item.getQuantity());
            result.setOriginalSubtotal(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
            result.setFinalSubtotal(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
            result.setTotalDiscount(ZERO);
            result.setDiscountDetail("");
            return result;
        }).collect(Collectors.toList());
    }

    private void applyBasePricePromotions(PromotionContext context, 
                                           List<PromotionCartItemResult> itemResults,
                                           List<PromotionAppliedResult> appliedPromotions) {
        applySkuSpecialPrice(context, itemResults, appliedPromotions);
        applyMemberPrice(context, itemResults, appliedPromotions);
    }

    private void applySkuSpecialPrice(PromotionContext context,
                                       List<PromotionCartItemResult> itemResults,
                                       List<PromotionAppliedResult> appliedPromotions) {
        Map<Long, SmsSkuSpecialPrice> skuSpecialPriceMap = getSkuSpecialPriceMap(context);
        
        for (PromotionCartItemResult item : itemResults) {
            SmsSkuSpecialPrice specialPrice = skuSpecialPriceMap.get(item.getSkuId());
            if (specialPrice != null) {
                BigDecimal originalPrice = item.getFinalPrice();
                BigDecimal specialPriceValue = specialPrice.getSpecialPrice();
                
                if (specialPriceValue.compareTo(originalPrice) < 0) {
                    BigDecimal discountPerItem = originalPrice.subtract(specialPriceValue);
                    BigDecimal totalDiscount = discountPerItem.multiply(new BigDecimal(item.getQuantity()));
                    
                    item.setFinalPrice(specialPriceValue);
                    item.setFinalSubtotal(specialPriceValue.multiply(new BigDecimal(item.getQuantity())));
                    item.setTotalDiscount(item.getTotalDiscount().add(totalDiscount));
                    item.setDiscountDetail(item.getDiscountDetail() + 
                            String.format("SKU特价: 原价%s, 特价%s, 优惠%s; ", 
                                    originalPrice, specialPriceValue, totalDiscount));
                    
                    PromotionAppliedResult applied = new PromotionAppliedResult();
                    applied.setPromotionId(specialPrice.getActivityId());
                    applied.setPromotionName("SKU特价优惠");
                    applied.setPromotionType(PromotionTypeEnum.SKU_SPECIAL_PRICE.getCode());
                    applied.setPromotionTypeDesc(PromotionTypeEnum.SKU_SPECIAL_PRICE.getDesc());
                    applied.setDiscountAmount(totalDiscount);
                    applied.setDiscountDesc(String.format("SKU特价优惠 %s 元", totalDiscount));
                    applied.setAppliedProductIds(Collections.singletonList(item.getProductId()));
                    applied.setAppliedSkuIds(Collections.singletonList(item.getSkuId()));
                    appliedPromotions.add(applied);
                }
            }
        }
    }

    private void applyMemberPrice(PromotionContext context,
                                  List<PromotionCartItemResult> itemResults,
                                  List<PromotionAppliedResult> appliedPromotions) {
        if (context.getMemberLevelId() == null) {
            return;
        }
        
        Map<Long, SmsMemberPrice> memberPriceMap = getMemberPriceMap(context);
        
        for (PromotionCartItemResult item : itemResults) {
            SmsMemberPrice memberPrice = memberPriceMap.get(item.getSkuId());
            if (memberPrice == null) {
                memberPrice = memberPriceMap.get(item.getProductId());
            }
            
            if (memberPrice != null) {
                BigDecimal currentPrice = item.getFinalPrice();
                BigDecimal memberPriceValue = memberPrice.getMemberPrice();
                
                if (memberPriceValue.compareTo(currentPrice) < 0) {
                    BigDecimal discountPerItem = currentPrice.subtract(memberPriceValue);
                    BigDecimal totalDiscount = discountPerItem.multiply(new BigDecimal(item.getQuantity()));
                    
                    item.setFinalPrice(memberPriceValue);
                    item.setFinalSubtotal(memberPriceValue.multiply(new BigDecimal(item.getQuantity())));
                    item.setTotalDiscount(item.getTotalDiscount().add(totalDiscount));
                    item.setDiscountDetail(item.getDiscountDetail() + 
                            String.format("会员价: 原价%s, 会员价%s, 优惠%s; ", 
                                    currentPrice, memberPriceValue, totalDiscount));
                    
                    PromotionAppliedResult applied = new PromotionAppliedResult();
                    applied.setPromotionId(memberPrice.getActivityId());
                    applied.setPromotionName("会员专享价");
                    applied.setPromotionType(PromotionTypeEnum.MEMBER_PRICE.getCode());
                    applied.setPromotionTypeDesc(PromotionTypeEnum.MEMBER_PRICE.getDesc());
                    applied.setDiscountAmount(totalDiscount);
                    applied.setDiscountDesc(String.format("会员价优惠 %s 元", totalDiscount));
                    applied.setAppliedProductIds(Collections.singletonList(item.getProductId()));
                    applied.setAppliedSkuIds(Collections.singletonList(item.getSkuId()));
                    appliedPromotions.add(applied);
                }
            }
        }
    }

    private void applyOrderLevelPromotions(PromotionContext context,
                                            List<PromotionCartItemResult> itemResults,
                                            List<PromotionAppliedResult> appliedPromotions,
                                            BigDecimal originalAmount) {
        List<SmsPromotionActivity> availablePromotions = getAvailablePromotions(context);
        
        availablePromotions.sort((a, b) -> {
            int priorityCompare = b.getPriority().compareTo(a.getPriority());
            if (priorityCompare != 0) {
                return priorityCompare;
            }
            return a.getId().compareTo(b.getId());
        });

        Set<Long> appliedPromotionIds = new HashSet<>();
        
        for (SmsPromotionActivity activity : availablePromotions) {
            if (appliedPromotionIds.contains(activity.getId())) {
                continue;
            }
            
            if (!isPromotionApplicable(activity, context, itemResults)) {
                continue;
            }
            
            if (!isStackable(activity, appliedPromotionIds, availablePromotions)) {
                continue;
            }
            
            BigDecimal discount = calculatePromotionDiscount(activity, itemResults, context);
            
            if (discount.compareTo(ZERO) > 0) {
                if (activity.getMaxDiscountAmount() != null && 
                    discount.compareTo(activity.getMaxDiscountAmount()) > 0) {
                    discount = activity.getMaxDiscountAmount();
                }
                
                allocateDiscountToItems(discount, itemResults, activity);
                
                PromotionAppliedResult applied = new PromotionAppliedResult();
                applied.setPromotionId(activity.getId());
                applied.setPromotionName(activity.getName());
                applied.setPromotionType(activity.getType());
                applied.setPromotionTypeDesc(PromotionTypeEnum.getByCode(activity.getType()).getDesc());
                applied.setDiscountAmount(discount);
                applied.setDiscountDesc(buildPromotionDiscountDesc(activity, discount));
                applied.setAppliedProductIds(getAppliedProductIds(itemResults, activity));
                applied.setAppliedSkuIds(getAppliedSkuIds(itemResults, activity));
                appliedPromotions.add(applied);
                
                appliedPromotionIds.add(activity.getId());
                
                if (!activity.getStackable()) {
                    break;
                }
            }
        }
    }

    private boolean isPromotionApplicable(SmsPromotionActivity activity, 
                                           PromotionContext context,
                                           List<PromotionCartItemResult> itemResults) {
        if (activity.getMinOrderAmount() != null && 
            activity.getMinOrderAmount().compareTo(ZERO) > 0) {
            BigDecimal currentAmount = calculateCurrentAmount(itemResults);
            if (currentAmount.compareTo(activity.getMinOrderAmount()) < 0) {
                return false;
            }
        }
        
        return isPromotionApplicableToItems(activity, itemResults, context);
    }

    private boolean isPromotionApplicableToItems(SmsPromotionActivity activity,
                                                  List<PromotionCartItemResult> itemResults,
                                                  PromotionContext context) {
        Integer useType = activity.getUseType();
        
        if (useType == null || useType == 0) {
            return true;
        }
        
        Set<Long> applicableProductIds = getApplicableProductIds(activity, useType);
        Set<Long> applicableCategoryIds = getApplicableCategoryIds(activity, useType);
        Set<Long> applicableBrandIds = getApplicableBrandIds(activity, useType);
        
        for (PromotionCartItemResult item : itemResults) {
            PromotionCartItem cartItem = findCartItem(context.getCartItems(), item.getSkuId());
            if (cartItem == null) {
                continue;
            }
            
            if (useType == 2 && applicableProductIds.contains(cartItem.getProductId())) {
                return true;
            }
            if (useType == 1 && applicableCategoryIds.contains(cartItem.getProductCategoryId())) {
                return true;
            }
            if (useType == 3 && applicableBrandIds.contains(cartItem.getBrandId())) {
                return true;
            }
        }
        
        return false;
    }

    private BigDecimal calculatePromotionDiscount(SmsPromotionActivity activity,
                                                   List<PromotionCartItemResult> itemResults,
                                                   PromotionContext context) {
        Integer promotionType = activity.getType();
        
        if (promotionType == null) {
            return ZERO;
        }
        
        List<SmsPromotionRule> rules = getPromotionRules(activity.getId());
        
        switch (PromotionTypeEnum.getByCode(promotionType)) {
            case FULL_REDUCTION:
                return calculateFullReductionDiscount(activity, rules, itemResults, context);
            case FULL_DISCOUNT:
                return calculateFullDiscountDiscount(activity, rules, itemResults, context);
            case NTH_ITEM_DISCOUNT:
                return calculateNthItemDiscount(activity, rules, itemResults, context);
            default:
                return ZERO;
        }
    }

    private BigDecimal calculateFullReductionDiscount(SmsPromotionActivity activity,
                                                       List<SmsPromotionRule> rules,
                                                       List<PromotionCartItemResult> itemResults,
                                                       PromotionContext context) {
        if (CollectionUtils.isEmpty(rules)) {
            return ZERO;
        }
        
        BigDecimal applicableAmount = calculateApplicableAmount(activity, itemResults, context);
        
        rules.sort((a, b) -> b.getThreshold().compareTo(a.getThreshold()));
        
        for (SmsPromotionRule rule : rules) {
            if (applicableAmount.compareTo(rule.getThreshold()) >= 0) {
                return rule.getDiscountValue();
            }
        }
        
        return ZERO;
    }

    private BigDecimal calculateFullDiscountDiscount(SmsPromotionActivity activity,
                                                      List<SmsPromotionRule> rules,
                                                      List<PromotionCartItemResult> itemResults,
                                                      PromotionContext context) {
        if (CollectionUtils.isEmpty(rules)) {
            return ZERO;
        }
        
        BigDecimal applicableAmount = calculateApplicableAmount(activity, itemResults, context);
        
        rules.sort((a, b) -> b.getThreshold().compareTo(a.getThreshold()));
        
        for (SmsPromotionRule rule : rules) {
            if (applicableAmount.compareTo(rule.getThreshold()) >= 0) {
                BigDecimal discountRate = rule.getDiscountValue();
                return applicableAmount.multiply(BigDecimal.ONE.subtract(discountRate));
            }
        }
        
        return ZERO;
    }

    private BigDecimal calculateNthItemDiscount(SmsPromotionActivity activity,
                                                 List<SmsPromotionRule> rules,
                                                 List<PromotionCartItemResult> itemResults,
                                                 PromotionContext context) {
        if (CollectionUtils.isEmpty(rules)) {
            return ZERO;
        }
        
        BigDecimal totalDiscount = ZERO;
        
        for (SmsPromotionRule rule : rules) {
            if (rule.getRuleType().equals(RuleTypeEnum.NTH_ITEM_DISCOUNT.getCode())) {
                int nthItem = rule.getThreshold().intValue();
                BigDecimal discountRate = rule.getDiscountValue();
                
                for (PromotionCartItemResult item : itemResults) {
                    if (isItemApplicable(activity, item, context)) {
                        int quantity = item.getQuantity();
                        int discountedCount = quantity / nthItem;
                        
                        if (discountedCount > 0) {
                            BigDecimal discountPerItem = item.getFinalPrice().multiply(
                                    BigDecimal.ONE.subtract(discountRate));
                            totalDiscount = totalDiscount.add(
                                    discountPerItem.multiply(new BigDecimal(discountedCount)));
                        }
                    }
                }
            }
        }
        
        return totalDiscount;
    }

    private void allocateDiscountToItems(BigDecimal discount,
                                          List<PromotionCartItemResult> itemResults,
                                          SmsPromotionActivity activity) {
        if (discount.compareTo(ZERO) <= 0) {
            return;
        }
        
        List<PromotionCartItemResult> applicableItems = itemResults.stream()
                .filter(item -> isItemApplicable(activity, item, null))
                .collect(Collectors.toList());
        
        if (CollectionUtils.isEmpty(applicableItems)) {
            return;
        }
        
        BigDecimal totalApplicableAmount = applicableItems.stream()
                .map(PromotionCartItemResult::getFinalSubtotal)
                .reduce(ZERO, BigDecimal::add);
        
        if (totalApplicableAmount.compareTo(ZERO) <= 0) {
            return;
        }
        
        BigDecimal remainingDiscount = discount;
        
        for (int i = 0; i < applicableItems.size(); i++) {
            PromotionCartItemResult item = applicableItems.get(i);
            BigDecimal itemAmount = item.getFinalSubtotal();
            
            BigDecimal itemDiscount;
            if (i == applicableItems.size() - 1) {
                itemDiscount = remainingDiscount;
            } else {
                itemDiscount = discount.multiply(itemAmount)
                        .divide(totalApplicableAmount, 2, RoundingMode.HALF_EVEN);
                remainingDiscount = remainingDiscount.subtract(itemDiscount);
            }
            
            if (itemDiscount.compareTo(ZERO) > 0) {
                BigDecimal newFinalSubtotal = item.getFinalSubtotal().subtract(itemDiscount);
                if (newFinalSubtotal.compareTo(ZERO) < 0) {
                    itemDiscount = item.getFinalSubtotal();
                    newFinalSubtotal = ZERO;
                }
                
                item.setFinalSubtotal(newFinalSubtotal);
                item.setTotalDiscount(item.getTotalDiscount().add(itemDiscount));
                
                int quantity = item.getQuantity();
                if (quantity > 0) {
                    item.setFinalPrice(newFinalSubtotal.divide(
                            new BigDecimal(quantity), 2, RoundingMode.HALF_EVEN));
                }
                
                item.setDiscountDetail(item.getDiscountDetail() + 
                        String.format("%s: 优惠%s; ", activity.getName(), itemDiscount));
            }
        }
    }

    private void applyCoupons(PromotionContext context,
                              List<PromotionCartItemResult> itemResults,
                              List<PromotionAppliedResult> appliedPromotions) {
        List<Long> selectedCouponIds = context.getSelectedCouponIds();
        if (CollectionUtils.isEmpty(selectedCouponIds)) {
            return;
        }
        
        List<SmsCoupon> coupons = getCouponsByIds(selectedCouponIds);
        
        for (SmsCoupon coupon : coupons) {
            if (!isCouponApplicable(coupon, context, itemResults)) {
                continue;
            }
            
            BigDecimal discount = calculateCouponDiscount(coupon, itemResults, context);
            
            if (discount.compareTo(ZERO) > 0) {
                allocateDiscountToItems(discount, itemResults, null);
                
                PromotionAppliedResult applied = new PromotionAppliedResult();
                applied.setPromotionId(coupon.getId());
                applied.setPromotionName(coupon.getName());
                applied.setPromotionType(PromotionTypeEnum.COUPON.getCode());
                applied.setPromotionTypeDesc(PromotionTypeEnum.COUPON.getDesc());
                applied.setDiscountAmount(discount);
                applied.setDiscountDesc(String.format("优惠券优惠 %s 元", discount));
                appliedPromotions.add(applied);
            }
        }
    }

    private boolean isCouponApplicable(SmsCoupon coupon,
                                        PromotionContext context,
                                        List<PromotionCartItemResult> itemResults) {
        if (coupon.getMinPoint() != null && coupon.getMinPoint().compareTo(ZERO) > 0) {
            BigDecimal currentAmount = calculateCurrentAmount(itemResults);
            if (currentAmount.compareTo(coupon.getMinPoint()) < 0) {
                return false;
            }
        }
        
        return true;
    }

    private BigDecimal calculateCouponDiscount(SmsCoupon coupon,
                                                List<PromotionCartItemResult> itemResults,
                                                PromotionContext context) {
        return coupon.getAmount();
    }

    private BigDecimal calculateCurrentAmount(List<PromotionCartItemResult> itemResults) {
        return itemResults.stream()
                .map(PromotionCartItemResult::getFinalSubtotal)
                .reduce(ZERO, BigDecimal::add);
    }

    private BigDecimal calculateFinalAmount(List<PromotionCartItemResult> itemResults) {
        return itemResults.stream()
                .map(PromotionCartItemResult::getFinalSubtotal)
                .reduce(ZERO, BigDecimal::add);
    }

    private BigDecimal calculateApplicableAmount(SmsPromotionActivity activity,
                                                  List<PromotionCartItemResult> itemResults,
                                                  PromotionContext context) {
        return itemResults.stream()
                .filter(item -> isItemApplicable(activity, item, context))
                .map(PromotionCartItemResult::getFinalSubtotal)
                .reduce(ZERO, BigDecimal::add);
    }

    private boolean isItemApplicable(SmsPromotionActivity activity,
                                      PromotionCartItemResult item,
                                      PromotionContext context) {
        Integer useType = activity.getUseType();
        
        if (useType == null || useType == 0) {
            return true;
        }
        
        if (context == null) {
            return true;
        }
        
        PromotionCartItem cartItem = findCartItem(context.getCartItems(), item.getSkuId());
        if (cartItem == null) {
            return false;
        }
        
        Set<Long> applicableProductIds = getApplicableProductIds(activity, useType);
        Set<Long> applicableCategoryIds = getApplicableCategoryIds(activity, useType);
        Set<Long> applicableBrandIds = getApplicableBrandIds(activity, useType);
        
        if (useType == 2 && applicableProductIds.contains(cartItem.getProductId())) {
            return true;
        }
        if (useType == 1 && applicableCategoryIds.contains(cartItem.getProductCategoryId())) {
            return true;
        }
        if (useType == 3 && applicableBrandIds.contains(cartItem.getBrandId())) {
            return true;
        }
        
        return false;
    }

    private PromotionCartItem findCartItem(List<PromotionCartItem> cartItems, Long skuId) {
        if (CollectionUtils.isEmpty(cartItems)) {
            return null;
        }
        return cartItems.stream()
                .filter(item -> item.getProductSkuId().equals(skuId))
                .findFirst()
                .orElse(null);
    }

    private Set<Long> getApplicableProductIds(SmsPromotionActivity activity, Integer useType) {
        return new HashSet<>();
    }

    private Set<Long> getApplicableCategoryIds(SmsPromotionActivity activity, Integer useType) {
        return new HashSet<>();
    }

    private Set<Long> getApplicableBrandIds(SmsPromotionActivity activity, Integer useType) {
        return new HashSet<>();
    }

    private boolean isStackable(SmsPromotionActivity activity,
                                 Set<Long> appliedPromotionIds,
                                 List<SmsPromotionActivity> availablePromotions) {
        if (activity.getStackable() == null || !activity.getStackable()) {
            return appliedPromotionIds.isEmpty();
        }
        
        String exclusiveWith = activity.getExclusiveWith();
        if (exclusiveWith != null && !exclusiveWith.isEmpty()) {
            Set<Long> exclusiveIds = Arrays.stream(exclusiveWith.split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toSet());
            
            for (Long appliedId : appliedPromotionIds) {
                if (exclusiveIds.contains(appliedId)) {
                    return false;
                }
            }
        }
        
        return true;
    }

    private List<SmsPromotionRule> getPromotionRules(Long activityId) {
        return new ArrayList<>();
    }

    private Map<Long, SmsSkuSpecialPrice> getSkuSpecialPriceMap(PromotionContext context) {
        return new HashMap<>();
    }

    private Map<Long, SmsMemberPrice> getMemberPriceMap(PromotionContext context) {
        return new HashMap<>();
    }

    private List<SmsPromotionActivity> getAvailablePromotions(PromotionContext context) {
        return new ArrayList<>();
    }

    private List<SmsCoupon> getCouponsByIds(List<Long> couponIds) {
        return new ArrayList<>();
    }

    private List<Long> getAppliedProductIds(List<PromotionCartItemResult> itemResults,
                                              SmsPromotionActivity activity) {
        return itemResults.stream()
                .map(PromotionCartItemResult::getProductId)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<Long> getAppliedSkuIds(List<PromotionCartItemResult> itemResults,
                                          SmsPromotionActivity activity) {
        return itemResults.stream()
                .map(PromotionCartItemResult::getSkuId)
                .distinct()
                .collect(Collectors.toList());
    }

    private String buildPromotionDiscountDesc(SmsPromotionActivity activity, BigDecimal discount) {
        PromotionTypeEnum type = PromotionTypeEnum.getByCode(activity.getType());
        if (type == null) {
            return String.format("优惠 %s 元", discount);
        }
        
        switch (type) {
            case FULL_REDUCTION:
                return String.format("满减优惠 %s 元", discount);
            case FULL_DISCOUNT:
                return String.format("满折优惠 %s 元", discount);
            case NTH_ITEM_DISCOUNT:
                return String.format("第N件优惠 %s 元", discount);
            default:
                return String.format("优惠 %s 元", discount);
        }
    }

    private String buildExplanation(List<PromotionAppliedResult> appliedPromotions,
                                     BigDecimal originalAmount,
                                     BigDecimal finalAmount,
                                     BigDecimal totalDiscount) {
        StringBuilder sb = new StringBuilder();
        sb.append("【价格计算说明】\n");
        sb.append(String.format("商品原价：%s 元\n", originalAmount));
        
        if (CollectionUtils.isEmpty(appliedPromotions)) {
            sb.append("无可用优惠\n");
        } else {
            sb.append("优惠明细：\n");
            for (PromotionAppliedResult applied : appliedPromotions) {
                sb.append(String.format("  - %s：%s\n", 
                        applied.getPromotionName(), applied.getDiscountDesc()));
            }
        }
        
        sb.append(String.format("总优惠金额：%s 元\n", totalDiscount.max(ZERO)));
        sb.append(String.format("应付金额：%s 元", finalAmount.max(ZERO)));
        
        return sb.toString();
    }
}
