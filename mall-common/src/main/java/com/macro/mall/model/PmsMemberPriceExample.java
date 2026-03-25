package com.macro.mall.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PmsMemberPriceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PmsMemberPriceExample() {
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

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Long value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Long value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Long value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Long value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Long value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Long> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Long> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Long value1, Long value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Long value1, Long value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIdIsNull() {
            addCriterion("member_level_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIdIsNotNull() {
            addCriterion("member_level_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIdEqualTo(Long value) {
            addCriterion("member_level_id =", value, "memberLevelId");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIdNotEqualTo(Long value) {
            addCriterion("member_level_id <>", value, "memberLevelId");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIdGreaterThan(Long value) {
            addCriterion("member_level_id >", value, "memberLevelId");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("member_level_id >=", value, "memberLevelId");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIdLessThan(Long value) {
            addCriterion("member_level_id <", value, "memberLevelId");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIdLessThanOrEqualTo(Long value) {
            addCriterion("member_level_id <=", value, "memberLevelId");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIdIn(List<Long> values) {
            addCriterion("member_level_id in", values, "memberLevelId");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIdNotIn(List<Long> values) {
            addCriterion("member_level_id not in", values, "memberLevelId");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIdBetween(Long value1, Long value2) {
            addCriterion("member_level_id between", value1, value2, "memberLevelId");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIdNotBetween(Long value1, Long value2) {
            addCriterion("member_level_id not between", value1, value2, "memberLevelId");
            return (Criteria) this;
        }

        public Criteria andMemberPriceIsNull() {
            addCriterion("member_price is null");
            return (Criteria) this;
        }

        public Criteria andMemberPriceIsNotNull() {
            addCriterion("member_price is not null");
            return (Criteria) this;
        }

        public Criteria andMemberPriceEqualTo(BigDecimal value) {
            addCriterion("member_price =", value, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceNotEqualTo(BigDecimal value) {
            addCriterion("member_price <>", value, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceGreaterThan(BigDecimal value) {
            addCriterion("member_price >", value, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("member_price >=", value, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceLessThan(BigDecimal value) {
            addCriterion("member_price <", value, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("member_price <=", value, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceIn(List<BigDecimal> values) {
            addCriterion("member_price in", values, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceNotIn(List<BigDecimal> values) {
            addCriterion("member_price not in", values, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("member_price between", value1, value2, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("member_price not between", value1, value2, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNameIsNull() {
            addCriterion("member_level_name is null");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNameIsNotNull() {
            addCriterion("member_level_name is not null");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNameEqualTo(String value) {
            addCriterion("member_level_name =", value, "memberLevelName");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNameNotEqualTo(String value) {
            addCriterion("member_level_name <>", value, "memberLevelName");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNameGreaterThan(String value) {
            addCriterion("member_level_name >", value, "memberLevelName");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("member_level_name >=", value, "memberLevelName");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNameLessThan(String value) {
            addCriterion("member_level_name <", value, "memberLevelName");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNameLessThanOrEqualTo(String value) {
            addCriterion("member_level_name <=", value, "memberLevelName");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNameLike(String value) {
            addCriterion("member_level_name like", value, "memberLevelName");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNameNotLike(String value) {
            addCriterion("member_level_name not like", value, "memberLevelName");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNameIn(List<String> values) {
            addCriterion("member_level_name in", values, "memberLevelName");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNameNotIn(List<String> values) {
            addCriterion("member_level_name not in", values, "memberLevelName");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNameBetween(String value1, String value2) {
            addCriterion("member_level_name between", value1, value2, "memberLevelName");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNameNotBetween(String value1, String value2) {
            addCriterion("member_level_name not between", value1, value2, "memberLevelName");
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