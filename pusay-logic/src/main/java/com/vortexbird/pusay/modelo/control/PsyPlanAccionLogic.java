package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.exceptions.ZMessManager.EmptyFieldException;
import com.vortexbird.pusay.exceptions.ZMessManager.ForeignException;
import com.vortexbird.pusay.exceptions.ZMessManager.NotValidFormatException;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyPlanAccionDTO;
import com.vortexbird.pusay.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
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
@Service("PsyPlanAccionLogic")
public class PsyPlanAccionLogic implements IPsyPlanAccionLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyPlanAccionLogic.class);
    private static final String ESTADO_REGISTRO_ACTIVO = "A";

    /**
     * DAO injected by Spring that manages PsyPlanAccion entities
     *
     */
    @Autowired
    private IPsyPlanAccionDAO psyPlanAccionDAO;

    /**
    * DAO injected by Spring that manages PsyIpu entities
    *
    */
    @Autowired
    private IPsyIpuDAO psyIpuDAO;

    /**
    * DAO injected by Spring that manages PsyPlanEstrategia entities
    *
    */
    @Autowired
    private IPsyPlanEstrategiaDAO psyPlanEstrategiaDAO;

    /**
    * DAO injected by Spring that manages PsyPresupuesto entities
    *
    */
    @Autowired
    private IPsyPresupuestoDAO psyPresupuestoDAO;

    /**
    * DAO injected by Spring that manages PsyTarea entities
    *
    */
    @Autowired
    private IPsyTareaDAO psyTareaDAO;
    @Autowired
    private IPsyIpuLogic psyIpuLogic;
    @Autowired
    private IPsyTareaLogic psyTareaLogic;
    @Autowired
    private IPsyPlanEstrategicoLogic psyPlanEstrategicoLogic2;
    @Autowired
    private IPsyPlanEstrategiaLogic psyPlanEstrategiaLogic3;
    

    @Transactional(readOnly = true)
    public List<PsyPlanAccion> getPsyPlanAccion() throws Exception {
        log.debug("Buscando todas las instancias del Planes de Accion");

        List<PsyPlanAccion> list = new ArrayList<PsyPlanAccion>();

        try {
            list = psyPlanAccionDAO.findAll();
        } catch (Exception e) {
            log.error("Busqueda de Planes de Accion fallida", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyPlanAccion");
        } finally {
        }

        return list;
    }
    
    @Transactional(readOnly = true)
    public PsyPlanAccion getPsyPlanAccionByName(String nombre) throws Exception {
        log.debug("Buscando todas las instancias del Planes de Accion");

        List<PsyPlanAccion> list = new ArrayList<PsyPlanAccion>();
    	
     	
    	try {
    		
    		Object[] variablesPlanAccion = { "nombre", true,
       		     nombre, "=" };
       		list = findByCriteria(variablesPlanAccion, null, null);
			
		} catch (Exception e) {
			throw new ZMessManager("Error buscando planes de accion");
		} 
    	
    	return list.get(0);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyPlanAccion(PsyPlanAccion entity)
        throws Exception {
        log.debug("Guardando un plan de accion");

        try {
            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Descripcion");
            }

            if (entity.getEstadoPlanAccion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Estado del Plan");
            }

            if ((entity.getEstadoPlanAccion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoPlanAccion(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Estado del Plan");
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

            if (entity.getFechaFinPlaneada() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Fecha Final Planeada");
            }

            if (entity.getFechaInicio() == null) {
                throw new ZMessManager().new EmptyFieldException("Fecha de Inicio");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("Nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("Nombre");
            }
            
            if(entity.getFechaFinPlaneada().before(entity.getFechaInicio())){
            	throw new ZMessManager("La fecha final planeada no puede ser anterior a la fecha inicial");
            }
            
            if(entity.getFechaFinReal() != null){
            	if(entity.getFechaFinReal().before(entity.getFechaInicio())){
                	throw new ZMessManager("La fecha final real no puede ser anterior a la fecha inicial");
                }
                
//                if(entity.getFechaFinReal().before(new Date())){
//                	throw new ZMessManager("No puede utilizar fechas anteriores");
//                }
            	
            }
            
//            if(entity.getFechaInicio().before(new Date())){
//            	throw new ZMessManager("No puede utilizar fechas anteriores");
//            }
//            
//            if(entity.getFechaFinPlaneada().before(new Date())){
//            	throw new ZMessManager("No puede utilizar fechas anteriores");
//            }
            
            if(entity.getPsyPrograma() == null){
            	throw new ZMessManager("Porfavor Seleccione un programa de la lista");
            }
            if(entity.getPsyPrograma().getProgCodigo() == null){
            	throw new ZMessManager("Porfavor Seleccione un programa de la lista");
            }
           

            psyPlanAccionDAO.save(entity);

            log.debug("save PsyPlanAccion successful");
        } catch (Exception e) {
            log.error("save PsyPlanAccion failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyPlanAccion(PsyPlanAccion entity)
        throws Exception {
        log.debug("deleting PsyPlanAccion instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Plan de Accion");
        }

        if (entity.getPlacCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("Codigo del Plan");
        }

        List<PsyIpu> psyIpus = null;
        List<PsyPresupuesto> psyPresupuestos = null;
        List<PsyTarea> psyTareas = null;

        try {
        	
//        	Object[] variables = {"psyPlanAccion.placCodigo",false,entity.getPlacCodigo(),"="};
//        	psyIpus = psyIpuLogic.findByCriteria(variables, null, null);
            psyIpus = psyIpuDAO.findByProperty("psyPlanAccion.placCodigo",
                    entity.getPlacCodigo());

            if (Utilities.validationsList(psyIpus) == true) {
                throw new ZMessManager().new DeletingException("IPU");
            }

            psyPresupuestos = psyPresupuestoDAO.findByProperty("psyPlanAccion.placCodigo",
                    entity.getPlacCodigo());

            if (Utilities.validationsList(psyPresupuestos) == true) {
                throw new ZMessManager().new DeletingException(
                    "Presupuesto");
            }

            psyTareas = psyTareaDAO.findByProperty("psyPlanAccion.placCodigo",
                    entity.getPlacCodigo());

            if (Utilities.validationsList(psyTareas) == true) {
                throw new ZMessManager().new DeletingException("Tareas");
            }

            psyPlanAccionDAO.delete(entity);

            log.debug("Eliminacion del plan exitosa");
        } catch (Exception e) {
            log.error("Eliminacion del plan fallida", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyPlanAccion(PsyPlanAccion entity)
        throws Exception {
        log.debug("Actualizando el plan de accion");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "Plan de Accion");
            }

            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Descripcion");
            }

            if (entity.getEstadoPlanAccion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Estado del Plan");
            }

            if ((entity.getEstadoPlanAccion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoPlanAccion(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Estado del Plan");
            }

            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Estado del Registro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Estado del Registro");
            }

            if (entity.getFechaFinPlaneada() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Fechan Final Planeada");
            }

            if (entity.getFechaInicio() == null) {
                throw new ZMessManager().new EmptyFieldException("Fecha de Inicio");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("Nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("Nombre");
            }

            if (entity.getPlacCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("Codigo");
            }
            
            if(entity.getFechaFinPlaneada().before(entity.getFechaInicio())){
            	throw new ZMessManager("La fecha final planeada no puede ser anterior a la fecha inicial");
            }
            
            if(entity.getFechaFinReal() != null){
            	if(entity.getFechaFinReal().before(entity.getFechaInicio())){
                	throw new ZMessManager("La fecha final real no puede ser anterior a la fecha inicial");
                }
                
//                if(entity.getFechaFinReal().before(new Date())){
//                	throw new ZMessManager("No puede utilizar fechas anteriores");
//                }
            	
            }
            
//            if(entity.getFechaInicio().before(new Date())){
//            	throw new ZMessManager("No puede utilizar fechas anteriores");
//            }
//            
//            if(entity.getFechaFinPlaneada().before(new Date())){
//            	throw new ZMessManager("No puede utilizar fechas anteriores");
//            }
            
            if(entity.getPsyPrograma() == null){
            	throw new ZMessManager("Porfavor Seleccione un programa de la lista");
            }
            if(entity.getPsyPrograma().getProgCodigo() == null){
            	throw new ZMessManager("Porfavor Seleccione un programa de la lista");
            }

            psyPlanAccionDAO.update(entity);

            log.debug("Plan Actualizado Correctamente");
        } catch (Exception e) {
            log.error("Actualizacion del plan fallida", e);
            throw e;
        } finally {
        }
    }
    
    @Transactional(readOnly = true)
    public boolean verificarMapaEstrategico(PsyEmpresa empresa) throws Exception {
    	Boolean estaIniciado = false;
    	List<PsyMapaEstrategico> mapaEstrategicos = psyPlanAccionDAO.verificarMapaEstrategico(empresa);
    	if(mapaEstrategicos!=null){
    	for (PsyMapaEstrategico mapaEstrategicoTmp : mapaEstrategicos) {
			if(mapaEstrategicoTmp.getEstadoMapaEstrategico().trim().equals("C")){
				estaIniciado = true;
			}
		}
    	}
    	return estaIniciado;
    }
    
    @Transactional(readOnly = true)
    public boolean verificarPlanEstrategico(PsyEmpresa empresa) throws Exception {
    	Boolean estaIniciado = false;
    	List<PsyPlanEstrategico> planEstrategico = psyPlanAccionDAO.verificarPlanEstrategico(empresa);
    	if(planEstrategico!=null){
    	for (PsyPlanEstrategico planEstrategicoTmp : planEstrategico) {
			if(planEstrategicoTmp.getEstadoPlan().trim().equals("I")){
				estaIniciado = true;
			}
		}
    	}
    	return estaIniciado;
    }

    @Transactional(readOnly = true)
    public List<PsyPlanAccionDTO> getDataPsyPlanAccion(PsyEmpresa empresa)
        throws Exception {
        try {
            List<PsyPlanAccion> psyPlanAccion = psyPlanAccionDAO.findPlanesAccionByEmpresaByPestActivo(empresa);

            List<PsyPlanAccionDTO> psyPlanAccionDTO = new ArrayList<PsyPlanAccionDTO>();

            for (PsyPlanAccion psyPlanAccionTmp : psyPlanAccion) {
                PsyPlanAccionDTO psyPlanAccionDTO2 = new PsyPlanAccionDTO();

                psyPlanAccionDTO2.setPlacCodigo(psyPlanAccionTmp.getPlacCodigo());
                psyPlanAccionDTO2.setDescripcion((psyPlanAccionTmp.getDescripcion() != null)
                    ? psyPlanAccionTmp.getDescripcion() : null);
                psyPlanAccionDTO2.setEstadoPlanAccion((psyPlanAccionTmp.getEstadoPlanAccion() != null)
                    ? (psyPlanAccionTmp.getEstadoPlanAccion().equals("A")) ? "Abierto" :
                    	(psyPlanAccionTmp.getEstadoPlanAccion().equals("I")) ? "Iniciado" :
                    		(psyPlanAccionTmp.getEstadoPlanAccion().equals("C")) ? "Cerrado":
                    			(psyPlanAccionTmp.getEstadoPlanAccion().equals("P")) ? "Presupuestado" : null : null);
                psyPlanAccionDTO2.setEstadoRegistro((psyPlanAccionTmp.getEstadoRegistro() != null)
                    ? psyPlanAccionTmp.getEstadoRegistro() : null);
                psyPlanAccionDTO2.setFechaFinPlaneada(psyPlanAccionTmp.getFechaFinPlaneada());
                psyPlanAccionDTO2.setFechaFinReal(psyPlanAccionTmp.getFechaFinReal());
                psyPlanAccionDTO2.setFechaInicio(psyPlanAccionTmp.getFechaInicio());
                psyPlanAccionDTO2.setNombre((psyPlanAccionTmp.getNombre() != null)
                    ? psyPlanAccionTmp.getNombre() : null);
                if(psyPlanAccionTmp.getEstadoPlanAccion().equals("A")){
                	 psyPlanAccionDTO2.setBtnPlanAccionDefinitivo(false);
                }else {
                	psyPlanAccionDTO2.setBtnPlanAccionDefinitivo(true);
				}
                
                psyPlanAccionDTO2.setProgCodigo_PsyPrograma((psyPlanAccionTmp.getPsyPrograma().getProgCodigo() != null)
                    ? psyPlanAccionTmp.getPsyPrograma().getProgCodigo() : null);
                
                psyPlanAccionDTO.add(psyPlanAccionDTO2);
            }

            return psyPlanAccionDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyPlanAccion getPsyPlanAccion(Long placCodigo)
        throws Exception {
        log.debug("Obteniendo instancia del plan de accion");

        PsyPlanAccion entity = null;

        try {
            entity = psyPlanAccionDAO.findById(placCodigo);
        } catch (Exception e) {
            log.error("Error obteniendo el plan de accion", e);
            throw new ZMessManager().new FindingException("Plan de Accion");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyPlanAccion> findPagePsyPlanAccion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<PsyPlanAccion> entity = null;

        try {
            entity = psyPlanAccionDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Plan de Accion");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyPlanAccion() throws Exception {
        Long entity = null;

        try {
            entity = psyPlanAccionDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Plan de Accion");
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
    public List<PsyPlanAccion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyPlanAccion> list = new ArrayList<PsyPlanAccion>();
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
            list = psyPlanAccionDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void generarPlanAccionDifinitivo(Long placCodigo) throws Exception {
    	List<PsyTarea> list = new ArrayList<PsyTarea>();
    	Long numeroTotalDeTareasPlanDeAccion=psyTareaDAO.numeroTotalDeTareasPlanDeAccion(placCodigo);
		try {
			if(numeroTotalDeTareasPlanDeAccion==0){
				 throw new ZMessManager("El plan de accion no tiene tarea asignadas");
			}
				else{

			Long  maxsemanaFinPlaneada=psyTareaDAO.consultarMaxSemana(placCodigo);
			Long minSemanInicio = psyTareaDAO.consultarMinimaSemanaFinPlaneada(placCodigo);
			for (int i = 1; i <maxsemanaFinPlaneada+1; i++) {
				Long numeroTareasHastaLaSeman = psyTareaDAO.consultarCountSemanaFinPlaneada(placCodigo,Long.parseLong(""+i));
				
				List<Date> lst = psyTareaDAO.maxMinSemana(placCodigo,Long.parseLong(""+i));
				
				int contador=1;
				while (lst.get(0)==null&&lst.get(1)==null) {
					lst = psyTareaDAO.maxMinSemana(placCodigo,Long.parseLong(""+(i-contador+minSemanInicio)));
					contador++;
				}
				
			
				PsyIpu psyIpu= new PsyIpu();
				psyIpu.setPsyPlanAccion(psyPlanAccionDAO.findById(placCodigo));
				psyIpu.setFechaInforme(new Date());
				psyIpu.setPeriodo(""+i);
				
			    //psyIpu.setPeriodoFechaInicio(lst.get(0));
			   // psyIpu.setPeriodoFechaFin(lst.get(1));
				if(psyTareaDAO.find("from PsyTarea where semanaFinPlaneada='"+i+"' and psyPlanAccion='"+placCodigo+"'").size()==0){
					 psyIpu.setPeriodoFechaInicio(Utilities.sumarDiasFecha(psyPlanAccionDAO.findById(placCodigo).getFechaInicio(), 7*(i-1)));
					 psyIpu.setPeriodoFechaFin(Utilities.sumarDiasFecha(psyPlanAccionDAO.findById(placCodigo).getFechaInicio(), 7*i));
				}else {
					 psyIpu.setPeriodoFechaInicio(lst.get(0));
					 psyIpu.setPeriodoFechaFin(lst.get(1));
				}
				
				psyIpu.setEstadoIpu("A");
				psyIpu.setEstadoRegistro("A");
				psyIpu.setAvancePresupuestado(Double.parseDouble(""+numeroTareasHastaLaSeman)/Double.parseDouble(""+numeroTotalDeTareasPlanDeAccion));
				
				psyIpu.setTipoIpu("T");
				psyIpuLogic.savePsyIpu(psyIpu);
				
				
			}
			list = psyTareaDAO.find("from PsyTarea where psyPlanAccion='"+placCodigo+"'");
			for (int i = 0; i < list.size(); i++) {
				PsyTarea psyTarea = new PsyTarea();
				psyTarea=list.get(i);
				psyTarea.setEstadoTarea("E");
				psyTareaDAO.update(psyTarea);
			}
			PsyPlanAccion psyPlanAccion = new PsyPlanAccion();
			psyPlanAccion=psyPlanAccionDAO.findById(placCodigo);
			psyPlanAccion.setEstadoPlanAccion("I");
		    updatePsyPlanAccion(psyPlanAccion);
			}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
			
		}
		
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyPlanAccionPsyPlanEstrategia(PsyPlanAccion entity, List<String> estrategiasTarget, PsyEmpresa empresa)
        throws Exception {
        log.debug("Guardando un plan de accion");

        try {
            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Descripcion");
            }

            if (entity.getEstadoPlanAccion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Estado del Plan");
            }

            if ((entity.getEstadoPlanAccion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoPlanAccion(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Estado del Plan");
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

            if (entity.getFechaFinPlaneada() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Fecha Final Planeada");
            }

            if (entity.getFechaInicio() == null) {
                throw new ZMessManager().new EmptyFieldException("Fecha de Inicio");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("Nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("Nombre");
            }
            
            if(entity.getFechaFinPlaneada().before(entity.getFechaInicio())){
            	throw new ZMessManager("La fecha final planeada no puede ser anterior a la fecha inicial");
            }
            
            if(entity.getFechaFinReal() != null){
            	if(entity.getFechaFinReal().before(entity.getFechaInicio())){
                	throw new ZMessManager("La fecha final real no puede ser anterior a la fecha inicial");
                }
                
//                if(entity.getFechaFinReal().before(new Date())){
//                	throw new ZMessManager("No puede utilizar fechas anteriores");
//                }
            	
            }
            
//            if(entity.getFechaInicio().before(new Date())){
//            	throw new ZMessManager("No puede utilizar fechas anteriores");
//            }
//            
//            if(entity.getFechaFinPlaneada().before(new Date())){
//            	throw new ZMessManager("No puede utilizar fechas anteriores");
//            }
            psyPlanAccionDAO.save(entity);

           

    	
    	


            log.debug("save PsyPlanAccion successful");
        } catch (Exception e) {
            log.error("save PsyPlanAccion failed", e);
            throw e;
        } finally {
        }
    }
    
    @Transactional(readOnly = true)
    public String consultaPlanEstrategicoBtnNew(PsyEmpresa empresa) throws Exception{
    	return psyPlanAccionDAO.consultaPlanEstrategicoBtnNew(empresa);
    }
    @Transactional(readOnly = true)
    public String consultaPlanEstrategico(PsyEmpresa empresa, Long placCodigo) throws Exception{
    	return psyPlanAccionDAO.consultaPlanEstrategico(empresa, placCodigo);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyPlanAccionPlanEstrategia(PsyPlanAccion entity, List<String> estrategiasTarget, PsyEmpresa empresa)
        throws Exception {
        log.debug("Actualizando el plan de accion");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "Plan de Accion");
            }

            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Descripcion");
            }

            if (entity.getEstadoPlanAccion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Estado del Plan");
            }

            if ((entity.getEstadoPlanAccion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoPlanAccion(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Estado del Plan");
            }

            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Estado del Registro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Estado del Registro");
            }

            if (entity.getFechaFinPlaneada() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Fechan Final Planeada");
            }

            if (entity.getFechaInicio() == null) {
                throw new ZMessManager().new EmptyFieldException("Fecha de Inicio");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("Nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("Nombre");
            }

            if (entity.getPlacCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("Codigo");
            }
            
            if(entity.getFechaFinPlaneada().before(entity.getFechaInicio())){
            	throw new ZMessManager("La fecha final planeada no puede ser anterior a la fecha inicial");
            }
            
            if(entity.getFechaFinReal() != null){
            	if(entity.getFechaFinReal().before(entity.getFechaInicio())){
                	throw new ZMessManager("La fecha final real no puede ser anterior a la fecha inicial");
                }
                
//                if(entity.getFechaFinReal().before(new Date())){
//                	throw new ZMessManager("No puede utilizar fechas anteriores");
//                }
            	
            }
            
//            if(entity.getFechaInicio().before(new Date())){
//            	throw new ZMessManager("No puede utilizar fechas anteriores");
//            }
//            
//            if(entity.getFechaFinPlaneada().before(new Date())){
//            	throw new ZMessManager("No puede utilizar fechas anteriores");
//            }
            

            psyPlanAccionDAO.update(entity);
            
            
            
            
            
            

            log.debug("Plan Actualizado Correctamente");
        } catch (Exception e) {
            log.error("Actualizacion del plan fallida", e);
            throw e;
        } finally {
        }
    }

    
    @Transactional(readOnly = true)
    public List<PsyPlanAccion> consultarPlanesAccion(PsyEmpresa empresa, PsyPlanEstrategico planEstrategico , String estadoIniciado, String estadoPresupuestado)
            throws Exception{    	
    	
    	return psyPlanAccionDAO.consultarPlanesAccion(empresa, planEstrategico, estadoIniciado, estadoPresupuestado);
    }
}

