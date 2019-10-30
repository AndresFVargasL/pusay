
package com.vortexbird.seguridad.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vortexbird.seguridad.modelo.dto.GrupoDTO;
import com.vortexbird.seguridad.modelo.dto.ResultadoCrearUsuarioDTO;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.Utilities;

@Controller
@RequestMapping("/loginRestService")
public class LoginRestService implements ILoginRestService {

	com.vortexbird.seguridad.modelo.control.ws.LoginBean loginBean = new com.vortexbird.seguridad.modelo.control.ws.LoginBean();

	/* (non-Javadoc)
	 * @see com.vortexbird.seguridad.rest.ILoginRestService#autenticar(java.lang.String, java.lang.String)
	 */
	@Override
	@RequestMapping(value="/autenticar/{login}/{pass}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody  UsuarioDTO autenticar(@PathVariable("login")String login, @PathVariable("pass")String pass) {		
		UsuarioDTO usuarioDTO=loginBean.autenticar(login, pass);
		return usuarioDTO;
	}
	

	
	/* (non-Javadoc)
	 * @see com.vortexbird.seguridad.rest.ILoginRestService#getOpciones(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@RequestMapping(value="/getOpciones/{login}/{sistema}/{sucursal}/{cia}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody GrupoDTO[] getOpciones(@PathVariable("login") String login,
													@PathVariable("sistema") String sistema,
													@PathVariable("sucursal")String sucursal, 
													@PathVariable("cia")String cia) {
		
		List<GrupoDTO> listaGrupoDTOs=loginBean.getOpciones(login, sistema, sucursal, cia);
		
		if(listaGrupoDTOs!=null && listaGrupoDTOs.size()>0){
			GrupoDTO[] arrayGrupoDTO=new GrupoDTO[listaGrupoDTOs.size()];
			for(int i=0;i<listaGrupoDTOs.size();i++){
				arrayGrupoDTO[0]=listaGrupoDTOs.get(i);
			}
			return arrayGrupoDTO;
		}

				
		return null;
		
	}


	/* (non-Javadoc)
	 * @see com.vortexbird.seguridad.rest.ILoginRestService#registrarCambioPass(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@RequestMapping(value="/registrarCambioPass/{login}/{fechaini}/{fechafin}/{token}/{estado}",method=RequestMethod.GET,produces="application/json")
	public void registrarCambioPass(@PathVariable("login")String login, 
									@PathVariable("fechaini")String fechaini,
									@PathVariable("fechafin")String fechafin, 
									@PathVariable("token")String token, 
									@PathVariable("estado")String estado) throws Exception {
		
		loginBean.registrarCambioPass(login, fechaini, fechafin, token, estado);
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.vortexbird.seguridad.rest.ILoginRestService#validaToken(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@RequestMapping(value="/validaToken/{fecha_ini}/{fecha_fin}/{token}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody Boolean validaToken(@PathVariable("fecha_ini")String fecha_ini, 
											 @PathVariable("fecha_fin")String fecha_fin, 
											 @PathVariable("token")String token){
		
		return loginBean.validaToken(fecha_ini, fecha_fin, token);
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.vortexbird.seguridad.rest.ILoginRestService#ejecutaToken(java.lang.String, java.lang.String)
	 */
	@Override
	@RequestMapping(value="/ejecutaToken/{pass}/{token}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody  Boolean ejecutaToken(@PathVariable("pass")String pass, 
											   @PathVariable("token")String token){
		return loginBean.ejecutaToken(pass, token);
	}
	
	
	/* (non-Javadoc)
	 * @see com.vortexbird.seguridad.rest.ILoginRestService#consultarUsuarioPorLogin(java.lang.String, java.lang.String)
	 */
	@Override
	@RequestMapping(value="/consultarUsuarioPorLogin/{login}/{codigoSistema}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody UsuarioDTO consultarUsuarioPorLogin(@PathVariable("login")String login, 
															 @PathVariable("codigoSistema")String codigoSistema) {
		return loginBean.consultarUsuarioPorLogin(login,codigoSistema);
	}
	
	
	/* (non-Javadoc)
	 * @see com.vortexbird.seguridad.rest.ILoginRestService#consultarUsuarioPorRol(java.lang.String, java.lang.String)
	 */
	@Override
	@RequestMapping(value="/consultarUsuarioPorRol/{nombreRol}/{codigoSistema}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody UsuarioDTO[] consultarUsuarioPorRol(@PathVariable("nombreRol")String nombreRol, 
																 @PathVariable("codigoSistema")String codigoSistema) {
		
		List<UsuarioDTO> listaUsuarioDTO=loginBean.consultarUsuarioPorRol(nombreRol,codigoSistema);
		if(listaUsuarioDTO!=null && listaUsuarioDTO.size()>0){
			UsuarioDTO[] arrayUsuarioDTOs=new UsuarioDTO[listaUsuarioDTO.size()];
			for(int i=0;i<listaUsuarioDTO.size();i++){
				arrayUsuarioDTOs[i]=listaUsuarioDTO.get(i);
			}
			return arrayUsuarioDTOs;
		}

		return null;
	}
	
	
	@RequestMapping(value="/crearUsuario/{usuApellidos}/{usuCodigoInterno}/{usuConstrasena}/{usuCorreo}/{usuEstadoRegistro}/{usuIntentosFallidos}/{usuCompania}/{usuLogin}/{usuNombres}/{usuSession}/{rolesAsignados}/{compania}/{sistema}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody ResultadoCrearUsuarioDTO crearUsuario(
			@PathVariable("usuApellidos")String usuApellidos, 
			@PathVariable("usuCodigoInterno")String usuCodigoInterno,
			@PathVariable("usuConstrasena")String usuConstrasena, 
			@PathVariable("usuCorreo")String usuCorreo, 
			@PathVariable("usuEstadoRegistro")String usuEstadoRegistro,
			@PathVariable("usuIntentosFallidos")Long usuIntentosFallidos, 
			@PathVariable("usuCompania")Long usuCompania, 
			@PathVariable("usuLogin")String usuLogin, 
			@PathVariable("usuNombres")String usuNombres,
			@PathVariable("usuSession")Long usuSession,
			@PathVariable("rolesAsignados")String rolesAsignados, 
			@PathVariable("compania")String compania, 
			@PathVariable("sistema")String sistema){
		
		
		
		ResultadoCrearUsuarioDTO resultadoCrearUsuarioDTO=new ResultadoCrearUsuarioDTO();
		
		try {
			
			
			List<String> listaRoles=new ArrayList<String>(1);
			listaRoles.add(rolesAsignados);
			BusinessDelegatorView.saveSegUsuarioConRoles(usuApellidos,
					usuCodigoInterno,
					Utilities.convertirMD5(usuConstrasena),
					usuCorreo,
					usuEstadoRegistro,
					usuIntentosFallidos,
					usuCompania,
					usuLogin.toUpperCase(),
					usuNombres,
					new Date(),
					usuSession, 
					listaRoles, 
					compania, sistema);
			
			resultadoCrearUsuarioDTO.setExito(true);
			resultadoCrearUsuarioDTO.setMensaje("Usuario creado con exito");
		} catch (Exception e) {
			resultadoCrearUsuarioDTO.setExito(false);
			resultadoCrearUsuarioDTO.setMensaje(e.getMessage());
		}
		return resultadoCrearUsuarioDTO;
	}
	
