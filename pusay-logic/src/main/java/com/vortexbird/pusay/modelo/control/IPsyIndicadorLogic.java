package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyEvaluacionPeaIndicador;
import com.vortexbird.pusay.modelo.PsyEvaluacionPeaObjetivo;
import com.vortexbird.pusay.modelo.PsyIndicador;
import com.vortexbird.pusay.modelo.PsyPlanEstrategicoAmbiental;
import com.vortexbird.pusay.modelo.PsySubtema;
import com.vortexbird.pusay.modelo.dto.PsyEvaluarIndicadoresDTO;
import com.vortexbird.pusay.modelo.dto.PsyIndicadorDTO;
import com.vortexbird.pusay.modelo.dto.PsyIndicadorGestionDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IPsyIndicadorLogic {
    public List<PsyIndicador> getPsyIndicador() throws Exception;

    /**
         * Save an new PsyIndicador entity
         */
    public void savePsyIndicador(PsyIndicador entity) throws Exception;

    /**
         * Delete an existing PsyIndicador entity
         *
         */
    public void deletePsyIndicador(PsyIndicador entity)
        throws Exception;

    /**
        * Update an existing PsyIndicador entity
        *
        */
    public void updatePsyIndicador(PsyIndicador entity)
        throws Exception;

    /**
         * Load an existing PsyIndicador entity
         *
         */
    public PsyIndicador getPsyIndicador(Long codigo) throws Exception;

    public List<PsyIndicador> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyIndicador> findPagePsyIndicador(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsyIndicador() throws Exception;

    public List<PsyEvaluarIndicadoresDTO> getDataPsyIndicador(PsyEmpresa empresa, Long imamCodigo, PsyPlanEstrategicoAmbiental planEstrategicoAmbiental)
        throws Exception;
    
    public List<PsyIndicadorGestionDTO> getDataPsyIndicadorGestion() throws Exception;
    
    public List<PsySubtema> consultarSubTemasPorTema(Long temaCodigo) throws Exception;
    
    public void evaluarIndicador(PsyEvaluacionPeaIndicador evaIndicador, PsyEvaluacionPeaObjetivo evaObjetivo) throws Exception;
    
    public List<PsyEvaluarIndicadoresDTO> getDataPsyIndicadorObam(PsyEmpresa empresa, Long obamCodigo, PsyPlanEstrategicoAmbiental planEstrategicoAmbiental)
            throws Exception;
    
}
