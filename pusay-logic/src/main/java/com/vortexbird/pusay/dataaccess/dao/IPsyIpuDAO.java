package com.vortexbird.pusay.dataaccess.dao;

import java.util.List;

import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyIpu;
import com.vortexbird.pusay.modelo.PsyPlanAccion;
import com.vortexbird.pusay.modelo.dto.PsyIpuDTO;


/**
* Interface for   PsyIpuDAO.
*
*/
public interface IPsyIpuDAO extends Dao<PsyIpu, Long> {
	
	public List<PsyIpuDTO> consultarIpu(PsyPlanAccion  planAccion, String tipoIpu ) throws Exception;
	public Long consultarCantidadTareasHastaPeriodo(PsyPlanAccion planAccion,
 		   Long semana) throws Exception;
	 public Long consultarCantidadTareasPlan(PsyPlanAccion planAccion) throws Exception;
	 public Long consultarTotalPresupuestoPlan(PsyPlanAccion planAccion) throws Exception;
	 public Long consultarPrespuestoRealHastaPeriodo(PsyPlanAccion  planAccion, Long semanaFin)
		        throws Exception ;
	 
}
