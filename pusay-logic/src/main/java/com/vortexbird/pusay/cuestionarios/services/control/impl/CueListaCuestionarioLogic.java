package com.vortexbird.pusay.cuestionarios.services.control.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.pusay.cuestionarios.api.Utilities;
import com.vortexbird.pusay.cuestionarios.api.dto.CueListaCuestionarioDTO;
import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.api.util.Propiedades;
import com.vortexbird.pusay.cuestionarios.dao.ICueListaCuestionarioDAO;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionario;
import com.vortexbird.pusay.cuestionarios.model.CueLista;
import com.vortexbird.pusay.cuestionarios.model.CueListaCuestionario;
import com.vortexbird.pusay.cuestionarios.services.control.ICueCuestionarioLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueListaCuestionarioLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueListaLogic;

@Scope("singleton")
@Service("CueListaCuestionarioLogic")
public class CueListaCuestionarioLogic implements ICueListaCuestionarioLogic {
	/**
	 * DAO injected by Spring that manages CueListaCuestionario entities support
	 * Andr�s Mauricio C�rdenas mauriciocardenasp@gmail.com
	 */
	@Autowired
	private ICueListaCuestionarioDAO cueListaCuestionarioDAO;

	/**
	 * Logic injected by Spring that manages CueCuestionario entities
	 * 
	 */
	@Autowired
	ICueCuestionarioLogic logicCueCuestionario1;

	/**
	 * Logic injected by Spring that manages CueLista entities
	 * 
	 */
	@Autowired
	ICueListaLogic logicCueLista2;
	
	private String lbl_consecutivo = Propiedades.getInstance().getMensaje(
			"lbl_listaCuestionario_consecutivo");
	private String lbl_cueLista = Propiedades.getInstance().getMensaje(
			"lbl_listaCuestionario_cueLista");
	private String lbl_cueCuestionario = Propiedades.getInstance().getMensaje(
			"lbl_listaCuestionario_cueCuestionario");
	private String lbl_fechaHoraAsignacion = Propiedades.getInstance().getMensaje(
			"lbl_listaCuestionario_fechaHoraAsignacion");
	private String lbl_lista = Propiedades.getInstance().getMensaje(
			"lbl_listaCuestionario_lista");
	
