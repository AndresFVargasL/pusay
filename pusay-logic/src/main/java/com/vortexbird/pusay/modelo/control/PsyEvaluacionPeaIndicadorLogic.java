package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.exceptions.ZMessManager.NotValidFormatException;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyEvaluacionPeaIndicadorDTO;
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
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("PsyEvaluacionPeaIndicadorLogic")
public class PsyEvaluacionPeaIndicadorLogic implements
		IPsyEvaluacionPeaIndicadorLogic {
	private static final Logger log = LoggerFactory
			.getLogger(PsyEvaluacionPeaIndicadorLogic.class);
	private static final String colorRojo = "red";
	private static final String colorVerde = "green";
	

	/**
	 * DAO injected by Spring that manages PsyEvaluacionPeaIndicador entities
	 *
	 */
	@Autowired
	private IPsyEvaluacionPeaIndicadorDAO psyEvaluacionPeaIndicadorDAO;

	/**
	 * Logic injected by Spring that manages PsyIndicador entities
	 *
	 */
	@Autowired
	IPsyIndicadorLogic logicPsyIndicador1;

	/**
	 * Logic injected by Spring that manages PsyPlanEstrategicoAmbiental
	 * entities
	 *
	 */
	@Autowired
	IPsyPlanEstrategicoAmbientalLogic logicPsyPlanEstrategicoAmbiental2;

	@Transactional(readOnly = true)
	public List<PsyEvaluacionPeaIndicador> getPsyEvaluacionPeaIndicador()
			throws Exception {
		log.debug("finding all PsyEvaluacionPeaIndicador instances");

		List<PsyEvaluacionPeaIndicador> list = new ArrayList<PsyEvaluacionPeaIndicador>();

		try {
			list = psyEvaluacionPeaIndicadorDAO.findAll();
		} catch (Exception e) {
			log.error("finding all PsyEvaluacionPeaIndicador failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL
					+ "PsyEvaluacionPeaIndicador");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void savePsyEvaluacionPeaIndicador(PsyEvaluacionPeaIndicador entity)
			throws Exception {
		log.debug("saving PsyEvaluacionPeaIndicador instance");

		try {
			if (entity.getPsyIndicador() == null) {
				throw new ZMessManager().new ForeignException("Indicador");
			}

			if (entity.getPsyPlanEstrategicoAmbiental() == null) {
				throw new ZMessManager().new ForeignException(
						"Plan Estrategico Ambiental");
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
			
			 if ((entity.getHistorial() != null) &&
	                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
	                        entity.getHistorial(), 12, 2) == false)) {
	                throw new ZMessManager().new NotValidFormatException(
	                    "Historial");
	            }

			if (entity.getMeta() == null) {
				throw new ZMessManager().new EmptyFieldException("Meta");
			}

			if ((entity.getMeta() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ entity.getMeta(), 12, 2) == false)) {
				throw new ZMessManager().new NotValidFormatException("Meta");
			}

			if ((entity.getMultiple() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getMultiple(), 50) == false)) {
				throw new ZMessManager().new NotValidFormatException("Multiple");
			}

			if ((entity.getNorma() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ entity.getNorma(), 12, 2) == false)) {
				throw new ZMessManager().new NotValidFormatException("Norma");
			}

			if (entity.getPeriodo() == null) {
				throw new ZMessManager().new EmptyFieldException("Periodo");
			}

			if ((entity.getPeriodo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ entity.getPeriodo(), 2, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("Periodo");
			}

			if (entity.getResultado() == null) {
				throw new ZMessManager().new EmptyFieldException("Resultado");
			}

			if ((entity.getResultado() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ entity.getResultado(), 12, 2) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Resultado");
			}

			if ((entity.getSectorial() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ entity.getSectorial(), 12, 2) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Sectorial");
			}

			if (entity.getPsyIndicador().getCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Indicador");
			}

			if (entity.getPsyPlanEstrategicoAmbiental().getCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Plan Estrategico Ambiental");
			}

			psyEvaluacionPeaIndicadorDAO.save(entity);

			log.debug("save PsyEvaluacionPeaIndicador successful");
		} catch (Exception e) {
			log.error("save PsyEvaluacionPeaIndicador failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletePsyEvaluacionPeaIndicador(PsyEvaluacionPeaIndicador entity)
			throws Exception {
		log.debug("deleting PsyEvaluacionPeaIndicador instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion(
					"PsyEvaluacionPeaIndicador");
		}

		if (entity.getCodigo() == null) {
			throw new ZMessManager().new EmptyFieldException("codigo");
		}

		try {
			psyEvaluacionPeaIndicadorDAO.delete(entity);

			log.debug("delete PsyEvaluacionPeaIndicador successful");
		} catch (Exception e) {
			log.error("delete PsyEvaluacionPeaIndicador failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updatePsyEvaluacionPeaIndicador(PsyEvaluacionPeaIndicador entity)
			throws Exception {
		log.debug("updating PsyEvaluacionPeaIndicador instance");

		try {
			if (entity == null) {
				throw new ZMessManager(
						"Diligencie todos los datos porfavor");
			}

			if (entity.getPsyIndicador() == null) {
				throw new ZMessManager().new ForeignException("Indicador");
			}

			if (entity.getPsyPlanEstrategicoAmbiental() == null) {
				throw new ZMessManager().new ForeignException(
						"Plan Estrategico Ambiental");
			}
			
			if (entity.getEstadoRegistro() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Estado Regsitro");
			}

			if ((entity.getEstadoRegistro() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getEstadoRegistro(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Estado Registro");
			}
			
			if ((entity.getHistorial() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getHistorial(), 12, 2) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Historial");
            }

			if (entity.getMeta() == null) {
				throw new ZMessManager().new EmptyFieldException("Meta");
			}

			if ((entity.getMeta() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ entity.getMeta(), 12, 2) == false)) {
				throw new ZMessManager().new NotValidFormatException("Meta");
			}

			if ((entity.getMultiple() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getMultiple(), 50) == false)) {
				throw new ZMessManager().new NotValidFormatException("Multiple");
			}

			if ((entity.getNorma() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ entity.getNorma(), 12, 2) == false)) {
				throw new ZMessManager().new NotValidFormatException("Norma");
			}

			if (entity.getPeriodo() == null) {
				throw new ZMessManager().new EmptyFieldException("Periodo");
			}

			if ((entity.getPeriodo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ entity.getPeriodo(), 2, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("Periodo");
			}

			if (entity.getResultado() == null) {
				throw new ZMessManager().new EmptyFieldException("Resultado");
			}

			if ((entity.getResultado() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ entity.getResultado(), 12, 2) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Resultado");
			}

			if ((entity.getSectorial() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ entity.getSectorial(), 12, 2) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Sectorial");
			}

			if (entity.getPsyIndicador().getCodigo() == null) {
				throw new ZMessManager("Diligencie todos los datos porfavor");
			}

			if (entity.getPsyPlanEstrategicoAmbiental().getCodigo() == null) {
				throw new ZMessManager(
						"Plan Estrategico Ambiental");
			}

			psyEvaluacionPeaIndicadorDAO.update(entity);

			log.debug("update PsyEvaluacionPeaIndicador successful");
		} catch (Exception e) {
			log.error("update PsyEvaluacionPeaIndicador failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<PsyEvaluacionPeaIndicadorDTO> getDataPsyEvaluacionPeaIndicador(
			PsyEmpresa empresa) throws Exception {
		try {

			List<PsyEvaluacionPeaIndicador> psyEvaluacionPeaIndicador = psyEvaluacionPeaIndicadorDAO
					.getDataPsyEvaluacionPeaIndicadorByEmpresa(empresa);

			List<PsyEvaluacionPeaIndicadorDTO> psyEvaluacionPeaIndicadorDTO = new ArrayList<PsyEvaluacionPeaIndicadorDTO>();

			for (PsyEvaluacionPeaIndicador psyEvaluacionPeaIndicadorTmp : psyEvaluacionPeaIndicador) {
				PsyEvaluacionPeaIndicadorDTO psyEvaluacionPeaIndicadorDTO2 = new PsyEvaluacionPeaIndicadorDTO();

				psyEvaluacionPeaIndicadorDTO2
						.setCodigo(psyEvaluacionPeaIndicadorTmp.getCodigo());
				psyEvaluacionPeaIndicadorDTO2
						.setEstadoRegistro((psyEvaluacionPeaIndicadorTmp
								.getEstadoRegistro() != null) ? psyEvaluacionPeaIndicadorTmp
								.getEstadoRegistro() : null);
				psyEvaluacionPeaIndicadorDTO2.setHistorial((psyEvaluacionPeaIndicadorTmp.getHistorial() != null)
						? psyEvaluacionPeaIndicadorTmp.getHistorial() : null);
				psyEvaluacionPeaIndicadorDTO2
						.setMeta((psyEvaluacionPeaIndicadorTmp.getMeta() != null) ? psyEvaluacionPeaIndicadorTmp
								.getMeta() : null);
				psyEvaluacionPeaIndicadorDTO2
						.setMultiple((psyEvaluacionPeaIndicadorTmp
								.getMultiple() != null) ? psyEvaluacionPeaIndicadorTmp
								.getMultiple() : null);
				psyEvaluacionPeaIndicadorDTO2
						.setNorma((psyEvaluacionPeaIndicadorTmp.getNorma() != null) ? psyEvaluacionPeaIndicadorTmp
								.getNorma() : null);
				psyEvaluacionPeaIndicadorDTO2
						.setPeriodo((psyEvaluacionPeaIndicadorTmp.getPeriodo() != null) ? psyEvaluacionPeaIndicadorTmp
								.getPeriodo() : null);
				psyEvaluacionPeaIndicadorDTO2
						.setResultado((psyEvaluacionPeaIndicadorTmp
								.getResultado() != null) ? psyEvaluacionPeaIndicadorTmp
								.getResultado() : null);
				psyEvaluacionPeaIndicadorDTO2
						.setSectorial((psyEvaluacionPeaIndicadorTmp
								.getSectorial() != null) ? psyEvaluacionPeaIndicadorTmp
								.getSectorial() : null);
				psyEvaluacionPeaIndicadorDTO2
						.setCodigo_PsyIndicador((psyEvaluacionPeaIndicadorTmp
								.getPsyIndicador().getCodigo() != null) ? psyEvaluacionPeaIndicadorTmp
								.getPsyIndicador().getCodigo() : null);
				psyEvaluacionPeaIndicadorDTO2
						.setCodigo_PsyPlanEstrategicoAmbiental((psyEvaluacionPeaIndicadorTmp
								.getPsyPlanEstrategicoAmbiental().getCodigo() != null) ? psyEvaluacionPeaIndicadorTmp
								.getPsyPlanEstrategicoAmbiental().getCodigo()
								: null);
				psyEvaluacionPeaIndicadorDTO2
						.setNombreIndicador((psyEvaluacionPeaIndicadorTmp
								.getPsyIndicador().getCodigo() != null) ? psyEvaluacionPeaIndicadorTmp
								.getPsyIndicador().getDescripcion() : null);
				psyEvaluacionPeaIndicadorDTO2
						.setNombrePlanEstrategicoAmiental((psyEvaluacionPeaIndicadorTmp
								.getPsyPlanEstrategicoAmbiental().getCodigo() != null) ? psyEvaluacionPeaIndicadorTmp
								.getPsyPlanEstrategicoAmbiental().getNombre()
								: null);
				if(psyEvaluacionPeaIndicadorDTO2.getResultado().longValue() < psyEvaluacionPeaIndicadorDTO2.getMeta().longValue()){
					psyEvaluacionPeaIndicadorDTO2.setColorResultado(colorRojo);
					psyEvaluacionPeaIndicadorDTO2.setColorMeta(colorRojo);
				}else if (psyEvaluacionPeaIndicadorDTO2.getResultado().longValue() >= psyEvaluacionPeaIndicadorDTO2.getMeta().longValue()) {
					psyEvaluacionPeaIndicadorDTO2.setColorResultado(colorVerde);
					psyEvaluacionPeaIndicadorDTO2.setColorMeta(colorVerde);
				}
				
				
				psyEvaluacionPeaIndicadorDTO.add(psyEvaluacionPeaIndicadorDTO2);
			}

			return psyEvaluacionPeaIndicadorDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public PsyEvaluacionPeaIndicador getPsyEvaluacionPeaIndicador(Long codigo)
			throws Exception {
		log.debug("getting PsyEvaluacionPeaIndicador instance");

		PsyEvaluacionPeaIndicador entity = null;

		try {
			entity = psyEvaluacionPeaIndicadorDAO.findById(codigo);
		} catch (Exception e) {
			log.error("get PsyEvaluacionPeaIndicador failed", e);
			throw new ZMessManager().new FindingException(
					"PsyEvaluacionPeaIndicador");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<PsyEvaluacionPeaIndicador> findPagePsyEvaluacionPeaIndicador(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<PsyEvaluacionPeaIndicador> entity = null;

		try {
			entity = psyEvaluacionPeaIndicadorDAO.findPage(sortColumnName,
					sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					"PsyEvaluacionPeaIndicador Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberPsyEvaluacionPeaIndicador() throws Exception {
		Long entity = null;

		try {
			entity = psyEvaluacionPeaIndicadorDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					"PsyEvaluacionPeaIndicador Count");
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
	public List<PsyEvaluacionPeaIndicador> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		List<PsyEvaluacionPeaIndicador> list = new ArrayList<PsyEvaluacionPeaIndicador>();
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
			list = psyEvaluacionPeaIndicadorDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}
}
