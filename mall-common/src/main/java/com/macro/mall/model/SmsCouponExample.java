package com.macro.mall.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmsCouponExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SmsCouponExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andPlatformIsNull() {
            addCriterion("platform is null");
            return (Criteria) this;
        }

        public Criteria andPlatformIsNotNull() {
            addCriterion("platform is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformEqualTo(Integer value) {
            addCriterion("platform =", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotEqualTo(Integer value) {
            addCriterion("platform <>", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThan(Integer value) {
            addCriterion("platform >", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThanOrEqualTo(Integer value) {
            addCriterion("platform >=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThan(Integer value) {
            addCriterion("platform <", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThanOrEqualTo(Integer value) {
            addCriterion("platform <=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformIn(List<Integer> values) {
            addCriterion("platform in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotIn(List<Integer> values) {
            addCriterion("platform not in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformBetween(Integer value1, Integer value2) {
            addCriterion("platform between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotBetween(Integer value1, Integer value2) {
            addCriterion("platform not between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andCountIsNull() {
            addCriterion("count is null");
            return (Criteria) this;
        }

        public Criteria andCountIsNotNull() {
            addCriterion("count is not null");
            return (Criteria) this;
        }

        public Criteria andCountEqualTo(Integer value) {
            addCriterion("count =", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotEqualTo(Integer value) {
            addCriterion("count <>", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThan(Integer value) {
            addCriterion("count >", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("count >=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThan(Integer value) {
            addCriterion("count <", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThanOrEqualTo(Integer value) {
            addCriterion("count <=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountIn(List<Integer> values) {
            addCriterion("count in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotIn(List<Integer> values) {
            addCriterion("count not in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountBetween(Integer value1, Integer value2) {
            addCriterion("count between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotBetween(Integer value1, Integer value2) {
            addCriterion("count not between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andPerLimitIsNull() {
            addCriterion("per_limit is null");
            return (Criteria) this;
        }

        public Criteria andPerLimitIsNotNull() {
            addCriterion("per_limit is not null");
            return (Criteria) this;
        }

        public Criteria andPerLimitEqualTo(Integer value) {
            addCriterion("per_limit =", value, "perLimit");
            return (Criteria) this;
        }

        public Criteria andPerLimitNotEqualTo(Integer value) {
            addCriterion("per_limit <>", value, "perLimit");
            return (Criteria) this;
        }

        public Criteria andPerLimitGreaterThan(Integer value) {
            addCriterion("per_limit >", value, "perLimit");
            return (Criteria) this;
        }

        public Criteria andPerLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("per_limit >=", value, "perLimit");
            return (Criteria) this;
        }

        public Criteria andPerLimitLessThan(Integer value) {
            addCriterion("per_limit <", value, "perLimit");
            return (Criteria) this;
        }

        public Criteria andPerLimitLessThanOrEqualTo(Integer value) {
            addCriterion("per_limit <=", value, "perLimit");
            return (Criteria) this;
        }

        public Criteria andPerLimitIn(List<Integer> values) {
            addCriterion("per_limit in", values, "perLimit");
            return (Criteria) this;
        }

        public Criteria andPerLimitNotIn(List<Integer> values) {
            addCriterion("per_limit not in", values, "perLimit");
            return (Criteria) this;
        }

        public Criteria andPerLimitBetween(Integer value1, Integer value2) {
            addCriterion("per_limit between", value1, value2, "perLimit");
            return (Criteria) this;
        }

        public Criteria andPerLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("per_limit not between", value1, value2, "perLimit");
            return (Criteria) this;
        }

        public Criteria andMinPointIsNull() {
            addCriterion("min_point is null");
            return (Criteria) this;
        }

        public Criteria andMinPointIsNotNull() {
            addCriterion("min_point is not null");
            return (Criteria) this;
        }

        public Criteria andMinPointEqualTo(BigDecimal value) {
            addCriterion("min_point =", value, "minPoint");
            return (Criteria) this;
        }

        public Criteria andMinPointNotEqualTo(BigDecimal value) {
            addCriterion("min_point <>", value, "minPoint");
            return (Criteria) this;
        }

        public Criteria andMinPointGreaterThan(BigDecimal value) {
            addCriterion("min_point >", value, "minPoint");
            return (Criteria) this;
        }

        public Criteria andMinPointGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_point >=", value, "minPoint");
            return (Criteria) this;
        }

        public Criteria andMinPointLessThan(BigDecimal value) {
            addCriterion("min_point <", value, "minPoint");
            return (Criteria) this;
        }

        public Criteria andMinPointLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_point <=", value, "minPoint");
            return (Criteria) this;
        }

        public Criteria andMinPointIn(List<BigDecimal> values) {
            addCriterion("min_point in", values, "minPoint");
            return (Criteria) this;
        }

        public Criteria andMinPointNotIn(List<BigDecimal> values) {
            addCriterion("min_point not in", values, "minPoint");
            return (Criteria) this;
        }

        public Criteria andMinPointBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_point between", value1, value2, "minPoint");
            return (Criteria) this;
        }

        public Criteria andMinPointNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_point not between", value1, value2, "minPoint");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andUseTypeIsNull() {
            addCriterion("use_type is null");
            return (Criteria) this;
        }

        public Criteria andUseTypeIsNotNull() {
            addCriterion("use_type is not null");
            return (Criteria) this;
        }

        public Criteria andUseTypeEqualTo(Integer value) {
            addCriterion("use_type =", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotEqualTo(Integer value) {
            addCriterion("use_type <>", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeGreaterThan(Integer value) {
            addCriterion("use_type >", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("use_type >=", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeLessThan(Integer value) {
            addCriterion("use_type <", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeLessThanOrEqualTo(Integer value) {
            addCriterion("use_type <=", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeIn(List<Integer> values) {
            addCriterion("use_type in", values, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotIn(List<Integer> values) {
            addCriterion("use_type not in", values, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeBetween(Integer value1, Integer value2) {
            addCriterion("use_type between", value1, value2, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("use_type not between", value1, value2, "useType");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andPublishCountIsNull() {
            addCriterion("publish_count is null");
            return (Criteria) this;
        }

        public Criteria andPublishCountIsNotNull() {
            addCriterion("publish_count is not null");
            return (Criteria) this;
        }

        public Criteria andPublishCountEqualTo(Integer value) {
            addCriterion("publish_count =", value, "publishCount");
            return (Criteria) this;
        }

        public Criteria andPublishCountNotEqualTo(Integer value) {
            addCriterion("publish_count <>", value, "publishCount");
            return (Criteria) this;
        }

        public Criteria andPublishCountGreaterThan(Integer value) {
            addCriterion("publish_count >", value, "publishCount");
            return (Criteria) this;
        }

        public Criteria andPublishCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("publish_count >=", value, "publishCount");
            return (Criteria) this;
        }

        public Criteria andPublishCountLessThan(Integer value) {
            addCriterion("publish_count <", value, "publishCount");
            return (Criteria) this;
        }

        public Criteria andPublishCountLessThanOrEqualTo(Integer value) {
            addCriterion("publish_count <=", value, "publishCount");
            return (Criteria) this;
        }

        public Criteria andPublishCountIn(List<Integer> values) {
            addCriterion("publish_count in", values, "publishCount");
            return (Criteria) this;
        }

        public Criteria andPublishCountNotIn(List<Integer> values) {
            addCriterion("publish_count not in", values, "publishCount");
            return (Criteria) this;
        }

        public Criteria andPublishCountBetween(Integer value1, Integer value2) {
            addCriterion("publish_count between", value1, value2, "publishCount");
            return (Criteria) this;
        }

        public Criteria andPublishCountNotBetween(Integer value1, Integer value2) {
            addCriterion("publish_count not between", value1, value2, "publishCount");
            return (Criteria) this;
        }

        public Criteria andUseCountIsNull() {
            addCriterion("use_count is null");
            return (Criteria) this;
        }

        public Criteria andUseCountIsNotNull() {
            addCriterion("use_count is not null");
            return (Criteria) this;
        }

        public Criteria andUseCountEqualTo(Integer value) {
            addCriterion("use_count =", value, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountNotEqualTo(Integer value) {
            addCriterion("use_count <>", value, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountGreaterThan(Integer value) {
            addCriterion("use_count >", value, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("use_count >=", value, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountLessThan(Integer value) {
            addCriterion("use_count <", value, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountLessThanOrEqualTo(Integer value) {
            addCriterion("use_count <=", value, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountIn(List<Integer> values) {
            addCriterion("use_count in", values, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountNotIn(List<Integer> values) {
            addCriterion("use_count not in", values, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountBetween(Integer value1, Integer value2) {
            addCriterion("use_count between", value1, value2, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountNotBetween(Integer value1, Integer value2) {
            addCriterion("use_count not between", value1, value2, "useCount");
            return (Criteria) this;
        }

        public Criteria andReceiveCountIsNull() {
            addCriterion("receive_count is null");
            return (Criteria) this;
        }

        public Criteria andReceiveCountIsNotNull() {
            addCriterion("receive_count is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveCountEqualTo(Integer value) {
            addCriterion("receive_count =", value, "receiveCount");
            return (Criteria) this;
        }

        public Criteria andReceiveCountNotEqualTo(Integer value) {
            addCriterion("receive_count <>", value, "receiveCount");
            return (Criteria) this;
        }

        public Criteria andReceiveCountGreaterThan(Integer value) {
            addCriterion("receive_count >", value, "receiveCount");
            return (Criteria) this;
        }

        public Criteria andReceiveCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("receive_count >=", value, "receiveCount");
            return (Criteria) this;
        }

        public Criteria andReceiveCountLessThan(Integer value) {
            addCriterion("receive_count <", value, "receiveCount");
            return (Criteria) this;
        }

        public Criteria andReceiveCountLessThanOrEqualTo(Integer value) {
            addCriterion("receive_count <=", value, "receiveCount");
            return (Criteria) this;
        }

        public Criteria andReceiveCountIn(List<Integer> values) {
            addCriterion("receive_count in", values, "receiveCount");
            return (Criteria) this;
        }

        public Criteria andReceiveCountNotIn(List<Integer> values) {
            addCriterion("receive_count not in", values, "receiveCount");
            return (Criteria) this;
        }

        public Criteria andReceiveCountBetween(Integer value1, Integer value2) {
            addCriterion("receive_count between", value1, value2, "receiveCount");
            return (Criteria) this;
        }

        public Criteria andReceiveCountNotBetween(Integer value1, Integer value2) {
            addCriterion("receive_count not between", value1, value2, "receiveCount");
            return (Criteria) this;
        }

        public Criteria andEnableTimeIsNull() {
            addCriterion("enable_time is null");
            return (Criteria) this;
        }

        public Criteria andEnableTimeIsNotNull() {
            addCriterion("enable_time is not null");
            return (Criteria) this;
        }

        public Criteria andEnableTimeEqualTo(Date value) {
            addCriterion("enable_time =", value, "enableTime");
            return (Criteria) this;
        }

        public Criteria andEnableTimeNotEqualTo(Date value) {
            addCriterion("enable_time <>", value, "enableTime");
            return (Criteria) this;
        }

        public Criteria andEnableTimeGreaterThan(Date value) {
            addCriterion("enable_time >", value, "enableTime");
            return (Criteria) this;
        }

        public Criteria andEnableTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("enable_time >=", value, "enableTime");
            return (Criteria) this;
        }

        public Criteria andEnableTimeLessThan(Date value) {
            addCriterion("enable_time <", value, "enableTime");
            return (Criteria) this;
        }

        public Criteria andEnableTimeLessThanOrEqualTo(Date value) {
            addCriterion("enable_time <=", value, "enableTime");
            return (Criteria) this;
        }

        public Criteria andEnableTimeIn(List<Date> values) {
            addCriterion("enable_time in", values, "enableTime");
            return (Criteria) this;
        }

        public Criteria andEnableTimeNotIn(List<Date> values) {
            addCriterion("enable_time not in", values, "enableTime");
            return (Criteria) this;
        }

        public Criteria andEnableTimeBetween(Date value1, Date value2) {
            addCriterion("enable_time between", value1, value2, "enableTime");
            return (Criteria) this;
        }

        public Criteria andEnableTimeNotBetween(Date value1, Date value2) {
            addCriterion("enable_time not between", value1, value2, "enableTime");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIsNull() {
            addCriterion("member_level is null");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIsNotNull() {
            addCriterion("member_level is not null");
            return (Criteria) this;
        }

        public Criteria andMemberLevelEqualTo(Integer value) {
            addCriterion("member_level =", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNotEqualTo(Integer value) {
            addCriterion("member_level <>", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelGreaterThan(Integer value) {
            addCriterion("member_level >", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_level >=", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelLessThan(Integer value) {
            addCriterion("member_level <", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelLessThanOrEqualTo(Integer value) {
            addCriterion("member_level <=", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIn(List<Integer> values) {
            addCriterion("member_level in", values, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNotIn(List<Integer> values) {
            addCriterion("member_level not in", values, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelBetween(Integer value1, Integer value2) {
            addCriterion("member_level between", value1, value2, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("member_level not between", value1, value2, "memberLevel");
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