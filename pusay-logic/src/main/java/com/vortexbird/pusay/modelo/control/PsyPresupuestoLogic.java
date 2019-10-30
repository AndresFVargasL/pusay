package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyPresupuestoDTO;
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
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("PsyPresupuestoLogic")
public class PsyPresupuestoLogic implements IPsyPresupuestoLogic {
	private static final Logger log = LoggerFactory
			.getLogger(PsyPresupuestoLogic.class);

	/**
	 * DAO injected by Spring that manages PsyPresupuesto entities
	 *
	 */
	@Autowired
	private IPsyPresupuestoDAO psyPresupuestoDAO;

	/**
	 * DAO injected by Spring that manages PsyItemPresupuesto entities
	 *
	 */
	@Autowired
	private IPsyItemPresupuestoDAO psyItemPresupuestoDAO;

	/**
	 * Logic injected by Spring that manages PsyMoneda entities
	 *
	 */
	@Autowired
	IPsyMonedaLogic logicPsyMoneda1;
	 
	@Autowired   
	 private IPsyPlanAccionDAO psyPlanAccionDAO;
	@Autowired
    private IPsyIpuLogic psyIpuLogic;

	/**
	 * Logic injected by Spring that manages PsyPlanAccion entities
	 *
	 */
	@Autowired
	IPsyPlanAccionLogic logicPsyPlanAccion2;

