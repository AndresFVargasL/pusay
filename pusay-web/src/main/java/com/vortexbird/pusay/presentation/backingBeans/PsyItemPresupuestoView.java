package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyDetalleEstrategiasDTO;
import com.vortexbird.pusay.modelo.dto.PsyItemPresupuestoDTO;
import com.vortexbird.pusay.modelo.dto.PsyPresupuestoDTO;
import com.vortexbird.pusay.modelo.dto.PsyProgramaDTO;
import com.vortexbird.pusay.presentation.businessDelegate.*;
import com.vortexbird.pusay.presentation.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import java.util.TimeZone;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyItemPresupuestoView implements Serializable {
	private static final String ESTADO_REGISTRO_ACTIVO = "A";
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(PsyItemPresupuestoView.class);
	private InputText txtEstadoRegistro;
	private InputText txtValorEjecutado;
	private InputText txtValorPresupuestado;
	private InputText txtPresCodigo_PsyPresupuesto;
	private InputText txtTiipCodigo_PsyTipoItemPresupuesto;
	private InputText txtIpreCodigo;
	private Calendar txtFechaFin;
	private Calendar txtFechaInicio;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private CommandButton btnNew;
	private List<PsyItemPresupuestoDTO> data;
	private PsyItemPresupuestoDTO selectedPsyItemPresupuesto;
	private PsyItemPresupuesto entity;
	private String somPresupuesto;
	private String somTipoItem;
	private List<SelectItem> losPresupuestos;
	private List<SelectItem> losTiposItems;
	private boolean showDialog;
	private boolean disableBtnNew = true;
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public PsyItemPresupuestoView() {
		super();
	}

	public void listener_txtPresupuesto() {
		btnNew = new CommandButton();
		btnNew.setDisabled(true);
		setDisableBtnNew(true);
		if(!getSomPresupuesto().trim().equals("")){
			btnNew.setDisabled(false);
			setDisableBtnNew(false);
		}else{
			FacesUtils.refresh();
		}
		data = null;
		getData();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			PsyItemPresupuestoDTO psyItemPresupuestoDTO = (PsyItemPresupuestoDTO) e
					.getObject();

			if (txtEstadoRegistro == null) {
				txtEstadoRegistro = new InputText();
			}

			txtEstadoRegistro.setValue(psyItemPresupuestoDTO
					.getEstadoRegistro());

			if (txtValorEjecutado == null) {
				txtValorEjecutado = new InputText();
			}

			txtValorEjecutado.setValue(psyItemPresupuestoDTO
					.getValorEjecutado());

			if (txtValorPresupuestado == null) {
				txtValorPresupuestado = new InputText();
			}

			txtValorPresupuestado.setValue(psyItemPresupuestoDTO
					.getValorPresupuestado());

			if (txtPresCodigo_PsyPresupuesto == null) {
				txtPresCodigo_PsyPresupuesto = new InputText();
			}

			txtPresCodigo_PsyPresupuesto.setValue(psyItemPresupuestoDTO
					.getPresCodigo_PsyPresupuesto());

			if (txtTiipCodigo_PsyTipoItemPresupuesto == null) {
				txtTiipCodigo_PsyTipoItemPresupuesto = new InputText();
			}

			txtTiipCodigo_PsyTipoItemPresupuesto.setValue(psyItemPresupuestoDTO
					.getTiipCodigo_PsyTipoItemPresupuesto());

			if (txtIpreCodigo == null) {
				txtIpreCodigo = new InputText();
			}

			txtIpreCodigo.setValue(psyItemPresupuestoDTO.getIpreCodigo());

			if (txtFechaFin == null) {
				txtFechaFin = new Calendar();
			}

			txtFechaFin.setValue(psyItemPresupuestoDTO.getFechaFin());

			if (txtFechaInicio == null) {
				txtFechaInicio = new Calendar();
			}

			txtFechaInicio.setValue(psyItemPresupuestoDTO.getFechaInicio());

			Long ipreCodigo = FacesUtils.checkLong(txtIpreCodigo);
			entity = businessDelegatorView.getPsyItemPresupuesto(ipreCodigo);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedPsyItemPresupuesto = null;
		setSomTipoItem("");
		setShowDialog(true);
		return "";
	}

	public String action_clear() {
		entity = null;
		selectedPsyItemPresupuesto = null;

		if (txtEstadoRegistro != null) {
			txtEstadoRegistro.setValue(null);
			txtEstadoRegistro.setDisabled(true);
		}

		if (txtValorEjecutado != null) {
			txtValorEjecutado.setValue(null);
			txtValorEjecutado.setDisabled(false);
		}

		if (txtValorPresupuestado != null) {
			txtValorPresupuestado.setValue(null);
			txtValorPresupuestado.setDisabled(false);
		}

		if (txtPresCodigo_PsyPresupuesto != null) {
			txtPresCodigo_PsyPresupuesto.setValue(null);
			txtPresCodigo_PsyPresupuesto.setDisabled(true);
		}

		if (txtTiipCodigo_PsyTipoItemPresupuesto != null) {
			txtTiipCodigo_PsyTipoItemPresupuesto.setValue(null);
			txtTiipCodigo_PsyTipoItemPresupuesto.setDisabled(true);
		}

		if (txtFechaFin != null) {
			txtFechaFin.setValue(null);
			txtFechaFin.setDisabled(false);
		}

		if (txtFechaInicio != null) {
			txtFechaInicio.setValue(null);
			txtFechaInicio.setDisabled(false);
		}

		if (txtIpreCodigo != null) {
			txtIpreCodigo.setValue(null);
			txtIpreCodigo.setDisabled(false);
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
				new FacesMessage("Fecha Seleccionada "
						+ dateFormat.format(inputDate)));
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
			Long ipreCodigo = FacesUtils.checkLong(txtIpreCodigo);
			entity = (ipreCodigo != null) ? businessDelegatorView
					.getPsyItemPresupuesto(ipreCodigo) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtEstadoRegistro.setDisabled(false);
			txtValorEjecutado.setDisabled(false);
			txtValorPresupuestado.setDisabled(false);
			txtPresCodigo_PsyPresupuesto.setDisabled(false);
			txtTiipCodigo_PsyTipoItemPresupuesto.setDisabled(false);
			txtFechaFin.setDisabled(false);
			txtFechaInicio.setDisabled(false);
			txtIpreCodigo.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtEstadoRegistro.setValue(entity.getEstadoRegistro());
			txtEstadoRegistro.setDisabled(false);
			txtFechaFin.setValue(entity.getFechaFin());
			txtFechaFin.setDisabled(false);
			txtFechaInicio.setValue(entity.getFechaInicio());
			txtFechaInicio.setDisabled(false);
			txtValorEjecutado.setValue(entity.getValorEjecutado());
			txtValorEjecutado.setDisabled(false);
			txtValorPresupuestado.setValue(entity.getValorPresupuestado());
			txtValorPresupuestado.setDisabled(false);
			txtPresCodigo_PsyPresupuesto.setValue(entity.getPsyPresupuesto()
					.getPresCodigo());
			txtPresCodigo_PsyPresupuesto.setDisabled(false);
			txtTiipCodigo_PsyTipoItemPresupuesto.setValue(entity
					.getPsyTipoItemPresupuesto().getTiipCodigo());
			txtTiipCodigo_PsyTipoItemPresupuesto.setDisabled(false);
			txtIpreCodigo.setValue(entity.getIpreCodigo());
			txtIpreCodigo.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedPsyItemPresupuesto = (PsyItemPresupuestoDTO) (evt
				.getComponent().getAttributes()
				.get("selectedPsyItemPresupuesto"));
		try {
		txtFechaFin
				.setValue((selectedPsyItemPresupuesto.getFechaFin() != null) ? selectedPsyItemPresupuesto
						.getFechaFin() : null);
		txtFechaFin.setDisabled(false);
		txtFechaInicio.setValue(selectedPsyItemPresupuesto.getFechaInicio());
		txtFechaInicio.setDisabled(false);
		txtValorEjecutado.setValue((selectedPsyItemPresupuesto
				.getValorEjecutado() != null) ? selectedPsyItemPresupuesto
				.getValorEjecutado() : null);
		txtValorEjecutado.setDisabled(false);
		txtValorPresupuestado.setValue(selectedPsyItemPresupuesto
				.getValorPresupuestado());
		txtValorPresupuestado.setDisabled(false);
		setSomTipoItem(selectedPsyItemPresupuesto
				.getTiipCodigo_PsyTipoItemPresupuesto().toString());
		btnSave.setDisabled(false);
		if (selectedPsyItemPresupuesto.getFechaFin() != null) {

			txtFechaFin.setDisabled(true);

			txtFechaInicio.setDisabled(true);

			txtValorEjecutado.setDisabled(true);

			txtValorPresupuestado.setDisabled(true);

			btnSave.setDisabled(true);
		}
		
			
		
		if(businessDelegatorView.getPsyPresupuesto(selectedPsyItemPresupuesto.getPresCodigo_PsyPresupuesto()).getEstadoPresupuesto().equals("I")){
				txtFechaInicio.setDisabled(true);
				txtValorPresupuestado.setDisabled(true);
		}

		setShowDialog(true);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String action_save() {
		try {
			if ((selectedPsyItemPresupuesto == null) && (entity == null)) {
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
			entity = new PsyItemPresupuesto();		
			entity.setEstadoRegistro(ESTADO_REGISTRO_ACTIVO);
			entity.setFechaInicio(FacesUtils.checkDate(txtFechaInicio));
			entity.setFechaFin((FacesUtils.checkDate(txtFechaFin) != null) ? FacesUtils
					.checkDate(txtFechaFin) : null);
			entity.setValorEjecutado((FacesUtils.checkDouble(txtValorEjecutado) != null) ? FacesUtils
					.checkDouble(txtValorEjecutado) : null);
			entity.setValorPresupuestado(FacesUtils
					.checkDouble(txtValorPresupuestado));
			entity.setPsyPresupuesto((somPresupuesto != null) ? businessDelegatorView
					.getPsyPresupuesto(Long.parseLong(somPresupuesto)) : null);
			entity.setPsyTipoItemPresupuesto((somTipoItem != null) ? businessDelegatorView
					.getPsyTipoItemPresupuesto(Long.parseLong(somTipoItem))
					: null);
			businessDelegatorView.savePsyItemPresupuesto(entity);
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
				Long ipreCodigo = new Long(
						selectedPsyItemPresupuesto.getIpreCodigo());
				entity = businessDelegatorView
						.getPsyItemPresupuesto(ipreCodigo);
			}

			entity.setFechaFin((FacesUtils.checkDate(txtFechaFin) != null) ? FacesUtils
					.checkDate(txtFechaFin) : null);
			entity.setFechaInicio(FacesUtils.checkDate(txtFechaInicio));
			entity.setValorEjecutado((FacesUtils.checkDouble(txtValorEjecutado) != null) ? FacesUtils
					.checkDouble(txtValorEjecutado) : null);
			entity.setValorPresupuestado(FacesUtils
					.checkDouble(txtValorPresupuestado));
			entity.setPsyPresupuesto((somPresupuesto != null) ? businessDelegatorView
					.getPsyPresupuesto(Long.parseLong(somPresupuesto)) : null);
			entity.setPsyTipoItemPresupuesto((somTipoItem != null) ? businessDelegatorView
					.getPsyTipoItemPresupuesto(Long.parseLong(somTipoItem))
					: null);
			businessDelegatorView.updatePsyItemPresupuesto(entity);
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
			selectedPsyItemPresupuesto = (PsyItemPresupuestoDTO) (evt
					.getComponent().getAttributes()
					.get("selectedPsyItemPresupuesto"));

			Long ipreCodigo = new Long(
					selectedPsyItemPresupuesto.getIpreCodigo());
			entity = businessDelegatorView.getPsyItemPresupuesto(ipreCodigo);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Long ipreCodigo = FacesUtils.checkLong(txtIpreCodigo);
			entity = businessDelegatorView.getPsyItemPresupuesto(ipreCodigo);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deletePsyItemPresupuesto(entity);
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
			selectedPsyItemPresupuesto = (PsyItemPresupuestoDTO) (evt
					.getComponent().getAttributes()
					.get("selectedPsyItemPresupuesto"));

			Long ipreCodigo = new Long(
					selectedPsyItemPresupuesto.getIpreCodigo());
			entity = businessDelegatorView.getPsyItemPresupuesto(ipreCodigo);
			businessDelegatorView.deletePsyItemPresupuesto(entity);
			data.remove(selectedPsyItemPresupuesto);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(String estadoRegistro, Date fechaFin,
			Date fechaInicio, Long ipreCodigo, Double valorEjecutado,
			Double valorPresupuestado, Long presCodigo_PsyPresupuesto,
			Long tiipCodigo_PsyTipoItemPresupuesto) throws Exception {
		try {
			entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
			entity.setFechaFin(FacesUtils.checkDate(fechaFin));
			entity.setFechaInicio(FacesUtils.checkDate(fechaInicio));
			entity.setValorEjecutado(FacesUtils.checkDouble(valorEjecutado));
			entity.setValorPresupuestado(FacesUtils
					.checkDouble(valorPresupuestado));
			businessDelegatorView.updatePsyItemPresupuesto(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("PsyItemPresupuestoView").requestRender();
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

	public InputText getTxtValorEjecutado() {
		return txtValorEjecutado;
	}

	public void setTxtValorEjecutado(InputText txtValorEjecutado) {
		this.txtValorEjecutado = txtValorEjecutado;
	}

	public InputText getTxtValorPresupuestado() {
		return txtValorPresupuestado;
	}

	public void setTxtValorPresupuestado(InputText txtValorPresupuestado) {
		this.txtValorPresupuestado = txtValorPresupuestado;
	}

	public InputText getTxtPresCodigo_PsyPresupuesto() {
		return txtPresCodigo_PsyPresupuesto;
	}

	public void setTxtPresCodigo_PsyPresupuesto(
			InputText txtPresCodigo_PsyPresupuesto) {
		this.txtPresCodigo_PsyPresupuesto = txtPresCodigo_PsyPresupuesto;
	}

	public InputText getTxtTiipCodigo_PsyTipoItemPresupuesto() {
		return txtTiipCodigo_PsyTipoItemPresupuesto;
	}

	public void setTxtTiipCodigo_PsyTipoItemPresupuesto(
			InputText txtTiipCodigo_PsyTipoItemPresupuesto) {
		this.txtTiipCodigo_PsyTipoItemPresupuesto = txtTiipCodigo_PsyTipoItemPresupuesto;
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

	public InputText getTxtIpreCodigo() {
		return txtIpreCodigo;
	}

	public void setTxtIpreCodigo(InputText txtIpreCodigo) {
		this.txtIpreCodigo = txtIpreCodigo;
	}

	public List<PsyItemPresupuestoDTO> getData() {
		try {
			if (data == null && somPresupuesto != null && !somPresupuesto.trim().equals("")) {
				data = businessDelegatorView
						.getDataPsyItemPresupuestoByPresCodigo(Long
								.parseLong(somPresupuesto));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<PsyItemPresupuestoDTO> psyItemPresupuestoDTO) {
		this.data = psyItemPresupuestoDTO;
	}

	public PsyItemPresupuestoDTO getSelectedPsyItemPresupuesto() {
		return selectedPsyItemPresupuesto;
	}

	public void setSelectedPsyItemPresupuesto(
			PsyItemPresupuestoDTO psyItemPresupuesto) {
		this.selectedPsyItemPresupuesto = psyItemPresupuesto;
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

	public String getSomPresupuesto() {
		return somPresupuesto;
	}

	public void setSomPresupuesto(String somPresupuesto) {
		this.somPresupuesto = somPresupuesto;
	}

	public List<SelectItem> getLosPresupuestos() {

		try {
			losPresupuestos = new ArrayList<SelectItem>();
			List<PsyPresupuestoDTO> losTiposDePresupuesto = businessDelegatorView
					.getDataPsyPresupuestoOnItem(getEmpresaIntoSession());
			for (PsyPresupuestoDTO presupuesto : losTiposDePresupuesto) {
				if (presupuesto.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							presupuesto.getPresCodigo(), presupuesto.getNombrePlanAccion());
					losPresupuestos.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los presupuestos");
		}

		return losPresupuestos;
	}

	public void setLosPresupuestos(List<SelectItem> losPresupuestos) {
		this.losPresupuestos = losPresupuestos;
	}

	public String getSomTipoItem() {
		return somTipoItem;
	}

	public void setSomTipoItem(String somTipoItem) {
		this.somTipoItem = somTipoItem;
	}

	public List<SelectItem> getLosTiposItems() {
		try {
			losTiposItems = new ArrayList<SelectItem>();
			List<PsyTipoItemPresupuesto> losTiposDeItem = businessDelegatorView
					.getPsyTipoItemPresupuesto();
			for (PsyTipoItemPresupuesto tipo : losTiposDeItem) {
				if (tipo.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							tipo.getTiipCodigo(), tipo.getNombre());
					losTiposItems.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los tipos de item");
		}

		return losTiposItems;
	}

	public void setLosTiposItems(List<SelectItem> losTiposItems) {
		this.losTiposItems = losTiposItems;
	}

	public CommandButton getBtnNew() {
		return btnNew;
	}

	public void setBtnNew(CommandButton btnNew) {
		this.btnNew = btnNew;
	}

	public boolean isDisableBtnNew() {
		return disableBtnNew;
	}

	public void setDisableBtnNew(boolean disableBtnNew) {
		this.disableBtnNew = disableBtnNew;
	}
	
	public PsyEmpresa getEmpresaIntoSession(){
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);
		return (PsyEmpresa) httpSession.getAttribute("empresa");		
	}
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {

		try {
			if(somPresupuesto!=null && !somPresupuesto.trim().equals("")){
			Long codigoPlanAccion = businessDelegatorView.getPsyPresupuesto(Long.parseLong(somPresupuesto)).getPsyPlanAccion().getPlacCodigo();
			PsyPlanAccion planAccion = businessDelegatorView.getPsyPlanAccion(codigoPlanAccion);
			Long codigoPrograma = businessDelegatorView.getPsyPlanAccion(codigoPlanAccion).getPsyPrograma().getProgCodigo();
			PsyPrograma programaSeleccionado = businessDelegatorView.getPsyPrograma(codigoPrograma);
			PsyProgramaDTO programaTmp = new PsyProgramaDTO();
			PsyDetalleEstrategiasDTO estrategiasDTO = new PsyDetalleEstrategiasDTO();		
			
			List<PsyProgramaDTO> listProgramas = businessDelegatorView.getDataPsyPrograma(FacesUtils.getEmpresaIntoSession());
			for (PsyProgramaDTO psyProgramaDTO : listProgramas) {
				if(psyProgramaDTO.getProgCodigo()==programaSeleccionado.getProgCodigo()){
					programaTmp = psyProgramaDTO;
				}
			}
			
			if(programaTmp!=null){
			List<PsyDetalleEstrategiasDTO> laEstrategia = businessDelegatorView.consultaEstrategiasParaPrograma(getEmpresaIntoSession());
			for (PsyDetalleEstrategiasDTO psyDetalleEstrategiasDTO : laEstrategia) {
				if(psyDetalleEstrategiasDTO.getDmaeCodigo()==programaTmp.getDmaeCodigo()){
				estrategiasDTO = psyDetalleEstrategiasDTO;
				}
			}
			}	
			Document pdf = (Document) document;
			pdf.open();
			pdf.setPageSize(PageSize.LETTER);
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String logo = servletContext.getRealPath("") + "images" + File.separator + "logoPusay.png";
			Image imageCenter = Image.getInstance(logo);
			imageCenter.setAlignment(Image.MIDDLE);
			pdf.add(imageCenter);
			pdf.add(new Paragraph("Empresa: "+FacesUtils.getEmpresaIntoSession().getNombre()+"\n"+
					"Plan Estratégico: "+businessDelegatorView.getPlanEstrategicoActivoByPEA(FacesUtils.getEmpresaIntoSession()).getNombre()+"\n"+
					"Nombre del Documento: Presupuesto (por Programa y Proyecto) \n"+
					"Plan de Accion: "+planAccion.getNombre()+"\n"+
					"Programa: "+programaSeleccionado.getNombre()+"\n"+
					"Estrategia: "+estrategiasDTO.getObesNombre()+" | "+
							estrategiasDTO.getImamNombre()+" | "+
							estrategiasDTO.getEsamNombre()+"\n"+
					"Fecha: "+ new Date().toString()+"\n\n"));
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error generando el pdf. El error fue : "+e.getMessage());
		}		



	}
}
