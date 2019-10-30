package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyPaisDTO;
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
public class PsyPaisView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyPaisView.class);
    private static final String ESTADO_REGISTRO_ACTIVO ="A";
    private static final String ESTADO_REGISTRO_INACTIVO = "I";
    private InputText txtEstadoRegistro;
    private InputText txtNombre;
    private InputText txtPaisCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyPaisDTO> data;
    private PsyPaisDTO selectedPsyPais;
    private PsyPais entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyPaisView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyPaisDTO psyPaisDTO = (PsyPaisDTO) e.getObject();

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyPaisDTO.getEstadoRegistro());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(psyPaisDTO.getNombre());

            if (txtPaisCodigo == null) {
                txtPaisCodigo = new InputText();
            }

            txtPaisCodigo.setValue(psyPaisDTO.getPaisCodigo());

            Long paisCodigo = FacesUtils.checkLong(txtPaisCodigo);
            entity = businessDelegatorView.getPsyPais(paisCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        txtEstadoRegistro.setValue("Activo");
        selectedPsyPais = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyPais = null;

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
        }

        if (txtPaisCodigo != null) {
            txtPaisCodigo.setValue(null);
            txtPaisCodigo.setDisabled(false);
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
            Long paisCodigo = FacesUtils.checkLong(txtPaisCodigo);
            entity = (paisCodigo != null)
                ? businessDelegatorView.getPsyPais(paisCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setDisabled(false);
            txtPaisCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtPaisCodigo.setValue(entity.getPaisCodigo());
            txtPaisCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyPais = (PsyPaisDTO) (evt.getComponent().getAttributes()
                                           .get("selectedPsyPais"));
        txtEstadoRegistro.setValue(selectedPsyPais.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(true);
        txtNombre.setValue(selectedPsyPais.getNombre());
        txtNombre.setDisabled(false);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyPais == null) && (entity == null)) {
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
            entity = new PsyPais();

            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.savePsyPais(entity);
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
                Long paisCodigo = new Long(selectedPsyPais.getPaisCodigo());
                entity = businessDelegatorView.getPsyPais(paisCodigo);
            }

            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.updatePsyPais(entity);
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
            selectedPsyPais = (PsyPaisDTO) (evt.getComponent().getAttributes()
                                               .get("selectedPsyPais"));

            Long paisCodigo = new Long(selectedPsyPais.getPaisCodigo());
            entity = businessDelegatorView.getPsyPais(paisCodigo);
            action_delete();
            data=null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long paisCodigo = FacesUtils.checkLong(txtPaisCodigo);
            entity = businessDelegatorView.getPsyPais(paisCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyPais(entity);
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
            selectedPsyPais = (PsyPaisDTO) (evt.getComponent().getAttributes()
                                               .get("selectedPsyPais"));

            Long paisCodigo = new Long(selectedPsyPais.getPaisCodigo());
            entity = businessDelegatorView.getPsyPais(paisCodigo);
            businessDelegatorView.deletePsyPais(entity);
            data.remove(selectedPsyPais);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estadoRegistro, String nombre,
        Long paisCodigo) throws Exception {
        try {
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updatePsyPais(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyPaisView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
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

    public InputText getTxtPaisCodigo() {
        return txtPaisCodigo;
    }

    public void setTxtPaisCodigo(InputText txtPaisCodigo) {
        this.txtPaisCodigo = txtPaisCodigo;
    }

    public List<PsyPaisDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyPais();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyPaisDTO> psyPaisDTO) {
        this.data = psyPaisDTO;
    }

    public PsyPaisDTO getSelectedPsyPais() {
        return selectedPsyPais;
    }

    public void setSelectedPsyPais(PsyPaisDTO psyPais) {
        this.selectedPsyPais = psyPais;
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
