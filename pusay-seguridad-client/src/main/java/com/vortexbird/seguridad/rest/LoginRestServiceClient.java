package com.vortexbird.seguridad.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.vortexbird.seguridad.modelo.dto.GrupoDTO;
import com.vortexbird.seguridad.modelo.dto.ResultadoCrearUsuarioDTO;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;


public class LoginRestServiceClient {


	public static UsuarioDTO autenticar(String login, String pass,String url){
		
		RestTemplate restTemplate = new RestTemplate();
		UsuarioDTO usuarioDTO=restTemplate.getForObject(url+"autenticar/"+login+"/"+pass, UsuarioDTO.class);
		return usuarioDTO;
	}
	
	public static GrupoDTO[]  getOpciones(String login, String sistema,String sucursal, String cia,String url){
		
		RestTemplate restTemplate = new RestTemplate();
		GrupoDTO[] listaGrupoDTO=restTemplate.getForObject(url+"getOpciones/"+login+"/"+sistema+"/"+sucursal+"/"+cia, GrupoDTO[].class);
		return listaGrupoDTO;
		
	}
	
	public static ResultadoCrearUsuarioDTO crearUsuario(String usuApellidos, 
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
			 String sistema,String url){
		
		
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResultadoCrearUsuarioDTO resultadoCrearUsuarioDTO=restTemplate.getForObject(url+"crearUsuario/"+usuApellidos+"/"+usuCodigoInterno+"/"+usuConstrasena+"/"+usuCorreo+"/"+usuEstadoRegistro+"/"+usuIntentosFallidos+"/"+usuCompania+"/"+usuLogin+"/"+usuNombres+"/"+usuSession+"/"+rolesAsignados+"/"+compania+"/"+sistema+"", ResultadoCrearUsuarioDTO.class);
		return resultadoCrearUsuarioDTO;
		
	}
	
	public static ResultadoCrearUsuarioDTO actualizarUsuario(String usuApellidos,
			 Long usuCodigo,
			 String usuCodigoInterno,
			 String usuConstrasena, 
			 String usuCorreo, 
			 String usuEstadoRegistro,
			 Long usuIntentosFallidos, 
			 Long usuCompania, 
			 String usuLogin, 
			 String usuNombres,
			 Long usuCodigoSegUsuario,
			 String url){
		
		
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResultadoCrearUsuarioDTO resultadoCrearUsuarioDTO=restTemplate.getForObject(url+"actualizarUsuario/"+usuApellidos+"/"+usuCodigo+"/"+usuCodigoInterno+"/"+usuConstrasena+"/"+usuCorreo+"/"+usuEstadoRegistro+"/"+usuIntentosFallidos+"/"+usuCompania+"/"+usuLogin+"/"+usuNombres+"/"+usuCodigoSegUsuario+"", ResultadoCrearUsuarioDTO.class);
		return resultadoCrearUsuarioDTO;
		
	}
	

	public static List<GrupoDTO> getOpcionesPorRol(String login,String url){
		
		
		RestTemplate restTemplate = new RestTemplate();
		
		List<GrupoDTO> listaGrupoDTO = new ArrayList<GrupoDTO>();
		listaGrupoDTO = Arrays.asList(restTemplate.getForObject(url+"getOpcionesPorRol/"+login+"/", GrupoDTO[].class));
		/* - Se ordena la lista por el codigo del grupo, puesto que los INSERTS se ejecutaron ordenados en funcion de éste.
		   - Para que la funcion sort() sea exitosa, la clase GrupoDTO implementa Comparable<GrupoDTO> y se sobreescribe el metodo
		     compareTo() con el atributo que se requiere que se ordene.*/
		Collections.sort(listaGrupoDTO);
		return listaGrupoDTO;
	}
	
	public static UsuarioDTO consultarUsuarioPorLogin(String login, String codigoSistema, String url){
		
		RestTemplate restTemplate = new RestTemplate();
		UsuarioDTO usuarioDTO=restTemplate.getForObject(url+"consultarUsuarioPorLogin/"+login+"/"+codigoSistema, UsuarioDTO.class);
		return usuarioDTO;
	}

	
}
