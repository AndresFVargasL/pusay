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
public class PsyDetalleMapaEstrategicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyDetalleMapaEstrategicoDTO.class);
    private Long dmaeCodigo;
    private String estadoRegistro;
    private Long maesCodigo_PsyMapaEstrategico;
    private Long macoCodigo_PsyMatrizCorrelacion;

    public Long getDmaeCodigo() {
        return dmaeCodigo;
    }
    
    public void setDmaeCodigo(Long dmaeCodigo) {
        this.dmaeCodigo = dmaeCodigo;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Long getMaesCodigo_PsyMapaEstrategico() {
        return maesCodigo_PsyMapaEstrategico;
    }

    public void setMaesCodigo_PsyMapaEstrategico(
        Long maesCodigo_PsyMapaEstrategico) {
        this.maesCodigo_PsyMapaEstrategico = maesCodigo_PsyMapaEstrategico;
    }

    public Long getMacoCodigo_PsyMatrizCorrelacion() {
        return macoCodigo_PsyMatrizCorrelacion;
    }

    public void setMacoCodigo_PsyMatrizCorrelacion(
        Long macoCodigo_PsyMatrizCorrelacion) {
        this.macoCodigo_PsyMatrizCorrelacion = macoCodigo_PsyMatrizCorrelacion;
    }
}
