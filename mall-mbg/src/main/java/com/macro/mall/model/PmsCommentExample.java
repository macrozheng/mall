package com.macro.mall.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PmsCommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PmsCommentExample() {
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

        public Criteria andMemberNickNameIsNull() {
            addCriterion("member_nick_name is null");
            return (Criteria) this;
        }

        public Criteria andMemberNickNameIsNotNull() {
            addCriterion("member_nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andMemberNickNameEqualTo(String value) {
            addCriterion("member_nick_name =", value, "memberNickName");
            return (Criteria) this;
        }

        public Criteria andMemberNickNameNotEqualTo(String value) {
            addCriterion("member_nick_name <>", value, "memberNickName");
            return (Criteria) this;
        }

        public Criteria andMemberNickNameGreaterThan(String value) {
            addCriterion("member_nick_name >", value, "memberNickName");
            return (Criteria) this;
        }

        public Criteria andMemberNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("member_nick_name >=", value, "memberNickName");
            return (Criteria) this;
        }

        public Criteria andMemberNickNameLessThan(String value) {
            addCriterion("member_nick_name <", value, "memberNickName");
            return (Criteria) this;
        }

        public Criteria andMemberNickNameLessThanOrEqualTo(String value) {
            addCriterion("member_nick_name <=", value, "memberNickName");
            return (Criteria) this;
        }

        public Criteria andMemberNickNameLike(String value) {
            addCriterion("member_nick_name like", value, "memberNickName");
            return (Criteria) this;
        }

        public Criteria andMemberNickNameNotLike(String value) {
            addCriterion("member_nick_name not like", value, "memberNickName");
            return (Criteria) this;
        }

        public Criteria andMemberNickNameIn(List<String> values) {
            addCriterion("member_nick_name in", values, "memberNickName");
            return (Criteria) this;
        }

        public Criteria andMemberNickNameNotIn(List<String> values) {
            addCriterion("member_nick_name not in", values, "memberNickName");
            return (Criteria) this;
        }

        public Criteria andMemberNickNameBetween(String value1, String value2) {
            addCriterion("member_nick_name between", value1, value2, "memberNickName");
            return (Criteria) this;
        }

        public Criteria andMemberNickNameNotBetween(String value1, String value2) {
            addCriterion("member_nick_name not between", value1, value2, "memberNickName");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andStarIsNull() {
            addCriterion("star is null");
            return (Criteria) this;
        }

        public Criteria andStarIsNotNull() {
            addCriterion("star is not null");
            return (Criteria) this;
        }

        public Criteria andStarEqualTo(Integer value) {
            addCriterion("star =", value, "star");
            return (Criteria) this;
        }

        public Criteria andStarNotEqualTo(Integer value) {
            addCriterion("star <>", value, "star");
            return (Criteria) this;
        }

        public Criteria andStarGreaterThan(Integer value) {
            addCriterion("star >", value, "star");
            return (Criteria) this;
        }

        public Criteria andStarGreaterThanOrEqualTo(Integer value) {
            addCriterion("star >=", value, "star");
            return (Criteria) this;
        }

        public Criteria andStarLessThan(Integer value) {
            addCriterion("star <", value, "star");
            return (Criteria) this;
        }

        public Criteria andStarLessThanOrEqualTo(Integer value) {
            addCriterion("star <=", value, "star");
            return (Criteria) this;
        }

        public Criteria andStarIn(List<Integer> values) {
            addCriterion("star in", values, "star");
            return (Criteria) this;
        }

        public Criteria andStarNotIn(List<Integer> values) {
            addCriterion("star not in", values, "star");
            return (Criteria) this;
        }

        public Criteria andStarBetween(Integer value1, Integer value2) {
            addCriterion("star between", value1, value2, "star");
            return (Criteria) this;
        }

        public Criteria andStarNotBetween(Integer value1, Integer value2) {
            addCriterion("star not between", value1, value2, "star");
            return (Criteria) this;
        }

        public Criteria andMemberIpIsNull() {
            addCriterion("member_ip is null");
            return (Criteria) this;
        }

        public Criteria andMemberIpIsNotNull() {
            addCriterion("member_ip is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIpEqualTo(String value) {
            addCriterion("member_ip =", value, "memberIp");
            return (Criteria) this;
        }

        public Criteria andMemberIpNotEqualTo(String value) {
            addCriterion("member_ip <>", value, "memberIp");
            return (Criteria) this;
        }

        public Criteria andMemberIpGreaterThan(String value) {
            addCriterion("member_ip >", value, "memberIp");
            return (Criteria) this;
        }

        public Criteria andMemberIpGreaterThanOrEqualTo(String value) {
            addCriterion("member_ip >=", value, "memberIp");
            return (Criteria) this;
        }

        public Criteria andMemberIpLessThan(String value) {
            addCriterion("member_ip <", value, "memberIp");
            return (Criteria) this;
        }

        public Criteria andMemberIpLessThanOrEqualTo(String value) {
            addCriterion("member_ip <=", value, "memberIp");
            return (Criteria) this;
        }

        public Criteria andMemberIpLike(String value) {
            addCriterion("member_ip like", value, "memberIp");
            return (Criteria) this;
        }

        public Criteria andMemberIpNotLike(String value) {
            addCriterion("member_ip not like", value, "memberIp");
            return (Criteria) this;
        }

        public Criteria andMemberIpIn(List<String> values) {
            addCriterion("member_ip in", values, "memberIp");
            return (Criteria) this;
        }

        public Criteria andMemberIpNotIn(List<String> values) {
            addCriterion("member_ip not in", values, "memberIp");
            return (Criteria) this;
        }

        public Criteria andMemberIpBetween(String value1, String value2) {
            addCriterion("member_ip between", value1, value2, "memberIp");
            return (Criteria) this;
        }

        public Criteria andMemberIpNotBetween(String value1, String value2) {
            addCriterion("member_ip not between", value1, value2, "memberIp");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andShowStatusIsNull() {
            addCriterion("show_status is null");
            return (Criteria) this;
        }

        public Criteria andShowStatusIsNotNull() {
            addCriterion("show_status is not null");
            return (Criteria) this;
        }

        public Criteria andShowStatusEqualTo(Integer value) {
            addCriterion("show_status =", value, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusNotEqualTo(Integer value) {
            addCriterion("show_status <>", value, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusGreaterThan(Integer value) {
            addCriterion("show_status >", value, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("show_status >=", value, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusLessThan(Integer value) {
            addCriterion("show_status <", value, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusLessThanOrEqualTo(Integer value) {
            addCriterion("show_status <=", value, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusIn(List<Integer> values) {
            addCriterion("show_status in", values, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusNotIn(List<Integer> values) {
            addCriterion("show_status not in", values, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusBetween(Integer value1, Integer value2) {
            addCriterion("show_status between", value1, value2, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("show_status not between", value1, value2, "showStatus");
            return (Criteria) this;
        }

        public Criteria andProductAttributeIsNull() {
            addCriterion("product_attribute is null");
            return (Criteria) this;
        }

        public Criteria andProductAttributeIsNotNull() {
            addCriterion("product_attribute is not null");
            return (Criteria) this;
        }

        public Criteria andProductAttributeEqualTo(String value) {
            addCriterion("product_attribute =", value, "productAttribute");
            return (Criteria) this;
        }

        public Criteria andProductAttributeNotEqualTo(String value) {
            addCriterion("product_attribute <>", value, "productAttribute");
            return (Criteria) this;
        }

        public Criteria andProductAttributeGreaterThan(String value) {
            addCriterion("product_attribute >", value, "productAttribute");
            return (Criteria) this;
        }

        public Criteria andProductAttributeGreaterThanOrEqualTo(String value) {
            addCriterion("product_attribute >=", value, "productAttribute");
            return (Criteria) this;
        }

        public Criteria andProductAttributeLessThan(String value) {
            addCriterion("product_attribute <", value, "productAttribute");
            return (Criteria) this;
        }

        public Criteria andProductAttributeLessThanOrEqualTo(String value) {
            addCriterion("product_attribute <=", value, "productAttribute");
            return (Criteria) this;
        }

        public Criteria andProductAttributeLike(String value) {
            addCriterion("product_attribute like", value, "productAttribute");
            return (Criteria) this;
        }

        public Criteria andProductAttributeNotLike(String value) {
            addCriterion("product_attribute not like", value, "productAttribute");
            return (Criteria) this;
        }

        public Criteria andProductAttributeIn(List<String> values) {
            addCriterion("product_attribute in", values, "productAttribute");
            return (Criteria) this;
        }

        public Criteria andProductAttributeNotIn(List<String> values) {
            addCriterion("product_attribute not in", values, "productAttribute");
            return (Criteria) this;
        }

        public Criteria andProductAttributeBetween(String value1, String value2) {
            addCriterion("product_attribute between", value1, value2, "productAttribute");
            return (Criteria) this;
        }

        public Criteria andProductAttributeNotBetween(String value1, String value2) {
            addCriterion("product_attribute not between", value1, value2, "productAttribute");
            return (Criteria) this;
        }

        public Criteria andCollectCouontIsNull() {
            addCriterion("collect_couont is null");
            return (Criteria) this;
        }

        public Criteria andCollectCouontIsNotNull() {
            addCriterion("collect_couont is not null");
            return (Criteria) this;
        }

        public Criteria andCollectCouontEqualTo(Integer value) {
            addCriterion("collect_couont =", value, "collectCouont");
            return (Criteria) this;
        }

        public Criteria andCollectCouontNotEqualTo(Integer value) {
            addCriterion("collect_couont <>", value, "collectCouont");
            return (Criteria) this;
        }

        public Criteria andCollectCouontGreaterThan(Integer value) {
            addCriterion("collect_couont >", value, "collectCouont");
            return (Criteria) this;
        }

        public Criteria andCollectCouontGreaterThanOrEqualTo(Integer value) {
            addCriterion("collect_couont >=", value, "collectCouont");
            return (Criteria) this;
        }

        public Criteria andCollectCouontLessThan(Integer value) {
            addCriterion("collect_couont <", value, "collectCouont");
            return (Criteria) this;
        }

        public Criteria andCollectCouontLessThanOrEqualTo(Integer value) {
            addCriterion("collect_couont <=", value, "collectCouont");
            return (Criteria) this;
        }

        public Criteria andCollectCouontIn(List<Integer> values) {
            addCriterion("collect_couont in", values, "collectCouont");
            return (Criteria) this;
        }

        public Criteria andCollectCouontNotIn(List<Integer> values) {
            addCriterion("collect_couont not in", values, "collectCouont");
            return (Criteria) this;
        }

        public Criteria andCollectCouontBetween(Integer value1, Integer value2) {
            addCriterion("collect_couont between", value1, value2, "collectCouont");
            return (Criteria) this;
        }

        public Criteria andCollectCouontNotBetween(Integer value1, Integer value2) {
            addCriterion("collect_couont not between", value1, value2, "collectCouont");
            return (Criteria) this;
        }

        public Criteria andReadCountIsNull() {
            addCriterion("read_count is null");
            return (Criteria) this;
        }

        public Criteria andReadCountIsNotNull() {
            addCriterion("read_count is not null");
            return (Criteria) this;
        }

        public Criteria andReadCountEqualTo(Integer value) {
            addCriterion("read_count =", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountNotEqualTo(Integer value) {
            addCriterion("read_count <>", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountGreaterThan(Integer value) {
            addCriterion("read_count >", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("read_count >=", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountLessThan(Integer value) {
            addCriterion("read_count <", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountLessThanOrEqualTo(Integer value) {
            addCriterion("read_count <=", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountIn(List<Integer> values) {
            addCriterion("read_count in", values, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountNotIn(List<Integer> values) {
            addCriterion("read_count not in", values, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountBetween(Integer value1, Integer value2) {
            addCriterion("read_count between", value1, value2, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountNotBetween(Integer value1, Integer value2) {
            addCriterion("read_count not between", value1, value2, "readCount");
            return (Criteria) this;
        }

        public Criteria andPicsIsNull() {
            addCriterion("pics is null");
            return (Criteria) this;
        }

        public Criteria andPicsIsNotNull() {
            addCriterion("pics is not null");
            return (Criteria) this;
        }

        public Criteria andPicsEqualTo(String value) {
            addCriterion("pics =", value, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsNotEqualTo(String value) {
            addCriterion("pics <>", value, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsGreaterThan(String value) {
            addCriterion("pics >", value, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsGreaterThanOrEqualTo(String value) {
            addCriterion("pics >=", value, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsLessThan(String value) {
            addCriterion("pics <", value, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsLessThanOrEqualTo(String value) {
            addCriterion("pics <=", value, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsLike(String value) {
            addCriterion("pics like", value, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsNotLike(String value) {
            addCriterion("pics not like", value, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsIn(List<String> values) {
            addCriterion("pics in", values, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsNotIn(List<String> values) {
            addCriterion("pics not in", values, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsBetween(String value1, String value2) {
            addCriterion("pics between", value1, value2, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsNotBetween(String value1, String value2) {
            addCriterion("pics not between", value1, value2, "pics");
            return (Criteria) this;
        }

        public Criteria andMemberIconIsNull() {
            addCriterion("member_icon is null");
            return (Criteria) this;
        }

        public Criteria andMemberIconIsNotNull() {
            addCriterion("member_icon is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIconEqualTo(String value) {
            addCriterion("member_icon =", value, "memberIcon");
            return (Criteria) this;
        }

        public Criteria andMemberIconNotEqualTo(String value) {
            addCriterion("member_icon <>", value, "memberIcon");
            return (Criteria) this;
        }

        public Criteria andMemberIconGreaterThan(String value) {
            addCriterion("member_icon >", value, "memberIcon");
            return (Criteria) this;
        }

        public Criteria andMemberIconGreaterThanOrEqualTo(String value) {
            addCriterion("member_icon >=", value, "memberIcon");
            return (Criteria) this;
        }

        public Criteria andMemberIconLessThan(String value) {
            addCriterion("member_icon <", value, "memberIcon");
            return (Criteria) this;
        }

        public Criteria andMemberIconLessThanOrEqualTo(String value) {
            addCriterion("member_icon <=", value, "memberIcon");
            return (Criteria) this;
        }

        public Criteria andMemberIconLike(String value) {
            addCriterion("member_icon like", value, "memberIcon");
            return (Criteria) this;
        }

        public Criteria andMemberIconNotLike(String value) {
            addCriterion("member_icon not like", value, "memberIcon");
            return (Criteria) this;
        }

        public Criteria andMemberIconIn(List<String> values) {
            addCriterion("member_icon in", values, "memberIcon");
            return (Criteria) this;
        }

        public Criteria andMemberIconNotIn(List<String> values) {
            addCriterion("member_icon not in", values, "memberIcon");
            return (Criteria) this;
        }

        public Criteria andMemberIconBetween(String value1, String value2) {
            addCriterion("member_icon between", value1, value2, "memberIcon");
            return (Criteria) this;
        }

        public Criteria andMemberIconNotBetween(String value1, String value2) {
            addCriterion("member_icon not between", value1, value2, "memberIcon");
            return (Criteria) this;
        }

        public Criteria andReplayCountIsNull() {
            addCriterion("replay_count is null");
            return (Criteria) this;
        }

        public Criteria andReplayCountIsNotNull() {
            addCriterion("replay_count is not null");
            return (Criteria) this;
        }

        public Criteria andReplayCountEqualTo(Integer value) {
            addCriterion("replay_count =", value, "replayCount");
            return (Criteria) this;
        }

        public Criteria andReplayCountNotEqualTo(Integer value) {
            addCriterion("replay_count <>", value, "replayCount");
            return (Criteria) this;
        }

        public Criteria andReplayCountGreaterThan(Integer value) {
            addCriterion("replay_count >", value, "replayCount");
            return (Criteria) this;
        }

        public Criteria andReplayCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("replay_count >=", value, "replayCount");
            return (Criteria) this;
        }

        public Criteria andReplayCountLessThan(Integer value) {
            addCriterion("replay_count <", value, "replayCount");
            return (Criteria) this;
        }

        public Criteria andReplayCountLessThanOrEqualTo(Integer value) {
            addCriterion("replay_count <=", value, "replayCount");
            return (Criteria) this;
        }

        public Criteria andReplayCountIn(List<Integer> values) {
            addCriterion("replay_count in", values, "replayCount");
            return (Criteria) this;
        }

        public Criteria andReplayCountNotIn(List<Integer> values) {
            addCriterion("replay_count not in", values, "replayCount");
            return (Criteria) this;
        }

        public Criteria andReplayCountBetween(Integer value1, Integer value2) {
            addCriterion("replay_count between", value1, value2, "replayCount");
            return (Criteria) this;
        }

        public Criteria andReplayCountNotBetween(Integer value1, Integer value2) {
            addCriterion("replay_count not between", value1, value2, "replayCount");
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