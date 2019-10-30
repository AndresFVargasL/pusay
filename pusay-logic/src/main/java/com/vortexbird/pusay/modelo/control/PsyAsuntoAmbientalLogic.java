package com.vortexbird.pusay.modelo.control;



import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyAsuntoAmbientalDTO;
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

import javax.faces.model.SelectItem;



/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("PsyAsuntoAmbientalLogic")
public class PsyAsuntoAmbientalLogic implements IPsyAsuntoAmbientalLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyAsuntoAmbientalLogic.class);

    /**
     * DAO injected by Spring that manages PsyAsuntoAmbiental entities
     *
     */
    @Autowired
    private IPsyAsuntoAmbientalDAO psyAsuntoAmbientalDAO;

    /**
    * DAO injected by Spring that manages PsyDetalleErida entities
    *
    */
    @Autowired
    private IPsyDetalleEridaDAO psyDetalleEridaDAO;

    @Transactional(readOnly = true)
    public List<PsyAsuntoAmbiental> getPsyAsuntoAmbiental()
        throws Exception {
        log.debug("finding all PsyAsuntoAmbiental instances");

        List<PsyAsuntoAmbiental> list = new ArrayList<PsyAsuntoAmbiental>();

        try {
            list = psyAsuntoAmbientalDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyAsuntoAmbiental failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyAsuntoAmbiental");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyAsuntoAmbiental(PsyAsuntoAmbiental entity)
        throws Exception {
        log.debug("saving PsyAsuntoAmbiental instance");

        try {

            if (entity.getDescripcion() == null) {
                throw new ZMessManager().new EmptyFieldException("descripcion");
            }

            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 2000) == false)) {
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

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }


            psyAsuntoAmbientalDAO.save(entity);

            log.debug("save PsyAsuntoAmbiental successful");
        } catch (Exception e) {
            log.error("save PsyAsuntoAmbiental failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyAsuntoAmbiental(PsyAsuntoAmbiental entity)
        throws Exception {
        log.debug("deleting PsyAsuntoAmbiental instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "PsyAsuntoAmbiental");
        }

        if (entity.getAsamCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("asamCodigo");
        }

        List<PsyDetalleErida> psyDetalleEridas = null;

        try {
            psyDetalleEridas = psyDetalleEridaDAO.findByProperty("psyAsuntoAmbiental.asamCodigo",
                    entity.getAsamCodigo());

            if (Utilities.validationsList(psyDetalleEridas) == true) {
                throw new ZMessManager().new DeletingException(
                    "psyDetalleEridas");
            }

            psyAsuntoAmbientalDAO.delete(entity);

            log.debug("delete PsyAsuntoAmbiental successful");
        } catch (Exception e) {
            log.error("delete PsyAsuntoAmbiental failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyAsuntoAmbiental(PsyAsuntoAmbiental entity)
        throws Exception {
        log.debug("updating PsyAsuntoAmbiental instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "PsyAsuntoAmbiental");
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

            psyAsuntoAmbientalDAO.update(entity);

            log.debug("update PsyAsuntoAmbiental successful");
        } catch (Exception e) {
            log.error("update PsyAsuntoAmbiental failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyAsuntoAmbientalDTO> getDataPsyAsuntoAmbiental()
        throws Exception {
        try {
            List<PsyAsuntoAmbiental> psyAsuntoAmbiental = psyAsuntoAmbientalDAO.findAll();

            List<PsyAsuntoAmbientalDTO> psyAsuntoAmbientalDTO = new ArrayList<PsyAsuntoAmbientalDTO>();

            for (PsyAsuntoAmbiental psyAsuntoAmbientalTmp : psyAsuntoAmbiental) {
                PsyAsuntoAmbientalDTO psyAsuntoAmbientalDTO2 = new PsyAsuntoAmbientalDTO();

                psyAsuntoAmbientalDTO2.setAsamCodigo(psyAsuntoAmbientalTmp.getAsamCodigo());
                psyAsuntoAmbientalDTO2.setDescripcion((psyAsuntoAmbientalTmp.getDescripcion() != null)
                    ? psyAsuntoAmbientalTmp.getDescripcion() : null);
                psyAsuntoAmbientalDTO2.setEstadoRegistro((psyAsuntoAmbientalTmp.getEstadoRegistro() != null)
                    ? (psyAsuntoAmbientalTmp.getEstadoRegistro().trim().equals("A")) ? "Activo" : "Inactivo" : null);
                psyAsuntoAmbientalDTO2.setNombre((psyAsuntoAmbientalTmp.getNombre() != null)
                    ? psyAsuntoAmbientalTmp.getNombre() : null);
                psyAsuntoAmbientalDTO.add(psyAsuntoAmbientalDTO2);
            }

            return psyAsuntoAmbientalDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyAsuntoAmbiental getPsyAsuntoAmbiental(Long asamCodigo)
        throws Exception {
        log.debug("getting PsyAsuntoAmbiental instance");

        PsyAsuntoAmbiental entity = null;

        try {
            entity = psyAsuntoAmbientalDAO.findById(asamCodigo);
        } catch (Exception e) {
            log.error("get PsyAsuntoAmbiental failed", e);
            throw new ZMessManager().new FindingException("PsyAsuntoAmbiental");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyAsuntoAmbiental> findPagePsyAsuntoAmbiental(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<PsyAsuntoAmbiental> entity = null;

        try {
            entity = psyAsuntoAmbientalDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyAsuntoAmbiental Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyAsuntoAmbiental() throws Exception {
        Long entity = null;

        try {
            entity = psyAsuntoAmbientalDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyAsuntoAmbiental Count");
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
    public List<PsyAsuntoAmbiental> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyAsuntoAmbiental> list = new ArrayList<PsyAsuntoAmbiental>();
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
            list = psyAsuntoAmbientalDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    @Transactional(readOnly = true)
	public List<SelectItem> cargarOneMenuAsuntoAmbienta() throws Exception {
    	List<PsyAsuntoAmbiental> listaAsuntoAmbiental= new ArrayList<PsyAsuntoAmbiental>();
    	List<SelectItem> lstAsuntoAmbiental = new ArrayList<SelectItem>();
    	try {
    		
    		listaAsuntoAmbiental = psyAsuntoAmbientalDAO.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
        for (PsyAsuntoAmbiental asuntoAmbiental : listaAsuntoAmbiental) {
        	lstAsuntoAmbiental.add(new SelectItem(asuntoAmbiental.getAsamCodigo(),asuntoAmbiental.getNombre()));
        }
        return lstAsuntoAmbiental;
	}
}
