package com.vortexbird.pusay.cuestionarios.services.control.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.pusay.cuestionarios.api.Utilities;
import com.vortexbird.pusay.cuestionarios.api.dto.CuePreguntaDTO;
import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.api.util.Propiedades;
import com.vortexbird.pusay.cuestionarios.dao.ICueNavegacionDAO;
import com.vortexbird.pusay.cuestionarios.dao.ICueOpcionDAO;
import com.vortexbird.pusay.cuestionarios.dao.ICuePreguntaDAO;
import com.vortexbird.pusay.cuestionarios.model.CueCategoria;
import com.vortexbird.pusay.cuestionarios.model.CueNavegacion;
import com.vortexbird.pusay.cuestionarios.model.CueOpcion;
import com.vortexbird.pusay.cuestionarios.model.CuePregunta;
import com.vortexbird.pusay.cuestionarios.services.control.ICueCategoriaLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICuePreguntaLogic;

@Scope("singleton")
@Service("CuePreguntaLogic")
public class CuePreguntaLogic implements ICuePreguntaLogic {
	/**
	 * DAO injected by Spring that manages CuePregunta entities support Andr�s Mauricio C�rdenas
	 * mauriciocardenasp@gmail.com
	 */
	@Autowired
	private ICuePreguntaDAO cuePreguntaDAO;

	/**
	 * DAO injected by Spring that manages CueNavegacion entities
	 * 
	 */
	@Autowired
	private ICueNavegacionDAO cueNavegacionDAO;

	/**
	 * DAO injected by Spring that manages CueOpcion entities
	 * 
	 */
	@Autowired
	private ICueOpcionDAO cueOpcionDAO;

	/**
	 * Logic injected by Spring that manages CueCategoria entities
	 * 
	 */
	@Autowired
	ICueCategoriaLogic logicCueCategoria1;

	@Resource
	public SessionFactory sessionFactory;

	private String lbl_consecutivo = Propiedades.getInstance().getMensaje("lbl_pregunta_consecutivo");
	private String lbl_cueCategoria = Propiedades.getInstance().getMensaje("lbl_pregunta_cueCategoria");
	private String lbl_enunciado = Propiedades.getInstance().getMensaje("lbl_pregunta_enunciado");
	private String lbl_condicion = Propiedades.getInstance().getMensaje("lbl_pregunta_condicion");
	private String lbl_orden = Propiedades.getInstance().getMensaje("lbl_pregunta_orden");
	private String lbl_puntaje = Propiedades.getInstance().getMensaje("lbl_pregunta_puntaje");
	private String lbl_labelAmpliacion = Propiedades.getInstance().getMensaje("lbl_pregunta_labelAmpliacion");
	private String lbl_nroRespuestas = Propiedades.getInstance().getMensaje("lbl_pregunta_nroRespuestas");
	private String lbl_estado = Propiedades.getInstance().getMensaje("lbl_pregunta_estado");

