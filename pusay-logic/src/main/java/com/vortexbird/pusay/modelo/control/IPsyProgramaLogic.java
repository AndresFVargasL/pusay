package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPrograma;
import com.vortexbird.pusay.modelo.dto.PsyDetalleEstrategiasDTO;
import com.vortexbird.pusay.modelo.dto.PsyProgramaDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyProgramaLogic {
    public List<PsyPrograma> getPsyPrograma() throws Exception;

    /**
         * Save an new PsyPrograma entity
         */
    public void savePsyPrograma(PsyPrograma entity, String dmaeCodigo, PsyEmpresa empresa) throws Exception;

    /**
         * Delete an existing PsyPrograma entity
         *
         */
    public void deletePsyPrograma(PsyPrograma entity) throws Exception;

    /**
        * Update an existing PsyPrograma entity
        *
        */
    public void updatePsyPrograma(PsyPrograma entity, String dmaeCodigo, PsyEmpresa empresa) throws Exception;

    /**
         * Load an existing PsyPrograma entity
         *
         */
    public PsyPrograma getPsyPrograma(Long progCodigo)
        throws Exception;

    public List<PsyPrograma> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyPrograma> findPagePsyPrograma(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsyPrograma() throws Exception;

    public List<PsyProgramaDTO> getDataPsyPrograma(PsyEmpresa empresa) throws Exception;
    
    public List<PsyDetalleEstrategiasDTO> consultaEstrategiasParaPrograma(PsyEmpresa empresa)
            throws Exception;
    
    public List<PsyDetalleEstrategiasDTO> consultaEstrategiasParaProgramaDepurada(PsyEmpresa empresa)
            throws Exception;
}
