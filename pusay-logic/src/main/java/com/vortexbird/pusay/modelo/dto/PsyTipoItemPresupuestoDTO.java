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
public class PsyTipoItemPresupuestoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyTipoItemPresupuestoDTO.class);
    private String descripcion;
    private String estadoRegistro;
    private String nombre;
    private Long tiipCodigo;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getTiipCodigo() {
        return tiipCodigo;
    }

    public void setTiipCodigo(Long tiipCodigo) {
        this.tiipCodigo = tiipCodigo;
    }
}
