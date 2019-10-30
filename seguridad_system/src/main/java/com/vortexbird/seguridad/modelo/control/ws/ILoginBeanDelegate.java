package com.vortexbird.seguridad.modelo.control.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.vortexbird.seguridad.modelo.dto.GrupoDTO;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;

@WebService
public interface ILoginBeanDelegate {

	@WebMethod(operationName="autenticar") 
	public UsuarioDTO autenticar(
			@WebParam(name="pLogin") String login, 
			@WebParam(name="pPass") String pass) throws Exception;
	
	public List<GrupoDTO> getOpciones(
			@WebParam(name="pLogin") String login, 
			@WebParam(name="pSistema") String sistema,
			@WebParam(name="pSucursal") String sucursal, 
			@WebParam(name="pCia") String cia) throws Exception;
	
	public void registrarCambioPass(
			@WebParam(name="pLogin") String login,
			@WebParam(name="pFechaini") String fechaini,
			@WebParam(name="pFechafin") String fechafin,
			@WebParam(name="pToken") String token,
			@WebParam(name="pEstado") String estado) throws Exception;
	public Boolean validaToken(
			@WebParam(name="pFechaini") String fecha_ini, 
			@WebParam(name="pFechafin") String fecha_fin, 
			@WebParam(name="pToken") String token);
	
	public Boolean ejecutaToken(
			@WebParam(name="pPass") String pass,
			@WebParam(name="pToken") String token);
	
	public UsuarioDTO consultarUsuarioPorLogin(
			@WebParam(name="pLogin") String login,
			@WebParam(name="pCodigoSistema") String codigoSistema);
	
	public List<UsuarioDTO> consultarUsuarioPorRol(
			@WebParam(name="pNombreRol") String nombreRol,
			@WebParam(name="pCodigoSistema") String codigoSistema);
}
