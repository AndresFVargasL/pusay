package com.vortexbird.seguridad.rest;

import java.util.List;

import org.springframework.web.bind.annotation.ResponseBody;

import com.vortexbird.seguridad.modelo.dto.GrupoDTO;
import com.vortexbird.seguridad.modelo.dto.ResultadoCrearUsuarioDTO;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;

public interface ILoginRestService {

	public abstract UsuarioDTO autenticar(String login, String pass);

	public abstract GrupoDTO[] getOpciones(String login, String sistema,
			String sucursal, String cia);

	public abstract void registrarCambioPass(String login, String fechaini,
			String fechafin, String token, String estado) throws Exception;

	public abstract Boolean validaToken(String fecha_ini, String fecha_fin,
			String token);

	public abstract Boolean ejecutaToken(String pass, String token);

	public abstract UsuarioDTO consultarUsuarioPorLogin(String login,
			String codigoSistema);

	public abstract UsuarioDTO[] consultarUsuarioPorRol(String nombreRol,
			String codigoSistema);
	
	public  ResultadoCrearUsuarioDTO crearUsuario(String usuApellidos, 
			 String usuCodigoInterno,
			 String usuConstrasena, 
			 String usuCorreo, 
			 String usuEstadoRegistro,
			 Long usuIntentosFallidos, 
			 Long usuCompania, 
			 String usuLogin, 
			 String usuNombres,
			 Long usuSession,
			 String rolesAsignados, 
			 String compania, 
			 String sistema);
	
	public abstract GrupoDTO[] getOpcionesPorRol(String login);

}