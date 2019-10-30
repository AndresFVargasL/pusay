package com.vortexbird.seguridad.modelo.control.ws;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Hashtable;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.modelo.SegCambioPass;
import com.vortexbird.seguridad.modelo.SegOpcion;
import com.vortexbird.seguridad.modelo.SegParametro;
import com.vortexbird.seguridad.modelo.SegPermiso;
import com.vortexbird.seguridad.modelo.SegRol;
import com.vortexbird.seguridad.modelo.SegRolUsuario;
import com.vortexbird.seguridad.modelo.SegUsuario;
import com.vortexbird.seguridad.modelo.dto.GrupoDTO;
import com.vortexbird.seguridad.modelo.dto.OpcionDTO;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.Fechas;

public class LoginBean {

	//static final String LDAP_URL = "ldap://ldap.mac.com.co:389/DC=mac,DC=com,DC=co";
	  static final String LDAP_URL = "ldap://172.16.2.49:389/DC=mac,DC=com,DC=co";
	private List<SegUsuario> usuario;
	private List<SegParametro> parametro;
	private Logger logger = Logger.getLogger(LoginBean.class);

	/*
	public String buscarDn(String cn){
		String dnaux="+#&+";
		try {
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, "ldap://172.16.2.49:389");
			env.put(Context.SECURITY_AUTHENTICATION, "none");
			env.put(Context.REFERRAL, "ignore");

			DirContext ctx = new InitialDirContext(env);
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

			String[] organizaciones=new String[]{"o=MAC","o=BMAC","o=COE","o=FONDEMAC","o=MPAC"};
			for (int i = 0; i < organizaciones.length; i++) {
				String string = organizaciones[i];

				NamingEnumeration<NameClassPair> results =ctx.list(string);
				while (results != null && results.hasMore()) {
					NameClassPair sr = (NameClassPair) results.next();
					String dn = sr.getNameInNamespace();	               
					if(dn.trim().toUpperCase().contains("="+cn.toUpperCase()+",")){
						return dn;
					}else {
						dnaux= dnrecursivo(ctx,dn,cn);
						if(dnaux.trim().toUpperCase().contains("="+cn.toUpperCase()+",")){
							return dnaux;
						}
					}	              
				}
			}
		}  catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		return dnaux;
	}
	*/

	public String dnrecursivo(DirContext ctx,String dn,String cn){

		String dnaux="+#&+";
		try {
			NamingEnumeration<NameClassPair> results2 =ctx.list(dn);
			while (results2 != null && results2.hasMore()) {
				NameClassPair sr2 = (NameClassPair) results2.next();
				String dn2 = sr2.getNameInNamespace();               
				if(dn2.toUpperCase().contains(cn.toUpperCase())){
					return dn2;
				}else{
					dnaux= dnrecursivo(ctx,dn2,cn);
					if(dnaux.toUpperCase().contains(cn.toUpperCase())){
						return dnaux;
					}
				}
			}
		}catch(Exception ex){
			dnaux="+#&+";
			//logger.error(ex.getMessage(), ex);
		}
		return dnaux;
	}

	/*
	public String autenticarLDAP(String userId, String password){
		String username = buscarDn(userId.toUpperCase().trim());
		if(username.equals("+#&+"))
			return "3";
		//String username = "apena";
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, LDAP_URL);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, username);
		env.put(Context.SECURITY_CREDENTIALS, password);
		try {
			new InitialDirContext(env);
			return "1";
		}catch (Exception ex) {
			return "2";//contrase�a incorrecata
		}
	}
	*/

	public SegUsuario autenticarUsuarios(String login, String pass) {

		try {
			usuario = BusinessDelegatorView.findByCriteriaInSegUsuario(new Object[]{"usuLogin",true,login.toUpperCase().trim(),"="},null, null);
			pass = convertirMD5(pass);
			if (usuario.size()==0)
				return null;
			else if (usuario.get(0).getUsuConstrasena().equalsIgnoreCase(pass)!= true)
				return null;
			else
				return usuario.get(0);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}

	}

