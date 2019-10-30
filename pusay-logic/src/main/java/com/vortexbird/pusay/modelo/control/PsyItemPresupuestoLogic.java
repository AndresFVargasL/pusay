package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyItemPresupuestoDTO;
import com.vortexbird.pusay.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
@Service("PsyItemPresupuestoLogic")
public class PsyItemPresupuestoLogic implements IPsyItemPresupuestoLogic {
	private static final Logger log = LoggerFactory
			.getLogger(PsyItemPresupuestoLogic.class);

	/**
	 * DAO injected by Spring that manages PsyItemPresupuesto entities
	 *
	 */
	@Autowired
	private IPsyItemPresupuestoDAO psyItemPresupuestoDAO;

	/**
	 * Logic injected by Spring that manages PsyPresupuesto entities
	 *
	 */
	@Autowired
	IPsyPresupuestoLogic logicPsyPresupuesto1;

	/**
	 * Logic injected by Spring that manages PsyTipoItemPresupuesto entities
	 *
	 */
	@Autowired
	IPsyTipoItemPresupuestoLogic logicPsyTipoItemPresupuesto2;
	
	@Autowired
	IPsyPlanAccionLogic logicPsyPlanAccion3;

	@Transactional(readOnly = true)
	public List<PsyItemPresupuesto> getPsyItemPresupuesto() throws Exception {
		log.debug("finding all PsyItemPresupuesto instances");

		List<PsyItemPresupuesto> list = new ArrayList<PsyItemPresupuesto>();

		try {
			list = psyItemPresupuestoDAO.findAll();
		} catch (Exception e) {
			log.error("finding all PsyItemPresupuesto failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL
					+ "PsyItemPresupuesto");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void savePsyItemPresupuesto(PsyItemPresupuesto entity)
			throws Exception {
		log.debug("saving PsyItemPresupuesto instance");

		try {
			if(entity.getPsyTipoItemPresupuesto()==null){
				throw new ZMessManager("Seleccione un tipo de item de la lista");
			}
			
			if (entity.getPsyPresupuesto() == null) {
				throw new ZMessManager().new ForeignException("Presupuesto");
			}

			if (entity.getPsyTipoItemPresupuesto() == null) {
				throw new ZMessManager().new ForeignException(
						"Tipo de Item de Presupuesto");
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

			// if (entity.getValorEjecutado() != null) {
			// Double value = entity.getValorEjecutado();
			// Double value2 = new Double(String.format( "%.2f", value ));
			// entity.setValorEjecutado(value2);
			//
			// }

			if ((entity.getValorEjecutado() != null)
					&& (entity.getValorEjecutado().toString().length() > 10 == true)) {
				throw new ZMessManager().new NotValidFormatException(
						"Valor Ejecutado");
			}

			if (entity.getValorPresupuestado() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Valor Presupuestado");
			}

			// if (entity.getValorPresupuestado() != null) {
			// Double value = entity.getValorPresupuestado();
			// Double value2 = new Double(String.format( "%.2f", value ));
			// entity.setValorPresupuestado(value2);
			// }

			if ((entity.getValorPresupuestado() != null)
					&& (entity.getValorPresupuestado().toString().length() > 10 == true)) {
				throw new ZMessManager().new NotValidFormatException(
						"Valor Presupuestado");
			}

			if (entity.getPsyPresupuesto().getPresCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException("Presupuesto");
			}

			if (entity.getPsyTipoItemPresupuesto().getTiipCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Tipo de Item de Presupuesto");
			}

			if (entity.getFechaInicio() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Fecha de Inicio");
			}

			if (entity.getFechaFin() != null) {
				if (entity.getFechaFin().before(entity.getFechaInicio())) {
					throw new ZMessManager(
							"La fecha  final no puede ser anterior a la fecha inicial");
				}

				if (entity.getFechaFin().after(
						entity.getPsyPresupuesto().getFechaFin())) {
					throw new ZMessManager(
							"La fecha final del item no puede sobrepasar a la fecha final del presupuesto");
				}

				if (entity.getValorEjecutado() == null) {
					throw new ZMessManager(
							"Al asignar una fecha final debe asignar un valor ejecutado");
				}

				

			}

			if (entity.getFechaInicio().before(
					entity.getPsyPresupuesto().getFechaInicio())) {
				throw new ZMessManager(
						"La fecha inicial del item no puede ser anterior a la fecha inicial del presupuesto");
			}

			if (entity.getValorEjecutado() != null) {
				if (entity.getFechaFin() == null) {
					throw new ZMessManager(
							"Si asigna un valor ejecutado debe asignar una fecha final");
				}
			}
			
			if(entity.getFechaInicio() != null){
				PsyPresupuesto presupuesto = new PsyPresupuesto();
				presupuesto = logicPsyPresupuesto1.getPsyPresupuesto(entity.getPsyPresupuesto().getPresCodigo());
				
				PsyPlanAccion planAccion = new PsyPlanAccion();
				planAccion = logicPsyPlanAccion3.getPsyPlanAccion(presupuesto.getPsyPlanAccion().getPlacCodigo());

				entity.setSemana(Utilities.calcularDiferenciaFechas(planAccion.getFechaInicio()
						, entity.getFechaInicio()));
			}
			
			

			psyItemPresupuestoDAO.save(entity);

			log.debug("save PsyItemPresupuesto successful");
		} catch (Exception e) {
			log.error("save PsyItemPresupuesto failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletePsyItemPresupuesto(PsyItemPresupuesto entity)
			throws Exception {
		log.debug("deleting PsyItemPresupuesto instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion(
					"PsyItemPresupuesto");
		}

		if (entity.getIpreCodigo() == null) {
			throw new ZMessManager().new EmptyFieldException("ipreCodigo");
		}

		try {
			psyItemPresupuestoDAO.delete(entity);

			log.debug("delete PsyItemPresupuesto successful");
		} catch (Exception e) {
			log.error("delete PsyItemPresupuesto failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updatePsyItemPresupuesto(PsyItemPresupuesto entity)
			throws Exception {
		log.debug("updating PsyItemPresupuesto instance");

		try {
			if(entity.getPsyTipoItemPresupuesto()==null){
				throw new ZMessManager("Seleccione un tipo de item de la lista");
			}
			
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion(
						"Item Presupuesto");
			}

			if (entity.getPsyPresupuesto() == null) {
				throw new ZMessManager().new ForeignException("Presupuesto");
			}

			if (entity.getPsyTipoItemPresupuesto() == null) {
				throw new ZMessManager().new ForeignException(
						"Tipo de Item de Presupuesto");
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
				throw new ZMessManager().new EmptyFieldException("Fecha Inicio");
			}

			if (entity.getIpreCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException("ipreCodigo");
			}

			// if (entity.getValorEjecutado() != null) {
			// Double value = entity.getValorEjecutado();
			// Double value2 = new Double(String.format( "%.2f", value ));
			// entity.setValorEjecutado(value2);
			//
			// }

			if ((entity.getValorEjecutado() != null)
					&& (entity.getValorEjecutado().toString().length() > 10 == true)) {
				throw new ZMessManager().new NotValidFormatException(
						"Valor Ejecutado");
			}

			if (entity.getValorPresupuestado() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Valor Presupuestado");
			}

			// if (entity.getValorPresupuestado() != null) {
			// Double value = entity.getValorPresupuestado();
			// Double value2 = new Double(String.format( "%.2f", value ));
			// entity.setValorPresupuestado(value2);
			// }

			if ((entity.getValorPresupuestado() != null)
					&& (entity.getValorPresupuestado().toString().length() > 10 == true)) {
				throw new ZMessManager().new NotValidFormatException(
						"Valor Presupuestado");
			}

			if (entity.getPsyPresupuesto().getPresCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException("Presupuesto");
			}

			if (entity.getPsyTipoItemPresupuesto().getTiipCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Tipo de Item de Presupuesto");
			}

			if (entity.getFechaInicio() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Fecha de Inicio");
			}

			if (entity.getFechaFin() != null) {
				if (entity.getFechaFin().before(entity.getFechaInicio())) {
					throw new ZMessManager(
							"La fecha  final no puede ser anterior a la fecha inicial");
				}

				if (entity.getFechaFin().after(
						entity.getPsyPresupuesto().getFechaFin())) {
					throw new ZMessManager(
							"La fecha final del item no puede sobrepasar a la fecha final del presupuesto");
				}

				if (entity.getValorEjecutado() == null) {
					throw new ZMessManager(
							"Al asignar una fecha final debe asignar un valor ejecutado");
				}
				
				PsyPresupuesto presupuesto = new PsyPresupuesto();
				presupuesto = logicPsyPresupuesto1.getPsyPresupuesto(entity.getPsyPresupuesto().getPresCodigo());
				
				PsyPlanAccion planAccion = new PsyPlanAccion();
				planAccion = logicPsyPlanAccion3.getPsyPlanAccion(presupuesto.getPsyPlanAccion().getPlacCodigo());

				entity.setSemana(Utilities.calcularDiferenciaFechas(planAccion.getFechaInicio()
						, entity.getFechaInicio()));

			}

			if (entity.getFechaInicio().before(
					entity.getPsyPresupuesto().getFechaInicio())) {
				throw new ZMessManager(
						"La fecha inicial del item no puede ser anterior a la fecha inicial del presupuesto");
			}

			if (entity.getValorEjecutado() != null) {
				if (entity.getFechaFin() == null) {
					throw new ZMessManager(
							"Si asigna un valor ejecutado debe asignar una fecha final");
				}
			}

			psyItemPresupuestoDAO.update(entity);

			log.debug("update PsyItemPresupuesto successful");
		} catch (Exception e) {
			log.error("update PsyItemPresupuesto failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<PsyItemPresupuestoDTO> getDataPsyItemPresupuesto()
			throws Exception {
		try {
			List<PsyItemPresupuesto> psyItemPresupuesto = psyItemPresupuestoDAO
					.findAll();

			List<PsyItemPresupuestoDTO> psyItemPresupuestoDTO = new ArrayList<PsyItemPresupuestoDTO>();

			for (PsyItemPresupuesto psyItemPresupuestoTmp : psyItemPresupuesto) {
				PsyItemPresupuestoDTO psyItemPresupuestoDTO2 = new PsyItemPresupuestoDTO();

				psyItemPresupuestoDTO2.setIpreCodigo(psyItemPresupuestoTmp
						.getIpreCodigo());
				psyItemPresupuestoDTO2.setEstadoRegistro((psyItemPresupuestoTmp
						.getEstadoRegistro() != null) ? psyItemPresupuestoTmp
						.getEstadoRegistro() : null);
				psyItemPresupuestoDTO2.setFechaFin(psyItemPresupuestoTmp
						.getFechaFin());
				psyItemPresupuestoDTO2.setFechaInicio(psyItemPresupuestoTmp
						.getFechaInicio());
				psyItemPresupuestoDTO2.setValorEjecutado((psyItemPresupuestoTmp
						.getValorEjecutado() != null) ? psyItemPresupuestoTmp
						.getValorEjecutado() : null);
				psyItemPresupuestoDTO2
						.setValorPresupuestado((psyItemPresupuestoTmp
								.getValorPresupuestado() != null) ? psyItemPresupuestoTmp
								.getValorPresupuestado() : null);
				psyItemPresupuestoDTO2
						.setPresCodigo_PsyPresupuesto((psyItemPresupuestoTmp
								.getPsyPresupuesto().getPresCodigo() != null) ? psyItemPresupuestoTmp
								.getPsyPresupuesto().getPresCodigo() : null);
				psyItemPresupuestoDTO2
						.setTiipCodigo_PsyTipoItemPresupuesto((psyItemPresupuestoTmp
								.getPsyTipoItemPresupuesto().getTiipCodigo() != null) ? psyItemPresupuestoTmp
								.getPsyTipoItemPresupuesto().getTiipCodigo()
								: null);				
				
				psyItemPresupuestoDTO.add(psyItemPresupuestoDTO2);
			}

			return psyItemPresupuestoDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public List<PsyItemPresupuestoDTO> getDataPsyItemPresupuestoByPresCodigo(
			Long presCodigo) throws Exception {
		try {

			Object[] variables = { "psyPresupuesto.presCodigo", false,
					presCodigo, "=", "estadoRegistro", true, "A", "=" };

			List<PsyItemPresupuesto> psyItemPresupuesto = findByCriteria(
					variables, null, null);

			List<PsyItemPresupuestoDTO> psyItemPresupuestoDTO = new ArrayList<PsyItemPresupuestoDTO>();

			for (PsyItemPresupuesto psyItemPresupuestoTmp : psyItemPresupuesto) {
				PsyItemPresupuestoDTO psyItemPresupuestoDTO2 = new PsyItemPresupuestoDTO();

				psyItemPresupuestoDTO2.setIpreCodigo(psyItemPresupuestoTmp
						.getIpreCodigo());
				psyItemPresupuestoDTO2.setEstadoRegistro((psyItemPresupuestoTmp
						.getEstadoRegistro() != null) ? psyItemPresupuestoTmp
						.getEstadoRegistro() : null);
				psyItemPresupuestoDTO2.setFechaFin(psyItemPresupuestoTmp
						.getFechaFin());
				psyItemPresupuestoDTO2.setFechaInicio(psyItemPresupuestoTmp
						.getFechaInicio());
				psyItemPresupuestoDTO2.setValorEjecutado((psyItemPresupuestoTmp
						.getValorEjecutado() != null) ? psyItemPresupuestoTmp
						.getValorEjecutado() : null);
				psyItemPresupuestoDTO2
						.setValorPresupuestado((psyItemPresupuestoTmp
								.getValorPresupuestado() != null) ? psyItemPresupuestoTmp
								.getValorPresupuestado() : null);
				psyItemPresupuestoDTO2
						.setPresCodigo_PsyPresupuesto((psyItemPresupuestoTmp
								.getPsyPresupuesto().getPresCodigo() != null) ? psyItemPresupuestoTmp
								.getPsyPresupuesto().getPresCodigo() : null);
				psyItemPresupuestoDTO2
						.setTiipCodigo_PsyTipoItemPresupuesto((psyItemPresupuestoTmp
								.getPsyTipoItemPresupuesto().getTiipCodigo() != null) ? psyItemPresupuestoTmp
								.getPsyTipoItemPresupuesto().getTiipCodigo()
								: null);
				psyItemPresupuestoDTO2
						.setTipoNombre((psyItemPresupuestoTmp
								.getPsyTipoItemPresupuesto().getNombre() != null) ? psyItemPresupuestoTmp
								.getPsyTipoItemPresupuesto().getNombre() : null);
				
				psyItemPresupuestoDTO2
						.setSemana((psyItemPresupuestoTmp.getSemana() != null) ? psyItemPresupuestoTmp
						.getSemana() : null);

				psyItemPresupuestoDTO2
						.setNombrePlanAccion((psyItemPresupuestoTmp
						.getPsyPresupuesto().getPresCodigo() != null) ? 
								psyItemPresupuestoTmp.getPsyPresupuesto().getPsyPlanAccion().getNombre() : null);

				psyItemPresupuestoDTO.add(psyItemPresupuestoDTO2);
			}

			return psyItemPresupuestoDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public PsyItemPresupuesto getPsyItemPresupuesto(Long ipreCodigo)
			throws Exception {
		log.debug("getting PsyItemPresupuesto instance");

		PsyItemPresupuesto entity = null;

		try {
			entity = psyItemPresupuestoDAO.findById(ipreCodigo);
		} catch (Exception e) {
			log.error("get PsyItemPresupuesto failed", e);
			throw new ZMessManager().new FindingException("PsyItemPresupuesto");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<PsyItemPresupuesto> findPagePsyItemPresupuesto(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<PsyItemPresupuesto> entity = null;

		try {
			entity = psyItemPresupuestoDAO.findPage(sortColumnName,
					sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					"PsyItemPresupuesto Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberPsyItemPresupuesto() throws Exception {
		Long entity = null;

		try {
			entity = psyItemPresupuestoDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					"PsyItemPresupuesto Count");
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
	public List<PsyItemPresupuesto> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		List<PsyItemPresupuesto> list = new ArrayList<PsyItemPresupuesto>();
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
			list = psyItemPresupuestoDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}
}
