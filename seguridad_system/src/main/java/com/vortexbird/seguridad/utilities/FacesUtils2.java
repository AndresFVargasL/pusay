package com.vortexbird.seguridad.utilities;

import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@SuppressWarnings("deprecation")
public class FacesUtils2 {
	/**
	 * Get servlet context.
	 * 
	 * @return the servlet context
	 */
	public static ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	}

	/**
	 * Get managed bean based on the bean name.
	 * 
	 * @param beanName
	 *            the bean name
	 * @return the managed bean associated with the bean name
	 */
	public static Object getManagedBean(String beanName) {
		Object o = getValueBinding(getJsfEl(beanName)).getValue(FacesContext.getCurrentInstance());

		return o;
	}

	/**
	 * Remove the managed bean based on the bean name.
	 * 
	 * @param beanName
	 *            the bean name of the managed bean to be removed
	 */
	public static void resetManagedBean(String beanName) {
		Object manageBean=null;
		
		HttpServletRequest request=getRequest();
		HttpSession httpSession=request.getSession();
		ServletContext servletContext=getServletContext();
		
		manageBean=request.getAttribute(beanName);
		if(manageBean!=null){
			request.removeAttribute(beanName);
			manageBean=null;
		}
		
		manageBean=httpSession.getAttribute(beanName);
		if(manageBean!=null){
			httpSession.removeAttribute(beanName);
			manageBean=null;
		}
		
		manageBean=servletContext.getAttribute(beanName);
		if(manageBean!=null){
			servletContext.removeAttribute(beanName);
			manageBean=null;
		}
		
		/*
		if(getValueBinding(getJsfEl(beanName))!=null){
			ValueBinding valueBinding=getValueBinding(getJsfEl(beanName));
			valueBinding.setValue(FacesContext.getCurrentInstance(), null);
		}
		*/			
	}
	
	

	/**
	 * Store the managed bean inside the session scope.
	 * 
	 * @param beanName
	 *            the name of the managed bean to be stored
	 * @param managedBean
	 *            the managed bean to be stored
	 */
	@SuppressWarnings("unchecked")
	public static void setManagedBeanInSession(String beanName, Object managedBean) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(beanName, managedBean);
	}

	/**
	 * Get parameter value from request scope.
	 * 
	 * @param name
	 *            the name of the parameter
	 * @return the parameter value
	 */
	public static String getRequestParameter(String name) {
		return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
	}
	
	public static void setRequestParameter(String id,Object objeto) {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		Map map =ctx.getRequestMap();
		map.put(id, objeto);
		
	}
	
	
	
	public static void setSessionParameter(String id,Object objeto) {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		Map map =ctx.getSessionMap();
		map.put(id, objeto);		
	}
	
	public static Object getSessionParameter(String name) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(name);
	}
	
	public static HttpServletRequest getRequest(){
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return request;
		
	}

	/**
	 * Add information message.
	 * 
	 * @param msg
	 *            the information message
	 */
	public static void addInfoMessage(String msg) {
		addInfoMessage(null, msg);
	}

	/**
	 * Add information message to a sepcific client.
	 * 
	 * @param clientId
	 *            the client id
	 * @param msg
	 *            the information message
	 */
	public static void addInfoMessage(String clientId, String msg) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	/**
	 * Add error message.
	 * 
	 * @param msg
	 *            the error message
	 */
	public static void addErrorMessage(String msg) {
		addErrorMessage(null, msg);
	}

	/**
	 * Add error message to a sepcific client.
	 * 
	 * @param clientId
	 *            the client id
	 * @param msg
	 *            the error message
	 */
	public static void addErrorMessage(String clientId, String msg) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

	/**
	 * Evaluate the integer value of a JSF expression.
	 * 
	 * @param el
	 *            the JSF expression
	 * @return the integer value associated with the JSF expression
	 */
