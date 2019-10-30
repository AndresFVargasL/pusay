package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyDetalleObjetivoPlan;
import com.vortexbird.pusay.modelo.PsyObjetivoPlan;
import com.vortexbird.pusay.modelo.dto.PsyDetalleObjetivoPlanDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyDetalleObjetivoPlanLogic {
    public List<PsyDetalleObjetivoPlan> getPsyDetalleObjetivoPlan()
        throws Exception;

    /**
         * Save an new PsyDetalleObjetivoPlan entity
         */
    public void savePsyDetalleObjetivoPlan(PsyDetalleObjetivoPlan entity)
        throws Exception;

    /**
         * Delete an existing PsyDetalleObjetivoPlan entity
         *
         */
    public void deletePsyDetalleObjetivoPlan(PsyDetalleObjetivoPlan entity)
        throws Exception;

    /**
        * Update an existing PsyDetalleObjetivoPlan entity
        *
        */
    public void updatePsyDetalleObjetivoPlan(PsyDetalleObjetivoPlan entity)
        throws Exception;

    /**
         * Load an existing PsyDetalleObjetivoPlan entity
         *
         */
    public PsyDetalleObjetivoPlan getPsyDetalleObjetivoPlan(Long dobpCodigo)
        throws Exception;

    public List<PsyDetalleObjetivoPlan> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyDetalleObjetivoPlan> findPagePsyDetalleObjetivoPlan(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyDetalleObjetivoPlan()
        throws Exception;

    public List<PsyDetalleObjetivoPlanDTO> getDataPsyDetalleObjetivoPlan()
        throws Exception;
    
    public List<PsyDetalleObjetivoPlan> consultarDetalleObjetivoPlan(PsyObjetivoPlan objetivoPlan) throws Exception;
    
    public List<PsyDetalleObjetivoPlan> guardarDetalleObjetivoPlan(List<PsyDetalleObjetivoPlan> listaDetalle, PsyObjetivoPlan objetivoPlan, String nombreVacio, String descripcionVacio) throws Exception;
   
    public List<PsyDetalleObjetivoPlan> consultarListaCompletaObjetivos(List<PsyDetalleObjetivoPlan> lstObjetivoPlan, Integer cantidadOjetivos) throws Exception;
}
