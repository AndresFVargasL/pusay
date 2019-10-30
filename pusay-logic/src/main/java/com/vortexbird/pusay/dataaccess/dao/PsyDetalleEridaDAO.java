package com.vortexbird.pusay.dataaccess.dao;



import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyAsuntoAmbiental;
import com.vortexbird.pusay.modelo.PsyDetalleErida;
import com.vortexbird.pusay.modelo.dto.PsyPriorizacionAsuntoAmbientalDTO;
import com.vortexbird.pusay.modelo.dto.PsyPriorizacionImpactosAmbientalesDTO;
import com.vortexbird.pusay.modelo.dto.PsyTablaEridaDTO;

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
 * PsyDetalleErida entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyDetalleErida
 */
@Scope("singleton")
@Repository("PsyDetalleEridaDAO")
public class PsyDetalleEridaDAO extends HibernateDaoImpl<PsyDetalleErida, Long>
    implements IPsyDetalleEridaDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyDetalleEridaDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyDetalleEridaDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IPsyDetalleEridaDAO) ctx.getBean("PsyDetalleEridaDAO");
    }
    @SuppressWarnings("unchecked")
	public List<PsyTablaEridaDTO> consultarTablaErida(Long mearCodigo,Long asamCodigo ) throws Exception {
 
    	  
    	  List<PsyTablaEridaDTO> listDto = null;
    	
    	  try {
    		  
    	   Query query = getSession().getNamedQuery(
    	      "consulta_tabla_erida").setLong("mearCodigo", mearCodigo).setLong("asamCodigo", asamCodigo);
    	  
    	    query.setResultTransformer(Transformers.aliasToBean(PsyTablaEridaDTO.class));
    	    listDto = query.list();

    	   
    	  } catch (Exception e) {
    	   log.error(e.getMessage());
    	   throw e;
    	  }

    	  return listDto;
	}

    @SuppressWarnings("unchecked")
	public List<PsyTablaEridaDTO> llenarTablaErida() throws Exception {

  	  List<PsyTablaEridaDTO> listDto = null;
  	
  	  try {

  	      
  	   Query query = getSession().getNamedQuery(
  	      "llenar_tabla_erida");
  	  
  	    query.setResultTransformer(Transformers.aliasToBean(PsyTablaEridaDTO.class));
  	    listDto = query.list();

  	   
  	  } catch (Exception e) {
  	   log.error(e.getMessage());
  	   throw e;
  	  }

  	  return listDto;
	}
    @SuppressWarnings("unchecked")
	public List<PsyPriorizacionAsuntoAmbientalDTO> PriorizacionAsuntoAmbiental(Long mearCodigo)
			throws Exception {
		List<PsyPriorizacionAsuntoAmbientalDTO> listDto = null;
	  	
	  	  try {

	  	      
	  	   Query query = getSession().getNamedQuery(
	  	      "consulta_priorizacion_asunto_ambienta").setLong("mearCodigo", mearCodigo);
	  	  
	  	    query.setResultTransformer(Transformers.aliasToBean(PsyPriorizacionAsuntoAmbientalDTO.class));
	  	    listDto = query.list();

	  	   
	  	  } catch (Exception e) {
	  	   log.error(e.getMessage());
	  	   throw e;
	  	  }

	  	  return listDto;
	}
    @SuppressWarnings("unchecked")
	public List<PsyPriorizacionImpactosAmbientalesDTO> PriorizacionImpactosAmbientales(Long mearCodigo)
			throws Exception {
		
		List<PsyPriorizacionImpactosAmbientalesDTO> listDto = null;
	  	
	  	  try {

	  	      
	  	   Query query = getSession().getNamedQuery(
	  	      "consulta_priorizaciono_impactos_ambientale").setLong("mearCodigo", mearCodigo);
	  	  
	  	    query.setResultTransformer(Transformers.aliasToBean(PsyPriorizacionImpactosAmbientalesDTO.class));
	  	    listDto = query.list();

	  	   
	  	  } catch (Exception e) {
	  	   log.error(e.getMessage());
	  	   throw e;
	  	  }

	  	  return listDto;
	}
   
}
