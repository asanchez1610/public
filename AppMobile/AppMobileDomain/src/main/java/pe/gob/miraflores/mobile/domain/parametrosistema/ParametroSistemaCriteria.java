package pe.gob.miraflores.mobile.domain.parametrosistema;

import java.util.ArrayList;
import java.util.List;

public class ParametroSistemaCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PARAMETRO_SISTEMA
     *
     * @mbggenerated Thu Nov 26 11:59:53 COT 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PARAMETRO_SISTEMA
     *
     * @mbggenerated Thu Nov 26 11:59:53 COT 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PARAMETRO_SISTEMA
     *
     * @mbggenerated Thu Nov 26 11:59:53 COT 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARAMETRO_SISTEMA
     *
     * @mbggenerated Thu Nov 26 11:59:53 COT 2015
     */
    public ParametroSistemaCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARAMETRO_SISTEMA
     *
     * @mbggenerated Thu Nov 26 11:59:53 COT 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARAMETRO_SISTEMA
     *
     * @mbggenerated Thu Nov 26 11:59:53 COT 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARAMETRO_SISTEMA
     *
     * @mbggenerated Thu Nov 26 11:59:53 COT 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARAMETRO_SISTEMA
     *
     * @mbggenerated Thu Nov 26 11:59:53 COT 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARAMETRO_SISTEMA
     *
     * @mbggenerated Thu Nov 26 11:59:53 COT 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARAMETRO_SISTEMA
     *
     * @mbggenerated Thu Nov 26 11:59:53 COT 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARAMETRO_SISTEMA
     *
     * @mbggenerated Thu Nov 26 11:59:53 COT 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARAMETRO_SISTEMA
     *
     * @mbggenerated Thu Nov 26 11:59:53 COT 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARAMETRO_SISTEMA
     *
     * @mbggenerated Thu Nov 26 11:59:53 COT 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARAMETRO_SISTEMA
     *
     * @mbggenerated Thu Nov 26 11:59:53 COT 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PARAMETRO_SISTEMA
     *
     * @mbggenerated Thu Nov 26 11:59:53 COT 2015
     */
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

        public Criteria andIdparametrosistemaIsNull() {
            addCriterion("IDPARAMETROSISTEMA is null");
            return (Criteria) this;
        }

        public Criteria andIdparametrosistemaIsNotNull() {
            addCriterion("IDPARAMETROSISTEMA is not null");
            return (Criteria) this;
        }

        public Criteria andIdparametrosistemaEqualTo(String value) {
            addCriterion("IDPARAMETROSISTEMA =", value, "idparametrosistema");
            return (Criteria) this;
        }

        public Criteria andIdparametrosistemaNotEqualTo(String value) {
            addCriterion("IDPARAMETROSISTEMA <>", value, "idparametrosistema");
            return (Criteria) this;
        }

        public Criteria andIdparametrosistemaGreaterThan(String value) {
            addCriterion("IDPARAMETROSISTEMA >", value, "idparametrosistema");
            return (Criteria) this;
        }

        public Criteria andIdparametrosistemaGreaterThanOrEqualTo(String value) {
            addCriterion("IDPARAMETROSISTEMA >=", value, "idparametrosistema");
            return (Criteria) this;
        }

        public Criteria andIdparametrosistemaLessThan(String value) {
            addCriterion("IDPARAMETROSISTEMA <", value, "idparametrosistema");
            return (Criteria) this;
        }

        public Criteria andIdparametrosistemaLessThanOrEqualTo(String value) {
            addCriterion("IDPARAMETROSISTEMA <=", value, "idparametrosistema");
            return (Criteria) this;
        }

        public Criteria andIdparametrosistemaLike(String value) {
            addCriterion("IDPARAMETROSISTEMA like", value, "idparametrosistema");
            return (Criteria) this;
        }

        public Criteria andIdparametrosistemaNotLike(String value) {
            addCriterion("IDPARAMETROSISTEMA not like", value, "idparametrosistema");
            return (Criteria) this;
        }

        public Criteria andIdparametrosistemaIn(List<String> values) {
            addCriterion("IDPARAMETROSISTEMA in", values, "idparametrosistema");
            return (Criteria) this;
        }

        public Criteria andIdparametrosistemaNotIn(List<String> values) {
            addCriterion("IDPARAMETROSISTEMA not in", values, "idparametrosistema");
            return (Criteria) this;
        }

        public Criteria andIdparametrosistemaBetween(String value1, String value2) {
            addCriterion("IDPARAMETROSISTEMA between", value1, value2, "idparametrosistema");
            return (Criteria) this;
        }

        public Criteria andIdparametrosistemaNotBetween(String value1, String value2) {
            addCriterion("IDPARAMETROSISTEMA not between", value1, value2, "idparametrosistema");
            return (Criteria) this;
        }

        public Criteria andValorIsNull() {
            addCriterion("VALOR is null");
            return (Criteria) this;
        }

        public Criteria andValorIsNotNull() {
            addCriterion("VALOR is not null");
            return (Criteria) this;
        }

        public Criteria andValorEqualTo(String value) {
            addCriterion("VALOR =", value, "valor");
            return (Criteria) this;
        }

        public Criteria andValorNotEqualTo(String value) {
            addCriterion("VALOR <>", value, "valor");
            return (Criteria) this;
        }

        public Criteria andValorGreaterThan(String value) {
            addCriterion("VALOR >", value, "valor");
            return (Criteria) this;
        }

        public Criteria andValorGreaterThanOrEqualTo(String value) {
            addCriterion("VALOR >=", value, "valor");
            return (Criteria) this;
        }

        public Criteria andValorLessThan(String value) {
            addCriterion("VALOR <", value, "valor");
            return (Criteria) this;
        }

        public Criteria andValorLessThanOrEqualTo(String value) {
            addCriterion("VALOR <=", value, "valor");
            return (Criteria) this;
        }

        public Criteria andValorLike(String value) {
            addCriterion("VALOR like", value, "valor");
            return (Criteria) this;
        }

        public Criteria andValorNotLike(String value) {
            addCriterion("VALOR not like", value, "valor");
            return (Criteria) this;
        }

        public Criteria andValorIn(List<String> values) {
            addCriterion("VALOR in", values, "valor");
            return (Criteria) this;
        }

        public Criteria andValorNotIn(List<String> values) {
            addCriterion("VALOR not in", values, "valor");
            return (Criteria) this;
        }

        public Criteria andValorBetween(String value1, String value2) {
            addCriterion("VALOR between", value1, value2, "valor");
            return (Criteria) this;
        }

        public Criteria andValorNotBetween(String value1, String value2) {
            addCriterion("VALOR not between", value1, value2, "valor");
            return (Criteria) this;
        }

        public Criteria andCodaplicacionIsNull() {
            addCriterion("CODAPLICACION is null");
            return (Criteria) this;
        }

        public Criteria andCodaplicacionIsNotNull() {
            addCriterion("CODAPLICACION is not null");
            return (Criteria) this;
        }

        public Criteria andCodaplicacionEqualTo(String value) {
            addCriterion("CODAPLICACION =", value, "codaplicacion");
            return (Criteria) this;
        }

        public Criteria andCodaplicacionNotEqualTo(String value) {
            addCriterion("CODAPLICACION <>", value, "codaplicacion");
            return (Criteria) this;
        }

        public Criteria andCodaplicacionGreaterThan(String value) {
            addCriterion("CODAPLICACION >", value, "codaplicacion");
            return (Criteria) this;
        }

        public Criteria andCodaplicacionGreaterThanOrEqualTo(String value) {
            addCriterion("CODAPLICACION >=", value, "codaplicacion");
            return (Criteria) this;
        }

        public Criteria andCodaplicacionLessThan(String value) {
            addCriterion("CODAPLICACION <", value, "codaplicacion");
            return (Criteria) this;
        }

        public Criteria andCodaplicacionLessThanOrEqualTo(String value) {
            addCriterion("CODAPLICACION <=", value, "codaplicacion");
            return (Criteria) this;
        }

        public Criteria andCodaplicacionLike(String value) {
            addCriterion("CODAPLICACION like", value, "codaplicacion");
            return (Criteria) this;
        }

        public Criteria andCodaplicacionNotLike(String value) {
            addCriterion("CODAPLICACION not like", value, "codaplicacion");
            return (Criteria) this;
        }

        public Criteria andCodaplicacionIn(List<String> values) {
            addCriterion("CODAPLICACION in", values, "codaplicacion");
            return (Criteria) this;
        }

        public Criteria andCodaplicacionNotIn(List<String> values) {
            addCriterion("CODAPLICACION not in", values, "codaplicacion");
            return (Criteria) this;
        }

        public Criteria andCodaplicacionBetween(String value1, String value2) {
            addCriterion("CODAPLICACION between", value1, value2, "codaplicacion");
            return (Criteria) this;
        }

        public Criteria andCodaplicacionNotBetween(String value1, String value2) {
            addCriterion("CODAPLICACION not between", value1, value2, "codaplicacion");
            return (Criteria) this;
        }

        public Criteria andIdparametrosistemaLikeInsensitive(String value) {
            addCriterion("upper(IDPARAMETROSISTEMA) like", value.toUpperCase(), "idparametrosistema");
            return (Criteria) this;
        }

        public Criteria andValorLikeInsensitive(String value) {
            addCriterion("upper(VALOR) like", value.toUpperCase(), "valor");
            return (Criteria) this;
        }

        public Criteria andCodaplicacionLikeInsensitive(String value) {
            addCriterion("upper(CODAPLICACION) like", value.toUpperCase(), "codaplicacion");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PARAMETRO_SISTEMA
     *
     * @mbggenerated do_not_delete_during_merge Thu Nov 26 11:59:53 COT 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PARAMETRO_SISTEMA
     *
     * @mbggenerated Thu Nov 26 11:59:53 COT 2015
     */
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