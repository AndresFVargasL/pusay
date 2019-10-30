package com.vortexbird.pusay.dataaccess.dao;

import java.util.List;

import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyImpactoAmbiental;


/**
* Interface for   PsyImpactoAmbientalDAO.
*
*/
public interface IPsyImpactoAmbientalDAO extends Dao<PsyImpactoAmbiental, Long> {
	
	public List<PsyImpactoAmbiental> consultaImpactosAmbientalesSeleccionados(PsyEmpresa empresa) throws Exception;
	
	public Long countTemasPorImpacto(Long codigo) throws Exception;
	
	public Long countSubTemasPorImpacto(Long codigo) throws Exception;
	
	public Long countIndicadoresPorImpacto(Long codigo) throws Exception;
	
}
