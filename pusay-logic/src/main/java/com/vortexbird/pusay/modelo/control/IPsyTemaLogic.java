package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyTema;
import com.vortexbird.pusay.modelo.dto.PsyTemaDTO;

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
public interface IPsyTemaLogic {
    public List<PsyTema> getPsyTema() throws Exception;

    /**
         * Save an new PsyTema entity
         */
    public void savePsyTema(PsyTema entity) throws Exception;

    /**
         * Delete an existing PsyTema entity
         *
         */
    public void deletePsyTema(PsyTema entity) throws Exception;

    /**
        * Update an existing PsyTema entity
        *
        */
    public void updatePsyTema(PsyTema entity) throws Exception;

    /**
         * Load an existing PsyTema entity
         *
         */
    public PsyTema getPsyTema(Long codigo) throws Exception;

    public List<PsyTema> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyTema> findPagePsyTema(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsyTema() throws Exception;

    public List<PsyTemaDTO> getDataPsyTema() throws Exception;
}
