package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPersona;
import com.vortexbird.pusay.modelo.dto.PsyPersonaDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyPersonaLogic {
    public List<PsyPersona> getPsyPersona() throws Exception;

    /**
         * Save an new PsyPersona entity
         */
    public void savePsyPersona(PsyPersona entity) throws Exception;

    /**
         * Delete an existing PsyPersona entity
         *
         */
    public void deletePsyPersona(PsyPersona entity) throws Exception;

    /**
        * Update an existing PsyPersona entity
        *
        */
    public void updatePsyPersona(PsyPersona entity) throws Exception;

    /**
         * Load an existing PsyPersona entity
         *
         */
    public PsyPersona getPsyPersona(Long persCodigo) throws Exception;

    public List<PsyPersona> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyPersona> findPagePsyPersona(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsyPersona() throws Exception;

    public List<PsyPersonaDTO> getDataPsyPersona(PsyEmpresa empresa) throws Exception;
    
    public void saveUsuarioConsulta(PsyPersona entity, String clave, String confimaClave, String nombreUConsulta, String apellidoUConsulta) throws Exception;
    
    public void updateUsuarioConsulta(PsyPersona entity, String clave, String confimaClave, String nombreUConsulta, String apellidoUConsulta) throws Exception;
    
    public void updatePerfilUsuario(PsyPersona entity,String claveActual, String clave, String confimaClave, String nombreUConsulta, String apellidoUConsulta) throws Exception;
}
