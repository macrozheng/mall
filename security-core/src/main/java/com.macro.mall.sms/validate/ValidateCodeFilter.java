package com.macro.mall.sms.validate;

import com.macro.mall.sms.properties.SecurityConstants;
import com.macro.mall.sms.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: HanLong
 * @Date: Create in 2018/3/21 21:04
 * @Description:    验证码过滤器
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{

    /**
     * 验证码校验失败处理器
     */
//    @Autowired
    private GoAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    /**
     * 系统中的校验码处理器
     */
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    /**
     * 系统配置信息
     */
    @Autowired
    private SecurityProperties securityProperties;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);
    }

    /**
     * 讲系统中配置的需要校验验证码的URL根据校验的类型放入map
     *
     * @param urlString
     * @param type
     */
    protected void addUrlToMap(String urlString, ValidateCodeType type) {
        if (StringUtils.isNotBlank(urlString)) {
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            for (String url : urls) {
                urlMap.put(url, type);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        ValidateCodeType type = getValidateCodeType(request);
        logger.info("fffffffffffffffffffffwwwwwwwwwwwww");
        authenticationFailureHandler = new GoAuthenticationFailureHandler();
        if(redisService.get(REDIS_KEY_PREFIX_AUTH_CODE+request.getParameter("mobile")) != request.getParameter("password")){
//            throw new ValidateCodeException( "验证码不匹配");
        }
//        if (type != null) {
//            logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
//            try {
                /*validateCodeProcessorHolder.findValidateCodeProcessor(type)
//                        .validate(new ServletWebRequest(request, response));*/
//                logger.info("验证码校验通过");
//            } catch (ValidateCodeException exception) {
//                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
//                logger.info("验证码校验通过err");
//                return;
//            }
//        }

        chain.doFilter(request, response);

    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls) {
                if (pathMatcher.match(url, request.getRequestURI())) {
                    result = urlMap.get(url);
                }
            }
        }
        return result;
    }


    public void setAuthenticationFailureHandler(GoAuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
