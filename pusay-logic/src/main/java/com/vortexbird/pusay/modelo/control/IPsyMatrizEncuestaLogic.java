package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyMatrizEncuesta;
import com.vortexbird.pusay.modelo.dto.PsyMatrizEncuestaDTO;
import com.vortexbird.pusay.modelo.dto.RespuestaEncuestasDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyMatrizEncuestaLogic {
    public List<PsyMatrizEncuesta> getPsyMatrizEncuesta()
        throws Exception;

    /**
         * Save an new PsyMatrizEncuesta entity
         */
    public void savePsyMatrizEncuesta(PsyMatrizEncuesta entity)
        throws Exception;

    /**
         * Delete an existing PsyMatrizEncuesta entity
         *
         */
    public void deletePsyMatrizEncuesta(PsyMatrizEncuesta entity)
        throws Exception;

    /**
        * Update an existing PsyMatrizEncuesta entity
        *
        */
    public void updatePsyMatrizEncuesta(PsyMatrizEncuesta entity)
        throws Exception;

    /**
         * Load an existing PsyMatrizEncuesta entity
         *
         */
    public PsyMatrizEncuesta getPsyMatrizEncuesta(Long maenCodigo)
        throws Exception;

    public List<PsyMatrizEncuesta> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyMatrizEncuesta> findPagePsyMatrizEncuesta(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyMatrizEncuesta() throws Exception;

    public List<PsyMatrizEncuestaDTO> getDataPsyMatrizEncuesta()
        throws Exception;
    
    public List<RespuestaEncuestasDTO> consultarResultadoEncuestas(PsyEmpresa empresa) throws Exception;
}
