package com.vortexbird.pusay.modelo.dto;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PsyDetalleEstrategiasDTO  implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyDetalleMapaEstrategicoDTO.class);
    private Long dmaeCodigo;
    private String estadoRegistro;
    private Long maesCodigo;
    private Long macoCodigo;
    private Long esamCodigo;
    private String esamNombre;
    private String obesNombre;
    private String imamNombre;
    private String tipo;

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

	public Long getMaesCodigo() {
		return maesCodigo;
	}

	public void setMaesCodigo(Long maesCodigo) {
		this.maesCodigo = maesCodigo;
	}

	public Long getMacoCodigo() {
		return macoCodigo;
	}

	public void setMacoCodigo(Long macoCodigo) {
		this.macoCodigo = macoCodigo;
	}

	public Long getEsamCodigo() {
		return esamCodigo;
	}

	public void setEsamCodigo(Long esamCodigo) {
		this.esamCodigo = esamCodigo;
	}

	public String getEsamNombre() {
		return esamNombre;
	}

	public void setEsamNombre(String esamNombre) {
		this.esamNombre = esamNombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getObesNombre() {
		return obesNombre;
	}

	public void setObesNombre(String obesNombre) {
		this.obesNombre = obesNombre;
	}

	public String getImamNombre() {
		return imamNombre;
	}

	public void setImamNombre(String imamNombre) {
		this.imamNombre = imamNombre;
	}
	
	

    
}
