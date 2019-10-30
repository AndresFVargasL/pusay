package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyTipoItemPresupuesto;
import com.vortexbird.pusay.modelo.dto.PsyTipoItemPresupuestoDTO;

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
public interface IPsyTipoItemPresupuestoLogic {
    public List<PsyTipoItemPresupuesto> getPsyTipoItemPresupuesto()
        throws Exception;

    /**
         * Save an new PsyTipoItemPresupuesto entity
         */
    public void savePsyTipoItemPresupuesto(PsyTipoItemPresupuesto entity)
        throws Exception;

    /**
         * Delete an existing PsyTipoItemPresupuesto entity
         *
         */
    public void deletePsyTipoItemPresupuesto(PsyTipoItemPresupuesto entity)
        throws Exception;

    /**
        * Update an existing PsyTipoItemPresupuesto entity
        *
        */
    public void updatePsyTipoItemPresupuesto(PsyTipoItemPresupuesto entity)
        throws Exception;

    /**
         * Load an existing PsyTipoItemPresupuesto entity
         *
         */
    public PsyTipoItemPresupuesto getPsyTipoItemPresupuesto(Long tiipCodigo)
        throws Exception;

    public List<PsyTipoItemPresupuesto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyTipoItemPresupuesto> findPagePsyTipoItemPresupuesto(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyTipoItemPresupuesto()
        throws Exception;

    public List<PsyTipoItemPresupuestoDTO> getDataPsyTipoItemPresupuesto()
        throws Exception;
}
