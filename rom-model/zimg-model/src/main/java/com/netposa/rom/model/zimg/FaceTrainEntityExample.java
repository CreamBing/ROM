package com.netposa.rom.model.zimg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FaceTrainEntityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FaceTrainEntityExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMd5IsNull() {
            addCriterion("md5 is null");
            return (Criteria) this;
        }

        public Criteria andMd5IsNotNull() {
            addCriterion("md5 is not null");
            return (Criteria) this;
        }

        public Criteria andMd5EqualTo(String value) {
            addCriterion("md5 =", value, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5NotEqualTo(String value) {
            addCriterion("md5 <>", value, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5GreaterThan(String value) {
            addCriterion("md5 >", value, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5GreaterThanOrEqualTo(String value) {
            addCriterion("md5 >=", value, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5LessThan(String value) {
            addCriterion("md5 <", value, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5LessThanOrEqualTo(String value) {
            addCriterion("md5 <=", value, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5Like(String value) {
            addCriterion("md5 like", value, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5NotLike(String value) {
            addCriterion("md5 not like", value, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5In(List<String> values) {
            addCriterion("md5 in", values, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5NotIn(List<String> values) {
            addCriterion("md5 not in", values, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5Between(String value1, String value2) {
            addCriterion("md5 between", value1, value2, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5NotBetween(String value1, String value2) {
            addCriterion("md5 not between", value1, value2, "md5");
            return (Criteria) this;
        }

        public Criteria andXIsNull() {
            addCriterion("x is null");
            return (Criteria) this;
        }

        public Criteria andXIsNotNull() {
            addCriterion("x is not null");
            return (Criteria) this;
        }

        public Criteria andXEqualTo(Integer value) {
            addCriterion("x =", value, "x");
            return (Criteria) this;
        }

        public Criteria andXNotEqualTo(Integer value) {
            addCriterion("x <>", value, "x");
            return (Criteria) this;
        }

        public Criteria andXGreaterThan(Integer value) {
            addCriterion("x >", value, "x");
            return (Criteria) this;
        }

        public Criteria andXGreaterThanOrEqualTo(Integer value) {
            addCriterion("x >=", value, "x");
            return (Criteria) this;
        }

        public Criteria andXLessThan(Integer value) {
            addCriterion("x <", value, "x");
            return (Criteria) this;
        }

        public Criteria andXLessThanOrEqualTo(Integer value) {
            addCriterion("x <=", value, "x");
            return (Criteria) this;
        }

        public Criteria andXIn(List<Integer> values) {
            addCriterion("x in", values, "x");
            return (Criteria) this;
        }

        public Criteria andXNotIn(List<Integer> values) {
            addCriterion("x not in", values, "x");
            return (Criteria) this;
        }

        public Criteria andXBetween(Integer value1, Integer value2) {
            addCriterion("x between", value1, value2, "x");
            return (Criteria) this;
        }

        public Criteria andXNotBetween(Integer value1, Integer value2) {
            addCriterion("x not between", value1, value2, "x");
            return (Criteria) this;
        }

        public Criteria andYIsNull() {
            addCriterion("y is null");
            return (Criteria) this;
        }

        public Criteria andYIsNotNull() {
            addCriterion("y is not null");
            return (Criteria) this;
        }

        public Criteria andYEqualTo(Integer value) {
            addCriterion("y =", value, "y");
            return (Criteria) this;
        }

        public Criteria andYNotEqualTo(Integer value) {
            addCriterion("y <>", value, "y");
            return (Criteria) this;
        }

        public Criteria andYGreaterThan(Integer value) {
            addCriterion("y >", value, "y");
            return (Criteria) this;
        }

        public Criteria andYGreaterThanOrEqualTo(Integer value) {
            addCriterion("y >=", value, "y");
            return (Criteria) this;
        }

        public Criteria andYLessThan(Integer value) {
            addCriterion("y <", value, "y");
            return (Criteria) this;
        }

        public Criteria andYLessThanOrEqualTo(Integer value) {
            addCriterion("y <=", value, "y");
            return (Criteria) this;
        }

        public Criteria andYIn(List<Integer> values) {
            addCriterion("y in", values, "y");
            return (Criteria) this;
        }

        public Criteria andYNotIn(List<Integer> values) {
            addCriterion("y not in", values, "y");
            return (Criteria) this;
        }

        public Criteria andYBetween(Integer value1, Integer value2) {
            addCriterion("y between", value1, value2, "y");
            return (Criteria) this;
        }

        public Criteria andYNotBetween(Integer value1, Integer value2) {
            addCriterion("y not between", value1, value2, "y");
            return (Criteria) this;
        }

        public Criteria andWIsNull() {
            addCriterion("w is null");
            return (Criteria) this;
        }

        public Criteria andWIsNotNull() {
            addCriterion("w is not null");
            return (Criteria) this;
        }

        public Criteria andWEqualTo(Integer value) {
            addCriterion("w =", value, "w");
            return (Criteria) this;
        }

        public Criteria andWNotEqualTo(Integer value) {
            addCriterion("w <>", value, "w");
            return (Criteria) this;
        }

        public Criteria andWGreaterThan(Integer value) {
            addCriterion("w >", value, "w");
            return (Criteria) this;
        }

        public Criteria andWGreaterThanOrEqualTo(Integer value) {
            addCriterion("w >=", value, "w");
            return (Criteria) this;
        }

        public Criteria andWLessThan(Integer value) {
            addCriterion("w <", value, "w");
            return (Criteria) this;
        }

        public Criteria andWLessThanOrEqualTo(Integer value) {
            addCriterion("w <=", value, "w");
            return (Criteria) this;
        }

        public Criteria andWIn(List<Integer> values) {
            addCriterion("w in", values, "w");
            return (Criteria) this;
        }

        public Criteria andWNotIn(List<Integer> values) {
            addCriterion("w not in", values, "w");
            return (Criteria) this;
        }

        public Criteria andWBetween(Integer value1, Integer value2) {
            addCriterion("w between", value1, value2, "w");
            return (Criteria) this;
        }

        public Criteria andWNotBetween(Integer value1, Integer value2) {
            addCriterion("w not between", value1, value2, "w");
            return (Criteria) this;
        }

        public Criteria andHIsNull() {
            addCriterion("h is null");
            return (Criteria) this;
        }

        public Criteria andHIsNotNull() {
            addCriterion("h is not null");
            return (Criteria) this;
        }

        public Criteria andHEqualTo(Integer value) {
            addCriterion("h =", value, "h");
            return (Criteria) this;
        }

        public Criteria andHNotEqualTo(Integer value) {
            addCriterion("h <>", value, "h");
            return (Criteria) this;
        }

        public Criteria andHGreaterThan(Integer value) {
            addCriterion("h >", value, "h");
            return (Criteria) this;
        }

        public Criteria andHGreaterThanOrEqualTo(Integer value) {
            addCriterion("h >=", value, "h");
            return (Criteria) this;
        }

        public Criteria andHLessThan(Integer value) {
            addCriterion("h <", value, "h");
            return (Criteria) this;
        }

        public Criteria andHLessThanOrEqualTo(Integer value) {
            addCriterion("h <=", value, "h");
            return (Criteria) this;
        }

        public Criteria andHIn(List<Integer> values) {
            addCriterion("h in", values, "h");
            return (Criteria) this;
        }

        public Criteria andHNotIn(List<Integer> values) {
            addCriterion("h not in", values, "h");
            return (Criteria) this;
        }

        public Criteria andHBetween(Integer value1, Integer value2) {
            addCriterion("h between", value1, value2, "h");
            return (Criteria) this;
        }

        public Criteria andHNotBetween(Integer value1, Integer value2) {
            addCriterion("h not between", value1, value2, "h");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andHasTrainedIsNull() {
            addCriterion("has_trained is null");
            return (Criteria) this;
        }

        public Criteria andHasTrainedIsNotNull() {
            addCriterion("has_trained is not null");
            return (Criteria) this;
        }

        public Criteria andHasTrainedEqualTo(Boolean value) {
            addCriterion("has_trained =", value, "hasTrained");
            return (Criteria) this;
        }

        public Criteria andHasTrainedNotEqualTo(Boolean value) {
            addCriterion("has_trained <>", value, "hasTrained");
            return (Criteria) this;
        }

        public Criteria andHasTrainedGreaterThan(Boolean value) {
            addCriterion("has_trained >", value, "hasTrained");
            return (Criteria) this;
        }

        public Criteria andHasTrainedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("has_trained >=", value, "hasTrained");
            return (Criteria) this;
        }

        public Criteria andHasTrainedLessThan(Boolean value) {
            addCriterion("has_trained <", value, "hasTrained");
            return (Criteria) this;
        }

        public Criteria andHasTrainedLessThanOrEqualTo(Boolean value) {
            addCriterion("has_trained <=", value, "hasTrained");
            return (Criteria) this;
        }

        public Criteria andHasTrainedIn(List<Boolean> values) {
            addCriterion("has_trained in", values, "hasTrained");
            return (Criteria) this;
        }

        public Criteria andHasTrainedNotIn(List<Boolean> values) {
            addCriterion("has_trained not in", values, "hasTrained");
            return (Criteria) this;
        }

        public Criteria andHasTrainedBetween(Boolean value1, Boolean value2) {
            addCriterion("has_trained between", value1, value2, "hasTrained");
            return (Criteria) this;
        }

        public Criteria andHasTrainedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("has_trained not between", value1, value2, "hasTrained");
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

        public Criteria andLabelIsNull() {
            addCriterion("label is null");
            return (Criteria) this;
        }

        public Criteria andLabelIsNotNull() {
            addCriterion("label is not null");
            return (Criteria) this;
        }

        public Criteria andLabelEqualTo(Integer value) {
            addCriterion("label =", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotEqualTo(Integer value) {
            addCriterion("label <>", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThan(Integer value) {
            addCriterion("label >", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThanOrEqualTo(Integer value) {
            addCriterion("label >=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThan(Integer value) {
            addCriterion("label <", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThanOrEqualTo(Integer value) {
            addCriterion("label <=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelIn(List<Integer> values) {
            addCriterion("label in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotIn(List<Integer> values) {
            addCriterion("label not in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelBetween(Integer value1, Integer value2) {
            addCriterion("label between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotBetween(Integer value1, Integer value2) {
            addCriterion("label not between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andTrainTimeIsNull() {
            addCriterion("train_time is null");
            return (Criteria) this;
        }

        public Criteria andTrainTimeIsNotNull() {
            addCriterion("train_time is not null");
            return (Criteria) this;
        }

        public Criteria andTrainTimeEqualTo(Date value) {
            addCriterion("train_time =", value, "trainTime");
            return (Criteria) this;
        }

        public Criteria andTrainTimeNotEqualTo(Date value) {
            addCriterion("train_time <>", value, "trainTime");
            return (Criteria) this;
        }

        public Criteria andTrainTimeGreaterThan(Date value) {
            addCriterion("train_time >", value, "trainTime");
            return (Criteria) this;
        }

        public Criteria andTrainTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("train_time >=", value, "trainTime");
            return (Criteria) this;
        }

        public Criteria andTrainTimeLessThan(Date value) {
            addCriterion("train_time <", value, "trainTime");
            return (Criteria) this;
        }

        public Criteria andTrainTimeLessThanOrEqualTo(Date value) {
            addCriterion("train_time <=", value, "trainTime");
            return (Criteria) this;
        }

        public Criteria andTrainTimeIn(List<Date> values) {
            addCriterion("train_time in", values, "trainTime");
            return (Criteria) this;
        }

        public Criteria andTrainTimeNotIn(List<Date> values) {
            addCriterion("train_time not in", values, "trainTime");
            return (Criteria) this;
        }

        public Criteria andTrainTimeBetween(Date value1, Date value2) {
            addCriterion("train_time between", value1, value2, "trainTime");
            return (Criteria) this;
        }

        public Criteria andTrainTimeNotBetween(Date value1, Date value2) {
            addCriterion("train_time not between", value1, value2, "trainTime");
            return (Criteria) this;
        }

        public Criteria andForecastNameIsNull() {
            addCriterion("forecast_name is null");
            return (Criteria) this;
        }

        public Criteria andForecastNameIsNotNull() {
            addCriterion("forecast_name is not null");
            return (Criteria) this;
        }

        public Criteria andForecastNameEqualTo(String value) {
            addCriterion("forecast_name =", value, "forecastName");
            return (Criteria) this;
        }

        public Criteria andForecastNameNotEqualTo(String value) {
            addCriterion("forecast_name <>", value, "forecastName");
            return (Criteria) this;
        }

        public Criteria andForecastNameGreaterThan(String value) {
            addCriterion("forecast_name >", value, "forecastName");
            return (Criteria) this;
        }

        public Criteria andForecastNameGreaterThanOrEqualTo(String value) {
            addCriterion("forecast_name >=", value, "forecastName");
            return (Criteria) this;
        }

        public Criteria andForecastNameLessThan(String value) {
            addCriterion("forecast_name <", value, "forecastName");
            return (Criteria) this;
        }

        public Criteria andForecastNameLessThanOrEqualTo(String value) {
            addCriterion("forecast_name <=", value, "forecastName");
            return (Criteria) this;
        }

        public Criteria andForecastNameLike(String value) {
            addCriterion("forecast_name like", value, "forecastName");
            return (Criteria) this;
        }

        public Criteria andForecastNameNotLike(String value) {
            addCriterion("forecast_name not like", value, "forecastName");
            return (Criteria) this;
        }

        public Criteria andForecastNameIn(List<String> values) {
            addCriterion("forecast_name in", values, "forecastName");
            return (Criteria) this;
        }

        public Criteria andForecastNameNotIn(List<String> values) {
            addCriterion("forecast_name not in", values, "forecastName");
            return (Criteria) this;
        }

        public Criteria andForecastNameBetween(String value1, String value2) {
            addCriterion("forecast_name between", value1, value2, "forecastName");
            return (Criteria) this;
        }

        public Criteria andForecastNameNotBetween(String value1, String value2) {
            addCriterion("forecast_name not between", value1, value2, "forecastName");
            return (Criteria) this;
        }

        public Criteria andForecastTimeIsNull() {
            addCriterion("forecast_time is null");
            return (Criteria) this;
        }

        public Criteria andForecastTimeIsNotNull() {
            addCriterion("forecast_time is not null");
            return (Criteria) this;
        }

        public Criteria andForecastTimeEqualTo(Date value) {
            addCriterion("forecast_time =", value, "forecastTime");
            return (Criteria) this;
        }

        public Criteria andForecastTimeNotEqualTo(Date value) {
            addCriterion("forecast_time <>", value, "forecastTime");
            return (Criteria) this;
        }

        public Criteria andForecastTimeGreaterThan(Date value) {
            addCriterion("forecast_time >", value, "forecastTime");
            return (Criteria) this;
        }

        public Criteria andForecastTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("forecast_time >=", value, "forecastTime");
            return (Criteria) this;
        }

        public Criteria andForecastTimeLessThan(Date value) {
            addCriterion("forecast_time <", value, "forecastTime");
            return (Criteria) this;
        }

        public Criteria andForecastTimeLessThanOrEqualTo(Date value) {
            addCriterion("forecast_time <=", value, "forecastTime");
            return (Criteria) this;
        }

        public Criteria andForecastTimeIn(List<Date> values) {
            addCriterion("forecast_time in", values, "forecastTime");
            return (Criteria) this;
        }

        public Criteria andForecastTimeNotIn(List<Date> values) {
            addCriterion("forecast_time not in", values, "forecastTime");
            return (Criteria) this;
        }

        public Criteria andForecastTimeBetween(Date value1, Date value2) {
            addCriterion("forecast_time between", value1, value2, "forecastTime");
            return (Criteria) this;
        }

        public Criteria andForecastTimeNotBetween(Date value1, Date value2) {
            addCriterion("forecast_time not between", value1, value2, "forecastTime");
            return (Criteria) this;
        }

        public Criteria andForecastResultIsNull() {
            addCriterion("forecast_result is null");
            return (Criteria) this;
        }

        public Criteria andForecastResultIsNotNull() {
            addCriterion("forecast_result is not null");
            return (Criteria) this;
        }

        public Criteria andForecastResultEqualTo(Boolean value) {
            addCriterion("forecast_result =", value, "forecastResult");
            return (Criteria) this;
        }

        public Criteria andForecastResultNotEqualTo(Boolean value) {
            addCriterion("forecast_result <>", value, "forecastResult");
            return (Criteria) this;
        }

        public Criteria andForecastResultGreaterThan(Boolean value) {
            addCriterion("forecast_result >", value, "forecastResult");
            return (Criteria) this;
        }

        public Criteria andForecastResultGreaterThanOrEqualTo(Boolean value) {
            addCriterion("forecast_result >=", value, "forecastResult");
            return (Criteria) this;
        }

        public Criteria andForecastResultLessThan(Boolean value) {
            addCriterion("forecast_result <", value, "forecastResult");
            return (Criteria) this;
        }

        public Criteria andForecastResultLessThanOrEqualTo(Boolean value) {
            addCriterion("forecast_result <=", value, "forecastResult");
            return (Criteria) this;
        }

        public Criteria andForecastResultIn(List<Boolean> values) {
            addCriterion("forecast_result in", values, "forecastResult");
            return (Criteria) this;
        }

        public Criteria andForecastResultNotIn(List<Boolean> values) {
            addCriterion("forecast_result not in", values, "forecastResult");
            return (Criteria) this;
        }

        public Criteria andForecastResultBetween(Boolean value1, Boolean value2) {
            addCriterion("forecast_result between", value1, value2, "forecastResult");
            return (Criteria) this;
        }

        public Criteria andForecastResultNotBetween(Boolean value1, Boolean value2) {
            addCriterion("forecast_result not between", value1, value2, "forecastResult");
            return (Criteria) this;
        }

        public Criteria andHasDeletedIsNull() {
            addCriterion("has_deleted is null");
            return (Criteria) this;
        }

        public Criteria andHasDeletedIsNotNull() {
            addCriterion("has_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andHasDeletedEqualTo(Boolean value) {
            addCriterion("has_deleted =", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedNotEqualTo(Boolean value) {
            addCriterion("has_deleted <>", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedGreaterThan(Boolean value) {
            addCriterion("has_deleted >", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("has_deleted >=", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedLessThan(Boolean value) {
            addCriterion("has_deleted <", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("has_deleted <=", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedIn(List<Boolean> values) {
            addCriterion("has_deleted in", values, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedNotIn(List<Boolean> values) {
            addCriterion("has_deleted not in", values, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("has_deleted between", value1, value2, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("has_deleted not between", value1, value2, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasRecognizedIsNull() {
            addCriterion("has_recognized is null");
            return (Criteria) this;
        }

        public Criteria andHasRecognizedIsNotNull() {
            addCriterion("has_recognized is not null");
            return (Criteria) this;
        }

        public Criteria andHasRecognizedEqualTo(Boolean value) {
            addCriterion("has_recognized =", value, "hasRecognized");
            return (Criteria) this;
        }

        public Criteria andHasRecognizedNotEqualTo(Boolean value) {
            addCriterion("has_recognized <>", value, "hasRecognized");
            return (Criteria) this;
        }

        public Criteria andHasRecognizedGreaterThan(Boolean value) {
            addCriterion("has_recognized >", value, "hasRecognized");
            return (Criteria) this;
        }

        public Criteria andHasRecognizedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("has_recognized >=", value, "hasRecognized");
            return (Criteria) this;
        }

        public Criteria andHasRecognizedLessThan(Boolean value) {
            addCriterion("has_recognized <", value, "hasRecognized");
            return (Criteria) this;
        }

        public Criteria andHasRecognizedLessThanOrEqualTo(Boolean value) {
            addCriterion("has_recognized <=", value, "hasRecognized");
            return (Criteria) this;
        }

        public Criteria andHasRecognizedIn(List<Boolean> values) {
            addCriterion("has_recognized in", values, "hasRecognized");
            return (Criteria) this;
        }

        public Criteria andHasRecognizedNotIn(List<Boolean> values) {
            addCriterion("has_recognized not in", values, "hasRecognized");
            return (Criteria) this;
        }

        public Criteria andHasRecognizedBetween(Boolean value1, Boolean value2) {
            addCriterion("has_recognized between", value1, value2, "hasRecognized");
            return (Criteria) this;
        }

        public Criteria andHasRecognizedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("has_recognized not between", value1, value2, "hasRecognized");
            return (Criteria) this;
        }

        public Criteria andRecognizeTimeIsNull() {
            addCriterion("recognize_time is null");
            return (Criteria) this;
        }

        public Criteria andRecognizeTimeIsNotNull() {
            addCriterion("recognize_time is not null");
            return (Criteria) this;
        }

        public Criteria andRecognizeTimeEqualTo(Date value) {
            addCriterion("recognize_time =", value, "recognizeTime");
            return (Criteria) this;
        }

        public Criteria andRecognizeTimeNotEqualTo(Date value) {
            addCriterion("recognize_time <>", value, "recognizeTime");
            return (Criteria) this;
        }

        public Criteria andRecognizeTimeGreaterThan(Date value) {
            addCriterion("recognize_time >", value, "recognizeTime");
            return (Criteria) this;
        }

        public Criteria andRecognizeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("recognize_time >=", value, "recognizeTime");
            return (Criteria) this;
        }

        public Criteria andRecognizeTimeLessThan(Date value) {
            addCriterion("recognize_time <", value, "recognizeTime");
            return (Criteria) this;
        }

        public Criteria andRecognizeTimeLessThanOrEqualTo(Date value) {
            addCriterion("recognize_time <=", value, "recognizeTime");
            return (Criteria) this;
        }

        public Criteria andRecognizeTimeIn(List<Date> values) {
            addCriterion("recognize_time in", values, "recognizeTime");
            return (Criteria) this;
        }

        public Criteria andRecognizeTimeNotIn(List<Date> values) {
            addCriterion("recognize_time not in", values, "recognizeTime");
            return (Criteria) this;
        }

        public Criteria andRecognizeTimeBetween(Date value1, Date value2) {
            addCriterion("recognize_time between", value1, value2, "recognizeTime");
            return (Criteria) this;
        }

        public Criteria andRecognizeTimeNotBetween(Date value1, Date value2) {
            addCriterion("recognize_time not between", value1, value2, "recognizeTime");
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