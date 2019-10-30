package com.vortexbird.pusay.dataaccess.dao;

import java.util.List;

import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyIndicador;
import com.vortexbird.pusay.modelo.PsyPlanEstrategicoAmbiental;
import com.vortexbird.pusay.modelo.dto.PsyEvaluarIndicadoresDTO;


/**
* Interface for   PsyIndicadorDAO.
*
*/
public interface IPsyIndicadorDAO extends Dao<PsyIndicador, Long> {
	
	public List<PsyEvaluarIndicadoresDTO> getDataIndiCadoresByEmpresaByImam(PsyEmpresa empresa, Long imamCodigo, PsyPlanEstrategicoAmbiental planEstrategicoAmbiental) throws Exception;
	
	public List<PsyIndicador> getAllIndicadoresWithDistinct() throws Exception;
	
	public List<PsyEvaluarIndicadoresDTO> getDataIndiCadoresByEmpresaByObam(PsyEmpresa empresa, Long obamCodigo, PsyPlanEstrategicoAmbiental planEstrategicoAmbiental) throws Exception;
	
}
