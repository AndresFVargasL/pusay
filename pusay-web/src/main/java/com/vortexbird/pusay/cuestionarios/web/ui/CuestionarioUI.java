/**
 * 
 */
package com.vortexbird.pusay.cuestionarios.web.ui;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;

import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.model.CueCategoria;
import com.vortexbird.pusay.cuestionarios.model.CueConfiguracion;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionario;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionarioTipo;
import com.vortexbird.pusay.cuestionarios.model.CueEstado;
import com.vortexbird.pusay.cuestionarios.model.CueListaCuestionario;
import com.vortexbird.pusay.cuestionarios.model.CueResponsable;
import com.vortexbird.pusay.cuestionarios.api.businessdelegate.IBusinessDelegatorEncuestasView;
import com.vortexbird.pusay.cuestionarios.web.utils.AbstractBaseMessageUI;
import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;

/**
 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
 * @project cuestionario-web
 * @class CuestionarioUI
 * @date 23/07/2013
 * 
 */
@ManagedBean
@ViewScoped
public class CuestionarioUI extends AbstractBaseMessageUI {

	private CueCuestionario cuestionario;
	private CueListaCuestionario listaCuestionario;
	private CueCuestionario selectedCuestionario;
	private CueConfiguracion selectedConfiguracion;
	@ManagedProperty(value = "#{BusinessDelegatorEncuestasView}")
	private IBusinessDelegatorEncuestasView businessDelegatorView;
	private List<CueCuestionario> data;
	private List<CueConfiguracion> dataConfiguracion;
	private List<SelectItem> listResponsable;
	private List<SelectItem> listCuestionarioTipo;
	private List<SelectItem> listEstados;
	private boolean disableButtonSave;
	private boolean disableButtonModify;
	private boolean disableButtonDelete;

	private String urlToUse;
	private Long consecutivoToUseURL;

