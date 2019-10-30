package com.vortexbird.pusay.cuestionarios.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class CueCuestionario implements java.io.Serializable {

	private Long consecutivo = null;
	private CueEstado cueEstado = null;
	private CueConfiguracion cueConfiguracion = null;
	private CueResponsable cueResponsable = null;
	private CueCuestionarioTipo cueCuestionarioTipo = null;
	private String titulo = null;
	private String descripcion = null;
	private String terminos = null;
	private Date fechaCreacion = null;
	private Set<CueNavegacion> cueNavegacions = new HashSet<CueNavegacion>(0);
	private Set<CueCategoria> cueCategorias = new HashSet<CueCategoria>(0);
	private Set<CueListaCuestionario> cueListaCuestionarios = new HashSet<CueListaCuestionario>(
			0);

	public CueCuestionario() {
	}

	public CueCuestionario(Long consecutivo, CueConfiguracion cueConfiguracion,
			CueResponsable cueResponsable, String titulo, String descripcion,
			Date fechaCreacion) {
		this.consecutivo = consecutivo;
		this.cueConfiguracion = cueConfiguracion;
		this.cueResponsable = cueResponsable;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
	}

	public CueCuestionario(Long consecutivo, CueEstado cueEstado,
			CueConfiguracion cueConfiguracion, CueResponsable cueResponsable,
			CueCuestionarioTipo cueCuestionarioTipo, String titulo,
			String descripcion, String terminos, Date fechaCreacion,
			Set<CueNavegacion> cueNavegacions, Set<CueCategoria> cueCategorias,
			Set<CueListaCuestionario> cueListaCuestionarios) {
		this.consecutivo = consecutivo;
		this.cueEstado = cueEstado;
		this.cueConfiguracion = cueConfiguracion;
		this.cueResponsable = cueResponsable;
		this.cueCuestionarioTipo = cueCuestionarioTipo;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.terminos = terminos;
		this.fechaCreacion = fechaCreacion;
		this.cueNavegacions = cueNavegacions;
		this.cueCategorias = cueCategorias;
		this.cueListaCuestionarios = cueListaCuestionarios;
	}

	public Long getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public CueEstado getCueEstado() {
		return this.cueEstado;
	}

	public void setCueEstado(CueEstado cueEstado) {
		this.cueEstado = cueEstado;
	}

	public CueConfiguracion getCueConfiguracion() {
		return this.cueConfiguracion;
	}

	public void setCueConfiguracion(CueConfiguracion cueConfiguracion) {
		this.cueConfiguracion = cueConfiguracion;
	}

	public CueResponsable getCueResponsable() {
		return this.cueResponsable;
	}

	public void setCueResponsable(CueResponsable cueResponsable) {
		this.cueResponsable = cueResponsable;
	}

	public CueCuestionarioTipo getCueCuestionarioTipo() {
		return this.cueCuestionarioTipo;
	}

	public void setCueCuestionarioTipo(CueCuestionarioTipo cueCuestionarioTipo) {
		this.cueCuestionarioTipo = cueCuestionarioTipo;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTerminos() {
		return this.terminos;
	}

	public void setTerminos(String terminos) {
		this.terminos = terminos;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Set<CueNavegacion> getCueNavegacions() {
		return this.cueNavegacions;
	}

	public void setCueNavegacions(Set<CueNavegacion> cueNavegacions) {
		this.cueNavegacions = cueNavegacions;
	}

	public Set<CueCategoria> getCueCategorias() {
		return this.cueCategorias;
	}

	public void setCueCategorias(Set<CueCategoria> cueCategorias) {
		this.cueCategorias = cueCategorias;
	}

	public Set<CueListaCuestionario> getCueListaCuestionarios() {
		return this.cueListaCuestionarios;
	}

	public void setCueListaCuestionarios(
			Set<CueListaCuestionario> cueListaCuestionarios) {
		this.cueListaCuestionarios = cueListaCuestionarios;
	}

}
