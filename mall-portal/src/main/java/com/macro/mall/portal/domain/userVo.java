package com.macro.mall.portal.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/7/4.
 *
 * @author genglintong.
 */
@Data
public class userVo {
    //主键
    private Long userId;
    //会员名称
    private String username;
    //会员密码
    private String password;
    //性别
    private Integer gender;
    //出生日期
    private Date birthday;
    //注册时间
    private Date register_time;
    //最后登录时间
    private Date last_login_time;
    //最后登录Ip
    private String last_login_ip;
    //会员等级
    private Integer user_level_id;
    //别名
    private String nickname;
    //手机号码
    private String mobile;
    //注册Ip
    private String register_ip;
    //头像
    private String avatar;
    //微信Id
    private String weixin_openid;

    //身份证号
    private String idCard;
    //推广人id
    private int promoterId;
    //推广人姓名
    private String promoterName;
    //是否实名认证 1：是 2：否
    private String isReal;
    //是否推荐购买返现 0没有、1已返现
    private Integer is_return_cash;
    //首次购买金额
    private BigDecimal first_buy_money;
    //推广小程序二维码
    private String qrCode;
    //真实姓名
    private String realName;
}
