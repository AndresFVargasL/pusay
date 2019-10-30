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
import com.vortexbird.pusay.cuestionarios.api.dto.CueRespuestaDTO;
import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.api.util.Propiedades;
import com.vortexbird.pusay.cuestionarios.dao.ICueRespuestaDAO;
import com.vortexbird.pusay.cuestionarios.model.CueListaContacto;
import com.vortexbird.pusay.cuestionarios.model.CueOpcion;
import com.vortexbird.pusay.cuestionarios.model.CueRespuesta;
import com.vortexbird.pusay.cuestionarios.services.control.ICueListaContactoLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueOpcionLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueRespuestaLogic;

@Scope("singleton")
@Service("CueRespuestaLogic")
public class CueRespuestaLogic implements ICueRespuestaLogic {
	/**
	 * DAO injected by Spring that manages CueRespuesta entities support Andr�s Mauricio C�rdenas
	 * mauriciocardenasp@gmail.com
	 */
	@Autowired
	private ICueRespuestaDAO cueRespuestaDAO;

	/**
	 * Logic injected by Spring that manages CueListaContacto entities
	 * 
	 */
	@Autowired
	ICueListaContactoLogic logicCueListaContacto1;

	/**
	 * Logic injected by Spring that manages CueOpcion entities
	 * 
	 */
	@Autowired
	ICueOpcionLogic logicCueOpcion2;
	
	private String lbl_consecutivo = Propiedades.getInstance().getMensaje(
			"lbl_respuesta_consecutivo");
	private String lbl_cueOpcion = Propiedades.getInstance().getMensaje(
			"lbl_respuesta_cueOpcion");
	private String lbl_cueListaContacto = Propiedades.getInstance().getMensaje(
			"lbl_respuesta_cueListaContacto");
	private String lbl_fechaHoraRespuesta = Propiedades.getInstance().getMensaje(
			"lbl_respuesta_fechaHoraRespuesta");
	private String lbl_ip = Propiedades.getInstance().getMensaje(
			"lbl_respuesta_ip");
	private String lbl_respuestaAmpliacion = Propiedades.getInstance().getMensaje(
			"lbl_respuesta_respuestaAmpliacion");
	
	private Long codigoPregunta = null; 
	
	@Transactional(readOnly = true)
	public List<CueRespuesta> getCueRespuesta() throws Exception {
		List<CueRespuesta> list = new ArrayList<CueRespuesta>();

		try {
			list = cueRespuestaDAO.findAll();
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "CueRespuesta");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCueRespuesta(CueRespuesta respuesta) throws Exception {
		CueRespuesta entity = null;

		try {
			if (respuesta.getFechaHoraRespuesta() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_fechaHoraRespuesta);
			}

			if (respuesta.getIp() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_ip);
			}

			if ((respuesta.getIp() != null) && (Utilities.checkWordAndCheckWithlength(respuesta.getIp(), 128) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_ip);
			}

			if (respuesta.getCueListaContacto().getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueListaContacto);
			}

			if ((respuesta.getCueListaContacto().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ respuesta.getCueListaContacto().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueListaContacto);
			}