	public String convertirMD5(String password) throws NoSuchAlgorithmException {

		String hash=password;
		byte[] defaultBytes = password.getBytes();	        	
		MessageDigest algorithm = MessageDigest.getInstance("MD5");
		algorithm.reset();
		algorithm.update(defaultBytes);
		byte messageDigest[] = algorithm.digest();	        		            
		StringBuffer hexString = new StringBuffer();
		for (int i=0;i<messageDigest.length;i++) {
			int val = 0xff &  messageDigest[i];
			if (val < 16)
				hexString.append("0");
			hexString.append(Integer.toHexString(val));

			//hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
		}	        			        			
		hash=hexString+"";

		return hash;

	}
	
	public List<UsuarioDTO> consultarUsuarioPorRol(String nombreRol, String codigoSistema) {
		
		List<UsuarioDTO> usuariosDto = new ArrayList<UsuarioDTO>();

		List<SegRolUsuario> usuarios = null;
		try {
			
			usuarios = BusinessDelegatorView.findByCriteriaInSegRolUsuario(new Object[]{
					"segRol.rolNombre",true,nombreRol.toUpperCase().trim(),"=",
					"segRol.segSistema.sisCodigo",false,codigoSistema,"="
			},null, null);
			
			for(SegRolUsuario usuario: usuarios) { 
			
				SegUsuario u = usuario.getSegUsuarioBySegUsuarioUsuCodigo();
				UsuarioDTO usuDto = new UsuarioDTO();
				
				usuDto.setUsu_activo(u.getUsuEstadoRegistro());
				usuDto.setUsu_apellidos(u.getUsuApellidos());
				usuDto.setUsu_codigo(u.getUsuCodigo().longValue()+"");
				usuDto.setUsu_codigo_interno(u.getUsuCodigoInterno());
				usuDto.setUsu_login(u.getUsuLogin());
				usuDto.setUsu_nombres(u.getUsuNombres());
				usuDto.setUsu_correo(u.getUsuCorreo());
				usuDto.setUsu_intentos_fallidos(u.getUsuIntentosFallidos()!=null?u.getUsuIntentosFallidos().toString():"0");
				usuDto.setUsu_ultmima_modificacion_pass(Fechas.dateToStr(u.getUsuUltmimaModificacionPass(), "yyyy-MM-dd"));
				usuDto.setContrasenaMD5(u.getUsuConstrasena());
				usuDto.setNombre_rol(nombreRol);
				
				usuariosDto.add(usuDto);
			}
			
			
		} catch (Exception e) {
			logger.error("Error consultando los usuarios del rol "+nombreRol+" y sistema "+codigoSistema, e);
			e.printStackTrace();
		}				
		
		return usuariosDto;
				
	}
  
	
	public UsuarioDTO consultarUsuarioPorLogin(String login, String codigoSistema) {
		
		UsuarioDTO usuDto = new UsuarioDTO();
		SegUsuario u = null;
		List<SegRolUsuario> usuarios = null;
		try {
			//usuarios = BusinessDelegatorView.findByCriteriaInSegUsuario(new Object[]{"usuLogin",true,login.toUpperCase().trim(),"="},null, null);
			
			usuarios = BusinessDelegatorView.findByCriteriaInSegRolUsuario(new Object[]{
					"segUsuarioBySegUsuarioUsuCodigo.usuLogin",true,login.toUpperCase().trim(),"=",
					"segRol.segSistema.sisCodigo",false,codigoSistema,"="
			},null, null);
			
		} catch (Exception e) {
			logger.error("Error consultando el usuario "+login, e);
			e.printStackTrace();
		}
		
		boolean existeUsuarioEnBD = usuarios!=null && usuarios.size()>0;
		
		if(existeUsuarioEnBD){
			u = usuarios.get(0).getSegUsuarioBySegUsuarioUsuCodigo();
			SegRol rol = usuarios.get(0).getSegRol();
					
			usuDto.setUsu_activo(u.getUsuEstadoRegistro());
			usuDto.setUsu_apellidos(u.getUsuApellidos());
			usuDto.setUsu_codigo(u.getUsuCodigo().longValue()+"");
			usuDto.setUsu_codigo_interno(u.getUsuCodigoInterno());
			usuDto.setUsu_login(u.getUsuLogin());
			usuDto.setUsu_nombres(u.getUsuNombres());
			usuDto.setUsu_correo(u.getUsuCorreo());
			usuDto.setUsu_intentos_fallidos(u.getUsuIntentosFallidos()!=null?u.getUsuIntentosFallidos().toString():"0");
			usuDto.setUsu_ultmima_modificacion_pass(Fechas.dateToStr(u.getUsuUltmimaModificacionPass(), "yyyy-MM-dd"));
			usuDto.setNombre_rol(rol.getRolNombre());
			usuDto.setContrasenaMD5(u.getUsuConstrasena());
		}
		
		return usuDto;
				
	}

