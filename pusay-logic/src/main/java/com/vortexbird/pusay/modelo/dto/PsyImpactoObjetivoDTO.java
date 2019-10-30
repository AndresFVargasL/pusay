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
public class PsyImpactoObjetivoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyImpactoObjetivoDTO.class);
    private String estadoRegistro;
    private Long imobCodigo;
    private Long imamCodigo_PsyImpactoAmbiental;
    private Long codigo_PsyObjetivoAmbiental;
    private String imamNombre;
    private String obamNombre;

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Long getImobCodigo() {
        return imobCodigo;
    }

    public void setImobCodigo(Long imobCodigo) {
        this.imobCodigo = imobCodigo;
    }

    public Long getImamCodigo_PsyImpactoAmbiental() {
        return imamCodigo_PsyImpactoAmbiental;
    }

    public void setImamCodigo_PsyImpactoAmbiental(
        Long imamCodigo_PsyImpactoAmbiental) {
        this.imamCodigo_PsyImpactoAmbiental = imamCodigo_PsyImpactoAmbiental;
    }

    public Long getCodigo_PsyObjetivoAmbiental() {
        return codigo_PsyObjetivoAmbiental;
    }

    public void setCodigo_PsyObjetivoAmbiental(Long codigo_PsyObjetivoAmbiental) {
        this.codigo_PsyObjetivoAmbiental = codigo_PsyObjetivoAmbiental;
    }

	public String getImamNombre() {
		return imamNombre;
	}

	public void setImamNombre(String imamNombre) {
		this.imamNombre = imamNombre;
	}

	public String getObamNombre() {
		return obamNombre;
	}

	public void setObamNombre(String obamNombre) {
		this.obamNombre = obamNombre;
	}
    
    
}
