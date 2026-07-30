package com.macro.mall.common.validator;

import cn.hutool.core.util.StrUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * {@link Telephone} 校验器实现
 * <p>
 * 使用正则匹配，避免引入对 {@code ValidationUtils#isTelephone} 等上游工具类的依赖。
 * </p>
 *
 * @author dromara
 */
public class TelephoneValidator implements ConstraintValidator<Telephone, String> {

    /**
     * 固话 / 400 / 800 号码统一正则（收紧后，要求有区号或 400 / 800 前缀）：
     * <ul>
     *   <li>区号 3~4 位 + 可选连字符 + 主号 7~8 位</li>
     *   <li>400 / 800 后接 3 位 + 4 位主号（可带连字符）</li>
     * </ul>
     * 注意：不允许"纯 7-8 位无区号"通过，避免误识别身份证后 7-8 位、纯数字 ID 等场景。
     */
    private static final Pattern TELEPHONE_PATTERN = Pattern.compile(
            "^(?:\\d{3,4}-?\\d{7,8}|400-?\\d{3}-?\\d{4}|800-?\\d{3}-?\\d{4})$"
    );

    @Override
    public void initialize(Telephone annotation) {
        // 无需要读取的注解属性
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 缺省策略：空值放过，由其他 @NotBlank / @NotNull 控制
        if (StrUtil.isEmpty(value)) {
            return true;
        }
        return TELEPHONE_PATTERN.matcher(value).matches();
    }
}
