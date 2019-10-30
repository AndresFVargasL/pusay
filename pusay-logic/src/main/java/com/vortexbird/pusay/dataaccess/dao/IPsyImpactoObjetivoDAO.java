package com.vortexbird.pusay.dataaccess.dao;

import java.util.List;

import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyImpactoObjetivo;
import com.vortexbird.pusay.modelo.dto.PsyImpactoObjetivoTableDTO;


/**
* Interface for   PsyImpactoObjetivoDAO.
*
*/
public interface IPsyImpactoObjetivoDAO extends Dao<PsyImpactoObjetivo, Long> {
	
	public List<PsyImpactoObjetivoTableDTO> getDataImpactoObjetivo(PsyEmpresa empresa) throws Exception;
	
}
