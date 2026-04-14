package com.macro.mall.promotion.allocation;

import com.macro.mall.promotion.domain.PromotionCartItemResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
@Component
public class DiscountAllocationServiceImpl implements DiscountAllocationService {

    private static final BigDecimal ZERO = BigDecimal.ZERO;

    @Override
    public void allocateDiscount(BigDecimal totalDiscount,
                                  List<PromotionCartItemResult> items,
                                  AllocationStrategy strategy) {
        if (totalDiscount == null || totalDiscount.compareTo(ZERO) <= 0) {
            return;
        }
        if (CollectionUtils.isEmpty(items)) {
            return;
        }

        switch (strategy) {
            case BY_AMOUNT:
                allocateDiscountByAmount(totalDiscount, items);
                break;
            case BY_QUANTITY:
                allocateDiscountByQuantity(totalDiscount, items);
                break;
            case EQUALLY:
                allocateDiscountEqually(totalDiscount, items);
                break;
            case PROPORTIONAL:
            default:
                allocateDiscountProportional(totalDiscount, items);
                break;
        }
    }

    @Override
    public void allocateDiscountByAmount(BigDecimal totalDiscount,
                                           List<PromotionCartItemResult> items) {
        if (totalDiscount.compareTo(ZERO) <= 0 || CollectionUtils.isEmpty(items)) {
            return;
        }

        BigDecimal totalAmount = items.stream()
                .map(PromotionCartItemResult::getFinalSubtotal)
                .reduce(ZERO, BigDecimal::add);

        if (totalAmount.compareTo(ZERO) <= 0) {
            allocateDiscountEqually(totalDiscount, items);
            return;
        }

        BigDecimal remainingDiscount = totalDiscount;
        int itemCount = items.size();

        for (int i = 0; i < itemCount; i++) {
            PromotionCartItemResult item = items.get(i);
            BigDecimal itemAmount = item.getFinalSubtotal();

            BigDecimal itemDiscount;
            if (i == itemCount - 1) {
                itemDiscount = remainingDiscount;
            } else {
                itemDiscount = totalDiscount.multiply(itemAmount)
                        .divide(totalAmount, 2, RoundingMode.HALF_EVEN);
                remainingDiscount = remainingDiscount.subtract(itemDiscount);
            }

            applyDiscountToItem(item, itemDiscount);
        }
    }

    @Override
    public void allocateDiscountByQuantity(BigDecimal totalDiscount,
                                            List<PromotionCartItemResult> items) {
        if (totalDiscount.compareTo(ZERO) <= 0 || CollectionUtils.isEmpty(items)) {
            return;
        }

        int totalQuantity = items.stream()
                .mapToInt(PromotionCartItemResult::getQuantity)
                .sum();

        if (totalQuantity <= 0) {
            allocateDiscountEqually(totalDiscount, items);
            return;
        }

        BigDecimal discountPerUnit = totalDiscount.divide(
                new BigDecimal(totalQuantity), 4, RoundingMode.HALF_EVEN);

        BigDecimal remainingDiscount = totalDiscount;
        int itemCount = items.size();

        for (int i = 0; i < itemCount; i++) {
            PromotionCartItemResult item = items.get(i);
            int quantity = item.getQuantity();

            BigDecimal itemDiscount;
            if (i == itemCount - 1) {
                itemDiscount = remainingDiscount;
            } else {
                itemDiscount = discountPerUnit.multiply(new BigDecimal(quantity))
                        .setScale(2, RoundingMode.HALF_EVEN);
                remainingDiscount = remainingDiscount.subtract(itemDiscount);
            }

            applyDiscountToItem(item, itemDiscount);
        }
    }

    @Override
    public void allocateDiscountEqually(BigDecimal totalDiscount,
                                         List<PromotionCartItemResult> items) {
        if (totalDiscount.compareTo(ZERO) <= 0 || CollectionUtils.isEmpty(items)) {
            return;
        }

        int itemCount = items.size();
        BigDecimal discountPerItem = totalDiscount.divide(
                new BigDecimal(itemCount), 4, RoundingMode.HALF_EVEN);

        BigDecimal remainingDiscount = totalDiscount;

        for (int i = 0; i < itemCount; i++) {
            PromotionCartItemResult item = items.get(i);

            BigDecimal itemDiscount;
            if (i == itemCount - 1) {
                itemDiscount = remainingDiscount;
            } else {
                itemDiscount = discountPerItem.setScale(2, RoundingMode.HALF_EVEN);
                remainingDiscount = remainingDiscount.subtract(itemDiscount);
            }

            applyDiscountToItem(item, itemDiscount);
        }
    }

    private void allocateDiscountProportional(BigDecimal totalDiscount,
                                                List<PromotionCartItemResult> items) {
        allocateDiscountByAmount(totalDiscount, items);
    }

    private void applyDiscountToItem(PromotionCartItemResult item, BigDecimal discount) {
        if (discount == null || discount.compareTo(ZERO) <= 0) {
            return;
        }

        BigDecimal currentSubtotal = item.getFinalSubtotal();
        BigDecimal newSubtotal = currentSubtotal.subtract(discount);

        if (newSubtotal.compareTo(ZERO) < 0) {
            discount = currentSubtotal;
            newSubtotal = ZERO;
        }

        item.setFinalSubtotal(newSubtotal);
        item.setTotalDiscount(item.getTotalDiscount().add(discount));

        int quantity = item.getQuantity();
        if (quantity > 0) {
            BigDecimal newPrice = newSubtotal.divide(
                    new BigDecimal(quantity), 2, RoundingMode.HALF_EVEN);
            item.setFinalPrice(newPrice);
        }

        log.debug("Applied discount {} to item: productId={}, skuId={}", 
                discount, item.getProductId(), item.getSkuId());
    }

    public void allocateDiscountWithPrecision(BigDecimal totalDiscount,
                                                List<PromotionCartItemResult> items,
                                                AllocationStrategy strategy,
                                                int scale) {
        if (totalDiscount.compareTo(ZERO) <= 0 || CollectionUtils.isEmpty(items)) {
            return;
        }

        BigDecimal totalAmount = items.stream()
                .map(PromotionCartItemResult::getFinalSubtotal)
                .reduce(ZERO, BigDecimal::add);

        if (totalAmount.compareTo(ZERO) <= 0) {
            return;
        }

        BigDecimal allocated = ZERO;
        int itemCount = items.size();

        for (int i = 0; i < itemCount; i++) {
            PromotionCartItemResult item = items.get(i);
            BigDecimal itemAmount = item.getFinalSubtotal();

            BigDecimal itemDiscount;
            if (i == itemCount - 1) {
                itemDiscount = totalDiscount.subtract(allocated);
            } else {
                itemDiscount = totalDiscount.multiply(itemAmount)
                        .divide(totalAmount, scale, RoundingMode.HALF_EVEN);
                allocated = allocated.add(itemDiscount);
            }

            applyDiscountToItem(item, itemDiscount);
        }
    }
}
