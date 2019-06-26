package com.macro.mall.sms.authentication.sms;

import com.macro.mall.sms.properties.SecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: HanLong
 * @Date: Create in 2018/3/24 15:30
 * @Description:    短信验证码过滤器  类似于{@link AuthenticationFilter}
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * 请求中的参数
     */
    private String mobileParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;

    /**
     * 只接受POST方式
     */
    private boolean postOnly = true;

    public SmsCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // 请求方式校验
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        // 获取请求中的参数值
        String mobile = obtainMobile(request);
        if (mobile == null) {
            mobile = "";
        }

        mobile = mobile.trim();

        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        /**
         * 通过 {@link ProvierMagaer} 调用{@link SmsCodeAuthenticationProvider}
         */
        return this.getAuthenticationManager().authenticate(authRequest);
    }


    /**
     * 获取手机号
     */
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }

    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    /**
     * Sets the parameter name which will be used to obtain the username from
     * the login request.
     *
     * @param usernameParameter
     *            the parameter name. Defaults to "username".
     */
    public void setMobileParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.mobileParameter = usernameParameter;
    }


    /**
     * Defines whether only HTTP POST requests will be allowed by this filter.
     * If set to true, and an authentication request is received which is not a
     * POST request, an exception will be raised immediately and authentication
     * will not be attempted. The <tt>unsuccessfulAuthentication()</tt> method
     * will be called as if handling a failed authentication.
     * <p>
     * Defaults to <tt>true</tt> but may be overridden by subclasses.
     */
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getMobileParameter() {
        return mobileParameter;
    }

}
