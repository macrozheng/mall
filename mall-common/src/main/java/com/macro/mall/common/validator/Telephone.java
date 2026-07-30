package com.macro.mall.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义校验注解：校验中国大陆固话 / 400 / 800 号码格式
 *
 * <p>支持的格式：
 * <ul>
 *   <li>{@code 0755-1234567} / {@code 010-12345678}（区号-号码，区号 3~4 位，号码 7~8 位）</li>
 *   <li>{@code 075512345678} / {@code 01012345678}（无分隔符）</li>
 *   <li>{@code 400-123-4567} / {@code 800-123-4567}（服务号，可带连字符）</li>
 * </ul>
 *
 * <p>使用示例：
 * <pre>{@code
 * public class CompanySaveReqVO {
 *     @Telephone
 *     private String telephone;
 * }
 * }</pre>
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
        validatedBy = TelephoneValidator.class
)
public @interface Telephone {

    String message() default "电话格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
