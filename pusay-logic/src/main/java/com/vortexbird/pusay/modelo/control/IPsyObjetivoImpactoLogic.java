package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyObjetivoImpacto;
import com.vortexbird.pusay.modelo.dto.PsyObjetivoImpactoDTO;

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
public interface IPsyObjetivoImpactoLogic {
    public List<PsyObjetivoImpacto> getPsyObjetivoImpacto()
        throws Exception;

    /**
         * Save an new PsyObjetivoImpacto entity
         */
    public void savePsyObjetivoImpacto(PsyObjetivoImpacto entity)
        throws Exception;

    /**
         * Delete an existing PsyObjetivoImpacto entity
         *
         */
    public void deletePsyObjetivoImpacto(PsyObjetivoImpacto entity)
        throws Exception;

    /**
        * Update an existing PsyObjetivoImpacto entity
        *
        */
    public void updatePsyObjetivoImpacto(PsyObjetivoImpacto entity)
        throws Exception;

    /**
         * Load an existing PsyObjetivoImpacto entity
         *
         */
    public PsyObjetivoImpacto getPsyObjetivoImpacto(Long obimCodigo)
        throws Exception;

    public List<PsyObjetivoImpacto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyObjetivoImpacto> findPagePsyObjetivoImpacto(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyObjetivoImpacto() throws Exception;

    public List<PsyObjetivoImpactoDTO> getDataPsyObjetivoImpacto()
        throws Exception;
}
