package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyMatrizEncuesta;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;
import com.vortexbird.pusay.modelo.dto.PsyPlanEstrategicoDTO;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
public interface IPsyPlanEstrategicoLogic {
	public List<PsyPlanEstrategico> getPsyPlanEstrategico() throws Exception;

	/**
	 * Save an new PsyPlanEstrategico entity
	 */
	public void savePsyPlanEstrategico(PsyPlanEstrategico entity, PsyEmpresa empresa)
			throws Exception;

	/**
	 * Delete an existing PsyPlanEstrategico entity
	 *
	 */
	public void deletePsyPlanEstrategico(PsyPlanEstrategico entity)
			throws Exception;

	/**
	 * Update an existing PsyPlanEstrategico entity
	 *
	 */
	public void updatePsyPlanEstrategico(PsyPlanEstrategico entity)
			throws Exception;

	/**
	 * Load an existing PsyPlanEstrategico entity
	 *
	 */
	public PsyPlanEstrategico getPsyPlanEstrategico(Long pestCodigo)
			throws Exception;

	public List<PsyPlanEstrategico> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<PsyPlanEstrategico> findPagePsyPlanEstrategico(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyPlanEstrategico() throws Exception;

	public List<PsyPlanEstrategicoDTO> getDataPsyPlanEstrategico(PsyEmpresa empresa)
			throws Exception;

	public List<PsyPlanEstrategico> consultarPlanEstrategicoEmpresa(
			PsyEmpresa empresa, String Estado) throws Exception;
	
	public List<PsyMatrizEncuesta> consultaAsociacionDeCuestionarios(PsyEmpresa empresa) throws Exception;
}
