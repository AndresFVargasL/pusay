package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyObjetivoImpactoDTO;
import com.vortexbird.pusay.presentation.businessDelegate.*;
import com.vortexbird.pusay.presentation.utilities.FacesUtils;
import com.vortexbird.pusay.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
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

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyObjetivoImpactoView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(PsyObjetivoImpactoView.class);
	private static final String ESTADO_REGISTRO_ACTIVO = "A";
	private static final String ESTADO_REGISTRO_INACTIVO = "I";
	private InputText txtEstadoRegistro;
	private InputText txtImamCodigo_PsyImpactoAmbiental;
	private InputText txtObesCodigo_PsyObjetivoEstrategico;
	private InputText txtObimCodigo;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<PsyObjetivoImpactoDTO> data;
	private PsyObjetivoImpactoDTO selectedPsyObjetivoImpacto;
	private PsyObjetivoImpacto entity;
	private boolean showDialog;
	private String somImpactoAmbiental;
	private String somObjetivoEstrategico;
	private List<SelectItem> losObjetivos;
	private List<SelectItem> losImpactos;
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public PsyObjetivoImpactoView() {
		super();
	}

	@PostConstruct
	public void init() {
		action_clear();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			PsyObjetivoImpactoDTO psyObjetivoImpactoDTO = (PsyObjetivoImpactoDTO) e
					.getObject();

			if (txtEstadoRegistro == null) {
				txtEstadoRegistro = new InputText();
			}

			txtEstadoRegistro.setValue(psyObjetivoImpactoDTO
					.getEstadoRegistro());

			if (txtImamCodigo_PsyImpactoAmbiental == null) {
				txtImamCodigo_PsyImpactoAmbiental = new InputText();
			}

			txtImamCodigo_PsyImpactoAmbiental.setValue(psyObjetivoImpactoDTO
					.getImamCodigo_PsyImpactoAmbiental());

			if (txtObesCodigo_PsyObjetivoEstrategico == null) {
				txtObesCodigo_PsyObjetivoEstrategico = new InputText();
			}

			txtObesCodigo_PsyObjetivoEstrategico.setValue(psyObjetivoImpactoDTO
					.getObesCodigo_PsyObjetivoEstrategico());

			if (txtObimCodigo == null) {
				txtObimCodigo = new InputText();
			}

			txtObimCodigo.setValue(psyObjetivoImpactoDTO.getObimCodigo());

			Long obimCodigo = FacesUtils.checkLong(txtObimCodigo);
			entity = businessDelegatorView.getPsyObjetivoImpacto(obimCodigo);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedPsyObjetivoImpacto = null;
		txtEstadoRegistro.setValue("Activo");
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedPsyObjetivoImpacto = null;

		setSomImpactoAmbiental("0");
		setSomObjetivoEstrategico("0");

		if (txtEstadoRegistro != null) {
			txtEstadoRegistro.setDisabled(true);
		}

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
			Long obimCodigo = FacesUtils.checkLong(txtObimCodigo);
			entity = (obimCodigo != null) ? businessDelegatorView
					.getPsyObjetivoImpacto(obimCodigo) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtEstadoRegistro.setDisabled(false);
			txtImamCodigo_PsyImpactoAmbiental.setDisabled(false);
			txtObesCodigo_PsyObjetivoEstrategico.setDisabled(false);
			txtObimCodigo.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtEstadoRegistro.setValue(entity.getEstadoRegistro());
			txtEstadoRegistro.setDisabled(false);
			txtImamCodigo_PsyImpactoAmbiental.setValue(entity
					.getPsyImpactoAmbiental().getImamCodigo());
			txtImamCodigo_PsyImpactoAmbiental.setDisabled(false);
			txtObesCodigo_PsyObjetivoEstrategico.setValue(entity
					.getPsyObjetivoEstrategico().getObesCodigo());
			txtObesCodigo_PsyObjetivoEstrategico.setDisabled(false);
			txtObimCodigo.setValue(entity.getObimCodigo());
			txtObimCodigo.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedPsyObjetivoImpacto = (PsyObjetivoImpactoDTO) (evt
				.getComponent().getAttributes()
				.get("selectedPsyObjetivoImpacto"));
		txtEstadoRegistro.setValue(selectedPsyObjetivoImpacto
				.getEstadoRegistro());
		txtEstadoRegistro.setDisabled(true);
		setSomImpactoAmbiental((selectedPsyObjetivoImpacto
				.getImamCodigo_PsyImpactoAmbiental()).toString());
		setSomObjetivoEstrategico((selectedPsyObjetivoImpacto
				.getObesCodigo_PsyObjetivoEstrategico()).toString());
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedPsyObjetivoImpacto == null) && (entity == null)) {
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
			entity = new PsyObjetivoImpacto();

			entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro) != null) ? (FacesUtils
					.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO
					: ESTADO_REGISTRO_INACTIVO
					: null);

			entity.setPsyImpactoAmbiental((somImpactoAmbiental != null && !somImpactoAmbiental
					.trim().equals("0")) ? businessDelegatorView
					.getPsyImpactoAmbiental(Long.parseLong(somImpactoAmbiental))
					: null);
			entity.setPsyObjetivoEstrategico((somObjetivoEstrategico != null && !somObjetivoEstrategico
					.trim().equals("0")) ? businessDelegatorView
					.getPsyObjetivoEstrategico(Long
							.parseLong(somObjetivoEstrategico)) : null);
			businessDelegatorView.savePsyObjetivoImpacto(entity);
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
				Long obimCodigo = new Long(
						selectedPsyObjetivoImpacto.getObimCodigo());
				entity = businessDelegatorView
						.getPsyObjetivoImpacto(obimCodigo);
			}

			entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro) != null) ? (FacesUtils
					.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO
					: ESTADO_REGISTRO_INACTIVO
					: null);

			entity.setPsyImpactoAmbiental((somImpactoAmbiental != null && !somImpactoAmbiental
					.trim().equals("0")) ? businessDelegatorView
					.getPsyImpactoAmbiental(Long.parseLong(somImpactoAmbiental))
					: null);
			entity.setPsyObjetivoEstrategico((somObjetivoEstrategico != null && !somObjetivoEstrategico
					.trim().equals("0")) ? businessDelegatorView
					.getPsyObjetivoEstrategico(Long
							.parseLong(somObjetivoEstrategico)) : null);
			businessDelegatorView.updatePsyObjetivoImpacto(entity);
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
			selectedPsyObjetivoImpacto = (PsyObjetivoImpactoDTO) (evt
					.getComponent().getAttributes()
					.get("selectedPsyObjetivoImpacto"));

			Long obimCodigo = new Long(
					selectedPsyObjetivoImpacto.getObimCodigo());
			entity = businessDelegatorView.getPsyObjetivoImpacto(obimCodigo);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Long obimCodigo = FacesUtils.checkLong(txtObimCodigo);
			entity = businessDelegatorView.getPsyObjetivoImpacto(obimCodigo);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deletePsyObjetivoImpacto(entity);
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
			selectedPsyObjetivoImpacto = (PsyObjetivoImpactoDTO) (evt
					.getComponent().getAttributes()
					.get("selectedPsyObjetivoImpacto"));

			Long obimCodigo = new Long(
					selectedPsyObjetivoImpacto.getObimCodigo());
			entity = businessDelegatorView.getPsyObjetivoImpacto(obimCodigo);
			businessDelegatorView.deletePsyObjetivoImpacto(entity);
			data.remove(selectedPsyObjetivoImpacto);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(String estadoRegistro, Long obimCodigo,
			Long imamCodigo_PsyImpactoAmbiental,
			Long obesCodigo_PsyObjetivoEstrategico) throws Exception {
		try {
			entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
			businessDelegatorView.updatePsyObjetivoImpacto(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("PsyObjetivoImpactoView").requestRender();
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

	public InputText getTxtObimCodigo() {
		return txtObimCodigo;
	}

	public void setTxtObimCodigo(InputText txtObimCodigo) {
		this.txtObimCodigo = txtObimCodigo;
	}

	public List<PsyObjetivoImpactoDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataPsyObjetivoImpacto();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<PsyObjetivoImpactoDTO> psyObjetivoImpactoDTO) {
		this.data = psyObjetivoImpactoDTO;
	}

	public PsyObjetivoImpactoDTO getSelectedPsyObjetivoImpacto() {
		return selectedPsyObjetivoImpacto;
	}

	public void setSelectedPsyObjetivoImpacto(
			PsyObjetivoImpactoDTO psyObjetivoImpacto) {
		this.selectedPsyObjetivoImpacto = psyObjetivoImpacto;
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

	public List<SelectItem> getLosObjetivos() {

		try {
			losObjetivos = new ArrayList<SelectItem>();
			List<PsyObjetivoEstrategico> losTiposObjetivos = businessDelegatorView
					.getPsyObjetivoEstrategico();
			for (PsyObjetivoEstrategico objetivo : losTiposObjetivos) {
				if (objetivo.getEstadoRegistro().trim().equals("A")) {
					SelectItem selectItem = new SelectItem(
							objetivo.getObesCodigo(), objetivo.getDescripcion());
					losObjetivos.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils
					.addErrorMessage("Error cargando los objetivos estrategicos");
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
			for (PsyImpactoAmbiental impacto : losTiposImpactos) {
				if (impacto.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							impacto.getImamCodigo(), impacto.getNombre());
					losImpactos.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils
					.addErrorMessage("Error cargando los impactos ambientales");
		}

		return losImpactos;
	}

	public void setLosImpactos(List<SelectItem> losImpactos) {
		this.losImpactos = losImpactos;
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

}