	public UsuarioDTO autenticar(String login, String pass) {//primer servicio web

		UsuarioDTO usuDto = new UsuarioDTO();
		List<SegUsuario> usuario = new ArrayList<SegUsuario>();
		try {

			SegUsuario u = null;
			String passEncriptado = "";
			boolean existeUsuarioEnBD = false;
			boolean coincidePwd = false;
			
			/*if (login.equals("JCORONEL")){
				usuDto.setUsu_activo("1");
				usuDto.setUsu_apellidos("Coronel");
				usuDto.setUsu_codigo("550");
				usuDto.setUsu_codigo_interno("94040431");
				usuDto.setUsu_login("JCORONEL");
				usuDto.setUsu_nombres("Coronel Jeycson");
				usuDto.setUsu_correo("porteria@mac.com.co");
				usuDto.setUsu_intentos_fallidos("1");
				usuDto.setUsu_ultmima_modificacion_pass(Fechas.dateToStr(new Date(), "yyyy-MM-dd"));
				usuDto.setEstado("1");
				
				
			}else{*/
		//	Se consulta el usuario en la BD de seguridad
			usuario = BusinessDelegatorView.findByCriteriaInSegUsuario(new Object[]{"usuLogin",true,login.toUpperCase().trim(),"=",
																					"usuConstrasena",true,convertirMD5(pass).trim(),"="},null, null);
			existeUsuarioEnBD = (usuario!=null && usuario.size()>0) ? true : false;

			//Se valida su contrase�a en la BD
			if(existeUsuarioEnBD){
				u = usuario.get(0);
				passEncriptado = convertirMD5(pass);
				coincidePwd = (u.getUsuConstrasena().equals(passEncriptado)) ? true : false;
			}

			//Se crea el DTO del usuario a partir del usuario consultado

			if(u!=null) {

				String dias_caducidad_pwd = "dias_caducidad_pwd";


				if(u.getSegRolUsuariosForSegUsuarioUsuCodigo().size()>=2) { //es porque no es un superadmin
					parametro = BusinessDelegatorView.findByCriteriaInSegParametro(new Object[]{"parNombre",true,dias_caducidad_pwd.trim(),"="},null, null);
					int dias = parametro.get(0).getParValorNumerico().intValue();
					Date hoy = new Date();

					Date ultima_fecha_mod_pass = u.getUsuUltmimaModificacionPass();
					ultima_fecha_mod_pass = Fechas.sumar(ultima_fecha_mod_pass, dias, 0,0,0);
					Double diasEntreFechas = Fechas.diasEntreFechas(hoy,ultima_fecha_mod_pass);
					usuDto.setDias_caducidad(diasEntreFechas.intValue()+"");

				}
				else if (u.getSegRolUsuariosForSegUsuarioUsuCodigo().size()==1) {

					//1 puede ser el superadmin
					Set<SegRolUsuario> arrayRoles = u.getSegRolUsuariosForSegUsuarioUsuCodigo();
					List<SegRolUsuario> arrayRoles2 = new ArrayList<SegRolUsuario>(arrayRoles); 
					if(arrayRoles2.get(0).getSegRol().getRolCodigo()==0) {
						usuDto.setDias_caducidad("*");
					}
					else {
						parametro = BusinessDelegatorView.findByCriteriaInSegParametro(new Object[]{"parNombre",true,dias_caducidad_pwd,"="},null, null);
						int dias = parametro.get(0).getParValorNumerico().intValue();
						Date hoy = new Date();

						Date ultima_fecha_mod_pass = u.getUsuUltmimaModificacionPass();
						Date fecha_mas_dias_caducidad = Fechas.sumar(ultima_fecha_mod_pass, dias, 0,0,0);
						Double diasEntreFechas = Fechas.diasEntreFechas(hoy,fecha_mas_dias_caducidad);
						usuDto.setDias_caducidad(diasEntreFechas.intValue()+"");
					}
				}
				
				Set<SegRolUsuario> arregloRoles = u.getSegRolUsuariosForSegUsuarioUsuCodigo();
				List<SegRolUsuario> ListRoles = new ArrayList<SegRolUsuario>(arregloRoles);

				usuDto.setUsu_activo(u.getUsuEstadoRegistro());
				usuDto.setNombre_rol(ListRoles.get(0).getSegRol().getRolNombre());
				usuDto.setUsu_apellidos(u.getUsuApellidos());
				usuDto.setUsu_codigo(u.getUsuCodigo().longValue()+"");
				usuDto.setUsu_codigo_interno(u.getUsuCodigoInterno());
				usuDto.setUsu_login(u.getUsuLogin());
				usuDto.setUsu_nombres(u.getUsuNombres());
				usuDto.setUsu_correo(u.getUsuCorreo());
				usuDto.setUsu_intentos_fallidos(u.getUsuIntentosFallidos()!=null?u.getUsuIntentosFallidos().toString():"0");
				usuDto.setUsu_ultmima_modificacion_pass(Fechas.dateToStr(u.getUsuUltmimaModificacionPass(), "yyyy-MM-dd"));
				usuDto.setContrasenaMD5(u.getUsuConstrasena());

			}
			
			
		
			Long intentosFallidos = 0L;
			//TODO Revisar para que usan esto con Johan
			
				if (existeUsuarioEnBD && coincidePwd){
					usuDto.setEstado("2");//no es usuario ldap pero es usuario de algun sistema (puede ser un vendedor o porteria). Debe permitir el acceso
					BusinessDelegatorView.updateSegUsuario(u.getUsuApellidos(), u.getUsuCodigo(), u.getUsuCodigoInterno(), u.getUsuConstrasena(), u.getUsuCorreo(), u.getUsuEstadoRegistro(), intentosFallidos, u.getUsuCompaniaCiaCodigo().getCiaCodigo(), u.getUsuLogin(), u.getUsuNombres(), u.getUsuUltmimaModificacionPass(), 0L);
					usuDto.setUsu_intentos_fallidos(intentosFallidos.toString());					
				}else if (existeUsuarioEnBD && !coincidePwd){
					usuDto.setEstado("4");//no es usuario ldap y no coincide la contrasena
					intentosFallidos = u.getUsuIntentosFallidos() + 1;
					BusinessDelegatorView.updateSegUsuario(u.getUsuApellidos(), u.getUsuCodigo(), u.getUsuCodigoInterno(), u.getUsuConstrasena(), u.getUsuCorreo(), u.getUsuEstadoRegistro(), intentosFallidos, u.getUsuCompaniaCiaCodigo().getCiaCodigo(), u.getUsuLogin(), u.getUsuNombres(), u.getUsuUltmimaModificacionPass(), 0L);
					usuDto.setUsu_intentos_fallidos(intentosFallidos.toString());
				}else if(!existeUsuarioEnBD){
					usuDto.setEstado("4");//no es usuario ldap y tampoco es usuario de algun sistema
				}
			
			
			
		} catch (Exception e) {
			logger.error("Error autenticando el usuario", e);
		}
		return usuDto;
	}

