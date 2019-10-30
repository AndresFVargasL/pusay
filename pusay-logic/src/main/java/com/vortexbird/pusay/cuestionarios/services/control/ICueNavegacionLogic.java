package com.vortexbird.pusay.cuestionarios.services.control;

import java.util.List;

import com.vortexbird.pusay.cuestionarios.api.dto.CueNavegacionDTO;
import com.vortexbird.pusay.cuestionarios.model.CueNavegacion;

/**
 * support Andr�s Mauricio C�rdenas mauriciocardenasp@gmail.com
 */
public interface ICueNavegacionLogic {
	public List<CueNavegacion> getCueNavegacion() throws Exception;

	/**
	 * Save an new CueNavegacion entity
	 */
	public void saveCueNavegacion(Long consecutivo, Long consecutivo_CueCuestionario, Long consecutivo_CueOpcion,
			Long consecutivo_CuePreguntaOrigen, Long consecutivo_CuePreguntaDestino) throws Exception;

	/**
	 * Delete an existing CueNavegacion entity
	 * 
	 */
	public void deleteCueNavegacion(Long consecutivo) throws Exception;

	/**
	 * Update an existing CueNavegacion entity
	 * 
	 */
	public void updateCueNavegacion(Long consecutivo, Long consecutivo_CueCuestionario, Long consecutivo_CueOpcion,
			Long consecutivo_CuePreguntaOrigen, Long consecutivo_CuePreguntaDestino) throws Exception;

	/**
	 * Load an existing CueNavegacion entity
	 * 
	 */
	public CueNavegacion getCueNavegacion(Long consecutivo) throws Exception;

	public List<CueNavegacion> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<CueNavegacion> findPageCueNavegacion(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberCueNavegacion() throws Exception;

	public List<CueNavegacionDTO> getDataCueNavegacion() throws Exception;
}
