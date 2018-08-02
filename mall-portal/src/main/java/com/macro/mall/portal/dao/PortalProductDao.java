package com.macro.mall.portal.dao;

import com.macro.mall.portal.domain.CartProduct;
import org.apache.ibatis.annotations.Param;

/**
 * 前台系统自定义商品Dao
 * Created by macro on 2018/8/2.
 */
public interface PortalProductDao {
    CartProduct getCartProduct(@Param("id") Long id);
}
