package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyEvaluarIndicadoresDTO;
import com.vortexbird.pusay.modelo.dto.PsyIndicadorDTO;
import com.vortexbird.pusay.modelo.dto.PsyIndicadorGestionDTO;
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
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("PsyIndicadorLogic")
public class PsyIndicadorLogic implements IPsyIndicadorLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyIndicadorLogic.class);

    /**
     * DAO injected by Spring that manages PsyIndicador entities
     *
     */
    @Autowired
    private IPsyIndicadorDAO psyIndicadorDAO;

    /**
    * DAO injected by Spring that manages PsyEvaluacionPeaIndicador entities
    *
    */
    @Autowired
    private IPsyEvaluacionPeaIndicadorDAO psyEvaluacionPeaIndicadorDAO;

    /**
    * Logic injected by Spring that manages PsyObjetivoAmbiental entities
    *
    */
    @Autowired
    IPsyObjetivoAmbientalLogic logicPsyObjetivoAmbiental1;

    /**
    * Logic injected by Spring that manages PsySubtema entities
    *
    */
    @Autowired
    IPsySubtemaLogic logicPsySubtema2;
    
    @Autowired
    private IPsyEvaluacionPeaIndicadorLogic logicEvaluacionPeaIndicador3;
    
    @Autowired
    private IPsyEvaluacionPeaObjetivoLogic logicEvaluacionPeaObjetivo4;

    @Transactional(readOnly = true)
    public List<PsyIndicador> getPsyIndicador() throws Exception {
        log.debug("finding all PsyIndicador instances");

        List<PsyIndicador> list = new ArrayList<PsyIndicador>();

        try {
            list = psyIndicadorDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyIndicador failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyIndicador");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyIndicador(PsyIndicador entity) throws Exception {
        log.debug("saving PsyIndicador instance");

        try {
            if (entity.getPsyObjetivoAmbiental() == null) {
            	throw new ZMessManager("Seleccione un objetivo ambiental de la lista porfavor");
            }

            if (entity.getPsySubtema() == null) {
                throw new ZMessManager("Seleccione un subtema de la lista porfavor");
            }

            if (entity.getDescripcion() == null) {
                throw new ZMessManager().new EmptyFieldException("Descripcion");
            }

            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Descripcion");
            }

            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Estado Registro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Estado Registro");
            }

            if (entity.getTipoIndicador() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Tipo de Indicador");
            }

            if ((entity.getTipoIndicador() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getTipoIndicador(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Tipo de Indicador");
            }

            if (entity.getUnidadMedida() == null) {
                throw new ZMessManager().new EmptyFieldException("Unidad de Medida");
            }

            if ((entity.getUnidadMedida() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUnidadMedida(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Unidad de Medida");
            }

            if (entity.getPsyObjetivoAmbiental().getCodigo() == null) {
            	throw new ZMessManager("Seleccione un objetivo ambiental de la lista porfavor");
            }

            if (entity.getPsySubtema().getCodigo() == null) {
            	throw new ZMessManager("Seleccione un subtema de la lista porfavor");
            }

            psyIndicadorDAO.save(entity);

            log.debug("save PsyIndicador successful");
        } catch (Exception e) {
            log.error("save PsyIndicador failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyIndicador(PsyIndicador entity)
        throws Exception {
        log.debug("deleting PsyIndicador instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Indicador");
        }

        if (entity.getCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("Codigo");
        }

        List<PsyEvaluacionPeaIndicador> psyEvaluacionPeaIndicadors = null;

        try {
            psyEvaluacionPeaIndicadors = psyEvaluacionPeaIndicadorDAO.findByProperty("psyIndicador.codigo",
                    entity.getCodigo());

            if (Utilities.validationsList(psyEvaluacionPeaIndicadors) == true) {
                throw new ZMessManager().new DeletingException(
                    "Evaluacion de Indicadores");
            }

            psyIndicadorDAO.delete(entity);

            log.debug("delete PsyIndicador successful");
        } catch (Exception e) {
            log.error("delete PsyIndicador failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyIndicador(PsyIndicador entity)
        throws Exception {
        log.debug("updating PsyIndicador instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Indicador");
            }

            if (entity.getPsyObjetivoAmbiental() == null) {
            	throw new ZMessManager("Seleccione un objetivo ambiental de la lista porfavor");
            }

            if (entity.getPsySubtema() == null) {
            	throw new ZMessManager("Seleccione un subtema de la lista porfavor");
            }

            if (entity.getCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("Codigo");
            }

            if (entity.getDescripcion() == null) {
                throw new ZMessManager().new EmptyFieldException("Descripcion");
            }

            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Descripcion");
            }

            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Estado Registro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Estado Registro");
            }

            if (entity.getTipoIndicador() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Tipo de Indicador");
            }

            if ((entity.getTipoIndicador() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getTipoIndicador(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Tipo de Indicador");
            }

            if (entity.getUnidadMedida() == null) {
                throw new ZMessManager().new EmptyFieldException("Unidad de Medida");
            }

            if ((entity.getUnidadMedida() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUnidadMedida(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Unidad de Medida");
            }

            if (entity.getPsyObjetivoAmbiental().getCodigo() == null) {
            	throw new ZMessManager("Seleccione un objetivo ambiental de la lista porfavor");
            }

            if (entity.getPsySubtema().getCodigo() == null) {
            	throw new ZMessManager("Seleccione un subtema de la lista porfavor");
            }

            psyIndicadorDAO.update(entity);

            log.debug("update PsyIndicador successful");
        } catch (Exception e) {
            log.error("update PsyIndicador failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyEvaluarIndicadoresDTO> getDataPsyIndicador(PsyEmpresa empresa, Long imamCodigo, PsyPlanEstrategicoAmbiental planEstrategicoAmbiental)
        throws Exception {
        try {
        	
            List<PsyEvaluarIndicadoresDTO> psyIndicador = psyIndicadorDAO.getDataIndiCadoresByEmpresaByImam(empresa,imamCodigo,planEstrategicoAmbiental);

            List<PsyEvaluarIndicadoresDTO> psyIndicadorDTO = new ArrayList<PsyEvaluarIndicadoresDTO>();

            for (PsyEvaluarIndicadoresDTO psyIndicadorTmp : psyIndicador) {
            	PsyEvaluarIndicadoresDTO psyEvaluarIndicadoresDTO2 = new PsyEvaluarIndicadoresDTO();

            	psyEvaluarIndicadoresDTO2.setIndiCodigo((psyIndicadorTmp.getIndiCodigo() != null) ? psyIndicadorTmp.getIndiCodigo() : null);
            	
            	psyEvaluarIndicadoresDTO2.setObamCodigo((psyIndicadorTmp.getObamCodigo() != null) ? psyIndicadorTmp.getObamCodigo() : null);
            	
            	psyEvaluarIndicadoresDTO2.setObamDescripcion((psyIndicadorTmp.getObamDescripcion() != null) ? psyIndicadorTmp.getObamDescripcion() : null);
            	
            	psyEvaluarIndicadoresDTO2.setSubteCodigo((psyIndicadorTmp.getSubteCodigo() != null) ? psyIndicadorTmp.getSubteCodigo() : null);
            	
            	psyEvaluarIndicadoresDTO2.setSubTemaDescripcion((psyIndicadorTmp.getSubTemaDescripcion() != null) ? psyIndicadorTmp.getSubTemaDescripcion() : null);
            	
            	psyEvaluarIndicadoresDTO2.setTemaDescripcion((psyIndicadorTmp.getTemaDescripcion() != null) ? psyIndicadorTmp.getTemaDescripcion() : null);
            	
            	psyEvaluarIndicadoresDTO2.setIndiDescripcion((psyIndicadorTmp.getIndiDescripcion() != null) ? psyIndicadorTmp.getIndiDescripcion() : null);
            	
            	psyEvaluarIndicadoresDTO2.setIndiUnidadMedida((psyIndicadorTmp.getIndiUnidadMedida() != null) ? psyIndicadorTmp.getIndiUnidadMedida() : null);
            	
            	psyEvaluarIndicadoresDTO2.setTipoIndicador((psyIndicadorTmp.getTipoIndicador() != null)
                        ? (psyIndicadorTmp.getTipoIndicador().trim().equals("M")) ? "Multiple" : "No Aplica" : null);
            	
            	psyEvaluarIndicadoresDTO2.setIndiEstadoRegistro((psyIndicadorTmp.getIndiEstadoRegistro() != null) ? 
            			psyIndicadorTmp.getIndiEstadoRegistro().trim() : null);
            	
            	psyEvaluarIndicadoresDTO2.setEvaCodigo((psyIndicadorTmp.getEvaCodigo() != null) ? psyIndicadorTmp.getEvaCodigo() : null);
            	
            	psyEvaluarIndicadoresDTO2.setEvaIndiCodigo((psyIndicadorTmp.getEvaIndiCodigo() != null) ? psyIndicadorTmp.getEvaIndiCodigo() : null);
            	
            	psyEvaluarIndicadoresDTO2.setPeaCodigo((psyIndicadorTmp.getPeaCodigo() != null) ? psyIndicadorTmp.getPeaCodigo() : null);
            	
            	psyEvaluarIndicadoresDTO2.setMultiple((psyIndicadorTmp.getMultiple() != null) ? psyIndicadorTmp.getMultiple().trim() : null);
            	
            	psyEvaluarIndicadoresDTO2.setPeriodo((psyIndicadorTmp.getPeriodo() != null) ? psyIndicadorTmp.getPeriodo() : null);
            	
            	psyEvaluarIndicadoresDTO2.setResultado((psyIndicadorTmp.getResultado() != null) ? psyIndicadorTmp.getResultado() : null);
            	
            	psyEvaluarIndicadoresDTO2.setMeta((psyIndicadorTmp.getMeta() != null) ? psyIndicadorTmp.getMeta() : null);
            	
            	psyEvaluarIndicadoresDTO2.setHistorial((psyIndicadorTmp.getHistorial() != null) ? psyIndicadorTmp.getHistorial() : null);
            	
            	psyEvaluarIndicadoresDTO2.setNorma((psyIndicadorTmp.getNorma() != null) ? psyIndicadorTmp.getNorma() : null);
            	
            	psyEvaluarIndicadoresDTO2.setSectorial((psyIndicadorTmp.getSectorial() != null) ? psyIndicadorTmp.getSectorial() : null);
            	
            	psyEvaluarIndicadoresDTO2.setEvaEstadoRegistro((psyIndicadorTmp.getEvaEstadoRegistro() != null) ? 
            			psyIndicadorTmp.getEvaEstadoRegistro() : null);
            	
            	if(psyIndicadorTmp.getResultado() != null && psyIndicadorTmp.getMeta() != null){

            		if(psyIndicadorTmp.getResultado() < psyIndicadorTmp.getMeta()){
            			
            			psyEvaluarIndicadoresDTO2.setColorEvaluacion("red");
            			psyEvaluarIndicadoresDTO2.setColorLetra("white");
            			
            		}else{
            			
            			if(psyIndicadorTmp.getResultado() >= psyIndicadorTmp.getMeta()){

            				psyEvaluarIndicadoresDTO2.setColorEvaluacion("green");
            				psyEvaluarIndicadoresDTO2.setColorLetra("white");

            			}
            		}

            	}

                psyIndicadorDTO.add(psyEvaluarIndicadoresDTO2);
            }

            return psyIndicadorDTO;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Transactional(readOnly = true)
    public List<PsyIndicadorGestionDTO> getDataPsyIndicadorGestion()
        throws Exception {
        try {
            List<PsyIndicador> psyIndicador = psyIndicadorDAO.findAll();

            List<PsyIndicadorGestionDTO> psyIndicadorDTO = new ArrayList<PsyIndicadorGestionDTO>();

            for (PsyIndicador psyIndicadorTmp : psyIndicador) {
            	PsyIndicadorGestionDTO psyIndicadorDTO2 = new PsyIndicadorGestionDTO();

                psyIndicadorDTO2.setCodigo(psyIndicadorTmp.getCodigo());
                psyIndicadorDTO2.setDescripcion((psyIndicadorTmp.getDescripcion() != null)
                    ? psyIndicadorTmp.getDescripcion() : null);
                psyIndicadorDTO2.setEstadoRegistro((psyIndicadorTmp.getEstadoRegistro() != null)
                    ? (psyIndicadorTmp.getEstadoRegistro().trim().equals("A")) ? "Activo" : "Inactivo" : null);
                psyIndicadorDTO2.setTipoIndicador((psyIndicadorTmp.getTipoIndicador() != null)
                    ? (psyIndicadorTmp.getTipoIndicador().trim().equals("M")) ? "Multiple" : "No Aplica" : null);
                psyIndicadorDTO2.setUnidadMedida((psyIndicadorTmp.getUnidadMedida() != null)
                    ? psyIndicadorTmp.getUnidadMedida() : null);
                psyIndicadorDTO2.setCodigo_PsyObjetivoAmbiental((psyIndicadorTmp.getPsyObjetivoAmbiental()
                                                                                .getCodigo() != null)
                    ? psyIndicadorTmp.getPsyObjetivoAmbiental().getCodigo() : null);
                
                psyIndicadorDTO2.setObamNombre((psyIndicadorTmp.getPsyObjetivoAmbiental()
                        .getCodigo() != null)
                    ? psyIndicadorTmp.getPsyObjetivoAmbiental().getDescripcion() : null);
                
                psyIndicadorDTO2.setCodigo_PsySubtema((psyIndicadorTmp.getPsySubtema()
                        .getCodigo() != null)
                    ? psyIndicadorTmp.getPsySubtema().getCodigo() : null);
                
                psyIndicadorDTO2.setSubTema((psyIndicadorTmp.getPsySubtema()
                                                                      .getCodigo() != null)
                    ? psyIndicadorTmp.getPsySubtema().getDescripcion() : null);
                
                psyIndicadorDTO2.setCodigoTema((psyIndicadorTmp.getPsySubtema()
                        .getCodigo() != null)
                    ? psyIndicadorTmp.getPsySubtema().getPsyTema().getCodigo() : null);
                
                psyIndicadorDTO2.setTema((psyIndicadorTmp.getPsySubtema()
                        .getCodigo() != null)
                    ? psyIndicadorTmp.getPsySubtema().getPsyTema().getDescripcion() : null);
                
                psyIndicadorDTO.add(psyIndicadorDTO2);
            }

            return psyIndicadorDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyIndicador getPsyIndicador(Long codigo) throws Exception {
        log.debug("getting PsyIndicador instance");

        PsyIndicador entity = null;

        try {
            entity = psyIndicadorDAO.findById(codigo);
        } catch (Exception e) {
            log.error("get PsyIndicador failed", e);
            throw new ZMessManager().new FindingException("PsyIndicador");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyIndicador> findPagePsyIndicador(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<PsyIndicador> entity = null;

        try {
            entity = psyIndicadorDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("PsyIndicador Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyIndicador() throws Exception {
        Long entity = null;

        try {
            entity = psyIndicadorDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("PsyIndicador Count");
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
    public List<PsyIndicador> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyIndicador> list = new ArrayList<PsyIndicador>();
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
            list = psyIndicadorDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
    @Transactional(readOnly = true)
    public List<PsySubtema> consultarSubTemasPorTema(Long temaCodigo) throws Exception {
    	List<PsySubtema> subTemasPorTema;

        try {
        	
        	Object[] variables = {"psyTema.codigo",false,temaCodigo,"=","estadoRegistro",true,"A","="};
        	subTemasPorTema = logicPsySubtema2.findByCriteria(variables, null, null);
        	
        } catch (Exception e) {
            throw e;
        }
        
        return subTemasPorTema;
        
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void evaluarIndicador(PsyEvaluacionPeaIndicador evaIndicador, PsyEvaluacionPeaObjetivo evaObjetivo) throws Exception {
    	
    	if(evaIndicador == null){
    		throw new ZMessManager("Porfavor diligencia todos los campos para evaluar el indicador ambiental");
    	}
    	if(evaObjetivo == null){
    		throw new ZMessManager("Porfavor diligencia todos los campos para evaluar el objetivo ambiental");
    	}
    	if(evaIndicador.getCodigo() != null){
    	logicEvaluacionPeaIndicador3.updatePsyEvaluacionPeaIndicador(evaIndicador);
    	}else{
    	logicEvaluacionPeaIndicador3.savePsyEvaluacionPeaIndicador(evaIndicador);
    	}
    	logicEvaluacionPeaObjetivo4.savePsyEvaluacionPeaObjetivo(evaObjetivo);
    	
    }
    
    @Transactional(readOnly = true)
    public List<PsyEvaluarIndicadoresDTO> getDataPsyIndicadorObam(PsyEmpresa empresa, Long obamCodigo, PsyPlanEstrategicoAmbiental planEstrategicoAmbiental)
        throws Exception {
        try {
        	
            List<PsyEvaluarIndicadoresDTO> psyIndicador = psyIndicadorDAO.getDataIndiCadoresByEmpresaByObam(empresa,obamCodigo,planEstrategicoAmbiental);

            List<PsyEvaluarIndicadoresDTO> psyIndicadorDTO = new ArrayList<PsyEvaluarIndicadoresDTO>();

            for (PsyEvaluarIndicadoresDTO psyIndicadorTmp : psyIndicador) {
            	PsyEvaluarIndicadoresDTO psyEvaluarIndicadoresDTO2 = new PsyEvaluarIndicadoresDTO();

            	psyEvaluarIndicadoresDTO2.setIndiCodigo((psyIndicadorTmp.getIndiCodigo() != null) ? psyIndicadorTmp.getIndiCodigo() : null);
            	
            	psyEvaluarIndicadoresDTO2.setObamCodigo((psyIndicadorTmp.getObamCodigo() != null) ? psyIndicadorTmp.getObamCodigo() : null);
            	
            	psyEvaluarIndicadoresDTO2.setObamDescripcion((psyIndicadorTmp.getObamDescripcion() != null) ? psyIndicadorTmp.getObamDescripcion() : null);
            	
            	psyEvaluarIndicadoresDTO2.setSubteCodigo((psyIndicadorTmp.getSubteCodigo() != null) ? psyIndicadorTmp.getSubteCodigo() : null);
            	
            	psyEvaluarIndicadoresDTO2.setSubTemaDescripcion((psyIndicadorTmp.getSubTemaDescripcion() != null) ? psyIndicadorTmp.getSubTemaDescripcion() : null);
            	
            	psyEvaluarIndicadoresDTO2.setTemaDescripcion((psyIndicadorTmp.getTemaDescripcion() != null) ? psyIndicadorTmp.getTemaDescripcion() : null);
            	
            	psyEvaluarIndicadoresDTO2.setIndiDescripcion((psyIndicadorTmp.getIndiDescripcion() != null) ? psyIndicadorTmp.getIndiDescripcion() : null);
            	
            	psyEvaluarIndicadoresDTO2.setIndiUnidadMedida((psyIndicadorTmp.getIndiUnidadMedida() != null) ? psyIndicadorTmp.getIndiUnidadMedida() : null);
            	
            	psyEvaluarIndicadoresDTO2.setTipoIndicador((psyIndicadorTmp.getTipoIndicador() != null)
                        ? (psyIndicadorTmp.getTipoIndicador().trim().equals("M")) ? "Multiple" : "No Aplica" : null);
            	
            	psyEvaluarIndicadoresDTO2.setIndiEstadoRegistro((psyIndicadorTmp.getIndiEstadoRegistro() != null) ? 
            			psyIndicadorTmp.getIndiEstadoRegistro().trim() : null);
            	
            	psyEvaluarIndicadoresDTO2.setEvaCodigo((psyIndicadorTmp.getEvaCodigo() != null) ? psyIndicadorTmp.getEvaCodigo() : null);
            	
            	psyEvaluarIndicadoresDTO2.setEvaIndiCodigo((psyIndicadorTmp.getEvaIndiCodigo() != null) ? psyIndicadorTmp.getEvaIndiCodigo() : null);
            	
            	psyEvaluarIndicadoresDTO2.setPeaCodigo((psyIndicadorTmp.getPeaCodigo() != null) ? psyIndicadorTmp.getPeaCodigo() : null);
            	
            	psyEvaluarIndicadoresDTO2.setMultiple((psyIndicadorTmp.getMultiple() != null) ? psyIndicadorTmp.getMultiple().trim() : null);
            	
            	psyEvaluarIndicadoresDTO2.setPeriodo((psyIndicadorTmp.getPeriodo() != null) ? psyIndicadorTmp.getPeriodo() : null);
            	
            	psyEvaluarIndicadoresDTO2.setResultado((psyIndicadorTmp.getResultado() != null) ? psyIndicadorTmp.getResultado() : null);
            	
            	psyEvaluarIndicadoresDTO2.setMeta((psyIndicadorTmp.getMeta() != null) ? psyIndicadorTmp.getMeta() : null);
            	
            	psyEvaluarIndicadoresDTO2.setHistorial((psyIndicadorTmp.getHistorial() != null) ? psyIndicadorTmp.getHistorial() : null);
            	
            	psyEvaluarIndicadoresDTO2.setNorma((psyIndicadorTmp.getNorma() != null) ? psyIndicadorTmp.getNorma() : null);
            	
            	psyEvaluarIndicadoresDTO2.setSectorial((psyIndicadorTmp.getSectorial() != null) ? psyIndicadorTmp.getSectorial() : null);
            	
            	psyEvaluarIndicadoresDTO2.setEvaEstadoRegistro((psyIndicadorTmp.getEvaEstadoRegistro() != null) ? 
            			psyIndicadorTmp.getEvaEstadoRegistro() : null);
            	
            	if(psyIndicadorTmp.getResultado() != null && psyIndicadorTmp.getMeta() != null){

            		if(psyIndicadorTmp.getResultado() < psyIndicadorTmp.getMeta()){
            			
            			psyEvaluarIndicadoresDTO2.setColorEvaluacion("red");
            			psyEvaluarIndicadoresDTO2.setColorLetra("white");
            			
            		}else{
            			
            			if(psyIndicadorTmp.getResultado() >= psyIndicadorTmp.getMeta()){

            				psyEvaluarIndicadoresDTO2.setColorEvaluacion("green");
            				psyEvaluarIndicadoresDTO2.setColorLetra("white");

            			}
            		}

            	}

                psyIndicadorDTO.add(psyEvaluarIndicadoresDTO2);
            }

            return psyIndicadorDTO;
        } catch (Exception e) {
            throw e;
        }
    }
    
}
