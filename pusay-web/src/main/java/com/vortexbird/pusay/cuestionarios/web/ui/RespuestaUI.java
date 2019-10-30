package com.vortexbird.pusay.cuestionarios.web.ui;

import java.util.List;

import javax.annotation.PostConstruct;

import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.model.CuePregunta;
import com.vortexbird.pusay.cuestionarios.model.CueRespuesta;
import com.vortexbird.pusay.cuestionarios.api.businessdelegate.IBusinessDelegatorEncuestasView;
import com.vortexbird.pusay.cuestionarios.web.utils.AbstractBaseMessageUI;
import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;

public class RespuestaUI extends AbstractBaseMessageUI {

	private CueRespuesta cueRespuesta;
	private IBusinessDelegatorEncuestasView businessDelegatorView;
	private List<CueRespuesta> data;
	private List<CuePregunta> dataPreguntas;
	
	private boolean disableSave = false;
	private boolean disableModify = true;
	private boolean disableDelete = true;
	private boolean disableClear = false;
	
	public RespuestaUI() {
	}

	@PostConstruct
	public void init() {
		this.cueRespuesta = new CueRespuesta();
	}

	/**
	 * Acciones de la pantalla
	 */
	public String action_save() {
		try {
			businessDelegatorView.saveCueRespuesta(this.cueRespuesta);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
			this.data = null;
			getData();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}

		return "";
	}

	public String action_modify() {
		try {
			businessDelegatorView.updateCueRespuesta(this.cueRespuesta);
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
			businessDelegatorView.deleteCuePregunta(this.cueRespuesta.getConsecutivo());
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
		this.cueRespuesta = new CueRespuesta();
		disableSave = false;
		disableModify = true;
		disableDelete = true;
		disableClear = false;
		return "";
	}

	public List<CueRespuesta> getData() {
		try {
			if (this.data == null) {
				this.data = businessDelegatorView.getCueRespuestaPorUsuario();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.data;
	}

	public CueRespuesta getCueRespuesta() {
		return cueRespuesta;
	}

	public void setCueRespuesta(CueRespuesta cueRespuesta) {
		if( cueRespuesta != null ){
			disableSave = true;
			disableModify = false;
			disableDelete = false;
			disableClear = false;
		}else{
			disableSave = false;
			disableModify = true;
			disableDelete = true;
			disableClear = false;
		}
		this.cueRespuesta = cueRespuesta;
	}

	public IBusinessDelegatorEncuestasView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorEncuestasView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public void setData(List<CueRespuesta> data) {
		this.data = data;
	}

	public boolean isDisableSave() {
		return disableSave;
	}

	public void setDisableSave(boolean disableSave) {
		this.disableSave = disableSave;
	}

	public boolean isDisableModify() {
		return disableModify;
	}

	public void setDisableModify(boolean disableModify) {
		this.disableModify = disableModify;
	}

	public boolean isDisableDelete() {
		return disableDelete;
	}

	public void setDisableDelete(boolean disableDelete) {
		this.disableDelete = disableDelete;
	}

	public boolean isDisableClear() {
		return disableClear;
	}

	public void setDisableClear(boolean disableClear) {
		this.disableClear = disableClear;
	}
}
