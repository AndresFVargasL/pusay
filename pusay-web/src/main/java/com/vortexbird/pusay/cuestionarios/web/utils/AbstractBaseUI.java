package com.vortexbird.pusay.cuestionarios.web.utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AbstractBaseUI {

	public static final String CURRENT_LOCALE = "currentLocale";

	public AbstractBaseUI() {
		super();
	}

	/**
	 * Devuelve el locale de la aplicacion.
	 * 
	 * @return
	 */
	protected static Locale getLocale() {
		Locale currentLocale = (Locale) getSession().getAttribute(CURRENT_LOCALE);
		if (currentLocale != null) {
			return currentLocale;
		}
		return getFacesContext().getViewRoot().getLocale();
	}

	/**
	 * Devuelve el contecto faces de la aplicacion.
	 * 
	 * @return
	 */
	protected static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	protected static HttpSession getSession() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		HttpSession session = request.getSession();
		return session;
	}

	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) getFacesContext().getExternalContext().getResponse();
	}

	protected String getContextPath() {
		return ((HttpServletRequest) getFacesContext().getExternalContext().getRequest()).getContextPath();
	}

	protected ServletContext getServletContext() {
		return (ServletContext) getFacesContext().getExternalContext().getContext();
	}

	/**
	 * Devuelve un mapa con los parametros del request.
	 * 
	 * @return
	 */
	protected Map<String, String> getRequestParameter() {
		return getFacesContext().getExternalContext().getRequestParameterMap();
	}

	/**
	 * Redirect a una url relativa al contexto de la aplicacion. <br>
	 * <b>Por ejemplo:</b> /pages/forms/factura/aprobarFacturas.jspx?id=1234&nombre=juan
	 * 
	 * @param url
	 */
	protected void redirect(String url) {
		try {
			// Invoke a redirect to the action URL.
			String rcp = getFacesContext().getExternalContext().getRequestContextPath();
			getFacesContext().getExternalContext().redirect(rcp + url);
		} catch (IOException e) {
			// Uhh, something went seriously wrong.
			throw new FacesException("Cannot redirect to " + url + " due to IO exception.", e);
		}
	}

	protected String getPageName() {
		String viewId = getFacesContext().getViewRoot().getViewId();
		if (!StringUtils.isNull(viewId)) {
			String page = viewId.substring(viewId.lastIndexOf('/') + 1);
			if (!StringUtils.isNull(page) && page.length() > 0) {
				viewId = page.substring(0, page.indexOf('.'));
			}
		}
		return viewId;
	}

	protected Iterator<String> getRequestParameterNames() {
		return getFacesContext().getExternalContext().getRequestParameterNames();
	}

	/**
	 * Devuelve la accion del motivo a mostrar.
	 * 
	 * @param comp
	 * @param key
	 * @return
	 */
	protected String getActionMotivo(UIComponent comp, String key) {

		String formName = getFormName(comp);

		String action = getRequestParameter().get(formName + key);
		if (action != null && action.length() > 0) {
			int last = action.lastIndexOf(":");
			return action.substring(last + 1);
		}
		return action;
	}

	/**
	 * Devuelve el id del formulario. Se busca el control de tipo htmlform y de ese se devuelve el id.
	 * 
	 * @param componente
	 * @return
	 */
	protected String getFormName(UIComponent component) {
		if (component instanceof HtmlForm) {
			return component.getId();
		} else {
			return getFormName(component.getParent());
		}
	}

	/**
	 * Metodo para encontrar un UIComponent por id.
	 * 
	 * @param uiComponent
	 *            Componente padre.
	 * @param id
	 *            Identificador del componente
	 * @return
	 */
	public UIComponent findElementById(UIComponent uiComponent, String id) {
		UIComponent ret = null;
		String actualId = uiComponent.getId();

		if (id.equals(actualId))
			return uiComponent;
		List<UIComponent> list = uiComponent.getChildren();
		Iterator<UIComponent> iter = list.iterator();

		while (iter.hasNext() && ret == null) {
			UIComponent element = (UIComponent) iter.next();
			ret = findElementById(element, id);
		}
		return ret;
	}

	/**
	 * Busca un componente desde el raiz (ViewRoot)
	 * 
	 * @param id
	 *            Identificador del componente
	 * @return
	 */
	protected UIComponent findElementById(String id) {
		return findElementById(getFacesContext().getViewRoot(), id);
	}
	
	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 15/07/2013
	 * @return
	 
	protected String getLogin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetails) {
				return ((UserDetails) principal).getUsername();
			} else {
				return principal.toString();
			}
		}
		return null;
	} */

	/**
	 * Retorna el nombre de la persona logueada
	 * 
	 * @author <a href="mailto:cristian.arboleda@premize.com">Cristian M. Arboleda</a>
	 * @date May 3, 2012
	 * @return the namePersonlogin
	
	public String getNamePersonlogin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetailsDTO) {
				return ((UserDetailsDTO) principal).getNombre();
			} else if (principal instanceof UserDetails) {
				return ((UserDetails) principal).getUsername();
			} else {
				return principal.toString();
			}
		}
		return "";
	}  */

	/**
	 * Verifica si el usuario logueado tiene uno o varios role. Los roles se especifican ROL1, ROL2,ROL3 es decir,
	 * separados por comas.
	 * 
	 * @param roles
	 * @return
	
	protected boolean isUserInRol(String roles) {
		return isUserInRol(getLogin(), roles);
	}  */

	/**
	 * Verifica si un usuario tiene uno o varios role. Los roles se especifican ROL1, ROL2,ROL3 es decir, separados por
	 * comas.
	 * 
	 * @param user
	 * @param roles
	 * @return
	 
	protected boolean isUserInRol(String user, String roles) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetails) {
				for (GrantedAuthority authority : ((UserDetails) principal).getAuthorities()) {

					StringTokenizer tokenizer = new StringTokenizer(roles, ",");
					while (tokenizer.hasMoreTokens()) {
						String nextToken = tokenizer.nextToken();
						if (nextToken != null && authority.equals(nextToken.trim())) {
							return true;
						}
					}
				}
			}
		}
		return false;
	} */

	/**
	 * Verifica que el usuario solo tenga los roles especificados en el parametro roles.
	 * 
	 * @param user
	 * @param roles
	 * @return
	 
	protected boolean isUserInOnlyRoles(String user, String roles) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			for (GrantedAuthority authority : ((UserDetails) principal).getAuthorities()) {

				StringTokenizer tokenizer = new StringTokenizer(roles, ",");
				boolean founded = false;
				while (tokenizer.hasMoreTokens()) {
					String nextToken = tokenizer.nextToken();
					if (nextToken != null && authority.equals(nextToken.trim())) {
						founded = true;
						break;
					}
				}
				if (!founded) {
					return false;
				}
			}
		}
		return true;
	}

	protected Collection<GrantedAuthority> getPermisosUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getAuthorities();
		}
		return Collections.emptyList();
	} */

	/**
	 * Retorna el managed bean previamente configurado
	 * 
	 * @param classBean
	 *            managed bean
	 * @return la referencia al managed bean
	 */
	protected <T extends AbstractBaseUI> T getManagedBean(Class<T> classBean) {
		String classConvencion = classBean.getSimpleName().substring(0, 1).toLowerCase()
				.concat(classBean.getSimpleName().substring(1));
		return resolveExpression("#{" + classConvencion + "}", classBean);
	}

	@SuppressWarnings({"unchecked"})
	private <T extends AbstractBaseUI> T resolveExpression(String expression, Class<T> classBean) {
		T value = null;
		if ((expression.indexOf("#{") != -1) && (expression.indexOf("#{") < expression.indexOf('}'))) {
			value = (T) getFacesContext().getApplication().getExpressionFactory()
					.createValueExpression(getFacesContext().getELContext(), expression, classBean)
					.getValue(getFacesContext().getELContext());
		}
		return value;
	}

}