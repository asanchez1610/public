package pe.gob.miraflores.mobile.domain.catalogo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CatalogoCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CTLMELECGO
     *
     * @mbggenerated Thu Nov 26 12:50:22 COT 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CTLMELECGO
     *
     * @mbggenerated Thu Nov 26 12:50:22 COT 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CTLMELECGO
     *
     * @mbggenerated Thu Nov 26 12:50:22 COT 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CTLMELECGO
     *
     * @mbggenerated Thu Nov 26 12:50:22 COT 2015
     */
    public CatalogoCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CTLMELECGO
     *
     * @mbggenerated Thu Nov 26 12:50:22 COT 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CTLMELECGO
     *
     * @mbggenerated Thu Nov 26 12:50:22 COT 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CTLMELECGO
     *
     * @mbggenerated Thu Nov 26 12:50:22 COT 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CTLMELECGO
     *
     * @mbggenerated Thu Nov 26 12:50:22 COT 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CTLMELECGO
     *
     * @mbggenerated Thu Nov 26 12:50:22 COT 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CTLMELECGO
     *
     * @mbggenerated Thu Nov 26 12:50:22 COT 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CTLMELECGO
     *
     * @mbggenerated Thu Nov 26 12:50:22 COT 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CTLMELECGO
     *
     * @mbggenerated Thu Nov 26 12:50:22 COT 2015
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
     * This method corresponds to the database table CTLMELECGO
     *
     * @mbggenerated Thu Nov 26 12:50:22 COT 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CTLMELECGO
     *
     * @mbggenerated Thu Nov 26 12:50:22 COT 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CTLMELECGO
     *
     * @mbggenerated Thu Nov 26 12:50:22 COT 2015
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdeElementoIsNull() {
            addCriterion("IDE_ELEMENTO is null");
            return (Criteria) this;
        }

        public Criteria andIdeElementoIsNotNull() {
            addCriterion("IDE_ELEMENTO is not null");
            return (Criteria) this;
        }

        public Criteria andIdeElementoEqualTo(Integer value) {
            addCriterion("IDE_ELEMENTO =", value, "ideElemento");
            return (Criteria) this;
        }

        public Criteria andIdeElementoNotEqualTo(Integer value) {
            addCriterion("IDE_ELEMENTO <>", value, "ideElemento");
            return (Criteria) this;
        }

        public Criteria andIdeElementoGreaterThan(Integer value) {
            addCriterion("IDE_ELEMENTO >", value, "ideElemento");
            return (Criteria) this;
        }

        public Criteria andIdeElementoGreaterThanOrEqualTo(Integer value) {
            addCriterion("IDE_ELEMENTO >=", value, "ideElemento");
            return (Criteria) this;
        }

        public Criteria andIdeElementoLessThan(Integer value) {
            addCriterion("IDE_ELEMENTO <", value, "ideElemento");
            return (Criteria) this;
        }

        public Criteria andIdeElementoLessThanOrEqualTo(Integer value) {
            addCriterion("IDE_ELEMENTO <=", value, "ideElemento");
            return (Criteria) this;
        }

        public Criteria andIdeElementoIn(List<Integer> values) {
            addCriterion("IDE_ELEMENTO in", values, "ideElemento");
            return (Criteria) this;
        }

        public Criteria andIdeElementoNotIn(List<Integer> values) {
            addCriterion("IDE_ELEMENTO not in", values, "ideElemento");
            return (Criteria) this;
        }

        public Criteria andIdeElementoBetween(Integer value1, Integer value2) {
            addCriterion("IDE_ELEMENTO between", value1, value2, "ideElemento");
            return (Criteria) this;
        }

        public Criteria andIdeElementoNotBetween(Integer value1, Integer value2) {
            addCriterion("IDE_ELEMENTO not between", value1, value2, "ideElemento");
            return (Criteria) this;
        }

        public Criteria andIdeGrupoIsNull() {
            addCriterion("IDE_GRUPO is null");
            return (Criteria) this;
        }

        public Criteria andIdeGrupoIsNotNull() {
            addCriterion("IDE_GRUPO is not null");
            return (Criteria) this;
        }

        public Criteria andIdeGrupoEqualTo(Integer value) {
            addCriterion("IDE_GRUPO =", value, "ideGrupo");
            return (Criteria) this;
        }

        public Criteria andIdeGrupoNotEqualTo(Integer value) {
            addCriterion("IDE_GRUPO <>", value, "ideGrupo");
            return (Criteria) this;
        }

        public Criteria andIdeGrupoGreaterThan(Integer value) {
            addCriterion("IDE_GRUPO >", value, "ideGrupo");
            return (Criteria) this;
        }

        public Criteria andIdeGrupoGreaterThanOrEqualTo(Integer value) {
            addCriterion("IDE_GRUPO >=", value, "ideGrupo");
            return (Criteria) this;
        }

        public Criteria andIdeGrupoLessThan(Integer value) {
            addCriterion("IDE_GRUPO <", value, "ideGrupo");
            return (Criteria) this;
        }

        public Criteria andIdeGrupoLessThanOrEqualTo(Integer value) {
            addCriterion("IDE_GRUPO <=", value, "ideGrupo");
            return (Criteria) this;
        }

        public Criteria andIdeGrupoIn(List<Integer> values) {
            addCriterion("IDE_GRUPO in", values, "ideGrupo");
            return (Criteria) this;
        }

        public Criteria andIdeGrupoNotIn(List<Integer> values) {
            addCriterion("IDE_GRUPO not in", values, "ideGrupo");
            return (Criteria) this;
        }

        public Criteria andIdeGrupoBetween(Integer value1, Integer value2) {
            addCriterion("IDE_GRUPO between", value1, value2, "ideGrupo");
            return (Criteria) this;
        }

        public Criteria andIdeGrupoNotBetween(Integer value1, Integer value2) {
            addCriterion("IDE_GRUPO not between", value1, value2, "ideGrupo");
            return (Criteria) this;
        }

        public Criteria andCodCodigoIsNull() {
            addCriterion("COD_CODIGO is null");
            return (Criteria) this;
        }

        public Criteria andCodCodigoIsNotNull() {
            addCriterion("COD_CODIGO is not null");
            return (Criteria) this;
        }

        public Criteria andCodCodigoEqualTo(String value) {
            addCriterion("COD_CODIGO =", value, "codCodigo");
            return (Criteria) this;
        }

        public Criteria andCodCodigoNotEqualTo(String value) {
            addCriterion("COD_CODIGO <>", value, "codCodigo");
            return (Criteria) this;
        }

        public Criteria andCodCodigoGreaterThan(String value) {
            addCriterion("COD_CODIGO >", value, "codCodigo");
            return (Criteria) this;
        }

        public Criteria andCodCodigoGreaterThanOrEqualTo(String value) {
            addCriterion("COD_CODIGO >=", value, "codCodigo");
            return (Criteria) this;
        }

        public Criteria andCodCodigoLessThan(String value) {
            addCriterion("COD_CODIGO <", value, "codCodigo");
            return (Criteria) this;
        }

        public Criteria andCodCodigoLessThanOrEqualTo(String value) {
            addCriterion("COD_CODIGO <=", value, "codCodigo");
            return (Criteria) this;
        }

        public Criteria andCodCodigoLike(String value) {
            addCriterion("COD_CODIGO like", value, "codCodigo");
            return (Criteria) this;
        }

        public Criteria andCodCodigoNotLike(String value) {
            addCriterion("COD_CODIGO not like", value, "codCodigo");
            return (Criteria) this;
        }

        public Criteria andCodCodigoIn(List<String> values) {
            addCriterion("COD_CODIGO in", values, "codCodigo");
            return (Criteria) this;
        }

        public Criteria andCodCodigoNotIn(List<String> values) {
            addCriterion("COD_CODIGO not in", values, "codCodigo");
            return (Criteria) this;
        }

        public Criteria andCodCodigoBetween(String value1, String value2) {
            addCriterion("COD_CODIGO between", value1, value2, "codCodigo");
            return (Criteria) this;
        }

        public Criteria andCodCodigoNotBetween(String value1, String value2) {
            addCriterion("COD_CODIGO not between", value1, value2, "codCodigo");
            return (Criteria) this;
        }

        public Criteria andDesNombreIsNull() {
            addCriterion("DES_NOMBRE is null");
            return (Criteria) this;
        }

        public Criteria andDesNombreIsNotNull() {
            addCriterion("DES_NOMBRE is not null");
            return (Criteria) this;
        }

        public Criteria andDesNombreEqualTo(String value) {
            addCriterion("DES_NOMBRE =", value, "desNombre");
            return (Criteria) this;
        }

        public Criteria andDesNombreNotEqualTo(String value) {
            addCriterion("DES_NOMBRE <>", value, "desNombre");
            return (Criteria) this;
        }

        public Criteria andDesNombreGreaterThan(String value) {
            addCriterion("DES_NOMBRE >", value, "desNombre");
            return (Criteria) this;
        }

        public Criteria andDesNombreGreaterThanOrEqualTo(String value) {
            addCriterion("DES_NOMBRE >=", value, "desNombre");
            return (Criteria) this;
        }

        public Criteria andDesNombreLessThan(String value) {
            addCriterion("DES_NOMBRE <", value, "desNombre");
            return (Criteria) this;
        }

        public Criteria andDesNombreLessThanOrEqualTo(String value) {
            addCriterion("DES_NOMBRE <=", value, "desNombre");
            return (Criteria) this;
        }

        public Criteria andDesNombreLike(String value) {
            addCriterion("DES_NOMBRE like", value, "desNombre");
            return (Criteria) this;
        }

        public Criteria andDesNombreNotLike(String value) {
            addCriterion("DES_NOMBRE not like", value, "desNombre");
            return (Criteria) this;
        }

        public Criteria andDesNombreIn(List<String> values) {
            addCriterion("DES_NOMBRE in", values, "desNombre");
            return (Criteria) this;
        }

        public Criteria andDesNombreNotIn(List<String> values) {
            addCriterion("DES_NOMBRE not in", values, "desNombre");
            return (Criteria) this;
        }

        public Criteria andDesNombreBetween(String value1, String value2) {
            addCriterion("DES_NOMBRE between", value1, value2, "desNombre");
            return (Criteria) this;
        }

        public Criteria andDesNombreNotBetween(String value1, String value2) {
            addCriterion("DES_NOMBRE not between", value1, value2, "desNombre");
            return (Criteria) this;
        }

        public Criteria andClvAbreviarIsNull() {
            addCriterion("CLV_ABREVIAR is null");
            return (Criteria) this;
        }

        public Criteria andClvAbreviarIsNotNull() {
            addCriterion("CLV_ABREVIAR is not null");
            return (Criteria) this;
        }

        public Criteria andClvAbreviarEqualTo(String value) {
            addCriterion("CLV_ABREVIAR =", value, "clvAbreviar");
            return (Criteria) this;
        }

        public Criteria andClvAbreviarNotEqualTo(String value) {
            addCriterion("CLV_ABREVIAR <>", value, "clvAbreviar");
            return (Criteria) this;
        }

        public Criteria andClvAbreviarGreaterThan(String value) {
            addCriterion("CLV_ABREVIAR >", value, "clvAbreviar");
            return (Criteria) this;
        }

        public Criteria andClvAbreviarGreaterThanOrEqualTo(String value) {
            addCriterion("CLV_ABREVIAR >=", value, "clvAbreviar");
            return (Criteria) this;
        }

        public Criteria andClvAbreviarLessThan(String value) {
            addCriterion("CLV_ABREVIAR <", value, "clvAbreviar");
            return (Criteria) this;
        }

        public Criteria andClvAbreviarLessThanOrEqualTo(String value) {
            addCriterion("CLV_ABREVIAR <=", value, "clvAbreviar");
            return (Criteria) this;
        }

        public Criteria andClvAbreviarLike(String value) {
            addCriterion("CLV_ABREVIAR like", value, "clvAbreviar");
            return (Criteria) this;
        }

        public Criteria andClvAbreviarNotLike(String value) {
            addCriterion("CLV_ABREVIAR not like", value, "clvAbreviar");
            return (Criteria) this;
        }

        public Criteria andClvAbreviarIn(List<String> values) {
            addCriterion("CLV_ABREVIAR in", values, "clvAbreviar");
            return (Criteria) this;
        }

        public Criteria andClvAbreviarNotIn(List<String> values) {
            addCriterion("CLV_ABREVIAR not in", values, "clvAbreviar");
            return (Criteria) this;
        }

        public Criteria andClvAbreviarBetween(String value1, String value2) {
            addCriterion("CLV_ABREVIAR between", value1, value2, "clvAbreviar");
            return (Criteria) this;
        }

        public Criteria andClvAbreviarNotBetween(String value1, String value2) {
            addCriterion("CLV_ABREVIAR not between", value1, value2, "clvAbreviar");
            return (Criteria) this;
        }

        public Criteria andIdeElemento1IsNull() {
            addCriterion("IDE_ELEMENTO1 is null");
            return (Criteria) this;
        }

        public Criteria andIdeElemento1IsNotNull() {
            addCriterion("IDE_ELEMENTO1 is not null");
            return (Criteria) this;
        }

        public Criteria andIdeElemento1EqualTo(Integer value) {
            addCriterion("IDE_ELEMENTO1 =", value, "ideElemento1");
            return (Criteria) this;
        }

        public Criteria andIdeElemento1NotEqualTo(Integer value) {
            addCriterion("IDE_ELEMENTO1 <>", value, "ideElemento1");
            return (Criteria) this;
        }

        public Criteria andIdeElemento1GreaterThan(Integer value) {
            addCriterion("IDE_ELEMENTO1 >", value, "ideElemento1");
            return (Criteria) this;
        }

        public Criteria andIdeElemento1GreaterThanOrEqualTo(Integer value) {
            addCriterion("IDE_ELEMENTO1 >=", value, "ideElemento1");
            return (Criteria) this;
        }

        public Criteria andIdeElemento1LessThan(Integer value) {
            addCriterion("IDE_ELEMENTO1 <", value, "ideElemento1");
            return (Criteria) this;
        }

        public Criteria andIdeElemento1LessThanOrEqualTo(Integer value) {
            addCriterion("IDE_ELEMENTO1 <=", value, "ideElemento1");
            return (Criteria) this;
        }

        public Criteria andIdeElemento1In(List<Integer> values) {
            addCriterion("IDE_ELEMENTO1 in", values, "ideElemento1");
            return (Criteria) this;
        }

        public Criteria andIdeElemento1NotIn(List<Integer> values) {
            addCriterion("IDE_ELEMENTO1 not in", values, "ideElemento1");
            return (Criteria) this;
        }

        public Criteria andIdeElemento1Between(Integer value1, Integer value2) {
            addCriterion("IDE_ELEMENTO1 between", value1, value2, "ideElemento1");
            return (Criteria) this;
        }

        public Criteria andIdeElemento1NotBetween(Integer value1, Integer value2) {
            addCriterion("IDE_ELEMENTO1 not between", value1, value2, "ideElemento1");
            return (Criteria) this;
        }

        public Criteria andIndEstadoIsNull() {
            addCriterion("IND_ESTADO is null");
            return (Criteria) this;
        }

        public Criteria andIndEstadoIsNotNull() {
            addCriterion("IND_ESTADO is not null");
            return (Criteria) this;
        }

        public Criteria andIndEstadoEqualTo(String value) {
            addCriterion("IND_ESTADO =", value, "indEstado");
            return (Criteria) this;
        }

        public Criteria andIndEstadoNotEqualTo(String value) {
            addCriterion("IND_ESTADO <>", value, "indEstado");
            return (Criteria) this;
        }

        public Criteria andIndEstadoGreaterThan(String value) {
            addCriterion("IND_ESTADO >", value, "indEstado");
            return (Criteria) this;
        }

        public Criteria andIndEstadoGreaterThanOrEqualTo(String value) {
            addCriterion("IND_ESTADO >=", value, "indEstado");
            return (Criteria) this;
        }

        public Criteria andIndEstadoLessThan(String value) {
            addCriterion("IND_ESTADO <", value, "indEstado");
            return (Criteria) this;
        }

        public Criteria andIndEstadoLessThanOrEqualTo(String value) {
            addCriterion("IND_ESTADO <=", value, "indEstado");
            return (Criteria) this;
        }

        public Criteria andIndEstadoLike(String value) {
            addCriterion("IND_ESTADO like", value, "indEstado");
            return (Criteria) this;
        }

        public Criteria andIndEstadoNotLike(String value) {
            addCriterion("IND_ESTADO not like", value, "indEstado");
            return (Criteria) this;
        }

        public Criteria andIndEstadoIn(List<String> values) {
            addCriterion("IND_ESTADO in", values, "indEstado");
            return (Criteria) this;
        }

        public Criteria andIndEstadoNotIn(List<String> values) {
            addCriterion("IND_ESTADO not in", values, "indEstado");
            return (Criteria) this;
        }

        public Criteria andIndEstadoBetween(String value1, String value2) {
            addCriterion("IND_ESTADO between", value1, value2, "indEstado");
            return (Criteria) this;
        }

        public Criteria andIndEstadoNotBetween(String value1, String value2) {
            addCriterion("IND_ESTADO not between", value1, value2, "indEstado");
            return (Criteria) this;
        }

        public Criteria andFecFechaCreacionIsNull() {
            addCriterion("FEC_FECHA_CREACION is null");
            return (Criteria) this;
        }

        public Criteria andFecFechaCreacionIsNotNull() {
            addCriterion("FEC_FECHA_CREACION is not null");
            return (Criteria) this;
        }

        public Criteria andFecFechaCreacionEqualTo(Date value) {
            addCriterionForJDBCDate("FEC_FECHA_CREACION =", value, "fecFechaCreacion");
            return (Criteria) this;
        }

        public Criteria andFecFechaCreacionNotEqualTo(Date value) {
            addCriterionForJDBCDate("FEC_FECHA_CREACION <>", value, "fecFechaCreacion");
            return (Criteria) this;
        }

        public Criteria andFecFechaCreacionGreaterThan(Date value) {
            addCriterionForJDBCDate("FEC_FECHA_CREACION >", value, "fecFechaCreacion");
            return (Criteria) this;
        }

        public Criteria andFecFechaCreacionGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("FEC_FECHA_CREACION >=", value, "fecFechaCreacion");
            return (Criteria) this;
        }

        public Criteria andFecFechaCreacionLessThan(Date value) {
            addCriterionForJDBCDate("FEC_FECHA_CREACION <", value, "fecFechaCreacion");
            return (Criteria) this;
        }

        public Criteria andFecFechaCreacionLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("FEC_FECHA_CREACION <=", value, "fecFechaCreacion");
            return (Criteria) this;
        }

        public Criteria andFecFechaCreacionIn(List<Date> values) {
            addCriterionForJDBCDate("FEC_FECHA_CREACION in", values, "fecFechaCreacion");
            return (Criteria) this;
        }

        public Criteria andFecFechaCreacionNotIn(List<Date> values) {
            addCriterionForJDBCDate("FEC_FECHA_CREACION not in", values, "fecFechaCreacion");
            return (Criteria) this;
        }

        public Criteria andFecFechaCreacionBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("FEC_FECHA_CREACION between", value1, value2, "fecFechaCreacion");
            return (Criteria) this;
        }

        public Criteria andFecFechaCreacionNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("FEC_FECHA_CREACION not between", value1, value2, "fecFechaCreacion");
            return (Criteria) this;
        }

        public Criteria andReferenciaIsNull() {
            addCriterion("REFERENCIA is null");
            return (Criteria) this;
        }

        public Criteria andReferenciaIsNotNull() {
            addCriterion("REFERENCIA is not null");
            return (Criteria) this;
        }

        public Criteria andReferenciaEqualTo(String value) {
            addCriterion("REFERENCIA =", value, "referencia");
            return (Criteria) this;
        }

        public Criteria andReferenciaNotEqualTo(String value) {
            addCriterion("REFERENCIA <>", value, "referencia");
            return (Criteria) this;
        }

        public Criteria andReferenciaGreaterThan(String value) {
            addCriterion("REFERENCIA >", value, "referencia");
            return (Criteria) this;
        }

        public Criteria andReferenciaGreaterThanOrEqualTo(String value) {
            addCriterion("REFERENCIA >=", value, "referencia");
            return (Criteria) this;
        }

        public Criteria andReferenciaLessThan(String value) {
            addCriterion("REFERENCIA <", value, "referencia");
            return (Criteria) this;
        }

        public Criteria andReferenciaLessThanOrEqualTo(String value) {
            addCriterion("REFERENCIA <=", value, "referencia");
            return (Criteria) this;
        }

        public Criteria andReferenciaLike(String value) {
            addCriterion("REFERENCIA like", value, "referencia");
            return (Criteria) this;
        }

        public Criteria andReferenciaNotLike(String value) {
            addCriterion("REFERENCIA not like", value, "referencia");
            return (Criteria) this;
        }

        public Criteria andReferenciaIn(List<String> values) {
            addCriterion("REFERENCIA in", values, "referencia");
            return (Criteria) this;
        }

        public Criteria andReferenciaNotIn(List<String> values) {
            addCriterion("REFERENCIA not in", values, "referencia");
            return (Criteria) this;
        }

        public Criteria andReferenciaBetween(String value1, String value2) {
            addCriterion("REFERENCIA between", value1, value2, "referencia");
            return (Criteria) this;
        }

        public Criteria andReferenciaNotBetween(String value1, String value2) {
            addCriterion("REFERENCIA not between", value1, value2, "referencia");
            return (Criteria) this;
        }

        public Criteria andReferencia1IsNull() {
            addCriterion("REFERENCIA1 is null");
            return (Criteria) this;
        }

        public Criteria andReferencia1IsNotNull() {
            addCriterion("REFERENCIA1 is not null");
            return (Criteria) this;
        }

        public Criteria andReferencia1EqualTo(String value) {
            addCriterion("REFERENCIA1 =", value, "referencia1");
            return (Criteria) this;
        }

        public Criteria andReferencia1NotEqualTo(String value) {
            addCriterion("REFERENCIA1 <>", value, "referencia1");
            return (Criteria) this;
        }

        public Criteria andReferencia1GreaterThan(String value) {
            addCriterion("REFERENCIA1 >", value, "referencia1");
            return (Criteria) this;
        }

        public Criteria andReferencia1GreaterThanOrEqualTo(String value) {
            addCriterion("REFERENCIA1 >=", value, "referencia1");
            return (Criteria) this;
        }

        public Criteria andReferencia1LessThan(String value) {
            addCriterion("REFERENCIA1 <", value, "referencia1");
            return (Criteria) this;
        }

        public Criteria andReferencia1LessThanOrEqualTo(String value) {
            addCriterion("REFERENCIA1 <=", value, "referencia1");
            return (Criteria) this;
        }

        public Criteria andReferencia1Like(String value) {
            addCriterion("REFERENCIA1 like", value, "referencia1");
            return (Criteria) this;
        }

        public Criteria andReferencia1NotLike(String value) {
            addCriterion("REFERENCIA1 not like", value, "referencia1");
            return (Criteria) this;
        }

        public Criteria andReferencia1In(List<String> values) {
            addCriterion("REFERENCIA1 in", values, "referencia1");
            return (Criteria) this;
        }

        public Criteria andReferencia1NotIn(List<String> values) {
            addCriterion("REFERENCIA1 not in", values, "referencia1");
            return (Criteria) this;
        }

        public Criteria andReferencia1Between(String value1, String value2) {
            addCriterion("REFERENCIA1 between", value1, value2, "referencia1");
            return (Criteria) this;
        }

        public Criteria andReferencia1NotBetween(String value1, String value2) {
            addCriterion("REFERENCIA1 not between", value1, value2, "referencia1");
            return (Criteria) this;
        }

        public Criteria andReferencia2IsNull() {
            addCriterion("REFERENCIA2 is null");
            return (Criteria) this;
        }

        public Criteria andReferencia2IsNotNull() {
            addCriterion("REFERENCIA2 is not null");
            return (Criteria) this;
        }

        public Criteria andReferencia2EqualTo(String value) {
            addCriterion("REFERENCIA2 =", value, "referencia2");
            return (Criteria) this;
        }

        public Criteria andReferencia2NotEqualTo(String value) {
            addCriterion("REFERENCIA2 <>", value, "referencia2");
            return (Criteria) this;
        }

        public Criteria andReferencia2GreaterThan(String value) {
            addCriterion("REFERENCIA2 >", value, "referencia2");
            return (Criteria) this;
        }

        public Criteria andReferencia2GreaterThanOrEqualTo(String value) {
            addCriterion("REFERENCIA2 >=", value, "referencia2");
            return (Criteria) this;
        }

        public Criteria andReferencia2LessThan(String value) {
            addCriterion("REFERENCIA2 <", value, "referencia2");
            return (Criteria) this;
        }

        public Criteria andReferencia2LessThanOrEqualTo(String value) {
            addCriterion("REFERENCIA2 <=", value, "referencia2");
            return (Criteria) this;
        }

        public Criteria andReferencia2Like(String value) {
            addCriterion("REFERENCIA2 like", value, "referencia2");
            return (Criteria) this;
        }

        public Criteria andReferencia2NotLike(String value) {
            addCriterion("REFERENCIA2 not like", value, "referencia2");
            return (Criteria) this;
        }

        public Criteria andReferencia2In(List<String> values) {
            addCriterion("REFERENCIA2 in", values, "referencia2");
            return (Criteria) this;
        }

        public Criteria andReferencia2NotIn(List<String> values) {
            addCriterion("REFERENCIA2 not in", values, "referencia2");
            return (Criteria) this;
        }

        public Criteria andReferencia2Between(String value1, String value2) {
            addCriterion("REFERENCIA2 between", value1, value2, "referencia2");
            return (Criteria) this;
        }

        public Criteria andReferencia2NotBetween(String value1, String value2) {
            addCriterion("REFERENCIA2 not between", value1, value2, "referencia2");
            return (Criteria) this;
        }

        public Criteria andCodCodigoLikeInsensitive(String value) {
            addCriterion("upper(COD_CODIGO) like", value.toUpperCase(), "codCodigo");
            return (Criteria) this;
        }

        public Criteria andDesNombreLikeInsensitive(String value) {
            addCriterion("upper(DES_NOMBRE) like", value.toUpperCase(), "desNombre");
            return (Criteria) this;
        }

        public Criteria andClvAbreviarLikeInsensitive(String value) {
            addCriterion("upper(CLV_ABREVIAR) like", value.toUpperCase(), "clvAbreviar");
            return (Criteria) this;
        }

        public Criteria andIndEstadoLikeInsensitive(String value) {
            addCriterion("upper(IND_ESTADO) like", value.toUpperCase(), "indEstado");
            return (Criteria) this;
        }

        public Criteria andReferenciaLikeInsensitive(String value) {
            addCriterion("upper(REFERENCIA) like", value.toUpperCase(), "referencia");
            return (Criteria) this;
        }

        public Criteria andReferencia1LikeInsensitive(String value) {
            addCriterion("upper(REFERENCIA1) like", value.toUpperCase(), "referencia1");
            return (Criteria) this;
        }

        public Criteria andReferencia2LikeInsensitive(String value) {
            addCriterion("upper(REFERENCIA2) like", value.toUpperCase(), "referencia2");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CTLMELECGO
     *
     * @mbggenerated do_not_delete_during_merge Thu Nov 26 12:50:22 COT 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CTLMELECGO
     *
     * @mbggenerated Thu Nov 26 12:50:22 COT 2015
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