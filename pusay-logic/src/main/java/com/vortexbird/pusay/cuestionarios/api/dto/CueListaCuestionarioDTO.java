package com.vortexbird.pusay.cuestionarios.api.dto;

import java.io.Serializable;
import java.util.Date;

public class CueListaCuestionarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long consecutivo;
	private Date fechaHoraAsignacion;
	private Long consecutivo_CueCuestionario;
	private Long consecutivo_CueLista;

	public Long getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Date getFechaHoraAsignacion() {
		return fechaHoraAsignacion;
	}

	public void setFechaHoraAsignacion(Date fechaHoraAsignacion) {
		this.fechaHoraAsignacion = fechaHoraAsignacion;
	}

	public Long getConsecutivo_CueCuestionario() {
		return consecutivo_CueCuestionario;
	}

	public void setConsecutivo_CueCuestionario(Long consecutivo_CueCuestionario) {
		this.consecutivo_CueCuestionario = consecutivo_CueCuestionario;
	}

	public Long getConsecutivo_CueLista() {
		return consecutivo_CueLista;
	}

	public void setConsecutivo_CueLista(Long consecutivo_CueLista) {
		this.consecutivo_CueLista = consecutivo_CueLista;
	}
}
