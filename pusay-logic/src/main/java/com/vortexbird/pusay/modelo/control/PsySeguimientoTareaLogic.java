package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsySeguimientoTareaDTO;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("PsySeguimientoTareaLogic")
public class PsySeguimientoTareaLogic implements IPsySeguimientoTareaLogic {
    private static final Logger log = LoggerFactory.getLogger(PsySeguimientoTareaLogic.class);

    /**
     * DAO injected by Spring that manages PsySeguimientoTarea entities
     *
     */
    @Autowired
    private IPsySeguimientoTareaDAO psySeguimientoTareaDAO;

    /**
    * Logic injected by Spring that manages PsyTarea entities
    *
    */
    @Autowired
    IPsyTareaLogic logicPsyTarea1;

    @Transactional(readOnly = true)
    public List<PsySeguimientoTarea> getPsySeguimientoTarea()
        throws Exception {
        log.debug("finding all PsySeguimientoTarea instances");

        List<PsySeguimientoTarea> list = new ArrayList<PsySeguimientoTarea>();

        try {
            list = psySeguimientoTareaDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsySeguimientoTarea failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsySeguimientoTarea");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsySeguimientoTarea(PsySeguimientoTarea entity)
        throws Exception {
        log.debug("Guardando instanca de PsySeguimientoTarea");

        try {
            if (entity.getPsyTarea() == null) {
                throw new ZMessManager().new ForeignException("Tarea");
            }

            if (entity.getDescripcion() == null) {
                throw new ZMessManager().new EmptyFieldException("Descripcion");
            }

            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Descripcion");
            }
            
            if(entity.getDescripcion().trim().equals("")){
            	throw new ZMessManager("");
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

            

            if (entity.getPsyTarea().getTareCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Codigo de la Tarea");
            }
            
            if(entity.getFecha()==null){
            	throw new ZMessManager("Debe escoger una fecha");
            }
            
            
            
            if(entity.getFecha().before(getYesterday())){
            	throw new ZMessManager("No puede utilizar fechas pasadas");
            }


            psySeguimientoTareaDAO.save(entity);

            log.debug("Se guardo exitosamente el seguimiento de la tarea");
        } catch (Exception e) {
            log.error("Fallo al guardar el seguimiento de la tarea", e);
            throw e;
        } finally {
        }
    }
    
    public Date getYesterday() throws Exception {
    	Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        Date date = c.getTime();
        return date;
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsySeguimientoTarea(PsySeguimientoTarea entity)
        throws Exception {
        log.debug("Eliminando el seguimiento de la tarea");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "Seguimiento de la Tarea");
        }

        if (entity.getSetaCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("Codigo");
        }

        try {
            psySeguimientoTareaDAO.delete(entity);

            log.debug("Eliminacion del seguimiento de la tarea exitoso");
        } catch (Exception e) {
            log.error("Fallo al eliminar el seguimiento de la tarea", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsySeguimientoTarea(PsySeguimientoTarea entity)
        throws Exception {
        log.debug("Actualizando el seguimiento de la tarea");

        try {
        	if (entity.getPsyTarea() == null) {
                throw new ZMessManager().new ForeignException("Tarea");
            }

            if (entity.getDescripcion() == null) {
                throw new ZMessManager().new EmptyFieldException("Descripcion");
            }

            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Descripcion");
            }
            
            if(entity.getDescripcion().trim().equals("")){
            	throw new ZMessManager("");
            }

            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Estado Registro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Estado Regitro");
            }

            

            if (entity.getPsyTarea().getTareCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Codigo de la Tarea");
            }
            
            if(entity.getFecha()==null){
            	throw new ZMessManager("Debe escoger una fecha");
            }
            
            
            
            if(entity.getFecha().before(getYesterday())){
            	throw new ZMessManager("No puede utilizar fechas pasadas");
            }

            psySeguimientoTareaDAO.update(entity);

            log.debug("Actualizacion exitosa del seguimiento de la tarea");
        } catch (Exception e) {
            log.error("Fallo al actualizar el seguimiento de la tarea", e);
            throw e;
        } finally {
        }
    }
    
    @Transactional(readOnly = true)
    public List<PsySeguimientoTareaDTO> getDataPsySeguimientoTareaByTareCodigo(Long tareCodigo) throws Exception {
        try {
        	Object[] variables ={"psyTarea.tareCodigo",false,tareCodigo,"="};
        	
            List<PsySeguimientoTarea> psySeguimientoTarea = findByCriteria(variables, null, null);

            List<PsySeguimientoTareaDTO> psySeguimientoTareaDTO = new ArrayList<PsySeguimientoTareaDTO>();

            for (PsySeguimientoTarea psySeguimientoTareaTmp : psySeguimientoTarea) {
                PsySeguimientoTareaDTO psySeguimientoTareaDTO2 = new PsySeguimientoTareaDTO();

                psySeguimientoTareaDTO2.setSetaCodigo(psySeguimientoTareaTmp.getSetaCodigo());
                psySeguimientoTareaDTO2.setDescripcion((psySeguimientoTareaTmp.getDescripcion() != null)
                    ? psySeguimientoTareaTmp.getDescripcion() : null);
                psySeguimientoTareaDTO2.setEstadoRegistro((psySeguimientoTareaTmp.getEstadoRegistro() != null)
                    ? psySeguimientoTareaTmp.getEstadoRegistro() : null);
                psySeguimientoTareaDTO2.setFecha(psySeguimientoTareaTmp.getFecha());
                psySeguimientoTareaDTO2.setTareCodigo_PsyTarea((psySeguimientoTareaTmp.getPsyTarea()
                                                                                      .getTareCodigo() != null)
                    ? psySeguimientoTareaTmp.getPsyTarea().getTareCodigo() : null);
                psySeguimientoTareaDTO.add(psySeguimientoTareaDTO2);
            }

            return psySeguimientoTareaDTO;
        } catch (Exception e) {
        	throw new ZMessManager("No se pudo cargar los seguimientos de la tarea");
        }
    }

    @Transactional(readOnly = true)
    public List<PsySeguimientoTareaDTO> getDataPsySeguimientoTarea()
        throws Exception {
        try {
            List<PsySeguimientoTarea> psySeguimientoTarea = psySeguimientoTareaDAO.findAll();

            List<PsySeguimientoTareaDTO> psySeguimientoTareaDTO = new ArrayList<PsySeguimientoTareaDTO>();

            for (PsySeguimientoTarea psySeguimientoTareaTmp : psySeguimientoTarea) {
                PsySeguimientoTareaDTO psySeguimientoTareaDTO2 = new PsySeguimientoTareaDTO();

                psySeguimientoTareaDTO2.setSetaCodigo(psySeguimientoTareaTmp.getSetaCodigo());
                psySeguimientoTareaDTO2.setDescripcion((psySeguimientoTareaTmp.getDescripcion() != null)
                    ? psySeguimientoTareaTmp.getDescripcion() : null);
                psySeguimientoTareaDTO2.setEstadoRegistro((psySeguimientoTareaTmp.getEstadoRegistro() != null)
                    ? psySeguimientoTareaTmp.getEstadoRegistro() : null);
                psySeguimientoTareaDTO2.setFecha(psySeguimientoTareaTmp.getFecha());
                psySeguimientoTareaDTO2.setTareCodigo_PsyTarea((psySeguimientoTareaTmp.getPsyTarea()
                                                                                      .getTareCodigo() != null)
                    ? psySeguimientoTareaTmp.getPsyTarea().getTareCodigo() : null);
                psySeguimientoTareaDTO.add(psySeguimientoTareaDTO2);
            }

            return psySeguimientoTareaDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsySeguimientoTarea getPsySeguimientoTarea(Long setaCodigo)
        throws Exception {
        log.debug("getting PsySeguimientoTarea instance");

        PsySeguimientoTarea entity = null;

        try {
            entity = psySeguimientoTareaDAO.findById(setaCodigo);
        } catch (Exception e) {
            log.error("get PsySeguimientoTarea failed", e);
            throw new ZMessManager().new FindingException("PsySeguimientoTarea");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsySeguimientoTarea> findPagePsySeguimientoTarea(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<PsySeguimientoTarea> entity = null;

        try {
            entity = psySeguimientoTareaDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsySeguimientoTarea Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsySeguimientoTarea() throws Exception {
        Long entity = null;

        try {
            entity = psySeguimientoTareaDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsySeguimientoTarea Count");
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
    public List<PsySeguimientoTarea> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsySeguimientoTarea> list = new ArrayList<PsySeguimientoTarea>();
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
            list = psySeguimientoTareaDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
