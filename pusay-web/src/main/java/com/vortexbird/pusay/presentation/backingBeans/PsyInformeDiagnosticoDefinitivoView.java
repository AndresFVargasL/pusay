package com.vortexbird.pusay.presentation.backingBeans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.outputlabel.OutputLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyMatrizErida;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;
import com.vortexbird.pusay.modelo.dto.PsyPriorizacionAsuntoAmbientalDTO;
import com.vortexbird.pusay.modelo.dto.PsyPriorizacionImpactosAmbientalesDTO;
import com.vortexbird.pusay.presentation.businessDelegate.IBusinessDelegatorView;
import com.vortexbird.pusay.presentation.utilities.FacesUtils;

@ManagedBean
@ViewScoped
public class PsyInformeDiagnosticoDefinitivoView {

	private static final long serialVersionUID = 1L;
	private static final String ESTADO_INICIADO = "I";
	private static final Logger log = LoggerFactory
			.getLogger(PsyAsuntoAmbientalView.class);
	private String txtDescripcion;
	private String txtNombre;
	private String txtEmprCodigo_PsyEmpresa;
	private Long txtPestCodigo;
	private Date txtFechaFin;
	private Date txtFechaInicio;
	private List<PsyPriorizacionAsuntoAmbientalDTO> dataAsuntoAmbienta;
	private List<PsyPriorizacionImpactosAmbientalesDTO> dataImpactosAmbientales;
	private PsyPlanEstrategico entity;
	private PsyPlanEstrategico planEstrategico = null;
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	@PostConstruct
	public void cargarInforme() {
		// Datos temporales para desarrollo
		PsyEmpresa empresa = new PsyEmpresa();
		empresa = (PsyEmpresa) FacesUtils.getfromSession("empresa");

		List<PsyPlanEstrategico> lstPlanEstrategico = null;
		PsyMatrizErida matrizErida = null;

		try {

			// Se consulta el plan estrategico para la empresa seleccionada
			lstPlanEstrategico = businessDelegatorView
					.consultarPlanEstrategicoEmpresa(empresa, ESTADO_INICIADO);

			if (lstPlanEstrategico != null && !lstPlanEstrategico.isEmpty()) {
				planEstrategico = lstPlanEstrategico.get(0);
			}

			entity = businessDelegatorView
					.getPsyPlanEstrategico(planEstrategico.getPestCodigo());

			matrizErida = businessDelegatorView
					.consultarMatrizErida(planEstrategico);

			dataAsuntoAmbienta = businessDelegatorView
					.PriorizacionAsuntoAmbiental(matrizErida.getMaerCodigo());
			dataImpactosAmbientales = businessDelegatorView
					.PriorizacionImpactosAmbientales(matrizErida
							.getMaerCodigo());

			txtNombre = entity.getNombre();
			txtDescripcion = entity.getDescripcion();
			txtPestCodigo = entity.getPestCodigo();
			txtFechaFin = entity.getFechaFin();
			txtFechaInicio = entity.getFechaInicio();
			txtEmprCodigo_PsyEmpresa = businessDelegatorView.getPsyEmpresa(
					entity.getPsyEmpresa().getEmprCodigo()).getNombre();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public String getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(String txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public String getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(String txtNombre) {
		this.txtNombre = txtNombre;
	}

	public String getTxtEmprCodigo_PsyEmpresa() {
		return txtEmprCodigo_PsyEmpresa;
	}

	public void setTxtEmprCodigo_PsyEmpresa(String txtEmprCodigo_PsyEmpresa) {
		this.txtEmprCodigo_PsyEmpresa = txtEmprCodigo_PsyEmpresa;
	}

	public Long getTxtPestCodigo() {
		return txtPestCodigo;
	}

	public void setTxtPestCodigo(Long txtPestCodigo) {
		this.txtPestCodigo = txtPestCodigo;
	}

	public Date getTxtFechaFin() {
		return txtFechaFin;
	}

	public void setTxtFechaFin(Date txtFechaFin) {
		this.txtFechaFin = txtFechaFin;
	}

	public Date getTxtFechaInicio() {
		return txtFechaInicio;
	}

	public void setTxtFechaInicio(Date txtFechaInicio) {
		this.txtFechaInicio = txtFechaInicio;
	}

	public List<PsyPriorizacionAsuntoAmbientalDTO> getDataAsuntoAmbienta() {

		return dataAsuntoAmbienta;
	}

	public void setDataAsuntoAmbienta(
			List<PsyPriorizacionAsuntoAmbientalDTO> dataAsuntoAmbienta) {
		this.dataAsuntoAmbienta = dataAsuntoAmbienta;
	}

	public List<PsyPriorizacionImpactosAmbientalesDTO> getDataImpactosAmbientales() {

		return dataImpactosAmbientales;
	}

	public void setDataImpactosAmbientales(
			List<PsyPriorizacionImpactosAmbientalesDTO> dataImpactosAmbientales) {
		this.dataImpactosAmbientales = dataImpactosAmbientales;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

}
