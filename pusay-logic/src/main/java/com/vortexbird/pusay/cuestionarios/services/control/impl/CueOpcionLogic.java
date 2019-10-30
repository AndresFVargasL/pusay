package com.vortexbird.pusay.cuestionarios.services.control.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.pusay.cuestionarios.api.Utilities;
import com.vortexbird.pusay.cuestionarios.api.VariablesGlobales;
import com.vortexbird.pusay.cuestionarios.api.dto.CueOpcionDTO;
import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.api.util.Propiedades;
import com.vortexbird.pusay.cuestionarios.dao.ICueNavegacionDAO;
import com.vortexbird.pusay.cuestionarios.dao.ICueOpcionDAO;
import com.vortexbird.pusay.cuestionarios.dao.ICueRespuestaDAO;
import com.vortexbird.pusay.cuestionarios.model.CueNavegacion;
import com.vortexbird.pusay.cuestionarios.model.CueOpcion;
import com.vortexbird.pusay.cuestionarios.model.CuePregunta;
import com.vortexbird.pusay.cuestionarios.model.CueRespuesta;
import com.vortexbird.pusay.cuestionarios.services.control.ICueOpcionLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICuePreguntaLogic;

@Scope("singleton")
@Service("CueOpcionLogic")
public class CueOpcionLogic implements ICueOpcionLogic {
	/**
	 * DAO injected by Spring that manages CueOpcion entities support Andr�s Mauricio C�rdenas
	 * mauriciocardenasp@gmail.com
	 */
	@Autowired
	private ICueOpcionDAO cueOpcionDAO;

	/**
	 * DAO injected by Spring that manages CueNavegacion entities
	 * 
	 */
	@Autowired
	private ICueNavegacionDAO cueNavegacionDAO;

	/**
	 * DAO injected by Spring that manages CueRespuesta entities
	 * 
	 */
	@Autowired
	private ICueRespuestaDAO cueRespuestaDAO;

	/**
	 * Logic injected by Spring that manages CuePregunta entities
	 * 
	 */
	@Autowired
	ICuePreguntaLogic logicCuePregunta1;
	
	@Resource
	public SessionFactory sessionFactory;

	private String lbl_consecutivo = Propiedades.getInstance().getMensaje(
			"lbl_opcion_consecutivo");
	private String lbl_cuePregunta = Propiedades.getInstance().getMensaje(
			"lbl_opcion_cuePregunta");
	private String lbl_enunciado = Propiedades.getInstance().getMensaje(
			"lbl_opcion_enunciado");
	private String lbl_condicion = Propiedades.getInstance().getMensaje(
			"lbl_opcion_condicion");
	private String lbl_orden = Propiedades.getInstance().getMensaje(
			"lbl_opcion_orden");
	private String lbl_puntaje = Propiedades.getInstance().getMensaje(
			"lbl_opcion_puntaje");
	private String lbl_requiereAmpliacion = Propiedades.getInstance().getMensaje(
			"lbl_opcion_requiereAmpliacion");
	private String lbl_labelAmpliacion = Propiedades.getInstance().getMensaje(
			"lbl_opcion_labelAmpliacion");
	private String lbl_indicadorCorrecta = Propiedades.getInstance().getMensaje(
			"lbl_opcion_indicadorCorrecta");
	private String lbl_estado = Propiedades.getInstance().getMensaje(
			"lbl_opcion_estado");
	private String lbl_lista = Propiedades.getInstance().getMensaje(
			"lbl_opcion_lista");
	
