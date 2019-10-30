package com.vortexbird.seguridad.presentation.backingBeans;

import org.apache.log4j.Logger;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.SegRol;
import com.vortexbird.seguridad.modelo.SegRolUsuario;
import com.vortexbird.seguridad.modelo.SegUsuario;
import com.vortexbird.seguridad.modelo.dto.SegRolUsuarioDTO;
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
public class SegRolUsuarioView {
	private SelectOneMenu txtRluEstadoRegistro;
	private SelectOneMenu txtRolCodigo_SegRol;
	private SelectOneMenu txtUsuCodigo_SegUsuario;
	private InputText txtRluCodigo;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnClear;
	private List<SegRolUsuarioDTO> data;
	private SegRolUsuarioDTO selectedSegRolUsuario;
	private List<SelectItem> selectRoles;
	private List<SelectItem> selectUsuarios;
	private Logger logger = Logger.getLogger(SegRolUsuarioView.class);

	public SegRolUsuarioView() {
		super();
	}

	public String action_clear() {
		txtRluEstadoRegistro.setValue("-1");
		txtRolCodigo_SegRol.setValue("-2");
		txtUsuCodigo_SegUsuario.setValue("-2");
		txtRluCodigo.setValue(null);
		btnSave.setDisabled(false);
		btnModify.setDisabled(true);
		btnClear.setDisabled(false);
		return "";
	}


