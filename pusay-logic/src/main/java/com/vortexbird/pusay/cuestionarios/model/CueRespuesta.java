package com.vortexbird.pusay.cuestionarios.model;

import java.util.Date;

@SuppressWarnings("serial")
public class CueRespuesta implements java.io.Serializable {

	private Long consecutivo = null;
	private CueOpcion cueOpcion = null;
	private CueListaContacto cueListaContacto = null;
	private Date fechaHoraRespuesta = null;
	private String ip = null;
	private String respuestaAmpliacion = null;

	public CueRespuesta() {
	}

	public CueRespuesta(Long consecutivo, CueOpcion cueOpcion,
			CueListaContacto cueListaContacto, Date fechaHoraRespuesta,
			String ip) {
		this.consecutivo = consecutivo;
		this.cueOpcion = cueOpcion;
		this.cueListaContacto = cueListaContacto;
		this.fechaHoraRespuesta = fechaHoraRespuesta;
		this.ip = ip;
	}

	public Long getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public CueOpcion getCueOpcion() {
		return this.cueOpcion;
	}

	public void setCueOpcion(CueOpcion cueOpcion) {
		this.cueOpcion = cueOpcion;
	}

	public CueListaContacto getCueListaContacto() {
		return this.cueListaContacto;
	}

	public void setCueListaContacto(CueListaContacto cueListaContacto) {
		this.cueListaContacto = cueListaContacto;
	}

	public Date getFechaHoraRespuesta() {
		return this.fechaHoraRespuesta;
	}

	public void setFechaHoraRespuesta(Date fechaHoraRespuesta) {
		this.fechaHoraRespuesta = fechaHoraRespuesta;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 24/07/2013
	 * @return the respuestaAmpliacion
	 */
	public String getRespuestaAmpliacion() {
		return respuestaAmpliacion;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 24/07/2013
	 * @param respuestaAmpliacion the respuestaAmpliacion to set
	 */
	public void setRespuestaAmpliacion(String respuestaAmpliacion) {
		this.respuestaAmpliacion = respuestaAmpliacion;
	}

}
