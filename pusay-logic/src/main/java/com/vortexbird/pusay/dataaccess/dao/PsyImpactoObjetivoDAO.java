package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyImpactoObjetivo;
import com.vortexbird.pusay.modelo.PsyPlanEstrategicoAmbiental;
import com.vortexbird.pusay.modelo.dto.PsyEvaluarIndicadoresDTO;
import com.vortexbird.pusay.modelo.dto.PsyImpactoObjetivoTableDTO;

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
 * PsyImpactoObjetivo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyImpactoObjetivo
 */
@Scope("singleton")
@Repository("PsyImpactoObjetivoDAO")
public class PsyImpactoObjetivoDAO extends HibernateDaoImpl<PsyImpactoObjetivo, Long>
    implements IPsyImpactoObjetivoDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyImpactoObjetivoDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyImpactoObjetivoDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IPsyImpactoObjetivoDAO) ctx.getBean("PsyImpactoObjetivoDAO");
    }
    
    @Override
	public List<PsyImpactoObjetivoTableDTO> getDataImpactoObjetivo(PsyEmpresa empresa) throws Exception {

		
    	List<PsyImpactoObjetivoTableDTO> listDto = null;
   	  	
	  	  try {
	  	   Query query = getSession().getNamedQuery(
	  			"consulta_impacto_objetivo").setLong("pCodigoEmpresa", empresa.getEmprCodigo());
	  	  
	  	   	query.setResultTransformer(Transformers.aliasToBean(PsyImpactoObjetivoTableDTO.class));
	  	    listDto = query.list();
	  	   
	  	  } catch (Exception e) {
	  	   log.error(e.getMessage());
	  	   throw e;
	  	  }

	  	  return listDto;

	}
}
