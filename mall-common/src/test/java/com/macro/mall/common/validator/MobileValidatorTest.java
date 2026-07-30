package com.macro.mall.common.validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link Mobile} 校验器单元测试
 * <p>
 * 覆盖：null、空、合法大陆手机号、含字母、过短。
 * </p>
 *
 * @author dromara
 */
class MobileValidatorTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    /**
     * 简易 Holder，用于触发方法/字段级别的 Jakarta bean validation
     */
    static class Holder {
        @Mobile
        @SuppressWarnings("unused")
        private String mobile;

        void setMobile(String v) {
            this.mobile = v;
        }
    }

    @BeforeAll
    static void setup() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    static void teardown() {
        factory.close();
    }

    @Test
    @DisplayName("null 应该校验通过（必填由 @NotBlank 控制）")
    void nullMobileShouldPass() {
        Holder h = new Holder();
        h.setMobile(null);
        Set<ConstraintViolation<Holder>> v = validator.validate(h);
        assertTrue(v.isEmpty(), "null 应当不触发 violation");
    }

    @Test
    @DisplayName("空字符串应该校验通过")
    void emptyMobileShouldPass() {
        Holder h = new Holder();
        h.setMobile("");
        assertTrue(validator.validate(h).isEmpty());
    }

    @Test
    @DisplayName("合法大陆手机号应该校验通过")
    void validMobileShouldPass() {
        Holder h = new Holder();
        h.setMobile("13812345678");
        assertTrue(validator.validate(h).isEmpty());
    }

    @Test
    @DisplayName("含非数字字符应该校验失败")
    void nonNumericMobileShouldFail() {
        Holder h = new Holder();
        h.setMobile("1381234567a");
        Set<ConstraintViolation<Holder>> v = validator.validate(h);
        assertEquals(1, v.size());
        assertEquals("手机号格式不正确", v.iterator().next().getMessage());
    }

    @Test
    @DisplayName("位数不足应该校验失败")
    void shortMobileShouldFail() {
        Holder h = new Holder();
        h.setMobile("123");
        assertEquals(1, validator.validate(h).size());
    }
}
