package com.vortexbird.pusay.cuestionarios.web.ui;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.model.CueContacto;
import com.vortexbird.pusay.cuestionarios.api.businessdelegate.IBusinessDelegatorEncuestasView;
import com.vortexbird.pusay.cuestionarios.web.utils.AbstractBaseMessageUI;
import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;

@ManagedBean
@ViewScoped
public class ContactoUI extends AbstractBaseMessageUI {

	private CueContacto cueContacto;
	private CueContacto selectedCueContacto;
	@ManagedProperty(value = "#{BusinessDelegatorEncuestasView}")
	private IBusinessDelegatorEncuestasView businessDelegatorView;
	private List<CueContacto> data;
	private List<CueContacto> dataActivos;
	private boolean disableButtonSave;
	private boolean disableButtonModify;
	private boolean disableButtonDelete;
	
	public ContactoUI() {
	}

	@PostConstruct
	public void init() {
		this.cueContacto = new CueContacto();
		this.disableButtonSave = true;
		this.disableButtonModify = true;
		this.disableButtonDelete = true;
	}

	/**
	 * Acciones de la pantalla
	 */
	
	public void listener_identificacion() {
		CueContacto entity = null;

		try {
			Long identificacion = this.cueContacto.getIdentificacion();
			entity = businessDelegatorView.getCueContacto(identificacion);
			load(entity);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public String action_save() {
		try {
			businessDelegatorView.saveCueContacto(this.cueContacto);
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
			businessDelegatorView.updateCueContacto(this.cueContacto);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			action_clear();
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
			businessDelegatorView.deleteCueContacto(this.cueContacto.getIdentificacion());
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
		
		this.cueContacto = new CueContacto();
		this.disableButtonSave = false;
		this.disableButtonModify = true;
		this.disableButtonDelete = true;
		
		return "";
	}

	public String action_load_entity() {

		CueContacto entity = null;

		try {
			Long identificacion = this.selectedCueContacto.getIdentificacion();
			entity = this.businessDelegatorView.getCueContacto(identificacion);
			load(entity);

			this.disableButtonSave = true;
			this.disableButtonModify = false;
			this.disableButtonDelete = false;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return null;
	}
	
	private void load(CueContacto entity) {
		if( entity != null ){
			this.cueContacto = entity;
			this.disableButtonSave = true;
			this.disableButtonModify = false;
			this.disableButtonDelete = false;
		}else{
			this.disableButtonSave = false;
			this.disableButtonModify = true;
			this.disableButtonDelete = true;

		}
	}

	public List<CueContacto> getData() {
		try {
			if (this.data == null) {
				this.data = businessDelegatorView.getCueContacto();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.data;
	}

	public CueContacto getCueContacto() {
		return cueContacto;
	}

	public void setCueContacto(CueContacto cueContacto) {
		this.cueContacto = cueContacto;
	}

	public CueContacto getSelectedCueContacto() {
		return selectedCueContacto;
	}

	public void setSelectedCueContacto(CueContacto selectedCueContacto) {
		this.selectedCueContacto = selectedCueContacto;
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

	public void setData(List<CueContacto> data) {
		this.data = data;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 21/10/2013
	 * @return the dataActivos
	 */
	public List<CueContacto> getDataActivos() {
		try {
			if (this.dataActivos == null) {
				this.dataActivos = this.businessDelegatorView.getCueContactosActivos();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.dataActivos;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 21/10/2013
	 * @param dataActivos the dataActivos to set
	 */
	public void setDataActivos(List<CueContacto> dataActivos) {
		this.dataActivos = dataActivos;
	}
}
