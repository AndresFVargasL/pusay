package com.vortexbird.pusay.cuestionarios.dao;

import com.vortexbird.pusay.cuestionarios.api.dao.support.Dao;
import com.vortexbird.pusay.cuestionarios.model.CueOpcion;

public interface ICueOpcionDAO extends Dao<CueOpcion, Long> {
	// property constants
	public static final String CONDICION = "condicion";
	public static final String CONSECUTIVO = "consecutivo";
	public static final String ENUNCIADO = "enunciado";
	public static final String ESTADO = "estado";
	public static final String INDICADORCORRECTA = "indicadorCorrecta";
	public static final String LABELAMPLIACION = "labelAmpliacion";
	public static final String ORDEN = "orden";
	public static final String PUNTAJE = "puntaje";
	public static final String REQUIEREAMPLIACION = "requiereAmpliacion";
	
	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 16/10/2013
	 * @param preguntaCodigo
	 * @return
	 */
	int countRequiereAmpliacionByPregunta(Long preguntaCodigo);
}
