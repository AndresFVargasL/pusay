package com.vortexbird.pusay.cuestionarios.services.control;

import java.util.List;

import com.vortexbird.pusay.cuestionarios.api.dto.CueCuestionarioDTO;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionario;

/**
 * support Andr�s Mauricio C�rdenas mauriciocardenasp@gmail.com
 */
public interface ICueCuestionarioLogic {
	public List<CueCuestionario> getCueCuestionario() throws Exception;

	/**
	 * Save an new CueCuestionario entity
	 */
	public void saveCueCuestionario(CueCuestionario cuestionario) throws Exception;

	/**
	 * Delete an existing CueCuestionario entity
	 * 
	 */
	public void deleteCueCuestionario(Long consecutivo) throws Exception;

	/**
	 * Update an existing CueCuestionario entity
	 * 
	 */
	public void updateCueCuestionario(CueCuestionario cuestionario) throws Exception;

	/**
	 * Load an existing CueCuestionario entity
	 * 
	 */
	public CueCuestionario getCueCuestionario(Long consecutivo) throws Exception;

	public List<CueCuestionario> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<CueCuestionario> findPageCueCuestionario(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberCueCuestionario() throws Exception;

	public List<CueCuestionarioDTO> getDataCueCuestionario() throws Exception;

	public List<CueCuestionario> getCueCuestionariosActivos();
}
