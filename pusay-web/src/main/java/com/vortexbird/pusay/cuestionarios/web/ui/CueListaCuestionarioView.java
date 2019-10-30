package com.vortexbird.pusay.cuestionarios.web.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.vortexbird.pusay.cuestionarios.api.businessdelegate.IBusinessDelegatorEncuestasView;
import com.vortexbird.pusay.cuestionarios.api.dto.CueListaCuestionarioDTO;
import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.model.CueListaCuestionario;
import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;

public class CueListaCuestionarioView {
	private InputText txtConsecutivo_CueCuestionario;
	private InputText txtConsecutivo_CueLista;
	private InputText txtConsecutivo;
	private Calendar txtFechaHoraAsignacion;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<CueListaCuestionarioDTO> data;
	private CueListaCuestionarioDTO selectedCueListaCuestionario;
	private IBusinessDelegatorEncuestasView businessDelegatorView;

	public CueListaCuestionarioView() {
		super();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			CueListaCuestionarioDTO cueListaCuestionarioDTO = (CueListaCuestionarioDTO) e.getObject();

			if (txtConsecutivo_CueCuestionario == null) {
				txtConsecutivo_CueCuestionario = new InputText();
			}

			txtConsecutivo_CueCuestionario.setValue(cueListaCuestionarioDTO.getConsecutivo_CueCuestionario());

			if (txtConsecutivo_CueLista == null) {
				txtConsecutivo_CueLista = new InputText();
			}

			txtConsecutivo_CueLista.setValue(cueListaCuestionarioDTO.getConsecutivo_CueLista());

			if (txtConsecutivo == null) {
				txtConsecutivo = new InputText();
			}

			txtConsecutivo.setValue(cueListaCuestionarioDTO.getConsecutivo());

			if (txtFechaHoraAsignacion == null) {
				txtFechaHoraAsignacion = new Calendar();
			}

			txtFechaHoraAsignacion.setValue(cueListaCuestionarioDTO.getFechaHoraAsignacion());

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_clear() {
		if (txtConsecutivo_CueCuestionario != null) {
			txtConsecutivo_CueCuestionario.setValue(null);
			txtConsecutivo_CueCuestionario.setDisabled(true);
		}

		if (txtConsecutivo_CueLista != null) {
			txtConsecutivo_CueLista.setValue(null);
			txtConsecutivo_CueLista.setDisabled(true);
		}

		if (txtFechaHoraAsignacion != null) {
			txtFechaHoraAsignacion.setValue(null);
			txtFechaHoraAsignacion.setDisabled(true);
		}

		if (txtConsecutivo != null) {
			txtConsecutivo.setValue(null);
			txtConsecutivo.setDisabled(false);
		}

		if (btnSave != null) {
			btnSave.setDisabled(true);
		}

		if (btnDelete != null) {
			btnDelete.setDisabled(true);
		}

		if (btnModify != null) {
			btnModify.setDisabled(true);
		}

		if (btnClear != null) {
			btnClear.setDisabled(false);
		}

		return "";
	}

	@SuppressWarnings("unused")
	public void listener_txtFechaHoraAsignacion(SelectEvent dse) {
		Date inputDate = (Date) txtFechaHoraAsignacion.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Fecha Seleccionada " + dateFormat.format((Date) dse.getObject())));
	}

	public void listener_txtId() {
		CueListaCuestionario entity = null;

		try {
			Long consecutivo = new Long(txtConsecutivo.getValue().toString());
			entity = businessDelegatorView.getCueListaCuestionario(consecutivo);
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (entity == null) {
			txtConsecutivo_CueCuestionario.setDisabled(false);
			txtConsecutivo_CueLista.setDisabled(false);
			txtFechaHoraAsignacion.setDisabled(false);
			txtConsecutivo.setDisabled(false);
			btnSave.setDisabled(false);
			btnDelete.setDisabled(true);
			btnModify.setDisabled(true);
			btnClear.setDisabled(false);
		} else {
			txtFechaHoraAsignacion.setValue(entity.getFechaHoraAsignacion());
			txtFechaHoraAsignacion.setDisabled(false);
			txtConsecutivo_CueCuestionario.setValue(entity.getCueCuestionario().getConsecutivo());
			txtConsecutivo_CueCuestionario.setDisabled(false);
			txtConsecutivo_CueLista.setValue(entity.getCueLista().getConsecutivo());
			txtConsecutivo_CueLista.setDisabled(false);
			txtConsecutivo.setValue(entity.getConsecutivo());
			txtConsecutivo.setDisabled(true);
			btnSave.setDisabled(true);
			btnDelete.setDisabled(false);
			btnModify.setDisabled(false);
			btnClear.setDisabled(false);
		}
	}

	public String action_save() {
		try {
			businessDelegatorView.saveCueListaCuestionario(FacesUtils.checkLong(txtConsecutivo),
					FacesUtils.checkDate(txtFechaHoraAsignacion), FacesUtils.checkLong(txtConsecutivo_CueCuestionario),
					FacesUtils.checkLong(txtConsecutivo_CueLista));
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete() {
		try {
			businessDelegatorView.deleteCueListaCuestionario(FacesUtils.checkLong(txtConsecutivo));
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modify() {
		try {
			businessDelegatorView.updateCueListaCuestionario(FacesUtils.checkLong(txtConsecutivo),
					FacesUtils.checkDate(txtFechaHoraAsignacion), FacesUtils.checkLong(txtConsecutivo_CueCuestionario),
					FacesUtils.checkLong(txtConsecutivo_CueLista));
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			action_clear();
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String actionDeleteDataTableEditable() {
		try {
			if (txtConsecutivo == null) {
				txtConsecutivo = new InputText();
			}

			txtConsecutivo.setValue(selectedCueListaCuestionario.getConsecutivo());

			businessDelegatorView.deleteCueListaCuestionario(FacesUtils.checkLong(txtConsecutivo));
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
			data.remove(selectedCueListaCuestionario);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(Long consecutivo, Date fechaHoraAsignacion, Long consecutivo_CueCuestionario,
			Long consecutivo_CueLista) throws Exception {
		try {
			businessDelegatorView.updateCueListaCuestionario(consecutivo, fechaHoraAsignacion,
					consecutivo_CueCuestionario, consecutivo_CueLista);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("CueListaCuestionarioView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputText getTxtConsecutivo_CueCuestionario() {
		return txtConsecutivo_CueCuestionario;
	}

	public void setTxtConsecutivo_CueCuestionario(InputText txtConsecutivo_CueCuestionario) {
		this.txtConsecutivo_CueCuestionario = txtConsecutivo_CueCuestionario;
	}

	public InputText getTxtConsecutivo_CueLista() {
		return txtConsecutivo_CueLista;
	}

	public void setTxtConsecutivo_CueLista(InputText txtConsecutivo_CueLista) {
		this.txtConsecutivo_CueLista = txtConsecutivo_CueLista;
	}

	public Calendar getTxtFechaHoraAsignacion() {
		return txtFechaHoraAsignacion;
	}

	public void setTxtFechaHoraAsignacion(Calendar txtFechaHoraAsignacion) {
		this.txtFechaHoraAsignacion = txtFechaHoraAsignacion;
	}

	public InputText getTxtConsecutivo() {
		return txtConsecutivo;
	}

	public void setTxtConsecutivo(InputText txtConsecutivo) {
		this.txtConsecutivo = txtConsecutivo;
	}

	public List<CueListaCuestionarioDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataCueListaCuestionario();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<CueListaCuestionarioDTO> cueListaCuestionarioDTO) {
		this.data = cueListaCuestionarioDTO;
	}

	public CueListaCuestionarioDTO getSelectedCueListaCuestionario() {
		return selectedCueListaCuestionario;
	}

	public void setSelectedCueListaCuestionario(CueListaCuestionarioDTO cueListaCuestionario) {
		this.selectedCueListaCuestionario = cueListaCuestionario;
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

	public IBusinessDelegatorEncuestasView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorEncuestasView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}
}
