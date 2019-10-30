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
public class PsyPresupuestoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyPresupuestoDTO.class);
    private String estadoPresupuesto;
    private String estadoRegistro;
    private Date fechaFin;
    private Date fechaInicio;
    private Long presCodigo;
    private Long moneCodigo_PsyMoneda;
    private Long placCodigo_PsyPlanAccion;
    private String nombrePlanAccion;
    private String abrevMoneda;
    private String estadoPresupuestoInterpretado;
    private String estadoRegistroInterpretado;
    private boolean btnGenerarPresupuestoDefinitivo;

    public String getEstadoPresupuesto() {
        return estadoPresupuesto;
    }

    public void setEstadoPresupuesto(String estadoPresupuesto) {
        this.estadoPresupuesto = estadoPresupuesto;
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

    public Long getPresCodigo() {
        return presCodigo;
    }

    public void setPresCodigo(Long presCodigo) {
        this.presCodigo = presCodigo;
    }

    public Long getMoneCodigo_PsyMoneda() {
        return moneCodigo_PsyMoneda;
    }

    public void setMoneCodigo_PsyMoneda(Long moneCodigo_PsyMoneda) {
        this.moneCodigo_PsyMoneda = moneCodigo_PsyMoneda;
    }

    public Long getPlacCodigo_PsyPlanAccion() {
        return placCodigo_PsyPlanAccion;
    }

    public void setPlacCodigo_PsyPlanAccion(Long placCodigo_PsyPlanAccion) {
        this.placCodigo_PsyPlanAccion = placCodigo_PsyPlanAccion;
    }

	public String getNombrePlanAccion() {
		return nombrePlanAccion;
	}

	public void setNombrePlanAccion(String nombrePlanAccion) {
		this.nombrePlanAccion = nombrePlanAccion;
	}

	public String getAbrevMoneda() {
		return abrevMoneda;
	}

	public void setAbrevMoneda(String abrevMoneda) {
		this.abrevMoneda = abrevMoneda;
	}

	public String getEstadoPresupuestoInterpretado() {
		return estadoPresupuestoInterpretado;
	}

	public void setEstadoPresupuestoInterpretado(
			String estadoPresupuestoInterpretado) {
		this.estadoPresupuestoInterpretado = estadoPresupuestoInterpretado;
	}

	public String getEstadoRegistroInterpretado() {
		return estadoRegistroInterpretado;
	}

	public void setEstadoRegistroInterpretado(String estadoRegistroInterpretado) {
		this.estadoRegistroInterpretado = estadoRegistroInterpretado;
	}

	public boolean isBtnGenerarPresupuestoDefinitivo() {
		return btnGenerarPresupuestoDefinitivo;
	}

	public void setBtnGenerarPresupuestoDefinitivo(
			boolean btnGenerarPresupuestoDefinitivo) {
		this.btnGenerarPresupuestoDefinitivo = btnGenerarPresupuestoDefinitivo;
	}
	
	
}
