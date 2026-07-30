package com.macro.mall.common.validator;

import com.macro.mall.common.api.ArrayValuable;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link InEnum} 校验器单元测试（含单值、集合两种解析路径）
 * <p>
 * 覆盖：单值 null、合法、非法并动态拼装可选值消息；集合 null、全部合法、部分非法。
 * </p>
 *
 * @author dromara
 */
class InEnumValidatorTest {

    /**
     * 测试用枚举，实现 {@link ArrayValuable<Integer>}
     */
    enum StatusEnum implements ArrayValuable<Integer> {
        ACTIVE(1, "启用"),
        DISABLED(0, "禁用");

        public static final Integer[] ARRAYS = Arrays.stream(values())
                .map(StatusEnum::getValue).toArray(Integer[]::new);

        private final Integer value;
        private final String label;

        StatusEnum(Integer value, String label) {
            this.value = value;
            this.label = label;
        }

        public Integer getValue() {
            return value;
        }

        @Override
        public Integer[] array() {
            return ARRAYS;
        }
    }

    static class SingleHolder {
        @InEnum(value = StatusEnum.class, message = "状态必须是 {value}")
        @SuppressWarnings("unused")
        private Integer status;

        void setStatus(Integer status) {
            this.status = status;
        }
    }

    static class CollectionHolder {
        @InEnum(value = StatusEnum.class, message = "状态必须是 {value}")
        @SuppressWarnings("unused")
        private List<Integer> statuses;

        void setStatuses(List<Integer> statuses) {
            this.statuses = statuses;
        }
    }

    private static ValidatorFactory factory;
    private static Validator validator;

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
    @DisplayName("InEnum 单值 null 应该通过")
    void nullSingleShouldPass() {
        SingleHolder h = new SingleHolder();
        h.setStatus(null);
        assertTrue(validator.validate(h).isEmpty());
    }

    @Test
    @DisplayName("InEnum 单值合法值应该通过")
    void validSingleShouldPass() {
        SingleHolder h = new SingleHolder();
        h.setStatus(1);
        assertTrue(validator.validate(h).isEmpty());
    }

    @Test
    @DisplayName("InEnum 单值非法值应该失败且 {value} 被替换为实际可选值列表")
    void invalidSingleShouldFailWithDynamicMessage() {
        SingleHolder h = new SingleHolder();
        h.setStatus(99);
        Set<ConstraintViolation<SingleHolder>> v = validator.validate(h);
        assertEquals(1, v.size());
        String msg = v.iterator().next().getMessage();
        // 枚举可选值为 {1, 0}，序列化为 [1, 0] 或 [0, 1]，两种顺序都接受
        assertTrue(msg.contains("[1, 0]") || msg.contains("[0, 1]"),
                "应当把可选值列表拼接到 message 中, 实际为: " + msg);
        assertFalse(msg.contains("{value}"), "占位符 {value} 必须已被替换");
    }

    @Test
    @DisplayName("InEnum 集合 null 应该通过")
    void nullCollectionShouldPass() {
        CollectionHolder h = new CollectionHolder();
        h.setStatuses(null);
        assertTrue(validator.validate(h).isEmpty());
    }

    @Test
    @DisplayName("InEnum 集合全合法应该通过")
    void allValidCollectionShouldPass() {
        CollectionHolder h = new CollectionHolder();
        h.setStatuses(Arrays.asList(1, 0));
        assertTrue(validator.validate(h).isEmpty());
    }

    @Test
    @DisplayName("InEnum 集合包含非法值应该失败")
    void partialInvalidCollectionShouldFail() {
        CollectionHolder h = new CollectionHolder();
        h.setStatuses(Arrays.asList(1, 99));
        assertEquals(1, validator.validate(h).size());
    }
}
