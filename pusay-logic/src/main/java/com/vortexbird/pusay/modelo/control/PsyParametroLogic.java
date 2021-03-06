package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyParametroDTO;
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
@Service("PsyParametroLogic")
public class PsyParametroLogic implements IPsyParametroLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyParametroLogic.class);

    /**
     * DAO injected by Spring that manages PsyParametro entities
     *
     */
    @Autowired
    private IPsyParametroDAO psyParametroDAO;

    @Transactional(readOnly = true)
    public List<PsyParametro> getPsyParametro() throws Exception {
        log.debug("finding all PsyParametro instances");

        List<PsyParametro> list = new ArrayList<PsyParametro>();

        try {
            list = psyParametroDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyParametro failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyParametro");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyParametro(PsyParametro entity) throws Exception {
        log.debug("saving PsyParametro instance");

        try {
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

            if (entity.getLlave() == null) {
                throw new ZMessManager().new EmptyFieldException("llave");
            }

            if ((entity.getLlave() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getLlave(),
                        2000) == false)) {
                throw new ZMessManager().new NotValidFormatException("llave");
            }

            if (entity.getParamCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("paramCodigo");
            }

            if (entity.getValor() == null) {
                throw new ZMessManager().new EmptyFieldException("valor");
            }

            if ((entity.getValor() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getValor(),
                        2000) == false)) {
                throw new ZMessManager().new NotValidFormatException("valor");
            }

            if (getPsyParametro(entity.getParamCodigo()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            psyParametroDAO.save(entity);

            log.debug("save PsyParametro successful");
        } catch (Exception e) {
            log.error("save PsyParametro failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyParametro(PsyParametro entity)
        throws Exception {
        log.debug("deleting PsyParametro instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("PsyParametro");
        }

        if (entity.getParamCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("paramCodigo");
        }

        try {
            psyParametroDAO.delete(entity);

            log.debug("delete PsyParametro successful");
        } catch (Exception e) {
            log.error("delete PsyParametro failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyParametro(PsyParametro entity)
        throws Exception {
        log.debug("updating PsyParametro instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("PsyParametro");
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

            if (entity.getLlave() == null) {
                throw new ZMessManager().new EmptyFieldException("llave");
            }

            if ((entity.getLlave() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getLlave(),
                        2000) == false)) {
                throw new ZMessManager().new NotValidFormatException("llave");
            }

            if (entity.getParamCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("paramCodigo");
            }

            if (entity.getValor() == null) {
                throw new ZMessManager().new EmptyFieldException("valor");
            }

            if ((entity.getValor() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getValor(),
                        2000) == false)) {
                throw new ZMessManager().new NotValidFormatException("valor");
            }

            psyParametroDAO.update(entity);

            log.debug("update PsyParametro successful");
        } catch (Exception e) {
            log.error("update PsyParametro failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyParametroDTO> getDataPsyParametro()
        throws Exception {
        try {
            List<PsyParametro> psyParametro = psyParametroDAO.findAll();

            List<PsyParametroDTO> psyParametroDTO = new ArrayList<PsyParametroDTO>();

            for (PsyParametro psyParametroTmp : psyParametro) {
                PsyParametroDTO psyParametroDTO2 = new PsyParametroDTO();

                psyParametroDTO2.setParamCodigo(psyParametroTmp.getParamCodigo());
                psyParametroDTO2.setEstadoRegistro((psyParametroTmp.getEstadoRegistro() != null)
                    ? psyParametroTmp.getEstadoRegistro() : null);
                psyParametroDTO2.setLlave((psyParametroTmp.getLlave() != null)
                    ? psyParametroTmp.getLlave() : null);
                psyParametroDTO2.setValor((psyParametroTmp.getValor() != null)
                    ? psyParametroTmp.getValor() : null);
                psyParametroDTO.add(psyParametroDTO2);
            }

            return psyParametroDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyParametro getPsyParametro(Long paramCodigo)
        throws Exception {
        log.debug("getting PsyParametro instance");

        PsyParametro entity = null;

        try {
            entity = psyParametroDAO.findById(paramCodigo);
        } catch (Exception e) {
            log.error("get PsyParametro failed", e);
            throw new ZMessManager().new FindingException("PsyParametro");
        } finally {
        }

        return entity;
    }
    
    @Transactional(readOnly = true)
    public PsyParametro getPsyParametro(String paramNombre)
        throws Exception {
        log.debug("getting PsyParametro instance");

        PsyParametro entity = null;

        try {
            entity = psyParametroDAO.findEntityByProperty("llave", paramNombre);
        } catch (Exception e) {
            log.error("get PsyParametro failed", e);
            throw new ZMessManager().new FindingException("PsyParametro");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyParametro> findPagePsyParametro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<PsyParametro> entity = null;

        try {
            entity = psyParametroDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("PsyParametro Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyParametro() throws Exception {
        Long entity = null;

        try {
            entity = psyParametroDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("PsyParametro Count");
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
    public List<PsyParametro> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyParametro> list = new ArrayList<PsyParametro>();
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
            list = psyParametroDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
