package com.vortexbird.pusay.modelo.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.pusay.dataaccess.dao.IPsyDetalleMapaEstrategicoDAO;
import com.vortexbird.pusay.dataaccess.dao.IPsyPlanEstrategiaDAO;
import com.vortexbird.pusay.exceptions.ZMessManager;
import com.vortexbird.pusay.modelo.PsyDetalleMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyDetalleObjetivoPlan;
import com.vortexbird.pusay.modelo.PsyEstrategiaAmbiental;
import com.vortexbird.pusay.modelo.PsyImpactoAmbiental;
import com.vortexbird.pusay.modelo.PsyMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyMatrizCorrelacion;
import com.vortexbird.pusay.modelo.PsyObjetivoEstrategico;
import com.vortexbird.pusay.modelo.PsyObjetivoImpacto;
import com.vortexbird.pusay.modelo.PsyPlanEstrategia;
import com.vortexbird.pusay.modelo.dto.PsyDetalleMapaEstrategicoDTO;
import com.vortexbird.pusay.modelo.dto.PsyDetalleMatrizCorrelacionDTO;
import com.vortexbird.pusay.utilities.Utilities;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("PsyDetalleMapaEstrategicoLogic")
public class PsyDetalleMapaEstrategicoLogic
    implements IPsyDetalleMapaEstrategicoLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyDetalleMapaEstrategicoLogic.class);

    /**
     * DAO injected by Spring that manages PsyDetalleMapaEstrategico entities
     *
     */
    @Autowired
    private IPsyDetalleMapaEstrategicoDAO psyDetalleMapaEstrategicoDAO;

    /**
    * DAO injected by Spring that manages PsyPlanEstrategia entities
    *
    */
    @Autowired
    private IPsyPlanEstrategiaDAO psyPlanEstrategiaDAO;

    /**
    * Logic injected by Spring that manages PsyMapaEstrategico entities
    *
    */
    @Autowired
    IPsyMapaEstrategicoLogic logicPsyMapaEstrategico1;

    /**
    * Logic injected by Spring that manages PsyMatrizCorrelacion entities
    *
    */
    @Autowired
    IPsyMatrizCorrelacionLogic logicPsyMatrizCorrelacion2;
    
    @Autowired
    IPsyImpactoAmbientalLogic psyImpactoAmbientalLogic;
    
    @Autowired
    IPsyEstrategiaAmbientalLogic psyEstrategiaAmbientalLogic;

    @Transactional(readOnly = true)
    public List<PsyDetalleMapaEstrategico> getPsyDetalleMapaEstrategico()
        throws Exception {
        log.debug("finding all PsyDetalleMapaEstrategico instances");

        List<PsyDetalleMapaEstrategico> list = new ArrayList<PsyDetalleMapaEstrategico>();

        try {
            list = psyDetalleMapaEstrategicoDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyDetalleMapaEstrategico failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyDetalleMapaEstrategico");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyDetalleMapaEstrategico(PsyDetalleMapaEstrategico entity)
        throws Exception {
        log.debug("saving PsyDetalleMapaEstrategico instance");

        try {
            if (entity.getPsyMapaEstrategico() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyMapaEstrategico");
            }

            if (entity.getPsyMatrizCorrelacion() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyMatrizCorrelacion");
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

            if (entity.getPsyMapaEstrategico().getMaesCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "maesCodigo_PsyMapaEstrategico");
            }

            if (entity.getPsyMatrizCorrelacion().getMacoCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "macoCodigo_PsyMatrizCorrelacion");
            }

            psyDetalleMapaEstrategicoDAO.save(entity);

            log.debug("save PsyDetalleMapaEstrategico successful");
        } catch (Exception e) {
            log.error("save PsyDetalleMapaEstrategico failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyDetalleMapaEstrategico(
        PsyDetalleMapaEstrategico entity) throws Exception {
        log.debug("deleting PsyDetalleMapaEstrategico instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "PsyDetalleMapaEstrategico");
        }

        if (entity.getDmaeCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("dmaeCodigo");
        }

        List<PsyPlanEstrategia> psyPlanEstrategias = null;

        try {
            psyPlanEstrategias = psyPlanEstrategiaDAO.findByProperty("psyDetalleMapaEstrategico.dmaeCodigo",
                    entity.getDmaeCodigo());

            if (Utilities.validationsList(psyPlanEstrategias) == true) {
                throw new ZMessManager().new DeletingException(
                    "psyPlanEstrategias");
            }

            psyDetalleMapaEstrategicoDAO.delete(entity);

            log.debug("delete PsyDetalleMapaEstrategico successful");
        } catch (Exception e) {
            log.error("delete PsyDetalleMapaEstrategico failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyDetalleMapaEstrategico(
        PsyDetalleMapaEstrategico entity) throws Exception {
        log.debug("updating PsyDetalleMapaEstrategico instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "PsyDetalleMapaEstrategico");
            }

            if (entity.getPsyMapaEstrategico() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyMapaEstrategico");
            }

            if (entity.getPsyMatrizCorrelacion() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyMatrizCorrelacion");
            }

            if (entity.getDmaeCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("dmaeCodigo");
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

            if (entity.getPsyMapaEstrategico().getMaesCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "maesCodigo_PsyMapaEstrategico");
            }

            if (entity.getPsyMatrizCorrelacion().getMacoCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "macoCodigo_PsyMatrizCorrelacion");
            }

            psyDetalleMapaEstrategicoDAO.update(entity);

            log.debug("update PsyDetalleMapaEstrategico successful");
        } catch (Exception e) {
            log.error("update PsyDetalleMapaEstrategico failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyDetalleMapaEstrategicoDTO> getDataPsyDetalleMapaEstrategico()
        throws Exception {
        try {
            List<PsyDetalleMapaEstrategico> psyDetalleMapaEstrategico = psyDetalleMapaEstrategicoDAO.findAll();

            List<PsyDetalleMapaEstrategicoDTO> psyDetalleMapaEstrategicoDTO = new ArrayList<PsyDetalleMapaEstrategicoDTO>();

            for (PsyDetalleMapaEstrategico psyDetalleMapaEstrategicoTmp : psyDetalleMapaEstrategico) {
                PsyDetalleMapaEstrategicoDTO psyDetalleMapaEstrategicoDTO2 = new PsyDetalleMapaEstrategicoDTO();

                psyDetalleMapaEstrategicoDTO2.setDmaeCodigo(psyDetalleMapaEstrategicoTmp.getDmaeCodigo());
                psyDetalleMapaEstrategicoDTO2.setEstadoRegistro((psyDetalleMapaEstrategicoTmp.getEstadoRegistro() != null)
                    ? psyDetalleMapaEstrategicoTmp.getEstadoRegistro() : null);
                psyDetalleMapaEstrategicoDTO2.setMaesCodigo_PsyMapaEstrategico((psyDetalleMapaEstrategicoTmp.getPsyMapaEstrategico()
                                                                                                            .getMaesCodigo() != null)
                    ? psyDetalleMapaEstrategicoTmp.getPsyMapaEstrategico()
                                                  .getMaesCodigo() : null);
                psyDetalleMapaEstrategicoDTO2.setMacoCodigo_PsyMatrizCorrelacion((psyDetalleMapaEstrategicoTmp.getPsyMatrizCorrelacion()
                                                                                                              .getMacoCodigo() != null)
                    ? psyDetalleMapaEstrategicoTmp.getPsyMatrizCorrelacion()
                                                  .getMacoCodigo() : null);
                psyDetalleMapaEstrategicoDTO.add(psyDetalleMapaEstrategicoDTO2);
            }

            return psyDetalleMapaEstrategicoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyDetalleMapaEstrategico getPsyDetalleMapaEstrategico(
        Long dmaeCodigo) throws Exception {
        log.debug("getting PsyDetalleMapaEstrategico instance");

        PsyDetalleMapaEstrategico entity = null;

        try {
            entity = psyDetalleMapaEstrategicoDAO.findById(dmaeCodigo);
        } catch (Exception e) {
            log.error("get PsyDetalleMapaEstrategico failed", e);
            throw new ZMessManager().new FindingException(
                "PsyDetalleMapaEstrategico");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyDetalleMapaEstrategico> findPagePsyDetalleMapaEstrategico(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<PsyDetalleMapaEstrategico> entity = null;

        try {
            entity = psyDetalleMapaEstrategicoDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyDetalleMapaEstrategico Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyDetalleMapaEstrategico()
        throws Exception {
        Long entity = null;

        try {
            entity = psyDetalleMapaEstrategicoDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyDetalleMapaEstrategico Count");
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
    public List<PsyDetalleMapaEstrategico> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyDetalleMapaEstrategico> list = new ArrayList<PsyDetalleMapaEstrategico>();
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
            list = psyDetalleMapaEstrategicoDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PsyDetalleMatrizCorrelacionDTO> consultarMatrizRelacionMapaEstrategico(
    		PsyMapaEstrategico mapaEstrategico, List<PsyDetalleObjetivoPlan>  lstObjetivosCorportativo, Integer cantidadObjetivos, List<PsyImpactoAmbiental> lstImpactoAmbiental) throws Exception{
       
    	//Variables necesario para consultar la matriz de correlacion
    	List<PsyDetalleMatrizCorrelacionDTO> matrizRelacionPlan = new ArrayList<PsyDetalleMatrizCorrelacionDTO>();
    	List<PsyMatrizCorrelacion> matrizCorrelacion = new ArrayList<PsyMatrizCorrelacion>();
    	List<PsyMatrizCorrelacion> validaEstrategiasRepetidas = new ArrayList<PsyMatrizCorrelacion>();
    	List<PsyDetalleMatrizCorrelacionDTO>  matrizRelacion = new ArrayList<PsyDetalleMatrizCorrelacionDTO>();
    	List<PsyDetalleMapaEstrategico> lstDetalleActual = null;
    	List<PsyDetalleMapaEstrategico> lstDetalleSeleccionado = null;
    	
    	//Se recorre todos los objetivos corporativos seleccionados para el plan
    	for(PsyDetalleObjetivoPlan objetivo: lstObjetivosCorportativo){
    		validaEstrategiasRepetidas = new ArrayList<PsyMatrizCorrelacion>();
    		//Se crean las variables para buscar en los valores en la matriz correlacion, por cada objetivo
//    		Object[] variablesMatrizCorrelacion = { "psyObjetivoEstrategico.obesCodigo", false,
//    				objetivo.getPsyObjetivoEstrategico().getObesCodigo(), "=", "estadoRegistro", true,
//       			 "A", "="};    		
//    		matrizCorrelacion = logicPsyMatrizCorrelacion2.findByCriteria(variablesMatrizCorrelacion, null, null);
    		
    		
    		//Se consultan los objetivos-impactos que corresponden con los objetivos estrategicos seleccionados previamente en el plan estrategico
    		List<PsyObjetivoImpacto> correlacionObjetivoImpacto = psyDetalleMapaEstrategicoDAO.consultarObjetivoImpactoPorPlanEstrategico(mapaEstrategico.getPsyPlanEstrategico().getPestCodigo(), objetivo.getPsyObjetivoEstrategico().getObesCodigo());
    		//Se recorre la consulta de los objetivos-impactos que corresponden con el actual objetivo estrategico iterando para agregarla a la variable matrizCorrelacion
    		for (PsyObjetivoImpacto obim : correlacionObjetivoImpacto) {
    			List<PsyMatrizCorrelacion> matrizTemporal = psyDetalleMapaEstrategicoDAO.
    					consultarMatrizRelacionMapaEstrategico(
    							obim.getPsyObjetivoEstrategico().getObesCodigo(), 
    							obim.getPsyImpactoAmbiental().getImamCodigo());
    			for (PsyMatrizCorrelacion matriz : matrizTemporal) {
    				matrizCorrelacion.add(matriz);
    				validaEstrategiasRepetidas.add(matriz);
				}
			}
    		
    		PsyDetalleMatrizCorrelacionDTO  detalleMatriz = null;
    		
    		PsyImpactoAmbiental impactoAmbiental = null;
    		PsyEstrategiaAmbiental estrategiaAmbiental = null;   
    		List<PsyEstrategiaAmbiental> lstEstrategiaAmbiental = null; 
    		List<PsyEstrategiaAmbiental> lstSeleccionada = null; 
    		List<PsyEstrategiaAmbiental> lstEstrategiasPreseleccionadas = null;
    		
    		if(matrizCorrelacion != null && !matrizCorrelacion.isEmpty() && validaEstrategiasRepetidas != null && !validaEstrategiasRepetidas.isEmpty()){
    			   			
    			boolean entro =false;
    			for(PsyMatrizCorrelacion item: validaEstrategiasRepetidas){
    				lstEstrategiaAmbiental = new ArrayList<PsyEstrategiaAmbiental>();
    				lstEstrategiasPreseleccionadas = new ArrayList<PsyEstrategiaAmbiental>();
    				entro =false;
//        			for(PsyDetalleMatrizCorrelacionDTO itemAdicionado: matrizRelacionPlan){
//        				
//        				if(itemAdicionado.getInamCodigo() == item.getPsyImpactoAmbiental().getImamCodigo()        						){
//        					entro = true;
//           				}
//        			}
    				
    				if(entro == false){        			
    				impactoAmbiental = psyImpactoAmbientalLogic.getPsyImpactoAmbiental(item.getPsyImpactoAmbiental().getImamCodigo());
    				
    				//estrategiaAmbiental = psyEstrategiaAmbientalLogic.getPsyEstrategiaAmbiental(item.getPsyEstrategiaAmbiental().getEsamCodigo());
    				
    				for(PsyMatrizCorrelacion itemEstrategia: validaEstrategiasRepetidas){
    					
    						
    					
    					if(itemEstrategia.getPsyImpactoAmbiental().getImamCodigo() == item.getPsyImpactoAmbiental().getImamCodigo()){
    						
    						estrategiaAmbiental = new PsyEstrategiaAmbiental();
    						estrategiaAmbiental = psyEstrategiaAmbientalLogic.getPsyEstrategiaAmbiental(itemEstrategia.getPsyEstrategiaAmbiental().getEsamCodigo());    						
    						lstEstrategiaAmbiental.add(estrategiaAmbiental);
    					}
    					
    				}
    				
//    				TODO: PENDIENTE POR PROBAR
    				List<PsyMatrizCorrelacion> laMatrizDeCorrelacion = psyDetalleMapaEstrategicoDAO.consultaEstrategiasSeleccionadas(item.getPsyImpactoAmbiental(), item.getPsyObjetivoEstrategico(), mapaEstrategico);
    				for (PsyMatrizCorrelacion lstMatrizCorrelacion : laMatrizDeCorrelacion) {
    					lstEstrategiasPreseleccionadas.add(lstMatrizCorrelacion.getPsyEstrategiaAmbiental());
    				}
    				
    				detalleMatriz = new PsyDetalleMatrizCorrelacionDTO();
    				detalleMatriz.setObesCodigo(item.getPsyObjetivoEstrategico().getObesCodigo());
    				detalleMatriz.setInamCodigo(item.getPsyImpactoAmbiental().getImamCodigo());
    				detalleMatriz.setEsamCodigo(item.getPsyEstrategiaAmbiental().getEsamCodigo());
    				detalleMatriz.setMacoCodigo(item.getMacoCodigo());
    				detalleMatriz.setNombreObjetivoCorporativo(objetivo.getNombre());
    				detalleMatriz.setNombreImpactoAmbiental(impactoAmbiental.getNombre());
    				detalleMatriz.setDescripcionImpactoAmbiental(impactoAmbiental.getDescripcion());
    				detalleMatriz.setDescripcionObjetivoCorporativo(objetivo.getDescripcion());
    				detalleMatriz.setEstrategias(lstEstrategiaAmbiental);
    				detalleMatriz.setNombreEstrategia(estrategiaAmbiental.getNombre());
    				detalleMatriz.setDescripcionEstrategia(estrategiaAmbiental.getDescripcion());
    				detalleMatriz.setEstrategiasDataTable(lstEstrategiasPreseleccionadas);
    				
    				lstEstrategiaAmbiental=null;
    				
    				detalleMatriz.setBloquear(false);
    				
    				Object[] variablesDetalleMatriz = { "psyMapaEstrategico.maesCodigo", false,
    						mapaEstrategico.getMaesCodigo(), "=", "estadoRegistro", true,
    	       			 "A", "=", "psyMatrizCorrelacion.macoCodigo", false,
    	       			item.getMacoCodigo(), "="};
    				
    				lstDetalleActual = findByCriteria(variablesDetalleMatriz, null, null);
    				
    				if(lstDetalleActual!= null && !lstDetalleActual.isEmpty()){
    					
    					lstSeleccionada = new ArrayList<PsyEstrategiaAmbiental>();
    					
    						Object[] variablesDetalleMatriz2 = { "psyMapaEstrategico.maesCodigo", false,
    	    						mapaEstrategico.getMaesCodigo(), "=", "estadoRegistro", true,
    	    	       			 "A", "=", "psyMatrizCorrelacion.macoCodigo", false,
    	    	       			item.getMacoCodigo(), "=", "psyMatrizCorrelacion.macoCodigo", false,
    	    	       			item.getMacoCodigo(), "="};
    					
    						lstDetalleSeleccionado = findByCriteria(variablesDetalleMatriz2, null, null);
    						    	    	    		
    						if(lstDetalleSeleccionado!=null && !lstDetalleSeleccionado.isEmpty()){
    							lstSeleccionada.add(lstDetalleSeleccionado.get(0).getPsyMatrizCorrelacion().getPsyEstrategiaAmbiental());
    						}
    						if(lstDetalleSeleccionado.get(0).getTipo().trim().equals("T")){
    							detalleMatriz.setColorFondo("green");
    							detalleMatriz.setTrabajar(lstDetalleSeleccionado.get(0).getTipo().trim());
    						}else{
    							detalleMatriz.setColorFondo("yellow");
    							detalleMatriz.setTrabajar(lstDetalleSeleccionado.get(0).getTipo().trim());
    						}
    				}else{
    					detalleMatriz.setColorFondo("darkorange");
    					detalleMatriz.setTrabajar("N");
    				}
    				
    				matrizRelacionPlan.add(detalleMatriz);
    				lstEstrategiasPreseleccionadas=null;
    				detalleMatriz=null;
    				}
    			}
    			
    			
    		}
    	}
    	validaEstrategiasRepetidas=null;
    	
    	//Se crean n (n es dado por la constante CANTIDAD_OBJETIVOS) objetivos corporativos
    	if(lstObjetivosCorportativo!= null && !lstObjetivosCorportativo.isEmpty()){
//    		lstObjetivosCorportativo = new ArrayList<PsyDetalleObjetivoPlan>();
//    		lstObjetivoCorporativo = lstObjetivosCorporativos; // se colocan los objetivos encontrados
    		
    		int cantidadObjetivosActuales = lstObjetivosCorportativo.size();        		
    		PsyDetalleObjetivoPlan detalle = null;
    		
    		for(int i=cantidadObjetivosActuales; i< cantidadObjetivos; i++){ // Se adicionan objetivos vacios para los espacios restantes
    			detalle = new PsyDetalleObjetivoPlan();
    			detalle.setDescripcion("EL OBJETIVO NO FUE DEFINIDO");
    			detalle.setNombre("NO DEFINIDO");
    			detalle.setEstadoRegistro("A");
    			detalle.setDobpCodigo(Long.parseLong(i+""));
    			detalle.setCodigoObjetivoEstrategico("-1");
    			detalle.setPsyObjetivoEstrategico(null);
    			detalle.setPsyObjetivoPlan(null);    			
    			   			
    			lstObjetivosCorportativo.add(detalle);
    			
     		}
    		
    	}else{
    		 throw new ZMessManager().new FindingException(
    	                "No existen objetivos corporativos definidos para la empresa"); 
    	}
    	
    	boolean encontrado = false;
    	boolean colorSeteado = false;
    	PsyDetalleMatrizCorrelacionDTO  relacionMatriz = null;
    	List<PsyDetalleMatrizCorrelacionDTO> matrizTmp = null;
        //Se llena la matriz que hace referencia a las relaciones entre objetivo corporativo e impacto ambiental
    	for(PsyImpactoAmbiental impactoAmbiental: lstImpactoAmbiental){
    		
    		for(PsyDetalleObjetivoPlan objetivoCorporativo: lstObjetivosCorportativo){
    			
    			encontrado = false;    			
    			colorSeteado = false;
    			matrizTmp = null;
    			if(objetivoCorporativo!= null && objetivoCorporativo.getCodigoObjetivoEstrategico().equals("-1")){
    				relacionMatriz = new PsyDetalleMatrizCorrelacionDTO();        				
    				relacionMatriz.setObesCodigo(-1L);
    				relacionMatriz.setInamCodigo(-1L);;
    				relacionMatriz.setEsamCodigo(null);
    				relacionMatriz.setMacoCodigo(null);
    				relacionMatriz.setColorFondo(null);
    				relacionMatriz.setColorLetra("black");
    				relacionMatriz.setBloquear(true);
    				matrizRelacion.add(relacionMatriz);
    			}else{
    				
    				matrizTmp = new ArrayList<PsyDetalleMatrizCorrelacionDTO>();   
                    
                    for(PsyDetalleMatrizCorrelacionDTO relacion1: matrizRelacionPlan){
                        
                        if(impactoAmbiental.getImamCodigo().equals(relacion1.getInamCodigo()) &&
                                objetivoCorporativo.getPsyObjetivoEstrategico().getObesCodigo().equals(relacion1.getObesCodigo())){
                            matrizTmp.add(relacion1);
                            
                        }
                        
                    }
                    
                    relacionMatriz = new PsyDetalleMatrizCorrelacionDTO();
                    for(PsyDetalleMatrizCorrelacionDTO relacion: matrizTmp){
                            
                            encontrado = true;
                            if(colorSeteado==false){
                            	relacionMatriz.setColorFondo("darkorange");
                            	relacionMatriz.setTrabajar(relacion.getTrabajar());
                                
                            }
                                                    
                            relacionMatriz.setObesCodigo(objetivoCorporativo.getPsyObjetivoEstrategico().getObesCodigo());
                            relacionMatriz.setInamCodigo(impactoAmbiental.getImamCodigo());;
                            relacionMatriz.setEsamCodigo(relacion.getEsamCodigo());
                            relacionMatriz.setMacoCodigo(relacion.getMacoCodigo());
                            relacionMatriz.setColorLetra("black");
                            relacionMatriz.setNombreObjetivoCorporativo(relacion.getNombreObjetivoCorporativo());
                            relacionMatriz.setNombreImpactoAmbiental(relacion.getNombreImpactoAmbiental());
                            relacionMatriz.setDescripcionImpactoAmbiental(relacion.getDescripcionImpactoAmbiental());
                            relacionMatriz.setDescripcionObjetivoCorporativo(relacion.getDescripcionObjetivoCorporativo());
                            relacionMatriz.setNombreEstrategia(relacion.getNombreEstrategia());
                            relacionMatriz.setEstrategiasDataTable(relacion.getEstrategiasDataTable());
                            relacionMatriz.setDescripcionEstrategia(relacion.getDescripcionEstrategia());
                            relacionMatriz.setBloquear(false);
                            
                            
                            
                            relacionMatriz.setEstrategias(relacion.getEstrategias());
                            if(!relacion.getColorFondo().trim().equals("darkorange")){
                                relacionMatriz.setColorFondo(relacion.getColorFondo());
                                relacionMatriz.setTrabajar(relacion.getTrabajar());
                                colorSeteado=true;
                            }
                        
                        
                    }
                    if(encontrado==true){
                        matrizRelacion.add(relacionMatriz);
                        colorSeteado = false;
                    }
	
    			if(encontrado == false && objetivoCorporativo!= null && !objetivoCorporativo.getCodigoObjetivoEstrategico().equals("-1")){
    				relacionMatriz = new PsyDetalleMatrizCorrelacionDTO();        				
    				relacionMatriz.setObesCodigo(objetivoCorporativo.getPsyObjetivoEstrategico().getObesCodigo());
    				relacionMatriz.setInamCodigo(impactoAmbiental.getImamCodigo());;
    				relacionMatriz.setEsamCodigo(null);
    				relacionMatriz.setMacoCodigo(null);
    				relacionMatriz.setColorFondo(null);
    				relacionMatriz.setColorLetra("black");
    				relacionMatriz.setBloquear(true);
    				matrizRelacion.add(relacionMatriz);
    			}
    			
    			}
    		}
    		
    	}
    	
    	
    	
    	return matrizRelacion;
    	
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void guardarDetalleMapaEstrategico(PsyMapaEstrategico  mapaEstrategico, List<PsyDetalleMatrizCorrelacionDTO>  lstMatriz,  PsyImpactoAmbiental impactoAmbiental) throws Exception{
    	
    	PsyDetalleMapaEstrategico detalleMapaEstrategico = null;
    	PsyMatrizCorrelacion matrizCorrelacion = null;
    	List<PsyDetalleMatrizCorrelacionDTO> lstdetalleMatrizDefinitivo = new ArrayList<PsyDetalleMatrizCorrelacionDTO>();
    	List<PsyDetalleMapaEstrategico> lstConsultaMapaEstrategico = new ArrayList<PsyDetalleMapaEstrategico>();
    	
    	//Se consultan las matrices de correlacion que se encuentren en el detalle de mapa estrategico, por medio del codigo del impacto ambiental
    	lstConsultaMapaEstrategico = psyDetalleMapaEstrategicoDAO.consultaMatrizCorrelacionPorImpacto(impactoAmbiental, mapaEstrategico);
    	
//    	Object[] variablesDetalleMapaEstrategico = { "psyMapaEstrategico.maesCodigo", false,
//    			mapaEstrategico.getMaesCodigo(), "=", "estadoRegistro", true,
//   			 "A", "="};
//
//    	lstConsultaMapaEstrategico = findByCriteria(variablesDetalleMapaEstrategico, null, null);
    	
    	//Se eliminina cada registro
    	for(PsyDetalleMapaEstrategico detalle: lstConsultaMapaEstrategico){
    		deletePsyDetalleMapaEstrategico(detalle);
    	}
    	
    	//Se recorre la lista de la matriz, para indentificar los que fueron seleccionados y agregarlos a una lista definitiva
    	for(PsyDetalleMatrizCorrelacionDTO detalleMatriz: lstMatriz){
    	  if(detalleMatriz.getMacoCodigo() !=null && !detalleMatriz.getTrabajar().equals("N")){
    		  lstdetalleMatrizDefinitivo.add(detalleMatriz);
    	  }
    	}
    	
    	//Se recorre la lista definitiva para posteriormente guardar cada registro
    	for(PsyDetalleMatrizCorrelacionDTO detalleMatrizDefinitivo: lstdetalleMatrizDefinitivo){
    		for (PsyEstrategiaAmbiental estrategiaDetalle : detalleMatrizDefinitivo.getEstrategiasDataTable()) {
    			detalleMapaEstrategico = new PsyDetalleMapaEstrategico();
        		detalleMapaEstrategico.setEstadoRegistro("A");
        		detalleMapaEstrategico.setPsyMapaEstrategico(mapaEstrategico);
        		detalleMapaEstrategico.setTipo(detalleMatrizDefinitivo.getTrabajar().trim());
        		Object[] varibales = {"psyObjetivoEstrategico.obesCodigo",false,detalleMatrizDefinitivo.getObesCodigo(),"=",
        				"psyEstrategiaAmbiental.esamCodigo",false,estrategiaDetalle.getEsamCodigo(),"=",
        				"psyImpactoAmbiental.imamCodigo",false,impactoAmbiental.getImamCodigo(),"=",
        				"estadoRegistro",true,"A","="};
        		List<PsyMatrizCorrelacion> laMatrizDeCorrelacion = logicPsyMatrizCorrelacion2.findByCriteria(varibales, null, null);
        		matrizCorrelacion = laMatrizDeCorrelacion.get(0);
        		detalleMapaEstrategico.setPsyMatrizCorrelacion(matrizCorrelacion);
        		savePsyDetalleMapaEstrategico(detalleMapaEstrategico);
			}
    		
    	}
    	
    	
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PsyDetalleMapaEstrategico> consultarDetalleMapaEstrategico(PsyMapaEstrategico mapaEstrategico) throws Exception{
    	  
    	List<PsyDetalleMapaEstrategico> lstConsultaMapaEstrategico = new ArrayList<PsyDetalleMapaEstrategico>();
    	
    	//Se consulta el detalle mapa estrategico
    	Object[] variablesDetalleMapaEstrategico = { "psyMapaEstrategico.maesCodigo", false,
    			mapaEstrategico.getMaesCodigo(), "=", "estadoRegistro", true,
   			 "A", "="};

    	lstConsultaMapaEstrategico = findByCriteria(variablesDetalleMapaEstrategico, null, null);
    	
    	return  lstConsultaMapaEstrategico;    	
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PsyMatrizCorrelacion> consultaEstrategiasSeleccionadas(PsyDetalleMatrizCorrelacionDTO relacionSeleccionada, PsyMapaEstrategico mapaEstrategico) throws Exception {
    	if(relacionSeleccionada==null){
    		return null;
    	}else if(mapaEstrategico==null){
    		return null;
    	}else {
    		return psyDetalleMapaEstrategicoDAO.consultaEstrategiasSeleccionadas(relacionSeleccionada, mapaEstrategico);
    	}
    	
    }
    
    
}
