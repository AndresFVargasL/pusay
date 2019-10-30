package com.vortexbird.pusay.dataaccess.dao;

import java.util.List;







import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyAsuntoAmbiental;
import com.vortexbird.pusay.modelo.PsyDetalleErida;
import com.vortexbird.pusay.modelo.dto.PsyPriorizacionAsuntoAmbientalDTO;
import com.vortexbird.pusay.modelo.dto.PsyPriorizacionImpactosAmbientalesDTO;
import com.vortexbird.pusay.modelo.dto.PsyTablaEridaDTO;


/**
* Interface for   PsyDetalleEridaDAO.
*
*/
public interface IPsyDetalleEridaDAO extends Dao<PsyDetalleErida, Long> {
	public List<PsyTablaEridaDTO> consultarTablaErida(Long mearCodigo,Long AsamCodigo ) throws Exception;
	public List<PsyTablaEridaDTO> llenarTablaErida() throws Exception;
	public List<PsyPriorizacionAsuntoAmbientalDTO> PriorizacionAsuntoAmbiental(Long mearCodigo) throws Exception;
	public List<PsyPriorizacionImpactosAmbientalesDTO> PriorizacionImpactosAmbientales(Long mearCodigo) throws Exception;
	
}
