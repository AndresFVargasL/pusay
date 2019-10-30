package com.vortexbird.pusay.dataaccess.dao;

import java.util.List;

import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyPlanAccion;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;


/**
* Interface for   PsyPlanAccionDAO.
*
*/
public interface IPsyPlanAccionDAO extends Dao<PsyPlanAccion, Long> {
	
	public List<PsyMapaEstrategico> verificarMapaEstrategico(PsyEmpresa empresa);
	
	public List<PsyPlanEstrategico> verificarPlanEstrategico(PsyEmpresa empresa);
	
	public String consultaPlanEstrategicoBtnNew(PsyEmpresa empresa) throws Exception;
	
    public String consultaPlanEstrategico(PsyEmpresa empresa, Long placCodigo) throws Exception;
    
    public List<PsyPlanAccion> findPlanesAccionByEmpresa(PsyEmpresa empresa) throws Exception;
    
    public List<PsyPlanAccion> findPlanesAccionByEmpresaByPestActivo(PsyEmpresa empresa) throws Exception;
    
    public List<PsyPlanAccion> consultarPlanesAccion(PsyEmpresa empresa, PsyPlanEstrategico planEstrategico , String estadoIniciado, String estadoPresupuestado)
            throws Exception;
    public List<PsyPlanAccion> findPlanesAccionByEmpresaByPestActivoByEstadoPlan(PsyEmpresa empresa) throws Exception;
}
