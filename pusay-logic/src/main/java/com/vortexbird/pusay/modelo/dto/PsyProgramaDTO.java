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
public class PsyProgramaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyProgramaDTO.class);
    private Long progCodigo;
    private String nombre;
    private String estadoRegistro;
    private Long dmaeCodigo;
    private Long macoCodigo;
    private Long maesCodigo;
    private String tipo;
    private String dmaeEstadoRegistro;
    
    

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

    public Long getProgCodigo() {
        return progCodigo;
    }

    public void setProgCodigo(Long progCodigo) {
        this.progCodigo = progCodigo;
    }

	public Long getDmaeCodigo() {
		return dmaeCodigo;
	}

	public void setDmaeCodigo(Long dmaeCodigo) {
		this.dmaeCodigo = dmaeCodigo;
	}

	public Long getMacoCodigo() {
		return macoCodigo;
	}

	public void setMacoCodigo(Long macoCodigo) {
		this.macoCodigo = macoCodigo;
	}

	public Long getMaesCodigo() {
		return maesCodigo;
	}

	public void setMaesCodigo(Long maesCodigo) {
		this.maesCodigo = maesCodigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDmaeEstadoRegistro() {
		return dmaeEstadoRegistro;
	}

	public void setDmaeEstadoRegistro(String dmaeEstadoRegistro) {
		this.dmaeEstadoRegistro = dmaeEstadoRegistro;
	}
}
