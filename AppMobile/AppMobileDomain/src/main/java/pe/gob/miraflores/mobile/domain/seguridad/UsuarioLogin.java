package pe.gob.miraflores.mobile.domain.seguridad;

import java.util.Date;

public class UsuarioLogin {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.IDE_USUARIO
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private Integer ideUsuario;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.IDE_ESTADO
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private Integer ideEstado;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.DES_LOGIN_USER
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private String desLoginUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.CLV_CLAVE_USER
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private String clvClaveUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.IDE_ROL
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private Integer ideRol;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.IDE_PERSONA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private Integer idePersona;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.FEC_REGISTRA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private Date fecRegistra;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.FEC_MODIFICA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private Date fecModifica;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.DES_USU_REGISTRA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private String desUsuRegistra;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.DES_USU_MODIFICA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private String desUsuModifica;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.DES_IP_REGISTRA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private String desIpRegistra;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.DES_IP_MODIFICA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private String desIpModifica;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.IN_CUENTA_EXPIRADA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private String inCuentaExpirada;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.IN_CUENTA_BLOQUEADA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private String inCuentaBloqueada;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.IN_CREDENCIAL_EXPIRADA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private String inCredencialExpirada;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.IN_HABILITADO
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private String inHabilitado;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.DES_PARAMETRO1
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private String desParametro1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.DES_RUTA_SIGNPFX
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private String desRutaSignpfx;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMCUSER.DES_RUTA_SIGNIMAGE
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    private String desRutaSignimage;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.IDE_USUARIO
     *
     * @return the value of ADMCUSER.IDE_USUARIO
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public Integer getIdeUsuario() {
        return ideUsuario;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.IDE_USUARIO
     *
     * @param ideUsuario the value for ADMCUSER.IDE_USUARIO
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setIdeUsuario(Integer ideUsuario) {
        this.ideUsuario = ideUsuario;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.IDE_ESTADO
     *
     * @return the value of ADMCUSER.IDE_ESTADO
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public Integer getIdeEstado() {
        return ideEstado;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.IDE_ESTADO
     *
     * @param ideEstado the value for ADMCUSER.IDE_ESTADO
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setIdeEstado(Integer ideEstado) {
        this.ideEstado = ideEstado;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.DES_LOGIN_USER
     *
     * @return the value of ADMCUSER.DES_LOGIN_USER
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public String getDesLoginUser() {
        return desLoginUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.DES_LOGIN_USER
     *
     * @param desLoginUser the value for ADMCUSER.DES_LOGIN_USER
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setDesLoginUser(String desLoginUser) {
        this.desLoginUser = desLoginUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.CLV_CLAVE_USER
     *
     * @return the value of ADMCUSER.CLV_CLAVE_USER
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public String getClvClaveUser() {
        return clvClaveUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.CLV_CLAVE_USER
     *
     * @param clvClaveUser the value for ADMCUSER.CLV_CLAVE_USER
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setClvClaveUser(String clvClaveUser) {
        this.clvClaveUser = clvClaveUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.IDE_ROL
     *
     * @return the value of ADMCUSER.IDE_ROL
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public Integer getIdeRol() {
        return ideRol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.IDE_ROL
     *
     * @param ideRol the value for ADMCUSER.IDE_ROL
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setIdeRol(Integer ideRol) {
        this.ideRol = ideRol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.IDE_PERSONA
     *
     * @return the value of ADMCUSER.IDE_PERSONA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public Integer getIdePersona() {
        return idePersona;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.IDE_PERSONA
     *
     * @param idePersona the value for ADMCUSER.IDE_PERSONA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setIdePersona(Integer idePersona) {
        this.idePersona = idePersona;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.FEC_REGISTRA
     *
     * @return the value of ADMCUSER.FEC_REGISTRA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public Date getFecRegistra() {
        return fecRegistra;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.FEC_REGISTRA
     *
     * @param fecRegistra the value for ADMCUSER.FEC_REGISTRA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setFecRegistra(Date fecRegistra) {
        this.fecRegistra = fecRegistra;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.FEC_MODIFICA
     *
     * @return the value of ADMCUSER.FEC_MODIFICA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public Date getFecModifica() {
        return fecModifica;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.FEC_MODIFICA
     *
     * @param fecModifica the value for ADMCUSER.FEC_MODIFICA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setFecModifica(Date fecModifica) {
        this.fecModifica = fecModifica;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.DES_USU_REGISTRA
     *
     * @return the value of ADMCUSER.DES_USU_REGISTRA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public String getDesUsuRegistra() {
        return desUsuRegistra;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.DES_USU_REGISTRA
     *
     * @param desUsuRegistra the value for ADMCUSER.DES_USU_REGISTRA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setDesUsuRegistra(String desUsuRegistra) {
        this.desUsuRegistra = desUsuRegistra;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.DES_USU_MODIFICA
     *
     * @return the value of ADMCUSER.DES_USU_MODIFICA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public String getDesUsuModifica() {
        return desUsuModifica;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.DES_USU_MODIFICA
     *
     * @param desUsuModifica the value for ADMCUSER.DES_USU_MODIFICA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setDesUsuModifica(String desUsuModifica) {
        this.desUsuModifica = desUsuModifica;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.DES_IP_REGISTRA
     *
     * @return the value of ADMCUSER.DES_IP_REGISTRA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public String getDesIpRegistra() {
        return desIpRegistra;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.DES_IP_REGISTRA
     *
     * @param desIpRegistra the value for ADMCUSER.DES_IP_REGISTRA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setDesIpRegistra(String desIpRegistra) {
        this.desIpRegistra = desIpRegistra;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.DES_IP_MODIFICA
     *
     * @return the value of ADMCUSER.DES_IP_MODIFICA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public String getDesIpModifica() {
        return desIpModifica;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.DES_IP_MODIFICA
     *
     * @param desIpModifica the value for ADMCUSER.DES_IP_MODIFICA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setDesIpModifica(String desIpModifica) {
        this.desIpModifica = desIpModifica;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.IN_CUENTA_EXPIRADA
     *
     * @return the value of ADMCUSER.IN_CUENTA_EXPIRADA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public String getInCuentaExpirada() {
        return inCuentaExpirada;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.IN_CUENTA_EXPIRADA
     *
     * @param inCuentaExpirada the value for ADMCUSER.IN_CUENTA_EXPIRADA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setInCuentaExpirada(String inCuentaExpirada) {
        this.inCuentaExpirada = inCuentaExpirada;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.IN_CUENTA_BLOQUEADA
     *
     * @return the value of ADMCUSER.IN_CUENTA_BLOQUEADA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public String getInCuentaBloqueada() {
        return inCuentaBloqueada;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.IN_CUENTA_BLOQUEADA
     *
     * @param inCuentaBloqueada the value for ADMCUSER.IN_CUENTA_BLOQUEADA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setInCuentaBloqueada(String inCuentaBloqueada) {
        this.inCuentaBloqueada = inCuentaBloqueada;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.IN_CREDENCIAL_EXPIRADA
     *
     * @return the value of ADMCUSER.IN_CREDENCIAL_EXPIRADA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public String getInCredencialExpirada() {
        return inCredencialExpirada;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.IN_CREDENCIAL_EXPIRADA
     *
     * @param inCredencialExpirada the value for ADMCUSER.IN_CREDENCIAL_EXPIRADA
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setInCredencialExpirada(String inCredencialExpirada) {
        this.inCredencialExpirada = inCredencialExpirada;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.IN_HABILITADO
     *
     * @return the value of ADMCUSER.IN_HABILITADO
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public String getInHabilitado() {
        return inHabilitado;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.IN_HABILITADO
     *
     * @param inHabilitado the value for ADMCUSER.IN_HABILITADO
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setInHabilitado(String inHabilitado) {
        this.inHabilitado = inHabilitado;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.DES_PARAMETRO1
     *
     * @return the value of ADMCUSER.DES_PARAMETRO1
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public String getDesParametro1() {
        return desParametro1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.DES_PARAMETRO1
     *
     * @param desParametro1 the value for ADMCUSER.DES_PARAMETRO1
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setDesParametro1(String desParametro1) {
        this.desParametro1 = desParametro1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.DES_RUTA_SIGNPFX
     *
     * @return the value of ADMCUSER.DES_RUTA_SIGNPFX
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public String getDesRutaSignpfx() {
        return desRutaSignpfx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.DES_RUTA_SIGNPFX
     *
     * @param desRutaSignpfx the value for ADMCUSER.DES_RUTA_SIGNPFX
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setDesRutaSignpfx(String desRutaSignpfx) {
        this.desRutaSignpfx = desRutaSignpfx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMCUSER.DES_RUTA_SIGNIMAGE
     *
     * @return the value of ADMCUSER.DES_RUTA_SIGNIMAGE
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public String getDesRutaSignimage() {
        return desRutaSignimage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMCUSER.DES_RUTA_SIGNIMAGE
     *
     * @param desRutaSignimage the value for ADMCUSER.DES_RUTA_SIGNIMAGE
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    public void setDesRutaSignimage(String desRutaSignimage) {
        this.desRutaSignimage = desRutaSignimage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMCUSER
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UsuarioLogin other = (UsuarioLogin) that;
        return (this.getIdeUsuario() == null ? other.getIdeUsuario() == null : this.getIdeUsuario().equals(other.getIdeUsuario()))
            && (this.getIdeEstado() == null ? other.getIdeEstado() == null : this.getIdeEstado().equals(other.getIdeEstado()))
            && (this.getDesLoginUser() == null ? other.getDesLoginUser() == null : this.getDesLoginUser().equals(other.getDesLoginUser()))
            && (this.getClvClaveUser() == null ? other.getClvClaveUser() == null : this.getClvClaveUser().equals(other.getClvClaveUser()))
            && (this.getIdeRol() == null ? other.getIdeRol() == null : this.getIdeRol().equals(other.getIdeRol()))
            && (this.getIdePersona() == null ? other.getIdePersona() == null : this.getIdePersona().equals(other.getIdePersona()))
            && (this.getFecRegistra() == null ? other.getFecRegistra() == null : this.getFecRegistra().equals(other.getFecRegistra()))
            && (this.getFecModifica() == null ? other.getFecModifica() == null : this.getFecModifica().equals(other.getFecModifica()))
            && (this.getDesUsuRegistra() == null ? other.getDesUsuRegistra() == null : this.getDesUsuRegistra().equals(other.getDesUsuRegistra()))
            && (this.getDesUsuModifica() == null ? other.getDesUsuModifica() == null : this.getDesUsuModifica().equals(other.getDesUsuModifica()))
            && (this.getDesIpRegistra() == null ? other.getDesIpRegistra() == null : this.getDesIpRegistra().equals(other.getDesIpRegistra()))
            && (this.getDesIpModifica() == null ? other.getDesIpModifica() == null : this.getDesIpModifica().equals(other.getDesIpModifica()))
            && (this.getInCuentaExpirada() == null ? other.getInCuentaExpirada() == null : this.getInCuentaExpirada().equals(other.getInCuentaExpirada()))
            && (this.getInCuentaBloqueada() == null ? other.getInCuentaBloqueada() == null : this.getInCuentaBloqueada().equals(other.getInCuentaBloqueada()))
            && (this.getInCredencialExpirada() == null ? other.getInCredencialExpirada() == null : this.getInCredencialExpirada().equals(other.getInCredencialExpirada()))
            && (this.getInHabilitado() == null ? other.getInHabilitado() == null : this.getInHabilitado().equals(other.getInHabilitado()))
            && (this.getDesParametro1() == null ? other.getDesParametro1() == null : this.getDesParametro1().equals(other.getDesParametro1()))
            && (this.getDesRutaSignpfx() == null ? other.getDesRutaSignpfx() == null : this.getDesRutaSignpfx().equals(other.getDesRutaSignpfx()))
            && (this.getDesRutaSignimage() == null ? other.getDesRutaSignimage() == null : this.getDesRutaSignimage().equals(other.getDesRutaSignimage()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMCUSER
     *
     * @mbggenerated Mon Jan 04 11:42:45 COT 2016
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIdeUsuario() == null) ? 0 : getIdeUsuario().hashCode());
        result = prime * result + ((getIdeEstado() == null) ? 0 : getIdeEstado().hashCode());
        result = prime * result + ((getDesLoginUser() == null) ? 0 : getDesLoginUser().hashCode());
        result = prime * result + ((getClvClaveUser() == null) ? 0 : getClvClaveUser().hashCode());
        result = prime * result + ((getIdeRol() == null) ? 0 : getIdeRol().hashCode());
        result = prime * result + ((getIdePersona() == null) ? 0 : getIdePersona().hashCode());
        result = prime * result + ((getFecRegistra() == null) ? 0 : getFecRegistra().hashCode());
        result = prime * result + ((getFecModifica() == null) ? 0 : getFecModifica().hashCode());
        result = prime * result + ((getDesUsuRegistra() == null) ? 0 : getDesUsuRegistra().hashCode());
        result = prime * result + ((getDesUsuModifica() == null) ? 0 : getDesUsuModifica().hashCode());
        result = prime * result + ((getDesIpRegistra() == null) ? 0 : getDesIpRegistra().hashCode());
        result = prime * result + ((getDesIpModifica() == null) ? 0 : getDesIpModifica().hashCode());
        result = prime * result + ((getInCuentaExpirada() == null) ? 0 : getInCuentaExpirada().hashCode());
        result = prime * result + ((getInCuentaBloqueada() == null) ? 0 : getInCuentaBloqueada().hashCode());
        result = prime * result + ((getInCredencialExpirada() == null) ? 0 : getInCredencialExpirada().hashCode());
        result = prime * result + ((getInHabilitado() == null) ? 0 : getInHabilitado().hashCode());
        result = prime * result + ((getDesParametro1() == null) ? 0 : getDesParametro1().hashCode());
        result = prime * result + ((getDesRutaSignpfx() == null) ? 0 : getDesRutaSignpfx().hashCode());
        result = prime * result + ((getDesRutaSignimage() == null) ? 0 : getDesRutaSignimage().hashCode());
        return result;
    }
}