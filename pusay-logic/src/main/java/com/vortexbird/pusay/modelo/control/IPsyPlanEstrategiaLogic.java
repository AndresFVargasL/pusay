package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPlanEstrategia;
import com.vortexbird.pusay.modelo.dto.PsyPlanEstrategiaDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyPlanEstrategiaLogic {
	
	public List<String> getEstrategias(PsyEmpresa empresa, Long placCodigo) throws Exception;
	
	public List<String> getEstrategiasSeleccionadas(PsyEmpresa empresa, Long placCodigo) throws Exception;
	
	public List<String> getEstrategiasRestantes(PsyEmpresa empresa, Long placCodigo, List<String> estrategiasSeleccionadas) throws Exception;
	
    public List<PsyPlanEstrategia> getPsyPlanEstrategia()
        throws Exception;

    /**
         * Save an new PsyPlanEstrategia entity
         */
    public void savePsyPlanEstrategia(PsyPlanEstrategia entity, List<String> estrategiasTarget)
        throws Exception;

    /**
         * Delete an existing PsyPlanEstrategia entity
         *
         */
    public void deletePsyPlanEstrategia(PsyPlanEstrategia entity)
        throws Exception;

    /**
        * Update an existing PsyPlanEstrategia entity
        *
        */
    public void updatePsyPlanEstrategia(PsyPlanEstrategia entity, List<String> estrategiasTarget)
        throws Exception;

    /**
         * Load an existing PsyPlanEstrategia entity
         *
         */
    public PsyPlanEstrategia getPsyPlanEstrategia(Long plesCodigo)
        throws Exception;

    public List<PsyPlanEstrategia> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyPlanEstrategia> findPagePsyPlanEstrategia(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyPlanEstrategia() throws Exception;

    public List<PsyPlanEstrategiaDTO> getDataPsyPlanEstrategia()
        throws Exception;
    
    public List<String> getEstrategiasBtnNew(PsyEmpresa empresa) throws Exception;
}
