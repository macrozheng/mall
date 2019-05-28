package com.macro.mall.portal.component;

import cn.hutool.json.JSONUtil;
import com.macro.mall.common.api.CommonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by macro on 2018/8/6.
 */
public class GoAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().print(JSONUtil.parse(CommonResult.forbidden(authException.getMessage())));
        response.getWriter().flush();
    }
}
