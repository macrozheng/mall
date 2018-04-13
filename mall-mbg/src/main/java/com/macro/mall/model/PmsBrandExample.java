package com.macro.mall.model;

import java.util.ArrayList;
import java.util.List;

public class PmsBrandExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PmsBrandExample() {
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

        public Criteria andFirstLetterIsNull() {
            addCriterion("first_letter is null");
            return (Criteria) this;
        }

        public Criteria andFirstLetterIsNotNull() {
            addCriterion("first_letter is not null");
            return (Criteria) this;
        }

        public Criteria andFirstLetterEqualTo(String value) {
            addCriterion("first_letter =", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterNotEqualTo(String value) {
            addCriterion("first_letter <>", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterGreaterThan(String value) {
            addCriterion("first_letter >", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterGreaterThanOrEqualTo(String value) {
            addCriterion("first_letter >=", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterLessThan(String value) {
            addCriterion("first_letter <", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterLessThanOrEqualTo(String value) {
            addCriterion("first_letter <=", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterLike(String value) {
            addCriterion("first_letter like", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterNotLike(String value) {
            addCriterion("first_letter not like", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterIn(List<String> values) {
            addCriterion("first_letter in", values, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterNotIn(List<String> values) {
            addCriterion("first_letter not in", values, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterBetween(String value1, String value2) {
            addCriterion("first_letter between", value1, value2, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterNotBetween(String value1, String value2) {
            addCriterion("first_letter not between", value1, value2, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andFactoryStatusIsNull() {
            addCriterion("factory_status is null");
            return (Criteria) this;
        }

        public Criteria andFactoryStatusIsNotNull() {
            addCriterion("factory_status is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryStatusEqualTo(Integer value) {
            addCriterion("factory_status =", value, "factoryStatus");
            return (Criteria) this;
        }

        public Criteria andFactoryStatusNotEqualTo(Integer value) {
            addCriterion("factory_status <>", value, "factoryStatus");
            return (Criteria) this;
        }

        public Criteria andFactoryStatusGreaterThan(Integer value) {
            addCriterion("factory_status >", value, "factoryStatus");
            return (Criteria) this;
        }

        public Criteria andFactoryStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("factory_status >=", value, "factoryStatus");
            return (Criteria) this;
        }

        public Criteria andFactoryStatusLessThan(Integer value) {
            addCriterion("factory_status <", value, "factoryStatus");
            return (Criteria) this;
        }

        public Criteria andFactoryStatusLessThanOrEqualTo(Integer value) {
            addCriterion("factory_status <=", value, "factoryStatus");
            return (Criteria) this;
        }

        public Criteria andFactoryStatusIn(List<Integer> values) {
            addCriterion("factory_status in", values, "factoryStatus");
            return (Criteria) this;
        }

        public Criteria andFactoryStatusNotIn(List<Integer> values) {
            addCriterion("factory_status not in", values, "factoryStatus");
            return (Criteria) this;
        }

        public Criteria andFactoryStatusBetween(Integer value1, Integer value2) {
            addCriterion("factory_status between", value1, value2, "factoryStatus");
            return (Criteria) this;
        }

        public Criteria andFactoryStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("factory_status not between", value1, value2, "factoryStatus");
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

        public Criteria andProductCountIsNull() {
            addCriterion("product_count is null");
            return (Criteria) this;
        }

        public Criteria andProductCountIsNotNull() {
            addCriterion("product_count is not null");
            return (Criteria) this;
        }

        public Criteria andProductCountEqualTo(Integer value) {
            addCriterion("product_count =", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotEqualTo(Integer value) {
            addCriterion("product_count <>", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountGreaterThan(Integer value) {
            addCriterion("product_count >", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_count >=", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountLessThan(Integer value) {
            addCriterion("product_count <", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountLessThanOrEqualTo(Integer value) {
            addCriterion("product_count <=", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountIn(List<Integer> values) {
            addCriterion("product_count in", values, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotIn(List<Integer> values) {
            addCriterion("product_count not in", values, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountBetween(Integer value1, Integer value2) {
            addCriterion("product_count between", value1, value2, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotBetween(Integer value1, Integer value2) {
            addCriterion("product_count not between", value1, value2, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCommentCountIsNull() {
            addCriterion("product_comment_count is null");
            return (Criteria) this;
        }

        public Criteria andProductCommentCountIsNotNull() {
            addCriterion("product_comment_count is not null");
            return (Criteria) this;
        }

        public Criteria andProductCommentCountEqualTo(Integer value) {
            addCriterion("product_comment_count =", value, "productCommentCount");
            return (Criteria) this;
        }

        public Criteria andProductCommentCountNotEqualTo(Integer value) {
            addCriterion("product_comment_count <>", value, "productCommentCount");
            return (Criteria) this;
        }

        public Criteria andProductCommentCountGreaterThan(Integer value) {
            addCriterion("product_comment_count >", value, "productCommentCount");
            return (Criteria) this;
        }

        public Criteria andProductCommentCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_comment_count >=", value, "productCommentCount");
            return (Criteria) this;
        }

        public Criteria andProductCommentCountLessThan(Integer value) {
            addCriterion("product_comment_count <", value, "productCommentCount");
            return (Criteria) this;
        }

        public Criteria andProductCommentCountLessThanOrEqualTo(Integer value) {
            addCriterion("product_comment_count <=", value, "productCommentCount");
            return (Criteria) this;
        }

        public Criteria andProductCommentCountIn(List<Integer> values) {
            addCriterion("product_comment_count in", values, "productCommentCount");
            return (Criteria) this;
        }

        public Criteria andProductCommentCountNotIn(List<Integer> values) {
            addCriterion("product_comment_count not in", values, "productCommentCount");
            return (Criteria) this;
        }

        public Criteria andProductCommentCountBetween(Integer value1, Integer value2) {
            addCriterion("product_comment_count between", value1, value2, "productCommentCount");
            return (Criteria) this;
        }

        public Criteria andProductCommentCountNotBetween(Integer value1, Integer value2) {
            addCriterion("product_comment_count not between", value1, value2, "productCommentCount");
            return (Criteria) this;
        }

        public Criteria andLogoIsNull() {
            addCriterion("logo is null");
            return (Criteria) this;
        }

        public Criteria andLogoIsNotNull() {
            addCriterion("logo is not null");
            return (Criteria) this;
        }

        public Criteria andLogoEqualTo(String value) {
            addCriterion("logo =", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotEqualTo(String value) {
            addCriterion("logo <>", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThan(String value) {
            addCriterion("logo >", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThanOrEqualTo(String value) {
            addCriterion("logo >=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThan(String value) {
            addCriterion("logo <", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThanOrEqualTo(String value) {
            addCriterion("logo <=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLike(String value) {
            addCriterion("logo like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotLike(String value) {
            addCriterion("logo not like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoIn(List<String> values) {
            addCriterion("logo in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotIn(List<String> values) {
            addCriterion("logo not in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoBetween(String value1, String value2) {
            addCriterion("logo between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotBetween(String value1, String value2) {
            addCriterion("logo not between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andBigPicIsNull() {
            addCriterion("big_pic is null");
            return (Criteria) this;
        }

        public Criteria andBigPicIsNotNull() {
            addCriterion("big_pic is not null");
            return (Criteria) this;
        }

        public Criteria andBigPicEqualTo(String value) {
            addCriterion("big_pic =", value, "bigPic");
            return (Criteria) this;
        }

        public Criteria andBigPicNotEqualTo(String value) {
            addCriterion("big_pic <>", value, "bigPic");
            return (Criteria) this;
        }

        public Criteria andBigPicGreaterThan(String value) {
            addCriterion("big_pic >", value, "bigPic");
            return (Criteria) this;
        }

        public Criteria andBigPicGreaterThanOrEqualTo(String value) {
            addCriterion("big_pic >=", value, "bigPic");
            return (Criteria) this;
        }

        public Criteria andBigPicLessThan(String value) {
            addCriterion("big_pic <", value, "bigPic");
            return (Criteria) this;
        }

        public Criteria andBigPicLessThanOrEqualTo(String value) {
            addCriterion("big_pic <=", value, "bigPic");
            return (Criteria) this;
        }

        public Criteria andBigPicLike(String value) {
            addCriterion("big_pic like", value, "bigPic");
            return (Criteria) this;
        }

        public Criteria andBigPicNotLike(String value) {
            addCriterion("big_pic not like", value, "bigPic");
            return (Criteria) this;
        }

        public Criteria andBigPicIn(List<String> values) {
            addCriterion("big_pic in", values, "bigPic");
            return (Criteria) this;
        }

        public Criteria andBigPicNotIn(List<String> values) {
            addCriterion("big_pic not in", values, "bigPic");
            return (Criteria) this;
        }

        public Criteria andBigPicBetween(String value1, String value2) {
            addCriterion("big_pic between", value1, value2, "bigPic");
            return (Criteria) this;
        }

        public Criteria andBigPicNotBetween(String value1, String value2) {
            addCriterion("big_pic not between", value1, value2, "bigPic");
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