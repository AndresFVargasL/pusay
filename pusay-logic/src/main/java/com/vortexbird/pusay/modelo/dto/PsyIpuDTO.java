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
public class PsyIpuDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyIpuDTO.class);
   private Long ipuCodigo;    
    private Long placCodigo_PsyPlanAccion;
    private Date fechaInforme;
    private String periodo;
    private Date periodoFechaInicio;
    private Date periodoFechaFin;
    private String estadoIpu;
    private String estadoRegistro;
    private Double avancePresupuestado;
    private Double avanceReal;
    private String causasDesviacion;
    private String accionesPropuestas;
    private String logrosAlcanzados;
    private String logrosNoAlcanzados;
    private String tipoIpu;
    private Double variacion;

    public String getEstadoIpu() {
        return estadoIpu;
    }

    public void setEstadoIpu(String estadoIpu) {
        this.estadoIpu = estadoIpu;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Date getFechaInforme() {
        return fechaInforme;
    }

    public void setFechaInforme(Date fechaInforme) {
        this.fechaInforme = fechaInforme;
    }

    public Long getIpuCodigo() {
        return ipuCodigo;
    }

    public void setIpuCodigo(Long ipuCodigo) {
        this.ipuCodigo = ipuCodigo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Date getPeriodoFechaFin() {
        return periodoFechaFin;
    }

    public void setPeriodoFechaFin(Date periodoFechaFin) {
        this.periodoFechaFin = periodoFechaFin;
    }

    public Date getPeriodoFechaInicio() {
        return periodoFechaInicio;
    }

    public void setPeriodoFechaInicio(Date periodoFechaInicio) {
        this.periodoFechaInicio = periodoFechaInicio;
    }

    public Long getPlacCodigo_PsyPlanAccion() {
        return placCodigo_PsyPlanAccion;
    }

    public void setPlacCodigo_PsyPlanAccion(Long placCodigo_PsyPlanAccion) {
        this.placCodigo_PsyPlanAccion = placCodigo_PsyPlanAccion;
    }

	public Double getAvancePresupuestado() {
		return avancePresupuestado;
	}

	public void setAvancePresupuestado(Double avancePresupuestado) {
		this.avancePresupuestado = avancePresupuestado;
	}

	public Double getAvanceReal() {
		return avanceReal;
	}

	public void setAvanceReal(Double avanceReal) {
		this.avanceReal = avanceReal;
	}

	public String getCausasDesviacion() {
		return causasDesviacion;
	}

	public void setCausasDesviacion(String causasDesviacion) {
		this.causasDesviacion = causasDesviacion;
	}

	public String getAccionesPropuestas() {
		return accionesPropuestas;
	}

	public void setAccionesPropuestas(String accionesPropuestas) {
		this.accionesPropuestas = accionesPropuestas;
	}

	public String getLogrosAlcanzados() {
		return logrosAlcanzados;
	}

	public void setLogrosAlcanzados(String logrosAlcanzados) {
		this.logrosAlcanzados = logrosAlcanzados;
	}

	public String getLogrosNoAlcanzados() {
		return logrosNoAlcanzados;
	}

	public void setLogrosNoAlcanzados(String logrosNoAlcanzados) {
		this.logrosNoAlcanzados = logrosNoAlcanzados;
	}

	public String getTipoIpu() {
		return tipoIpu;
	}

	public void setTipoIpu(String tipoIpu) {
		this.tipoIpu = tipoIpu;
	}

	public Double getVariacion() {
		return variacion;
	}

	public void setVariacion(Double variacion) {
		this.variacion = variacion;
	}

	
    
    
}
