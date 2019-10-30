package com.vortexbird.pusay.cuestionarios.services.control;

import java.util.List;

import com.vortexbird.pusay.cuestionarios.api.dto.CueEstadoDTO;
import com.vortexbird.pusay.cuestionarios.model.CueEstado;

/**
 * support Andr�s Mauricio C�rdenas mauriciocardenasp@gmail.com
 */
public interface ICueEstadoLogic {
	public List<CueEstado> getCueEstado() throws Exception;

	/**
	 * Save an new CueEstado entity
	 */
	public void saveCueEstado(CueEstado estado) throws Exception;

	/**
	 * Delete an existing CueEstado entity
	 * 
	 */
	public void deleteCueEstado(Long consecutivo) throws Exception;

	/**
	 * Update an existing CueEstado entity
	 * 
	 */
	public void updateCueEstado(CueEstado estado) throws Exception;

	/**
	 * Load an existing CueEstado entity
	 * 
	 */
	public CueEstado getCueEstado(Long consecutivo) throws Exception;

	public List<CueEstado> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<CueEstado> findPageCueEstado(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberCueEstado() throws Exception;

	public List<CueEstadoDTO> getDataCueEstado() throws Exception;

	public List<CueEstado> getCueEstadoActivo() throws Exception;
}
