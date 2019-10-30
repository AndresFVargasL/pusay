/**
 * 
 */
package com.vortexbird.pusay.cuestionarios.web.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.primefaces.event.SelectEvent;

import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.model.CueConfiguracion;
import com.vortexbird.pusay.cuestionarios.api.businessdelegate.IBusinessDelegatorEncuestasView;
import com.vortexbird.pusay.cuestionarios.web.utils.AbstractBaseMessageUI;
import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;

/**
 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
 * @project cuestionario-web
 * @class ConfiguracionUI
 * @date 22/07/2013
 * 
 */
@ManagedBean
@ViewScoped
public class ConfiguracionUI extends AbstractBaseMessageUI {

	private CueConfiguracion configuracion;
	private CueConfiguracion selectedConfiguracion;
	@ManagedProperty(value = "#{BusinessDelegatorEncuestasView}")
	private IBusinessDelegatorEncuestasView businessDelegatorView;
	private List<CueConfiguracion> data;
	private boolean disableButtonSave;
	private boolean disableButtonModify;
	private boolean disableButtonDelete;
	private boolean renderInputTextMsjMultRespuesta;
	private boolean renderOpcionRedirigeURL;
	private boolean renderClaveAcceso;
	private String color;
	private String componentClaveAcceso;
	private Long componentOpcionFinalizacion;

	public ConfiguracionUI() {

	}

