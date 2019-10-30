package com.vortexbird.pusay.cuestionarios.api.dto;

import java.io.Serializable;
import java.util.Date;

public class CueListaContactoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long consecutivo;
	private Long duracion;
	private Long estado;
	private Date fechaHoraAsignacion;
	private Date fechaHoraFinalizacion;
	private Long puntajeTotal;
	private Long identificacion_CueContacto;
	private Long consecutivo_CueLista;

	public Long getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Long getDuracion() {
		return duracion;
	}

	public void setDuracion(Long duracion) {
		this.duracion = duracion;
	}

	public Long getEstado() {
		return estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public Date getFechaHoraAsignacion() {
		return fechaHoraAsignacion;
	}

	public void setFechaHoraAsignacion(Date fechaHoraAsignacion) {
		this.fechaHoraAsignacion = fechaHoraAsignacion;
	}

	public Date getFechaHoraFinalizacion() {
		return fechaHoraFinalizacion;
	}

	public void setFechaHoraFinalizacion(Date fechaHoraFinalizacion) {
		this.fechaHoraFinalizacion = fechaHoraFinalizacion;
	}

	public Long getPuntajeTotal() {
		return puntajeTotal;
	}

	public void setPuntajeTotal(Long puntajeTotal) {
		this.puntajeTotal = puntajeTotal;
	}

	public Long getIdentificacion_CueContacto() {
		return identificacion_CueContacto;
	}

	public void setIdentificacion_CueContacto(Long identificacion_CueContacto) {
		this.identificacion_CueContacto = identificacion_CueContacto;
	}

	public Long getConsecutivo_CueLista() {
		return consecutivo_CueLista;
	}

	public void setConsecutivo_CueLista(Long consecutivo_CueLista) {
		this.consecutivo_CueLista = consecutivo_CueLista;
	}
}
