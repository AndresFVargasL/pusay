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
import com.vortexbird.pusay.cuestionarios.api.dto.CueListaContactoDTO;
import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.api.util.Propiedades;
import com.vortexbird.pusay.cuestionarios.dao.ICueListaContactoDAO;
import com.vortexbird.pusay.cuestionarios.dao.ICueRespuestaDAO;
import com.vortexbird.pusay.cuestionarios.model.CueContacto;
import com.vortexbird.pusay.cuestionarios.model.CueLista;
import com.vortexbird.pusay.cuestionarios.model.CueListaContacto;
import com.vortexbird.pusay.cuestionarios.model.CueRespuesta;
import com.vortexbird.pusay.cuestionarios.services.control.ICueContactoLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueListaContactoLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueListaLogic;

@Scope("singleton")
@Service("CueListaContactoLogic")
public class CueListaContactoLogic implements ICueListaContactoLogic {
	/**
	 * DAO injected by Spring that manages CueListaContacto entities support Andr�s Mauricio C�rdenas
	 * mauriciocardenasp@gmail.com
	 */
	@Autowired
	private ICueListaContactoDAO cueListaContactoDAO;

	/**
	 * DAO injected by Spring that manages CueRespuesta entities
	 * 
	 */
	@Autowired
	private ICueRespuestaDAO cueRespuestaDAO;

	/**
	 * Logic injected by Spring that manages CueContacto entities
	 * 
	 */
	@Autowired
	ICueContactoLogic logicCueContacto1;

	/**
	 * Logic injected by Spring that manages CueLista entities
	 * 
	 */
	@Autowired
	ICueListaLogic logicCueLista2;

	private String lbl_consecutivo = Propiedades.getInstance().getMensaje(
			"lbl_listaContacto_consecutivo");
	private String lbl_cueLista = Propiedades.getInstance().getMensaje(
			"lbl_listaContacto_cueLista");
	private String lbl_cueContacto = Propiedades.getInstance().getMensaje(
			"lbl_listaContacto_cueContacto");
	private String lbl_fechaHoraAsignacion = Propiedades.getInstance().getMensaje(
			"lbl_listaContacto_fechaHoraAsignacion");
	private String lbl_fechaHoraFinalizacion = Propiedades.getInstance().getMensaje(
			"lbl_listaContacto_fechaHoraFinalizacion");
	private String lbl_puntajeTotal = Propiedades.getInstance().getMensaje(
			"lbl_listaContacto_puntajeTotal");
	private String lbl_duracion = Propiedades.getInstance().getMensaje(
			"lbl_listaContacto_duracion");
	private String lbl_estado = Propiedades.getInstance().getMensaje(
			"lbl_listaContacto_estado");
	
	@Transactional(readOnly = true)
	public List<CueListaContacto> getCueListaContacto() throws Exception {
		List<CueListaContacto> list = new ArrayList<CueListaContacto>();

		try {
			list = cueListaContactoDAO.findAll();
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "CueListaContacto");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCueListaContacto(Long consecutivo, Long duracion, Long estado, Date fechaHoraAsignacion,
			Date fechaHoraFinalizacion, Long puntajeTotal, Long identificacion_CueContacto, Long consecutivo_CueLista)
			throws Exception {
		CueListaContacto entity = null;

		try {
			if (consecutivo == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
			}

			if ((consecutivo != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_consecutivo);
			}

			if (duracion == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_duracion);
			}

			if ((duracion != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + duracion, 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_duracion);
			}

			if (estado == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((estado != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + estado, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if (fechaHoraAsignacion == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_fechaHoraAsignacion);
			}

			if (fechaHoraFinalizacion == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_fechaHoraFinalizacion);
			}

			if ((puntajeTotal != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + puntajeTotal, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_puntajeTotal);
			}

			if (identificacion_CueContacto == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueContacto);
			}

			if ((identificacion_CueContacto != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + identificacion_CueContacto, 17, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueContacto);
			}

			if (consecutivo_CueLista == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueLista);
			}

			if ((consecutivo_CueLista != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo_CueLista, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueLista);
			}

			CueContacto cueContactoClass = logicCueContacto1.getCueContacto(identificacion_CueContacto);
			CueLista cueListaClass = logicCueLista2.getCueLista(consecutivo_CueLista);

			if (cueContactoClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueContacto);
			}

