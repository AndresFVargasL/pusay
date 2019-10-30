package com.vortexbird.pusay.cuestionarios.web.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Utilidad para manejo de Strings.
 * 
 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
 * @project cuestionario-web
 * @class StringUtils
 * @date 12/07/2013
 * 
 */
public class StringUtils {
	public static final String USD = "USD";

	private StringUtils() {

	}

	/**
	 * Devuelve un String sin los espacios. Si es vacio entonces devuelve null.
	 * 
	 * @param value
	 * @return
	 */
	public static String trimToNull(String value) {
		if (value == null) {
			return null;
		}
		if (value.trim().length() == 0) {
			return null;
		}
		return value.trim();
	}

	/**
	 * Verifica si un String es nulo o vacio.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNull(String value) {
		if (value == null || value.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Permite generar un String a partir de un arreglo de objetos.
	 * <P>
	 * El String devuelto tiene la siguiente forma: {1, 2, 3, 4}
	 * 
	 * @param array
	 * @return
	 */
	public static String join(Object[] array) {
		StringBuffer sb = new StringBuffer(" {");
		if (array != null && array.length > 0) {
			for (int i = 0; i < array.length; i++) {
				sb.append(array[i].toString());

				if (i < array.length - 1) {
					sb.append(", ");
				}
			}
			sb.append("}");
		}
		return sb.toString();
	}

	/**
	 * Permite generar un String a partir de un arreglo de objetos.
	 * <P>
	 * El String devuelto tiene la siguiente forma: 1, 2, 3, 4
	 * 
	 * @param array
	 * @return
	 */
	public static String join2(Object[] array) {
		StringBuffer sb = new StringBuffer("");
		if (array != null && array.length > 0) {
			for (int i = 0; i < array.length; i++) {
				sb.append(array[i].toString());

				if (i < array.length - 1) {
					sb.append(", ");
				}
			}
			sb.append("");
		}
		return sb.toString();
	}

	/**
	 * Retorna un String de la forma ?, ?, ? donde la cantidad de elementos determina la cantidad de signos de preguntas
	 * a devolver.
	 * 
	 * @param array
	 * @return
	 */
	public static String join2Signal(Object[] array) {
		StringBuffer sb = new StringBuffer("");
		if (array != null && array.length > 0) {
			for (int i = 0; i < array.length; i++) {
				sb.append("?");

				if (i < array.length - 1) {
					sb.append(", ");
				}
			}
			sb.append("");
		}
		return sb.toString();
	}

	/**
	 * Formatea un valor con un patron determinado.
	 * 
	 * @param value
	 * @param pattern
	 * @return
	 */
	public static String formatNumber(double value, String pattern) {
		NumberFormat formatter = new DecimalFormat(pattern);
		return formatter.format(value);
	}

	public static String formatNumber(BigDecimal value, String pattern) {
		NumberFormat formatter = new DecimalFormat(pattern);
		return formatter.format(value.doubleValue());
	}

	/**
	 * Retorna el valor con formato de dinero
	 * 
	 * @param valor
	 * @param locale
	 * @return
	 */
	public static String formatMoneda(Double valor, String sigla) {
		Locale locale = getLocale(sigla);
		NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		return format.format(valor);
	}

	public static Locale getLocale(String sigla) {
		Locale locale = null;
		// TODO: completar con otras monedas.
		if (USD.equals(sigla)) {
			locale = Locale.US;
		} else {
			locale = new Locale("es", "CO");
		}
		return locale;
	}

}
