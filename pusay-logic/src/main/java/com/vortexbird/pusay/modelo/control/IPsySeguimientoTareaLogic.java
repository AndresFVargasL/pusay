package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsySeguimientoTarea;
import com.vortexbird.pusay.modelo.dto.PsySeguimientoTareaDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsySeguimientoTareaLogic {
    public List<PsySeguimientoTarea> getPsySeguimientoTarea()
        throws Exception;

    /**
         * Save an new PsySeguimientoTarea entity
         */
    public void savePsySeguimientoTarea(PsySeguimientoTarea entity)
        throws Exception;

    /**
         * Delete an existing PsySeguimientoTarea entity
         *
         */
    public void deletePsySeguimientoTarea(PsySeguimientoTarea entity)
        throws Exception;

    /**
        * Update an existing PsySeguimientoTarea entity
        *
        */
    public void updatePsySeguimientoTarea(PsySeguimientoTarea entity)
        throws Exception;

    /**
         * Load an existing PsySeguimientoTarea entity
         *
         */
    public PsySeguimientoTarea getPsySeguimientoTarea(Long setaCodigo)
        throws Exception;

    public List<PsySeguimientoTarea> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsySeguimientoTarea> findPagePsySeguimientoTarea(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsySeguimientoTarea() throws Exception;

    public List<PsySeguimientoTareaDTO> getDataPsySeguimientoTarea()
        throws Exception;
    
    public List<PsySeguimientoTareaDTO> getDataPsySeguimientoTareaByTareCodigo(Long tareCodigo) throws Exception;
}
