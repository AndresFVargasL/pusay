package com.vortexbird.pusay.dataaccess.dao;

import com.vortexbird.pusay.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.pusay.modelo.PsyIpu;
import com.vortexbird.pusay.modelo.PsyPlanAccion;
import com.vortexbird.pusay.modelo.dto.PsyIpuDTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;


/**
 * A data access object (DAO) providing persistence and search support for
 * PsyIpu entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.PsyIpu
 */
@Scope("singleton")
@Repository("PsyIpuDAO")
public class PsyIpuDAO extends HibernateDaoImpl<PsyIpu, Long>
    implements IPsyIpuDAO {
    private static final Logger log = LoggerFactory.getLogger(PsyIpuDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPsyIpuDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IPsyIpuDAO) ctx.getBean("PsyIpuDAO");
    }
    
    @SuppressWarnings("unchecked")
   	public List<PsyIpuDTO> consultarIpu(PsyPlanAccion  planAccion, String tipoIpu ) throws Exception {
           	  
       	  List<PsyIpuDTO> listDto = null;       	
       	  try {
       		 Query query = null;
       		  
       		  if(tipoIpu.equals("T")){
       			 query = getSession().getNamedQuery(
       	       	      "consulta_ipu").setLong("pPlanCodigo", planAccion.getPlacCodigo()).setString("pEstadoRegistro", "A").setString("pTipoIpu", tipoIpu);
       		  }else if(tipoIpu.equals("P")){
       			 query = getSession().getNamedQuery(
       	       	      "consulta_ipu_pesupuesto").setLong("pPlanCodigo", planAccion.getPlacCodigo()).setString("pEstadoRegistro", "A").setString("pTipoIpu", tipoIpu);
       		  }
       	         	   
       	    query.setResultTransformer(Transformers.aliasToBean(PsyIpuDTO.class));
       	    listDto = query.list();
       	   
       	  } catch (Exception e) {
       	   log.error(e.getMessage());
       	   throw e;
       	  }

       	  return listDto;
   	}
    
    @SuppressWarnings("unchecked")
    public Long consultarCantidadTareasHastaPeriodo(PsyPlanAccion planAccion,
    		   Long semana) throws Exception {
    	
    		  Query query = getSession().createSQLQuery("select COUNT(semana_fin_real) from psy_tarea where semana_fin_real<=:pSemana and plac_codigo=:pPlacCodigo and estado_registro = 'A'").setParameter("pSemana", semana).setParameter("pPlacCodigo", planAccion.getPlacCodigo());
    		  BigInteger resultado = (BigInteger)query.uniqueResult();
    		  Long cantidadTareas = resultado.longValue();
    		  return cantidadTareas;
    }
    

    @SuppressWarnings("unchecked")
    public Long consultarCantidadTareasPlan(PsyPlanAccion planAccion) throws Exception {
    	
    		  Query query = getSession().createSQLQuery("select COUNT(*) from psy_tarea where plac_codigo=:pPlacCodigo and estado_registro = 'A'").setParameter("pPlacCodigo", planAccion.getPlacCodigo());
    		  BigInteger resultado = (BigInteger)query.uniqueResult();
    		  Long cantidadTareas = resultado.longValue();
    		  return cantidadTareas;
    }
    
    @SuppressWarnings("unchecked")
    public Long consultarTotalPresupuestoPlan(PsyPlanAccion planAccion) throws Exception {
    	
    		  Query query = getSession().createSQLQuery("select SUM(itpr.VALOR_presupuestado) from psy_presupuesto  "
    		  		+ "pres inner join psy_item_presupuesto itpr on pres.pres_codigo = itpr.pres_codigo "
    		  		+ "where pres.plac_codigo=:pPlacCodigo and pres.estado_registro = 'A'").setParameter("pPlacCodigo", planAccion.getPlacCodigo());
    		  BigDecimal resultado = (BigDecimal)query.uniqueResult();
    		  Long cantidadTareas = resultado.longValue();
    		  return cantidadTareas;
    }
    
    @SuppressWarnings("unchecked")
    public Long consultarPrespuestoRealHastaPeriodo(PsyPlanAccion planAccion,
    		   Long semana) throws Exception {
    	
    		  Query query = getSession().createSQLQuery("select SUM(itpr.valor_ejecutado)from psy_presupuesto  pres "
    		  		+ "inner join psy_item_presupuesto itpr on pres.pres_codigo = itpr.pres_codigo "
    		  		+ "where pres.plac_codigo=:pPlacCodigo and pres.estado_registro = 'A'and itpr.semana <= :pSemana").setParameter("pSemana", semana).setParameter("pPlacCodigo", planAccion.getPlacCodigo());
    		  BigDecimal resultado = (BigDecimal)query.uniqueResult();
    		  Long cantidadTareas = resultado.longValue();
    		  return cantidadTareas;
    }
    
    
        
}
