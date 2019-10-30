
package com.vortexbird.seguridad.modelo.control.ws.impl;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.directory.DirContext;

import com.vortexbird.seguridad.modelo.SegUsuario;
import com.vortexbird.seguridad.modelo.control.ws.ILoginBeanDelegate;
import com.vortexbird.seguridad.modelo.dto.GrupoDTO;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;

@WebService(
		endpointInterface = "com.vortexbird.seguridad.modelo.control.ws.ILoginBeanDelegate", 
		targetNamespace = "http://ws.control.modelo.mac.com.co/", 
		serviceName = "LoginBeanService", 
		portName = "LoginBeanPort")
public class LoginBeanDelegate implements ILoginBeanDelegate{

	com.vortexbird.seguridad.modelo.control.ws.LoginBean loginBean = new com.vortexbird.seguridad.modelo.control.ws.LoginBean();

	@Override
	public UsuarioDTO autenticar(String login, String pass) {
		return loginBean.autenticar(login, pass);
	}
	

	@Override
	public List<GrupoDTO> getOpciones(String login, String sistema,	String sucursal, String cia) {
		return loginBean.getOpciones(login, sistema, sucursal, cia);
	}

	@Override
	public void registrarCambioPass(String login, String fechaini,String fechafin, String token, String estado) throws Exception {
		loginBean.registrarCambioPass(login, fechaini, fechafin, token, estado);
	}
	@Override
	public Boolean validaToken(String fecha_ini, String fecha_fin, String token){
		return loginBean.validaToken(fecha_ini, fecha_fin, token);
	}
	@Override
	public Boolean ejecutaToken(String pass, String token){
		return loginBean.ejecutaToken(pass, token);
	}
	
	@Override
	public UsuarioDTO consultarUsuarioPorLogin(String login, String codigoSistema) {
		return loginBean.consultarUsuarioPorLogin(login,codigoSistema);
	}
	
	@Override
	public List<UsuarioDTO> consultarUsuarioPorRol(String nombreRol, String codigoSistema) {
		return loginBean.consultarUsuarioPorRol(nombreRol,codigoSistema);
	}

}