	public CuestionarioUI() {

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
			this.cuestionario.setFechaCreacion(new Date());
			this.businessDelegatorView.saveCueCuestionario(this.cuestionario);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			this.consecutivoToUseURL = this.cuestionario.getConsecutivo();
			this.urlToUse = "";
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
			this.businessDelegatorView.updateCueCuestionario(this.cuestionario);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			this.consecutivoToUseURL = this.cuestionario.getConsecutivo();
			this.urlToUse = "";
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

	public String actionGenerarURL() {
		try {
			List<CueListaCuestionario> listaCuestionario = this.businessDelegatorView
					.getCueListaCuestionarioPorCuestionario(this.consecutivoToUseURL);
			if (listaCuestionario != null && listaCuestionario.size() > 0) {
				this.urlToUse = "http://"
						+ getIP()
						+ ":"
						+ getPort()
						+ "/pusay-web/gui/forms/front/cuestionario/diligenciarCuestionario.xhtml?cuestionarioId="
						+ this.consecutivoToUseURL + "&codigoLista="
						+ listaCuestionario.get(0).getCueLista().getConsecutivo()
						+ "&identificacion=(reemplazar)&nombre=(reemplazar)&apellido=(reemplazar)&correo=(reemplazar)";
			} else {
				FacesUtils.addInfoMessage(ZMessManager.NO_LISTA_ASIGANADA);
			}
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
			this.businessDelegatorView.deleteCueCuestionario(this.cuestionario.getConsecutivo());

			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);

			actionClear();
			this.data = null;
			getData();

			this.disableButtonSave = false;
			this.disableButtonModify = true;
			this.disableButtonDelete = true;
			this.urlToUse = "";
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String getIP() {
		String ipAddress = "";
		 InetAddress ip;
		  try {
			ip = InetAddress.getLocalHost();
			ipAddress =  ip.getCanonicalHostName();
		  } catch (UnknownHostException e) {
			e.printStackTrace();
		  }
		return ipAddress;
	}

	public String getPort() {
		String ipPort = "";
		Integer puerto = null;
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			ipPort = request.getHeader("X-FORWARDED-FOR");
			if (ipPort == null) {
				puerto = request.getLocalPort();
			}
		} catch (Exception e) {
			throw new FacesException(e);
		}
		return puerto != null ? puerto.toString() : "";
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 22/07/2013
	 * @return
	 */
	public String actionLoadEntity() {

		CueCuestionario entity = null;

		try {
			Long consecutivo = this.selectedCuestionario.getConsecutivo();
			entity = this.businessDelegatorView.getCueCuestionario(consecutivo);
			load(entity);

			this.disableButtonSave = true;
			this.disableButtonModify = false;
			this.disableButtonDelete = false;

			ListaCuestionarioUI listaCuestionarioUI = (ListaCuestionarioUI) FacesUtils
					.getManagedBeanFromSession("listaCuestionarioUI");
			listaCuestionarioUI.setSelectedCuestionario(selectedCuestionario);
			listaCuestionarioUI.action_cargar();
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
	private void load(CueCuestionario entity) {
		this.cuestionario = entity;

		CategoriaUI categoriaUI = (CategoriaUI) FacesUtils.getManagedBean("categoriaUI");
		if (categoriaUI != null) {
			categoriaUI.setCueCategoria(new CueCategoria());
			categoriaUI.getCueCategoria().setCueCuestionario(this.cuestionario);
			categoriaUI.setData(null);
		}
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 20/07/2013
	 */
	public String actionClear() {
		this.cuestionario = new CueCuestionario();
		this.listaCuestionario = new CueListaCuestionario();
		CueResponsable responsable = new CueResponsable();
		responsable.setIdentificacion(0L);
		this.cuestionario.setCueResponsable(responsable);
		CueCuestionarioTipo cuestionarioTipo = new CueCuestionarioTipo();
		cuestionarioTipo.setConsecutivo(0L);
		this.cuestionario.setCueCuestionarioTipo(cuestionarioTipo);
		CueConfiguracion configuracion = new CueConfiguracion();
		configuracion.setConsecutivo(0L);
		this.cuestionario.setCueConfiguracion(configuracion);
		CueEstado estado = new CueEstado();
		estado.setConsecutivo(0L);
		this.cuestionario.setCueEstado(estado);
		this.disableButtonSave = false;
		this.disableButtonModify = true;
		this.disableButtonDelete = true;

		CategoriaUI categoriaUI = (CategoriaUI) FacesUtils.getManagedBean("categoriaUI");
		if (categoriaUI != null) {
			categoriaUI.setCueCategoria(new CueCategoria());
		}

		ListaCuestionarioUI listaCuestionarioUI = (ListaCuestionarioUI) FacesUtils
				.getManagedBean("listaCuestionarioUI");
		if (listaCuestionarioUI != null) {
			listaCuestionarioUI.setData(null);
			listaCuestionarioUI.setSelectedCuestionario(null);
		}
		this.urlToUse = "";
		return "";
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param selectEvent
	 */
	public void listenerFecha(SelectEvent selectEvent) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Fecha Seleccionada " + dateFormat.format((Date) selectEvent.getObject())));
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the cuestionario
	 */
	public CueCuestionario getCuestionario() {
		return cuestionario;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param cuestionario
	 *            the cuestionario to set
	 */
	public void setCuestionario(CueCuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the selectedcuestionario
	 */
	public CueCuestionario getSelectedCuestionario() {
		return selectedCuestionario;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param selectedcuestionario
	 *            the selectedcuestionario to set
	 */
	public void setSelectedCuestionario(CueCuestionario selectedCuestionario) {
		this.selectedCuestionario = selectedCuestionario;
		this.urlToUse = "";
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param selectedcuestionario
	 *            the selectedcuestionario to set
	 */
	public void setSelectedCuestionarioUrl(CueCuestionario selectedCuestionario) {
		this.urlToUse = "";
		this.consecutivoToUseURL = selectedCuestionario.getConsecutivo();
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the selectedcuestionario
	 */
	public CueCuestionario getSelectedCuestionarioUrl() {
		return selectedCuestionario;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the businessDelegatorView
	 */
	public IBusinessDelegatorEncuestasView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param businessDelegatorView
	 *            the businessDelegatorView to set
	 */
	public void setBusinessDelegatorView(IBusinessDelegatorEncuestasView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the data
	 */
	public List<CueCuestionario> getData() {
		if (this.data == null) {
			try {
				this.data = this.businessDelegatorView.getCueCuestionario();
			} catch (Exception e) {
				error(e);
			}
		}
		return data;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param data
	 *            the data to set
	 */
	public void setData(List<CueCuestionario> data) {
		this.data = data;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the disableButtonSave
	 */
	public boolean isDisableButtonSave() {
		return disableButtonSave;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param disableButtonSave
	 *            the disableButtonSave to set
	 */
	public void setDisableButtonSave(boolean disableButtonSave) {
		this.disableButtonSave = disableButtonSave;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the disableButtonModify
	 */
	public boolean isDisableButtonModify() {
		return disableButtonModify;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param disableButtonModify
	 *            the disableButtonModify to set
	 */
	public void setDisableButtonModify(boolean disableButtonModify) {
		this.disableButtonModify = disableButtonModify;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the disableButtonDelete
	 */
	public boolean isDisableButtonDelete() {
		return disableButtonDelete;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param disableButtonDelete
	 *            the disableButtonDelete to set
	 */
	public void setDisableButtonDelete(boolean disableButtonDelete) {
		this.disableButtonDelete = disableButtonDelete;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the listResponsable
	 */
	public List<SelectItem> getListResponsable() {
		if (this.listResponsable == null) {
			try {
				List<CueResponsable> list = this.businessDelegatorView.getCueResponsable();
				if (list != null && list.size() > 0) {
					this.listResponsable = new ArrayList<SelectItem>();
					this.listResponsable.add(new SelectItem("", "Seleccione"));
					for (CueResponsable cueResponsable : list) {
						this.listResponsable.add(new SelectItem(cueResponsable.getIdentificacion(), cueResponsable
								.getIdentificacion()
								+ " - "
								+ cueResponsable.getNombre()
								+ " "
								+ cueResponsable.getApellido()));
					}
				}
			} catch (Exception e) {
				error(e);
			}
		}
		return this.listResponsable;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param listResponsable
	 *            the listResponsable to set
	 */
	public void setListResponsable(List<SelectItem> listResponsable) {
		this.listResponsable = listResponsable;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the listCuestionarioTipo
	 */
	public List<SelectItem> getListCuestionarioTipo() {
		if (this.listCuestionarioTipo == null) {
			try {
				this.listCuestionarioTipo = toSelectItem(this.businessDelegatorView.getCueCuestionarioTiposActivos(),
						"consecutivo", "nombre");
			} catch (Exception e) {
				error(e);
			}
		}
		return this.listCuestionarioTipo;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param listCuestionarioTipo
	 *            the listCuestionarioTipo to set
	 */
	public void setListCuestionarioTipo(List<SelectItem> listCuestionarioTipo) {
		this.listCuestionarioTipo = listCuestionarioTipo;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the selectedConfiguracion
	 */
	public CueConfiguracion getSelectedConfiguracion() {
		return selectedConfiguracion;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param selectedConfiguracion
	 *            the selectedConfiguracion to set
	 */
	public void setSelectedConfiguracion(CueConfiguracion selectedConfiguracion) {
		this.selectedConfiguracion = selectedConfiguracion;
		this.cuestionario.setCueConfiguracion(this.selectedConfiguracion);
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the dataConfiguracion
	 */
	public List<CueConfiguracion> getDataConfiguracion() {
		if (this.dataConfiguracion == null) {
			try {
				this.dataConfiguracion = this.businessDelegatorView.getCueConfiguracion();
			} catch (Exception e) {
				error(e);
			}
		}
		return dataConfiguracion;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param dataConfiguracion
	 *            the dataConfiguracion to set
	 */
	public void setDataConfiguracion(List<CueConfiguracion> dataConfiguracion) {
		this.dataConfiguracion = dataConfiguracion;
	}

	public List<SelectItem> getListEstados() {
		if (this.listEstados == null) {
			try {
				this.listEstados = toSelectItem(this.businessDelegatorView.getCueEstadoActivo(), "consecutivo",
						"nombre");
			} catch (Exception e) {
				error(e);
			}
		}
		return listEstados;
	}

	public void setListEstados(List<SelectItem> listEstados) {
		this.listEstados = listEstados;
	}

	public CueListaCuestionario getListaCuestionario() {
		return listaCuestionario;
	}

	public void setListaCuestionario(CueListaCuestionario listaCuestionario) {
		this.listaCuestionario = listaCuestionario;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date Sep 19, 2013
	 * @return the urlToUse
	 */
	public String getUrlToUse() {
		return urlToUse;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date Sep 19, 2013
	 * @param urlToUse
	 *            the urlToUse to set
	 */
	public void setUrlToUse(String urlToUse) {
		this.urlToUse = urlToUse;
	}

}
