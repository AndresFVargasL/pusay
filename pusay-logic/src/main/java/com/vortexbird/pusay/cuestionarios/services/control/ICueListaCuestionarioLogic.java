package com.vortexbird.pusay.cuestionarios.services.control;

import java.util.Date;
import java.util.List;

import com.vortexbird.pusay.cuestionarios.api.dto.CueListaCuestionarioDTO;
import com.vortexbird.pusay.cuestionarios.model.CueListaCuestionario;

/**
 * support Andr�s Mauricio C�rdenas mauriciocardenasp@gmail.com
 */
public interface ICueListaCuestionarioLogic {
	public List<CueListaCuestionario> getCueListaCuestionario() throws Exception;

	/**
	 * Save an new CueListaCuestionario entity
	 */
	public void saveCueListaCuestionario(Long consecutivo, Date fechaHoraAsignacion, Long consecutivo_CueCuestionario,
			Long consecutivo_CueLista) throws Exception;

	/**
	 * Delete an existing CueListaCuestionario entity
	 * 
	 */
	public void deleteCueListaCuestionario(Long consecutivo) throws Exception;

	/**
	 * Update an existing CueListaCuestionario entity
	 * 
	 */
	public void updateCueListaCuestionario(Long consecutivo, Date fechaHoraAsignacion,
			Long consecutivo_CueCuestionario, Long consecutivo_CueLista) throws Exception;

	/**
	 * Load an existing CueListaCuestionario entity
	 * 
	 */
	public CueListaCuestionario getCueListaCuestionario(Long consecutivo) throws Exception;

	public List<CueListaCuestionario> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<CueListaCuestionario> findPageCueListaCuestionario(String sortColumnName, boolean sortAscending,
			int startRow, int maxResults) throws Exception;

	public Long findTotalNumberCueListaCuestionario() throws Exception;

	public List<CueListaCuestionarioDTO> getDataCueListaCuestionario() throws Exception;

	public void saveCueListaCuestionario(CueListaCuestionario listaCuestionario) throws Exception;

	public void updateCueListaCuestionario(
			CueListaCuestionario cueListaCuestionario) throws Exception;

	public List<CueListaCuestionario> getCueListaCuestionarioPorCuestionario(
			Long consecutivo) throws Exception;
}