//	public static Integer evalInt(String el) {
//		if (el == null) {
//			return null;
//		}
//
//		if (UIComponentTag.isValueReference(el)) {
//			Object value = getElValue(el);
//
//			if (value == null) {
//				return null;
//			} else if (value instanceof Integer) {
//				return (Integer) value;
//			} else {
//				return new Integer(value.toString());
//			}
//		} else {
//			return new Integer(el);
//		}
//	}

	private static Application getApplication() {
		ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder
				.getFactory(FactoryFinder.APPLICATION_FACTORY);
		return appFactory.getApplication();
	}

	private static ValueBinding getValueBinding(String el) {
		return getApplication().createValueBinding(el);
	}

	public static HttpServletRequest getServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	private static Object getElValue(String el) {
		return getValueBinding(el).getValue(FacesContext.getCurrentInstance());
	}

	private static String getJsfEl(String value) {
		return "#{" + value + "}";
	}

	public static String getMessageByKey(String key) {
		String messageBundleName = FacesContext.getCurrentInstance().getApplication().getMessageBundle();
		ResourceBundle resourceBundle = ResourceBundle.getBundle(messageBundleName);

		try {
			return resourceBundle.getString(key);
		} catch (Exception e) {
			return key;
		}

	}

	public static String getUrlActual() {
		return getServletRequest().getRequestURL().toString();
	}

	public static String getDireccionRemota() {
		return getServletRequest().getRemoteAddr();
	}

	/**
	 * Convenience method to get the application's URL based on request
	 * variables.
	 */
	public static String getUrlAplicacion() {

		HttpServletRequest peticion = getServletRequest();

		StringBuffer url = new StringBuffer();
		int port = peticion.getServerPort();
		if (port < 0) {
			port = 80; // Work around java.net.URL bug
		}
		String scheme = peticion.getScheme();
		url.append(scheme);
		url.append("://");
		url.append(peticion.getServerName());
		if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) {
			url.append(':');
			url.append(port);
		}
		url.append(peticion.getContextPath());
		return url.toString();
	}
	
	public static String getRemoteAddr() {
		HttpServletRequest peticion = getServletRequest();
		return peticion.getRemoteHost();
	}

	/*
	@SuppressWarnings("unused")
	public static void showMessage(String message) {
		PopupMessage popupError = (PopupMessage) FacesUtils.getManagedBean("mensajeBean");
		if (popupError != null)
			popupError.showPopup(message);
	}
*/
	/**
	 * Retorna la session web.
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		HttpSession session = request.getSession();
		return session;
	}
	
	/**
	 * Abre una pagina del explorador nueva recibiendo la url por parametros
	 * @param url
	 * @throws Exception
	 */
	public static void openUrlPopup(String url) throws Exception{
		Long tmp=System.currentTimeMillis();
		url=url+"?"+tmp;
		//JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(),"window.open(\""+url+"\").focus();");
		
	}
	

	
	/**
	 * Reomve parameter value from request scope.
	 * 
	 * @param name the name of the parameter
	 * @return the parameter value
	 */
	public static void removeSessionParameter(String key) {		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
	}
		
	/**
	 * Get parameter value from request scope.
	 * 
	 * @param name
	 *            the name of the parameter
	 * @return the parameter value
	 */
	public static String getHttpRequestAttribute(String name) {
		return (String) getRequest().getAttribute(name);
	}
	
	public static void setHttpRequestAttribute(String id,Object objeto) {
		getRequest().setAttribute(id, objeto);
	}
	/**
	 * Remueve el atributo especificado de sesi�n
	 * @param attributeName
	 */
	public final static void removeAttribute(String attributeName){
		getSession().removeAttribute(attributeName);
	}
	/**
	 * Remueve los cookies que consevan la sesi�n del usuario
	 */
	public final static void invalidateSession(){
        
		HttpSession session = (HttpSession)
        FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		   if (session != null) {
		       session.invalidate();
		   }
	}
	
}