	@Transactional(readOnly = true)
	public List<CueOpcion> getCueOpcion() throws Exception {
		List<CueOpcion> list = new ArrayList<CueOpcion>();

		try {
			list = cueOpcionDAO.findAll();
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL + lbl_lista);
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCueOpcion(String condicion, Long consecutivo, String enunciado, Long estado,
			Long indicadorCorrecta, String labelAmpliacion, Long orden, Long puntaje, Long requiereAmpliacion,
			Long consecutivo_CuePregunta) throws Exception {
		CueOpcion entity = null;

		try {
			if (condicion == null) {
				condicion = VariablesGlobales.NO_APLICA;
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
				enunciado = VariablesGlobales.NO_APLICA;
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

			if (indicadorCorrecta == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_indicadorCorrecta);
			}

			if ((indicadorCorrecta != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + indicadorCorrecta, 2, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_indicadorCorrecta);
			}

			if ((labelAmpliacion != null) && (Utilities.checkWordAndCheckWithlength(labelAmpliacion, 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_labelAmpliacion);
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

			if (requiereAmpliacion == null) {
				requiereAmpliacion = VariablesGlobales.VR_NUMERO_DEFECTO;
			}

			if ((requiereAmpliacion != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + requiereAmpliacion, 2, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_requiereAmpliacion);
			}

			if (consecutivo_CuePregunta == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cuePregunta);
			}

			if ((consecutivo_CuePregunta != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo_CuePregunta, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cuePregunta);
			}

			CuePregunta cuePreguntaClass = logicCuePregunta1.getCuePregunta(consecutivo_CuePregunta);

			if (cuePreguntaClass == null) {
				throw new ZMessManager().new ForeignException("cuePregunta");
			}

			entity = getCueOpcion(consecutivo);

			if (entity != null) {
				throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
			}

			entity = new CueOpcion();
			entity.setCondicion(condicion);
			entity.setConsecutivo(consecutivo);
			entity.setEnunciado(enunciado);
			entity.setEstado(estado);
			entity.setIndicadorCorrecta(indicadorCorrecta);
			entity.setLabelAmpliacion(labelAmpliacion);
			entity.setOrden(orden);
			entity.setPuntaje(puntaje);
			entity.setRequiereAmpliacion(requiereAmpliacion);
			entity.setCuePregunta(cuePreguntaClass);
			cueOpcionDAO.save(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteCueOpcion(Long consecutivo) throws Exception {
		CueOpcion entity = null;

		if (consecutivo == null) {
			throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
		}

		List<CueNavegacion> cueNavegacions = null;
		List<CueRespuesta> cueRespuestas = null;
		entity = getCueOpcion(consecutivo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException(lbl_lista);
		}

		try {
			cueNavegacions = cueNavegacionDAO.findByProperty("cueOpcion.consecutivo", consecutivo);

			if (Utilities.validationsList(cueNavegacions) == true) {
				throw new ZMessManager().new DeletingException("cueNavegacions");
			}

			cueRespuestas = cueRespuestaDAO.findByProperty("cueOpcion.consecutivo", consecutivo);

			if (Utilities.validationsList(cueRespuestas) == true) {
				throw new ZMessManager().new DeletingException("cueRespuestas");
			}

			cueOpcionDAO.delete(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCueOpcion(String condicion, Long consecutivo, String enunciado, Long estado,
			Long indicadorCorrecta, String labelAmpliacion, Long orden, Long puntaje, Long requiereAmpliacion,
			Long consecutivo_CuePregunta) throws Exception {
		CueOpcion entity = null;

		try {
			if (condicion == null) {
				condicion = VariablesGlobales.NO_APLICA;
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
				enunciado = VariablesGlobales.NO_APLICA;
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

			if (indicadorCorrecta == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_indicadorCorrecta);
			}

			if ((indicadorCorrecta != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + indicadorCorrecta, 2, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_indicadorCorrecta);
			}

			if ((labelAmpliacion != null) && (Utilities.checkWordAndCheckWithlength(labelAmpliacion, 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_labelAmpliacion);
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

			if (requiereAmpliacion == null) {
				requiereAmpliacion = VariablesGlobales.VR_NUMERO_DEFECTO;
			}

			if ((requiereAmpliacion != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + requiereAmpliacion, 2, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_requiereAmpliacion);
			}

			if (consecutivo_CuePregunta == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cuePregunta);
			}

			if ((consecutivo_CuePregunta != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo_CuePregunta, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cuePregunta);
			}

			CuePregunta cuePreguntaClass = logicCuePregunta1.getCuePregunta(consecutivo_CuePregunta);

			if (cuePreguntaClass == null) {
				throw new ZMessManager().new ForeignException("cuePregunta");
			}

			entity = getCueOpcion(consecutivo);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setCondicion(condicion);
			entity.setConsecutivo(consecutivo);
			entity.setEnunciado(enunciado);
			entity.setEstado(estado);
			entity.setIndicadorCorrecta(indicadorCorrecta);
			entity.setLabelAmpliacion(labelAmpliacion);
			entity.setOrden(orden);
			entity.setPuntaje(puntaje);
			entity.setRequiereAmpliacion(requiereAmpliacion);
			entity.setCuePregunta(cuePreguntaClass);
			cueOpcionDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<CueOpcionDTO> getDataCueOpcion() throws Exception {
		try {
			List<CueOpcion> cueOpcion = cueOpcionDAO.findAll();

			List<CueOpcionDTO> cueOpcionDTO = new ArrayList<CueOpcionDTO>();

			for (CueOpcion cueOpcionTmp : cueOpcion) {
				CueOpcionDTO cueOpcionDTO2 = new CueOpcionDTO();

				cueOpcionDTO2.setConsecutivo(cueOpcionTmp.getConsecutivo());
				cueOpcionDTO2.setCondicion((cueOpcionTmp.getCondicion() != null) ? cueOpcionTmp.getCondicion() : null);
				cueOpcionDTO2.setEnunciado((cueOpcionTmp.getEnunciado() != null) ? cueOpcionTmp.getEnunciado() : null);
				cueOpcionDTO2.setEstado((cueOpcionTmp.getEstado() != null) ? cueOpcionTmp.getEstado() : null);
				cueOpcionDTO2.setIndicadorCorrecta((cueOpcionTmp.getIndicadorCorrecta() != null) ? cueOpcionTmp
						.getIndicadorCorrecta() : null);
				cueOpcionDTO2.setLabelAmpliacion((cueOpcionTmp.getLabelAmpliacion() != null) ? cueOpcionTmp
						.getLabelAmpliacion() : null);
				cueOpcionDTO2.setOrden((cueOpcionTmp.getOrden() != null) ? cueOpcionTmp.getOrden() : null);
				cueOpcionDTO2.setPuntaje((cueOpcionTmp.getPuntaje() != null) ? cueOpcionTmp.getPuntaje() : null);
				cueOpcionDTO2.setRequiereAmpliacion((cueOpcionTmp.getRequiereAmpliacion() != null) ? cueOpcionTmp
						.getRequiereAmpliacion() : null);
				cueOpcionDTO2
						.setConsecutivo_CuePregunta((cueOpcionTmp.getCuePregunta().getConsecutivo() != null) ? cueOpcionTmp
								.getCuePregunta().getConsecutivo() : null);
				cueOpcionDTO.add(cueOpcionDTO2);
			}

			return cueOpcionDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public CueOpcion getCueOpcion(Long consecutivo) throws Exception {
		CueOpcion entity = null;

		try {
			entity = cueOpcionDAO.findById(consecutivo);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(lbl_lista);
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<CueOpcion> findPageCueOpcion(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		List<CueOpcion> entity = null;

		try {
			entity = cueOpcionDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueOpcion Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberCueOpcion() throws Exception {
		Long entity = null;

		try {
			entity = cueOpcionDAO.findTotalNumber();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueOpcion Count");
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
	public List<CueOpcion> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		List<CueOpcion> list = new ArrayList<CueOpcion>();
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
			list = cueOpcionDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCueOpcion(CueOpcion cueOpcion) throws Exception {
		CueOpcion entity = null;

		try {
			
			int numeroRequiereAmpliacion = this.cueOpcionDAO.countRequiereAmpliacionByPregunta(cueOpcion.getCuePregunta().getConsecutivo());
			if (numeroRequiereAmpliacion >= 1) {
				throw new ZMessManager(ZMessManager.YA_REQUIERE_AMPLIACION);
			}
			
			if (cueOpcion.getCondicion() == null || cueOpcion.getCondicion().length() == 0 ) {
				cueOpcion.setCondicion(VariablesGlobales.NO_APLICA);
			}

			if ((cueOpcion.getCondicion() != null) && (Utilities.checkWordAndCheckWithlength(cueOpcion.getCondicion(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_condicion);
			}

			if (cueOpcion.getEnunciado() == null || cueOpcion.getEnunciado().length() == 0 ) {
				throw new ZMessManager().new NotValidFormatException(lbl_enunciado);
			}

			if ((cueOpcion.getEnunciado() != null) && (Utilities.checkWordAndCheckWithlength(cueOpcion.getEnunciado(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_enunciado);
			}

			if (cueOpcion.getEstado() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((cueOpcion.getEstado() != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueOpcion.getEstado(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if (cueOpcion.getIndicadorCorrecta() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_indicadorCorrecta);
			}

			if ((cueOpcion.getIndicadorCorrecta() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueOpcion.getIndicadorCorrecta(), 2, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_indicadorCorrecta);
			}

			if ((cueOpcion.getLabelAmpliacion() != null) && (Utilities.checkWordAndCheckWithlength(cueOpcion.getLabelAmpliacion(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_labelAmpliacion);
			}

			if (cueOpcion.getOrden() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_orden);
			}

			if ((cueOpcion.getOrden() != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueOpcion.getOrden(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_orden);
			}

			if (cueOpcion.getPuntaje() == null) {
				cueOpcion.setPuntaje(VariablesGlobales.VR_NUMERO_DEFECTO);
			}
			
			if ((cueOpcion.getPuntaje() != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueOpcion.getPuntaje(), 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_puntaje);
			}

			if (cueOpcion.getRequiereAmpliacion() == null) {
				cueOpcion.setRequiereAmpliacion(VariablesGlobales.VR_NUMERO_DEFECTO);
			}

			if ((cueOpcion.getRequiereAmpliacion() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueOpcion.getRequiereAmpliacion(), 2, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_requiereAmpliacion);
			}

			if (cueOpcion.getCuePregunta().getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cuePregunta);
			}

			if ((cueOpcion.getCuePregunta().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueOpcion.getCuePregunta().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cuePregunta);
			}

			CuePregunta cuePreguntaClass = logicCuePregunta1.getCuePregunta(cueOpcion.getCuePregunta().getConsecutivo());

			if (cuePreguntaClass == null) {
				throw new ZMessManager().new ForeignException("cuePregunta");
			}

			/**
			 * Se asigna el orden a la pregunta
			 */
			Criteria c = sessionFactory.getCurrentSession().createCriteria(CueOpcion.class);
			c.add(Restrictions.eq("cuePregunta.consecutivo", cueOpcion.getCuePregunta().getConsecutivo()));
			c.addOrder(Order.desc("orden"));
			c.setMaxResults(1);
			CueOpcion cueOpcionMax = (CueOpcion) c.uniqueResult();
			
			if( cueOpcionMax != null ){
				cueOpcion.setOrden(cueOpcionMax.getOrden()+1);
			}else{
				cueOpcion.setOrden(1l);
			}
			
			entity = new CueOpcion();
			entity.setCondicion(cueOpcion.getCondicion());
			entity.setConsecutivo(cueOpcion.getConsecutivo());
			entity.setEnunciado(cueOpcion.getEnunciado());
			entity.setEstado(cueOpcion.getEstado());
			entity.setIndicadorCorrecta(cueOpcion.getIndicadorCorrecta());
			entity.setLabelAmpliacion(cueOpcion.getLabelAmpliacion());
			entity.setOrden(cueOpcion.getOrden());
			entity.setPuntaje(cueOpcion.getPuntaje());
			entity.setRequiereAmpliacion(cueOpcion.getRequiereAmpliacion());
			entity.setCuePregunta(cuePreguntaClass);
			cueOpcionDAO.save(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCueOpcion(CueOpcion cueOpcion) throws Exception {
		CueOpcion entity = null;

		try {
			if (cueOpcion.getCondicion() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_condicion);
			}

			if ((cueOpcion.getCondicion() != null) && (Utilities.checkWordAndCheckWithlength(cueOpcion.getCondicion(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_condicion);
			}

			if (cueOpcion.getEnunciado() == null || cueOpcion.getEnunciado().length() == 0 ) {
				throw new ZMessManager().new NotValidFormatException(lbl_enunciado);
			}

			if ((cueOpcion.getEnunciado() != null) && (Utilities.checkWordAndCheckWithlength(cueOpcion.getEnunciado(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_enunciado);
			}

			if (cueOpcion.getEstado() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((cueOpcion.getEstado() != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueOpcion.getEstado(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if (cueOpcion.getIndicadorCorrecta() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_indicadorCorrecta);
			}

			if ((cueOpcion.getIndicadorCorrecta() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueOpcion.getIndicadorCorrecta(), 2, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_indicadorCorrecta);
			}

			if ((cueOpcion.getLabelAmpliacion() != null) && (Utilities.checkWordAndCheckWithlength(cueOpcion.getLabelAmpliacion(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_labelAmpliacion);
			}

			if (cueOpcion.getOrden() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_orden);
			}

			if ((cueOpcion.getOrden() != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueOpcion.getOrden(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_orden);
			}

			if (cueOpcion.getPuntaje() == null) {
				cueOpcion.setPuntaje(VariablesGlobales.VR_NUMERO_DEFECTO);
			}
			
			if ((cueOpcion.getPuntaje() != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueOpcion.getPuntaje(), 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_puntaje);
			}

			if (cueOpcion.getRequiereAmpliacion() == null) {
				cueOpcion.setRequiereAmpliacion(VariablesGlobales.VR_NUMERO_DEFECTO);
			}

			if ((cueOpcion.getRequiereAmpliacion() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueOpcion.getRequiereAmpliacion(), 2, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_requiereAmpliacion);
			}

			if (cueOpcion.getCuePregunta().getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cuePregunta);
			}

			if ((cueOpcion.getCuePregunta().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cueOpcion.getCuePregunta().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cuePregunta);
			}

			CuePregunta cuePreguntaClass = logicCuePregunta1.getCuePregunta(cueOpcion.getCuePregunta().getConsecutivo());

			if (cuePreguntaClass == null) {
				throw new ZMessManager().new ForeignException("cuePregunta");
			}

			entity = getCueOpcion(cueOpcion.getConsecutivo());

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}
			
			entity.setCondicion(cueOpcion.getCondicion());
			entity.setConsecutivo(cueOpcion.getConsecutivo());
			entity.setEnunciado(cueOpcion.getEnunciado());
			entity.setEstado(cueOpcion.getEstado());
			entity.setIndicadorCorrecta(cueOpcion.getIndicadorCorrecta());
			entity.setLabelAmpliacion(cueOpcion.getLabelAmpliacion());
			entity.setOrden(cueOpcion.getOrden());
			entity.setPuntaje(cueOpcion.getPuntaje());
			entity.setRequiereAmpliacion(cueOpcion.getRequiereAmpliacion());
			entity.setCuePregunta(cuePreguntaClass);
			cueOpcionDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<CueOpcion> getCueOpcionPregunta(Long consecutivo)
			throws Exception {
		List<CueOpcion> list = new ArrayList<CueOpcion>();

		try {
			list = cueOpcionDAO.findByCriteria("cuePregunta.consecutivo = " + consecutivo + " order by orden ASC");
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL + lbl_lista);
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void action_subir_orden_opcion(CueOpcion cueOpcion) {
		/**
		 * Se consulta la pregunta anterior
		 */
		List<CueOpcion> cueOpcionResultado = cueOpcionDAO.findByCriteria("cuePregunta.consecutivo = " + cueOpcion.getCuePregunta().getConsecutivo() +" and orden = "+ (cueOpcion.getOrden()-1) );
		if( cueOpcionResultado != null && cueOpcionResultado.size() == 1){
			Long ordenTmp = cueOpcionResultado.get(0).getOrden();
			cueOpcionResultado.get(0).setOrden(cueOpcion.getOrden());
			cueOpcion.setOrden(ordenTmp);
			
			cueOpcionDAO.update(cueOpcionResultado.get(0));
			cueOpcionDAO.update(cueOpcion);
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void action_bajar_orden_opcion(CueOpcion cueOpcion) {
		/**
		 * Se consulta la pregunta anterior
		 */
		List<CueOpcion> cueOpcionResultado = cueOpcionDAO.findByCriteria("cuePregunta.consecutivo = " + cueOpcion.getCuePregunta().getConsecutivo() +" and orden = "+ (cueOpcion.getOrden()+1) );
		if( cueOpcionResultado != null && cueOpcionResultado.size() == 1){
			Long ordenTmp = cueOpcionResultado.get(0).getOrden();
			cueOpcionResultado.get(0).setOrden(cueOpcion.getOrden());
			cueOpcion.setOrden(ordenTmp);
			
			cueOpcionDAO.update(cueOpcionResultado.get(0));
			cueOpcionDAO.update(cueOpcion);
		}
	}
	
}
