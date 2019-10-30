package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyDetalleMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;
import com.vortexbird.pusay.modelo.PsyPlanEstrategicoAmbiental;

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
 * PsyPlanEstrategicoAmbiental entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyPlanEstrategicoAmbiental
 */
@Scope("singleton")
@Repository("PsyPlanEstrategicoAmbientalDAO")
public class PsyPlanEstrategicoAmbientalDAO extends HibernateDaoImpl<PsyPlanEstrategicoAmbiental, Long>
    implements IPsyPlanEstrategicoAmbientalDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyPlanEstrategicoAmbientalDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyPlanEstrategicoAmbientalDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IPsyPlanEstrategicoAmbientalDAO) ctx.getBean(
            "PsyPlanEstrategicoAmbientalDAO");
    }
    
    @Override
    public PsyPlanEstrategico getPlanEstrategicoActivoByPEA(PsyEmpresa empresa) throws Exception {
    	
    	String hql = "SELECT pest FROM PsyPlanEstrategico pest, PsyEmpresa empr "
				+ "WHERE pest.estadoPlan!='A' and pest.estadoPlan!='C' "
				+ "and pest.psyEmpresa.emprCodigo=empr.emprCodigo and "
				+ "empr.emprCodigo="+empresa.getEmprCodigo();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		PsyPlanEstrategico planEstrategico = (PsyPlanEstrategico) query.uniqueResult();
    	return planEstrategico;
    }
    
    @Override
    public PsyPlanEstrategico getPlanEstrategicoByPEA(PsyEmpresa empresa, Long codigo) throws Exception {
    	
    	String hql = "SELECT pest FROM PsyPlanEstrategico pest, PsyEmpresa empr, PsyPlanEstrategicoAmbiental pea "
				+ "WHERE pea.psyPlanEstrategico.pestCodigo=pest.pestCodigo and pest.pestCodigo="+codigo
				+ " and pest.psyEmpresa.emprCodigo=empr.emprCodigo and "
				+ "empr.emprCodigo="+empresa.getEmprCodigo();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		PsyPlanEstrategico planEstrategico = (PsyPlanEstrategico) query.uniqueResult();
    	return planEstrategico;
    }
    
    @Override
    public PsyPlanEstrategico getPlanEstrategicoByPEA(PsyEmpresa empresa, String nombre) throws Exception {
    	
    	String hql = "SELECT pest FROM PsyPlanEstrategico pest, PsyEmpresa empr, PsyPlanEstrategicoAmbiental pea "
				+ "WHERE pea.psyPlanEstrategico.pestCodigo=pest.pestCodigo and pest.nombre=\'"+nombre+"\'"
				+ " and pest.psyEmpresa.emprCodigo=empr.emprCodigo and "
				+ "empr.emprCodigo="+empresa.getEmprCodigo();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		PsyPlanEstrategico planEstrategico = (PsyPlanEstrategico) query.uniqueResult();
    	return planEstrategico;
    }
    
    @Override
    public List<PsyPlanEstrategicoAmbiental> getPEA(PsyEmpresa empresa) throws Exception {
    	
    	String hql = "SELECT pea FROM PsyPlanEstrategico pest, PsyEmpresa empr, PsyPlanEstrategicoAmbiental pea "
				+ "WHERE pea.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
				+ " and pest.psyEmpresa.emprCodigo=empr.emprCodigo and pea.estadoPlan!='C' and "
				+ "empr.emprCodigo="+empresa.getEmprCodigo();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<PsyPlanEstrategicoAmbiental> planEstrategicoAmbiental = query.list();
    	return planEstrategicoAmbiental;
    }
}
