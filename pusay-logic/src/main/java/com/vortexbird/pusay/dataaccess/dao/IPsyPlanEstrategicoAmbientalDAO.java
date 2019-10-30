package com.vortexbird.pusay.dataaccess.dao;

import java.util.List;

import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;
import com.vortexbird.pusay.modelo.PsyPlanEstrategicoAmbiental;


/**
* Interface for   PsyPlanEstrategicoAmbientalDAO.
*
*/
public interface IPsyPlanEstrategicoAmbientalDAO extends Dao<PsyPlanEstrategicoAmbiental, Long> {
	
	public PsyPlanEstrategico getPlanEstrategicoByPEA(PsyEmpresa empresa, Long codigo) throws Exception;
	
	public PsyPlanEstrategico getPlanEstrategicoActivoByPEA(PsyEmpresa empresa) throws Exception;
	
	public PsyPlanEstrategico getPlanEstrategicoByPEA(PsyEmpresa empresa, String nombre) throws Exception;
	
	public List<PsyPlanEstrategicoAmbiental> getPEA(PsyEmpresa empresa) throws Exception;
	
}
