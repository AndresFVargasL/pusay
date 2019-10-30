package com.vortexbird.pusay.dataaccess.dao;

import java.util.List;

import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyObjetivoAmbiental;


/**
* Interface for   PsyObjetivoAmbientalDAO.
*
*/
public interface IPsyObjetivoAmbientalDAO extends Dao<PsyObjetivoAmbiental, Long> {
	
	public List<PsyObjetivoAmbiental> consultaObjetivosAmbientalesSeleccionados(PsyEmpresa empresa) throws Exception;
}
