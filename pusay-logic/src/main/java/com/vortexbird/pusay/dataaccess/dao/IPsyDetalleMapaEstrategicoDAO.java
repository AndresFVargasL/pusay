package com.vortexbird.pusay.dataaccess.dao;

import java.util.List;

import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyDetalleMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyImpactoAmbiental;
import com.vortexbird.pusay.modelo.PsyMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyMatrizCorrelacion;
import com.vortexbird.pusay.modelo.PsyObjetivoEstrategico;
import com.vortexbird.pusay.modelo.PsyObjetivoImpacto;
import com.vortexbird.pusay.modelo.dto.PsyDetalleMatrizCorrelacionDTO;


/**
* Interface for   PsyDetalleMapaEstrategicoDAO.
*
*/
public interface IPsyDetalleMapaEstrategicoDAO extends Dao<PsyDetalleMapaEstrategico, Long> {
	
	public List<PsyMatrizCorrelacion> consultarMatrizRelacionMapaEstrategico(Long obesCodigo, Long imamCodigo) throws Exception;
	
	public List<PsyObjetivoImpacto> consultarObjetivoImpactoPorPlanEstrategico(Long pestCodigo, Long obesCodigo) throws Exception;
	
	public List<PsyDetalleMapaEstrategico> consultaMatrizCorrelacionPorImpacto(PsyImpactoAmbiental impactoAmbiental,PsyMapaEstrategico mapaEstrategico) throws Exception;
	
	public List<PsyMatrizCorrelacion> consultaEstrategiasSeleccionadas(PsyDetalleMatrizCorrelacionDTO relacionSeleccionada, PsyMapaEstrategico mapaEstrategico) throws Exception;
	
	public List<PsyMatrizCorrelacion> consultaEstrategiasSeleccionadas(PsyImpactoAmbiental impactoAmbiental, PsyObjetivoEstrategico obesEstrategico, PsyMapaEstrategico mapaEstrategico) throws Exception;
	
}