			if (cueListaClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueLista);
			}

			entity = getCueListaContacto(consecutivo);

			if (entity != null) {
				throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
			}

			entity = new CueListaContacto();
			entity.setConsecutivo(consecutivo);
			entity.setDuracion(duracion);
			entity.setEstado(estado);
			entity.setFechaHoraAsignacion(fechaHoraAsignacion);
			entity.setFechaHoraFinalizacion(fechaHoraFinalizacion);
			entity.setPuntajeTotal(puntajeTotal);
			entity.setCueContacto(cueContactoClass);
			entity.setCueLista(cueListaClass);
			cueListaContactoDAO.save(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteCueListaContacto(Long consecutivo) throws Exception {
		CueListaContacto entity = null;

		if (consecutivo == null) {
			throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
		}

		List<CueRespuesta> cueRespuestas = null;
		entity = getCueListaContacto(consecutivo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException("CueListaContacto");
		}

		try {
			cueRespuestas = cueRespuestaDAO.findByProperty("cueListaContacto.consecutivo", consecutivo);

			if (Utilities.validationsList(cueRespuestas) == true) {
				throw new ZMessManager().new DeletingException("cueRespuestas");
			}

			cueListaContactoDAO.delete(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCueListaContacto(Long consecutivo, Long duracion, Long estado, Date fechaHoraAsignacion,
			Date fechaHoraFinalizacion, Long puntajeTotal, Long identificacion_CueContacto, Long consecutivo_CueLista)
			throws Exception {
		CueListaContacto entity = null;

		try {
			if (consecutivo == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
			}

			if ((consecutivo != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_consecutivo);
			}

			if (duracion == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_duracion);
			}

			if ((duracion != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + duracion, 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_duracion);
			}

			if (estado == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((estado != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + estado, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if (fechaHoraAsignacion == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_fechaHoraAsignacion);
			}

			if (fechaHoraFinalizacion == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_fechaHoraFinalizacion);
			}

			if ((puntajeTotal != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + puntajeTotal, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_puntajeTotal);
			}

			if (identificacion_CueContacto == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueContacto);
			}

			if ((identificacion_CueContacto != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + identificacion_CueContacto, 17, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueContacto);
			}

			if (consecutivo_CueLista == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueLista);
			}

			if ((consecutivo_CueLista != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + consecutivo_CueLista, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueLista);
			}

			CueContacto cueContactoClass = logicCueContacto1.getCueContacto(identificacion_CueContacto);
			CueLista cueListaClass = logicCueLista2.getCueLista(consecutivo_CueLista);

			if (cueContactoClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueContacto);
			}

			if (cueListaClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueLista);
			}

			entity = getCueListaContacto(consecutivo);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setConsecutivo(consecutivo);
			entity.setDuracion(duracion);
			entity.setEstado(estado);
			entity.setFechaHoraAsignacion(fechaHoraAsignacion);
			entity.setFechaHoraFinalizacion(fechaHoraFinalizacion);
			entity.setPuntajeTotal(puntajeTotal);
			entity.setCueContacto(cueContactoClass);
			entity.setCueLista(cueListaClass);
			cueListaContactoDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<CueListaContactoDTO> getDataCueListaContacto() throws Exception {
		try {
			List<CueListaContacto> cueListaContacto = cueListaContactoDAO.findAll();

			List<CueListaContactoDTO> cueListaContactoDTO = new ArrayList<CueListaContactoDTO>();

			for (CueListaContacto cueListaContactoTmp : cueListaContacto) {
				CueListaContactoDTO cueListaContactoDTO2 = new CueListaContactoDTO();

				cueListaContactoDTO2.setConsecutivo(cueListaContactoTmp.getConsecutivo());
				cueListaContactoDTO2.setDuracion((cueListaContactoTmp.getDuracion() != null) ? cueListaContactoTmp
						.getDuracion() : null);
				cueListaContactoDTO2.setEstado((cueListaContactoTmp.getEstado() != null) ? cueListaContactoTmp
						.getEstado() : null);
				cueListaContactoDTO2.setFechaHoraAsignacion(cueListaContactoTmp.getFechaHoraAsignacion());
				cueListaContactoDTO2.setFechaHoraFinalizacion(cueListaContactoTmp.getFechaHoraFinalizacion());
				cueListaContactoDTO2
						.setPuntajeTotal((cueListaContactoTmp.getPuntajeTotal() != null) ? cueListaContactoTmp
								.getPuntajeTotal() : null);
				cueListaContactoDTO2
						.setIdentificacion_CueContacto((cueListaContactoTmp.getCueContacto().getIdentificacion() != null) ? cueListaContactoTmp
								.getCueContacto().getIdentificacion() : null);
				cueListaContactoDTO2
						.setConsecutivo_CueLista((cueListaContactoTmp.getCueLista().getConsecutivo() != null) ? cueListaContactoTmp
								.getCueLista().getConsecutivo() : null);
				cueListaContactoDTO.add(cueListaContactoDTO2);
			}

			return cueListaContactoDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public CueListaContacto getCueListaContacto(Long consecutivo) throws Exception {
		CueListaContacto entity = null;

		try {
			entity = cueListaContactoDAO.findById(consecutivo);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueListaContacto");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<CueListaContacto> findPageCueListaContacto(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<CueListaContacto> entity = null;

		try {
			entity = cueListaContactoDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueListaContacto Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberCueListaContacto() throws Exception {
		Long entity = null;

		try {
			entity = cueListaContactoDAO.findTotalNumber();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueListaContacto Count");
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
	public List<CueListaContacto> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<CueListaContacto> list = new ArrayList<CueListaContacto>();
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
			list = cueListaContactoDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCueListaContacto(CueListaContacto selectedCueListaContacto)
			throws Exception {
		CueListaContacto entity = null;

		try {

			if (selectedCueListaContacto.getDuracion() == null) {
				selectedCueListaContacto.setDuracion(0l);
			}

			if ((selectedCueListaContacto.getDuracion() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + selectedCueListaContacto.getDuracion(), 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_duracion);
			}

			if (selectedCueListaContacto.getEstado() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((selectedCueListaContacto.getEstado() != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + selectedCueListaContacto.getEstado(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if (selectedCueListaContacto.getFechaHoraAsignacion() == null) {
				selectedCueListaContacto.setFechaHoraAsignacion(new Date());
			}

			if (selectedCueListaContacto.getFechaHoraFinalizacion() == null) {
				selectedCueListaContacto.setFechaHoraFinalizacion(selectedCueListaContacto.getFechaHoraAsignacion());
			}
			
			if (selectedCueListaContacto.getPuntajeTotal() == null) {
				selectedCueListaContacto.setPuntajeTotal(0l);
			}

			if ((selectedCueListaContacto.getPuntajeTotal() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + selectedCueListaContacto.getPuntajeTotal(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_puntajeTotal);
			}

			if (selectedCueListaContacto.getCueContacto().getIdentificacion() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueContacto);
			}

			if ((selectedCueListaContacto.getCueContacto().getIdentificacion() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + selectedCueListaContacto.getCueContacto().getIdentificacion(), 17, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueContacto);
			}

			if (selectedCueListaContacto.getCueLista().getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueLista);
			}

			if ((selectedCueListaContacto.getCueLista().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + selectedCueListaContacto.getCueLista().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueLista);
			}

			CueContacto cueContactoClass = logicCueContacto1.getCueContacto(selectedCueListaContacto.getCueContacto().getIdentificacion());
			CueLista cueListaClass = logicCueLista2.getCueLista(selectedCueListaContacto.getCueLista().getConsecutivo());

			if (cueContactoClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueContacto);
			}

			if (cueListaClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueLista);
			}

			List<CueListaContacto> resultado = cueListaContactoDAO.findByCriteria("cueLista.consecutivo = "+selectedCueListaContacto.getCueLista().getConsecutivo()+"and cueContacto.identificacion = "+selectedCueListaContacto.getCueContacto().getIdentificacion()+"and pestCodigo = "+selectedCueListaContacto.getPestCodigo());
			
			if(resultado != null && resultado.size() > 0){
				throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
			}
			
			if(selectedCueListaContacto.getPestCodigo()==null){
				throw new ZMessManager("Debe haber un plan estrategico asociado");
			}
			
			if(selectedCueListaContacto.getPestCodigo().equals("0")){
				throw new ZMessManager("Debe haber un plan estrategico asociado");
			}
				
			entity = new CueListaContacto();
			entity.setDuracion(selectedCueListaContacto.getDuracion());
			entity.setEstado(selectedCueListaContacto.getEstado());
			entity.setFechaHoraAsignacion(selectedCueListaContacto.getFechaHoraAsignacion());
			entity.setFechaHoraFinalizacion(selectedCueListaContacto.getFechaHoraFinalizacion());
			entity.setPuntajeTotal(selectedCueListaContacto.getPuntajeTotal());
			entity.setPestCodigo(selectedCueListaContacto.getPestCodigo());
			entity.setCueContacto(cueContactoClass);
			entity.setCueLista(cueListaClass);
			cueListaContactoDAO.save(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCueListaContacto(CueListaContacto selectedCueListaContacto)
			throws Exception {
		CueListaContacto entity = null;

		try {

			if (selectedCueListaContacto.getDuracion() == null) {
				selectedCueListaContacto.setDuracion(0l);
			}

			if ((selectedCueListaContacto.getDuracion() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + selectedCueListaContacto.getDuracion(), 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_duracion);
			}

			if (selectedCueListaContacto.getEstado() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_estado);
			}

			if ((selectedCueListaContacto.getEstado() != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + selectedCueListaContacto.getEstado(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_estado);
			}

			if (selectedCueListaContacto.getFechaHoraAsignacion() == null) {
				selectedCueListaContacto.setFechaHoraAsignacion(new Date());
			}

			if (selectedCueListaContacto.getFechaHoraFinalizacion() == null) {
				selectedCueListaContacto.setFechaHoraFinalizacion(selectedCueListaContacto.getFechaHoraAsignacion());
			}
			
			if (selectedCueListaContacto.getPuntajeTotal() == null) {
				selectedCueListaContacto.setPuntajeTotal(0l);
			}

			if ((selectedCueListaContacto.getPuntajeTotal() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + selectedCueListaContacto.getPuntajeTotal(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_puntajeTotal);
			}

			if (selectedCueListaContacto.getCueContacto().getIdentificacion() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueContacto);
			}

			if ((selectedCueListaContacto.getCueContacto().getIdentificacion() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + selectedCueListaContacto.getCueContacto().getIdentificacion(), 17, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueContacto);
			}

			if (selectedCueListaContacto.getCueLista().getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueLista);
			}

			if ((selectedCueListaContacto.getCueLista().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + selectedCueListaContacto.getCueLista().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueLista);
			}

			CueContacto cueContactoClass = logicCueContacto1.getCueContacto(selectedCueListaContacto.getCueContacto().getIdentificacion());
			CueLista cueListaClass = logicCueLista2.getCueLista(selectedCueListaContacto.getCueLista().getConsecutivo());

			if (cueContactoClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueContacto);
			}

			if (cueListaClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueLista);
			}

			/**
			List<CueListaContacto> resultado = cueListaContactoDAO.findByCriteria("cueLista.consecutivo = "+selectedCueListaContacto.getCueLista().getConsecutivo()+"and cueContacto.identificacion = "+selectedCueListaContacto.getCueContacto().getIdentificacion());
			
			if(resultado != null && resultado.size() > 0){
				throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
			}**/
				
			entity = getCueListaContacto(selectedCueListaContacto.getConsecutivo());

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}
			
			//entity = new CueListaContacto();
			entity.setDuracion(selectedCueListaContacto.getDuracion());
			entity.setEstado(selectedCueListaContacto.getEstado());
			entity.setFechaHoraAsignacion(selectedCueListaContacto.getFechaHoraAsignacion());
			entity.setFechaHoraFinalizacion(selectedCueListaContacto.getFechaHoraFinalizacion());
			entity.setPuntajeTotal(selectedCueListaContacto.getPuntajeTotal());
			entity.setCueContacto(cueContactoClass);
			entity.setCueLista(cueListaClass);
			cueListaContactoDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public CueListaContacto getCueListaContacto(Long lista, Long identificacion, Long pestCodigo) {
		List<CueListaContacto> resultado = cueListaContactoDAO.findByCriteria("cueLista.consecutivo = "+lista+" and  cueContacto.identificacion = "+identificacion+" and  pestCodigo = "+pestCodigo);// TODO Auto-generated method stub
		if( resultado != null && resultado.size() == 1) {
			return resultado.get(0);
		}
		return null;
	}
}
