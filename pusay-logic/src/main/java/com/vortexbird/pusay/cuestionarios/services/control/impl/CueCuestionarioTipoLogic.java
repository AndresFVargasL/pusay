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
import com.vortexbird.pusay.cuestionarios.api.dto.CueCuestionarioTipoDTO;
import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.api.util.Propiedades;
import com.vortexbird.pusay.cuestionarios.dao.ICueCuestionarioDAO;
import com.vortexbird.pusay.cuestionarios.dao.ICueCuestionarioTipoDAO;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionario;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionarioTipo;
import com.vortexbird.pusay.cuestionarios.services.control.ICueCuestionarioTipoLogic;

@Scope("singleton")
@Service("CueCuestionarioTipoLogic")
public class CueCuestionarioTipoLogic implements ICueCuestionarioTipoLogic {
	/**
	 * DAO injected by Spring that manages CueCuestionarioTipo entities support Andr�s Mauricio C�rdenas
	 * mauriciocardenasp@gmail.com
	 */
	@Autowired
	private ICueCuestionarioTipoDAO cueCuestionarioTipoDAO;

	/**
	 * DAO injected by Spring that manages CueCuestionario entities
	 * 
	 */
	@Autowired
	private ICueCuestionarioDAO cueCuestionarioDAO;
	
