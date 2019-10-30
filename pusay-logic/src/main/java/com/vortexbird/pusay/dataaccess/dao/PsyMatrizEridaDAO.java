package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyAsuntoAmbiental;
import com.vortexbird.pusay.modelo.PsyMatrizErida;

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
 * PsyMatrizErida entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyMatrizErida
 */
@Scope("singleton")
@Repository("PsyMatrizEridaDAO")
public class PsyMatrizEridaDAO extends HibernateDaoImpl<PsyMatrizErida, Long>
    implements IPsyMatrizEridaDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyMatrizEridaDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyMatrizEridaDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IPsyMatrizEridaDAO) ctx.getBean("PsyMatrizEridaDAO");
    }
    @SuppressWarnings("unchecked")
   	public List<PsyAsuntoAmbiental> asuntosAmbientalesFaltantes(Long mearCodigo)
   			throws Exception {
   		List<PsyAsuntoAmbiental> listDto = null;
   	  	
   	  	  try {

   	  	      
   	  	   Query query = getSession().getNamedQuery(
   	  	      "consulta_asuntos_ambientales_faltantes").setLong("mearCodigo", mearCodigo);
   	  	  
   	  	    query.setResultTransformer(Transformers.aliasToBean(PsyAsuntoAmbiental.class));
   	  	    listDto = query.list();

   	  	   
   	  	  } catch (Exception e) {
   	  	   log.error(e.getMessage());
   	  	   throw e;
   	  	  }

   	  	  return listDto;
   	}
}
