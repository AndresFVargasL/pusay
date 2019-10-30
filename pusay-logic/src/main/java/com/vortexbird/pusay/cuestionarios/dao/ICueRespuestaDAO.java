package com.vortexbird.pusay.cuestionarios.dao;

import com.vortexbird.pusay.cuestionarios.api.dao.support.Dao;
import com.vortexbird.pusay.cuestionarios.model.CueRespuesta;

/**
 * Interface for CueRespuestaDAO.
 * 
 */
public interface ICueRespuestaDAO extends Dao<CueRespuesta, Long> {
	// property constants
	public static final String CONSECUTIVO = "consecutivo";
	public static final String FECHAHORARESPUESTA = "fechaHoraRespuesta";
	public static final String IP = "ip";

}
