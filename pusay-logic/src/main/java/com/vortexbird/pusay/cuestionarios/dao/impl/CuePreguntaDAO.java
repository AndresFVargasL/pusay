package com.vortexbird.pusay.cuestionarios.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.pusay.cuestionarios.dao.ICuePreguntaDAO;
import com.vortexbird.pusay.cuestionarios.dao.support.HibernateDaoImpl;
import com.vortexbird.pusay.cuestionarios.model.CuePregunta;

@Scope("singleton")
@Repository("CuePreguntaDAO")
public class CuePreguntaDAO extends HibernateDaoImpl<CuePregunta, Long> implements ICuePreguntaDAO {

	/**
	 * Implementaci�n detallada del DAO en caso de requerirlo
	 */
}