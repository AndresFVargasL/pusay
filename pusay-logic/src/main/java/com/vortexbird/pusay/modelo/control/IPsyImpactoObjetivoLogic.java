package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyImpactoObjetivo;
import com.vortexbird.pusay.modelo.dto.PsyImpactoObjetivoDTO;
import com.vortexbird.pusay.modelo.dto.PsyImpactoObjetivoTableDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyImpactoObjetivoLogic {
    public List<PsyImpactoObjetivo> getPsyImpactoObjetivo()
        throws Exception;

    /**
         * Save an new PsyImpactoObjetivo entity
         */
    public void savePsyImpactoObjetivo(PsyImpactoObjetivo entity)
        throws Exception;

    /**
         * Delete an existing PsyImpactoObjetivo entity
         *
         */
    public void deletePsyImpactoObjetivo(PsyImpactoObjetivo entity)
        throws Exception;

    /**
        * Update an existing PsyImpactoObjetivo entity
        *
        */
    public void updatePsyImpactoObjetivo(PsyImpactoObjetivo entity)
        throws Exception;

    /**
         * Load an existing PsyImpactoObjetivo entity
         *
         */
    public PsyImpactoObjetivo getPsyImpactoObjetivo(Long imobCodigo)
        throws Exception;

    public List<PsyImpactoObjetivo> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyImpactoObjetivo> findPagePsyImpactoObjetivo(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyImpactoObjetivo() throws Exception;

    public List<PsyImpactoObjetivoDTO> getDataPsyImpactoObjetivo()
        throws Exception;
    
    public List<PsyImpactoObjetivoTableDTO> getDataImpactoObjetivo(PsyEmpresa empresa) throws Exception;
}
