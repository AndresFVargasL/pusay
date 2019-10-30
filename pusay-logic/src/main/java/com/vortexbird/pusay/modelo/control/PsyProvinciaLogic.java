package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyProvinciaDTO;
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
@Service("PsyProvinciaLogic")
public class PsyProvinciaLogic implements IPsyProvinciaLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyProvinciaLogic.class);

    /**
     * DAO injected by Spring that manages PsyProvincia entities
     *
     */
    @Autowired
    private IPsyProvinciaDAO psyProvinciaDAO;

    /**
    * DAO injected by Spring that manages PsyCiudad entities
    *
    */
    @Autowired
    private IPsyCiudadDAO psyCiudadDAO;

    /**
    * Logic injected by Spring that manages PsyPais entities
    *
    */
    @Autowired
    IPsyPaisLogic logicPsyPais1;

    @Transactional(readOnly = true)
    public List<PsyProvincia> getPsyProvincia() throws Exception {
        log.debug("finding all PsyProvincia instances");

        List<PsyProvincia> list = new ArrayList<PsyProvincia>();

        try {
            list = psyProvinciaDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyProvincia failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyProvincia");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyProvincia(PsyProvincia entity) throws Exception {
        log.debug("saving PsyProvincia instance");

        try {
            if (entity.getPsyPais() == null) {
            	throw new ZMessManager("Seleccione un pais de la lista porfavor");
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
                        200) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }

            if (entity.getPsyPais().getPaisCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "paisCodigo_PsyPais");
            }

            psyProvinciaDAO.save(entity);

            log.debug("save PsyProvincia successful");
        } catch (Exception e) {
            log.error("save PsyProvincia failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyProvincia(PsyProvincia entity)
        throws Exception {
        log.debug("deleting PsyProvincia instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("PsyProvincia");
        }

        if (entity.getProvCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("provCodigo");
        }

        List<PsyCiudad> psyCiudads = null;

        try {
            psyCiudads = psyCiudadDAO.findByProperty("psyProvincia.provCodigo",
                    entity.getProvCodigo());

            if (Utilities.validationsList(psyCiudads) == true) {
                throw new ZMessManager().new DeletingException("psyCiudads");
            }

            psyProvinciaDAO.delete(entity);

            log.debug("delete PsyProvincia successful");
        } catch (Exception e) {
            log.error("delete PsyProvincia failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyProvincia(PsyProvincia entity)
        throws Exception {
        log.debug("updating PsyProvincia instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("PsyProvincia");
            }

            if (entity.getPsyPais() == null) {
                throw new ZMessManager("Seleccione un pais de la lista porfavor");
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
                        200) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }

            if (entity.getPsyPais().getPaisCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "paisCodigo_PsyPais");
            }

            psyProvinciaDAO.update(entity);

            log.debug("update PsyProvincia successful");
        } catch (Exception e) {
            log.error("update PsyProvincia failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyProvinciaDTO> getDataPsyProvincia()
        throws Exception {
        try {
            List<PsyProvincia> psyProvincia = psyProvinciaDAO.findAll();

            List<PsyProvinciaDTO> psyProvinciaDTO = new ArrayList<PsyProvinciaDTO>();

            for (PsyProvincia psyProvinciaTmp : psyProvincia) {
                PsyProvinciaDTO psyProvinciaDTO2 = new PsyProvinciaDTO();

                psyProvinciaDTO2.setProvCodigo(psyProvinciaTmp.getProvCodigo());
                psyProvinciaDTO2.setEstadoRegistro((psyProvinciaTmp.getEstadoRegistro() != null)
                    ? (psyProvinciaTmp.getEstadoRegistro().trim().equals("A")) ? "Activo" : "Inactivo" : null);
                psyProvinciaDTO2.setNombre((psyProvinciaTmp.getNombre() != null)
                    ? psyProvinciaTmp.getNombre() : null);
                psyProvinciaDTO2.setPaisCodigo_PsyPais((psyProvinciaTmp.getPsyPais()
                                                                       .getPaisCodigo() != null)
                    ? psyProvinciaTmp.getPsyPais().getPaisCodigo() : null);
                psyProvinciaDTO2.setNombrePais((psyProvinciaTmp.getPsyPais()
                        .getPaisCodigo() != null)
                        			? psyProvinciaTmp.getPsyPais().getNombre() : null);
                psyProvinciaDTO.add(psyProvinciaDTO2);
            }

            return psyProvinciaDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyProvincia getPsyProvincia(Long provCodigo)
        throws Exception {
        log.debug("getting PsyProvincia instance");

        PsyProvincia entity = null;

        try {
            entity = psyProvinciaDAO.findById(provCodigo);
        } catch (Exception e) {
            log.error("get PsyProvincia failed", e);
            throw new ZMessManager().new FindingException("PsyProvincia");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyProvincia> findPagePsyProvincia(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<PsyProvincia> entity = null;

        try {
            entity = psyProvinciaDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("PsyProvincia Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyProvincia() throws Exception {
        Long entity = null;

        try {
            entity = psyProvinciaDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("PsyProvincia Count");
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
    public List<PsyProvincia> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyProvincia> list = new ArrayList<PsyProvincia>();
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
            list = psyProvinciaDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
