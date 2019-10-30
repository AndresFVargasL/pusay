package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyImpactoAmbientalDTO;
import com.vortexbird.pusay.presentation.businessDelegate.*;
import com.vortexbird.pusay.presentation.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyImpactoAmbientalView implements Serializable {
    private static final long serialVersionUID = 1L; 
    private static final Logger log = LoggerFactory.getLogger(PsyImpactoAmbientalView.class);
    private static final String ESTADO_REGISTRO_ACTIVO ="A";
    private static final String ESTADO_REGISTRO_INACTIVO = "I";
    private InputText txtDescripcion;
    private InputText txtEstadoRegistro;
    private InputText txtNombre;
    private OutputLabel txtDimension;
    private OutputLabel txtTemas;
    private OutputLabel txtSubTemas;
    private OutputLabel txtIndicadores;
    private InputText txtImamCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyImpactoAmbientalDTO> data;
    private List<PsyImpactoAmbientalDTO> dataGestion;
    private PsyImpactoAmbientalDTO selectedPsyImpactoAmbiental;
    private PsyImpactoAmbientalDTO selectedPsyImpacto;
    private PsyImpactoAmbiental entity;
    private boolean showDialog;
    private boolean pintar;
    private boolean pintarMensajeError;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyImpactoAmbientalView() {
        super();
    }
    
    public PsyEmpresa getEmpresaIntoSession(){
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);
		return (PsyEmpresa) httpSession.getAttribute("empresa");		
	}
    
    @PostConstruct
    public void psyImpactoAmbientalView() {
       try {
		PsyPlanEstrategico planEstrategico = businessDelegatorView.getPlanEstrategicoActivoByPEA(getEmpresaIntoSession());
		if(planEstrategico==null){
			pintar=false;
			pintarMensajeError=true;
		}else{
			pintar=true;
			pintarMensajeError=false;
		}
	} catch (Exception e) {
		FacesUtils.addErrorMessage(e.getMessage());
	}
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyImpactoAmbientalDTO psyImpactoAmbientalDTO = (PsyImpactoAmbientalDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(psyImpactoAmbientalDTO.getDescripcion());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyImpactoAmbientalDTO.getEstadoRegistro());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(psyImpactoAmbientalDTO.getNombre());

            if (txtImamCodigo == null) {
                txtImamCodigo = new InputText();
            }

            txtImamCodigo.setValue(psyImpactoAmbientalDTO.getImamCodigo());

            Long imamCodigo = FacesUtils.checkLong(txtImamCodigo);
            entity = businessDelegatorView.getPsyImpactoAmbiental(imamCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyImpactoAmbiental = null;
        txtEstadoRegistro.setValue("Activo");
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyImpactoAmbiental = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
        }

        if (txtImamCodigo != null) {
            txtImamCodigo.setValue(null);
            txtImamCodigo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(false);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Long imamCodigo = FacesUtils.checkLong(txtImamCodigo);
            entity = (imamCodigo != null)
                ? businessDelegatorView.getPsyImpactoAmbiental(imamCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setDisabled(false);
            txtImamCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtImamCodigo.setValue(entity.getImamCodigo());
            txtImamCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyImpactoAmbiental = (PsyImpactoAmbientalDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedPsyImpactoAmbiental"));
        txtDescripcion.setValue(selectedPsyImpactoAmbiental.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyImpactoAmbiental.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(true);
        txtNombre.setValue(selectedPsyImpactoAmbiental.getNombre());
        txtNombre.setDisabled(false);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }
    
    public String action_clear_resume() {
        selectedPsyImpacto = null;

        if (txtDimension != null) {
        	txtDimension.setValue(null);
        }

        if (txtTemas != null) {
        	txtTemas.setValue(null);
        }

        if (txtIndicadores != null) {
        	txtIndicadores.setValue(null);
        }

        if (txtSubTemas != null) {
        	txtSubTemas.setValue(null);
        }

        return "";
    }
    
    public String action_resume(ActionEvent evt) {
    	action_clear_resume();
    	selectedPsyImpacto = (PsyImpactoAmbientalDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedPsyImpacto"));
    	try {
    	Long codigo = new Long(selectedPsyImpacto.getImamCodigo());
    	
        txtDimension.setValue(selectedPsyImpacto.getNombre());
        
        txtTemas.setValue(businessDelegatorView.countTemasPorImpacto(codigo));
        
        txtSubTemas.setValue(businessDelegatorView.countSubTemasPorImpacto(codigo));
        
        txtIndicadores.setValue(businessDelegatorView.countIndicadoresPorImpacto(codigo));
        
        setShowDialog(true);
    	} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyImpactoAmbiental == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new PsyImpactoAmbiental();

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.savePsyImpactoAmbiental(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
            data=null;
            dataGestion=null;
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long imamCodigo = new Long(selectedPsyImpactoAmbiental.getImamCodigo());
                entity = businessDelegatorView.getPsyImpactoAmbiental(imamCodigo);
            }

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.updatePsyImpactoAmbiental(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
            action_clear();
            data=null;
            dataGestion=null;
        } catch (Exception e) {
            data = null;
            dataGestion=null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyImpactoAmbiental = (PsyImpactoAmbientalDTO) (evt.getComponent()
                                                                       .getAttributes()
                                                                       .get("selectedPsyImpactoAmbiental"));

            Long imamCodigo = new Long(selectedPsyImpactoAmbiental.getImamCodigo());
            entity = businessDelegatorView.getPsyImpactoAmbiental(imamCodigo);
            action_delete();
            data=null;
            dataGestion=null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long imamCodigo = FacesUtils.checkLong(txtImamCodigo);
            entity = businessDelegatorView.getPsyImpactoAmbiental(imamCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyImpactoAmbiental(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedPsyImpactoAmbiental = (PsyImpactoAmbientalDTO) (evt.getComponent()
                                                                       .getAttributes()
                                                                       .get("selectedPsyImpactoAmbiental"));

            Long imamCodigo = new Long(selectedPsyImpactoAmbiental.getImamCodigo());
            entity = businessDelegatorView.getPsyImpactoAmbiental(imamCodigo);
            businessDelegatorView.deletePsyImpactoAmbiental(entity);
            data.remove(selectedPsyImpactoAmbiental);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcion,
        String estadoRegistro, Long imamCodigo, String nombre)
        throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updatePsyImpactoAmbiental(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyImpactoAmbientalView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(InputText txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public InputText getTxtEstadoRegistro() {
        return txtEstadoRegistro;
    }

    public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
        this.txtEstadoRegistro = txtEstadoRegistro;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtImamCodigo() {
        return txtImamCodigo;
    }

    public void setTxtImamCodigo(InputText txtImamCodigo) {
        this.txtImamCodigo = txtImamCodigo;
    }

    public List<PsyImpactoAmbientalDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyImpactoAmbiental(getEmpresaIntoSession());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
    
    public List<PsyImpactoAmbientalDTO> getDataGestion() {
        try {
            if (dataGestion == null) {
            	dataGestion = businessDelegatorView.getDataPsyImpactoAmbientalGestion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataGestion;
    }

    public void setData(List<PsyImpactoAmbientalDTO> psyImpactoAmbientalDTO) {
        this.data = psyImpactoAmbientalDTO;
    }

    public PsyImpactoAmbientalDTO getSelectedPsyImpactoAmbiental() {
        return selectedPsyImpactoAmbiental;
    }

    public void setSelectedPsyImpactoAmbiental(
        PsyImpactoAmbientalDTO psyImpactoAmbiental) {
        this.selectedPsyImpactoAmbiental = psyImpactoAmbiental;
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

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

	public boolean isPintar() {
		return pintar;
	}

	public void setPintar(boolean pintar) {
		this.pintar = pintar;
	}

	public boolean isPintarMensajeError() {
		return pintarMensajeError;
	}

	public void setPintarMensajeError(boolean pintarMensajeError) {
		this.pintarMensajeError = pintarMensajeError;
	}

	

	public PsyImpactoAmbientalDTO getSelectedPsyImpacto() {
		return selectedPsyImpacto;
	}

	public void setSelectedPsyImpacto(PsyImpactoAmbientalDTO selectedPsyImpacto) {
		this.selectedPsyImpacto = selectedPsyImpacto;
	}

	public OutputLabel getTxtDimension() {
		return txtDimension;
	}

	public void setTxtDimension(OutputLabel txtDimension) {
		this.txtDimension = txtDimension;
	}

	public OutputLabel getTxtTemas() {
		return txtTemas;
	}

	public void setTxtTemas(OutputLabel txtTemas) {
		this.txtTemas = txtTemas;
	}

	public OutputLabel getTxtSubTemas() {
		return txtSubTemas;
	}

	public void setTxtSubTemas(OutputLabel txtSubTemas) {
		this.txtSubTemas = txtSubTemas;
	}

	public OutputLabel getTxtIndicadores() {
		return txtIndicadores;
	}

	public void setTxtIndicadores(OutputLabel txtIndicadores) {
		this.txtIndicadores = txtIndicadores;
	}

	public void setDataGestion(List<PsyImpactoAmbientalDTO> dataGestion) {
		this.dataGestion = dataGestion;
	}
}
