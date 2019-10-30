package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyProvincia;
import com.vortexbird.pusay.modelo.dto.PsyProvinciaDTO;

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
public interface IPsyProvinciaLogic {
    public List<PsyProvincia> getPsyProvincia() throws Exception;

    /**
         * Save an new PsyProvincia entity
         */
    public void savePsyProvincia(PsyProvincia entity) throws Exception;

    /**
         * Delete an existing PsyProvincia entity
         *
         */
    public void deletePsyProvincia(PsyProvincia entity)
        throws Exception;

    /**
        * Update an existing PsyProvincia entity
        *
        */
    public void updatePsyProvincia(PsyProvincia entity)
        throws Exception;

    /**
         * Load an existing PsyProvincia entity
         *
         */
    public PsyProvincia getPsyProvincia(Long provCodigo)
        throws Exception;

    public List<PsyProvincia> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyProvincia> findPagePsyProvincia(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsyProvincia() throws Exception;

    public List<PsyProvinciaDTO> getDataPsyProvincia()
        throws Exception;
}
