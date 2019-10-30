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
public class PsyObjetivoImpactoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyObjetivoImpactoDTO.class);
    private String estadoRegistro;
    private Long obimCodigo;
    private Long imamCodigo_PsyImpactoAmbiental;
    private Long obesCodigo_PsyObjetivoEstrategico;
    private String imamNombre;
    private String obesNombre;

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Long getObimCodigo() {
        return obimCodigo;
    }

    public void setObimCodigo(Long obimCodigo) {
        this.obimCodigo = obimCodigo;
    }

    public Long getImamCodigo_PsyImpactoAmbiental() {
        return imamCodigo_PsyImpactoAmbiental;
    }

    public void setImamCodigo_PsyImpactoAmbiental(
        Long imamCodigo_PsyImpactoAmbiental) {
        this.imamCodigo_PsyImpactoAmbiental = imamCodigo_PsyImpactoAmbiental;
    }

    public Long getObesCodigo_PsyObjetivoEstrategico() {
        return obesCodigo_PsyObjetivoEstrategico;
    }

    public void setObesCodigo_PsyObjetivoEstrategico(
        Long obesCodigo_PsyObjetivoEstrategico) {
        this.obesCodigo_PsyObjetivoEstrategico = obesCodigo_PsyObjetivoEstrategico;
    }

	public String getImamNombre() {
		return imamNombre;
	}

	public void setImamNombre(String imamNombre) {
		this.imamNombre = imamNombre;
	}

	public String getObesNombre() {
		return obesNombre;
	}

	public void setObesNombre(String obesNombre) {
		this.obesNombre = obesNombre;
	}
    
    
}
