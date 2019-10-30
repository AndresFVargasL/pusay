package com.vortexbird.pusay.cuestionarios.api.util;

import java.util.Locale;
import java.util.ResourceBundle;

//import co.com.coomeva.logger.CoomevaLoggerFactory;
//import co.com.coomeva.logger.ICoomevaLogger;

public class LoadBundle {

	private ResourceBundle resources;

	/**
	 * Contructor de la clase.
	 */
	public LoadBundle() {

	}

	/**
	 * Contructor de la clase con parametros.
	 */
	public LoadBundle(String archivo) {
		try {
			resources = ResourceBundle.getBundle(archivo, Locale.getDefault());
		} catch (Exception e) {
			// log.error("@class " + e.getClass());
			// log.error("No se puede cargar el archivo: " + archivo);
		}
	}

	/**
	 * Carga el archivo de propiedades.
	 * 
	 * @param archivo
	 *            - Debe incluir el nombre del paquete y el nombre del archivo
	 *            son extencion.
	 */
	public void cargarArchivo(String archivo) {

		try {
			resources = ResourceBundle.getBundle(archivo, Locale.getDefault());
		} catch (Exception e) {
			// log.error("@class " + e.getClass());
			// log.error("No se puede cargar el archivo: " + archivo);
		}
	}

	/**
	 * Retorna la propiedad que le pertenece a la llave entregada por parametro.
	 * 
	 * @return String
	 */
	public String getProperty(String llave) {

		String propiedad = null;
		try {
			propiedad = resources.getString(llave);
		} catch (Exception e) {
			// log.error("@class " + e.getClass());
			// log.error("No se puede encontrar la llave: " + llave);
		}

		return propiedad;
	}

}
