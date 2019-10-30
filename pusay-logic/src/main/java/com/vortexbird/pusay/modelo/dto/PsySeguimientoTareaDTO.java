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
public class PsySeguimientoTareaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsySeguimientoTareaDTO.class);
    private String descripcion;
    private String estadoRegistro;
    private Date fecha;
    private Long setaCodigo;
    private Long tareCodigo_PsyTarea;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getSetaCodigo() {
        return setaCodigo;
    }

    public void setSetaCodigo(Long setaCodigo) {
        this.setaCodigo = setaCodigo;
    }

    public Long getTareCodigo_PsyTarea() {
        return tareCodigo_PsyTarea;
    }

    public void setTareCodigo_PsyTarea(Long tareCodigo_PsyTarea) {
        this.tareCodigo_PsyTarea = tareCodigo_PsyTarea;
    }
}
