package com.vortexbird.pusay.cuestionarios.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.pusay.cuestionarios.dao.ICueCuestionarioDAO;
import com.vortexbird.pusay.cuestionarios.dao.support.HibernateDaoImpl;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionario;

@Scope("singleton")
@Repository("CueCuestionarioDAO")
public class CueCuestionarioDAO extends HibernateDaoImpl<CueCuestionario, Long> implements ICueCuestionarioDAO {

	/**
	 * Implementaci�n detallada del DAO en caso de requerirlo
	 */
}