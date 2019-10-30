package com.vortexbird.seguridad.presentation.backingBeans;

import org.apache.log4j.Logger;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.SegCompaniaDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.faces.context.FacesContext;


public class SegCompaniaView {
	private InputText txtCiaCodigoInterno;
	private InputText txtCiaNombre;
	private InputText txtUsuCodigo_SegUsuario;
	private InputText txtCiaCodigo;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<SegCompaniaDTO> data;
	private SegCompaniaDTO selectedSegCompania;
	private SelectOneMenu somEstadosRegistro;
	private Logger logger = Logger.getLogger(SegCompaniaView.class);

	public SegCompaniaView() {
		super();
	}

	public String action_clear() {
		txtCiaCodigoInterno.setValue(null);
		txtCiaCodigoInterno.setDisabled(false);
		txtCiaNombre.setValue(null);
		txtCiaCodigo.setValue(null);
		txtCiaNombre.setDisabled(false);
		btnSave.setDisabled(false);
		btnModify.setDisabled(true);
		btnClear.setDisabled(false);
		somEstadosRegistro.setValue("-1");

		return "";
	}


	public String action_save() {
		try {

			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

			if (txtCiaCodigoInterno.getValue()==null||txtCiaCodigoInterno.getValue().toString().equals("")==true ) {
				throw new Exception("El Codigo interno de la Compa�ia no puede estar vacio");
			}

			if (txtCiaNombre.getValue()==null||txtCiaNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre de la Compa�ia no puede estar vacio");
			}

			if (somEstadosRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			String ciaCodigoInterno = txtCiaCodigoInterno.getValue().toString();
			String ciaEstadoRegistro = somEstadosRegistro.getValue().toString();
			String ciaNombre = txtCiaNombre.getValue().toString();

			BusinessDelegatorView.saveSegCompania(null, ciaCodigoInterno,ciaEstadoRegistro,ciaNombre, usuSession);

//			SegCompaniaDTO segCompaniaDTOs = new SegCompaniaDTO();
//			segCompaniaDTOs.setCiaCodigo(null);
//			segCompaniaDTOs.setCiaCodigoInterno(ciaCodigoInterno);
//			segCompaniaDTOs.setCiaEstadoRegistro(ciaEstadoRegistro.equals("0")?"Inactivo":"Activo");
//			segCompaniaDTOs.setCiaNombre(ciaNombre);
//			data.add(segCompaniaDTOs);
			
			if (usuSession==0) {
				data = BusinessDelegatorView.getDataSegCompania();
			}else {
				String sistema = FacesUtils2.getSessionParameter("sistema").toString();
				data=BusinessDelegatorView.getCompaniasDeUsuarioPorRolDTO(usuSession, Long.parseLong(sistema.toString()));
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

		String ciaCodigo = (String)requestMap.get("ciaCodigo");

		try {

			SegCompania entity = BusinessDelegatorView.getSegCompania(Long.parseLong(ciaCodigo));
			txtCiaCodigoInterno.setValue(entity.getCiaCodigoInterno());
			somEstadosRegistro.setValue(entity.getCiaEstadoRegistro());
			txtCiaNombre.setValue(entity.getCiaNombre());
			txtCiaCodigo.setValue(entity.getCiaCodigo());
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
		
		List<SegCompania> lasCompanias = new ArrayList<SegCompania>();

		Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
		
		try {

			if (txtCiaCodigoInterno.getValue()==null||txtCiaCodigoInterno.getValue().toString().equals("")==true ) {
				throw new Exception("El Codigo interno de la Compa�ia no puede estar vacio");
			}

			if (txtCiaNombre.getValue()==null||txtCiaNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre de la Compa�ia no puede estar vacio");
			}

			if (somEstadosRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			Long ciaCodigo = Long.parseLong(txtCiaCodigo.getValue().toString());
			String ciaCodigoInterno = txtCiaCodigoInterno.getValue().toString();
			String ciaEstadoRegistro = somEstadosRegistro.getValue().toString();
			String ciaNombre = txtCiaNombre.getValue().toString();

			BusinessDelegatorView.updateSegCompania(ciaCodigo, ciaCodigoInterno, ciaEstadoRegistro, ciaNombre, usuSession);
//			lasCompanias = BusinessDelegatorView.getSegCompania();
//			data = BusinessDelegatorView.getSegCompania(lasCompanias);
			
			if (usuSession==0) {
				data = BusinessDelegatorView.getDataSegCompania();
			}else {
				String sistema = FacesUtils2.getSessionParameter("sistema").toString();
				data=BusinessDelegatorView.getCompaniasDeUsuarioPorRolDTO(usuSession, Long.parseLong(sistema.toString()));
			}
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return "";
	}

	public String action_modifyWitDTO(Long ciaCodigo, String ciaCodigoInterno,
			String ciaEstadoRegistro, String ciaNombre, Long usuCodigo_SegUsuario)
					throws Exception {
		try {
			BusinessDelegatorView.updateSegCompania(ciaCodigo,
					ciaCodigoInterno, ciaEstadoRegistro, ciaNombre,
					usuCodigo_SegUsuario);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputText getTxtCiaCodigoInterno() {
		return txtCiaCodigoInterno;
	}

	public void setTxtCiaCodigoInterno(InputText txtCiaCodigoInterno) {
		this.txtCiaCodigoInterno = txtCiaCodigoInterno;
	}

	public InputText getTxtCiaNombre() {
		return txtCiaNombre;
	}

	public void setTxtCiaNombre(InputText txtCiaNombre) {
		this.txtCiaNombre = txtCiaNombre;
	}

	public InputText getTxtUsuCodigo_SegUsuario() {
		return txtUsuCodigo_SegUsuario;
	}

	public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
		this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
	}

	public InputText getTxtCiaCodigo() {
		return txtCiaCodigo;
	}

	public void setTxtCiaCodigo(InputText txtCiaCodigo) {
		this.txtCiaCodigo = txtCiaCodigo;
	}

	public List<SegCompaniaDTO> getData() {
		try {
			if (data == null) {
				Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
				if (usuSession==0) {
					data = BusinessDelegatorView.getDataSegCompania();
				}else {
					String sistema = FacesUtils2.getSessionParameter("sistema").toString();
					data=BusinessDelegatorView.getCompaniasDeUsuarioPorRolDTO(usuSession, Long.parseLong(sistema.toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<SegCompaniaDTO> segCompaniaDTO) {
		this.data = segCompaniaDTO;
	}

	public SegCompaniaDTO getSelectedSegCompania() {
		return selectedSegCompania;
	}

	public void setSelectedSegCompania(SegCompaniaDTO segCompania) {
		this.selectedSegCompania = segCompania;
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

	public SelectOneMenu getSomEstadosRegistro() {
		return somEstadosRegistro;
	}

	public void setSomEstadosRegistro(SelectOneMenu somEstadosRegistro) {
		this.somEstadosRegistro = somEstadosRegistro;
	}
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
}