package com.vortexbird.pusay.cuestionarios.model;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class CueCuestionarioTipo implements java.io.Serializable {

	private Long consecutivo = null;
	private String nombre = null;
	private String descripcion = null;
	private Long estado = null;
	private Set<CueCuestionario> cueCuestionarios = new HashSet<CueCuestionario>(
			0);

	public CueCuestionarioTipo() {
	}

	public CueCuestionarioTipo(Long consecutivo, String nombre, Long estado) {
		this.consecutivo = consecutivo;
		this.nombre = nombre;
		this.estado = estado;
	}

	public CueCuestionarioTipo(Long consecutivo, String nombre,
			String descripcion, Long estado,
			Set<CueCuestionario> cueCuestionarios) {
		this.consecutivo = consecutivo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.cueCuestionarios = cueCuestionarios;
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

	public Set<CueCuestionario> getCueCuestionarios() {
		return this.cueCuestionarios;
	}

	public void setCueCuestionarios(Set<CueCuestionario> cueCuestionarios) {
		this.cueCuestionarios = cueCuestionarios;
	}

}
