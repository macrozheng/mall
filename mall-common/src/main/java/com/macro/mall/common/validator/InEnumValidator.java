package com.macro.mall.common.validator;

import com.macro.mall.common.api.ArrayValuable;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

/**
 * {@link InEnum} 的单值校验器
 * <p>
 * 通过 {@link ArrayValuable#array()} 提取枚举的全部可选值，校验给定 {@code Object} 是否在其中。
 * </p>
 *
 * @author dromara
 */
public class InEnumValidator implements ConstraintValidator<InEnum, Object> {

    private List<?> values;

    @Override
    public void initialize(InEnum annotation) {
        ArrayValuable<?>[] arrayValuables = annotation.value().getEnumConstants();
        if (arrayValuables.length == 0 || arrayValuables[0].array() == null) {
            // 调用方违反 ArrayValuable 契约：不返回 null。退回空列表以避免 NPE。
            this.values = Collections.emptyList();
        } else {
            // 数组中每个元素都是同一个枚举常量（getEnumConstants 返回所有实例）
            this.values = Arrays.asList(arrayValuables[0].array());
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        // 缺省策略：null 视为通过
        if (value == null) {
            return true;
        }
        // 命中允许值即通过
        if (values.contains(value)) {
            return true;
        }
        // 未命中：动态拼接允许值列表到 message 中，方便前端直接展示。
        // 使用 Matcher.quoteReplacement 防止 values.toString() 中包含 $ / \\ 等被替换引擎特殊处理。
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()
                        .replaceAll("\\{value}", Matcher.quoteReplacement(values.toString())))
                .addConstraintViolation();
        return false;
    }
}
