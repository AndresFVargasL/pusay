package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyTareaDTO;
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
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("PsyTareaLogic")
public class PsyTareaLogic implements IPsyTareaLogic {
	private static final Logger log = LoggerFactory
			.getLogger(PsyTareaLogic.class);
	private static final String ESTADO_FINALIZADO = "F";

	/**
	 * DAO injected by Spring that manages PsyTarea entities
	 *
	 */
	@Autowired
	private IPsyTareaDAO psyTareaDAO;

	/**
	 * DAO injected by Spring that manages PsySeguimientoTarea entities
	 *
	 */
	@Autowired
	private IPsySeguimientoTareaDAO psySeguimientoTareaDAO;

	/**
	 * Logic injected by Spring that manages PsyPlanAccion entities
	 *
	 */
	@Autowired
	IPsyPlanAccionLogic logicPsyPlanAccion1;

	/**
	 * Logic injected by Spring that manages PsyResponsable entities
	 *
	 */
	@Autowired
	IPsyResponsableLogic logicPsyResponsable2;
	
	@Autowired
	IPsyPlanAccionDAO psyPlanAccionDAO;

	@Transactional(readOnly = true)
	public List<PsyTarea> getPsyTarea() throws Exception {
		log.debug("finding all PsyTarea instances");

		List<PsyTarea> list = new ArrayList<PsyTarea>();

		try {
			list = psyTareaDAO.findAll();
		} catch (Exception e) {
			log.error("finding all PsyTarea failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL
					+ "PsyTarea");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void savePsyTarea(PsyTarea entity, String somResponsable)
			throws Exception {
		Boolean cambioFechaPlan = false;
		log.debug("saving PsyTarea instance");

		try {
			if (entity.getPsyPlanAccion() == null) {
				throw new ZMessManager().new ForeignException("Plan de Accion");
			}

			if ((entity.getDescripcion() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getDescripcion(), 2000) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Descripcion");
			}

			if (entity.getEstadoRegistro() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Estado Registro");
			}

			if ((entity.getEstadoRegistro() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getEstadoRegistro(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Estado Registro");
			}

			if (entity.getEstadoTarea() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Estado de la Tarea");
			}

			if ((entity.getEstadoTarea() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getEstadoTarea(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Estado de la Tarea");
			}

			if (entity.getFechaFinPlaneada() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Fecha Final Planeada");
			}

			if (entity.getFechaInicio() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Fecha de Inicio");
			}

			if (entity.getPsyPlanAccion().getPlacCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Plan de Accion");
			}
			
			if(entity.getFechaFinPlaneada().before(entity.getFechaInicio())){
            	throw new ZMessManager("La fecha planeada no puede ser anterior a la fecha inicial");
            }
            
            if(entity.getFechaFinReal() != null){
            	if(entity.getFechaFinReal().before(entity.getFechaInicio())){
                	throw new ZMessManager("La fecha real no puede ser anterior a la fecha inicial");
                }
                
//                if(entity.getFechaFinReal().before(new Date())){
//                	throw new ZMessManager("No puede utilizar fechas anteriores");
//                }
            	
            	if(entity.getFechaInicio().after(entity.getFechaFinReal())){
                	throw new ZMessManager("La fecha de inicio debe ir antes de la fecha final real");
                }
            	
            }
            
//            if(entity.getFechaInicio().before(new Date())){
//            	throw new ZMessManager("No puede utilizar fechas anteriores");
//            }
//            
//            if(entity.getFechaFinPlaneada().before(new Date())){
//            	throw new ZMessManager("No puede utilizar fechas anteriores");
//            }
            
            
            if(entity.getFechaInicio().after(entity.getFechaFinPlaneada())){
            	throw new ZMessManager("La fecha de inicio debe ir antes de la fecha final planeada");
            }
            
            if(somResponsable == null || somResponsable.trim().equals("")){
            	throw new ZMessManager("Debe seleccionar un responsable");
            }
            

			PsyResponsable responsable = new PsyResponsable();
			responsable = logicPsyResponsable2.getPsyResponsable(Long
					.parseLong(somResponsable));

			entity.setPsyResponsable(responsable);
			
			if(entity.getFechaFinPlaneada().before(entity.getPsyPlanAccion().getFechaInicio())){
				entity.setSemanaFinPlaneada(calcularDiferenciaFechas(entity
						.getFechaInicio(), entity
						.getFechaFinPlaneada()));
			}else{
				entity.setSemanaFinPlaneada(calcularDiferenciaFechas(entity
						.getPsyPlanAccion().getFechaInicio(), entity
						.getFechaFinPlaneada()));
			}

			if (entity.getFechaFinReal() != null) {
				if(entity.getFechaFinReal().before(entity.getPsyPlanAccion().getFechaInicio())){
					entity.setSemanaFinReal(calcularDiferenciaFechas(entity
							.getFechaInicio(), entity
							.getFechaFinReal()));
				}else{
					entity.setSemanaFinReal(calcularDiferenciaFechas(entity
							.getPsyPlanAccion().getFechaInicio(), entity
							.getFechaFinReal()));
				}
				
			}

			if (entity.getPsyResponsable() == null) {
				throw new ZMessManager().new ForeignException("Responsable");
			}

			if (entity.getSemanaFinPlaneada() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Semana Final Planeada");
			}

			if ((entity.getSemanaFinPlaneada() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ entity.getSemanaFinPlaneada(), 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Semana Final Planeada");
			}

			PsyPlanAccion planAccion = new PsyPlanAccion();

			planAccion = entity.getPsyPlanAccion();
			if (entity.getFechaInicio().before(planAccion.getFechaInicio())) {
				planAccion.setFechaInicio(entity.getFechaInicio());
				cambioFechaPlan = true;
			}

			if (entity.getFechaFinPlaneada().after(
					planAccion.getFechaFinPlaneada())) {
				planAccion.setFechaFinPlaneada(entity.getFechaFinPlaneada());
				cambioFechaPlan = true;
			}
			
			if(planAccion !=null && planAccion.getFechaFinReal() != null){
				if (entity.getFechaFinReal() != null && entity.getFechaFinReal().after(planAccion.getFechaFinReal())) {
					planAccion.setFechaFinReal(entity.getFechaFinReal());
					cambioFechaPlan = true;
				}

			}
			if (cambioFechaPlan == true) {
				logicPsyPlanAccion1.updatePsyPlanAccion(planAccion);
			}

			psyTareaDAO.save(entity);

			log.debug("save PsyTarea successful");
		} catch (Exception e) {
			log.error("save PsyTarea failed", e);
			//TODO: ARREGLAR MENSAJES DE FECHAS
			throw new ZMessManager(e.getMessage());
		} finally {
		}
	}

	public Long calcularDiferenciaFechas(Date fechaInicioPlan, Date fechafin) {
		// Calcula los milisegundos por dia
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
		// Calcula la diferencia en dias entre 2 fechas
		long diferencia = (fechafin.getTime() - fechaInicioPlan.getTime())
				/ MILLSECS_PER_DAY;
		// Calcula la diferencia en semanas
		Long diferenciaSemanas = diferencia / 7;
		// Formula para calcula semana plan
		long diff2 = ((sumarDiasFecha(fechafin, 1)).getTime() - fechaInicioPlan
				.getTime()) / MILLSECS_PER_DAY;
		// Se redondea al entero siguiente
		Long semana = (long) Math.floor((double) (diff2) / (double) 7);
		// Se suma +1 de diferencia
		Long resultset = semana + 1L;
		return resultset;

	}

	public Date sumarDiasFecha(Date fecha, int dias) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(fecha); // Configuramos la fecha que se recibe

		calendar.add(Calendar.DAY_OF_YEAR, dias); // numero de días a añadir, o
													// restar en caso de días<0

		return calendar.getTime(); // Devuelve el objeto Date con los nuevos
									// días añadidos

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletePsyTarea(PsyTarea entity) throws Exception {
		log.debug("deleting PsyTarea instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("PsyTarea");
		}

		if (entity.getTareCodigo() == null) {
			throw new ZMessManager().new EmptyFieldException("tareCodigo");
		}

		List<PsySeguimientoTarea> psySeguimientoTareas = null;

		try {
			psySeguimientoTareas = psySeguimientoTareaDAO.findByProperty(
					"psyTarea.tareCodigo", entity.getTareCodigo());

			if (Utilities.validationsList(psySeguimientoTareas) == true) {
				throw new ZMessManager().new DeletingException(
						"Seguimiento Tareas");
			}

			psyTareaDAO.delete(entity);

			log.debug("Eliminacion exitosa de la Tarea");
		} catch (Exception e) {
			log.error("Fallo la eliminacion de la tarea", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updatePsyTarea(PsyTarea entity) throws Exception {
		log.debug("updating PsyTarea instance");
		Boolean cambioFechaPlan = false;

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Tarea");
			}

			if (entity.getPsyPlanAccion() == null) {
				throw new ZMessManager().new ForeignException("Plan Accion");
			}

			if (entity.getPsyResponsable() == null) {
				throw new ZMessManager().new ForeignException("Responsable");
			}

			if ((entity.getDescripcion() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getDescripcion(), 2000) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Descripcion");
			}

			if (entity.getEstadoRegistro() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Estado Registro");
			}

			if ((entity.getEstadoRegistro() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getEstadoRegistro(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Estado Registro");
			}

			if (entity.getEstadoTarea() == null) {
				throw new ZMessManager().new EmptyFieldException("Estado Tarea");
			}

			if ((entity.getEstadoTarea() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getEstadoTarea(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Estado Tarea");
			}

			if (entity.getFechaFinPlaneada() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Fecha Final Planeada");
			}

			if (entity.getFechaInicio() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Fecha Inicial");
			}

			if (entity.getSemanaFinPlaneada() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Semana Final Planeada");
			}

			if ((entity.getSemanaFinPlaneada() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ entity.getSemanaFinPlaneada(), 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Semana Final Planeada");
			}

			if ((entity.getSemanaFinReal() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ entity.getSemanaFinReal(), 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Semana Final Real");
			}

			if (entity.getTareCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException("Codigo");
			}

			if (entity.getPsyPlanAccion().getPlacCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Plan de Accion");
			}

			if (entity.getPsyResponsable().getRespCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException("Responsable");
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
            	
            	if(entity.getFechaInicio().after(entity.getFechaFinReal())){
                	throw new ZMessManager("La fecha de inicio debe ir antes de la fecha final real");
                }
            	
            	if (entity.getFechaFinReal() != null) {
    				if(entity.getFechaFinReal().before(entity.getPsyPlanAccion().getFechaInicio())){
    					entity.setSemanaFinReal(calcularDiferenciaFechas(entity
    							.getFechaInicio(), entity
    							.getFechaFinReal()));
    				}else{
    					entity.setSemanaFinReal(calcularDiferenciaFechas(entity
    							.getPsyPlanAccion().getFechaInicio(), entity
    							.getFechaFinReal()));
    				}
    			}
            	
            	entity.setEstadoTarea(ESTADO_FINALIZADO);
            	
            }
            
            
            if(entity.getFechaInicio().after(entity.getFechaFinPlaneada())){
            	throw new ZMessManager("La fecha de inicio debe ir antes de la fecha final planeada");
            }
//            
//            if(entity.getFechaFinPlaneada().before(new Date())){
//            	throw new ZMessManager("No puede utilizar fechas anteriores");
//            }
            

            if(entity.getFechaFinPlaneada().before(entity.getPsyPlanAccion().getFechaInicio())){
				entity.setSemanaFinPlaneada(calcularDiferenciaFechas(entity
						.getFechaInicio(), entity
						.getFechaFinPlaneada()));
			}else{
				entity.setSemanaFinPlaneada(calcularDiferenciaFechas(entity
						.getPsyPlanAccion().getFechaInicio(), entity
						.getFechaFinPlaneada()));
			}

			PsyPlanAccion planAccion = new PsyPlanAccion();

			planAccion = entity.getPsyPlanAccion();
			if (entity.getFechaInicio().before(planAccion.getFechaInicio())) {
				planAccion.setFechaInicio(entity.getFechaInicio());
				cambioFechaPlan = true;
			}

			if (entity.getFechaFinPlaneada().after(
					planAccion.getFechaFinPlaneada())) {
				planAccion.setFechaFinPlaneada(entity.getFechaFinPlaneada());
				cambioFechaPlan = true;
			}
			
			if(planAccion.getFechaFinReal() !=null){
			if (entity.getFechaFinReal().after(planAccion.getFechaFinReal())) {
				planAccion.setFechaFinReal(entity.getFechaFinReal());
				cambioFechaPlan = true;
			}
			}

			if (cambioFechaPlan == true) {
				logicPsyPlanAccion1.updatePsyPlanAccion(planAccion);
			}

			psyTareaDAO.update(entity);

			log.debug("Actualizacion de Tarea exitosa");
		} catch (Exception e) {
			log.error("Actualziacion de Tarea faliida", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<PsyTareaDTO> getDataPsyTarea() throws Exception {
		try {
			List<PsyTarea> psyTarea = psyTareaDAO.findAll();

			List<PsyTareaDTO> psyTareaDTO = new ArrayList<PsyTareaDTO>();

			for (PsyTarea psyTareaTmp : psyTarea) {
				PsyTareaDTO psyTareaDTO2 = new PsyTareaDTO();

				psyTareaDTO2.setTareCodigo(psyTareaTmp.getTareCodigo());
				psyTareaDTO2
						.setDescripcion((psyTareaTmp.getDescripcion() != null) ? psyTareaTmp
								.getDescripcion() : null);
				psyTareaDTO2
						.setEstadoRegistro((psyTareaTmp.getEstadoRegistro() != null) ? psyTareaTmp
								.getEstadoRegistro() : null);
				psyTareaDTO2
						.setEstadoTarea((psyTareaTmp.getEstadoTarea() != null) ? psyTareaTmp
								.getEstadoTarea() : null);
				psyTareaDTO2.setFechaFinPlaneada(psyTareaTmp
						.getFechaFinPlaneada());
				psyTareaDTO2.setFechaFinReal(psyTareaTmp.getFechaFinReal());
				psyTareaDTO2.setFechaInicio(psyTareaTmp.getFechaInicio());
				psyTareaDTO2.setSemanaFinPlaneada((psyTareaTmp
						.getSemanaFinPlaneada() != null) ? psyTareaTmp
						.getSemanaFinPlaneada() : null);
				psyTareaDTO2
						.setSemanaFinReal((psyTareaTmp.getSemanaFinReal() != null) ? psyTareaTmp
								.getSemanaFinReal() : null);
				psyTareaDTO2
						.setPlacCodigo_PsyPlanAccion((psyTareaTmp
								.getPsyPlanAccion().getPlacCodigo() != null) ? psyTareaTmp
								.getPsyPlanAccion().getPlacCodigo() : null);
				psyTareaDTO2
						.setRespCodigo_PsyResponsable((psyTareaTmp
								.getPsyResponsable().getRespCodigo() != null) ? psyTareaTmp
								.getPsyResponsable().getRespCodigo() : null);

//				psyTareaDTO2
//						.setNombre_PsyPlanAccion((psyTareaTmp
//								.getPsyPlanAccion().getPlacCodigo() != null) ? (logicPsyPlanAccion1
//								.getPsyPlanAccion(psyTareaTmp
//										.getPsyPlanAccion().getPlacCodigo())
//								.getNombre()) : null);
//				psyTareaDTO2
//				.setNombre_PsyResponsable((psyTareaTmp
//						.getPsyResponsable().getRespCodigo() != null) ? (logicPsyResponsable2
//						.getPsyResponsable(psyTareaTmp
//								.getPsyResponsable().getRespCodigo())
//						.getNombre()) : null);
				
				psyTareaDTO2
					.setNombre_PsyPlanAccion((psyTareaTmp
						.getPsyPlanAccion().getPlacCodigo() != null) ? psyTareaTmp.getPsyPlanAccion().getNombre() : null);
				psyTareaDTO2
						.setNombre_PsyResponsable((psyTareaTmp
								.getPsyResponsable().getRespCodigo() != null) ? psyTareaTmp.getPsyResponsable().getNombre() : null);

				psyTareaDTO.add(psyTareaDTO2);
			}

			return psyTareaDTO;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Transactional(readOnly = true)
	public List<PsyTareaDTO> getDataPsyTareaByPsyPlanAccion(Long placCodigo) throws Exception {
		try {
			
			Object[] variables = { "psyPlanAccion.placCodigo", false,
					placCodigo, "=", "estadoRegistro", true, "A", "=" };

			List<PsyTarea> psyTarea = findByCriteria(
					variables, null, null);

			List<PsyTareaDTO> psyTareaDTO = new ArrayList<PsyTareaDTO>();

			for (PsyTarea psyTareaTmp : psyTarea) {
				PsyTareaDTO psyTareaDTO2 = new PsyTareaDTO();

				psyTareaDTO2.setTareCodigo(psyTareaTmp.getTareCodigo());
				psyTareaDTO2
						.setDescripcion((psyTareaTmp.getDescripcion() != null) ? psyTareaTmp
								.getDescripcion() : null);
				psyTareaDTO2
						.setEstadoRegistro((psyTareaTmp.getEstadoRegistro() != null) ? psyTareaTmp
								.getEstadoRegistro() : null);
				psyTareaDTO2
						.setEstadoTarea((psyTareaTmp.getEstadoTarea()!=null || !psyTareaTmp.getEstadoTarea().trim().equals("")) ? 
			            		(psyTareaTmp.getEstadoTarea().equals("P")) ? "Pendiente" : 
			            			(psyTareaTmp.getEstadoTarea().equals("E")) ? "En curso" :
			            				(psyTareaTmp.getEstadoTarea().equals("F")) ? "Finalizado" : null :null);
				psyTareaDTO2.setFechaFinPlaneada(psyTareaTmp
						.getFechaFinPlaneada());
				psyTareaDTO2.setFechaFinReal(psyTareaTmp.getFechaFinReal());
				psyTareaDTO2.setFechaInicio(psyTareaTmp.getFechaInicio());
				psyTareaDTO2.setSemanaFinPlaneada((psyTareaTmp
						.getSemanaFinPlaneada() != null) ? psyTareaTmp
						.getSemanaFinPlaneada() : null);
				psyTareaDTO2
						.setSemanaFinReal((psyTareaTmp.getSemanaFinReal() != null) ? psyTareaTmp
								.getSemanaFinReal() : null);
				psyTareaDTO2
						.setPlacCodigo_PsyPlanAccion((psyTareaTmp
								.getPsyPlanAccion().getPlacCodigo() != null) ? psyTareaTmp
								.getPsyPlanAccion().getPlacCodigo() : null);
				psyTareaDTO2
						.setRespCodigo_PsyResponsable((psyTareaTmp
								.getPsyResponsable().getRespCodigo() != null) ? psyTareaTmp
								.getPsyResponsable().getRespCodigo() : null);

//				psyTareaDTO2
//						.setNombre_PsyPlanAccion((psyTareaTmp
//								.getPsyPlanAccion().getPlacCodigo() != null) ? (logicPsyPlanAccion1
//								.getPsyPlanAccion(psyTareaTmp
//										.getPsyPlanAccion().getPlacCodigo())
//								.getNombre()) : null);
//				psyTareaDTO2
//				.setNombre_PsyResponsable((psyTareaTmp
//						.getPsyResponsable().getRespCodigo() != null) ? (logicPsyResponsable2
//						.getPsyResponsable(psyTareaTmp
//								.getPsyResponsable().getRespCodigo())
//						.getNombre()) : null);
				
				psyTareaDTO2
					.setNombre_PsyPlanAccion((psyTareaTmp
						.getPsyPlanAccion().getPlacCodigo() != null) ? psyTareaTmp.getPsyPlanAccion().getNombre() : null);
				psyTareaDTO2
						.setNombre_PsyResponsable((psyTareaTmp
								.getPsyResponsable().getRespCodigo() != null) ? psyTareaTmp.getPsyResponsable().getNombre() : null);

				psyTareaDTO.add(psyTareaDTO2);
			}

			return psyTareaDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public PsyTarea getPsyTarea(Long tareCodigo) throws Exception {
		log.debug("getting PsyTarea instance");

		PsyTarea entity = null;

		try {
			entity = psyTareaDAO.findById(tareCodigo);
		} catch (Exception e) {
			log.error("get PsyTarea failed", e);
			throw new ZMessManager().new FindingException("PsyTarea");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<PsyTarea> findPagePsyTarea(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		List<PsyTarea> entity = null;

		try {
			entity = psyTareaDAO.findPage(sortColumnName, sortAscending,
					startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("PsyTarea Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberPsyTarea() throws Exception {
		Long entity = null;

		try {
			entity = psyTareaDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("PsyTarea Count");
		} finally {
		}

		return entity;
	}

	/**
	 *
	 * @param varibles
	 *            este arreglo debera tener:
	 *
	 *            [0] = String variable = (String) varibles[i]; representa como
	 *            se llama la variable en el pojo
	 *
	 *            [1] = Boolean booVariable = (Boolean) varibles[i + 1];
	 *            representa si el valor necesita o no ''(comillas simples)usado
	 *            para campos de tipo string
	 *
	 *            [2] = Object value = varibles[i + 2]; representa el valor que
	 *            se va a buscar en la BD
	 *
	 *            [3] = String comparator = (String) varibles[i + 3]; representa
	 *            que tipo de busqueda voy a hacer.., ejemplo: where
	 *            nombre=william o where nombre<>william, en este campo iria el
	 *            tipo de comparador que quiero si es = o <>
	 *
	 *            Se itera de 4 en 4..., entonces 4 registros del arreglo
	 *            representan 1 busqueda en un campo, si se ponen mas pues el
	 *            continuara buscando en lo que se le ingresen en los otros 4
	 *
	 *
	 * @param variablesBetween
	 *
	 *            la diferencia son estas dos posiciones
	 *
	 *            [0] = String variable = (String) varibles[j]; la variable ne
	 *            la BD que va a ser buscada en un rango
	 *
	 *            [1] = Object value = varibles[j + 1]; valor 1 para buscar en
	 *            un rango
	 *
	 *            [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en
	 *            un rango ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria
	 *            value2
	 *
	 *            [3] = String comparator1 = (String) varibles[j + 3];
	 *            comparador 1 ejemplo: a comparator1 1 and a < 5
	 *
	 *            [4] = String comparator2 = (String) varibles[j + 4];
	 *            comparador 2 ejemplo: a comparador1>1 and a comparador2<5 (el
	 *            original: a > 1 and a < 5) *
	 * @param variablesBetweenDates
	 *            (en este caso solo para mysql) [0] = String variable =
	 *            (String) varibles[k]; el nombre de la variable que hace
	 *            referencia a una fecha
	 *
	 *            [1] = Object object1 = varibles[k + 2]; fecha 1 a
	 *            comparar(deben ser dates)
	 *
	 *            [2] = Object object2 = varibles[k + 3]; fecha 2 a
	 *            comparar(deben ser dates)
	 *
	 *            esto hace un between entre las dos fechas.
	 *
	 * @return lista con los objetos que se necesiten
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<PsyTarea> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		List<PsyTarea> list = new ArrayList<PsyTarea>();
		String where = new String();
		String tempWhere = new String();

		if (variables != null) {
			for (int i = 0; i < variables.length; i++) {
				if ((variables[i] != null) && (variables[i + 1] != null)
						&& (variables[i + 2] != null)
						&& (variables[i + 3] != null)) {
					String variable = (String) variables[i];
					Boolean booVariable = (Boolean) variables[i + 1];
					Object value = variables[i + 2];
					String comparator = (String) variables[i + 3];

					if (booVariable.booleanValue()) {
						tempWhere = (tempWhere.length() == 0) ? ("(model."
								+ variable + " " + comparator + " \'" + value + "\' )")
								: (tempWhere + " AND (model." + variable + " "
										+ comparator + " \'" + value + "\' )");
					} else {
						tempWhere = (tempWhere.length() == 0) ? ("(model."
								+ variable + " " + comparator + " " + value + " )")
								: (tempWhere + " AND (model." + variable + " "
										+ comparator + " " + value + " )");
					}
				}

				i = i + 3;
			}
		}

		if (variablesBetween != null) {
			for (int j = 0; j < variablesBetween.length; j++) {
				if ((variablesBetween[j] != null)
						&& (variablesBetween[j + 1] != null)
						&& (variablesBetween[j + 2] != null)
						&& (variablesBetween[j + 3] != null)
						&& (variablesBetween[j + 4] != null)) {
					String variable = (String) variablesBetween[j];
					Object value = variablesBetween[j + 1];
					Object value2 = variablesBetween[j + 2];
					String comparator1 = (String) variablesBetween[j + 3];
					String comparator2 = (String) variablesBetween[j + 4];
					tempWhere = (tempWhere.length() == 0) ? ("(" + value + " "
							+ comparator1 + " " + variable + " and " + variable
							+ " " + comparator2 + " " + value2 + " )")
							: (tempWhere + " AND (" + value + " " + comparator1
									+ " " + variable + " and " + variable + " "
									+ comparator2 + " " + value2 + " )");
				}

				j = j + 4;
			}
		}

		if (variablesBetweenDates != null) {
			for (int k = 0; k < variablesBetweenDates.length; k++) {
				if ((variablesBetweenDates[k] != null)
						&& (variablesBetweenDates[k + 1] != null)
						&& (variablesBetweenDates[k + 2] != null)) {
					String variable = (String) variablesBetweenDates[k];
					Object object1 = variablesBetweenDates[k + 1];
					Object object2 = variablesBetweenDates[k + 2];
					String value = null;
					String value2 = null;

					try {
						Date date1 = (Date) object1;
						Date date2 = (Date) object2;
						value = Utilities
								.formatDateWithoutTimeInAStringForBetweenWhere(date1);
						value2 = Utilities
								.formatDateWithoutTimeInAStringForBetweenWhere(date2);
					} catch (Exception e) {
						list = null;
						throw e;
					}

					tempWhere = (tempWhere.length() == 0) ? ("(model."
							+ variable + " between \'" + value + "\' and \'"
							+ value2 + "\')") : (tempWhere + " AND (model."
							+ variable + " between \'" + value + "\' and \'"
							+ value2 + "\')");
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
			list = psyTareaDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}
	
	@Transactional(readOnly = true)
	public List<PsyPlanAccion> findPsyPlanAccionByEmpresa(PsyEmpresa empresa) throws Exception {
		List<PsyPlanAccion> planesDeAccion = new ArrayList<PsyPlanAccion>();

		try {
			planesDeAccion = psyPlanAccionDAO.findPlanesAccionByEmpresaByPestActivoByEstadoPlan(empresa);
		} catch (Exception e) {
			throw new ZMessManager(e.getMessage());
		} finally {
		}

		return planesDeAccion;
	}
}
