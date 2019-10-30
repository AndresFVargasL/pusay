package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyMonedaDTO;
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
public class PsyMonedaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyMonedaView.class);
    private static final String ESTADO_REGISTRO_ACTIVO ="A";
    private static final String ESTADO_REGISTRO_INACTIVO = "I";
    private InputText txtAbreviatura;
    private InputText txtEstadoRegistro;
    private InputText txtNombre;
    private InputText txtMoneCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyMonedaDTO> data;
    private PsyMonedaDTO selectedPsyMoneda;
    private PsyMoneda entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyMonedaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyMonedaDTO psyMonedaDTO = (PsyMonedaDTO) e.getObject();

            if (txtAbreviatura == null) {
                txtAbreviatura = new InputText();
            }

            txtAbreviatura.setValue(psyMonedaDTO.getAbreviatura());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyMonedaDTO.getEstadoRegistro());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(psyMonedaDTO.getNombre());

            if (txtMoneCodigo == null) {
                txtMoneCodigo = new InputText();
            }

            txtMoneCodigo.setValue(psyMonedaDTO.getMoneCodigo());

            Long moneCodigo = FacesUtils.checkLong(txtMoneCodigo);
            entity = businessDelegatorView.getPsyMoneda(moneCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        txtEstadoRegistro.setValue("Activo");
        selectedPsyMoneda = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyMoneda = null;

        if (txtAbreviatura != null) {
            txtAbreviatura.setValue(null);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
        }

        if (txtMoneCodigo != null) {
            txtMoneCodigo.setValue(null);
            txtMoneCodigo.setDisabled(false);
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
            Long moneCodigo = FacesUtils.checkLong(txtMoneCodigo);
            entity = (moneCodigo != null)
                ? businessDelegatorView.getPsyMoneda(moneCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtAbreviatura.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setDisabled(false);
            txtMoneCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtAbreviatura.setValue(entity.getAbreviatura());
            txtAbreviatura.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtMoneCodigo.setValue(entity.getMoneCodigo());
            txtMoneCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyMoneda = (PsyMonedaDTO) (evt.getComponent().getAttributes()
                                               .get("selectedPsyMoneda"));
        txtAbreviatura.setValue(selectedPsyMoneda.getAbreviatura());
        txtAbreviatura.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyMoneda.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(true);
        txtNombre.setValue(selectedPsyMoneda.getNombre());
        txtNombre.setDisabled(false);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyMoneda == null) && (entity == null)) {
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
            entity = new PsyMoneda();

            entity.setAbreviatura(FacesUtils.checkString(txtAbreviatura));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.savePsyMoneda(entity);
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
                Long moneCodigo = new Long(selectedPsyMoneda.getMoneCodigo());
                entity = businessDelegatorView.getPsyMoneda(moneCodigo);
            }

            entity.setAbreviatura(FacesUtils.checkString(txtAbreviatura));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.updatePsyMoneda(entity);
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
            selectedPsyMoneda = (PsyMonedaDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedPsyMoneda"));

            Long moneCodigo = new Long(selectedPsyMoneda.getMoneCodigo());
            entity = businessDelegatorView.getPsyMoneda(moneCodigo);
            action_delete();
            data=null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long moneCodigo = FacesUtils.checkLong(txtMoneCodigo);
            entity = businessDelegatorView.getPsyMoneda(moneCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyMoneda(entity);
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
            selectedPsyMoneda = (PsyMonedaDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedPsyMoneda"));

            Long moneCodigo = new Long(selectedPsyMoneda.getMoneCodigo());
            entity = businessDelegatorView.getPsyMoneda(moneCodigo);
            businessDelegatorView.deletePsyMoneda(entity);
            data.remove(selectedPsyMoneda);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String abreviatura,
        String estadoRegistro, Long moneCodigo, String nombre)
        throws Exception {
        try {
            entity.setAbreviatura(FacesUtils.checkString(abreviatura));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updatePsyMoneda(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyMonedaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtAbreviatura() {
        return txtAbreviatura;
    }

    public void setTxtAbreviatura(InputText txtAbreviatura) {
        this.txtAbreviatura = txtAbreviatura;
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

    public InputText getTxtMoneCodigo() {
        return txtMoneCodigo;
    }

    public void setTxtMoneCodigo(InputText txtMoneCodigo) {
        this.txtMoneCodigo = txtMoneCodigo;
    }

    public List<PsyMonedaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyMoneda();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyMonedaDTO> psyMonedaDTO) {
        this.data = psyMonedaDTO;
    }

    public PsyMonedaDTO getSelectedPsyMoneda() {
        return selectedPsyMoneda;
    }

    public void setSelectedPsyMoneda(PsyMonedaDTO psyMoneda) {
        this.selectedPsyMoneda = psyMoneda;
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
