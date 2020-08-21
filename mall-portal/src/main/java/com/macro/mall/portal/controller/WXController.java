package com.macro.mall.portal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.portal.domain.LoginInfo;
import com.macro.mall.portal.service.UmsMemberService;
import com.mysql.cj.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/7/4.
 *
 * @author genglintong.
 */
@Api(tags = "WX 相关对外接口")
@RestController
@RequestMapping("/wx")
public class WXController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${wx.webAccessTokenhttps}")
    private String webAccessTokenhttps;

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.secret}")
    private String secret;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsMemberService memberService;

    /**
     * 登录
     */
    @ApiOperation(value = "登录")
    @PostMapping("login_by_weixin")
    public CommonResult loginByWeixin(@RequestBody LoginInfo loginInfo, HttpServletRequest request) {
        //获取openid
        String requestUrl = String.format(this.webAccessTokenhttps,
                this.appId,
                this.secret,
                loginInfo.getCode());//通过自定义工具类组合出小程序需要的登录凭证 code

        String res = restTemplate.getForObject(requestUrl, String.class);
        JSONObject sessionData = JSON.parseObject(res);
        String openid=sessionData.getString("openid");
        if (StringUtils.isNullOrEmpty(openid)) {
            return CommonResult.failed("登录失败");
        }

        String token = memberService.login(openid, openid);
        if (token == null) {
            String tel = String.valueOf(System.currentTimeMillis());
            memberService.register(openid, openid, tel, loginInfo.getNickName());
            token = memberService.login(loginInfo.getNickName(), openid);
        }

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        tokenMap.put("openid", openid);
        tokenMap.put("userid", "1");

        return CommonResult.success(tokenMap);
    }
}
