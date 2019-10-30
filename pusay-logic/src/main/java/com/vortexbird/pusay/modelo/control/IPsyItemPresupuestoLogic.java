package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyItemPresupuesto;
import com.vortexbird.pusay.modelo.dto.PsyItemPresupuestoDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyItemPresupuestoLogic {
    public List<PsyItemPresupuesto> getPsyItemPresupuesto()
        throws Exception;

    /**
         * Save an new PsyItemPresupuesto entity
         */
    public void savePsyItemPresupuesto(PsyItemPresupuesto entity)
        throws Exception;

    /**
         * Delete an existing PsyItemPresupuesto entity
         *
         */
    public void deletePsyItemPresupuesto(PsyItemPresupuesto entity)
        throws Exception;

    /**
        * Update an existing PsyItemPresupuesto entity
        *
        */
    public void updatePsyItemPresupuesto(PsyItemPresupuesto entity)
        throws Exception;

    /**
         * Load an existing PsyItemPresupuesto entity
         *
         */
    public PsyItemPresupuesto getPsyItemPresupuesto(Long ipreCodigo)
        throws Exception;

    public List<PsyItemPresupuesto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyItemPresupuesto> findPagePsyItemPresupuesto(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyItemPresupuesto() throws Exception;

    public List<PsyItemPresupuestoDTO> getDataPsyItemPresupuesto()
        throws Exception;
    
    public List<PsyItemPresupuestoDTO> getDataPsyItemPresupuestoByPresCodigo(Long presCodigo) throws Exception;
    
}
