package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.exceptions.ZMessManager.NotValidFormatException;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyEvaluacionPeaObjetivoDTO;
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
@Service("PsyEvaluacionPeaObjetivoLogic")
public class PsyEvaluacionPeaObjetivoLogic
    implements IPsyEvaluacionPeaObjetivoLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyEvaluacionPeaObjetivoLogic.class);

    /**
     * DAO injected by Spring that manages PsyEvaluacionPeaObjetivo entities
     *
     */
    @Autowired
    private IPsyEvaluacionPeaObjetivoDAO psyEvaluacionPeaObjetivoDAO;

    /**
    * Logic injected by Spring that manages PsyObjetivoAmbiental entities
    *
    */
    @Autowired
    IPsyObjetivoAmbientalLogic logicPsyObjetivoAmbiental1;

    /**
    * Logic injected by Spring that manages PsyPlanEstrategicoAmbiental entities
    *
    */
    @Autowired
    IPsyPlanEstrategicoAmbientalLogic logicPsyPlanEstrategicoAmbiental2;

    @Transactional(readOnly = true)
    public List<PsyEvaluacionPeaObjetivo> getPsyEvaluacionPeaObjetivo()
        throws Exception {
        log.debug("finding all PsyEvaluacionPeaObjetivo instances");

        List<PsyEvaluacionPeaObjetivo> list = new ArrayList<PsyEvaluacionPeaObjetivo>();

        try {
            list = psyEvaluacionPeaObjetivoDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyEvaluacionPeaObjetivo failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyEvaluacionPeaObjetivo");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyEvaluacionPeaObjetivo(PsyEvaluacionPeaObjetivo entity)
        throws Exception {
        log.debug("saving PsyEvaluacionPeaObjetivo instance");

        try {
            if (entity.getPsyObjetivoAmbiental() == null) {
                throw new ZMessManager().new ForeignException(
                    "Objetivo Ambiental");
            }

            if (entity.getPsyPlanEstrategicoAmbiental() == null) {
                throw new ZMessManager().new ForeignException(
                    "Plan Estrategico Ambiental");
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
            
            if ((entity.getHistorial() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getHistorial(), 12, 2) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Historial");
            }

            if (entity.getPeriodo() == null) {
                throw new ZMessManager().new EmptyFieldException("Periodo");
            }

            if ((entity.getPeriodo() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getPeriodo(), 2, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Periodo");
            }

            if (entity.getResultado() == null) {
                throw new ZMessManager().new EmptyFieldException("Resultado");
            }

            if ((entity.getResultado() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getResultado(), 12, 2) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Resultado");
            }

            if (entity.getPsyObjetivoAmbiental().getCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Objetivo Ambiental");
            }

            if (entity.getPsyPlanEstrategicoAmbiental().getCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Plan Estrategico Ambiental");
            }

            psyEvaluacionPeaObjetivoDAO.save(entity);

            log.debug("save PsyEvaluacionPeaObjetivo successful");
        } catch (Exception e) {
            log.error("save PsyEvaluacionPeaObjetivo failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyEvaluacionPeaObjetivo(PsyEvaluacionPeaObjetivo entity)
        throws Exception {
        log.debug("deleting PsyEvaluacionPeaObjetivo instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "PsyEvaluacionPeaObjetivo");
        }

        if (entity.getCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("codigo");
        }

        try {
            psyEvaluacionPeaObjetivoDAO.delete(entity);

            log.debug("delete PsyEvaluacionPeaObjetivo successful");
        } catch (Exception e) {
            log.error("delete PsyEvaluacionPeaObjetivo failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyEvaluacionPeaObjetivo(PsyEvaluacionPeaObjetivo entity)
        throws Exception {
        log.debug("updating PsyEvaluacionPeaObjetivo instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "PsyEvaluacionPeaObjetivo");
            }

            if (entity.getPsyObjetivoAmbiental() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyObjetivoAmbiental");
            }

            if (entity.getPsyPlanEstrategicoAmbiental() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyPlanEstrategicoAmbiental");
            }

            if (entity.getCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("codigo");
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
            
            if ((entity.getHistorial() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getHistorial(), 12, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "historial");
            }

            if (entity.getPeriodo() == null) {
                throw new ZMessManager().new EmptyFieldException("periodo");
            }

            if ((entity.getPeriodo() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getPeriodo(), 2, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("periodo");
            }

            if (entity.getResultado() == null) {
                throw new ZMessManager().new EmptyFieldException("resultado");
            }

            if ((entity.getResultado() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getResultado(), 5, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "resultado");
            }

            if (entity.getPsyObjetivoAmbiental().getCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "codigo_PsyObjetivoAmbiental");
            }

            if (entity.getPsyPlanEstrategicoAmbiental().getCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "codigo_PsyPlanEstrategicoAmbiental");
            }

            psyEvaluacionPeaObjetivoDAO.update(entity);

            log.debug("update PsyEvaluacionPeaObjetivo successful");
        } catch (Exception e) {
            log.error("update PsyEvaluacionPeaObjetivo failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyEvaluacionPeaObjetivoDTO> getDataPsyEvaluacionPeaObjetivo()
        throws Exception {
        try {
            List<PsyEvaluacionPeaObjetivo> psyEvaluacionPeaObjetivo = psyEvaluacionPeaObjetivoDAO.findAll();

            List<PsyEvaluacionPeaObjetivoDTO> psyEvaluacionPeaObjetivoDTO = new ArrayList<PsyEvaluacionPeaObjetivoDTO>();

            for (PsyEvaluacionPeaObjetivo psyEvaluacionPeaObjetivoTmp : psyEvaluacionPeaObjetivo) {
                PsyEvaluacionPeaObjetivoDTO psyEvaluacionPeaObjetivoDTO2 = new PsyEvaluacionPeaObjetivoDTO();

                psyEvaluacionPeaObjetivoDTO2.setCodigo(psyEvaluacionPeaObjetivoTmp.getCodigo());
                psyEvaluacionPeaObjetivoDTO2.setEstadoRegistro((psyEvaluacionPeaObjetivoTmp.getEstadoRegistro() != null)
                    ? psyEvaluacionPeaObjetivoTmp.getEstadoRegistro() : null);
                psyEvaluacionPeaObjetivoDTO2.setHistorial((psyEvaluacionPeaObjetivoTmp.getHistorial() != null)
                        ? psyEvaluacionPeaObjetivoTmp.getHistorial() : null);
                psyEvaluacionPeaObjetivoDTO2.setPeriodo((psyEvaluacionPeaObjetivoTmp.getPeriodo() != null)
                    ? psyEvaluacionPeaObjetivoTmp.getPeriodo() : null);
                psyEvaluacionPeaObjetivoDTO2.setResultado((psyEvaluacionPeaObjetivoTmp.getResultado() != null)
                    ? psyEvaluacionPeaObjetivoTmp.getResultado() : null);
                psyEvaluacionPeaObjetivoDTO2.setCodigo_PsyObjetivoAmbiental((psyEvaluacionPeaObjetivoTmp.getPsyObjetivoAmbiental()
                                                                                                        .getCodigo() != null)
                    ? psyEvaluacionPeaObjetivoTmp.getPsyObjetivoAmbiental()
                                                 .getCodigo() : null);
                psyEvaluacionPeaObjetivoDTO2.setCodigo_PsyPlanEstrategicoAmbiental((psyEvaluacionPeaObjetivoTmp.getPsyPlanEstrategicoAmbiental()
                                                                                                               .getCodigo() != null)
                    ? psyEvaluacionPeaObjetivoTmp.getPsyPlanEstrategicoAmbiental()
                                                 .getCodigo() : null);
                psyEvaluacionPeaObjetivoDTO.add(psyEvaluacionPeaObjetivoDTO2);
            }

            return psyEvaluacionPeaObjetivoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyEvaluacionPeaObjetivo getPsyEvaluacionPeaObjetivo(Long codigo)
        throws Exception {
        log.debug("getting PsyEvaluacionPeaObjetivo instance");

        PsyEvaluacionPeaObjetivo entity = null;

        try {
            entity = psyEvaluacionPeaObjetivoDAO.findById(codigo);
        } catch (Exception e) {
            log.error("get PsyEvaluacionPeaObjetivo failed", e);
            throw new ZMessManager().new FindingException(
                "PsyEvaluacionPeaObjetivo");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyEvaluacionPeaObjetivo> findPagePsyEvaluacionPeaObjetivo(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<PsyEvaluacionPeaObjetivo> entity = null;

        try {
            entity = psyEvaluacionPeaObjetivoDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyEvaluacionPeaObjetivo Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyEvaluacionPeaObjetivo()
        throws Exception {
        Long entity = null;

        try {
            entity = psyEvaluacionPeaObjetivoDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyEvaluacionPeaObjetivo Count");
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
    public List<PsyEvaluacionPeaObjetivo> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyEvaluacionPeaObjetivo> list = new ArrayList<PsyEvaluacionPeaObjetivo>();
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
            list = psyEvaluacionPeaObjetivoDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
