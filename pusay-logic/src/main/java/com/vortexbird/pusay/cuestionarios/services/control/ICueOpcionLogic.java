package com.vortexbird.pusay.cuestionarios.services.control;

import java.util.List;

import com.vortexbird.pusay.cuestionarios.api.dto.CueOpcionDTO;
import com.vortexbird.pusay.cuestionarios.model.CueOpcion;

/**
 * support Andr�s Mauricio C�rdenas mauriciocardenasp@gmail.com
 */
public interface ICueOpcionLogic {
	public List<CueOpcion> getCueOpcion() throws Exception;

	/**
	 * Save an new CueOpcion entity
	 */
	public void saveCueOpcion(String condicion, Long consecutivo, String enunciado, Long estado,
			Long indicadorCorrecta, String labelAmpliacion, Long orden, Long puntaje, Long requiereAmpliacion,
			Long consecutivo_CuePregunta) throws Exception;

	/**
	 * Delete an existing CueOpcion entity
	 * 
	 */
	public void deleteCueOpcion(Long consecutivo) throws Exception;

	/**
	 * Update an existing CueOpcion entity
	 * 
	 */
	public void updateCueOpcion(String condicion, Long consecutivo, String enunciado, Long estado,
			Long indicadorCorrecta, String labelAmpliacion, Long orden, Long puntaje, Long requiereAmpliacion,
			Long consecutivo_CuePregunta) throws Exception;

	/**
	 * Load an existing CueOpcion entity
	 * 
	 */
	public CueOpcion getCueOpcion(Long consecutivo) throws Exception;

	public List<CueOpcion> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<CueOpcion> findPageCueOpcion(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberCueOpcion() throws Exception;

	public List<CueOpcionDTO> getDataCueOpcion() throws Exception;

	public void saveCueOpcion(CueOpcion cueOpcion) throws Exception;

	public void updateCueOpcion(CueOpcion cueOpcion) throws Exception;

	public List<CueOpcion> getCueOpcionPregunta(Long consecutivo) throws Exception;

	public void action_subir_orden_opcion(CueOpcion cueOpcion);

	public void action_bajar_orden_opcion(CueOpcion cueOpcion);
}
