package com.macro.mall.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmsMemberStatisticsInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsMemberStatisticsInfoExample() {
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

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Long value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Long value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Long value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Long value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Long value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Long value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Long> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Long> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Long value1, Long value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Long value1, Long value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountIsNull() {
            addCriterion("consume_amount is null");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountIsNotNull() {
            addCriterion("consume_amount is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountEqualTo(BigDecimal value) {
            addCriterion("consume_amount =", value, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountNotEqualTo(BigDecimal value) {
            addCriterion("consume_amount <>", value, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountGreaterThan(BigDecimal value) {
            addCriterion("consume_amount >", value, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("consume_amount >=", value, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountLessThan(BigDecimal value) {
            addCriterion("consume_amount <", value, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("consume_amount <=", value, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountIn(List<BigDecimal> values) {
            addCriterion("consume_amount in", values, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountNotIn(List<BigDecimal> values) {
            addCriterion("consume_amount not in", values, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("consume_amount between", value1, value2, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("consume_amount not between", value1, value2, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andOrderCountIsNull() {
            addCriterion("order_count is null");
            return (Criteria) this;
        }

        public Criteria andOrderCountIsNotNull() {
            addCriterion("order_count is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCountEqualTo(Integer value) {
            addCriterion("order_count =", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotEqualTo(Integer value) {
            addCriterion("order_count <>", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountGreaterThan(Integer value) {
            addCriterion("order_count >", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_count >=", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountLessThan(Integer value) {
            addCriterion("order_count <", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountLessThanOrEqualTo(Integer value) {
            addCriterion("order_count <=", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountIn(List<Integer> values) {
            addCriterion("order_count in", values, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotIn(List<Integer> values) {
            addCriterion("order_count not in", values, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountBetween(Integer value1, Integer value2) {
            addCriterion("order_count between", value1, value2, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotBetween(Integer value1, Integer value2) {
            addCriterion("order_count not between", value1, value2, "orderCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountIsNull() {
            addCriterion("coupon_count is null");
            return (Criteria) this;
        }

        public Criteria andCouponCountIsNotNull() {
            addCriterion("coupon_count is not null");
            return (Criteria) this;
        }

        public Criteria andCouponCountEqualTo(Integer value) {
            addCriterion("coupon_count =", value, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountNotEqualTo(Integer value) {
            addCriterion("coupon_count <>", value, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountGreaterThan(Integer value) {
            addCriterion("coupon_count >", value, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_count >=", value, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountLessThan(Integer value) {
            addCriterion("coupon_count <", value, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_count <=", value, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountIn(List<Integer> values) {
            addCriterion("coupon_count in", values, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountNotIn(List<Integer> values) {
            addCriterion("coupon_count not in", values, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountBetween(Integer value1, Integer value2) {
            addCriterion("coupon_count between", value1, value2, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_count not between", value1, value2, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountIsNull() {
            addCriterion("comment_count is null");
            return (Criteria) this;
        }

        public Criteria andCommentCountIsNotNull() {
            addCriterion("comment_count is not null");
            return (Criteria) this;
        }

        public Criteria andCommentCountEqualTo(Integer value) {
            addCriterion("comment_count =", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotEqualTo(Integer value) {
            addCriterion("comment_count <>", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountGreaterThan(Integer value) {
            addCriterion("comment_count >", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_count >=", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountLessThan(Integer value) {
            addCriterion("comment_count <", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountLessThanOrEqualTo(Integer value) {
            addCriterion("comment_count <=", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountIn(List<Integer> values) {
            addCriterion("comment_count in", values, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotIn(List<Integer> values) {
            addCriterion("comment_count not in", values, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountBetween(Integer value1, Integer value2) {
            addCriterion("comment_count between", value1, value2, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_count not between", value1, value2, "commentCount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderCountIsNull() {
            addCriterion("return_order_count is null");
            return (Criteria) this;
        }

        public Criteria andReturnOrderCountIsNotNull() {
            addCriterion("return_order_count is not null");
            return (Criteria) this;
        }

        public Criteria andReturnOrderCountEqualTo(Integer value) {
            addCriterion("return_order_count =", value, "returnOrderCount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderCountNotEqualTo(Integer value) {
            addCriterion("return_order_count <>", value, "returnOrderCount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderCountGreaterThan(Integer value) {
            addCriterion("return_order_count >", value, "returnOrderCount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("return_order_count >=", value, "returnOrderCount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderCountLessThan(Integer value) {
            addCriterion("return_order_count <", value, "returnOrderCount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderCountLessThanOrEqualTo(Integer value) {
            addCriterion("return_order_count <=", value, "returnOrderCount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderCountIn(List<Integer> values) {
            addCriterion("return_order_count in", values, "returnOrderCount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderCountNotIn(List<Integer> values) {
            addCriterion("return_order_count not in", values, "returnOrderCount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderCountBetween(Integer value1, Integer value2) {
            addCriterion("return_order_count between", value1, value2, "returnOrderCount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderCountNotBetween(Integer value1, Integer value2) {
            addCriterion("return_order_count not between", value1, value2, "returnOrderCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountIsNull() {
            addCriterion("login_count is null");
            return (Criteria) this;
        }

        public Criteria andLoginCountIsNotNull() {
            addCriterion("login_count is not null");
            return (Criteria) this;
        }

        public Criteria andLoginCountEqualTo(Integer value) {
            addCriterion("login_count =", value, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountNotEqualTo(Integer value) {
            addCriterion("login_count <>", value, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountGreaterThan(Integer value) {
            addCriterion("login_count >", value, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("login_count >=", value, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountLessThan(Integer value) {
            addCriterion("login_count <", value, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountLessThanOrEqualTo(Integer value) {
            addCriterion("login_count <=", value, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountIn(List<Integer> values) {
            addCriterion("login_count in", values, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountNotIn(List<Integer> values) {
            addCriterion("login_count not in", values, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountBetween(Integer value1, Integer value2) {
            addCriterion("login_count between", value1, value2, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountNotBetween(Integer value1, Integer value2) {
            addCriterion("login_count not between", value1, value2, "loginCount");
            return (Criteria) this;
        }

        public Criteria andAttendCountIsNull() {
            addCriterion("attend_count is null");
            return (Criteria) this;
        }

        public Criteria andAttendCountIsNotNull() {
            addCriterion("attend_count is not null");
            return (Criteria) this;
        }

        public Criteria andAttendCountEqualTo(Integer value) {
            addCriterion("attend_count =", value, "attendCount");
            return (Criteria) this;
        }

        public Criteria andAttendCountNotEqualTo(Integer value) {
            addCriterion("attend_count <>", value, "attendCount");
            return (Criteria) this;
        }

        public Criteria andAttendCountGreaterThan(Integer value) {
            addCriterion("attend_count >", value, "attendCount");
            return (Criteria) this;
        }

        public Criteria andAttendCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("attend_count >=", value, "attendCount");
            return (Criteria) this;
        }

        public Criteria andAttendCountLessThan(Integer value) {
            addCriterion("attend_count <", value, "attendCount");
            return (Criteria) this;
        }

        public Criteria andAttendCountLessThanOrEqualTo(Integer value) {
            addCriterion("attend_count <=", value, "attendCount");
            return (Criteria) this;
        }

        public Criteria andAttendCountIn(List<Integer> values) {
            addCriterion("attend_count in", values, "attendCount");
            return (Criteria) this;
        }

        public Criteria andAttendCountNotIn(List<Integer> values) {
            addCriterion("attend_count not in", values, "attendCount");
            return (Criteria) this;
        }

        public Criteria andAttendCountBetween(Integer value1, Integer value2) {
            addCriterion("attend_count between", value1, value2, "attendCount");
            return (Criteria) this;
        }

        public Criteria andAttendCountNotBetween(Integer value1, Integer value2) {
            addCriterion("attend_count not between", value1, value2, "attendCount");
            return (Criteria) this;
        }

        public Criteria andFansCountIsNull() {
            addCriterion("fans_count is null");
            return (Criteria) this;
        }

        public Criteria andFansCountIsNotNull() {
            addCriterion("fans_count is not null");
            return (Criteria) this;
        }

        public Criteria andFansCountEqualTo(Integer value) {
            addCriterion("fans_count =", value, "fansCount");
            return (Criteria) this;
        }

        public Criteria andFansCountNotEqualTo(Integer value) {
            addCriterion("fans_count <>", value, "fansCount");
            return (Criteria) this;
        }

        public Criteria andFansCountGreaterThan(Integer value) {
            addCriterion("fans_count >", value, "fansCount");
            return (Criteria) this;
        }

        public Criteria andFansCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("fans_count >=", value, "fansCount");
            return (Criteria) this;
        }

        public Criteria andFansCountLessThan(Integer value) {
            addCriterion("fans_count <", value, "fansCount");
            return (Criteria) this;
        }

        public Criteria andFansCountLessThanOrEqualTo(Integer value) {
            addCriterion("fans_count <=", value, "fansCount");
            return (Criteria) this;
        }

        public Criteria andFansCountIn(List<Integer> values) {
            addCriterion("fans_count in", values, "fansCount");
            return (Criteria) this;
        }

        public Criteria andFansCountNotIn(List<Integer> values) {
            addCriterion("fans_count not in", values, "fansCount");
            return (Criteria) this;
        }

        public Criteria andFansCountBetween(Integer value1, Integer value2) {
            addCriterion("fans_count between", value1, value2, "fansCount");
            return (Criteria) this;
        }

        public Criteria andFansCountNotBetween(Integer value1, Integer value2) {
            addCriterion("fans_count not between", value1, value2, "fansCount");
            return (Criteria) this;
        }

        public Criteria andCollectProductCountIsNull() {
            addCriterion("collect_product_count is null");
            return (Criteria) this;
        }

        public Criteria andCollectProductCountIsNotNull() {
            addCriterion("collect_product_count is not null");
            return (Criteria) this;
        }

        public Criteria andCollectProductCountEqualTo(Integer value) {
            addCriterion("collect_product_count =", value, "collectProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectProductCountNotEqualTo(Integer value) {
            addCriterion("collect_product_count <>", value, "collectProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectProductCountGreaterThan(Integer value) {
            addCriterion("collect_product_count >", value, "collectProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectProductCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("collect_product_count >=", value, "collectProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectProductCountLessThan(Integer value) {
            addCriterion("collect_product_count <", value, "collectProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectProductCountLessThanOrEqualTo(Integer value) {
            addCriterion("collect_product_count <=", value, "collectProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectProductCountIn(List<Integer> values) {
            addCriterion("collect_product_count in", values, "collectProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectProductCountNotIn(List<Integer> values) {
            addCriterion("collect_product_count not in", values, "collectProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectProductCountBetween(Integer value1, Integer value2) {
            addCriterion("collect_product_count between", value1, value2, "collectProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectProductCountNotBetween(Integer value1, Integer value2) {
            addCriterion("collect_product_count not between", value1, value2, "collectProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectSubjectCountIsNull() {
            addCriterion("collect_subject_count is null");
            return (Criteria) this;
        }

        public Criteria andCollectSubjectCountIsNotNull() {
            addCriterion("collect_subject_count is not null");
            return (Criteria) this;
        }

        public Criteria andCollectSubjectCountEqualTo(Integer value) {
            addCriterion("collect_subject_count =", value, "collectSubjectCount");
            return (Criteria) this;
        }

        public Criteria andCollectSubjectCountNotEqualTo(Integer value) {
            addCriterion("collect_subject_count <>", value, "collectSubjectCount");
            return (Criteria) this;
        }

        public Criteria andCollectSubjectCountGreaterThan(Integer value) {
            addCriterion("collect_subject_count >", value, "collectSubjectCount");
            return (Criteria) this;
        }

        public Criteria andCollectSubjectCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("collect_subject_count >=", value, "collectSubjectCount");
            return (Criteria) this;
        }

        public Criteria andCollectSubjectCountLessThan(Integer value) {
            addCriterion("collect_subject_count <", value, "collectSubjectCount");
            return (Criteria) this;
        }

        public Criteria andCollectSubjectCountLessThanOrEqualTo(Integer value) {
            addCriterion("collect_subject_count <=", value, "collectSubjectCount");
            return (Criteria) this;
        }

        public Criteria andCollectSubjectCountIn(List<Integer> values) {
            addCriterion("collect_subject_count in", values, "collectSubjectCount");
            return (Criteria) this;
        }

        public Criteria andCollectSubjectCountNotIn(List<Integer> values) {
            addCriterion("collect_subject_count not in", values, "collectSubjectCount");
            return (Criteria) this;
        }

        public Criteria andCollectSubjectCountBetween(Integer value1, Integer value2) {
            addCriterion("collect_subject_count between", value1, value2, "collectSubjectCount");
            return (Criteria) this;
        }

        public Criteria andCollectSubjectCountNotBetween(Integer value1, Integer value2) {
            addCriterion("collect_subject_count not between", value1, value2, "collectSubjectCount");
            return (Criteria) this;
        }

        public Criteria andCollectTopicCountIsNull() {
            addCriterion("collect_topic_count is null");
            return (Criteria) this;
        }

        public Criteria andCollectTopicCountIsNotNull() {
            addCriterion("collect_topic_count is not null");
            return (Criteria) this;
        }

        public Criteria andCollectTopicCountEqualTo(Integer value) {
            addCriterion("collect_topic_count =", value, "collectTopicCount");
            return (Criteria) this;
        }

        public Criteria andCollectTopicCountNotEqualTo(Integer value) {
            addCriterion("collect_topic_count <>", value, "collectTopicCount");
            return (Criteria) this;
        }

        public Criteria andCollectTopicCountGreaterThan(Integer value) {
            addCriterion("collect_topic_count >", value, "collectTopicCount");
            return (Criteria) this;
        }

        public Criteria andCollectTopicCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("collect_topic_count >=", value, "collectTopicCount");
            return (Criteria) this;
        }

        public Criteria andCollectTopicCountLessThan(Integer value) {
            addCriterion("collect_topic_count <", value, "collectTopicCount");
            return (Criteria) this;
        }

        public Criteria andCollectTopicCountLessThanOrEqualTo(Integer value) {
            addCriterion("collect_topic_count <=", value, "collectTopicCount");
            return (Criteria) this;
        }

        public Criteria andCollectTopicCountIn(List<Integer> values) {
            addCriterion("collect_topic_count in", values, "collectTopicCount");
            return (Criteria) this;
        }

        public Criteria andCollectTopicCountNotIn(List<Integer> values) {
            addCriterion("collect_topic_count not in", values, "collectTopicCount");
            return (Criteria) this;
        }

        public Criteria andCollectTopicCountBetween(Integer value1, Integer value2) {
            addCriterion("collect_topic_count between", value1, value2, "collectTopicCount");
            return (Criteria) this;
        }

        public Criteria andCollectTopicCountNotBetween(Integer value1, Integer value2) {
            addCriterion("collect_topic_count not between", value1, value2, "collectTopicCount");
            return (Criteria) this;
        }

        public Criteria andCollectCommentCountIsNull() {
            addCriterion("collect_comment_count is null");
            return (Criteria) this;
        }

        public Criteria andCollectCommentCountIsNotNull() {
            addCriterion("collect_comment_count is not null");
            return (Criteria) this;
        }

        public Criteria andCollectCommentCountEqualTo(Integer value) {
            addCriterion("collect_comment_count =", value, "collectCommentCount");
            return (Criteria) this;
        }

        public Criteria andCollectCommentCountNotEqualTo(Integer value) {
            addCriterion("collect_comment_count <>", value, "collectCommentCount");
            return (Criteria) this;
        }

        public Criteria andCollectCommentCountGreaterThan(Integer value) {
            addCriterion("collect_comment_count >", value, "collectCommentCount");
            return (Criteria) this;
        }

        public Criteria andCollectCommentCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("collect_comment_count >=", value, "collectCommentCount");
            return (Criteria) this;
        }

        public Criteria andCollectCommentCountLessThan(Integer value) {
            addCriterion("collect_comment_count <", value, "collectCommentCount");
            return (Criteria) this;
        }

        public Criteria andCollectCommentCountLessThanOrEqualTo(Integer value) {
            addCriterion("collect_comment_count <=", value, "collectCommentCount");
            return (Criteria) this;
        }

        public Criteria andCollectCommentCountIn(List<Integer> values) {
            addCriterion("collect_comment_count in", values, "collectCommentCount");
            return (Criteria) this;
        }

        public Criteria andCollectCommentCountNotIn(List<Integer> values) {
            addCriterion("collect_comment_count not in", values, "collectCommentCount");
            return (Criteria) this;
        }

        public Criteria andCollectCommentCountBetween(Integer value1, Integer value2) {
            addCriterion("collect_comment_count between", value1, value2, "collectCommentCount");
            return (Criteria) this;
        }

        public Criteria andCollectCommentCountNotBetween(Integer value1, Integer value2) {
            addCriterion("collect_comment_count not between", value1, value2, "collectCommentCount");
            return (Criteria) this;
        }

        public Criteria andInviteFriendCountIsNull() {
            addCriterion("invite_friend_count is null");
            return (Criteria) this;
        }

        public Criteria andInviteFriendCountIsNotNull() {
            addCriterion("invite_friend_count is not null");
            return (Criteria) this;
        }

        public Criteria andInviteFriendCountEqualTo(Integer value) {
            addCriterion("invite_friend_count =", value, "inviteFriendCount");
            return (Criteria) this;
        }

        public Criteria andInviteFriendCountNotEqualTo(Integer value) {
            addCriterion("invite_friend_count <>", value, "inviteFriendCount");
            return (Criteria) this;
        }

        public Criteria andInviteFriendCountGreaterThan(Integer value) {
            addCriterion("invite_friend_count >", value, "inviteFriendCount");
            return (Criteria) this;
        }

        public Criteria andInviteFriendCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("invite_friend_count >=", value, "inviteFriendCount");
            return (Criteria) this;
        }

        public Criteria andInviteFriendCountLessThan(Integer value) {
            addCriterion("invite_friend_count <", value, "inviteFriendCount");
            return (Criteria) this;
        }

        public Criteria andInviteFriendCountLessThanOrEqualTo(Integer value) {
            addCriterion("invite_friend_count <=", value, "inviteFriendCount");
            return (Criteria) this;
        }

        public Criteria andInviteFriendCountIn(List<Integer> values) {
            addCriterion("invite_friend_count in", values, "inviteFriendCount");
            return (Criteria) this;
        }

        public Criteria andInviteFriendCountNotIn(List<Integer> values) {
            addCriterion("invite_friend_count not in", values, "inviteFriendCount");
            return (Criteria) this;
        }

        public Criteria andInviteFriendCountBetween(Integer value1, Integer value2) {
            addCriterion("invite_friend_count between", value1, value2, "inviteFriendCount");
            return (Criteria) this;
        }

        public Criteria andInviteFriendCountNotBetween(Integer value1, Integer value2) {
            addCriterion("invite_friend_count not between", value1, value2, "inviteFriendCount");
            return (Criteria) this;
        }

        public Criteria andRecentOrderTimeIsNull() {
            addCriterion("recent_order_time is null");
            return (Criteria) this;
        }

        public Criteria andRecentOrderTimeIsNotNull() {
            addCriterion("recent_order_time is not null");
            return (Criteria) this;
        }

        public Criteria andRecentOrderTimeEqualTo(Date value) {
            addCriterion("recent_order_time =", value, "recentOrderTime");
            return (Criteria) this;
        }

        public Criteria andRecentOrderTimeNotEqualTo(Date value) {
            addCriterion("recent_order_time <>", value, "recentOrderTime");
            return (Criteria) this;
        }

        public Criteria andRecentOrderTimeGreaterThan(Date value) {
            addCriterion("recent_order_time >", value, "recentOrderTime");
            return (Criteria) this;
        }

        public Criteria andRecentOrderTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("recent_order_time >=", value, "recentOrderTime");
            return (Criteria) this;
        }

        public Criteria andRecentOrderTimeLessThan(Date value) {
            addCriterion("recent_order_time <", value, "recentOrderTime");
            return (Criteria) this;
        }

        public Criteria andRecentOrderTimeLessThanOrEqualTo(Date value) {
            addCriterion("recent_order_time <=", value, "recentOrderTime");
            return (Criteria) this;
        }

        public Criteria andRecentOrderTimeIn(List<Date> values) {
            addCriterion("recent_order_time in", values, "recentOrderTime");
            return (Criteria) this;
        }

        public Criteria andRecentOrderTimeNotIn(List<Date> values) {
            addCriterion("recent_order_time not in", values, "recentOrderTime");
            return (Criteria) this;
        }

        public Criteria andRecentOrderTimeBetween(Date value1, Date value2) {
            addCriterion("recent_order_time between", value1, value2, "recentOrderTime");
            return (Criteria) this;
        }

        public Criteria andRecentOrderTimeNotBetween(Date value1, Date value2) {
            addCriterion("recent_order_time not between", value1, value2, "recentOrderTime");
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