package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyMatrizCorrelacion;
import com.vortexbird.pusay.modelo.dto.PsyMatrizCorrelacionDTO;

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
public interface IPsyMatrizCorrelacionLogic {
    public List<PsyMatrizCorrelacion> getPsyMatrizCorrelacion()
        throws Exception;

    /**
         * Save an new PsyMatrizCorrelacion entity
         */
    public void savePsyMatrizCorrelacion(PsyMatrizCorrelacion entity)
        throws Exception;

    /**
         * Delete an existing PsyMatrizCorrelacion entity
         *
         */
    public void deletePsyMatrizCorrelacion(PsyMatrizCorrelacion entity)
        throws Exception;

    /**
        * Update an existing PsyMatrizCorrelacion entity
        *
        */
    public void updatePsyMatrizCorrelacion(PsyMatrizCorrelacion entity)
        throws Exception;

    /**
         * Load an existing PsyMatrizCorrelacion entity
         *
         */
    public PsyMatrizCorrelacion getPsyMatrizCorrelacion(Long macoCodigo)
        throws Exception;

    public List<PsyMatrizCorrelacion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyMatrizCorrelacion> findPagePsyMatrizCorrelacion(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyMatrizCorrelacion() throws Exception;

    public List<PsyMatrizCorrelacionDTO> getDataPsyMatrizCorrelacion()
        throws Exception;
}
