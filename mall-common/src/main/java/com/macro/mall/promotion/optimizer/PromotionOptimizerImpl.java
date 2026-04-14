package com.macro.mall.promotion.optimizer;

import com.macro.mall.model.*;
import com.macro.mall.promotion.domain.*;
import com.macro.mall.promotion.engine.PromotionEngine;
import com.macro.mall.promotion.enums.PromotionTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PromotionOptimizerImpl implements PromotionOptimizer {

    @Autowired
    private PromotionEngine promotionEngine;

    private static final BigDecimal ZERO = BigDecimal.ZERO;

    @Override
    public PromotionCalcResult findOptimalCombination(PromotionContext context) {
        List<PromotionCalcResult> allCombinations = findAllPossibleCombinations(context);
        
        if (CollectionUtils.isEmpty(allCombinations)) {
            return promotionEngine.calculate(context);
        }
        
        allCombinations.sort((a, b) -> {
            int discountCompare = b.getTotalDiscount().compareTo(a.getTotalDiscount());
            if (discountCompare != 0) {
                return discountCompare;
            }
            return a.getFinalAmount().compareTo(b.getFinalAmount());
        });
        
        return allCombinations.get(0);
    }

    @Override
    public List<PromotionCalcResult> findAllPossibleCombinations(PromotionContext context) {
        List<PromotionCalcResult> results = new ArrayList<>();
        
        List<SmsPromotionActivity> availablePromotions = getAvailablePromotions(context);
        List<SmsCoupon> availableCoupons = getAvailableCoupons(context);
        
        if (CollectionUtils.isEmpty(availablePromotions) && CollectionUtils.isEmpty(availableCoupons)) {
            PromotionCalcResult baseResult = promotionEngine.calculate(context);
            results.add(baseResult);
            return results;
        }
        
        List<List<SmsPromotionActivity>> promotionCombinations = 
                generatePromotionCombinations(availablePromotions);
        
        List<List<SmsCoupon>> couponCombinations = 
                generateCouponCombinations(availableCoupons);
        
        for (List<SmsPromotionActivity> promotionCombo : promotionCombinations) {
            for (List<SmsCoupon> couponCombo : couponCombinations) {
                if (isCombinationValid(promotionCombo, couponCombo)) {
                    PromotionContext modifiedContext = createModifiedContext(
                            context, promotionCombo, couponCombo);
                    
                    PromotionCalcResult result = promotionEngine.calculate(modifiedContext);
                    results.add(result);
                }
            }
        }
        
        if (results.isEmpty()) {
            PromotionCalcResult baseResult = promotionEngine.calculate(context);
            results.add(baseResult);
        }
        
        return deduplicateResults(results);
    }

    @Override
    public List<PromotionCalcResult> findTopNCombinations(PromotionContext context, int n) {
        List<PromotionCalcResult> allCombinations = findAllPossibleCombinations(context);
        
        allCombinations.sort((a, b) -> {
            int discountCompare = b.getTotalDiscount().compareTo(a.getTotalDiscount());
            if (discountCompare != 0) {
                return discountCompare;
            }
            return a.getFinalAmount().compareTo(b.getFinalAmount());
        });
        
        return allCombinations.stream()
                .limit(n)
                .collect(Collectors.toList());
    }

    private List<List<SmsPromotionActivity>> generatePromotionCombinations(
            List<SmsPromotionActivity> promotions) {
        List<List<SmsPromotionActivity>> combinations = new ArrayList<>();
        
        if (CollectionUtils.isEmpty(promotions)) {
            combinations.add(new ArrayList<>());
            return combinations;
        }
        
        combinations.add(new ArrayList<>());
        
        for (SmsPromotionActivity promotion : promotions) {
            int currentSize = combinations.size();
            for (int i = 0; i < currentSize; i++) {
                List<SmsPromotionActivity> existing = combinations.get(i);
                
                if (canAddToCombination(existing, promotion)) {
                    List<SmsPromotionActivity> newCombo = new ArrayList<>(existing);
                    newCombo.add(promotion);
                    combinations.add(newCombo);
                }
            }
        }
        
        return combinations;
    }

    private List<List<SmsCoupon>> generateCouponCombinations(List<SmsCoupon> coupons) {
        List<List<SmsCoupon>> combinations = new ArrayList<>();
        
        if (CollectionUtils.isEmpty(coupons)) {
            combinations.add(new ArrayList<>());
            return combinations;
        }
        
        combinations.add(new ArrayList<>());
        
        for (SmsCoupon coupon : coupons) {
            int currentSize = combinations.size();
            for (int i = 0; i < currentSize; i++) {
                List<SmsCoupon> existing = combinations.get(i);
                
                if (canAddCouponToCombination(existing, coupon)) {
                    List<SmsCoupon> newCombo = new ArrayList<>(existing);
                    newCombo.add(coupon);
                    combinations.add(newCombo);
                }
            }
        }
        
        return combinations;
    }

    private boolean canAddToCombination(List<SmsPromotionActivity> existing, 
                                          SmsPromotionActivity newPromotion) {
        if (CollectionUtils.isEmpty(existing)) {
            return true;
        }
        
        if (newPromotion.getStackable() == null || !newPromotion.getStackable()) {
            return false;
        }
        
        for (SmsPromotionActivity existingPromo : existing) {
            if (existingPromo.getStackable() == null || !existingPromo.getStackable()) {
                return false;
            }
            
            if (areMutuallyExclusive(existingPromo, newPromotion)) {
                return false;
            }
        }
        
        return true;
    }

    private boolean canAddCouponToCombination(List<SmsCoupon> existing, SmsCoupon newCoupon) {
        if (CollectionUtils.isEmpty(existing)) {
            return true;
        }
        
        SmsCouponExtension newExtension = getCouponExtension(newCoupon.getId());
        if (newExtension == null || newExtension.getStackable() == null || !newExtension.getStackable()) {
            return false;
        }
        
        for (SmsCoupon existingCoupon : existing) {
            SmsCouponExtension existingExtension = getCouponExtension(existingCoupon.getId());
            if (existingExtension == null || existingExtension.getStackable() == null || 
                !existingExtension.getStackable()) {
                return false;
            }
            
            if (areCouponsMutuallyExclusive(existingCoupon, newCoupon, existingExtension, newExtension)) {
                return false;
            }
        }
        
        return true;
    }

    private boolean areMutuallyExclusive(SmsPromotionActivity promo1, SmsPromotionActivity promo2) {
        String exclusiveWith1 = promo1.getExclusiveWith();
        String exclusiveWith2 = promo2.getExclusiveWith();
        
        if (exclusiveWith1 != null && !exclusiveWith1.isEmpty()) {
            Set<Long> exclusiveIds = Arrays.stream(exclusiveWith1.split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toSet());
            if (exclusiveIds.contains(promo2.getId())) {
                return true;
            }
        }
        
        if (exclusiveWith2 != null && !exclusiveWith2.isEmpty()) {
            Set<Long> exclusiveIds = Arrays.stream(exclusiveWith2.split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toSet());
            if (exclusiveIds.contains(promo1.getId())) {
                return true;
            }
        }
        
        return false;
    }

    private boolean areCouponsMutuallyExclusive(SmsCoupon coupon1, SmsCoupon coupon2,
                                                  SmsCouponExtension ext1, SmsCouponExtension ext2) {
        if (ext1 != null && ext1.getExclusiveWithCoupons() != null) {
            Set<Long> exclusiveIds = Arrays.stream(ext1.getExclusiveWithCoupons().split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toSet());
            if (exclusiveIds.contains(coupon2.getId())) {
                return true;
            }
        }
        
        if (ext2 != null && ext2.getExclusiveWithCoupons() != null) {
            Set<Long> exclusiveIds = Arrays.stream(ext2.getExclusiveWithCoupons().split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toSet());
            if (exclusiveIds.contains(coupon1.getId())) {
                return true;
            }
        }
        
        return false;
    }

    private boolean isCombinationValid(List<SmsPromotionActivity> promotions,
                                         List<SmsCoupon> coupons) {
        if (CollectionUtils.isEmpty(promotions) || CollectionUtils.isEmpty(coupons)) {
            return true;
        }
        
        for (SmsCoupon coupon : coupons) {
            SmsCouponExtension extension = getCouponExtension(coupon.getId());
            if (extension != null && extension.getCanUseWithPromotion() != null && 
                !extension.getCanUseWithPromotion()) {
                return false;
            }
            
            if (extension != null && extension.getExclusiveWithPromotions() != null) {
                Set<Long> exclusivePromoIds = Arrays.stream(extension.getExclusiveWithPromotions().split(","))
                        .map(Long::parseLong)
                        .collect(Collectors.toSet());
                
                for (SmsPromotionActivity promotion : promotions) {
                    if (exclusivePromoIds.contains(promotion.getId())) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }

    private PromotionContext createModifiedContext(PromotionContext original,
                                                    List<SmsPromotionActivity> promotions,
                                                    List<SmsCoupon> coupons) {
        PromotionContext context = new PromotionContext();
        context.setMemberId(original.getMemberId());
        context.setMemberLevelId(original.getMemberLevelId());
        context.setCartItems(new ArrayList<>(original.getCartItems()));
        context.setPlatform(original.getPlatform());
        
        if (!CollectionUtils.isEmpty(coupons)) {
            context.setSelectedCouponIds(coupons.stream()
                    .map(SmsCoupon::getId)
                    .collect(Collectors.toList()));
        }
        
        return context;
    }

    private List<PromotionCalcResult> deduplicateResults(List<PromotionCalcResult> results) {
        if (CollectionUtils.isEmpty(results)) {
            return results;
        }
        
        Map<String, PromotionCalcResult> uniqueResults = new LinkedHashMap<>();
        
        for (PromotionCalcResult result : results) {
            String key = generateResultKey(result);
            if (!uniqueResults.containsKey(key)) {
                uniqueResults.put(key, result);
            }
        }
        
        return new ArrayList<>(uniqueResults.values());
    }

    private String generateResultKey(PromotionCalcResult result) {
        StringBuilder key = new StringBuilder();
        key.append(result.getFinalAmount().toString());
        key.append("_");
        
        if (!CollectionUtils.isEmpty(result.getAppliedPromotions())) {
            List<Long> promoIds = result.getAppliedPromotions().stream()
                    .map(PromotionAppliedResult::getPromotionId)
                    .sorted()
                    .collect(Collectors.toList());
            key.append(promoIds.toString());
        }
        
        return key.toString();
    }

    private List<SmsPromotionActivity> getAvailablePromotions(PromotionContext context) {
        return new ArrayList<>();
    }

    private List<SmsCoupon> getAvailableCoupons(PromotionContext context) {
        return new ArrayList<>();
    }

    private SmsCouponExtension getCouponExtension(Long couponId) {
        return null;
    }
}
