package com.vortexbird.pusay.cuestionarios.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.pusay.cuestionarios.dao.ICueContactoDAO;
import com.vortexbird.pusay.cuestionarios.dao.support.HibernateDaoImpl;
import com.vortexbird.pusay.cuestionarios.model.CueContacto;

@Scope("singleton")
@Repository("CueContactoDAO")
public class CueContactoDAO extends HibernateDaoImpl<CueContacto, Long> implements ICueContactoDAO {

	/**
	 * Implementaci�n detallada del DAO en caso de requerirlo
	 */
}