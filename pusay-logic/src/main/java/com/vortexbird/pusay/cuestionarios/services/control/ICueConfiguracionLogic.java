package com.vortexbird.pusay.cuestionarios.services.control;

import java.util.List;

import com.vortexbird.pusay.cuestionarios.api.dto.CueConfiguracionDTO;
import com.vortexbird.pusay.cuestionarios.model.CueConfiguracion;

/**
 * support Andr�s Mauricio C�rdenas mauriciocardenasp@gmail.com
 */
public interface ICueConfiguracionLogic {
	public List<CueConfiguracion> getCueConfiguracion() throws Exception;

	/**
	 * Save an new CueConfiguracion entity
	 */
	public void saveCueConfiguracion(CueConfiguracion configuracion) throws Exception;

	/**
	 * Delete an existing CueConfiguracion entity
	 * 
	 */
	public void deleteCueConfiguracion(Long consecutivo) throws Exception;

	/**
	 * Update an existing CueConfiguracion entity
	 * 
	 */
	public void updateCueConfiguracion(CueConfiguracion configuracion) throws Exception;

	/**
	 * Load an existing CueConfiguracion entity
	 * 
	 */
	public CueConfiguracion getCueConfiguracion(Long consecutivo) throws Exception;

	public List<CueConfiguracion> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<CueConfiguracion> findPageCueConfiguracion(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberCueConfiguracion() throws Exception;

	public List<CueConfiguracionDTO> getDataCueConfiguracion() throws Exception;
}
