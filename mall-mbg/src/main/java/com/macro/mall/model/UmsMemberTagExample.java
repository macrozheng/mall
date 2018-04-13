package com.macro.mall.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UmsMemberTagExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsMemberTagExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andFinishOrderCountIsNull() {
            addCriterion("finish_order_count is null");
            return (Criteria) this;
        }

        public Criteria andFinishOrderCountIsNotNull() {
            addCriterion("finish_order_count is not null");
            return (Criteria) this;
        }

        public Criteria andFinishOrderCountEqualTo(Integer value) {
            addCriterion("finish_order_count =", value, "finishOrderCount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderCountNotEqualTo(Integer value) {
            addCriterion("finish_order_count <>", value, "finishOrderCount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderCountGreaterThan(Integer value) {
            addCriterion("finish_order_count >", value, "finishOrderCount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("finish_order_count >=", value, "finishOrderCount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderCountLessThan(Integer value) {
            addCriterion("finish_order_count <", value, "finishOrderCount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderCountLessThanOrEqualTo(Integer value) {
            addCriterion("finish_order_count <=", value, "finishOrderCount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderCountIn(List<Integer> values) {
            addCriterion("finish_order_count in", values, "finishOrderCount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderCountNotIn(List<Integer> values) {
            addCriterion("finish_order_count not in", values, "finishOrderCount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderCountBetween(Integer value1, Integer value2) {
            addCriterion("finish_order_count between", value1, value2, "finishOrderCount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderCountNotBetween(Integer value1, Integer value2) {
            addCriterion("finish_order_count not between", value1, value2, "finishOrderCount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderAmountIsNull() {
            addCriterion("finish_order_amount is null");
            return (Criteria) this;
        }

        public Criteria andFinishOrderAmountIsNotNull() {
            addCriterion("finish_order_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFinishOrderAmountEqualTo(BigDecimal value) {
            addCriterion("finish_order_amount =", value, "finishOrderAmount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderAmountNotEqualTo(BigDecimal value) {
            addCriterion("finish_order_amount <>", value, "finishOrderAmount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderAmountGreaterThan(BigDecimal value) {
            addCriterion("finish_order_amount >", value, "finishOrderAmount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("finish_order_amount >=", value, "finishOrderAmount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderAmountLessThan(BigDecimal value) {
            addCriterion("finish_order_amount <", value, "finishOrderAmount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("finish_order_amount <=", value, "finishOrderAmount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderAmountIn(List<BigDecimal> values) {
            addCriterion("finish_order_amount in", values, "finishOrderAmount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderAmountNotIn(List<BigDecimal> values) {
            addCriterion("finish_order_amount not in", values, "finishOrderAmount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("finish_order_amount between", value1, value2, "finishOrderAmount");
            return (Criteria) this;
        }

        public Criteria andFinishOrderAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("finish_order_amount not between", value1, value2, "finishOrderAmount");
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