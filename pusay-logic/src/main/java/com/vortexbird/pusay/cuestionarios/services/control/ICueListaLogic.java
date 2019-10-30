package com.vortexbird.pusay.cuestionarios.services.control;

import java.util.List;

import com.vortexbird.pusay.cuestionarios.api.dto.CueListaDTO;
import com.vortexbird.pusay.cuestionarios.model.CueLista;
import com.vortexbird.pusay.cuestionarios.model.CueListaContacto;

/**
 * support Andr�s Mauricio C�rdenas mauriciocardenasp@gmail.com
 */
public interface ICueListaLogic {
	public List<CueLista> getCueLista() throws Exception;

	/**
	 * Save an new CueLista entity
	 */
	public void saveCueLista(Long consecutivo, String descripcion, Long estado, String nombre) throws Exception;

	/**
	 * Delete an existing CueLista entity
	 * 
	 */
	public void deleteCueLista(Long consecutivo) throws Exception;

	/**
	 * Update an existing CueLista entity
	 * 
	 */
	public void updateCueLista(Long consecutivo, String descripcion, Long estado, String nombre) throws Exception;

	/**
	 * Load an existing CueLista entity
	 * 
	 */
	public CueLista getCueLista(Long consecutivo) throws Exception;

	public List<CueLista> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<CueLista> findPageCueLista(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberCueLista() throws Exception;

	public List<CueListaDTO> getDataCueLista() throws Exception;

	public void saveCueLista(CueLista cueLista) throws Exception; 

	public void updateCueLista(CueLista cueLista) throws Exception;

	public List<CueListaContacto> getCueListaContactoLista(Long consecutivo) throws Exception;

	public List<CueLista> getCueListasAtivas();
}
