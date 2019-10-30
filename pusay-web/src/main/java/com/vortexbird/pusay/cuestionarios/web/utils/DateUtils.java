package com.vortexbird.pusay.cuestionarios.web.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Utilidades para formateo e interpretacion de fechas. Esta clase no es threadsafe. Cada thread debe invocar al metodo
 * {@link #getInstance} para obtener una instancia dedicada.
 * 
 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
 * @project cuestionario-web
 * @class DateUtils
 * @date 12/07/2013
 * 
 */
public final class DateUtils {
	public static final String DEFAULT_FORMATO_FECHA = "dd/MM/yyyy HH:mm";

	private static final String[] formatosString = { DEFAULT_FORMATO_FECHA, "dd/MM/yyyy", "dd/MM/yyyy hh:mm:ss a",
			"dd-MM-yyyy HH:mm:ss z", "dd-MM-yyyy HH:mm:ss", "dd-MM-yyyyZ", "dd-MM-yyyy", "ddMMyy",
			"EEEEEEEEE, dd MMMMMMMMMM yyyy HH:mm" };

	private static final ThreadLocal<DateUtils> tlInstancia = new ThreadLocal<DateUtils>();

	private final DateFormat[] formatos;

	/**
	 * Devuelve una instancia de esta utileria para el thread actual.
	 * 
	 * @return instancia
	 */
	public static DateUtils getInstance() {
		DateUtils inst = (DateUtils) tlInstancia.get();
		if (inst == null) {
			inst = new DateUtils();
			tlInstancia.set(inst);
		}
		return inst;
	}

	/**
	 * Construye una nueva instancia.
	 */
	private DateUtils() {
		formatos = new DateFormat[formatosString.length];
		for (int i = 0; i < formatos.length; i++) {
			formatos[i] = new SimpleDateFormat(formatosString[i], new Locale("es", "CO"));
		}
	}

	/**
	 * Asigna los formatos.
	 * 
	 * @param pFormatos
	 */
	public void setFormatos(DateFormat[] pFormatos) {
		System.arraycopy(pFormatos, 0, formatos, 0, formatos.length);
	}

	/**
	 * Devuelve los formatos de fechas.
	 * 
	 * @return
	 */
	public DateFormat[] getFormatos() {
		return (DateFormat[]) formatos.clone();
	}

	/**
	 * Interpreta una fecha especifica de un string.
	 * 
	 * @param pFecha
	 *            La fecha como string
	 * @return El objeto fecha
	 * @throws ParseException
	 *             si el formato no esta soportado
	 */
	public Date parse(String pFecha) throws ParseException {
		if (pFecha == null) {
			return null;
		}

		for (int i = 0; i < formatos.length; i++) {
			try {
				return formatos[i].parse(pFecha);
			} catch (ParseException e) {
			}
		}
		throw new ParseException("Formato de fecha no soportado: " + pFecha, -1);
	}

	/**
	 * Formatea una fecha.
	 * 
	 * @param pDate
	 * @return
	 */
	public String format(Date pDate) {
		return pDate == null ? null : formatos[0].format(pDate);
	}

	public String format(Date pDate, int ff) {
		return pDate == null ? null : formatos[ff].format(pDate);
	}

	public String format(Date pDate, int ff, String sigla) {
		DateFormat df = null;

		// TODO: completar con otras monedas.
		if (StringUtils.USD.equals(sigla)) {
			df = new SimpleDateFormat(formatosString[ff], Locale.US);
		} else {
			df = formatos[ff];
		}

		return pDate == null ? null : df.format(pDate);
	}

	public static boolean isFechaPosterior(Date fecha, Date fechaPosterior) {
		if (fecha != null && fechaPosterior != null) {
			return fecha.getTime() > fechaPosterior.getTime();
		}
		return false;
	}

	public static Date addMeses(Date fechaBase, int cantidadMeses) {
		if (fechaBase != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaBase);
			calendar.add(Calendar.MONTH, cantidadMeses);

			return calendar.getTime();
		}
		return null;
	}

	public static Date addHour(Date fechaBase, int cantidadHour) {
		if (fechaBase != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaBase);
			calendar.add(Calendar.HOUR, cantidadHour);

			return calendar.getTime();
		}
		return null;
	}

	public static Date restarDia(Date fechaBase, int cantidadDias) {
		if (fechaBase != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaBase);
			calendar.add(Calendar.DAY_OF_YEAR, -1 * cantidadDias);

			return calendar.getTime();
		}
		return null;
	}

	public static boolean isEquals(Date fechaBase, Date fecha2) {
		if (fechaBase != null && fecha2 != null) {
			return fechaBase.getTime() == fecha2.getTime();
		}
		return false;
	}

	public static Date formatDateToCriteria(Date date) throws Exception {
		Date fechaFinaliza = date;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		try {
			fechaFinaliza = dateFormat.parse((String) dateFormat.format(fechaFinaliza));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return fechaFinaliza;
	}

	public static Date formatDateToReport(Date date) throws Exception {
		Date fechaFinaliza = date;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		try {
			fechaFinaliza = dateFormat.parse((String) dateFormat.format(fechaFinaliza));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return fechaFinaliza;
	}

	public int getFechaField(Date date, int field) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(field);
	}

	public static List<Integer> getMesesAnio() {
		List<Integer> listMesesCalendar = new ArrayList<Integer>();
		listMesesCalendar.add(Calendar.JANUARY);
		listMesesCalendar.add(Calendar.FEBRUARY);
		listMesesCalendar.add(Calendar.MARCH);
		listMesesCalendar.add(Calendar.APRIL);
		listMesesCalendar.add(Calendar.MAY);
		listMesesCalendar.add(Calendar.JUNE);
		listMesesCalendar.add(Calendar.JULY);
		listMesesCalendar.add(Calendar.AUGUST);
		listMesesCalendar.add(Calendar.SEPTEMBER);
		listMesesCalendar.add(Calendar.OCTOBER);
		listMesesCalendar.add(Calendar.NOVEMBER);
		listMesesCalendar.add(Calendar.DECEMBER);
		return listMesesCalendar;
	}

}