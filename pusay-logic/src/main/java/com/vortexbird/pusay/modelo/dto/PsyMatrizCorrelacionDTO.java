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
public class PsyMatrizCorrelacionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyMatrizCorrelacionDTO.class);
    private String estadoRegistro;
    private Long macoCodigo;
    private Long esamCodigo_PsyEstrategiaAmbiental;
    private String nombreEstrategiaAmbiental;
    private Long imamCodigo_PsyImpactoAmbiental;
    private String nombreImpactoAmbiental;
    private Long obesCodigo_PsyObjetivoEstrategico;
    private String nombreObjetivoEstrategico;

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Long getMacoCodigo() {
        return macoCodigo;
    }

    public void setMacoCodigo(Long macoCodigo) {
        this.macoCodigo = macoCodigo;
    }

    public Long getEsamCodigo_PsyEstrategiaAmbiental() {
        return esamCodigo_PsyEstrategiaAmbiental;
    }

    public void setEsamCodigo_PsyEstrategiaAmbiental(
        Long esamCodigo_PsyEstrategiaAmbiental) {
        this.esamCodigo_PsyEstrategiaAmbiental = esamCodigo_PsyEstrategiaAmbiental;
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

	public String getNombreEstrategiaAmbiental() {
		return nombreEstrategiaAmbiental;
	}

	public void setNombreEstrategiaAmbiental(String nombreEstrategiaAmbiental) {
		this.nombreEstrategiaAmbiental = nombreEstrategiaAmbiental;
	}

	public String getNombreImpactoAmbiental() {
		return nombreImpactoAmbiental;
	}

	public void setNombreImpactoAmbiental(String nombreImpactoAmbiental) {
		this.nombreImpactoAmbiental = nombreImpactoAmbiental;
	}

	public String getNombreObjetivoEstrategico() {
		return nombreObjetivoEstrategico;
	}

	public void setNombreObjetivoEstrategico(String nombreObjetivoEstrategico) {
		this.nombreObjetivoEstrategico = nombreObjetivoEstrategico;
	}
}