	@Transactional(readOnly = true)
	public List<CuePregunta> getCuePregunta() throws Exception {
		List<CuePregunta> list = new ArrayList<CuePregunta>();

		try {
			list = cuePreguntaDAO.findByCriteria("order by orden ASC");
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "CuePregunta");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteCuePregunta(Long consecutivo) throws Exception {
		CuePregunta entity = null;

		if (consecutivo == null) {
			throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
		}

		List<CueNavegacion> cueNavegacionsForPreguntaDestino = null;
		List<CueNavegacion> cueNavegacionsForPreguntaOrigen = null;
		List<CueOpcion> cueOpcions = null;
		entity = getCuePregunta(consecutivo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException("CuePregunta");
		}

		try {
			cueNavegacionsForPreguntaDestino = cueNavegacionDAO.findByProperty(
					"cuePreguntaByPreguntaDestino.consecutivo", consecutivo);

			if (Utilities.validationsList(cueNavegacionsForPreguntaDestino) == true) {
				throw new ZMessManager().new DeletingException("cueNavegacionsForPreguntaDestino");
			}

			cueNavegacionsForPreguntaOrigen = cueNavegacionDAO.findByProperty(
					"cuePreguntaByPreguntaOrigen.consecutivo", consecutivo);

			if (Utilities.validationsList(cueNavegacionsForPreguntaOrigen) == true) {
				throw new ZMessManager().new DeletingException("cueNavegacionsForPreguntaOrigen");
			}

			cueOpcions = cueOpcionDAO.findByProperty("cuePregunta.consecutivo", consecutivo);

			if (Utilities.validationsList(cueOpcions) == true) {
				throw new ZMessManager().new DeletingException("cueOpcions");
			}

			cuePreguntaDAO.delete(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCuePregunta(String condicion, Long consecutivo, String enunciado, Long estado,
			String labelAmpliacion, Long nroRespuestas, Long orden, Long puntaje, Long consecutivo_CueCategoria)
			throws Exception {
		CuePregunta entity = null;

		try {
			if (condicion == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_condicion);
			}

			if ((condicion != null) && (Utilities.checkWordAndCheckWithlength(condicion, 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_condicion);
			}

			if (consecutivo == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
			}

			if ((consecutivo != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_consecutivo);
			}

			if (enunciado == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_enunciado);
			}

			if ((enunciado != null) && (Utilities.checkWordAndCheckWithlength(enunciado, 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_enunciado);
			}

			if (estado == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((estado != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + estado, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if ((labelAmpliacion != null) && (Utilities.checkWordAndCheckWithlength(labelAmpliacion, 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_labelAmpliacion);
			}

			if (nroRespuestas == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_nroRespuestas);
			}

			if ((nroRespuestas != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + nroRespuestas, 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_nroRespuestas);
			}

			if (orden == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_orden);
			}

			if ((orden != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + orden, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_orden);
			}

			if ((puntaje != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + puntaje, 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_puntaje);
			}

			if (consecutivo_CueCategoria == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueCategoria);
			}

			if ((consecutivo_CueCategoria != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo_CueCategoria, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueCategoria);
			}

			CueCategoria cueCategoriaClass = logicCueCategoria1.getCueCategoria(consecutivo_CueCategoria);

			if (cueCategoriaClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueCategoria);
			}

			entity = getCuePregunta(consecutivo);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setCondicion(condicion);
			entity.setConsecutivo(consecutivo);
			entity.setEnunciado(enunciado);
			entity.setEstado(estado);
			entity.setLabelAmpliacion(labelAmpliacion);
			entity.setNroRespuestas(nroRespuestas);
			entity.setOrden(orden);
			entity.setPuntaje(puntaje);
			entity.setCueCategoria(cueCategoriaClass);
			cuePreguntaDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<CuePreguntaDTO> getDataCuePregunta() throws Exception {
		try {
			List<CuePregunta> cuePregunta = cuePreguntaDAO.findAll();

			List<CuePreguntaDTO> cuePreguntaDTO = new ArrayList<CuePreguntaDTO>();

			for (CuePregunta cuePreguntaTmp : cuePregunta) {
				CuePreguntaDTO cuePreguntaDTO2 = new CuePreguntaDTO();

				cuePreguntaDTO2.setConsecutivo(cuePreguntaTmp.getConsecutivo());
				cuePreguntaDTO2.setCondicion((cuePreguntaTmp.getCondicion() != null) ? cuePreguntaTmp.getCondicion()
						: null);
				cuePreguntaDTO2.setEnunciado((cuePreguntaTmp.getEnunciado() != null) ? cuePreguntaTmp.getEnunciado()
						: null);
				cuePreguntaDTO2.setEstado((cuePreguntaTmp.getEstado() != null) ? cuePreguntaTmp.getEstado() : null);
				cuePreguntaDTO2.setLabelAmpliacion((cuePreguntaTmp.getLabelAmpliacion() != null) ? cuePreguntaTmp
						.getLabelAmpliacion() : null);
				cuePreguntaDTO2.setNroRespuestas((cuePreguntaTmp.getNroRespuestas() != null) ? cuePreguntaTmp
						.getNroRespuestas() : null);
				cuePreguntaDTO2.setOrden((cuePreguntaTmp.getOrden() != null) ? cuePreguntaTmp.getOrden() : null);
				cuePreguntaDTO2.setPuntaje((cuePreguntaTmp.getPuntaje() != null) ? cuePreguntaTmp.getPuntaje() : null);
				cuePreguntaDTO2
						.setConsecutivo_CueCategoria((cuePreguntaTmp.getCueCategoria().getConsecutivo() != null) ? cuePreguntaTmp
								.getCueCategoria().getConsecutivo() : null);
				cuePreguntaDTO.add(cuePreguntaDTO2);
			}

			return cuePreguntaDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public CuePregunta getCuePregunta(Long consecutivo) throws Exception {
		CuePregunta entity = null;

		try {
			entity = cuePreguntaDAO.findById(consecutivo);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CuePregunta");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<CuePregunta> findPageCuePregunta(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<CuePregunta> entity = null;

		try {
			entity = cuePreguntaDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CuePregunta Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberCuePregunta() throws Exception {
		Long entity = null;

		try {
			entity = cuePreguntaDAO.findTotalNumber();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CuePregunta Count");
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
	public List<CuePregunta> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<CuePregunta> list = new ArrayList<CuePregunta>();
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
			list = cuePreguntaDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCuePregunta(CuePregunta cuePregunta) throws Exception {
		try {
			if (cuePregunta.getCondicion() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_condicion);
			}

			if ((cuePregunta.getCondicion() != null)
					&& (Utilities.checkWordAndCheckWithlength(cuePregunta.getCondicion(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_condicion);
			}

			if (cuePregunta.getEnunciado() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_enunciado);
			}

			if ((cuePregunta.getEnunciado() != null)
					&& (Utilities.checkWordAndCheckWithlength(cuePregunta.getEnunciado(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_enunciado);
			}

			if (cuePregunta.getEstado() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((cuePregunta.getEstado() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cuePregunta.getEstado(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if ((cuePregunta.getLabelAmpliacion() != null)
					&& (Utilities.checkWordAndCheckWithlength(cuePregunta.getLabelAmpliacion(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_labelAmpliacion);
			}

			if (cuePregunta.getNroRespuestas() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_nroRespuestas);
			}

			if ((cuePregunta.getNroRespuestas() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cuePregunta.getNroRespuestas(), 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_nroRespuestas);
			}

			if (cuePregunta.getOrden() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_orden);
			}

			if ((cuePregunta.getOrden() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cuePregunta.getOrden(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_orden);
			}

			if ((cuePregunta.getPuntaje() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cuePregunta.getPuntaje(), 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_puntaje);
			}

			if (cuePregunta.getCueCategoria() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueCategoria);
			}

			if ((cuePregunta.getCueCategoria().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ cuePregunta.getCueCategoria().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueCategoria);
			}

			CueCategoria cueCategoriaClass = logicCueCategoria1.getCueCategoria(cuePregunta.getCueCategoria()
					.getConsecutivo());

			if (cueCategoriaClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueCategoria);
			}

			CuePregunta entity = getCuePregunta(cuePregunta.getConsecutivo());

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
			}

			entity.setConsecutivo(cuePregunta.getConsecutivo());
			entity.setCondicion(cuePregunta.getCondicion());
			entity.setEnunciado(cuePregunta.getEnunciado());
			entity.setEstado(cuePregunta.getEstado());
			entity.setLabelAmpliacion(cuePregunta.getLabelAmpliacion());
			entity.setNroRespuestas(cuePregunta.getNroRespuestas());
			entity.setOrden(cuePregunta.getOrden());
			entity.setPuntaje(cuePregunta.getPuntaje());
			entity.setCueCategoria(cueCategoriaClass);
			cuePreguntaDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCuePregunta(CuePregunta cuePregunta) throws Exception {
		try {
			if (cuePregunta.getCondicion() == null) {
				cuePregunta.setCondicion("NA");
			}

			if ((cuePregunta.getCondicion() != null)
					&& (Utilities.checkWordAndCheckWithlength(cuePregunta.getCondicion(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_condicion);
			}

			if (cuePregunta.getEnunciado() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_enunciado);
			}

			if ((cuePregunta.getEnunciado() != null)
					&& (Utilities.checkWordAndCheckWithlength(cuePregunta.getEnunciado(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_enunciado);
			}

			if (cuePregunta.getEstado() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((cuePregunta.getEstado() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cuePregunta.getEstado(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if ((cuePregunta.getLabelAmpliacion() != null)
					&& (Utilities.checkWordAndCheckWithlength(cuePregunta.getLabelAmpliacion(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_labelAmpliacion);
			}

			if (cuePregunta.getNroRespuestas() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_nroRespuestas);
			}

			if ((cuePregunta.getNroRespuestas() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cuePregunta.getNroRespuestas(), 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_nroRespuestas);
			}

			if ((cuePregunta.getOrden() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cuePregunta.getOrden(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_orden);
			}

			if ((cuePregunta.getPuntaje() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cuePregunta.getPuntaje(), 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_puntaje);
			}

			if (cuePregunta.getCueCategoria() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueCategoria);
			}

			if ((cuePregunta.getCueCategoria().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ cuePregunta.getCueCategoria().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueCategoria);
			}

			CueCategoria cueCategoriaClass = logicCueCategoria1.getCueCategoria(cuePregunta.getCueCategoria()
					.getConsecutivo());

			if (cueCategoriaClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueCategoria);
			}

			/**
			 * Se asigna el orden a la pregunta
			 */
			Criteria c = sessionFactory.getCurrentSession().createCriteria(CuePregunta.class);
			c.add(Restrictions.eq("cueCategoria.consecutivo", cueCategoriaClass.getConsecutivo()));
			c.addOrder(Order.desc("orden"));
			c.setMaxResults(1);
			CuePregunta cuePreguntaMax = (CuePregunta) c.uniqueResult();

			if (cuePreguntaMax != null) {
				cuePregunta.setOrden(cuePreguntaMax.getOrden() + 1);
			} else {
				cuePregunta.setOrden(1l);
			}

			CuePregunta entity = new CuePregunta();
			entity.setCondicion(cuePregunta.getCondicion());
			entity.setEnunciado(cuePregunta.getEnunciado());
			entity.setEstado(cuePregunta.getEstado());
			entity.setLabelAmpliacion(cuePregunta.getLabelAmpliacion());
			entity.setNroRespuestas(cuePregunta.getNroRespuestas());
			entity.setOrden(cuePregunta.getOrden());
			entity.setPuntaje(cuePregunta.getPuntaje());
			entity.setCueCategoria(cueCategoriaClass);
			cuePreguntaDAO.save(entity);

		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<CuePregunta> findPreguntasByCategoria(Long consecutivo) {
		String whereCondition = "model.cueCategoria.consecutivo = " + consecutivo;
		List<CuePregunta> list = this.cuePreguntaDAO.findByCriteria(whereCondition);
		for (CuePregunta cuePregunta : list) {
			Hibernate.initialize(cuePregunta.getCueOpcions());
		}
		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<CuePregunta> getCuePreguntaCategoria(Long consecutivo) throws Exception {
		List<CuePregunta> list = new ArrayList<CuePregunta>();

		try {
			list = cuePreguntaDAO.findByCriteria("cueCategoria.consecutivo = " + consecutivo + " order by orden ASC");

			if (list != null) {
				for (CuePregunta cuePregunta : list) {
					Hibernate.initialize(cuePregunta);
				}
			}
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "CuePregunta");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void action_subir_orden_pregunta(CuePregunta cuePregunta) {
		/**
		 * Se consulta la pregunta anterior
		 */
		List<CuePregunta> cuePreguntaResultado = cuePreguntaDAO.findByCriteria("cueCategoria.consecutivo = "
				+ cuePregunta.getCueCategoria().getConsecutivo() + " and orden = " + (cuePregunta.getOrden() - 1));
		if (cuePreguntaResultado != null && cuePreguntaResultado.size() == 1) {
			Long ordenTmp = cuePreguntaResultado.get(0).getOrden();
			cuePreguntaResultado.get(0).setOrden(cuePregunta.getOrden());
			cuePregunta.setOrden(ordenTmp);

			cuePreguntaDAO.update(cuePreguntaResultado.get(0));
			cuePreguntaDAO.update(cuePregunta);
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void action_bajar_orden_pregunta(CuePregunta cuePregunta) {
		/**
		 * Se consulta la pregunta anterior
		 */
		List<CuePregunta> cuePreguntaResultado = cuePreguntaDAO.findByCriteria("cueCategoria.consecutivo = "
				+ cuePregunta.getCueCategoria().getConsecutivo() + " and orden = " + (cuePregunta.getOrden() + 1));
		if (cuePreguntaResultado != null && cuePreguntaResultado.size() == 1) {
			Long ordenTmp = cuePreguntaResultado.get(0).getOrden();
			cuePreguntaResultado.get(0).setOrden(cuePregunta.getOrden());
			cuePregunta.setOrden(ordenTmp);

			cuePreguntaDAO.update(cuePreguntaResultado.get(0));
			cuePreguntaDAO.update(cuePregunta);
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<CuePregunta> findPreguntasActivasByCategoria(Long consecutivo) {
		String whereCondition = "model.cueCategoria.consecutivo = " + consecutivo + " and model.estado = 1";
		List<CuePregunta> list = this.cuePreguntaDAO.findByCriteria(whereCondition);
		for (CuePregunta cuePregunta : list) {
			Hibernate.initialize(cuePregunta.getCueOpcions());
		}
		return list;
	}
}