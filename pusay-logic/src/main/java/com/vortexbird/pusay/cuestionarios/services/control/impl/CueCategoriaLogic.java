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
import com.vortexbird.pusay.cuestionarios.api.dto.CueCategoriaDTO;
import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.api.util.Propiedades;
import com.vortexbird.pusay.cuestionarios.dao.ICueCategoriaDAO;
import com.vortexbird.pusay.cuestionarios.dao.ICuePreguntaDAO;
import com.vortexbird.pusay.cuestionarios.model.CueCategoria;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionario;
import com.vortexbird.pusay.cuestionarios.model.CuePregunta;
import com.vortexbird.pusay.cuestionarios.services.control.ICueCategoriaLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueCuestionarioLogic;

@Scope("singleton")
@Service("CueCategoriaLogic")
public class CueCategoriaLogic implements ICueCategoriaLogic {
	/**
	 * DAO injected by Spring that manages CueCategoria entities support Andr�s Mauricio C�rdenas
	 * mauriciocardenasp@gmail.com
	 */
	@Autowired
	private ICueCategoriaDAO cueCategoriaDAO;

	/**
	 * DAO injected by Spring that manages CuePregunta entities
	 * 
	 */
	@Autowired
	private ICuePreguntaDAO cuePreguntaDAO;

	/**
	 * Logic injected by Spring that manages CueCuestionario entities
	 * 
	 */
	@Autowired
	ICueCuestionarioLogic logicCueCuestionario1;

	private String lbl_consecutivo = Propiedades.getInstance().getMensaje("lbl_categoria_consecutivo");
	private String lbl_cueCuestionario = Propiedades.getInstance().getMensaje("lbl_categoria_cueCuestionario");
	private String lbl_nombre = Propiedades.getInstance().getMensaje("lbl_categoria_nombre");
	private String lbl_descripcion = Propiedades.getInstance().getMensaje("lbl_categoria_descripcion");
	private String lbl_estado = Propiedades.getInstance().getMensaje("lbl_categoria_estado");
	private String lbl_lista = Propiedades.getInstance().getMensaje("lbl_categoria_lista");

