package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyDetalleEstrategiasDTO;
import com.vortexbird.pusay.modelo.dto.PsyEstrategiaAmbientalDTO;
import com.vortexbird.pusay.presentation.businessDelegate.*;
import com.vortexbird.pusay.presentation.utilities.*;
import com.vortexbird.pusay.utilities.EnviarCorreo;

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

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

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
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyEstrategiaAmbientalView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyEstrategiaAmbientalView.class);
    private static final String ESTADO_REGISTRO_ACTIVO ="A";
    private static final String ESTADO_REGISTRO_INACTIVO = "I";
    private InputText txtDescripcion;
    private InputText txtEstadoRegistro;
    private InputText txtNombre;
    private InputText txtEsamCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyEstrategiaAmbientalDTO> data;
    private PsyEstrategiaAmbientalDTO selectedPsyEstrategiaAmbiental;
    private PsyEstrategiaAmbiental entity;
    private boolean showDialog;
    private List<PsyDetalleEstrategiasDTO> laEstrategia;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyEstrategiaAmbientalView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyEstrategiaAmbientalDTO psyEstrategiaAmbientalDTO = (PsyEstrategiaAmbientalDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(psyEstrategiaAmbientalDTO.getDescripcion());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyEstrategiaAmbientalDTO.getEstadoRegistro());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(psyEstrategiaAmbientalDTO.getNombre());

            if (txtEsamCodigo == null) {
                txtEsamCodigo = new InputText();
            }

            txtEsamCodigo.setValue(psyEstrategiaAmbientalDTO.getEsamCodigo());

            Long esamCodigo = FacesUtils.checkLong(txtEsamCodigo);
            entity = businessDelegatorView.getPsyEstrategiaAmbiental(esamCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        txtEstadoRegistro.setValue("Activo");
        selectedPsyEstrategiaAmbiental = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyEstrategiaAmbiental = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
        }

        if (txtEsamCodigo != null) {
            txtEsamCodigo.setValue(null);
            txtEsamCodigo.setDisabled(false);
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
            Long esamCodigo = FacesUtils.checkLong(txtEsamCodigo);
            entity = (esamCodigo != null)
                ? businessDelegatorView.getPsyEstrategiaAmbiental(esamCodigo)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setDisabled(false);
            txtEsamCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtEsamCodigo.setValue(entity.getEsamCodigo());
            txtEsamCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyEstrategiaAmbiental = (PsyEstrategiaAmbientalDTO) (evt.getComponent()
                                                                         .getAttributes()
                                                                         .get("selectedPsyEstrategiaAmbiental"));
        txtDescripcion.setValue(selectedPsyEstrategiaAmbiental.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyEstrategiaAmbiental.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(true);
        txtNombre.setValue(selectedPsyEstrategiaAmbiental.getNombre());
        txtNombre.setDisabled(false);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyEstrategiaAmbiental == null) && (entity == null)) {
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
            entity = new PsyEstrategiaAmbiental();

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.savePsyEstrategiaAmbiental(entity);
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
                Long esamCodigo = new Long(selectedPsyEstrategiaAmbiental.getEsamCodigo());
                entity = businessDelegatorView.getPsyEstrategiaAmbiental(esamCodigo);
            }

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.updatePsyEstrategiaAmbiental(entity);
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
            selectedPsyEstrategiaAmbiental = (PsyEstrategiaAmbientalDTO) (evt.getComponent()
                                                                             .getAttributes()
                                                                             .get("selectedPsyEstrategiaAmbiental"));

            Long esamCodigo = new Long(selectedPsyEstrategiaAmbiental.getEsamCodigo());
            entity = businessDelegatorView.getPsyEstrategiaAmbiental(esamCodigo);
            action_delete();
            data=null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long esamCodigo = FacesUtils.checkLong(txtEsamCodigo);
            entity = businessDelegatorView.getPsyEstrategiaAmbiental(esamCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyEstrategiaAmbiental(entity);
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
            selectedPsyEstrategiaAmbiental = (PsyEstrategiaAmbientalDTO) (evt.getComponent()
                                                                             .getAttributes()
                                                                             .get("selectedPsyEstrategiaAmbiental"));

            Long esamCodigo = new Long(selectedPsyEstrategiaAmbiental.getEsamCodigo());
            entity = businessDelegatorView.getPsyEstrategiaAmbiental(esamCodigo);
            businessDelegatorView.deletePsyEstrategiaAmbiental(entity);
            data.remove(selectedPsyEstrategiaAmbiental);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcion, Long esamCodigo,
        String estadoRegistro, String nombre) throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updatePsyEstrategiaAmbiental(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyEstrategiaAmbientalView").requestRender();
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

    public InputText getTxtEsamCodigo() {
        return txtEsamCodigo;
    }

    public void setTxtEsamCodigo(InputText txtEsamCodigo) {
        this.txtEsamCodigo = txtEsamCodigo;
    }

    public List<PsyEstrategiaAmbientalDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyEstrategiaAmbiental();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(
        List<PsyEstrategiaAmbientalDTO> psyEstrategiaAmbientalDTO) {
        this.data = psyEstrategiaAmbientalDTO;
    }

    public PsyEstrategiaAmbientalDTO getSelectedPsyEstrategiaAmbiental() {
        return selectedPsyEstrategiaAmbiental;
    }

    public void setSelectedPsyEstrategiaAmbiental(
        PsyEstrategiaAmbientalDTO psyEstrategiaAmbiental) {
        this.selectedPsyEstrategiaAmbiental = psyEstrategiaAmbiental;
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

	public List<PsyDetalleEstrategiasDTO> getLaEstrategia() {
		
		try {
			laEstrategia = businessDelegatorView
					.consultaEstrategiasParaPrograma(FacesUtils.getEmpresaIntoSession());
		} catch (Exception e) {
			log.error("#### Error cargando las estrategias para la pantalla final el error fue :"+e.getMessage()+"####");
		}
		
		return laEstrategia;
	}

	public void setLaEstrategia(List<PsyDetalleEstrategiasDTO> laEstrategia) {
		this.laEstrategia = laEstrategia;
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
	        					  "Nombre del Documento: Estrategias \n"+
	        					  "Fecha: "+ new Date().toString()+"\n\n"));
					
			} catch (Exception e) {
				FacesUtils.addErrorMessage("Error generando el pdf. El error fue : "+e.getMessage());
			}		
	        
	        
	        
	}
}
