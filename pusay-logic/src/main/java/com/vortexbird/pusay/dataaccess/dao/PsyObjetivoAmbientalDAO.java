package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyImpactoAmbiental;
import com.vortexbird.pusay.modelo.PsyObjetivoAmbiental;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;


/**
 * A data access object (DAO) providing persistence and search support for
 * PsyObjetivoAmbiental entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyObjetivoAmbiental
 */
@Scope("singleton")
@Repository("PsyObjetivoAmbientalDAO")
public class PsyObjetivoAmbientalDAO extends HibernateDaoImpl<PsyObjetivoAmbiental, Long>
    implements IPsyObjetivoAmbientalDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyObjetivoAmbientalDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyObjetivoAmbientalDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IPsyObjetivoAmbientalDAO) ctx.getBean("PsyObjetivoAmbientalDAO");
    }
    
    @Override
	public List<PsyObjetivoAmbiental> consultaObjetivosAmbientalesSeleccionados(PsyEmpresa empresa) throws Exception {

		String hql2 = "SELECT DISTINCT(obam) FROM PsyObjetivoAmbiental obam, PsyEvaluacionPeaObjetivo obameva, PsyPlanEstrategicoAmbiental pea,"
				+ " PsyEmpresa empr, PsyPlanEstrategico pest " 
				+ "WHERE pea.psyPlanEstrategico.pestCodigo=pest.pestCodigo and pest.estadoPlan!='A' and pest.estadoPlan!='C' and pest.psyEmpresa.emprCodigo=empr.emprCodigo"
				+ " and empr.emprCodigo=" + empresa.getEmprCodigo()+ " and pea.codigo=obameva.psyPlanEstrategicoAmbiental.codigo and obameva.psyObjetivoAmbiental.codigo=obam.codigo";
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<PsyObjetivoAmbiental> losObjetivos = query.list();
		return losObjetivos;

	}
}
