package com.vortexbird.pusay.cuestionarios.dao;

import com.vortexbird.pusay.cuestionarios.api.dao.support.Dao;
import com.vortexbird.pusay.cuestionarios.model.CueListaCuestionario;

/**
 * Interface for CueListaCuestionarioDAO.
 * 
 */
public interface ICueListaCuestionarioDAO extends Dao<CueListaCuestionario, Long> {
	// property constants
	public static final String CONSECUTIVO = "consecutivo";
	public static final String FECHAHORAASIGNACION = "fechaHoraAsignacion";
}