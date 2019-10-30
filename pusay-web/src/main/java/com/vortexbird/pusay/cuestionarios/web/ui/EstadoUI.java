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
import com.vortexbird.pusay.cuestionarios.model.CueEstado;
import com.vortexbird.pusay.cuestionarios.api.businessdelegate.IBusinessDelegatorEncuestasView;
import com.vortexbird.pusay.cuestionarios.web.utils.AbstractBaseMessageUI;
import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;

/**
 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
 * @project cuestionario-web
 * @class EstadoUI
 * @date 20/07/2013
 * 
 */
@ManagedBean
@ViewScoped
public class EstadoUI extends AbstractBaseMessageUI {

	private CueEstado estado;
	private CueEstado selectedEstado;
	@ManagedProperty(value = "#{BusinessDelegatorEncuestasView}")
	private IBusinessDelegatorEncuestasView businessDelegatorView;
	private List<CueEstado> data;
	private boolean disableButtonSave;
	private boolean disableButtonModify;
	private boolean disableButtonDelete;

	public EstadoUI() {

	}

	@PostConstruct
	public void init() {
		this.estado = new CueEstado();
		this.disableButtonSave = false;
		this.disableButtonModify = true;
		this.disableButtonDelete = true;
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 20/07/2013
	 * @return
	 */
	public String actionSave() {
		try {
			this.businessDelegatorView.saveCueEstado(this.estado);

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
			this.businessDelegatorView.updateCueEstado(this.estado);

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
			this.businessDelegatorView.deleteCueEstado(this.estado.getConsecutivo());

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

		CueEstado entity = null;

		try {
			Long consecutivo = this.selectedEstado.getConsecutivo();
			entity = this.businessDelegatorView.getCueEstado(consecutivo);
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
	private void load(CueEstado entity) {
		this.estado = entity;
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 20/07/2013
	 */
	public String actionClear() {
		this.estado = new CueEstado();
		this.disableButtonSave = false;
		this.disableButtonModify = true;
		this.disableButtonDelete = true;
		return "";
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 20/07/2013
	 * @return the estado
	 */
	public CueEstado getEstado() {
		return estado;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 20/07/2013
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(CueEstado estado) {
		this.estado = estado;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 20/07/2013
	 * @return the businessDelegatorView
	 */
	public IBusinessDelegatorEncuestasView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 20/07/2013
	 * @param businessDelegatorView
	 *            the businessDelegatorView to set
	 */
	public void setBusinessDelegatorView(IBusinessDelegatorEncuestasView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 20/07/2013
	 * @return the data
	 */
	public List<CueEstado> getData() {
		if (this.data == null) {
			try {
				this.data = this.businessDelegatorView.getCueEstado();
			} catch (Exception e) {
				error(e);
			}
		}
		return data;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 20/07/2013
	 * @param data
	 *            the data to set
	 */
	public void setData(List<CueEstado> data) {
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
	 * @return the selectedEstado
	 */
	public CueEstado getSelectedEstado() {
		return selectedEstado;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @param selectedEstado
	 *            the selectedEstado to set
	 */
	public void setSelectedEstado(CueEstado selectedEstado) {
		this.selectedEstado = selectedEstado;
	}
}