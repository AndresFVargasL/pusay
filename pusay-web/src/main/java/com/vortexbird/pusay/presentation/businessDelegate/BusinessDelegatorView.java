package com.vortexbird.pusay.presentation.businessDelegate;

import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

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
import com.vortexbird.pusay.modelo.control.IPsyAsuntoAmbientalLogic;
import com.vortexbird.pusay.modelo.control.IPsyCiudadLogic;
import com.vortexbird.pusay.modelo.control.IPsyDetalleEridaLogic;
import com.vortexbird.pusay.modelo.control.IPsyDetalleMapaEstrategicoLogic;
import com.vortexbird.pusay.modelo.control.IPsyDetalleObjetivoPlanLogic;
import com.vortexbird.pusay.modelo.control.IPsyEmpresaLogic;
import com.vortexbird.pusay.modelo.control.IPsyEstrategiaAmbientalLogic;
import com.vortexbird.pusay.modelo.control.IPsyEvaluacionPeaIndicadorLogic;
import com.vortexbird.pusay.modelo.control.IPsyEvaluacionPeaObjetivoLogic;
import com.vortexbird.pusay.modelo.control.IPsyImpactoAmbientalLogic;
import com.vortexbird.pusay.modelo.control.IPsyImpactoObjetivoLogic;
import com.vortexbird.pusay.modelo.control.IPsyIndicadorLogic;
import com.vortexbird.pusay.modelo.control.IPsyIpuLogic;
import com.vortexbird.pusay.modelo.control.IPsyItemPresupuestoLogic;
import com.vortexbird.pusay.modelo.control.IPsyMapaEstrategicoLogic;
import com.vortexbird.pusay.modelo.control.IPsyMatrizCorrelacionLogic;
import com.vortexbird.pusay.modelo.control.IPsyMatrizEncuestaLogic;
import com.vortexbird.pusay.modelo.control.IPsyMatrizEridaLogic;
import com.vortexbird.pusay.modelo.control.IPsyMonedaLogic;
import com.vortexbird.pusay.modelo.control.IPsyObjetivoAmbientalLogic;
import com.vortexbird.pusay.modelo.control.IPsyObjetivoEstrategicoLogic;
import com.vortexbird.pusay.modelo.control.IPsyObjetivoImpactoLogic;
import com.vortexbird.pusay.modelo.control.IPsyObjetivoPlanLogic;
import com.vortexbird.pusay.modelo.control.IPsyPaisLogic;
import com.vortexbird.pusay.modelo.control.IPsyParametroLogic;
import com.vortexbird.pusay.modelo.control.IPsyPersonaLogic;
import com.vortexbird.pusay.modelo.control.IPsyPlanAccionLogic;
import com.vortexbird.pusay.modelo.control.IPsyPlanEstrategiaLogic;
import com.vortexbird.pusay.modelo.control.IPsyPlanEstrategicoAmbientalLogic;
import com.vortexbird.pusay.modelo.control.IPsyPlanEstrategicoLogic;
import com.vortexbird.pusay.modelo.control.IPsyPresupuestoLogic;
import com.vortexbird.pusay.modelo.control.IPsyProgramaLogic;
import com.vortexbird.pusay.modelo.control.IPsyProvinciaLogic;
import com.vortexbird.pusay.modelo.control.IPsyResponsableLogic;
import com.vortexbird.pusay.modelo.control.IPsySeguimientoTareaLogic;
import com.vortexbird.pusay.modelo.control.IPsySubtemaLogic;
import com.vortexbird.pusay.modelo.control.IPsyTareaLogic;
import com.vortexbird.pusay.modelo.control.IPsyTemaLogic;
import com.vortexbird.pusay.modelo.control.IPsyTipoItemPresupuestoLogic;
import com.vortexbird.pusay.modelo.control.IRegistrarLogic;
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
import com.vortexbird.pusay.modelo.dto.PsyIndicadorDTO;
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
* Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
* The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
*
* The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
* the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
* the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
* volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
* presentation-tier client code when the business service API or its underlying implementation changes.
*
* However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
* Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
*
* Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
* for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
* considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
* to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
* exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
* generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
* The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
* exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
* use the pattern.
*
* Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
* because it limits unnecessary and potentially costly round trips over the network.
*
* A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
* details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
* implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
*
* When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
* This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
* with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
*
* Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
* business tiers.
*
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
    private static final Logger log = LoggerFactory.getLogger(BusinessDelegatorView.class);
    @Autowired
    private IPsyAsuntoAmbientalLogic psyAsuntoAmbientalLogic;
    @Autowired
    private IPsyCiudadLogic psyCiudadLogic;
    @Autowired
    private IPsyDetalleEridaLogic psyDetalleEridaLogic;
    @Autowired
    private IPsyDetalleMapaEstrategicoLogic psyDetalleMapaEstrategicoLogic;
    @Autowired
    private IPsyDetalleObjetivoPlanLogic psyDetalleObjetivoPlanLogic;
    @Autowired
    private IPsyEmpresaLogic psyEmpresaLogic;
    @Autowired
    private IPsyEstrategiaAmbientalLogic psyEstrategiaAmbientalLogic;
    @Autowired
    private IPsyEvaluacionPeaIndicadorLogic psyEvaluacionPeaIndicadorLogic;
    @Autowired
    private IPsyEvaluacionPeaObjetivoLogic psyEvaluacionPeaObjetivoLogic;
    @Autowired
    private IPsyImpactoAmbientalLogic psyImpactoAmbientalLogic;
    @Autowired
    private IPsyIndicadorLogic psyIndicadorLogic;
    @Autowired
    private IPsyIpuLogic psyIpuLogic;
    @Autowired
    private IPsyItemPresupuestoLogic psyItemPresupuestoLogic;
    @Autowired
    private IPsyMapaEstrategicoLogic psyMapaEstrategicoLogic;
    @Autowired
    private IPsyMatrizCorrelacionLogic psyMatrizCorrelacionLogic;
    @Autowired
    private IPsyMatrizEncuestaLogic psyMatrizEncuestaLogic;
    @Autowired
    private IPsyMatrizEridaLogic psyMatrizEridaLogic;
    @Autowired
    private IPsyMonedaLogic psyMonedaLogic;
    @Autowired
    private IPsyObjetivoAmbientalLogic psyObjetivoAmbientalLogic;
    @Autowired
    private IPsyObjetivoEstrategicoLogic psyObjetivoEstrategicoLogic;
    @Autowired
    private IPsyObjetivoPlanLogic psyObjetivoPlanLogic;
    @Autowired
    private IPsyPaisLogic psyPaisLogic;
    @Autowired
    private IPsyPersonaLogic psyPersonaLogic;
    @Autowired
    private IPsyPlanAccionLogic psyPlanAccionLogic;
    @Autowired
    private IPsyPlanEstrategiaLogic psyPlanEstrategiaLogic;
    @Autowired
    private IPsyPlanEstrategicoLogic psyPlanEstrategicoLogic;
    @Autowired
    private IPsyPlanEstrategicoAmbientalLogic psyPlanEstrategicoAmbientalLogic;
    @Autowired
    private IPsyPresupuestoLogic psyPresupuestoLogic;
    @Autowired
    private IPsyProvinciaLogic psyProvinciaLogic;
    @Autowired
    private IPsyResponsableLogic psyResponsableLogic;
    @Autowired
    private IPsySeguimientoTareaLogic psySeguimientoTareaLogic;
    @Autowired
    private IPsySubtemaLogic psySubtemaLogic;
    @Autowired
    private IPsyTareaLogic psyTareaLogic;
    @Autowired
    private IPsyTemaLogic psyTemaLogic;;
    @Autowired
    private IPsyTipoItemPresupuestoLogic psyTipoItemPresupuestoLogic;
    @Autowired
    private IRegistrarLogic registrarLogic;
    @Autowired
    private IPsyProgramaLogic psyProgramaLogic;
    @Autowired
    private IPsyObjetivoImpactoLogic psyObjetivoImpactoLogic;
    @Autowired
    private IPsyImpactoObjetivoLogic psyImpactoObjetivoLogic;
    @Autowired
    private IPsyParametroLogic psyParametroLogic;

    public List<PsyAsuntoAmbiental> getPsyAsuntoAmbiental()
        throws Exception {
        return psyAsuntoAmbientalLogic.getPsyAsuntoAmbiental();
    }

    public void savePsyAsuntoAmbiental(PsyAsuntoAmbiental entity)
        throws Exception {
        psyAsuntoAmbientalLogic.savePsyAsuntoAmbiental(entity);
    }

    public void deletePsyAsuntoAmbiental(PsyAsuntoAmbiental entity)
        throws Exception {
        psyAsuntoAmbientalLogic.deletePsyAsuntoAmbiental(entity);
    }

    public void updatePsyAsuntoAmbiental(PsyAsuntoAmbiental entity)
        throws Exception {
        psyAsuntoAmbientalLogic.updatePsyAsuntoAmbiental(entity);
    }

    public PsyAsuntoAmbiental getPsyAsuntoAmbiental(Long asamCodigo)
        throws Exception {
        PsyAsuntoAmbiental psyAsuntoAmbiental = null;

        try {
            psyAsuntoAmbiental = psyAsuntoAmbientalLogic.getPsyAsuntoAmbiental(asamCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyAsuntoAmbiental;
    }

    public List<PsyAsuntoAmbiental> findByCriteriaInPsyAsuntoAmbiental(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyAsuntoAmbientalLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsyAsuntoAmbiental> findPagePsyAsuntoAmbiental(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyAsuntoAmbientalLogic.findPagePsyAsuntoAmbiental(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyAsuntoAmbiental() throws Exception {
        return psyAsuntoAmbientalLogic.findTotalNumberPsyAsuntoAmbiental();
    }

    public List<PsyAsuntoAmbientalDTO> getDataPsyAsuntoAmbiental()
        throws Exception {
        return psyAsuntoAmbientalLogic.getDataPsyAsuntoAmbiental();
    }

    public List<PsyCiudad> getPsyCiudad() throws Exception {
        return psyCiudadLogic.getPsyCiudad();
    }

    public void savePsyCiudad(PsyCiudad entity) throws Exception {
        psyCiudadLogic.savePsyCiudad(entity);
    }

    public void deletePsyCiudad(PsyCiudad entity) throws Exception {
        psyCiudadLogic.deletePsyCiudad(entity);
    }

    public void updatePsyCiudad(PsyCiudad entity) throws Exception {
        psyCiudadLogic.updatePsyCiudad(entity);
    }

    public PsyCiudad getPsyCiudad(Long ciudCodigo) throws Exception {
        PsyCiudad psyCiudad = null;

        try {
            psyCiudad = psyCiudadLogic.getPsyCiudad(ciudCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyCiudad;
    }

    public List<PsyCiudad> findByCriteriaInPsyCiudad(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return psyCiudadLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PsyCiudad> findPagePsyCiudad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return psyCiudadLogic.findPagePsyCiudad(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberPsyCiudad() throws Exception {
        return psyCiudadLogic.findTotalNumberPsyCiudad();
    }

    public List<PsyCiudadDTO> getDataPsyCiudad() throws Exception {
        return psyCiudadLogic.getDataPsyCiudad();
    }

    public List<PsyDetalleErida> getPsyDetalleErida() throws Exception {
        return psyDetalleEridaLogic.getPsyDetalleErida();
    }

    public void savePsyDetalleErida(PsyDetalleErida entity)
        throws Exception {
        psyDetalleEridaLogic.savePsyDetalleErida(entity);
    }

    public void deletePsyDetalleErida(PsyDetalleErida entity)
        throws Exception {
        psyDetalleEridaLogic.deletePsyDetalleErida(entity);
    }

    public void updatePsyDetalleErida(PsyDetalleErida entity)
        throws Exception {
        psyDetalleEridaLogic.updatePsyDetalleErida(entity);
    }

    public PsyDetalleErida getPsyDetalleErida(Long deerCodigo)
        throws Exception {
        PsyDetalleErida psyDetalleErida = null;

        try {
            psyDetalleErida = psyDetalleEridaLogic.getPsyDetalleErida(deerCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyDetalleErida;
    }

    public List<PsyDetalleErida> findByCriteriaInPsyDetalleErida(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyDetalleEridaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PsyDetalleErida> findPagePsyDetalleErida(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyDetalleEridaLogic.findPagePsyDetalleErida(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyDetalleErida() throws Exception {
        return psyDetalleEridaLogic.findTotalNumberPsyDetalleErida();
    }

    public List<PsyDetalleEridaDTO> getDataPsyDetalleErida()
        throws Exception {
        return psyDetalleEridaLogic.getDataPsyDetalleErida();
    }

   

    public List<PsyDetalleMapaEstrategico> getPsyDetalleMapaEstrategico()
        throws Exception {
        return psyDetalleMapaEstrategicoLogic.getPsyDetalleMapaEstrategico();
    }

    public void savePsyDetalleMapaEstrategico(PsyDetalleMapaEstrategico entity)
        throws Exception {
        psyDetalleMapaEstrategicoLogic.savePsyDetalleMapaEstrategico(entity);
    }

    public void deletePsyDetalleMapaEstrategico(
        PsyDetalleMapaEstrategico entity) throws Exception {
        psyDetalleMapaEstrategicoLogic.deletePsyDetalleMapaEstrategico(entity);
    }

    public void updatePsyDetalleMapaEstrategico(
        PsyDetalleMapaEstrategico entity) throws Exception {
        psyDetalleMapaEstrategicoLogic.updatePsyDetalleMapaEstrategico(entity);
    }

    public PsyDetalleMapaEstrategico getPsyDetalleMapaEstrategico(
        Long dmaeCodigo) throws Exception {
        PsyDetalleMapaEstrategico psyDetalleMapaEstrategico = null;

        try {
            psyDetalleMapaEstrategico = psyDetalleMapaEstrategicoLogic.getPsyDetalleMapaEstrategico(dmaeCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyDetalleMapaEstrategico;
    }

    public List<PsyDetalleMapaEstrategico> findByCriteriaInPsyDetalleMapaEstrategico(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyDetalleMapaEstrategicoLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsyDetalleMapaEstrategico> findPagePsyDetalleMapaEstrategico(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyDetalleMapaEstrategicoLogic.findPagePsyDetalleMapaEstrategico(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyDetalleMapaEstrategico()
        throws Exception {
        return psyDetalleMapaEstrategicoLogic.findTotalNumberPsyDetalleMapaEstrategico();
    }

    public List<PsyDetalleMapaEstrategicoDTO> getDataPsyDetalleMapaEstrategico()
        throws Exception {
        return psyDetalleMapaEstrategicoLogic.getDataPsyDetalleMapaEstrategico();
    }

    public List<PsyDetalleObjetivoPlan> getPsyDetalleObjetivoPlan()
        throws Exception {
        return psyDetalleObjetivoPlanLogic.getPsyDetalleObjetivoPlan();
    }

    public void savePsyDetalleObjetivoPlan(PsyDetalleObjetivoPlan entity)
        throws Exception {
        psyDetalleObjetivoPlanLogic.savePsyDetalleObjetivoPlan(entity);
    }

    public void deletePsyDetalleObjetivoPlan(PsyDetalleObjetivoPlan entity)
        throws Exception {
        psyDetalleObjetivoPlanLogic.deletePsyDetalleObjetivoPlan(entity);
    }

    public void updatePsyDetalleObjetivoPlan(PsyDetalleObjetivoPlan entity)
        throws Exception {
        psyDetalleObjetivoPlanLogic.updatePsyDetalleObjetivoPlan(entity);
    }

    public PsyDetalleObjetivoPlan getPsyDetalleObjetivoPlan(Long dobpCodigo)
        throws Exception {
        PsyDetalleObjetivoPlan psyDetalleObjetivoPlan = null;

        try {
            psyDetalleObjetivoPlan = psyDetalleObjetivoPlanLogic.getPsyDetalleObjetivoPlan(dobpCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyDetalleObjetivoPlan;
    }

    public List<PsyDetalleObjetivoPlan> findByCriteriaInPsyDetalleObjetivoPlan(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyDetalleObjetivoPlanLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsyDetalleObjetivoPlan> findPagePsyDetalleObjetivoPlan(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyDetalleObjetivoPlanLogic.findPagePsyDetalleObjetivoPlan(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyDetalleObjetivoPlan()
        throws Exception {
        return psyDetalleObjetivoPlanLogic.findTotalNumberPsyDetalleObjetivoPlan();
    }

    public List<PsyDetalleObjetivoPlanDTO> getDataPsyDetalleObjetivoPlan()
        throws Exception {
        return psyDetalleObjetivoPlanLogic.getDataPsyDetalleObjetivoPlan();
    }

    public List<PsyEmpresa> getPsyEmpresa() throws Exception {
        return psyEmpresaLogic.getPsyEmpresa();
    }

    public void savePsyEmpresa(PsyEmpresa entity) throws Exception {
        psyEmpresaLogic.savePsyEmpresa(entity);
    }

    public void deletePsyEmpresa(PsyEmpresa entity) throws Exception {
        psyEmpresaLogic.deletePsyEmpresa(entity);
    }

    public void updatePsyEmpresa(PsyEmpresa entity) throws Exception {
        psyEmpresaLogic.updatePsyEmpresa(entity);
    }

    public PsyEmpresa getPsyEmpresa(Long emprCodigo) throws Exception {
        PsyEmpresa psyEmpresa = null;

        try {
            psyEmpresa = psyEmpresaLogic.getPsyEmpresa(emprCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyEmpresa;
    }

    public List<PsyEmpresa> findByCriteriaInPsyEmpresa(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return psyEmpresaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PsyEmpresa> findPagePsyEmpresa(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return psyEmpresaLogic.findPagePsyEmpresa(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyEmpresa() throws Exception {
        return psyEmpresaLogic.findTotalNumberPsyEmpresa();
    }

    public List<PsyEmpresaDTO> getDataPsyEmpresa() throws Exception {
        return psyEmpresaLogic.getDataPsyEmpresa();
    }

    public List<PsyEstrategiaAmbiental> getPsyEstrategiaAmbiental()
        throws Exception {
        return psyEstrategiaAmbientalLogic.getPsyEstrategiaAmbiental();
    }

    public void savePsyEstrategiaAmbiental(PsyEstrategiaAmbiental entity)
        throws Exception {
        psyEstrategiaAmbientalLogic.savePsyEstrategiaAmbiental(entity);
    }

    public void deletePsyEstrategiaAmbiental(PsyEstrategiaAmbiental entity)
        throws Exception {
        psyEstrategiaAmbientalLogic.deletePsyEstrategiaAmbiental(entity);
    }

    public void updatePsyEstrategiaAmbiental(PsyEstrategiaAmbiental entity)
        throws Exception {
        psyEstrategiaAmbientalLogic.updatePsyEstrategiaAmbiental(entity);
    }

    public PsyEstrategiaAmbiental getPsyEstrategiaAmbiental(Long esamCodigo)
        throws Exception {
        PsyEstrategiaAmbiental psyEstrategiaAmbiental = null;

        try {
            psyEstrategiaAmbiental = psyEstrategiaAmbientalLogic.getPsyEstrategiaAmbiental(esamCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyEstrategiaAmbiental;
    }

    public List<PsyEstrategiaAmbiental> findByCriteriaInPsyEstrategiaAmbiental(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyEstrategiaAmbientalLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsyEstrategiaAmbiental> findPagePsyEstrategiaAmbiental(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyEstrategiaAmbientalLogic.findPagePsyEstrategiaAmbiental(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyEstrategiaAmbiental()
        throws Exception {
        return psyEstrategiaAmbientalLogic.findTotalNumberPsyEstrategiaAmbiental();
    }

    public List<PsyEstrategiaAmbientalDTO> getDataPsyEstrategiaAmbiental()
        throws Exception {
        return psyEstrategiaAmbientalLogic.getDataPsyEstrategiaAmbiental();
    }

    public List<PsyImpactoAmbiental> getPsyImpactoAmbiental()
        throws Exception {
        return psyImpactoAmbientalLogic.getPsyImpactoAmbiental();
    }

    public void savePsyImpactoAmbiental(PsyImpactoAmbiental entity)
        throws Exception {
        psyImpactoAmbientalLogic.savePsyImpactoAmbiental(entity);
    }

    public void deletePsyImpactoAmbiental(PsyImpactoAmbiental entity)
        throws Exception {
        psyImpactoAmbientalLogic.deletePsyImpactoAmbiental(entity);
    }

    public void updatePsyImpactoAmbiental(PsyImpactoAmbiental entity)
        throws Exception {
        psyImpactoAmbientalLogic.updatePsyImpactoAmbiental(entity);
    }

    public PsyImpactoAmbiental getPsyImpactoAmbiental(Long imamCodigo)
        throws Exception {
        PsyImpactoAmbiental psyImpactoAmbiental = null;

        try {
            psyImpactoAmbiental = psyImpactoAmbientalLogic.getPsyImpactoAmbiental(imamCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyImpactoAmbiental;
    }

    public List<PsyImpactoAmbiental> findByCriteriaInPsyImpactoAmbiental(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyImpactoAmbientalLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsyImpactoAmbiental> findPagePsyImpactoAmbiental(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyImpactoAmbientalLogic.findPagePsyImpactoAmbiental(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyImpactoAmbiental() throws Exception {
        return psyImpactoAmbientalLogic.findTotalNumberPsyImpactoAmbiental();
    }

    public List<PsyImpactoAmbientalDTO> getDataPsyImpactoAmbiental(PsyEmpresa empresa)
        throws Exception {
        return psyImpactoAmbientalLogic.getDataPsyImpactoAmbiental(empresa);
    }

    public List<PsyIpu> getPsyIpu() throws Exception {
        return psyIpuLogic.getPsyIpu();
    }

    public void savePsyIpu(PsyIpu entity) throws Exception {
        psyIpuLogic.savePsyIpu(entity);
    }

    public void deletePsyIpu(PsyIpu entity) throws Exception {
        psyIpuLogic.deletePsyIpu(entity);
    }

    public void updatePsyIpu(PsyIpu entity) throws Exception {
        psyIpuLogic.updatePsyIpu(entity);
    }

    public PsyIpu getPsyIpu(Long ipuCodigo) throws Exception {
        PsyIpu psyIpu = null;

        try {
            psyIpu = psyIpuLogic.getPsyIpu(ipuCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyIpu;
    }

    public List<PsyIpu> findByCriteriaInPsyIpu(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return psyIpuLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PsyIpu> findPagePsyIpu(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return psyIpuLogic.findPagePsyIpu(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberPsyIpu() throws Exception {
        return psyIpuLogic.findTotalNumberPsyIpu();
    }

    public List<PsyIpuDTO> getDataPsyIpu() throws Exception {
        return psyIpuLogic.getDataPsyIpu();
    }

    public List<PsyItemPresupuesto> getPsyItemPresupuesto()
        throws Exception {
        return psyItemPresupuestoLogic.getPsyItemPresupuesto();
    }

    public void savePsyItemPresupuesto(PsyItemPresupuesto entity)
        throws Exception {
        psyItemPresupuestoLogic.savePsyItemPresupuesto(entity);
    }

    public void deletePsyItemPresupuesto(PsyItemPresupuesto entity)
        throws Exception {
        psyItemPresupuestoLogic.deletePsyItemPresupuesto(entity);
    }

    public void updatePsyItemPresupuesto(PsyItemPresupuesto entity)
        throws Exception {
        psyItemPresupuestoLogic.updatePsyItemPresupuesto(entity);
    }

    public PsyItemPresupuesto getPsyItemPresupuesto(Long ipreCodigo)
        throws Exception {
        PsyItemPresupuesto psyItemPresupuesto = null;

        try {
            psyItemPresupuesto = psyItemPresupuestoLogic.getPsyItemPresupuesto(ipreCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyItemPresupuesto;
    }

    public List<PsyItemPresupuesto> findByCriteriaInPsyItemPresupuesto(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyItemPresupuestoLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsyItemPresupuesto> findPagePsyItemPresupuesto(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyItemPresupuestoLogic.findPagePsyItemPresupuesto(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyItemPresupuesto() throws Exception {
        return psyItemPresupuestoLogic.findTotalNumberPsyItemPresupuesto();
    }

    public List<PsyItemPresupuestoDTO> getDataPsyItemPresupuesto()
        throws Exception {
        return psyItemPresupuestoLogic.getDataPsyItemPresupuesto();
    }

    public List<PsyMapaEstrategico> getPsyMapaEstrategico()
        throws Exception {
        return psyMapaEstrategicoLogic.getPsyMapaEstrategico();
    }

    public void savePsyMapaEstrategico(PsyMapaEstrategico entity)
        throws Exception {
        psyMapaEstrategicoLogic.savePsyMapaEstrategico(entity);
    }

    public void deletePsyMapaEstrategico(PsyMapaEstrategico entity)
        throws Exception {
        psyMapaEstrategicoLogic.deletePsyMapaEstrategico(entity);
    }

    public void updatePsyMapaEstrategico(PsyMapaEstrategico entity)
        throws Exception {
        psyMapaEstrategicoLogic.updatePsyMapaEstrategico(entity);
    }

    public PsyMapaEstrategico getPsyMapaEstrategico(Long maesCodigo)
        throws Exception {
        PsyMapaEstrategico psyMapaEstrategico = null;

        try {
            psyMapaEstrategico = psyMapaEstrategicoLogic.getPsyMapaEstrategico(maesCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyMapaEstrategico;
    }

    public List<PsyMapaEstrategico> findByCriteriaInPsyMapaEstrategico(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyMapaEstrategicoLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsyMapaEstrategico> findPagePsyMapaEstrategico(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyMapaEstrategicoLogic.findPagePsyMapaEstrategico(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyMapaEstrategico() throws Exception {
        return psyMapaEstrategicoLogic.findTotalNumberPsyMapaEstrategico();
    }

    public List<PsyMapaEstrategicoDTO> getDataPsyMapaEstrategico()
        throws Exception {
        return psyMapaEstrategicoLogic.getDataPsyMapaEstrategico();
    }

    public List<PsyMatrizCorrelacion> getPsyMatrizCorrelacion()
        throws Exception {
        return psyMatrizCorrelacionLogic.getPsyMatrizCorrelacion();
    }

    public void savePsyMatrizCorrelacion(PsyMatrizCorrelacion entity)
        throws Exception {
        psyMatrizCorrelacionLogic.savePsyMatrizCorrelacion(entity);
    }

    public void deletePsyMatrizCorrelacion(PsyMatrizCorrelacion entity)
        throws Exception {
        psyMatrizCorrelacionLogic.deletePsyMatrizCorrelacion(entity);
    }

    public void updatePsyMatrizCorrelacion(PsyMatrizCorrelacion entity)
        throws Exception {
        psyMatrizCorrelacionLogic.updatePsyMatrizCorrelacion(entity);
    }

    public PsyMatrizCorrelacion getPsyMatrizCorrelacion(Long macoCodigo)
        throws Exception {
        PsyMatrizCorrelacion psyMatrizCorrelacion = null;

        try {
            psyMatrizCorrelacion = psyMatrizCorrelacionLogic.getPsyMatrizCorrelacion(macoCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyMatrizCorrelacion;
    }

    public List<PsyMatrizCorrelacion> findByCriteriaInPsyMatrizCorrelacion(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyMatrizCorrelacionLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsyMatrizCorrelacion> findPagePsyMatrizCorrelacion(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyMatrizCorrelacionLogic.findPagePsyMatrizCorrelacion(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyMatrizCorrelacion() throws Exception {
        return psyMatrizCorrelacionLogic.findTotalNumberPsyMatrizCorrelacion();
    }

    public List<PsyMatrizCorrelacionDTO> getDataPsyMatrizCorrelacion()
        throws Exception {
        return psyMatrizCorrelacionLogic.getDataPsyMatrizCorrelacion();
    }

    public List<PsyMatrizEncuesta> getPsyMatrizEncuesta()
        throws Exception {
        return psyMatrizEncuestaLogic.getPsyMatrizEncuesta();
    }

    public void savePsyMatrizEncuesta(PsyMatrizEncuesta entity)
        throws Exception {
        psyMatrizEncuestaLogic.savePsyMatrizEncuesta(entity);
    }

    public void deletePsyMatrizEncuesta(PsyMatrizEncuesta entity)
        throws Exception {
        psyMatrizEncuestaLogic.deletePsyMatrizEncuesta(entity);
    }

    public void updatePsyMatrizEncuesta(PsyMatrizEncuesta entity)
        throws Exception {
        psyMatrizEncuestaLogic.updatePsyMatrizEncuesta(entity);
    }

    public PsyMatrizEncuesta getPsyMatrizEncuesta(Long maenCodigo)
        throws Exception {
        PsyMatrizEncuesta psyMatrizEncuesta = null;

        try {
            psyMatrizEncuesta = psyMatrizEncuestaLogic.getPsyMatrizEncuesta(maenCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyMatrizEncuesta;
    }

    public List<PsyMatrizEncuesta> findByCriteriaInPsyMatrizEncuesta(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyMatrizEncuestaLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsyMatrizEncuesta> findPagePsyMatrizEncuesta(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyMatrizEncuestaLogic.findPagePsyMatrizEncuesta(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyMatrizEncuesta() throws Exception {
        return psyMatrizEncuestaLogic.findTotalNumberPsyMatrizEncuesta();
    }

    public List<PsyMatrizEncuestaDTO> getDataPsyMatrizEncuesta()
        throws Exception {
        return psyMatrizEncuestaLogic.getDataPsyMatrizEncuesta();
    }

    public List<PsyMatrizErida> getPsyMatrizErida() throws Exception {
        return psyMatrizEridaLogic.getPsyMatrizErida();
    }

    public void savePsyMatrizErida(PsyMatrizErida entity)
        throws Exception {
        psyMatrizEridaLogic.savePsyMatrizErida(entity);
    }

    public void deletePsyMatrizErida(PsyMatrizErida entity)
        throws Exception {
        psyMatrizEridaLogic.deletePsyMatrizErida(entity);
    }

    public void updatePsyMatrizErida(PsyMatrizErida entity)
        throws Exception {
        psyMatrizEridaLogic.updatePsyMatrizErida(entity);
    }

    public PsyMatrizErida getPsyMatrizErida(Long maerCodigo)
        throws Exception {
        PsyMatrizErida psyMatrizErida = null;

        try {
            psyMatrizErida = psyMatrizEridaLogic.getPsyMatrizErida(maerCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyMatrizErida;
    }

    public List<PsyMatrizErida> findByCriteriaInPsyMatrizErida(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyMatrizEridaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PsyMatrizErida> findPagePsyMatrizErida(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return psyMatrizEridaLogic.findPagePsyMatrizErida(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyMatrizErida() throws Exception {
        return psyMatrizEridaLogic.findTotalNumberPsyMatrizErida();
    }

    public List<PsyMatrizEridaDTO> getDataPsyMatrizErida()
        throws Exception {
        return psyMatrizEridaLogic.getDataPsyMatrizErida();
    }

    public List<PsyMoneda> getPsyMoneda() throws Exception {
        return psyMonedaLogic.getPsyMoneda();
    }

    public void savePsyMoneda(PsyMoneda entity) throws Exception {
        psyMonedaLogic.savePsyMoneda(entity);
    }

    public void deletePsyMoneda(PsyMoneda entity) throws Exception {
        psyMonedaLogic.deletePsyMoneda(entity);
    }

    public void updatePsyMoneda(PsyMoneda entity) throws Exception {
        psyMonedaLogic.updatePsyMoneda(entity);
    }

    public PsyMoneda getPsyMoneda(Long moneCodigo) throws Exception {
        PsyMoneda psyMoneda = null;

        try {
            psyMoneda = psyMonedaLogic.getPsyMoneda(moneCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyMoneda;
    }

    public List<PsyMoneda> findByCriteriaInPsyMoneda(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return psyMonedaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PsyMoneda> findPagePsyMoneda(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return psyMonedaLogic.findPagePsyMoneda(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberPsyMoneda() throws Exception {
        return psyMonedaLogic.findTotalNumberPsyMoneda();
    }

    public List<PsyMonedaDTO> getDataPsyMoneda() throws Exception {
        return psyMonedaLogic.getDataPsyMoneda();
    }

    public List<PsyObjetivoEstrategico> getPsyObjetivoEstrategico()
        throws Exception {
        return psyObjetivoEstrategicoLogic.getPsyObjetivoEstrategico();
    }

    public void savePsyObjetivoEstrategico(PsyObjetivoEstrategico entity)
        throws Exception {
        psyObjetivoEstrategicoLogic.savePsyObjetivoEstrategico(entity);
    }

    public void deletePsyObjetivoEstrategico(PsyObjetivoEstrategico entity)
        throws Exception {
        psyObjetivoEstrategicoLogic.deletePsyObjetivoEstrategico(entity);
    }

    public void updatePsyObjetivoEstrategico(PsyObjetivoEstrategico entity)
        throws Exception {
        psyObjetivoEstrategicoLogic.updatePsyObjetivoEstrategico(entity);
    }

    public PsyObjetivoEstrategico getPsyObjetivoEstrategico(Long obesCodigo)
        throws Exception {
        PsyObjetivoEstrategico psyObjetivoEstrategico = null;

        try {
            psyObjetivoEstrategico = psyObjetivoEstrategicoLogic.getPsyObjetivoEstrategico(obesCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyObjetivoEstrategico;
    }

    public List<PsyObjetivoEstrategico> findByCriteriaInPsyObjetivoEstrategico(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyObjetivoEstrategicoLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsyObjetivoEstrategico> findPagePsyObjetivoEstrategico(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyObjetivoEstrategicoLogic.findPagePsyObjetivoEstrategico(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyObjetivoEstrategico()
        throws Exception {
        return psyObjetivoEstrategicoLogic.findTotalNumberPsyObjetivoEstrategico();
    }

    public List<PsyObjetivoEstrategicoDTO> getDataPsyObjetivoEstrategico()
        throws Exception {
        return psyObjetivoEstrategicoLogic.getDataPsyObjetivoEstrategico();
    }

    public List<PsyObjetivoPlan> getPsyObjetivoPlan() throws Exception {
        return psyObjetivoPlanLogic.getPsyObjetivoPlan();
    }

    public void savePsyObjetivoPlan(PsyObjetivoPlan entity)
        throws Exception {
        psyObjetivoPlanLogic.savePsyObjetivoPlan(entity);
    }

    public void deletePsyObjetivoPlan(PsyObjetivoPlan entity)
        throws Exception {
        psyObjetivoPlanLogic.deletePsyObjetivoPlan(entity);
    }

    public void updatePsyObjetivoPlan(PsyObjetivoPlan entity)
        throws Exception {
        psyObjetivoPlanLogic.updatePsyObjetivoPlan(entity);
    }

    public PsyObjetivoPlan getPsyObjetivoPlan(Long obplCodigo)
        throws Exception {
        PsyObjetivoPlan psyObjetivoPlan = null;

        try {
            psyObjetivoPlan = psyObjetivoPlanLogic.getPsyObjetivoPlan(obplCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyObjetivoPlan;
    }

    public List<PsyObjetivoPlan> findByCriteriaInPsyObjetivoPlan(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyObjetivoPlanLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PsyObjetivoPlan> findPagePsyObjetivoPlan(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyObjetivoPlanLogic.findPagePsyObjetivoPlan(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyObjetivoPlan() throws Exception {
        return psyObjetivoPlanLogic.findTotalNumberPsyObjetivoPlan();
    }

    public List<PsyObjetivoPlanDTO> getDataPsyObjetivoPlan()
        throws Exception {
        return psyObjetivoPlanLogic.getDataPsyObjetivoPlan();
    }

    public List<PsyPais> getPsyPais() throws Exception {
        return psyPaisLogic.getPsyPais();
    }

    public void savePsyPais(PsyPais entity) throws Exception {
        psyPaisLogic.savePsyPais(entity);
    }

    public void deletePsyPais(PsyPais entity) throws Exception {
        psyPaisLogic.deletePsyPais(entity);
    }

    public void updatePsyPais(PsyPais entity) throws Exception {
        psyPaisLogic.updatePsyPais(entity);
    }

    public PsyPais getPsyPais(Long paisCodigo) throws Exception {
        PsyPais psyPais = null;

        try {
            psyPais = psyPaisLogic.getPsyPais(paisCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyPais;
    }

    public List<PsyPais> findByCriteriaInPsyPais(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return psyPaisLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PsyPais> findPagePsyPais(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return psyPaisLogic.findPagePsyPais(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberPsyPais() throws Exception {
        return psyPaisLogic.findTotalNumberPsyPais();
    }

    public List<PsyPaisDTO> getDataPsyPais() throws Exception {
        return psyPaisLogic.getDataPsyPais();
    }

    public List<PsyPersona> getPsyPersona() throws Exception {
        return psyPersonaLogic.getPsyPersona();
    }

    public void savePsyPersona(PsyPersona entity) throws Exception {
        psyPersonaLogic.savePsyPersona(entity);
    }
    
    public void saveUsuarioConsulta(PsyPersona entity, String clave, String confimaClave, String nombreUConsulta, String apellidoUConsulta) throws Exception {
        psyPersonaLogic.saveUsuarioConsulta(entity, clave, confimaClave, nombreUConsulta, apellidoUConsulta);
    }

    public void deletePsyPersona(PsyPersona entity) throws Exception {
        psyPersonaLogic.deletePsyPersona(entity);
    }

    public void updatePsyPersona(PsyPersona entity) throws Exception {
        psyPersonaLogic.updatePsyPersona(entity);
    }
    
    public void updateUsuarioConsulta(PsyPersona entity, String clave, String confimaClave, String nombreUConsulta, String apellidoUConsulta) throws Exception {
        psyPersonaLogic.updateUsuarioConsulta(entity, clave, confimaClave, nombreUConsulta, apellidoUConsulta);
    }

    public PsyPersona getPsyPersona(Long persCodigo) throws Exception {
        PsyPersona psyPersona = null;

        try {
            psyPersona = psyPersonaLogic.getPsyPersona(persCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyPersona;
    }

    public List<PsyPersona> findByCriteriaInPsyPersona(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return psyPersonaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PsyPersona> findPagePsyPersona(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return psyPersonaLogic.findPagePsyPersona(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyPersona() throws Exception {
        return psyPersonaLogic.findTotalNumberPsyPersona();
    }

    public List<PsyPersonaDTO> getDataPsyPersona(PsyEmpresa empresa) throws Exception {
        return psyPersonaLogic.getDataPsyPersona(empresa);
    }

    public List<PsyPlanAccion> getPsyPlanAccion() throws Exception {
        return psyPlanAccionLogic.getPsyPlanAccion();
    }

    public void savePsyPlanAccion(PsyPlanAccion entity)
        throws Exception {
        psyPlanAccionLogic.savePsyPlanAccion(entity);
    }

    public void deletePsyPlanAccion(PsyPlanAccion entity)
        throws Exception {
        psyPlanAccionLogic.deletePsyPlanAccion(entity);
    }

    public void updatePsyPlanAccion(PsyPlanAccion entity)
        throws Exception {
        psyPlanAccionLogic.updatePsyPlanAccion(entity);
    }

    public PsyPlanAccion getPsyPlanAccion(Long placCodigo)
        throws Exception {
        PsyPlanAccion psyPlanAccion = null;

        try {
            psyPlanAccion = psyPlanAccionLogic.getPsyPlanAccion(placCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyPlanAccion;
    }

    public List<PsyPlanAccion> findByCriteriaInPsyPlanAccion(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyPlanAccionLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PsyPlanAccion> findPagePsyPlanAccion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return psyPlanAccionLogic.findPagePsyPlanAccion(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyPlanAccion() throws Exception {
        return psyPlanAccionLogic.findTotalNumberPsyPlanAccion();
    }

    public List<PsyPlanEstrategia> getPsyPlanEstrategia()
        throws Exception {
        return psyPlanEstrategiaLogic.getPsyPlanEstrategia();
    }

    public void savePsyPlanEstrategia(PsyPlanEstrategia entity, List<String> estrategiasTarget)
        throws Exception {
        psyPlanEstrategiaLogic.savePsyPlanEstrategia(entity, estrategiasTarget);
    }

    public void deletePsyPlanEstrategia(PsyPlanEstrategia entity)
        throws Exception {
        psyPlanEstrategiaLogic.deletePsyPlanEstrategia(entity);
    }

    public void updatePsyPlanEstrategia(PsyPlanEstrategia entity, List<String> estrategiasTarget)
        throws Exception {
        psyPlanEstrategiaLogic.updatePsyPlanEstrategia(entity, estrategiasTarget);
    }

    public PsyPlanEstrategia getPsyPlanEstrategia(Long plesCodigo)
        throws Exception {
        PsyPlanEstrategia psyPlanEstrategia = null;

        try {
            psyPlanEstrategia = psyPlanEstrategiaLogic.getPsyPlanEstrategia(plesCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyPlanEstrategia;
    }

    public List<PsyPlanEstrategia> findByCriteriaInPsyPlanEstrategia(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyPlanEstrategiaLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsyPlanEstrategia> findPagePsyPlanEstrategia(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyPlanEstrategiaLogic.findPagePsyPlanEstrategia(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyPlanEstrategia() throws Exception {
        return psyPlanEstrategiaLogic.findTotalNumberPsyPlanEstrategia();
    }

    public List<PsyPlanEstrategiaDTO> getDataPsyPlanEstrategia()
        throws Exception {
        return psyPlanEstrategiaLogic.getDataPsyPlanEstrategia();
    }

    public List<PsyPlanEstrategico> getPsyPlanEstrategico()
        throws Exception {
        return psyPlanEstrategicoLogic.getPsyPlanEstrategico();
    }

    public void savePsyPlanEstrategico(PsyPlanEstrategico entity, PsyEmpresa empresa)
        throws Exception {
        psyPlanEstrategicoLogic.savePsyPlanEstrategico(entity, empresa);
    }

    public void deletePsyPlanEstrategico(PsyPlanEstrategico entity)
        throws Exception {
        psyPlanEstrategicoLogic.deletePsyPlanEstrategico(entity);
    }

    public void updatePsyPlanEstrategico(PsyPlanEstrategico entity)
        throws Exception {
        psyPlanEstrategicoLogic.updatePsyPlanEstrategico(entity);
    }

    public PsyPlanEstrategico getPsyPlanEstrategico(Long pestCodigo)
        throws Exception {
        PsyPlanEstrategico psyPlanEstrategico = null;

        try {
            psyPlanEstrategico = psyPlanEstrategicoLogic.getPsyPlanEstrategico(pestCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyPlanEstrategico;
    }

    public List<PsyPlanEstrategico> findByCriteriaInPsyPlanEstrategico(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyPlanEstrategicoLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsyPlanEstrategico> findPagePsyPlanEstrategico(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyPlanEstrategicoLogic.findPagePsyPlanEstrategico(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyPlanEstrategico() throws Exception {
        return psyPlanEstrategicoLogic.findTotalNumberPsyPlanEstrategico();
    }

    public List<PsyPlanEstrategicoDTO> getDataPsyPlanEstrategico(PsyEmpresa empresa)
        throws Exception {
        return psyPlanEstrategicoLogic.getDataPsyPlanEstrategico(empresa);
    }

    public List<PsyPresupuesto> getPsyPresupuesto() throws Exception {
        return psyPresupuestoLogic.getPsyPresupuesto();
    }

    public void deletePsyPresupuesto(PsyPresupuesto entity)
        throws Exception {
        psyPresupuestoLogic.deletePsyPresupuesto(entity);
    }

    public void updatePsyPresupuesto(PsyPresupuesto entity)
        throws Exception {
        psyPresupuestoLogic.updatePsyPresupuesto(entity);
    }

    public PsyPresupuesto getPsyPresupuesto(Long presCodigo)
        throws Exception {
        PsyPresupuesto psyPresupuesto = null;

        try {
            psyPresupuesto = psyPresupuestoLogic.getPsyPresupuesto(presCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyPresupuesto;
    }

    public List<PsyPresupuesto> findByCriteriaInPsyPresupuesto(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyPresupuestoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PsyPresupuesto> findPagePsyPresupuesto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return psyPresupuestoLogic.findPagePsyPresupuesto(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyPresupuesto() throws Exception {
        return psyPresupuestoLogic.findTotalNumberPsyPresupuesto();
    }

    public List<PsyPresupuestoDTO> getDataPsyPresupuesto(PsyEmpresa empresa)
        throws Exception {
        return psyPresupuestoLogic.getDataPsyPresupuesto(empresa);
    }

    public List<PsyProvincia> getPsyProvincia() throws Exception {
        return psyProvinciaLogic.getPsyProvincia();
    }

    public void savePsyProvincia(PsyProvincia entity) throws Exception {
        psyProvinciaLogic.savePsyProvincia(entity);
    }

    public void deletePsyProvincia(PsyProvincia entity)
        throws Exception {
        psyProvinciaLogic.deletePsyProvincia(entity);
    }

    public void updatePsyProvincia(PsyProvincia entity)
        throws Exception {
        psyProvinciaLogic.updatePsyProvincia(entity);
    }

    public PsyProvincia getPsyProvincia(Long provCodigo)
        throws Exception {
        PsyProvincia psyProvincia = null;

        try {
            psyProvincia = psyProvinciaLogic.getPsyProvincia(provCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyProvincia;
    }

    public List<PsyProvincia> findByCriteriaInPsyProvincia(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return psyProvinciaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PsyProvincia> findPagePsyProvincia(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return psyProvinciaLogic.findPagePsyProvincia(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyProvincia() throws Exception {
        return psyProvinciaLogic.findTotalNumberPsyProvincia();
    }

    public List<PsyProvinciaDTO> getDataPsyProvincia()
        throws Exception {
        return psyProvinciaLogic.getDataPsyProvincia();
    }

    public List<PsyResponsable> getPsyResponsable() throws Exception {
        return psyResponsableLogic.getPsyResponsable();
    }

    public void savePsyResponsable(PsyResponsable entity)
        throws Exception {
        psyResponsableLogic.savePsyResponsable(entity);
    }

    public void deletePsyResponsable(PsyResponsable entity)
        throws Exception {
        psyResponsableLogic.deletePsyResponsable(entity);
    }

    public void updatePsyResponsable(PsyResponsable entity)
        throws Exception {
        psyResponsableLogic.updatePsyResponsable(entity);
    }

    public PsyResponsable getPsyResponsable(Long respCodigo)
        throws Exception {
        PsyResponsable psyResponsable = null;

        try {
            psyResponsable = psyResponsableLogic.getPsyResponsable(respCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyResponsable;
    }

    public List<PsyResponsable> findByCriteriaInPsyResponsable(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyResponsableLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PsyResponsable> findPagePsyResponsable(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return psyResponsableLogic.findPagePsyResponsable(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyResponsable() throws Exception {
        return psyResponsableLogic.findTotalNumberPsyResponsable();
    }

    public List<PsySeguimientoTarea> getPsySeguimientoTarea()
        throws Exception {
        return psySeguimientoTareaLogic.getPsySeguimientoTarea();
    }

    public void savePsySeguimientoTarea(PsySeguimientoTarea entity)
        throws Exception {
        psySeguimientoTareaLogic.savePsySeguimientoTarea(entity);
    }

    public void deletePsySeguimientoTarea(PsySeguimientoTarea entity)
        throws Exception {
        psySeguimientoTareaLogic.deletePsySeguimientoTarea(entity);
    }

    public void updatePsySeguimientoTarea(PsySeguimientoTarea entity)
        throws Exception {
        psySeguimientoTareaLogic.updatePsySeguimientoTarea(entity);
    }

    public PsySeguimientoTarea getPsySeguimientoTarea(Long setaCodigo)
        throws Exception {
        PsySeguimientoTarea psySeguimientoTarea = null;

        try {
            psySeguimientoTarea = psySeguimientoTareaLogic.getPsySeguimientoTarea(setaCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psySeguimientoTarea;
    }

    public List<PsySeguimientoTarea> findByCriteriaInPsySeguimientoTarea(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psySeguimientoTareaLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsySeguimientoTarea> findPagePsySeguimientoTarea(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psySeguimientoTareaLogic.findPagePsySeguimientoTarea(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsySeguimientoTarea() throws Exception {
        return psySeguimientoTareaLogic.findTotalNumberPsySeguimientoTarea();
    }

    public List<PsySeguimientoTareaDTO> getDataPsySeguimientoTarea()
        throws Exception {
        return psySeguimientoTareaLogic.getDataPsySeguimientoTarea();
    }

    public List<PsyTarea> getPsyTarea() throws Exception {
        return psyTareaLogic.getPsyTarea();
    }

    public void savePsyTarea(PsyTarea entity, String somResponsable) throws Exception {
        psyTareaLogic.savePsyTarea(entity, somResponsable);
    }

    public void deletePsyTarea(PsyTarea entity) throws Exception {
        psyTareaLogic.deletePsyTarea(entity);
    }

    public void updatePsyTarea(PsyTarea entity) throws Exception {
        psyTareaLogic.updatePsyTarea(entity);
    }

    public PsyTarea getPsyTarea(Long tareCodigo) throws Exception {
        PsyTarea psyTarea = null;

        try {
            psyTarea = psyTareaLogic.getPsyTarea(tareCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyTarea;
    }

    public List<PsyTarea> findByCriteriaInPsyTarea(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return psyTareaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PsyTarea> findPagePsyTarea(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return psyTareaLogic.findPagePsyTarea(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberPsyTarea() throws Exception {
        return psyTareaLogic.findTotalNumberPsyTarea();
    }

    public List<PsyTareaDTO> getDataPsyTarea() throws Exception {
        return psyTareaLogic.getDataPsyTarea();
    }

    public List<PsyTipoItemPresupuesto> getPsyTipoItemPresupuesto()
        throws Exception {
        return psyTipoItemPresupuestoLogic.getPsyTipoItemPresupuesto();
    }

    public void savePsyTipoItemPresupuesto(PsyTipoItemPresupuesto entity)
        throws Exception {
        psyTipoItemPresupuestoLogic.savePsyTipoItemPresupuesto(entity);
    }

    public void deletePsyTipoItemPresupuesto(PsyTipoItemPresupuesto entity)
        throws Exception {
        psyTipoItemPresupuestoLogic.deletePsyTipoItemPresupuesto(entity);
    }

    public void updatePsyTipoItemPresupuesto(PsyTipoItemPresupuesto entity)
        throws Exception {
        psyTipoItemPresupuestoLogic.updatePsyTipoItemPresupuesto(entity);
    }

    public PsyTipoItemPresupuesto getPsyTipoItemPresupuesto(Long tiipCodigo)
        throws Exception {
        PsyTipoItemPresupuesto psyTipoItemPresupuesto = null;

        try {
            psyTipoItemPresupuesto = psyTipoItemPresupuestoLogic.getPsyTipoItemPresupuesto(tiipCodigo);
        } catch (Exception e) {
            throw e;
        }

        return psyTipoItemPresupuesto;
    }

    public List<PsyTipoItemPresupuesto> findByCriteriaInPsyTipoItemPresupuesto(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyTipoItemPresupuestoLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsyTipoItemPresupuesto> findPagePsyTipoItemPresupuesto(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyTipoItemPresupuestoLogic.findPagePsyTipoItemPresupuesto(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyTipoItemPresupuesto()
        throws Exception {
        return psyTipoItemPresupuestoLogic.findTotalNumberPsyTipoItemPresupuesto();
    }

    public List<PsyTipoItemPresupuestoDTO> getDataPsyTipoItemPresupuesto()
        throws Exception {
        return psyTipoItemPresupuestoLogic.getDataPsyTipoItemPresupuesto();
    }
    public List<PsyTablaEridaDTO> consultarTablaErida(Long mearCodigo,Long asamCodigo) throws Exception{
    	return psyDetalleEridaLogic.consultarTablaErida(mearCodigo, asamCodigo);
    }

	
	public List<SelectItem> cargarOneMenuAsuntoAmbienta() throws Exception {
		
		return psyAsuntoAmbientalLogic.cargarOneMenuAsuntoAmbienta();
	}

	@Override
	public void guardarTablaErida(List<PsyTablaEridaDTO> tablaEridaDTO,
			Long maerCodigo, Long asamCodigo) throws Exception {
		psyDetalleEridaLogic.guardarTablaErida(tablaEridaDTO, maerCodigo, asamCodigo);
		
	}

	@Override
	public List<Double> calcularTotalesTablaErida(
			List<PsyTablaEridaDTO> listPsyTablaEridaDTO) throws Exception {
		// TODO Auto-generated method stub
		return psyDetalleEridaLogic.calcularTotalesTablaErida(listPsyTablaEridaDTO);
	}
	
	@Override
    public List<PsyPlanEstrategico> consultarPlanEstrategicoEmpresa(PsyEmpresa empresa, String estado)
            throws Exception {
            return psyPlanEstrategicoLogic.consultarPlanEstrategicoEmpresa(empresa, estado);
    }
    
    @Override
    public PsyObjetivoPlan consultarObjetivoPlan(PsyPlanEstrategico planEstrategico, String estado)
            throws Exception {
            return psyObjetivoPlanLogic.consultarObjetivoPlan(planEstrategico, estado);
    }
    
    @Override
    public  List<PsyDetalleObjetivoPlan> consultarDetalleObjetivoPlan(PsyObjetivoPlan objetivoPlan)
            throws Exception {
            return psyDetalleObjetivoPlanLogic.consultarDetalleObjetivoPlan(objetivoPlan);
    }
    
    @Override
	public List<PsyPriorizacionAsuntoAmbientalDTO> PriorizacionAsuntoAmbiental(
			Long mearCodigo) throws Exception {
		// TODO Auto-generated method stub
		return psyDetalleEridaLogic.PriorizacionAsuntoAmbiental(mearCodigo);
	}

	@Override
	public List<PsyPriorizacionImpactosAmbientalesDTO> PriorizacionImpactosAmbientales(
			Long mearCodigo) throws Exception {
		// TODO Auto-generated method stub
		return psyDetalleEridaLogic.PriorizacionImpactosAmbientales(mearCodigo);
	}    
    
    
    //Trae los responsables por la empresa
    @Override
    public List<PsyResponsable> getPsyResponsableByEmpresa (Long emprCodigo)
    		throws Exception {
    	return psyResponsableLogic.getPsyResponsableByEmpresa(emprCodigo);
    }
    
    //Trae el plan de accion por el nombre
    @Override
    public PsyPlanAccion getPsyPlanAccionByName(String nombre)
    		throws Exception {
     return psyPlanAccionLogic.getPsyPlanAccionByName(nombre);	
    }
    
    //Trae los nombres de las estrategias
    public List<String> getEstrategias(PsyEmpresa empresa, Long placCodigo) throws Exception {
    	return psyPlanEstrategiaLogic.getEstrategias(empresa, placCodigo);
    }
    
    @Override
    public  List<PsyDetalleObjetivoPlan> guardarDetalleObjetivoPlan(List<PsyDetalleObjetivoPlan> listaDetalle, PsyObjetivoPlan objetivoPlan,
    		String nombrevacio, String descripcionVacio)
            throws Exception {
            return psyDetalleObjetivoPlanLogic.guardarDetalleObjetivoPlan(listaDetalle, objetivoPlan,nombrevacio,descripcionVacio);
    }
   
    @Override
    public PsyMapaEstrategico consultarMapaEstrategico(PsyPlanEstrategico planEstrategico, String estado)
            throws Exception {
            return psyMapaEstrategicoLogic.consultarMapaEstrategico(planEstrategico, estado);
    }
    
    
    @Override
    public List<PsyDetalleMatrizCorrelacionDTO> consultarMatrizRelacionMapaEstrategico(PsyMapaEstrategico mapaEstrategico, List<PsyDetalleObjetivoPlan> lstObjetivosCorportativo, Integer cantidadObjetivos,List<PsyImpactoAmbiental> lstImpactoAmbiental)
            throws Exception {
            return psyDetalleMapaEstrategicoLogic.consultarMatrizRelacionMapaEstrategico(mapaEstrategico,lstObjetivosCorportativo, cantidadObjetivos, lstImpactoAmbiental);
    }
    
    public List<PsyMatrizEridaDTO> consultarExisteEridaPlanEstrategico(Long pestCodigo) throws Exception  {
    	return psyMatrizEridaLogic.consultarExisteEridaPlanEstrategico(pestCodigo);
	}
    
    
    //Trae los seguimientos de una tarea por el codigo
    public List<PsySeguimientoTareaDTO> getDataPsySeguimientoTareaByTareCodigo(Long tareCodigo) throws Exception{
    	return psySeguimientoTareaLogic.getDataPsySeguimientoTareaByTareCodigo(tareCodigo);
    }
    
    @Override
    public List<PsyDetalleObjetivoPlan> consultarListaCompletaObjetivos(List<PsyDetalleObjetivoPlan> lstObjetivoPlan, Integer cantidadOjetivos) throws Exception{
    	
    	return psyDetalleObjetivoPlanLogic.consultarListaCompletaObjetivos(lstObjetivoPlan, cantidadOjetivos);
    }
    
    @Override
    public void guardarDetalleMapaEstrategico(PsyMapaEstrategico  mapaEstrategico, List<PsyDetalleMatrizCorrelacionDTO>  lstMatriz, PsyImpactoAmbiental impactoAmbiental) throws Exception{
    	
    	psyDetalleMapaEstrategicoLogic.guardarDetalleMapaEstrategico(mapaEstrategico, lstMatriz, impactoAmbiental);
    }
    
    public List<PsyAsuntoAmbiental>  terminarErida(Long mearCodigo) throws Exception{
    	return psyMatrizEridaLogic.terminarErida(mearCodigo);
    }
    public void generarPlanAccionDifinitivo(Long placCodigo) throws Exception{
    	psyPlanAccionLogic.generarPlanAccionDifinitivo(placCodigo);
    }
    public void generarPresupuestoDefinitivo(Long presCodigo,Long placCodigo) throws Exception{
    	psyPresupuestoLogic.generarPresupuestoDefinitivo(presCodigo, placCodigo);
    }
    
    @Override
    public List<PsyIpuDTO> consultarIpu(PsyPlanAccion  planAccion, String tipoIpu) throws Exception{    	
    	return psyIpuLogic.consultarIpu(planAccion, tipoIpu);
    }
    
    @Override
    public List<PsyIpuDTO> generarIpu(PsyPlanAccion  planAccion, String tipoIpu, Date fechaInforme, Long semanaInforme, List<PsyIpuDTO> lstIpu,
    		String logrosSignificativos, String logrosNoAlcanzados, String causasDesviacion, String accionesPropuestas, boolean actualizar) throws Exception{    	
    	return psyIpuLogic.generarIpu(planAccion, tipoIpu, fechaInforme, semanaInforme, lstIpu,logrosSignificativos,logrosNoAlcanzados, causasDesviacion,accionesPropuestas, actualizar);
    }
   
    @Override
    public List<PsyIpuDTO> generarIpuPresupuesto(PsyPlanAccion  planAccion, String tipoIpu, Date fechaInforme, Long semanaInforme, List<PsyIpuDTO> lstIpu,
    		String logrosSignificativos, String logrosNoAlcanzados, String causasDesviacion, String accionesPropuestas) throws Exception{    	
    	return psyIpuLogic.generarIpuPresupuesto(planAccion, tipoIpu, fechaInforme, semanaInforme, lstIpu,logrosSignificativos,logrosNoAlcanzados, causasDesviacion,accionesPropuestas);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<PsyIpuDTO> consultarIpuHastaPeriodo(PsyPlanAccion  planAccion, String tipoIpu, String periodo) throws Exception{    	
    	return psyIpuLogic.consultarIpuHastaPeriodo(planAccion, tipoIpu, periodo);
    }
    
  //Trae los items de presupuesto por el codigo del presupuesto
    @Override
    public List<PsyItemPresupuestoDTO> getDataPsyItemPresupuestoByPresCodigo(Long presCodigo) throws Exception {
    	return psyItemPresupuestoLogic.getDataPsyItemPresupuestoByPresCodigo(presCodigo);
    }
    
    //Trae las tareas de un plan de acion por el codigo del plan
    @Override
    public List<PsyTareaDTO> getDataPsyTareaByPsyPlanAccion(Long placCodigo) throws Exception {
    	return psyTareaLogic.getDataPsyTareaByPsyPlanAccion(placCodigo);
    }
    
    //Verifica si el se ha  diligenciado el mapa estrategico para crear un plan de accion
    @Override
    public boolean verificarMapaEstrategico(PsyEmpresa empresa) throws Exception {
    	return psyPlanAccionLogic.verificarMapaEstrategico(empresa);
    }
    
    @Override
    public boolean verificarPlanEstrategico(PsyEmpresa empresa) throws Exception {
    	return psyPlanAccionLogic.verificarPlanEstrategico(empresa);
    }
    
    @Override
    public List<String> getEstrategiasSeleccionadas(PsyEmpresa empresa, Long placCodigo) throws Exception {
    	return psyPlanEstrategiaLogic.getEstrategiasSeleccionadas(empresa, placCodigo);
    }
    
    @Override
    public List<String> getEstrategiasRestantes(PsyEmpresa empresa, Long placCodigo, List<String> estrategiasSeleccionadas) throws Exception {
    	return psyPlanEstrategiaLogic.getEstrategiasRestantes(empresa, placCodigo, estrategiasSeleccionadas);
    }
    
    @Override
    public void savePsyPlanAccionPsyPlanEstrategia(PsyPlanAccion entity, List<String> estrategiasTarget, PsyEmpresa empresa)
            throws Exception {
    	psyPlanAccionLogic.savePsyPlanAccionPsyPlanEstrategia(entity, estrategiasTarget, empresa);
    }
    
    @Override
    public List<String> getEstrategiasBtnNew(PsyEmpresa empresa) throws Exception {
    	return psyPlanEstrategiaLogic.getEstrategiasBtnNew(empresa);
    }
    
    @Override
    public String consultaPlanEstrategicoBtnNew(PsyEmpresa empresa) throws Exception{
    	return psyPlanAccionLogic.consultaPlanEstrategicoBtnNew(empresa);
    }
    
    @Override
    public String consultaPlanEstrategico(PsyEmpresa empresa, Long placCodigo) throws Exception{
    	return psyPlanAccionLogic.consultaPlanEstrategico(empresa, placCodigo);
    }
    
    @Override
    public List<PsyDetalleMapaEstrategico> consultarDetalleMapaEstrategico(PsyMapaEstrategico mapaEstrategico) throws Exception {
    	return psyDetalleMapaEstrategicoLogic.consultarDetalleMapaEstrategico(mapaEstrategico);
    }
    
    @Override
    public void updatePsyPlanAccionPlanEstrategia(PsyPlanAccion entity, List<String> estrategiasTarget, PsyEmpresa empresa)
            throws Exception {
    	psyPlanAccionLogic.updatePsyPlanAccionPlanEstrategia(entity, estrategiasTarget, empresa);
    }
    
    @Override
    public List<PsyPlanAccionDTO> getDataPsyPlanAccion(PsyEmpresa empresa)
            throws Exception {
            return psyPlanAccionLogic.getDataPsyPlanAccion(empresa);
        }
    @Override
    public List<PsyResponsableDTO> getDataPsyResponsable(PsyEmpresa empresa)
            throws Exception {
            return psyResponsableLogic.getDataPsyResponsable(empresa);
        }
    
    @Override
    public List<PsyPlanAccion> findPsyPlanAccionByEmpresa(PsyEmpresa empresa) throws Exception {
    	return psyTareaLogic.findPsyPlanAccionByEmpresa(empresa);
    }
    
    public void savePsyPresupuesto(PsyPresupuesto entity)
            throws Exception {
            psyPresupuestoLogic.savePsyPresupuesto(entity);
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<PsyPlanAccion> consultarPlanesAccion(PsyEmpresa empresa, PsyPlanEstrategico planEstrategico , String estadoIniciado, String estadoPresupuestado)
            throws Exception {
    	return psyPlanAccionLogic.consultarPlanesAccion(empresa, planEstrategico, estadoIniciado,estadoPresupuestado);
    }
    
    @Override
    public List<PsyIpuDTO> generarIpuLogico(PsyPlanAccion  planAccion, String tipoIpu, Date fechaInforme, Long semanaInforme, List<PsyIpuDTO> lstIpu,
    		String logrosSignificativos, String logrosNoAlcanzados, String causasDesviacion, String accionesPropuestas, boolean actualizar) throws Exception{
    	return psyIpuLogic.generarIpuLogico(planAccion, tipoIpu, fechaInforme, semanaInforme, lstIpu, logrosSignificativos, logrosNoAlcanzados, causasDesviacion, accionesPropuestas, actualizar);
    }
    
    @Override
    public PsyMatrizErida consultarMatrizErida(PsyPlanEstrategico planEstrategico) throws Exception{
    	return psyMatrizEridaLogic.consultarMatrizErida(planEstrategico);
    }
    
    @Override
    public List<PsyIpuDTO> generarIpuPresupuestoLogico(PsyPlanAccion  planAccion, String tipoIpu, Date fechaInforme, Long semanaInforme, List<PsyIpuDTO> lstIpu,
    		String logrosSignificativos, String logrosNoAlcanzados, String causasDesviacion, String accionesPropuestas) throws Exception{    	
    	return psyIpuLogic.generarIpuPresupuestoLogico(planAccion, tipoIpu, fechaInforme, semanaInforme, lstIpu,logrosSignificativos,logrosNoAlcanzados, causasDesviacion,accionesPropuestas);
    }
    
//  INICIO MODULO DE INDICADORES
    
    public List<PsySubtema> getPsySubtema() throws Exception {
        return psySubtemaLogic.getPsySubtema();
    }

    public void savePsySubtema(PsySubtema entity) throws Exception {
        psySubtemaLogic.savePsySubtema(entity);
    }

    public void deletePsySubtema(PsySubtema entity) throws Exception {
        psySubtemaLogic.deletePsySubtema(entity);
    }

    public void updatePsySubtema(PsySubtema entity) throws Exception {
        psySubtemaLogic.updatePsySubtema(entity);
    }

    public PsySubtema getPsySubtema(Long codigo) throws Exception {
        PsySubtema psySubtema = null;

        try {
            psySubtema = psySubtemaLogic.getPsySubtema(codigo);
        } catch (Exception e) {
            throw e;
        }

        return psySubtema;
    }

    public List<PsySubtema> findByCriteriaInPsySubtema(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return psySubtemaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PsySubtema> findPagePsySubtema(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return psySubtemaLogic.findPagePsySubtema(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsySubtema() throws Exception {
        return psySubtemaLogic.findTotalNumberPsySubtema();
    }

    public List<PsySubtemaDTO> getDataPsySubtema() throws Exception {
        return psySubtemaLogic.getDataPsySubtema();
    }
    
    public List<PsyTema> getPsyTema() throws Exception {
        return psyTemaLogic.getPsyTema();
    }

    public void savePsyTema(PsyTema entity) throws Exception {
        psyTemaLogic.savePsyTema(entity);
    }

    public void deletePsyTema(PsyTema entity) throws Exception {
        psyTemaLogic.deletePsyTema(entity);
    }

    public void updatePsyTema(PsyTema entity) throws Exception {
        psyTemaLogic.updatePsyTema(entity);
    }

    public PsyTema getPsyTema(Long codigo) throws Exception {
        PsyTema psyTema = null;

        try {
            psyTema = psyTemaLogic.getPsyTema(codigo);
        } catch (Exception e) {
            throw e;
        }

        return psyTema;
    }

    public List<PsyTema> findByCriteriaInPsyTema(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return psyTemaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PsyTema> findPagePsyTema(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return psyTemaLogic.findPagePsyTema(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberPsyTema() throws Exception {
        return psyTemaLogic.findTotalNumberPsyTema();
    }

    public List<PsyTemaDTO> getDataPsyTema() throws Exception {
        return psyTemaLogic.getDataPsyTema();
    }
    
    public List<PsyIndicador> getPsyIndicador() throws Exception {
        return psyIndicadorLogic.getPsyIndicador();
    }

    public void savePsyIndicador(PsyIndicador entity) throws Exception {
        psyIndicadorLogic.savePsyIndicador(entity);
    }

    public void deletePsyIndicador(PsyIndicador entity)
        throws Exception {
        psyIndicadorLogic.deletePsyIndicador(entity);
    }

    public void updatePsyIndicador(PsyIndicador entity)
        throws Exception {
        psyIndicadorLogic.updatePsyIndicador(entity);
    }

    public PsyIndicador getPsyIndicador(Long codigo) throws Exception {
        PsyIndicador psyIndicador = null;

        try {
            psyIndicador = psyIndicadorLogic.getPsyIndicador(codigo);
        } catch (Exception e) {
            throw e;
        }

        return psyIndicador;
    }

    public List<PsyIndicador> findByCriteriaInPsyIndicador(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return psyIndicadorLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PsyIndicador> findPagePsyIndicador(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return psyIndicadorLogic.findPagePsyIndicador(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyIndicador() throws Exception {
        return psyIndicadorLogic.findTotalNumberPsyIndicador();
    }

    public List<PsyEvaluarIndicadoresDTO> getDataPsyIndicador(PsyEmpresa empresa,Long imamCodigo, PsyPlanEstrategicoAmbiental planEstrategicoAmbiental)
        throws Exception {
        return psyIndicadorLogic.getDataPsyIndicador(empresa, imamCodigo, planEstrategicoAmbiental);
    }
    
    public List<PsyObjetivoAmbiental> getPsyObjetivoAmbiental()
            throws Exception {
            return psyObjetivoAmbientalLogic.getPsyObjetivoAmbiental();
	}

    public void savePsyObjetivoAmbiental(PsyObjetivoAmbiental entity)
        throws Exception {
        psyObjetivoAmbientalLogic.savePsyObjetivoAmbiental(entity);
    }

    public void deletePsyObjetivoAmbiental(PsyObjetivoAmbiental entity)
        throws Exception {
        psyObjetivoAmbientalLogic.deletePsyObjetivoAmbiental(entity);
    }

    public void updatePsyObjetivoAmbiental(PsyObjetivoAmbiental entity)
        throws Exception {
        psyObjetivoAmbientalLogic.updatePsyObjetivoAmbiental(entity);
    }

    public PsyObjetivoAmbiental getPsyObjetivoAmbiental(Long codigo)
        throws Exception {
        PsyObjetivoAmbiental psyObjetivoAmbiental = null;

        try {
            psyObjetivoAmbiental = psyObjetivoAmbientalLogic.getPsyObjetivoAmbiental(codigo);
        } catch (Exception e) {
            throw e;
        }

        return psyObjetivoAmbiental;
    }

    public List<PsyObjetivoAmbiental> findByCriteriaInPsyObjetivoAmbiental(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyObjetivoAmbientalLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsyObjetivoAmbiental> findPagePsyObjetivoAmbiental(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyObjetivoAmbientalLogic.findPagePsyObjetivoAmbiental(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyObjetivoAmbiental() throws Exception {
        return psyObjetivoAmbientalLogic.findTotalNumberPsyObjetivoAmbiental();
    }

    public List<PsyObjetivoAmbientalDTO> getDataPsyObjetivoAmbiental()
        throws Exception {
        return psyObjetivoAmbientalLogic.getDataPsyObjetivoAmbiental();
    }
    
    public List<PsyEvaluacionPeaIndicador> getPsyEvaluacionPeaIndicador()
            throws Exception {
            return psyEvaluacionPeaIndicadorLogic.getPsyEvaluacionPeaIndicador();
    }

    public void savePsyEvaluacionPeaIndicador(PsyEvaluacionPeaIndicador entity)
        throws Exception {
        psyEvaluacionPeaIndicadorLogic.savePsyEvaluacionPeaIndicador(entity);
    }

    public void deletePsyEvaluacionPeaIndicador(
        PsyEvaluacionPeaIndicador entity) throws Exception {
        psyEvaluacionPeaIndicadorLogic.deletePsyEvaluacionPeaIndicador(entity);
    }

    public void updatePsyEvaluacionPeaIndicador(
        PsyEvaluacionPeaIndicador entity) throws Exception {
        psyEvaluacionPeaIndicadorLogic.updatePsyEvaluacionPeaIndicador(entity);
    }

    public PsyEvaluacionPeaIndicador getPsyEvaluacionPeaIndicador(Long codigo)
        throws Exception {
        PsyEvaluacionPeaIndicador psyEvaluacionPeaIndicador = null;

        try {
            psyEvaluacionPeaIndicador = psyEvaluacionPeaIndicadorLogic.getPsyEvaluacionPeaIndicador(codigo);
        } catch (Exception e) {
            throw e;
        }

        return psyEvaluacionPeaIndicador;
    }

    public List<PsyEvaluacionPeaIndicador> findByCriteriaInPsyEvaluacionPeaIndicador(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyEvaluacionPeaIndicadorLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsyEvaluacionPeaIndicador> findPagePsyEvaluacionPeaIndicador(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyEvaluacionPeaIndicadorLogic.findPagePsyEvaluacionPeaIndicador(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyEvaluacionPeaIndicador()
        throws Exception {
        return psyEvaluacionPeaIndicadorLogic.findTotalNumberPsyEvaluacionPeaIndicador();
    }

    public List<PsyEvaluacionPeaIndicadorDTO> getDataPsyEvaluacionPeaIndicador(PsyEmpresa empresa)
        throws Exception {
        return psyEvaluacionPeaIndicadorLogic.getDataPsyEvaluacionPeaIndicador(empresa);
    }

    public List<PsyEvaluacionPeaObjetivo> getPsyEvaluacionPeaObjetivo()
        throws Exception {
        return psyEvaluacionPeaObjetivoLogic.getPsyEvaluacionPeaObjetivo();
    }

    public void savePsyEvaluacionPeaObjetivo(PsyEvaluacionPeaObjetivo entity)
        throws Exception {
        psyEvaluacionPeaObjetivoLogic.savePsyEvaluacionPeaObjetivo(entity);
    }

    public void deletePsyEvaluacionPeaObjetivo(PsyEvaluacionPeaObjetivo entity)
        throws Exception {
        psyEvaluacionPeaObjetivoLogic.deletePsyEvaluacionPeaObjetivo(entity);
    }

    public void updatePsyEvaluacionPeaObjetivo(PsyEvaluacionPeaObjetivo entity)
        throws Exception {
        psyEvaluacionPeaObjetivoLogic.updatePsyEvaluacionPeaObjetivo(entity);
    }

    public PsyEvaluacionPeaObjetivo getPsyEvaluacionPeaObjetivo(Long codigo)
        throws Exception {
        PsyEvaluacionPeaObjetivo psyEvaluacionPeaObjetivo = null;

        try {
            psyEvaluacionPeaObjetivo = psyEvaluacionPeaObjetivoLogic.getPsyEvaluacionPeaObjetivo(codigo);
        } catch (Exception e) {
            throw e;
        }

        return psyEvaluacionPeaObjetivo;
    }

    public List<PsyEvaluacionPeaObjetivo> findByCriteriaInPsyEvaluacionPeaObjetivo(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyEvaluacionPeaObjetivoLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsyEvaluacionPeaObjetivo> findPagePsyEvaluacionPeaObjetivo(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyEvaluacionPeaObjetivoLogic.findPagePsyEvaluacionPeaObjetivo(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyEvaluacionPeaObjetivo()
        throws Exception {
        return psyEvaluacionPeaObjetivoLogic.findTotalNumberPsyEvaluacionPeaObjetivo();
    }

    public List<PsyEvaluacionPeaObjetivoDTO> getDataPsyEvaluacionPeaObjetivo()
        throws Exception {
        return psyEvaluacionPeaObjetivoLogic.getDataPsyEvaluacionPeaObjetivo();
    }
    
    public List<PsyPlanEstrategicoAmbiental> getPsyPlanEstrategicoAmbiental()
            throws Exception {
            return psyPlanEstrategicoAmbientalLogic.getPsyPlanEstrategicoAmbiental();
    }

    public void savePsyPlanEstrategicoAmbiental(
        PsyPlanEstrategicoAmbiental entity) throws Exception {
        psyPlanEstrategicoAmbientalLogic.savePsyPlanEstrategicoAmbiental(entity);
    }

    public void deletePsyPlanEstrategicoAmbiental(
        PsyPlanEstrategicoAmbiental entity) throws Exception {
        psyPlanEstrategicoAmbientalLogic.deletePsyPlanEstrategicoAmbiental(entity);
    }

    public void updatePsyPlanEstrategicoAmbiental(
        PsyPlanEstrategicoAmbiental entity) throws Exception {
        psyPlanEstrategicoAmbientalLogic.updatePsyPlanEstrategicoAmbiental(entity);
    }

    public PsyPlanEstrategicoAmbiental getPsyPlanEstrategicoAmbiental(
        Long codigo) throws Exception {
        PsyPlanEstrategicoAmbiental psyPlanEstrategicoAmbiental = null;

        try {
            psyPlanEstrategicoAmbiental = psyPlanEstrategicoAmbientalLogic.getPsyPlanEstrategicoAmbiental(codigo);
        } catch (Exception e) {
            throw e;
        }

        return psyPlanEstrategicoAmbiental;
    }

    public List<PsyPlanEstrategicoAmbiental> findByCriteriaInPsyPlanEstrategicoAmbiental(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return psyPlanEstrategicoAmbientalLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PsyPlanEstrategicoAmbiental> findPagePsyPlanEstrategicoAmbiental(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return psyPlanEstrategicoAmbientalLogic.findPagePsyPlanEstrategicoAmbiental(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPsyPlanEstrategicoAmbiental()
        throws Exception {
        return psyPlanEstrategicoAmbientalLogic.findTotalNumberPsyPlanEstrategicoAmbiental();
    }

    public List<PsyPlanEstrategicoAmbientalDTO> getDataPsyPlanEstrategicoAmbiental(PsyEmpresa empresa)
        throws Exception {
        return psyPlanEstrategicoAmbientalLogic.getDataPsyPlanEstrategicoAmbiental(empresa);
    }
    
//  FIN MODULO DE INDICADORES  
    
    public PsyPlanEstrategico getPlanEstrategicoActivoByPEA(PsyEmpresa empresa) throws Exception{
    	return psyPlanEstrategicoAmbientalLogic.getPlanEstrategicoActivoByPEA(empresa);
    }
    
    public PsyPlanEstrategico getPlanEstrategicoByPEA(PsyEmpresa empresa, Long codigo) throws Exception{
    	return psyPlanEstrategicoAmbientalLogic.getPlanEstrategicoByPEA(empresa, codigo);
    }
    
    public PsyPlanEstrategico getPlanEstrategicoByPEA(PsyEmpresa empresa, String nombre) throws Exception {
    	return psyPlanEstrategicoAmbientalLogic.getPlanEstrategicoByPEA(empresa, nombre);	
    }
    
    public List<PsyMatrizEncuesta> consultaAsociacionDeCuestionarios(PsyEmpresa empresa) throws Exception{
    	return psyPlanEstrategicoLogic.consultaAsociacionDeCuestionarios(empresa);
    }
    
    public Long countIndicadoresPorImpacto(Long codigo) throws Exception{
    	return psyImpactoAmbientalLogic.countIndicadoresPorImpacto(codigo);
    }
    
    public Long countSubTemasPorImpacto(Long codigo) throws Exception{
    	return psyImpactoAmbientalLogic.countSubTemasPorImpacto(codigo);
    }
    
    public Long countTemasPorImpacto(Long codigo) throws Exception{
    	return psyImpactoAmbientalLogic.countTemasPorImpacto(codigo);
    }
    
    public List<PsyImpactoAmbiental> consultaImpactosAmbientalesSeleccionados(PsyEmpresa empresa) throws Exception {
    	return psyImpactoAmbientalLogic.consultaImpactosAmbientalesSeleccionados(empresa);
    }
    
    public boolean validarGrupoRepetido(List<PsyDetalleObjetivoPlan> lstObjetivoCorporativo) throws Exception{
    	return psyObjetivoAmbientalLogic.validarGrupoRepetido(lstObjetivoCorporativo);
    }
    
    public List<PsyImpactoAmbientalDTO> getDataPsyImpactoAmbientalGestion()
            throws Exception {
    	return psyImpactoAmbientalLogic.getDataPsyImpactoAmbientalGestion();
    }
    
    public List<PsyProvincia> consultarProvinciasPorPais(Long paisCodigo) throws Exception {
    	return psyCiudadLogic.consultarProvinciasPorPais(paisCodigo);
    }
    
    public PsyPais consultarPaisPorProvincias(Long provCodigo) throws Exception {
    	return psyCiudadLogic.consultarPaisPorProvincias(provCodigo);
    }
    
    public List<PsyIndicadorGestionDTO> getDataPsyIndicadorGestion() throws Exception {
    	return psyIndicadorLogic.getDataPsyIndicadorGestion();
    }
    
    public List<PsySubtema> consultarSubTemasPorTema(Long temaCodigo) throws Exception {
    	return psyIndicadorLogic.consultarSubTemasPorTema(temaCodigo);
    }
    
    public void registrar(PsyPersona persona, PsyEmpresa empresa, String nombreResponsable, String apellidoResponsable, String contrasena, String confirmaContrasena) throws Exception {
    	registrarLogic.registrar(persona, empresa, nombreResponsable, apellidoResponsable, contrasena, confirmaContrasena);
    }
    
    public List<PsyCiudad> consultarCiudadesPorProvincia(Long provCodigo) throws Exception {
    	return psyCiudadLogic.consultarCiudadesPorProvincia(provCodigo);
    }
    
    public List<PsyPlanEstrategicoAmbiental> getPEA(PsyEmpresa empresa) throws Exception {
    	return psyPlanEstrategicoAmbientalLogic.getPEA(empresa);
    }
    
    public void evaluarIndicador(PsyEvaluacionPeaIndicador evaIndicador, PsyEvaluacionPeaObjetivo evaObjetivo) 
    		throws Exception {
    	psyIndicadorLogic.evaluarIndicador(evaIndicador, evaObjetivo);
    }
    
    public List<PsyObjetivoAmbientalDTO> getDataPsyObjetivoAmbientalEvaluado(PsyEmpresa empresa)
            throws Exception {
    	return psyObjetivoAmbientalLogic.getDataPsyObjetivoAmbientalEvaluado(empresa);
    }
    
    public List<RespuestaEncuestasDTO> consultarResultadoEncuestas(PsyEmpresa empresa) throws Exception {
    	
    	return psyMatrizEncuestaLogic.consultarResultadoEncuestas(empresa);
    }
    
    /*Metodos de PsyObjetivoImpacto*/
    
    public List<PsyObjetivoImpacto> getPsyObjetivoImpacto()
            throws Exception {
            return psyObjetivoImpactoLogic.getPsyObjetivoImpacto();
        }

        public void savePsyObjetivoImpacto(PsyObjetivoImpacto entity)
            throws Exception {
            psyObjetivoImpactoLogic.savePsyObjetivoImpacto(entity);
        }

        public void deletePsyObjetivoImpacto(PsyObjetivoImpacto entity)
            throws Exception {
            psyObjetivoImpactoLogic.deletePsyObjetivoImpacto(entity);
        }

        public void updatePsyObjetivoImpacto(PsyObjetivoImpacto entity)
            throws Exception {
            psyObjetivoImpactoLogic.updatePsyObjetivoImpacto(entity);
        }

        public PsyObjetivoImpacto getPsyObjetivoImpacto(Long obimCodigo)
            throws Exception {
            PsyObjetivoImpacto psyObjetivoImpacto = null;

            try {
                psyObjetivoImpacto = psyObjetivoImpactoLogic.getPsyObjetivoImpacto(obimCodigo);
            } catch (Exception e) {
                throw e;
            }

            return psyObjetivoImpacto;
        }

        public List<PsyObjetivoImpacto> findByCriteriaInPsyObjetivoImpacto(
            Object[] variables, Object[] variablesBetween,
            Object[] variablesBetweenDates) throws Exception {
            return psyObjetivoImpactoLogic.findByCriteria(variables,
                variablesBetween, variablesBetweenDates);
        }

        public List<PsyObjetivoImpacto> findPagePsyObjetivoImpacto(
            String sortColumnName, boolean sortAscending, int startRow,
            int maxResults) throws Exception {
            return psyObjetivoImpactoLogic.findPagePsyObjetivoImpacto(sortColumnName,
                sortAscending, startRow, maxResults);
        }

        public Long findTotalNumberPsyObjetivoImpacto() throws Exception {
            return psyObjetivoImpactoLogic.findTotalNumberPsyObjetivoImpacto();
        }

        public List<PsyObjetivoImpactoDTO> getDataPsyObjetivoImpacto()
            throws Exception {
            return psyObjetivoImpactoLogic.getDataPsyObjetivoImpacto();
        }
    
    /*Metodos de PsyPrograma*/
        
        public List<PsyPrograma> getPsyPrograma() throws Exception {
            return psyProgramaLogic.getPsyPrograma();
        }

        public void savePsyPrograma(PsyPrograma entity, String dmaeCodigo, PsyEmpresa empresa) throws Exception {
            psyProgramaLogic.savePsyPrograma(entity, dmaeCodigo, empresa);
        }

        public void deletePsyPrograma(PsyPrograma entity) throws Exception {
            psyProgramaLogic.deletePsyPrograma(entity);
        }

        public void updatePsyPrograma(PsyPrograma entity, String dmaeCodigo, PsyEmpresa empresa) throws Exception {
            psyProgramaLogic.updatePsyPrograma(entity, dmaeCodigo, empresa);
        }

        public PsyPrograma getPsyPrograma(Long progCodigo)
            throws Exception {
            PsyPrograma psyPrograma = null;

            try {
                psyPrograma = psyProgramaLogic.getPsyPrograma(progCodigo);
            } catch (Exception e) {
                throw e;
            }

            return psyPrograma;
        }

        public List<PsyPrograma> findByCriteriaInPsyPrograma(Object[] variables,
            Object[] variablesBetween, Object[] variablesBetweenDates)
            throws Exception {
            return psyProgramaLogic.findByCriteria(variables, variablesBetween,
                variablesBetweenDates);
        }

        public List<PsyPrograma> findPagePsyPrograma(String sortColumnName,
            boolean sortAscending, int startRow, int maxResults)
            throws Exception {
            return psyProgramaLogic.findPagePsyPrograma(sortColumnName,
                sortAscending, startRow, maxResults);
        }

        public Long findTotalNumberPsyPrograma() throws Exception {
            return psyProgramaLogic.findTotalNumberPsyPrograma();
        }

        public List<PsyProgramaDTO> getDataPsyPrograma(PsyEmpresa empresa) throws Exception {
            return psyProgramaLogic.getDataPsyPrograma(empresa);
        }
        
        public List<PsyMatrizCorrelacion> consultaEstrategiasSeleccionadas(PsyDetalleMatrizCorrelacionDTO relacionSeleccionada, PsyMapaEstrategico mapaEstrategico) throws Exception{
        	return psyDetalleMapaEstrategicoLogic.consultaEstrategiasSeleccionadas(relacionSeleccionada, mapaEstrategico);
        }
        
        public List<PsyDetalleEstrategiasDTO> consultaEstrategiasParaPrograma(PsyEmpresa empresa)
                throws Exception{
        	return psyProgramaLogic.consultaEstrategiasParaPrograma(empresa);
        }
        
        public List<PsyDetalleEstrategiasDTO> consultaEstrategiasParaProgramaDepurada(PsyEmpresa empresa)
                throws Exception{
        	return psyProgramaLogic.consultaEstrategiasParaProgramaDepurada(empresa);
        }
        /*Metodos para la entidad PsyImpactoObjetivo*/
        
        public List<PsyImpactoObjetivo> getPsyImpactoObjetivo()
                throws Exception {
                return psyImpactoObjetivoLogic.getPsyImpactoObjetivo();
            }

            public void savePsyImpactoObjetivo(PsyImpactoObjetivo entity)
                throws Exception {
                psyImpactoObjetivoLogic.savePsyImpactoObjetivo(entity);
            }

            public void deletePsyImpactoObjetivo(PsyImpactoObjetivo entity)
                throws Exception {
                psyImpactoObjetivoLogic.deletePsyImpactoObjetivo(entity);
            }

            public void updatePsyImpactoObjetivo(PsyImpactoObjetivo entity)
                throws Exception {
                psyImpactoObjetivoLogic.updatePsyImpactoObjetivo(entity);
            }

            public PsyImpactoObjetivo getPsyImpactoObjetivo(Long imobCodigo)
                throws Exception {
                PsyImpactoObjetivo psyImpactoObjetivo = null;

                try {
                    psyImpactoObjetivo = psyImpactoObjetivoLogic.getPsyImpactoObjetivo(imobCodigo);
                } catch (Exception e) {
                    throw e;
                }

                return psyImpactoObjetivo;
            }

            public List<PsyImpactoObjetivo> findByCriteriaInPsyImpactoObjetivo(
                Object[] variables, Object[] variablesBetween,
                Object[] variablesBetweenDates) throws Exception {
                return psyImpactoObjetivoLogic.findByCriteria(variables,
                    variablesBetween, variablesBetweenDates);
            }

            public List<PsyImpactoObjetivo> findPagePsyImpactoObjetivo(
                String sortColumnName, boolean sortAscending, int startRow,
                int maxResults) throws Exception {
                return psyImpactoObjetivoLogic.findPagePsyImpactoObjetivo(sortColumnName,
                    sortAscending, startRow, maxResults);
            }

            public Long findTotalNumberPsyImpactoObjetivo() throws Exception {
                return psyImpactoObjetivoLogic.findTotalNumberPsyImpactoObjetivo();
            }

            public List<PsyImpactoObjetivoDTO> getDataPsyImpactoObjetivo()
                throws Exception {
                return psyImpactoObjetivoLogic.getDataPsyImpactoObjetivo();
            }
        
        /*Metodos para la entidad PsyImpactoObjetivo*/
            
            public List<PsyPresupuestoDTO> getDataPsyPresupuestoOnItem(PsyEmpresa empresa) throws Exception {
            	return psyPresupuestoLogic.getDataPsyPresupuestoOnItem(empresa);
            }
            
            
            public List<PsyParametro> getPsyParametro() throws Exception {
                return psyParametroLogic.getPsyParametro();
            }

            public void savePsyParametro(PsyParametro entity) throws Exception {
                psyParametroLogic.savePsyParametro(entity);
            }

            public void deletePsyParametro(PsyParametro entity)
                throws Exception {
                psyParametroLogic.deletePsyParametro(entity);
            }

            public void updatePsyParametro(PsyParametro entity)
                throws Exception {
                psyParametroLogic.updatePsyParametro(entity);
            }

            public PsyParametro getPsyParametro(Long paramCodigo)
                throws Exception {
                PsyParametro psyParametro = null;

                try {
                    psyParametro = psyParametroLogic.getPsyParametro(paramCodigo);
                } catch (Exception e) {
                    throw e;
                }

                return psyParametro;
            }

            public List<PsyParametro> findByCriteriaInPsyParametro(Object[] variables,
                Object[] variablesBetween, Object[] variablesBetweenDates)
                throws Exception {
                return psyParametroLogic.findByCriteria(variables, variablesBetween,
                    variablesBetweenDates);
            }

            public List<PsyParametro> findPagePsyParametro(String sortColumnName,
                boolean sortAscending, int startRow, int maxResults)
                throws Exception {
                return psyParametroLogic.findPagePsyParametro(sortColumnName,
                    sortAscending, startRow, maxResults);
            }

            public Long findTotalNumberPsyParametro() throws Exception {
                return psyParametroLogic.findTotalNumberPsyParametro();
            }

            public List<PsyParametroDTO> getDataPsyParametro()
                throws Exception {
                return psyParametroLogic.getDataPsyParametro();
            }
            
            public PsyParametro getPsyParametro(String paramNombre)
                    throws Exception {
            	PsyParametro psyParametro = null;

                try {
                    psyParametro = psyParametroLogic.getPsyParametro(paramNombre);
                } catch (Exception e) {
                    throw e;
                }

                return psyParametro;
            }
            
            public List<PsyImpactoObjetivoTableDTO> getDataImpactoObjetivo(PsyEmpresa empresa) throws Exception {
            	return psyImpactoObjetivoLogic.getDataImpactoObjetivo(empresa);
            }
            
            public List<PsyEvaluarIndicadoresDTO> getDataPsyIndicadorObam(PsyEmpresa empresa, Long obamCodigo, PsyPlanEstrategicoAmbiental planEstrategicoAmbiental) throws Exception {
            	return psyIndicadorLogic.getDataPsyIndicadorObam(empresa, obamCodigo, planEstrategicoAmbiental);
            }
            
            public void recuperarClave(String correo, UsuarioDTO usuarioDTO) throws Exception{
            	registrarLogic.recuperarClave(correo, usuarioDTO);
            }
            public void actionRecuperarClave(UsuarioDTO usuarioConsulta, String token, String correoRecuperacion, String clave, String claveRecuperacion) throws Exception {
            	registrarLogic.actionRecuperarClave(usuarioConsulta, token, correoRecuperacion, clave, claveRecuperacion);
            }
            
            public void updatePerfilUsuario(PsyPersona entity,String claveActual, String clave, String confimaClave, String nombreUConsulta, String apellidoUConsulta) throws Exception {
            	psyPersonaLogic.updatePerfilUsuario(entity, claveActual, clave, confimaClave, nombreUConsulta, apellidoUConsulta);
            }
        
}
