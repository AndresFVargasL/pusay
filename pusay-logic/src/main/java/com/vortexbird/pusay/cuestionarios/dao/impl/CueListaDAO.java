package com.vortexbird.pusay.cuestionarios.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.pusay.cuestionarios.dao.ICueListaDAO;
import com.vortexbird.pusay.cuestionarios.dao.support.HibernateDaoImpl;
import com.vortexbird.pusay.cuestionarios.model.CueLista;

@Scope("singleton")
@Repository("CueListaDAO")
public class CueListaDAO extends HibernateDaoImpl<CueLista, Long> implements ICueListaDAO {

	/**
	 * Implementaci�n detallada del DAO en caso de requerirlo
	 */
}