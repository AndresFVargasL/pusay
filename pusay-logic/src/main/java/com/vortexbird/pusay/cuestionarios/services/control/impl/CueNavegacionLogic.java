package com.vortexbird.pusay.cuestionarios.services.control.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.pusay.cuestionarios.api.Utilities;
import com.vortexbird.pusay.cuestionarios.api.dto.CueNavegacionDTO;
import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.dao.ICueNavegacionDAO;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionario;
import com.vortexbird.pusay.cuestionarios.model.CueNavegacion;
import com.vortexbird.pusay.cuestionarios.model.CueOpcion;
import com.vortexbird.pusay.cuestionarios.model.CuePregunta;
import com.vortexbird.pusay.cuestionarios.services.control.ICueCuestionarioLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueNavegacionLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueOpcionLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICuePreguntaLogic;

@Scope("singleton")
@Service("CueNavegacionLogic")
public class CueNavegacionLogic implements ICueNavegacionLogic {
	/**
	 * DAO injected by Spring that manages CueNavegacion entities support Andr�s Mauricio C�rdenas
	 * mauriciocardenasp@gmail.com
	 */
	@Autowired
	private ICueNavegacionDAO cueNavegacionDAO;

	/**
	 * Logic injected by Spring that manages CueCuestionario entities
	 * 
	 */
	@Autowired
	ICueCuestionarioLogic logicCueCuestionario1;

	/**
	 * Logic injected by Spring that manages CueOpcion entities
	 * 
	 */
	@Autowired
	ICueOpcionLogic logicCueOpcion2;

	/**
	 * Logic injected by Spring that manages CuePregunta entities
	 * 
	 */
	@Autowired
	ICuePreguntaLogic logicCuePregunta3;

	/**
	 * Logic injected by Spring that manages CuePregunta entities
	 * 
	 */
	@Autowired
	ICuePreguntaLogic logicCuePregunta4;

	@Transactional(readOnly = true)
	public List<CueNavegacion> getCueNavegacion() throws Exception {
		List<CueNavegacion> list = new ArrayList<CueNavegacion>();

		try {
			list = cueNavegacionDAO.findAll();
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "CueNavegacion");
		} finally {
		}

