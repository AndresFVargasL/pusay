package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyEmpresaDTO;
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
public class PsyEmpresaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyEmpresaView.class);
    private InputText txtDireccionPrincipal;
    private InputText txtEstadoRegistro;
    private InputText txtNit;
    private InputText txtNombre;
    private InputText txtTelefonoPrincipal;
    private InputText txtCiudCodigo_PsyCiudad;
    private InputText txtPersCodigo_PsyPersona;
    private InputText txtEmprCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyEmpresaDTO> data;
    private PsyEmpresaDTO selectedPsyEmpresa;
    private PsyEmpresa entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyEmpresaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyEmpresaDTO psyEmpresaDTO = (PsyEmpresaDTO) e.getObject();

            if (txtDireccionPrincipal == null) {
                txtDireccionPrincipal = new InputText();
            }

            txtDireccionPrincipal.setValue(psyEmpresaDTO.getDireccionPrincipal());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyEmpresaDTO.getEstadoRegistro());

            if (txtNit == null) {
                txtNit = new InputText();
            }

            txtNit.setValue(psyEmpresaDTO.getNit());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(psyEmpresaDTO.getNombre());

            if (txtTelefonoPrincipal == null) {
                txtTelefonoPrincipal = new InputText();
            }

            txtTelefonoPrincipal.setValue(psyEmpresaDTO.getTelefonoPrincipal());

            if (txtCiudCodigo_PsyCiudad == null) {
                txtCiudCodigo_PsyCiudad = new InputText();
            }

            txtCiudCodigo_PsyCiudad.setValue(psyEmpresaDTO.getCiudCodigo_PsyCiudad());

            if (txtPersCodigo_PsyPersona == null) {
                txtPersCodigo_PsyPersona = new InputText();
            }

            txtPersCodigo_PsyPersona.setValue(psyEmpresaDTO.getPersCodigo_PsyPersona());

            if (txtEmprCodigo == null) {
                txtEmprCodigo = new InputText();
            }

            txtEmprCodigo.setValue(psyEmpresaDTO.getEmprCodigo());

            Long emprCodigo = FacesUtils.checkLong(txtEmprCodigo);
            entity = businessDelegatorView.getPsyEmpresa(emprCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyEmpresa = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyEmpresa = null;

        if (txtDireccionPrincipal != null) {
            txtDireccionPrincipal.setValue(null);
            txtDireccionPrincipal.setDisabled(true);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtNit != null) {
            txtNit.setValue(null);
            txtNit.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtTelefonoPrincipal != null) {
            txtTelefonoPrincipal.setValue(null);
            txtTelefonoPrincipal.setDisabled(true);
        }

        if (txtCiudCodigo_PsyCiudad != null) {
            txtCiudCodigo_PsyCiudad.setValue(null);
            txtCiudCodigo_PsyCiudad.setDisabled(true);
        }

        if (txtPersCodigo_PsyPersona != null) {
            txtPersCodigo_PsyPersona.setValue(null);
            txtPersCodigo_PsyPersona.setDisabled(true);
        }

        if (txtEmprCodigo != null) {
            txtEmprCodigo.setValue(null);
            txtEmprCodigo.setDisabled(false);
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
            Long emprCodigo = FacesUtils.checkLong(txtEmprCodigo);
            entity = (emprCodigo != null)
                ? businessDelegatorView.getPsyEmpresa(emprCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDireccionPrincipal.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtNit.setDisabled(false);
            txtNombre.setDisabled(false);
            txtTelefonoPrincipal.setDisabled(false);
            txtCiudCodigo_PsyCiudad.setDisabled(false);
            txtPersCodigo_PsyPersona.setDisabled(false);
            txtEmprCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDireccionPrincipal.setValue(entity.getDireccionPrincipal());
            txtDireccionPrincipal.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtNit.setValue(entity.getNit());
            txtNit.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtTelefonoPrincipal.setValue(entity.getTelefonoPrincipal());
            txtTelefonoPrincipal.setDisabled(false);
            txtCiudCodigo_PsyCiudad.setValue(entity.getPsyCiudad()
                                                   .getCiudCodigo());
            txtCiudCodigo_PsyCiudad.setDisabled(false);
            txtPersCodigo_PsyPersona.setValue(entity.getPsyPersona()
                                                    .getPersCodigo());
            txtPersCodigo_PsyPersona.setDisabled(false);
            txtEmprCodigo.setValue(entity.getEmprCodigo());
            txtEmprCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyEmpresa = (PsyEmpresaDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedPsyEmpresa"));
        txtDireccionPrincipal.setValue(selectedPsyEmpresa.getDireccionPrincipal());
        txtDireccionPrincipal.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyEmpresa.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtNit.setValue(selectedPsyEmpresa.getNit());
        txtNit.setDisabled(false);
        txtNombre.setValue(selectedPsyEmpresa.getNombre());
        txtNombre.setDisabled(false);
        txtTelefonoPrincipal.setValue(selectedPsyEmpresa.getTelefonoPrincipal());
        txtTelefonoPrincipal.setDisabled(false);
        txtCiudCodigo_PsyCiudad.setValue(selectedPsyEmpresa.getCiudCodigo_PsyCiudad());
        txtCiudCodigo_PsyCiudad.setDisabled(false);
        txtPersCodigo_PsyPersona.setValue(selectedPsyEmpresa.getPersCodigo_PsyPersona());
        txtPersCodigo_PsyPersona.setDisabled(false);
        txtEmprCodigo.setValue(selectedPsyEmpresa.getEmprCodigo());
        txtEmprCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyEmpresa == null) && (entity == null)) {
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
            entity = new PsyEmpresa();

            Long emprCodigo = FacesUtils.checkLong(txtEmprCodigo);

            entity.setDireccionPrincipal(FacesUtils.checkString(
                    txtDireccionPrincipal));
            entity.setEmprCodigo(emprCodigo);
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setNit(FacesUtils.checkString(txtNit));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setTelefonoPrincipal(FacesUtils.checkLong(
                    txtTelefonoPrincipal));
            entity.setPsyCiudad((FacesUtils.checkLong(txtCiudCodigo_PsyCiudad) != null)
                ? businessDelegatorView.getPsyCiudad(FacesUtils.checkLong(
                        txtCiudCodigo_PsyCiudad)) : null);
            entity.setPsyPersona((FacesUtils.checkLong(txtPersCodigo_PsyPersona) != null)
                ? businessDelegatorView.getPsyPersona(FacesUtils.checkLong(
                        txtPersCodigo_PsyPersona)) : null);
            businessDelegatorView.savePsyEmpresa(entity);
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
                Long emprCodigo = new Long(selectedPsyEmpresa.getEmprCodigo());
                entity = businessDelegatorView.getPsyEmpresa(emprCodigo);
            }

            entity.setDireccionPrincipal(FacesUtils.checkString(
                    txtDireccionPrincipal));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setNit(FacesUtils.checkString(txtNit));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setTelefonoPrincipal(FacesUtils.checkLong(
                    txtTelefonoPrincipal));
            entity.setPsyCiudad((FacesUtils.checkLong(txtCiudCodigo_PsyCiudad) != null)
                ? businessDelegatorView.getPsyCiudad(FacesUtils.checkLong(
                        txtCiudCodigo_PsyCiudad)) : null);
            entity.setPsyPersona((FacesUtils.checkLong(txtPersCodigo_PsyPersona) != null)
                ? businessDelegatorView.getPsyPersona(FacesUtils.checkLong(
                        txtPersCodigo_PsyPersona)) : null);
            businessDelegatorView.updatePsyEmpresa(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyEmpresa = (PsyEmpresaDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedPsyEmpresa"));

            Long emprCodigo = new Long(selectedPsyEmpresa.getEmprCodigo());
            entity = businessDelegatorView.getPsyEmpresa(emprCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long emprCodigo = FacesUtils.checkLong(txtEmprCodigo);
            entity = businessDelegatorView.getPsyEmpresa(emprCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyEmpresa(entity);
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
            selectedPsyEmpresa = (PsyEmpresaDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedPsyEmpresa"));

            Long emprCodigo = new Long(selectedPsyEmpresa.getEmprCodigo());
            entity = businessDelegatorView.getPsyEmpresa(emprCodigo);
            businessDelegatorView.deletePsyEmpresa(entity);
            data.remove(selectedPsyEmpresa);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String direccionPrincipal,
        Long emprCodigo, String estadoRegistro, String nit, String nombre,
        Long telefonoPrincipal, Long ciudCodigo_PsyCiudad,
        Long persCodigo_PsyPersona) throws Exception {
        try {
            entity.setDireccionPrincipal(FacesUtils.checkString(
                    direccionPrincipal));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setNit(FacesUtils.checkString(nit));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setTelefonoPrincipal(FacesUtils.checkLong(telefonoPrincipal));
            businessDelegatorView.updatePsyEmpresa(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyEmpresaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDireccionPrincipal() {
        return txtDireccionPrincipal;
    }

    public void setTxtDireccionPrincipal(InputText txtDireccionPrincipal) {
        this.txtDireccionPrincipal = txtDireccionPrincipal;
    }

    public InputText getTxtEstadoRegistro() {
        return txtEstadoRegistro;
    }

    public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
        this.txtEstadoRegistro = txtEstadoRegistro;
    }

    public InputText getTxtNit() {
        return txtNit;
    }

    public void setTxtNit(InputText txtNit) {
        this.txtNit = txtNit;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtTelefonoPrincipal() {
        return txtTelefonoPrincipal;
    }

    public void setTxtTelefonoPrincipal(InputText txtTelefonoPrincipal) {
        this.txtTelefonoPrincipal = txtTelefonoPrincipal;
    }

    public InputText getTxtCiudCodigo_PsyCiudad() {
        return txtCiudCodigo_PsyCiudad;
    }

    public void setTxtCiudCodigo_PsyCiudad(InputText txtCiudCodigo_PsyCiudad) {
        this.txtCiudCodigo_PsyCiudad = txtCiudCodigo_PsyCiudad;
    }

    public InputText getTxtPersCodigo_PsyPersona() {
        return txtPersCodigo_PsyPersona;
    }

    public void setTxtPersCodigo_PsyPersona(InputText txtPersCodigo_PsyPersona) {
        this.txtPersCodigo_PsyPersona = txtPersCodigo_PsyPersona;
    }

    public InputText getTxtEmprCodigo() {
        return txtEmprCodigo;
    }

    public void setTxtEmprCodigo(InputText txtEmprCodigo) {
        this.txtEmprCodigo = txtEmprCodigo;
    }

    public List<PsyEmpresaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyEmpresa();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyEmpresaDTO> psyEmpresaDTO) {
        this.data = psyEmpresaDTO;
    }

    public PsyEmpresaDTO getSelectedPsyEmpresa() {
        return selectedPsyEmpresa;
    }

    public void setSelectedPsyEmpresa(PsyEmpresaDTO psyEmpresa) {
        this.selectedPsyEmpresa = psyEmpresa;
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
