package com.macro.mall.model;

import java.util.ArrayList;
import java.util.List;

public class OmsOrderSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OmsOrderSettingExample() {
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

        public Criteria andFlashOrderOvertimeIsNull() {
            addCriterion("flash_order_overtime is null");
            return (Criteria) this;
        }

        public Criteria andFlashOrderOvertimeIsNotNull() {
            addCriterion("flash_order_overtime is not null");
            return (Criteria) this;
        }

        public Criteria andFlashOrderOvertimeEqualTo(Integer value) {
            addCriterion("flash_order_overtime =", value, "flashOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andFlashOrderOvertimeNotEqualTo(Integer value) {
            addCriterion("flash_order_overtime <>", value, "flashOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andFlashOrderOvertimeGreaterThan(Integer value) {
            addCriterion("flash_order_overtime >", value, "flashOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andFlashOrderOvertimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("flash_order_overtime >=", value, "flashOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andFlashOrderOvertimeLessThan(Integer value) {
            addCriterion("flash_order_overtime <", value, "flashOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andFlashOrderOvertimeLessThanOrEqualTo(Integer value) {
            addCriterion("flash_order_overtime <=", value, "flashOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andFlashOrderOvertimeIn(List<Integer> values) {
            addCriterion("flash_order_overtime in", values, "flashOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andFlashOrderOvertimeNotIn(List<Integer> values) {
            addCriterion("flash_order_overtime not in", values, "flashOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andFlashOrderOvertimeBetween(Integer value1, Integer value2) {
            addCriterion("flash_order_overtime between", value1, value2, "flashOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andFlashOrderOvertimeNotBetween(Integer value1, Integer value2) {
            addCriterion("flash_order_overtime not between", value1, value2, "flashOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeIsNull() {
            addCriterion("normal_order_overtime is null");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeIsNotNull() {
            addCriterion("normal_order_overtime is not null");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeEqualTo(Integer value) {
            addCriterion("normal_order_overtime =", value, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeNotEqualTo(Integer value) {
            addCriterion("normal_order_overtime <>", value, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeGreaterThan(Integer value) {
            addCriterion("normal_order_overtime >", value, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("normal_order_overtime >=", value, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeLessThan(Integer value) {
            addCriterion("normal_order_overtime <", value, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeLessThanOrEqualTo(Integer value) {
            addCriterion("normal_order_overtime <=", value, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeIn(List<Integer> values) {
            addCriterion("normal_order_overtime in", values, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeNotIn(List<Integer> values) {
            addCriterion("normal_order_overtime not in", values, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeBetween(Integer value1, Integer value2) {
            addCriterion("normal_order_overtime between", value1, value2, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeNotBetween(Integer value1, Integer value2) {
            addCriterion("normal_order_overtime not between", value1, value2, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeIsNull() {
            addCriterion("confirm_overtime is null");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeIsNotNull() {
            addCriterion("confirm_overtime is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeEqualTo(Integer value) {
            addCriterion("confirm_overtime =", value, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeNotEqualTo(Integer value) {
            addCriterion("confirm_overtime <>", value, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeGreaterThan(Integer value) {
            addCriterion("confirm_overtime >", value, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("confirm_overtime >=", value, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeLessThan(Integer value) {
            addCriterion("confirm_overtime <", value, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeLessThanOrEqualTo(Integer value) {
            addCriterion("confirm_overtime <=", value, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeIn(List<Integer> values) {
            addCriterion("confirm_overtime in", values, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeNotIn(List<Integer> values) {
            addCriterion("confirm_overtime not in", values, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeBetween(Integer value1, Integer value2) {
            addCriterion("confirm_overtime between", value1, value2, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeNotBetween(Integer value1, Integer value2) {
            addCriterion("confirm_overtime not between", value1, value2, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andFinishOvertimeIsNull() {
            addCriterion("finish_overtime is null");
            return (Criteria) this;
        }

        public Criteria andFinishOvertimeIsNotNull() {
            addCriterion("finish_overtime is not null");
            return (Criteria) this;
        }

        public Criteria andFinishOvertimeEqualTo(Integer value) {
            addCriterion("finish_overtime =", value, "finishOvertime");
            return (Criteria) this;
        }

        public Criteria andFinishOvertimeNotEqualTo(Integer value) {
            addCriterion("finish_overtime <>", value, "finishOvertime");
            return (Criteria) this;
        }

        public Criteria andFinishOvertimeGreaterThan(Integer value) {
            addCriterion("finish_overtime >", value, "finishOvertime");
            return (Criteria) this;
        }

        public Criteria andFinishOvertimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("finish_overtime >=", value, "finishOvertime");
            return (Criteria) this;
        }

        public Criteria andFinishOvertimeLessThan(Integer value) {
            addCriterion("finish_overtime <", value, "finishOvertime");
            return (Criteria) this;
        }

        public Criteria andFinishOvertimeLessThanOrEqualTo(Integer value) {
            addCriterion("finish_overtime <=", value, "finishOvertime");
            return (Criteria) this;
        }

        public Criteria andFinishOvertimeIn(List<Integer> values) {
            addCriterion("finish_overtime in", values, "finishOvertime");
            return (Criteria) this;
        }

        public Criteria andFinishOvertimeNotIn(List<Integer> values) {
            addCriterion("finish_overtime not in", values, "finishOvertime");
            return (Criteria) this;
        }

        public Criteria andFinishOvertimeBetween(Integer value1, Integer value2) {
            addCriterion("finish_overtime between", value1, value2, "finishOvertime");
            return (Criteria) this;
        }

        public Criteria andFinishOvertimeNotBetween(Integer value1, Integer value2) {
            addCriterion("finish_overtime not between", value1, value2, "finishOvertime");
            return (Criteria) this;
        }

        public Criteria andCommentOvertimeIsNull() {
            addCriterion("comment_overtime is null");
            return (Criteria) this;
        }

        public Criteria andCommentOvertimeIsNotNull() {
            addCriterion("comment_overtime is not null");
            return (Criteria) this;
        }

        public Criteria andCommentOvertimeEqualTo(Integer value) {
            addCriterion("comment_overtime =", value, "commentOvertime");
            return (Criteria) this;
        }

        public Criteria andCommentOvertimeNotEqualTo(Integer value) {
            addCriterion("comment_overtime <>", value, "commentOvertime");
            return (Criteria) this;
        }

        public Criteria andCommentOvertimeGreaterThan(Integer value) {
            addCriterion("comment_overtime >", value, "commentOvertime");
            return (Criteria) this;
        }

        public Criteria andCommentOvertimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_overtime >=", value, "commentOvertime");
            return (Criteria) this;
        }

        public Criteria andCommentOvertimeLessThan(Integer value) {
            addCriterion("comment_overtime <", value, "commentOvertime");
            return (Criteria) this;
        }

        public Criteria andCommentOvertimeLessThanOrEqualTo(Integer value) {
            addCriterion("comment_overtime <=", value, "commentOvertime");
            return (Criteria) this;
        }

        public Criteria andCommentOvertimeIn(List<Integer> values) {
            addCriterion("comment_overtime in", values, "commentOvertime");
            return (Criteria) this;
        }

        public Criteria andCommentOvertimeNotIn(List<Integer> values) {
            addCriterion("comment_overtime not in", values, "commentOvertime");
            return (Criteria) this;
        }

        public Criteria andCommentOvertimeBetween(Integer value1, Integer value2) {
            addCriterion("comment_overtime between", value1, value2, "commentOvertime");
            return (Criteria) this;
        }

        public Criteria andCommentOvertimeNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_overtime not between", value1, value2, "commentOvertime");
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