	public String action_save() {
		
		Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
		
		try {

			if (txtRolCodigo_SegRol.getValue().toString().equals("-2")==true) {
				throw new Exception("Debe seleccionar un Rol valido");
			}

			if (txtUsuCodigo_SegUsuario.getValue().toString().equals("-2")==true) {
				throw new Exception("Debe seleccionar un Usuario valido");
			}

			if (txtRluEstadoRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}
			
			String estadoRegistro = txtRluEstadoRegistro.getValue().toString();
			Long codigoRolSeleccionado = Long.parseLong(txtRolCodigo_SegRol.getValue().toString());
			Long codigoUsuSeleccionado = Long.parseLong(txtUsuCodigo_SegUsuario.getValue().toString());

			BusinessDelegatorView.saveSegRolUsuario(
					estadoRegistro,
					codigoRolSeleccionado,
					codigoUsuSeleccionado,
					usuSession);
			
//			SegRolUsuarioDTO rolUsuarioDTO = new SegRolUsuarioDTO();
//			rolUsuarioDTO.setRluCodigo(null);
//			rolUsuarioDTO.setRluEstadoRegistro(estadoRegistro);
//			rolUsuarioDTO.setRluEstadoRegistroNombre(estadoRegistro.equals("1")?"Activo" : "Inactivo");
//			
//			SegRol rol = BusinessDelegatorView.getSegRol(codigoRolSeleccionado);
//			rolUsuarioDTO.setRolCodigo_SegRol(rol.getRolNombre());
//			
//			SegUsuario usu = BusinessDelegatorView.getSegUsuario(codigoUsuSeleccionado);
//			rolUsuarioDTO.setUsuCodigo_SegUsuario(usu.getUsuLogin());
//			
//			data.add(rolUsuarioDTO);
			
			if (usuSession == 0) {
				data = BusinessDelegatorView.getDataSegRolUsuario();	
			}else {
				String sistema = FacesUtils2.getSessionParameter("sistema").toString();
				data = BusinessDelegatorView.getRolesUsuarioBySystem(usuSession, sistema);	
			}
			
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

    public String action_selected(){
    	
    	FacesContext context = FacesContext.getCurrentInstance();  
    	Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
    	
    	String rluCodigo = (String)requestMap.get("rluCodigo");
    	
    	try {
    		SegRolUsuario entity = BusinessDelegatorView.getSegRolUsuario(Long.parseLong(rluCodigo));
    		txtRluEstadoRegistro.setValue(entity.getRluEstadoRegistro());
    		txtRolCodigo_SegRol.setValue(entity.getSegRol().getRolCodigo());
    		txtUsuCodigo_SegUsuario.setValue(entity.getSegUsuarioBySegUsuarioUsuCodigo().getUsuCodigo());
    		txtRluCodigo.setValue(entity.getRluCodigo());
    		btnSave.setDisabled(true);
            btnModify.setDisabled(false);
    	}
    	 catch (NumberFormatException e) {
 			e.printStackTrace();
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
    	
    	return "";
    }

	public String action_modify() {
		
		List<SegRolUsuario> losRolUsuarios = new ArrayList<SegRolUsuario>();
		Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
		
        try {
        	
        	if (txtRolCodigo_SegRol.getValue().toString().equals("-2")==true) {
    			throw new Exception("Debe seleccionar un Rol valido");
    		}
        	
        	if (txtUsuCodigo_SegUsuario.getValue().toString().equals("-2")==true) {
    			throw new Exception("Debe seleccionar un Usuario valido");
    		}
        	
        	if (txtRluEstadoRegistro.getValue().toString().equals("-1")==true) {
    			throw new Exception("Debe seleccionar un estado de registro valido");
    		}
        	
        	Long codigoRolXUsuario = Long.parseLong(txtRluCodigo.getValue().toString());
			String estadoRegistro = txtRluEstadoRegistro.getValue().toString();
			Long codigoRolSeleccionado = Long.parseLong(txtRolCodigo_SegRol.getValue().toString());
			Long codigoUsuSeleccionado = Long.parseLong(txtUsuCodigo_SegUsuario.getValue().toString());
        	
            BusinessDelegatorView.updateSegRolUsuario(
            		codigoRolXUsuario,
                    estadoRegistro,
                    codigoRolSeleccionado,
                    codigoUsuSeleccionado,
                    usuSession);
            
//            losRolUsuarios = BusinessDelegatorView.getSegRolUsuario();
//            data = BusinessDelegatorView.getSegRolUsuariosDTO(losRolUsuarios);
            
			if (usuSession == 0) {
				data = BusinessDelegatorView.getDataSegRolUsuario();	
			}else {
				String sistema = FacesUtils2.getSessionParameter("sistema").toString();
				data = BusinessDelegatorView.getRolesUsuarioBySystem(usuSession, sistema);	
			}
            
            
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
	}


	public String action_modifyWitDTO(Long rluCodigo, String rluEstadoRegistro,
			Long rolCodigo_SegRol, Long usuCodigo_SegUsuario,
			Long usuCodigo_SegUsuario0) throws Exception {
		try {
			BusinessDelegatorView.updateSegRolUsuario(rluCodigo,
					rluEstadoRegistro, rolCodigo_SegRol, usuCodigo_SegUsuario,usuCodigo_SegUsuario0);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			//renderManager.getOnDemandRenderer("SegRolUsuarioView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputText getTxtRluCodigo() {
		return txtRluCodigo;
	}

	public void setTxtRluCodigo(InputText txtRluCodigo) {
		this.txtRluCodigo = txtRluCodigo;
	}

	public List<SegRolUsuarioDTO> getData() {
		try {
			if (data == null) {
				
				Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

				if (usuSession == 0) {
					data = BusinessDelegatorView.getDataSegRolUsuario();	
				}else {
					String sistema = FacesUtils2.getSessionParameter("sistema").toString();
					data = BusinessDelegatorView.getRolesUsuarioBySystem(usuSession, sistema);	
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<SegRolUsuarioDTO> segRolUsuarioDTO) {
		this.data = segRolUsuarioDTO;
	}

	public SegRolUsuarioDTO getSelectedSegRolUsuario() {
		return selectedSegRolUsuario;
	}

	public void setSelectedSegRolUsuario(SegRolUsuarioDTO segRolUsuario) {
		this.selectedSegRolUsuario = segRolUsuario;
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

	public CommandButton getBtnClear() {
		return btnClear;
	}

	public void setBtnClear(CommandButton btnClear) {
		this.btnClear = btnClear;
	}

	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}

	public List<SelectItem> getSelectRoles() {

		selectRoles = new ArrayList<SelectItem>();
		List<SegRol> roles=new ArrayList<SegRol>();  
		
		try {
			
			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
			
			if (usuSession==0) {
				roles=BusinessDelegatorView.findByCriteriaInSegRol(new Object[]{"rolEstadoRegistro",true,"1","="},null, null);	
			}else {
				String sistema = FacesUtils2.getSessionParameter("sistema").toString();
				roles=BusinessDelegatorView.getRolesBySystemModel(usuSession,sistema);
			}
			
			for (SegRol rol: roles) {				
				selectRoles.add(new SelectItem(rol.getRolCodigo(),rol.getRolNombre()));
			}			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return selectRoles;		
	}

	public void setSelectRoles(List<SelectItem> selectRoles) {
		this.selectRoles = selectRoles;
	}

	public List<SelectItem> getSelectUsuarios() {
		selectUsuarios = new ArrayList<SelectItem>();
		List<SegUsuario> usuarios=new ArrayList<SegUsuario>();  
		try {
			
			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

			if (usuSession == 0) {
				usuarios=BusinessDelegatorView.findByCriteriaInSegUsuario(new Object[]{"usuEstadoRegistro",true,"1","="},null, null);	
			}else {
				String sistema = FacesUtils2.getSessionParameter("sistema").toString();
				String compania = FacesUtils2.getSessionParameter("compania").toString();
				usuarios = BusinessDelegatorView.getUsuariosPorSistemaYCia(sistema, compania);	
			}
			
			for (SegUsuario usuario: usuarios) {				
				selectUsuarios.add(new SelectItem(usuario.getUsuCodigo(),usuario.getUsuLogin()));
			}			
		} catch (Exception e) {
		}

		return selectUsuarios;
	}

	public void setSelectUsuarios(List<SelectItem> selectUsuarios) {
		this.selectUsuarios = selectUsuarios;
	}

	public SelectOneMenu getTxtRluEstadoRegistro() {
		return txtRluEstadoRegistro;
	}

	public void setTxtRluEstadoRegistro(SelectOneMenu txtRluEstadoRegistro) {
		this.txtRluEstadoRegistro = txtRluEstadoRegistro;
	}

	public SelectOneMenu getTxtRolCodigo_SegRol() {
		return txtRolCodigo_SegRol;
	}

	public void setTxtRolCodigo_SegRol(SelectOneMenu txtRolCodigo_SegRol) {
		this.txtRolCodigo_SegRol = txtRolCodigo_SegRol;
	}

	public SelectOneMenu getTxtUsuCodigo_SegUsuario() {
		return txtUsuCodigo_SegUsuario;
	}

	public void setTxtUsuCodigo_SegUsuario(SelectOneMenu txtUsuCodigo_SegUsuario) {
		this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

}
