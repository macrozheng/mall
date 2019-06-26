package com.macro.mall.sms.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: HanLong
 * @Date: Create in 2018/3/24 12:47
 * @Description:    校验码处理器，封装不同校验码的处理逻辑
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     *
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param servletWebRequest
     * @throws Exception
     */
    void validate(ServletWebRequest servletWebRequest);

}
