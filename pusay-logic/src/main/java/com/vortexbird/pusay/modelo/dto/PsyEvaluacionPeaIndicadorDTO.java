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
public class PsyEvaluacionPeaIndicadorDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyEvaluacionPeaIndicadorDTO.class);
    private Long codigo;
    private String estadoRegistro;
    private Double historial;
    private Double meta;
    private String multiple;
    private Double norma;
    private Long periodo;
    private Double resultado;
    private Double sectorial;
    private Long codigo_PsyIndicador;
    private Long codigo_PsyPlanEstrategicoAmbiental;
    private String nombreIndicador;
    private String nombrePlanEstrategicoAmiental;
    private String colorResultado;
    private String colorMeta;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    
    public Double getHistorial() {
        return historial;
    }

    public void setHistorial(Double historial) {
        this.historial = historial;
    }

    public Double getMeta() {
        return meta;
    }

    public void setMeta(Double meta) {
        this.meta = meta;
    }

    public String getMultiple() {
        return multiple;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    public Double getNorma() {
        return norma;
    }

    public void setNorma(Double norma) {
        this.norma = norma;
    }

    public Long getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Long periodo) {
        this.periodo = periodo;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public Double getSectorial() {
        return sectorial;
    }

    public void setSectorial(Double sectorial) {
        this.sectorial = sectorial;
    }

    public Long getCodigo_PsyIndicador() {
        return codigo_PsyIndicador;
    }

    public void setCodigo_PsyIndicador(Long codigo_PsyIndicador) {
        this.codigo_PsyIndicador = codigo_PsyIndicador;
    }

    public Long getCodigo_PsyPlanEstrategicoAmbiental() {
        return codigo_PsyPlanEstrategicoAmbiental;
    }

    public void setCodigo_PsyPlanEstrategicoAmbiental(
        Long codigo_PsyPlanEstrategicoAmbiental) {
        this.codigo_PsyPlanEstrategicoAmbiental = codigo_PsyPlanEstrategicoAmbiental;
    }

	public String getNombreIndicador() {
		return nombreIndicador;
	}

	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}

	public String getNombrePlanEstrategicoAmiental() {
		return nombrePlanEstrategicoAmiental;
	}

	public void setNombrePlanEstrategicoAmiental(
			String nombrePlanEstrategicoAmiental) {
		this.nombrePlanEstrategicoAmiental = nombrePlanEstrategicoAmiental;
	}

	public String getColorResultado() {
		return colorResultado;
	}

	public void setColorResultado(String colorResultado) {
		this.colorResultado = colorResultado;
	}

	public String getColorMeta() {
		return colorMeta;
	}

	public void setColorMeta(String colorMeta) {
		this.colorMeta = colorMeta;
	}
}
