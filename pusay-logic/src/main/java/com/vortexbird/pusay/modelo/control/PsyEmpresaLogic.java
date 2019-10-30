package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyEmpresaDTO;
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
@Service("PsyEmpresaLogic")
public class PsyEmpresaLogic implements IPsyEmpresaLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyEmpresaLogic.class);

    /**
     * DAO injected by Spring that manages PsyEmpresa entities
     *
     */
    @Autowired
    private IPsyEmpresaDAO psyEmpresaDAO;

    /**
    * DAO injected by Spring that manages PsyPersona entities
    *
    */
    @Autowired
    private IPsyPersonaDAO psyPersonaDAO;

    /**
    * DAO injected by Spring that manages PsyPlanEstrategico entities
    *
    */
    @Autowired
    private IPsyPlanEstrategicoDAO psyPlanEstrategicoDAO;

    /**
    * DAO injected by Spring that manages PsyResponsable entities
    *
    */
    @Autowired
    private IPsyResponsableDAO psyResponsableDAO;

    /**
    * Logic injected by Spring that manages PsyCiudad entities
    *
    */
    @Autowired
    IPsyCiudadLogic logicPsyCiudad1;

    /**
    * Logic injected by Spring that manages PsyPersona entities
    *
    */
    @Autowired
    IPsyPersonaLogic logicPsyPersona2;

    @Transactional(readOnly = true)
    public List<PsyEmpresa> getPsyEmpresa() throws Exception {
        log.debug("finding all PsyEmpresa instances");

        List<PsyEmpresa> list = new ArrayList<PsyEmpresa>();

        try {
            list = psyEmpresaDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyEmpresa failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyEmpresa");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyEmpresa(PsyEmpresa entity) throws Exception {
        log.debug("saving PsyEmpresa instance");

        try {
            if (entity.getPsyCiudad() == null) {
                throw new ZMessManager().new ForeignException("Ciudad");
            }

            
            if ((entity.getDireccionPrincipal() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDireccionPrincipal(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Direccion Principal");
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

            if (entity.getNit() == null) {
                throw new ZMessManager().new EmptyFieldException("NIT");
            }

            if ((entity.getNit() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNit(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException("NIT");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("Nombre de la Empresa");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("Nombre de la Empresa");
            }

            if (entity.getTelefonoPrincipal() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Telefono Principal");
            }

            if ((entity.getTelefonoPrincipal() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getTelefonoPrincipal(), 15, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Telefono Principal");
            }

            if (entity.getPsyCiudad().getCiudCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Ciudad");
            }
            Object[] variables = {"nombre",true,entity.getNombre(),"="};
            List<PsyEmpresa> listaValidaEmpresaExistente = findByCriteria(variables, null, null);
            
            if(listaValidaEmpresaExistente.size() > 0){
            	throw new ZMessManager("Ya existe una empresa con ese nombre");
            }

            psyEmpresaDAO.save(entity);

            log.debug("save PsyEmpresa successful");
        } catch (Exception e) {
            log.error("save PsyEmpresa failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyEmpresa(PsyEmpresa entity) throws Exception {
        log.debug("deleting PsyEmpresa instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Empresa");
        }

        if (entity.getEmprCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("Codigo de la Empresa");
        }

        List<PsyPersona> psyPersonas = null;
        List<PsyPlanEstrategico> psyPlanEstrategicos = null;
        List<PsyResponsable> psyResponsables = null;

        try {
            psyPersonas = psyPersonaDAO.findByProperty("psyEmpresa.emprCodigo",
                    entity.getEmprCodigo());

            if (Utilities.validationsList(psyPersonas) == true) {
                throw new ZMessManager().new DeletingException("Responsable Ambiental");
            }

            psyPlanEstrategicos = psyPlanEstrategicoDAO.findByProperty("psyEmpresa.emprCodigo",
                    entity.getEmprCodigo());

            if (Utilities.validationsList(psyPlanEstrategicos) == true) {
                throw new ZMessManager().new DeletingException(
                    "Plan Estrategico");
            }

            psyResponsables = psyResponsableDAO.findByProperty("psyEmpresa.emprCodigo",
                    entity.getEmprCodigo());

            if (Utilities.validationsList(psyResponsables) == true) {
                throw new ZMessManager().new DeletingException(
                    "Responsables de Tareas");
            }

            psyEmpresaDAO.delete(entity);

            log.debug("delete PsyEmpresa successful");
        } catch (Exception e) {
            log.error("delete PsyEmpresa failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyEmpresa(PsyEmpresa entity) throws Exception {
        log.debug("updating PsyEmpresa instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Empresa");
            }

            if (entity.getPsyCiudad() == null) {
                throw new ZMessManager().new ForeignException("Ciudad");
            }

            if (entity.getPsyPersona() == null) {
                throw new ZMessManager().new ForeignException("Responsable Ambiental");
            }

            if ((entity.getDireccionPrincipal() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDireccionPrincipal(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Direccion Principal");
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

            if (entity.getNit() == null) {
                throw new ZMessManager().new EmptyFieldException("NIT");
            }

            if ((entity.getNit() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNit(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException("NIT");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("Nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("Nombre");
            }

            if (entity.getTelefonoPrincipal() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Telefono Principal");
            }

            if ((entity.getTelefonoPrincipal() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getTelefonoPrincipal(), 15, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Telefono Principal");
            }

            if (entity.getPsyCiudad().getCiudCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Ciudad");
            }

            if (entity.getPsyPersona().getPersCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Resonsable Ambiental");
            }

            psyEmpresaDAO.update(entity);

            log.debug("update PsyEmpresa successful");
        } catch (Exception e) {
            log.error("update PsyEmpresa failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyEmpresaDTO> getDataPsyEmpresa() throws Exception {
        try {
            List<PsyEmpresa> psyEmpresa = psyEmpresaDAO.findAll();

            List<PsyEmpresaDTO> psyEmpresaDTO = new ArrayList<PsyEmpresaDTO>();

            for (PsyEmpresa psyEmpresaTmp : psyEmpresa) {
                PsyEmpresaDTO psyEmpresaDTO2 = new PsyEmpresaDTO();

                psyEmpresaDTO2.setEmprCodigo(psyEmpresaTmp.getEmprCodigo());
                psyEmpresaDTO2.setDireccionPrincipal((psyEmpresaTmp.getDireccionPrincipal() != null)
                    ? psyEmpresaTmp.getDireccionPrincipal() : null);
                psyEmpresaDTO2.setEstadoRegistro((psyEmpresaTmp.getEstadoRegistro() != null)
                    ? psyEmpresaTmp.getEstadoRegistro() : null);
                psyEmpresaDTO2.setNit((psyEmpresaTmp.getNit() != null)
                    ? psyEmpresaTmp.getNit() : null);
                psyEmpresaDTO2.setNombre((psyEmpresaTmp.getNombre() != null)
                    ? psyEmpresaTmp.getNombre() : null);
                psyEmpresaDTO2.setTelefonoPrincipal((psyEmpresaTmp.getTelefonoPrincipal() != null)
                    ? psyEmpresaTmp.getTelefonoPrincipal() : null);
                psyEmpresaDTO2.setCiudCodigo_PsyCiudad((psyEmpresaTmp.getPsyCiudad()
                                                                     .getCiudCodigo() != null)
                    ? psyEmpresaTmp.getPsyCiudad().getCiudCodigo() : null);
                psyEmpresaDTO2.setPersCodigo_PsyPersona((psyEmpresaTmp.getPsyPersona()
                                                                      .getPersCodigo() != null)
                    ? psyEmpresaTmp.getPsyPersona().getPersCodigo() : null);
                psyEmpresaDTO.add(psyEmpresaDTO2);
            }

            return psyEmpresaDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyEmpresa getPsyEmpresa(Long emprCodigo) throws Exception {
        log.debug("getting PsyEmpresa instance");

        PsyEmpresa entity = null;

        try {
            entity = psyEmpresaDAO.findById(emprCodigo);
        } catch (Exception e) {
            log.error("get PsyEmpresa failed", e);
            throw new ZMessManager().new FindingException("PsyEmpresa");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyEmpresa> findPagePsyEmpresa(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<PsyEmpresa> entity = null;

        try {
            entity = psyEmpresaDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("PsyEmpresa Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyEmpresa() throws Exception {
        Long entity = null;

        try {
            entity = psyEmpresaDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("PsyEmpresa Count");
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
    public List<PsyEmpresa> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyEmpresa> list = new ArrayList<PsyEmpresa>();
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
            list = psyEmpresaDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
