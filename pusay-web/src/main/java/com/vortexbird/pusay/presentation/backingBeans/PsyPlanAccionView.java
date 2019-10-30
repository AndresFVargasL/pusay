package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyPlanAccionDTO;
import com.vortexbird.pusay.modelo.dto.PsyPlanEstrategicoDTO;
import com.vortexbird.pusay.modelo.dto.PsyProgramaDTO;
import com.vortexbird.pusay.presentation.businessDelegate.*;
import com.vortexbird.pusay.presentation.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.picklist.PickList;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyPlanAccionView implements Serializable {
	private static final String ESTADO_REGISTRO_ACTIVO = "A";
	private static final String ESTADO_PLAN_ABIERTO = "A";
	private static final String ESTADO_PLAN_INICIADO = "I";
	private static final String ESTADO_PLAN_PRESUPUESTADO = "P";
	private static final String ESTADO_PLAN_CERRADO = "C";
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(PsyPlanAccionView.class);
	private InputTextarea txtDescripcion;
	private InputText txtEstadoPlanAccion;
	private InputText txtEstadoRegistro;
	private InputText txtNombre;
	private InputText txtPlacCodigo;
	private InputText txtPlanEstrategico;
	private Calendar txtFechaFinPlaneada;
	private Calendar txtFechaFinReal;
	private Calendar txtFechaInicio;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<PsyPlanAccionDTO> data;
	private PsyPlanAccionDTO selectedPsyPlanAccion;
	private PsyPlanAccion entity;
	private boolean showDialog;
	private boolean renderFechaFinal=false;
	private String somPrograma;
	private List<SelectItem> losProgramas;
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public PsyPlanAccionView() {
		super();
	}

	public PsyEmpresa getEmpresaIntoSession() {
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);
		return (PsyEmpresa) httpSession.getAttribute("empresa");
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			PsyPlanAccionDTO psyPlanAccionDTO = (PsyPlanAccionDTO) e
					.getObject();

			if (txtDescripcion == null) {
				txtDescripcion = new InputTextarea();
			}

			txtDescripcion.setValue(psyPlanAccionDTO.getDescripcion());

			if (txtEstadoPlanAccion == null) {
				txtEstadoPlanAccion = new InputText();
			}

			txtEstadoPlanAccion
					.setValue(psyPlanAccionDTO.getEstadoPlanAccion());

			if (txtEstadoRegistro == null) {
				txtEstadoRegistro = new InputText();
			}

			txtEstadoRegistro.setValue(psyPlanAccionDTO.getEstadoRegistro());

			if (txtNombre == null) {
				txtNombre = new InputText();
			}

			txtNombre.setValue(psyPlanAccionDTO.getNombre());

			if (txtPlacCodigo == null) {
				txtPlacCodigo = new InputText();
			}

			txtPlacCodigo.setValue(psyPlanAccionDTO.getPlacCodigo());

			if (txtFechaFinPlaneada == null) {
				txtFechaFinPlaneada = new Calendar();
			}

			txtFechaFinPlaneada
					.setValue(psyPlanAccionDTO.getFechaFinPlaneada());

			if (txtFechaFinReal == null) {
				txtFechaFinReal = new Calendar();
			}

			txtFechaFinReal.setValue(psyPlanAccionDTO.getFechaFinReal());

			if (txtFechaInicio == null) {
				txtFechaInicio = new Calendar();
			}

			txtFechaInicio.setValue(psyPlanAccionDTO.getFechaInicio());

			Long placCodigo = FacesUtils.checkLong(txtPlacCodigo);
			entity = businessDelegatorView.getPsyPlanAccion(placCodigo);

			action_modify();
		} catch (Exception ex) {
		}
	}
	

	public void generarPlanAccionDifinitivo(ActionEvent evt) {
		try {
			selectedPsyPlanAccion = (PsyPlanAccionDTO) (evt.getComponent()
					.getAttributes().get("selectedPsyPlanAccion"));
			businessDelegatorView
					.generarPlanAccionDifinitivo(selectedPsyPlanAccion
							.getPlacCodigo());
			data = null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public String action_new() {
		renderFechaFinal=false;
		setSomPrograma("0");
		try {
			Boolean planIniciado = businessDelegatorView
					.verificarPlanEstrategico(getEmpresaIntoSession());
			if (planIniciado == true) {
				Boolean mapaIniciado = businessDelegatorView
						.verificarMapaEstrategico(getEmpresaIntoSession());
				if (mapaIniciado == true) {
					action_clear();
					selectedPsyPlanAccion = null;
					txtEstadoPlanAccion.setValue("Abierto");			
					txtPlanEstrategico.setValue(businessDelegatorView.consultaPlanEstrategicoBtnNew(getEmpresaIntoSession()));
					setShowDialog(true);
				} else {
					FacesUtils
							.addInfoMessage("Porfavor diligencie primero el mapa estrategico");
				}
			} else {
				FacesUtils
						.addInfoMessage("Para abrir planes de accion debe haber al menos un plan estrategico en estado INICIADO");
			}
		} catch (Exception e) {
			FacesUtils
					.addErrorMessage("Error al intentar crear un nuevo plan de accion");
		}
		return "";
	}

	public String action_clear() {
		entity = null;
		selectedPsyPlanAccion = null;

		if (txtDescripcion != null) {
			txtDescripcion.setValue(null);
			txtDescripcion.setDisabled(false);
		}
		
		if (txtPlanEstrategico != null) {
			txtPlanEstrategico.setDisabled(true);
		}

		if (txtEstadoPlanAccion != null) {
			txtEstadoPlanAccion.setDisabled(true);
		}

		if (txtEstadoRegistro != null) {
			txtEstadoRegistro.setValue(null);
			txtEstadoRegistro.setDisabled(true);
		}

		if (txtNombre != null) {
			txtNombre.setValue(null);
			txtNombre.setDisabled(false);
		}

		if (txtFechaFinPlaneada != null) {
			txtFechaFinPlaneada.setValue(null);
			txtFechaFinPlaneada.setDisabled(false);
		}

		if (txtFechaFinReal != null) {
			txtFechaFinReal.setValue(null);
			txtFechaFinReal.setDisabled(false);
		}

		if (txtFechaInicio != null) {
			txtFechaInicio.setValue(null);
			txtFechaInicio.setDisabled(false);
		}

		if (txtPlacCodigo != null) {
			txtPlacCodigo.setValue(null);
			txtPlacCodigo.setDisabled(true);
		}

		if (btnSave != null) {
			btnSave.setDisabled(false);
		}

		if (btnDelete != null) {
			btnDelete.setDisabled(false);
		}

		return "";
	}

	public void listener_txtFechaFinPlaneada() {
		Date inputDate = (Date) txtFechaFinPlaneada.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage(
				"",
				new FacesMessage("Fecha Seleccionada "
						+ dateFormat.format(inputDate)));
	}

	public void listener_txtFechaFinReal() {
		if (txtFechaFinReal != null) {
			Date inputDate = (Date) txtFechaFinReal.getValue();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			FacesContext.getCurrentInstance().addMessage(
					"",
					new FacesMessage("Fecha Seleccionada "
							+ dateFormat.format(inputDate)));

		}

	}

	public void listener_txtFechaInicio() {
		Date inputDate = (Date) txtFechaInicio.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage(
				"",
				new FacesMessage("Fecha Seleccionada "
						+ dateFormat.format(inputDate)));
	}

	public void listener_txtId() {
		try {
			Long placCodigo = FacesUtils.checkLong(txtPlacCodigo);
			entity = (placCodigo != null) ? businessDelegatorView
					.getPsyPlanAccion(placCodigo) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtDescripcion.setDisabled(false);
			txtEstadoPlanAccion.setDisabled(false);
			txtEstadoRegistro.setDisabled(false);
			txtNombre.setDisabled(false);
			txtFechaFinPlaneada.setDisabled(false);
			txtFechaFinReal.setDisabled(false);
			txtFechaInicio.setDisabled(false);
			txtPlacCodigo.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtDescripcion.setValue(entity.getDescripcion());
			txtDescripcion.setDisabled(false);
			txtEstadoPlanAccion.setValue(entity.getEstadoPlanAccion());
			txtEstadoPlanAccion.setDisabled(false);
			txtEstadoRegistro.setValue(entity.getEstadoRegistro());
			txtEstadoRegistro.setDisabled(false);
			txtFechaFinPlaneada.setValue(entity.getFechaFinPlaneada());
			txtFechaFinPlaneada.setDisabled(false);
			txtFechaFinReal.setValue(entity.getFechaFinReal());
			txtFechaFinReal.setDisabled(false);
			txtFechaInicio.setValue(entity.getFechaInicio());
			txtFechaInicio.setDisabled(false);
			txtNombre.setValue(entity.getNombre());
			txtNombre.setDisabled(false);
			txtPlacCodigo.setValue(entity.getPlacCodigo());
			txtPlacCodigo.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		renderFechaFinal=true;	
		btnSave.setDisabled(false);
		txtNombre.setDisabled(false);
		txtDescripcion.setDisabled(false);
		txtFechaFinPlaneada.setDisabled(false);
		txtFechaInicio.setDisabled(false);
		

		try {
			selectedPsyPlanAccion = (PsyPlanAccionDTO) (evt.getComponent()
					.getAttributes().get("selectedPsyPlanAccion"));
			txtDescripcion.setValue(selectedPsyPlanAccion.getDescripcion());
			txtDescripcion.setDisabled(false);
			txtEstadoPlanAccion.setValue(selectedPsyPlanAccion
					.getEstadoPlanAccion().trim().equals("Abierto") ? "Abierto" : null);
			txtPlanEstrategico.setValue(businessDelegatorView.consultaPlanEstrategico(getEmpresaIntoSession(), selectedPsyPlanAccion.getPlacCodigo()));
			txtEstadoPlanAccion.setDisabled(true);
			txtFechaFinPlaneada.setValue(selectedPsyPlanAccion
					.getFechaFinPlaneada());
			txtFechaFinPlaneada.setDisabled(false);
			txtFechaFinReal.setValue(selectedPsyPlanAccion.getFechaFinReal());
			txtFechaFinReal.setDisabled(false);
			txtFechaInicio.setValue(selectedPsyPlanAccion.getFechaInicio());
			txtFechaInicio.setDisabled(false);
			txtNombre.setValue(selectedPsyPlanAccion.getNombre());
			txtNombre.setDisabled(false);
		    setSomPrograma(selectedPsyPlanAccion.getProgCodigo_PsyPrograma().toString());
			btnSave.setDisabled(false);
			
			if (selectedPsyPlanAccion.getEstadoPlanAccion().trim().equals("Iniciado")) {
				txtNombre.setDisabled(true);
				txtDescripcion.setDisabled(true);
				txtFechaFinPlaneada.setDisabled(true);
				txtFechaInicio.setDisabled(true);
				
			}
			if (selectedPsyPlanAccion.getEstadoPlanAccion().trim().equals("Presupuestado")) {
				txtNombre.setDisabled(true);
				txtDescripcion.setDisabled(true);
				txtFechaFinPlaneada.setDisabled(true);
				txtFechaInicio.setDisabled(true);
				
			}
			if (selectedPsyPlanAccion.getEstadoPlanAccion().trim().equals("Cerrado")) {
				txtNombre.setDisabled(true);
				txtDescripcion.setDisabled(true);
				txtFechaFinPlaneada.setDisabled(true);
				txtFechaInicio.setDisabled(true);
				btnSave.setDisabled(true);
			}

			
			setShowDialog(true);

			
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error al editar el plan de accion");
		}

		return "";
	}

	public String action_save() {
		try {
			if ((selectedPsyPlanAccion == null) && (entity == null)) {
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
			entity = new PsyPlanAccion();

			entity.setNombre((FacesUtils.checkString(txtNombre) != null) ? FacesUtils.checkString(txtNombre) : null);
			entity.setDescripcion((FacesUtils.checkString(txtDescripcion)!= null) ? FacesUtils.checkString(txtDescripcion) : null);
			entity.setEstadoPlanAccion((FacesUtils
					.checkString(txtEstadoPlanAccion) != null) ? (FacesUtils
					.checkString(txtEstadoPlanAccion).equals("Abierto")) ? ESTADO_PLAN_ABIERTO
					: null : null);
			entity.setEstadoRegistro(ESTADO_REGISTRO_ACTIVO);
			
			entity.setFechaFinPlaneada((txtFechaFinPlaneada != null) ? FacesUtils
					.checkDate(txtFechaFinPlaneada) : null);
			entity.setFechaFinReal((txtFechaFinReal != null) ? FacesUtils
					.checkDate(txtFechaFinReal) : null);
			entity.setFechaInicio((txtFechaInicio != null) ? FacesUtils
					.checkDate(txtFechaInicio) : null);
			entity.setPsyPrograma((somPrograma!=null && !somPrograma.trim().equals("0") && !somPrograma.trim().equals("")) ?
									businessDelegatorView.getPsyPrograma(Long.parseLong(somPrograma)) : null);
			businessDelegatorView.savePsyPlanAccion(entity);

			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			data=null;
			action_clear();
			action_closeDialog();
		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modify() {
		try {
			if (entity == null) {
				Long placCodigo = new Long(
						selectedPsyPlanAccion.getPlacCodigo());
				entity = businessDelegatorView.getPsyPlanAccion(placCodigo);
			}
			entity.setDescripcion(( FacesUtils.checkString(txtDescripcion) !=null) ? FacesUtils.checkString(txtDescripcion) : null);
			entity.setEstadoPlanAccion(( FacesUtils
					.checkString(txtEstadoPlanAccion) !=null) ? (FacesUtils
							.checkString(txtEstadoPlanAccion).equals("Abierto")) ? ESTADO_PLAN_ABIERTO : (FacesUtils
									.checkString(txtEstadoPlanAccion).equals("Iniciado")) ? ESTADO_PLAN_INICIADO : 
										(FacesUtils
												.checkString(txtEstadoPlanAccion).equals("Presupuestado")) ? ESTADO_PLAN_PRESUPUESTADO :
													(FacesUtils
															.checkString(txtEstadoPlanAccion).equals("Cerrado")) ? ESTADO_PLAN_CERRADO : null : null);
			entity.setFechaFinPlaneada(( FacesUtils
					.checkDate(txtFechaFinPlaneada) !=null) ? FacesUtils
							.checkDate(txtFechaFinPlaneada) : null);
			entity.setFechaFinReal((txtFechaFinReal != null) ? FacesUtils
					.checkDate(txtFechaFinReal) : null);
			entity.setFechaInicio(( FacesUtils.checkDate(txtFechaInicio) !=null) ? FacesUtils.checkDate(txtFechaInicio) : null);
			entity.setNombre( ( FacesUtils.checkString(txtNombre) !=null) ? FacesUtils.checkString(txtNombre) : null );
			entity.setPsyPrograma((somPrograma!=null && !somPrograma.trim().equals("0") && !somPrograma.trim().equals("")) ?
					businessDelegatorView.getPsyPrograma(Long.parseLong(somPrograma)) : null);
			businessDelegatorView.updatePsyPlanAccion(entity);

			
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			data=null;
			action_clear();
			action_closeDialog();
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}
	
	public String cerrarPlan(ActionEvent evt) {
		try {
			selectedPsyPlanAccion = (PsyPlanAccionDTO) (evt.getComponent()
					.getAttributes().get("selectedPsyPlanAccion"));

			Long placCodigo = new Long(
					selectedPsyPlanAccion.getPlacCodigo());
			entity = businessDelegatorView.getPsyPlanAccion(placCodigo);
			if(!entity.getEstadoPlanAccion().equals(ESTADO_PLAN_CERRADO)){
				entity.setEstadoPlanAccion(ESTADO_PLAN_CERRADO);
				businessDelegatorView.updatePsyPlanAccion(entity);
			}else{
				FacesUtils.addErrorMessage("El plan ya esta cerrado");
			}
			data=null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedPsyPlanAccion = (PsyPlanAccionDTO) (evt.getComponent()
					.getAttributes().get("selectedPsyPlanAccion"));

			Long placCodigo = new Long(selectedPsyPlanAccion.getPlacCodigo());
			entity = businessDelegatorView.getPsyPlanAccion(placCodigo);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Long placCodigo = FacesUtils.checkLong(txtPlacCodigo);
			entity = businessDelegatorView.getPsyPlanAccion(placCodigo);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() {
		try {
			businessDelegatorView.deletePsyPlanAccion(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
			data = null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public String action_closeDialog() {
		setShowDialog(false);
		action_clear();

		return "";
	}

	public String actionDeleteDataTableEditable(ActionEvent evt) {
		try {
			selectedPsyPlanAccion = (PsyPlanAccionDTO) (evt.getComponent()
					.getAttributes().get("selectedPsyPlanAccion"));

			Long placCodigo = new Long(selectedPsyPlanAccion.getPlacCodigo());
			entity = businessDelegatorView.getPsyPlanAccion(placCodigo);
			businessDelegatorView.deletePsyPlanAccion(entity);
			data.remove(selectedPsyPlanAccion);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(String descripcion,
			String estadoPlanAccion, String estadoRegistro,
			Date fechaFinPlaneada, Date fechaFinReal, Date fechaInicio,
			String nombre, Long placCodigo) throws Exception {
		try {
			entity.setDescripcion(FacesUtils.checkString(descripcion));
			entity.setEstadoPlanAccion(FacesUtils.checkString(estadoPlanAccion));
			entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
			entity.setFechaFinPlaneada(FacesUtils.checkDate(fechaFinPlaneada));
			entity.setFechaFinReal(FacesUtils.checkDate(fechaFinReal));
			entity.setFechaInicio(FacesUtils.checkDate(fechaInicio));
			entity.setNombre(FacesUtils.checkString(nombre));
			businessDelegatorView.updatePsyPlanAccion(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("PsyPlanAccionView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputTextarea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(InputTextarea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public InputText getTxtEstadoPlanAccion() {
		return txtEstadoPlanAccion;
	}

	public void setTxtEstadoPlanAccion(InputText txtEstadoPlanAccion) {
		this.txtEstadoPlanAccion = txtEstadoPlanAccion;
	}

	public InputText getTxtEstadoRegistro() {
		return txtEstadoRegistro;
	}

	public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
		this.txtEstadoRegistro = txtEstadoRegistro;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public Calendar getTxtFechaFinPlaneada() {
		return txtFechaFinPlaneada;
	}

	public void setTxtFechaFinPlaneada(Calendar txtFechaFinPlaneada) {
		this.txtFechaFinPlaneada = txtFechaFinPlaneada;
	}

	public Calendar getTxtFechaFinReal() {
		return txtFechaFinReal;
	}

	public void setTxtFechaFinReal(Calendar txtFechaFinReal) {
		this.txtFechaFinReal = txtFechaFinReal;
	}

	public Calendar getTxtFechaInicio() {
		return txtFechaInicio;
	}

	public void setTxtFechaInicio(Calendar txtFechaInicio) {
		this.txtFechaInicio = txtFechaInicio;
	}

	public InputText getTxtPlacCodigo() {
		return txtPlacCodigo;
	}

	public void setTxtPlacCodigo(InputText txtPlacCodigo) {
		this.txtPlacCodigo = txtPlacCodigo;
	}

	public List<PsyPlanAccionDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataPsyPlanAccion(getEmpresaIntoSession());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<PsyPlanAccionDTO> psyPlanAccionDTO) {
		this.data = psyPlanAccionDTO;
	}

	public PsyPlanAccionDTO getSelectedPsyPlanAccion() {
		return selectedPsyPlanAccion;
	}

	public void setSelectedPsyPlanAccion(PsyPlanAccionDTO psyPlanAccion) {
		this.selectedPsyPlanAccion = psyPlanAccion;
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

	public InputText getTxtPlanEstrategico() {
		return txtPlanEstrategico;
	}

	public void setTxtPlanEstrategico(InputText txtPlanEstrategico) {
		this.txtPlanEstrategico = txtPlanEstrategico;
	}

	public boolean isRenderFechaFinal() {
		return renderFechaFinal;
	}

	public void setRenderFechaFinal(boolean renderFechaFinal) {
		this.renderFechaFinal = renderFechaFinal;
	}

	public String getSomPrograma() {
		return somPrograma;
	}

	public void setSomPrograma(String somPrograma) {
		this.somPrograma = somPrograma;
	}

	public List<SelectItem> getLosProgramas() {
		
		try {
			losProgramas = new ArrayList<SelectItem>();
			List<PsyProgramaDTO> losTiposPaises = businessDelegatorView.getDataPsyPrograma(getEmpresaIntoSession());
			for (PsyProgramaDTO programa : losTiposPaises) {
				if (programa.getEstadoRegistro().trim().equals("Activo")) {
					SelectItem selectItem = new SelectItem(
							programa.getProgCodigo(), programa.getNombre());
					losProgramas.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los programas");
		}
		
		return losProgramas;
	}

	public void setLosProgramas(List<SelectItem> losProgramas) {
		this.losProgramas = losProgramas;
	}
	
}
