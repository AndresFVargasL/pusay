package com.vortexbird.pusay.cuestionarios.dao;

import com.vortexbird.pusay.cuestionarios.api.dao.support.Dao;
import com.vortexbird.pusay.cuestionarios.model.CueConfiguracion;

/**
 * Interface for CueConfiguracionDAO.
 * 
 */
public interface ICueConfiguracionDAO extends Dao<CueConfiguracion, Long> {
	// property constants
	public static final String ABIERTO = "abierto";
	public static final String CLAVEACCESO = "claveAcceso";
	public static final String COLORTABLA = "colorTabla";
	public static final String CONSECUTIVO = "consecutivo";
	public static final String HEADER = "header";
	public static final String MENSAJECIERRE = "mensajeCierre";
	public static final String MENSAJECLAVEINCORRECTA = "mensajeClaveIncorrecta";
	public static final String MENSAJECUESTIONARIOFINALIZAD = "mensajeCuestionarioFinalizad";
	public static final String MENSAJEFECHALIMITE = "mensajeFechaLimite";
	public static final String MENSAJEMAXIMORESPUESTAS = "mensajeMaximoRespuestas";
	public static final String MENSAJEREDIRECCIONAL = "mensajeRedireccional";
	public static final String MULTIPLERESPUESTA = "multipleRespuesta";
	public static final String MULTIPLERESPUESTAMSJ = "multipleRespuestaMsj";
	public static final String PUNTAJEMAX = "puntajeMax";
	public static final String REDIRIGIRCERRAR = "redirigirCerrar";
	public static final String REDIRIGIRINFORME = "redirigirInforme";
	public static final String REDIRIGIRURL = "redirigirUrl";
	public static final String RETOMARCUESTIONARIO = "retomarCuestionario";
	public static final String VIGENCIAFIN = "vigenciaFin";
	public static final String VIGENCIAINICIO = "vigenciaInicio";
}