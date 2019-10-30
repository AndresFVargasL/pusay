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
public class PsyIndicadorGestionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyIndicadorGestionDTO.class);
    private Long codigo;
    private String descripcion;
    private String estadoRegistro;
    private String tipoIndicador;
    private String unidadMedida;
    private Long codigo_PsyObjetivoAmbiental;
    private Long codigo_PsySubtema;
    private Long codigoTema;
    private String tema;
    private String subTema;
    private String obamNombre;
    

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

    public String getTipoIndicador() {
        return tipoIndicador;
    }

    public void setTipoIndicador(String tipoIndicador) {
        this.tipoIndicador = tipoIndicador;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Long getCodigo_PsyObjetivoAmbiental() {
        return codigo_PsyObjetivoAmbiental;
    }

    public void setCodigo_PsyObjetivoAmbiental(Long codigo_PsyObjetivoAmbiental) {
        this.codigo_PsyObjetivoAmbiental = codigo_PsyObjetivoAmbiental;
    }

    public Long getCodigo_PsySubtema() {
        return codigo_PsySubtema;
    }

    public void setCodigo_PsySubtema(Long codigo_PsySubtema) {
        this.codigo_PsySubtema = codigo_PsySubtema;
    }

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getSubTema() {
		return subTema;
	}

	public void setSubTema(String subTema) {
		this.subTema = subTema;
	}

	public String getObamNombre() {
		return obamNombre;
	}

	public void setObamNombre(String obamNombre) {
		this.obamNombre = obamNombre;
	}

	public Long getCodigoTema() {
		return codigoTema;
	}

	public void setCodigoTema(Long codigoTema) {
		this.codigoTema = codigoTema;
	}

}
