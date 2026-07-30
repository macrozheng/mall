package com.macro.mall.common.validator;

import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.StrUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * {@link Mobile} 校验器实现
 * <p>
 * 实际校验逻辑委托给 Hutool 的 {@link PhoneUtil#isMobile(CharSequence)}。
 * 该方法不接受 {@code null} / 空字符串，需要在 {@code isValid} 内做空值短路。
 * </p>
 *
 * @author dromara
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    @Override
    public void initialize(Mobile annotation) {
        // 无需读取注解参数
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 缺省策略：为空即不校验，配合 @NotBlank / @NotNull 控制必填
        if (StrUtil.isEmpty(value)) {
            return true;
        }
        // 前置 trim：用户复制粘贴常带前后空白，导致 isMobile 拒绝
        String trimmed = StrUtil.trim(value);
        if (StrUtil.isEmpty(trimmed)) {
            return true;
        }
        // Hutool PhoneUtil.isMobile 校验中国大陆 11 位手机号（含 14x/15x/16x/17x/18x/19x）
        return PhoneUtil.isMobile(trimmed);
    }
}
