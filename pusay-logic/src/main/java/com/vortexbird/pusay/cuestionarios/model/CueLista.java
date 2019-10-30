package com.vortexbird.pusay.cuestionarios.model;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class CueLista implements java.io.Serializable {

	private Long consecutivo = null;
	private String nombre = null;
	private String descripcion = null;
	private Long estado = null;
	private Set<CueListaCuestionario> cueListaCuestionarios = new HashSet<CueListaCuestionario>(
			0);
	private Set<CueListaContacto> cueListaContactos = new HashSet<CueListaContacto>(
			0);

	public CueLista() {
	}

	public CueLista(Long consecutivo, String nombre, Long estado) {
		this.consecutivo = consecutivo;
		this.nombre = nombre;
		this.estado = estado;
	}

	public CueLista(Long consecutivo, String nombre, String descripcion,
			Long estado, Set<CueListaCuestionario> cueListaCuestionarios,
			Set<CueListaContacto> cueListaContactos) {
		this.consecutivo = consecutivo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.cueListaCuestionarios = cueListaCuestionarios;
		this.cueListaContactos = cueListaContactos;
	}

	public Long getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
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

	public Set<CueListaCuestionario> getCueListaCuestionarios() {
		return this.cueListaCuestionarios;
	}

	public void setCueListaCuestionarios(
			Set<CueListaCuestionario> cueListaCuestionarios) {
		this.cueListaCuestionarios = cueListaCuestionarios;
	}

	public Set<CueListaContacto> getCueListaContactos() {
		return this.cueListaContactos;
	}

	public void setCueListaContactos(Set<CueListaContacto> cueListaContactos) {
		this.cueListaContactos = cueListaContactos;
	}

}
