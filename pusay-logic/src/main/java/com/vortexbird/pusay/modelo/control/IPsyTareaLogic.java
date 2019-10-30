package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPlanAccion;
import com.vortexbird.pusay.modelo.PsyTarea;
import com.vortexbird.pusay.modelo.dto.PsyTareaDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyTareaLogic {
    public List<PsyTarea> getPsyTarea() throws Exception;

    /**
         * Save an new PsyTarea entity
         */
    public void savePsyTarea(PsyTarea entity, String somResponsable) throws Exception;

    /**
         * Delete an existing PsyTarea entity
         *
         */
    public void deletePsyTarea(PsyTarea entity) throws Exception;

    /**
        * Update an existing PsyTarea entity
        *
        */
    public void updatePsyTarea(PsyTarea entity) throws Exception;

    /**
         * Load an existing PsyTarea entity
         *
         */
    public PsyTarea getPsyTarea(Long tareCodigo) throws Exception;

    public List<PsyTarea> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyTarea> findPagePsyTarea(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsyTarea() throws Exception;

    public List<PsyTareaDTO> getDataPsyTarea() throws Exception;
    
    public List<PsyTareaDTO> getDataPsyTareaByPsyPlanAccion(Long placCodigo) throws Exception;
    
    public List<PsyPlanAccion> findPsyPlanAccionByEmpresa(PsyEmpresa empresa) throws Exception;
}
