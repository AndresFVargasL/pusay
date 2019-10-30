package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsySubtema;
import com.vortexbird.pusay.modelo.dto.PsySubtemaDTO;

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
public interface IPsySubtemaLogic {
    public List<PsySubtema> getPsySubtema() throws Exception;

    /**
         * Save an new PsySubtema entity
         */
    public void savePsySubtema(PsySubtema entity) throws Exception;

    /**
         * Delete an existing PsySubtema entity
         *
         */
    public void deletePsySubtema(PsySubtema entity) throws Exception;

    /**
        * Update an existing PsySubtema entity
        *
        */
    public void updatePsySubtema(PsySubtema entity) throws Exception;

    /**
         * Load an existing PsySubtema entity
         *
         */
    public PsySubtema getPsySubtema(Long codigo) throws Exception;

    public List<PsySubtema> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsySubtema> findPagePsySubtema(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsySubtema() throws Exception;

    public List<PsySubtemaDTO> getDataPsySubtema() throws Exception;
}
