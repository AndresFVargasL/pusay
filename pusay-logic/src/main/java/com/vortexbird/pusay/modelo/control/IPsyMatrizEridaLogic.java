package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyAsuntoAmbiental;
import com.vortexbird.pusay.modelo.PsyMatrizErida;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;
import com.vortexbird.pusay.modelo.dto.PsyMatrizEridaDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyMatrizEridaLogic {
    public List<PsyMatrizErida> getPsyMatrizErida() throws Exception;

    /**
         * Save an new PsyMatrizErida entity
         */
    public void savePsyMatrizErida(PsyMatrizErida entity)
        throws Exception;

    /**
         * Delete an existing PsyMatrizErida entity
         *
         */
    public void deletePsyMatrizErida(PsyMatrizErida entity)
        throws Exception;

    /**
        * Update an existing PsyMatrizErida entity
        *
        */
    public void updatePsyMatrizErida(PsyMatrizErida entity)
        throws Exception;

    /**
         * Load an existing PsyMatrizErida entity
         *
         */
    public PsyMatrizErida getPsyMatrizErida(Long maerCodigo)
        throws Exception;

    public List<PsyMatrizErida> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyMatrizErida> findPagePsyMatrizErida(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsyMatrizErida() throws Exception;

    public List<PsyMatrizEridaDTO> getDataPsyMatrizErida()
        throws Exception;
    public List<PsyMatrizEridaDTO> consultarExisteEridaPlanEstrategico(Long pestCodigo) throws Exception ;
    public List<PsyAsuntoAmbiental>  terminarErida(Long mearCodigo) throws Exception;
    public PsyMatrizErida consultarMatrizErida(PsyPlanEstrategico planEstrategico) throws Exception;
}
