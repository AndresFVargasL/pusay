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
public class PsyTemaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyTemaDTO.class);
    private Long codigo;
    private String descripcion;
    private String estadoRegistro;
    private Long imamCodigo_PsyImpactoAmbiental;
    private String imamNombre;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

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

    public Long getImamCodigo_PsyImpactoAmbiental() {
        return imamCodigo_PsyImpactoAmbiental;
    }

    public void setImamCodigo_PsyImpactoAmbiental(
        Long imamCodigo_PsyImpactoAmbiental) {
        this.imamCodigo_PsyImpactoAmbiental = imamCodigo_PsyImpactoAmbiental;
    }

	public String getImamNombre() {
		return imamNombre;
	}

	public void setImamNombre(String imamNombre) {
		this.imamNombre = imamNombre;
	}
}
