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
public class PsyItemPresupuestoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyItemPresupuestoDTO.class);
    private String estadoRegistro;
    private Date fechaFin;
    private Date fechaInicio;
    private Long ipreCodigo;
    private Double valorEjecutado;
    private Double valorPresupuestado;
    private Long presCodigo_PsyPresupuesto;
    private Long tiipCodigo_PsyTipoItemPresupuesto;
    private String tipoNombre;
    private String nombrePlanAccion;
    private Long semana;
    
    
    public String getTipoNombre() {
		return tipoNombre;
	}

	public void setTipoNombre(String tipoNombre) {
		this.tipoNombre = tipoNombre;
	}

	public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Long getIpreCodigo() {
        return ipreCodigo;
    }

    public void setIpreCodigo(Long ipreCodigo) {
        this.ipreCodigo = ipreCodigo;
    }

    public Double getValorEjecutado() {
        return valorEjecutado;
    }

    public void setValorEjecutado(Double valorEjecutado) {
        this.valorEjecutado = valorEjecutado;
    }

    public Double getValorPresupuestado() {
        return valorPresupuestado;
    }

    public void setValorPresupuestado(Double valorPresupuestado) {
        this.valorPresupuestado = valorPresupuestado;
    }

    public Long getPresCodigo_PsyPresupuesto() {
        return presCodigo_PsyPresupuesto;
    }

    public void setPresCodigo_PsyPresupuesto(Long presCodigo_PsyPresupuesto) {
        this.presCodigo_PsyPresupuesto = presCodigo_PsyPresupuesto;
    }

    public Long getTiipCodigo_PsyTipoItemPresupuesto() {
        return tiipCodigo_PsyTipoItemPresupuesto;
    }

    public void setTiipCodigo_PsyTipoItemPresupuesto(
        Long tiipCodigo_PsyTipoItemPresupuesto) {
        this.tiipCodigo_PsyTipoItemPresupuesto = tiipCodigo_PsyTipoItemPresupuesto;
    }
    

	public Long getSemana() {
		return semana;
	}

	public void setSemana(Long semana) {
		this.semana = semana;
	}

	public String getNombrePlanAccion() {
		return nombrePlanAccion;
	}

	public void setNombrePlanAccion(String nombrePlanAccion) {
		this.nombrePlanAccion = nombrePlanAccion;
	}
	
	
    
}
