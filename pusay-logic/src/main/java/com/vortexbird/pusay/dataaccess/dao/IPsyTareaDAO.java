package com.vortexbird.pusay.dataaccess.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.vortexbird.pusay.dataaccess.api.Dao;
import com.vortexbird.pusay.modelo.PsyTarea;


/**
* Interface for   PsyTareaDAO.
*
*/
public interface IPsyTareaDAO extends Dao<PsyTarea, Long> {
	public Long consultarMaxSemana(Long placCodigo)throws Exception;
	public Long consultarCountSemanaFinPlaneada(Long placCodigo,Long semanaFinPlaneada)throws Exception;
	public Long numeroTotalDeTareasPlanDeAccion(Long placCodigo)throws Exception;
	public List<Date> maxMinSemana(Long placCodigo,Long semanaFinPlaneada)throws Exception;
	public Long consultarMinimaSemanaFinPlaneada (Long placCodigo)throws Exception;
}
