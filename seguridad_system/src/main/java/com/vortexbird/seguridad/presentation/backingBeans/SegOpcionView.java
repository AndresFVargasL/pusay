package com.vortexbird.seguridad.presentation.backingBeans;

import org.apache.log4j.Logger;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.SegOpcionDTO;
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
public class SegOpcionView {
    private InputText txtOpcDescripcion;
    private SelectOneMenu somEstadosRegistro;
    private SelectOneMenu somGrupoOpcionPadre;
    private SelectItem[] losGrupoOpcionPadre;
    private InputText txtOpcLlaveAcceso;
    private InputText txtOpcNombre;
    private InputText txtGruCodigo_SegGrupoOpcion;
    private InputText txtUsuCodigo_SegUsuario;
    private InputText txtOpcCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SegOpcionDTO> data;
    private SegOpcionDTO selectedSegOpcion;
    private Logger logger = Logger.getLogger(SegOpcionView.class);
    
    public SegOpcionView() {
        super();
    }


	public String action_clear() {
		txtOpcDescripcion.setValue(null);
		txtOpcDescripcion.setDisabled(false);
		somEstadosRegistro.setValue("-1");
		somGrupoOpcionPadre.setValue("-1");
		txtOpcLlaveAcceso.setValue(null);
		txtOpcLlaveAcceso.setDisabled(false);
		txtOpcNombre.setValue(null);
		txtOpcCodigo.setValue(null);
		txtOpcNombre.setDisabled(false);
		btnSave.setDisabled(false);
		btnModify.setDisabled(true);
		btnClear.setDisabled(false);
		return "";
	}
	
