package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.cuestionarios.model.CueOpcion;
import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyMatrizEncuestaDTO;
import com.vortexbird.pusay.modelo.dto.RespuestaEncuestasDTO;
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
@Service("PsyMatrizEncuestaLogic")
public class PsyMatrizEncuestaLogic implements IPsyMatrizEncuestaLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyMatrizEncuestaLogic.class);

    /**
     * DAO injected by Spring that manages PsyMatrizEncuesta entities
     *
     */
    @Autowired
    private IPsyMatrizEncuestaDAO psyMatrizEncuestaDAO;

    /**
    * Logic injected by Spring that manages PsyPlanEstrategico entities
    *
    */
    @Autowired
    IPsyPlanEstrategicoLogic logicPsyPlanEstrategico1;
    
    @Autowired
    IPsyPlanEstrategicoAmbientalLogic logicPsyPlanEstrategicoAmbiental;
    
    

    @Transactional(readOnly = true)
    public List<PsyMatrizEncuesta> getPsyMatrizEncuesta()
        throws Exception {
        log.debug("finding all PsyMatrizEncuesta instances");

        List<PsyMatrizEncuesta> list = new ArrayList<PsyMatrizEncuesta>();

        try {
            list = psyMatrizEncuestaDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyMatrizEncuesta failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyMatrizEncuesta");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyMatrizEncuesta(PsyMatrizEncuesta entity)
        throws Exception {
        log.debug("saving PsyMatrizEncuesta instance");

        try {
            if (entity.getPsyPlanEstrategico() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyPlanEstrategico");
            }

            if (entity.getCodigoEncuesta() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "codigoEncuesta");
            }

            if ((entity.getCodigoEncuesta() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCodigoEncuesta(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "codigoEncuesta");
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

            

            if (entity.getPsyPlanEstrategico().getPestCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "pestCodigo_PsyPlanEstrategico");
            }

            

            psyMatrizEncuestaDAO.save(entity);

            log.debug("save PsyMatrizEncuesta successful");
        } catch (Exception e) {
            log.error("save PsyMatrizEncuesta failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyMatrizEncuesta(PsyMatrizEncuesta entity)
        throws Exception {
        log.debug("deleting PsyMatrizEncuesta instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "PsyMatrizEncuesta");
        }

        if (entity.getMaenCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("maenCodigo");
        }

        try {
            psyMatrizEncuestaDAO.delete(entity);

            log.debug("delete PsyMatrizEncuesta successful");
        } catch (Exception e) {
            log.error("delete PsyMatrizEncuesta failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyMatrizEncuesta(PsyMatrizEncuesta entity)
        throws Exception {
        log.debug("updating PsyMatrizEncuesta instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "PsyMatrizEncuesta");
            }

            if (entity.getPsyPlanEstrategico() == null) {
                throw new ZMessManager().new ForeignException(
                    "psyPlanEstrategico");
            }

            if (entity.getCodigoEncuesta() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "codigoEncuesta");
            }

            if ((entity.getCodigoEncuesta() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCodigoEncuesta(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "codigoEncuesta");
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

           

            if (entity.getPsyPlanEstrategico().getPestCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "pestCodigo_PsyPlanEstrategico");
            }

            psyMatrizEncuestaDAO.update(entity);

            log.debug("update PsyMatrizEncuesta successful");
        } catch (Exception e) {
            log.error("update PsyMatrizEncuesta failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyMatrizEncuestaDTO> getDataPsyMatrizEncuesta()
        throws Exception {
        try {
            List<PsyMatrizEncuesta> psyMatrizEncuesta = psyMatrizEncuestaDAO.findAll();

            List<PsyMatrizEncuestaDTO> psyMatrizEncuestaDTO = new ArrayList<PsyMatrizEncuestaDTO>();

            for (PsyMatrizEncuesta psyMatrizEncuestaTmp : psyMatrizEncuesta) {
                PsyMatrizEncuestaDTO psyMatrizEncuestaDTO2 = new PsyMatrizEncuestaDTO();

                psyMatrizEncuestaDTO2.setMaenCodigo(psyMatrizEncuestaTmp.getMaenCodigo());
                psyMatrizEncuestaDTO2.setCodigoEncuesta((psyMatrizEncuestaTmp.getCodigoEncuesta() != null)
                    ? psyMatrizEncuestaTmp.getCodigoEncuesta() : null);
                psyMatrizEncuestaDTO2.setEstadoRegistro((psyMatrizEncuestaTmp.getEstadoRegistro() != null)
                    ? psyMatrizEncuestaTmp.getEstadoRegistro() : null);
                psyMatrizEncuestaDTO2.setPestCodigo_PsyPlanEstrategico((psyMatrizEncuestaTmp.getPsyPlanEstrategico()
                                                                                            .getPestCodigo() != null)
                    ? psyMatrizEncuestaTmp.getPsyPlanEstrategico()
                                          .getPestCodigo() : null);
                psyMatrizEncuestaDTO.add(psyMatrizEncuestaDTO2);
            }

            return psyMatrizEncuestaDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyMatrizEncuesta getPsyMatrizEncuesta(Long maenCodigo)
        throws Exception {
        log.debug("getting PsyMatrizEncuesta instance");

        PsyMatrizEncuesta entity = null;

        try {
            entity = psyMatrizEncuestaDAO.findById(maenCodigo);
        } catch (Exception e) {
            log.error("get PsyMatrizEncuesta failed", e);
            throw new ZMessManager().new FindingException("PsyMatrizEncuesta");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyMatrizEncuesta> findPagePsyMatrizEncuesta(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<PsyMatrizEncuesta> entity = null;

        try {
            entity = psyMatrizEncuestaDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyMatrizEncuesta Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyMatrizEncuesta() throws Exception {
        Long entity = null;

        try {
            entity = psyMatrizEncuestaDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "PsyMatrizEncuesta Count");
        } finally {
        }

        return entity;
    }
    
    @Transactional(readOnly = true)
    public List<RespuestaEncuestasDTO> consultarResultadoEncuestas(PsyEmpresa empresa) throws Exception {
    	
    	
        PsyPlanEstrategico planEstrategicoActivo = logicPsyPlanEstrategicoAmbiental.getPlanEstrategicoActivoByPEA(empresa);
        String calificacionCuestionario1 = "";
        String calificacionCuestionario2 = "";
        String calificacionCuestionario3 = "";
        for (int i = 0; i <  3 ; i++) {
			
		
        Long codigoCuestionario=Long.valueOf(i+1);
        List<CueOpcion> respuestasCuestionario =  
        		psyMatrizEncuestaDAO.consultaRespuestasPorCuestionario(planEstrategicoActivo.getPestCodigo(), codigoCuestionario);
       
        for (CueOpcion cueOpcion : respuestasCuestionario) {
			if(codigoCuestionario==1){
				if(cueOpcion.getEnunciado().trim().equals("No")){
					calificacionCuestionario1 = "Deficiente";
				}else{
					calificacionCuestionario1 = "Eficiente";
				}
			}
			if(codigoCuestionario==2){
				if(cueOpcion.getEnunciado().trim().equals("1")){
					calificacionCuestionario2 = "Alto";
				}else if(cueOpcion.getEnunciado().trim().equals("2")){
					calificacionCuestionario2 = "Alto";
				}else if(cueOpcion.getEnunciado().trim().equals("3")){
					calificacionCuestionario2 = "Alto";
				}else{
					calificacionCuestionario2 = "Bajo";
				}
			}
			if(codigoCuestionario==3){
				if(cueOpcion.getEnunciado().trim().equals("No")){
					calificacionCuestionario3 = "Limitado";
				}else{
					calificacionCuestionario3 = "Optimo";
				}
			}
		}
        
        }
        List<RespuestaEncuestasDTO> resultadoCuestionario = new ArrayList<RespuestaEncuestasDTO>();
        
        for (int i = 0; i < 3; i++) {
			RespuestaEncuestasDTO respuesta = new RespuestaEncuestasDTO();
			if((i+1)==1){
			respuesta.setNombreCuestionario("Encuesta Desempeño Ambiental");
			respuesta.setResultado(calificacionCuestionario1);
			}
			if((i+1)==2){
				respuesta.setNombreCuestionario("Encuesta Grado de Afectacion");
				respuesta.setResultado(calificacionCuestionario2);
			}
			if((i+1)==3){
				respuesta.setNombreCuestionario("Encuesta Desempeño Nivel tecnico");
				respuesta.setResultado(calificacionCuestionario3);
			}
			
			resultadoCuestionario.add(respuesta);
			
		}
        
        return resultadoCuestionario;
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
    public List<PsyMatrizEncuesta> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyMatrizEncuesta> list = new ArrayList<PsyMatrizEncuesta>();
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
            list = psyMatrizEncuestaDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
