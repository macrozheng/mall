package com.macro.mall.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PmsFeightTemplateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PmsFeightTemplateExample() {
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

        public Criteria andChargeTypeIsNull() {
            addCriterion("charge_type is null");
            return (Criteria) this;
        }

        public Criteria andChargeTypeIsNotNull() {
            addCriterion("charge_type is not null");
            return (Criteria) this;
        }

        public Criteria andChargeTypeEqualTo(Integer value) {
            addCriterion("charge_type =", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeNotEqualTo(Integer value) {
            addCriterion("charge_type <>", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeGreaterThan(Integer value) {
            addCriterion("charge_type >", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("charge_type >=", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeLessThan(Integer value) {
            addCriterion("charge_type <", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("charge_type <=", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeIn(List<Integer> values) {
            addCriterion("charge_type in", values, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeNotIn(List<Integer> values) {
            addCriterion("charge_type not in", values, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeBetween(Integer value1, Integer value2) {
            addCriterion("charge_type between", value1, value2, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("charge_type not between", value1, value2, "chargeType");
            return (Criteria) this;
        }

        public Criteria andFirstWeightIsNull() {
            addCriterion("first_weight is null");
            return (Criteria) this;
        }

        public Criteria andFirstWeightIsNotNull() {
            addCriterion("first_weight is not null");
            return (Criteria) this;
        }

        public Criteria andFirstWeightEqualTo(BigDecimal value) {
            addCriterion("first_weight =", value, "firstWeight");
            return (Criteria) this;
        }

        public Criteria andFirstWeightNotEqualTo(BigDecimal value) {
            addCriterion("first_weight <>", value, "firstWeight");
            return (Criteria) this;
        }

        public Criteria andFirstWeightGreaterThan(BigDecimal value) {
            addCriterion("first_weight >", value, "firstWeight");
            return (Criteria) this;
        }

        public Criteria andFirstWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("first_weight >=", value, "firstWeight");
            return (Criteria) this;
        }

        public Criteria andFirstWeightLessThan(BigDecimal value) {
            addCriterion("first_weight <", value, "firstWeight");
            return (Criteria) this;
        }

        public Criteria andFirstWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("first_weight <=", value, "firstWeight");
            return (Criteria) this;
        }

        public Criteria andFirstWeightIn(List<BigDecimal> values) {
            addCriterion("first_weight in", values, "firstWeight");
            return (Criteria) this;
        }

        public Criteria andFirstWeightNotIn(List<BigDecimal> values) {
            addCriterion("first_weight not in", values, "firstWeight");
            return (Criteria) this;
        }

        public Criteria andFirstWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("first_weight between", value1, value2, "firstWeight");
            return (Criteria) this;
        }

        public Criteria andFirstWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("first_weight not between", value1, value2, "firstWeight");
            return (Criteria) this;
        }

        public Criteria andFirstFeeIsNull() {
            addCriterion("first_fee is null");
            return (Criteria) this;
        }

        public Criteria andFirstFeeIsNotNull() {
            addCriterion("first_fee is not null");
            return (Criteria) this;
        }

        public Criteria andFirstFeeEqualTo(BigDecimal value) {
            addCriterion("first_fee =", value, "firstFee");
            return (Criteria) this;
        }

        public Criteria andFirstFeeNotEqualTo(BigDecimal value) {
            addCriterion("first_fee <>", value, "firstFee");
            return (Criteria) this;
        }

        public Criteria andFirstFeeGreaterThan(BigDecimal value) {
            addCriterion("first_fee >", value, "firstFee");
            return (Criteria) this;
        }

        public Criteria andFirstFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("first_fee >=", value, "firstFee");
            return (Criteria) this;
        }

        public Criteria andFirstFeeLessThan(BigDecimal value) {
            addCriterion("first_fee <", value, "firstFee");
            return (Criteria) this;
        }

        public Criteria andFirstFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("first_fee <=", value, "firstFee");
            return (Criteria) this;
        }

        public Criteria andFirstFeeIn(List<BigDecimal> values) {
            addCriterion("first_fee in", values, "firstFee");
            return (Criteria) this;
        }

        public Criteria andFirstFeeNotIn(List<BigDecimal> values) {
            addCriterion("first_fee not in", values, "firstFee");
            return (Criteria) this;
        }

        public Criteria andFirstFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("first_fee between", value1, value2, "firstFee");
            return (Criteria) this;
        }

        public Criteria andFirstFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("first_fee not between", value1, value2, "firstFee");
            return (Criteria) this;
        }

        public Criteria andContinueWeightIsNull() {
            addCriterion("continue_weight is null");
            return (Criteria) this;
        }

        public Criteria andContinueWeightIsNotNull() {
            addCriterion("continue_weight is not null");
            return (Criteria) this;
        }

        public Criteria andContinueWeightEqualTo(BigDecimal value) {
            addCriterion("continue_weight =", value, "continueWeight");
            return (Criteria) this;
        }

        public Criteria andContinueWeightNotEqualTo(BigDecimal value) {
            addCriterion("continue_weight <>", value, "continueWeight");
            return (Criteria) this;
        }

        public Criteria andContinueWeightGreaterThan(BigDecimal value) {
            addCriterion("continue_weight >", value, "continueWeight");
            return (Criteria) this;
        }

        public Criteria andContinueWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("continue_weight >=", value, "continueWeight");
            return (Criteria) this;
        }

        public Criteria andContinueWeightLessThan(BigDecimal value) {
            addCriterion("continue_weight <", value, "continueWeight");
            return (Criteria) this;
        }

        public Criteria andContinueWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("continue_weight <=", value, "continueWeight");
            return (Criteria) this;
        }

        public Criteria andContinueWeightIn(List<BigDecimal> values) {
            addCriterion("continue_weight in", values, "continueWeight");
            return (Criteria) this;
        }

        public Criteria andContinueWeightNotIn(List<BigDecimal> values) {
            addCriterion("continue_weight not in", values, "continueWeight");
            return (Criteria) this;
        }

        public Criteria andContinueWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("continue_weight between", value1, value2, "continueWeight");
            return (Criteria) this;
        }

        public Criteria andContinueWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("continue_weight not between", value1, value2, "continueWeight");
            return (Criteria) this;
        }

        public Criteria andContinmeFeeIsNull() {
            addCriterion("continme_fee is null");
            return (Criteria) this;
        }

        public Criteria andContinmeFeeIsNotNull() {
            addCriterion("continme_fee is not null");
            return (Criteria) this;
        }

        public Criteria andContinmeFeeEqualTo(BigDecimal value) {
            addCriterion("continme_fee =", value, "continmeFee");
            return (Criteria) this;
        }

        public Criteria andContinmeFeeNotEqualTo(BigDecimal value) {
            addCriterion("continme_fee <>", value, "continmeFee");
            return (Criteria) this;
        }

        public Criteria andContinmeFeeGreaterThan(BigDecimal value) {
            addCriterion("continme_fee >", value, "continmeFee");
            return (Criteria) this;
        }

        public Criteria andContinmeFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("continme_fee >=", value, "continmeFee");
            return (Criteria) this;
        }

        public Criteria andContinmeFeeLessThan(BigDecimal value) {
            addCriterion("continme_fee <", value, "continmeFee");
            return (Criteria) this;
        }

        public Criteria andContinmeFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("continme_fee <=", value, "continmeFee");
            return (Criteria) this;
        }

        public Criteria andContinmeFeeIn(List<BigDecimal> values) {
            addCriterion("continme_fee in", values, "continmeFee");
            return (Criteria) this;
        }

        public Criteria andContinmeFeeNotIn(List<BigDecimal> values) {
            addCriterion("continme_fee not in", values, "continmeFee");
            return (Criteria) this;
        }

        public Criteria andContinmeFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("continme_fee between", value1, value2, "continmeFee");
            return (Criteria) this;
        }

        public Criteria andContinmeFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("continme_fee not between", value1, value2, "continmeFee");
            return (Criteria) this;
        }

        public Criteria andDestIsNull() {
            addCriterion("dest is null");
            return (Criteria) this;
        }

        public Criteria andDestIsNotNull() {
            addCriterion("dest is not null");
            return (Criteria) this;
        }

        public Criteria andDestEqualTo(String value) {
            addCriterion("dest =", value, "dest");
            return (Criteria) this;
        }

        public Criteria andDestNotEqualTo(String value) {
            addCriterion("dest <>", value, "dest");
            return (Criteria) this;
        }

        public Criteria andDestGreaterThan(String value) {
            addCriterion("dest >", value, "dest");
            return (Criteria) this;
        }

        public Criteria andDestGreaterThanOrEqualTo(String value) {
            addCriterion("dest >=", value, "dest");
            return (Criteria) this;
        }

        public Criteria andDestLessThan(String value) {
            addCriterion("dest <", value, "dest");
            return (Criteria) this;
        }

        public Criteria andDestLessThanOrEqualTo(String value) {
            addCriterion("dest <=", value, "dest");
            return (Criteria) this;
        }

        public Criteria andDestLike(String value) {
            addCriterion("dest like", value, "dest");
            return (Criteria) this;
        }

        public Criteria andDestNotLike(String value) {
            addCriterion("dest not like", value, "dest");
            return (Criteria) this;
        }

        public Criteria andDestIn(List<String> values) {
            addCriterion("dest in", values, "dest");
            return (Criteria) this;
        }

        public Criteria andDestNotIn(List<String> values) {
            addCriterion("dest not in", values, "dest");
            return (Criteria) this;
        }

        public Criteria andDestBetween(String value1, String value2) {
            addCriterion("dest between", value1, value2, "dest");
            return (Criteria) this;
        }

        public Criteria andDestNotBetween(String value1, String value2) {
            addCriterion("dest not between", value1, value2, "dest");
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