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
public class PsyEvaluacionPeaObjetivoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyEvaluacionPeaObjetivoDTO.class);
    private Long codigo;
    private String estadoRegistro;
    private Double historial;
    private Long periodo;
    private Double resultado;
    private Long codigo_PsyObjetivoAmbiental;
    private Long codigo_PsyPlanEstrategicoAmbiental;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    
    public Double getHistorial() {
        return historial;
    }

    public void setHistorial(Double historial) {
        this.historial = historial;
    }

    public Long getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Long periodo) {
        this.periodo = periodo;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public Long getCodigo_PsyObjetivoAmbiental() {
        return codigo_PsyObjetivoAmbiental;
    }

    public void setCodigo_PsyObjetivoAmbiental(Long codigo_PsyObjetivoAmbiental) {
        this.codigo_PsyObjetivoAmbiental = codigo_PsyObjetivoAmbiental;
    }

    public Long getCodigo_PsyPlanEstrategicoAmbiental() {
        return codigo_PsyPlanEstrategicoAmbiental;
    }

    public void setCodigo_PsyPlanEstrategicoAmbiental(
        Long codigo_PsyPlanEstrategicoAmbiental) {
        this.codigo_PsyPlanEstrategicoAmbiental = codigo_PsyPlanEstrategicoAmbiental;
    }
}
