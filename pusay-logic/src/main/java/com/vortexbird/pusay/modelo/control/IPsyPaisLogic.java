package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyPais;
import com.vortexbird.pusay.modelo.dto.PsyPaisDTO;

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
public interface IPsyPaisLogic {
    public List<PsyPais> getPsyPais() throws Exception;

    /**
         * Save an new PsyPais entity
         */
    public void savePsyPais(PsyPais entity) throws Exception;

    /**
         * Delete an existing PsyPais entity
         *
         */
    public void deletePsyPais(PsyPais entity) throws Exception;

    /**
        * Update an existing PsyPais entity
        *
        */
    public void updatePsyPais(PsyPais entity) throws Exception;

    /**
         * Load an existing PsyPais entity
         *
         */
    public PsyPais getPsyPais(Long paisCodigo) throws Exception;

    public List<PsyPais> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyPais> findPagePsyPais(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsyPais() throws Exception;

    public List<PsyPaisDTO> getDataPsyPais() throws Exception;
}
