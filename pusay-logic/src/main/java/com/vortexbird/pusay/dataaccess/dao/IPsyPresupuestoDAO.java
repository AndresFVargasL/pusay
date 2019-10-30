package com.vortexbird.pusay.dataaccess.dao;

import java.util.List;

import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPlanAccion;
import com.vortexbird.pusay.modelo.PsyPresupuesto;


/**
* Interface for   PsyPresupuestoDAO.
*
*/
public interface IPsyPresupuestoDAO extends Dao<PsyPresupuesto, Long> {
	
	public List<PsyPresupuesto> findPresupuestoByEmpresaByPestActivo(PsyEmpresa empresa) throws Exception;
	
	public List<PsyPresupuesto> findPresupuestoByEmpresaByPestActivoByPresActivo(PsyEmpresa empresa) throws Exception;
	
	public List<PsyPresupuesto> consultarPresupuestos(PsyEmpresa empresa) throws Exception;
}
