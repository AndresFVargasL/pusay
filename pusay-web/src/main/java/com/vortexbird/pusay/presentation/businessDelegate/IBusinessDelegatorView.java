package com.vortexbird.pusay.presentation.businessDelegate;

import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import com.vortexbird.pusay.modelo.PsyAsuntoAmbiental;
import com.vortexbird.pusay.modelo.PsyCiudad;
import com.vortexbird.pusay.modelo.PsyDetalleErida;
import com.vortexbird.pusay.modelo.PsyDetalleMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyDetalleObjetivoPlan;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyEstrategiaAmbiental;
import com.vortexbird.pusay.modelo.PsyEvaluacionPeaIndicador;
import com.vortexbird.pusay.modelo.PsyEvaluacionPeaObjetivo;
import com.vortexbird.pusay.modelo.PsyImpactoAmbiental;
import com.vortexbird.pusay.modelo.PsyImpactoObjetivo;
import com.vortexbird.pusay.modelo.PsyIndicador;
import com.vortexbird.pusay.modelo.PsyIpu;
import com.vortexbird.pusay.modelo.PsyItemPresupuesto;
import com.vortexbird.pusay.modelo.PsyMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyMatrizCorrelacion;
import com.vortexbird.pusay.modelo.PsyMatrizEncuesta;
import com.vortexbird.pusay.modelo.PsyMatrizErida;
import com.vortexbird.pusay.modelo.PsyMoneda;
import com.vortexbird.pusay.modelo.PsyObjetivoAmbiental;
import com.vortexbird.pusay.modelo.PsyObjetivoEstrategico;
import com.vortexbird.pusay.modelo.PsyObjetivoImpacto;
import com.vortexbird.pusay.modelo.PsyObjetivoPlan;
import com.vortexbird.pusay.modelo.PsyPais;
import com.vortexbird.pusay.modelo.PsyParametro;
import com.vortexbird.pusay.modelo.PsyPersona;
import com.vortexbird.pusay.modelo.PsyPlanAccion;
import com.vortexbird.pusay.modelo.PsyPlanEstrategia;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;
import com.vortexbird.pusay.modelo.PsyPlanEstrategicoAmbiental;
import com.vortexbird.pusay.modelo.PsyPresupuesto;
import com.vortexbird.pusay.modelo.PsyPrograma;
import com.vortexbird.pusay.modelo.PsyProvincia;
import com.vortexbird.pusay.modelo.PsyResponsable;
import com.vortexbird.pusay.modelo.PsySeguimientoTarea;
import com.vortexbird.pusay.modelo.PsySubtema;
import com.vortexbird.pusay.modelo.PsyTarea;
import com.vortexbird.pusay.modelo.PsyTema;
import com.vortexbird.pusay.modelo.PsyTipoItemPresupuesto;
import com.vortexbird.pusay.modelo.dto.PsyAsuntoAmbientalDTO;
import com.vortexbird.pusay.modelo.dto.PsyCiudadDTO;
import com.vortexbird.pusay.modelo.dto.PsyDetalleEridaDTO;
import com.vortexbird.pusay.modelo.dto.PsyDetalleEstrategiasDTO;
import com.vortexbird.pusay.modelo.dto.PsyDetalleMapaEstrategicoDTO;
import com.vortexbird.pusay.modelo.dto.PsyDetalleMatrizCorrelacionDTO;
import com.vortexbird.pusay.modelo.dto.PsyDetalleObjetivoPlanDTO;
import com.vortexbird.pusay.modelo.dto.PsyEmpresaDTO;
import com.vortexbird.pusay.modelo.dto.PsyEstrategiaAmbientalDTO;
import com.vortexbird.pusay.modelo.dto.PsyEvaluacionPeaIndicadorDTO;
import com.vortexbird.pusay.modelo.dto.PsyEvaluacionPeaObjetivoDTO;
import com.vortexbird.pusay.modelo.dto.PsyEvaluarIndicadoresDTO;
import com.vortexbird.pusay.modelo.dto.PsyImpactoAmbientalDTO;
import com.vortexbird.pusay.modelo.dto.PsyImpactoObjetivoDTO;
import com.vortexbird.pusay.modelo.dto.PsyImpactoObjetivoTableDTO;
import com.vortexbird.pusay.modelo.dto.PsyIndicadorGestionDTO;
import com.vortexbird.pusay.modelo.dto.PsyIpuDTO;
import com.vortexbird.pusay.modelo.dto.PsyItemPresupuestoDTO;
import com.vortexbird.pusay.modelo.dto.PsyMapaEstrategicoDTO;
import com.vortexbird.pusay.modelo.dto.PsyMatrizCorrelacionDTO;
import com.vortexbird.pusay.modelo.dto.PsyMatrizEncuestaDTO;
import com.vortexbird.pusay.modelo.dto.PsyMatrizEridaDTO;
import com.vortexbird.pusay.modelo.dto.PsyMonedaDTO;
import com.vortexbird.pusay.modelo.dto.PsyObjetivoAmbientalDTO;
import com.vortexbird.pusay.modelo.dto.PsyObjetivoEstrategicoDTO;
import com.vortexbird.pusay.modelo.dto.PsyObjetivoImpactoDTO;
import com.vortexbird.pusay.modelo.dto.PsyObjetivoPlanDTO;
import com.vortexbird.pusay.modelo.dto.PsyPaisDTO;
import com.vortexbird.pusay.modelo.dto.PsyParametroDTO;
import com.vortexbird.pusay.modelo.dto.PsyPersonaDTO;
import com.vortexbird.pusay.modelo.dto.PsyPlanAccionDTO;
import com.vortexbird.pusay.modelo.dto.PsyPlanEstrategiaDTO;
import com.vortexbird.pusay.modelo.dto.PsyPlanEstrategicoAmbientalDTO;
import com.vortexbird.pusay.modelo.dto.PsyPlanEstrategicoDTO;
import com.vortexbird.pusay.modelo.dto.PsyPresupuestoDTO;
import com.vortexbird.pusay.modelo.dto.PsyPriorizacionAsuntoAmbientalDTO;
import com.vortexbird.pusay.modelo.dto.PsyPriorizacionImpactosAmbientalesDTO;
import com.vortexbird.pusay.modelo.dto.PsyProgramaDTO;
import com.vortexbird.pusay.modelo.dto.PsyProvinciaDTO;
import com.vortexbird.pusay.modelo.dto.PsyResponsableDTO;
import com.vortexbird.pusay.modelo.dto.PsySeguimientoTareaDTO;
import com.vortexbird.pusay.modelo.dto.PsySubtemaDTO;
import com.vortexbird.pusay.modelo.dto.PsyTablaEridaDTO;
import com.vortexbird.pusay.modelo.dto.PsyTareaDTO;
import com.vortexbird.pusay.modelo.dto.PsyTemaDTO;
import com.vortexbird.pusay.modelo.dto.PsyTipoItemPresupuestoDTO;
import com.vortexbird.pusay.modelo.dto.RespuestaEncuestasDTO;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
public interface IBusinessDelegatorView {
	public List<PsyAsuntoAmbiental> getPsyAsuntoAmbiental() throws Exception;

