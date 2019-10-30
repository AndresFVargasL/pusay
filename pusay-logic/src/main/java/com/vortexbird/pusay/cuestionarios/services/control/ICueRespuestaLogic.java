package com.vortexbird.pusay.cuestionarios.services.control;

import java.util.List;

import com.vortexbird.pusay.cuestionarios.api.dto.CueRespuestaDTO;
import com.vortexbird.pusay.cuestionarios.model.CueListaContacto;
import com.vortexbird.pusay.cuestionarios.model.CueRespuesta;

/**
 * support Andr�s Mauricio C�rdenas mauriciocardenasp@gmail.com
 */
public interface ICueRespuestaLogic {
	public List<CueRespuesta> getCueRespuesta() throws Exception;

	/**
	 * Save an new CueRespuesta entity
	 */
	public void saveCueRespuesta(CueRespuesta respuesta) throws Exception;

	/**
	 * Delete an existing CueRespuesta entity
	 * 
	 */
	public void deleteCueRespuesta(Long consecutivo) throws Exception;

	/**
	 * Update an existing CueRespuesta entity
	 * 
	 */
	public void updateCueRespuesta(CueRespuesta respuesta) throws Exception;

	/**
	 * Load an existing CueRespuesta entity
	 * 
	 */
	public CueRespuesta getCueRespuesta(Long consecutivo) throws Exception;

	public List<CueRespuesta> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<CueRespuesta> findPageCueRespuesta(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberCueRespuesta() throws Exception;

	public List<CueRespuestaDTO> getDataCueRespuesta() throws Exception;

	public void setCodigoPregunta(Long codigoPregunta);

	public void deleteRespuestas(CueListaContacto cuelistaContacto);

	public CueRespuesta getCueRespuesta(Long listaContacto, Long codigoOpcion);

}
