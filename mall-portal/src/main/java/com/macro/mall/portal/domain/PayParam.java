package com.macro.mall.portal.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 * <p>
 *     支付所需参数
 *     https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=7_7&index=5
 * </p>
 * DATE 2020/7/12.
 *
 * @author genglintong.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PayParam {
    /**
     * 位置支付生成的预支付单ID
     */
    private String prepayID;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 当前时间戳
     */
    private Long timeStamp;
    /**
     * 签名类型 默认MD5
     */
    private String signType;

    /**
     * 支付签名
     */
    private String paySign;
}
