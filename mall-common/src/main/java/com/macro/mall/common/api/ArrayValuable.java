package com.macro.mall.common.api;

/**
 * 可枚举值接口
 * <p>
 * 任何需要提供给 {@code @InEnum} 校验的枚举类实现该接口，
 * 通过 {@link #array()} 方法返回该枚举的全部可选值数组。
 * </p>
 *
 * <p>典型用法（在枚举上声明一次）：
 * <pre>{@code
 * public enum GenderEnum implements ArrayValuable<Integer> {
 *     MALE(1, "男"),
 *     FEMALE(2, "女");
 *
 *     public static final Integer[] ARRAYS = Arrays.stream(values())
 *             .map(GenderEnum::getValue).toArray(Integer[]::new);
 *
 *     private final Integer value;
 *     private final String label;
 *
 *     @Override
 *     public Integer[] array() {
 *         return ARRAYS;
 *     }
 * }
 * }</pre>
 *
 * @param <T> 可选值的类型
 * @author dromara
 */
public interface ArrayValuable<T> {

    /**
     * 返回该枚举的全部可选值数组。
     * <p>
     * <strong>调用方约束</strong>：方法签名上允许返回 {@code null}，但调用者（即实现该接口的
     * 枚举类）<strong>必须</strong>返回非 null 数组，否则 {@code @InEnum} 校验器在调用
     * {@link java.util.Arrays#asList(Object[])} 时将抛 NPE。
     * </p>
     *
     * @return 全部可选值的非空数组
     */
    T[] array();

}
