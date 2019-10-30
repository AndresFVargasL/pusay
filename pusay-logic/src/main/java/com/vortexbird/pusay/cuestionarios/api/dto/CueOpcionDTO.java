package com.vortexbird.pusay.cuestionarios.api.dto;

import java.io.Serializable;

public class CueOpcionDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String condicion;
	private Long consecutivo;
	private String enunciado;
	private Long estado;
	private Long indicadorCorrecta;
	private String labelAmpliacion;
	private Long orden;
	private Long puntaje;
	private Long requiereAmpliacion;
	private Long consecutivo_CuePregunta;

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

	public Long getIndicadorCorrecta() {
		return indicadorCorrecta;
	}

	public void setIndicadorCorrecta(Long indicadorCorrecta) {
		this.indicadorCorrecta = indicadorCorrecta;
	}

	public String getLabelAmpliacion() {
		return labelAmpliacion;
	}

	public void setLabelAmpliacion(String labelAmpliacion) {
		this.labelAmpliacion = labelAmpliacion;
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

	public Long getRequiereAmpliacion() {
		return requiereAmpliacion;
	}

	public void setRequiereAmpliacion(Long requiereAmpliacion) {
		this.requiereAmpliacion = requiereAmpliacion;
	}

	public Long getConsecutivo_CuePregunta() {
		return consecutivo_CuePregunta;
	}

	public void setConsecutivo_CuePregunta(Long consecutivo_CuePregunta) {
		this.consecutivo_CuePregunta = consecutivo_CuePregunta;
	}
}
