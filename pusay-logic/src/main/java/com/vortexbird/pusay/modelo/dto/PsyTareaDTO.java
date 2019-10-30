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
public class PsyTareaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyTareaDTO.class);
    private String descripcion;
    private String estadoRegistro;
    private String estadoTarea;
    private Date fechaFinPlaneada;
    private Date fechaFinReal;
    private Date fechaInicio;
    private Long semanaFinPlaneada;
    private Long semanaFinReal;
    private Long tareCodigo;
    private Long placCodigo_PsyPlanAccion;
    private Long respCodigo_PsyResponsable;
    private String nombre_PsyPlanAccion;
    private String nombre_PsyResponsable;

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre_PsyPlanAccion() {
		return nombre_PsyPlanAccion;
	}

	public void setNombre_PsyPlanAccion(String nombre_PsyPlanAccion) {
		this.nombre_PsyPlanAccion = nombre_PsyPlanAccion;
	}

	public String getNombre_PsyResponsable() {
		return nombre_PsyResponsable;
	}

	public void setNombre_PsyResponsable(String nombre_PsyResponsable) {
		this.nombre_PsyResponsable = nombre_PsyResponsable;
	}

	public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(String estadoTarea) {
        this.estadoTarea = estadoTarea;
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

    public Long getSemanaFinPlaneada() {
        return semanaFinPlaneada;
    }

    public void setSemanaFinPlaneada(Long semanaFinPlaneada) {
        this.semanaFinPlaneada = semanaFinPlaneada;
    }

    public Long getSemanaFinReal() {
        return semanaFinReal;
    }

    public void setSemanaFinReal(Long semanaFinReal) {
        this.semanaFinReal = semanaFinReal;
    }

    public Long getTareCodigo() {
        return tareCodigo;
    }

    public void setTareCodigo(Long tareCodigo) {
        this.tareCodigo = tareCodigo;
    }

    public Long getPlacCodigo_PsyPlanAccion() {
        return placCodigo_PsyPlanAccion;
    }

    public void setPlacCodigo_PsyPlanAccion(Long placCodigo_PsyPlanAccion) {
        this.placCodigo_PsyPlanAccion = placCodigo_PsyPlanAccion;
    }

    public Long getRespCodigo_PsyResponsable() {
        return respCodigo_PsyResponsable;
    }

    public void setRespCodigo_PsyResponsable(Long respCodigo_PsyResponsable) {
        this.respCodigo_PsyResponsable = respCodigo_PsyResponsable;
    }
}
