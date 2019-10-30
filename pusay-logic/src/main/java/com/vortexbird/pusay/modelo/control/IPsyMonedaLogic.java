package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyMoneda;
import com.vortexbird.pusay.modelo.dto.PsyMonedaDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyMonedaLogic {
    public List<PsyMoneda> getPsyMoneda() throws Exception;

    /**
         * Save an new PsyMoneda entity
         */
    public void savePsyMoneda(PsyMoneda entity) throws Exception;

    /**
         * Delete an existing PsyMoneda entity
         *
         */
    public void deletePsyMoneda(PsyMoneda entity) throws Exception;

    /**
        * Update an existing PsyMoneda entity
        *
        */
    public void updatePsyMoneda(PsyMoneda entity) throws Exception;

    /**
         * Load an existing PsyMoneda entity
         *
         */
    public PsyMoneda getPsyMoneda(Long moneCodigo) throws Exception;

    public List<PsyMoneda> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyMoneda> findPagePsyMoneda(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsyMoneda() throws Exception;

    public List<PsyMonedaDTO> getDataPsyMoneda() throws Exception;
}