			/**
			 * Se valida si no hay ninguna opci�n seleccionada.
			 */
			if( respuesta.getCueOpcion() != null ){
				if (respuesta.getCueOpcion().getConsecutivo() == null) {
					throw new ZMessManager().new EmptyFieldException(lbl_cueOpcion);
				}

				if ((respuesta.getCueOpcion().getConsecutivo() != null)
						&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
								+ respuesta.getCueOpcion().getConsecutivo(), 10, 0) == false)) {
					throw new ZMessManager().new NotValidFormatException(lbl_cueOpcion);
				}

				if ((respuesta.getRespuestaAmpliacion() != null)
						&& (Utilities.checkWordAndCheckWithlength(respuesta.getRespuestaAmpliacion(), 200) == false)) {
					throw new ZMessManager().new NotValidFormatException(lbl_respuestaAmpliacion);
				}

				CueListaContacto cueListaContactoClass = this.logicCueListaContacto1.getCueListaContacto(respuesta
						.getCueListaContacto().getConsecutivo());
				CueOpcion cueOpcionClass = this.logicCueOpcion2.getCueOpcion(respuesta.getCueOpcion().getConsecutivo());

				if (cueListaContactoClass == null) {
					throw new ZMessManager().new ForeignException(lbl_cueListaContacto);
				}

				if (cueOpcionClass == null) {
					throw new ZMessManager().new ForeignException(lbl_cueOpcion);
				}
				
				/**
				 * Se debe validar si la respuesta ya est� almacenada para que no quede un registro doble.
				
				List<CueRespuesta> resultado = cueRespuestaDAO.findByCriteria("cueOpcion.cuePregunta.consecutivo = "+respuesta.getCueOpcion().getCuePregunta().getConsecutivo()+" and cueListaContacto.consecutivo = "+respuesta
						.getCueListaContacto().getConsecutivo());
				 */	
				entity = new CueRespuesta();
				entity.setFechaHoraRespuesta(respuesta.getFechaHoraRespuesta());
				entity.setIp(respuesta.getIp());
				entity.setCueListaContacto(cueListaContactoClass);
				entity.setCueOpcion(cueOpcionClass);
				entity.setRespuestaAmpliacion(respuesta.getRespuestaAmpliacion());
				this.cueRespuestaDAO.save(entity);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteCueRespuesta(Long consecutivo) throws Exception {
		CueRespuesta entity = null;

		if (consecutivo == null) {
			throw new ZMessManager().new EmptyFieldException(lbl_consecutivo);
		}

		entity = getCueRespuesta(consecutivo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException("CueRespuesta");
		}

		try {
			cueRespuestaDAO.delete(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCueRespuesta(CueRespuesta respuesta) throws Exception {
		CueRespuesta entity = null;

		try {
			if (respuesta.getFechaHoraRespuesta() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_fechaHoraRespuesta);
			}

			if (respuesta.getIp() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_ip);
			}

			if ((respuesta.getIp() != null) && (Utilities.checkWordAndCheckWithlength(respuesta.getIp(), 128) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_ip);
			}

			if (respuesta.getCueListaContacto().getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueListaContacto);
			}

			if ((respuesta.getCueListaContacto().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ respuesta.getCueListaContacto().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueListaContacto);
			}

			if (respuesta.getCueOpcion().getConsecutivo() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_cueOpcion);
			}

			if ((respuesta.getCueOpcion().getConsecutivo() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ respuesta.getCueOpcion().getConsecutivo(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_cueOpcion);
			}
			
			if ((respuesta.getRespuestaAmpliacion() != null)
					&& (Utilities.checkWordAndCheckWithlength(respuesta.getRespuestaAmpliacion(), 200) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_respuestaAmpliacion);
			}

			CueListaContacto cueListaContactoClass = this.logicCueListaContacto1.getCueListaContacto(respuesta
					.getCueListaContacto().getConsecutivo());
			CueOpcion cueOpcionClass = this.logicCueOpcion2.getCueOpcion(respuesta.getCueOpcion().getConsecutivo());

			if (cueListaContactoClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueListaContacto);
			}

			if (cueOpcionClass == null) {
				throw new ZMessManager().new ForeignException(lbl_cueOpcion);
			}

			entity = getCueRespuesta(respuesta.getConsecutivo());

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setFechaHoraRespuesta(respuesta.getFechaHoraRespuesta());
			entity.setIp(respuesta.getIp());
			entity.setCueListaContacto(cueListaContactoClass);
			entity.setCueOpcion(cueOpcionClass);
			entity.setRespuestaAmpliacion(respuesta.getRespuestaAmpliacion());
			this.cueRespuestaDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<CueRespuestaDTO> getDataCueRespuesta() throws Exception {
		try {
			List<CueRespuesta> cueRespuesta = cueRespuestaDAO.findAll();

			List<CueRespuestaDTO> cueRespuestaDTO = new ArrayList<CueRespuestaDTO>();

			for (CueRespuesta cueRespuestaTmp : cueRespuesta) {
				CueRespuestaDTO cueRespuestaDTO2 = new CueRespuestaDTO();

				cueRespuestaDTO2.setConsecutivo(cueRespuestaTmp.getConsecutivo());
				cueRespuestaDTO2.setFechaHoraRespuesta(cueRespuestaTmp.getFechaHoraRespuesta());
				cueRespuestaDTO2.setIp((cueRespuestaTmp.getIp() != null) ? cueRespuestaTmp.getIp() : null);
				cueRespuestaDTO2.setConsecutivo_CueListaContacto((cueRespuestaTmp.getCueListaContacto()
						.getConsecutivo() != null) ? cueRespuestaTmp.getCueListaContacto().getConsecutivo() : null);
				cueRespuestaDTO2
						.setConsecutivo_CueOpcion((cueRespuestaTmp.getCueOpcion().getConsecutivo() != null) ? cueRespuestaTmp
								.getCueOpcion().getConsecutivo() : null);
				cueRespuestaDTO.add(cueRespuestaDTO2);
			}

			return cueRespuestaDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public CueRespuesta getCueRespuesta(Long consecutivo) throws Exception {
		CueRespuesta entity = null;

		try {
			entity = cueRespuestaDAO.findById(consecutivo);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueRespuesta");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<CueRespuesta> findPageCueRespuesta(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<CueRespuesta> entity = null;

		try {
			entity = cueRespuestaDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueRespuesta Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberCueRespuesta() throws Exception {
		Long entity = null;

		try {
			entity = cueRespuestaDAO.findTotalNumber();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueRespuesta Count");
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
	public List<CueRespuesta> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<CueRespuesta> list = new ArrayList<CueRespuesta>();
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
			list = cueRespuestaDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	public void setCodigoPregunta(Long codigoPregunta) {
		this.codigoPregunta = codigoPregunta;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteRespuestas(CueListaContacto cuelistaContacto) {
		List<CueRespuesta> resultado = cueRespuestaDAO.findByCriteria("cueListaContacto.consecutivo = "+cuelistaContacto.getConsecutivo());
		/**
		 * Se deben eliminar las preguntas almacenadas para guardar las nuevas
		 */
			for (CueRespuesta cueRespuesta : resultado) {
				cueRespuestaDAO.delete(cueRespuesta);
			}
	}

	@Transactional(readOnly = true)
	public CueRespuesta getCueRespuesta(Long listaContacto, Long codigoOpcion) {
		List<CueRespuesta> resultado = cueRespuestaDAO.findByCriteria("cueListaContacto.consecutivo = "+listaContacto+" and cueOpcion.consecutivo = "+codigoOpcion);
		if( resultado != null && resultado.size() == 1 ){
			return resultado.get(0);
		}else{
			return null;
		}
	}
}
