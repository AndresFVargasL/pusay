package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyEvaluacionPeaObjetivo;
import com.vortexbird.pusay.modelo.dto.PsyEvaluacionPeaObjetivoDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IPsyEvaluacionPeaObjetivoLogic {
    public List<PsyEvaluacionPeaObjetivo> getPsyEvaluacionPeaObjetivo()
        throws Exception;

    /**
         * Save an new PsyEvaluacionPeaObjetivo entity
         */
    public void savePsyEvaluacionPeaObjetivo(PsyEvaluacionPeaObjetivo entity)
        throws Exception;

    /**
         * Delete an existing PsyEvaluacionPeaObjetivo entity
         *
         */
    public void deletePsyEvaluacionPeaObjetivo(PsyEvaluacionPeaObjetivo entity)
        throws Exception;

    /**
        * Update an existing PsyEvaluacionPeaObjetivo entity
        *
        */
    public void updatePsyEvaluacionPeaObjetivo(PsyEvaluacionPeaObjetivo entity)
        throws Exception;

    /**
         * Load an existing PsyEvaluacionPeaObjetivo entity
         *
         */
    public PsyEvaluacionPeaObjetivo getPsyEvaluacionPeaObjetivo(Long codigo)
        throws Exception;

    public List<PsyEvaluacionPeaObjetivo> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyEvaluacionPeaObjetivo> findPagePsyEvaluacionPeaObjetivo(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyEvaluacionPeaObjetivo()
        throws Exception;

    public List<PsyEvaluacionPeaObjetivoDTO> getDataPsyEvaluacionPeaObjetivo()
        throws Exception;
}
