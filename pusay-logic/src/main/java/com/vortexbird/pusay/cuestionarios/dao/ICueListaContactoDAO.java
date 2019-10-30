package com.vortexbird.pusay.cuestionarios.dao;

import com.vortexbird.pusay.cuestionarios.api.dao.support.Dao;
import com.vortexbird.pusay.cuestionarios.model.CueListaContacto;

public interface ICueListaContactoDAO extends Dao<CueListaContacto, Long> {
	// property constants
	public static final String CONSECUTIVO = "consecutivo";
	public static final String DURACION = "duracion";
	public static final String ESTADO = "estado";
	public static final String FECHAHORAASIGNACION = "fechaHoraAsignacion";
	public static final String FECHAHORAFINALIZACION = "fechaHoraFinalizacion";
	public static final String PUNTAJETOTAL = "puntajeTotal";
}