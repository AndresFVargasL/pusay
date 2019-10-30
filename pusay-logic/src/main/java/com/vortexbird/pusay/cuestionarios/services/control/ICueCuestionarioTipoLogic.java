package com.vortexbird.pusay.cuestionarios.services.control;

import java.util.List;

import com.vortexbird.pusay.cuestionarios.api.dto.CueCuestionarioTipoDTO;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionarioTipo;

/**
 * support Andr�s Mauricio C�rdenas mauriciocardenasp@gmail.com
 */
public interface ICueCuestionarioTipoLogic {
	public List<CueCuestionarioTipo> getCueCuestionarioTipo() throws Exception;

	/**
	 * Save an new CueCuestionarioTipo entity
	 */
	public void saveCueCuestionarioTipo(CueCuestionarioTipo cuestionarioTipo) throws Exception;

	/**
	 * Delete an existing CueCuestionarioTipo entity
	 * 
	 */
	public void deleteCueCuestionarioTipo(Long consecutivo) throws Exception;

	/**
	 * Update an existing CueCuestionarioTipo entity
	 * 
	 */
	public void updateCueCuestionarioTipo(CueCuestionarioTipo cuestionarioTipo) throws Exception;

	/**
	 * Load an existing CueCuestionarioTipo entity
	 * 
	 */
	public CueCuestionarioTipo getCueCuestionarioTipo(Long consecutivo) throws Exception;

	public List<CueCuestionarioTipo> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<CueCuestionarioTipo> findPageCueCuestionarioTipo(String sortColumnName, boolean sortAscending,
			int startRow, int maxResults) throws Exception;

	public Long findTotalNumberCueCuestionarioTipo() throws Exception;

	public List<CueCuestionarioTipoDTO> getDataCueCuestionarioTipo() throws Exception;

	public List<CueCuestionarioTipo> getCueCuestionarioTiposActivos();
}
