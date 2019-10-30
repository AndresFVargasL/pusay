package com.vortexbird.pusay.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class PsyPlanEstrategicoAmbientalDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyPlanEstrategicoAmbientalDTO.class);
    private Long codigo;
    private String estadoPlan;
    private String estadoRegistro;
    private Date fechaFin;
    private Date fechaInico;
    private String nombre;
    private Long pestCodigo_PsyPlanEstrategico;
    private String nombrePest;
    private boolean disabled;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getEstadoPlan() {
        return estadoPlan;
    }

    public void setEstadoPlan(String estadoPlan) {
        this.estadoPlan = estadoPlan;
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

    public Date getFechaInico() {
        return fechaInico;
    }

    public void setFechaInico(Date fechaInico) {
        this.fechaInico = fechaInico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPestCodigo_PsyPlanEstrategico() {
        return pestCodigo_PsyPlanEstrategico;
    }

    public void setPestCodigo_PsyPlanEstrategico(
        Long pestCodigo_PsyPlanEstrategico) {
        this.pestCodigo_PsyPlanEstrategico = pestCodigo_PsyPlanEstrategico;
    }

	public String getNombrePest() {
		return nombrePest;
	}

	public void setNombrePest(String nombrePest) {
		this.nombrePest = nombrePest;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
}
