package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyDetalleMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPlanEstrategia;

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
 * PsyPlanEstrategia entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyPlanEstrategia
 */
@Scope("singleton")
@Repository("PsyPlanEstrategiaDAO")
public class PsyPlanEstrategiaDAO extends
		HibernateDaoImpl<PsyPlanEstrategia, Long> implements
		IPsyPlanEstrategiaDAO {
	private static final Logger log = LoggerFactory
			.getLogger(PsyPlanEstrategiaDAO.class);
	@Resource
	private SessionFactory sessionFactory;

	public static IPsyPlanEstrategiaDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IPsyPlanEstrategiaDAO) ctx.getBean("PsyPlanEstrategiaDAO");
	}

	// @Override
	// public List<Object> consultaEstrategias(PsyEmpresa empresa) throws
	// Exception{
	//
	// String hql2 =
	// "SELECT e.nombre FROM PsyEstrategiaAmbiental e, PsyMatrizCorrelacion m, PsyDetalleMapaEstrategico me,"
	// + " PsyEmpresa empr, PsyPlanEstrategico pest, PsyMapaEstrategico maes "
	// +
	// "WHERE me.psyMatrizCorrelacion.macoCodigo=m.macoCodigo and m.psyEstrategiaAmbiental.esamCodigo=e.esamCodigo "
	// +
	// "and me.psyMapaEstrategico.maesCodigo=maes.maesCodigo and maes.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
	// +
	// " and pest.estadoPlan!='A' and pest.estadoPlan!='C' and pest.psyEmpresa.emprCodigo=empr.emprCodigo"
	// + " and empr.emprCodigo="+empresa.getEmprCodigo();
	// Query query = sessionFactory.getCurrentSession().createQuery(hql2);
	// List<Object> lasEstrategias = query.list();
	// return lasEstrategias;
	//
	// }

	// @Override
	// public List<Object> consultaEstrategiasSeleccionadas(PsyEmpresa empresa,
	// Long placCodigo) throws Exception{
	//
	// String hql2 =
	// "SELECT e.nombre FROM PsyEstrategiaAmbiental e, PsyMatrizCorrelacion m, PsyDetalleMapaEstrategico me,"
	// +
	// " PsyEmpresa empr, PsyPlanEstrategico pest, PsyMapaEstrategico maes, PsyPlanEstrategia ples, PsyPlanAccion plac"
	// +
	// " WHERE me.psyMatrizCorrelacion.macoCodigo=m.macoCodigo and m.psyEstrategiaAmbiental.esamCodigo=e.esamCodigo "
	// +
	// "and me.psyMapaEstrategico.maesCodigo=maes.maesCodigo and maes.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
	// +
	// " and pest.estadoPlan!='A' and pest.estadoPlan!='C' and pest.psyEmpresa.emprCodigo=empr.emprCodigo"
	// +
	// " and empr.emprCodigo="+empresa.getEmprCodigo()+" and me.dmaeCodigo=ples.psyDetalleMapaEstrategico.dmaeCodigo"
	// +
	// " and ples.psyPlanAccion.placCodigo=plac.placCodigo and plac.placCodigo="+placCodigo;
	// Query query = sessionFactory.getCurrentSession().createQuery(hql2);
	// List<Object> lasEstrategias = query.list();
	// return lasEstrategias;
	//
	// }

	@Override
	public List<Object> consultaEstrategiasBtnNew(PsyEmpresa empresa) throws Exception {

		String hql2 = "SELECT e.nombre FROM PsyEstrategiaAmbiental e, PsyMatrizCorrelacion m, PsyDetalleMapaEstrategico me,"
				+ " PsyEmpresa empr, PsyPlanEstrategico pest, PsyMapaEstrategico maes "
				+ "WHERE me.psyMatrizCorrelacion.macoCodigo=m.macoCodigo and m.psyEstrategiaAmbiental.esamCodigo=e.esamCodigo "
				+ "and me.psyMapaEstrategico.maesCodigo=maes.maesCodigo and maes.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
				+ " and pest.estadoPlan!='A' and pest.estadoPlan!='C' and pest.psyEmpresa.emprCodigo=empr.emprCodigo"
				+ " and empr.emprCodigo=" + empresa.getEmprCodigo();
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<Object> lasEstrategias = query.list();
		return lasEstrategias;

	}
	
	@Override
	public List<PsyDetalleMapaEstrategico> validacionEstrategiasPlanActual(PsyEmpresa empresa, Long dameCodigo) throws Exception {

		String hql2 = "SELECT me FROM  PsyMatrizCorrelacion m, PsyDetalleMapaEstrategico me,"
				+ " PsyEmpresa empr, PsyPlanEstrategico pest, PsyMapaEstrategico maes, PsyPlanEstrategia ples "
				+ "WHERE me.psyMatrizCorrelacion.macoCodigo=m.macoCodigo and me.dmaeCodigo="+dameCodigo+" "
				+ "and me.psyMapaEstrategico.maesCodigo=maes.maesCodigo and maes.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
				+ " and pest.estadoPlan!='A' and pest.estadoPlan!='C' and pest.psyEmpresa.emprCodigo=empr.emprCodigo"
				+ " and empr.emprCodigo="+empresa.getEmprCodigo()+" and me.dmaeCodigo=ples.psyDetalleMapaEstrategico.dmaeCodigo";
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<PsyDetalleMapaEstrategico> lasEstrategias = query.list();
		return lasEstrategias;

	}

	@Override
	public List<Object> consultaEstrategias(PsyEmpresa empresa, Long placCodigo)
			throws Exception {

		String hql2 = "SELECT e.nombre FROM PsyEstrategiaAmbiental e, PsyMatrizCorrelacion m, PsyDetalleMapaEstrategico me,"
				+ " PsyEmpresa empr, PsyPlanEstrategico pest, PsyMapaEstrategico maes, PsyPlanEstrategia ples, PsyPlanAccion plac "
				+ "WHERE me.psyMatrizCorrelacion.macoCodigo=m.macoCodigo and m.psyEstrategiaAmbiental.esamCodigo=e.esamCodigo "
				+ "and me.psyMapaEstrategico.maesCodigo=maes.maesCodigo and maes.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
				+ " and pest.psyEmpresa.emprCodigo=empr.emprCodigo"
				+ " and empr.emprCodigo="+empresa.getEmprCodigo()+" and me.dmaeCodigo=ples.psyDetalleMapaEstrategico.dmaeCodigo "
						+ "and ples.psyPlanAccion.placCodigo=plac.placCodigo and plac.placCodigo="+placCodigo;
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<Object> lasEstrategias = query.list();
		return lasEstrategias;

	}
	
	@Override
	public List<Object> consultaDmaePorNombreEstrategia(PsyEmpresa empresa, Long placCodigo)
			throws Exception {

		String hql2 = "SELECT me.psyMapaEstrategico.maesCodigo FROM PsyEstrategiaAmbiental e, PsyMatrizCorrelacion m, PsyDetalleMapaEstrategico me,"
				+ " PsyEmpresa empr, PsyPlanEstrategico pest, PsyMapaEstrategico maes, PsyPlanEstrategia ples, PsyPlanAccion plac "
				+ "WHERE me.psyMatrizCorrelacion.macoCodigo=m.macoCodigo and m.psyEstrategiaAmbiental.esamCodigo=e.esamCodigo "
				+ "and me.psyMapaEstrategico.maesCodigo=maes.maesCodigo and maes.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
				+ " and pest.psyEmpresa.emprCodigo=empr.emprCodigo"
				+ " and empr.emprCodigo="+empresa.getEmprCodigo()+" and me.dmaeCodigo=ples.psyDetalleMapaEstrategico.dmaeCodigo "
						+ "and ples.psyPlanAccion.placCodigo=plac.placCodigo and plac.placCodigo="+placCodigo;
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<Object> lasEstrategias = query.list();
		return lasEstrategias;
	}
	
	@Override
	public List<Object> consultaDmaePorPorMaesCodigo(PsyEmpresa empresa, Long placCodigo, Long maesCodigo)
			throws Exception {

		String hql2 = "SELECT DISTINCT(e.nombre) FROM PsyEstrategiaAmbiental e, PsyMatrizCorrelacion m, PsyDetalleMapaEstrategico me,"
				+ " PsyEmpresa empr, PsyPlanEstrategico pest, PsyMapaEstrategico maes, PsyPlanEstrategia ples, PsyPlanAccion plac "
				+ "WHERE me.psyMatrizCorrelacion.macoCodigo=m.macoCodigo and m.psyEstrategiaAmbiental.esamCodigo=e.esamCodigo "
				+ "and me.psyMapaEstrategico.maesCodigo=maes.maesCodigo and maes.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
				+ " and pest.psyEmpresa.emprCodigo=empr.emprCodigo"
				+ " and empr.emprCodigo="+empresa.getEmprCodigo()+" and maes.maesCodigo="+maesCodigo;
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<Object> lasEstrategias = query.list();
		return lasEstrategias;
	}

	@Override
	public List<Object> consultaEstrategiasSeleccionadas(PsyEmpresa empresa,
			Long placCodigo) throws Exception {

		String hql2 = "SELECT e.nombre FROM PsyEstrategiaAmbiental e, PsyMatrizCorrelacion m, PsyDetalleMapaEstrategico me,"
				+ " PsyEmpresa empr, PsyPlanEstrategico pest, PsyMapaEstrategico maes, PsyPlanEstrategia ples, PsyPlanAccion plac"
				+ " WHERE me.psyMatrizCorrelacion.macoCodigo=m.macoCodigo and m.psyEstrategiaAmbiental.esamCodigo=e.esamCodigo "
				+ "and me.psyMapaEstrategico.maesCodigo=maes.maesCodigo and maes.psyPlanEstrategico.pestCodigo=pest.pestCodigo"
				+ " and pest.psyEmpresa.emprCodigo=empr.emprCodigo"
				+ " and empr.emprCodigo="
				+ empresa.getEmprCodigo()
				+ " and me.dmaeCodigo=ples.psyDetalleMapaEstrategico.dmaeCodigo"
				+ " and ples.psyPlanAccion.placCodigo=plac.placCodigo and plac.placCodigo="
				+ placCodigo;
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<Object> lasEstrategias = query.list();
		return lasEstrategias;

	}

	@Override
	public List<PsyDetalleMapaEstrategico> consultaDetalleMapaEstrategicoPorNombreEstrategia(
			String name) throws Exception {

		String hql2 = "SELECT me FROM PsyEstrategiaAmbiental e, PsyMatrizCorrelacion m, PsyDetalleMapaEstrategico me "
				+ "WHERE me.psyMatrizCorrelacion.macoCodigo=m.macoCodigo and"
				+ " m.psyEstrategiaAmbiental.esamCodigo=e.esamCodigo and"
				+ " e.nombre=\'" + name + "\'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
		List<PsyDetalleMapaEstrategico> lasEstrategias = query.list();
		return lasEstrategias;

	}

}
