package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyTarea;
import com.vortexbird.pusay.utilities.Utilities;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
/**
 * A data access object (DAO) providing persistence and search support for
 * PsyTarea entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyTarea
 */
@Scope("singleton")
@Repository("PsyTareaDAO")
public class PsyTareaDAO extends HibernateDaoImpl<PsyTarea, Long>
    implements IPsyTareaDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyTareaDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyTareaDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IPsyTareaDAO) ctx.getBean("PsyTareaDAO");
    }

	@Override
	public Long consultarMaxSemana(Long placCodigo) throws Exception {
		Query query = getSession().createSQLQuery("select max(semana_fin_planeada) from psy_tarea where plac_codigo=:placCodigo").setParameter("placCodigo", placCodigo);
		return Long.parseLong(""+query.uniqueResult());
	}

	@Override
	public Long consultarCountSemanaFinPlaneada(Long placCodigo,
			Long semanaFinPlaneada) throws Exception {
		Query query = getSession().createSQLQuery("select COUNT(semana_fin_planeada) from psy_tarea where semana_fin_planeada<=:semanaFinPlaneada and plac_codigo=:placCodigo").setParameter("semanaFinPlaneada", semanaFinPlaneada).setParameter("placCodigo", placCodigo);
		
		return Long.parseLong(""+query.uniqueResult());
	}

	@Override
	public Long numeroTotalDeTareasPlanDeAccion(Long placCodigo)
			throws Exception {
		Query query = getSession().createSQLQuery("select COUNT(tare_codigo) from psy_tarea where plac_codigo=:placCodigo").setParameter("placCodigo", placCodigo);
		
		return Long.parseLong(""+query.uniqueResult());
	}

	@Override
	public List<Date> maxMinSemana(Long placCodigo, Long semanaFinPlaneada)
			throws Exception {
		Query min = getSession().createSQLQuery("select min(fecha_inicio) from psy_tarea where semana_fin_planeada=:semanaFinPlaneada and plac_codigo=:placCodigo").setParameter("semanaFinPlaneada", semanaFinPlaneada).setParameter("placCodigo", placCodigo);
		Query max = getSession().createSQLQuery("select max(fecha_fin_planeada) from psy_tarea where semana_fin_planeada=:semanaFinPlaneada and plac_codigo=:placCodigo").setParameter("semanaFinPlaneada", semanaFinPlaneada).setParameter("placCodigo", placCodigo);
		List<Date> lst = new ArrayList<Date>();
		lst.add(Utilities.pasarStringDate(""+min.uniqueResult()));
		lst.add(Utilities.pasarStringDate(""+max.uniqueResult()));
		return lst;
	}

	@Override
	public Long consultarMinimaSemanaFinPlaneada(Long placCodigo)
			throws Exception {
		Query query = getSession().createSQLQuery("select min(semana_fin_planeada)minima_semana_fin_planeada from psy_tarea where plac_codigo=:placCodigo").setParameter("placCodigo", placCodigo);
		
		return Long.parseLong(""+query.uniqueResult());
	}
}
