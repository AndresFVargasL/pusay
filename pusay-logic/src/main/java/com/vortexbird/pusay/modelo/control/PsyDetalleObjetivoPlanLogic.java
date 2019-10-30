package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyDetalleObjetivoPlanDTO;
import com.vortexbird.pusay.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("PsyDetalleObjetivoPlanLogic")
public class PsyDetalleObjetivoPlanLogic implements IPsyDetalleObjetivoPlanLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyDetalleObjetivoPlanLogic.class);

    /**
     * DAO injected by Spring that manages PsyDetalleObjetivoPlan entities
     *
     */
    @Autowired
    private IPsyDetalleObjetivoPlanDAO psyDetalleObjetivoPlanDAO;

    /**
    * Logic injected by Spring that manages PsyObjetivoEstrategico entities
    *
    */
    @Autowired
    IPsyObjetivoEstrategicoLogic logicPsyObjetivoEstrategico1;

    /**
    * Logic injected by Spring that manages PsyObjetivoPlan entities
    *
    */
    @Autowired
    IPsyObjetivoPlanLogic logicPsyObjetivoPlan2;

    @Transactional(readOnly = true)
    public List<PsyDetalleObjetivoPlan> getPsyDetalleObjetivoPlan()
        throws Exception {
        log.debug("finding all PsyDetalleObjetivoPlan instances");

        List<PsyDetalleObjetivoPlan> list = new ArrayList<PsyDetalleObjetivoPlan>();

        try {
            list = psyDetalleObjetivoPlanDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyDetalleObjetivoPlan failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyDetalleObjetivoPlan");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyDetalleObjetivoPlan(PsyDetalleObjetivoPlan entity)
        throws Exception {
        log.debug("saving PsyDetalleObjetivoPlan instance");

        try {
            if (entity.getPsyObjetivoEstrategico() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyObjetivoEstrategico");
            }

            if (entity.getPsyObjetivoPlan() == null) {
                throw new ZMessManager().new ForeignException("psyObjetivoPlan");
            }

            if (entity.getDescripcion() == null) {
                throw new ZMessManager().new EmptyFieldException("descripcion");
            }

            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcion");
            }

            if (entity.getDobpCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("dobpCodigo");
            }

            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "estadoRegistro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoRegistro");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }

            if (entity.getPsyObjetivoEstrategico().getObesCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "obesCodigo_PsyObjetivoEstrategico");
            }

            if (entity.getPsyObjetivoPlan().getObplCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "obplCodigo_PsyObjetivoPlan");
            }

            if (getPsyDetalleObjetivoPlan(entity.getDobpCodigo()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            psyDetalleObjetivoPlanDAO.save(entity);

            log.debug("save PsyDetalleObjetivoPlan successful");
        } catch (Exception e) {
            log.error("save PsyDetalleObjetivoPlan failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyDetalleObjetivoPlan(PsyDetalleObjetivoPlan entity)
        throws Exception {
        log.debug("deleting PsyDetalleObjetivoPlan instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "PsyDetalleObjetivoPlan");
        }

        if (entity.getDobpCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("dobpCodigo");
        }

        try {
            psyDetalleObjetivoPlanDAO.delete(entity);

            log.debug("delete PsyDetalleObjetivoPlan successful");
        } catch (Exception e) {
            log.error("delete PsyDetalleObjetivoPlan failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyDetalleObjetivoPlan(PsyDetalleObjetivoPlan entity)
        throws Exception {
        log.debug("updating PsyDetalleObjetivoPlan instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "PsyDetalleObjetivoPlan");
            }

            if (entity.getPsyObjetivoEstrategico() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyObjetivoEstrategico");
            }

            if (entity.getPsyObjetivoPlan() == null) {
                throw new ZMessManager().new ForeignException("psyObjetivoPlan");
            }

            if (entity.getDescripcion() == null) {
                throw new ZMessManager().new EmptyFieldException("descripcion");
            }

            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcion");
            }

            if (entity.getDobpCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("dobpCodigo");
            }

            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "estadoRegistro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoRegistro");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }

            if (entity.getPsyObjetivoEstrategico().getObesCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "obesCodigo_PsyObjetivoEstrategico");
            }

            if (entity.getPsyObjetivoPlan().getObplCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "obplCodigo_PsyObjetivoPlan");
            }

            psyDetalleObjetivoPlanDAO.update(entity);

            log.debug("update PsyDetalleObjetivoPlan successful");
        } catch (Exception e) {
            log.error("update PsyDetalleObjetivoPlan failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyDetalleObjetivoPlanDTO> getDataPsyDetalleObjetivoPlan()
        throws Exception {
        try {
            List<PsyDetalleObjetivoPlan> psyDetalleObjetivoPlan = psyDetalleObjetivoPlanDAO.findAll();

            List<PsyDetalleObjetivoPlanDTO> psyDetalleObjetivoPlanDTO = new ArrayList<PsyDetalleObjetivoPlanDTO>();

            for (PsyDetalleObjetivoPlan psyDetalleObjetivoPlanTmp : psyDetalleObjetivoPlan) {
                PsyDetalleObjetivoPlanDTO psyDetalleObjetivoPlanDTO2 = new PsyDetalleObjetivoPlanDTO();

                psyDetalleObjetivoPlanDTO2.setDobpCodigo(psyDetalleObjetivoPlanTmp.getDobpCodigo());
                psyDetalleObjetivoPlanDTO2.setDescripcion((psyDetalleObjetivoPlanTmp.getDescripcion() != null)
                    ? psyDetalleObjetivoPlanTmp.getDescripcion() : null);
                psyDetalleObjetivoPlanDTO2.setEstadoRegistro((psyDetalleObjetivoPlanTmp.getEstadoRegistro() != null)
                    ? psyDetalleObjetivoPlanTmp.getEstadoRegistro() : null);
                psyDetalleObjetivoPlanDTO2.setNombre((psyDetalleObjetivoPlanTmp.getNombre() != null)
                    ? psyDetalleObjetivoPlanTmp.getNombre() : null);
                psyDetalleObjetivoPlanDTO2.setObesCodigo_PsyObjetivoEstrategico((psyDetalleObjetivoPlanTmp.getPsyObjetivoEstrategico()
                                                                                                          .getObesCodigo() != null)
                    ? psyDetalleObjetivoPlanTmp.getPsyObjetivoEstrategico()
                                               .getObesCodigo() : null);
                psyDetalleObjetivoPlanDTO2.setObplCodigo_PsyObjetivoPlan((psyDetalleObjetivoPlanTmp.getPsyObjetivoPlan()
                                                                                                   .getObplCodigo() != null)
                    ? psyDetalleObjetivoPlanTmp.getPsyObjetivoPlan()
                                               .getObplCodigo() : null);
                psyDetalleObjetivoPlanDTO.add(psyDetalleObjetivoPlanDTO2);
            }

            return psyDetalleObjetivoPlanDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyDetalleObjetivoPlan getPsyDetalleObjetivoPlan(Long dobpCodigo)
        throws Exception {
        log.debug("getting PsyDetalleObjetivoPlan instance");

        PsyDetalleObjetivoPlan entity = null;

        try {
            entity = psyDetalleObjetivoPlanDAO.findById(dobpCodigo);
        } catch (Exception e) {
            log.error("get PsyDetalleObjetivoPlan failed", e);
            throw new ZMessManager().new FindingException(
                "PsyDetalleObjetivoPlan");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyDetalleObjetivoPlan> findPagePsyDetalleObjetivoPlan(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<PsyDetalleObjetivoPlan> entity = null;

        try {
            entity = psyDetalleObjetivoPlanDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyDetalleObjetivoPlan Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyDetalleObjetivoPlan()
        throws Exception {
        Long entity = null;

        try {
            entity = psyDetalleObjetivoPlanDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyDetalleObjetivoPlan Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<PsyDetalleObjetivoPlan> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyDetalleObjetivoPlan> list = new ArrayList<PsyDetalleObjetivoPlan>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = psyDetalleObjetivoPlanDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PsyDetalleObjetivoPlan> consultarDetalleObjetivoPlan(PsyObjetivoPlan objetivoPlan) throws Exception {
   
    	 List<PsyDetalleObjetivoPlan> listaObjetivosCorporativos = null;
    	 //se consulta los objtivos corporativos relacionados con el plan objetivo
    	 
    	 Object[] variablesDetalleObjetivoPlan = { "psyObjetivoPlan.obplCodigo", false,
    			 objetivoPlan.getObplCodigo(), "=", "estadoRegistro", true,
    			 "A", "="};
 		
    	 listaObjetivosCorporativos = findByCriteria(variablesDetalleObjetivoPlan, null, null);
    	 
    	 for(PsyDetalleObjetivoPlan objetivo: listaObjetivosCorporativos){
    		 objetivo.setCodigoObjetivoEstrategico(objetivo.getPsyObjetivoEstrategico().getObesCodigo()+"");
    	 }
    	 
    	 return listaObjetivosCorporativos;
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<PsyDetalleObjetivoPlan> guardarDetalleObjetivoPlan(List<PsyDetalleObjetivoPlan> listaDetalle, PsyObjetivoPlan objetivoPlan, String nombreVacio, String descripcionVacio) throws Exception {
   
    	PsyDetalleObjetivoPlan detalleConsulta = null;
    	PsyObjetivoEstrategico objetivoEstrategico = null;
    	
    	//Se recorre cada uno de los item a guardar
    	for(PsyDetalleObjetivoPlan detalle: listaDetalle){
    		
    		detalle.setPsyObjetivoPlan(objetivoPlan);
    		objetivoEstrategico = logicPsyObjetivoEstrategico1.getPsyObjetivoEstrategico(Long.parseLong(detalle.getCodigoObjetivoEstrategico()));
    		detalle.setPsyObjetivoEstrategico(objetivoEstrategico);
    		
    		//Se consulta si ya existe un item con ese id
    		detalleConsulta = getPsyDetalleObjetivoPlan(detalle.getDobpCodigo());
    		if(detalleConsulta!= null){
    			if(detalle.getNombre().equals(nombreVacio) &&
    					detalle.getDescripcion().equals(descripcionVacio)){       				
    				deletePsyDetalleObjetivoPlan(detalleConsulta);    				
    			}else{
    				detalleConsulta.setEstadoRegistro("A");
    				detalleConsulta.setDescripcion(detalle.getDescripcion());
    				detalleConsulta.setNombre(detalle.getNombre());
    				detalleConsulta.setPsyObjetivoEstrategico(detalle.getPsyObjetivoEstrategico());
    				detalleConsulta.setPsyObjetivoPlan(detalle.getPsyObjetivoPlan());
    				updatePsyDetalleObjetivoPlan(detalleConsulta);
    			}
    			    			
    		}else{    			
    			
    			if(detalle.getNombre().equals(nombreVacio) &&
    					detalle.getDescripcion().equals(descripcionVacio)){    				
    				 				
    			}else{
    				detalle.setEstadoRegistro("A");
    				savePsyDetalleObjetivoPlan(detalle);
    			}
    			
    			
    		}
    		
    		
    	}
    	    	    	 
    	 return listaDetalle;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PsyDetalleObjetivoPlan> consultarListaCompletaObjetivos(List<PsyDetalleObjetivoPlan> lstObjetivoPlan, Integer cantidadOjetivos) throws Exception {
   
    	int cantidadObjetivosActuales = lstObjetivoPlan.size();        		
		PsyDetalleObjetivoPlan detalle = null;
		
		for(int i=cantidadObjetivosActuales; i< cantidadOjetivos; i++){ // Se adicionan objetivos vacios para los espacios restantes
			detalle = new PsyDetalleObjetivoPlan();
			detalle.setDescripcion("NO SE HA DEFINIDO EL OBJETIVO");
			detalle.setNombre("NO DEFINIDO");
			detalle.setEstadoRegistro("A");
			detalle.setDobpCodigo(Long.parseLong(i+""));
			detalle.setCodigoObjetivoEstrategico("-1");
			detalle.setPsyObjetivoEstrategico(null);
			detalle.setPsyObjetivoPlan(null);
			lstObjetivoPlan.add(detalle);
			
 		}
    	
		return lstObjetivoPlan;
    }

    
}
