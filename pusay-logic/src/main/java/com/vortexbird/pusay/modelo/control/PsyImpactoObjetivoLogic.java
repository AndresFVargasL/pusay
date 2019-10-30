package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyImpactoObjetivoDTO;
import com.vortexbird.pusay.modelo.dto.PsyImpactoObjetivoTableDTO;
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
@Service("PsyImpactoObjetivoLogic")
public class PsyImpactoObjetivoLogic implements IPsyImpactoObjetivoLogic {
	private static final Logger log = LoggerFactory
			.getLogger(PsyImpactoObjetivoLogic.class);

	/**
	 * DAO injected by Spring that manages PsyImpactoObjetivo entities
	 *
	 */
	@Autowired
	private IPsyImpactoObjetivoDAO psyImpactoObjetivoDAO;

	/**
	 * Logic injected by Spring that manages PsyImpactoAmbiental entities
	 *
	 */
	@Autowired
	IPsyImpactoAmbientalLogic logicPsyImpactoAmbiental1;

	/**
	 * Logic injected by Spring that manages PsyObjetivoAmbiental entities
	 *
	 */
	@Autowired
	IPsyObjetivoAmbientalLogic logicPsyObjetivoAmbiental2;

	@Transactional(readOnly = true)
	public List<PsyImpactoObjetivo> getPsyImpactoObjetivo() throws Exception {
		log.debug("finding all PsyImpactoObjetivo instances");

		List<PsyImpactoObjetivo> list = new ArrayList<PsyImpactoObjetivo>();

		try {
			list = psyImpactoObjetivoDAO.findAll();
		} catch (Exception e) {
			log.error("finding all PsyImpactoObjetivo failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL
					+ "PsyImpactoObjetivo");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void savePsyImpactoObjetivo(PsyImpactoObjetivo entity)
			throws Exception {
		log.debug("saving PsyImpactoObjetivo instance");

		try {
			if (entity.getPsyImpactoAmbiental() == null) {
				throw new ZMessManager("Seleccione un impacto ambiental de la lista");
			}

			if (entity.getPsyObjetivoAmbiental() == null) {
				throw new ZMessManager("Seleccione un objetivo ambiental de la lista");
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

			if (entity.getPsyImpactoAmbiental().getImamCodigo() == null) {
				throw new ZMessManager("Seleccione un impacto ambiental de la lista");
			}

			if (entity.getPsyObjetivoAmbiental().getCodigo() == null) {
				throw new ZMessManager("Seleccione un objetivo ambiental de la lista");
			}

			psyImpactoObjetivoDAO.save(entity);

			log.debug("save PsyImpactoObjetivo successful");
		} catch (Exception e) {
			log.error("save PsyImpactoObjetivo failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletePsyImpactoObjetivo(PsyImpactoObjetivo entity)
			throws Exception {
		log.debug("deleting PsyImpactoObjetivo instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion(
					"PsyImpactoObjetivo");
		}

		if (entity.getImobCodigo() == null) {
			throw new ZMessManager().new EmptyFieldException("imobCodigo");
		}

		try {
			psyImpactoObjetivoDAO.delete(entity);

			log.debug("delete PsyImpactoObjetivo successful");
		} catch (Exception e) {
			log.error("delete PsyImpactoObjetivo failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updatePsyImpactoObjetivo(PsyImpactoObjetivo entity)
			throws Exception {
		log.debug("updating PsyImpactoObjetivo instance");

		try {

			if (entity.getPsyImpactoAmbiental() == null) {
				throw new ZMessManager("Seleccione un impacto ambiental de la lista");
			}

			if (entity.getPsyObjetivoAmbiental() == null) {
				throw new ZMessManager("Seleccione un objetivo ambiental de la lista");
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

			if (entity.getPsyImpactoAmbiental().getImamCodigo() == null) {
				throw new ZMessManager("Seleccione un impacto ambiental de la lista");
			}

			if (entity.getPsyObjetivoAmbiental().getCodigo() == null) {
				throw new ZMessManager("Seleccione un objetivo ambiental de la lista");
			}

			psyImpactoObjetivoDAO.update(entity);

			log.debug("update PsyImpactoObjetivo successful");
		} catch (Exception e) {
			log.error("update PsyImpactoObjetivo failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<PsyImpactoObjetivoDTO> getDataPsyImpactoObjetivo()
			throws Exception {
		try {
			List<PsyImpactoObjetivo> psyImpactoObjetivo = psyImpactoObjetivoDAO
					.findAll();

			List<PsyImpactoObjetivoDTO> psyImpactoObjetivoDTO = new ArrayList<PsyImpactoObjetivoDTO>();

			for (PsyImpactoObjetivo psyImpactoObjetivoTmp : psyImpactoObjetivo) {
				PsyImpactoObjetivoDTO psyImpactoObjetivoDTO2 = new PsyImpactoObjetivoDTO();

				psyImpactoObjetivoDTO2.setImobCodigo(psyImpactoObjetivoTmp
						.getImobCodigo());

				psyImpactoObjetivoDTO2.setEstadoRegistro((psyImpactoObjetivoTmp
						.getEstadoRegistro().trim().equals("A")) ? "Activo"
						: "Inactivo");

				psyImpactoObjetivoDTO2
						.setImamCodigo_PsyImpactoAmbiental((psyImpactoObjetivoTmp
								.getPsyImpactoAmbiental().getImamCodigo() != null) ? psyImpactoObjetivoTmp
								.getPsyImpactoAmbiental().getImamCodigo()
								: null);

				psyImpactoObjetivoDTO2
						.setCodigo_PsyObjetivoAmbiental((psyImpactoObjetivoTmp
								.getPsyObjetivoAmbiental().getCodigo() != null) ? psyImpactoObjetivoTmp
								.getPsyObjetivoAmbiental().getCodigo() : null);

				psyImpactoObjetivoDTO2
						.setImamNombre((psyImpactoObjetivoTmp
								.getPsyImpactoAmbiental().getImamCodigo() != null) ? psyImpactoObjetivoTmp
								.getPsyImpactoAmbiental().getNombre() : null);
				psyImpactoObjetivoDTO2
						.setObamNombre((psyImpactoObjetivoTmp
								.getPsyObjetivoAmbiental().getCodigo() != null) ? psyImpactoObjetivoTmp
								.getPsyObjetivoAmbiental().getDescripcion()
								: null);

				psyImpactoObjetivoDTO.add(psyImpactoObjetivoDTO2);
			}

			return psyImpactoObjetivoDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public PsyImpactoObjetivo getPsyImpactoObjetivo(Long imobCodigo)
			throws Exception {
		log.debug("getting PsyImpactoObjetivo instance");

		PsyImpactoObjetivo entity = null;

		try {
			entity = psyImpactoObjetivoDAO.findById(imobCodigo);
		} catch (Exception e) {
			log.error("get PsyImpactoObjetivo failed", e);
			throw new ZMessManager().new FindingException("PsyImpactoObjetivo");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<PsyImpactoObjetivo> findPagePsyImpactoObjetivo(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<PsyImpactoObjetivo> entity = null;

		try {
			entity = psyImpactoObjetivoDAO.findPage(sortColumnName,
					sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					"PsyImpactoObjetivo Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberPsyImpactoObjetivo() throws Exception {
		Long entity = null;

		try {
			entity = psyImpactoObjetivoDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					"PsyImpactoObjetivo Count");
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
	public List<PsyImpactoObjetivo> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		List<PsyImpactoObjetivo> list = new ArrayList<PsyImpactoObjetivo>();
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
			list = psyImpactoObjetivoDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}
	
	@Transactional(readOnly = true)
	public List<PsyImpactoObjetivoTableDTO> getDataImpactoObjetivo(PsyEmpresa empresa) throws Exception {
		
		try {
			List<PsyImpactoObjetivoTableDTO> losImpactosSeleccionados = psyImpactoObjetivoDAO.getDataImpactoObjetivo(empresa);
			
			List<PsyImpactoObjetivoTableDTO> psyImpactoObjetivoDTO = new ArrayList<PsyImpactoObjetivoTableDTO>();
			
			for (PsyImpactoObjetivoTableDTO psyImpactoObjetivoTableTmp : losImpactosSeleccionados) {
				
				PsyImpactoObjetivoTableDTO psyImpactoObjetivoDTO2 = new PsyImpactoObjetivoTableDTO();
				
				psyImpactoObjetivoDTO2.setImamCodigo((psyImpactoObjetivoTableTmp.getImamCodigo() != null) ? psyImpactoObjetivoTableTmp.getImamCodigo() : null);
				
				psyImpactoObjetivoDTO2.setImamNombre((psyImpactoObjetivoTableTmp.getImamNombre() != null
													&& !psyImpactoObjetivoTableTmp.getImamNombre().trim().equals("")) ? psyImpactoObjetivoTableTmp.getImamNombre() : null);
				
				psyImpactoObjetivoDTO2.setImobCodigo((psyImpactoObjetivoTableTmp.getImobCodigo() != null) ? psyImpactoObjetivoTableTmp.getImobCodigo() : null);
				
				psyImpactoObjetivoDTO2.setObamCodigo((psyImpactoObjetivoTableTmp.getObamCodigo() != null) ? psyImpactoObjetivoTableTmp.getObamCodigo() : null);
				
				psyImpactoObjetivoDTO2.setObamDescripcion((psyImpactoObjetivoTableTmp.getObamDescripcion() != null
						&& !psyImpactoObjetivoTableTmp.getObamDescripcion().trim().equals("")) ? psyImpactoObjetivoTableTmp.getObamDescripcion() : null);
				
				psyImpactoObjetivoDTO.add(psyImpactoObjetivoDTO2);
				
				}
			
			return psyImpactoObjetivoDTO;
			
		} catch (Exception e) {
			throw e;
		}
		
	}
}
