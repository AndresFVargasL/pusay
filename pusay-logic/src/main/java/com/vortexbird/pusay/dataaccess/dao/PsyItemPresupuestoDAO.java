package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyItemPresupuesto;
import com.vortexbird.pusay.utilities.Utilities;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;


/**
 * A data access object (DAO) providing persistence and search support for
 * PsyItemPresupuesto entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyItemPresupuesto
 */
@Scope("singleton")
@Repository("PsyItemPresupuestoDAO")
public class PsyItemPresupuestoDAO extends HibernateDaoImpl<PsyItemPresupuesto, Long>
    implements IPsyItemPresupuestoDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyItemPresupuestoDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyItemPresupuestoDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IPsyItemPresupuestoDAO) ctx.getBean("PsyItemPresupuestoDAO");
    }
    @Override
   	public Long consultarMaxSemana(Long presCodigo) throws Exception {
   		Query query = getSession().createSQLQuery("select max(semana) from psy_item_presupuesto where pres_codigo=:presCodigo").setParameter("presCodigo", presCodigo);
   		return Long.parseLong(""+query.uniqueResult());
   	}

   	@Override
   	public Double consultarSumSemana(Long presCodigo, Long semana){
   	Query query = getSession().createSQLQuery("select sum(valor_presupuestado) from psy_item_presupuesto where pres_codigo=:presCodigo and semana<=:semana").setParameter("semana", semana).setParameter("presCodigo", presCodigo);
   	Double varDouble;
   	
   	if(query.uniqueResult()==null){
   		varDouble=0D;
   	}else{
   		varDouble=Double.parseDouble(""+query.uniqueResult());
   	}
   	return varDouble;
   		
   	}
	@Override
	public List<Date> maxMinSemana(Long presCodigo, Long semana) {
		Query min = getSession().createSQLQuery("select min(fecha_inicio) from psy_item_presupuesto where semana=:semana and pres_codigo=:presCodigo").setParameter("semana", semana).setParameter("presCodigo", presCodigo);
		//Query max = getSession().createSQLQuery("select max(fecha_fin) from psy_item_presupuesto where semana=:semana and pres_codigo=:presCodigo").setParameter("semana", semana).setParameter("presCodigo", presCodigo);
		List<Date> lst = new ArrayList<Date>();
		lst.add(Utilities.pasarStringDate(""+min.uniqueResult()));
		//lst.add(Utilities.pasarStringDate(""+max.uniqueResult()));
		return lst;
		
	}
	@Override
	public Long consultarMinSemana(Long presCodigo) throws Exception {
		Query query = getSession().createSQLQuery("select min(semana) from psy_item_presupuesto where pres_codigo=:presCodigo").setParameter("presCodigo", presCodigo);
   		return Long.parseLong(""+query.uniqueResult());
	}
}
