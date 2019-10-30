package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyAsuntoAmbiental;
import com.vortexbird.pusay.modelo.dto.PsyAsuntoAmbientalDTO;
import javax.faces.model.SelectItem;
import java.math.BigDecimal;
import java.util.*;




/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyAsuntoAmbientalLogic {
    public List<PsyAsuntoAmbiental> getPsyAsuntoAmbiental()
        throws Exception;

    /**
         * Save an new PsyAsuntoAmbiental entity
         */
    public void savePsyAsuntoAmbiental(PsyAsuntoAmbiental entity)
        throws Exception;

    /**
         * Delete an existing PsyAsuntoAmbiental entity
         *
         */
    public void deletePsyAsuntoAmbiental(PsyAsuntoAmbiental entity)
        throws Exception;

    /**
        * Update an existing PsyAsuntoAmbiental entity
        *
        */
    public void updatePsyAsuntoAmbiental(PsyAsuntoAmbiental entity)
        throws Exception;

    /**
         * Load an existing PsyAsuntoAmbiental entity
         *
         */
    public PsyAsuntoAmbiental getPsyAsuntoAmbiental(Long asamCodigo)
        throws Exception;

    public List<PsyAsuntoAmbiental> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyAsuntoAmbiental> findPagePsyAsuntoAmbiental(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPsyAsuntoAmbiental() throws Exception;

    public List<PsyAsuntoAmbientalDTO> getDataPsyAsuntoAmbiental()
        throws Exception;
    public List<SelectItem> cargarOneMenuAsuntoAmbienta() throws Exception ;
}
