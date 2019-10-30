package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsySeguimientoTareaDTO;
import com.vortexbird.pusay.modelo.dto.PsyTareaDTO;
import com.vortexbird.pusay.presentation.businessDelegate.*;
import com.vortexbird.pusay.presentation.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
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


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsySeguimientoTareaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String ESTADO_REGISTRO_ACTIVO = "A";
    private static final Logger log = LoggerFactory.getLogger(PsySeguimientoTareaView.class);
    private InputTextarea txtDescripcion;
    private InputText txtEstadoRegistro;
    private InputText txtTareCodigo_PsyTarea;
    private InputText txtSetaCodigo;
    private Calendar txtFecha;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsySeguimientoTareaDTO> data;
    private PsySeguimientoTareaDTO selectedPsySeguimientoTarea;
    private PsyTareaDTO selectedTareaSeguimiento;
    private PsySeguimientoTarea entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsySeguimientoTareaView() {
        super();
    }
    
    public String showSeguimientoDialog(ActionEvent evt) {
    	selectedTareaSeguimiento = (PsyTareaDTO) (evt.getComponent()
              .getAttributes()
              .get("selectedSeguimiento"));
    	
        setShowDialog(true);
        data=null;
        return "";
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsySeguimientoTareaDTO psySeguimientoTareaDTO = (PsySeguimientoTareaDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputTextarea();
            }

            txtDescripcion.setValue(psySeguimientoTareaDTO.getDescripcion());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psySeguimientoTareaDTO.getEstadoRegistro());

            if (txtTareCodigo_PsyTarea == null) {
                txtTareCodigo_PsyTarea = new InputText();
            }

            txtTareCodigo_PsyTarea.setValue(psySeguimientoTareaDTO.getTareCodigo_PsyTarea());

            if (txtSetaCodigo == null) {
                txtSetaCodigo = new InputText();
            }

            txtSetaCodigo.setValue(psySeguimientoTareaDTO.getSetaCodigo());

            if (txtFecha == null) {
                txtFecha = new Calendar();
            }

            txtFecha.setValue(psySeguimientoTareaDTO.getFecha());

            Long setaCodigo = FacesUtils.checkLong(txtSetaCodigo);
            entity = businessDelegatorView.getPsySeguimientoTarea(setaCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsySeguimientoTarea = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsySeguimientoTarea = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            txtDescripcion.setDisabled(false);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtTareCodigo_PsyTarea != null) {
            txtTareCodigo_PsyTarea.setValue(null);
            txtTareCodigo_PsyTarea.setDisabled(true);
        }

        if (txtFecha != null) {
            txtFecha.setValue(null);
            txtFecha.setDisabled(false);
        }

        if (txtSetaCodigo != null) {
            txtSetaCodigo.setValue(null);
            txtSetaCodigo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(false);
        }

        return "";
    }

    public void listener_txtFecha() {
        Date inputDate = (Date) txtFecha.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Fecha Seleccionada " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long setaCodigo = FacesUtils.checkLong(txtSetaCodigo);
            entity = (setaCodigo != null)
                ? businessDelegatorView.getPsySeguimientoTarea(setaCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtTareCodigo_PsyTarea.setDisabled(false);
            txtFecha.setDisabled(false);
            txtSetaCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtFecha.setValue(entity.getFecha());
            txtFecha.setDisabled(false);
            txtTareCodigo_PsyTarea.setValue(entity.getPsyTarea().getTareCodigo());
            txtTareCodigo_PsyTarea.setDisabled(false);
            txtSetaCodigo.setValue(entity.getSetaCodigo());
            txtSetaCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsySeguimientoTarea = (PsySeguimientoTareaDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedPsySeguimientoTarea"));
        txtDescripcion.setValue(selectedPsySeguimientoTarea.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtFecha.setValue(selectedPsySeguimientoTarea.getFecha());
        txtFecha.setDisabled(false);
        btnSave.setDisabled(false);
        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsySeguimientoTarea == null) && (entity == null)) {
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
            entity = new PsySeguimientoTarea();            

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro(ESTADO_REGISTRO_ACTIVO);
            entity.setFecha(FacesUtils.checkDate(txtFecha));
            entity.setPsyTarea((selectedTareaSeguimiento!=null) ? 
            		businessDelegatorView.getPsyTarea(selectedTareaSeguimiento.getTareCodigo()) : null);
            businessDelegatorView.savePsySeguimientoTarea(entity);
            data=null;
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long setaCodigo = new Long(selectedPsySeguimientoTarea.getSetaCodigo());
                entity = businessDelegatorView.getPsySeguimientoTarea(setaCodigo);
            }

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro(ESTADO_REGISTRO_ACTIVO);
            entity.setFecha(FacesUtils.checkDate(txtFecha));
            entity.setPsyTarea((selectedTareaSeguimiento!=null) ? 
            		businessDelegatorView.getPsyTarea(selectedTareaSeguimiento.getTareCodigo()) : null);
            businessDelegatorView.updatePsySeguimientoTarea(entity);
            data=null;
            action_clear();
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
            
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsySeguimientoTarea = (PsySeguimientoTareaDTO) (evt.getComponent()
                                                                       .getAttributes()
                                                                       .get("selectedPsySeguimientoTarea"));

            Long setaCodigo = new Long(selectedPsySeguimientoTarea.getSetaCodigo());
            entity = businessDelegatorView.getPsySeguimientoTarea(setaCodigo);
            action_delete();
            data=null;
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long setaCodigo = FacesUtils.checkLong(txtSetaCodigo);
            entity = businessDelegatorView.getPsySeguimientoTarea(setaCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsySeguimientoTarea(entity);
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
            selectedPsySeguimientoTarea = (PsySeguimientoTareaDTO) (evt.getComponent()
                                                                       .getAttributes()
                                                                       .get("selectedPsySeguimientoTarea"));

            Long setaCodigo = new Long(selectedPsySeguimientoTarea.getSetaCodigo());
            entity = businessDelegatorView.getPsySeguimientoTarea(setaCodigo);
            businessDelegatorView.deletePsySeguimientoTarea(entity);
            data.remove(selectedPsySeguimientoTarea);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcion,
        String estadoRegistro, Date fecha, Long setaCodigo,
        Long tareCodigo_PsyTarea) throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFecha(FacesUtils.checkDate(fecha));
            businessDelegatorView.updatePsySeguimientoTarea(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsySeguimientoTareaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputTextarea getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(InputTextarea txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public InputText getTxtEstadoRegistro() {
        return txtEstadoRegistro;
    }

    public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
        this.txtEstadoRegistro = txtEstadoRegistro;
    }

    public InputText getTxtTareCodigo_PsyTarea() {
        return txtTareCodigo_PsyTarea;
    }

    public void setTxtTareCodigo_PsyTarea(InputText txtTareCodigo_PsyTarea) {
        this.txtTareCodigo_PsyTarea = txtTareCodigo_PsyTarea;
    }

    public Calendar getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(Calendar txtFecha) {
        this.txtFecha = txtFecha;
    }

    public InputText getTxtSetaCodigo() {
        return txtSetaCodigo;
    }

    public void setTxtSetaCodigo(InputText txtSetaCodigo) {
        this.txtSetaCodigo = txtSetaCodigo;
    }

    public List<PsySeguimientoTareaDTO> getData() {
        try {
            if (data == null) {
            	if(selectedTareaSeguimiento!=null){
                data = businessDelegatorView.getDataPsySeguimientoTareaByTareCodigo(selectedTareaSeguimiento.getTareCodigo());
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsySeguimientoTareaDTO> psySeguimientoTareaDTO) {
        this.data = psySeguimientoTareaDTO;
    }

    public PsySeguimientoTareaDTO getSelectedPsySeguimientoTarea() {
        return selectedPsySeguimientoTarea;
    }

    public void setSelectedPsySeguimientoTarea(
        PsySeguimientoTareaDTO psySeguimientoTarea) {
        this.selectedPsySeguimientoTarea = psySeguimientoTarea;
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

	public PsyTareaDTO getSelectedTareaSeguimiento() {
		return selectedTareaSeguimiento;
	}

	public void setSelectedTareaSeguimiento(PsyTareaDTO selectedTareaSeguimiento) {
		this.selectedTareaSeguimiento = selectedTareaSeguimiento;
	}
}
