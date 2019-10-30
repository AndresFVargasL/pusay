package com.vortexbird.pusay.cuestionarios.services.control;

import java.util.List;

import com.vortexbird.pusay.cuestionarios.api.dto.CuePreguntaDTO;
import com.vortexbird.pusay.cuestionarios.model.CuePregunta;

/**
 * support Andr�s Mauricio C�rdenas mauriciocardenasp@gmail.com
 */
public interface ICuePreguntaLogic {
	public List<CuePregunta> getCuePregunta() throws Exception;

	/**
	 * Delete an existing CuePregunta entity
	 * 
	 */
	public void deleteCuePregunta(Long consecutivo) throws Exception;

	/**
	 * Load an existing CuePregunta entity
	 * 
	 */
	public CuePregunta getCuePregunta(Long consecutivo) throws Exception;

	public List<CuePregunta> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<CuePregunta> findPageCuePregunta(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberCuePregunta() throws Exception;

	public List<CuePreguntaDTO> getDataCuePregunta() throws Exception;

	public void updateCuePregunta(CuePregunta cuePregunta) throws Exception;

	public void saveCuePregunta(CuePregunta cuePregunta) throws Exception;

	public List<CuePregunta> findPreguntasByCategoria(Long consecutivo) throws Exception;

	public List<CuePregunta> getCuePreguntaCategoria(Long consecutivo) throws Exception;

	public void action_subir_orden_pregunta(CuePregunta cuePregunta);

	public void action_bajar_orden_pregunta(CuePregunta cuePregunta);

	public List<CuePregunta> findPreguntasActivasByCategoria(Long consecutivo);
}