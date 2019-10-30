package com.vortexbird.pusay.cuestionarios.api.dto;

import java.io.Serializable;
import java.util.Date;

public class CueCuestionarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long consecutivo;
	private String descripcion;
	private Date fechaCreacion;
	private String terminos;
	private String titulo;
	private Long consecutivo_CueConfiguracion;
	private Long consecutivo_CueCuestionarioTipo;
	private Long consecutivo_CueEstado;
	private Long identificacion_CueResponsable;

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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getTerminos() {
		return terminos;
	}

	public void setTerminos(String terminos) {
		this.terminos = terminos;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getConsecutivo_CueConfiguracion() {
		return consecutivo_CueConfiguracion;
	}

	public void setConsecutivo_CueConfiguracion(
			Long consecutivo_CueConfiguracion) {
		this.consecutivo_CueConfiguracion = consecutivo_CueConfiguracion;
	}

	public Long getConsecutivo_CueCuestionarioTipo() {
		return consecutivo_CueCuestionarioTipo;
	}

	public void setConsecutivo_CueCuestionarioTipo(
			Long consecutivo_CueCuestionarioTipo) {
		this.consecutivo_CueCuestionarioTipo = consecutivo_CueCuestionarioTipo;
	}

	public Long getConsecutivo_CueEstado() {
		return consecutivo_CueEstado;
	}

	public void setConsecutivo_CueEstado(Long consecutivo_CueEstado) {
		this.consecutivo_CueEstado = consecutivo_CueEstado;
	}

	public Long getIdentificacion_CueResponsable() {
		return identificacion_CueResponsable;
	}

	public void setIdentificacion_CueResponsable(
			Long identificacion_CueResponsable) {
		this.identificacion_CueResponsable = identificacion_CueResponsable;
	}
}
