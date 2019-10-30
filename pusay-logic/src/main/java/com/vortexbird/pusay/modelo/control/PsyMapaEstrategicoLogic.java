package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyMapaEstrategicoDTO;
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
@Service("PsyMapaEstrategicoLogic")
public class PsyMapaEstrategicoLogic implements IPsyMapaEstrategicoLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyMapaEstrategicoLogic.class);
    private static final String ESTADO_ABIERTO = "A";

    /**
     * DAO injected by Spring that manages PsyMapaEstrategico entities
     *
     */
    @Autowired
    private IPsyMapaEstrategicoDAO psyMapaEstrategicoDAO;

    /**
    * DAO injected by Spring that manages PsyDetalleMapaEstrategico entities
    *
    */
    @Autowired
    private IPsyDetalleMapaEstrategicoDAO psyDetalleMapaEstrategicoDAO;

    /**
    * Logic injected by Spring that manages PsyPlanEstrategico entities
    *
    */
    @Autowired
    IPsyPlanEstrategicoLogic logicPsyPlanEstrategico1;

    @Transactional(readOnly = true)
    public List<PsyMapaEstrategico> getPsyMapaEstrategico()
        throws Exception {
        log.debug("finding all PsyMapaEstrategico instances");

        List<PsyMapaEstrategico> list = new ArrayList<PsyMapaEstrategico>();

        try {
            list = psyMapaEstrategicoDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyMapaEstrategico failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyMapaEstrategico");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyMapaEstrategico(PsyMapaEstrategico entity)
        throws Exception {
        log.debug("saving PsyMapaEstrategico instance");

        try {
            if (entity.getPsyPlanEstrategico() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyPlanEstrategico");
            }

            if (entity.getEstadoMapaEstrategico() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "estadoMapaEstrategico");
            }

            if ((entity.getEstadoMapaEstrategico() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoMapaEstrategico(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoMapaEstrategico");
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

            if (entity.getFechaInicio() == null) {
                throw new ZMessManager().new EmptyFieldException("fechaInicio");
            }

            if (entity.getPsyPlanEstrategico().getPestCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "pestCodigo_PsyPlanEstrategico");
            }

            psyMapaEstrategicoDAO.save(entity);

            log.debug("save PsyMapaEstrategico successful");
        } catch (Exception e) {
            log.error("save PsyMapaEstrategico failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyMapaEstrategico(PsyMapaEstrategico entity)
        throws Exception {
        log.debug("deleting PsyMapaEstrategico instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "PsyMapaEstrategico");
        }

        if (entity.getMaesCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("maesCodigo");
        }

        List<PsyDetalleMapaEstrategico> psyDetalleMapaEstrategicos = null;

        try {
            psyDetalleMapaEstrategicos = psyDetalleMapaEstrategicoDAO.findByProperty("psyMapaEstrategico.maesCodigo",
                    entity.getMaesCodigo());

            if (Utilities.validationsList(psyDetalleMapaEstrategicos) == true) {
                throw new ZMessManager().new DeletingException(
                    "psyDetalleMapaEstrategicos");
            }

            psyMapaEstrategicoDAO.delete(entity);

            log.debug("delete PsyMapaEstrategico successful");
        } catch (Exception e) {
            log.error("delete PsyMapaEstrategico failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyMapaEstrategico(PsyMapaEstrategico entity)
        throws Exception {
        log.debug("updating PsyMapaEstrategico instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "PsyMapaEstrategico");
            }

            if (entity.getPsyPlanEstrategico() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyPlanEstrategico");
            }

            if (entity.getEstadoMapaEstrategico() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "estadoMapaEstrategico");
            }

            if ((entity.getEstadoMapaEstrategico() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoMapaEstrategico(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoMapaEstrategico");
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

            if (entity.getFechaInicio() == null) {
                throw new ZMessManager().new EmptyFieldException("fechaInicio");
            }

            if (entity.getMaesCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("maesCodigo");
            }

            if (entity.getPsyPlanEstrategico().getPestCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "pestCodigo_PsyPlanEstrategico");
            }

            psyMapaEstrategicoDAO.update(entity);

            log.debug("update PsyMapaEstrategico successful");
        } catch (Exception e) {
            log.error("update PsyMapaEstrategico failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyMapaEstrategicoDTO> getDataPsyMapaEstrategico()
        throws Exception {
        try {
            List<PsyMapaEstrategico> psyMapaEstrategico = psyMapaEstrategicoDAO.findAll();

            List<PsyMapaEstrategicoDTO> psyMapaEstrategicoDTO = new ArrayList<PsyMapaEstrategicoDTO>();

            for (PsyMapaEstrategico psyMapaEstrategicoTmp : psyMapaEstrategico) {
                PsyMapaEstrategicoDTO psyMapaEstrategicoDTO2 = new PsyMapaEstrategicoDTO();

                psyMapaEstrategicoDTO2.setMaesCodigo(psyMapaEstrategicoTmp.getMaesCodigo());
                psyMapaEstrategicoDTO2.setEstadoMapaEstrategico((psyMapaEstrategicoTmp.getEstadoMapaEstrategico() != null)
                    ? psyMapaEstrategicoTmp.getEstadoMapaEstrategico() : null);
                psyMapaEstrategicoDTO2.setEstadoRegistro((psyMapaEstrategicoTmp.getEstadoRegistro() != null)
                    ? psyMapaEstrategicoTmp.getEstadoRegistro() : null);
                psyMapaEstrategicoDTO2.setFechaFin(psyMapaEstrategicoTmp.getFechaFin());
                psyMapaEstrategicoDTO2.setFechaInicio(psyMapaEstrategicoTmp.getFechaInicio());
                psyMapaEstrategicoDTO2.setPestCodigo_PsyPlanEstrategico((psyMapaEstrategicoTmp.getPsyPlanEstrategico()
                                                                                              .getPestCodigo() != null)
                    ? psyMapaEstrategicoTmp.getPsyPlanEstrategico()
                                           .getPestCodigo() : null);
                psyMapaEstrategicoDTO.add(psyMapaEstrategicoDTO2);
            }

            return psyMapaEstrategicoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyMapaEstrategico getPsyMapaEstrategico(Long maesCodigo)
        throws Exception {
        log.debug("getting PsyMapaEstrategico instance");

        PsyMapaEstrategico entity = null;

        try {
            entity = psyMapaEstrategicoDAO.findById(maesCodigo);
        } catch (Exception e) {
            log.error("get PsyMapaEstrategico failed", e);
            throw new ZMessManager().new FindingException("PsyMapaEstrategico");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyMapaEstrategico> findPagePsyMapaEstrategico(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<PsyMapaEstrategico> entity = null;

        try {
            entity = psyMapaEstrategicoDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyMapaEstrategico Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyMapaEstrategico() throws Exception {
        Long entity = null;

        try {
            entity = psyMapaEstrategicoDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyMapaEstrategico Count");
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
    public List<PsyMapaEstrategico> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyMapaEstrategico> list = new ArrayList<PsyMapaEstrategico>();
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
            list = psyMapaEstrategicoDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public PsyMapaEstrategico consultarMapaEstrategico(PsyPlanEstrategico planEstrategico, String estado)
            throws Exception {
    	
        List<PsyMapaEstrategico> lstMapaEstrategico = null;
        PsyMapaEstrategico mapaEstrategico = null;
    	
        if(estado == null){
        	Object[] variablesMapaEstrategico = { "psyPlanEstrategico.pestCodigo", false,
    				planEstrategico.getPestCodigo(), "=" , "estadoRegistro", true,
    				"A", "=" };
        	lstMapaEstrategico = findByCriteria(variablesMapaEstrategico, null, null);
        }else{
    	//Consultamos si existe objetivos para el plan con estado
		Object[] variablesMapaEstrategico = { "psyPlanEstrategico.pestCodigo", false,
				planEstrategico.getPestCodigo(), "=", "estadoObjetivoPlan", true,
				estado, "=", "estadoRegistro", true,
				"A", "=" };
		lstMapaEstrategico = findByCriteria(variablesMapaEstrategico, null, null);
        }
        
		//Si se encuentra objetivos para el plan estrategico activos los muestro
		//En caso de no encontrar activos creo un nuevo objetivos plan
		if(lstMapaEstrategico!=null && !lstMapaEstrategico.isEmpty()){
			mapaEstrategico = lstMapaEstrategico.get(0);
		}else{
			mapaEstrategico = new PsyMapaEstrategico();	
			mapaEstrategico.setEstadoMapaEstrategico(ESTADO_ABIERTO);
			mapaEstrategico.setEstadoRegistro("A");
			mapaEstrategico.setFechaInicio(new Date());
			mapaEstrategico.setPsyPlanEstrategico(planEstrategico);
			savePsyMapaEstrategico(mapaEstrategico);
		}		
		return mapaEstrategico;
    }
}
