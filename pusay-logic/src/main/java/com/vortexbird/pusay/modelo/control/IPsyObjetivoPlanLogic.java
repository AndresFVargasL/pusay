package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyObjetivoPlan;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;
import com.vortexbird.pusay.modelo.dto.PsyObjetivoPlanDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyObjetivoPlanLogic {
    public List<PsyObjetivoPlan> getPsyObjetivoPlan() throws Exception;

    /**
         * Save an new PsyObjetivoPlan entity
         */
    public void savePsyObjetivoPlan(PsyObjetivoPlan entity)
        throws Exception;

    /**
         * Delete an existing PsyObjetivoPlan entity
         *
         */
    public void deletePsyObjetivoPlan(PsyObjetivoPlan entity)
        throws Exception;

    /**
        * Update an existing PsyObjetivoPlan entity
        *
        */
    public void updatePsyObjetivoPlan(PsyObjetivoPlan entity)
        throws Exception;

    /**
         * Load an existing PsyObjetivoPlan entity
         *
         */
    public PsyObjetivoPlan getPsyObjetivoPlan(Long obplCodigo)
        throws Exception;

    public List<PsyObjetivoPlan> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyObjetivoPlan> findPagePsyObjetivoPlan(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyObjetivoPlan() throws Exception;

    public List<PsyObjetivoPlanDTO> getDataPsyObjetivoPlan()
        throws Exception;
    
    public PsyObjetivoPlan consultarObjetivoPlan(PsyPlanEstrategico planEstrategico, String Estado)
            throws Exception;
}
