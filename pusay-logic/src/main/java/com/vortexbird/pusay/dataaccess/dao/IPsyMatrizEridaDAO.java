package com.vortexbird.pusay.dataaccess.dao;

import java.util.List;

import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyAsuntoAmbiental;
import com.vortexbird.pusay.modelo.PsyMatrizErida;


/**
* Interface for   PsyMatrizEridaDAO.
*
*/
public interface IPsyMatrizEridaDAO extends Dao<PsyMatrizErida, Long> {
	public List<PsyAsuntoAmbiental> asuntosAmbientalesFaltantes(Long mearCodigo) throws Exception;
}
