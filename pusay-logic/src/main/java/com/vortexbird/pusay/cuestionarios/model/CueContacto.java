package com.vortexbird.pusay.cuestionarios.model;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class CueContacto implements java.io.Serializable {

	private Long identificacion = null;
	private String nombre = null;
	private String apellido = null;
	private String email = null;
	private String celular = null;
	private String empresa = null;
	private Long estado = null;
	private Set<CueListaContacto> cueListaContactos = new HashSet<CueListaContacto>(
			0);

	public CueContacto() {
	}

	public CueContacto(Long identificacion, String nombre, String apellido,
			Long estado) {
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.estado = estado;
	}

	public CueContacto(Long identificacion, String nombre, String apellido,
			String email, String celular, String empresa, Long estado,
			Set<CueListaContacto> cueListaContactos) {
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.celular = celular;
		this.empresa = empresa;
		this.estado = estado;
		this.cueListaContactos = cueListaContactos;
	}

	public Long getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(Long identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Long getEstado() {
		return this.estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public Set<CueListaContacto> getCueListaContactos() {
		return this.cueListaContactos;
	}

	public void setCueListaContactos(Set<CueListaContacto> cueListaContactos) {
		this.cueListaContactos = cueListaContactos;
	}

}
