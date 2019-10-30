package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyEvaluacionPeaIndicador;
import com.vortexbird.pusay.modelo.dto.PsyEvaluacionPeaIndicadorDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IPsyEvaluacionPeaIndicadorLogic {
    public List<PsyEvaluacionPeaIndicador> getPsyEvaluacionPeaIndicador()
        throws Exception;

    /**
         * Save an new PsyEvaluacionPeaIndicador entity
         */
    public void savePsyEvaluacionPeaIndicador(PsyEvaluacionPeaIndicador entity)
        throws Exception;

    /**
         * Delete an existing PsyEvaluacionPeaIndicador entity
         *
         */
    public void deletePsyEvaluacionPeaIndicador(
        PsyEvaluacionPeaIndicador entity) throws Exception;

    /**
        * Update an existing PsyEvaluacionPeaIndicador entity
        *
        */
    public void updatePsyEvaluacionPeaIndicador(
        PsyEvaluacionPeaIndicador entity) throws Exception;

    /**
         * Load an existing PsyEvaluacionPeaIndicador entity
         *
         */
    public PsyEvaluacionPeaIndicador getPsyEvaluacionPeaIndicador(Long codigo)
        throws Exception;

    public List<PsyEvaluacionPeaIndicador> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyEvaluacionPeaIndicador> findPagePsyEvaluacionPeaIndicador(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyEvaluacionPeaIndicador()
        throws Exception;

    public List<PsyEvaluacionPeaIndicadorDTO> getDataPsyEvaluacionPeaIndicador(PsyEmpresa empresa)
        throws Exception;
}
