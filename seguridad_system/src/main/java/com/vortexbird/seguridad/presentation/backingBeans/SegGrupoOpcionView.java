package com.vortexbird.seguridad.presentation.backingBeans;

import org.apache.log4j.Logger;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.SegGrupoOpcionDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


public class SegGrupoOpcionView {
	private InputText txtGruDescripcion;
	private SelectOneMenu somEstadosRegistro;
	private InputText txtGruLlaveAcceso;
	private InputText txtGruNombre;
	private InputText txtGruCodigo_SegGrupoOpcion;
	private InputText txtSisCodigo_SegSistema;
	private InputText txtUsuCodigo_SegUsuario;
	private InputText txtGruCodigo;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<SegGrupoOpcionDTO> data;
	private SegGrupoOpcionDTO selectedSegGrupoOpcion;
	private SelectOneMenu somGrupoOpcionPadre;
	private SelectItem[] losGrupoOpcionPadre;
	private SelectOneMenu somSistemas;
	private SelectItem[] losSistemas;
	private Logger logger = Logger.getLogger(SegGrupoOpcionView.class);

	public SegGrupoOpcionView() {
		super();
	}


	public String action_clear() {
		txtGruDescripcion.setValue(null);
		txtGruDescripcion.setDisabled(false);
		txtGruLlaveAcceso.setValue(null);
		txtGruLlaveAcceso.setDisabled(false);
		txtGruNombre.setValue(null);
		txtGruNombre.setDisabled(false);
		somEstadosRegistro.setValue("-1");
		txtGruCodigo.setValue(null);
		somGrupoOpcionPadre.setValue("-1");
		somSistemas.setValue("-1");
		btnSave.setDisabled(false);
		btnModify.setDisabled(true);
		btnClear.setDisabled(false);
		return "";
	}