	private String lbl_consecutivo = Propiedades.getInstance().getMensaje(
			"lbl_cuestionarioTipo_consecutivo");
	private String lbl_nombre = Propiedades.getInstance().getMensaje(
			"lbl_cuestionarioTipo_nombre");
	private String lbl_descripcion = Propiedades.getInstance().getMensaje(
			"lbl_cuestionarioTipo_descripcion");
	private String lbl_estado = Propiedades.getInstance().getMensaje(
			"lbl_cuestionarioTipo_estado");
	private String lbl_lista = Propiedades.getInstance().getMensaje(
			"lbl_cuestionarioTipo_lista");

	
	@Transactional(readOnly = true)
	public List<CueCuestionarioTipo> getCueCuestionarioTipo() throws Exception {
		List<CueCuestionarioTipo> list = new ArrayList<CueCuestionarioTipo>();

		try {
			list = cueCuestionarioTipoDAO.findAll();
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL + lbl_lista);
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCueCuestionarioTipo(CueCuestionarioTipo cuestionarioTipo) throws Exception {
		CueCuestionarioTipo entity = null;

		try {
			/**
			if (cuestionarioTipo.getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
			}*/

			if ((cuestionarioTipo.getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cuestionarioTipo.getConsecutivo(), 10,
							0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_consecutivo);
			}

			if ((cuestionarioTipo.getDescripcion() != null || cuestionarioTipo.getDescripcion().length() == 0)
					&& (Utilities.checkWordAndCheckWithlength(cuestionarioTipo.getDescripcion(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_descripcion);
			}

			if (cuestionarioTipo.getEstado() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((cuestionarioTipo.getEstado() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cuestionarioTipo.getEstado(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if (cuestionarioTipo.getNombre() == null || cuestionarioTipo.getNombre().length() == 0) {
				throw new ZMessManager().new EmptyFieldException(lbl_nombre);
			}

			if ((cuestionarioTipo.getNombre() != null)
					&& (Utilities.checkWordAndCheckWithlength(cuestionarioTipo.getNombre(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_nombre);
			}

			entity = new CueCuestionarioTipo();
			entity.setDescripcion(cuestionarioTipo.getDescripcion());
			entity.setEstado(cuestionarioTipo.getEstado());
			entity.setNombre(cuestionarioTipo.getNombre());
			cueCuestionarioTipoDAO.save(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteCueCuestionarioTipo(Long consecutivo) throws Exception {
		CueCuestionarioTipo entity = null;

		if (consecutivo == null) {
			throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
		}

		List<CueCuestionario> cueCuestionarios = null;
		entity = getCueCuestionarioTipo(consecutivo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException("CueCuestionarioTipo");
		}

		try {
			cueCuestionarios = cueCuestionarioDAO.findByProperty("cueCuestionarioTipo.consecutivo", consecutivo);

			if (Utilities.validationsList(cueCuestionarios) == true) {
				throw new ZMessManager().new DeletingException("cueCuestionarios");
			}

			cueCuestionarioTipoDAO.delete(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCueCuestionarioTipo(CueCuestionarioTipo cuestionarioTipo) throws Exception {
		CueCuestionarioTipo entity = null;

		try {
			if (cuestionarioTipo.getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
			}

			if ((cuestionarioTipo.getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cuestionarioTipo.getConsecutivo(), 10,
							0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_consecutivo);
			}

			if ((cuestionarioTipo.getDescripcion() != null || cuestionarioTipo.getDescripcion().length() == 0)
					&& (Utilities.checkWordAndCheckWithlength(cuestionarioTipo.getDescripcion(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_descripcion);
			}

			if (cuestionarioTipo.getEstado() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((cuestionarioTipo.getEstado() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cuestionarioTipo.getEstado(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if (cuestionarioTipo.getNombre() == null || cuestionarioTipo.getNombre().length() == 0) {
				throw new ZMessManager().new EmptyFieldException(lbl_nombre);
			}

			if ((cuestionarioTipo.getNombre() != null)
					&& (Utilities.checkWordAndCheckWithlength(cuestionarioTipo.getNombre(), 256) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_nombre);
			}

			entity = getCueCuestionarioTipo(cuestionarioTipo.getConsecutivo());

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setDescripcion(cuestionarioTipo.getDescripcion());
			entity.setEstado(cuestionarioTipo.getEstado());
			entity.setNombre(cuestionarioTipo.getNombre());
			cueCuestionarioTipoDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<CueCuestionarioTipoDTO> getDataCueCuestionarioTipo() throws Exception {
		try {
			List<CueCuestionarioTipo> cueCuestionarioTipo = cueCuestionarioTipoDAO.findAll();

			List<CueCuestionarioTipoDTO> cueCuestionarioTipoDTO = new ArrayList<CueCuestionarioTipoDTO>();

			for (CueCuestionarioTipo cueCuestionarioTipoTmp : cueCuestionarioTipo) {
				CueCuestionarioTipoDTO cueCuestionarioTipoDTO2 = new CueCuestionarioTipoDTO();

				cueCuestionarioTipoDTO2.setConsecutivo(cueCuestionarioTipoTmp.getConsecutivo());
				cueCuestionarioTipoDTO2
						.setDescripcion((cueCuestionarioTipoTmp.getDescripcion() != null) ? cueCuestionarioTipoTmp
								.getDescripcion() : null);
				cueCuestionarioTipoDTO2.setEstado((cueCuestionarioTipoTmp.getEstado() != null) ? cueCuestionarioTipoTmp
						.getEstado() : null);
				cueCuestionarioTipoDTO2.setNombre((cueCuestionarioTipoTmp.getNombre() != null) ? cueCuestionarioTipoTmp
						.getNombre() : null);
				cueCuestionarioTipoDTO.add(cueCuestionarioTipoDTO2);
			}

			return cueCuestionarioTipoDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public CueCuestionarioTipo getCueCuestionarioTipo(Long consecutivo) throws Exception {
		CueCuestionarioTipo entity = null;

		try {
			entity = cueCuestionarioTipoDAO.findById(consecutivo);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueCuestionarioTipo");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<CueCuestionarioTipo> findPageCueCuestionarioTipo(String sortColumnName, boolean sortAscending,
			int startRow, int maxResults) throws Exception {
		List<CueCuestionarioTipo> entity = null;

		try {
			entity = cueCuestionarioTipoDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueCuestionarioTipo Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberCueCuestionarioTipo() throws Exception {
		Long entity = null;

		try {
			entity = cueCuestionarioTipoDAO.findTotalNumber();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueCuestionarioTipo Count");
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
	public List<CueCuestionarioTipo> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<CueCuestionarioTipo> list = new ArrayList<CueCuestionarioTipo>();
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
			list = cueCuestionarioTipoDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<CueCuestionarioTipo> getCueCuestionarioTiposActivos() {
		return this.cueCuestionarioTipoDAO.findByProperty("estado", 1L);
	}
}