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
public class PsyDetalleEridaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyDetalleEridaDTO.class);
    private Long calificacion;
    private Long deerCodigo;
    private String estadoRegistro;
    private Double peso;
    private Double valoracion;
    private Long asamCodigo_PsyAsuntoAmbiental;
    private Long imamCodigo_PsyImpactoAmbiental;
    private Long maerCodigo_PsyMatrizErida;

    public Long getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Long calificacion) {
        this.calificacion = calificacion;
    }

    public Long getDeerCodigo() {
        return deerCodigo;
    }

    public void setDeerCodigo(Long deerCodigo) {
        this.deerCodigo = deerCodigo;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getValoracion() {
        return valoracion;
    }

    public void setValoracion(Double valoracion) {
        this.valoracion = valoracion;
    }

    public Long getAsamCodigo_PsyAsuntoAmbiental() {
        return asamCodigo_PsyAsuntoAmbiental;
    }

    public void setAsamCodigo_PsyAsuntoAmbiental(
        Long asamCodigo_PsyAsuntoAmbiental) {
        this.asamCodigo_PsyAsuntoAmbiental = asamCodigo_PsyAsuntoAmbiental;
    }

    public Long getImamCodigo_PsyImpactoAmbiental() {
        return imamCodigo_PsyImpactoAmbiental;
    }

    public void setImamCodigo_PsyImpactoAmbiental(
        Long imamCodigo_PsyImpactoAmbiental) {
        this.imamCodigo_PsyImpactoAmbiental = imamCodigo_PsyImpactoAmbiental;
    }

    public Long getMaerCodigo_PsyMatrizErida() {
        return maerCodigo_PsyMatrizErida;
    }

    public void setMaerCodigo_PsyMatrizErida(Long maerCodigo_PsyMatrizErida) {
        this.maerCodigo_PsyMatrizErida = maerCodigo_PsyMatrizErida;
    }
}
