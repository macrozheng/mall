package com.macro.mall.portal.domain;

import com.macro.mall.model.OmsCartItem;

import java.math.BigDecimal;

/**
 * Created by macro on 2018/8/27.
 * 购物车中促销信息的封装
 */
public class CartPromotionItem {
    private OmsCartItem cartItem;
    //促销活动信息
    private String promotionMessage;
    //促销活动减去的金额，针对每个商品
    private BigDecimal reduceAmount;

    public OmsCartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(OmsCartItem cartItem) {
        this.cartItem = cartItem;
    }

    public String getPromotionMessage() {
        return promotionMessage;
    }

    public void setPromotionMessage(String promotionMessage) {
        this.promotionMessage = promotionMessage;
    }

    public BigDecimal getReduceAmount() {
        return reduceAmount;
    }

    public void setReduceAmount(BigDecimal reduceAmount) {
        this.reduceAmount = reduceAmount;
    }
}
