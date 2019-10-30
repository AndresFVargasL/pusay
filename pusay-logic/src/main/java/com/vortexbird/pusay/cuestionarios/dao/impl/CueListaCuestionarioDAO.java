package com.vortexbird.pusay.cuestionarios.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.pusay.cuestionarios.dao.ICueListaCuestionarioDAO;
import com.vortexbird.pusay.cuestionarios.dao.support.HibernateDaoImpl;
import com.vortexbird.pusay.cuestionarios.model.CueListaCuestionario;

@Scope("singleton")
@Repository("CueListaCuestionarioDAO")
public class CueListaCuestionarioDAO extends HibernateDaoImpl<CueListaCuestionario, Long> implements
		ICueListaCuestionarioDAO {

	/**
	 * Implementaci�n detallada del DAO en caso de requerirlo
	 */
}