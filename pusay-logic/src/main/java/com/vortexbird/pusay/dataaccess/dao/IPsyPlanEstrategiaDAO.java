package com.vortexbird.pusay.dataaccess.dao;

import java.util.List;

import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyDetalleMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPlanEstrategia;


/**
* Interface for   PsyPlanEstrategiaDAO.
*
*/
public interface IPsyPlanEstrategiaDAO extends Dao<PsyPlanEstrategia, Long> {
	
	public List<Object> consultaEstrategias(PsyEmpresa empresa, Long placCodigo) throws Exception;
	
	public List<PsyDetalleMapaEstrategico> consultaDetalleMapaEstrategicoPorNombreEstrategia(String name) throws Exception; 
	
	public List<Object> consultaEstrategiasSeleccionadas(PsyEmpresa empresa, Long placCodigo) throws Exception;
	
	public List<Object> consultaEstrategiasBtnNew(PsyEmpresa empresa) throws Exception;
	
	public List<Object> consultaDmaePorNombreEstrategia(PsyEmpresa empresa, Long placCodigo)
			throws Exception;
	
	public List<Object> consultaDmaePorPorMaesCodigo(PsyEmpresa empresa, Long placCodigo, Long maesCodigo)
			throws Exception;
	
	public List<PsyDetalleMapaEstrategico> validacionEstrategiasPlanActual(PsyEmpresa empresa, Long dmaeCodigo) throws Exception;
}
