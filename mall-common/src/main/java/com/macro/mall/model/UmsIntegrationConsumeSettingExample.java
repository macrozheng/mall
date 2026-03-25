package com.macro.mall.model;

import java.util.ArrayList;
import java.util.List;

public class UmsIntegrationConsumeSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsIntegrationConsumeSettingExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDeductionPerAmountIsNull() {
            addCriterion("deduction_per_amount is null");
            return (Criteria) this;
        }

        public Criteria andDeductionPerAmountIsNotNull() {
            addCriterion("deduction_per_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDeductionPerAmountEqualTo(Integer value) {
            addCriterion("deduction_per_amount =", value, "deductionPerAmount");
            return (Criteria) this;
        }

        public Criteria andDeductionPerAmountNotEqualTo(Integer value) {
            addCriterion("deduction_per_amount <>", value, "deductionPerAmount");
            return (Criteria) this;
        }

        public Criteria andDeductionPerAmountGreaterThan(Integer value) {
            addCriterion("deduction_per_amount >", value, "deductionPerAmount");
            return (Criteria) this;
        }

        public Criteria andDeductionPerAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("deduction_per_amount >=", value, "deductionPerAmount");
            return (Criteria) this;
        }

        public Criteria andDeductionPerAmountLessThan(Integer value) {
            addCriterion("deduction_per_amount <", value, "deductionPerAmount");
            return (Criteria) this;
        }

        public Criteria andDeductionPerAmountLessThanOrEqualTo(Integer value) {
            addCriterion("deduction_per_amount <=", value, "deductionPerAmount");
            return (Criteria) this;
        }

        public Criteria andDeductionPerAmountIn(List<Integer> values) {
            addCriterion("deduction_per_amount in", values, "deductionPerAmount");
            return (Criteria) this;
        }

        public Criteria andDeductionPerAmountNotIn(List<Integer> values) {
            addCriterion("deduction_per_amount not in", values, "deductionPerAmount");
            return (Criteria) this;
        }

        public Criteria andDeductionPerAmountBetween(Integer value1, Integer value2) {
            addCriterion("deduction_per_amount between", value1, value2, "deductionPerAmount");
            return (Criteria) this;
        }

        public Criteria andDeductionPerAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("deduction_per_amount not between", value1, value2, "deductionPerAmount");
            return (Criteria) this;
        }

        public Criteria andMaxPercentPerOrderIsNull() {
            addCriterion("max_percent_per_order is null");
            return (Criteria) this;
        }

        public Criteria andMaxPercentPerOrderIsNotNull() {
            addCriterion("max_percent_per_order is not null");
            return (Criteria) this;
        }

        public Criteria andMaxPercentPerOrderEqualTo(Integer value) {
            addCriterion("max_percent_per_order =", value, "maxPercentPerOrder");
            return (Criteria) this;
        }

        public Criteria andMaxPercentPerOrderNotEqualTo(Integer value) {
            addCriterion("max_percent_per_order <>", value, "maxPercentPerOrder");
            return (Criteria) this;
        }

        public Criteria andMaxPercentPerOrderGreaterThan(Integer value) {
            addCriterion("max_percent_per_order >", value, "maxPercentPerOrder");
            return (Criteria) this;
        }

        public Criteria andMaxPercentPerOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_percent_per_order >=", value, "maxPercentPerOrder");
            return (Criteria) this;
        }

        public Criteria andMaxPercentPerOrderLessThan(Integer value) {
            addCriterion("max_percent_per_order <", value, "maxPercentPerOrder");
            return (Criteria) this;
        }

        public Criteria andMaxPercentPerOrderLessThanOrEqualTo(Integer value) {
            addCriterion("max_percent_per_order <=", value, "maxPercentPerOrder");
            return (Criteria) this;
        }

        public Criteria andMaxPercentPerOrderIn(List<Integer> values) {
            addCriterion("max_percent_per_order in", values, "maxPercentPerOrder");
            return (Criteria) this;
        }

        public Criteria andMaxPercentPerOrderNotIn(List<Integer> values) {
            addCriterion("max_percent_per_order not in", values, "maxPercentPerOrder");
            return (Criteria) this;
        }

        public Criteria andMaxPercentPerOrderBetween(Integer value1, Integer value2) {
            addCriterion("max_percent_per_order between", value1, value2, "maxPercentPerOrder");
            return (Criteria) this;
        }

        public Criteria andMaxPercentPerOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("max_percent_per_order not between", value1, value2, "maxPercentPerOrder");
            return (Criteria) this;
        }

        public Criteria andUseUnitIsNull() {
            addCriterion("use_unit is null");
            return (Criteria) this;
        }

        public Criteria andUseUnitIsNotNull() {
            addCriterion("use_unit is not null");
            return (Criteria) this;
        }

        public Criteria andUseUnitEqualTo(Integer value) {
            addCriterion("use_unit =", value, "useUnit");
            return (Criteria) this;
        }

        public Criteria andUseUnitNotEqualTo(Integer value) {
            addCriterion("use_unit <>", value, "useUnit");
            return (Criteria) this;
        }

        public Criteria andUseUnitGreaterThan(Integer value) {
            addCriterion("use_unit >", value, "useUnit");
            return (Criteria) this;
        }

        public Criteria andUseUnitGreaterThanOrEqualTo(Integer value) {
            addCriterion("use_unit >=", value, "useUnit");
            return (Criteria) this;
        }

        public Criteria andUseUnitLessThan(Integer value) {
            addCriterion("use_unit <", value, "useUnit");
            return (Criteria) this;
        }

        public Criteria andUseUnitLessThanOrEqualTo(Integer value) {
            addCriterion("use_unit <=", value, "useUnit");
            return (Criteria) this;
        }

        public Criteria andUseUnitIn(List<Integer> values) {
            addCriterion("use_unit in", values, "useUnit");
            return (Criteria) this;
        }

        public Criteria andUseUnitNotIn(List<Integer> values) {
            addCriterion("use_unit not in", values, "useUnit");
            return (Criteria) this;
        }

        public Criteria andUseUnitBetween(Integer value1, Integer value2) {
            addCriterion("use_unit between", value1, value2, "useUnit");
            return (Criteria) this;
        }

        public Criteria andUseUnitNotBetween(Integer value1, Integer value2) {
            addCriterion("use_unit not between", value1, value2, "useUnit");
            return (Criteria) this;
        }

        public Criteria andCouponStatusIsNull() {
            addCriterion("coupon_status is null");
            return (Criteria) this;
        }

        public Criteria andCouponStatusIsNotNull() {
            addCriterion("coupon_status is not null");
            return (Criteria) this;
        }

        public Criteria andCouponStatusEqualTo(Integer value) {
            addCriterion("coupon_status =", value, "couponStatus");
            return (Criteria) this;
        }

        public Criteria andCouponStatusNotEqualTo(Integer value) {
            addCriterion("coupon_status <>", value, "couponStatus");
            return (Criteria) this;
        }

        public Criteria andCouponStatusGreaterThan(Integer value) {
            addCriterion("coupon_status >", value, "couponStatus");
            return (Criteria) this;
        }

        public Criteria andCouponStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_status >=", value, "couponStatus");
            return (Criteria) this;
        }

        public Criteria andCouponStatusLessThan(Integer value) {
            addCriterion("coupon_status <", value, "couponStatus");
            return (Criteria) this;
        }

        public Criteria andCouponStatusLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_status <=", value, "couponStatus");
            return (Criteria) this;
        }

        public Criteria andCouponStatusIn(List<Integer> values) {
            addCriterion("coupon_status in", values, "couponStatus");
            return (Criteria) this;
        }

        public Criteria andCouponStatusNotIn(List<Integer> values) {
            addCriterion("coupon_status not in", values, "couponStatus");
            return (Criteria) this;
        }

        public Criteria andCouponStatusBetween(Integer value1, Integer value2) {
            addCriterion("coupon_status between", value1, value2, "couponStatus");
            return (Criteria) this;
        }

        public Criteria andCouponStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_status not between", value1, value2, "couponStatus");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}