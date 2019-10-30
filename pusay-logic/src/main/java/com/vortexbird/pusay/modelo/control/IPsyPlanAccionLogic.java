package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPlanAccion;
import com.vortexbird.pusay.modelo.PsyPlanEstrategia;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;
import com.vortexbird.pusay.modelo.dto.PsyPlanAccionDTO;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.transaction.annotation.Transactional;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyPlanAccionLogic {
    
	public PsyPlanAccion getPsyPlanAccionByName(String nombre) throws Exception;
	
	public List<PsyPlanAccion> getPsyPlanAccion() throws Exception;
    

    /**
         * Save an new PsyPlanAccion entity
         */
    public void savePsyPlanAccion(PsyPlanAccion entity)
        throws Exception;

    /**
         * Delete an existing PsyPlanAccion entity
         *
         */
    public void deletePsyPlanAccion(PsyPlanAccion entity)
        throws Exception;

    /**
        * Update an existing PsyPlanAccion entity
        *
        */
    public void updatePsyPlanAccion(PsyPlanAccion entity)
        throws Exception;

    /**
         * Load an existing PsyPlanAccion entity
         *
         */
    public PsyPlanAccion getPsyPlanAccion(Long placCodigo)
        throws Exception;

    public List<PsyPlanAccion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyPlanAccion> findPagePsyPlanAccion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsyPlanAccion() throws Exception;

    public List<PsyPlanAccionDTO> getDataPsyPlanAccion(PsyEmpresa empresa)
        throws Exception;
    
    public void generarPlanAccionDifinitivo(Long placCodigo)throws Exception;
    
    public boolean verificarMapaEstrategico(PsyEmpresa empresa) throws Exception;
    
    public boolean verificarPlanEstrategico(PsyEmpresa empresa) throws Exception;
    
    public void savePsyPlanAccionPsyPlanEstrategia(PsyPlanAccion entity, List<String> estrategiasTarget, PsyEmpresa empresa)
            throws Exception;
    
    public String consultaPlanEstrategicoBtnNew(PsyEmpresa empresa) throws Exception;
    
    public String consultaPlanEstrategico(PsyEmpresa empresa, Long placCodigo) throws Exception;
    
    public void updatePsyPlanAccionPlanEstrategia(PsyPlanAccion entity, List<String> estrategiasTarget, PsyEmpresa empresa)
            throws Exception;
    
    public List<PsyPlanAccion> consultarPlanesAccion(PsyEmpresa empresa, PsyPlanEstrategico planEstrategico , String estadoIniciado, String estadoPresupuestado)
            throws Exception;

}
