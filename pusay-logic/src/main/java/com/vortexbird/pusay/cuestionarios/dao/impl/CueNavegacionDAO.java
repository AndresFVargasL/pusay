package com.vortexbird.pusay.cuestionarios.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.pusay.cuestionarios.dao.ICueNavegacionDAO;
import com.vortexbird.pusay.cuestionarios.dao.support.HibernateDaoImpl;
import com.vortexbird.pusay.cuestionarios.model.CueNavegacion;

@Scope("singleton")
@Repository("CueNavegacionDAO")
public class CueNavegacionDAO extends HibernateDaoImpl<CueNavegacion, Long> implements ICueNavegacionDAO {

	/**
	 * Implementaci�n detallada del DAO en caso de requerirlo
	 */
}