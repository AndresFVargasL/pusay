package com.vortexbird.seguridad.presentation.backingBeans;

import org.apache.log4j.Logger;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.SegSistema;
import com.vortexbird.seguridad.modelo.dto.SegSistemaDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.faces.context.FacesContext;

/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SegSistemaView {
    private InputText txtSisDescripcion;
    private SelectOneMenu somEstadosRegistro;
    private InputText txtSisNombre;
    private InputText txtUsuCodigo_SegUsuario;
    private InputText txtSisCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SegSistemaDTO> data;
    private SegSistemaDTO selectedSegSistema;
    private Logger logger = Logger.getLogger(SegSistemaView.class);

    public SegSistemaView() {
        super();
    }

	public String action_clear() {
		txtSisDescripcion.setValue(null);
		txtSisDescripcion.setDisabled(false);
		somEstadosRegistro.setValue("-1");
		txtSisNombre.setValue(null);
		txtSisNombre.setDisabled(false);
		btnSave.setDisabled(false);
		txtSisCodigo.setValue(null);
		btnModify.setDisabled(true);
		btnClear.setDisabled(false);

		return "";
	}

    public String action_save() {
        try {
        	Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
        	
			if (txtSisNombre.getValue()==null||txtSisNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre del sistema no puede estar vacio");
			}
			if (somEstadosRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}
			if (txtSisDescripcion.getValue()==null||txtSisDescripcion.getValue().toString().equals("")==true ) {
				throw new Exception("La descripcion del sistema no puede estar vacia");
			}

			String sisNombre = txtSisNombre.getValue().toString();
			String sisEstadoRegistro = somEstadosRegistro.getValue().toString();
			String sisDescripcion = txtSisDescripcion.getValue().toString();

			BusinessDelegatorView.saveSegSistema(null, sisDescripcion, sisEstadoRegistro, sisNombre, usuSession);
			SegSistemaDTO segSistemaDTOs = new SegSistemaDTO();
			segSistemaDTOs.setSisCodigo(null);
			segSistemaDTOs.setSisDescripcion(sisDescripcion);
			
			if (sisEstadoRegistro.equals("0")==true) {
				sisEstadoRegistro = "Inactivo";
			}else {
				sisEstadoRegistro = "Activo";
			}
			segSistemaDTOs.setSisEstadoRegistro(sisEstadoRegistro);
			segSistemaDTOs.setSisNombre(sisNombre);
			data.add(segSistemaDTOs);
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
		Map requestMap = context.getExternalContext().getRequestParameterMap();
		String sisCodigo = (String)requestMap.get("sisCodigo");

		try {
			SegSistema entity = BusinessDelegatorView.getSegSistema(Long.parseLong(sisCodigo));
			txtSisDescripcion.setValue(entity.getSisDescripcion());
			somEstadosRegistro.setValue(entity.getSisEstadoRegistro());
			txtSisNombre.setValue(entity.getSisNombre());
			txtSisCodigo.setValue(entity.getSisCodigo());

		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}

    public String action_modify() {
    	
    	List<SegSistema> segSistemas = new ArrayList<SegSistema>();
    	Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
    	
		try {

			if (txtSisNombre.getValue()==null||txtSisNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre del sistema no puede estar vacio");
			}
			if (somEstadosRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}
			if (txtSisDescripcion.getValue()==null||txtSisDescripcion.getValue().toString().equals("")==true ) {
				throw new Exception("La descripcion del sistema no puede estar vacia");
			}

			Long sisCodigo = Long.parseLong(txtSisCodigo.getValue().toString());
			String sisNombre = txtSisNombre.getValue().toString();
			String sisEstadoRegistro = somEstadosRegistro.getValue().toString();
			String sisDescripcion = txtSisDescripcion.getValue().toString();

			BusinessDelegatorView.updateSegSistema(sisCodigo, sisDescripcion,sisEstadoRegistro, sisNombre, usuSession);
			
			segSistemas = BusinessDelegatorView.getSegSistema();
			data = BusinessDelegatorView.getSegSistemas(segSistemas);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}

        return "";
    }


    public String action_modifyWitDTO(Long sisCodigo, String sisDescripcion,
        String sisEstadoRegistro, String sisNombre, Long usuCodigo_SegUsuario)
        throws Exception {
        try {
            BusinessDelegatorView.updateSegSistema(sisCodigo, sisDescripcion,
                sisEstadoRegistro, sisNombre, usuCodigo_SegUsuario);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SegSistemaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtSisDescripcion() {
        return txtSisDescripcion;
    }

    public void setTxtSisDescripcion(InputText txtSisDescripcion) {
        this.txtSisDescripcion = txtSisDescripcion;
    }


    public InputText getTxtSisNombre() {
        return txtSisNombre;
    }

    public void setTxtSisNombre(InputText txtSisNombre) {
        this.txtSisNombre = txtSisNombre;
    }

    public InputText getTxtUsuCodigo_SegUsuario() {
        return txtUsuCodigo_SegUsuario;
    }

    public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
        this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
    }

    public InputText getTxtSisCodigo() {
        return txtSisCodigo;
    }

    public void setTxtSisCodigo(InputText txtSisCodigo) {
        this.txtSisCodigo = txtSisCodigo;
    }

    public List<SegSistemaDTO> getData() {
    	 try {
             if (data == null) {
                 data = BusinessDelegatorView.getDataSegSistema();
             }
         } catch (Exception e) {
             e.printStackTrace();
         }

        return data;
    }

    public void setData(List<SegSistemaDTO> segSistemaDTO) {
        this.data = segSistemaDTO;
    }

    public SegSistemaDTO getSelectedSegSistema() {
        return selectedSegSistema;
    }

    public void setSelectedSegSistema(SegSistemaDTO segSistema) {
        this.selectedSegSistema = segSistema;
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
}
