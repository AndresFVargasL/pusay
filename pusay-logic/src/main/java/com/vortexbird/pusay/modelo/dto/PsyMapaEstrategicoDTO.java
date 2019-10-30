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
public class PsyMapaEstrategicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyMapaEstrategicoDTO.class);
    private String estadoMapaEstrategico;
    private String estadoRegistro;
    private Date fechaFin;
    private Date fechaInicio;
    private Long maesCodigo;
    private Long pestCodigo_PsyPlanEstrategico;

    public String getEstadoMapaEstrategico() {
        return estadoMapaEstrategico;
    }

    public void setEstadoMapaEstrategico(String estadoMapaEstrategico) {
        this.estadoMapaEstrategico = estadoMapaEstrategico;
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

    public Long getMaesCodigo() {
        return maesCodigo;
    }

    public void setMaesCodigo(Long maesCodigo) {
        this.maesCodigo = maesCodigo;
    }

    public Long getPestCodigo_PsyPlanEstrategico() {
        return pestCodigo_PsyPlanEstrategico;
    }

    public void setPestCodigo_PsyPlanEstrategico(
        Long pestCodigo_PsyPlanEstrategico) {
        this.pestCodigo_PsyPlanEstrategico = pestCodigo_PsyPlanEstrategico;
    }
}
