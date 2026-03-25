package com.macro.mall.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotEmpty;

/**
 * 用户登录参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode
public class UmsAdminLoginParam {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