	@Transactional(readOnly = true)
	public List<CueListaCuestionario> getCueListaCuestionario()
			throws Exception {
		List<CueListaCuestionario> list = new ArrayList<CueListaCuestionario>();

		try {
			list = cueListaCuestionarioDAO.findAll();
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL
					+ lbl_lista);
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCueListaCuestionario(Long consecutivo,
			Date fechaHoraAsignacion, Long consecutivo_CueCuestionario,
			Long consecutivo_CueLista) throws Exception {
		CueListaCuestionario entity = null;

		try {
			if (consecutivo == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
			}

			if ((consecutivo != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ consecutivo, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						lbl_consecutivo);
			}

			if (fechaHoraAsignacion == null) {
				throw new ZMessManager().new EmptyFieldException(
						lbl_fechaHoraAsignacion);
			}

			if (consecutivo_CueCuestionario == null) {
				throw new ZMessManager().new EmptyFieldException(
						lbl_cueCuestionario);
			}

			if ((consecutivo_CueCuestionario != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ consecutivo_CueCuestionario, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						lbl_cueCuestionario);
			}

			if (consecutivo_CueLista == null) {
				throw new ZMessManager().new EmptyFieldException(
						lbl_cueLista);
			}

			if ((consecutivo_CueLista != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ consecutivo_CueLista, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						lbl_cueLista);
			}

			CueCuestionario cueCuestionarioClass = logicCueCuestionario1
					.getCueCuestionario(consecutivo_CueCuestionario);
			CueLista cueListaClass = logicCueLista2
					.getCueLista(consecutivo_CueLista);

			if (cueCuestionarioClass == null) {
				throw new ZMessManager().new ForeignException("cueCuestionario");
			}

			if (cueListaClass == null) {
				throw new ZMessManager().new ForeignException("cueLista");
			}

			entity = getCueListaCuestionario(consecutivo);

			if (entity != null) {
				throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
			}

			entity = new CueListaCuestionario();
			entity.setConsecutivo(consecutivo);
			entity.setFechaHoraAsignacion(fechaHoraAsignacion);
			entity.setCueCuestionario(cueCuestionarioClass);
			entity.setCueLista(cueListaClass);
			cueListaCuestionarioDAO.save(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteCueListaCuestionario(Long consecutivo) throws Exception {
		CueListaCuestionario entity = null;

		if (consecutivo == null) {
			throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
		}

		entity = getCueListaCuestionario(consecutivo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException(
					lbl_lista);
		}

		try {
			cueListaCuestionarioDAO.delete(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCueListaCuestionario(Long consecutivo,
			Date fechaHoraAsignacion, Long consecutivo_CueCuestionario,
			Long consecutivo_CueLista) throws Exception {
		CueListaCuestionario entity = null;

		try {
			if (consecutivo == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
			}

			if ((consecutivo != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ consecutivo, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						lbl_consecutivo);
			}

			if (fechaHoraAsignacion == null) {
				throw new ZMessManager().new EmptyFieldException(
						lbl_fechaHoraAsignacion);
			}

			if (consecutivo_CueCuestionario == null) {
				throw new ZMessManager().new EmptyFieldException(
						lbl_cueCuestionario);
			}

			if ((consecutivo_CueCuestionario != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ consecutivo_CueCuestionario, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						lbl_cueCuestionario);
			}

			if (consecutivo_CueLista == null) {
				throw new ZMessManager().new EmptyFieldException(
						lbl_cueLista);
			}

			if ((consecutivo_CueLista != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ consecutivo_CueLista, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						lbl_cueLista);
			}

			CueCuestionario cueCuestionarioClass = logicCueCuestionario1
					.getCueCuestionario(consecutivo_CueCuestionario);
			CueLista cueListaClass = logicCueLista2
					.getCueLista(consecutivo_CueLista);

			if (cueCuestionarioClass == null) {
				throw new ZMessManager().new ForeignException("cueCuestionario");
			}

			if (cueListaClass == null) {
				throw new ZMessManager().new ForeignException("cueLista");
			}

			entity = getCueListaCuestionario(consecutivo);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setConsecutivo(consecutivo);
			entity.setFechaHoraAsignacion(fechaHoraAsignacion);
			entity.setCueCuestionario(cueCuestionarioClass);
			entity.setCueLista(cueListaClass);
			cueListaCuestionarioDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<CueListaCuestionarioDTO> getDataCueListaCuestionario()
			throws Exception {
		try {
			List<CueListaCuestionario> cueListaCuestionario = cueListaCuestionarioDAO
					.findAll();

			List<CueListaCuestionarioDTO> cueListaCuestionarioDTO = new ArrayList<CueListaCuestionarioDTO>();

			for (CueListaCuestionario cueListaCuestionarioTmp : cueListaCuestionario) {
				CueListaCuestionarioDTO cueListaCuestionarioDTO2 = new CueListaCuestionarioDTO();

				cueListaCuestionarioDTO2.setConsecutivo(cueListaCuestionarioTmp
						.getConsecutivo());
				cueListaCuestionarioDTO2
						.setFechaHoraAsignacion(cueListaCuestionarioTmp
								.getFechaHoraAsignacion());
				cueListaCuestionarioDTO2
						.setConsecutivo_CueCuestionario((cueListaCuestionarioTmp
								.getCueCuestionario().getConsecutivo() != null) ? cueListaCuestionarioTmp
								.getCueCuestionario().getConsecutivo() : null);
				cueListaCuestionarioDTO2
						.setConsecutivo_CueLista((cueListaCuestionarioTmp
								.getCueLista().getConsecutivo() != null) ? cueListaCuestionarioTmp
								.getCueLista().getConsecutivo() : null);
				cueListaCuestionarioDTO.add(cueListaCuestionarioDTO2);
			}

			return cueListaCuestionarioDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public CueListaCuestionario getCueListaCuestionario(Long consecutivo)
			throws Exception {
		CueListaCuestionario entity = null;

		try {
			entity = cueListaCuestionarioDAO.findById(consecutivo);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					lbl_lista);
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<CueListaCuestionario> findPageCueListaCuestionario(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<CueListaCuestionario> entity = null;

		try {
			entity = cueListaCuestionarioDAO.findPage(sortColumnName,
					sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					"CueListaCuestionario Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberCueListaCuestionario() throws Exception {
		Long entity = null;

		try {
			entity = cueListaCuestionarioDAO.findTotalNumber();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					"CueListaCuestionario Count");
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
	public List<CueListaCuestionario> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		List<CueListaCuestionario> list = new ArrayList<CueListaCuestionario>();
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
			list = cueListaCuestionarioDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCueListaCuestionario(CueListaCuestionario listaCuestionario)
			throws Exception {
		CueListaCuestionario entity = null;

		try {
			if (listaCuestionario.getFechaHoraAsignacion() == null) {
				listaCuestionario.setFechaHoraAsignacion(new Date());
			}

			if (listaCuestionario.getCueLista().getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						lbl_cueLista);
			}

			if ((listaCuestionario.getCueLista().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ listaCuestionario.getCueLista().getConsecutivo(),
							10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						lbl_cueLista);
			}

			CueCuestionario cueCuestionarioClass = logicCueCuestionario1
					.getCueCuestionario(listaCuestionario.getCueCuestionario()
							.getConsecutivo());
			CueLista cueListaClass = logicCueLista2
					.getCueLista(listaCuestionario.getCueLista()
							.getConsecutivo());

			if (cueCuestionarioClass == null) {
				throw new ZMessManager().new ForeignException("cueCuestionario");
			}

			if (cueListaClass == null) {
				throw new ZMessManager().new ForeignException("cueLista");
			}

			/**
			 * entity = getCueListaCuestionario(consecutivo);
			 * 
			 * if (entity != null) { throw new
			 * ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY); }
			 */
			/**
			 * Se valida que la lista no se haya asignado al cuestionario para
			 * que el registro no se repita.
			 */
			List<CueListaCuestionario> resultado = cueListaCuestionarioDAO
					.findByCriteria("cueLista.consecutivo = "
							+ listaCuestionario.getCueLista().getConsecutivo()
							+ " and cueCuestionario.consecutivo = "
							+ listaCuestionario.getCueCuestionario()
									.getConsecutivo());

			if (resultado != null && resultado.size() > 0) {
				throw new Exception(ZMessManager.ENTITY_WITHSAMEKEY);
			}

			entity = new CueListaCuestionario();
			entity.setFechaHoraAsignacion(listaCuestionario
					.getFechaHoraAsignacion());
			entity.setCueCuestionario(cueCuestionarioClass);
			entity.setCueLista(cueListaClass);
			cueListaCuestionarioDAO.save(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCueListaCuestionario(
			CueListaCuestionario cueListaCuestionario) throws Exception {
		CueListaCuestionario entity = null;

		try {
			if (cueListaCuestionario.getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
			}

			if ((cueListaCuestionario.getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ cueListaCuestionario.getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						lbl_consecutivo);
			}

			if (cueListaCuestionario.getFechaHoraAsignacion() == null) {
				throw new ZMessManager().new EmptyFieldException(
						lbl_fechaHoraAsignacion);
			}

			if (cueListaCuestionario.getCueCuestionario() != null && cueListaCuestionario.getCueCuestionario().getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						lbl_cueCuestionario);
			}

			if ((cueListaCuestionario.getCueCuestionario() != null && cueListaCuestionario.getCueCuestionario().getConsecutivo() == null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ cueListaCuestionario.getCueCuestionario().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						lbl_cueCuestionario);
			}

			if (cueListaCuestionario.getCueLista() == null) {
				throw new ZMessManager().new EmptyFieldException(
						lbl_cueLista);
			}

			if ((cueListaCuestionario.getCueLista() != null && cueListaCuestionario.getCueLista().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ cueListaCuestionario.getCueLista().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						lbl_cueLista);
			}

			CueCuestionario cueCuestionarioClass = logicCueCuestionario1
					.getCueCuestionario(cueListaCuestionario.getCueCuestionario().getConsecutivo());
			CueLista cueListaClass = logicCueLista2
					.getCueLista(cueListaCuestionario.getCueLista().getConsecutivo());

			if (cueCuestionarioClass == null) {
				throw new ZMessManager().new ForeignException("cueCuestionario");
			}

			if (cueListaClass == null) {
				throw new ZMessManager().new ForeignException("cueLista");
			}

			entity = getCueListaCuestionario(cueListaCuestionario.getConsecutivo());

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setConsecutivo(cueListaCuestionario.getConsecutivo());
			entity.setFechaHoraAsignacion(cueListaCuestionario.getFechaHoraAsignacion());
			entity.setCueCuestionario(cueCuestionarioClass);
			entity.setCueLista(cueListaClass);
			cueListaCuestionarioDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<CueListaCuestionario> getCueListaCuestionarioPorCuestionario(
			Long consecutivo) throws Exception {
		List<CueListaCuestionario> list = new ArrayList<CueListaCuestionario>();

		try {
			list = cueListaCuestionarioDAO.findByProperty("cueCuestionario.consecutivo", consecutivo);
			
			if( list != null ){
				for (CueListaCuestionario cueListaCuestionario : list) {
					Hibernate.initialize(cueListaCuestionario.getCueCuestionario());
					Hibernate.initialize(cueListaCuestionario.getCueLista());
				}
			}
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL
					+ lbl_lista);
		} finally {
		}

		return list;
	}
}
