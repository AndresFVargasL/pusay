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
public class PsyPlanEstrategiaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyPlanEstrategiaDTO.class);
    private String estadoRegistro;
    private Long plesCodigo;
    private Long dmaeCodigo_PsyDetalleMapaEstrategico;
    private Long progCodigo_PsyPrograma;

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Long getPlesCodigo() {
        return plesCodigo;
    }

    public void setPlesCodigo(Long plesCodigo) {
        this.plesCodigo = plesCodigo;
    }

    public Long getDmaeCodigo_PsyDetalleMapaEstrategico() {
        return dmaeCodigo_PsyDetalleMapaEstrategico;
    }

    public void setDmaeCodigo_PsyDetalleMapaEstrategico(
        Long dmaeCodigo_PsyDetalleMapaEstrategico) {
        this.dmaeCodigo_PsyDetalleMapaEstrategico = dmaeCodigo_PsyDetalleMapaEstrategico;
    }

	public Long getProgCodigo_PsyPrograma() {
		return progCodigo_PsyPrograma;
	}

	public void setProgCodigo_PsyPrograma(Long progCodigo_PsyPrograma) {
		this.progCodigo_PsyPrograma = progCodigo_PsyPrograma;
	}
    
    
    

}
