package com.vortexbird.seguridad.presentation.backingBeans;

import org.apache.log4j.Logger;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.SegSucursalDTO;
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
public class SegSucursalView {
    private InputText txtSucCodigoInterno;
    private SelectOneMenu txtSucEstadoRegistro;
    private InputText txtSucNombre;
    private InputText txtCiaCodigo_SegCompania;
    private InputText txtUsuCodigo_SegUsuario;
    private InputText txtSucCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SegSucursalDTO> data;
    private SegSucursalDTO selectedSegSucursal;
	private SelectOneMenu somCompanias;
	private SelectItem[] lasCompanias;
	private Logger logger = Logger.getLogger(SegSucursalView.class);

    public SegSucursalView() {
        super();
    }
    
	public String action_clear() {
		txtSucCodigoInterno.setValue(null);
		txtSucCodigoInterno.setDisabled(false);
		txtSucEstadoRegistro.setValue("-1");
		somCompanias.setValue("-1");
		txtSucNombre.setValue(null);
		txtSucNombre.setDisabled(false);
		btnSave.setDisabled(false);
		btnModify.setDisabled(true);
		txtSucCodigo.setValue(null);
		btnClear.setDisabled(false);
		return "";
	}

	public String action_selected(){

		action_clear();
		btnModify.setDisabled(false);
		btnSave.setDisabled(true);

		FacesContext context = FacesContext.getCurrentInstance();  
		Map requestMap = context.getExternalContext().getRequestParameterMap();

		String sucCodigo = (String)requestMap.get("sucCodigo");

		try {

			SegSucursal entity = BusinessDelegatorView.getSegSucursal(Long.parseLong(sucCodigo));
			txtSucCodigoInterno.setValue(entity.getSucCodigoInterno());
			txtSucEstadoRegistro.setValue(entity.getSucEstadoRegistro());
			txtSucNombre.setValue(entity.getSucNombre());
			txtSucCodigo.setValue(entity.getSucCodigo());
			if (entity.getSegCompania()!=null) {
				somCompanias.setValue(entity.getSegCompania().getCiaCodigo());
			}else {
				somCompanias.setValue(1L);
			}
			btnSave.setDisabled(true);
			btnModify.setDisabled(false);

		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}

    public String action_save() {
    	try {
			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
    		
			if (txtSucCodigoInterno.getValue()==null||txtSucCodigoInterno.getValue().toString().equals("")==true ) {
				throw new Exception("El codigo interno de la sucursal no puede estar vacia");
			}

			if (txtSucEstadoRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}
			
			if (txtSucNombre.getValue()==null||txtSucNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre de la compa�ia no puede estar vacio");
			}
			
			if (somCompanias.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar una Compa�ia valida");
			}

			String codigoInterno = txtSucCodigoInterno.getValue().toString();
			String estadoRegistro = txtSucEstadoRegistro.getValue().toString();
			String nombre = txtSucNombre.getValue().toString();
			Long codCompania = Long.parseLong(somCompanias.getValue().toString());

			BusinessDelegatorView.saveSegSucursal(
					null, codigoInterno,
					estadoRegistro,
					nombre,
					codCompania,
					usuSession);

			String estadoRegistroNombre = "";
			
			SegSucursalDTO sucuDTO = new SegSucursalDTO();
			sucuDTO.setSucCodigo(null);
			sucuDTO.setSucCodigoInterno(codigoInterno);

			if (estadoRegistro.equals("0")==true) {
				estadoRegistroNombre = "Inactivo";
			}else {
				estadoRegistroNombre = "Activo";
			}
			sucuDTO.setSucEstadoRegistro(estadoRegistro);
			sucuDTO.setSucEstadoRegistroNombre(estadoRegistroNombre);
			sucuDTO.setSucNombre(nombre);
			sucuDTO.setCiaCodigo_SegCompania(codCompania.toString());

			SegCompania compania = BusinessDelegatorView.getSegCompania(codCompania);
			sucuDTO.setCiaNombre_SegCompania(compania.getCiaNombre());

			data.add(sucuDTO);
			
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}

        return "";
    }

    public String action_delete() {
        try {
            BusinessDelegatorView.deleteSegSucursal(FacesUtils.checkLong(
                    txtSucCodigo));
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
    	
    	List<SegSucursal> lasSucursales = new ArrayList<SegSucursal>();
    	
		Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

		try {

			if (txtSucCodigoInterno.getValue()==null||txtSucCodigoInterno.getValue().toString().equals("")==true ) {
				throw new Exception("El codigo interno de la sucursal no puede estar vacia");
			}

			if (txtSucEstadoRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			if (txtSucNombre.getValue()==null||txtSucNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre de la compa�ia no puede estar vacio");
			}
			
			if (somCompanias.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar una Compa�ia valida");
			}

			Long codigoSucursal = Long.parseLong(txtSucCodigo.getValue().toString());
			String codigoInterno = txtSucCodigoInterno.getValue().toString();
			String estadoRegistro = txtSucEstadoRegistro.getValue().toString();
			String nombre = txtSucNombre.getValue().toString();
			Long codCompania = Long.parseLong(somCompanias.getValue().toString());

			BusinessDelegatorView.updateSegSucursal(
					codigoSucursal, codigoInterno,
					estadoRegistro,
					nombre,
					codCompania,
					usuSession);
			
			lasSucursales = BusinessDelegatorView.getSegSucursal();
			data = BusinessDelegatorView.getSegSucursalesDTO(lasSucursales);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}

        return "";
    }

    public String actionDeleteDataTableEditable() {
        try {
            if (txtSucCodigo == null) {
                txtSucCodigo = new InputText();
            }

            txtSucCodigo.setValue(selectedSegSucursal.getSucCodigo());

            action_delete();
            data.remove(selectedSegSucursal);
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long sucCodigo, String sucCodigoInterno,
        String sucEstadoRegistro, String sucNombre, Long ciaCodigo_SegCompania,
        Long usuCodigo_SegUsuario) throws Exception {
        try {
            BusinessDelegatorView.updateSegSucursal(sucCodigo,
                sucCodigoInterno, sucEstadoRegistro, sucNombre,
                ciaCodigo_SegCompania, usuCodigo_SegUsuario);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SegSucursalView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtSucCodigoInterno() {
        return txtSucCodigoInterno;
    }

    public void setTxtSucCodigoInterno(InputText txtSucCodigoInterno) {
        this.txtSucCodigoInterno = txtSucCodigoInterno;
    }

    public SelectOneMenu getTxtSucEstadoRegistro() {
		return txtSucEstadoRegistro;
	}


	public void setTxtSucEstadoRegistro(SelectOneMenu txtSucEstadoRegistro) {
		this.txtSucEstadoRegistro = txtSucEstadoRegistro;
	}


	public InputText getTxtSucNombre() {
        return txtSucNombre;
    }

    public void setTxtSucNombre(InputText txtSucNombre) {
        this.txtSucNombre = txtSucNombre;
    }

    public InputText getTxtCiaCodigo_SegCompania() {
        return txtCiaCodigo_SegCompania;
    }

    public void setTxtCiaCodigo_SegCompania(InputText txtCiaCodigo_SegCompania) {
        this.txtCiaCodigo_SegCompania = txtCiaCodigo_SegCompania;
    }

    public InputText getTxtUsuCodigo_SegUsuario() {
        return txtUsuCodigo_SegUsuario;
    }

    public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
        this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
    }

    public InputText getTxtSucCodigo() {
        return txtSucCodigo;
    }

    public void setTxtSucCodigo(InputText txtSucCodigo) {
        this.txtSucCodigo = txtSucCodigo;
    }

    public List<SegSucursalDTO> getData() {
        try {
            if (data == null) {
                data = BusinessDelegatorView.getDataSegSucursal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SegSucursalDTO> segSucursalDTO) {
        this.data = segSucursalDTO;
    }

    public SegSucursalDTO getSelectedSegSucursal() {
        return selectedSegSucursal;
    }

    public void setSelectedSegSucursal(SegSucursalDTO segSucursal) {
        this.selectedSegSucursal = segSucursal;
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


	public SelectOneMenu getSomCompanias() {
		return somCompanias;
	}


	public void setSomCompanias(SelectOneMenu somCompanias) {
		this.somCompanias = somCompanias;
	}

	public SelectItem[] getLasCompanias() {
		try {
			List<SegCompania> listCompanias=BusinessDelegatorView.getSegCompania();
			lasCompanias=new SelectItem[listCompanias.size()];
			int i=0;
			for (SegCompania segCompania : listCompanias) {
				lasCompanias[i]= new SelectItem(segCompania.getCiaCodigo(),segCompania.getCiaNombre().toString());
				i++;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return lasCompanias;
	}


	public void setLasCompanias(SelectItem[] lasCompanias) {
		this.lasCompanias = lasCompanias;
	}

	public Logger getLogger() {
		return logger;
	}


	public void setLogger(Logger logger) {
		this.logger = logger;
	}
    
}
