package com.vortexbird.pusay.cuestionarios.api.dto;

import java.io.Serializable;
import java.util.Date;

public class CueRespuestaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long consecutivo;
	private Date fechaHoraRespuesta;
	private String ip;
	private Long consecutivo_CueListaContacto;
	private Long consecutivo_CueOpcion;

	public Long getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Date getFechaHoraRespuesta() {
		return fechaHoraRespuesta;
	}

	public void setFechaHoraRespuesta(Date fechaHoraRespuesta) {
		this.fechaHoraRespuesta = fechaHoraRespuesta;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getConsecutivo_CueListaContacto() {
		return consecutivo_CueListaContacto;
	}

	public void setConsecutivo_CueListaContacto(
			Long consecutivo_CueListaContacto) {
		this.consecutivo_CueListaContacto = consecutivo_CueListaContacto;
	}

	public Long getConsecutivo_CueOpcion() {
		return consecutivo_CueOpcion;
	}

	public void setConsecutivo_CueOpcion(Long consecutivo_CueOpcion) {
		this.consecutivo_CueOpcion = consecutivo_CueOpcion;
	}
}