	public void savePsyAsuntoAmbiental(PsyAsuntoAmbiental entity)
			throws Exception;

	public void deletePsyAsuntoAmbiental(PsyAsuntoAmbiental entity)
			throws Exception;

	public void updatePsyAsuntoAmbiental(PsyAsuntoAmbiental entity)
			throws Exception;

	public PsyAsuntoAmbiental getPsyAsuntoAmbiental(Long asamCodigo)
			throws Exception;

	public List<PsyAsuntoAmbiental> findByCriteriaInPsyAsuntoAmbiental(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyAsuntoAmbiental> findPagePsyAsuntoAmbiental(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyAsuntoAmbiental() throws Exception;

	public List<PsyAsuntoAmbientalDTO> getDataPsyAsuntoAmbiental()
			throws Exception;

	public List<PsyCiudad> getPsyCiudad() throws Exception;

	public void savePsyCiudad(PsyCiudad entity) throws Exception;

	public void deletePsyCiudad(PsyCiudad entity) throws Exception;

	public void updatePsyCiudad(PsyCiudad entity) throws Exception;

	public PsyCiudad getPsyCiudad(Long ciudCodigo) throws Exception;

	public List<PsyCiudad> findByCriteriaInPsyCiudad(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<PsyCiudad> findPagePsyCiudad(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsyCiudad() throws Exception;

	public List<PsyCiudadDTO> getDataPsyCiudad() throws Exception;

	public List<PsyDetalleErida> getPsyDetalleErida() throws Exception;

	public void savePsyDetalleErida(PsyDetalleErida entity) throws Exception;

	public void deletePsyDetalleErida(PsyDetalleErida entity) throws Exception;

	public void updatePsyDetalleErida(PsyDetalleErida entity) throws Exception;

	public PsyDetalleErida getPsyDetalleErida(Long deerCodigo) throws Exception;

	public List<PsyDetalleErida> findByCriteriaInPsyDetalleErida(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyDetalleErida> findPagePsyDetalleErida(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsyDetalleErida() throws Exception;

	public List<PsyDetalleEridaDTO> getDataPsyDetalleErida() throws Exception;

	public List<PsyDetalleMapaEstrategico> getPsyDetalleMapaEstrategico()
			throws Exception;

	public void savePsyDetalleMapaEstrategico(PsyDetalleMapaEstrategico entity)
			throws Exception;

	public void deletePsyDetalleMapaEstrategico(PsyDetalleMapaEstrategico entity)
			throws Exception;

	public void updatePsyDetalleMapaEstrategico(PsyDetalleMapaEstrategico entity)
			throws Exception;

	public PsyDetalleMapaEstrategico getPsyDetalleMapaEstrategico(
			Long dmaeCodigo) throws Exception;

	public List<PsyDetalleMapaEstrategico> findByCriteriaInPsyDetalleMapaEstrategico(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyDetalleMapaEstrategico> findPagePsyDetalleMapaEstrategico(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyDetalleMapaEstrategico() throws Exception;

	public List<PsyDetalleMapaEstrategicoDTO> getDataPsyDetalleMapaEstrategico()
			throws Exception;

	public List<PsyDetalleObjetivoPlan> getPsyDetalleObjetivoPlan()
			throws Exception;

	public void savePsyDetalleObjetivoPlan(PsyDetalleObjetivoPlan entity)
			throws Exception;

	public void deletePsyDetalleObjetivoPlan(PsyDetalleObjetivoPlan entity)
			throws Exception;

	public void updatePsyDetalleObjetivoPlan(PsyDetalleObjetivoPlan entity)
			throws Exception;

	public PsyDetalleObjetivoPlan getPsyDetalleObjetivoPlan(Long dobpCodigo)
			throws Exception;

	public List<PsyDetalleObjetivoPlan> findByCriteriaInPsyDetalleObjetivoPlan(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyDetalleObjetivoPlan> findPagePsyDetalleObjetivoPlan(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyDetalleObjetivoPlan() throws Exception;

	public List<PsyDetalleObjetivoPlanDTO> getDataPsyDetalleObjetivoPlan()
			throws Exception;

	public List<PsyEmpresa> getPsyEmpresa() throws Exception;

	public void savePsyEmpresa(PsyEmpresa entity) throws Exception;

	public void deletePsyEmpresa(PsyEmpresa entity) throws Exception;

	public void updatePsyEmpresa(PsyEmpresa entity) throws Exception;

	public PsyEmpresa getPsyEmpresa(Long emprCodigo) throws Exception;

	public List<PsyEmpresa> findByCriteriaInPsyEmpresa(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<PsyEmpresa> findPagePsyEmpresa(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsyEmpresa() throws Exception;

	public List<PsyEmpresaDTO> getDataPsyEmpresa() throws Exception;

	public List<PsyEstrategiaAmbiental> getPsyEstrategiaAmbiental()
			throws Exception;

	public void savePsyEstrategiaAmbiental(PsyEstrategiaAmbiental entity)
			throws Exception;

	public void deletePsyEstrategiaAmbiental(PsyEstrategiaAmbiental entity)
			throws Exception;

	public void updatePsyEstrategiaAmbiental(PsyEstrategiaAmbiental entity)
			throws Exception;

	public PsyEstrategiaAmbiental getPsyEstrategiaAmbiental(Long esamCodigo)
			throws Exception;

	public List<PsyEstrategiaAmbiental> findByCriteriaInPsyEstrategiaAmbiental(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyEstrategiaAmbiental> findPagePsyEstrategiaAmbiental(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyEstrategiaAmbiental() throws Exception;

	public List<PsyEstrategiaAmbientalDTO> getDataPsyEstrategiaAmbiental()
			throws Exception;

	public List<PsyImpactoAmbiental> getPsyImpactoAmbiental() throws Exception;

	public void savePsyImpactoAmbiental(PsyImpactoAmbiental entity)
			throws Exception;

	public void deletePsyImpactoAmbiental(PsyImpactoAmbiental entity)
			throws Exception;

	public void updatePsyImpactoAmbiental(PsyImpactoAmbiental entity)
			throws Exception;

	public PsyImpactoAmbiental getPsyImpactoAmbiental(Long imamCodigo)
			throws Exception;

	public List<PsyImpactoAmbiental> findByCriteriaInPsyImpactoAmbiental(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyImpactoAmbiental> findPagePsyImpactoAmbiental(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyImpactoAmbiental() throws Exception;

	public List<PsyImpactoAmbientalDTO> getDataPsyImpactoAmbiental(
			PsyEmpresa empresa) throws Exception;

	public List<PsyIpu> getPsyIpu() throws Exception;

	public void savePsyIpu(PsyIpu entity) throws Exception;

	public void deletePsyIpu(PsyIpu entity) throws Exception;

	public void updatePsyIpu(PsyIpu entity) throws Exception;

	public PsyIpu getPsyIpu(Long ipuCodigo) throws Exception;

	public List<PsyIpu> findByCriteriaInPsyIpu(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<PsyIpu> findPagePsyIpu(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsyIpu() throws Exception;

	public List<PsyIpuDTO> getDataPsyIpu() throws Exception;

	public List<PsyItemPresupuesto> getPsyItemPresupuesto() throws Exception;

	public void savePsyItemPresupuesto(PsyItemPresupuesto entity)
			throws Exception;

	public void deletePsyItemPresupuesto(PsyItemPresupuesto entity)
			throws Exception;

	public void updatePsyItemPresupuesto(PsyItemPresupuesto entity)
			throws Exception;

	public PsyItemPresupuesto getPsyItemPresupuesto(Long ipreCodigo)
			throws Exception;

	public List<PsyItemPresupuesto> findByCriteriaInPsyItemPresupuesto(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyItemPresupuesto> findPagePsyItemPresupuesto(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyItemPresupuesto() throws Exception;

	public List<PsyItemPresupuestoDTO> getDataPsyItemPresupuesto()
			throws Exception;

	public List<PsyMapaEstrategico> getPsyMapaEstrategico() throws Exception;

	public void savePsyMapaEstrategico(PsyMapaEstrategico entity)
			throws Exception;

	public void deletePsyMapaEstrategico(PsyMapaEstrategico entity)
			throws Exception;

	public void updatePsyMapaEstrategico(PsyMapaEstrategico entity)
			throws Exception;

	public PsyMapaEstrategico getPsyMapaEstrategico(Long maesCodigo)
			throws Exception;

	public List<PsyMapaEstrategico> findByCriteriaInPsyMapaEstrategico(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyMapaEstrategico> findPagePsyMapaEstrategico(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyMapaEstrategico() throws Exception;

	public List<PsyMapaEstrategicoDTO> getDataPsyMapaEstrategico()
			throws Exception;

	public List<PsyMatrizCorrelacion> getPsyMatrizCorrelacion()
			throws Exception;

	public void savePsyMatrizCorrelacion(PsyMatrizCorrelacion entity)
			throws Exception;

	public void deletePsyMatrizCorrelacion(PsyMatrizCorrelacion entity)
			throws Exception;

	public void updatePsyMatrizCorrelacion(PsyMatrizCorrelacion entity)
			throws Exception;

	public PsyMatrizCorrelacion getPsyMatrizCorrelacion(Long macoCodigo)
			throws Exception;

	public List<PsyMatrizCorrelacion> findByCriteriaInPsyMatrizCorrelacion(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyMatrizCorrelacion> findPagePsyMatrizCorrelacion(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyMatrizCorrelacion() throws Exception;

	public List<PsyMatrizCorrelacionDTO> getDataPsyMatrizCorrelacion()
			throws Exception;

	public List<PsyMatrizEncuesta> getPsyMatrizEncuesta() throws Exception;

	public void savePsyMatrizEncuesta(PsyMatrizEncuesta entity)
			throws Exception;

	public void deletePsyMatrizEncuesta(PsyMatrizEncuesta entity)
			throws Exception;

	public void updatePsyMatrizEncuesta(PsyMatrizEncuesta entity)
			throws Exception;

	public PsyMatrizEncuesta getPsyMatrizEncuesta(Long maenCodigo)
			throws Exception;

	public List<PsyMatrizEncuesta> findByCriteriaInPsyMatrizEncuesta(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyMatrizEncuesta> findPagePsyMatrizEncuesta(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyMatrizEncuesta() throws Exception;

	public List<PsyMatrizEncuestaDTO> getDataPsyMatrizEncuesta()
			throws Exception;

	public List<PsyMatrizErida> getPsyMatrizErida() throws Exception;

	public void savePsyMatrizErida(PsyMatrizErida entity) throws Exception;

	public void deletePsyMatrizErida(PsyMatrizErida entity) throws Exception;

	public void updatePsyMatrizErida(PsyMatrizErida entity) throws Exception;

	public PsyMatrizErida getPsyMatrizErida(Long maerCodigo) throws Exception;

	public List<PsyMatrizErida> findByCriteriaInPsyMatrizErida(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyMatrizErida> findPagePsyMatrizErida(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsyMatrizErida() throws Exception;

	public List<PsyMatrizEridaDTO> getDataPsyMatrizErida() throws Exception;

	public List<PsyMoneda> getPsyMoneda() throws Exception;

	public void savePsyMoneda(PsyMoneda entity) throws Exception;

	public void deletePsyMoneda(PsyMoneda entity) throws Exception;

	public void updatePsyMoneda(PsyMoneda entity) throws Exception;

	public PsyMoneda getPsyMoneda(Long moneCodigo) throws Exception;

	public List<PsyMoneda> findByCriteriaInPsyMoneda(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<PsyMoneda> findPagePsyMoneda(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsyMoneda() throws Exception;

	public List<PsyMonedaDTO> getDataPsyMoneda() throws Exception;

	public List<PsyObjetivoEstrategico> getPsyObjetivoEstrategico()
			throws Exception;

	public void savePsyObjetivoEstrategico(PsyObjetivoEstrategico entity)
			throws Exception;

	public void deletePsyObjetivoEstrategico(PsyObjetivoEstrategico entity)
			throws Exception;

	public void updatePsyObjetivoEstrategico(PsyObjetivoEstrategico entity)
			throws Exception;

	public PsyObjetivoEstrategico getPsyObjetivoEstrategico(Long obesCodigo)
			throws Exception;

	public List<PsyObjetivoEstrategico> findByCriteriaInPsyObjetivoEstrategico(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyObjetivoEstrategico> findPagePsyObjetivoEstrategico(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyObjetivoEstrategico() throws Exception;

	public List<PsyObjetivoEstrategicoDTO> getDataPsyObjetivoEstrategico()
			throws Exception;

	public List<PsyObjetivoPlan> getPsyObjetivoPlan() throws Exception;

	public void savePsyObjetivoPlan(PsyObjetivoPlan entity) throws Exception;

	public void deletePsyObjetivoPlan(PsyObjetivoPlan entity) throws Exception;

	public void updatePsyObjetivoPlan(PsyObjetivoPlan entity) throws Exception;

	public PsyObjetivoPlan getPsyObjetivoPlan(Long obplCodigo) throws Exception;

	public List<PsyObjetivoPlan> findByCriteriaInPsyObjetivoPlan(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyObjetivoPlan> findPagePsyObjetivoPlan(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsyObjetivoPlan() throws Exception;

	public List<PsyObjetivoPlanDTO> getDataPsyObjetivoPlan() throws Exception;

	public List<PsyPais> getPsyPais() throws Exception;

	public void savePsyPais(PsyPais entity) throws Exception;

	public void deletePsyPais(PsyPais entity) throws Exception;

	public void updatePsyPais(PsyPais entity) throws Exception;

	public PsyPais getPsyPais(Long paisCodigo) throws Exception;

	public List<PsyPais> findByCriteriaInPsyPais(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<PsyPais> findPagePsyPais(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsyPais() throws Exception;

	public List<PsyPaisDTO> getDataPsyPais() throws Exception;

	public List<PsyPersona> getPsyPersona() throws Exception;

	public void savePsyPersona(PsyPersona entity) throws Exception;
	
	public void saveUsuarioConsulta(PsyPersona entity, String clave, String confimaClave, String nombreUConsulta, String apellidoUConsulta) throws Exception;

	public void deletePsyPersona(PsyPersona entity) throws Exception;

	public void updatePsyPersona(PsyPersona entity) throws Exception;
	
	public void updateUsuarioConsulta(PsyPersona entity, String clave, String confimaClave, String nombreUConsulta, String apellidoUConsulta) throws Exception;

	public PsyPersona getPsyPersona(Long persCodigo) throws Exception;

	public List<PsyPersona> findByCriteriaInPsyPersona(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<PsyPersona> findPagePsyPersona(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsyPersona() throws Exception;

	public List<PsyPersonaDTO> getDataPsyPersona(PsyEmpresa empresa) throws Exception;

	public List<PsyPlanAccion> getPsyPlanAccion() throws Exception;

	public void savePsyPlanAccion(PsyPlanAccion entity) throws Exception;

	public void deletePsyPlanAccion(PsyPlanAccion entity) throws Exception;

	public void updatePsyPlanAccion(PsyPlanAccion entity) throws Exception;

	public PsyPlanAccion getPsyPlanAccion(Long placCodigo) throws Exception;

	public List<PsyPlanAccion> findByCriteriaInPsyPlanAccion(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyPlanAccion> findPagePsyPlanAccion(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsyPlanAccion() throws Exception;

	public List<PsyPlanEstrategia> getPsyPlanEstrategia() throws Exception;

	public void savePsyPlanEstrategia(PsyPlanEstrategia entity,
			List<String> estrategiasTarget) throws Exception;

	public void deletePsyPlanEstrategia(PsyPlanEstrategia entity)
			throws Exception;

	public void updatePsyPlanEstrategia(PsyPlanEstrategia entity,
			List<String> estrategiasTarget) throws Exception;

	public PsyPlanEstrategia getPsyPlanEstrategia(Long plesCodigo)
			throws Exception;

	public List<PsyPlanEstrategia> findByCriteriaInPsyPlanEstrategia(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyPlanEstrategia> findPagePsyPlanEstrategia(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyPlanEstrategia() throws Exception;

	public List<PsyPlanEstrategiaDTO> getDataPsyPlanEstrategia()
			throws Exception;

	public List<PsyPlanEstrategico> getPsyPlanEstrategico() throws Exception;

	public void savePsyPlanEstrategico(PsyPlanEstrategico entity,
			PsyEmpresa empresa) throws Exception;

	public void deletePsyPlanEstrategico(PsyPlanEstrategico entity)
			throws Exception;

	public void updatePsyPlanEstrategico(PsyPlanEstrategico entity)
			throws Exception;

	public PsyPlanEstrategico getPsyPlanEstrategico(Long pestCodigo)
			throws Exception;

	public List<PsyPlanEstrategico> findByCriteriaInPsyPlanEstrategico(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyPlanEstrategico> findPagePsyPlanEstrategico(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyPlanEstrategico() throws Exception;

	public List<PsyPlanEstrategicoDTO> getDataPsyPlanEstrategico(
			PsyEmpresa empresa) throws Exception;

	public List<PsyPresupuesto> getPsyPresupuesto() throws Exception;

	public void deletePsyPresupuesto(PsyPresupuesto entity) throws Exception;

	public void updatePsyPresupuesto(PsyPresupuesto entity) throws Exception;

	public PsyPresupuesto getPsyPresupuesto(Long presCodigo) throws Exception;

	public List<PsyPresupuesto> findByCriteriaInPsyPresupuesto(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyPresupuesto> findPagePsyPresupuesto(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsyPresupuesto() throws Exception;

	public List<PsyProvincia> getPsyProvincia() throws Exception;

	public void savePsyProvincia(PsyProvincia entity) throws Exception;

	public void deletePsyProvincia(PsyProvincia entity) throws Exception;

	public void updatePsyProvincia(PsyProvincia entity) throws Exception;

	public PsyProvincia getPsyProvincia(Long provCodigo) throws Exception;

	public List<PsyProvincia> findByCriteriaInPsyProvincia(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<PsyProvincia> findPagePsyProvincia(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsyProvincia() throws Exception;

	public List<PsyProvinciaDTO> getDataPsyProvincia() throws Exception;

	public List<PsyResponsable> getPsyResponsable() throws Exception;

	public void savePsyResponsable(PsyResponsable entity) throws Exception;

	public void deletePsyResponsable(PsyResponsable entity) throws Exception;

	public void updatePsyResponsable(PsyResponsable entity) throws Exception;

	public PsyResponsable getPsyResponsable(Long respCodigo) throws Exception;

	public List<PsyResponsable> findByCriteriaInPsyResponsable(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyResponsable> findPagePsyResponsable(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsyResponsable() throws Exception;

	public List<PsySeguimientoTarea> getPsySeguimientoTarea() throws Exception;

	public void savePsySeguimientoTarea(PsySeguimientoTarea entity)
			throws Exception;

	public void deletePsySeguimientoTarea(PsySeguimientoTarea entity)
			throws Exception;

	public void updatePsySeguimientoTarea(PsySeguimientoTarea entity)
			throws Exception;

	public PsySeguimientoTarea getPsySeguimientoTarea(Long setaCodigo)
			throws Exception;

	public List<PsySeguimientoTarea> findByCriteriaInPsySeguimientoTarea(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsySeguimientoTarea> findPagePsySeguimientoTarea(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsySeguimientoTarea() throws Exception;

	public List<PsySeguimientoTareaDTO> getDataPsySeguimientoTarea()
			throws Exception;

	public List<PsyTarea> getPsyTarea() throws Exception;

	public void savePsyTarea(PsyTarea entity, String somResponsable)
			throws Exception;

	public void deletePsyTarea(PsyTarea entity) throws Exception;

	public void updatePsyTarea(PsyTarea entity) throws Exception;

	public PsyTarea getPsyTarea(Long tareCodigo) throws Exception;

	public List<PsyTarea> findByCriteriaInPsyTarea(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<PsyTarea> findPagePsyTarea(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsyTarea() throws Exception;

	public List<PsyTareaDTO> getDataPsyTarea() throws Exception;

	public List<PsyTipoItemPresupuesto> getPsyTipoItemPresupuesto()
			throws Exception;

	public void savePsyTipoItemPresupuesto(PsyTipoItemPresupuesto entity)
			throws Exception;

	public void deletePsyTipoItemPresupuesto(PsyTipoItemPresupuesto entity)
			throws Exception;

	public void updatePsyTipoItemPresupuesto(PsyTipoItemPresupuesto entity)
			throws Exception;

	public PsyTipoItemPresupuesto getPsyTipoItemPresupuesto(Long tiipCodigo)
			throws Exception;

	public List<PsyTipoItemPresupuesto> findByCriteriaInPsyTipoItemPresupuesto(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyTipoItemPresupuesto> findPagePsyTipoItemPresupuesto(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyTipoItemPresupuesto() throws Exception;

	public List<PsyTipoItemPresupuestoDTO> getDataPsyTipoItemPresupuesto()
			throws Exception;

	public List<PsyTablaEridaDTO> consultarTablaErida(Long mearCodigo,
			Long asamCodigo) throws Exception;

	public List<SelectItem> cargarOneMenuAsuntoAmbienta() throws Exception;

	public void guardarTablaErida(List<PsyTablaEridaDTO> tablaEridaDTO,
			Long maerCodigo, Long asamCodigo) throws Exception;

	public List<Double> calcularTotalesTablaErida(
			List<PsyTablaEridaDTO> listPsyTablaEridaDTO) throws Exception;

	public List<PsyPriorizacionAsuntoAmbientalDTO> PriorizacionAsuntoAmbiental(
			Long mearCodigo) throws Exception;

	public List<PsyPriorizacionImpactosAmbientalesDTO> PriorizacionImpactosAmbientales(
			Long mearCodigo) throws Exception;

	// Trae la lista de responsables de la empresa en sesion

	public List<PsyResponsable> getPsyResponsableByEmpresa(Long emprCodigo)
			throws Exception;

	// Trae la lista de planes de accion por el nombre
	public PsyPlanAccion getPsyPlanAccionByName(String nombre) throws Exception;

	// Trae los nombres de las estrategias
	public List<String> getEstrategias(PsyEmpresa empresa, Long placCodigo)
			throws Exception;

	public List<PsyPlanEstrategico> consultarPlanEstrategicoEmpresa(
			PsyEmpresa empresa, String estado) throws Exception;

	public PsyObjetivoPlan consultarObjetivoPlan(
			PsyPlanEstrategico planEstrategico, String estado) throws Exception;

	public List<PsyDetalleObjetivoPlan> consultarDetalleObjetivoPlan(
			PsyObjetivoPlan objetivoPlan) throws Exception;

	public List<PsyDetalleObjetivoPlan> guardarDetalleObjetivoPlan(
			List<PsyDetalleObjetivoPlan> listaDetalle,
			PsyObjetivoPlan objetivoPlan, String nombrevacio,
			String descripcionVacio) throws Exception;

	public PsyMapaEstrategico consultarMapaEstrategico(
			PsyPlanEstrategico planEstrategico, String estado) throws Exception;

	public List<PsyDetalleMatrizCorrelacionDTO> consultarMatrizRelacionMapaEstrategico(
			PsyMapaEstrategico mapaEstrategico,
			List<PsyDetalleObjetivoPlan> lstObjetivosCorportativo,
			Integer cantidadObjetivos,
			List<PsyImpactoAmbiental> lstImpactoAmbiental) throws Exception;

	public List<PsyMatrizEridaDTO> consultarExisteEridaPlanEstrategico(
			Long pestCodigo) throws Exception;

	// Trae los seguimientos por el codigod de la tarea
	public List<PsySeguimientoTareaDTO> getDataPsySeguimientoTareaByTareCodigo(
			Long tareCodigo) throws Exception;

	public List<PsyDetalleObjetivoPlan> consultarListaCompletaObjetivos(
			List<PsyDetalleObjetivoPlan> lstObjetivoPlan,
			Integer cantidadOjetivos) throws Exception;

	public void guardarDetalleMapaEstrategico(
			PsyMapaEstrategico mapaEstrategico,
			List<PsyDetalleMatrizCorrelacionDTO> lstMatriz,
			PsyImpactoAmbiental impactoAmbiental) throws Exception;

	public List<PsyAsuntoAmbiental> terminarErida(Long mearCodigo)
			throws Exception;

	public void generarPlanAccionDifinitivo(Long placCodigo) throws Exception;

	public void generarPresupuestoDefinitivo(Long presCodigo, Long placCodigo)
			throws Exception;

	public List<PsyIpuDTO> consultarIpu(PsyPlanAccion planAccion, String tipoIpu)
			throws Exception;

	public List<PsyIpuDTO> generarIpu(PsyPlanAccion planAccion, String tipoIpu,
			Date fechaInforme, Long semanaInforme, List<PsyIpuDTO> lstIpu,
			String logrosSignificativos, String logrosNoAlcanzados,
			String causasDesviacion, String accionesPropuestas,
			boolean actualizar) throws Exception;

	public List<PsyIpuDTO> generarIpuPresupuesto(PsyPlanAccion planAccion,
			String tipoIpu, Date fechaInforme, Long semanaInforme,
			List<PsyIpuDTO> lstIpu, String logrosSignificativos,
			String logrosNoAlcanzados, String causasDesviacion,
			String accionesPropuestas) throws Exception;

	public List<PsyIpuDTO> consultarIpuHastaPeriodo(PsyPlanAccion planAccion,
			String tipoIpu, String periodo) throws Exception;

	// Trae los items de presupuesto por el codigo del presupuesto
	public List<PsyItemPresupuestoDTO> getDataPsyItemPresupuestoByPresCodigo(
			Long presCodigo) throws Exception;

	// Trae las tareas de un plan de accion por el codigo del plan
	public List<PsyTareaDTO> getDataPsyTareaByPsyPlanAccion(Long placCodigo)
			throws Exception;

	public boolean verificarMapaEstrategico(PsyEmpresa empresa)
			throws Exception;

	public boolean verificarPlanEstrategico(PsyEmpresa empresa)
			throws Exception;

	public List<String> getEstrategiasSeleccionadas(PsyEmpresa empresa,
			Long placCodigo) throws Exception;

	public List<String> getEstrategiasRestantes(PsyEmpresa empresa,
			Long placCodigo, List<String> estrategiasSeleccionadas)
			throws Exception;

	public void savePsyPlanAccionPsyPlanEstrategia(PsyPlanAccion entity,
			List<String> estrategiasTarget, PsyEmpresa empresa)
			throws Exception;

	public List<String> getEstrategiasBtnNew(PsyEmpresa empresa)
			throws Exception;

	public String consultaPlanEstrategicoBtnNew(PsyEmpresa empresa)
			throws Exception;

	public String consultaPlanEstrategico(PsyEmpresa empresa, Long placCodigo)
			throws Exception;

	public List<PsyDetalleMapaEstrategico> consultarDetalleMapaEstrategico(
			PsyMapaEstrategico mapaEstrategico) throws Exception;

	public void updatePsyPlanAccionPlanEstrategia(PsyPlanAccion entity,
			List<String> estrategiasTarget, PsyEmpresa empresa)
			throws Exception;

	public List<PsyPlanAccionDTO> getDataPsyPlanAccion(PsyEmpresa empresa)
			throws Exception;

	public List<PsyResponsableDTO> getDataPsyResponsable(PsyEmpresa empresa)
			throws Exception;

	public List<PsyPlanAccion> findPsyPlanAccionByEmpresa(PsyEmpresa empresa)
			throws Exception;

	public void savePsyPresupuesto(PsyPresupuesto entity) throws Exception;

	public List<PsyPresupuestoDTO> getDataPsyPresupuesto(PsyEmpresa empresa)
			throws Exception;

	public List<PsyPlanAccion> consultarPlanesAccion(PsyEmpresa empresa,
			PsyPlanEstrategico planEstrategico, String estadoIniciado,
			String estadoPresupuestado) throws Exception;

	public List<PsyIpuDTO> generarIpuLogico(PsyPlanAccion planAccion,
			String tipoIpu, Date fechaInforme, Long semanaInforme,
			List<PsyIpuDTO> lstIpu, String logrosSignificativos,
			String logrosNoAlcanzados, String causasDesviacion,
			String accionesPropuestas, boolean actualizar) throws Exception;

	public PsyMatrizErida consultarMatrizErida(
			PsyPlanEstrategico planEstrategico) throws Exception;

	public List<PsyIpuDTO> generarIpuPresupuestoLogico(
			PsyPlanAccion planAccion, String tipoIpu, Date fechaInforme,
			Long semanaInforme, List<PsyIpuDTO> lstIpu,
			String logrosSignificativos, String logrosNoAlcanzados,
			String causasDesviacion, String accionesPropuestas)
			throws Exception;

	// INICIO MODULO DE INDICADORES

	public List<PsyTema> getPsyTema() throws Exception;

	public void savePsyTema(PsyTema entity) throws Exception;

	public void deletePsyTema(PsyTema entity) throws Exception;

	public void updatePsyTema(PsyTema entity) throws Exception;

	public PsyTema getPsyTema(Long codigo) throws Exception;

	public List<PsyTema> findByCriteriaInPsyTema(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<PsyTema> findPagePsyTema(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsyTema() throws Exception;

	public List<PsyTemaDTO> getDataPsyTema() throws Exception;

	public List<PsySubtema> getPsySubtema() throws Exception;

	public void savePsySubtema(PsySubtema entity) throws Exception;

	public void deletePsySubtema(PsySubtema entity) throws Exception;

	public void updatePsySubtema(PsySubtema entity) throws Exception;

	public PsySubtema getPsySubtema(Long codigo) throws Exception;

	public List<PsySubtema> findByCriteriaInPsySubtema(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<PsySubtema> findPagePsySubtema(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsySubtema() throws Exception;

	public List<PsySubtemaDTO> getDataPsySubtema() throws Exception;

	public List<PsyIndicador> getPsyIndicador() throws Exception;

	public void savePsyIndicador(PsyIndicador entity) throws Exception;

	public void deletePsyIndicador(PsyIndicador entity) throws Exception;

	public void updatePsyIndicador(PsyIndicador entity) throws Exception;

	public PsyIndicador getPsyIndicador(Long codigo) throws Exception;

	public List<PsyIndicador> findByCriteriaInPsyIndicador(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<PsyIndicador> findPagePsyIndicador(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsyIndicador() throws Exception;

	public List<PsyEvaluarIndicadoresDTO> getDataPsyIndicador(PsyEmpresa empresa,Long imamCodigo, PsyPlanEstrategicoAmbiental planEstrategicoAmbiental) throws Exception;

	public List<PsyObjetivoAmbiental> getPsyObjetivoAmbiental()
			throws Exception;

	public void savePsyObjetivoAmbiental(PsyObjetivoAmbiental entity)
			throws Exception;

	public void deletePsyObjetivoAmbiental(PsyObjetivoAmbiental entity)
			throws Exception;

	public void updatePsyObjetivoAmbiental(PsyObjetivoAmbiental entity)
			throws Exception;

	public PsyObjetivoAmbiental getPsyObjetivoAmbiental(Long codigo)
			throws Exception;

	public List<PsyObjetivoAmbiental> findByCriteriaInPsyObjetivoAmbiental(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyObjetivoAmbiental> findPagePsyObjetivoAmbiental(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyObjetivoAmbiental() throws Exception;

	public List<PsyObjetivoAmbientalDTO> getDataPsyObjetivoAmbiental()
			throws Exception;

	public List<PsyEvaluacionPeaIndicador> getPsyEvaluacionPeaIndicador()
			throws Exception;

	public void savePsyEvaluacionPeaIndicador(PsyEvaluacionPeaIndicador entity)
			throws Exception;

	public void deletePsyEvaluacionPeaIndicador(PsyEvaluacionPeaIndicador entity)
			throws Exception;

	public void updatePsyEvaluacionPeaIndicador(PsyEvaluacionPeaIndicador entity)
			throws Exception;

	public PsyEvaluacionPeaIndicador getPsyEvaluacionPeaIndicador(Long codigo)
			throws Exception;

	public List<PsyEvaluacionPeaIndicador> findByCriteriaInPsyEvaluacionPeaIndicador(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyEvaluacionPeaIndicador> findPagePsyEvaluacionPeaIndicador(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyEvaluacionPeaIndicador() throws Exception;

	public List<PsyEvaluacionPeaIndicadorDTO> getDataPsyEvaluacionPeaIndicador(
			PsyEmpresa empresa) throws Exception;

	public List<PsyEvaluacionPeaObjetivo> getPsyEvaluacionPeaObjetivo()
			throws Exception;

	public void savePsyEvaluacionPeaObjetivo(PsyEvaluacionPeaObjetivo entity)
			throws Exception;

	public void deletePsyEvaluacionPeaObjetivo(PsyEvaluacionPeaObjetivo entity)
			throws Exception;

	public void updatePsyEvaluacionPeaObjetivo(PsyEvaluacionPeaObjetivo entity)
			throws Exception;

	public PsyEvaluacionPeaObjetivo getPsyEvaluacionPeaObjetivo(Long codigo)
			throws Exception;

	public List<PsyEvaluacionPeaObjetivo> findByCriteriaInPsyEvaluacionPeaObjetivo(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyEvaluacionPeaObjetivo> findPagePsyEvaluacionPeaObjetivo(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyEvaluacionPeaObjetivo() throws Exception;

	public List<PsyEvaluacionPeaObjetivoDTO> getDataPsyEvaluacionPeaObjetivo()
			throws Exception;

	public List<PsyPlanEstrategicoAmbiental> getPsyPlanEstrategicoAmbiental()
			throws Exception;

	public void savePsyPlanEstrategicoAmbiental(
			PsyPlanEstrategicoAmbiental entity) throws Exception;

	public void deletePsyPlanEstrategicoAmbiental(
			PsyPlanEstrategicoAmbiental entity) throws Exception;

	public void updatePsyPlanEstrategicoAmbiental(
			PsyPlanEstrategicoAmbiental entity) throws Exception;

	public PsyPlanEstrategicoAmbiental getPsyPlanEstrategicoAmbiental(
			Long codigo) throws Exception;

	public List<PsyPlanEstrategicoAmbiental> findByCriteriaInPsyPlanEstrategicoAmbiental(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyPlanEstrategicoAmbiental> findPagePsyPlanEstrategicoAmbiental(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyPlanEstrategicoAmbiental() throws Exception;

	public List<PsyPlanEstrategicoAmbientalDTO> getDataPsyPlanEstrategicoAmbiental(
			PsyEmpresa empresa) throws Exception;

	// FIN MODULO DE INDICADORES

	public PsyPlanEstrategico getPlanEstrategicoActivoByPEA(PsyEmpresa empresa)
			throws Exception;

	public PsyPlanEstrategico getPlanEstrategicoByPEA(PsyEmpresa empresa,
			Long codigo) throws Exception;

	public PsyPlanEstrategico getPlanEstrategicoByPEA(PsyEmpresa empresa,
			String nombre) throws Exception;

	public List<PsyMatrizEncuesta> consultaAsociacionDeCuestionarios(
			PsyEmpresa empresa) throws Exception;

	public Long countIndicadoresPorImpacto(Long codigo) throws Exception;

	public Long countSubTemasPorImpacto(Long codigo) throws Exception;

	public Long countTemasPorImpacto(Long codigo) throws Exception;

	public List<PsyImpactoAmbiental> consultaImpactosAmbientalesSeleccionados(
			PsyEmpresa empresa) throws Exception;

	public boolean validarGrupoRepetido(
			List<PsyDetalleObjetivoPlan> lstObjetivoCorporativo)
			throws Exception;

	public List<PsyImpactoAmbientalDTO> getDataPsyImpactoAmbientalGestion()
			throws Exception;

	public List<PsyProvincia> consultarProvinciasPorPais(Long paisCodigo)
			throws Exception;

	public PsyPais consultarPaisPorProvincias(Long provCodigo) throws Exception;

	public List<PsyIndicadorGestionDTO> getDataPsyIndicadorGestion()
			throws Exception;

	public List<PsySubtema> consultarSubTemasPorTema(Long temaCodigo)
			throws Exception;

	public void registrar(PsyPersona persona, PsyEmpresa empresa,
			String nombreResponsable, String apellidoResponsable,
			String contrasena, String confirmaContrasena) throws Exception;

	public List<PsyCiudad> consultarCiudadesPorProvincia(Long provCodigo)
			throws Exception;

	public List<PsyPlanEstrategicoAmbiental> getPEA(PsyEmpresa empresa)
			throws Exception;

	public void evaluarIndicador(PsyEvaluacionPeaIndicador evaIndicador,
			PsyEvaluacionPeaObjetivo evaObjetivo) throws Exception;

	public List<PsyObjetivoAmbientalDTO> getDataPsyObjetivoAmbientalEvaluado(
			PsyEmpresa empresa) throws Exception;

	public List<RespuestaEncuestasDTO> consultarResultadoEncuestas(
			PsyEmpresa empresa) throws Exception;

	/* Metodos PsyObjetivoImpacto */

	public List<PsyObjetivoImpacto> getPsyObjetivoImpacto() throws Exception;

	public void savePsyObjetivoImpacto(PsyObjetivoImpacto entity)
			throws Exception;

	public void deletePsyObjetivoImpacto(PsyObjetivoImpacto entity)
			throws Exception;

	public void updatePsyObjetivoImpacto(PsyObjetivoImpacto entity)
			throws Exception;

	public PsyObjetivoImpacto getPsyObjetivoImpacto(Long obimCodigo)
			throws Exception;

	public List<PsyObjetivoImpacto> findByCriteriaInPsyObjetivoImpacto(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyObjetivoImpacto> findPagePsyObjetivoImpacto(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyObjetivoImpacto() throws Exception;

	public List<PsyObjetivoImpactoDTO> getDataPsyObjetivoImpacto()
			throws Exception;

	/* Metodos PsyPrograma */

	public List<PsyPrograma> getPsyPrograma() throws Exception;

	public void savePsyPrograma(PsyPrograma entity, String dmaeCodigo,
			PsyEmpresa empresa) throws Exception;

	public void deletePsyPrograma(PsyPrograma entity) throws Exception;

	public void updatePsyPrograma(PsyPrograma entity, String dmaeCodigo,
			PsyEmpresa empresa) throws Exception;

	public PsyPrograma getPsyPrograma(Long progCodigo) throws Exception;

	public List<PsyPrograma> findByCriteriaInPsyPrograma(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<PsyPrograma> findPagePsyPrograma(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPsyPrograma() throws Exception;

	public List<PsyProgramaDTO> getDataPsyPrograma(PsyEmpresa empresa)
			throws Exception;

	public List<PsyMatrizCorrelacion> consultaEstrategiasSeleccionadas(
			PsyDetalleMatrizCorrelacionDTO relacionSeleccionada,
			PsyMapaEstrategico mapaEstrategico) throws Exception;

	public List<PsyDetalleEstrategiasDTO> consultaEstrategiasParaPrograma(
			PsyEmpresa empresa) throws Exception;

	public List<PsyDetalleEstrategiasDTO> consultaEstrategiasParaProgramaDepurada(
			PsyEmpresa empresa) throws Exception;

	/* Metodos para la entidad PsyImpactoObjetivo */

	public List<PsyImpactoObjetivo> getPsyImpactoObjetivo() throws Exception;

	public void savePsyImpactoObjetivo(PsyImpactoObjetivo entity)
			throws Exception;

	public void deletePsyImpactoObjetivo(PsyImpactoObjetivo entity)
			throws Exception;

	public void updatePsyImpactoObjetivo(PsyImpactoObjetivo entity)
			throws Exception;

	public PsyImpactoObjetivo getPsyImpactoObjetivo(Long imobCodigo)
			throws Exception;

	public List<PsyImpactoObjetivo> findByCriteriaInPsyImpactoObjetivo(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<PsyImpactoObjetivo> findPagePsyImpactoObjetivo(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberPsyImpactoObjetivo() throws Exception;

	public List<PsyImpactoObjetivoDTO> getDataPsyImpactoObjetivo()
			throws Exception;

	/* MEtodos parfa la entidad PsyImpactoObjetivo */

	public List<PsyPresupuestoDTO> getDataPsyPresupuestoOnItem(
			PsyEmpresa empresa) throws Exception;
	

    public List<PsyParametro> getPsyParametro() throws Exception;

    public void savePsyParametro(PsyParametro entity) throws Exception;

    public void deletePsyParametro(PsyParametro entity)
        throws Exception;

    public void updatePsyParametro(PsyParametro entity)
        throws Exception;

    public PsyParametro getPsyParametro(Long paramCodigo)
        throws Exception;

    public List<PsyParametro> findByCriteriaInPsyParametro(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PsyParametro> findPagePsyParametro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPsyParametro() throws Exception;

    public List<PsyParametroDTO> getDataPsyParametro()
        throws Exception;
    
    public PsyParametro getPsyParametro(String paramNombre)
            throws Exception;
    
    public List<PsyImpactoObjetivoTableDTO> getDataImpactoObjetivo(PsyEmpresa empresa) throws Exception;
    
    public List<PsyEvaluarIndicadoresDTO> getDataPsyIndicadorObam(PsyEmpresa empresa, Long obamCodigo, PsyPlanEstrategicoAmbiental planEstrategicoAmbiental) throws Exception;
    
    public void recuperarClave(String correo, UsuarioDTO usuarioDTO) throws Exception;
    
    public void actionRecuperarClave(UsuarioDTO usuarioConsulta, String token, String correoRecuperacion, String clave, String claveRecuperacion) throws Exception;
    
    public void updatePerfilUsuario(PsyPersona entity,String claveActual, String clave, String confimaClave, String nombreUConsulta, String apellidoUConsulta) throws Exception;

}
