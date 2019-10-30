package com.vortexbird.pusay.cuestionarios.web.utils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.model.SelectItem;
import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
//import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;
import org.hibernate.validator.resourceloading.ResourceBundleLocator;
import org.springframework.dao.DataAccessException;

/**
 * Clase base para manejar los mensajes de jsf.
 * 
 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
 * @project cuestionario-web
 * @class AbstractBaseMessageUI
 * @date 12/07/2013
 * 
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractBaseMessageUI extends AbstractBaseUI {
	private static final String MESSAGE_NOT_FOUND_PREFIX = "??";

	public AbstractBaseMessageUI() {

	}

	protected void error(String idComponente, Exception e) {
		if (e == null) {
			return;
		}

		if (e instanceof NullPointerException) {
			error("null");
		} else if (e instanceof ConstraintViolationException) {
			addConstraintError((ConstraintViolationException) e);
		} else if (e instanceof javax.validation.ConstraintViolationException) {
			Set constraintViolations = ((javax.validation.ConstraintViolationException) e).getConstraintViolations();
			generateError(constraintViolations, null);
		} else if (e instanceof org.springframework.transaction.UnexpectedRollbackException) {
			error("i18n.messages", "HBM_GENERIC_ERROR");
		} else {
			Throwable cause = e.getCause();
			if (cause != null) {
				e.printStackTrace();
				if (cause instanceof NullPointerException) {
					error("null");
					return;
				} else if (cause instanceof ConstraintViolationException) {
					addConstraintError((ConstraintViolationException) cause);
					return;
				}
			}

			if (cause != null
					&& (DataAccessException.class.isAssignableFrom(cause.getClass())
							|| HibernateException.class.isAssignableFrom(cause.getClass()) || SQLException.class
								.isAssignableFrom(cause.getClass()))) {
				error("i18n.messages", "HBM_GENERIC_ERROR");
			} else {
				if (StringUtils.isNull(idComponente)) {
					error(e.getMessage());
				} else {
					errorComponent(idComponente, e.getMessage());
				}
			}
		}
	}

	private void addConstraintError(ConstraintViolationException cause) {
		int errorCode = cause.getErrorCode();
		if (errorCode > 0) {
			if (errorCode == 1) {
				addMessage(null,
						getMessage("DB_ERRORCODE_" + errorCode) + " Restricci�n: " + cause.getConstraintName(),
						FacesMessage.SEVERITY_ERROR);
			} else {
				addMessage(null, getMessage("DB_ERRORCODE_" + errorCode), FacesMessage.SEVERITY_ERROR);
			}
		} else {
			addMessage(null, getMessage("DB_ERRORCODE_UNKNOWN" + errorCode), FacesMessage.SEVERITY_ERROR);
		}
	}

	/**
	 * Informa un mensaje de error.
	 * 
	 * @param e
	 */
	protected void error(Exception e) {
		error(null, e);
	}

	protected void error(String message) {
		if (message == null || "null".equals(message)) {
			message = getMessage("NULL_EXCEPTION");
		}
		addMessage(message, FacesMessage.SEVERITY_ERROR);
	}

	protected void error(String messageBundle, String key) {
		addMessage(getMessage(messageBundle, key), FacesMessage.SEVERITY_ERROR);
	}

	protected void errorComponent(String idComponente, String msg) {
		if (idComponente != null) {
			UIComponent ui = findElementById(idComponente);
			if (ui != null) {
				idComponente = ui.getClientId(getFacesContext());
			}
		}
		addMessage(idComponente, msg, FacesMessage.SEVERITY_ERROR);
	}

	protected void errorComponent(String idComponente, String messageBundle, String key) {
		errorComponent(idComponente, getMessage(messageBundle, key));
	}

	/**
	 * Informa un mensaje de informacion.
	 * 
	 * @param msg
	 */
	protected void info(String msg) {
		addMessage(msg, FacesMessage.SEVERITY_INFO);
	}

	/**
	 * Informa un mensaje de informacion buscando una key en un messageBundle.
	 * 
	 * @param bundle
	 * @param key
	 */
	protected void info(String bundle, String key) {
		info(null, bundle, key);
	}

	protected void info(String idComponente, String bundle, String key) {
		if (idComponente != null) {
			UIComponent ui = findElementById(idComponente);
			if (ui != null) {
				idComponente = ui.getClientId(getFacesContext());
			}
		}
		addMessage(idComponente, getMessage(bundle, key), FacesMessage.SEVERITY_INFO);
	}

	/**
	 * Informa un mensaje de warning.
	 * 
	 * @param msg
	 */
	protected void warn(String msg) {
		addMessage(msg, FacesMessage.SEVERITY_WARN);
	}

	/**
	 * Informa un mensaje de warning buscando una key en un messageBundle.
	 * 
	 * @param bundle
	 * @param key
	 */
	protected void warn(String bundle, String key) {
		warn(null, bundle, key);
	}

	protected void warn(String idComponente, String bundle, String key) {
		if (idComponente != null) {
			UIComponent ui = findElementById(idComponente);
			if (ui != null) {
				idComponente = ui.getClientId(getFacesContext());
			}
		}

		addMessage(idComponente, getMessage(bundle, key), FacesMessage.SEVERITY_WARN);
	}

	protected void addMessage(FacesMessage.Severity severity, String key) {
		addMessage(null, getMessage(null, key), severity);
	}

	protected void addMessage(FacesMessage.Severity severity, String messageBundle, String key) {
		addMessage(null, getMessage(null, key), severity);
	}

	protected void addMessage(String message, FacesMessage.Severity severity) {
		addMessage(null, message, severity);
	}

	protected void addMessage(String idComponente, String message, FacesMessage.Severity severity) {
		getFacesContext().addMessage(idComponente, new FacesMessage(severity, message, null));
	}

	protected static String getMessage(String messageBundle, String key) {
		try {
			if (messageBundle == null) {
				messageBundle = "i18n.messages";
			}

			ResourceBundle bundle = getResourceBundle(messageBundle);
			return bundle.getString(key);
		} catch (Exception e) {
			return MESSAGE_NOT_FOUND_PREFIX + key + MESSAGE_NOT_FOUND_PREFIX;
		}
	}

	protected static String getMessage(String key) {
		return getMessage(null, key);
	}

	protected static ResourceBundle getResourceBundle(String messagePropertyName) {
		ResourceBundle bundle = ResourceBundle.getBundle(messagePropertyName, getLocale());
		return bundle;
	}

	protected List<SelectItem> toSelectItemEnum(Class<? extends Enum> enumClass, String messageBlundle) {
		return toSelectItemEnum(enumClass, messageBlundle, true);
	}

	protected List<SelectItem> toSelectItemEnumSinDefault(Class<? extends Enum> enumClass, String messageBlundle) {
		return toSelectItemEnum(enumClass, messageBlundle, false);
	}

	private List<SelectItem> toSelectItemEnum(Class<? extends Enum> enumClass, String messageBlundle,
			boolean addDefaultItem) {
		List<SelectItem> items = new ArrayList<SelectItem>();

		try {
			if (addDefaultItem) {
				items.add(createDefaultSelectItem());
			}

			Method method = enumClass.getDeclaredMethod("values");
			Object[] enumValues = (Object[]) method.invoke(null);
			for (int i = 0; i < enumValues.length; i++) {
				String message = enumValues[i].toString();
				if (messageBlundle == null) {
					message = getMessage(enumValues[i].toString());
				} else {
					message = getMessage(messageBlundle, enumValues[i].toString());
				}

				items.add(new SelectItem(enumValues[i], message));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}

	protected List<SelectItem> toSelectItem(Collection<? extends Serializable> result, String valueVariable,
			String labelVariable) {
		return toSelectItema(result, valueVariable, labelVariable, true);
	}

	protected List<SelectItem> toSelectItemSinDefault(Collection<? extends Serializable> result, String valueVariable,
			String labelVariable) {
		return toSelectItema(result, valueVariable, labelVariable, false);
	}

	private List<SelectItem> toSelectItema(Collection<? extends Serializable> result, String valueVariable,
			String labelVariable, boolean addDefaultItem) {
		List<SelectItem> items = new ArrayList<SelectItem>(result != null ? result.size() : 0);

		if (result != null && !result.isEmpty()) {

			if (addDefaultItem) {
				items.add(createDefaultSelectItem());
			}

			for (Object bean : result) {
				try {
					Object value = PropertyUtils.getSimpleProperty(bean, valueVariable);
					Object label = null;
					if (labelVariable.indexOf(',') > 0) {
						StringTokenizer st = new StringTokenizer(labelVariable, ",");
						while (st.hasMoreTokens()) {
							String str = (String) st.nextToken();
							Object llb = PropertyUtils.getSimpleProperty(bean, str);
							if (llb != null) {
								label = (label != null ? (label.toString() + " - ") : "") + llb.toString();
							}
						}
					} else {
						label = PropertyUtils.getSimpleProperty(bean, labelVariable);
					}
					if (value != null && label != null) {
						items.add(new SelectItem(value, label.toString()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return items;
	}

	/**
	 * Metodo que permite validar un objeto mediante Bean Validation. Con el resultado de la validacion se crean
	 * mensajes de error de faces.
	 * 
	 * @param entity
	 * @return true si hay violaciones a las validaciones, false si no se encontraron errores.
	 */
	protected boolean validate(Object entity) {
		return validate(entity, (Class<?>) null);
	}

	protected boolean validate(Object entity, Class<?>... groups) {
		return validate(entity, null, groups);
	}

	/**
	 * Metodo que permite validar un objeto mediante Bean Validation, agregando un prefijo al identificador del
	 * atributo. Con el resultado de la validacion se crean mensajes de error de faces.
	 * 
	 * @param entity
	 * @param prefix
	 * @return true si no hay violaciones a las validaciones, false si se encontraron errores.
	 */
	protected boolean validate(Object entity, String prefix) {
		return validate(entity, prefix, (Class<?>) null);
	}

	protected boolean validate(Object entity, String prefix, Class<?>... groups) {
		// TODO: llevar el factory a la sesion por ejemplo para que no se crea a
		// cada rato.

		Configuration<?> configuration = Validation.byDefaultProvider().configure();
		ResourceBundleLocator resourceBundleLocator = new ResourceBundleLocator() {
			public ResourceBundle getResourceBundle(Locale locale) {
				return ResourceBundle.getBundle("ValidationMessages", getLocale());
			}
		};
		configuration.messageInterpolator(new ResourceBundleMessageInterpolator(resourceBundleLocator));

		ValidatorFactory factory = configuration.buildValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Object>> constraintViolations = null;
		if (groups == null || (groups.length == 1 && groups[0] == null)) {
			constraintViolations = validator.validate(entity);
		} else {
			constraintViolations = validator.validate(entity, groups);
		}
		generateError(constraintViolations, prefix);

		return constraintViolations.isEmpty();

	}

	private void generateError(Set violations, String prefix) {
		if (StringUtils.isNull(prefix)) {
			prefix = "";
		}
		for (Object vio : violations) {
			ConstraintViolation cv = (ConstraintViolation) vio;
			String property = prefix + cv.getRootBean().getClass().getSimpleName() + "_"
					+ cv.getPropertyPath().toString();

			errorComponent(property, getMessageTranslated(cv.getMessage()));
		}
	}

	private String getMessageTranslated(String message) {
		String msg = getMessage(message);
		return (msg.startsWith(MESSAGE_NOT_FOUND_PREFIX)) ? message : msg;
	}

	protected SelectItem createDefaultSelectItem() {
		return new SelectItem("", getMessage("LABEL_SELECCIONE"));
	}

	/**
	 * 
	 * @author <a href="mailto:galvez.alejo@gmail.com">Manuel Alejandro Galvez</a>
	 * @date 11/02/2012
	 * @param numero
	 * @return
	 */
	protected String completarNumeroString(Integer numero) {
		String numberSTR = numero.toString();
		if (numberSTR.length() < 2) {
			return "0" + numberSTR;
		} else {
			return numberSTR;
		}
	}
}