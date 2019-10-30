package com.vortexbird.seguridad.modelo.dto;

public class UsuarioDTO {
	
	private String usu_codigo;
    private String usu_nombres;
    private String usu_apellidos;
    private String usu_login;
    private String usu_activo;
    private String usu_correo;
    private String usu_codigo_interno;
    private String estado;
    private String usu_ultmima_modificacion_pass;
    private String usu_intentos_fallidos;
    private String dias_caducidad;
    private String nombre_rol;
    private String contrasenaMD5;
    
	public String getDias_caducidad() {
		return dias_caducidad;
	}
	
	
	
	@Override
	public String toString() {
		return "UsuarioDTO [usu_codigo=" + usu_codigo + ", usu_nombres="
				+ usu_nombres + ", usu_apellidos=" + usu_apellidos
				+ ", usu_login=" + usu_login + ", usu_activo=" + usu_activo
				+ ", usu_correo=" + usu_correo + ", usu_codigo_interno="
				+ usu_codigo_interno + ", estado=" + estado
				+ ", usu_ultmima_modificacion_pass="
				+ usu_ultmima_modificacion_pass + ", usu_intentos_fallidos="
				+ usu_intentos_fallidos + ", dias_caducidad=" + dias_caducidad
				+ ", nombre_rol=" + nombre_rol + ", contrasenaMD5="
				+ contrasenaMD5 + "]";
	}



	public String getNombre_rol() {
		return nombre_rol;
	}
	public void setNombre_rol(String nombre_rol) {
		this.nombre_rol = nombre_rol;
	}
	public void setDias_caducidad(String diasCaducidad) {
		dias_caducidad = diasCaducidad;
	}
	public String getUsu_codigo() {
		return usu_codigo;
	}
	public void setUsu_codigo(String usu_codigo) {
		this.usu_codigo = usu_codigo;
	}
	public String getUsu_nombres() {
		return usu_nombres;
	}
	public void setUsu_nombres(String usu_nombres) {
		this.usu_nombres = usu_nombres;
	}
	public String getUsu_apellidos() {
		return usu_apellidos;
	}
	public void setUsu_apellidos(String usu_apellidos) {
		this.usu_apellidos = usu_apellidos;
	}
	public String getUsu_login() {
		return usu_login;
	}
	public void setUsu_login(String usu_login) {
		this.usu_login = usu_login;
	}
	public String getUsu_activo() {
		return usu_activo;
	}
	public void setUsu_activo(String usu_activo) {
		this.usu_activo = usu_activo;
	}
	public String getUsu_codigo_interno() {
		return usu_codigo_interno;
	}
	public void setUsu_codigo_interno(String usu_codigo_interno) {
		this.usu_codigo_interno = usu_codigo_interno;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUsu_correo() {
		return usu_correo;
	}
	public void setUsu_correo(String usuCorreo) {
		usu_correo = usuCorreo;
	}
	public String getUsu_ultmima_modificacion_pass() {
		return usu_ultmima_modificacion_pass;
	}
	public void setUsu_ultmima_modificacion_pass(String usuUltmimaModificacionPass) {
		usu_ultmima_modificacion_pass = usuUltmimaModificacionPass;
	}
	public String getUsu_intentos_fallidos() {
		return usu_intentos_fallidos;
	}
	public void setUsu_intentos_fallidos(String usuIntentosFallidos) {
		usu_intentos_fallidos = usuIntentosFallidos;
	}



	public String getContrasenaMD5() {
		return contrasenaMD5;
	}



	public void setContrasenaMD5(String contrasenaMD5) {
		this.contrasenaMD5 = contrasenaMD5;
	}

}
