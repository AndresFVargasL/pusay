package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyParametro;
import com.vortexbird.pusay.modelo.dto.PsyParametroDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyParametroLogic {
    public List<PsyParametro> getPsyParametro() throws Exception;

    /**
         * Save an new PsyParametro entity
         */
    public void savePsyParametro(PsyParametro entity) throws Exception;

    /**
         * Delete an existing PsyParametro entity
         *
         */
    public void deletePsyParametro(PsyParametro entity)
        throws Exception;

    /**
        * Update an existing PsyParametro entity
        *
        */
    public void updatePsyParametro(PsyParametro entity)
        throws Exception;

    /**
         * Load an existing PsyParametro entity
         *
         */
    public PsyParametro getPsyParametro(Long paramCodigo)
        throws Exception;

    public List<PsyParametro> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyParametro> findPagePsyParametro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsyParametro() throws Exception;

    public List<PsyParametroDTO> getDataPsyParametro()
        throws Exception;
    
    public PsyParametro getPsyParametro(String paramNombre)
            throws Exception;
}
