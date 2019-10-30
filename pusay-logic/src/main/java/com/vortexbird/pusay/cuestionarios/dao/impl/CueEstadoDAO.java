package com.vortexbird.pusay.cuestionarios.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.pusay.cuestionarios.dao.ICueEstadoDAO;
import com.vortexbird.pusay.cuestionarios.dao.support.HibernateDaoImpl;
import com.vortexbird.pusay.cuestionarios.model.CueEstado;

@Scope("singleton")
@Repository("CueEstadoDAO")
public class CueEstadoDAO extends HibernateDaoImpl<CueEstado, Long> implements ICueEstadoDAO {

	/**
	 * Implementaci�n detallada del DAO en caso de requerirlo
	 */
}