package com.macro.mall.portal.domain;

import lombok.Data;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/7/4.
 *
 * @author genglintong.
 */
@Data
public class LoginInfo {
    private String code;
    private String avatarUrl;
    private int gender;
    private String nickName;
    private String language;
    private String city;
    private String province;
    private String country;
    private int promoterId;
    private Long merchantId;	//商户id
}
