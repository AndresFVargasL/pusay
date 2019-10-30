package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyEvaluarIndicadoresDTO;
import com.vortexbird.pusay.modelo.dto.PsyObjetivoAmbientalDTO;
import com.vortexbird.pusay.presentation.businessDelegate.*;
import com.vortexbird.pusay.presentation.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
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

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import java.util.TimeZone;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyObjetivoAmbientalView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyObjetivoAmbientalView.class);
    private static final String ESTADO_REGISTRO_ACTIVO ="A";
    private static final String ESTADO_REGISTRO_INACTIVO = "I";
    private InputText txtDescripcion;
    private InputText txtEstadoRegistro;
    private InputText txtCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyObjetivoAmbientalDTO> data;
    private List<PsyObjetivoAmbientalDTO> dataEvaluado;
    private List<PsyEvaluarIndicadoresDTO> dataIndicadoresObam;
    private PsyObjetivoAmbientalDTO selectedPsyObjetivoAmbiental;
    private PsyObjetivoAmbientalDTO objetivoSeleccionado;
    private PsyObjetivoAmbiental entity;
    private boolean showDialog;
    private boolean showDialogDetalle;
    private boolean pintar;
    private boolean pintarMensajeError;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyObjetivoAmbientalView() {
        super();
    }
    
    public PsyEmpresa getEmpresaIntoSession(){
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);
		return (PsyEmpresa) httpSession.getAttribute("empresa");		
	}

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyObjetivoAmbientalDTO psyObjetivoAmbientalDTO = (PsyObjetivoAmbientalDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(psyObjetivoAmbientalDTO.getDescripcion());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyObjetivoAmbientalDTO.getEstadoRegistro());

            if (txtCodigo == null) {
                txtCodigo = new InputText();
            }

            txtCodigo.setValue(psyObjetivoAmbientalDTO.getCodigo());

            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = businessDelegatorView.getPsyObjetivoAmbiental(codigo);

            action_modify();
        } catch (Exception ex) {
        }
    }
    
    @PostConstruct
    public void psyObjetivoAmbientalView() {
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

    public String action_new() {
        action_clear();
        txtEstadoRegistro.setValue("Activo");
        selectedPsyObjetivoAmbiental = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyObjetivoAmbiental = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtCodigo != null) {
            txtCodigo.setValue(null);
            txtCodigo.setDisabled(false);
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
            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = (codigo != null)
                ? businessDelegatorView.getPsyObjetivoAmbiental(codigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtCodigo.setValue(entity.getCodigo());
            txtCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyObjetivoAmbiental = (PsyObjetivoAmbientalDTO) (evt.getComponent()
                                                                     .getAttributes()
                                                                     .get("selectedPsyObjetivoAmbiental"));
        txtDescripcion.setValue(selectedPsyObjetivoAmbiental.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyObjetivoAmbiental.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyObjetivoAmbiental == null) && (entity == null)) {
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
            entity = new PsyObjetivoAmbiental();

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            businessDelegatorView.savePsyObjetivoAmbiental(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
            data=null;
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long codigo = new Long(selectedPsyObjetivoAmbiental.getCodigo());
                entity = businessDelegatorView.getPsyObjetivoAmbiental(codigo);
            }

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            businessDelegatorView.updatePsyObjetivoAmbiental(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
            action_clear();
            data=null;
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyObjetivoAmbiental = (PsyObjetivoAmbientalDTO) (evt.getComponent()
                                                                         .getAttributes()
                                                                         .get("selectedPsyObjetivoAmbiental"));

            Long codigo = new Long(selectedPsyObjetivoAmbiental.getCodigo());
            entity = businessDelegatorView.getPsyObjetivoAmbiental(codigo);
            action_delete();
            data=null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = businessDelegatorView.getPsyObjetivoAmbiental(codigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }
    
    public String action_mostrar_detalle() {
      	setShowDialogDetalle(true);
        return "";
    }
    
    public String action_cerrar_detalle() {
    	dataIndicadoresObam=null;
    	objetivoSeleccionado=null;
      	setShowDialogDetalle(false);
        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyObjetivoAmbiental(entity);
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
            selectedPsyObjetivoAmbiental = (PsyObjetivoAmbientalDTO) (evt.getComponent()
                                                                         .getAttributes()
                                                                         .get("selectedPsyObjetivoAmbiental"));

            Long codigo = new Long(selectedPsyObjetivoAmbiental.getCodigo());
            entity = businessDelegatorView.getPsyObjetivoAmbiental(codigo);
            businessDelegatorView.deletePsyObjetivoAmbiental(entity);
            data.remove(selectedPsyObjetivoAmbiental);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigo, String descripcion,
        String estadoRegistro) throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            businessDelegatorView.updatePsyObjetivoAmbiental(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyObjetivoAmbientalView").requestRender();
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

    public InputText getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(InputText txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public List<PsyObjetivoAmbientalDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyObjetivoAmbiental();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyObjetivoAmbientalDTO> psyObjetivoAmbientalDTO) {
        this.data = psyObjetivoAmbientalDTO;
    }

    public PsyObjetivoAmbientalDTO getSelectedPsyObjetivoAmbiental() {
        return selectedPsyObjetivoAmbiental;
    }

    public void setSelectedPsyObjetivoAmbiental(
        PsyObjetivoAmbientalDTO psyObjetivoAmbiental) {
        this.selectedPsyObjetivoAmbiental = psyObjetivoAmbiental;
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

	public List<PsyObjetivoAmbientalDTO> getDataEvaluado() {
		try {
            if (dataEvaluado == null) {
            	dataEvaluado = businessDelegatorView.getDataPsyObjetivoAmbientalEvaluado(getEmpresaIntoSession());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return dataEvaluado;
	}

	public void setDataEvaluado(List<PsyObjetivoAmbientalDTO> dataEvaluado) {
		this.dataEvaluado = dataEvaluado;
	}

	public List<PsyEvaluarIndicadoresDTO> getDataIndicadoresObam() {
		
		try {
            if (dataIndicadoresObam == null && objetivoSeleccionado!=null) {
            	Long codigo = objetivoSeleccionado.getCodigo();
            	List<PsyPlanEstrategicoAmbiental> planActivo = businessDelegatorView.getPEA(getEmpresaIntoSession());
            	dataIndicadoresObam = businessDelegatorView.getDataPsyIndicadorObam(FacesUtils.getEmpresaIntoSession(), codigo, planActivo.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return dataIndicadoresObam;
	}

	public void setDataIndicadoresObam(
			List<PsyEvaluarIndicadoresDTO> dataIndicadoresObam) {
		this.dataIndicadoresObam = dataIndicadoresObam;
	}

	public PsyObjetivoAmbientalDTO getObjetivoSeleccionado() {
		return objetivoSeleccionado;
	}

	public void setObjetivoSeleccionado(PsyObjetivoAmbientalDTO objetivoSeleccionado) {
		this.objetivoSeleccionado = objetivoSeleccionado;
	}

	public boolean isShowDialogDetalle() {
		return showDialogDetalle;
	}

	public void setShowDialogDetalle(boolean showDialogDetalle) {
		this.showDialogDetalle = showDialogDetalle;
	}
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {

		try {

			Document pdf = (Document) document;
			pdf.open();
			pdf.setPageSize(PageSize.LETTER);
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String logo = servletContext.getRealPath("") + "images" + File.separator + "logoPusay.png";
			Image imageCenter = Image.getInstance(logo);
			imageCenter.setAlignment(Image.MIDDLE);
			pdf.add(imageCenter);
			pdf.add(new Paragraph("Empresa: "+FacesUtils.getEmpresaIntoSession().getNombre()+"\n"+
					"Plan Estratégico: "+businessDelegatorView.getPlanEstrategicoActivoByPEA(FacesUtils.getEmpresaIntoSession()).getNombre()+"\n"+
					"Nombre del Documento: Indicadores por Objetivo Ambiental \n"+
					"Fecha: "+ new Date().toString()+"\n\n"));

		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error generando el pdf. El error fue : "+e.getMessage());
		}		



	}
	
}
