package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyCiudad;
import com.vortexbird.pusay.modelo.PsyPais;
import com.vortexbird.pusay.modelo.PsyProvincia;
import com.vortexbird.pusay.modelo.dto.PsyCiudadDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyCiudadLogic {
    public List<PsyCiudad> getPsyCiudad() throws Exception;

    /**
         * Save an new PsyCiudad entity
         */
    public void savePsyCiudad(PsyCiudad entity) throws Exception;

    /**
         * Delete an existing PsyCiudad entity
         *
         */
    public void deletePsyCiudad(PsyCiudad entity) throws Exception;

    /**
        * Update an existing PsyCiudad entity
        *
        */
    public void updatePsyCiudad(PsyCiudad entity) throws Exception;

    /**
         * Load an existing PsyCiudad entity
         *
         */
    public PsyCiudad getPsyCiudad(Long ciudCodigo) throws Exception;

    public List<PsyCiudad> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyCiudad> findPagePsyCiudad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsyCiudad() throws Exception;

    public List<PsyCiudadDTO> getDataPsyCiudad() throws Exception;
    
    public List<PsyProvincia> consultarProvinciasPorPais(Long paisCodigo) throws Exception;
    
    public PsyPais consultarPaisPorProvincias(Long provCodigo) throws Exception;
    
    public List<PsyCiudad> consultarCiudadesPorProvincia(Long provCodigo) throws Exception;
}
