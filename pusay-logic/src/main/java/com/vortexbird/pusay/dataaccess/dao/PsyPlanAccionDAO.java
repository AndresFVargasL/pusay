package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyAsuntoAmbiental;
import com.vortexbird.pusay.modelo.PsyDetalleMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyPlanAccion;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.transform.Transformers;
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
 * PsyPlanAccion entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyPlanAccion
 */
@Scope("singleton")
@Repository("PsyPlanAccionDAO")
public class PsyPlanAccionDAO extends HibernateDaoImpl<PsyPlanAccion, Long>
    implements IPsyPlanAccionDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyPlanAccionDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyPlanAccionDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IPsyPlanAccionDAO) ctx.getBean("PsyPlanAccionDAO");
    }
    
    @Override
    public List<PsyMapaEstrategico> verificarMapaEstrategico(PsyEmpresa empresa){
    	
        
        	
        	String hql2 = "SELECT me FROM PsyPlanEstrategico p, PsyEmpresa empr, PsyMapaEstrategico me WHERE "
        			+ "empr.emprCodigo=p.psyEmpresa.emprCodigo AND p.pestCodigo=me.psyPlanEstrategico.pestCodigo AND p.estadoPlan!='C' AND p.psyEmpresa.emprCodigo="+empresa.getEmprCodigo();
        	Query query = sessionFactory.getCurrentSession().createQuery(hql2);
        	List<PsyMapaEstrategico> elmapa = query.list();
        	
        
    	return elmapa;
    }
    
    @Override
    public List<PsyPlanEstrategico> verificarPlanEstrategico(PsyEmpresa empresa){
    	
        
        	
        	String hql2 = "SELECT p FROM PsyPlanEstrategico p, PsyEmpresa empr WHERE "
        			+ "empr.emprCodigo=p.psyEmpresa.emprCodigo AND p.estadoPlan!='C' AND p.estadoPlan!='A' AND p.psyEmpresa.emprCodigo="+empresa.getEmprCodigo();
        	Query query = sessionFactory.getCurrentSession().createQuery(hql2);
        	List<PsyPlanEstrategico> elplan = query.list();
        	
        
    	return elplan;
    }
    
    @Override
	public String consultaPlanEstrategicoBtnNew(PsyEmpresa empresa) throws Exception {

		String hql2 = "SELECT DISTINCT(pest.nombre) FROM PsyDetalleMapaEstrategico me,"
				+ " PsyEmpresa empr, PsyPlanEstrategico pest, PsyMapaEstrategico maes "
				+ "WHERE me.psyMapaEstrategico.maesCodigo=maes.maesCodigo and maes.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
				+ " and pest.estadoPlan!='A' and pest.estadoPlan!='C' and pest.psyEmpresa.emprCodigo=empr.emprCodigo"
				+ " and empr.emprCodigo="+ empresa.getEmprCodigo();
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<Object> losPlanes = query.list();
		String planes = new String();
		for (Object planesTmp : losPlanes) {
			planes = planesTmp.toString();
		}
		return planes;

	}
    
    @Override
	public String consultaPlanEstrategico(PsyEmpresa empresa, Long placCodigo) throws Exception {

		String hql2 = "SELECT DISTINCT(pest.nombre) FROM PsyDetalleMapaEstrategico me,"
				+ " PsyEmpresa empr, PsyPlanEstrategico pest, PsyMapaEstrategico maes, PsyPlanEstrategia ples, PsyPlanAccion plac, PsyPrograma prog "
				+ "WHERE me.psyMapaEstrategico.maesCodigo=maes.maesCodigo and maes.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
				+ " and pest.psyEmpresa.emprCodigo=empr.emprCodigo"
				+ " and empr.emprCodigo="+ empresa.getEmprCodigo()+" and me.dmaeCodigo=ples.psyDetalleMapaEstrategico.dmaeCodigo"
						+ " and ples.psyPrograma.progCodigo=prog.progCodigo and prog.progCodigo=plac.psyPrograma.progCodigo and plac.placCodigo="+placCodigo;
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<Object> losPlanes = query.list();
		String planes = new String();
		for (Object planesTmp : losPlanes) {
			planes = planesTmp.toString();
		}
		return planes;

	}
    
    @Override
	public List<PsyPlanAccion> findPlanesAccionByEmpresa(PsyEmpresa empresa) throws Exception {

		String hql2 = "SELECT DISTINCT(plac) FROM PsyDetalleMapaEstrategico me, PsyEmpresa empr, PsyPlanEstrategico pest, PsyMapaEstrategico maes,"
				+ " PsyPlanAccion plac, PsyPlanEstrategia ples, PsyPrograma prog "
				+ "WHERE plac.psyPrograma.progCodigo=prog.progCodigo and prog.progCodigo=ples.psyPrograma.progCodigo "
				+ "and ples.psyDetalleMapaEstrategico.dmaeCodigo=me.dmaeCodigo and "
				+ "me.psyMapaEstrategico.maesCodigo=maes.maesCodigo and maes.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
				+ " and pest.psyEmpresa.emprCodigo=empr.emprCodigo and empr.emprCodigo=" + empresa.getEmprCodigo();
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<PsyPlanAccion> losPlanes = query.list();
		return losPlanes;
	}
    
    @Override
	public List<PsyPlanAccion> findPlanesAccionByEmpresaByPestActivo(PsyEmpresa empresa) throws Exception {

		String hql2 = "SELECT DISTINCT(plac) FROM PsyDetalleMapaEstrategico me, PsyEmpresa empr, PsyPlanEstrategico pest, PsyMapaEstrategico maes,"
				+ " PsyPlanAccion plac, PsyPlanEstrategia ples, PsyPrograma prog "
				+ "WHERE plac.psyPrograma.progCodigo=prog.progCodigo and prog.progCodigo=ples.psyPrograma.progCodigo and ples.psyDetalleMapaEstrategico.dmaeCodigo=me.dmaeCodigo and "
				+ "me.psyMapaEstrategico.maesCodigo=maes.maesCodigo and maes.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
				+ " and pest.estadoPlan!='A' and pest.estadoPlan!='C' and pest.psyEmpresa.emprCodigo=empr.emprCodigo and empr.emprCodigo=" + empresa.getEmprCodigo();
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<PsyPlanAccion> losPlanes = query.list();
		return losPlanes;
	}
    
    @Override
	public List<PsyPlanAccion> findPlanesAccionByEmpresaByPestActivoByEstadoPlan(PsyEmpresa empresa) throws Exception {

		String hql2 = "SELECT DISTINCT(plac) FROM PsyDetalleMapaEstrategico me, PsyEmpresa empr, PsyPlanEstrategico pest, PsyMapaEstrategico maes,"
				+ " PsyPlanAccion plac, PsyPlanEstrategia ples, PsyPrograma prog "
				+ "WHERE plac.psyPrograma.progCodigo=prog.progCodigo and prog.progCodigo=ples.psyPrograma.progCodigo and ples.psyDetalleMapaEstrategico.dmaeCodigo=me.dmaeCodigo and "
				+ "me.psyMapaEstrategico.maesCodigo=maes.maesCodigo and maes.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
				+ " and pest.estadoPlan!='A' and pest.estadoPlan!='C' and plac.estadoPlanAccion!='C' and pest.psyEmpresa.emprCodigo=empr.emprCodigo and empr.emprCodigo=" + empresa.getEmprCodigo();
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<PsyPlanAccion> losPlanes = query.list();
		return losPlanes;
	}
    
    @Override
    public List<PsyPlanAccion> consultarPlanesAccion(PsyEmpresa empresa, PsyPlanEstrategico planEstrategico , String estadoIniciado, String estadoPresupuestado)
            throws Exception{
    	
    	List<PsyPlanAccion> listDto = null;
   	  	
 	  	  try {
 	  	   Query query = getSession().getNamedQuery(
 	  	      "consulta_planes_accion").setLong("pPestCodigo", planEstrategico.getPestCodigo()).setString("pEstadoIniciado", estadoIniciado).setString("pEstadoPresupuestado", estadoPresupuestado);
 	  	  
 	  	  //  query.setResultTransformer(Transformers.aliasToBean(PsyPlanAccion.class));
 	  	    listDto = query.list();
 	  	   
 	  	  } catch (Exception e) {
 	  	   log.error(e.getMessage());
 	  	   throw e;
 	  	  }

 	  	  return listDto;
    	
    }
    	

    
}
