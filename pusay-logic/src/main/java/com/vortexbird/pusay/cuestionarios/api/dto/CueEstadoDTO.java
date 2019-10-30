package com.vortexbird.pusay.cuestionarios.api.dto;

import java.io.Serializable;

public class CueEstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long consecutivo;
	private String descripcion;
	private Long estado;
	private String nombre;

	public Long getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getEstado() {
		return estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