	@PostConstruct
	public void init() {
		actionClear();
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 20/07/2013
	 * @return
	 */
	public String actionSave() {
		try {
			this.businessDelegatorView.saveCueConfiguracion(this.configuracion);

			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);

			actionClear();
			this.data = null;
			getData();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 20/07/2013
	 * @return
	 */
	public String actionModify() {
		try {
			this.businessDelegatorView.updateCueConfiguracion(this.configuracion);

			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);

			actionClear();
			this.data = null;
			getData();

			this.disableButtonSave = false;
			this.disableButtonModify = true;
			this.disableButtonDelete = true;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 20/07/2013
	 * @return
	 */
	public String actionDelete() {
		try {
			this.businessDelegatorView.deleteCueConfiguracion(this.configuracion.getConsecutivo());

			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);

			actionClear();
			this.data = null;
			getData();

			this.disableButtonSave = false;
			this.disableButtonModify = true;
			this.disableButtonDelete = true;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @return
	 */
	public String actionLoadEntity() {

		CueConfiguracion entity = null;

		try {
			Long consecutivo = this.selectedConfiguracion.getConsecutivo();
			entity = this.businessDelegatorView.getCueConfiguracion(consecutivo);
			load(entity);

			this.disableButtonSave = true;
			this.disableButtonModify = false;
			this.disableButtonDelete = false;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @param entity
	 */
	private void load(CueConfiguracion entity) {
		this.configuracion = entity;
		if (this.configuracion != null) {
			if (this.configuracion.getMultipleRespuesta() != null && this.configuracion.getMultipleRespuesta().equals(1L)) {
				this.renderInputTextMsjMultRespuesta = true;
			}
			if (this.configuracion.getRedirigirCerrar() != null) {
				this.renderOpcionRedirigeURL = false;
				this.componentOpcionFinalizacion = 0L;
			}
			if (this.configuracion.getRedirigirInforme() != null) {
				this.renderOpcionRedirigeURL = false;
				this.componentOpcionFinalizacion = 1L;
			}
			if (this.configuracion.getRedirigirUrl() != null && !this.configuracion.getRedirigirUrl().equals("")) {
				this.renderOpcionRedirigeURL = true;
				this.componentOpcionFinalizacion = 2L;
			}
			if (this.configuracion.getClaveAcceso() != null && !this.configuracion.getClaveAcceso().equals("")) {
				this.componentClaveAcceso = "1";
				this.renderClaveAcceso = true;
			} else {
				this.componentClaveAcceso = "0";
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 20/07/2013
	 */
	public String actionClear() {
		this.configuracion = new CueConfiguracion();
		this.configuracion.setPuntajeMax(0L);
		this.configuracion.setAbierto(0L);
		this.configuracion.setHeader("No Aplica");
		this.configuracion.setMensajeCierre("No Aplica");
		this.configuracion.setMensajeClaveIncorrecta("No Aplica");
		this.configuracion.setMensajeCuestionarioFinalizad("No Aplica");
		this.configuracion.setMensajeFechaLimite("No Aplica");
		this.configuracion.setMensajeMaximoRespuestas("No Aplica");
		this.configuracion.setMensajeRedireccional("No Aplica");
		this.configuracion.setMultipleRespuesta(0L);
		this.configuracion.setRedirigirCerrar(1L);
		this.disableButtonSave = false;
		this.disableButtonModify = true;
		this.disableButtonDelete = true;
		this.color = "fcfcfc";
		this.componentClaveAcceso = "0";
		this.componentOpcionFinalizacion = 0L;
		this.renderClaveAcceso = false;
		this.renderInputTextMsjMultRespuesta = false;
		this.renderOpcionRedirigeURL = false;
		return "";
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 */
	public void listenerMultipleRespuesta(AjaxBehaviorEvent ajaxBehavior) {
		SelectOneRadio selectOneRadio = (SelectOneRadio) ajaxBehavior.getComponent();
		if (selectOneRadio.getValue().equals(1L)) {
			this.renderInputTextMsjMultRespuesta = true;
		} else {
			this.configuracion.setMultipleRespuestaMsj(null);
			this.renderInputTextMsjMultRespuesta = false;
		}
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 */
	public void listenerOpcionFinalizacion(AjaxBehaviorEvent ajaxBehavior) {
		SelectOneRadio selectOneRadio = (SelectOneRadio) ajaxBehavior.getComponent();
		Long opcion = (Long) selectOneRadio.getValue();
		switch (opcion.intValue()) {
		case 0:
			this.configuracion.setRedirigirCerrar(1L);
			this.configuracion.setRedirigirInforme(null);
			this.configuracion.setRedirigirUrl(null);
			this.renderOpcionRedirigeURL = false;
			break;
		case 1:
			this.configuracion.setRedirigirInforme(1L);
			this.configuracion.setRedirigirCerrar(null);
			this.configuracion.setRedirigirUrl(null);
			this.renderOpcionRedirigeURL = false;
			break;
		case 2:
			this.renderOpcionRedirigeURL = true;
			this.configuracion.setRedirigirInforme(null);
			this.configuracion.setRedirigirCerrar(null);
			break;
		}
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 */
	public void listenerClaveAcceso(AjaxBehaviorEvent ajaxBehavior) {
		SelectOneRadio selectOneRadio = (SelectOneRadio) ajaxBehavior.getComponent();
		if (selectOneRadio.getValue().equals("1")) {
			this.renderClaveAcceso = true;
		} else {
			this.configuracion.setClaveAcceso(null);
			this.renderClaveAcceso = false;
		}
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param selectEvent
	 */
	public void listenerVigenciaFecha(SelectEvent selectEvent) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Fecha Seleccionada " + dateFormat.format((Date) selectEvent.getObject())));
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @return the configuracion
	 */
	public CueConfiguracion getConfiguracion() {
		return configuracion;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @param configuracion
	 *            the configuracion to set
	 */
	public void setConfiguracion(CueConfiguracion configuracion) {
		this.configuracion = configuracion;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @return the selectedconfiguracion
	 */
	public CueConfiguracion getSelectedConfiguracion() {
		return selectedConfiguracion;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @param selectedconfiguracion
	 *            the selectedconfiguracion to set
	 */
	public void setSelectedConfiguracion(CueConfiguracion selectedConfiguracion) {
		this.selectedConfiguracion = selectedConfiguracion;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @return the businessDelegatorView
	 */
	public IBusinessDelegatorEncuestasView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @param businessDelegatorView
	 *            the businessDelegatorView to set
	 */
	public void setBusinessDelegatorView(IBusinessDelegatorEncuestasView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @return the data
	 */
	public List<CueConfiguracion> getData() {
		if (this.data == null) {
			try {
				this.data = this.businessDelegatorView.getCueConfiguracion();
			} catch (Exception e) {
				error(e);
			}
		}
		return data;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @param data
	 *            the data to set
	 */
	public void setData(List<CueConfiguracion> data) {
		this.data = data;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @return the disableButtonSave
	 */
	public boolean isDisableButtonSave() {
		return disableButtonSave;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @param disableButtonSave
	 *            the disableButtonSave to set
	 */
	public void setDisableButtonSave(boolean disableButtonSave) {
		this.disableButtonSave = disableButtonSave;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @return the disableButtonModify
	 */
	public boolean isDisableButtonModify() {
		return disableButtonModify;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @param disableButtonModify
	 *            the disableButtonModify to set
	 */
	public void setDisableButtonModify(boolean disableButtonModify) {
		this.disableButtonModify = disableButtonModify;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @return the disableButtonDelete
	 */
	public boolean isDisableButtonDelete() {
		return disableButtonDelete;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @param disableButtonDelete
	 *            the disableButtonDelete to set
	 */
	public void setDisableButtonDelete(boolean disableButtonDelete) {
		this.disableButtonDelete = disableButtonDelete;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @return the renderInputTextMsjMultRespuesta
	 */
	public boolean isRenderInputTextMsjMultRespuesta() {
		return renderInputTextMsjMultRespuesta;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @param renderInputTextMsjMultRespuesta
	 *            the renderInputTextMsjMultRespuesta to set
	 */
	public void setRenderInputTextMsjMultRespuesta(boolean renderInputTextMsjMultRespuesta) {
		this.renderInputTextMsjMultRespuesta = renderInputTextMsjMultRespuesta;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @return the renderOpcionRedirigeURL
	 */
	public boolean isRenderOpcionRedirigeURL() {
		return renderOpcionRedirigeURL;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @param renderOpcionRedirigeURL
	 *            the renderOpcionRedirigeURL to set
	 */
	public void setRenderOpcionRedirigeURL(boolean renderOpcionRedirigeURL) {
		this.renderOpcionRedirigeURL = renderOpcionRedirigeURL;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the renderClaveAcceso
	 */
	public boolean isRenderClaveAcceso() {
		return renderClaveAcceso;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param renderClaveAcceso
	 *            the renderClaveAcceso to set
	 */
	public void setRenderClaveAcceso(boolean renderClaveAcceso) {
		this.renderClaveAcceso = renderClaveAcceso;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the componentClaveAcceso
	 */
	public String getComponentClaveAcceso() {
		return componentClaveAcceso;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param componentClaveAcceso
	 *            the componentClaveAcceso to set
	 */
	public void setComponentClaveAcceso(String componentClaveAcceso) {
		this.componentClaveAcceso = componentClaveAcceso;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the componentOpcionFinalizacion
	 */
	public Long getComponentOpcionFinalizacion() {
		return componentOpcionFinalizacion;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param componentOpcionFinalizacion the componentOpcionFinalizacion to set
	 */
	public void setComponentOpcionFinalizacion(Long componentOpcionFinalizacion) {
		this.componentOpcionFinalizacion = componentOpcionFinalizacion;
	}
}