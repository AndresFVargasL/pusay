package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyMatrizEridaDTO;
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
@Service("PsyMatrizEridaLogic")
public class PsyMatrizEridaLogic implements IPsyMatrizEridaLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyMatrizEridaLogic.class);

    /**
     * DAO injected by Spring that manages PsyMatrizErida entities
     *
     */
    @Autowired
    private IPsyMatrizEridaDAO psyMatrizEridaDAO;
    
    @Autowired
    private IPsyAsuntoAmbientalDAO psyAsuntoAmbientalDAO;
    /**
    * DAO injected by Spring that manages PsyDetalleErida entities
    *
    */
    @Autowired
    private IPsyDetalleEridaDAO psyDetalleEridaDAO;

    /**
    * Logic injected by Spring that manages PsyPlanEstrategico entities
    *
    */
    @Autowired
    IPsyPlanEstrategicoLogic logicPsyPlanEstrategico1;
    @Autowired
    private IPsyPlanEstrategicoDAO PsyPlanEstrategicoDAO;

    @Transactional(readOnly = true)
    public List<PsyMatrizErida> getPsyMatrizErida() throws Exception {
        log.debug("finding all PsyMatrizErida instances");

        List<PsyMatrizErida> list = new ArrayList<PsyMatrizErida>();

        try {
            list = psyMatrizEridaDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyMatrizErida failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyMatrizErida");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyMatrizErida(PsyMatrizErida entity)
        throws Exception {
        log.debug("saving PsyMatrizErida instance");

        try {
            if (entity.getPsyPlanEstrategico() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyPlanEstrategico");
            }

            if (entity.getEstadoErida() == null) {
                throw new ZMessManager().new EmptyFieldException("estadoErida");
            }

            if ((entity.getEstadoErida() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoErida(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoErida");
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

            if (entity.getFechaFin() == null) {
                throw new ZMessManager().new EmptyFieldException("fechaFin");
            }

            if (entity.getMaerCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("maerCodigo");
            }

            if (entity.getPsyPlanEstrategico().getPestCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "pestCodigo_PsyPlanEstrategico");
            }

            if (getPsyMatrizErida(entity.getMaerCodigo()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            psyMatrizEridaDAO.save(entity);

            log.debug("save PsyMatrizErida successful");
        } catch (Exception e) {
            log.error("save PsyMatrizErida failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyMatrizErida(PsyMatrizErida entity)
        throws Exception {
        log.debug("deleting PsyMatrizErida instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("PsyMatrizErida");
        }

        if (entity.getMaerCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("maerCodigo");
        }

        List<PsyDetalleErida> psyDetalleEridas = null;

        try {
            psyDetalleEridas = psyDetalleEridaDAO.findByProperty("psyMatrizErida.maerCodigo",
                    entity.getMaerCodigo());

            if (Utilities.validationsList(psyDetalleEridas) == true) {
                throw new ZMessManager().new DeletingException(
                    "psyDetalleEridas");
            }

            psyMatrizEridaDAO.delete(entity);

            log.debug("delete PsyMatrizErida successful");
        } catch (Exception e) {
            log.error("delete PsyMatrizErida failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyMatrizErida(PsyMatrizErida entity)
        throws Exception {
        log.debug("updating PsyMatrizErida instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "PsyMatrizErida");
            }

            if (entity.getPsyPlanEstrategico() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyPlanEstrategico");
            }

            if (entity.getEstadoErida() == null) {
                throw new ZMessManager().new EmptyFieldException("estadoErida");
            }

            if ((entity.getEstadoErida() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoErida(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoErida");
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

            if (entity.getFechaFin() == null) {
                throw new ZMessManager().new EmptyFieldException("fechaFin");
            }

            if (entity.getMaerCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("maerCodigo");
            }

            if (entity.getPsyPlanEstrategico().getPestCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "pestCodigo_PsyPlanEstrategico");
            }

            psyMatrizEridaDAO.update(entity);

            log.debug("update PsyMatrizErida successful");
        } catch (Exception e) {
            log.error("update PsyMatrizErida failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyMatrizEridaDTO> getDataPsyMatrizErida()
        throws Exception {
        try {
            List<PsyMatrizErida> psyMatrizErida = psyMatrizEridaDAO.findAll();

            List<PsyMatrizEridaDTO> psyMatrizEridaDTO = new ArrayList<PsyMatrizEridaDTO>();

            for (PsyMatrizErida psyMatrizEridaTmp : psyMatrizErida) {
                PsyMatrizEridaDTO psyMatrizEridaDTO2 = new PsyMatrizEridaDTO();

                psyMatrizEridaDTO2.setMaerCodigo(psyMatrizEridaTmp.getMaerCodigo());
                psyMatrizEridaDTO2.setEstadoErida((psyMatrizEridaTmp.getEstadoErida() != null)
                    ? psyMatrizEridaTmp.getEstadoErida() : null);
                psyMatrizEridaDTO2.setEstadoRegistro((psyMatrizEridaTmp.getEstadoRegistro() != null)
                    ? psyMatrizEridaTmp.getEstadoRegistro() : null);
                psyMatrizEridaDTO2.setFechaFin(psyMatrizEridaTmp.getFechaFin());
                psyMatrizEridaDTO2.setFechaInicio(psyMatrizEridaTmp.getFechaInicio());
                psyMatrizEridaDTO2.setPestCodigo_PsyPlanEstrategico((psyMatrizEridaTmp.getPsyPlanEstrategico()
                                                                                      .getPestCodigo() != null)
                    ? psyMatrizEridaTmp.getPsyPlanEstrategico().getPestCodigo()
                    : null);
                psyMatrizEridaDTO.add(psyMatrizEridaDTO2);
            }

            return psyMatrizEridaDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyMatrizErida getPsyMatrizErida(Long maerCodigo)
        throws Exception {
        log.debug("getting PsyMatrizErida instance");

        PsyMatrizErida entity = null;

        try {
            entity = psyMatrizEridaDAO.findById(maerCodigo);
        } catch (Exception e) {
            log.error("get PsyMatrizErida failed", e);
            throw new ZMessManager().new FindingException("PsyMatrizErida");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyMatrizErida> findPagePsyMatrizErida(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<PsyMatrizErida> entity = null;

        try {
            entity = psyMatrizEridaDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyMatrizErida Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyMatrizErida() throws Exception {
        Long entity = null;

        try {
            entity = psyMatrizEridaDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyMatrizErida Count");
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
    public List<PsyMatrizErida> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyMatrizErida> list = new ArrayList<PsyMatrizErida>();
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
            list = psyMatrizEridaDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<PsyMatrizEridaDTO> consultarExisteEridaPlanEstrategico(Long pestCodigo)throws Exception  {
		try {
			
			  List<PsyMatrizErida> lstME=psyMatrizEridaDAO.find("from PsyMatrizErida where psyPlanEstrategico='"+pestCodigo+"' ");
			if(lstME.size()>0){
				
			}else{
				PsyMatrizErida matrizErida = new PsyMatrizErida();
				
				matrizErida.setEstadoErida("A");
				matrizErida.setEstadoRegistro("A");
				matrizErida.setFechaFin(new Date());
				matrizErida.setFechaInicio(new Date());
				matrizErida.setPsyPlanEstrategico(PsyPlanEstrategicoDAO.findById(pestCodigo));
				psyMatrizEridaDAO.save(matrizErida);
				
			}
			
			List<PsyMatrizEridaDTO> psyMatrizEridaDTO = new ArrayList<PsyMatrizEridaDTO>();

            for (PsyMatrizErida psyMatrizEridaTmp : lstME) {
                PsyMatrizEridaDTO psyMatrizEridaDTO2 = new PsyMatrizEridaDTO();

                psyMatrizEridaDTO2.setMaerCodigo(psyMatrizEridaTmp.getMaerCodigo());
                psyMatrizEridaDTO2.setEstadoErida((psyMatrizEridaTmp.getEstadoErida() != null)
                    ? psyMatrizEridaTmp.getEstadoErida() : null);
                psyMatrizEridaDTO2.setEstadoRegistro((psyMatrizEridaTmp.getEstadoRegistro() != null)
                    ? psyMatrizEridaTmp.getEstadoRegistro() : null);
                psyMatrizEridaDTO2.setFechaFin(psyMatrizEridaTmp.getFechaFin());
                psyMatrizEridaDTO2.setFechaInicio(psyMatrizEridaTmp.getFechaInicio());
                psyMatrizEridaDTO2.setPestCodigo_PsyPlanEstrategico((psyMatrizEridaTmp.getPsyPlanEstrategico()
                                                                                      .getPestCodigo() != null)
                    ? psyMatrizEridaTmp.getPsyPlanEstrategico().getPestCodigo()
                    : null);
                psyMatrizEridaDTO.add(psyMatrizEridaDTO2);
            }
			
			return psyMatrizEridaDTO;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<PsyAsuntoAmbiental>  terminarErida(Long mearCodigo)
			throws Exception {
		 
		
		try {
			
			List<PsyAsuntoAmbiental> psyAsuntoAmbiental = psyMatrizEridaDAO.asuntosAmbientalesFaltantes(mearCodigo);
			if(psyAsuntoAmbiental.size()==0){
				PsyMatrizErida matrizErida = psyMatrizEridaDAO.findById(mearCodigo);			
				matrizErida.setEstadoErida("I");
				updatePsyMatrizErida(matrizErida);
			}
			 return psyAsuntoAmbiental;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
    
    @Transactional(readOnly = true)
    public PsyMatrizErida consultarMatrizErida(PsyPlanEstrategico planEstrategico) throws Exception {
		 
    	List<PsyMatrizErida> lstMatrizErida = null;
    	PsyMatrizErida matrizErida = null;
		try {
			
			 Object[] variablesDetalleObjetivoPlanMatrizErida = { "psyPlanEstrategico.pestCodigo", false,
					 planEstrategico.getPestCodigo(), "=", "estadoRegistro", true,
	    			 "A", "="};
	 		
			 lstMatrizErida = findByCriteria(variablesDetalleObjetivoPlanMatrizErida, null, null);

			if(lstMatrizErida != null && !lstMatrizErida.isEmpty()){
				matrizErida = lstMatrizErida.get(0);
			}else{
				throw new Exception("No existe una matriz ERIDA para el plan estrategico "+planEstrategico.getPestCodigo());
			}
			
			 return matrizErida;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
    
   
}
