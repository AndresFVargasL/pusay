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
public class PsyProvinciaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyProvinciaDTO.class);
    private String estadoRegistro;
    private String nombre;
    private Long provCodigo;
    private Long paisCodigo_PsyPais;
    private String nombrePais;

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

    public Long getProvCodigo() {
        return provCodigo;
    }

    public void setProvCodigo(Long provCodigo) {
        this.provCodigo = provCodigo;
    }

    public Long getPaisCodigo_PsyPais() {
        return paisCodigo_PsyPais;
    }

    public void setPaisCodigo_PsyPais(Long paisCodigo_PsyPais) {
        this.paisCodigo_PsyPais = paisCodigo_PsyPais;
    }

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
}
