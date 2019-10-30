package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyDetalleMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyEstrategiaAmbiental;
import com.vortexbird.pusay.modelo.PsyImpactoAmbiental;
import com.vortexbird.pusay.modelo.PsyMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyMatrizCorrelacion;
import com.vortexbird.pusay.modelo.PsyObjetivoEstrategico;
import com.vortexbird.pusay.modelo.PsyObjetivoImpacto;
import com.vortexbird.pusay.modelo.dto.PsyDetalleMatrizCorrelacionDTO;

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
 * PsyDetalleMapaEstrategico entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyDetalleMapaEstrategico
 */
@Scope("singleton")
@Repository("PsyDetalleMapaEstrategicoDAO")
public class PsyDetalleMapaEstrategicoDAO extends HibernateDaoImpl<PsyDetalleMapaEstrategico, Long>
    implements IPsyDetalleMapaEstrategicoDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyDetalleMapaEstrategicoDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyDetalleMapaEstrategicoDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IPsyDetalleMapaEstrategicoDAO) ctx.getBean(
            "PsyDetalleMapaEstrategicoDAO");
    }
    
    @Override
	public List<PsyMatrizCorrelacion> consultarMatrizRelacionMapaEstrategico(Long obesCodigo, Long imamCodigo) throws Exception {

		String hql2 = "SELECT maco FROM PsyMatrizCorrelacion maco WHERE maco.psyImpactoAmbiental.imamCodigo="+imamCodigo
				+ " and maco.psyObjetivoEstrategico.obesCodigo="+obesCodigo;
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<PsyMatrizCorrelacion> lasRelaciones = query.list();
		return lasRelaciones;

	}
    
    @Override
	public List<PsyObjetivoImpacto> consultarObjetivoImpactoPorPlanEstrategico(Long pestCodigo, Long obesCodigo) throws Exception {

		String hql2 = "SELECT obim "
				+"FROM PsyObjetivoImpacto obim "
				+"WHERE obim.psyObjetivoEstrategico.obesCodigo IN (SELECT obes.obesCodigo "
				+"FROM PsyDetalleObjetivoPlan dobpl, PsyObjetivoPlan obpl, PsyObjetivoEstrategico obes " 
				+"WHERE obes.obesCodigo=dobpl.psyObjetivoEstrategico.obesCodigo and obpl.psyPlanEstrategico.pestCodigo="+pestCodigo
				+ " and dobpl.psyObjetivoEstrategico.obesCodigo="+obesCodigo+")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<PsyObjetivoImpacto> lasRelaciones = query.list();
		return lasRelaciones;

	}
    
    @Override
	public List<PsyDetalleMapaEstrategico> consultaMatrizCorrelacionPorImpacto(PsyImpactoAmbiental impactoAmbiental,PsyMapaEstrategico mapaEstrategico) throws Exception {
		String hql2 = "Select dmae "
				+ "FROM PsyDetalleMapaEstrategico dmae, PsyMatrizCorrelacion maco "
				+ "WHERE dmae.psyMatrizCorrelacion.macoCodigo=maco.macoCodigo and"
				+ " dmae.psyMapaEstrategico.maesCodigo="+mapaEstrategico.getMaesCodigo()+" and maco.psyImpactoAmbiental.imamCodigo="+impactoAmbiental.getImamCodigo();
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<PsyDetalleMapaEstrategico> losDetalles = query.list();
		return losDetalles;
    }
    
    @Override
	public List<PsyMatrizCorrelacion> consultaEstrategiasSeleccionadas(PsyDetalleMatrizCorrelacionDTO relacionSeleccionada, PsyMapaEstrategico mapaEstrategico) throws Exception {
		String hql2 = "Select maco "
				+ "FROM PsyDetalleMapaEstrategico dmae, PsyMatrizCorrelacion maco "
				+ "WHERE dmae.psyMatrizCorrelacion.macoCodigo=maco.macoCodigo and"
				+ " dmae.psyMapaEstrategico.maesCodigo="+mapaEstrategico.getMaesCodigo()+" and maco.psyImpactoAmbiental.imamCodigo="+relacionSeleccionada.getInamCodigo()
				+ " and maco.psyObjetivoEstrategico.obesCodigo="+relacionSeleccionada.getObesCodigo();
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<PsyMatrizCorrelacion> laMatriz = query.list();
		return laMatriz;
    }
    
    @Override
   	public List<PsyMatrizCorrelacion> consultaEstrategiasSeleccionadas(PsyImpactoAmbiental impactoAmbiental, PsyObjetivoEstrategico obesEstrategico, PsyMapaEstrategico mapaEstrategico) throws Exception {
   		String hql2 = "Select maco "
   				+ "FROM PsyDetalleMapaEstrategico dmae, PsyMatrizCorrelacion maco "
   				+ "WHERE dmae.psyMatrizCorrelacion.macoCodigo=maco.macoCodigo and"
   				+ " dmae.psyMapaEstrategico.maesCodigo="+mapaEstrategico.getMaesCodigo()+" and maco.psyImpactoAmbiental.imamCodigo="+impactoAmbiental.getImamCodigo()
   				+ " and maco.psyObjetivoEstrategico.obesCodigo="+obesEstrategico.getObesCodigo();
   		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
   		List<PsyMatrizCorrelacion> laMatriz = query.list();
   		return laMatriz;
       }
    
    
    
    
}
