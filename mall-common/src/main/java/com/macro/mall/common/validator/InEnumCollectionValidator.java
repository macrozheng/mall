package com.macro.mall.common.validator;

import cn.hutool.core.collection.CollUtil;
import com.macro.mall.common.api.ArrayValuable;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

/**
 * {@link InEnum} 的集合校验器
 * <p>
 * 校验 {@link Collection} 中的全部元素是否都属于 {@link ArrayValuable#array()} 给出的可选值列表。
 * </p>
 *
 * @author dromara
 */
public class InEnumCollectionValidator implements ConstraintValidator<InEnum, Collection<?>> {

    private List<?> values;

    @Override
    public void initialize(InEnum annotation) {
        ArrayValuable<?>[] arrayValuables = annotation.value().getEnumConstants();
        if (arrayValuables.length == 0) {
            this.values = Collections.emptyList();
        } else {
            this.values = Arrays.asList(arrayValuables[0].array());
        }
    }

    @Override
    public boolean isValid(Collection<?> list, ConstraintValidatorContext context) {
        // 缺省策略：null 视为通过
        if (list == null) {
            return true;
        }
        // 集合内元素全部命中允许值即通过
        if (CollUtil.containsAll(values, list)) {
            return true;
        }
        // 未命中：把实际传入的元素拼接到 message 中。
        // 使用 Matcher.quoteReplacement 防止拼接的字符串包含 $ / \\ 等被替换引擎特殊处理。
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()
                        .replaceAll("\\{value}", Matcher.quoteReplacement(CollUtil.join(list, ","))))
                .addConstraintViolation();
        return false;
    }
}