	@Transactional(readOnly = true)
	public List<PsyPresupuesto> getPsyPresupuesto() throws Exception {
		log.debug("finding all PsyPresupuesto instances");

		List<PsyPresupuesto> list = new ArrayList<PsyPresupuesto>();

		try {
			list = psyPresupuestoDAO.findAll();
		} catch (Exception e) {
			log.error("finding all PsyPresupuesto failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL
					+ "PsyPresupuesto");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void savePsyPresupuesto(PsyPresupuesto entity) throws Exception {
		log.debug("saving PsyPresupuesto instance");

		try {
			if (entity.getPsyMoneda() == null) {
				throw new ZMessManager().new ForeignException("Moneda");
			}

			if (entity.getPsyPlanAccion() == null) {
				throw new ZMessManager().new ForeignException("Plan de Accion");
			}

			if (entity.getEstadoPresupuesto() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Estado Presupuesto");
			}

			if ((entity.getEstadoPresupuesto() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getEstadoPresupuesto(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Estado Presupuesto");
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

			if (entity.getFechaInicio() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Fecha de Inicio");
			}

			if (entity.getPsyMoneda().getMoneCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException("Moneda");
			}

			if (entity.getPsyPlanAccion().getPlacCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Plan de Accion");
			}

			if (entity.getFechaFin() != null) {
				if (entity.getFechaFin().before(entity.getFechaInicio())) {
					throw new ZMessManager(
							"La fecha final no puede ser anterior a la fecha inicial");
				}

				// if(entity.getFechaFin().before(new Date())){
				// throw new
				// ZMessManager("No puede utilizar fechas anteriores");
				// }

			}

			// if(entity.getFechaInicio().before(new Date())){
			// throw new ZMessManager("No puede utilizar fechas anteriores");
			// }
			
			
//			Object[] variables = {"psyPlanAccion.placCodigo",false,entity.getPsyPlanAccion().getPlacCodigo(),"=",
//					"estadoRegistro",true,"A","="};
			
			List<PsyPresupuesto> presupuestosExistentes = psyPresupuestoDAO.consultarPresupuestos(Utilities.getEmpresaIntoSession());
			
			if(presupuestosExistentes.size() > 0){
				for (PsyPresupuesto presupuesto : presupuestosExistentes) {
					if(presupuesto.getPsyPlanAccion().getPlacCodigo().equals(entity.getPsyPlanAccion().getPlacCodigo())){
						throw new ZMessManager(
								"Ya existe otro presupuesto con el mismo proyecto asignado");
					}
				}
			}
			
			

			psyPresupuestoDAO.save(entity);

			log.debug("save PsyPresupuesto successful");
		} catch (Exception e) {
			log.error("save PsyPresupuesto failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletePsyPresupuesto(PsyPresupuesto entity) throws Exception {
		log.debug("deleting PsyPresupuesto instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("PsyPresupuesto");
		}

		if (entity.getPresCodigo() == null) {
			throw new ZMessManager().new EmptyFieldException("presCodigo");
		}

		List<PsyItemPresupuesto> psyItemPresupuestos = null;

		try {
			psyItemPresupuestos = psyItemPresupuestoDAO.findByProperty(
					"psyPresupuesto.presCodigo", entity.getPresCodigo());

			if (Utilities.validationsList(psyItemPresupuestos) == true) {
				throw new ZMessManager().new DeletingException(
						"psyItemPresupuestos");
			}

			psyPresupuestoDAO.delete(entity);

			log.debug("delete PsyPresupuesto successful");
		} catch (Exception e) {
			log.error("delete PsyPresupuesto failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updatePsyPresupuesto(PsyPresupuesto entity) throws Exception {
		log.debug("updating PsyPresupuesto instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Presupuesto");
			}

			if (entity.getPsyMoneda() == null) {
				throw new ZMessManager().new ForeignException("Moneda");
			}

			if (entity.getPsyPlanAccion() == null) {
				throw new ZMessManager().new ForeignException("Plan de Accion");
			}

			if (entity.getEstadoPresupuesto() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Estado Presupuesto");
			}

			if ((entity.getEstadoPresupuesto() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getEstadoPresupuesto(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Estado Presupuesto");
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

			if (entity.getFechaInicio() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Fecha de Inicio");
			}

			if (entity.getPresCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException("Codigo");
			}

			if (entity.getPsyMoneda().getMoneCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException("Moneda");
			}

			if (entity.getPsyPlanAccion().getPlacCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Plan de Accion");
			}

			if (entity.getFechaFin() != null) {
				if (entity.getFechaFin().before(entity.getFechaInicio())) {
					throw new ZMessManager(
							"La fecha final no puede ser anterior a la fecha inicial");
				}

				// if(entity.getFechaFin().before(new Date())){
				// throw new
				// ZMessManager("No puede utilizar fechas anteriores");
				// }

			}
			
//			Object[] variables = {"psyPlanAccion.placCodigo",false,entity.getPsyPlanAccion().getPlacCodigo(),"=",
//					"estadoRegistro",true,"A","="};
//			List<PsyPresupuesto> presupuestosExistentes = findByCriteria(variables, null, null);
//			
//			if(presupuestosExistentes.size()!=0){
//				for (PsyPresupuesto psyPresupuesto : presupuestosExistentes) {
//					if(psyPresupuesto.getPresCodigo()>entity.getPresCodigo() || psyPresupuesto.getPresCodigo()<entity.getPresCodigo()){
//						throw new ZMessManager(
//								"Ya existe otro presupuesto con el mismo plan de accion asignado");	
//					}
//				}
//				
//				
//			}
			

			// if(entity.getFechaInicio().before(new Date())){
			// throw new ZMessManager("No puede utilizar fechas anteriores");
			// }

			psyPresupuestoDAO.update(entity);

			log.debug("update PsyPresupuesto successful");
		} catch (Exception e) {
			log.error("update PsyPresupuesto failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<PsyPresupuestoDTO> getDataPsyPresupuesto(PsyEmpresa empresa) throws Exception {
		try {
			List<PsyPresupuesto> psyPresupuesto = psyPresupuestoDAO.findPresupuestoByEmpresaByPestActivo(empresa);

			List<PsyPresupuestoDTO> psyPresupuestoDTO = new ArrayList<PsyPresupuestoDTO>();

			for (PsyPresupuesto psyPresupuestoTmp : psyPresupuesto) {
				PsyPresupuestoDTO psyPresupuestoDTO2 = new PsyPresupuestoDTO();

				psyPresupuestoDTO2.setPresCodigo(psyPresupuestoTmp
						.getPresCodigo());
				psyPresupuestoDTO2.setEstadoPresupuesto((psyPresupuestoTmp
						.getEstadoPresupuesto() != null) ? psyPresupuestoTmp
						.getEstadoPresupuesto() : null);
				psyPresupuestoDTO2.setEstadoRegistro((psyPresupuestoTmp
						.getEstadoRegistro() != null) ? psyPresupuestoTmp
						.getEstadoRegistro() : null);
				psyPresupuestoDTO2.setFechaFin(psyPresupuestoTmp.getFechaFin());
				psyPresupuestoDTO2.setFechaInicio(psyPresupuestoTmp
						.getFechaInicio());
				psyPresupuestoDTO2
						.setMoneCodigo_PsyMoneda((psyPresupuestoTmp
								.getPsyMoneda().getMoneCodigo() != null) ? psyPresupuestoTmp
								.getPsyMoneda().getMoneCodigo() : null);
				psyPresupuestoDTO2
						.setPlacCodigo_PsyPlanAccion((psyPresupuestoTmp
								.getPsyPlanAccion().getPlacCodigo() != null) ? psyPresupuestoTmp
								.getPsyPlanAccion().getPlacCodigo() : null);
				psyPresupuestoDTO2
						.setAbrevMoneda((psyPresupuestoTmp
								.getPsyMoneda().getMoneCodigo() != null) ? psyPresupuestoTmp.getPsyMoneda().getAbreviatura() : null);
				psyPresupuestoDTO2
						.setNombrePlanAccion((psyPresupuestoTmp
								.getPsyPlanAccion().getPlacCodigo() != null) ? psyPresupuestoTmp.getPsyPlanAccion().getNombre() : null);
				psyPresupuestoDTO2.setEstadoPresupuestoInterpretado((psyPresupuestoTmp
						.getEstadoPresupuesto() != null) ? (psyPresupuestoTmp
						.getEstadoPresupuesto().equals("A")) ? "Abierto" : (psyPresupuestoTmp
								.getEstadoPresupuesto().equals("I")) ? "Iniciado" : (psyPresupuestoTmp
										.getEstadoPresupuesto().equals("C")) ? "Cerrado" : null : null);
				psyPresupuestoDTO2.setEstadoRegistroInterpretado((psyPresupuestoTmp
						.getEstadoRegistro() != null) ? (psyPresupuestoTmp
						.getEstadoRegistro().equals("A")) ? "Activo" : (psyPresupuestoTmp
								.getEstadoRegistro().equals("I")) ? "Inactivo" : null : null);
                 if(psyPresupuestoTmp.getEstadoPresupuesto().equals("A")){
                	 psyPresupuestoDTO2.setBtnGenerarPresupuestoDefinitivo(false);
                 }else{
                	 psyPresupuestoDTO2.setBtnGenerarPresupuestoDefinitivo(true);
                 }
				
				
				psyPresupuestoDTO.add(psyPresupuestoDTO2);
			}

			return psyPresupuestoDTO;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Transactional(readOnly = true)
	public List<PsyPresupuestoDTO> getDataPsyPresupuestoOnItem(PsyEmpresa empresa) throws Exception {
		try {
			List<PsyPresupuesto> psyPresupuesto = psyPresupuestoDAO.findPresupuestoByEmpresaByPestActivoByPresActivo(empresa);

			List<PsyPresupuestoDTO> psyPresupuestoDTO = new ArrayList<PsyPresupuestoDTO>();

			for (PsyPresupuesto psyPresupuestoTmp : psyPresupuesto) {
				PsyPresupuestoDTO psyPresupuestoDTO2 = new PsyPresupuestoDTO();

				psyPresupuestoDTO2.setPresCodigo(psyPresupuestoTmp
						.getPresCodigo());
				psyPresupuestoDTO2.setEstadoPresupuesto((psyPresupuestoTmp
						.getEstadoPresupuesto() != null) ? psyPresupuestoTmp
						.getEstadoPresupuesto() : null);
				psyPresupuestoDTO2.setEstadoRegistro((psyPresupuestoTmp
						.getEstadoRegistro() != null) ? psyPresupuestoTmp
						.getEstadoRegistro() : null);
				psyPresupuestoDTO2.setFechaFin(psyPresupuestoTmp.getFechaFin());
				psyPresupuestoDTO2.setFechaInicio(psyPresupuestoTmp
						.getFechaInicio());
				psyPresupuestoDTO2
						.setMoneCodigo_PsyMoneda((psyPresupuestoTmp
								.getPsyMoneda().getMoneCodigo() != null) ? psyPresupuestoTmp
								.getPsyMoneda().getMoneCodigo() : null);
				psyPresupuestoDTO2
						.setPlacCodigo_PsyPlanAccion((psyPresupuestoTmp
								.getPsyPlanAccion().getPlacCodigo() != null) ? psyPresupuestoTmp
								.getPsyPlanAccion().getPlacCodigo() : null);
				psyPresupuestoDTO2
						.setAbrevMoneda((psyPresupuestoTmp
								.getPsyMoneda().getMoneCodigo() != null) ? psyPresupuestoTmp.getPsyMoneda().getAbreviatura() : null);
				psyPresupuestoDTO2
						.setNombrePlanAccion((psyPresupuestoTmp
								.getPsyPlanAccion().getPlacCodigo() != null) ? psyPresupuestoTmp.getPsyPlanAccion().getNombre() : null);
				psyPresupuestoDTO2.setEstadoPresupuestoInterpretado((psyPresupuestoTmp
						.getEstadoPresupuesto() != null) ? (psyPresupuestoTmp
						.getEstadoPresupuesto().equals("A")) ? "Abierto" : (psyPresupuestoTmp
								.getEstadoPresupuesto().equals("I")) ? "Iniciado" : (psyPresupuestoTmp
										.getEstadoPresupuesto().equals("C")) ? "Cerrado" : null : null);
				psyPresupuestoDTO2.setEstadoRegistroInterpretado((psyPresupuestoTmp
						.getEstadoRegistro() != null) ? (psyPresupuestoTmp
						.getEstadoRegistro().equals("A")) ? "Activo" : (psyPresupuestoTmp
								.getEstadoRegistro().equals("I")) ? "Inactivo" : null : null);
                 if(psyPresupuestoTmp.getEstadoPresupuesto().equals("A")){
                	 psyPresupuestoDTO2.setBtnGenerarPresupuestoDefinitivo(false);
                 }else{
                	 psyPresupuestoDTO2.setBtnGenerarPresupuestoDefinitivo(true);
                 }
				
				
				psyPresupuestoDTO.add(psyPresupuestoDTO2);
			}

			return psyPresupuestoDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public PsyPresupuesto getPsyPresupuesto(Long presCodigo) throws Exception {
		log.debug("getting PsyPresupuesto instance");

		PsyPresupuesto entity = null;

		try {
			entity = psyPresupuestoDAO.findById(presCodigo);
		} catch (Exception e) {
			log.error("get PsyPresupuesto failed", e);
			throw new ZMessManager().new FindingException("PsyPresupuesto");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<PsyPresupuesto> findPagePsyPresupuesto(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		List<PsyPresupuesto> entity = null;

		try {
			entity = psyPresupuestoDAO.findPage(sortColumnName, sortAscending,
					startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					"PsyPresupuesto Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberPsyPresupuesto() throws Exception {
		Long entity = null;

		try {
			entity = psyPresupuestoDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					"PsyPresupuesto Count");
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
	public List<PsyPresupuesto> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		List<PsyPresupuesto> list = new ArrayList<PsyPresupuesto>();
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
			list = psyPresupuestoDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void generarPresupuestoDefinitivo(Long presCodigo,Long placCodigo) throws Exception {
		List<PsyItemPresupuesto> listIp = psyItemPresupuestoDAO.find("from PsyItemPresupuesto where psyPresupuesto='"+presCodigo+"'");
		try {
			
		    if(listIp.size()==0){
		    	throw new ZMessManager("El Presupuesto  no tiene items asignados");
		    }else{
		    	List<PsyItemPresupuesto> list = new ArrayList<PsyItemPresupuesto>();
				Long  maxsemana=psyItemPresupuestoDAO.consultarMaxSemana(presCodigo);
				Long minSemanInicio =psyItemPresupuestoDAO.consultarMinSemana(presCodigo);
			for (int i = 1; i <maxsemana+1; i++) {
				List<Date> lst = psyItemPresupuestoDAO.maxMinSemana(presCodigo,Long.parseLong(""+i));
				int contador=1;
				while (lst.get(0)==null) {
					lst = psyItemPresupuestoDAO.maxMinSemana(presCodigo,Long.parseLong(""+(i-contador+minSemanInicio)));
					contador++;
				}
				 
				PsyIpu psyIpu= new PsyIpu();
				psyIpu.setPsyPlanAccion(psyPlanAccionDAO.findById(placCodigo));
				psyIpu.setFechaInforme(new Date());
				psyIpu.setPeriodo(""+i);										
				psyIpu.setEstadoIpu("A");
				psyIpu.setEstadoRegistro("A");
				
				psyIpu.setAvancePresupuestado(psyItemPresupuestoDAO.consultarSumSemana(presCodigo, Long.parseLong(""+i)));
				
				 //psyIpu.setPeriodoFechaInicio(lst.get(0));
				  //  psyIpu.setPeriodoFechaFin(lst.get(1));
				if(psyItemPresupuestoDAO.find("from PsyItemPresupuesto where semana='"+i+"' and psyPresupuesto='"+presCodigo+"'").size()==0){
					 psyIpu.setPeriodoFechaInicio(Utilities.sumarDiasFecha(psyPresupuestoDAO.findById(presCodigo).getFechaInicio(), 7*(i-1)));
					// psyIpu.setPeriodoFechaFin(Utilities.sumarDiasFecha(psyPresupuestoDAO.findById(presCodigo).getFechaInicio(), 7*i));
				}else {
					 psyIpu.setPeriodoFechaInicio(lst.get(0));
					// psyIpu.setPeriodoFechaFin(lst.get(1));
				}
				psyIpu.setTipoIpu("P");
				psyIpuLogic.savePsyIpu(psyIpu);
				
			}
			/*
			list = psyItemPresupuestoDAO.find("from PsyItemPresupuesto where psyPresupuesto='"+presCodigo+"'");
			for (int i = 0; i < list.size(); i++) {
				PsyItemPresupuesto psyItemPresupuesto = new PsyItemPresupuesto();
				psyItemPresupuesto=list.get(i);
				
				psyItemPresupuestoDAO.update(psyItemPresupuesto);
			}
			*/
			PsyPresupuesto psyPresupuesto = new PsyPresupuesto();
			psyPresupuesto=psyPresupuestoDAO.findById(presCodigo);
			psyPresupuesto.setEstadoPresupuesto("I");
			psyPresupuestoDAO.update(psyPresupuesto);
			
			PsyPlanAccion psyPlanAccion = new PsyPlanAccion();
			psyPlanAccion=psyPlanAccionDAO.findById(placCodigo);
			psyPlanAccion.setEstadoPlanAccion("P");
		    psyPlanAccionDAO.update(psyPlanAccion);
		    }
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
}
