package com.vortexbird.pusay.cuestionarios.model;

import java.util.Date;

@SuppressWarnings("serial")
public class CueListaCuestionario implements java.io.Serializable {

	private Long consecutivo = null;
	private CueLista cueLista = null;
	private CueCuestionario cueCuestionario = null;
	private Date fechaHoraAsignacion = null;

	public CueListaCuestionario() {
	}

	public CueListaCuestionario(Long consecutivo, CueLista cueLista,
			CueCuestionario cueCuestionario, Date fechaHoraAsignacion) {
		this.consecutivo = consecutivo;
		this.cueLista = cueLista;
		this.cueCuestionario = cueCuestionario;
		this.fechaHoraAsignacion = fechaHoraAsignacion;
	}

	public Long getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public CueLista getCueLista() {
		return this.cueLista;
	}

	public void setCueLista(CueLista cueLista) {
		this.cueLista = cueLista;
	}

	public CueCuestionario getCueCuestionario() {
		return this.cueCuestionario;
	}

	public void setCueCuestionario(CueCuestionario cueCuestionario) {
		this.cueCuestionario = cueCuestionario;
	}

	public Date getFechaHoraAsignacion() {
		return this.fechaHoraAsignacion;
	}

	public void setFechaHoraAsignacion(Date fechaHoraAsignacion) {
		this.fechaHoraAsignacion = fechaHoraAsignacion;
	}

}
