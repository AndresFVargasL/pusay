package com.vortexbird.pusay.cuestionarios.web.ui;

import java.util.List;
import java.util.TimeZone;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.RowEditEvent;

import com.vortexbird.pusay.cuestionarios.api.businessdelegate.IBusinessDelegatorEncuestasView;
import com.vortexbird.pusay.cuestionarios.api.dto.CueListaDTO;
import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.model.CueLista;
import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;

public class CueListaView {
	private InputText txtDescripcion;
	private InputText txtEstado;
	private InputText txtNombre;
	private InputText txtConsecutivo;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<CueListaDTO> data;
	private CueListaDTO selectedCueLista;
	private IBusinessDelegatorEncuestasView businessDelegatorView;

	public CueListaView() {
		super();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			CueListaDTO cueListaDTO = (CueListaDTO) e.getObject();

			if (txtDescripcion == null) {
				txtDescripcion = new InputText();
			}

			txtDescripcion.setValue(cueListaDTO.getDescripcion());

			if (txtEstado == null) {
				txtEstado = new InputText();
			}

			txtEstado.setValue(cueListaDTO.getEstado());

			if (txtNombre == null) {
				txtNombre = new InputText();
			}

			txtNombre.setValue(cueListaDTO.getNombre());

			if (txtConsecutivo == null) {
				txtConsecutivo = new InputText();
			}

			txtConsecutivo.setValue(cueListaDTO.getConsecutivo());

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_clear() {
		if (txtDescripcion != null) {
			txtDescripcion.setValue(null);
			txtDescripcion.setDisabled(true);
		}

		if (txtEstado != null) {
			txtEstado.setValue(null);
			txtEstado.setDisabled(true);
		}

		if (txtNombre != null) {
			txtNombre.setValue(null);
			txtNombre.setDisabled(true);
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
		CueLista entity = null;

		try {
			Long consecutivo = new Long(txtConsecutivo.getValue().toString());
			entity = businessDelegatorView.getCueLista(consecutivo);
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (entity == null) {
			txtDescripcion.setDisabled(false);
			txtEstado.setDisabled(false);
			txtNombre.setDisabled(false);
			txtConsecutivo.setDisabled(false);
			btnSave.setDisabled(false);
			btnDelete.setDisabled(true);
			btnModify.setDisabled(true);
			btnClear.setDisabled(false);
		} else {
			txtDescripcion.setValue(entity.getDescripcion());
			txtDescripcion.setDisabled(false);
			txtEstado.setValue(entity.getEstado());
			txtEstado.setDisabled(false);
			txtNombre.setValue(entity.getNombre());
			txtNombre.setDisabled(false);
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
			businessDelegatorView.saveCueLista(FacesUtils.checkLong(txtConsecutivo),
					FacesUtils.checkString(txtDescripcion), FacesUtils.checkLong(txtEstado),
					FacesUtils.checkString(txtNombre));
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete() {
		try {
			businessDelegatorView.deleteCueLista(FacesUtils.checkLong(txtConsecutivo));
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modify() {
		try {
			businessDelegatorView.updateCueLista(FacesUtils.checkLong(txtConsecutivo),
					FacesUtils.checkString(txtDescripcion), FacesUtils.checkLong(txtEstado),
					FacesUtils.checkString(txtNombre));
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

			txtConsecutivo.setValue(selectedCueLista.getConsecutivo());

			businessDelegatorView.deleteCueLista(FacesUtils.checkLong(txtConsecutivo));
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
			data.remove(selectedCueLista);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(Long consecutivo, String descripcion, Long estado, String nombre)
			throws Exception {
		try {
			businessDelegatorView.updateCueLista(consecutivo, descripcion, estado, nombre);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("CueListaView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputText getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(InputText txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public InputText getTxtEstado() {
		return txtEstado;
	}

	public void setTxtEstado(InputText txtEstado) {
		this.txtEstado = txtEstado;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputText getTxtConsecutivo() {
		return txtConsecutivo;
	}

	public void setTxtConsecutivo(InputText txtConsecutivo) {
		this.txtConsecutivo = txtConsecutivo;
	}

	public List<CueListaDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataCueLista();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<CueListaDTO> cueListaDTO) {
		this.data = cueListaDTO;
	}

	public CueListaDTO getSelectedCueLista() {
		return selectedCueLista;
	}

	public void setSelectedCueLista(CueListaDTO cueLista) {
		this.selectedCueLista = cueLista;
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
