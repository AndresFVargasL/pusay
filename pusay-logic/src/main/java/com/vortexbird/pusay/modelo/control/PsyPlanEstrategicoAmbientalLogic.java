package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.exceptions.ZMessManager.DeletingException;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyPlanEstrategicoAmbientalDTO;
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
@Service("PsyPlanEstrategicoAmbientalLogic")
public class PsyPlanEstrategicoAmbientalLogic
    implements IPsyPlanEstrategicoAmbientalLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyPlanEstrategicoAmbientalLogic.class);

    /**
     * DAO injected by Spring that manages PsyPlanEstrategicoAmbiental entities
     *
     */
    @Autowired
    private IPsyPlanEstrategicoAmbientalDAO psyPlanEstrategicoAmbientalDAO;

    /**
    * DAO injected by Spring that manages PsyEvaluacionPeaObjetivo entities
    *
    */
    @Autowired
    private IPsyEvaluacionPeaObjetivoDAO psyEvaluacionPeaObjetivoDAO;

    /**
    * Logic injected by Spring that manages PsyPlanEstrategico entities
    *
    */
    @Autowired
    IPsyPlanEstrategicoLogic logicPsyPlanEstrategico1;
    
    @Autowired
    private IPsyEvaluacionPeaIndicadorDAO psyEvaluacionPeaIndicadorDAO;

    @Transactional(readOnly = true)
    public List<PsyPlanEstrategicoAmbiental> getPsyPlanEstrategicoAmbiental()
        throws Exception {
        log.debug("finding all PsyPlanEstrategicoAmbiental instances");

        List<PsyPlanEstrategicoAmbiental> list = new ArrayList<PsyPlanEstrategicoAmbiental>();

        try {
            list = psyPlanEstrategicoAmbientalDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyPlanEstrategicoAmbiental failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyPlanEstrategicoAmbiental");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyPlanEstrategicoAmbiental(
        PsyPlanEstrategicoAmbiental entity) throws Exception {
        log.debug("saving PsyPlanEstrategicoAmbiental instance");

        try {
            if (entity.getPsyPlanEstrategico() == null) {
                throw new ZMessManager().new ForeignException(
                    "Plan Estrategico");
            }

            if (entity.getEstadoPlan() == null) {
                throw new ZMessManager().new EmptyFieldException("Estado del Plan");
            }

            if ((entity.getEstadoPlan() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoPlan(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Estado del Plan");
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

            if (entity.getFechaInico() == null) {
                throw new ZMessManager().new EmptyFieldException("Fecha Inicio");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("Nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("Nombre");
            }

            if (entity.getPsyPlanEstrategico().getPestCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Plan Estrategico");
            }
            
            if (entity.getFechaFin() != null) {
				if (entity.getFechaFin().before(entity.getFechaInico())) {
					throw new ZMessManager(
							"La fecha final no puede ser anterior a la fecha inicial");
				}

				// if(entity.getFechaFin().before(new Date())){
				// throw new
				// ZMessManager("No puede utilizar fechas anteriores");
				// }

			}

			// if(entity.getFechaInico().before(new Date())){
			// throw new ZMessManager("No puede utilizar fechas anteriores");
			// }

            psyPlanEstrategicoAmbientalDAO.save(entity);

            log.debug("save PsyPlanEstrategicoAmbiental successful");
        } catch (Exception e) {
            log.error("save PsyPlanEstrategicoAmbiental failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyPlanEstrategicoAmbiental(
        PsyPlanEstrategicoAmbiental entity) throws Exception {
        log.debug("deleting PsyPlanEstrategicoAmbiental instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "PsyPlanEstrategicoAmbiental");
        }

        if (entity.getCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("codigo");
        }
        List<PsyEvaluacionPeaIndicador> psyEvaluacionPeaIndicadors = null;
        List<PsyEvaluacionPeaObjetivo> psyEvaluacionPeaObjetivos = null;

        try {
        	psyEvaluacionPeaIndicadors = psyEvaluacionPeaIndicadorDAO.findByProperty("psyPlanEstrategicoAmbiental.codigo",
                    entity.getCodigo());

            if (Utilities.validationsList(psyEvaluacionPeaIndicadors) == true) {
                throw new ZMessManager().new DeletingException(
                    "Evaluacion de Indicadores");
            }
        	
            psyEvaluacionPeaObjetivos = psyEvaluacionPeaObjetivoDAO.findByProperty("psyPlanEstrategicoAmbiental.codigo",
                    entity.getCodigo());

            if (Utilities.validationsList(psyEvaluacionPeaObjetivos) == true) {
                throw new ZMessManager().new DeletingException(
                    "Evaluacion de Objetivos");
            }

            psyPlanEstrategicoAmbientalDAO.delete(entity);

            log.debug("delete PsyPlanEstrategicoAmbiental successful");
        } catch (Exception e) {
            log.error("delete PsyPlanEstrategicoAmbiental failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyPlanEstrategicoAmbiental(
        PsyPlanEstrategicoAmbiental entity) throws Exception {
        log.debug("updating PsyPlanEstrategicoAmbiental instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "PsyPlanEstrategicoAmbiental");
            }

            if (entity.getPsyPlanEstrategico() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyPlanEstrategico");
            }

            if (entity.getCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("codigo");
            }

            if (entity.getEstadoPlan() == null) {
                throw new ZMessManager().new EmptyFieldException("estadoPlan");
            }

            if ((entity.getEstadoPlan() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoPlan(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoPlan");
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

            if (entity.getFechaInico() == null) {
                throw new ZMessManager().new EmptyFieldException("fechaInico");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }

            if (entity.getPsyPlanEstrategico().getPestCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "pestCodigo_PsyPlanEstrategico");
            }
            
            if (entity.getFechaFin() != null) {
				if (entity.getFechaFin().before(entity.getFechaInico())) {
					throw new ZMessManager(
							"La fecha final no puede ser anterior a la fecha inicial");
				}

				// if(entity.getFechaFin().before(new Date())){
				// throw new
				// ZMessManager("No puede utilizar fechas anteriores");
				// }

			}

			// if(entity.getFechaInico().before(new Date())){
			// throw new ZMessManager("No puede utilizar fechas anteriores");
			// }


            psyPlanEstrategicoAmbientalDAO.update(entity);

            log.debug("update PsyPlanEstrategicoAmbiental successful");
        } catch (Exception e) {
            log.error("update PsyPlanEstrategicoAmbiental failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyPlanEstrategicoAmbientalDTO> getDataPsyPlanEstrategicoAmbiental(PsyEmpresa empresa)
        throws Exception {
        try {
            List<PsyPlanEstrategicoAmbiental> psyPlanEstrategicoAmbiental = psyPlanEstrategicoAmbientalDAO.getPEA(empresa);

            List<PsyPlanEstrategicoAmbientalDTO> psyPlanEstrategicoAmbientalDTO = new ArrayList<PsyPlanEstrategicoAmbientalDTO>();

            for (PsyPlanEstrategicoAmbiental psyPlanEstrategicoAmbientalTmp : psyPlanEstrategicoAmbiental) {
                PsyPlanEstrategicoAmbientalDTO psyPlanEstrategicoAmbientalDTO2 = new PsyPlanEstrategicoAmbientalDTO();

                psyPlanEstrategicoAmbientalDTO2.setCodigo(psyPlanEstrategicoAmbientalTmp.getCodigo());
                psyPlanEstrategicoAmbientalDTO2.setEstadoPlan((psyPlanEstrategicoAmbientalTmp.getEstadoPlan() != null)
                    ? (psyPlanEstrategicoAmbientalTmp.getEstadoPlan().equals("A")) ? "Abierto" :
                    	(psyPlanEstrategicoAmbientalTmp.getEstadoPlan().equals("I")) ? "Iniciado":
                    		(psyPlanEstrategicoAmbientalTmp.getEstadoPlan().equals("C")) ? "Cerrado": null : null);
                psyPlanEstrategicoAmbientalDTO2.setEstadoRegistro((psyPlanEstrategicoAmbientalTmp.getEstadoRegistro() != null)
                    ? psyPlanEstrategicoAmbientalTmp.getEstadoRegistro() : null);
                psyPlanEstrategicoAmbientalDTO2.setFechaFin(psyPlanEstrategicoAmbientalTmp.getFechaFin());
                psyPlanEstrategicoAmbientalDTO2.setFechaInico(psyPlanEstrategicoAmbientalTmp.getFechaInico());
                psyPlanEstrategicoAmbientalDTO2.setNombre((psyPlanEstrategicoAmbientalTmp.getNombre() != null)
                    ? psyPlanEstrategicoAmbientalTmp.getNombre() : null);
                psyPlanEstrategicoAmbientalDTO2.setPestCodigo_PsyPlanEstrategico((psyPlanEstrategicoAmbientalTmp.getPsyPlanEstrategico()
                                                                                                                .getPestCodigo() != null)
                    ? psyPlanEstrategicoAmbientalTmp.getPsyPlanEstrategico()
                                                    .getPestCodigo() : null);
                psyPlanEstrategicoAmbientalDTO2.setNombrePest((!psyPlanEstrategicoAmbientalTmp.getPsyPlanEstrategico()
                        .getNombre().trim().equals(""))
					? psyPlanEstrategicoAmbientalTmp.getPsyPlanEstrategico()
													.getNombre() : null);
                
                psyPlanEstrategicoAmbientalDTO2.setDisabled((psyPlanEstrategicoAmbientalTmp.getEstadoPlan() != null) ? 
                		(psyPlanEstrategicoAmbientalTmp.getEstadoPlan().trim().equals("C")) ? true : false : false);
                
                psyPlanEstrategicoAmbientalDTO.add(psyPlanEstrategicoAmbientalDTO2);
            }

            return psyPlanEstrategicoAmbientalDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyPlanEstrategicoAmbiental getPsyPlanEstrategicoAmbiental(
        Long codigo) throws Exception {
        log.debug("getting PsyPlanEstrategicoAmbiental instance");

        PsyPlanEstrategicoAmbiental entity = null;

        try {
            entity = psyPlanEstrategicoAmbientalDAO.findById(codigo);
        } catch (Exception e) {
            log.error("get PsyPlanEstrategicoAmbiental failed", e);
            throw new ZMessManager().new FindingException(
                "PsyPlanEstrategicoAmbiental");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyPlanEstrategicoAmbiental> findPagePsyPlanEstrategicoAmbiental(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<PsyPlanEstrategicoAmbiental> entity = null;

        try {
            entity = psyPlanEstrategicoAmbientalDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyPlanEstrategicoAmbiental Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyPlanEstrategicoAmbiental()
        throws Exception {
        Long entity = null;

        try {
            entity = psyPlanEstrategicoAmbientalDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyPlanEstrategicoAmbiental Count");
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
    public List<PsyPlanEstrategicoAmbiental> findByCriteria(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        List<PsyPlanEstrategicoAmbiental> list = new ArrayList<PsyPlanEstrategicoAmbiental>();
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
            list = psyPlanEstrategicoAmbientalDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    @Transactional(readOnly = true)
    public PsyPlanEstrategico getPlanEstrategicoByPEA(PsyEmpresa empresa, Long codigo) throws Exception {
    	if(empresa==null || empresa.getEmprCodigo()==0){
    		throw new ZMessManager("Para consultar el plan estrategico la empresa no debe estar nulo");
    	}
    	if(codigo==0){
    		throw new ZMessManager("Para consultar el plan estrategico el PEA no debe estar nulo");
    	}
    	
    	return psyPlanEstrategicoAmbientalDAO.getPlanEstrategicoByPEA(empresa, codigo);
    }
    @Transactional(readOnly = true)    
    public PsyPlanEstrategico getPlanEstrategicoActivoByPEA(PsyEmpresa empresa) throws Exception {
    	if(empresa==null || empresa.getEmprCodigo()==0){
    		throw new ZMessManager("Para consultar el plan estrategico la empresa no debe estar nulo");
    	}
    	
    	return psyPlanEstrategicoAmbientalDAO.getPlanEstrategicoActivoByPEA(empresa);
	}
    @Transactional(readOnly = true)
    public PsyPlanEstrategico getPlanEstrategicoByPEA(PsyEmpresa empresa, String nombre) throws Exception {
    	if(empresa==null || empresa.getEmprCodigo()==0){
    		throw new ZMessManager("Para consultar el plan estrategico la empresa no debe estar nulo");
    	}
    	if(nombre.trim().equals("")){
    		throw new ZMessManager("Para consultar el plan estrategico el nombre no debe estar vacio");
    	}
    	
    	return psyPlanEstrategicoAmbientalDAO.getPlanEstrategicoByPEA(empresa,nombre);
    }
    @Transactional(readOnly = true)
    public List<PsyPlanEstrategicoAmbiental> getPEA(PsyEmpresa empresa) throws Exception {
    	if(empresa==null || empresa.getEmprCodigo()==0){
    		throw new ZMessManager("Para consultar el plan estrategico ambiental la empresa no debe estar nulo");
    	}
    	
    	return psyPlanEstrategicoAmbientalDAO.getPEA(empresa);
    }
    
    
}
