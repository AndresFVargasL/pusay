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
public class PsyMatrizEncuestaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyMatrizEncuestaDTO.class);
    private String codigoEncuesta;
    private String estadoRegistro;
    private Long maenCodigo;
    private Long pestCodigo_PsyPlanEstrategico;

    public String getCodigoEncuesta() {
        return codigoEncuesta;
    }

    public void setCodigoEncuesta(String codigoEncuesta) {
        this.codigoEncuesta = codigoEncuesta;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Long getMaenCodigo() {
        return maenCodigo;
    }

    public void setMaenCodigo(Long maenCodigo) {
        this.maenCodigo = maenCodigo;
    }

    public Long getPestCodigo_PsyPlanEstrategico() {
        return pestCodigo_PsyPlanEstrategico;
    }

    public void setPestCodigo_PsyPlanEstrategico(
        Long pestCodigo_PsyPlanEstrategico) {
        this.pestCodigo_PsyPlanEstrategico = pestCodigo_PsyPlanEstrategico;
    }
}
