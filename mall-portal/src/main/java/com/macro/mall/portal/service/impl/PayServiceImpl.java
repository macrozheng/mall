package com.macro.mall.portal.service.impl;

import com.macro.mall.portal.domain.PayParam;
import org.springframework.stereotype.Component;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/7/12.
 *
 * @author genglintong.
 */
@Component
public class PayServiceImpl {

    /**
     * 生成微信支付签名数据
     * @return
     */
    public PayParam genPayParams() {
        return new PayParam();
    }
}
