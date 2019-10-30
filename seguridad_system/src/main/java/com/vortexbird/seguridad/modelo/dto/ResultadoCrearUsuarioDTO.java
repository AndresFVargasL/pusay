package com.vortexbird.seguridad.modelo.dto;

public class ResultadoCrearUsuarioDTO {
	
	private Boolean exito=true;
	private String mensaje;
	
	
	public ResultadoCrearUsuarioDTO() {
		
	}

	
	
	
	public ResultadoCrearUsuarioDTO(Boolean exito, String mensaje) {
		super();
		this.exito = exito;
		this.mensaje = mensaje;
	}




	public Boolean getExito() {
		return exito;
	}


	public void setExito(Boolean exito) {
		this.exito = exito;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
	
	
	
}
