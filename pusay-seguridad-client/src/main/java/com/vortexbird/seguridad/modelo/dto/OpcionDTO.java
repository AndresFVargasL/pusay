package com.vortexbird.seguridad.modelo.dto;

public class OpcionDTO {
	
	private String opc_codigo;
	private String opc_nombre;
	private String opc_descripcion;
	private String opc_llave_acceso;
	private String seg_grupo_opcion_gru_codigo;
	private String opc_activo;
	
	public OpcionDTO(String opc_codigo, String opc_nombre,
			String opc_descripcion, String opc_llave_acceso,
			String seg_grupo_opcion_gru_codigo, String opc_activo) {
		
		this.opc_codigo = opc_codigo;
		this.opc_nombre = opc_nombre;
		this.opc_descripcion = opc_descripcion;
		this.opc_llave_acceso = opc_llave_acceso;
		this.seg_grupo_opcion_gru_codigo = seg_grupo_opcion_gru_codigo;
		this.opc_activo = opc_activo;
	}
	
	public OpcionDTO() {
		
	}
	public String getOpc_codigo() {
		return opc_codigo;
	}
	public void setOpc_codigo(String opc_codigo) {
		this.opc_codigo = opc_codigo;
	}
	public String getOpc_nombre() {
		return opc_nombre;
	}
	public void setOpc_nombre(String opc_nombre) {
		this.opc_nombre = opc_nombre;
	}
	public String getOpc_descripcion() {
		return opc_descripcion;
	}
	public void setOpc_descripcion(String opc_descripcion) {
		this.opc_descripcion = opc_descripcion;
	}
	public String getOpc_llave_acceso() {
		return opc_llave_acceso;
	}
	public void setOpc_llave_acceso(String opc_llave_acceso) {
		this.opc_llave_acceso = opc_llave_acceso;
	}
	public String getSeg_grupo_opcion_gru_codigo() {
		return seg_grupo_opcion_gru_codigo;
	}
	public void setSeg_grupo_opcion_gru_codigo(String seg_grupo_opcion_gru_codigo) {
		this.seg_grupo_opcion_gru_codigo = seg_grupo_opcion_gru_codigo;
	}

	public String getOpc_activo() {
		return opc_activo;
	}

	public void setOpc_activo(String opc_activo) {
		this.opc_activo = opc_activo;
	}
	

}
