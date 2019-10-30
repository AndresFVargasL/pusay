package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyObjetivoEstrategico;
import com.vortexbird.pusay.modelo.dto.PsyObjetivoEstrategicoDTO;

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
public interface IPsyObjetivoEstrategicoLogic {
    public List<PsyObjetivoEstrategico> getPsyObjetivoEstrategico()
        throws Exception;

    /**
         * Save an new PsyObjetivoEstrategico entity
         */
    public void savePsyObjetivoEstrategico(PsyObjetivoEstrategico entity)
        throws Exception;

    /**
         * Delete an existing PsyObjetivoEstrategico entity
         *
         */
    public void deletePsyObjetivoEstrategico(PsyObjetivoEstrategico entity)
        throws Exception;

    /**
        * Update an existing PsyObjetivoEstrategico entity
        *
        */
    public void updatePsyObjetivoEstrategico(PsyObjetivoEstrategico entity)
        throws Exception;

    /**
         * Load an existing PsyObjetivoEstrategico entity
         *
         */
    public PsyObjetivoEstrategico getPsyObjetivoEstrategico(Long obesCodigo)
        throws Exception;

    public List<PsyObjetivoEstrategico> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyObjetivoEstrategico> findPagePsyObjetivoEstrategico(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyObjetivoEstrategico()
        throws Exception;

    public List<PsyObjetivoEstrategicoDTO> getDataPsyObjetivoEstrategico()
        throws Exception;
}
