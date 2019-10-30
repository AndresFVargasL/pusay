package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyIpuDTO;
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
@Service("PsyIpuLogic")
public class PsyIpuLogic implements IPsyIpuLogic {
    private static final Logger log = LoggerFactory.getLogger(PsyIpuLogic.class);

    /**
     * DAO injected by Spring that manages PsyIpu entities
     *
     */
    @Autowired
    private IPsyIpuDAO psyIpuDAO;

    
    /**
    * Logic injected by Spring that manages PsyPlanAccion entities
    *
    */
    @Autowired
    IPsyPlanAccionLogic logicPsyPlanAccion1;

    @Transactional(readOnly = true)
    public List<PsyIpu> getPsyIpu() throws Exception {
        log.debug("finding all PsyIpu instances");

        List<PsyIpu> list = new ArrayList<PsyIpu>();

        try {
            list = psyIpuDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyIpu failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyIpu");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyIpu(PsyIpu entity) throws Exception {
        log.debug("saving PsyIpu instance");

        try {
            if (entity.getPsyPlanAccion() == null) {
                throw new ZMessManager().new ForeignException("psyPlanAccion");
            }

            if (entity.getEstadoIpu() == null) {
                throw new ZMessManager().new EmptyFieldException("estadoIpu");
            }

            if ((entity.getEstadoIpu() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoIpu(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoIpu");
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

            if (entity.getFechaInforme() == null) {
                throw new ZMessManager().new EmptyFieldException("fechaInforme");
            }

            

            if (entity.getPeriodo() == null) {
                throw new ZMessManager().new EmptyFieldException("periodo");
            }

            if ((entity.getPeriodo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getPeriodo(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException("periodo");
            }

            

            if (entity.getPsyPlanAccion().getPlacCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "placCodigo_PsyPlanAccion");
            }

            

            psyIpuDAO.save(entity);

            log.debug("save PsyIpu successful");
        } catch (Exception e) {
            log.error("save PsyIpu failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyIpu(PsyIpu entity) throws Exception {
        log.debug("deleting PsyIpu instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("PsyIpu");
        }

        if (entity.getIpuCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("ipuCodigo");
        }

       
        try {
            
            psyIpuDAO.delete(entity);

            log.debug("delete PsyIpu successful");
        } catch (Exception e) {
            log.error("delete PsyIpu failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyIpu(PsyIpu entity) throws Exception {
        log.debug("updating PsyIpu instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("PsyIpu");
            }

            if (entity.getPsyPlanAccion() == null) {
                throw new ZMessManager().new ForeignException("psyPlanAccion");
            }

            if (entity.getEstadoIpu() == null) {
                throw new ZMessManager().new EmptyFieldException("estadoIpu");
            }

            if ((entity.getEstadoIpu() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoIpu(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoIpu");
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

            if (entity.getFechaInforme() == null) {
                throw new ZMessManager().new EmptyFieldException("fechaInforme");
            }

            if (entity.getIpuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("ipuCodigo");
            }

            if (entity.getPeriodo() == null) {
                throw new ZMessManager().new EmptyFieldException("periodo");
            }

            if ((entity.getPeriodo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getPeriodo(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException("periodo");
            }

           
            if (entity.getPsyPlanAccion().getPlacCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "placCodigo_PsyPlanAccion");
            }

            psyIpuDAO.update(entity);

            log.debug("update PsyIpu successful");
        } catch (Exception e) {
            log.error("update PsyIpu failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyIpuDTO> getDataPsyIpu() throws Exception {
        try {
            List<PsyIpu> psyIpu = psyIpuDAO.findAll();

            List<PsyIpuDTO> psyIpuDTO = new ArrayList<PsyIpuDTO>();

            for (PsyIpu psyIpuTmp : psyIpu) {
                PsyIpuDTO psyIpuDTO2 = new PsyIpuDTO();

                psyIpuDTO2.setIpuCodigo(psyIpuTmp.getIpuCodigo());
                psyIpuDTO2.setEstadoIpu((psyIpuTmp.getEstadoIpu() != null)
                    ? psyIpuTmp.getEstadoIpu() : null);
                psyIpuDTO2.setEstadoRegistro((psyIpuTmp.getEstadoRegistro() != null)
                    ? psyIpuTmp.getEstadoRegistro() : null);
                psyIpuDTO2.setFechaInforme(psyIpuTmp.getFechaInforme());
                psyIpuDTO2.setPeriodo((psyIpuTmp.getPeriodo() != null)
                    ? psyIpuTmp.getPeriodo() : null);
                psyIpuDTO2.setPeriodoFechaFin(psyIpuTmp.getPeriodoFechaFin());
                psyIpuDTO2.setPeriodoFechaInicio(psyIpuTmp.getPeriodoFechaInicio());
                psyIpuDTO2.setPlacCodigo_PsyPlanAccion((psyIpuTmp.getPsyPlanAccion()
                                                                 .getPlacCodigo() != null)
                    ? psyIpuTmp.getPsyPlanAccion().getPlacCodigo() : null);
                psyIpuDTO.add(psyIpuDTO2);
            }

            return psyIpuDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyIpu getPsyIpu(Long ipuCodigo) throws Exception {
        log.debug("getting PsyIpu instance");

        PsyIpu entity = null;

        try {
            entity = psyIpuDAO.findById(ipuCodigo);
        } catch (Exception e) {
            log.error("get PsyIpu failed", e);
            throw new ZMessManager().new FindingException("PsyIpu");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyIpu> findPagePsyIpu(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<PsyIpu> entity = null;

        try {
            entity = psyIpuDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("PsyIpu Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyIpu() throws Exception {
        Long entity = null;

        try {
            entity = psyIpuDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("PsyIpu Count");
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
    public List<PsyIpu> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyIpu> list = new ArrayList<PsyIpu>();
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
            list = psyIpuDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
    @Transactional(readOnly = true)
    public List<PsyIpuDTO> consultarIpu(PsyPlanAccion  planAccion, String tipoIpu)
        throws Exception {
       
    	// se crea la lista a devolver con el IPU para el plan de acción 
        List<PsyIpuDTO> lstIpuDefinitivo = new ArrayList<PsyIpuDTO>();
        	 
    	 lstIpuDefinitivo = psyIpuDAO.consultarIpu(planAccion, tipoIpu);
    	 
    	 if(lstIpuDefinitivo == null || lstIpuDefinitivo.isEmpty()){
    		 throw new Exception("No existe un IPU definido para el plan de accion con codigo "+planAccion.getPlacCodigo());
    	 }
    	    	
    	 
        return lstIpuDefinitivo;
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<PsyIpuDTO> generarIpu(PsyPlanAccion  planAccion, String tipoIpu, Date fechaInforme, Long semanaInforme, List<PsyIpuDTO> lstIpu,
    		String logrosSignificativos, String logrosNoAlcanzados, String causasDesviacion, String accionesPropuestas, boolean actualizar) throws Exception{
       
    	
    	if(fechaInforme.before(planAccion.getFechaInicio())){
			throw new Exception("La fecha de informe NO puede ser más antigua que la fecha del plan de acción");			
		}
    	
    	if(semanaInforme> lstIpu.size()){
			throw new Exception("La semana no puede ser superior a la ultima semana del plan de trabajo");			
		}
    	
    	if(logrosNoAlcanzados != null && logrosNoAlcanzados.trim().length()>0){
    		
    		if(causasDesviacion==null || causasDesviacion.trim().length()==0){
    			throw new Exception("Debe ingresar las causas de desviación, ya que se ha ingresado logros NO alcanzados");
    		}
    		
    		if(accionesPropuestas==null || accionesPropuestas.trim().length()==0){
    			throw new Exception("Debe ingresar acciones propuestas, ya que se ha ingresado logros NO alcanzados");
    		}
    		
    	}
    	
    	Long cantidadTareasFinalizadasReales = 0L;
    	//Se consulta la cantidad de tareas del plan
    	Long cantidadTareasPlan = consultarCantidadTareasPlan(planAccion);
    	PsyIpu ipu = new PsyIpu();
    	Double auxiliarVariacion = 0D;
    	
    	
    	//Recorro la lista con los periodos del IPU
    	//Por cada uno verifico la semana y guardo IPU hasta que la semana sea igual o menor a la semana informe
    		for(PsyIpuDTO periodo: lstIpu){
    			cantidadTareasFinalizadasReales = 0L;
    			//Se verifica que se guarde un IPU solo hasta la semana del informe
    			if(Long.parseLong(periodo.getPeriodo()) > semanaInforme){
    				break;
       			}else{
       				
       				//Se verifica que ya se tenga avance real, en caso de no tener se guarda el IPU para ese periodo
       				if(periodo.getAvanceReal()==null && Long.parseLong(periodo.getPeriodo())<semanaInforme){       					
       					//Se calcula el avance real hasta el perido
       					cantidadTareasFinalizadasReales = consultarCantidadTareasHastaPeriodo(planAccion,Long.parseLong(periodo.getPeriodo()));
       					ipu = getPsyIpu(periodo.getIpuCodigo());
       					
       					if(cantidadTareasPlan==0){
       						ipu.setAvanceReal(0D);
       					}else{
       						ipu.setAvanceReal(cantidadTareasFinalizadasReales.doubleValue()/cantidadTareasPlan.doubleValue());
       					}
       					if(ipu.getAvancePresupuestado() == 0){
       						periodo.setVariacion(0D);
       					}else{
       						//Se utiliza auxiliar para evitar la division por cero
       						auxiliarVariacion = (ipu.getAvanceReal()*100) - (ipu.getAvancePresupuestado()*100);
       						periodo.setVariacion(auxiliarVariacion/100);
       					}
       					periodo.setAvanceReal(ipu.getAvanceReal());
       					ipu.setFechaInforme(fechaInforme);
       					if(actualizar == true){
       					updatePsyIpu(ipu);
       					}
       				}else if(periodo.getAvanceReal()==null && Long.parseLong(periodo.getPeriodo())==semanaInforme){
       					
       				//Se calcula el avance real hasta el perido
       					cantidadTareasFinalizadasReales = consultarCantidadTareasHastaPeriodo(planAccion,Long.parseLong(periodo.getPeriodo()));
       					ipu = getPsyIpu(periodo.getIpuCodigo());
       					if(cantidadTareasPlan==0){
       						ipu.setAvanceReal(0D);
       					}else{
       						ipu.setAvanceReal(cantidadTareasFinalizadasReales.doubleValue()/cantidadTareasPlan.doubleValue());
       						
       					}
       					if(ipu.getAvancePresupuestado() == 0){
       						periodo.setVariacion(0d);
       					}else{
       						//Se utiliza auxiliar para evitar la division por cero
       						auxiliarVariacion = (ipu.getAvanceReal()*100) - (ipu.getAvancePresupuestado()*100);
       						periodo.setVariacion(auxiliarVariacion/100);
       					}
       					periodo.setAvanceReal(ipu.getAvanceReal());
       					ipu.setFechaInforme(fechaInforme);
       					ipu.setAccionesPropuestas(accionesPropuestas);
       					ipu.setCausasDesviacion(causasDesviacion);
       					ipu.setLogrosAlcanzados(logrosSignificativos);
       					ipu.setLogrosNoAlcanzados(logrosNoAlcanzados);
       					periodo.setAccionesPropuestas(accionesPropuestas);
       					periodo.setCausasDesviacion(causasDesviacion);
       					periodo.setLogrosAlcanzados(logrosSignificativos);
       					periodo.setLogrosNoAlcanzados(logrosNoAlcanzados);
       					if(actualizar == true){
       					updatePsyIpu(ipu);
       					}
       			}
       				
       			}
    			
    		}
    	 
        return lstIpu;
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<PsyIpuDTO> generarIpuPresupuesto(PsyPlanAccion  planAccion, String tipoIpu, Date fechaInforme, Long semanaInforme, List<PsyIpuDTO> lstIpu,
    		String logrosSignificativos, String logrosNoAlcanzados, String causasDesviacion, String accionesPropuestas) throws Exception{
       
    	
    	if(fechaInforme.before(planAccion.getFechaInicio())){
			throw new Exception("La fecha de informe NO puede ser más antigua que la fecha del plan de acción");			
		}
    	
    	if(semanaInforme> lstIpu.size()){
			throw new Exception("La semana no puede ser superior a la ultima semana del plan de trabajo");			
		}
    	
    	if(logrosNoAlcanzados != null && logrosNoAlcanzados.trim().length()>0){
    		
    		if(causasDesviacion==null || causasDesviacion.trim().length()==0){
    			throw new Exception("Debe ingresar las causas de desviación, ya que se ha ingresado logros NO alcanzados");
    		}
    		
    		if(accionesPropuestas==null || accionesPropuestas.trim().length()==0){
    			throw new Exception("Debe ingresar acciones propuestas, ya que se ha ingresado logros NO alcanzados");
    		}
    		
    	}
    	
    	Long presupuestoRealHastaPeriodo = 0L;
    	//Se consulta la cantidad de tareas del plan
    	Long totalPresupuesto = consultarTotalPresupuestoPlan(planAccion);
    	PsyIpu ipu = new PsyIpu();
    	Double auxiliarVariacion = 0D;
    	
    	
    	//Recorro la lista con los periodos del IPU
    	//Por cada uno verifico la semana y guardo IPU hasta que la semana sea igual o menor a la semana informe
    		for(PsyIpuDTO periodo: lstIpu){
    			presupuestoRealHastaPeriodo = 0L;
    			//Se verifica que se guarde un IPU solo hasta la semana del informe
    			if(Long.parseLong(periodo.getPeriodo()) > semanaInforme){
    				break;
       			}else{
       				
       				//Se verifica que ya se tenga avance real, en caso de no tener se guarda el IPU para ese periodo
       				if(periodo.getAvanceReal()==null && Long.parseLong(periodo.getPeriodo())<semanaInforme){       					
       					//Se calcula el avance real hasta el perido
       					presupuestoRealHastaPeriodo = consultarPrespuestoRealHastaPeriodo(planAccion,Long.parseLong(periodo.getPeriodo()));
       					ipu = getPsyIpu(periodo.getIpuCodigo());
       					
       					if(totalPresupuesto==0){
       						ipu.setAvanceReal(0D);
       					}else{
       						ipu.setAvanceReal(presupuestoRealHastaPeriodo.doubleValue());
       					}
       					if(ipu.getAvancePresupuestado() == 0){
       						periodo.setVariacion(0D);
       					}else{
       						//Se utiliza auxiliar para evitar la division por cero
       						//auxiliarVariacion = (ipu.getAvanceReal()) - (ipu.getAvancePresupuestado());
       						periodo.setVariacion( ipu.getAvancePresupuestado() - ipu.getAvanceReal());
       					}
       					periodo.setAvanceReal(ipu.getAvanceReal());
       					ipu.setFechaInforme(fechaInforme);
       					updatePsyIpu(ipu);
       				}else if(periodo.getAvanceReal()==null && Long.parseLong(periodo.getPeriodo())==semanaInforme){
       					
       				//Se calcula el avance real hasta el perido
       					presupuestoRealHastaPeriodo = consultarPrespuestoRealHastaPeriodo(planAccion,Long.parseLong(periodo.getPeriodo()));
       					ipu = getPsyIpu(periodo.getIpuCodigo());
       					if(totalPresupuesto==0){
       						ipu.setAvanceReal(0D);
       					}else{
       						ipu.setAvanceReal(presupuestoRealHastaPeriodo.doubleValue());
       						
       					}
       					if(ipu.getAvancePresupuestado() == 0){
       						periodo.setVariacion(0d);
       					}else{
       						//Se utiliza auxiliar para evitar la division por cero
       						//auxiliarVariacion = (ipu.getAvanceReal()*100) - (ipu.getAvancePresupuestado()*100);
       						periodo.setVariacion(ipu.getAvancePresupuestado() - ipu.getAvanceReal());
       					}
       					periodo.setAvanceReal(ipu.getAvanceReal());
       					ipu.setFechaInforme(fechaInforme);
       					ipu.setAccionesPropuestas(accionesPropuestas);
       					ipu.setCausasDesviacion(causasDesviacion);
       					ipu.setLogrosAlcanzados(logrosSignificativos);
       					ipu.setLogrosNoAlcanzados(logrosNoAlcanzados);
       					
       					periodo.setAccionesPropuestas(accionesPropuestas);
       					periodo.setCausasDesviacion(causasDesviacion);
       					periodo.setLogrosAlcanzados(logrosSignificativos);
       					periodo.setLogrosNoAlcanzados(logrosNoAlcanzados);
       					updatePsyIpu(ipu);
       					
       				}
       				
       			}
    			
    		}
    	 
        return lstIpu;
    }
    
    
    
    @Transactional(readOnly = true)
    public Long consultarCantidadTareasHastaPeriodo(PsyPlanAccion  planAccion, Long semanaFin)
        throws Exception {
    	 
        return psyIpuDAO.consultarCantidadTareasHastaPeriodo(planAccion, semanaFin);
    }
    
    @Transactional(readOnly = true)
    public Long consultarCantidadTareasPlan(PsyPlanAccion  planAccion)
        throws Exception {
    	 
        return psyIpuDAO.consultarCantidadTareasPlan(planAccion);
    }
    
    @Transactional(readOnly = true)
    public Long consultarTotalPresupuestoPlan(PsyPlanAccion  planAccion)
        throws Exception {
    	 
        return psyIpuDAO.consultarTotalPresupuestoPlan(planAccion);
    }
    
    @Transactional(readOnly = true)
    public Long consultarPrespuestoRealHastaPeriodo(PsyPlanAccion  planAccion, Long semanaFin)
        throws Exception {
    	 
        return psyIpuDAO.consultarPrespuestoRealHastaPeriodo(planAccion, semanaFin);
    }
    
    @Transactional(readOnly = true)
    public List<PsyIpuDTO> consultarIpuHastaPeriodo(PsyPlanAccion  planAccion, String tipoIpu, String periodo)
        throws Exception {
    	
    	Integer periodoInt= Integer.parseInt(periodo);
    	
    	if(periodoInt == null || periodoInt==-1){
    		 throw new Exception("Se debe seleccionar un periodo para la consulta");
    	}
       
    	// se crea la lista a devolver con el IPU para el plan de acción 
        List<PsyIpuDTO> lstIpu= new ArrayList<PsyIpuDTO>();
        List<PsyIpuDTO> lstIpDefinitivo= new ArrayList<PsyIpuDTO>();
        	 
    	 lstIpu = psyIpuDAO.consultarIpu(planAccion, tipoIpu);
    	 
    	 for(PsyIpuDTO periodoItem: lstIpu){
    		 if(Integer.parseInt(periodoItem.getPeriodo()) <= periodoInt){
    			 lstIpDefinitivo.add(periodoItem);
    		 }else{
    			 break;
    		 }
    	 }
    	     	 
//    	 if(lstIpuDefinitivo == null || lstIpuDefinitivo.isEmpty()){
//    		 throw new Exception("No existe un IPU definido para el plan de accion con codigo "+planAccion.getPlacCodigo());
//    	 }
    	    	
    	 
        return lstIpDefinitivo;
    }
    
    
    @Transactional(readOnly = true)
    public List<PsyIpuDTO> generarIpuLogico(PsyPlanAccion  planAccion, String tipoIpu, Date fechaInforme, Long semanaInforme, List<PsyIpuDTO> lstIpu,
    		String logrosSignificativos, String logrosNoAlcanzados, String causasDesviacion, String accionesPropuestas, boolean actualizar) throws Exception{
       
    	
    	if(fechaInforme.before(planAccion.getFechaInicio())){
			throw new Exception("La fecha de informe NO puede ser más antigua que la fecha del plan de acción");			
		}
    	
    	if(semanaInforme> lstIpu.size()){
			throw new Exception("La semana no puede ser superior a la ultima semana del plan de trabajo");			
		}
    	
    	if(logrosNoAlcanzados != null && logrosNoAlcanzados.trim().length()>0){
    		
    		if(causasDesviacion==null || causasDesviacion.trim().length()==0){
    			throw new Exception("Debe ingresar las causas de desviación, ya que se ha ingresado logros NO alcanzados");
    		}
    		
    		if(accionesPropuestas==null || accionesPropuestas.trim().length()==0){
    			throw new Exception("Debe ingresar acciones propuestas, ya que se ha ingresado logros NO alcanzados");
    		}
    		
    	}
    	
    	Long cantidadTareasFinalizadasReales = 0L;
    	//Se consulta la cantidad de tareas del plan
    	Long cantidadTareasPlan = consultarCantidadTareasPlan(planAccion);
    	PsyIpu ipu = new PsyIpu();
    	Double auxiliarVariacion = 0D;
    	
    	
    	//Recorro la lista con los periodos del IPU
    	//Por cada uno verifico la semana y guardo IPU hasta que la semana sea igual o menor a la semana informe
    		for(PsyIpuDTO periodo: lstIpu){
    			cantidadTareasFinalizadasReales = 0L;
    			//Se verifica que se guarde un IPU solo hasta la semana del informe
    			if(Long.parseLong(periodo.getPeriodo()) > semanaInforme){
    				break;
       			}else{
       				
       				//Se verifica que ya se tenga avance real, en caso de no tener se guarda el IPU para ese periodo
       				if(periodo.getAvanceReal()==null && Long.parseLong(periodo.getPeriodo())<semanaInforme){       					
       					//Se calcula el avance real hasta el perido
       					cantidadTareasFinalizadasReales = consultarCantidadTareasHastaPeriodo(planAccion,Long.parseLong(periodo.getPeriodo()));
       					ipu = getPsyIpu(periodo.getIpuCodigo());
       					
       					if(cantidadTareasPlan==0){
       						ipu.setAvanceReal(0D);
       					}else{
       						ipu.setAvanceReal(cantidadTareasFinalizadasReales.doubleValue()/cantidadTareasPlan.doubleValue());
       					}
       					if(ipu.getAvancePresupuestado() == 0){
       						periodo.setVariacion(0D);
       					}else{
       						//Se utiliza auxiliar para evitar la division por cero
       						auxiliarVariacion = (ipu.getAvanceReal()*100) - (ipu.getAvancePresupuestado()*100);
       						periodo.setVariacion(auxiliarVariacion/100);
       					}
       					periodo.setAvanceReal(ipu.getAvanceReal());
       					ipu.setFechaInforme(fechaInforme);
       					
       				}else if(periodo.getAvanceReal()==null && Long.parseLong(periodo.getPeriodo())==semanaInforme){
       					
       				//Se calcula el avance real hasta el perido
       					cantidadTareasFinalizadasReales = consultarCantidadTareasHastaPeriodo(planAccion,Long.parseLong(periodo.getPeriodo()));
       					ipu = getPsyIpu(periodo.getIpuCodigo());
       					if(cantidadTareasPlan==0){
       						ipu.setAvanceReal(0D);
       					}else{
       						ipu.setAvanceReal(cantidadTareasFinalizadasReales.doubleValue()/cantidadTareasPlan.doubleValue());
       						
       					}
       					if(ipu.getAvancePresupuestado() == 0){
       						periodo.setVariacion(0d);
       					}else{
       						//Se utiliza auxiliar para evitar la division por cero
       						auxiliarVariacion = (ipu.getAvanceReal()*100) - (ipu.getAvancePresupuestado()*100);
       						periodo.setVariacion(auxiliarVariacion/100);
       					}
       					periodo.setAvanceReal(ipu.getAvanceReal());
       					ipu.setFechaInforme(fechaInforme);
       					ipu.setAccionesPropuestas(accionesPropuestas);
       					ipu.setCausasDesviacion(causasDesviacion);
       					ipu.setLogrosAlcanzados(logrosSignificativos);
       					ipu.setLogrosNoAlcanzados(logrosNoAlcanzados);
       					periodo.setAccionesPropuestas(accionesPropuestas);
       					periodo.setCausasDesviacion(causasDesviacion);
       					periodo.setLogrosAlcanzados(logrosSignificativos);
       					periodo.setLogrosNoAlcanzados(logrosNoAlcanzados);
       				
       			}
       				
       			}
    			
    		}
    	 
        return lstIpu;
    }
    
    
    @Transactional(readOnly = true)
    public List<PsyIpuDTO> generarIpuPresupuestoLogico(PsyPlanAccion  planAccion, String tipoIpu, Date fechaInforme, Long semanaInforme, List<PsyIpuDTO> lstIpu,
    		String logrosSignificativos, String logrosNoAlcanzados, String causasDesviacion, String accionesPropuestas) throws Exception{
       
    	
    	if(fechaInforme.before(planAccion.getFechaInicio())){
			throw new Exception("La fecha de informe NO puede ser más antigua que la fecha del plan de acción");			
		}
    	
    	if(semanaInforme> lstIpu.size()){
			throw new Exception("La semana no puede ser superior a la ultima semana del plan de trabajo");			
		}
    	
    	if(logrosNoAlcanzados != null && logrosNoAlcanzados.trim().length()>0){
    		
    		if(causasDesviacion==null || causasDesviacion.trim().length()==0){
    			throw new Exception("Debe ingresar las causas de desviación, ya que se ha ingresado logros NO alcanzados");
    		}
    		
    		if(accionesPropuestas==null || accionesPropuestas.trim().length()==0){
    			throw new Exception("Debe ingresar acciones propuestas, ya que se ha ingresado logros NO alcanzados");
    		}
    		
    	}
    	
    	Long presupuestoRealHastaPeriodo = 0L;
    	//Se consulta la cantidad de tareas del plan
    	Long totalPresupuesto = consultarTotalPresupuestoPlan(planAccion);
    	PsyIpu ipu = new PsyIpu();
    	Double auxiliarVariacion = 0D;
    	
    	
    	//Recorro la lista con los periodos del IPU
    	//Por cada uno verifico la semana y guardo IPU hasta que la semana sea igual o menor a la semana informe
    		for(PsyIpuDTO periodo: lstIpu){
    			presupuestoRealHastaPeriodo = 0L;
    			//Se verifica que se guarde un IPU solo hasta la semana del informe
    			if(Long.parseLong(periodo.getPeriodo()) > semanaInforme){
    				break;
       			}else{
       				
       				//Se verifica que ya se tenga avance real, en caso de no tener se guarda el IPU para ese periodo
       				if(periodo.getAvanceReal()==null && Long.parseLong(periodo.getPeriodo())<semanaInforme){       					
       					//Se calcula el avance real hasta el perido
       					presupuestoRealHastaPeriodo = consultarPrespuestoRealHastaPeriodo(planAccion,Long.parseLong(periodo.getPeriodo()));
       					ipu = getPsyIpu(periodo.getIpuCodigo());
       					
       					if(totalPresupuesto==0){
       						ipu.setAvanceReal(0D);
       					}else{
       						ipu.setAvanceReal(presupuestoRealHastaPeriodo.doubleValue());
       					}
       					if(ipu.getAvancePresupuestado() == 0){
       						periodo.setVariacion(0D);
       					}else{
       						//Se utiliza auxiliar para evitar la division por cero
       						//auxiliarVariacion = (ipu.getAvanceReal()) - (ipu.getAvancePresupuestado());
       						periodo.setVariacion( ipu.getAvancePresupuestado() - ipu.getAvanceReal());
       					}
       					periodo.setAvanceReal(ipu.getAvanceReal());
       					ipu.setFechaInforme(fechaInforme);
       				}else if(periodo.getAvanceReal()==null && Long.parseLong(periodo.getPeriodo())==semanaInforme){
       					
       				//Se calcula el avance real hasta el perido
       					presupuestoRealHastaPeriodo = consultarPrespuestoRealHastaPeriodo(planAccion,Long.parseLong(periodo.getPeriodo()));
       					ipu = getPsyIpu(periodo.getIpuCodigo());
       					if(totalPresupuesto==0){
       						ipu.setAvanceReal(0D);
       					}else{
       						ipu.setAvanceReal(presupuestoRealHastaPeriodo.doubleValue());
       						
       					}
       					if(ipu.getAvancePresupuestado() == 0){
       						periodo.setVariacion(0d);
       					}else{
       						//Se utiliza auxiliar para evitar la division por cero
       						//auxiliarVariacion = (ipu.getAvanceReal()*100) - (ipu.getAvancePresupuestado()*100);
       						periodo.setVariacion(ipu.getAvancePresupuestado() - ipu.getAvanceReal());
       					}
       					periodo.setAvanceReal(ipu.getAvanceReal());
       					ipu.setFechaInforme(fechaInforme);
       					ipu.setAccionesPropuestas(accionesPropuestas);
       					ipu.setCausasDesviacion(causasDesviacion);
       					ipu.setLogrosAlcanzados(logrosSignificativos);
       					ipu.setLogrosNoAlcanzados(logrosNoAlcanzados);
       					
       					periodo.setAccionesPropuestas(accionesPropuestas);
       					periodo.setCausasDesviacion(causasDesviacion);
       					periodo.setLogrosAlcanzados(logrosSignificativos);
       					periodo.setLogrosNoAlcanzados(logrosNoAlcanzados);
       					
       				}
       				
       			}
    			
    		}
    	 
        return lstIpu;
    }
    
}
