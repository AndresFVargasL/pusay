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
public class PsyPlanEstrategicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyPlanEstrategicoDTO.class);
    private String descripcion;
    private String estadoPlan;
    private String estadoRegistro;
    private Date fechaFin;
    private Date fechaInicio;
    private String nombre;
    private Long pestCodigo;
    private Long emprCodigo_PsyEmpresa;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPestCodigo() {
        return pestCodigo;
    }

    public void setPestCodigo(Long pestCodigo) {
        this.pestCodigo = pestCodigo;
    }

    public Long getEmprCodigo_PsyEmpresa() {
        return emprCodigo_PsyEmpresa;
    }

    public void setEmprCodigo_PsyEmpresa(Long emprCodigo_PsyEmpresa) {
        this.emprCodigo_PsyEmpresa = emprCodigo_PsyEmpresa;
    }
}
