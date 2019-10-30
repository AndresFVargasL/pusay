package com.vortexbird.pusay.cuestionarios.web.ui;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.selectoneradio.SelectOneRadio;

import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.model.CueOpcion;
import com.vortexbird.pusay.cuestionarios.model.CuePregunta;
import com.vortexbird.pusay.cuestionarios.api.businessdelegate.IBusinessDelegatorEncuestasView;
import com.vortexbird.pusay.cuestionarios.web.utils.AbstractBaseMessageUI;
import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;

@ManagedBean
@ViewScoped
public class OpcionUI extends AbstractBaseMessageUI {

	private CueOpcion cueOpcion;
	private CuePregunta cuePregunta;
	@ManagedProperty(value = "#{BusinessDelegatorEncuestasView}")
	private IBusinessDelegatorEncuestasView businessDelegatorView;
	private List<CueOpcion> data;
	
	private boolean disableSave = false;
	private boolean disableModify = true;
	private boolean disableDelete = true;
	private boolean disableClear = false;
	
	private boolean renderAmpliacion = false;
	
	/**
	 * Listas de selecci�n
	 */
	private List<SelectItem> listIndicadorCorrecta;
	
	public OpcionUI() {
	}

	@PostConstruct
	public void init() {
		this.cueOpcion = new CueOpcion();
		this.cueOpcion.setCondicion("No Aplica");
		this.cueOpcion.setIndicadorCorrecta(0L);
		this.cueOpcion.setRequiereAmpliacion(0L);
	}

	/**
	 * Acciones de la pantalla
	 */
	public String action_save() {
		try {
			this.cueOpcion.setCuePregunta(this.cuePregunta);
			businessDelegatorView.saveCueOpcion(this.cueOpcion);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
			this.cueOpcion.setCuePregunta(this.cuePregunta);
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
			this.cueOpcion.setCuePregunta(this.cuePregunta);
			businessDelegatorView.updateCueOpcion(this.cueOpcion);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			action_clear();
			this.cueOpcion.setCuePregunta(this.cuePregunta);
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
			businessDelegatorView.deleteCueOpcion(this.cueOpcion.getConsecutivo());
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
			this.cueOpcion.setCuePregunta(this.cuePregunta);
			this.data = null;
			getData();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_clear() {
		this.cueOpcion = new CueOpcion();
		this.cueOpcion.setCondicion("No Aplica");
		this.cueOpcion.setIndicadorCorrecta(0L);
		this.cueOpcion.setRequiereAmpliacion(0L);
		disableSave = false;
		disableModify = true;
		disableDelete = true;
		disableClear = false;
		return "";
	}

	public List<CueOpcion> getData() {
		try {
			if (this.data == null) {
				
				if( this.cuePregunta != null ){
					this.data = businessDelegatorView.getCueOpcionPregunta(this.cueOpcion.getCuePregunta().getConsecutivo());
				}else{
					this.data = businessDelegatorView.getCueOpcion();
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.data;
	}

	public String action_load_opciones() {
		this.cueOpcion.setCuePregunta(this.cuePregunta);
		this.data = null;
		getData();
		return "";
	}
	
	public void listener_ampliacion(AjaxBehaviorEvent ajaxBehavior) {
		SelectOneRadio selectOneRadio = (SelectOneRadio) ajaxBehavior.getComponent();
		if (selectOneRadio.getValue().equals(1L)) {
			this.renderAmpliacion = true;
		} else {
			this.cueOpcion.setLabelAmpliacion(null);
			this.renderAmpliacion = false;
		}
	}
	
	public CueOpcion getCueOpcion() {
		return cueOpcion;
	}

	public void setCueOpcion(CueOpcion cueOpcion) {
		if( cueOpcion != null ){
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
		this.cueOpcion = cueOpcion;
	}
	
	/**
	 * Funcionalidades para el ordenamiento de las pregunta
	 */
	public void action_subir_opcion() {
		getBusinessDelegatorView().action_subir_orden_opcion(this.cueOpcion);
		data = null;
		getData();
	}

	public void action_bajar_opcion() {
		getBusinessDelegatorView().action_bajar_orden_opcion(this.cueOpcion);
		data = null;
		getData();
	}

	public IBusinessDelegatorEncuestasView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorEncuestasView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public void setData(List<CueOpcion> data) {
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

	public CuePregunta getCuePregunta() {
		return cuePregunta;
	}

	public void setCuePregunta(CuePregunta cuePregunta) {
		this.cuePregunta = cuePregunta;
	}

	public List<SelectItem> getListIndicadorCorrecta() {
		return listIndicadorCorrecta;
	}

	public void setListIndicadorCorrecta(List<SelectItem> listIndicadorCorrecta) {
		this.listIndicadorCorrecta = listIndicadorCorrecta;
	}

	public boolean isRenderAmpliacion() {
		return renderAmpliacion;
	}

	public void setRenderAmpliacion(boolean renderAmpliacion) {
		this.renderAmpliacion = renderAmpliacion;
	}

}
