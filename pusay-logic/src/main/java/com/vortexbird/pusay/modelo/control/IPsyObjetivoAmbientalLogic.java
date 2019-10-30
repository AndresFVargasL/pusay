package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyDetalleObjetivoPlan;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyObjetivoAmbiental;
import com.vortexbird.pusay.modelo.dto.PsyObjetivoAmbientalDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IPsyObjetivoAmbientalLogic {
    public List<PsyObjetivoAmbiental> getPsyObjetivoAmbiental()
        throws Exception;

    /**
         * Save an new PsyObjetivoAmbiental entity
         */
    public void savePsyObjetivoAmbiental(PsyObjetivoAmbiental entity)
        throws Exception;

    /**
         * Delete an existing PsyObjetivoAmbiental entity
         *
         */
    public void deletePsyObjetivoAmbiental(PsyObjetivoAmbiental entity)
        throws Exception;

    /**
        * Update an existing PsyObjetivoAmbiental entity
        *
        */
    public void updatePsyObjetivoAmbiental(PsyObjetivoAmbiental entity)
        throws Exception;

    /**
         * Load an existing PsyObjetivoAmbiental entity
         *
         */
    public PsyObjetivoAmbiental getPsyObjetivoAmbiental(Long codigo)
        throws Exception;

    public List<PsyObjetivoAmbiental> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyObjetivoAmbiental> findPagePsyObjetivoAmbiental(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyObjetivoAmbiental() throws Exception;

    public List<PsyObjetivoAmbientalDTO> getDataPsyObjetivoAmbiental()
        throws Exception;
    
    public List<PsyObjetivoAmbientalDTO> getDataPsyObjetivoAmbientalEvaluado(PsyEmpresa empresa)
            throws Exception;
    public boolean validarGrupoRepetido(List<PsyDetalleObjetivoPlan> lstObjetivoCorporativo) throws Exception;
    
    
}
