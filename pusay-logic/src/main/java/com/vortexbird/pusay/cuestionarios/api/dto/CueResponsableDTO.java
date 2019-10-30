package com.vortexbird.pusay.cuestionarios.api.dto;

import java.io.Serializable;

public class CueResponsableDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String apellido;
	private String email;
	private String emailSoporte;
	private Long identificacion;
	private String nombre;
	private String paginaSoporte;
	private String razonSocial;
	private String telefono1;
	private String telefono2;

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailSoporte() {
		return emailSoporte;
	}

	public void setEmailSoporte(String emailSoporte) {
		this.emailSoporte = emailSoporte;
	}

	public Long getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Long identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPaginaSoporte() {
		return paginaSoporte;
	}

	public void setPaginaSoporte(String paginaSoporte) {
		this.paginaSoporte = paginaSoporte;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
}
