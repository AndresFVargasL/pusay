package com.vortexbird.pusay.cuestionarios.services.control;

import java.util.List;

import com.vortexbird.pusay.cuestionarios.api.dto.CueResponsableDTO;
import com.vortexbird.pusay.cuestionarios.model.CueResponsable;

/**
 * support Andr�s Mauricio C�rdenas mauriciocardenasp@gmail.com
 */
public interface ICueResponsableLogic {
	public List<CueResponsable> getCueResponsable() throws Exception;

	/**
	 * Save an new CueResponsable entity
	 */
	public void saveCueResponsable(CueResponsable responsable) throws Exception;

	/**
	 * Delete an existing CueResponsable entity
	 * 
	 */
	public void deleteCueResponsable(Long identificacion) throws Exception;

	/**
	 * Update an existing CueResponsable entity
	 * 
	 */
	public void updateCueResponsable(CueResponsable responsable) throws Exception;

	/**
	 * Load an existing CueResponsable entity
	 * 
	 */
	public CueResponsable getCueResponsable(Long identificacion) throws Exception;

	public List<CueResponsable> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<CueResponsable> findPageCueResponsable(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberCueResponsable() throws Exception;

	public List<CueResponsableDTO> getDataCueResponsable() throws Exception;
}
