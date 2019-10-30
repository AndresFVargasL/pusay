package com.vortexbird.seguridad.rest.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.vortexbird.seguridad.modelo.dto.GrupoDTO;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;

public class LoginRestServiceTest {

	@Test
	public void test() {
	
		/*
		UsuarioDTO usuarioDTO=autenticar("sa_seguridad", "123456");
		assertNotNull("No se pudo autenticar usuarioDTO null", usuarioDTO);
		System.out.println(usuarioDTO.toString());
		
		GrupoDTO[] listaGrupoDTO=getOpciones("sa_seguridad", "1", "1", "1");
		assertNotNull("No se pudo consultar las opciones", listaGrupoDTO);
		System.out.println(listaGrupoDTO.length);
		for (GrupoDTO grupoDTO : listaGrupoDTO) {
			System.out.println(grupoDTO.getGru_descripcion());
		}
		
		*/
		
		
	}
	
	

	public UsuarioDTO autenticar(String login, String pass){
		
		RestTemplate restTemplate = new RestTemplate();
		UsuarioDTO usuarioDTO=restTemplate.getForObject("http://localhost:8080/seguridad_system/controller/loginRestService/autenticar/"+login+"/"+pass, UsuarioDTO.class);
		return usuarioDTO;
	}

	
	public  GrupoDTO[]  getOpciones(String login, String sistema,String sucursal, String cia){
		
		List<GrupoDTO> listaGrupoDTOs=new ArrayList<GrupoDTO>();
		RestTemplate restTemplate = new RestTemplate();
		GrupoDTO[] listaGrupoDTO=restTemplate.getForObject("http://localhost:8080/seguridad_system/controller/loginRestService/getOpciones/"+login+"/"+sistema+"/"+sucursal+"/"+cia, GrupoDTO[].class);
		return listaGrupoDTO;
		
	}

	/*
	public  void registrarCambioPass(String login, String fechaini,String fechafin, String token, String estado) throws Exception{
		
	}

	public  Boolean validaToken(String fecha_ini, String fecha_fin,	String token){
		
	}

	public  Boolean ejecutaToken(String pass, String token){
		
	}

	public  UsuarioDTO consultarUsuarioPorLogin(String login,String codigoSistema){
		
	}

	public  List<UsuarioDTO> consultarUsuarioPorRol(String nombreRol,String codigoSistema){
		
	}
	*/
}
