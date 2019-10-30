package com.vortexbird.pusay.cuestionarios.services.control;

import java.util.List;

import com.vortexbird.pusay.cuestionarios.api.dto.CueCategoriaDTO;
import com.vortexbird.pusay.cuestionarios.model.CueCategoria;

/**
 * support Andr�s Mauricio C�rdenas mauriciocardenasp@gmail.com
 */
public interface ICueCategoriaLogic {
	public List<CueCategoria> getCueCategoria() throws Exception;
	
	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param cuestionarioId
	 * @return
	 * @throws Exception
	 */
	CueCategoria findCategoriaByCuestionario(Long cuestionarioId) throws Exception;

	/**
	 * Save an new CueCategoria entity
	 */
	public void saveCueCategoria(Long consecutivo, String descripcion, Long estado, String nombre,
			Long consecutivo_CueCuestionario) throws Exception;

	/**
	 * Delete an existing CueCategoria entity
	 * 
	 */
	public void deleteCueCategoria(Long consecutivo) throws Exception;

	/**
	 * Update an existing CueCategoria entity
	 * 
	 */
	public void updateCueCategoria(Long consecutivo, String descripcion, Long estado, String nombre,
			Long consecutivo_CueCuestionario) throws Exception;

	/**
	 * Load an existing CueCategoria entity
	 * 
	 */
	public CueCategoria getCueCategoria(Long consecutivo) throws Exception;

	public List<CueCategoria> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<CueCategoria> findPageCueCategoria(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberCueCategoria() throws Exception;

	public List<CueCategoriaDTO> getDataCueCategoria() throws Exception;

	public void saveCueCategoria(CueCategoria cueCategoria) throws Exception;

	public void updateCueCategoria(CueCategoria cueCategoria) throws Exception;

	public List<CueCategoria> getCueCategoriaCuestionario(Long consecutivo) throws Exception;

	public List<CueCategoria> getCueCategoriaCuestionarioActivos(Long consecutivoCuestionario);
}