	@Transactional(readOnly = true)
	public List<CueCategoria> getCueCategoria() throws Exception {
		List<CueCategoria> list = new ArrayList<CueCategoria>();

		try {
			list = cueCategoriaDAO.findAll();
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL + lbl_lista);
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCueCategoria(Long consecutivo, String descripcion, Long estado, String nombre,
			Long consecutivo_CueCuestionario) throws Exception {
		CueCategoria entity = null;

		try {
			if (consecutivo == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
			}

			if ((consecutivo != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_consecutivo);
			}

			if ((descripcion != null) && (Utilities.checkWordAndCheckWithlength(descripcion, 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_descripcion);
			}

			if (estado == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((estado != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + estado, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if (nombre == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_nombre);
			}

			if ((nombre != null) && (Utilities.checkWordAndCheckWithlength(nombre, 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_nombre);
			}

			if (consecutivo_CueCuestionario == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueCuestionario);
			}

			if ((consecutivo_CueCuestionario != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo_CueCuestionario, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueCuestionario);
			}

			CueCuestionario cueCuestionarioClass = logicCueCuestionario1
					.getCueCuestionario(consecutivo_CueCuestionario);

			if (cueCuestionarioClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueCuestionario);
			}

			entity = getCueCategoria(consecutivo);

			if (entity != null) {
				throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
			}

			entity = new CueCategoria();
			entity.setConsecutivo(consecutivo);
			entity.setDescripcion(descripcion);
			entity.setEstado(estado);
			entity.setNombre(nombre);
			entity.setCueCuestionario(cueCuestionarioClass);
			cueCategoriaDAO.save(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteCueCategoria(Long consecutivo) throws Exception {
		CueCategoria entity = null;

		if (consecutivo == null) {
			throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
		}

		List<CuePregunta> cuePreguntas = null;
		entity = getCueCategoria(consecutivo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException(lbl_lista);
		}

		try {
			cuePreguntas = cuePreguntaDAO.findByProperty("cueCategoria.consecutivo", consecutivo);

			if (Utilities.validationsList(cuePreguntas) == true) {
				throw new ZMessManager().new DeletingException("cuePreguntas");
			}

			cueCategoriaDAO.delete(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCueCategoria(Long consecutivo, String descripcion, Long estado, String nombre,
			Long consecutivo_CueCuestionario) throws Exception {
		CueCategoria entity = null;

		try {
			if (consecutivo == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
			}

			if ((consecutivo != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_consecutivo);
			}

			if ((descripcion != null) && (Utilities.checkWordAndCheckWithlength(descripcion, 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_descripcion);
			}

			if (estado == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((estado != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + estado, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if (nombre == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_nombre);
			}

			if ((nombre != null) && (Utilities.checkWordAndCheckWithlength(nombre, 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_nombre);
			}

			if (consecutivo_CueCuestionario == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueCuestionario);
			}

			if ((consecutivo_CueCuestionario != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo_CueCuestionario, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueCuestionario);
			}

			CueCuestionario cueCuestionarioClass = logicCueCuestionario1
					.getCueCuestionario(consecutivo_CueCuestionario);

			if (cueCuestionarioClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueCuestionario);
			}

			entity = getCueCategoria(consecutivo);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setConsecutivo(consecutivo);
			entity.setDescripcion(descripcion);
			entity.setEstado(estado);
			entity.setNombre(nombre);
			entity.setCueCuestionario(cueCuestionarioClass);
			cueCategoriaDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<CueCategoriaDTO> getDataCueCategoria() throws Exception {
		try {
			List<CueCategoria> cueCategoria = cueCategoriaDAO.findAll();

			List<CueCategoriaDTO> cueCategoriaDTO = new ArrayList<CueCategoriaDTO>();

			for (CueCategoria cueCategoriaTmp : cueCategoria) {
				CueCategoriaDTO cueCategoriaDTO2 = new CueCategoriaDTO();

				cueCategoriaDTO2.setConsecutivo(cueCategoriaTmp.getConsecutivo());
				cueCategoriaDTO2.setDescripcion((cueCategoriaTmp.getDescripcion() != null) ? cueCategoriaTmp
						.getDescripcion() : null);
				cueCategoriaDTO2.setEstado((cueCategoriaTmp.getEstado() != null) ? cueCategoriaTmp.getEstado() : null);
				cueCategoriaDTO2.setNombre((cueCategoriaTmp.getNombre() != null) ? cueCategoriaTmp.getNombre() : null);
				cueCategoriaDTO2
						.setConsecutivo_CueCuestionario((cueCategoriaTmp.getCueCuestionario().getConsecutivo() != null) ? cueCategoriaTmp
								.getCueCuestionario().getConsecutivo() : null);
				cueCategoriaDTO.add(cueCategoriaDTO2);
			}

			return cueCategoriaDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public CueCategoria getCueCategoria(Long consecutivo) throws Exception {
		CueCategoria entity = null;

		try {
			entity = cueCategoriaDAO.findById(consecutivo);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(lbl_lista);
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<CueCategoria> findPageCueCategoria(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<CueCategoria> entity = null;

		try {
			entity = cueCategoriaDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueCategoria Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberCueCategoria() throws Exception {
		Long entity = null;

		try {
			entity = cueCategoriaDAO.findTotalNumber();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueCategoria Count");
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
	public List<CueCategoria> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<CueCategoria> list = new ArrayList<CueCategoria>();
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
			list = cueCategoriaDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCueCategoria(CueCategoria cueCategoria) throws Exception {
		try {
			CueCategoria entity = null;

			if ((cueCategoria.getDescripcion() != null)
					&& (Utilities.checkWordAndCheckWithlength(cueCategoria.getDescripcion(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_descripcion);
			}

			if (cueCategoria.getEstado() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((cueCategoria.getEstado() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueCategoria.getEstado(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if (cueCategoria.getNombre() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_nombre);
			}

			if ((cueCategoria.getNombre() != null)
					&& (Utilities.checkWordAndCheckWithlength(cueCategoria.getNombre(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_nombre);
			}

			if (cueCategoria.getCueCuestionario().getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueCuestionario);
			}

			if ((cueCategoria.getCueCuestionario().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ cueCategoria.getCueCuestionario().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueCuestionario);
			}

			CueCuestionario cueCuestionarioClass = logicCueCuestionario1.getCueCuestionario(cueCategoria
					.getCueCuestionario().getConsecutivo());

			if (cueCuestionarioClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueCuestionario);
			}

			entity = new CueCategoria();
			entity.setDescripcion(cueCategoria.getDescripcion());
			entity.setEstado(cueCategoria.getEstado());
			entity.setNombre(cueCategoria.getNombre());
			entity.setCueCuestionario(cueCategoria.getCueCuestionario());
			cueCategoriaDAO.save(entity);

		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCueCategoria(CueCategoria cueCategoria) throws Exception {
		try {

			if ((cueCategoria.getDescripcion() != null)
					&& (Utilities.checkWordAndCheckWithlength(cueCategoria.getDescripcion(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_descripcion);
			}

			if (cueCategoria.getEstado() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((cueCategoria.getEstado() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueCategoria.getEstado(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if (cueCategoria.getNombre() == null || cueCategoria.getNombre().length() == 0) {
				throw new ZMessManager().new EmptyFieldException(lbl_nombre);
			}

			if ((cueCategoria.getNombre() != null)
					&& (Utilities.checkWordAndCheckWithlength(cueCategoria.getNombre(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_nombre);
			}

			if (cueCategoria.getCueCuestionario().getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueCuestionario);
			}

			if ((cueCategoria.getCueCuestionario().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ cueCategoria.getCueCuestionario().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueCuestionario);
			}

			CueCuestionario cueCuestionarioClass = logicCueCuestionario1.getCueCuestionario(cueCategoria
					.getCueCuestionario().getConsecutivo());

			if (cueCuestionarioClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueCuestionario);
			}

			CueCategoria entity = getCueCategoria(cueCategoria.getConsecutivo());

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setConsecutivo(cueCategoria.getConsecutivo());
			entity.setDescripcion(cueCategoria.getDescripcion());
			entity.setEstado(cueCategoria.getEstado());
			entity.setNombre(cueCategoria.getNombre());
			entity.setCueCuestionario(cueCategoria.getCueCuestionario());
			cueCategoriaDAO.update(entity);

		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public CueCategoria findCategoriaByCuestionario(Long cuestionarioId) throws Exception {
		String whereCondition = "model.cueCuestionario.consecutivo = " + cuestionarioId;
		List<CueCategoria> list = this.cueCategoriaDAO.findByCriteria(whereCondition);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public List<CueCategoria> getCueCategoriaCuestionario(Long consecutivo) throws Exception {
		List<CueCategoria> list = new ArrayList<CueCategoria>();

		try {
			list = cueCategoriaDAO.findByProperty("cueCuestionario.consecutivo", consecutivo);
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL + lbl_lista);
		} finally {
		}

		return list;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<CueCategoria> getCueCategoriaCuestionarioActivos(Long consecutivoCuestionario) {
		String whereCondition = "model.cueCuestionario.consecutivo = " + consecutivoCuestionario
				+ " and model.estado = 1";
		return this.cueCategoriaDAO.findByCriteria(whereCondition);
	}
}