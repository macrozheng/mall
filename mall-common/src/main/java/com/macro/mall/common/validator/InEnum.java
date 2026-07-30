package com.macro.mall.common.validator;

import com.macro.mall.common.api.ArrayValuable;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义校验注解：校验值是否在指定 {@link ArrayValuable} 枚举的可选值列表内
 *
 * <p>使用示例：
 * <pre>{@code
 * public class UserUpdateReqVO {
 *     @InEnum(value = GenderEnum.class, message = "性别只能是 {value}")
 *     private Integer gender;
 * }
 * }</pre>
 *
 * <p>错误信息模板中的 {@code {value}} 会被替换为实际允许的可选值列表，便于用户理解如何修复。
 *
 * @author dromara
 */
@Target({
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER,
        ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {InEnumValidator.class, InEnumCollectionValidator.class}
)
public @interface InEnum {

    /**
     * @return 实现了 {@link ArrayValuable} 接口的枚举类
     */
    Class<? extends ArrayValuable<?>> value();

    /**
     * @return 错误信息模板，支持 {@code {value}} 占位符（运行时被替换为可选值数组字符串）
     */
    String message() default "必须在指定范围 {value}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
