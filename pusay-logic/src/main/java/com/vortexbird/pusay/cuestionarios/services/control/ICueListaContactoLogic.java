package com.vortexbird.pusay.cuestionarios.services.control;

import java.util.Date;
import java.util.List;

import com.vortexbird.pusay.cuestionarios.api.dto.CueListaContactoDTO;
import com.vortexbird.pusay.cuestionarios.model.CueListaContacto;

/**
 * support Andr�s Mauricio C�rdenas mauriciocardenasp@gmail.com
 */
public interface ICueListaContactoLogic {
	public List<CueListaContacto> getCueListaContacto() throws Exception;

	/**
	 * Save an new CueListaContacto entity
	 */
	public void saveCueListaContacto(Long consecutivo, Long duracion, Long estado, Date fechaHoraAsignacion,
			Date fechaHoraFinalizacion, Long puntajeTotal, Long identificacion_CueContacto, Long consecutivo_CueLista)
			throws Exception;

	/**
	 * Delete an existing CueListaContacto entity
	 * 
	 */
	public void deleteCueListaContacto(Long consecutivo) throws Exception;

	/**
	 * Update an existing CueListaContacto entity
	 * 
	 */
	public void updateCueListaContacto(Long consecutivo, Long duracion, Long estado, Date fechaHoraAsignacion,
			Date fechaHoraFinalizacion, Long puntajeTotal, Long identificacion_CueContacto, Long consecutivo_CueLista)
			throws Exception;

	/**
	 * Load an existing CueListaContacto entity
	 * 
	 */
	public CueListaContacto getCueListaContacto(Long consecutivo) throws Exception;

	public List<CueListaContacto> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<CueListaContacto> findPageCueListaContacto(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberCueListaContacto() throws Exception;

	public List<CueListaContactoDTO> getDataCueListaContacto() throws Exception;

	public void saveCueListaContacto(CueListaContacto selectedCueListaContacto) throws Exception;

	public void updateCueListaContacto(CueListaContacto listaContacto) throws Exception;

	public CueListaContacto getCueListaContacto(Long lista, Long identificacion, Long pestCodigo);
}
