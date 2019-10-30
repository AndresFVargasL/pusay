package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyPlanAccionDTO;
import com.vortexbird.pusay.modelo.dto.PsyPresupuestoDTO;
import com.vortexbird.pusay.presentation.businessDelegate.*;
import com.vortexbird.pusay.presentation.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.RowEditEvent;
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
public class PsyPresupuestoView implements Serializable {
	private static final String ESTADO_REGISTRO_ACTIVO = "A";
	private static final String ESTADO_PRESUPUESTO_ABIERTO = "A";
	private static final String ESTADO_PRESUPUESTO_CERRADO = "C";
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(PsyPresupuestoView.class);
	private InputText txtEstadoPresupuesto;
	private InputText txtEstadoRegistro;
	private InputText txtMoneCodigo_PsyMoneda;
	private InputText txtPlacCodigo_PsyPlanAccion;
	private InputText txtPresCodigo;
	private InputText txtAbreviatura;
	private Calendar txtFechaFin;
	private Calendar txtFechaInicio;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<PsyPresupuestoDTO> data;
	private PsyPresupuestoDTO selectedPsyPresupuesto;
	private PsyPresupuesto entity;
	private boolean showDialog;
	private String somMoneda;
	private String somPlanAccion;
	private List<SelectItem> losTiposDeMoneda;
	private List<SelectItem> losPlanes;
	private SelectOneMenu selectOneMenuPlanAccion;
	private SelectOneMenu selectOneMenuMoneda;
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public PsyPresupuestoView() {
		super();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			PsyPresupuestoDTO psyPresupuestoDTO = (PsyPresupuestoDTO) e
					.getObject();

			if (txtEstadoPresupuesto == null) {
				txtEstadoPresupuesto = new InputText();
			}

			txtEstadoPresupuesto.setValue(psyPresupuestoDTO
					.getEstadoPresupuesto());

			if (txtEstadoRegistro == null) {
				txtEstadoRegistro = new InputText();
			}

			txtEstadoRegistro.setValue(psyPresupuestoDTO.getEstadoRegistro());

			if (txtMoneCodigo_PsyMoneda == null) {
				txtMoneCodigo_PsyMoneda = new InputText();
			}

			txtMoneCodigo_PsyMoneda.setValue(psyPresupuestoDTO
					.getMoneCodigo_PsyMoneda());

			if (txtPlacCodigo_PsyPlanAccion == null) {
				txtPlacCodigo_PsyPlanAccion = new InputText();
			}

			txtPlacCodigo_PsyPlanAccion.setValue(psyPresupuestoDTO
					.getPlacCodigo_PsyPlanAccion());

			if (txtPresCodigo == null) {
				txtPresCodigo = new InputText();
			}

			txtPresCodigo.setValue(psyPresupuestoDTO.getPresCodigo());

			if (txtFechaFin == null) {
				txtFechaFin = new Calendar();
			}

			txtFechaFin.setValue(psyPresupuestoDTO.getFechaFin());

			if (txtFechaInicio == null) {
				txtFechaInicio = new Calendar();
			}

			txtFechaInicio.setValue(psyPresupuestoDTO.getFechaInicio());

			Long presCodigo = FacesUtils.checkLong(txtPresCodigo);
			entity = businessDelegatorView.getPsyPresupuesto(presCodigo);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedPsyPresupuesto = null;
		setShowDialog(true);
		txtEstadoPresupuesto.setValue("Abierto");
		setSomMoneda("");
		setSomPlanAccion("");
		txtAbreviatura.setValue("");
		return "";
	}
	public void generarPresupuestoDefinitivo(ActionEvent evt) {
		try {
			selectedPsyPresupuesto = (PsyPresupuestoDTO) (evt.getComponent()
					.getAttributes().get("selectedPsyPresupuesto"));
			businessDelegatorView.generarPresupuestoDefinitivo(selectedPsyPresupuesto.getPresCodigo(), selectedPsyPresupuesto.getPlacCodigo_PsyPlanAccion());
			data=null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public String action_clear() {
		entity = null;
		selectedPsyPresupuesto = null;

		if (txtEstadoPresupuesto != null) {
			txtEstadoPresupuesto.setValue(null);
			txtEstadoPresupuesto.setDisabled(true);
		}
		
		
		
		if (txtAbreviatura != null) {
			txtAbreviatura.setValue(null);
			txtAbreviatura.setDisabled(true);
		}

		if (txtEstadoRegistro != null) {
			txtEstadoRegistro.setValue(null);
			txtEstadoRegistro.setDisabled(true);
		}

		if (txtMoneCodigo_PsyMoneda != null) {
			txtMoneCodigo_PsyMoneda.setValue(null);
			txtMoneCodigo_PsyMoneda.setDisabled(true);
		}

		if (txtPlacCodigo_PsyPlanAccion != null) {
			txtPlacCodigo_PsyPlanAccion.setValue(null);
			txtPlacCodigo_PsyPlanAccion.setDisabled(true);
		}

		if (txtFechaFin != null) {
			txtFechaFin.setValue(null);
			txtFechaFin.setDisabled(false);
		}

		if (txtFechaInicio != null) {
			txtFechaInicio.setValue(null);
			txtFechaInicio.setDisabled(false);
		}

		if (txtPresCodigo != null) {
			txtPresCodigo.setValue(null);
			txtPresCodigo.setDisabled(false);
		}

		if (btnSave != null) {
			btnSave.setDisabled(false);
		}

		if (btnDelete != null) {
			btnDelete.setDisabled(false);
		}

		return "";
	}

	public void listener_txtFechaFin() {
		Date inputDate = (Date) txtFechaFin.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage(
				"",
				new FacesMessage("Selected Date "
						+ dateFormat.format(inputDate)));
	}

	public void listener_txtAbreviatura() {
		try {
			Long moneCodigo = Long.parseLong(somMoneda);
			PsyMoneda moneda = new PsyMoneda();
			moneda = businessDelegatorView.getPsyMoneda(moneCodigo);
			if (txtAbreviatura == null) {
				txtAbreviatura = new InputText();
			}
			txtAbreviatura.setValue(moneda.getAbreviatura());
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error cargando las abreviaturas");
		}

	}

	public void listener_txtFechaInicio() {
		Date inputDate = (Date) txtFechaInicio.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage(
				"",
				new FacesMessage("Selected Date "
						+ dateFormat.format(inputDate)));
	}

	public void listener_txtId() {
		try {
			Long presCodigo = FacesUtils.checkLong(txtPresCodigo);
			entity = (presCodigo != null) ? businessDelegatorView
					.getPsyPresupuesto(presCodigo) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtEstadoPresupuesto.setDisabled(false);
			txtEstadoRegistro.setDisabled(false);
			txtMoneCodigo_PsyMoneda.setDisabled(false);
			txtPlacCodigo_PsyPlanAccion.setDisabled(false);
			txtFechaFin.setDisabled(false);
			txtFechaInicio.setDisabled(false);
			txtPresCodigo.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtEstadoPresupuesto.setValue(entity.getEstadoPresupuesto());
			txtEstadoPresupuesto.setDisabled(false);
			txtEstadoRegistro.setValue(entity.getEstadoRegistro());
			txtEstadoRegistro.setDisabled(false);
			txtFechaFin.setValue(entity.getFechaFin());
			txtFechaFin.setDisabled(false);
			txtFechaInicio.setValue(entity.getFechaInicio());
			txtFechaInicio.setDisabled(false);
			txtMoneCodigo_PsyMoneda.setValue(entity.getPsyMoneda()
					.getMoneCodigo());
			txtMoneCodigo_PsyMoneda.setDisabled(false);
			txtPlacCodigo_PsyPlanAccion.setValue(entity.getPsyPlanAccion()
					.getPlacCodigo());
			txtPlacCodigo_PsyPlanAccion.setDisabled(false);
			txtPresCodigo.setValue(entity.getPresCodigo());
			txtPresCodigo.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedPsyPresupuesto = (PsyPresupuestoDTO) (evt.getComponent()
				.getAttributes().get("selectedPsyPresupuesto"));
		txtEstadoPresupuesto
				.setValue((selectedPsyPresupuesto.getEstadoPresupuesto()
						.equals("A")) ? "Abierto" : (selectedPsyPresupuesto
						.getEstadoPresupuesto().equals("I")) ? "Iniciado"
						: (selectedPsyPresupuesto.getEstadoPresupuesto()
								.equals("C")) ? "Cerrado" : null);
		txtEstadoPresupuesto.setDisabled(true);
		txtFechaFin.setValue(selectedPsyPresupuesto.getFechaFin());
		txtFechaFin.setDisabled(false);
		txtFechaInicio.setValue(selectedPsyPresupuesto.getFechaInicio());
		txtFechaInicio.setDisabled(false);
		setSomMoneda(selectedPsyPresupuesto.getMoneCodigo_PsyMoneda()
				.toString());
		setSomPlanAccion(selectedPsyPresupuesto.getPlacCodigo_PsyPlanAccion()
				.toString());
		listener_txtAbreviatura();
		btnSave.setDisabled(false);
		setShowDialog(true);
		if(selectedPsyPresupuesto.getEstadoPresupuesto().equals("I")){
			txtFechaInicio.setDisabled(true);
			txtFechaFin.setDisabled(true);
			selectOneMenuPlanAccion.setDisabled(true);
			selectOneMenuMoneda.setDisabled(true);
		}

		return "";
	}

	// @PostConstruct
	// public void testSetDefaultValueForSelectOneMenu(){
	//
	// try {
	// setSomMoneda(businessDelegatorView.getPsyMoneda(9L).getMoneCodigo().toString());
	// listener_txtAbreviatura();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public String action_save() {
		try {
			if ((selectedPsyPresupuesto == null) && (entity == null)) {
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
			entity = new PsyPresupuesto();
			entity.setFechaInicio(FacesUtils.checkDate(txtFechaInicio));
			entity.setFechaFin(FacesUtils.checkDate(txtFechaFin));
			entity.setPsyMoneda((somMoneda != null) ? businessDelegatorView
					.getPsyMoneda(Long.parseLong(somMoneda)) : null);
			entity.setPsyPlanAccion((somPlanAccion != null) ? businessDelegatorView
					.getPsyPlanAccion(Long.parseLong(somPlanAccion)) : null);
			entity.setEstadoPresupuesto(ESTADO_PRESUPUESTO_ABIERTO);
			entity.setEstadoRegistro(ESTADO_REGISTRO_ACTIVO);
			businessDelegatorView.savePsyPresupuesto(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
			data = null;
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
				Long presCodigo = new Long(
						selectedPsyPresupuesto.getPresCodigo());
				entity = businessDelegatorView.getPsyPresupuesto(presCodigo);
			}
			entity.setFechaInicio(FacesUtils.checkDate(txtFechaInicio));
			entity.setFechaFin(FacesUtils.checkDate(txtFechaFin));
			entity.setPsyMoneda((somMoneda != null) ? businessDelegatorView
					.getPsyMoneda(Long.parseLong(somMoneda)) : null);
			entity.setPsyPlanAccion((somPlanAccion != null) ? businessDelegatorView
					.getPsyPlanAccion(Long.parseLong(somPlanAccion)) : null);
			businessDelegatorView.updatePsyPresupuesto(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			action_clear();
			data = null;
			action_closeDialog();
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedPsyPresupuesto = (PsyPresupuestoDTO) (evt.getComponent()
					.getAttributes().get("selectedPsyPresupuesto"));

			Long presCodigo = new Long(selectedPsyPresupuesto.getPresCodigo());
			entity = businessDelegatorView.getPsyPresupuesto(presCodigo);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Long presCodigo = FacesUtils.checkLong(txtPresCodigo);
			entity = businessDelegatorView.getPsyPresupuesto(presCodigo);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deletePsyPresupuesto(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
			data = null;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public String cerrarPresupuesto(ActionEvent evt) {
		try {
			selectedPsyPresupuesto = (PsyPresupuestoDTO) (evt.getComponent()
					.getAttributes().get("selectedPsyPresupuesto"));

			Long presCodigo = new Long(
					selectedPsyPresupuesto.getPresCodigo());
			PsyPresupuesto entity = new PsyPresupuesto();
			entity = businessDelegatorView.getPsyPresupuesto(presCodigo);
			if(!entity.getEstadoPresupuesto().equals(ESTADO_PRESUPUESTO_CERRADO)){
				entity.setEstadoPresupuesto(ESTADO_PRESUPUESTO_CERRADO);
				businessDelegatorView.updatePsyPresupuesto(entity);
			}else{
				FacesUtils.addErrorMessage("El presupuesto ya esta cerrado");
			}
			data=null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_closeDialog() {
		setShowDialog(false);
		action_clear();

		return "";
	}

	public String actionDeleteDataTableEditable(ActionEvent evt) {
		try {
			selectedPsyPresupuesto = (PsyPresupuestoDTO) (evt.getComponent()
					.getAttributes().get("selectedPsyPresupuesto"));

			Long presCodigo = new Long(selectedPsyPresupuesto.getPresCodigo());
			entity = businessDelegatorView.getPsyPresupuesto(presCodigo);
			businessDelegatorView.deletePsyPresupuesto(entity);
			data.remove(selectedPsyPresupuesto);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(String estadoPresupuesto,
			String estadoRegistro, Date fechaFin, Date fechaInicio,
			Long presCodigo, Long moneCodigo_PsyMoneda,
			Long placCodigo_PsyPlanAccion) throws Exception {
		try {
			entity.setEstadoPresupuesto(FacesUtils
					.checkString(estadoPresupuesto));
			entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
			entity.setFechaFin(FacesUtils.checkDate(fechaFin));
			entity.setFechaInicio(FacesUtils.checkDate(fechaInicio));
			businessDelegatorView.updatePsyPresupuesto(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("PsyPresupuestoView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputText getTxtEstadoPresupuesto() {
		return txtEstadoPresupuesto;
	}

	public void setTxtEstadoPresupuesto(InputText txtEstadoPresupuesto) {
		this.txtEstadoPresupuesto = txtEstadoPresupuesto;
	}

	public InputText getTxtEstadoRegistro() {
		return txtEstadoRegistro;
	}

	public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
		this.txtEstadoRegistro = txtEstadoRegistro;
	}

	public InputText getTxtMoneCodigo_PsyMoneda() {
		return txtMoneCodigo_PsyMoneda;
	}

	public void setTxtMoneCodigo_PsyMoneda(InputText txtMoneCodigo_PsyMoneda) {
		this.txtMoneCodigo_PsyMoneda = txtMoneCodigo_PsyMoneda;
	}

	public InputText getTxtPlacCodigo_PsyPlanAccion() {
		return txtPlacCodigo_PsyPlanAccion;
	}

	public void setTxtPlacCodigo_PsyPlanAccion(
			InputText txtPlacCodigo_PsyPlanAccion) {
		this.txtPlacCodigo_PsyPlanAccion = txtPlacCodigo_PsyPlanAccion;
	}

	public Calendar getTxtFechaFin() {
		return txtFechaFin;
	}

	public void setTxtFechaFin(Calendar txtFechaFin) {
		this.txtFechaFin = txtFechaFin;
	}

	public Calendar getTxtFechaInicio() {
		return txtFechaInicio;
	}

	public void setTxtFechaInicio(Calendar txtFechaInicio) {
		this.txtFechaInicio = txtFechaInicio;
	}

	public InputText getTxtPresCodigo() {
		return txtPresCodigo;
	}

	public void setTxtPresCodigo(InputText txtPresCodigo) {
		this.txtPresCodigo = txtPresCodigo;
	}

	public List<PsyPresupuestoDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataPsyPresupuesto(getEmpresaIntoSession());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<PsyPresupuestoDTO> psyPresupuestoDTO) {
		this.data = psyPresupuestoDTO;
	}

	public PsyPresupuestoDTO getSelectedPsyPresupuesto() {
		return selectedPsyPresupuesto;
	}

	public void setSelectedPsyPresupuesto(PsyPresupuestoDTO psyPresupuesto) {
		this.selectedPsyPresupuesto = psyPresupuesto;
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

	public String getSomMoneda() {
		return somMoneda;
	}

	public void setSomMoneda(String somMoneda) {
		this.somMoneda = somMoneda;
	}

	public String getSomPlanAccion() {
		return somPlanAccion;
	}

	public void setSomPlanAccion(String somPlanAccion) {
		this.somPlanAccion = somPlanAccion;
	}

	public List<SelectItem> getLosTiposDeMoneda() {

		try {
			losTiposDeMoneda = new ArrayList<SelectItem>();
			List<PsyMoneda> lasMonedas = businessDelegatorView.getPsyMoneda();
			for (PsyMoneda moneda : lasMonedas) {
				if (moneda.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							moneda.getMoneCodigo(), moneda.getNombre());
					losTiposDeMoneda.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los tipos de moneda");
		}

		return losTiposDeMoneda;
	}

	public void setLosTiposDeMoneda(List<SelectItem> losTiposDeMoneda) {
		this.losTiposDeMoneda = losTiposDeMoneda;
	}
	
	public PsyEmpresa getEmpresaIntoSession(){
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);
		return (PsyEmpresa) httpSession.getAttribute("empresa");		
	}

	public List<SelectItem> getLosPlanes() {
		try {
			losPlanes = new ArrayList<SelectItem>();
			List<PsyPlanAccion> losTiposDePlanes = businessDelegatorView
					.findPsyPlanAccionByEmpresa(getEmpresaIntoSession());
			for (PsyPlanAccion plan : losTiposDePlanes) {
				if (plan.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							plan.getPlacCodigo(), plan.getNombre());
					losPlanes.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los planes activos");
		}

		return losPlanes;
	}

	public void setLosPlanes(List<SelectItem> losPlanes) {
		this.losPlanes = losPlanes;
	}

	/**
	 * @return the txtAbreviatura
	 */
	public InputText getTxtAbreviatura() {
		return txtAbreviatura;
	}

	/**
	 * @param txtAbreviatura
	 *            the txtAbreviatura to set
	 */
	public void setTxtAbreviatura(InputText txtAbreviatura) {
		this.txtAbreviatura = txtAbreviatura;
	}

	public SelectOneMenu getSelectOneMenuPlanAccion() {
		return selectOneMenuPlanAccion;
	}

	public void setSelectOneMenuPlanAccion(SelectOneMenu selectOneMenuPlanAccion) {
		this.selectOneMenuPlanAccion = selectOneMenuPlanAccion;
	}

	public SelectOneMenu getSelectOneMenuMoneda() {
		return selectOneMenuMoneda;
	}

	public void setSelectOneMenuMoneda(SelectOneMenu selectOneMenuMoneda) {
		this.selectOneMenuMoneda = selectOneMenuMoneda;
	}
	
	
}
