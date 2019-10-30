package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyDetalleMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyDetalleObjetivoPlan;
import com.vortexbird.pusay.modelo.PsyEstrategiaAmbiental;
import com.vortexbird.pusay.modelo.PsyImpactoAmbiental;
import com.vortexbird.pusay.modelo.PsyMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyMatrizCorrelacion;
import com.vortexbird.pusay.modelo.dto.PsyDetalleMapaEstrategicoDTO;
import com.vortexbird.pusay.modelo.dto.PsyDetalleMatrizCorrelacionDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyDetalleMapaEstrategicoLogic {
    public List<PsyDetalleMapaEstrategico> getPsyDetalleMapaEstrategico()
        throws Exception;

    /**
         * Save an new PsyDetalleMapaEstrategico entity
         */
    public void savePsyDetalleMapaEstrategico(PsyDetalleMapaEstrategico entity)
        throws Exception;

    /**
         * Delete an existing PsyDetalleMapaEstrategico entity
         *
         */
    public void deletePsyDetalleMapaEstrategico(
        PsyDetalleMapaEstrategico entity) throws Exception;

    /**
        * Update an existing PsyDetalleMapaEstrategico entity
        *
        */
    public void updatePsyDetalleMapaEstrategico(
        PsyDetalleMapaEstrategico entity) throws Exception;

    /**
         * Load an existing PsyDetalleMapaEstrategico entity
         *
         */
    public PsyDetalleMapaEstrategico getPsyDetalleMapaEstrategico(
        Long dmaeCodigo) throws Exception;

    public List<PsyDetalleMapaEstrategico> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyDetalleMapaEstrategico> findPagePsyDetalleMapaEstrategico(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyDetalleMapaEstrategico()
        throws Exception;

    public List<PsyDetalleMapaEstrategicoDTO> getDataPsyDetalleMapaEstrategico()
        throws Exception;
    
    public List<PsyDetalleMatrizCorrelacionDTO> consultarMatrizRelacionMapaEstrategico(PsyMapaEstrategico mapaEstrategico, List<PsyDetalleObjetivoPlan>  lstObjetivosCorportativo, Integer cantidadObjetivos, List<PsyImpactoAmbiental> lstImpactoAmbiental) throws Exception;

    public void guardarDetalleMapaEstrategico(PsyMapaEstrategico  mapaEstrategico, List<PsyDetalleMatrizCorrelacionDTO>  lstMatriz,  PsyImpactoAmbiental impactoAmbiental) throws Exception;

    public List<PsyDetalleMapaEstrategico> consultarDetalleMapaEstrategico(PsyMapaEstrategico mapaEstrategico) throws Exception;
    
    public List<PsyMatrizCorrelacion> consultaEstrategiasSeleccionadas(PsyDetalleMatrizCorrelacionDTO relacionSeleccionada, PsyMapaEstrategico mapaEstrategico) throws Exception;

}
