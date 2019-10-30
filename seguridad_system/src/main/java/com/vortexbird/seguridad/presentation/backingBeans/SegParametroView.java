package com.vortexbird.seguridad.presentation.backingBeans;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;


import org.primefaces.event.RowEditEvent;

import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.SegParametroDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SegParametroView {
	private InputText txtParEstadoRegistro;
	private InputText txtParNombre;
	private InputText txtParValorAlfanumerico;
	private InputText txtParValorNumerico;
	private InputText txtUsuCodigo_SegUsuario;
	private InputText txtParCodigo;
	private Calendar txtParValorFecha;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<SegParametroDTO> data;
	private SegParametroDTO selectedSegParametro;

	public SegParametroView() {
		super();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			SegParametroDTO segParametroDTO = (SegParametroDTO) e.getObject();

			if (txtParEstadoRegistro == null) {
				txtParEstadoRegistro = new InputText();
			}

			txtParEstadoRegistro.setValue(segParametroDTO.getParEstadoRegistro());

			if (txtParNombre == null) {
				txtParNombre = new InputText();
			}

			txtParNombre.setValue(segParametroDTO.getParNombre());

			if (txtParValorAlfanumerico == null) {
				txtParValorAlfanumerico = new InputText();
			}

			txtParValorAlfanumerico.setValue(segParametroDTO.getParValorAlfanumerico());

			if (txtParValorNumerico == null) {
				txtParValorNumerico = new InputText();
			}

			txtParValorNumerico.setValue(segParametroDTO.getParValorNumerico());

			if (txtUsuCodigo_SegUsuario == null) {
				txtUsuCodigo_SegUsuario = new InputText();
			}

			txtUsuCodigo_SegUsuario.setValue(segParametroDTO.getUsuCodigo_SegUsuario());

			if (txtParCodigo == null) {
				txtParCodigo = new InputText();
			}

			txtParCodigo.setValue(segParametroDTO.getParCodigo());

			if (txtParValorFecha == null) {
				txtParValorFecha = new Calendar();
			}

			txtParValorFecha.setValue(segParametroDTO.getParValorFecha());

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_clear() {
		if (txtParEstadoRegistro != null) {
			txtParEstadoRegistro.setValue(null);
			txtParEstadoRegistro.setDisabled(true);
		}

		if (txtParNombre != null) {
			txtParNombre.setValue(null);
			txtParNombre.setDisabled(true);
		}

		if (txtParValorAlfanumerico != null) {
			txtParValorAlfanumerico.setValue(null);
			txtParValorAlfanumerico.setDisabled(true);
		}

		if (txtParValorNumerico != null) {
			txtParValorNumerico.setValue(null);
			txtParValorNumerico.setDisabled(true);
		}

		if (txtUsuCodigo_SegUsuario != null) {
			txtUsuCodigo_SegUsuario.setValue(null);
			txtUsuCodigo_SegUsuario.setDisabled(true);
		}

		if (txtParValorFecha != null) {
			txtParValorFecha.setValue(null);
			txtParValorFecha.setDisabled(true);
		}

		if (txtParCodigo != null) {
			txtParCodigo.setValue(null);
			txtParCodigo.setDisabled(false);
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

	public void listener_txtParValorFecha() {
		Date inputDate = (Date) txtParValorFecha.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
	}

	public void listener_txtId() {
		SegParametro entity = null;

		try {
			Long parCodigo = new Long(txtParCodigo.getValue().toString());
			entity = BusinessDelegatorView.getSegParametro(parCodigo);
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (entity == null) {
			txtParEstadoRegistro.setDisabled(false);
			txtParNombre.setDisabled(false);
			txtParValorAlfanumerico.setDisabled(false);
			txtParValorNumerico.setDisabled(false);
			txtUsuCodigo_SegUsuario.setDisabled(false);
			txtParValorFecha.setDisabled(false);
			txtParCodigo.setDisabled(false);
			btnSave.setDisabled(false);
			btnDelete.setDisabled(true);
			btnModify.setDisabled(true);
			btnClear.setDisabled(false);
		} else {
			txtParEstadoRegistro.setValue(entity.getParEstadoRegistro());
			txtParEstadoRegistro.setDisabled(false);
			txtParNombre.setValue(entity.getParNombre());
			txtParNombre.setDisabled(false);
			txtParValorAlfanumerico.setValue(entity.getParValorAlfanumerico());
			txtParValorAlfanumerico.setDisabled(false);
			txtParValorFecha.setValue(entity.getParValorFecha());
			txtParValorFecha.setDisabled(false);
			txtParValorNumerico.setValue(entity.getParValorNumerico());
			txtParValorNumerico.setDisabled(false);
			txtUsuCodigo_SegUsuario.setValue(entity.getSegUsuario()
					.getUsuCodigo());
			txtUsuCodigo_SegUsuario.setDisabled(false);
			txtParCodigo.setValue(entity.getParCodigo());
			txtParCodigo.setDisabled(true);
			btnSave.setDisabled(true);
			btnDelete.setDisabled(false);
			btnModify.setDisabled(false);
			btnClear.setDisabled(false);
		}
	}

	public String action_save() {
		try {
			BusinessDelegatorView.saveSegParametro(
					FacesUtils.checkString(txtParEstadoRegistro),
					FacesUtils.checkString(txtParNombre),
					FacesUtils.checkString(txtParValorAlfanumerico),
					FacesUtils.checkDate(txtParValorFecha),
					FacesUtils.checkDouble(txtParValorNumerico),
					FacesUtils.checkLong(txtUsuCodigo_SegUsuario));
					FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
					action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete() {
		try {
			BusinessDelegatorView.deleteSegParametro(FacesUtils.checkLong(
					txtParCodigo));
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modify() {
		try {
			BusinessDelegatorView.updateSegParametro(FacesUtils.checkLong(
					txtParCodigo),
					FacesUtils.checkString(txtParEstadoRegistro),
					FacesUtils.checkString(txtParNombre),
					FacesUtils.checkString(txtParValorAlfanumerico),
					FacesUtils.checkDate(txtParValorFecha),
					FacesUtils.checkDouble(txtParValorNumerico),
					FacesUtils.checkLong(txtUsuCodigo_SegUsuario));
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
			if (txtParCodigo == null) {
				txtParCodigo = new InputText();
			}

			txtParCodigo.setValue(selectedSegParametro.getParCodigo());

			action_delete();
			data.remove(selectedSegParametro);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(Long parCodigo, String parEstadoRegistro,
			String parNombre, String parValorAlfanumerico, Date parValorFecha,
			Double parValorNumerico, Long usuCodigo_SegUsuario)
					throws Exception {
		try {
			BusinessDelegatorView.updateSegParametro(parCodigo,
					parEstadoRegistro, parNombre, parValorAlfanumerico,
					parValorFecha, parValorNumerico, usuCodigo_SegUsuario);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			//renderManager.getOnDemandRenderer("SegParametroView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputText getTxtParEstadoRegistro() {
		return txtParEstadoRegistro;
	}

	public void setTxtParEstadoRegistro(InputText txtParEstadoRegistro) {
		this.txtParEstadoRegistro = txtParEstadoRegistro;
	}

	public InputText getTxtParNombre() {
		return txtParNombre;
	}

	public void setTxtParNombre(InputText txtParNombre) {
		this.txtParNombre = txtParNombre;
	}

	public InputText getTxtParValorAlfanumerico() {
		return txtParValorAlfanumerico;
	}

	public void setTxtParValorAlfanumerico(InputText txtParValorAlfanumerico) {
		this.txtParValorAlfanumerico = txtParValorAlfanumerico;
	}

	public InputText getTxtParValorNumerico() {
		return txtParValorNumerico;
	}

	public void setTxtParValorNumerico(InputText txtParValorNumerico) {
		this.txtParValorNumerico = txtParValorNumerico;
	}

	public InputText getTxtUsuCodigo_SegUsuario() {
		return txtUsuCodigo_SegUsuario;
	}

	public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
		this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
	}

	public Calendar getTxtParValorFecha() {
		return txtParValorFecha;
	}

	public void setTxtParValorFecha(Calendar txtParValorFecha) {
		this.txtParValorFecha = txtParValorFecha;
	}

	public InputText getTxtParCodigo() {
		return txtParCodigo;
	}

	public void setTxtParCodigo(InputText txtParCodigo) {
		this.txtParCodigo = txtParCodigo;
	}

	public List<SegParametroDTO> getData() {
		return data;
	}

	public void setData(List<SegParametroDTO> segParametroDTO) {
		this.data = segParametroDTO;
	}

	public SegParametroDTO getSelectedSegParametro() {
		return selectedSegParametro;
	}

	public void setSelectedSegParametro(SegParametroDTO segParametro) {
		this.selectedSegParametro = segParametro;
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
}
