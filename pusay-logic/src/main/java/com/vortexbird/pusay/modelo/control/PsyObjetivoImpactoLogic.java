package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyObjetivoImpactoDTO;
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
@Service("PsyObjetivoImpactoLogic")
public class PsyObjetivoImpactoLogic implements IPsyObjetivoImpactoLogic {
	private static final Logger log = LoggerFactory.getLogger(PsyObjetivoImpactoLogic.class);

	/**
	 * DAO injected by Spring that manages PsyObjetivoImpacto entities
	 *
	 */
	@Autowired
	private IPsyObjetivoImpactoDAO psyObjetivoImpactoDAO;

	/**
	 * Logic injected by Spring that manages PsyImpactoAmbiental entities
	 *
	 */
	@Autowired
	IPsyImpactoAmbientalLogic logicPsyImpactoAmbiental1;

	/**
	 * Logic injected by Spring that manages PsyObjetivoEstrategico entities
	 *
	 */
	@Autowired
	IPsyObjetivoEstrategicoLogic logicPsyObjetivoEstrategico2;

	@Transactional(readOnly = true)
	public List<PsyObjetivoImpacto> getPsyObjetivoImpacto()
			throws Exception {
		log.debug("finding all PsyObjetivoImpacto instances");

		List<PsyObjetivoImpacto> list = new ArrayList<PsyObjetivoImpacto>();

		try {
			list = psyObjetivoImpactoDAO.findAll();
		} catch (Exception e) {
			log.error("finding all PsyObjetivoImpacto failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL +
					"PsyObjetivoImpacto");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void savePsyObjetivoImpacto(PsyObjetivoImpacto entity)
			throws Exception {
		log.debug("saving PsyObjetivoImpacto instance");

		try {
			if (entity.getPsyImpactoAmbiental() == null) {
				throw new ZMessManager("Seleccione un impacto ambiental de la lista");
			}

			if (entity.getPsyObjetivoEstrategico() == null) {
				throw new ZMessManager("Seleccione un objetivo estrategico de la lista");
			}

			if ((entity.getEstadoRegistro() != null) &&
					(Utilities.checkWordAndCheckWithlength(
							entity.getEstadoRegistro(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Estado Registro");
			}

			if (entity.getPsyImpactoAmbiental().getImamCodigo() == null) {
				throw new ZMessManager("Seleccione un impacto ambiental de la lista");
			}

			if (entity.getPsyObjetivoEstrategico().getObesCodigo() == null) {
				throw new ZMessManager("Seleccione un objetivo estrategico de la lista");
			}

			psyObjetivoImpactoDAO.save(entity);

			log.debug("save PsyObjetivoImpacto successful");
		} catch (Exception e) {
			log.error("save PsyObjetivoImpacto failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletePsyObjetivoImpacto(PsyObjetivoImpacto entity)
			throws Exception {
		log.debug("deleting PsyObjetivoImpacto instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion(
					"PsyObjetivoImpacto");
		}

		if (entity.getObimCodigo() == null) {
			throw new ZMessManager().new EmptyFieldException("obimCodigo");
		}

		try {
			psyObjetivoImpactoDAO.delete(entity);

			log.debug("delete PsyObjetivoImpacto successful");
		} catch (Exception e) {
			log.error("delete PsyObjetivoImpacto failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updatePsyObjetivoImpacto(PsyObjetivoImpacto entity)
			throws Exception {
		log.debug("updating PsyObjetivoImpacto instance");

		try {

			if (entity.getPsyImpactoAmbiental() == null) {
				throw new ZMessManager("Seleccione un impacto ambiental de la lista");
			}

			if (entity.getPsyObjetivoEstrategico() == null) {
				throw new ZMessManager("Seleccione un objetivo estrategico de la lista");
			}

			if ((entity.getEstadoRegistro() != null) &&
					(Utilities.checkWordAndCheckWithlength(
							entity.getEstadoRegistro(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Estado Registro");
			}

			if (entity.getPsyImpactoAmbiental().getImamCodigo() == null) {
				throw new ZMessManager("Seleccione un impacto ambiental de la lista");
			}

			if (entity.getPsyObjetivoEstrategico().getObesCodigo() == null) {
				throw new ZMessManager("Seleccione un objetivo estrategico de la lista");
			}

			psyObjetivoImpactoDAO.update(entity);

			log.debug("update PsyObjetivoImpacto successful");
		} catch (Exception e) {
			log.error("update PsyObjetivoImpacto failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<PsyObjetivoImpactoDTO> getDataPsyObjetivoImpacto()
			throws Exception {
		try {
			List<PsyObjetivoImpacto> psyObjetivoImpacto = psyObjetivoImpactoDAO.findAll();

			List<PsyObjetivoImpactoDTO> psyObjetivoImpactoDTO = new ArrayList<PsyObjetivoImpactoDTO>();

			for (PsyObjetivoImpacto psyObjetivoImpactoTmp : psyObjetivoImpacto) {
				PsyObjetivoImpactoDTO psyObjetivoImpactoDTO2 = new PsyObjetivoImpactoDTO();

				psyObjetivoImpactoDTO2.setObimCodigo(psyObjetivoImpactoTmp.getObimCodigo());
				psyObjetivoImpactoDTO2.setEstadoRegistro((psyObjetivoImpactoTmp
						.getEstadoRegistro().trim().equals("A")) ? "Activo"
								: "Inactivo");
				psyObjetivoImpactoDTO2.setImamCodigo_PsyImpactoAmbiental((psyObjetivoImpactoTmp.getPsyImpactoAmbiental()
						.getImamCodigo() != null)
						? psyObjetivoImpactoTmp.getPsyImpactoAmbiental()
								.getImamCodigo() : null);
				psyObjetivoImpactoDTO2.setObesCodigo_PsyObjetivoEstrategico((psyObjetivoImpactoTmp.getPsyObjetivoEstrategico()
						.getObesCodigo() != null)
						? psyObjetivoImpactoTmp.getPsyObjetivoEstrategico()
								.getObesCodigo() : null);
				psyObjetivoImpactoDTO2.setImamNombre((psyObjetivoImpactoTmp.getPsyImpactoAmbiental()
						.getImamCodigo() != null)
						? psyObjetivoImpactoTmp.getPsyImpactoAmbiental()
								.getNombre() : null);
				psyObjetivoImpactoDTO2.setObesNombre((psyObjetivoImpactoTmp.getPsyObjetivoEstrategico()
						.getObesCodigo() != null)
						? psyObjetivoImpactoTmp.getPsyObjetivoEstrategico()
								.getNombre() : null);
				psyObjetivoImpactoDTO.add(psyObjetivoImpactoDTO2);
			}

			return psyObjetivoImpactoDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public PsyObjetivoImpacto getPsyObjetivoImpacto(Long obimCodigo)
			throws Exception {
		log.debug("getting PsyObjetivoImpacto instance");

		PsyObjetivoImpacto entity = null;

		try {
			entity = psyObjetivoImpactoDAO.findById(obimCodigo);
		} catch (Exception e) {
			log.error("get PsyObjetivoImpacto failed", e);
			throw new ZMessManager().new FindingException("PsyObjetivoImpacto");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<PsyObjetivoImpacto> findPagePsyObjetivoImpacto(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<PsyObjetivoImpacto> entity = null;

		try {
			entity = psyObjetivoImpactoDAO.findPage(sortColumnName,
					sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					"PsyObjetivoImpacto Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberPsyObjetivoImpacto() throws Exception {
		Long entity = null;

		try {
			entity = psyObjetivoImpactoDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					"PsyObjetivoImpacto Count");
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
	public List<PsyObjetivoImpacto> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception {
		List<PsyObjetivoImpacto> list = new ArrayList<PsyObjetivoImpacto>();
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
			list = psyObjetivoImpactoDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}
}
