package com.vortexbird.pusay.dataaccess.dao;

import java.util.List;

import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyMatrizEncuesta;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;


/**
* Interface for   PsyPlanEstrategicoDAO.
*
*/
public interface IPsyPlanEstrategicoDAO extends Dao<PsyPlanEstrategico, Long> {
	
	public List<PsyMatrizEncuesta> consultaAsociacionDeCuestionarios(PsyEmpresa empresa) throws Exception;
}
