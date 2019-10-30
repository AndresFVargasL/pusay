package com.vortexbird.pusay.cuestionarios.model;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class CueCategoria implements java.io.Serializable {

	private Long consecutivo = null;
	private CueCuestionario cueCuestionario = null;
	private String nombre = null;
	private String descripcion = null;
	private Long estado = null;
	private Set<CuePregunta> cuePreguntas = new HashSet<CuePregunta>(0);

	public CueCategoria() {
		this.cueCuestionario = new CueCuestionario();
	}

	public CueCategoria(Long consecutivo, String nombre, Long estado) {
		this.consecutivo = consecutivo;
		this.nombre = nombre;
		this.estado = estado;
	}

	public CueCategoria(Long consecutivo, CueCuestionario cueCuestionario,
			String nombre, String descripcion, Long estado,
			Set<CuePregunta> cuePreguntas) {
		this.consecutivo = consecutivo;
		this.cueCuestionario = cueCuestionario;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.cuePreguntas = cuePreguntas;
	}

	public Long getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public CueCuestionario getCueCuestionario() {
		return this.cueCuestionario;
	}

	public void setCueCuestionario(CueCuestionario cueCuestionario) {
		this.cueCuestionario = cueCuestionario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getEstado() {
		return this.estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public Set<CuePregunta> getCuePreguntas() {
		return this.cuePreguntas;
	}

	public void setCuePreguntas(Set<CuePregunta> cuePreguntas) {
		this.cuePreguntas = cuePreguntas;
	}

}
