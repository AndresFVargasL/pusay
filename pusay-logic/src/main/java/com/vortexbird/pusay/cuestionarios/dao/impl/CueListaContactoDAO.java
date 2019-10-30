package com.vortexbird.pusay.cuestionarios.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.pusay.cuestionarios.dao.ICueListaContactoDAO;
import com.vortexbird.pusay.cuestionarios.dao.support.HibernateDaoImpl;
import com.vortexbird.pusay.cuestionarios.model.CueListaContacto;

@Scope("singleton")
@Repository("CueListaContactoDAO")
public class CueListaContactoDAO extends HibernateDaoImpl<CueListaContacto, Long> implements ICueListaContactoDAO {

	/**
	 * Implementaci�n detallada del DAO en caso de requerirlo
	 */
}