package com.vortexbird.pusay.dataaccess.dao;

import java.util.List;

import com.vortexbird.pusay.cuestionarios.model.CueOpcion;
import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyMatrizEncuesta;


/**
* Interface for   PsyMatrizEncuestaDAO.
*
*/
public interface IPsyMatrizEncuestaDAO extends Dao<PsyMatrizEncuesta, Long> {
	
	public List<CueOpcion> consultaRespuestasPorCuestionario(Long pestCodigo, Long codigoCuestionario) throws Exception;
	
}
