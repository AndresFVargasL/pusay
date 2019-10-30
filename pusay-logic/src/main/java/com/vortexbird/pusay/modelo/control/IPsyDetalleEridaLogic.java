package com.vortexbird.pusay.modelo.control;



import com.vortexbird.pusay.modelo.PsyDetalleErida;
import com.vortexbird.pusay.modelo.dto.PsyDetalleEridaDTO;
import com.vortexbird.pusay.modelo.dto.PsyPriorizacionAsuntoAmbientalDTO;
import com.vortexbird.pusay.modelo.dto.PsyPriorizacionImpactosAmbientalesDTO;
import com.vortexbird.pusay.modelo.dto.PsyTablaEridaDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyDetalleEridaLogic {
    public List<PsyDetalleErida> getPsyDetalleErida() throws Exception;

    /**
         * Save an new PsyDetalleErida entity
         */
    public void savePsyDetalleErida(PsyDetalleErida entity)
        throws Exception;

    /**
         * Delete an existing PsyDetalleErida entity
         *
         */
    public void deletePsyDetalleErida(PsyDetalleErida entity)
        throws Exception;

    /**
        * Update an existing PsyDetalleErida entity
        *
        */
    public void updatePsyDetalleErida(PsyDetalleErida entity)
        throws Exception;

    /**
         * Load an existing PsyDetalleErida entity
         *
         */
    public PsyDetalleErida getPsyDetalleErida(Long deerCodigo)
        throws Exception;

    public List<PsyDetalleErida> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyDetalleErida> findPagePsyDetalleErida(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyDetalleErida() throws Exception;

    public List<PsyDetalleEridaDTO> getDataPsyDetalleErida()
        throws Exception;
 public List<PsyTablaEridaDTO> consultarTablaErida(Long mearCodigo,Long asamCodigo) throws Exception;
    
    public void guardarTablaErida(List<PsyTablaEridaDTO> tablaEridaDTO,Long maerCodigo,Long asamCodigo)throws Exception;
    
    public List<Double> calcularTotalesTablaErida(List<PsyTablaEridaDTO> listPsyTablaEridaDTO)throws Exception;
    
    public List<PsyPriorizacionAsuntoAmbientalDTO> PriorizacionAsuntoAmbiental(Long mearCodigo) throws Exception;
	public List<PsyPriorizacionImpactosAmbientalesDTO> PriorizacionImpactosAmbientales(Long mearCodigo) throws Exception;
	
}
