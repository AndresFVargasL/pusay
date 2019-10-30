package com.vortexbird.pusay.cuestionarios.services.control;

import java.util.List;

import com.vortexbird.pusay.cuestionarios.api.dto.CueContactoDTO;
import com.vortexbird.pusay.cuestionarios.model.CueContacto;

/**
 * support Andr�s Mauricio C�rdenas mauriciocardenasp@gmail.com
 */
public interface ICueContactoLogic {
	public List<CueContacto> getCueContacto() throws Exception;

	/**
	 * Save an new CueContacto entity
	 */
	public void saveCueContacto(String apellido, String celular, String email, String empresa, Long estado,
			Long identificacion, String nombre) throws Exception;

	/**
	 * Delete an existing CueContacto entity
	 * 
	 */
	public void deleteCueContacto(Long identificacion) throws Exception;

	/**
	 * Update an existing CueContacto entity
	 * 
	 */
	public void updateCueContacto(String apellido, String celular, String email, String empresa, Long estado,
			Long identificacion, String nombre) throws Exception;

	/**
	 * Load an existing CueContacto entity
	 * 
	 */
	public CueContacto getCueContacto(Long identificacion) throws Exception;

	public List<CueContacto> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<CueContacto> findPageCueContacto(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberCueContacto() throws Exception;

	public List<CueContactoDTO> getDataCueContacto() throws Exception;

	public void saveCueContacto(CueContacto cueContacto) throws Exception;

	public void updateCueContacto(CueContacto cueContacto) throws Exception;

	public List<CueContacto> getCueContactosActivos();
}