	public String action_selected(){

		action_clear();
		btnModify.setDisabled(false);
		btnSave.setDisabled(true);

		FacesContext context = FacesContext.getCurrentInstance();  
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();

		String opcCodigo = (String)requestMap.get("opcCodigo");

		try {

			SegOpcion entity = BusinessDelegatorView.getSegOpcion(Long.parseLong(opcCodigo));
			txtOpcDescripcion.setValue(entity.getOpcDescripcion());
			somEstadosRegistro.setValue(entity.getOpcEstadoRegistro());
			txtOpcLlaveAcceso.setValue(entity.getOpcLlaveAcceso());
			txtOpcNombre.setValue(entity.getOpcNombre());
			txtOpcCodigo.setValue(entity.getOpcCodigo());
			
			if (entity.getSegGrupoOpcion()!=null) {
				somGrupoOpcionPadre.setValue(entity.getSegGrupoOpcion().getGruCodigo());
			}else {
				somGrupoOpcionPadre.setValue(1L);
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
			String sistema = FacesUtils2.getSessionParameter("sistema").toString();

			if (txtOpcNombre.getValue()==null||txtOpcNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre de la opcion no puede estar vacio");
			}

			if (somEstadosRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			if (txtOpcLlaveAcceso.getValue()==null||txtOpcLlaveAcceso.getValue().toString().equals("")==true ) {
				throw new Exception("La llave de acceso de la opcion no puede estar vacia");
			}
			
			if (txtOpcDescripcion.getValue()==null||txtOpcDescripcion.getValue().toString().equals("")==true ) {
				throw new Exception("La descripcion de la Opcion no puede estar vacia");
			}
			
			if (somGrupoOpcionPadre.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un grupo Opcion valido");
			}
			
			String descripcion = txtOpcDescripcion.getValue().toString();
			String estadoRegistro = somEstadosRegistro.getValue().toString();
			String llaveAcceso = txtOpcLlaveAcceso.getValue().toString();
			String nombre = txtOpcNombre.getValue().toString();
			Long codigoGrupo = Long.parseLong(somGrupoOpcionPadre.getValue().toString());
			
			BusinessDelegatorView.saveSegOpcion(
					null, 
					descripcion,
					estadoRegistro,
					llaveAcceso,
					nombre,
					codigoGrupo,
					usuSession);
			
			if (usuSession == 0) {
				data = BusinessDelegatorView.getDataSegOpcion();	
			}else {
				data = BusinessDelegatorView.getOpcionesPorSistema(usuSession, sistema);	
			}
			
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
        } catch (Exception e) {
        	FacesUtils.addErrorMessage(e.getMessage());
        	logger.error(e.getMessage(), e);
        }

        return "";
    }

    public String action_modify() {
    	
    	List<SegOpcion> lasOpciones = new ArrayList<SegOpcion>();
    	
    	Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
		
		try {

			if (txtOpcNombre.getValue()==null||txtOpcNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre de la opcion no puede estar vacio");
			}
			
			if (somEstadosRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			if (txtOpcLlaveAcceso.getValue()==null||txtOpcLlaveAcceso.getValue().toString().equals("")==true ) {
				throw new Exception("La llave de acceso de la opcion no puede estar vacia");
			}

			if (txtOpcDescripcion.getValue()==null||txtOpcDescripcion.getValue().toString().equals("")==true ) {
				throw new Exception("La descripcion de la Opcion no puede estar vacia");
			}
			
			if (somGrupoOpcionPadre.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un grupo Opcion valido");
			}
			
			Long codigo = Long.parseLong(txtOpcCodigo.getValue().toString());
			String descripcion = txtOpcDescripcion.getValue().toString();
			String estadoRegistro = somEstadosRegistro.getValue().toString();
			String llaveAcceso = txtOpcLlaveAcceso.getValue().toString();
			String nombre = txtOpcNombre.getValue().toString();
			Long codigoGrupo = Long.parseLong(somGrupoOpcionPadre.getValue().toString());
			
			BusinessDelegatorView.updateSegOpcion(
					codigo, descripcion,
					estadoRegistro,
					llaveAcceso,
					nombre,
					codigoGrupo,
					usuSession);
			
//			lasOpciones = BusinessDelegatorView.getSegOpcion();
//			data = BusinessDelegatorView.getSegOpcionesDTO(lasOpciones);
			
			String sistema = FacesUtils2.getSessionParameter("sistema").toString();
        	
			if (usuSession == 0) {
				data = BusinessDelegatorView.getDataSegOpcion();	
			}else {
				data = BusinessDelegatorView.getOpcionesPorSistema(usuSession, sistema);	
			}
			
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}
        return "";
    }

    public String action_modifyWitDTO(Long opcCodigo, String opcDescripcion,
        String opcEstadoRegistro, String opcLlaveAcceso, String opcNombre,
        Long gruCodigo_SegGrupoOpcion, Long usuCodigo_SegUsuario)
        throws Exception {
        try {
            BusinessDelegatorView.updateSegOpcion(opcCodigo, opcDescripcion,
                opcEstadoRegistro, opcLlaveAcceso, opcNombre,
                gruCodigo_SegGrupoOpcion, usuCodigo_SegUsuario);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SegOpcionView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtOpcDescripcion() {
        return txtOpcDescripcion;
    }

    public void setTxtOpcDescripcion(InputText txtOpcDescripcion) {
        this.txtOpcDescripcion = txtOpcDescripcion;
    }

    public SelectOneMenu getSomEstadosRegistro() {
		return somEstadosRegistro;
	}

	public void setSomEstadosRegistro(SelectOneMenu somEstadosRegistro) {
		this.somEstadosRegistro = somEstadosRegistro;
	}


	public InputText getTxtOpcLlaveAcceso() {
        return txtOpcLlaveAcceso;
    }

    public void setTxtOpcLlaveAcceso(InputText txtOpcLlaveAcceso) {
        this.txtOpcLlaveAcceso = txtOpcLlaveAcceso;
    }

    public InputText getTxtOpcNombre() {
        return txtOpcNombre;
    }

    public void setTxtOpcNombre(InputText txtOpcNombre) {
        this.txtOpcNombre = txtOpcNombre;
    }

    public InputText getTxtGruCodigo_SegGrupoOpcion() {
        return txtGruCodigo_SegGrupoOpcion;
    }

    public void setTxtGruCodigo_SegGrupoOpcion(
        InputText txtGruCodigo_SegGrupoOpcion) {
        this.txtGruCodigo_SegGrupoOpcion = txtGruCodigo_SegGrupoOpcion;
    }

    public InputText getTxtUsuCodigo_SegUsuario() {
        return txtUsuCodigo_SegUsuario;
    }

    public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
        this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
    }

    public InputText getTxtOpcCodigo() {
        return txtOpcCodigo;
    }

    public void setTxtOpcCodigo(InputText txtOpcCodigo) {
        this.txtOpcCodigo = txtOpcCodigo;
    }

    public List<SegOpcionDTO> getData() {
    	try {
            if (data == null) {

            	Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
            	String sistema = FacesUtils2.getSessionParameter("sistema").toString();
            	
				if (usuSession == 0) {
					data = BusinessDelegatorView.getDataSegOpcion();	
				}else {
					data = BusinessDelegatorView.getOpcionesPorSistema(usuSession, sistema);	
				}
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public void setData(List<SegOpcionDTO> segOpcionDTO) {
        this.data = segOpcionDTO;
    }

    public SegOpcionDTO getSelectedSegOpcion() {
        return selectedSegOpcion;
    }

    public void setSelectedSegOpcion(SegOpcionDTO segOpcion) {
        this.selectedSegOpcion = segOpcion;
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


	public SelectOneMenu getSomGrupoOpcionPadre() {
		return somGrupoOpcionPadre;
	}


	public void setSomGrupoOpcionPadre(SelectOneMenu somGrupoOpcionPadre) {
		this.somGrupoOpcionPadre = somGrupoOpcionPadre;
	}


	public Logger getLogger() {
		return logger;
	}


	public void setLogger(Logger logger) {
		this.logger = logger;
	}


	public void setLosGrupoOpcionPadre(SelectItem[] losGrupoOpcionPadre) {
		this.losGrupoOpcionPadre = losGrupoOpcionPadre;
	}
    
}
