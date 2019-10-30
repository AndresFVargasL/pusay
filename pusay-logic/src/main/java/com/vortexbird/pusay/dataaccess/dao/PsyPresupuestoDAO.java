package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPlanAccion;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;
import com.vortexbird.pusay.modelo.PsyPresupuesto;

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
 * PsyPresupuesto entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyPresupuesto
 */
@Scope("singleton")
@Repository("PsyPresupuestoDAO")
public class PsyPresupuestoDAO extends HibernateDaoImpl<PsyPresupuesto, Long>
    implements IPsyPresupuestoDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyPresupuestoDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyPresupuestoDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IPsyPresupuestoDAO) ctx.getBean("PsyPresupuestoDAO");
    }
    
    @Override
	public List<PsyPresupuesto> findPresupuestoByEmpresaByPestActivo(PsyEmpresa empresa) throws Exception {

		String hql2 = "SELECT DISTINCT(pres) FROM PsyDetalleMapaEstrategico me, PsyEmpresa empr, PsyPlanEstrategico pest, PsyMapaEstrategico maes,"
				+ " PsyPlanAccion plac, PsyPlanEstrategia ples, PsyPresupuesto pres, PsyPrograma prog "
				+ "WHERE plac.psyPrograma.progCodigo=prog.progCodigo and prog.progCodigo=ples.psyPrograma.progCodigo and ples.psyDetalleMapaEstrategico.dmaeCodigo=me.dmaeCodigo and "
				+ "me.psyMapaEstrategico.maesCodigo=maes.maesCodigo and maes.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
				+ " and pest.estadoPlan!='A' and pest.estadoPlan!='C' and pest.psyEmpresa.emprCodigo=empr.emprCodigo and empr.emprCodigo="+empresa.getEmprCodigo()
				+ " and plac.placCodigo=pres.psyPlanAccion.placCodigo";
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<PsyPresupuesto> losPresupuestos = query.list();
		return losPresupuestos;
	}
    
    @Override
	public List<PsyPresupuesto> findPresupuestoByEmpresaByPestActivoByPresActivo(PsyEmpresa empresa) throws Exception {

		String hql2 = "SELECT DISTINCT(pres) FROM PsyDetalleMapaEstrategico me, PsyEmpresa empr, PsyPlanEstrategico pest, PsyMapaEstrategico maes,"
				+ " PsyPlanAccion plac, PsyPlanEstrategia ples, PsyPresupuesto pres, PsyPrograma prog "
				+ "WHERE plac.psyPrograma.progCodigo=prog.progCodigo and prog.progCodigo=ples.psyPrograma.progCodigo and ples.psyDetalleMapaEstrategico.dmaeCodigo=me.dmaeCodigo and "
				+ "me.psyMapaEstrategico.maesCodigo=maes.maesCodigo and maes.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
				+ " and pest.estadoPlan!='A' and pest.estadoPlan!='C' and pest.psyEmpresa.emprCodigo=empr.emprCodigo and empr.emprCodigo="+empresa.getEmprCodigo()
				+ " and plac.placCodigo=pres.psyPlanAccion.placCodigo and pres.estadoPresupuesto!='C'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<PsyPresupuesto> losPresupuestos = query.list();
		return losPresupuestos;
	}
    
    @Override
    public List<PsyPresupuesto> consultarPresupuestos(PsyEmpresa empresa) throws Exception{
    	
    	List<PsyPresupuesto> listDto = null;
   	  	
 	  	  try {
 	  		Query query = getSession().getNamedQuery(
 	  	      "consulta_validacion_presupuesto").setLong("pCodigoEmpresa", empresa.getEmprCodigo());
 	  	  
 	  	   	//query.setResultTransformer(Transformers.aliasToBean(PsyPlanAccion.class));
 	  	    listDto = query.list();
 	  	   
 	  	  } catch (Exception e) {
 	  	   log.error(e.getMessage());
 	  	   throw e;
 	  	  }

 	  	  return listDto;
    	
    }
}
