package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;
import com.vortexbird.pusay.modelo.dto.PsyMapaEstrategicoDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyMapaEstrategicoLogic {
    public List<PsyMapaEstrategico> getPsyMapaEstrategico()
        throws Exception;

    /**
         * Save an new PsyMapaEstrategico entity
         */
    public void savePsyMapaEstrategico(PsyMapaEstrategico entity)
        throws Exception;

    /**
         * Delete an existing PsyMapaEstrategico entity
         *
         */
    public void deletePsyMapaEstrategico(PsyMapaEstrategico entity)
        throws Exception;

    /**
        * Update an existing PsyMapaEstrategico entity
        *
        */
    public void updatePsyMapaEstrategico(PsyMapaEstrategico entity)
        throws Exception;

    /**
         * Load an existing PsyMapaEstrategico entity
         *
         */
    public PsyMapaEstrategico getPsyMapaEstrategico(Long maesCodigo)
        throws Exception;

    public List<PsyMapaEstrategico> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyMapaEstrategico> findPagePsyMapaEstrategico(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyMapaEstrategico() throws Exception;

    public List<PsyMapaEstrategicoDTO> getDataPsyMapaEstrategico()
        throws Exception;
    public PsyMapaEstrategico consultarMapaEstrategico(PsyPlanEstrategico planEstrategico, String estado)
            throws Exception ;
}
