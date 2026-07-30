package com.macro.mall.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义校验注解：校验中国大陆手机号格式
 *
 * <p>使用示例：
 * <pre>{@code
 * public class UserSaveReqVO {
 *     @NotBlank(message = "手机号不能为空")
 *     @Mobile
 *     private String mobile;
 * }
 * }</pre>
 *
 * <p>注意：{@code null} 与空字符串默认视为通过；若需必填请配合 {@code @NotBlank} / {@code @NotNull}。
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
        validatedBy = MobileValidator.class
)
public @interface Mobile {

    String message() default "手机号格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
