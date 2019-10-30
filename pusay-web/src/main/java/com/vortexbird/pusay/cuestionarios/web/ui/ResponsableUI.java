/**
 * 
 */
package com.vortexbird.pusay.cuestionarios.web.ui;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.model.CueResponsable;
import com.vortexbird.pusay.cuestionarios.api.businessdelegate.IBusinessDelegatorEncuestasView;
import com.vortexbird.pusay.cuestionarios.web.utils.AbstractBaseMessageUI;
import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;

/**
 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
 * @project cuestionario-web
 * @class ResponsableUI
 * @date 22/07/2013
 * 
 */
@ManagedBean
@ViewScoped
public class ResponsableUI extends AbstractBaseMessageUI {

	private CueResponsable responsable;
	private CueResponsable selectedResponsable;
	@ManagedProperty(value = "#{BusinessDelegatorEncuestasView}")
	private IBusinessDelegatorEncuestasView businessDelegatorView;
	private List<CueResponsable> data;
	private boolean disableButtonSave;
	private boolean disableButtonModify;
	private boolean disableButtonDelete;
	private boolean disableTextIdentificacion;

	public ResponsableUI() {

	}

	@PostConstruct
	public void init() {
		this.responsable = new CueResponsable();
		this.disableButtonSave = false;
		this.disableButtonModify = true;
		this.disableButtonDelete = true;
		this.disableTextIdentificacion = false; 
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 20/07/2013
	 * @return
	 */
	public String actionSave() {
		try {
			this.businessDelegatorView.saveCueResponsable(this.responsable);

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
			this.businessDelegatorView.updateCueResponsable(this.responsable);

			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);

			actionClear();
			this.data = null;
			getData();

			this.disableButtonSave = false;
			this.disableButtonModify = true;
			this.disableButtonDelete = true;
			this.disableTextIdentificacion = false;
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
			this.businessDelegatorView.deleteCueResponsable(this.responsable.getIdentificacion());

			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);

			actionClear();
			this.data = null;
			getData();

			this.disableButtonSave = false;
			this.disableButtonModify = true;
			this.disableButtonDelete = true;
			this.disableTextIdentificacion = false;
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

		CueResponsable entity = null;

		try {
			Long identificacion = this.selectedResponsable.getIdentificacion();
			entity = this.businessDelegatorView.getCueResponsable(identificacion);
			load(entity);

			this.disableButtonSave = true;
			this.disableButtonModify = false;
			this.disableButtonDelete = false;
			this.disableTextIdentificacion = true;
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
	private void load(CueResponsable entity) {
		this.responsable = entity;
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 20/07/2013
	 */
	public String actionClear() {
		this.responsable = new CueResponsable();
		this.disableButtonSave = false;
		this.disableButtonModify = true;
		this.disableButtonDelete = true;
		this.disableTextIdentificacion = false;
		return "";
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @return the responsable
	 */
	public CueResponsable getResponsable() {
		return responsable;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @param responsable
	 *            the responsable to set
	 */
	public void setResponsable(CueResponsable responsable) {
		this.responsable = responsable;
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
	public List<CueResponsable> getData() {
		if (this.data == null) {
			try {
				this.data = this.businessDelegatorView.getCueResponsable();
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
	public void setData(List<CueResponsable> data) {
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
	 * @return the selectedResponsable
	 */
	public CueResponsable getSelectedResponsable() {
		return selectedResponsable;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @param selectedResponsable the selectedResponsable to set
	 */
	public void setSelectedResponsable(CueResponsable selectedResponsable) {
		this.selectedResponsable = selectedResponsable;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @return the disableTextIdentificacion
	 */
	public boolean isDisableTextIdentificacion() {
		return disableTextIdentificacion;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @param disableTextIdentificacion the disableTextIdentificacion to set
	 */
	public void setDisableTextIdentificacion(boolean disableTextIdentificacion) {
		this.disableTextIdentificacion = disableTextIdentificacion;
	}
}