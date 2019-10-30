package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPresupuesto;
import com.vortexbird.pusay.modelo.dto.PsyPresupuestoDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyPresupuestoLogic {
    public List<PsyPresupuesto> getPsyPresupuesto() throws Exception;

    /**
         * Save an new PsyPresupuesto entity
         */
    public void savePsyPresupuesto(PsyPresupuesto entity)
        throws Exception;

    /**
         * Delete an existing PsyPresupuesto entity
         *
         */
    public void deletePsyPresupuesto(PsyPresupuesto entity)
        throws Exception;

    /**
        * Update an existing PsyPresupuesto entity
        *
        */
    public void updatePsyPresupuesto(PsyPresupuesto entity)
        throws Exception;

    /**
         * Load an existing PsyPresupuesto entity
         *
         */
    public PsyPresupuesto getPsyPresupuesto(Long presCodigo)
        throws Exception;

    public List<PsyPresupuesto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyPresupuesto> findPagePsyPresupuesto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsyPresupuesto() throws Exception;

    public List<PsyPresupuestoDTO> getDataPsyPresupuesto(PsyEmpresa empresa)
        throws Exception;
    
    public void generarPresupuestoDefinitivo(Long presCodigo,Long placCodigo) throws Exception;
    
    public List<PsyPresupuestoDTO> getDataPsyPresupuestoOnItem(PsyEmpresa empresa) throws Exception;
}
