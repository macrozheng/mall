package com.macro.mall.common.util;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * JPA Specification 构建器
 * 用于替代 MyBatis Example 的动态查询功能
 * Created by macro on 2024/1/1.
 */
public class SpecificationBuilder<T> {

    private final List<Specification<T>> specifications = new ArrayList<>();

    /**
     * 添加等于条件
     */
    public SpecificationBuilder<T> eq(String fieldName, Object value) {
        if (value != null) {
            specifications.add((root, query, cb) -> cb.equal(root.get(fieldName), value));
        }
        return this;
    }

    /**
     * 添加不等于条件
     */
    public SpecificationBuilder<T> ne(String fieldName, Object value) {
        if (value != null) {
            specifications.add((root, query, cb) -> cb.notEqual(root.get(fieldName), value));
        }
        return this;
    }

    /**
     * 添加 LIKE 条件
     */
    public SpecificationBuilder<T> like(String fieldName, String value) {
        if (value != null && !value.isEmpty()) {
            specifications.add((root, query, cb) -> cb.like(root.get(fieldName), "%" + value + "%"));
        }
        return this;
    }

    /**
     * 添加左 LIKE 条件
     */
    public SpecificationBuilder<T> likeLeft(String fieldName, String value) {
        if (value != null && !value.isEmpty()) {
            specifications.add((root, query, cb) -> cb.like(root.get(fieldName), "%" + value));
        }
        return this;
    }

    /**
     * 添加右 LIKE 条件
     */
    public SpecificationBuilder<T> likeRight(String fieldName, String value) {
        if (value != null && !value.isEmpty()) {
            specifications.add((root, query, cb) -> cb.like(root.get(fieldName), value + "%"));
        }
        return this;
    }

    /**
     * 添加 IN 条件
     */
    public SpecificationBuilder<T> in(String fieldName, List<?> values) {
        if (values != null && !values.isEmpty()) {
            specifications.add((root, query, cb) -> root.get(fieldName).in(values));
        }
        return this;
    }

    /**
     * 添加 NOT IN 条件
     */
    public SpecificationBuilder<T> notIn(String fieldName, List<?> values) {
        if (values != null && !values.isEmpty()) {
            specifications.add((root, query, cb) -> root.get(fieldName).in(values).not());
        }
        return this;
    }

    /**
     * 添加大于条件
     */
    @SuppressWarnings("unchecked")
    public <Y extends Comparable<? super Y>> SpecificationBuilder<T> gt(String fieldName, Y value) {
        if (value != null) {
            specifications.add((root, query, cb) -> cb.greaterThan(root.get(fieldName), value));
        }
        return this;
    }

    /**
     * 添加大于等于条件
     */
    @SuppressWarnings("unchecked")
    public <Y extends Comparable<? super Y>> SpecificationBuilder<T> ge(String fieldName, Y value) {
        if (value != null) {
            specifications.add((root, query, cb) -> cb.greaterThanOrEqualTo(root.get(fieldName), value));
        }
        return this;
    }

    /**
     * 添加小于条件
     */
    @SuppressWarnings("unchecked")
    public <Y extends Comparable<? super Y>> SpecificationBuilder<T> lt(String fieldName, Y value) {
        if (value != null) {
            specifications.add((root, query, cb) -> cb.lessThan(root.get(fieldName), value));
        }
        return this;
    }

    /**
     * 添加小于等于条件
     */
    @SuppressWarnings("unchecked")
    public <Y extends Comparable<? super Y>> SpecificationBuilder<T> le(String fieldName, Y value) {
        if (value != null) {
            specifications.add((root, query, cb) -> cb.lessThanOrEqualTo(root.get(fieldName), value));
        }
        return this;
    }

    /**
     * 添加 BETWEEN 条件
     */
    public <Y extends Comparable<? super Y>> SpecificationBuilder<T> between(String fieldName, Y value1, Y value2) {
        if (value1 != null && value2 != null) {
            specifications.add((root, query, cb) -> cb.between(root.get(fieldName), value1, value2));
        }
        return this;
    }

    /**
     * 添加 IS NULL 条件
     */
    public SpecificationBuilder<T> isNull(String fieldName) {
        specifications.add((root, query, cb) -> cb.isNull(root.get(fieldName)));
        return this;
    }

    /**
     * 添加 IS NOT NULL 条件
     */
    public SpecificationBuilder<T> isNotNull(String fieldName) {
        specifications.add((root, query, cb) -> cb.isNotNull(root.get(fieldName)));
        return this;
    }

    /**
     * 添加自定义条件
     */
    public SpecificationBuilder<T> custom(Specification<T> specification) {
        specifications.add(specification);
        return this;
    }

    /**
     * 构建最终的 Specification
     */
    public Specification<T> build() {
        if (specifications.isEmpty()) {
            return null;
        }
        return (root, query, cb) -> {
            Predicate[] predicates = specifications.stream()
                    .map(spec -> spec.toPredicate(root, query, cb))
                    .toArray(Predicate[]::new);
            return cb.and(predicates);
        };
    }

    /**
     * 构建带 OR 条件的 Specification
     */
    public Specification<T> buildOr() {
        if (specifications.isEmpty()) {
            return null;
        }
        return (root, query, cb) -> {
            Predicate[] predicates = specifications.stream()
                    .map(spec -> spec.toPredicate(root, query, cb))
                    .toArray(Predicate[]::new);
            return cb.or(predicates);
        };
    }

    /**
     * 静态工厂方法
     */
    public static <T> SpecificationBuilder<T> create() {
        return new SpecificationBuilder<>();
    }
}
