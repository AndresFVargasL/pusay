package com.vortexbird.pusay.cuestionarios.dao;

import com.vortexbird.pusay.cuestionarios.api.dao.support.Dao;
import com.vortexbird.pusay.cuestionarios.model.CuePregunta;

public interface ICuePreguntaDAO extends Dao<CuePregunta, Long> {
	// property constants
	public static final String CONDICION = "condicion";
	public static final String CONSECUTIVO = "consecutivo";
	public static final String ENUNCIADO = "enunciado";
	public static final String ESTADO = "estado";
	public static final String LABELAMPLIACION = "labelAmpliacion";
	public static final String NRORESPUESTAS = "nroRespuestas";
	public static final String ORDEN = "orden";
	public static final String PUNTAJE = "puntaje";
}
