
package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyIndicador;
import com.vortexbird.pusay.modelo.PsyPlanEstrategicoAmbiental;
import com.vortexbird.pusay.modelo.dto.PsyDetalleEstrategiasDTO;
import com.vortexbird.pusay.modelo.dto.PsyEvaluarIndicadoresDTO;

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
 * PsyIndicador entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyIndicador
 */
@Scope("singleton")
@Repository("PsyIndicadorDAO")
public class PsyIndicadorDAO extends HibernateDaoImpl<PsyIndicador, Long>
    implements IPsyIndicadorDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyIndicadorDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyIndicadorDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IPsyIndicadorDAO) ctx.getBean("PsyIndicadorDAO");
    }
    
    @Override
	public List<PsyEvaluarIndicadoresDTO> getDataIndiCadoresByEmpresaByImam(PsyEmpresa empresa, Long imamCodigo, PsyPlanEstrategicoAmbiental planEstrategicoAmbiental) throws Exception {

		
    	List<PsyEvaluarIndicadoresDTO> listDto = null;
   	  	
	  	  try {
	  	   Query query = getSession().getNamedQuery(
	  			"consulta_data_indicadores").setLong("pCodigoEmpresa", empresa.getEmprCodigo()).setLong("pImamCodigo", imamCodigo)
	  			.setLong("pPeaCodigo", planEstrategicoAmbiental.getCodigo());
	  	  
	  	   	query.setResultTransformer(Transformers.aliasToBean(PsyEvaluarIndicadoresDTO.class));
	  	    listDto = query.list();
	  	   
	  	  } catch (Exception e) {
	  	   log.error(e.getMessage());
	  	   throw e;
	  	  }

	  	  return listDto;

	}
    
    @Override
	public List<PsyEvaluarIndicadoresDTO> getDataIndiCadoresByEmpresaByObam(PsyEmpresa empresa, Long obamCodigo, PsyPlanEstrategicoAmbiental planEstrategicoAmbiental) throws Exception {

		
    	List<PsyEvaluarIndicadoresDTO> listDto = null;
   	  	
	  	  try {
	  	   Query query = getSession().getNamedQuery(
	  			"consulta_data_indicadores_objetivo").setLong("pCodigoEmpresa", empresa.getEmprCodigo()).setLong("pObamCodigo", obamCodigo)
	  			.setLong("pPeaCodigo", planEstrategicoAmbiental.getCodigo());
	  	  
	  	   	query.setResultTransformer(Transformers.aliasToBean(PsyEvaluarIndicadoresDTO.class));
	  	    listDto = query.list();
	  	   
	  	  } catch (Exception e) {
	  	   log.error(e.getMessage());
	  	   throw e;
	  	  }

	  	  return listDto;

	}
    
    @Override
   	public List<PsyIndicador> getAllIndicadoresWithDistinct() throws Exception {

   		
   		String hql2 = "SELECT DISTINCT indi FROM PsyIndicador indi";
   		Query query = sessionFactory.getCurrentSession().createQuery(hql2);
   		List<PsyIndicador> losIndicadores = query.list();
   		return losIndicadores;

   	}
    
}
