package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyPersonaDTO;
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
public class PsyPersonaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyPersonaView.class);
    private InputText txtEmail;
    private InputText txtEstadoRegistro;
    private InputText txtNombre;
    private InputText txtTelefono;
    private InputText txtEmprCodigo_PsyEmpresa;
    private InputText txtPersCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyPersonaDTO> data;
    private PsyPersonaDTO selectedPsyPersona;
    private PsyPersona entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyPersonaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyPersonaDTO psyPersonaDTO = (PsyPersonaDTO) e.getObject();

            if (txtEmail == null) {
                txtEmail = new InputText();
            }

            txtEmail.setValue(psyPersonaDTO.getEmail());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyPersonaDTO.getEstadoRegistro());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(psyPersonaDTO.getNombre());

            if (txtTelefono == null) {
                txtTelefono = new InputText();
            }

            txtTelefono.setValue(psyPersonaDTO.getTelefono());

            if (txtEmprCodigo_PsyEmpresa == null) {
                txtEmprCodigo_PsyEmpresa = new InputText();
            }

            txtEmprCodigo_PsyEmpresa.setValue(psyPersonaDTO.getEmprCodigo_PsyEmpresa());

            if (txtPersCodigo == null) {
                txtPersCodigo = new InputText();
            }

            txtPersCodigo.setValue(psyPersonaDTO.getPersCodigo());

            Long persCodigo = FacesUtils.checkLong(txtPersCodigo);
            entity = businessDelegatorView.getPsyPersona(persCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyPersona = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyPersona = null;

        if (txtEmail != null) {
            txtEmail.setValue(null);
            txtEmail.setDisabled(true);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtTelefono != null) {
            txtTelefono.setValue(null);
            txtTelefono.setDisabled(true);
        }

        if (txtEmprCodigo_PsyEmpresa != null) {
            txtEmprCodigo_PsyEmpresa.setValue(null);
            txtEmprCodigo_PsyEmpresa.setDisabled(true);
        }

        if (txtPersCodigo != null) {
            txtPersCodigo.setValue(null);
            txtPersCodigo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Long persCodigo = FacesUtils.checkLong(txtPersCodigo);
            entity = (persCodigo != null)
                ? businessDelegatorView.getPsyPersona(persCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEmail.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setDisabled(false);
            txtTelefono.setDisabled(false);
            txtEmprCodigo_PsyEmpresa.setDisabled(false);
            txtPersCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEmail.setValue(entity.getEmail());
            txtEmail.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtTelefono.setValue(entity.getTelefono());
            txtTelefono.setDisabled(false);
            txtEmprCodigo_PsyEmpresa.setValue(entity.getPsyEmpresa()
                                                    .getEmprCodigo());
            txtEmprCodigo_PsyEmpresa.setDisabled(false);
            txtPersCodigo.setValue(entity.getPersCodigo());
            txtPersCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyPersona = (PsyPersonaDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedPsyPersona"));
        txtEmail.setValue(selectedPsyPersona.getEmail());
        txtEmail.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyPersona.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtNombre.setValue(selectedPsyPersona.getNombre());
        txtNombre.setDisabled(false);
        txtTelefono.setValue(selectedPsyPersona.getTelefono());
        txtTelefono.setDisabled(false);
        txtEmprCodigo_PsyEmpresa.setValue(selectedPsyPersona.getEmprCodigo_PsyEmpresa());
        txtEmprCodigo_PsyEmpresa.setDisabled(false);
        txtPersCodigo.setValue(selectedPsyPersona.getPersCodigo());
        txtPersCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyPersona == null) && (entity == null)) {
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
            entity = new PsyPersona();

            Long persCodigo = FacesUtils.checkLong(txtPersCodigo);

            entity.setEmail(FacesUtils.checkString(txtEmail));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPersCodigo(persCodigo);
            entity.setTelefono(FacesUtils.checkLong(txtTelefono));
            entity.setPsyEmpresa((FacesUtils.checkLong(txtEmprCodigo_PsyEmpresa) != null)
                ? businessDelegatorView.getPsyEmpresa(FacesUtils.checkLong(
                        txtEmprCodigo_PsyEmpresa)) : null);
            businessDelegatorView.savePsyPersona(entity);
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
                Long persCodigo = new Long(selectedPsyPersona.getPersCodigo());
                entity = businessDelegatorView.getPsyPersona(persCodigo);
            }

            entity.setEmail(FacesUtils.checkString(txtEmail));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setTelefono(FacesUtils.checkLong(txtTelefono));
            entity.setPsyEmpresa((FacesUtils.checkLong(txtEmprCodigo_PsyEmpresa) != null)
                ? businessDelegatorView.getPsyEmpresa(FacesUtils.checkLong(
                        txtEmprCodigo_PsyEmpresa)) : null);
            businessDelegatorView.updatePsyPersona(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyPersona = (PsyPersonaDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedPsyPersona"));

            Long persCodigo = new Long(selectedPsyPersona.getPersCodigo());
            entity = businessDelegatorView.getPsyPersona(persCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long persCodigo = FacesUtils.checkLong(txtPersCodigo);
            entity = businessDelegatorView.getPsyPersona(persCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyPersona(entity);
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
            selectedPsyPersona = (PsyPersonaDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedPsyPersona"));

            Long persCodigo = new Long(selectedPsyPersona.getPersCodigo());
            entity = businessDelegatorView.getPsyPersona(persCodigo);
            businessDelegatorView.deletePsyPersona(entity);
            data.remove(selectedPsyPersona);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String email, String estadoRegistro,
        String nombre, Long persCodigo, Long telefono,
        Long emprCodigo_PsyEmpresa) throws Exception {
        try {
            entity.setEmail(FacesUtils.checkString(email));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setTelefono(FacesUtils.checkLong(telefono));
            businessDelegatorView.updatePsyPersona(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyPersonaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(InputText txtEmail) {
        this.txtEmail = txtEmail;
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

    public InputText getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(InputText txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    public InputText getTxtEmprCodigo_PsyEmpresa() {
        return txtEmprCodigo_PsyEmpresa;
    }

    public void setTxtEmprCodigo_PsyEmpresa(InputText txtEmprCodigo_PsyEmpresa) {
        this.txtEmprCodigo_PsyEmpresa = txtEmprCodigo_PsyEmpresa;
    }

    public InputText getTxtPersCodigo() {
        return txtPersCodigo;
    }

    public void setTxtPersCodigo(InputText txtPersCodigo) {
        this.txtPersCodigo = txtPersCodigo;
    }

    public List<PsyPersonaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyPersona(FacesUtils.getEmpresaIntoSession());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyPersonaDTO> psyPersonaDTO) {
        this.data = psyPersonaDTO;
    }

    public PsyPersonaDTO getSelectedPsyPersona() {
        return selectedPsyPersona;
    }

    public void setSelectedPsyPersona(PsyPersonaDTO psyPersona) {
        this.selectedPsyPersona = psyPersona;
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
