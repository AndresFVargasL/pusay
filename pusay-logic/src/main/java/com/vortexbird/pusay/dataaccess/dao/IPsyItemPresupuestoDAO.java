package com.vortexbird.pusay.dataaccess.dao;

import java.util.Date;
import java.util.List;

import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyItemPresupuesto;


/**
* Interface for   PsyItemPresupuestoDAO.
*
*/
public interface IPsyItemPresupuestoDAO extends Dao<PsyItemPresupuesto, Long> {
	public Long consultarMaxSemana(Long presCodigo)throws Exception;
	public Double consultarSumSemana(Long presCodigo,Long semana)throws Exception;
	public List<Date> maxMinSemana(Long presCodigo, Long semana);
	public Long consultarMinSemana(Long presCodigo)throws Exception;
}
