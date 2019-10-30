package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyIpu;
import com.vortexbird.pusay.modelo.PsyPlanAccion;
import com.vortexbird.pusay.modelo.dto.PsyIpuDTO;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.transaction.annotation.Transactional;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IPsyIpuLogic {
    public List<PsyIpu> getPsyIpu() throws Exception;

    /**
         * Save an new PsyIpu entity
         */
    public void savePsyIpu(PsyIpu entity) throws Exception;

    /**
         * Delete an existing PsyIpu entity
         *
         */
    public void deletePsyIpu(PsyIpu entity) throws Exception;

    /**
        * Update an existing PsyIpu entity
        *
        */
    public void updatePsyIpu(PsyIpu entity) throws Exception;

    /**
         * Load an existing PsyIpu entity
         *
         */
    public PsyIpu getPsyIpu(Long ipuCodigo) throws Exception;

    public List<PsyIpu> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyIpu> findPagePsyIpu(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsyIpu() throws Exception;

    public List<PsyIpuDTO> getDataPsyIpu() throws Exception;
        
    public List<PsyIpuDTO> consultarIpu(PsyPlanAccion  planAccion, String tipoIpu)
        throws Exception ;
    
    public List<PsyIpuDTO> generarIpu(PsyPlanAccion  planAccion, String tipoIpu, Date fechaInforme, Long semanaInforme, List<PsyIpuDTO> lstIpu,String logrosSignificativos, String logrosNoAlcanzados, String causasDesviacion, String accionesPropuestas, boolean actualizar) throws Exception;

    public List<PsyIpuDTO> generarIpuPresupuesto(PsyPlanAccion  planAccion, String tipoIpu, Date fechaInforme, Long semanaInforme, List<PsyIpuDTO> lstIpu,
    		String logrosSignificativos, String logrosNoAlcanzados, String causasDesviacion, String accionesPropuestas) throws Exception;
    public List<PsyIpuDTO> consultarIpuHastaPeriodo(PsyPlanAccion  planAccion, String tipoIpu, String periodo)
            throws Exception;
    public List<PsyIpuDTO> generarIpuLogico(PsyPlanAccion  planAccion, String tipoIpu, Date fechaInforme, Long semanaInforme, List<PsyIpuDTO> lstIpu,
    		String logrosSignificativos, String logrosNoAlcanzados, String causasDesviacion, String accionesPropuestas, boolean actualizar) throws Exception;

    public List<PsyIpuDTO> generarIpuPresupuestoLogico(PsyPlanAccion  planAccion, String tipoIpu, Date fechaInforme, Long semanaInforme, List<PsyIpuDTO> lstIpu,
    		String logrosSignificativos, String logrosNoAlcanzados, String causasDesviacion, String accionesPropuestas) throws Exception;
}
