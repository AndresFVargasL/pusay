package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.exceptions.ZMessManager.DeletingException;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyEvaluarIndicadoresDTO;
import com.vortexbird.pusay.modelo.dto.PsyObjetivoAmbientalDTO;
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
@Service("PsyObjetivoAmbientalLogic")
public class PsyObjetivoAmbientalLogic implements IPsyObjetivoAmbientalLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyObjetivoAmbientalLogic.class);

    /**
     * DAO injected by Spring that manages PsyObjetivoAmbiental entities
     *
     */
    @Autowired
    private IPsyObjetivoAmbientalDAO psyObjetivoAmbientalDAO;

    /**
    * DAO injected by Spring that manages PsyEvaluacionPeaObjetivo entities
    *
    */
    @Autowired
    private IPsyEvaluacionPeaObjetivoDAO psyEvaluacionPeaObjetivoDAO;

    /**
    * DAO injected by Spring that manages PsyIndicador entities
    *
    */
    @Autowired
    private IPsyImpactoObjetivoDAO psyImpactoObjetivoDAO;

    /**
    * DAO injected by Spring that manages PsyIndicador entities
    *
    */
    @Autowired
    private IPsyIndicadorDAO psyIndicadorDAO;
    
    @Autowired
    private IPsyPlanEstrategicoAmbientalDAO psyPlanEstrategicoAmbientalDAO;
    
    @Autowired
    private IPsyIndicadorLogic psyIndicadorLogic;

    @Transactional(readOnly = true)
    public List<PsyObjetivoAmbiental> getPsyObjetivoAmbiental()
        throws Exception {
        log.debug("finding all PsyObjetivoAmbiental instances");

        List<PsyObjetivoAmbiental> list = new ArrayList<PsyObjetivoAmbiental>();

        try {
            list = psyObjetivoAmbientalDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyObjetivoAmbiental failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyObjetivoAmbiental");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyObjetivoAmbiental(PsyObjetivoAmbiental entity)
        throws Exception {
        log.debug("saving PsyObjetivoAmbiental instance");

        try {


            if (entity.getDescripcion() == null) {
                throw new ZMessManager().new EmptyFieldException("descripcion");
            }

            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcion");
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

            psyObjetivoAmbientalDAO.save(entity);

            log.debug("save PsyObjetivoAmbiental successful");
        } catch (Exception e) {
            log.error("save PsyObjetivoAmbiental failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyObjetivoAmbiental(PsyObjetivoAmbiental entity)
        throws Exception {
        log.debug("deleting PsyObjetivoAmbiental instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "PsyObjetivoAmbiental");
        }

        if (entity.getCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("codigo");
        }

        List<PsyEvaluacionPeaObjetivo> psyEvaluacionPeaObjetivos = null;
        List<PsyImpactoObjetivo> psyImpactoObjetivos = null;
        List<PsyIndicador> psyIndicadors = null;

        try {
            psyEvaluacionPeaObjetivos = psyEvaluacionPeaObjetivoDAO.findByProperty("psyObjetivoAmbiental.codigo",
                    entity.getCodigo());

            if (Utilities.validationsList(psyEvaluacionPeaObjetivos) == true) {
                throw new ZMessManager().new DeletingException(
                    "psyEvaluacionPeaObjetivos");
            }

            psyImpactoObjetivos = psyImpactoObjetivoDAO.findByProperty("psyObjetivoAmbiental.codigo",
                    entity.getCodigo());

            if (Utilities.validationsList(psyImpactoObjetivos) == true) {
                throw new ZMessManager().new DeletingException(
                    "psyImpactoObjetivos");
            }

            psyIndicadors = psyIndicadorDAO.findByProperty("psyObjetivoAmbiental.codigo",
                    entity.getCodigo());

            if (Utilities.validationsList(psyIndicadors) == true) {
                throw new ZMessManager().new DeletingException("psyIndicadors");
            }

            psyObjetivoAmbientalDAO.delete(entity);

            log.debug("delete PsyObjetivoAmbiental successful");
        } catch (Exception e) {
            log.error("delete PsyObjetivoAmbiental failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyObjetivoAmbiental(PsyObjetivoAmbiental entity)
        throws Exception {
        log.debug("updating PsyObjetivoAmbiental instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "PsyObjetivoAmbiental");
            }

            if (entity.getDescripcion() == null) {
                throw new ZMessManager().new EmptyFieldException("descripcion");
            }

            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcion");
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

            psyObjetivoAmbientalDAO.update(entity);

            log.debug("update PsyObjetivoAmbiental successful");
        } catch (Exception e) {
            log.error("update PsyObjetivoAmbiental failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyObjetivoAmbientalDTO> getDataPsyObjetivoAmbiental()
        throws Exception {
        try {
        	
        	
            List<PsyObjetivoAmbiental> psyObjetivoAmbiental = psyObjetivoAmbientalDAO.findAll();

            List<PsyObjetivoAmbientalDTO> psyObjetivoAmbientalDTO = new ArrayList<PsyObjetivoAmbientalDTO>();

            for (PsyObjetivoAmbiental psyObjetivoAmbientalTmp : psyObjetivoAmbiental) {
                PsyObjetivoAmbientalDTO psyObjetivoAmbientalDTO2 = new PsyObjetivoAmbientalDTO();

                psyObjetivoAmbientalDTO2.setCodigo(psyObjetivoAmbientalTmp.getCodigo());
                psyObjetivoAmbientalDTO2.setDescripcion((psyObjetivoAmbientalTmp.getDescripcion() != null)
                    ? psyObjetivoAmbientalTmp.getDescripcion() : null);
                psyObjetivoAmbientalDTO2.setEstadoRegistro((psyObjetivoAmbientalTmp.getEstadoRegistro() != null)
                    ? (psyObjetivoAmbientalTmp.getEstadoRegistro().trim().equals("A")) ? "Activo" : "Inactivo" : null);
                psyObjetivoAmbientalDTO.add(psyObjetivoAmbientalDTO2);
            }

            return psyObjetivoAmbientalDTO;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Transactional(readOnly = true)
    public List<PsyObjetivoAmbientalDTO> getDataPsyObjetivoAmbientalEvaluado(PsyEmpresa empresa)
        throws Exception {
        try {
        	
        	List<PsyObjetivoAmbiental> psyObjetivoAmbientalSeleccionado = psyObjetivoAmbientalDAO.consultaObjetivosAmbientalesSeleccionados(empresa);
            List<PsyObjetivoAmbiental> psyObjetivoAmbiental = psyObjetivoAmbientalDAO.findAll();
            List<PsyPlanEstrategicoAmbiental> planActivo = psyPlanEstrategicoAmbientalDAO.getPEA(empresa);

            List<PsyObjetivoAmbientalDTO> psyObjetivoAmbientalDTO = new ArrayList<PsyObjetivoAmbientalDTO>();

            for (PsyObjetivoAmbiental psyObjetivoAmbientalTmp : psyObjetivoAmbiental) {
                PsyObjetivoAmbientalDTO psyObjetivoAmbientalDTO2 = new PsyObjetivoAmbientalDTO();
                boolean hayIndicadores=false;
                Long indicadores=0L;
                Long total=0L;
                Double porcentaje=0D;

                psyObjetivoAmbientalDTO2.setCodigo(psyObjetivoAmbientalTmp.getCodigo());
                psyObjetivoAmbientalDTO2.setDescripcion((psyObjetivoAmbientalTmp.getDescripcion() != null)
                    ? psyObjetivoAmbientalTmp.getDescripcion() : null);
                psyObjetivoAmbientalDTO2.setEstadoRegistro((psyObjetivoAmbientalTmp.getEstadoRegistro() != null)
                    ? (psyObjetivoAmbientalTmp.getEstadoRegistro().trim().equals("A")) ? "Activo" : "Inactivo" : null);
                for (PsyObjetivoAmbiental psyObjetivoAmbientalTmp2 : psyObjetivoAmbientalSeleccionado) {
					if(psyObjetivoAmbientalTmp.getDescripcion().trim().equals(psyObjetivoAmbientalTmp2.getDescripcion().trim())){
						psyObjetivoAmbientalDTO2.setDisabled(false);
					}
				}
                
                List<PsyEvaluarIndicadoresDTO> indicadoresDelObjetivo = psyIndicadorLogic.
                		getDataPsyIndicadorObam(empresa, psyObjetivoAmbientalTmp.getCodigo(), planActivo.get(0));
                hayIndicadores = (indicadoresDelObjetivo.size() > 0) ? true : false;
                total = Long.parseLong(indicadoresDelObjetivo.size()+"");		
                if(hayIndicadores==true){


                	for (PsyEvaluarIndicadoresDTO psyEvaluarIndicadoresDTO : indicadoresDelObjetivo) {
                		if(psyEvaluarIndicadoresDTO.getColorEvaluacion().trim().equals("green")){
                			indicadores++;
                		}
                	}

                	porcentaje = ((indicadores.doubleValue() / total.doubleValue()) * 100);

                }
                
                if(porcentaje >= 0 && porcentaje < 49){
                	psyObjetivoAmbientalDTO2.setColorObjetivo("red");
                }
                if(porcentaje >= 50 && porcentaje < 79){
                	psyObjetivoAmbientalDTO2.setColorObjetivo("yellow");
                }
                if(porcentaje >= 80 && porcentaje <= 100){
                	psyObjetivoAmbientalDTO2.setColorObjetivo("green");
                }
                
                psyObjetivoAmbientalDTO.add(psyObjetivoAmbientalDTO2);
                
            }

            return psyObjetivoAmbientalDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyObjetivoAmbiental getPsyObjetivoAmbiental(Long codigo)
        throws Exception {
        log.debug("getting PsyObjetivoAmbiental instance");

        PsyObjetivoAmbiental entity = null;

        try {
            entity = psyObjetivoAmbientalDAO.findById(codigo);
        } catch (Exception e) {
            log.error("get PsyObjetivoAmbiental failed", e);
            throw new ZMessManager().new FindingException(
                "PsyObjetivoAmbiental");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyObjetivoAmbiental> findPagePsyObjetivoAmbiental(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<PsyObjetivoAmbiental> entity = null;

        try {
            entity = psyObjetivoAmbientalDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyObjetivoAmbiental Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyObjetivoAmbiental() throws Exception {
        Long entity = null;

        try {
            entity = psyObjetivoAmbientalDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyObjetivoAmbiental Count");
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
    public List<PsyObjetivoAmbiental> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyObjetivoAmbiental> list = new ArrayList<PsyObjetivoAmbiental>();
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
            list = psyObjetivoAmbientalDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
    @Transactional(readOnly = true)
    public boolean validarGrupoRepetido(List<PsyDetalleObjetivoPlan> lstObjetivoCorporativo) throws Exception{
    	
    	//Se recorre toda la lista para comparar y validar si existen  objetivos estrategicos con grupo repetido
    	for(PsyDetalleObjetivoPlan objetivo: lstObjetivoCorporativo){
    		
    		//Solo se toman en cuenta los objetivos que no son vacios
    		if(objetivo.getCodigoObjetivoEstrategico() != null && !objetivo.getCodigoObjetivoEstrategico().equals("-1") ){
    			//Se compara con cada uno de los objetivos
    			for(PsyDetalleObjetivoPlan objetivoComparar: lstObjetivoCorporativo){
    				//Devuelve verdadero si encuentra un objetivo repetido
    				if(objetivo.getCodigoObjetivoEstrategico()!= objetivoComparar.getCodigoObjetivoEstrategico() 
    						&& objetivo.getCodigoObjetivoEstrategico().equals(objetivoComparar.getCodigoObjetivoEstrategico())){
    					return true;
    				}
    				
    			}
    			
    		}
    		
    		
    	}
    	
    	return false;
    	
    }
}
