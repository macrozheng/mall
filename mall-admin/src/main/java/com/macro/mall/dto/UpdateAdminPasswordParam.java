package com.macro.mall.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;

/**
 * 修改用户名密码参数
 * Created by macro on 2019/10/9.
 */
@Getter
@Setter
public class UpdateAdminPasswordParam {
    @NotEmpty
    private String username;
    @NotEmpty
    private String oldPassword;
    @NotEmpty
    private String newPassword;
}
