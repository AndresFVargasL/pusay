package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyResponsable;
import com.vortexbird.pusay.modelo.dto.PsyResponsableDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyResponsableLogic {
	
	public List<PsyResponsable> getPsyResponsableByEmpresa (Long emprCodigo)
    		throws Exception;
	
	public boolean validateEmailAddress(String email) throws Exception;
	
    public List<PsyResponsable> getPsyResponsable() throws Exception;

    /**
         * Save an new PsyResponsable entity
         */
    public void savePsyResponsable(PsyResponsable entity)
        throws Exception;

    /**
         * Delete an existing PsyResponsable entity
         *
         */
    public void deletePsyResponsable(PsyResponsable entity)
        throws Exception;

    /**
        * Update an existing PsyResponsable entity
        *
        */
    public void updatePsyResponsable(PsyResponsable entity)
        throws Exception;

    /**
         * Load an existing PsyResponsable entity
         *
         */
    public PsyResponsable getPsyResponsable(Long respCodigo)
        throws Exception;

    public List<PsyResponsable> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyResponsable> findPagePsyResponsable(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsyResponsable() throws Exception;

    public List<PsyResponsableDTO> getDataPsyResponsable(PsyEmpresa empresa)
        throws Exception;
}