		return list;
	}

	@SuppressWarnings("unused")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCueNavegacion(Long consecutivo, Long consecutivo_CueCuestionario, Long consecutivo_CueOpcion,
			Long consecutivo_CuePregunta, Long consecutivo_CuePregunta0) throws Exception {
		CueNavegacion entity = null;

		try {
			if (consecutivo == null) {
				throw new ZMessManager().new EmptyFieldException("consecutivo");
			}

			if ((consecutivo != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("consecutivo");
			}

			if (consecutivo_CueCuestionario == null) {
				throw new ZMessManager().new EmptyFieldException("consecutivo_CueCuestionario");
			}

			if ((consecutivo_CueCuestionario != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo_CueCuestionario, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("consecutivo_CueCuestionario");
			}

			if (consecutivo_CueOpcion == null) {
				throw new ZMessManager().new EmptyFieldException("consecutivo_CueOpcion");
			}

			if ((consecutivo_CueOpcion != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo_CueOpcion, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("consecutivo_CueOpcion");
			}

			if (consecutivo_CuePregunta == null) {
				throw new ZMessManager().new EmptyFieldException("consecutivo_CuePregunta");
			}

			if ((consecutivo_CuePregunta != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo_CuePregunta, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("consecutivo_CuePregunta");
			}

			if (consecutivo_CuePregunta == null) {
				throw new ZMessManager().new EmptyFieldException("consecutivo_CuePregunta");
			}

			if ((consecutivo_CuePregunta != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo_CuePregunta, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("consecutivo_CuePregunta");
			}

			CueCuestionario cueCuestionarioClass = logicCueCuestionario1
					.getCueCuestionario(consecutivo_CueCuestionario);
			CueOpcion cueOpcionClass = logicCueOpcion2.getCueOpcion(consecutivo_CueOpcion);
			CuePregunta cuePreguntaByPreguntaDestinoClass = logicCuePregunta3.getCuePregunta(consecutivo_CuePregunta);
			CuePregunta cuePreguntaByPreguntaOrigenClass = logicCuePregunta4.getCuePregunta(consecutivo_CuePregunta);

			if (cueCuestionarioClass == null) {
				throw new ZMessManager().new ForeignException("cueCuestionario");
			}

			if (cueOpcionClass == null) {
				throw new ZMessManager().new ForeignException("cueOpcion");
			}

			if (cuePreguntaByPreguntaDestinoClass == null) {
				throw new ZMessManager().new ForeignException("cuePreguntaByPreguntaDestino");
			}

			if (cuePreguntaByPreguntaOrigenClass == null) {
				throw new ZMessManager().new ForeignException("cuePreguntaByPreguntaOrigen");
			}

			entity = getCueNavegacion(consecutivo);

			if (entity != null) {
				throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
			}

			entity = new CueNavegacion();
			entity.setConsecutivo(consecutivo);
			entity.setCueCuestionario(cueCuestionarioClass);
			entity.setCueOpcion(cueOpcionClass);
			entity.setCuePreguntaByPreguntaDestino(cuePreguntaByPreguntaDestinoClass);
			entity.setCuePreguntaByPreguntaOrigen(cuePreguntaByPreguntaOrigenClass);
			cueNavegacionDAO.save(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteCueNavegacion(Long consecutivo) throws Exception {
		CueNavegacion entity = null;

		if (consecutivo == null) {
			throw new ZMessManager().new EmptyFieldException("consecutivo");
		}

		entity = getCueNavegacion(consecutivo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException("CueNavegacion");
		}

		try {
			cueNavegacionDAO.delete(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@SuppressWarnings("unused")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCueNavegacion(Long consecutivo, Long consecutivo_CueCuestionario, Long consecutivo_CueOpcion,
			Long consecutivo_CuePregunta, Long consecutivo_CuePregunta0) throws Exception {
		CueNavegacion entity = null;

		try {
			if (consecutivo == null) {
				throw new ZMessManager().new EmptyFieldException("consecutivo");
			}

			if ((consecutivo != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("consecutivo");
			}

			if (consecutivo_CueCuestionario == null) {
				throw new ZMessManager().new EmptyFieldException("consecutivo_CueCuestionario");
			}

			if ((consecutivo_CueCuestionario != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo_CueCuestionario, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("consecutivo_CueCuestionario");
			}

			if (consecutivo_CueOpcion == null) {
				throw new ZMessManager().new EmptyFieldException("consecutivo_CueOpcion");
			}

			if ((consecutivo_CueOpcion != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo_CueOpcion, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("consecutivo_CueOpcion");
			}

			if (consecutivo_CuePregunta == null) {
				throw new ZMessManager().new EmptyFieldException("consecutivo_CuePregunta");
			}

			if ((consecutivo_CuePregunta != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo_CuePregunta, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("consecutivo_CuePregunta");
			}

			if (consecutivo_CuePregunta == null) {
				throw new ZMessManager().new EmptyFieldException("consecutivo_CuePregunta");
			}

			if ((consecutivo_CuePregunta != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo_CuePregunta, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("consecutivo_CuePregunta");
			}

			CueCuestionario cueCuestionarioClass = logicCueCuestionario1
					.getCueCuestionario(consecutivo_CueCuestionario);
			CueOpcion cueOpcionClass = logicCueOpcion2.getCueOpcion(consecutivo_CueOpcion);
			CuePregunta cuePreguntaByPreguntaDestinoClass = logicCuePregunta3.getCuePregunta(consecutivo_CuePregunta);
			CuePregunta cuePreguntaByPreguntaOrigenClass = logicCuePregunta4.getCuePregunta(consecutivo_CuePregunta);

			if (cueCuestionarioClass == null) {
				throw new ZMessManager().new ForeignException("cueCuestionario");
			}

			if (cueOpcionClass == null) {
				throw new ZMessManager().new ForeignException("cueOpcion");
			}

			if (cuePreguntaByPreguntaDestinoClass == null) {
				throw new ZMessManager().new ForeignException("cuePreguntaByPreguntaDestino");
			}

			if (cuePreguntaByPreguntaOrigenClass == null) {
				throw new ZMessManager().new ForeignException("cuePreguntaByPreguntaOrigen");
			}

			entity = getCueNavegacion(consecutivo);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setConsecutivo(consecutivo);
			entity.setCueCuestionario(cueCuestionarioClass);
			entity.setCueOpcion(cueOpcionClass);
			entity.setCuePreguntaByPreguntaDestino(cuePreguntaByPreguntaDestinoClass);
			entity.setCuePreguntaByPreguntaOrigen(cuePreguntaByPreguntaOrigenClass);
			cueNavegacionDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<CueNavegacionDTO> getDataCueNavegacion() throws Exception {
		try {
			List<CueNavegacion> cueNavegacion = cueNavegacionDAO.findAll();

			List<CueNavegacionDTO> cueNavegacionDTO = new ArrayList<CueNavegacionDTO>();

			for (CueNavegacion cueNavegacionTmp : cueNavegacion) {
				CueNavegacionDTO cueNavegacionDTO2 = new CueNavegacionDTO();

				cueNavegacionDTO2.setConsecutivo(cueNavegacionTmp.getConsecutivo());
				cueNavegacionDTO2.setConsecutivo_CueCuestionario((cueNavegacionTmp.getCueCuestionario()
						.getConsecutivo() != null) ? cueNavegacionTmp.getCueCuestionario().getConsecutivo() : null);
				cueNavegacionDTO2
						.setConsecutivo_CueOpcion((cueNavegacionTmp.getCueOpcion().getConsecutivo() != null) ? cueNavegacionTmp
								.getCueOpcion().getConsecutivo() : null);
				cueNavegacionDTO2.setConsecutivo_CuePreguntaOrigen((cueNavegacionTmp.getCuePreguntaByPreguntaOrigen()
						.getConsecutivo() != null) ? cueNavegacionTmp.getCuePreguntaByPreguntaDestino()
						.getConsecutivo() : null);
				cueNavegacionDTO.add(cueNavegacionDTO2);
			}

			return cueNavegacionDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public CueNavegacion getCueNavegacion(Long consecutivo) throws Exception {
		CueNavegacion entity = null;

		try {
			entity = cueNavegacionDAO.findById(consecutivo);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueNavegacion");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<CueNavegacion> findPageCueNavegacion(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<CueNavegacion> entity = null;

		try {
			entity = cueNavegacionDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueNavegacion Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberCueNavegacion() throws Exception {
		Long entity = null;

		try {
			entity = cueNavegacionDAO.findTotalNumber();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueNavegacion Count");
		} finally {
		}

		return entity;
	}

	/**
	 * 
	 * @param varibles
	 *            este arreglo debera tener:
	 * 
	 *            [0] = String variable = (String) varibles[i]; representa como se llama la variable en el pojo
	 * 
	 *            [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el valor necesita o no
	 *            ''(comillas simples)usado para campos de tipo string
	 * 
	 *            [2] = Object value = varibles[i + 2]; representa el valor que se va a buscar en la BD
	 * 
	 *            [3] = String comparator = (String) varibles[i + 3]; representa que tipo de busqueda voy a hacer..,
	 *            ejemplo: where nombre=william o where nombre<>william, en este campo iria el tipo de comparador que
	 *            quiero si es = o <>
	 * 
	 *            Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1 busqueda en un campo, si se
	 *            ponen mas pues el continuara buscando en lo que se le ingresen en los otros 4
	 * 
	 * 
	 * @param variablesBetween
	 * 
	 *            la diferencia son estas dos posiciones
	 * 
	 *            [0] = String variable = (String) varibles[j]; la variable ne la BD que va a ser buscada en un rango
	 * 
	 *            [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
	 * 
	 *            [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango ejempolo: a > 1 and a < 5 --> 1
	 *            seria value y 5 seria value2
	 * 
	 *            [3] = String comparator1 = (String) varibles[j + 3]; comparador 1 ejemplo: a comparator1 1 and a < 5
	 * 
	 *            [4] = String comparator2 = (String) varibles[j + 4]; comparador 2 ejemplo: a comparador1>1 and a
	 *            comparador2<5 (el original: a > 1 and a < 5) *
	 * @param variablesBetweenDates
	 *            (en este caso solo para mysql) [0] = String variable = (String) varibles[k]; el nombre de la variable
	 *            que hace referencia a una fecha
	 * 
	 *            [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser dates)
	 * 
	 *            [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser dates)
	 * 
	 *            esto hace un between entre las dos fechas.
	 * 
	 * @return lista con los objetos que se necesiten
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<CueNavegacion> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<CueNavegacion> list = new ArrayList<CueNavegacion>();
		String where = new String();
		String tempWhere = new String();

		if (variables != null) {
			for (int i = 0; i < variables.length; i++) {
				if ((variables[i] != null) && (variables[i + 1] != null) && (variables[i + 2] != null)
						&& (variables[i + 3] != null)) {
					String variable = (String) variables[i];
					Boolean booVariable = (Boolean) variables[i + 1];
					Object value = variables[i + 2];
					String comparator = (String) variables[i + 3];

					if (booVariable.booleanValue()) {
						tempWhere = (tempWhere.length() == 0) ? ("(model." + variable + " " + comparator + " \'"
								+ value + "\' )") : (tempWhere + " AND (model." + variable + " " + comparator + " \'"
								+ value + "\' )");
					} else {
						tempWhere = (tempWhere.length() == 0) ? ("(model." + variable + " " + comparator + " " + value + " )")
								: (tempWhere + " AND (model." + variable + " " + comparator + " " + value + " )");
					}
				}

				i = i + 3;
			}
		}

		if (variablesBetween != null) {
			for (int j = 0; j < variablesBetween.length; j++) {
				if ((variablesBetween[j] != null) && (variablesBetween[j + 1] != null)
						&& (variablesBetween[j + 2] != null) && (variablesBetween[j + 3] != null)
						&& (variablesBetween[j + 4] != null)) {
					String variable = (String) variablesBetween[j];
					Object value = variablesBetween[j + 1];
					Object value2 = variablesBetween[j + 2];
					String comparator1 = (String) variablesBetween[j + 3];
					String comparator2 = (String) variablesBetween[j + 4];
					tempWhere = (tempWhere.length() == 0) ? ("(" + value + " " + comparator1 + " " + variable + " and "
							+ variable + " " + comparator2 + " " + value2 + " )")
							: (tempWhere + " AND (" + value + " " + comparator1 + " " + variable + " and " + variable
									+ " " + comparator2 + " " + value2 + " )");
				}

				j = j + 4;
			}
		}

		if (variablesBetweenDates != null) {
			for (int k = 0; k < variablesBetweenDates.length; k++) {
				if ((variablesBetweenDates[k] != null) && (variablesBetweenDates[k + 1] != null)
						&& (variablesBetweenDates[k + 2] != null)) {
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

					tempWhere = (tempWhere.length() == 0) ? ("(model." + variable + " between \'" + value + "\' and \'"
							+ value2 + "\')") : (tempWhere + " AND (model." + variable + " between \'" + value
							+ "\' and \'" + value2 + "\')");
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
			list = cueNavegacionDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}
}
