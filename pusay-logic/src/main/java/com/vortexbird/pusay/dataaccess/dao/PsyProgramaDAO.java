package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPlanAccion;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;
import com.vortexbird.pusay.modelo.PsyPrograma;
import com.vortexbird.pusay.modelo.dto.PsyDetalleEstrategiasDTO;
import com.vortexbird.pusay.modelo.dto.PsyProgramaDTO;

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
 * PsyPrograma entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyPrograma
 */
@Scope("singleton")
@Repository("PsyProgramaDAO")
public class PsyProgramaDAO extends HibernateDaoImpl<PsyPrograma, Long>
    implements IPsyProgramaDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyProgramaDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyProgramaDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IPsyProgramaDAO) ctx.getBean("PsyProgramaDAO");
    }
    
    @Override
	public List<PsyProgramaDTO> consultaProgramaPorEmpresa(PsyEmpresa empresa) throws Exception {

    	
    	
    	List<PsyProgramaDTO> listProgramDto = null;

    	try {
    		Query query = getSession().getNamedQuery(
    				"consulta_data_psy_programa").setLong("pCodigoEmpresa", empresa.getEmprCodigo());

    		query.setResultTransformer(Transformers.aliasToBean(PsyProgramaDTO.class));
    		listProgramDto = query.list();

    	} catch (Exception e) {
    		log.error(e.getMessage());
    		throw e;
    	}

    	return listProgramDto;
		
	}
    
    @Override
    public List<PsyDetalleEstrategiasDTO> consultaEstrategiasParaPrograma(PsyEmpresa empresa)
            throws Exception{
    	
    	List<PsyDetalleEstrategiasDTO> listDto = null;
   	  	
 	  	  try {
 	  	   Query query = getSession().getNamedQuery(
 	  			"consulta_estrategias_detalle").setLong("pCodigoEmpresa", empresa.getEmprCodigo());
 	  	  
 	  	   	query.setResultTransformer(Transformers.aliasToBean(PsyDetalleEstrategiasDTO.class));
 	  	    listDto = query.list();
 	  	   
 	  	  } catch (Exception e) {
 	  	   log.error(e.getMessage());
 	  	   throw e;
 	  	  }

 	  	  return listDto;
    	
    }
    
    @Override
    public List<PsyDetalleEstrategiasDTO> consultaEstrategiasParaProgramaDepurada(PsyEmpresa empresa)
            throws Exception{
    	
    	List<PsyDetalleEstrategiasDTO> listDto = null;
   	  	
 	  	  try {
 	  	   Query query = getSession().getNamedQuery(
 	  			"consulta_estrategias_detalle_depurada").setLong("pCodigoEmpresa", empresa.getEmprCodigo());
 	  	  
 	  	   	query.setResultTransformer(Transformers.aliasToBean(PsyDetalleEstrategiasDTO.class));
 	  	    listDto = query.list();
 	  	   
 	  	  } catch (Exception e) {
 	  	   log.error(e.getMessage());
 	  	   throw e;
 	  	  }

 	  	  return listDto;
    	
    }
    
}
