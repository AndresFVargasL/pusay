package com.vortexbird.pusay.cuestionarios.web.ui;

import java.util.List;
import java.util.TimeZone;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.RowEditEvent;

import com.vortexbird.pusay.cuestionarios.api.businessdelegate.IBusinessDelegatorEncuestasView;
import com.vortexbird.pusay.cuestionarios.api.dto.CueNavegacionDTO;
import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.model.CueNavegacion;
import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;

public class CueNavegacionView {
	private InputText txtConsecutivo_CueCuestionario;
	private InputText txtConsecutivo_CueOpcion;
	private InputText txtConsecutivo_CuePreguntaOrigen;
	private InputText txtConsecutivo_CuePreguntaDestino;
	private InputText txtConsecutivo;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<CueNavegacionDTO> data;
	private CueNavegacionDTO selectedCueNavegacion;
	private IBusinessDelegatorEncuestasView businessDelegatorView;

	public CueNavegacionView() {
		super();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			CueNavegacionDTO cueNavegacionDTO = (CueNavegacionDTO) e.getObject();

			if (txtConsecutivo_CueCuestionario == null) {
				txtConsecutivo_CueCuestionario = new InputText();
			}

			txtConsecutivo_CueCuestionario.setValue(cueNavegacionDTO.getConsecutivo_CueCuestionario());

			if (txtConsecutivo_CueOpcion == null) {
				txtConsecutivo_CueOpcion = new InputText();
			}

			txtConsecutivo_CueOpcion.setValue(cueNavegacionDTO.getConsecutivo_CueOpcion());

			if (txtConsecutivo_CuePreguntaOrigen == null) {
				txtConsecutivo_CuePreguntaOrigen = new InputText();
			}

			txtConsecutivo_CuePreguntaOrigen.setValue(cueNavegacionDTO.getConsecutivo_CuePreguntaOrigen());

			if (txtConsecutivo_CuePreguntaDestino == null) {
				txtConsecutivo_CuePreguntaDestino = new InputText();
			}

			txtConsecutivo_CuePreguntaDestino.setValue(cueNavegacionDTO.getConsecutivo_CuePreguntaDestino());

			if (txtConsecutivo == null) {
				txtConsecutivo = new InputText();
			}

			txtConsecutivo.setValue(cueNavegacionDTO.getConsecutivo());

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_clear() {
		if (txtConsecutivo_CueCuestionario != null) {
			txtConsecutivo_CueCuestionario.setValue(null);
			txtConsecutivo_CueCuestionario.setDisabled(true);
		}

		if (txtConsecutivo_CueOpcion != null) {
			txtConsecutivo_CueOpcion.setValue(null);
			txtConsecutivo_CueOpcion.setDisabled(true);
		}

		if (txtConsecutivo_CuePreguntaOrigen != null) {
			txtConsecutivo_CuePreguntaOrigen.setValue(null);
			txtConsecutivo_CuePreguntaOrigen.setDisabled(true);
		}

		if (txtConsecutivo_CuePreguntaDestino != null) {
			txtConsecutivo_CuePreguntaDestino.setValue(null);
			txtConsecutivo_CuePreguntaDestino.setDisabled(true);
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

	public void listener_txtId() {
		CueNavegacion entity = null;

		try {
			Long consecutivo = new Long(txtConsecutivo.getValue().toString());
			entity = businessDelegatorView.getCueNavegacion(consecutivo);
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (entity == null) {
			txtConsecutivo_CueCuestionario.setDisabled(false);
			txtConsecutivo_CueOpcion.setDisabled(false);
			txtConsecutivo_CuePreguntaOrigen.setDisabled(false);
			txtConsecutivo_CuePreguntaDestino.setDisabled(false);
			txtConsecutivo.setDisabled(false);
			btnSave.setDisabled(false);
			btnDelete.setDisabled(true);
			btnModify.setDisabled(true);
			btnClear.setDisabled(false);
		} else {
			txtConsecutivo_CueCuestionario.setValue(entity.getCueCuestionario().getConsecutivo());
			txtConsecutivo_CueCuestionario.setDisabled(false);
			txtConsecutivo_CueOpcion.setValue(entity.getCueOpcion().getConsecutivo());
			txtConsecutivo_CueOpcion.setDisabled(false);
			txtConsecutivo_CuePreguntaOrigen.setValue(entity.getCuePreguntaByPreguntaOrigen().getConsecutivo());
			txtConsecutivo_CuePreguntaOrigen.setDisabled(false);
			txtConsecutivo_CuePreguntaDestino.setValue(entity.getCuePreguntaByPreguntaDestino().getConsecutivo());
			txtConsecutivo_CuePreguntaDestino.setDisabled(false);
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
			businessDelegatorView.saveCueNavegacion(FacesUtils.checkLong(txtConsecutivo),
					FacesUtils.checkLong(txtConsecutivo_CueCuestionario),
					FacesUtils.checkLong(txtConsecutivo_CueOpcion),
					FacesUtils.checkLong(txtConsecutivo_CuePreguntaOrigen),
					FacesUtils.checkLong(txtConsecutivo_CuePreguntaDestino));
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete() {
		try {
			businessDelegatorView.deleteCueNavegacion(FacesUtils.checkLong(txtConsecutivo));
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modify() {
		try {
			businessDelegatorView.updateCueNavegacion(FacesUtils.checkLong(txtConsecutivo),
					FacesUtils.checkLong(txtConsecutivo_CueCuestionario),
					FacesUtils.checkLong(txtConsecutivo_CueOpcion),
					FacesUtils.checkLong(txtConsecutivo_CuePreguntaOrigen),
					FacesUtils.checkLong(txtConsecutivo_CuePreguntaDestino));
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

			txtConsecutivo.setValue(selectedCueNavegacion.getConsecutivo());

			businessDelegatorView.deleteCueNavegacion(FacesUtils.checkLong(txtConsecutivo));
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
			data.remove(selectedCueNavegacion);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(Long consecutivo, Long consecutivo_CueCuestionario, Long consecutivo_CueOpcion,
			Long consecutivo_CuePreguntaOrigen, Long consecutivo_CuePreguntaDestino) throws Exception {
		try {
			businessDelegatorView.updateCueNavegacion(consecutivo, consecutivo_CueCuestionario, consecutivo_CueOpcion,
					consecutivo_CuePreguntaOrigen, consecutivo_CuePreguntaDestino);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("CueNavegacionView").requestRender();
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

	public InputText getTxtConsecutivo_CueOpcion() {
		return txtConsecutivo_CueOpcion;
	}

	public void setTxtConsecutivo_CueOpcion(InputText txtConsecutivo_CueOpcion) {
		this.txtConsecutivo_CueOpcion = txtConsecutivo_CueOpcion;
	}

	public InputText getTxtConsecutivo_CuePreguntaOrigen() {
		return txtConsecutivo_CuePreguntaOrigen;
	}

	public void setTxtConsecutivo_CuePreguntaOrigen(InputText txtConsecutivo_CuePreguntaOrigen) {
		this.txtConsecutivo_CuePreguntaOrigen = txtConsecutivo_CuePreguntaOrigen;
	}

	public InputText getTxtConsecutivo_CuePreguntaDestino() {
		return txtConsecutivo_CuePreguntaDestino;
	}

	public void setTxtConsecutivo_CuePreguntaDestino(InputText txtConsecutivo_CuePreguntaDestino) {
		this.txtConsecutivo_CuePreguntaDestino = txtConsecutivo_CuePreguntaDestino;
	}

	public InputText getTxtConsecutivo() {
		return txtConsecutivo;
	}

	public void setTxtConsecutivo(InputText txtConsecutivo) {
		this.txtConsecutivo = txtConsecutivo;
	}

	public List<CueNavegacionDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataCueNavegacion();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<CueNavegacionDTO> cueNavegacionDTO) {
		this.data = cueNavegacionDTO;
	}

	public CueNavegacionDTO getSelectedCueNavegacion() {
		return selectedCueNavegacion;
	}

	public void setSelectedCueNavegacion(CueNavegacionDTO cueNavegacion) {
		this.selectedCueNavegacion = cueNavegacion;
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
