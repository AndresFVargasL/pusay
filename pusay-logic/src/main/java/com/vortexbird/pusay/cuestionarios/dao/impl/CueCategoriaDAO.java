package com.vortexbird.pusay.cuestionarios.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.pusay.cuestionarios.dao.ICueCategoriaDAO;
import com.vortexbird.pusay.cuestionarios.dao.support.HibernateDaoImpl;
import com.vortexbird.pusay.cuestionarios.model.CueCategoria;

@Scope("singleton")
@Repository("CueCategoriaDAO")
public class CueCategoriaDAO extends HibernateDaoImpl<CueCategoria, Long> implements ICueCategoriaDAO {

	/**
	 * Implementaci�n detallada del DAO en caso de requerirlo
	 */
}
