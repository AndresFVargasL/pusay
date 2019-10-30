package com.vortexbird.seguridad.modelo.dto;

import java.util.List;

public class GrupoDTO {
	
	private String gru_codigo;
	private String gru_nombre;
	private String gru_descripcion;
	private String gru_llave_acceso;
	private String gru_codigo_padre;
	private String sis_codigo;
	private String gru_activo;
	private String gru_icono;
	private List<OpcionDTO> opciones;
	
	public GrupoDTO(String gru_codigo, String gru_nombre,
			String gru_descripcion, String gru_llave_acceso,
			String gru_codigo_padre, String sis_codigo, String gru_activo) {
		
		this.gru_codigo = gru_codigo;
		this.gru_nombre = gru_nombre;
		this.gru_descripcion = gru_descripcion;
		this.gru_llave_acceso = gru_llave_acceso;
		this.gru_codigo_padre = gru_codigo_padre;
		this.sis_codigo = sis_codigo;
		this.gru_activo = gru_activo;
	}
	
	public GrupoDTO() {
		
	}
	public String getGru_codigo() {
		return gru_codigo;
	}
	public void setGru_codigo(String gru_codigo) {
		this.gru_codigo = gru_codigo;
	}
	public String getGru_nombre() {
		return gru_nombre;
	}
	public void setGru_nombre(String gru_nombre) {
		this.gru_nombre = gru_nombre;
	}
	public String getGru_descripcion() {
		return gru_descripcion;
	}
	public void setGru_descripcion(String gru_descripcion) {
		this.gru_descripcion = gru_descripcion;
	}
	public String getGru_llave_acceso() {
		return gru_llave_acceso;
	}
	public void setGru_llave_acceso(String gru_llave_acceso) {
		this.gru_llave_acceso = gru_llave_acceso;
	}
	public String getGru_codigo_padre() {
		return gru_codigo_padre;
	}
	public void setGru_codigo_padre(String gru_codigo_padre) {
		this.gru_codigo_padre = gru_codigo_padre;
	}
	public String getSis_codigo() {
		return sis_codigo;
	}
	public void setSis_codigo(String sis_codigo) {
		this.sis_codigo = sis_codigo;
	}
	public String getGru_activo() {
		return gru_activo;
	}
	public void setGru_activo(String gru_activo) {
		this.gru_activo = gru_activo;
	}

	public List<OpcionDTO> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<OpcionDTO> opciones) {
		this.opciones = opciones;
	}

	public String getGru_icono() {
		return gru_icono;
	}

	public void setGru_icono(String gru_icono) {
		this.gru_icono = gru_icono;
	}
	
	

}
