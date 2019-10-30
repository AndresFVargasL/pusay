package com.vortexbird.pusay.dataaccess.dao;

import java.util.List;

import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPrograma;
import com.vortexbird.pusay.modelo.dto.PsyDetalleEstrategiasDTO;
import com.vortexbird.pusay.modelo.dto.PsyProgramaDTO;


/**
* Interface for   PsyProgramaDAO.
*
*/
public interface IPsyProgramaDAO extends Dao<PsyPrograma, Long> {
	
	public List<PsyProgramaDTO> consultaProgramaPorEmpresa(PsyEmpresa empresa) throws Exception;
	
	public List<PsyDetalleEstrategiasDTO> consultaEstrategiasParaPrograma(PsyEmpresa empresa)
            throws Exception;
	
	public List<PsyDetalleEstrategiasDTO> consultaEstrategiasParaProgramaDepurada(PsyEmpresa empresa)
            throws Exception;
	
}
