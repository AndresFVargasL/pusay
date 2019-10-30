package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.dto.PsyEmpresaDTO;

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
public interface IPsyEmpresaLogic {
    public List<PsyEmpresa> getPsyEmpresa() throws Exception;

    /**
         * Save an new PsyEmpresa entity
         */
    public void savePsyEmpresa(PsyEmpresa entity) throws Exception;

    /**
         * Delete an existing PsyEmpresa entity
         *
         */
    public void deletePsyEmpresa(PsyEmpresa entity) throws Exception;

    /**
        * Update an existing PsyEmpresa entity
        *
        */
    public void updatePsyEmpresa(PsyEmpresa entity) throws Exception;

    /**
         * Load an existing PsyEmpresa entity
         *
         */
    public PsyEmpresa getPsyEmpresa(Long emprCodigo) throws Exception;

    public List<PsyEmpresa> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyEmpresa> findPagePsyEmpresa(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsyEmpresa() throws Exception;

    public List<PsyEmpresaDTO> getDataPsyEmpresa() throws Exception;
}
