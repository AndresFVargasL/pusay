package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyMatrizEncuesta;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;

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
 * PsyPlanEstrategico entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyPlanEstrategico
 */
@Scope("singleton")
@Repository("PsyPlanEstrategicoDAO")
public class PsyPlanEstrategicoDAO extends HibernateDaoImpl<PsyPlanEstrategico, Long>
    implements IPsyPlanEstrategicoDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyPlanEstrategicoDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyPlanEstrategicoDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IPsyPlanEstrategicoDAO) ctx.getBean("PsyPlanEstrategicoDAO");
        
        
    }
    
    @Override
	public List<PsyMatrizEncuesta> consultaAsociacionDeCuestionarios(PsyEmpresa empresa) throws Exception {

		String hql2 = "SELECT maen FROM PsyMatrizEncuesta maen, PsyPlanEstrategico pest, PsyEmpresa empr "
				+ "WHERE empr.emprCodigo="+empresa.getEmprCodigo()+" and empr.emprCodigo=pest.psyEmpresa.emprCodigo and "
						+ "pest.pestCodigo=maen.psyPlanEstrategico.pestCodigo and pest.estadoPlan!='A' and pest.estadoPlan!='C'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<PsyMatrizEncuesta> lasEncuestas = query.list();
		return lasEncuestas;

	}
}
