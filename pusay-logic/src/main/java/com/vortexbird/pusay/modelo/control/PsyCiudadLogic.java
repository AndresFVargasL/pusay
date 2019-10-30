package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyCiudadDTO;
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
@Service("PsyCiudadLogic")
public class PsyCiudadLogic implements IPsyCiudadLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyCiudadLogic.class);

    /**
     * DAO injected by Spring that manages PsyCiudad entities
     *
     */
    @Autowired
    private IPsyCiudadDAO psyCiudadDAO;

    /**
    * DAO injected by Spring that manages PsyEmpresa entities
    *
    */
    @Autowired
    private IPsyEmpresaDAO psyEmpresaDAO;

    /**
    * Logic injected by Spring that manages PsyProvincia entities
    *
    */
    @Autowired
    IPsyProvinciaLogic logicPsyProvincia1;
    
    @Autowired
    IPsyPaisLogic logicPsyPais2;

    @Transactional(readOnly = true)
    public List<PsyCiudad> getPsyCiudad() throws Exception {
        log.debug("finding all PsyCiudad instances");

        List<PsyCiudad> list = new ArrayList<PsyCiudad>();

        try {
            list = psyCiudadDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyCiudad failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyCiudad");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyCiudad(PsyCiudad entity) throws Exception {
        log.debug("saving PsyCiudad instance");

        try {
            if (entity.getPsyProvincia() == null) {
                throw new ZMessManager("Porfavor seleccione una provincia de la lista");
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

            if (entity.getPsyProvincia().getProvCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "provCodigo_PsyProvincia");
            }

            psyCiudadDAO.save(entity);

            log.debug("save PsyCiudad successful");
        } catch (Exception e) {
            log.error("save PsyCiudad failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyCiudad(PsyCiudad entity) throws Exception {
        log.debug("deleting PsyCiudad instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("PsyCiudad");
        }

        if (entity.getCiudCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("ciudCodigo");
        }

        List<PsyEmpresa> psyEmpresas = null;

        try {
            psyEmpresas = psyEmpresaDAO.findByProperty("psyCiudad.ciudCodigo",
                    entity.getCiudCodigo());

            if (Utilities.validationsList(psyEmpresas) == true) {
                throw new ZMessManager().new DeletingException("psyEmpresas");
            }

            psyCiudadDAO.delete(entity);

            log.debug("delete PsyCiudad successful");
        } catch (Exception e) {
            log.error("delete PsyCiudad failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyCiudad(PsyCiudad entity) throws Exception {
        log.debug("updating PsyCiudad instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("PsyCiudad");
            }

            if (entity.getPsyProvincia() == null) {
            	throw new ZMessManager("Porfavor seleccione una provincia de la lista");
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

            if (entity.getPsyProvincia().getProvCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "provCodigo_PsyProvincia");
            }

            psyCiudadDAO.update(entity);

            log.debug("update PsyCiudad successful");
        } catch (Exception e) {
            log.error("update PsyCiudad failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyCiudadDTO> getDataPsyCiudad() throws Exception {
        try {
            List<PsyCiudad> psyCiudad = psyCiudadDAO.findAll();

            List<PsyCiudadDTO> psyCiudadDTO = new ArrayList<PsyCiudadDTO>();

            for (PsyCiudad psyCiudadTmp : psyCiudad) {
                PsyCiudadDTO psyCiudadDTO2 = new PsyCiudadDTO();

                psyCiudadDTO2.setCiudCodigo(psyCiudadTmp.getCiudCodigo());
                psyCiudadDTO2.setEstadoRegistro((psyCiudadTmp.getEstadoRegistro() != null)
                    ? (psyCiudadTmp.getEstadoRegistro().trim().equals("A")) ? "Activo" : "Inactivo" : null);
                psyCiudadDTO2.setNombre((psyCiudadTmp.getNombre() != null)
                    ? psyCiudadTmp.getNombre() : null);
                psyCiudadDTO2.setProvCodigo_PsyProvincia((psyCiudadTmp.getPsyProvincia()
                                                                      .getProvCodigo() != null)
                    ? psyCiudadTmp.getPsyProvincia().getProvCodigo() : null);
                psyCiudadDTO.add(psyCiudadDTO2);
            }

            return psyCiudadDTO;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Transactional(readOnly = true)
    public List<PsyProvincia> consultarProvinciasPorPais(Long paisCodigo) throws Exception {
        try {
        	
        	if(paisCodigo == 0){
        		throw new ZMessManager("Debe seleccionar un pais de la lista");
        	}
        	
        	Object[] variables = {"psyPais.paisCodigo",false,paisCodigo,"=","estadoRegistro",true,"A","="};
        	
            List<PsyProvincia> psyProvincias = logicPsyProvincia1.findByCriteria(variables, null, null);

            

            return psyProvincias;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Transactional(readOnly = true)
    public List<PsyCiudad> consultarCiudadesPorProvincia(Long provCodigo) throws Exception {
        try {
        	
        	if(provCodigo == 0){
        		throw new ZMessManager("Debe seleccionar una provincia de la lista");
        	}
        	
        	Object[] variables = {"psyProvincia.provCodigo",false,provCodigo,"=","estadoRegistro",true,"A","="};
        	
            List<PsyCiudad> lasCiudades = findByCriteria(variables, null, null);

            

            return lasCiudades;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Transactional(readOnly = true)
    public PsyPais consultarPaisPorProvincias(Long provCodigo) throws Exception {
        try {
        	
        	if(provCodigo == 0){
        		throw new ZMessManager("Debe seleccionar un pais de la lista");
        	}
        	
        	Object[] variables = {"provCodigo",false,provCodigo,"=","estadoRegistro",true,"A","="};
        	
            List<PsyProvincia> psyProvincias = logicPsyProvincia1.findByCriteria(variables, null, null);
            Long paisCodigo = psyProvincias.get(0).getPsyPais().getPaisCodigo();
            PsyPais pais = logicPsyPais2.getPsyPais(paisCodigo);
            

            return pais;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyCiudad getPsyCiudad(Long ciudCodigo) throws Exception {
        log.debug("getting PsyCiudad instance");

        PsyCiudad entity = null;

        try {
            entity = psyCiudadDAO.findById(ciudCodigo);
        } catch (Exception e) {
            log.error("get PsyCiudad failed", e);
            throw new ZMessManager().new FindingException("PsyCiudad");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyCiudad> findPagePsyCiudad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<PsyCiudad> entity = null;

        try {
            entity = psyCiudadDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("PsyCiudad Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyCiudad() throws Exception {
        Long entity = null;

        try {
            entity = psyCiudadDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("PsyCiudad Count");
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
    public List<PsyCiudad> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyCiudad> list = new ArrayList<PsyCiudad>();
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
            list = psyCiudadDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
