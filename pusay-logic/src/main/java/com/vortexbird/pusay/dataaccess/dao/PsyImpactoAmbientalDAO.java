package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyImpactoAmbiental;

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
 * PsyImpactoAmbiental entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyImpactoAmbiental
 */
@Scope("singleton")
@Repository("PsyImpactoAmbientalDAO")
public class PsyImpactoAmbientalDAO extends HibernateDaoImpl<PsyImpactoAmbiental, Long>
    implements IPsyImpactoAmbientalDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyImpactoAmbientalDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyImpactoAmbientalDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IPsyImpactoAmbientalDAO) ctx.getBean("PsyImpactoAmbientalDAO");
    }
    
    @Override
	public List<PsyImpactoAmbiental> consultaImpactosAmbientalesSeleccionados(PsyEmpresa empresa) throws Exception {

		String hql2 = "SELECT DISTINCT imam FROM PsyImpactoAmbiental imam, PsyMatrizCorrelacion m, PsyDetalleMapaEstrategico me,"
				+ " PsyEmpresa empr, PsyPlanEstrategico pest, PsyMapaEstrategico maes "
				+ "WHERE me.psyMatrizCorrelacion.macoCodigo=m.macoCodigo and m.psyImpactoAmbiental.imamCodigo=imam.imamCodigo "
				+ "and me.psyMapaEstrategico.maesCodigo=maes.maesCodigo and maes.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
				+ " and pest.estadoPlan!='A' and pest.estadoPlan!='C' and pest.psyEmpresa.emprCodigo=empr.emprCodigo"
				+ " and empr.emprCodigo=" + empresa.getEmprCodigo();
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<PsyImpactoAmbiental> losImpactos = query.list();
		return losImpactos;

	}
    
    @Override
	public Long countTemasPorImpacto(Long codigo) throws Exception {

		String hql2 = "SELECT count(tema) FROM PsyImpactoAmbiental imam, PsyTema tema "
				+ "WHERE imam.imamCodigo=tema.psyImpactoAmbiental.imamCodigo and imam.imamCodigo="+codigo;
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		Long cantidad = (Long)query.uniqueResult();
		return cantidad;
	}
	
	@Override
	public Long countSubTemasPorImpacto(Long codigo) throws Exception {

			String hql2 = "SELECT count(subte) FROM PsyImpactoAmbiental imam, PsyTema tema, PsySubtema subte "
					+ "WHERE imam.imamCodigo=tema.psyImpactoAmbiental.imamCodigo and tema.codigo=subte.psyTema.codigo and"
					+ " imam.imamCodigo="+codigo;
			Query query = sessionFactory.getCurrentSession().createQuery(hql2);
			Long cantidad = (Long)query.uniqueResult();
			return cantidad;
	}
		
	@Override	
	public Long countIndicadoresPorImpacto(Long codigo) throws Exception {

			String hql2 = "SELECT count(indi) FROM PsyImpactoAmbiental imam, PsyTema tema, PsySubtema subte, PsyIndicador indi "
					+ "WHERE imam.imamCodigo=tema.psyImpactoAmbiental.imamCodigo and tema.codigo=subte.psyTema.codigo and"
					+ " subte.codigo=indi.psySubtema.codigo and imam.imamCodigo="+codigo;
			Query query = sessionFactory.getCurrentSession().createQuery(hql2);
			Long cantidad = (Long)query.uniqueResult();
			return cantidad;
	}
}
