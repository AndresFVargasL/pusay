package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyParametroDTO;
import com.vortexbird.pusay.presentation.businessDelegate.*;
import com.vortexbird.pusay.presentation.utilities.FacesUtils;
import com.vortexbird.pusay.utilities.*;

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
public class PsyParametroView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyParametroView.class);
    private InputText txtEstadoRegistro;
    private InputText txtLlave;
    private InputText txtValor;
    private InputText txtParamCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyParametroDTO> data;
    private PsyParametroDTO selectedPsyParametro;
    private PsyParametro entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyParametroView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyParametroDTO psyParametroDTO = (PsyParametroDTO) e.getObject();

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyParametroDTO.getEstadoRegistro());

            if (txtLlave == null) {
                txtLlave = new InputText();
            }

            txtLlave.setValue(psyParametroDTO.getLlave());

            if (txtValor == null) {
                txtValor = new InputText();
            }

            txtValor.setValue(psyParametroDTO.getValor());

            if (txtParamCodigo == null) {
                txtParamCodigo = new InputText();
            }

            txtParamCodigo.setValue(psyParametroDTO.getParamCodigo());

            Long paramCodigo = FacesUtils.checkLong(txtParamCodigo);
            entity = businessDelegatorView.getPsyParametro(paramCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyParametro = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyParametro = null;

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtLlave != null) {
            txtLlave.setValue(null);
            txtLlave.setDisabled(true);
        }

        if (txtValor != null) {
            txtValor.setValue(null);
            txtValor.setDisabled(true);
        }

        if (txtParamCodigo != null) {
            txtParamCodigo.setValue(null);
            txtParamCodigo.setDisabled(false);
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
            Long paramCodigo = FacesUtils.checkLong(txtParamCodigo);
            entity = (paramCodigo != null)
                ? businessDelegatorView.getPsyParametro(paramCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoRegistro.setDisabled(false);
            txtLlave.setDisabled(false);
            txtValor.setDisabled(false);
            txtParamCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtLlave.setValue(entity.getLlave());
            txtLlave.setDisabled(false);
            txtValor.setValue(entity.getValor());
            txtValor.setDisabled(false);
            txtParamCodigo.setValue(entity.getParamCodigo());
            txtParamCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyParametro = (PsyParametroDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedPsyParametro"));
        txtEstadoRegistro.setValue(selectedPsyParametro.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtLlave.setValue(selectedPsyParametro.getLlave());
        txtLlave.setDisabled(false);
        txtValor.setValue(selectedPsyParametro.getValor());
        txtValor.setDisabled(false);
        txtParamCodigo.setValue(selectedPsyParametro.getParamCodigo());
        txtParamCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyParametro == null) && (entity == null)) {
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
            entity = new PsyParametro();

            Long paramCodigo = FacesUtils.checkLong(txtParamCodigo);

            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setLlave(FacesUtils.checkString(txtLlave));
            entity.setParamCodigo(paramCodigo);
            entity.setValor(FacesUtils.checkString(txtValor));
            businessDelegatorView.savePsyParametro(entity);
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
                Long paramCodigo = new Long(selectedPsyParametro.getParamCodigo());
                entity = businessDelegatorView.getPsyParametro(paramCodigo);
            }

            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setLlave(FacesUtils.checkString(txtLlave));
            entity.setValor(FacesUtils.checkString(txtValor));
            businessDelegatorView.updatePsyParametro(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyParametro = (PsyParametroDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedPsyParametro"));

            Long paramCodigo = new Long(selectedPsyParametro.getParamCodigo());
            entity = businessDelegatorView.getPsyParametro(paramCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long paramCodigo = FacesUtils.checkLong(txtParamCodigo);
            entity = businessDelegatorView.getPsyParametro(paramCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyParametro(entity);
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
            selectedPsyParametro = (PsyParametroDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedPsyParametro"));

            Long paramCodigo = new Long(selectedPsyParametro.getParamCodigo());
            entity = businessDelegatorView.getPsyParametro(paramCodigo);
            businessDelegatorView.deletePsyParametro(entity);
            data.remove(selectedPsyParametro);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estadoRegistro, String llave,
        Long paramCodigo, String valor) throws Exception {
        try {
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setLlave(FacesUtils.checkString(llave));
            entity.setValor(FacesUtils.checkString(valor));
            businessDelegatorView.updatePsyParametro(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyParametroView").requestRender();
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

    public InputText getTxtLlave() {
        return txtLlave;
    }

    public void setTxtLlave(InputText txtLlave) {
        this.txtLlave = txtLlave;
    }

    public InputText getTxtValor() {
        return txtValor;
    }

    public void setTxtValor(InputText txtValor) {
        this.txtValor = txtValor;
    }

    public InputText getTxtParamCodigo() {
        return txtParamCodigo;
    }

    public void setTxtParamCodigo(InputText txtParamCodigo) {
        this.txtParamCodigo = txtParamCodigo;
    }

    public List<PsyParametroDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyParametro();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyParametroDTO> psyParametroDTO) {
        this.data = psyParametroDTO;
    }

    public PsyParametroDTO getSelectedPsyParametro() {
        return selectedPsyParametro;
    }

    public void setSelectedPsyParametro(PsyParametroDTO psyParametro) {
        this.selectedPsyParametro = psyParametro;
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
