package com.vortexbird.pusay.cuestionarios.dao;

import com.vortexbird.pusay.cuestionarios.api.dao.support.Dao;
import com.vortexbird.pusay.cuestionarios.model.CueNavegacion;

public interface ICueNavegacionDAO extends Dao<CueNavegacion, Long> {
	// property constants
	public static final String CONSECUTIVO = "consecutivo";
}