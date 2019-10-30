package com.vortexbird.pusay.cuestionarios.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.pusay.cuestionarios.dao.ICueConfiguracionDAO;
import com.vortexbird.pusay.cuestionarios.dao.support.HibernateDaoImpl;
import com.vortexbird.pusay.cuestionarios.model.CueConfiguracion;

@Scope("singleton")
@Repository("CueConfiguracionDAO")
public class CueConfiguracionDAO extends HibernateDaoImpl<CueConfiguracion, Long> implements ICueConfiguracionDAO {

	/**
	 * Implementaci�n detallada del DAO en caso de requerirlo
	 */
}