package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;
import com.vortexbird.pusay.modelo.PsyPlanEstrategicoAmbiental;
import com.vortexbird.pusay.modelo.dto.PsyPlanEstrategicoAmbientalDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IPsyPlanEstrategicoAmbientalLogic {
    public List<PsyPlanEstrategicoAmbiental> getPsyPlanEstrategicoAmbiental()
        throws Exception;

    /**
         * Save an new PsyPlanEstrategicoAmbiental entity
         */
    public void savePsyPlanEstrategicoAmbiental(
        PsyPlanEstrategicoAmbiental entity) throws Exception;

    /**
         * Delete an existing PsyPlanEstrategicoAmbiental entity
         *
         */
    public void deletePsyPlanEstrategicoAmbiental(
        PsyPlanEstrategicoAmbiental entity) throws Exception;

    /**
        * Update an existing PsyPlanEstrategicoAmbiental entity
        *
        */
    public void updatePsyPlanEstrategicoAmbiental(
        PsyPlanEstrategicoAmbiental entity) throws Exception;

    /**
         * Load an existing PsyPlanEstrategicoAmbiental entity
         *
         */
    public PsyPlanEstrategicoAmbiental getPsyPlanEstrategicoAmbiental(
        Long codigo) throws Exception;

    public List<PsyPlanEstrategicoAmbiental> findByCriteria(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<PsyPlanEstrategicoAmbiental> findPagePsyPlanEstrategicoAmbiental(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyPlanEstrategicoAmbiental()
        throws Exception;

    public List<PsyPlanEstrategicoAmbientalDTO> getDataPsyPlanEstrategicoAmbiental(PsyEmpresa empresa)
        throws Exception;
    
    public PsyPlanEstrategico getPlanEstrategicoActivoByPEA(PsyEmpresa empresa) throws Exception;
    
    public PsyPlanEstrategico getPlanEstrategicoByPEA(PsyEmpresa empresa, Long codigo) throws Exception;
    
    public PsyPlanEstrategico getPlanEstrategicoByPEA(PsyEmpresa empresa, String nombre) throws Exception;
    
    public List<PsyPlanEstrategicoAmbiental> getPEA(PsyEmpresa empresa) throws Exception;
}
