package com.vortexbird.pusay.cuestionarios.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.pusay.cuestionarios.dao.ICueRespuestaDAO;
import com.vortexbird.pusay.cuestionarios.dao.support.HibernateDaoImpl;
import com.vortexbird.pusay.cuestionarios.model.CueRespuesta;

@Scope("singleton")
@Repository("CueRespuestaDAO")
public class CueRespuestaDAO extends HibernateDaoImpl<CueRespuesta, Long> implements ICueRespuestaDAO {

	/**
	 * Implementaci�n detallada del DAO en caso de requerirlo
	 */
}