package com.vortexbird.seguridad.presentation.backingBeans;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.SegRol;
import com.vortexbird.seguridad.modelo.SegSistema;
import com.vortexbird.seguridad.modelo.dto.SegRolDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SegRolView {
	private InputText txtRolDescripcion;
	private InputText txtRolDiasCaducidadPwd;
	private SelectOneMenu txtRolEstadoRegistro;
	private InputText txtRolNombre;
	private SelectOneMenu txtSisCodigo_SegSistema;
	private InputText txtUsuCodigo_SegUsuario;
	private InputText txtRolCodigo;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<SegRolDTO> data;
	private SegRolDTO selectedSegRol;
	private List<SelectItem> selectSistemas;

	public SegRolView() {
		super();
	}


	public String action_clear() {
		txtRolDescripcion.setValue(null);
		txtRolDiasCaducidadPwd.setValue(null);
		txtRolEstadoRegistro.setValue("-1");
		txtRolNombre.setValue(null);
		txtSisCodigo_SegSistema.setValue("-1");
		txtRolCodigo.setValue(null);
		btnSave.setDisabled(false);
		btnModify.setDisabled(true);
		btnClear.setDisabled(false);
		return "";
	}

	public String action_save() {

		Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

		try {

			if (txtRolNombre.getValue()==null||txtRolNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre del rol no puede estar vacio");
			}


			if (txtRolEstadoRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			if (txtRolDiasCaducidadPwd.getValue()==null||txtRolDiasCaducidadPwd.getValue().toString().equals("")==true ) {
				throw new Exception("El numero de dias de caducidad del password no puede estar vacio");
			}

			if (txtRolDescripcion.getValue()==null||txtRolDescripcion.getValue().toString().equals("")==true ) {
				throw new Exception("La Descripcion del rol no puede estar vacia");
			}

			if (txtSisCodigo_SegSistema.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un Sistema valido");
			}

			String descripcion =  txtRolDescripcion.getValue().toString();
			Long diasCaducidad = Long.parseLong(txtRolDiasCaducidadPwd.getValue().toString());
			String estadoRegistro = txtRolEstadoRegistro.getValue().toString();
			String nombre = txtRolNombre.getValue().toString();
			Long codigoSistema = Long.parseLong(txtSisCodigo_SegSistema.getValue().toString());

			BusinessDelegatorView.saveSegRol(
					descripcion,
					diasCaducidad,
					estadoRegistro,
					nombre ,
					codigoSistema,
					usuSession);

//			SegRolDTO rolDTO = new SegRolDTO();
//			rolDTO.setRolCodigo(null);
//			rolDTO.setRolDescripcion(descripcion);
//			rolDTO.setRolDiasCaducidadPwd(diasCaducidad.toString());
//			rolDTO.setRolEstadoRegistro(estadoRegistro);
//			rolDTO.setRolEstadoRegistroNombre(estadoRegistro.equals("1")?"Activo":"Activo");
//			rolDTO.setRolNombre(nombre);
//
//			SegSistema sistema = BusinessDelegatorView.getSegSistema(codigoSistema);
//			rolDTO.setSisCodigo_SegSistema(sistema.getSisNombre());
//
//			data.add(rolDTO);
			
			if (usuSession == 0) {
				data = BusinessDelegatorView.getDataSegRol();
			}else {
				String sistema = FacesUtils2.getSessionParameter("sistema").toString();
				data = BusinessDelegatorView.getRolesBySystem(usuSession, sistema);
			}
			
			
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String action_selected(){

		action_clear();

		FacesContext context = FacesContext.getCurrentInstance();  
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();

		String rolCodigo = (String)requestMap.get("rolCodigo");
		try {
			SegRol entity = BusinessDelegatorView.getSegRol(Long.parseLong(rolCodigo));
			txtRolDescripcion.setValue(entity.getRolDescripcion());
			txtRolDiasCaducidadPwd.setValue(entity.getRolDiasCaducidadPwd());
			txtRolEstadoRegistro.setValue(entity.getRolEstadoRegistro());
			txtRolNombre.setValue(entity.getRolNombre());
			txtSisCodigo_SegSistema.setValue(entity.getSegSistema().getSisCodigo());
			txtRolCodigo.setValue(entity.getRolCodigo());
			btnSave.setDisabled(true);
			btnModify.setDisabled(false);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String action_modify() {

		Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
		List<SegRol> losRoles = new ArrayList<SegRol>();

		try {

			if (txtRolNombre.getValue()==null||txtRolNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre del rol no puede estar vacio");
			}

			if (txtRolEstadoRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			if (txtRolDiasCaducidadPwd.getValue()==null||txtRolDiasCaducidadPwd.getValue().toString().equals("")==true ) {
				throw new Exception("El numero de dias de caducidad del password no puede estar vacio");
			}

			if (txtRolDescripcion.getValue()==null||txtRolDescripcion.getValue().toString().equals("")==true ) {
				throw new Exception("La Descripcion del rol no puede estar vacia");
			}

			if (txtSisCodigo_SegSistema.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un Sistema valido");
			}

			Long codigoRol = Long.parseLong(txtRolCodigo.getValue().toString());
			String descripcion =  txtRolDescripcion.getValue().toString();
			Long diasCaducidad = Long.parseLong(txtRolDiasCaducidadPwd.getValue().toString());
			String estadoRegistro = txtRolEstadoRegistro.getValue().toString();
			String nombre = txtRolNombre.getValue().toString();
			Long codigoSistema = Long.parseLong(txtSisCodigo_SegSistema.getValue().toString());

			BusinessDelegatorView.updateSegRol(
					codigoRol,
					descripcion,
					diasCaducidad,
					estadoRegistro,
					nombre,
					codigoSistema,
					usuSession
					);

//			losRoles = BusinessDelegatorView.getSegRol();
//			data = BusinessDelegatorView.getSegRolDTO(losRoles);
			
			if (usuSession == 0) {
				data = BusinessDelegatorView.getDataSegRol();
			}else {
				String sistema = FacesUtils2.getSessionParameter("sistema").toString();
				data = BusinessDelegatorView.getRolesBySystem(usuSession, sistema);
			}
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}


	public String action_modifyWitDTO(Long rolCodigo, String rolDescripcion,
			Long rolDiasCaducidadPwd, String rolEstadoRegistro, String rolNombre,
			Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario)
					throws Exception {
		try {
			BusinessDelegatorView.updateSegRol(rolCodigo, rolDescripcion,
					rolDiasCaducidadPwd, rolEstadoRegistro, rolNombre,
					sisCodigo_SegSistema, usuCodigo_SegUsuario);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputText getTxtRolDescripcion() {
		return txtRolDescripcion;
	}

	public void setTxtRolDescripcion(InputText txtRolDescripcion) {
		this.txtRolDescripcion = txtRolDescripcion;
	}

	public InputText getTxtRolDiasCaducidadPwd() {
		return txtRolDiasCaducidadPwd;
	}

	public void setTxtRolDiasCaducidadPwd(InputText txtRolDiasCaducidadPwd) {
		this.txtRolDiasCaducidadPwd = txtRolDiasCaducidadPwd;
	}

	public SelectOneMenu getTxtRolEstadoRegistro() {
		return txtRolEstadoRegistro;
	}

	public void setTxtRolEstadoRegistro(SelectOneMenu txtRolEstadoRegistro) {
		this.txtRolEstadoRegistro = txtRolEstadoRegistro;
	}

	public InputText getTxtRolNombre() {
		return txtRolNombre;
	}

	public void setTxtRolNombre(InputText txtRolNombre) {
		this.txtRolNombre = txtRolNombre;
	}


	public SelectOneMenu getTxtSisCodigo_SegSistema() {
		return txtSisCodigo_SegSistema;
	}


	public void setTxtSisCodigo_SegSistema(SelectOneMenu txtSisCodigo_SegSistema) {
		this.txtSisCodigo_SegSistema = txtSisCodigo_SegSistema;
	}


	public InputText getTxtUsuCodigo_SegUsuario() {
		return txtUsuCodigo_SegUsuario;
	}

	public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
		this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
	}

	public InputText getTxtRolCodigo() {
		return txtRolCodigo;
	}

	public void setTxtRolCodigo(InputText txtRolCodigo) {
		this.txtRolCodigo = txtRolCodigo;
	}

	public List<SegRolDTO> getData() {
		try {
			if (data == null) {

				Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
				if (usuSession == 0) {
					data = BusinessDelegatorView.getDataSegRol();
				}else {
					String sistema = FacesUtils2.getSessionParameter("sistema").toString();
					data = BusinessDelegatorView.getRolesBySystem(usuSession, sistema);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<SegRolDTO> segRolDTO) {
		this.data = segRolDTO;
	}

	public SegRolDTO getSelectedSegRol() {
		return selectedSegRol;
	}

	public void setSelectedSegRol(SegRolDTO segRol) {
		this.selectedSegRol = segRol;
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


	public List<SelectItem> getSelectSistemas() {

		selectSistemas = new ArrayList<SelectItem>();
		List<SegSistema> sistemas=new ArrayList<SegSistema>();
		Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

		try {

			if (usuSession==0) {
				sistemas=BusinessDelegatorView.findByCriteriaInSegSistema(new Object[]{"sisEstadoRegistro",true,"1","="},null, null);
			}else {
				String sistema = FacesUtils2.getSessionParameter("sistema").toString();
				sistemas = BusinessDelegatorView.getSistemasDeUsuario(usuSession, sistema);
			}

			for (SegSistema sistema: sistemas) {				
				selectSistemas.add(new SelectItem(sistema.getSisCodigo(),sistema.getSisNombre()));
			}			
		} catch (Exception e) {
		}

		return selectSistemas;		
	}

	public void setSelectSistemas(List<SelectItem> selectSistemas) {
		this.selectSistemas = selectSistemas;
	}


}
