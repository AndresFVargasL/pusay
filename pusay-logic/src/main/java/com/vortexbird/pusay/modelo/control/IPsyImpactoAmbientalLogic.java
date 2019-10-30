package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyImpactoAmbiental;
import com.vortexbird.pusay.modelo.dto.PsyImpactoAmbientalDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyImpactoAmbientalLogic {
    public List<PsyImpactoAmbiental> getPsyImpactoAmbiental()
        throws Exception;

    /**
         * Save an new PsyImpactoAmbiental entity
         */
    public void savePsyImpactoAmbiental(PsyImpactoAmbiental entity)
        throws Exception;

    /**
         * Delete an existing PsyImpactoAmbiental entity
         *
         */
    public void deletePsyImpactoAmbiental(PsyImpactoAmbiental entity)
        throws Exception;

    /**
        * Update an existing PsyImpactoAmbiental entity
        *
        */
    public void updatePsyImpactoAmbiental(PsyImpactoAmbiental entity)
        throws Exception;

    /**
         * Load an existing PsyImpactoAmbiental entity
         *
         */
    public PsyImpactoAmbiental getPsyImpactoAmbiental(Long imamCodigo)
        throws Exception;

    public List<PsyImpactoAmbiental> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyImpactoAmbiental> findPagePsyImpactoAmbiental(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyImpactoAmbiental() throws Exception;

    public List<PsyImpactoAmbientalDTO> getDataPsyImpactoAmbiental(PsyEmpresa empresa)
        throws Exception;
    
    public Long countIndicadoresPorImpacto(Long codigo) throws Exception;
    
    public Long countSubTemasPorImpacto(Long codigo) throws Exception;
    
    public Long countTemasPorImpacto(Long codigo) throws Exception;
    
    public List<PsyImpactoAmbiental> consultaImpactosAmbientalesSeleccionados(PsyEmpresa empresa) throws Exception;
    
    public List<PsyImpactoAmbientalDTO> getDataPsyImpactoAmbientalGestion()
            throws Exception;
}
