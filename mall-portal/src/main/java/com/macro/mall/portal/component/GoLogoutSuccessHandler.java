package com.macro.mall.portal.component;

import cn.hutool.json.JSONUtil;
import com.macro.mall.common.api.CommonResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by macro on 2018/8/6.
 */
public class GoLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().print(JSONUtil.parse(CommonResult.success(null,"已注销")));
        response.getWriter().flush();
    }
}
