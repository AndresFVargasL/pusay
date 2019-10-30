package com.vortexbird.pusay.cuestionarios.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.pusay.cuestionarios.dao.ICueCuestionarioTipoDAO;
import com.vortexbird.pusay.cuestionarios.dao.support.HibernateDaoImpl;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionarioTipo;

@Scope("singleton")
@Repository("CueCuestionarioTipoDAO")
public class CueCuestionarioTipoDAO extends HibernateDaoImpl<CueCuestionarioTipo, Long> implements
		ICueCuestionarioTipoDAO {

	/**
	 * Implementaci�n detallada del DAO en caso de requerirlo
	 */
}