package com.vortexbird.pusay.cuestionarios.model;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class CueResponsable implements java.io.Serializable {

	private Long identificacion = null;
	private String razonSocial = null;
	private String nombre = null;
	private String apellido = null;
	private String email = null;
	private String emailSoporte = null;
	private String paginaSoporte = null;
	private String telefono1 = null;
	private String telefono2 = null;
	private Set<CueCuestionario> cueCuestionarios = new HashSet<CueCuestionario>(
			0);

	public CueResponsable() {
	}

	public CueResponsable(Long identificacion, String email, String telefono1) {
		this.identificacion = identificacion;
		this.email = email;
		this.telefono1 = telefono1;
	}

	public CueResponsable(Long identificacion, String razonSocial,
			String nombre, String apellido, String email, String emailSoporte,
			String paginaSoporte, String telefono1, String telefono2,
			Set<CueCuestionario> cueCuestionarios) {
		this.identificacion = identificacion;
		this.razonSocial = razonSocial;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.emailSoporte = emailSoporte;
		this.paginaSoporte = paginaSoporte;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.cueCuestionarios = cueCuestionarios;
	}

	public Long getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(Long identificacion) {
		this.identificacion = identificacion;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
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

	public String getEmailSoporte() {
		return this.emailSoporte;
	}

	public void setEmailSoporte(String emailSoporte) {
		this.emailSoporte = emailSoporte;
	}

	public String getPaginaSoporte() {
		return this.paginaSoporte;
	}

	public void setPaginaSoporte(String paginaSoporte) {
		this.paginaSoporte = paginaSoporte;
	}

	public String getTelefono1() {
		return this.telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public Set<CueCuestionario> getCueCuestionarios() {
		return this.cueCuestionarios;
	}

	public void setCueCuestionarios(Set<CueCuestionario> cueCuestionarios) {
		this.cueCuestionarios = cueCuestionarios;
	}

}
