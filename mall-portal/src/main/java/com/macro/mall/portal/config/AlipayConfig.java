package com.macro.mall.portal.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @auther macrozheng
 * @description 支付宝支付相关配置
 * @date 2023/9/8
 * @github https://github.com/macrozheng
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {
    /**
     * 支付宝网关
     */
    private String gatewayUrl;
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 应用私钥
     */
    private String appPrivateKey;
    /**
     * 支付宝公钥
     */
    private String alipayPublicKey;
    /**
     * 用户确认支付后，支付宝调用的页面返回路径
     * 开发环境为：http://localhost:8060/#/pages/money/paySuccess
     */
    private String returnUrl;
    /**
     * 支付成功后，支付宝服务器主动通知商户服务器里的异步通知回调（需要公网能访问）
     * 开发环境为：http://localhost:8085/alipay/notify
     */
    private String notifyUrl;
    /**
     * 参数返回格式，只支持JSON
     */
    private String format = "JSON";
    /**
     * 请求使用的编码格式
     */
    private String charset = "UTF-8";
    /**
     * 生成签名字符串所使用的签名算法类型
     */
    private String signType = "RSA2";
}
