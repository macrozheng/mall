package com.macro.mall.sms.properties;


/**
 * @Author: HanLong
 * @Date: Create in 2018/3/18 15:21
 * @Description:    浏览器登录认证相关配置
 */
public class BrowserProperties {

    private SessionProperties session = new SessionProperties();

    /**
     * 默认登录页面
     */
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    /**
     * 默认登录方式
     */
    private LoginResponseType loginType = LoginResponseType.JSON;

    /**
     * 默认记住我的时长
     */
    private int rememberMeSeconds = 3600;

    public SessionProperties getSession() {
        return session;
    }

    public void setSession(SessionProperties session) {
        this.session = session;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginResponseType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginResponseType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
