package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyEvaluacionPeaIndicador;
import com.vortexbird.pusay.modelo.PsyIndicador;

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
 * PsyEvaluacionPeaIndicador entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyEvaluacionPeaIndicador
 */
@Scope("singleton")
@Repository("PsyEvaluacionPeaIndicadorDAO")
public class PsyEvaluacionPeaIndicadorDAO extends HibernateDaoImpl<PsyEvaluacionPeaIndicador, Long>
    implements IPsyEvaluacionPeaIndicadorDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyEvaluacionPeaIndicadorDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyEvaluacionPeaIndicadorDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IPsyEvaluacionPeaIndicadorDAO) ctx.getBean(
            "PsyEvaluacionPeaIndicadorDAO");
    }    

	
//	String hql2 = "SELECT evapea.indiCodigo, evapea.multiple, evapea.periodo, evapea.resultado, evapea.meta, evapea.norma, evapea.sectorial, evapea.estadoRegistro,"
//			+ " codigo, obamCodigo, subteCodigo, descripcion, unidadMedida, tipoIndicador, estadoRegistro, descripcion, descripcion "
//			+ "FROM PsyImpactoAmbiental imam, PsyMatrizCorrelacion m, PsyDetalleMapaEstrategico me,"
	
    //@Override
	
	public List<Object> getEvaluacionPeaByEmpresaByImam(PsyEmpresa empresa, Long imamCodigo) throws Exception {

		
		String hql2 = "SELECT indi, evapea FROM PsyImpactoAmbiental imam, PsyMatrizCorrelacion m, PsyDetalleMapaEstrategico me,"
				+ " PsyEmpresa empr, PsyPlanEstrategico pest, PsyMapaEstrategico maes, PsyIndicador indi, PsyTema tema, PsySubtema subtema, PsyEvaluacionPeaIndicador evapea "
				+ "WHERE imam.imamCodigo=tema.psyImpactoAmbiental.imamcodigo and tema.codigo=subtema.psyTema.codigo and subtema.codigo=indi.psySubtema.codigo"
				+ " and imam.imamCodigo="+imamCodigo+"and me.psyMatrizCorrelacion.macoCodigo=m.macoCodigo and m.psyImpactoAmbiental.imamCodigo=imam.imamCodigo "
				+ "and me.psyMapaEstrategico.maesCodigo=maes.maesCodigo and maes.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
				+ " and pest.estadoPlan!='A' and pest.estadoPlan!='C' and pest.psyEmpresa.emprCodigo=empr.emprCodigo"
				+ " and empr.emprCodigo=" + empresa.getEmprCodigo();
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<Object> lasEstrategias = query.list();
		return lasEstrategias;

	}
	
	@Override
	public List<PsyEvaluacionPeaIndicador> getDataPsyEvaluacionPeaIndicadorByEmpresa(PsyEmpresa empresa) throws Exception {

		
		String hql2 = "SELECT evapea FROM PsyEmpresa empr, PsyPlanEstrategico pest, PsyEvaluacionPeaIndicador evapea, PsyPlanEstrategicoAmbiental pea "
				+ "WHERE empr.emprCodigo=pest.psyEmpresa.emprCodigo and pest.estadoPlan!='A' and pest.estadoPlan!='C' "
				+ "and empr.emprCodigo=" + empresa.getEmprCodigo() + " and pest.pestCodigo=pea.psyPlanEstrategico.pestCodigo and"
				+ " pea.codigo=evapea.psyPlanEstrategicoAmbiental.codigo";
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<PsyEvaluacionPeaIndicador> lasEvaluaciones = query.list();
		return lasEvaluaciones;

	}
}
