package com.vortexbird.pusay.dataaccess.dao;

import java.util.List;

import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyEvaluacionPeaIndicador;


/**
* Interface for   PsyEvaluacionPeaIndicadorDAO.
*
*/
public interface IPsyEvaluacionPeaIndicadorDAO extends Dao<PsyEvaluacionPeaIndicador, Long> {
	
	public List<PsyEvaluacionPeaIndicador> getDataPsyEvaluacionPeaIndicadorByEmpresa(PsyEmpresa empresa) throws Exception;
}
