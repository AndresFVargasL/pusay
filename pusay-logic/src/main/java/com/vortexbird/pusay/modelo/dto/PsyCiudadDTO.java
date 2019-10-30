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
public class PsyCiudadDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyCiudadDTO.class);
    private Long ciudCodigo;
    private String estadoRegistro;
    private String nombre;
    private Long provCodigo_PsyProvincia;

    public Long getCiudCodigo() {
        return ciudCodigo;
    }

    public void setCiudCodigo(Long ciudCodigo) {
        this.ciudCodigo = ciudCodigo;
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

    public Long getProvCodigo_PsyProvincia() {
        return provCodigo_PsyProvincia;
    }

    public void setProvCodigo_PsyProvincia(Long provCodigo_PsyProvincia) {
        this.provCodigo_PsyProvincia = provCodigo_PsyProvincia;
    }
}
