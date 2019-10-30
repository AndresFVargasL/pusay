package com.vortexbird.pusay.cuestionarios.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.pusay.cuestionarios.dao.ICueResponsableDAO;
import com.vortexbird.pusay.cuestionarios.dao.support.HibernateDaoImpl;
import com.vortexbird.pusay.cuestionarios.model.CueResponsable;

/**
 * A data access object (DAO) providing persistence and search support for CueResponsable entities. Transaction control
 * of the save(), update() and delete() operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these methods provides additional information
 * for how to configure it for the desired type of transaction control. support Andr�s Mauricio C�rdenas
 * mauriciocardenasp@gmail.com
 * 
 * @see lidis.CueResponsable
 */
@Scope("singleton")
@Repository("CueResponsableDAO")
public class CueResponsableDAO extends HibernateDaoImpl<CueResponsable, Long> implements ICueResponsableDAO {

	/**
	 * Implementaci�n detallada del DAO en caso de requerirlo
	 */
}