package com.vortexbird.pusay.cuestionarios.web.ui;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.model.CueCategoria;
import com.vortexbird.pusay.cuestionarios.api.businessdelegate.IBusinessDelegatorEncuestasView;
import com.vortexbird.pusay.cuestionarios.web.utils.AbstractBaseMessageUI;
import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;

@ManagedBean
@ViewScoped
public class CategoriaUI extends AbstractBaseMessageUI {

	private CueCategoria cueCategoria;
	private CueCategoria selectedCueCategoria;
	@ManagedProperty(value = "#{BusinessDelegatorEncuestasView}")
	private IBusinessDelegatorEncuestasView businessDelegatorView;
	private List<CueCategoria> data;
	private boolean disableButtonSave;
	private boolean disableButtonModify;
	private boolean disableButtonDelete;

	/**
	 * Listas de selecci�n
	 */
	private List<SelectItem> listCuestionario;

	public CategoriaUI() {
	}

	@PostConstruct
	public void init() {
		this.cueCategoria = new CueCategoria();
		this.disableButtonSave = false;
		this.disableButtonModify = true;
		this.disableButtonDelete = true;
	}
	
	public void listener_categoria() {
		getData();
	}

	/**
	 * Acciones de la pantalla
	 */
	public String action_save() {
		try {
			businessDelegatorView.saveCueCategoria(this.cueCategoria);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			//action_clear();
			this.data = null;
			getData();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modify() {
		try {
			businessDelegatorView.updateCueCategoria(this.cueCategoria);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			//action_clear();
			this.data = null;
			getData();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}

		return "";
	}

	public String action_delete() {
		try {
			businessDelegatorView.deleteCueCategoria(this.cueCategoria
					.getConsecutivo());
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			//action_clear();
			this.data = null;
			getData();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_clear() {

		this.cueCategoria.getCueCuestionario().setConsecutivo(null);
		this.cueCategoria.setNombre(null);
		this.cueCategoria.setDescripcion(null);
		this.cueCategoria.setEstado(null);
		this.disableButtonSave = false;
		this.disableButtonModify = true;
		this.disableButtonDelete = true;
		getData();
		return "";
	}

	public String action_load_entity() {

		CueCategoria entity = null;

		try {
			Long consecutivo = this.selectedCueCategoria.getConsecutivo();
			entity = this.businessDelegatorView.getCueCategoria(consecutivo);
			load(entity);

			this.disableButtonSave = true;
			this.disableButtonModify = false;
			this.disableButtonDelete = false;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return null;
	}

	private void load(CueCategoria entity) {
		this.cueCategoria = entity;
	}

	public List<CueCategoria> getData() {
		try {
			if (cueCategoria.getCueCuestionario() != null && cueCategoria.getCueCuestionario().getConsecutivo() != null ) {

				this.data = businessDelegatorView
						.getCueCategoriaCuestionario(cueCategoria.getCueCuestionario().getConsecutivo());
			}else{
				this.data = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.data;
	}

	public CueCategoria getCueCategoria() {
		return cueCategoria;
	}

	public void setCueCategoria(CueCategoria cueCategoria) {
		this.cueCategoria = cueCategoria;
	}

	public IBusinessDelegatorEncuestasView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorEncuestasView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public void setData(List<CueCategoria> data) {
		this.data = data;
	}

	public CueCategoria getSelectedCueCategoria() {
		return selectedCueCategoria;
	}

	public void setSelectedCueCategoria(CueCategoria selectedCueCategoria) {
		this.selectedCueCategoria = selectedCueCategoria;
	}

	public boolean isDisableButtonSave() {
		return disableButtonSave;
	}

	public void setDisableButtonSave(boolean disableButtonSave) {
		this.disableButtonSave = disableButtonSave;
	}

	public boolean isDisableButtonModify() {
		return disableButtonModify;
	}

	public void setDisableButtonModify(boolean disableButtonModify) {
		this.disableButtonModify = disableButtonModify;
	}

	public boolean isDisableButtonDelete() {
		return disableButtonDelete;
	}

	public void setDisableButtonDelete(boolean disableButtonDelete) {
		this.disableButtonDelete = disableButtonDelete;
	}

	public List<SelectItem> getListCuestionario() {
		if (this.listCuestionario == null) {
			try {
				this.listCuestionario = toSelectItem(
						this.businessDelegatorView.getCueCuestionario(),
						"consecutivo", "titulo");
			} catch (Exception e) {
				error(e);
			}
		}
		return listCuestionario;
	}

	public void setListCuestionario(List<SelectItem> listCuestionario) {
		this.listCuestionario = listCuestionario;
	}

}
