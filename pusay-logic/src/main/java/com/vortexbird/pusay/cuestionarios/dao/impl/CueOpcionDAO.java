package com.vortexbird.pusay.cuestionarios.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.pusay.cuestionarios.dao.ICueOpcionDAO;
import com.vortexbird.pusay.cuestionarios.dao.support.HibernateDaoImpl;
import com.vortexbird.pusay.cuestionarios.model.CueOpcion;

@Scope("singleton")
@Repository("CueOpcionDAO")
public class CueOpcionDAO extends HibernateDaoImpl<CueOpcion, Long> implements ICueOpcionDAO {
	/**
	 * Implementaci�n detallada del DAO en caso de requerirlo
	 */

	@Override
	public int countRequiereAmpliacionByPregunta(Long preguntaCodigo) {
		Criteria criteria = getSession().createCriteria(CueOpcion.class);
		criteria.add(Restrictions.eq("cuePregunta.consecutivo", preguntaCodigo));
		criteria.add(Restrictions.eq("requiereAmpliacion", 1L));
		List<CueOpcion> list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.size();
		} else {
			return 0;
		}
	}
}