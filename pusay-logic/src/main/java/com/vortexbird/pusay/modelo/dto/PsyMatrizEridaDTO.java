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
public class PsyMatrizEridaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyMatrizEridaDTO.class);
    private String estadoErida;
    private String estadoRegistro;
    private Date fechaFin;
    private Date fechaInicio;
    private Long maerCodigo;
    private Long pestCodigo_PsyPlanEstrategico;

    public String getEstadoErida() {
        return estadoErida;
    }

    public void setEstadoErida(String estadoErida) {
        this.estadoErida = estadoErida;
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

    public Long getMaerCodigo() {
        return maerCodigo;
    }

    public void setMaerCodigo(Long maerCodigo) {
        this.maerCodigo = maerCodigo;
    }

    public Long getPestCodigo_PsyPlanEstrategico() {
        return pestCodigo_PsyPlanEstrategico;
    }

    public void setPestCodigo_PsyPlanEstrategico(
        Long pestCodigo_PsyPlanEstrategico) {
        this.pestCodigo_PsyPlanEstrategico = pestCodigo_PsyPlanEstrategico;
    }
}
