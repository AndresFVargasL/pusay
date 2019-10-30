package com.vortexbird.pusay.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.*;
import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public class PsyPlanAccionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyPlanAccionDTO.class);
    private String descripcion;
    private String estadoPlanAccion;
    private String estadoRegistro;
    private Date fechaFinPlaneada;
    private Date fechaFinReal;
    private Date fechaInicio;
    private String nombre;
    private Long placCodigo;
    private Long progCodigo_PsyPrograma;
    private boolean btnPlanAccionDefinitivo;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstadoPlanAccion() {
        return estadoPlanAccion;
    }

    public void setEstadoPlanAccion(String estadoPlanAccion) {
        this.estadoPlanAccion = estadoPlanAccion;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Date getFechaFinPlaneada() {
        return fechaFinPlaneada;
    }

    public void setFechaFinPlaneada(Date fechaFinPlaneada) {
        this.fechaFinPlaneada = fechaFinPlaneada;
    }

    public Date getFechaFinReal() {
        return fechaFinReal;
    }

    public void setFechaFinReal(Date fechaFinReal) {
        this.fechaFinReal = fechaFinReal;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPlacCodigo() {
        return placCodigo;
    }

    public void setPlacCodigo(Long placCodigo) {
        this.placCodigo = placCodigo;
    }

	public boolean isBtnPlanAccionDefinitivo() {
		return btnPlanAccionDefinitivo;
	}

	public void setBtnPlanAccionDefinitivo(boolean btnPlanAccionDefinitivo) {
		this.btnPlanAccionDefinitivo = btnPlanAccionDefinitivo;
	}

	public Long getProgCodigo_PsyPrograma() {
		return progCodigo_PsyPrograma;
	}

	public void setProgCodigo_PsyPrograma(Long progCodigo_PsyPrograma) {
		this.progCodigo_PsyPrograma = progCodigo_PsyPrograma;
	}
    
    
}
