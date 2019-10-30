package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyAsuntoAmbientalDTO;
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
public class PsyAsuntoAmbientalView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyAsuntoAmbientalView.class);
    private static final String ESTADO_REGISTRO_ACTIVO ="A";
    private static final String ESTADO_REGISTRO_INACTIVO = "I";
    private InputText txtDescripcion;
    private InputText txtEstadoRegistro;
    private InputText txtNombre;
    private InputText txtAsamCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyAsuntoAmbientalDTO> data;
    private PsyAsuntoAmbientalDTO selectedPsyAsuntoAmbiental;
    private PsyAsuntoAmbiental entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyAsuntoAmbientalView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyAsuntoAmbientalDTO psyAsuntoAmbientalDTO = (PsyAsuntoAmbientalDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(psyAsuntoAmbientalDTO.getDescripcion());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyAsuntoAmbientalDTO.getEstadoRegistro());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(psyAsuntoAmbientalDTO.getNombre());

            if (txtAsamCodigo == null) {
                txtAsamCodigo = new InputText();
            }

            txtAsamCodigo.setValue(psyAsuntoAmbientalDTO.getAsamCodigo());

            Long asamCodigo = FacesUtils.checkLong(txtAsamCodigo);
            entity = businessDelegatorView.getPsyAsuntoAmbiental(asamCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyAsuntoAmbiental = null;
        txtEstadoRegistro.setValue("Activo");
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyAsuntoAmbiental = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
        }

        if (txtAsamCodigo != null) {
            txtAsamCodigo.setValue(null);
            txtAsamCodigo.setDisabled(false);
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
            Long asamCodigo = FacesUtils.checkLong(txtAsamCodigo);
            entity = (asamCodigo != null)
                ? businessDelegatorView.getPsyAsuntoAmbiental(asamCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setDisabled(false);
            txtAsamCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtAsamCodigo.setValue(entity.getAsamCodigo());
            txtAsamCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyAsuntoAmbiental = (PsyAsuntoAmbientalDTO) (evt.getComponent()
                                                                 .getAttributes()
                                                                 .get("selectedPsyAsuntoAmbiental"));
        txtDescripcion.setValue(selectedPsyAsuntoAmbiental.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyAsuntoAmbiental.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(true);
        txtNombre.setValue(selectedPsyAsuntoAmbiental.getNombre());
        txtNombre.setDisabled(false);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyAsuntoAmbiental == null) && (entity == null)) {
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
            entity = new PsyAsuntoAmbiental();

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.savePsyAsuntoAmbiental(entity);
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
                Long asamCodigo = new Long(selectedPsyAsuntoAmbiental.getAsamCodigo());
                entity = businessDelegatorView.getPsyAsuntoAmbiental(asamCodigo);
            }

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.updatePsyAsuntoAmbiental(entity);
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
            selectedPsyAsuntoAmbiental = (PsyAsuntoAmbientalDTO) (evt.getComponent()
                                                                     .getAttributes()
                                                                     .get("selectedPsyAsuntoAmbiental"));

            Long asamCodigo = new Long(selectedPsyAsuntoAmbiental.getAsamCodigo());
            entity = businessDelegatorView.getPsyAsuntoAmbiental(asamCodigo);
            action_delete();
            data=null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long asamCodigo = FacesUtils.checkLong(txtAsamCodigo);
            entity = businessDelegatorView.getPsyAsuntoAmbiental(asamCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyAsuntoAmbiental(entity);
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
            selectedPsyAsuntoAmbiental = (PsyAsuntoAmbientalDTO) (evt.getComponent()
                                                                     .getAttributes()
                                                                     .get("selectedPsyAsuntoAmbiental"));

            Long asamCodigo = new Long(selectedPsyAsuntoAmbiental.getAsamCodigo());
            entity = businessDelegatorView.getPsyAsuntoAmbiental(asamCodigo);
            businessDelegatorView.deletePsyAsuntoAmbiental(entity);
            data.remove(selectedPsyAsuntoAmbiental);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long asamCodigo, String descripcion,
        String estadoRegistro, String nombre) throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updatePsyAsuntoAmbiental(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyAsuntoAmbientalView").requestRender();
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

    public InputText getTxtAsamCodigo() {
        return txtAsamCodigo;
    }

    public void setTxtAsamCodigo(InputText txtAsamCodigo) {
        this.txtAsamCodigo = txtAsamCodigo;
    }

    public List<PsyAsuntoAmbientalDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyAsuntoAmbiental();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyAsuntoAmbientalDTO> psyAsuntoAmbientalDTO) {
        this.data = psyAsuntoAmbientalDTO;
    }

    public PsyAsuntoAmbientalDTO getSelectedPsyAsuntoAmbiental() {
        return selectedPsyAsuntoAmbiental;
    }

    public void setSelectedPsyAsuntoAmbiental(
        PsyAsuntoAmbientalDTO psyAsuntoAmbiental) {
        this.selectedPsyAsuntoAmbiental = psyAsuntoAmbiental;
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
}