	@RequestMapping(value="/actualizarUsuario/{usuApellidos}/{usuCodigo}/{usuCodigoInterno}/{usuConstrasena}/{usuCorreo}/{usuEstadoRegistro}/{usuIntentosFallidos}/{usuCompania}/{usuLogin}/{usuNombres}/{usuCodigoSegUsuario}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody ResultadoCrearUsuarioDTO updateUsuario(
			@PathVariable("usuApellidos")String usuApellidos,
			@PathVariable("usuCodigo")Long usuCodigo,
			@PathVariable("usuCodigoInterno")String usuCodigoInterno,
			@PathVariable("usuConstrasena")String usuConstrasena, 
			@PathVariable("usuCorreo")String usuCorreo, 
			@PathVariable("usuEstadoRegistro")String usuEstadoRegistro,
			@PathVariable("usuIntentosFallidos")Long usuIntentosFallidos, 
			@PathVariable("usuCompania")Long usuCompania, 
			@PathVariable("usuLogin")String usuLogin, 
			@PathVariable("usuNombres")String usuNombres,
			@PathVariable("usuCodigoSegUsuario")Long usuCodigoSegUsuario){
		
		
		
		ResultadoCrearUsuarioDTO resultadoCrearUsuarioDTO=new ResultadoCrearUsuarioDTO();
		
		try {
			
			

			BusinessDelegatorView.updateSegUsuario(usuApellidos,
					usuCodigo,
					usuCodigoInterno,
					Utilities.convertirMD5(usuConstrasena),
					usuCorreo,
					usuEstadoRegistro,
					usuIntentosFallidos,
					usuCompania,
					usuLogin.toUpperCase(),
					usuNombres,
					new Date(),
					usuCodigoSegUsuario);
			
			resultadoCrearUsuarioDTO.setExito(true);
			resultadoCrearUsuarioDTO.setMensaje("Usuario actualizado con exito");
		} catch (Exception e) {
			resultadoCrearUsuarioDTO.setExito(false);
			resultadoCrearUsuarioDTO.setMensaje(e.getMessage());
		}
		return resultadoCrearUsuarioDTO;
	}
	
//	@Override
//	@RequestMapping(value="/getOpcionesPorRol/{login}",method=RequestMethod.GET,produces="application/json")
//	public @ResponseBody List<GrupoDTO> getOpcionesPorRol(@PathVariable("login")String login) {
//		try {
//
//			List<GrupoDTO> listaGrupoDTOs=BusinessDelegatorView.getOpciones(login);
//
////			if(listaGrupoDTOs!=null && listaGrupoDTOs.size()>0){
////				GrupoDTO[] arrayGrupoDTO = new GrupoDTO[listaGrupoDTOs.size()];
////				for(int i=0;i<listaGrupoDTOs.size();i++){
////					arrayGrupoDTO[0]=listaGrupoDTOs.get(i);
////				}
////				return arrayGrupoDTO;
////			}else{
////				return null;
////			}
//			
//			return listaGrupoDTOs;
//
//		} catch (Exception e) {
//			return null;
//		}
//
//	}
	
	@Override
	@RequestMapping(value="/getOpcionesPorRol/{login}/",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody  GrupoDTO[] getOpcionesPorRol(@PathVariable("login")String login) {		
		try {

			List<GrupoDTO> listaGrupoDTOs=BusinessDelegatorView.getOpciones(login);

			if(listaGrupoDTOs!=null && listaGrupoDTOs.size()>0){
				GrupoDTO[] arrayGrupoDTO = new GrupoDTO[listaGrupoDTOs.size()];
				for(int i=0;i<listaGrupoDTOs.size();i++){
					arrayGrupoDTO[i]=listaGrupoDTOs.get(i);
				}
				return arrayGrupoDTO;
			}else{
				return null;
			}
		

		} catch (Exception e) {
			return null;
		}
	}

}