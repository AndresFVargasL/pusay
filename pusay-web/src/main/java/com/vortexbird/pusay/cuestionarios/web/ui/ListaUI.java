package com.vortexbird.pusay.cuestionarios.web.ui;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.model.CueContacto;
import com.vortexbird.pusay.cuestionarios.model.CueLista;
import com.vortexbird.pusay.cuestionarios.model.CueListaContacto;
import com.vortexbird.pusay.cuestionarios.model.CueListaCuestionario;
import com.vortexbird.pusay.cuestionarios.api.businessdelegate.IBusinessDelegatorEncuestasView;
import com.vortexbird.pusay.cuestionarios.web.utils.AbstractBaseMessageUI;
import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;

@ManagedBean
@ViewScoped
public class ListaUI extends AbstractBaseMessageUI {

	private CueLista cueLista;
	private CueLista selectedCueLista;
	private CueContacto selectedCueContacto;
	private CueListaContacto selectedListaContacto;
	@ManagedProperty(value = "#{BusinessDelegatorEncuestasView}")
	private IBusinessDelegatorEncuestasView businessDelegatorView;
	private List<CueLista> data;
	private List<CueLista> dataActivos;
	private CueListaContacto selectedCueListaContacto;
	private List<CueListaContacto> dataListaContacto;
	private List<CueListaCuestionario> dataListaCuestionario;
	
	private boolean disableButtonSave;
	private boolean disableButtonModify;
	private boolean disableButtonDelete;
	
	public ListaUI() {
	}

	@PostConstruct
	public void init() {
		this.cueLista = new CueLista();
		this.disableButtonSave = false;
		this.disableButtonModify = true;
		this.disableButtonDelete = true;
	}

	/**
	 * Acciones de la pantalla
	 */
	public String action_save() {
		try {
			businessDelegatorView.saveCueLista(this.cueLista);
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
			businessDelegatorView.updateCueLista(this.cueLista);
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
			businessDelegatorView.deleteCueLista(this.cueLista.getConsecutivo());
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
		this.cueLista = new CueLista();
		this.disableButtonSave = false;
		this.disableButtonModify = true;
		this.disableButtonDelete = true;
		return "";
	}

	public List<CueLista> getData() {
		try {
			if (this.data == null) {
				this.data = businessDelegatorView.getCueLista();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.data;
	}

	public String action_load_entity() {

		CueLista entity = null;

		try {
			Long consecutivo = this.selectedCueLista.getConsecutivo();
			entity = this.businessDelegatorView.getCueLista(consecutivo);
			load(entity);
			
			dataListaContacto = null;
 			this.disableButtonSave = true;
			this.disableButtonModify = false;
			this.disableButtonDelete = false;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return null;
	}
	
	public String action_add_encuestado(){
		try {
			selectedCueListaContacto = new CueListaContacto();
			selectedCueListaContacto.setCueContacto(this.selectedCueContacto);
			selectedCueListaContacto.setCueLista(this.cueLista);
			selectedCueListaContacto.setEstado(1l);
			this.businessDelegatorView.saveCueListaContacto(selectedCueListaContacto);
			
			dataListaContacto = null;
			getDataListaContacto();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return null;
	}
	
	public String action_del_encuestado(){
		try {
			this.businessDelegatorView.deleteCueListaContacto(selectedListaContacto.getConsecutivo());
			dataListaContacto = null;
			getDataListaContacto();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return null;
	}
	
	private void load(CueLista entity) {
		this.cueLista = entity;
	}

	public CueLista getCueLista() {
		return cueLista;
	}

	public void setCueOpcion(CueLista cueLista) {
		this.cueLista = cueLista;
	}

	public IBusinessDelegatorEncuestasView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorEncuestasView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public void setData(List<CueLista> data) {
		this.data = data;
	}

	public void setCueLista(CueLista cueLista) {
		this.cueLista = cueLista;
	}

	public List<CueListaCuestionario> getDataListaCuestionario() {
		return dataListaCuestionario;
	}

	public void setDataListaCuestionario(
			List<CueListaCuestionario> dataListaCuestionario) {
		this.dataListaCuestionario = dataListaCuestionario;
	}

	public CueLista getSelectedCueLista() {
		return selectedCueLista;
	}

	public void setSelectedCueLista(CueLista selectedCueLista) {
		this.selectedCueLista = selectedCueLista;
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

	public List<CueListaContacto> getDataListaContacto() {
		if( dataListaContacto == null ){
			try {       
				dataListaContacto = this.businessDelegatorView.getCueListaContactoLista(this.cueLista.getConsecutivo());
    			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dataListaContacto;
	}

	public void setDataListaContacto(List<CueListaContacto> dataListaContacto) {
		this.dataListaContacto = dataListaContacto;
	}

	public CueListaContacto getSelectedCueListaContacto() {
		return selectedCueListaContacto;
	}

	public void setSelectedCueListaContacto(
			CueListaContacto selectedCueListaContacto) {
		this.selectedCueListaContacto = selectedCueListaContacto;
	}

	public CueContacto getSelectedCueContacto() {
		return selectedCueContacto;
	}

	public void setSelectedCueContacto(CueContacto selectedCueContacto) {
		this.selectedCueContacto = selectedCueContacto;
	}

	public CueListaContacto getSelectedListaContacto() {
		return selectedListaContacto;
	}

	public void setSelectedListaContacto(CueListaContacto selectedListaContacto) {
		this.selectedListaContacto = selectedListaContacto;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 21/10/2013
	 * @return the dataActivos
	 */
	public List<CueLista> getDataActivos() {
		try {
			if (this.dataActivos == null) {
				this.dataActivos = businessDelegatorView.getCueListasAtivas();
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
	public void setDataActivos(List<CueLista> dataActivos) {
		this.dataActivos = dataActivos;
	}
	
}
