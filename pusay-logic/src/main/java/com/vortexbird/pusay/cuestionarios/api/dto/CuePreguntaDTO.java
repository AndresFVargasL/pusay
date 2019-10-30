package com.vortexbird.pusay.cuestionarios.api.dto;

import java.io.Serializable;

public class CuePreguntaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String condicion;
	private Long consecutivo;
	private String enunciado;
	private Long estado;
	private String labelAmpliacion;
	private Long nroRespuestas;
	private Long orden;
	private Long puntaje;
	private Long consecutivo_CueCategoria;

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public Long getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public Long getEstado() {
		return estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public String getLabelAmpliacion() {
		return labelAmpliacion;
	}

	public void setLabelAmpliacion(String labelAmpliacion) {
		this.labelAmpliacion = labelAmpliacion;
	}

	public Long getNroRespuestas() {
		return nroRespuestas;
	}

	public void setNroRespuestas(Long nroRespuestas) {
		this.nroRespuestas = nroRespuestas;
	}

	public Long getOrden() {
		return orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

	public Long getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Long puntaje) {
		this.puntaje = puntaje;
	}

	public Long getConsecutivo_CueCategoria() {
		return consecutivo_CueCategoria;
	}

	public void setConsecutivo_CueCategoria(Long consecutivo_CueCategoria) {
		this.consecutivo_CueCategoria = consecutivo_CueCategoria;
	}
}
