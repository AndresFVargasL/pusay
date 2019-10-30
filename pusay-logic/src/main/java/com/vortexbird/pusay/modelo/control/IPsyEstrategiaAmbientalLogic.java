package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyEstrategiaAmbiental;
import com.vortexbird.pusay.modelo.dto.PsyEstrategiaAmbientalDTO;

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
public interface IPsyEstrategiaAmbientalLogic {
    public List<PsyEstrategiaAmbiental> getPsyEstrategiaAmbiental()
        throws Exception;

    /**
         * Save an new PsyEstrategiaAmbiental entity
         */
    public void savePsyEstrategiaAmbiental(PsyEstrategiaAmbiental entity)
        throws Exception;

    /**
         * Delete an existing PsyEstrategiaAmbiental entity
         *
         */
    public void deletePsyEstrategiaAmbiental(PsyEstrategiaAmbiental entity)
        throws Exception;

    /**
        * Update an existing PsyEstrategiaAmbiental entity
        *
        */
    public void updatePsyEstrategiaAmbiental(PsyEstrategiaAmbiental entity)
        throws Exception;

    /**
         * Load an existing PsyEstrategiaAmbiental entity
         *
         */
    public PsyEstrategiaAmbiental getPsyEstrategiaAmbiental(Long esamCodigo)
        throws Exception;

    public List<PsyEstrategiaAmbiental> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyEstrategiaAmbiental> findPagePsyEstrategiaAmbiental(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyEstrategiaAmbiental()
        throws Exception;

    public List<PsyEstrategiaAmbientalDTO> getDataPsyEstrategiaAmbiental()
        throws Exception;
}
