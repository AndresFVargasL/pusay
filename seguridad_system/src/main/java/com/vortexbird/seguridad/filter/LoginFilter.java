package com.vortexbird.seguridad.filter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.modelo.SegUsuario;
import com.vortexbird.seguridad.modelo.dto.GrupoDTO;
import com.vortexbird.seguridad.modelo.dto.OpcionDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;

public class LoginFilter implements Filter {

	private List<String> administrador = new ArrayList<String>();
	private List<String> usuarioEstandar =  new ArrayList<String>();
	private String paginaError;
	private List<String> externo= new ArrayList<String>();
	private static Logger logger = Logger.getLogger(LoginFilter.class);

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpSession session = null;
		String basePath = null;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		try {
			httpServletResponse.setHeader("Cache-Control", "no-cache");
			httpServletResponse.setDateHeader("Expires", 0);
			httpServletResponse.setHeader("Pragma", "No-cache");

			String path = httpServletRequest.getContextPath();
			basePath = httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort()+path+"/";
			System.out.println(basePath);

			session = httpServletRequest.getSession(false);

			String pageRequested = httpServletRequest.getRequestURI().toString();
			System.out.println(pageRequested);

			if (pageRequested.startsWith("/seguridad_system/XHTML")){

				if (session == null || session.getAttribute("loginSession")==null) {
					session = httpServletRequest.getSession(true);
					httpServletResponse.sendRedirect(basePath);

				}else{
					
//					String usuLogin =(String) session.getAttribute("loginSession");
					String usuLogin ="SA_SEGURIDAD";
					String usuActivo = (String) session.getAttribute("usuActivo");
					String sucursal = "";
					String sistema = "1";
					String cia = "1";
					
					usuarioEstandar.add("/seguridad_system/XHTML/selectSisYCias.xhtml");

					if ((usuLogin!=null && sucursal != null && sistema!=null && cia != null) && !pageRequested.contains("login2.xhtml")) {
						

						List<GrupoDTO> menus = BusinessDelegatorView.getOpciones(usuLogin, sistema, sucursal, cia);

						if (menus!=null && menus.size()>0) {

							for (int i = 0; i < menus.size(); i++) {
								GrupoDTO grupoDTO = menus.get(i);

								List<OpcionDTO> opcionesDTO = grupoDTO.getOpciones();

								for (int j = 0; j < opcionesDTO.size(); j++) {
									OpcionDTO opDTO = opcionesDTO.get(j);
									String opcionCodigo = opDTO.getOpc_codigo();

									if (usuActivo.equals("1")==true){
										usuarioEstandar.add("/seguridad_system/XHTML/initialMenu.xhtml");
									}
									if (usuActivo.equals("1")==true && (opcionCodigo.equals("1")==true)){
										usuarioEstandar.add("/seguridad_system/XHTML/segUsuario.xhtml");
									}
									if (usuActivo.equals("1")==true && (opcionCodigo.equals("2")==true)) {
										usuarioEstandar.add("/seguridad_system/XHTML/segSucursal.xhtml");
									}
									if (usuActivo.equals("1")==true && (opcionCodigo.equals("3")==true)) {
										usuarioEstandar.add("/seguridad_system/XHTML/segSistema.xhtml");
									}
									if (usuActivo.equals("1")==true && (opcionCodigo.equals("4")==true)) {
										usuarioEstandar.add("/seguridad_system/XHTML/segRol.xhtml");
									}
									if (usuActivo.equals("1")==true && (opcionCodigo.equals("6")==true)) {
										usuarioEstandar.add("/seguridad_system/XHTML/segRolUsuario.xhtml");
									}
									if (usuActivo.equals("1")==true && (opcionCodigo.equals("7")==true)) {
										usuarioEstandar.add("/seguridad_system/XHTML/segGrupoOpcion.xhtml");
									}
									if (usuActivo.equals("1")==true && (opcionCodigo.equals("8")==true)) {
										usuarioEstandar.add("/seguridad_system/XHTML/tree.xhtml");
									}
									if (usuActivo.equals("1")==true && (opcionCodigo.equals("9")==true)) {
										usuarioEstandar.add("/seguridad_system/XHTML/segAuditoria.xhtml");
									}
									if (usuActivo.equals("1")==true && (opcionCodigo.equals("10")==true)) {
										usuarioEstandar.add("/seguridad_system/XHTML/segCompania.xhtml");
									}
									if (usuActivo.equals("1")==true && (opcionCodigo.equals("11")==true)) {
										usuarioEstandar.add("/seguridad_system/XHTML/segParametro.xhtml");
									}
									if (usuActivo.equals("1")==true && (opcionCodigo.equals("12")==true)) {
										usuarioEstandar.add("/seguridad_system/XHTML/segSistemaCia.xhtml");
									}
									if (usuActivo.equals("1")==true && (opcionCodigo.equals("13")==true)) {
										usuarioEstandar.add("/seguridad_system/XHTML/segOpcion.xhtml");
									}
									if (usuActivo.equals("1")==true && (opcionCodigo.equals("21")==true)) {
										usuarioEstandar.add("/seguridad_system/XHTML/segPermiso.xhtml");
									}
								}
							}
						}
					}
					if (usuActivo.equals("1")==true && (usuarioEstandar.contains(pageRequested)==true)){
						chain.doFilter(request, response);
					}else {

						session.setAttribute("loginSession", null);
						session.invalidate();
						session = httpServletRequest.getSession(true);
						httpServletResponse.sendRedirect(basePath);
					}
				}
			}else{
				
				if(session != null && session.getAttribute("usuCodigo")!=null) {
					chain.doFilter(request, response);				
				}
				else if(pageRequested.startsWith("/seguridad_system/cambio_pass")) {
					Date date = new Date();
					String usuLogin = httpServletRequest.getParameter("usuLogin");
					String usuEstado = httpServletRequest.getParameter("usuEstado");
					String token = httpServletRequest.getParameter("token");

					SegUsuario u = null;
					List<SegUsuario> usuario;
					usuario = BusinessDelegatorView.findByCriteriaInSegUsuario(new Object[]{"usuLogin",true,usuLogin.toUpperCase().trim(),"="},null, null);

					u = usuario.get(0);

					String queryString = "select * "+
					"from SEG_CAMBIO_PASS "+
					"where cap_token = ? "+
					"and seg_usuario_usu_codigo = ? "+
					"and CAP_FECHA_INI <= ? " +
					"and CAP_FECHA_FIN >= ? ";

					Query query = EntityManagerHelper.getEntityManager().createNativeQuery(queryString); 
					query.setParameter(1, token);
					query.setParameter(2, u.getUsuCodigo().intValue());
					query.setParameter(3, date);
					query.setParameter(4, date);
					List<Object[]> res = query.getResultList();

					if(res.size()>0) {			
						logger.info("Voy a enviar usucodigo a cambiopass.xhtml con el valor: "+ u.getUsuCodigo().intValue());
						session = httpServletRequest.getSession(true);
						session.setAttribute("usuCodigo", u.getUsuCodigo().intValue());
						chain.doFilter(request, response);
					}else {
						session.setAttribute("loginSession", null);
						session.invalidate();
						session = httpServletRequest.getSession(true);
						httpServletResponse.sendRedirect(basePath);
					}
				}else {
					chain.doFilter(request, response);
				}
			}
		}catch (Exception e2) {
			logger.error("Error ejecutando el filtro de seguridad.", e2);
			if (session!=null){
				session.setAttribute("msgError", "Error t�cnico: " + e2.getMessage());
			}
			httpServletResponse.sendRedirect(paginaError);
		}
	}

	public static void main(String[] args) throws Exception {
		Date date  = new Date();
		String usuLogin = "GSANDOVAL";	
		String usuEstado = "1";
		String token = "6a3002f8a064b06d564b31794305b5e672c47d81";


		SegUsuario u = null;
		List<SegUsuario> usuario;
		usuario = BusinessDelegatorView.findByCriteriaInSegUsuario(new Object[]{"usuLogin",true,usuLogin.toUpperCase().trim(),"="},null, null);

		u = usuario.get(0);

		String queryString = "select * "+
		"from SEG_CAMBIO_PASS "+
		"where cap_token = ? "+
		"and seg_usuario_usu_codigo = ? " +
		"and CAP_FECHA_INI <= ? " +
		"and CAP_FECHA_FIN >= ? ";

		Query query = EntityManagerHelper.getEntityManager().createNativeQuery(queryString); 
		query.setParameter(1, token);
		query.setParameter(2, u.getUsuCodigo().intValue());
		query.setParameter(3, date);
		query.setParameter(4, date);
		List<Object[]> res = query.getResultList();

		if(res.size()>0) {				   		
			System.out.println("enviar correo");
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	
		administrador = llenarAdministrador(administrador);
		
		try{
			
			paginaError = filterConfig.getInitParameter("paginaError");
			
			if (paginaError == null) 
				throw new ServletException("Parametrizar en el Web.xml el parametro 'paginaError'.");
			
		}catch (ServletException e) {
			throw e;
		}
		
	}

	public List<String> llenarAdministrador(List<String> administrador){
		administrador.add("/seguridad_system/login2.xhtml");
		return administrador;
	}

	public List<String> llenarUsuEstandar(List<String> estandar){
		estandar.add("/seguridad_system/login2.xhtml");
		return estandar;
	}

	public List<String> getAdministrador() {
		return administrador;
	}

	public void setAdministrador(List<String> administrador) {
		this.administrador = administrador;
	}

	public List<String> getUsuarioEstandar() {
		return usuarioEstandar;
	}

	public void setUsuarioEstandar(List<String> usuarioEstandar) {
		this.usuarioEstandar = usuarioEstandar;
	}

	public List<String> getExterno() {
		return externo;
	}

	public void setExterno(List<String> externo) {
		this.externo = externo;
	}

	public String getPaginaError() {
		return paginaError;
	}

	public void setPaginaError(String paginaError) {
		this.paginaError = paginaError;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		LoginFilter.logger = logger;
	}
}