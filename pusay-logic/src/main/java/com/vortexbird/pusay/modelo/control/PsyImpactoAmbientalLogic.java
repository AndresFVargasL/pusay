package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.exceptions.ZMessManager.DeletingException;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyImpactoAmbientalDTO;
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
@Service("PsyImpactoAmbientalLogic")
public class PsyImpactoAmbientalLogic implements IPsyImpactoAmbientalLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyImpactoAmbientalLogic.class);

    /**
     * DAO injected by Spring that manages PsyImpactoAmbiental entities
     *
     */
    @Autowired
    private IPsyImpactoAmbientalDAO psyImpactoAmbientalDAO;

    /**
    * DAO injected by Spring that manages PsyDetalleErida entities
    *
    */
    @Autowired
    private IPsyDetalleEridaDAO psyDetalleEridaDAO;

    /**
    * DAO injected by Spring that manages PsyMatrizCorrelacion entities
    *
    */
    @Autowired
    private IPsyMatrizCorrelacionDAO psyMatrizCorrelacionDAO;
    
    /**
     * DAO injected by Spring that manages PsyTema entities
     *
     */
     @Autowired
     private IPsyImpactoObjetivoDAO psyImpactoObjetivoDAO;

     @Autowired
     private IPsyTemaDAO psyTemaDAO;
     
     @Autowired
     private IPsyObjetivoImpactoDAO psyObjetivoImpactoDAO;

    @Transactional(readOnly = true)
    public List<PsyImpactoAmbiental> getPsyImpactoAmbiental()
        throws Exception {
        log.debug("finding all PsyImpactoAmbiental instances");

        List<PsyImpactoAmbiental> list = new ArrayList<PsyImpactoAmbiental>();

        try {
            list = psyImpactoAmbientalDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyImpactoAmbiental failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyImpactoAmbiental");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyImpactoAmbiental(PsyImpactoAmbiental entity)
        throws Exception {
        log.debug("saving PsyImpactoAmbiental instance");

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



            psyImpactoAmbientalDAO.save(entity);

            log.debug("save PsyImpactoAmbiental successful");
        } catch (Exception e) {
            log.error("save PsyImpactoAmbiental failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyImpactoAmbiental(PsyImpactoAmbiental entity)
        throws Exception {
        log.debug("deleting PsyImpactoAmbiental instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "PsyImpactoAmbiental");
        }

        if (entity.getImamCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("imamCodigo");
        }

        List<PsyDetalleErida> psyDetalleEridas = null;
        List<PsyImpactoObjetivo> psyImpactoObjetivos = null;
        List<PsyMatrizCorrelacion> psyMatrizCorrelacions = null;
        List<PsyObjetivoImpacto> psyObjetivoImpactos = null;
        List<PsyTema> psyTemas = null;

        try {
            psyDetalleEridas = psyDetalleEridaDAO.findByProperty("psyImpactoAmbiental.imamCodigo",
                    entity.getImamCodigo());

            if (Utilities.validationsList(psyDetalleEridas) == true) {
                throw new ZMessManager().new DeletingException(
                    "psyDetalleEridas");
            }
            
            psyImpactoObjetivos = psyImpactoObjetivoDAO.findByProperty("psyImpactoAmbiental.imamCodigo",
                    entity.getImamCodigo());

            if (Utilities.validationsList(psyImpactoObjetivos) == true) {
                throw new ZMessManager().new DeletingException(
                    "psyImpactoObjetivos");
            }


            psyMatrizCorrelacions = psyMatrizCorrelacionDAO.findByProperty("psyImpactoAmbiental.imamCodigo",
                    entity.getImamCodigo());

            if (Utilities.validationsList(psyMatrizCorrelacions) == true) {
                throw new ZMessManager().new DeletingException(
                    "psyMatrizCorrelacions");
            }
            
            psyObjetivoImpactos = psyObjetivoImpactoDAO.findByProperty("psyImpactoAmbiental.imamCodigo",
                    entity.getImamCodigo());

            if (Utilities.validationsList(psyObjetivoImpactos) == true) {
                throw new ZMessManager().new DeletingException(
                    "psyObjetivoImpactos");
            }
            
            psyTemas = psyTemaDAO.findByProperty("psyImpactoAmbiental.imamCodigo",
                    entity.getImamCodigo());

            if (Utilities.validationsList(psyTemas) == true) {
                throw new ZMessManager().new DeletingException("psyTemas");
            }


            psyImpactoAmbientalDAO.delete(entity);

            log.debug("delete PsyImpactoAmbiental successful");
        } catch (Exception e) {
            log.error("delete PsyImpactoAmbiental failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyImpactoAmbiental(PsyImpactoAmbiental entity)
        throws Exception {
        log.debug("updating PsyImpactoAmbiental instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "PsyImpactoAmbiental");
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

            psyImpactoAmbientalDAO.update(entity);

            log.debug("update PsyImpactoAmbiental successful");
        } catch (Exception e) {
            log.error("update PsyImpactoAmbiental failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyImpactoAmbientalDTO> getDataPsyImpactoAmbiental(PsyEmpresa empresa)
        throws Exception {
        try {
            List<PsyImpactoAmbiental> psyImpactoAmbientalSeleccionado = psyImpactoAmbientalDAO.consultaImpactosAmbientalesSeleccionados(empresa);
            List<PsyImpactoAmbiental> psyImpactos = psyImpactoAmbientalDAO.findAll();

            List<PsyImpactoAmbientalDTO> psyImpactoAmbientalDTO = new ArrayList<PsyImpactoAmbientalDTO>();

            for (PsyImpactoAmbiental psyImpactoAmbientalTmp : psyImpactos) {
                PsyImpactoAmbientalDTO psyImpactoAmbientalDTO2 = new PsyImpactoAmbientalDTO();

                psyImpactoAmbientalDTO2.setImamCodigo(psyImpactoAmbientalTmp.getImamCodigo());
                psyImpactoAmbientalDTO2.setDescripcion((psyImpactoAmbientalTmp.getDescripcion() != null)
                    ? psyImpactoAmbientalTmp.getDescripcion() : null);
                psyImpactoAmbientalDTO2.setEstadoRegistro((psyImpactoAmbientalTmp.getEstadoRegistro() != null)
                    ? psyImpactoAmbientalTmp.getEstadoRegistro() : null);
                psyImpactoAmbientalDTO2.setNombre((psyImpactoAmbientalTmp.getNombre() != null)
                    ? psyImpactoAmbientalTmp.getNombre() : null);
                for (PsyImpactoAmbiental psyImpactoAmbientalTemp : psyImpactoAmbientalSeleccionado) {
					if(psyImpactoAmbientalTmp.getNombre().trim().equals(psyImpactoAmbientalTemp.getNombre().trim())){
						psyImpactoAmbientalDTO2.setDisabled(false);
					}
				}
                psyImpactoAmbientalDTO.add(psyImpactoAmbientalDTO2);
            }
            
            

            return psyImpactoAmbientalDTO;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Transactional(readOnly = true)
    public List<PsyImpactoAmbientalDTO> getDataPsyImpactoAmbientalGestion()
        throws Exception {
        try {
            List<PsyImpactoAmbiental> psyImpactos = psyImpactoAmbientalDAO.findAll();

            List<PsyImpactoAmbientalDTO> psyImpactoAmbientalDTO = new ArrayList<PsyImpactoAmbientalDTO>();

            for (PsyImpactoAmbiental psyImpactoAmbientalTmp : psyImpactos) {
                PsyImpactoAmbientalDTO psyImpactoAmbientalDTO2 = new PsyImpactoAmbientalDTO();

                psyImpactoAmbientalDTO2.setImamCodigo(psyImpactoAmbientalTmp.getImamCodigo());
                psyImpactoAmbientalDTO2.setDescripcion((psyImpactoAmbientalTmp.getDescripcion() != null)
                    ? psyImpactoAmbientalTmp.getDescripcion() : null);
                psyImpactoAmbientalDTO2.setEstadoRegistro((psyImpactoAmbientalTmp.getEstadoRegistro() != null)
                    ? (psyImpactoAmbientalTmp.getEstadoRegistro().trim().equals("A")) ? "Activo" : "Inactivo" : null);
                psyImpactoAmbientalDTO2.setNombre((psyImpactoAmbientalTmp.getNombre() != null)
                    ? psyImpactoAmbientalTmp.getNombre() : null);

                psyImpactoAmbientalDTO.add(psyImpactoAmbientalDTO2);
            }
            
            

            return psyImpactoAmbientalDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyImpactoAmbiental getPsyImpactoAmbiental(Long imamCodigo)
        throws Exception {
        log.debug("getting PsyImpactoAmbiental instance");

        PsyImpactoAmbiental entity = null;

        try {
            entity = psyImpactoAmbientalDAO.findById(imamCodigo);
        } catch (Exception e) {
            log.error("get PsyImpactoAmbiental failed", e);
            throw new ZMessManager().new FindingException("PsyImpactoAmbiental");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyImpactoAmbiental> findPagePsyImpactoAmbiental(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<PsyImpactoAmbiental> entity = null;

        try {
            entity = psyImpactoAmbientalDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyImpactoAmbiental Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyImpactoAmbiental() throws Exception {
        Long entity = null;

        try {
            entity = psyImpactoAmbientalDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyImpactoAmbiental Count");
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
    public List<PsyImpactoAmbiental> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyImpactoAmbiental> list = new ArrayList<PsyImpactoAmbiental>();
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
            list = psyImpactoAmbientalDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
    @Transactional(readOnly = true)
	public Long countTemasPorImpacto(Long codigo) throws Exception {
		if(codigo==null){
			throw new ZMessManager("Debe haber un impacto ambiental para poder hacer este tipo de consultas");
		}
		return psyImpactoAmbientalDAO.countTemasPorImpacto(codigo);
	}
    
    @Transactional(readOnly = true)
	public Long countSubTemasPorImpacto(Long codigo) throws Exception {
    	if(codigo==null){
			throw new ZMessManager("Debe haber un impacto ambiental para poder hacer este tipo de consultas");
		}
    	return psyImpactoAmbientalDAO.countSubTemasPorImpacto(codigo);
	}
    
    @Transactional(readOnly = true)
	public Long countIndicadoresPorImpacto(Long codigo) throws Exception {
    	if(codigo==null){
			throw new ZMessManager("Debe haber un impacto ambiental para poder hacer este tipo de consultas");
		}
    	return psyImpactoAmbientalDAO.countIndicadoresPorImpacto(codigo);
	}
    
    @Transactional(readOnly = true)
    public List<PsyImpactoAmbiental> consultaImpactosAmbientalesSeleccionados(PsyEmpresa empresa) throws Exception {
    	if(empresa==null){
			throw new ZMessManager("La empresa no puede ser nula");
		}
    	return psyImpactoAmbientalDAO.consultaImpactosAmbientalesSeleccionados(empresa);
    }
    

}
