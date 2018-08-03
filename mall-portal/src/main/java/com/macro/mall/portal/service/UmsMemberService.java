package com.macro.mall.portal.service;

import com.macro.mall.model.UmsMember;
import com.macro.mall.portal.domain.CommonResult;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会员管理Service
 * Created by macro on 2018/8/3.
 */
public interface UmsMemberService {
    /**
     * 根据用户名获取会员
     */
    UmsMember getByUsername(String username);

    /**
     * 用户注册
     */
    @Transactional
    UmsMember register(String username, String password, String telephone, String authCode);

    /**
     * 登录操作
     */
    CommonResult login(String username, String password);

    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);
}
