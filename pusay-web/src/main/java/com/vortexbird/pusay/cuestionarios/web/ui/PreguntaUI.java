package com.vortexbird.pusay.cuestionarios.web.ui;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.model.CuePregunta;
import com.vortexbird.pusay.cuestionarios.api.businessdelegate.IBusinessDelegatorEncuestasView;
import com.vortexbird.pusay.cuestionarios.web.utils.AbstractBaseMessageUI;
import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;

@ManagedBean
@ViewScoped
public class PreguntaUI extends AbstractBaseMessageUI {

	private CuePregunta cuePregunta;
	@ManagedProperty(value = "#{BusinessDelegatorEncuestasView}")
	private IBusinessDelegatorEncuestasView businessDelegatorView;
	private List<CuePregunta> data;

	private boolean disableSave = false;
	private boolean disableModify = true;
	private boolean disableDelete = true;
	private boolean disableClear = false;

	/**
	 * Listas de selecci�n
	 */
	private List<SelectItem> listCuestionario;
	private List<SelectItem> listCategoria;

	public PreguntaUI() {
	}

	@PostConstruct
	public void init() {
		this.cuePregunta = new CuePregunta();
		this.cuePregunta.setCondicion("No Aplica");
	}

	public void listener_cuestionario() {
		listCategoria = null;
		getListCategoria();
	}

	public void listener_categoria() {
		data = null;
		getData();
	}

	/**
	 * Acciones de la pantalla
	 */
	public String action_save() {
		try {
			businessDelegatorView.saveCuePregunta(this.cuePregunta);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			// action_clear();
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
			businessDelegatorView.updateCuePregunta(this.cuePregunta);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			// action_clear();
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
			CuePregunta tmp = cuePregunta;
			businessDelegatorView.deleteCuePregunta(this.cuePregunta
					.getConsecutivo());
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			this.cuePregunta = new CuePregunta();
			cuePregunta.setCueCategoria(tmp.getCueCategoria());
			this.data = null;
			// action_clear();
			getData();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_clear() {
		this.cuePregunta = new CuePregunta();
		this.cuePregunta.setCondicion("No Aplica");
		disableSave = false;
		disableModify = true;
		disableDelete = true;
		disableClear = false;
		this.data = null;
		getData();
		return "";
	}

	public List<CuePregunta> getData() {
		try {
			if (this.data == null) {
				this.data = businessDelegatorView
						.getCuePreguntaCategoria(cuePregunta.getCueCategoria()
								.getConsecutivo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.data;
	}

	public CuePregunta getCuePregunta() {
		return cuePregunta;
	}

	public void setCuePregunta(CuePregunta cuePregunta) {
		if (cuePregunta != null) {
			disableSave = true;
			disableModify = false;
			disableDelete = false;
			disableClear = false;
		} else {
			disableSave = false;
			disableModify = true;
			disableDelete = true;
			disableClear = false;
		}
		this.cuePregunta = cuePregunta;
	}

	/**
	 * Funcionalidades para el ordenamiento de las pregunta
	 */
	public void action_subir_orden() {
		getBusinessDelegatorView().action_subir_orden_pregunta(this.cuePregunta);
		data = null;
		getData();
	}

	public void action_bajar_orden() {
		getBusinessDelegatorView().action_bajar_orden_pregunta(this.cuePregunta);
		data = null;
		getData();
	}

	public IBusinessDelegatorEncuestasView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorEncuestasView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public void setData(List<CuePregunta> data) {
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

	public List<SelectItem> getListCuestionario() {
		if (this.listCuestionario == null) {
			try {
				this.listCuestionario = toSelectItem(
						this.businessDelegatorView.getCueCuestionariosActivos(),
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

	public List<SelectItem> getListCategoria() {
		if (this.listCategoria == null) {
			try {
				this.listCategoria = toSelectItem(
						this.businessDelegatorView.getCueCategoriaCuestionarioActivos(cuePregunta
								.getCueCategoria().getCueCuestionario()
								.getConsecutivo()), "consecutivo", "nombre");
			} catch (Exception e) {
				error(e);
			}
		}
		return listCategoria;
	}

	public void setListCategoria(List<SelectItem> listCategoria) {
		this.listCategoria = listCategoria;
	}

}
