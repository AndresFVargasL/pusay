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
public class PsyDetalleObjetivoPlanDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyDetalleObjetivoPlanDTO.class);
    private String descripcion;
    private Long dobpCodigo;
    private String estadoRegistro;
    private String nombre;
    private Long obesCodigo_PsyObjetivoEstrategico;
    private Long obplCodigo_PsyObjetivoPlan;
    private String codigoObjetivoEstrategico;
    private String nombreObjetivoEstrategico;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getDobpCodigo() {
        return dobpCodigo;
    }

    public void setDobpCodigo(Long dobpCodigo) {
        this.dobpCodigo = dobpCodigo;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getObesCodigo_PsyObjetivoEstrategico() {
        return obesCodigo_PsyObjetivoEstrategico;
    }

    public void setObesCodigo_PsyObjetivoEstrategico(
        Long obesCodigo_PsyObjetivoEstrategico) {
        this.obesCodigo_PsyObjetivoEstrategico = obesCodigo_PsyObjetivoEstrategico;
    }

    public Long getObplCodigo_PsyObjetivoPlan() {
        return obplCodigo_PsyObjetivoPlan;
    }

    public void setObplCodigo_PsyObjetivoPlan(Long obplCodigo_PsyObjetivoPlan) {
        this.obplCodigo_PsyObjetivoPlan = obplCodigo_PsyObjetivoPlan;
    }

	public String getCodigoObjetivoEstrategico() {
		return codigoObjetivoEstrategico;
	}

	public void setCodigoObjetivoEstrategico(String codigoObjetivoEstrategico) {
		this.codigoObjetivoEstrategico = codigoObjetivoEstrategico;
	}

	public String getNombreObjetivoEstrategico() {
		return nombreObjetivoEstrategico;
	}

	public void setNombreObjetivoEstrategico(String nombreObjetivoEstrategico) {
		this.nombreObjetivoEstrategico = nombreObjetivoEstrategico;
	}


}

