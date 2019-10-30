package com.vortexbird.pusay.cuestionarios.model;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class CueOpcion implements java.io.Serializable, Comparable<CueOpcion> {

	private Long consecutivo = null;
	private CuePregunta cuePregunta = null;
	private String enunciado = null;
	private String condicion = null;
	private Long orden = null;
	private Long puntaje = null;
	private Long requiereAmpliacion = null;
	private String labelAmpliacion = null;
	private Long indicadorCorrecta = null;
	private Long estado = null;
	private Set<CueNavegacion> cueNavegacions = new HashSet<CueNavegacion>(0);
	private Set<CueRespuesta> cueRespuestas = new HashSet<CueRespuesta>(0);

	public CueOpcion() {
		this.cuePregunta = new CuePregunta();
	}

	public CueOpcion(Long consecutivo, CuePregunta cuePregunta, String enunciado, String condicion, Long orden,
			Long requiereAmpliacion, Long indicadorCorrecta, Long estado) {
		this.consecutivo = consecutivo;
		this.cuePregunta = cuePregunta;
		this.enunciado = enunciado;
		this.condicion = condicion;
		this.orden = orden;
		this.requiereAmpliacion = requiereAmpliacion;
		this.indicadorCorrecta = indicadorCorrecta;
		this.estado = estado;
	}

	public CueOpcion(Long consecutivo, CuePregunta cuePregunta, String enunciado, String condicion, Long orden,
			Long puntaje, Long requiereAmpliacion, String labelAmpliacion, Long indicadorCorrecta, Long estado,
			Set<CueNavegacion> cueNavegacions, Set<CueRespuesta> cueRespuestas) {
		this.consecutivo = consecutivo;
		this.cuePregunta = cuePregunta;
		this.enunciado = enunciado;
		this.condicion = condicion;
		this.orden = orden;
		this.puntaje = puntaje;
		this.requiereAmpliacion = requiereAmpliacion;
		this.labelAmpliacion = labelAmpliacion;
		this.indicadorCorrecta = indicadorCorrecta;
		this.estado = estado;
		this.cueNavegacions = cueNavegacions;
		this.cueRespuestas = cueRespuestas;
	}

	public Long getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public CuePregunta getCuePregunta() {
		return this.cuePregunta;
	}

	public void setCuePregunta(CuePregunta cuePregunta) {
		this.cuePregunta = cuePregunta;
	}

	public String getEnunciado() {
		return this.enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String getCondicion() {
		return this.condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public Long getOrden() {
		return this.orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

	public Long getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(Long puntaje) {
		this.puntaje = puntaje;
	}

	public Long getRequiereAmpliacion() {
		return this.requiereAmpliacion;
	}

	public void setRequiereAmpliacion(Long requiereAmpliacion) {
		this.requiereAmpliacion = requiereAmpliacion;
	}

	public String getLabelAmpliacion() {
		return this.labelAmpliacion;
	}

	public void setLabelAmpliacion(String labelAmpliacion) {
		this.labelAmpliacion = labelAmpliacion;
	}

	public Long getIndicadorCorrecta() {
		return this.indicadorCorrecta;
	}

	public void setIndicadorCorrecta(Long indicadorCorrecta) {
		this.indicadorCorrecta = indicadorCorrecta;
	}

	public Long getEstado() {
		return this.estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public Set<CueNavegacion> getCueNavegacions() {
		return this.cueNavegacions;
	}

	public void setCueNavegacions(Set<CueNavegacion> cueNavegacions) {
		this.cueNavegacions = cueNavegacions;
	}

	public Set<CueRespuesta> getCueRespuestas() {
		return this.cueRespuestas;
	}

	public void setCueRespuestas(Set<CueRespuesta> cueRespuestas) {
		this.cueRespuestas = cueRespuestas;
	}

	@Override
	public int compareTo(CueOpcion opcion) {
		return getOrden().compareTo(opcion.getOrden());
	}
}