	public List<GrupoDTO> getOpciones(String login,String sistema, String sucursal, String cia) {

		List<SegUsuario> res;
		Hashtable<Long, SegOpcion> opciones = new Hashtable<Long, SegOpcion>();

		try {

			res = BusinessDelegatorView.findByCriteriaInSegUsuario(
					new Object[]{
							"usuLogin",true,login.toUpperCase().trim(),"=",
							"usuEstadoRegistro",true,"1","="},null, null);

			if(res.size()>0) {
				//System.out.println("si existe");
				SegUsuario u = res.get(0);//el login es unico!

				Object[] roles = u.getSegRolUsuariosForSegUsuarioUsuCodigo().toArray();

				for(Object rol:roles){

					System.out.println("");

					SegRolUsuario s = (SegRolUsuario)rol;

					if (s.getSegRol().getRolEstadoRegistro().equals("0")){
						continue;
					}

					Object[] permisos = s.getSegRol().getSegPermisos().toArray();

					System.out.println("opciones Rol "+s.getSegRol().getRolNombre()+" = "+permisos.length);

					//Se recorren los permisos del rol
					for(Object opcion:permisos){

						SegPermiso per = (SegPermiso)opcion;

						boolean incluirPermiso = true;

						//Se valida si el permiso se parametriz� por sucursal / cia /
						if (per.getSegSucursal()!=null){
							if (sucursal.equals(per.getSegSucursal().getSucCodigo().toString())){
								incluirPermiso = true;
							}
							else {
								incluirPermiso = false;
							}

						}else if (per.getSegSistemaCia() !=null){
							if (cia.equals(per.getSegSistemaCia().getSegCompania().getCiaCodigo().toString())){
								incluirPermiso = true;
							}
							else {
								incluirPermiso = false;
							}
						}

						//Se calcula el sistema asignado al permiso iterado
						String sisCodigo = null;

						if (per.getSegOpcion()!=null){
							//Permiso por opcion
							sisCodigo = per.getSegOpcion().getSegGrupoOpcion().getSegSistema().getSisCodigo().toString();
						}else if (per.getSegGrupoOpcion()!=null){
							//Permiso por grupo
							sisCodigo = per.getSegGrupoOpcion().getSegSistema().getSisCodigo().toString();
						}else if (per.getSegRol()!=null){
							//Permiso por rol
							sisCodigo = per.getSegRol().getSegSistema().getSisCodigo().toString();
						}

						//Si el permiso iterado es para el sistema requerido
						incluirPermiso = incluirPermiso && sistema.equals(sisCodigo);

						if (incluirPermiso){
							if(per.getSegGrupoOpcion()!=null){

								for(SegOpcion opcionGrupo:per.getSegGrupoOpcion().getSegOpcions()){
									opciones.put(opcionGrupo.getOpcCodigo(), opcionGrupo);
								}

							}
							else if (per.getSegOpcion()!=null){
								opciones.put(per.getSegOpcion().getOpcCodigo(), per.getSegOpcion());
							}

						}

						if(per.getPerEstadoRegistro().equals("0")) {
							//System.out.println("opcion desactivada "+per.getSegOpcion().getOpcNombre());

							if(per.getSegGrupoOpcion()!=null){

								for(SegOpcion opcionGrupo:per.getSegGrupoOpcion().getSegOpcions()){
									opciones.remove(opcionGrupo.getOpcCodigo());
								}

							}
							else if (per.getSegOpcion()!=null){
								if(per.getSegSistemaCia().getSegCompania()==null||cia.equals(per.getSegSistemaCia().getSegCompania().getCiaCodigo().toString()))
									opciones.remove(per.getSegOpcion().getOpcCodigo());
							}
						}

						

						//System.out.println(per.getSegOpcion().getSegGrupoOpcion().getGruNombre()+" : "+per.getSegOpcion().getOpcNombre());

					}
				}
				Object[] permisosUsuario = u.getSegPermisosForUsuCodigo().toArray();//permisos individuales de la persona
				//System.out.println("Permisos individuales: "+permisosUsuario.length);

				//System.out.println("numero de opciones "+opciones.size());

				System.out.println("permisosUsuario "+permisosUsuario.length);

				//Se recorren los permisos del rol
				for(Object opcion:permisosUsuario){

					SegPermiso per = (SegPermiso)opcion;

					boolean incluirPermiso = true;

					//Se valida si el permiso se parametriz� por sucursal / cia
					if (per.getSegSucursal()!=null){
						if (sucursal.equals(per.getSegSucursal().getSucCodigo().toString())){
							incluirPermiso = true;
						}
						else {
							incluirPermiso = false;
						}
					}else if (per.getSegSistemaCia()!=null){
						if (cia.equals(per.getSegSistemaCia().getSegCompania().getCiaCodigo().toString())){
							incluirPermiso = true;
						}
						else {
							incluirPermiso = false;
						}
					}

					//Se calcula el sistema asignado al permiso iterado
					String sisCodigo = null;

					if (per.getSegOpcion()!=null){
						//Permiso por opcion
						sisCodigo = per.getSegOpcion().getSegGrupoOpcion().getSegSistema().getSisCodigo().toString();
					}else if (per.getSegGrupoOpcion()!=null){
						//Permiso por grupo
						sisCodigo = per.getSegGrupoOpcion().getSegSistema().getSisCodigo().toString();
					}else if (per.getSegRol()!=null){
						//Permiso por rol
						sisCodigo = per.getSegRol().getSegSistema().getSisCodigo().toString();
					}

					//Si el permiso iterado es para el sistema requerido
					incluirPermiso = incluirPermiso && sistema.equals(sisCodigo);

					if (incluirPermiso){
						if(per.getSegGrupoOpcion()!=null){

							for(SegOpcion opcionGrupo:per.getSegGrupoOpcion().getSegOpcions()){
								opciones.put(opcionGrupo.getOpcCodigo(), opcionGrupo);
							}

						}
						else if (per.getSegOpcion()!=null){
							opciones.put(per.getSegOpcion().getOpcCodigo(), per.getSegOpcion());
						}

					}

					if(per.getPerEstadoRegistro().equals("0")) {
						//System.out.println("opcion desactivada "+per.getSegOpcion().getOpcNombre());

						if(per.getSegGrupoOpcion()!=null){

							for(SegOpcion opcionGrupo:per.getSegGrupoOpcion().getSegOpcions()){
								opciones.remove(opcionGrupo.getOpcCodigo());
							}

						}
						else if (per.getSegOpcion()!=null){
							opciones.remove(per.getSegOpcion().getOpcCodigo());
						}
					}

					//System.out.println(per.getSegOpcion().getSegGrupoOpcion().getGruNombre()+" : "+per.getSegOpcion().getOpcNombre());

				}

				System.out.println();
				System.out.println("Opciones definitivas "+opciones.size());
				System.out.println();

				List<GrupoDTO> arrayGrupos = new ArrayList<GrupoDTO>();


				while(opciones.size()>0){

					Object[] opcionesFinales = (Object[])(opciones.values().toArray());
					SegOpcion opc = (SegOpcion)opcionesFinales[0];

					GrupoDTO grupo = new GrupoDTO();
					grupo.setGru_codigo(opc.getSegGrupoOpcion().getGruCodigo().longValue()+"");

					if(opc.getSegGrupoOpcion().getSegGrupoOpcion()!=null)
						grupo.setGru_codigo_padre(opc.getSegGrupoOpcion().getSegGrupoOpcion().getGruCodigo().longValue()+"");
					else
						grupo.setGru_codigo_padre("");

					if(opc.getSegGrupoOpcion().getGruDescripcion()!=null)
						grupo.setGru_descripcion(opc.getSegGrupoOpcion().getGruDescripcion().toString());
					else
						grupo.setGru_descripcion("");

					if(opc.getSegGrupoOpcion().getGruLlaveAcceso()!=null)
						grupo.setGru_llave_acceso(opc.getSegGrupoOpcion().getGruLlaveAcceso().toString());
					else
						grupo.setGru_llave_acceso("");

					grupo.setGru_nombre(opc.getSegGrupoOpcion().getGruNombre().toString());
					grupo.setSis_codigo(opc.getSegGrupoOpcion().getSegSistema().getSisCodigo().longValue()+"");
					grupo.setGru_activo(opc.getSegGrupoOpcion().getGruEstadoRegistro().toString());

					List<OpcionDTO> arrayOpciones = new ArrayList<OpcionDTO>();

					for(int j = 0;j<opcionesFinales.length;j++) {
						SegOpcion opc2 = (SegOpcion)opcionesFinales[j];
						if((opc2.getSegGrupoOpcion().getGruCodigo().longValue()+"").equals(grupo.getGru_codigo())){

							OpcionDTO opcionDTO = new OpcionDTO();
							opcionDTO.setOpc_activo(opc2.getOpcEstadoRegistro().toString());
							opcionDTO.setOpc_codigo(opc2.getOpcCodigo().longValue()+"");

							if(opc2.getOpcDescripcion()!=null)
								opcionDTO.setOpc_descripcion(opc2.getOpcDescripcion().toString()+"");
							else
								opcionDTO.setOpc_descripcion("");

							if(opc2.getOpcLlaveAcceso()!=null)
								opcionDTO.setOpc_llave_acceso(opc2.getOpcLlaveAcceso().toString());
							else
								opcionDTO.setOpc_llave_acceso("");

							opcionDTO.setOpc_nombre(opc2.getOpcNombre().toString());
							opcionDTO.setSeg_grupo_opcion_gru_codigo(opc2.getSegGrupoOpcion().getGruCodigo().longValue()+"");

							opciones.remove(opc2.getOpcCodigo());
							arrayOpciones.add(opcionDTO);
						}
					}

					grupo.setOpciones(arrayOpciones);

					arrayGrupos.add(grupo);

				}

				if(arrayGrupos.size()>0)
					return arrayGrupos;
				else
					return null;

			}else {
				if (logger.isDebugEnabled()){
					logger.debug("El usuario esta desactivado");
				}
				return null;

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}

	}

	public void registrarCambioPass(String login, String fecha_ini, String fecha_fin, String token, String estado) throws Exception{

		/*el estado es para saber si es un usuario LDAP
		cuando el usuario cambie su password se debe verificar este estado para saber si debe tambien cambiar el password en el LDAP
		Esto esta pendiente por implementar...*/

		Date fechaini = Fechas.strToDate(fecha_ini, "yyyy/MM/dd hh:mm:ss");
		Date fechafin = Fechas.strToDate(fecha_fin, "yyyy/MM/dd hh:mm:ss");

		//(1) Consultar el usuario de ese login
		List<SegUsuario> usuario;
		usuario = BusinessDelegatorView.findByCriteriaInSegUsuario(new Object[]{"usuLogin",true,login.toUpperCase().trim(),"="},null, null);

		System.out.println("buscado "+usuario.size());

		//(2) Registrat el cambio de pass
		if (usuario.size()>0) {
			System.out.println("usuario "+usuario.get(0).getUsuCodigo());
			BusinessDelegatorView.saveSegCambioPass(
					null,
					estado,
					fechafin,
					fechaini,
					token,
					usuario.get(0).getUsuCodigo());

			System.out.println("cambio guardado ");
		}


	}
	
	@SuppressWarnings({ "unchecked" })
	public Boolean validaToken(String fecha_ini, String fecha_fin, String token){
		
		boolean resultado=false;
		try {
			Date fechaini = Fechas.strToDate(fecha_ini, "yyyy/MM/dd hh:mm:ss");
			Date fechafin = Fechas.strToDate(fecha_fin, "yyyy/MM/dd hh:mm:ss");
			
			String queryString = "select * from SEG_CAMBIO_PASS "
					+ "where cap_token = ? "			
					+ "and CAP_FECHA_INI <= ? " + "and CAP_FECHA_FIN >= ? ";

			Query query = EntityManagerHelper.getEntityManager().createNativeQuery(queryString);
			query.setParameter(1, token);			
			query.setParameter(2, fechaini);
			query.setParameter(3, fechafin);
			List<Object[]> res = query.getResultList();
			if (res.size() > 0) {
				resultado = true;
			}
		}catch(Exception ex){
			resultado=false;
		}
		return resultado;
	}
	
public Boolean ejecutaToken(String pass, String token){
		
		boolean resultado=false;
		try {
			Object[] busqueda=new Object[4];
			busqueda[0]="capToken";
			busqueda[1]=true;
			busqueda[2]=token;
			busqueda[3]="=";
			
			List<SegCambioPass> res = BusinessDelegatorView.findByCriteriaInSegCambioPass(busqueda, null, null);
			if (res.size() > 0) {
				SegCambioPass cambiopass=res.get(0);
				Long usuCodigo=cambiopass.getSegUsuario().getUsuCodigo();
				SegUsuario u = BusinessDelegatorView.getSegUsuario(usuCodigo);
				BusinessDelegatorView.updateSegUsuario(u.getUsuApellidos(), u.getUsuCodigo(), u.getUsuCodigoInterno(), pass,
						u.getUsuCorreo(), u.getUsuEstadoRegistro(), u.getUsuIntentosFallidos(), u.getUsuCompaniaCiaCodigo().getCiaCodigo(), u.getUsuLogin(), u.getUsuNombres(), new Date(), u.getSegUsuario().getUsuCodigo());
				resultado = true;
			}
		}catch(Exception ex){
			resultado=false;
			ex.printStackTrace();
		}
		return resultado;
	}

	public static void main(String args[]) throws Exception {

//		Date fecha_actual = new Date();
//		Date fecha_mas_2_horas = Fechas.sumarConMinutos(fecha_actual, 0,0, 0, 0, 20);
//
//		System.out.println("fecha actual "+Fechas.dateToStr(fecha_actual, "yyyy/MM/dd HH:mm:ss"));
//		System.out.println("fecha actual+2horas "+Fechas.dateToStr(fecha_mas_2_horas, "yyyy/MM/dd HH:mm:ss"));
//
		LoginBean lb = new LoginBean();
		
		
		//System.out.println(lb.autenticarLDAP("JOROZCO", "Mzo*2015"));
		
//
//		lb.registrarCambioPass("GSANDOVAL",Fechas.dateToStr(fecha_actual,"yyyy/MM/dd hh:mm:ss"), Fechas.dateToStr(fecha_mas_2_horas,"yyyy/MM/dd hh:mm:ss"), "6a3002f8a064b06d564b31794305b5e672c47d81", "4");
//
//
//		UsuarioDTO u=lb.autenticar("DGOMEZ", "123456");
//		System.out.println(u.getEstado());
/*
		String login = "APENA";
		String password = "123456";
		String sistema = "1";
		String sucursal = "";
		String cia = "1";

		UsuarioDTO usu = lb.autenticar(login,password);

		if(usu!=null) {
			System.out.println("LOGIN: "+usu.getUsu_login());
			System.out.println("NOMBRES: "+usu.getUsu_nombres());
			System.out.println("APELLIDOS: "+usu.getUsu_apellidos());
			System.out.println("ESTADO: "+usu.getEstado());
			System.out.println("ACTIVO: "+usu.getUsu_activo());
			System.out.println("DIAS CADUCIDAD "+ usu.getDias_caducidad());
			System.out.println("INTENTOS FALLIDOS :" +usu.getUsu_intentos_fallidos());
		}
		List<GrupoDTO> menus=(lb.getOpciones(login,sistema,sucursal,cia));//ws 2

		if(menus == null) {
			System.out.println("error");
		}
		else 
			System.out.println("menus "+menus.size());


		if (menus!=null) {
			for(GrupoDTO menu:menus){
				System.out.println("menu "+menu.getGru_nombre());
				for(OpcionDTO opcion:menu.getOpciones()){
					System.out.println("opcion "+opcion.getOpc_nombre());
				}
			}
		}else {
			System.out.println("El usuario logeado no tiene opciones parametrizadas");
		}*/
	}
}