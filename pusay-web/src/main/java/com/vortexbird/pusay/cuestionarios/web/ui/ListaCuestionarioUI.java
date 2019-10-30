package com.vortexbird.pusay.cuestionarios.web.ui;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.api.util.Propiedades;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionario;
import com.vortexbird.pusay.cuestionarios.model.CueListaCuestionario;
import com.vortexbird.pusay.cuestionarios.api.businessdelegate.IBusinessDelegatorEncuestasView;
import com.vortexbird.pusay.cuestionarios.web.utils.AbstractBaseMessageUI;
import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;

@ManagedBean
@SessionScoped
public class ListaCuestionarioUI extends AbstractBaseMessageUI {

	private CueListaCuestionario cueListaCuestionario;
	private CueListaCuestionario selectedListaCuestionario;
	private CueCuestionario selectedCuestionario;
	
	@ManagedProperty(value = "#{BusinessDelegatorEncuestasView}")
	private IBusinessDelegatorEncuestasView businessDelegatorView;
	private List<CueListaCuestionario> data;
	private boolean disableButtonSave;
	private boolean disableButtonModify;
	private boolean disableButtonDelete;

	/**
	 * Listas de selecci�n
	 */
	private List<SelectItem> listCuestionario;

	public ListaCuestionarioUI() {
	}

	@PostConstruct
	public void init() {
		this.cueListaCuestionario = new CueListaCuestionario();
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
			businessDelegatorView.saveCueListaCuestionario(this.cueListaCuestionario);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
			this.data = null;
			getData();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modify() {
		try {
			businessDelegatorView.updateCueListaCuestionario(this.cueListaCuestionario);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			action_clear();
			this.data = null;
			getData();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete() {
		try {
			businessDelegatorView.deleteCueListaCuestionario(this.cueListaCuestionario
					.getConsecutivo());
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
			this.data = null;
			getData();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_clear() {

		this.cueListaCuestionario = new CueListaCuestionario();
		this.disableButtonSave = false;
		this.disableButtonModify = true;
		this.disableButtonDelete = true;
		return "";
	}

	public String action_load_entity() {

		CueListaCuestionario entity = null;

		try {
			Long consecutivo = this.selectedListaCuestionario.getConsecutivo();
			entity = this.businessDelegatorView.getCueListaCuestionario(consecutivo);
			load(entity);

			this.disableButtonSave = true;
			this.disableButtonModify = false;
			this.disableButtonDelete = false;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return null;
	}

	public String action_cargar(){
		this.data = null;
		getData();
		return "";
	}
	
	public String action_agregar_lista(){
		
		try {
			
			if( selectedCuestionario == null ){
				throw new Exception(Propiedades.getInstance().getMensaje(
						"lbl_cuestionario_noSeleccionado"));
			}
			cueListaCuestionario.setCueCuestionario(selectedCuestionario);
			businessDelegatorView.saveCueListaCuestionario(cueListaCuestionario);
			
			action_clear();
			this.data = null;
			getData();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}
	
	public String action_eliminar_lista(){
		try {
			businessDelegatorView.deleteCueListaCuestionario(this.selectedListaCuestionario
					.getConsecutivo());
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
			this.data = null;
			getData();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}
	
	private void load(CueListaCuestionario entity) {
		this.cueListaCuestionario = entity;
	}

	public List<CueListaCuestionario> getData() {
		try {
			if (selectedCuestionario != null && (this.data == null || this.data.size() == 0)) {
				this.data = businessDelegatorView
						.getCueListaCuestionarioPorCuestionario(selectedCuestionario.getConsecutivo());
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return this.data;
	}

	public CueListaCuestionario getCueListaCuestionario() {
		return cueListaCuestionario;
	}

	public void setCueListaCuestionario(CueListaCuestionario cueListaCuestionario) {
		this.cueListaCuestionario = cueListaCuestionario;
	}

	public CueListaCuestionario getSelectedListaCuestionario() {
		return selectedListaCuestionario;
	}

	public void setSelectedListaCuestionario(
			CueListaCuestionario selectedListaCuestionario) {
		this.selectedListaCuestionario = selectedListaCuestionario;
	}

	public IBusinessDelegatorEncuestasView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorEncuestasView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
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
		return listCuestionario;
	}

	public void setListCuestionario(List<SelectItem> listCuestionario) {
		this.listCuestionario = listCuestionario;
	}

	public void setData(List<CueListaCuestionario> data) {
		this.data = data;
	}

	public CueCuestionario getSelectedCuestionario() {
		return selectedCuestionario;
	}

	public void setSelectedCuestionario(CueCuestionario selectedCuestionario) {
		this.selectedCuestionario = selectedCuestionario;
	}
	
}