	public String action_save() {
		try {
			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
			String sistema = FacesUtils2.getSessionParameter("sistema").toString();

			if (txtGruNombre.getValue()==null||txtGruNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre del Grupo no puede estar vacio");
			}

			if (somEstadosRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			if (txtGruDescripcion.getValue()==null||txtGruDescripcion.getValue().toString().equals("")==true ) {
				throw new Exception("La descripcion del grupo no puede estar vacia");
			}

			if (somSistemas.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un sistema valido");
			}

			Long codigoGrupoOpcionPadre=null;
			String descripcion = txtGruDescripcion.getValue().toString();
			String estadoRegistro = somEstadosRegistro.getValue().toString();
			String llaveAceso = txtGruLlaveAcceso.getValue().toString();
			String nombre = txtGruNombre.getValue().toString();

			if (somGrupoOpcionPadre.getValue().toString().equals("-1")== false) {
				codigoGrupoOpcionPadre = Long.parseLong(somGrupoOpcionPadre.getValue().toString());
			}
			Long codigoSistema = Long.parseLong(somSistemas.getValue().toString());

			BusinessDelegatorView.saveSegGrupoOpcion(
					null , descripcion, estadoRegistro, llaveAceso,
					nombre,	codigoGrupoOpcionPadre,	codigoSistema,
					usuSession);

			if (usuSession == 0) {
				data = BusinessDelegatorView.getDataSegGrupoOpcion();	
			}else {
				data = BusinessDelegatorView.getGrupoOpcionesPorSistema(usuSession, sistema);	
			}

			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return "";
	}


	public String action_selected(){

		action_clear();
		btnModify.setDisabled(false);
		btnSave.setDisabled(true);

		FacesContext context = FacesContext.getCurrentInstance();  
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();

		String gruCodigo = (String)requestMap.get("gruCodigo");

		try {

			SegGrupoOpcion entity = BusinessDelegatorView.getSegGrupoOpcion(Long.parseLong(gruCodigo));
			txtGruDescripcion.setValue(entity.getGruDescripcion());
			somEstadosRegistro.setValue(entity.getGruEstadoRegistro());
			txtGruLlaveAcceso.setValue(entity.getGruLlaveAcceso());
			txtGruNombre.setValue(entity.getGruNombre());
			txtGruCodigo.setValue(entity.getGruCodigo());

			if (entity.getSegGrupoOpcion()!=null) {
				somGrupoOpcionPadre.setValue(entity.getSegGrupoOpcion().getGruCodigo());
			}else {
				somGrupoOpcionPadre.setValue("-1");
			}
			somSistemas.setValue(entity.getSegSistema().getSisCodigo());
			btnSave.setDisabled(true);
			btnModify.setDisabled(false);

		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}


	public String action_modify() {

		List<SegGrupoOpcion> losGrupoOpcions = new ArrayList<SegGrupoOpcion>();
		Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

		try {

			if (txtGruNombre.getValue()==null||txtGruNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre del Grupo no puede estar vacio");
			}

			if (somEstadosRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			if (txtGruDescripcion.getValue()==null||txtGruDescripcion.getValue().toString().equals("")==true ) {
				throw new Exception("La descripcion del grupo no puede estar vacia");
			}

			if (somSistemas.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un sistema valido");
			}

			Long grupoCodigo = Long.parseLong(txtGruCodigo.getValue().toString());
			Long codigoGrupoOpcionPadre = null;
			String descripcion = txtGruDescripcion.getValue().toString();
			String estadoRegistro = somEstadosRegistro.getValue().toString();
			String llaveAceso = txtGruLlaveAcceso.getValue().toString();
			String nombre = txtGruNombre.getValue().toString();
			if (somGrupoOpcionPadre.getValue().toString().equals("-1")== false) {
				codigoGrupoOpcionPadre = Long.parseLong(somGrupoOpcionPadre.getValue().toString());
			}
			Long codigoSistema = Long.parseLong(somSistemas.getValue().toString());

			BusinessDelegatorView.updateSegGrupoOpcion(
					grupoCodigo , descripcion, estadoRegistro, llaveAceso,
					nombre,	codigoGrupoOpcionPadre,	codigoSistema,
					usuSession);

//			losGrupoOpcions = BusinessDelegatorView.getSegGrupoOpcion();
//			data = BusinessDelegatorView.getSegGrupoOpcion(losGrupoOpcions);
			
			if (usuSession == 0) {
				data = BusinessDelegatorView.getDataSegGrupoOpcion();	
			}else {
				String sistema = FacesUtils2.getSessionParameter("sistema").toString();
				data = BusinessDelegatorView.getGrupoOpcionesPorSistema(usuSession, sistema);	
			}
			
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return "";
	}

	public String action_modifyWitDTO(Long gruCodigo, String gruDescripcion,
			String gruEstadoRegistro, String gruLlaveAcceso, String gruNombre,
			Long gruCodigo_SegGrupoOpcion, Long sisCodigo_SegSistema,
			Long usuCodigo_SegUsuario) throws Exception {
		try {
			BusinessDelegatorView.updateSegGrupoOpcion(gruCodigo,
					gruDescripcion, gruEstadoRegistro, gruLlaveAcceso, gruNombre,
					gruCodigo_SegGrupoOpcion, sisCodigo_SegSistema,
					usuCodigo_SegUsuario);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			//renderManager.getOnDemandRenderer("SegGrupoOpcionView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputText getTxtGruDescripcion() {
		return txtGruDescripcion;
	}

	public void setTxtGruDescripcion(InputText txtGruDescripcion) {
		this.txtGruDescripcion = txtGruDescripcion;
	}

	public SelectOneMenu getSomEstadosRegistro() {
		return somEstadosRegistro;
	}

	public void setSomEstadosRegistro(SelectOneMenu somEstadosRegistro) {
		this.somEstadosRegistro = somEstadosRegistro;
	}


	public InputText getTxtGruLlaveAcceso() {
		return txtGruLlaveAcceso;
	}

	public void setTxtGruLlaveAcceso(InputText txtGruLlaveAcceso) {
		this.txtGruLlaveAcceso = txtGruLlaveAcceso;
	}

	public InputText getTxtGruNombre() {
		return txtGruNombre;
	}

	public void setTxtGruNombre(InputText txtGruNombre) {
		this.txtGruNombre = txtGruNombre;
	}

	public InputText getTxtGruCodigo_SegGrupoOpcion() {
		return txtGruCodigo_SegGrupoOpcion;
	}

	public void setTxtGruCodigo_SegGrupoOpcion(
			InputText txtGruCodigo_SegGrupoOpcion) {
		this.txtGruCodigo_SegGrupoOpcion = txtGruCodigo_SegGrupoOpcion;
	}

	public InputText getTxtSisCodigo_SegSistema() {
		return txtSisCodigo_SegSistema;
	}

	public void setTxtSisCodigo_SegSistema(InputText txtSisCodigo_SegSistema) {
		this.txtSisCodigo_SegSistema = txtSisCodigo_SegSistema;
	}

	public InputText getTxtUsuCodigo_SegUsuario() {
		return txtUsuCodigo_SegUsuario;
	}

	public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
		this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
	}

	public InputText getTxtGruCodigo() {
		return txtGruCodigo;
	}

	public void setTxtGruCodigo(InputText txtGruCodigo) {
		this.txtGruCodigo = txtGruCodigo;
	}


	public void setData(List<SegGrupoOpcionDTO> segGrupoOpcionDTO) {
		this.data = segGrupoOpcionDTO;
	}

	public SegGrupoOpcionDTO getSelectedSegGrupoOpcion() {
		return selectedSegGrupoOpcion;
	}

	public void setSelectedSegGrupoOpcion(SegGrupoOpcionDTO segGrupoOpcion) {
		this.selectedSegGrupoOpcion = segGrupoOpcion;
	}

	public CommandButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(CommandButton btnSave) {
		this.btnSave = btnSave;
	}

	public CommandButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(CommandButton btnModify) {
		this.btnModify = btnModify;
	}

	public CommandButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(CommandButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public CommandButton getBtnClear() {
		return btnClear;
	}

	public void setBtnClear(CommandButton btnClear) {
		this.btnClear = btnClear;
	}

	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}

	public List<SegGrupoOpcionDTO> getData() {
		try {
			if (data == null) {
				Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

				if (usuSession == 0) {
					data = BusinessDelegatorView.getDataSegGrupoOpcion();	
				}else {
					String sistema = FacesUtils2.getSessionParameter("sistema").toString();
					data = BusinessDelegatorView.getGrupoOpcionesPorSistema(usuSession, sistema);	
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}


	public SelectOneMenu getSomGrupoOpcionPadre() {
		return somGrupoOpcionPadre;
	}


	public void setSomGrupoOpcionPadre(SelectOneMenu somGrupoOpcionPadre) {
		this.somGrupoOpcionPadre = somGrupoOpcionPadre;
	}


	public SelectItem[] getLosGrupoOpcionPadre() {

		try {
			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
			List<SegGrupoOpcion> listGrupoOpcion = new ArrayList<SegGrupoOpcion>();
			
			if (usuSession==0) {
				listGrupoOpcion=BusinessDelegatorView.getSegGrupoOpcion();
			}else {
				String sistema = FacesUtils2.getSessionParameter("sistema").toString();
				listGrupoOpcion=BusinessDelegatorView.getGrupoOpcionesPorSistemaModel(usuSession, sistema);
			}
			losGrupoOpcionPadre=new SelectItem[listGrupoOpcion.size()];
			int i=0;
			for (SegGrupoOpcion grupoOpcion : listGrupoOpcion) {
				losGrupoOpcionPadre[i]= new SelectItem(grupoOpcion.getGruCodigo(),grupoOpcion.getGruNombre().toString());
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return losGrupoOpcionPadre;
	}


	public void setLosGrupoOpcionPadre(SelectItem[] losGrupoOpcionPadre) {
		this.losGrupoOpcionPadre = losGrupoOpcionPadre;
	}


	public SelectOneMenu getSomSistemas() {
		return somSistemas;
	}


	public void setSomSistemas(SelectOneMenu somSistemas) {
		this.somSistemas = somSistemas;
	}

	public SelectItem[] getLosSistemas() {
		try {
			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
			List<SegSistema> listSistemas = new ArrayList<SegSistema>();
			
			if (usuSession == 0) {
				listSistemas=BusinessDelegatorView.getSegSistema();
			}else {
				String sistema = FacesUtils2.getSessionParameter("sistema").toString();
				listSistemas = BusinessDelegatorView.getSistemasDeUsuario(usuSession, sistema);
			}
			
			losSistemas=new SelectItem[listSistemas.size()];
			int i=0;
			for (SegSistema segSistema : listSistemas) {
				losSistemas[i]= new SelectItem(segSistema.getSisCodigo(),segSistema.getSisNombre().toString());
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return losSistemas;
	}


	public void setLosSistemas(SelectItem[] losSistemas) {
		this.losSistemas = losSistemas;
	}
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
}