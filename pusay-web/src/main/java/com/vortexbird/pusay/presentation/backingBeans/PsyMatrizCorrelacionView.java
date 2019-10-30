package com.vortexbird.pusay.presentation.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.pusay.exceptions.ZMessManager;
import com.vortexbird.pusay.modelo.PsyDetalleMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyDetalleObjetivoPlan;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyEstrategiaAmbiental;
import com.vortexbird.pusay.modelo.PsyImpactoAmbiental;
import com.vortexbird.pusay.modelo.PsyMapaEstrategico;
import com.vortexbird.pusay.modelo.PsyMatrizCorrelacion;
import com.vortexbird.pusay.modelo.PsyObjetivoEstrategico;
import com.vortexbird.pusay.modelo.PsyObjetivoPlan;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;
import com.vortexbird.pusay.modelo.dto.PsyDetalleMatrizCorrelacionDTO;
import com.vortexbird.pusay.modelo.dto.PsyMatrizCorrelacionDTO;
import com.vortexbird.pusay.presentation.businessDelegate.IBusinessDelegatorView;
import com.vortexbird.pusay.presentation.utilities.FacesUtils;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyMatrizCorrelacionView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(PsyMatrizCorrelacionView.class);
	private static final String ESTADO_REGISTRO_ACTIVO ="A";
	private static final String ESTADO_REGISTRO_INACTIVO = "I";
	private static final String ESTADO_CERRADO = "C";
	private static final String ESTADO_ABIERTO = "A";
	private static final String ESTADO_ACTIVO = "A";
	private static final String ESTADO_INICIADO = "I";
	private static final Integer CANTIDAD_OBJETIVOS = 5;
	private InputText txtEstadoRegistro;
	private InputText txtEsamCodigo_PsyEstrategiaAmbiental;
	private InputText txtImamCodigo_PsyImpactoAmbiental;
	private InputText txtObesCodigo_PsyObjetivoEstrategico;
	private InputText txtMacoCodigo;
	private PsyMapaEstrategico mapaEstrategico = null;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<PsyMatrizCorrelacionDTO> data;
	private PsyMatrizCorrelacionDTO selectedPsyMatrizCorrelacion;
	private PsyMatrizCorrelacion entity;
	private boolean showDialog;
	private boolean bloqueado;
	private List<PsyImpactoAmbiental> lstImpactoAmbiental = null;
	private List<PsyDetalleMatrizCorrelacionDTO> matrizRelacion = new ArrayList<PsyDetalleMatrizCorrelacionDTO>();
	private PsyDetalleMatrizCorrelacionDTO relacionSeleccionada = null;
	private List<PsyDetalleObjetivoPlan> lstDetalleObjetivoPlan = null;
	private int cantidadObjetivos = 0;
	private String trabajarRelacion;
	private boolean pintar;
	private boolean pintarMensajeVacio;
	private String somEstrategia;
	private String somImpactoAmbiental;
	private String somObjetivoEstrategico;
	private List<SelectItem> lasEstrategias;
	private List<SelectItem> losObjetivos;
	private List<SelectItem> losImpactos;
	private PsyImpactoAmbiental impactoSeleccionado;
	
	// private List<String> estrategiasSeleccionadas = new ArrayList<String>();
	// private List<String> estrategias = new ArrayList<String>();
	//
	private List<PsyEstrategiaAmbiental> estrategiasSeleccionadas = new ArrayList<PsyEstrategiaAmbiental>();
	private List<PsyEstrategiaAmbiental> estrategias = new ArrayList<PsyEstrategiaAmbiental>();

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public PsyMatrizCorrelacionView() {
		super();
	}

	@PostConstruct
	public void psyMatrizCorrelacionView() {

		// Datos temporales para desarrollo
		PsyEmpresa empresa = new PsyEmpresa();
		empresa = (PsyEmpresa) FacesUtils.getfromSession("empresa");

		// Variables para consultar datos necesarios en la asignacion de
		// objetivos corportativos
		PsyPlanEstrategico planEstrategico = null;
		PsyObjetivoPlan objetivoPlan = null;
		List<PsyDetalleObjetivoPlan> lstObjetivosCorporativos = null;
		List<PsyPlanEstrategico> lstPlanEstrategico = null;
		PsyObjetivoEstrategico objetivoEstrategico = new PsyObjetivoEstrategico();

		try {

			// Se consulta el plan estrategico para la empresa seleccionada
			lstPlanEstrategico = businessDelegatorView
					.consultarPlanEstrategicoEmpresa(empresa, ESTADO_INICIADO);

			if (lstPlanEstrategico == null || lstPlanEstrategico.isEmpty()) {
				pintar = false;
				pintarMensajeVacio = true;
			} else {

				planEstrategico = lstPlanEstrategico.get(0);

				// Se impide modificar la pantalla si el plan estrategico se
				// encuentra cerrado
				if (planEstrategico != null
						&& planEstrategico.getEstadoPlan().equals(
								ESTADO_CERRADO)) {
					bloqueado = true;
				}

				// Se consulta si existe un mapa estrategico para el plan
				// Si se encuentra un mapa estrategico activo, lo consulto
				// En caso de no encontrar un mapa estrategico para el plan
				// activo, creo un nuevo mapa estrategico
				mapaEstrategico = businessDelegatorView
						.consultarMapaEstrategico(planEstrategico, null);
				
				if(mapaEstrategico!= null && mapaEstrategico.getEstadoMapaEstrategico().equals(ESTADO_CERRADO)){
					bloqueado = true;
				}
				
				// Se consulta el objetivo plan para del plan estrategico
				objetivoPlan = businessDelegatorView.consultarObjetivoPlan(
						planEstrategico, null);

				if (objetivoPlan != null
						&& objetivoPlan.getEstadoObjetivoPlan().equals(
								ESTADO_CERRADO)) {
					lstObjetivosCorporativos = businessDelegatorView
							.consultarDetalleObjetivoPlan(objetivoPlan);
					pintar = true;
					pintarMensajeVacio = false;

					// Se arman los filtros de busqued para los impactos
					// ambientales
					Object[] variableImpactoAmbiental = { "estadoRegistro",
							true, ESTADO_ACTIVO, "=" };
					

					cantidadObjetivos = CANTIDAD_OBJETIVOS;

					// Se consulta la lista de los impactos ambientales activos
					lstImpactoAmbiental = businessDelegatorView
							.findByCriteriaInPsyImpactoAmbiental(
									variableImpactoAmbiental, null, null);

					// Se consulta la matriz correlaci�n para los objetivos
					// corportivos seleccionados.
					matrizRelacion = businessDelegatorView
							.consultarMatrizRelacionMapaEstrategico(
									mapaEstrategico, lstObjetivosCorporativos,
									CANTIDAD_OBJETIVOS, lstImpactoAmbiental);

					// Se colocan los objetivos corporativos en las columnas

					lstDetalleObjetivoPlan = businessDelegatorView
							.consultarListaCompletaObjetivos(
									lstObjetivosCorporativos,
									CANTIDAD_OBJETIVOS);

				} else {
					pintar = false;
					pintarMensajeVacio = true;
				}

			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String action_seleccionarRelacion() {

		trabajarRelacion = relacionSeleccionada.getTrabajar();
		return "";
	}
	
	public String action_seleccionarImpacto() {
		try {
			
		
		FacesUtils.putinSession("impactoSeleccionado", impactoSeleccionado);
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		context.redirect(context.getRequestContextPath()
				+ "/XHTML/GestionarSeleccionEstrategias.xhtml");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
		
		
	}

	public String action_aceptar_estrategia() {
		relacionSeleccionada.setTrabajar(trabajarRelacion);

		if (trabajarRelacion.equals("T")) {
			relacionSeleccionada.setColorFondo("green");
			
		} else if(trabajarRelacion.equals("S")){
			
			relacionSeleccionada.setColorFondo("yellow");
			
		}else {
	
			relacionSeleccionada.setColorFondo("darkorange");
		}

		return "";
	}

//	public String action_guardar() {
//		// Se guarda el detalle del mapa estrategico
//
//		try {
//			businessDelegatorView.guardarDetalleMapaEstrategico(
//					mapaEstrategico, matrizRelacion);
//			FacesUtils
//					.addInfoMessage("Se ha guardado el mapa estrategico satisfactoriamente");
//
//		} catch (Exception e) {
//
//			FacesUtils.addErrorMessage(e.toString());
//		}
//
//		return "";
//	}

	public String action_generarMapaEstrategicoDefinitvo() {

		List<PsyDetalleMapaEstrategico> lstDetalleMapaEstrategicos = null;
		try {

			mapaEstrategico.setFechaFin(new Date());
			mapaEstrategico.setEstadoMapaEstrategico(ESTADO_CERRADO);

			lstDetalleMapaEstrategicos = businessDelegatorView
					.consultarDetalleMapaEstrategico(mapaEstrategico);

			if (lstDetalleMapaEstrategicos == null
					|| lstDetalleMapaEstrategicos.isEmpty()) {
				throw new Exception(
						"Debe definir al menos una estrategia para trabajar");
			}

			businessDelegatorView.updatePsyMapaEstrategico(mapaEstrategico);
			bloqueado = true;
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_new() {
		action_clear();
		selectedPsyMatrizCorrelacion = null;
		txtEstadoRegistro.setValue("Activo");
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedPsyMatrizCorrelacion = null;

		if (txtEstadoRegistro != null) {
			txtEstadoRegistro.setDisabled(true);
		}

		setSomEstrategia("0");

		setSomImpactoAmbiental("0");
		
		setSomObjetivoEstrategico("0");

		if (btnSave != null) {
			btnSave.setDisabled(false);
		}

		if (btnDelete != null) {
			btnDelete.setDisabled(false);
		}

		return "";
	}

	public void listener_txtId() {
		try {
			Long macoCodigo = FacesUtils.checkLong(txtMacoCodigo);
			entity = (macoCodigo != null) ? businessDelegatorView
					.getPsyMatrizCorrelacion(macoCodigo) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtEstadoRegistro.setDisabled(false);
			txtEsamCodigo_PsyEstrategiaAmbiental.setDisabled(false);
			txtImamCodigo_PsyImpactoAmbiental.setDisabled(false);
			txtObesCodigo_PsyObjetivoEstrategico.setDisabled(false);
			txtMacoCodigo.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtEstadoRegistro.setValue(entity.getEstadoRegistro());
			txtEstadoRegistro.setDisabled(false);
			txtEsamCodigo_PsyEstrategiaAmbiental.setValue(entity
					.getPsyEstrategiaAmbiental().getEsamCodigo());
			txtEsamCodigo_PsyEstrategiaAmbiental.setDisabled(false);
			txtImamCodigo_PsyImpactoAmbiental.setValue(entity
					.getPsyImpactoAmbiental().getImamCodigo());
			txtImamCodigo_PsyImpactoAmbiental.setDisabled(false);
			txtObesCodigo_PsyObjetivoEstrategico.setValue(entity
					.getPsyObjetivoEstrategico().getObesCodigo());
			txtObesCodigo_PsyObjetivoEstrategico.setDisabled(false);
			txtMacoCodigo.setValue(entity.getMacoCodigo());
			txtMacoCodigo.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedPsyMatrizCorrelacion = (PsyMatrizCorrelacionDTO) (evt
				.getComponent().getAttributes()
				.get("selectedPsyMatrizCorrelacion"));
		txtEstadoRegistro.setValue(selectedPsyMatrizCorrelacion
				.getEstadoRegistro());
		txtEstadoRegistro.setDisabled(true);
		setSomEstrategia((selectedPsyMatrizCorrelacion
						.getEsamCodigo_PsyEstrategiaAmbiental()).toString());
		
		setSomImpactoAmbiental((selectedPsyMatrizCorrelacion
				.getImamCodigo_PsyImpactoAmbiental()).toString());
		setSomObjetivoEstrategico((selectedPsyMatrizCorrelacion
						.getObesCodigo_PsyObjetivoEstrategico()).toString());
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedPsyMatrizCorrelacion == null) && (entity == null)) {
				action_create();
			} else {
				action_modify();
			}

			data = null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_create() {
		try {
			entity = new PsyMatrizCorrelacion();

			entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);			
			entity.setPsyEstrategiaAmbiental((somEstrategia != null) ? businessDelegatorView
					.getPsyEstrategiaAmbiental(Long.parseLong(somEstrategia))
					: null);
			entity.setPsyImpactoAmbiental((somImpactoAmbiental != null) ? businessDelegatorView
					.getPsyImpactoAmbiental(Long.parseLong(somImpactoAmbiental))
					: null);
			entity.setPsyObjetivoEstrategico((somObjetivoEstrategico != null) ? businessDelegatorView
					.getPsyObjetivoEstrategico(Long.parseLong(somObjetivoEstrategico))
					: null);
			businessDelegatorView.savePsyMatrizCorrelacion(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
			data=null;
		} catch (Exception e) {
			entity = null;
			data=null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modify() {
		try {
			if (entity == null) {
				Long macoCodigo = new Long(
						selectedPsyMatrizCorrelacion.getMacoCodigo());
				entity = businessDelegatorView
						.getPsyMatrizCorrelacion(macoCodigo);
			}

			entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);			
			entity.setPsyEstrategiaAmbiental((somEstrategia != null) ? businessDelegatorView
					.getPsyEstrategiaAmbiental(Long.parseLong(somEstrategia))
					: null);
			entity.setPsyImpactoAmbiental((somImpactoAmbiental != null) ? businessDelegatorView
					.getPsyImpactoAmbiental(Long.parseLong(somImpactoAmbiental))
					: null);
			entity.setPsyObjetivoEstrategico((somObjetivoEstrategico != null) ? businessDelegatorView
					.getPsyObjetivoEstrategico(Long.parseLong(somObjetivoEstrategico))
					: null);
			businessDelegatorView.updatePsyMatrizCorrelacion(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			action_clear();
			data=null;
		} catch (Exception e) {
			entity = null;
			data=null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedPsyMatrizCorrelacion = (PsyMatrizCorrelacionDTO) (evt
					.getComponent().getAttributes()
					.get("selectedPsyMatrizCorrelacion"));

			Long macoCodigo = new Long(
					selectedPsyMatrizCorrelacion.getMacoCodigo());
			entity = businessDelegatorView.getPsyMatrizCorrelacion(macoCodigo);
			action_delete();
			data=null;
		} catch (Exception e) {
			entity = null;
			data=null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Long macoCodigo = FacesUtils.checkLong(txtMacoCodigo);
			entity = businessDelegatorView.getPsyMatrizCorrelacion(macoCodigo);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deletePsyMatrizCorrelacion(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
			data = null;
		} catch (Exception e) {
			throw e;
		}
	}

	public String action_closeDialog() {
		setShowDialog(false);
		action_clear();

		return "";
	}

	public String actionDeleteDataTableEditable(ActionEvent evt) {
		try {
			selectedPsyMatrizCorrelacion = (PsyMatrizCorrelacionDTO) (evt
					.getComponent().getAttributes()
					.get("selectedPsyMatrizCorrelacion"));

			Long macoCodigo = new Long(
					selectedPsyMatrizCorrelacion.getMacoCodigo());
			entity = businessDelegatorView.getPsyMatrizCorrelacion(macoCodigo);
			businessDelegatorView.deletePsyMatrizCorrelacion(entity);
			data.remove(selectedPsyMatrizCorrelacion);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(String estadoRegistro, Long macoCodigo,
			Long esamCodigo_PsyEstrategiaAmbiental,
			Long imamCodigo_PsyImpactoAmbiental,
			Long obesCodigo_PsyObjetivoEstrategico) throws Exception {
		try {
			entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
			businessDelegatorView.updatePsyMatrizCorrelacion(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("PsyMatrizCorrelacionView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputText getTxtEstadoRegistro() {
		return txtEstadoRegistro;
	}

	public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
		this.txtEstadoRegistro = txtEstadoRegistro;
	}

	public InputText getTxtEsamCodigo_PsyEstrategiaAmbiental() {
		return txtEsamCodigo_PsyEstrategiaAmbiental;
	}

	public void setTxtEsamCodigo_PsyEstrategiaAmbiental(
			InputText txtEsamCodigo_PsyEstrategiaAmbiental) {
		this.txtEsamCodigo_PsyEstrategiaAmbiental = txtEsamCodigo_PsyEstrategiaAmbiental;
	}

	public InputText getTxtImamCodigo_PsyImpactoAmbiental() {
		return txtImamCodigo_PsyImpactoAmbiental;
	}

	public void setTxtImamCodigo_PsyImpactoAmbiental(
			InputText txtImamCodigo_PsyImpactoAmbiental) {
		this.txtImamCodigo_PsyImpactoAmbiental = txtImamCodigo_PsyImpactoAmbiental;
	}

	public InputText getTxtObesCodigo_PsyObjetivoEstrategico() {
		return txtObesCodigo_PsyObjetivoEstrategico;
	}

	public void setTxtObesCodigo_PsyObjetivoEstrategico(
			InputText txtObesCodigo_PsyObjetivoEstrategico) {
		this.txtObesCodigo_PsyObjetivoEstrategico = txtObesCodigo_PsyObjetivoEstrategico;
	}

	public InputText getTxtMacoCodigo() {
		return txtMacoCodigo;
	}

	public void setTxtMacoCodigo(InputText txtMacoCodigo) {
		this.txtMacoCodigo = txtMacoCodigo;
	}

	public List<PsyMatrizCorrelacionDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataPsyMatrizCorrelacion();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<PsyMatrizCorrelacionDTO> psyMatrizCorrelacionDTO) {
		this.data = psyMatrizCorrelacionDTO;
	}

	public PsyMatrizCorrelacionDTO getSelectedPsyMatrizCorrelacion() {
		return selectedPsyMatrizCorrelacion;
	}

	public void setSelectedPsyMatrizCorrelacion(
			PsyMatrizCorrelacionDTO psyMatrizCorrelacion) {
		this.selectedPsyMatrizCorrelacion = psyMatrizCorrelacion;
	}

	public CommandButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(CommandButton btnSave) {
		this.btnSave = btnSave;
	}

	public CommandButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(CommandButton btnModify) {
		this.btnModify = btnModify;
	}

	public CommandButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(CommandButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public CommandButton getBtnClear() {
		return btnClear;
	}

	public void setBtnClear(CommandButton btnClear) {
		this.btnClear = btnClear;
	}

	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public PsyMapaEstrategico getMapaEstrategico() {
		return mapaEstrategico;
	}

	public void setMapaEstrategico(PsyMapaEstrategico mapaEstrategico) {
		this.mapaEstrategico = mapaEstrategico;
	}

	public List<PsyImpactoAmbiental> getLstImpactoAmbiental() {
		return lstImpactoAmbiental;
	}

	public void setLstImpactoAmbiental(
			List<PsyImpactoAmbiental> lstImpactoAmbiental) {
		this.lstImpactoAmbiental = lstImpactoAmbiental;
	}

	public List<PsyDetalleMatrizCorrelacionDTO> getMatrizRelacion() {
		return matrizRelacion;
	}

	public void setMatrizRelacion(
			List<PsyDetalleMatrizCorrelacionDTO> matrizRelacion) {
		this.matrizRelacion = matrizRelacion;
	}

	public List<PsyDetalleObjetivoPlan> getLstDetalleObjetivoPlan() {
		return lstDetalleObjetivoPlan;
	}

	public void setLstDetalleObjetivoPlan(
			List<PsyDetalleObjetivoPlan> lstDetalleObjetivoPlan) {
		this.lstDetalleObjetivoPlan = lstDetalleObjetivoPlan;
	}

	public int getCantidadObjetivos() {
		return cantidadObjetivos;
	}

	public void setCantidadObjetivos(int cantidadObjetivos) {
		this.cantidadObjetivos = cantidadObjetivos;
	}

	public PsyDetalleMatrizCorrelacionDTO getRelacionSeleccionada() {
		return relacionSeleccionada;
	}

	public void setRelacionSeleccionada(
			PsyDetalleMatrizCorrelacionDTO relacionSeleccionada) {
		this.relacionSeleccionada = relacionSeleccionada;
	}

	public String getTrabajarRelacion() {
		return trabajarRelacion;
	}

	public void setTrabajarRelacion(String trabajarRelacion) {
		this.trabajarRelacion = trabajarRelacion;
	}

	public boolean isPintar() {
		return pintar;
	}

	public void setPintar(boolean pintar) {
		this.pintar = pintar;
	}

	public boolean isPintarMensajeVacio() {
		return pintarMensajeVacio;
	}

	public void setPintarMensajeVacio(boolean pintarMensajeVacio) {
		this.pintarMensajeVacio = pintarMensajeVacio;
	}

	public List<PsyEstrategiaAmbiental> getEstrategiasSeleccionadas() {
		return estrategiasSeleccionadas;
	}

	public void setEstrategiasSeleccionadas(
			List<PsyEstrategiaAmbiental> estrategiasSeleccionadas) {
		this.estrategiasSeleccionadas = estrategiasSeleccionadas;
	}

	public List<PsyEstrategiaAmbiental> getEstrategias() {
		return estrategias;
	}

	public void setEstrategias(List<PsyEstrategiaAmbiental> estrategias) {
		this.estrategias = estrategias;
	}

	public String getSomEstrategia() {
		return somEstrategia;
	}

	public void setSomEstrategia(String somEstrategia) {
		this.somEstrategia = somEstrategia;
	}

	public String getSomImpactoAmbiental() {
		return somImpactoAmbiental;
	}

	public void setSomImpactoAmbiental(String somImpactoAmbiental) {
		this.somImpactoAmbiental = somImpactoAmbiental;
	}

	public String getSomObjetivoEstrategico() {
		return somObjetivoEstrategico;
	}

	public void setSomObjetivoEstrategico(String somObjetivoEstrategico) {
		this.somObjetivoEstrategico = somObjetivoEstrategico;
	}

	public List<SelectItem> getLasEstrategias() {
		try {
			lasEstrategias = new ArrayList<SelectItem>();
			List<PsyEstrategiaAmbiental> losTiposDeEstrategias = businessDelegatorView
					.getPsyEstrategiaAmbiental();
			for (PsyEstrategiaAmbiental estrategiaAmbiental : losTiposDeEstrategias) {
				if (estrategiaAmbiental.getEstadoRegistro().equalsIgnoreCase("A")) {
					SelectItem selectItem = new SelectItem(
							estrategiaAmbiental.getEsamCodigo(), estrategiaAmbiental.getNombre());
					lasEstrategias.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando las Estrategias Ambientales");
		}
		return lasEstrategias;
	}

	public void setLasEstrategias(List<SelectItem> lasEstrategias) {
		this.lasEstrategias = lasEstrategias;
	}

	public List<SelectItem> getLosObjetivos() {
		try {
			losObjetivos = new ArrayList<SelectItem>();
			List<PsyObjetivoEstrategico> losTiposDeObjetivos = businessDelegatorView
					.getPsyObjetivoEstrategico();
			for (PsyObjetivoEstrategico objetivoEstrategico : losTiposDeObjetivos) {
				if (objetivoEstrategico.getEstadoRegistro().equalsIgnoreCase("A")) {
					SelectItem selectItem = new SelectItem(
							objetivoEstrategico.getObesCodigo(), objetivoEstrategico.getNombre());
					losObjetivos.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los Objetivos Estrategicos");
		}
		return losObjetivos;
	}

	public void setLosObjetivos(List<SelectItem> losObjetivos) {
		this.losObjetivos = losObjetivos;
	}

	public List<SelectItem> getLosImpactos() {
		try {
			losImpactos = new ArrayList<SelectItem>();
			List<PsyImpactoAmbiental> losTiposImpactos = businessDelegatorView
					.getPsyImpactoAmbiental();
			for (PsyImpactoAmbiental impactoAmbiental : losTiposImpactos) {
				if (impactoAmbiental.getEstadoRegistro().equalsIgnoreCase("A")) {
					SelectItem selectItem = new SelectItem(
							impactoAmbiental.getImamCodigo(), impactoAmbiental.getNombre());
					losImpactos.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los Impactos Ambientales");
		}
		return losImpactos;
	}

	public void setLosImpactos(List<SelectItem> losImpactos) {
		this.losImpactos = losImpactos;
	}

	public PsyImpactoAmbiental getImpactoSeleccionado() {
		return impactoSeleccionado;
	}

	public void setImpactoSeleccionado(PsyImpactoAmbiental impactoSeleccionado) {
		this.impactoSeleccionado = impactoSeleccionado;
	}

	// public List<String> getEstrategiasSeleccionadas() {
	// return estrategiasSeleccionadas;
	// }
	//
	// public void setEstrategiasSeleccionadas(List<String>
	// estrategiasSeleccionadas) {
	// this.estrategiasSeleccionadas = estrategiasSeleccionadas;
	// }
	//
	// public List<String> getEstrategias() {
	// return estrategias;
	// }
	//
	// public void setEstrategias(List<String> estrategias) {
	// this.estrategias = estrategias;
	// }

}
