package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyObjetivoPlanDTO;
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
public class PsyObjetivoPlanView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyObjetivoPlanView.class);
    private InputText txtEstadoObjetivoPlan;
    private InputText txtEstadoRegistro;
    private InputText txtPestCodigo_PsyPlanEstrategico;
    private InputText txtObplCodigo;
    private Calendar txtFechaFin;
    private Calendar txtFechaInicio;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyObjetivoPlanDTO> data;
    private PsyObjetivoPlanDTO selectedPsyObjetivoPlan;
    private PsyObjetivoPlan entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyObjetivoPlanView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyObjetivoPlanDTO psyObjetivoPlanDTO = (PsyObjetivoPlanDTO) e.getObject();

            if (txtEstadoObjetivoPlan == null) {
                txtEstadoObjetivoPlan = new InputText();
            }

            txtEstadoObjetivoPlan.setValue(psyObjetivoPlanDTO.getEstadoObjetivoPlan());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyObjetivoPlanDTO.getEstadoRegistro());

            if (txtPestCodigo_PsyPlanEstrategico == null) {
                txtPestCodigo_PsyPlanEstrategico = new InputText();
            }

            txtPestCodigo_PsyPlanEstrategico.setValue(psyObjetivoPlanDTO.getPestCodigo_PsyPlanEstrategico());

            if (txtObplCodigo == null) {
                txtObplCodigo = new InputText();
            }

            txtObplCodigo.setValue(psyObjetivoPlanDTO.getObplCodigo());

            if (txtFechaFin == null) {
                txtFechaFin = new Calendar();
            }

            txtFechaFin.setValue(psyObjetivoPlanDTO.getFechaFin());

            if (txtFechaInicio == null) {
                txtFechaInicio = new Calendar();
            }

            txtFechaInicio.setValue(psyObjetivoPlanDTO.getFechaInicio());

            Long obplCodigo = FacesUtils.checkLong(txtObplCodigo);
            entity = businessDelegatorView.getPsyObjetivoPlan(obplCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyObjetivoPlan = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyObjetivoPlan = null;

        if (txtEstadoObjetivoPlan != null) {
            txtEstadoObjetivoPlan.setValue(null);
            txtEstadoObjetivoPlan.setDisabled(true);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtPestCodigo_PsyPlanEstrategico != null) {
            txtPestCodigo_PsyPlanEstrategico.setValue(null);
            txtPestCodigo_PsyPlanEstrategico.setDisabled(true);
        }

        if (txtFechaFin != null) {
            txtFechaFin.setValue(null);
            txtFechaFin.setDisabled(true);
        }

        if (txtFechaInicio != null) {
            txtFechaInicio.setValue(null);
            txtFechaInicio.setDisabled(true);
        }

        if (txtObplCodigo != null) {
            txtObplCodigo.setValue(null);
            txtObplCodigo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFechaFin() {
        Date inputDate = (Date) txtFechaFin.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaInicio() {
        Date inputDate = (Date) txtFechaInicio.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long obplCodigo = FacesUtils.checkLong(txtObplCodigo);
            entity = (obplCodigo != null)
                ? businessDelegatorView.getPsyObjetivoPlan(obplCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoObjetivoPlan.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtPestCodigo_PsyPlanEstrategico.setDisabled(false);
            txtFechaFin.setDisabled(false);
            txtFechaInicio.setDisabled(false);
            txtObplCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoObjetivoPlan.setValue(entity.getEstadoObjetivoPlan());
            txtEstadoObjetivoPlan.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtFechaFin.setValue(entity.getFechaFin());
            txtFechaFin.setDisabled(false);
            txtFechaInicio.setValue(entity.getFechaInicio());
            txtFechaInicio.setDisabled(false);
            txtPestCodigo_PsyPlanEstrategico.setValue(entity.getPsyPlanEstrategico()
                                                            .getPestCodigo());
            txtPestCodigo_PsyPlanEstrategico.setDisabled(false);
            txtObplCodigo.setValue(entity.getObplCodigo());
            txtObplCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyObjetivoPlan = (PsyObjetivoPlanDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedPsyObjetivoPlan"));
        txtEstadoObjetivoPlan.setValue(selectedPsyObjetivoPlan.getEstadoObjetivoPlan());
        txtEstadoObjetivoPlan.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyObjetivoPlan.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtFechaFin.setValue(selectedPsyObjetivoPlan.getFechaFin());
        txtFechaFin.setDisabled(false);
        txtFechaInicio.setValue(selectedPsyObjetivoPlan.getFechaInicio());
        txtFechaInicio.setDisabled(false);
        txtPestCodigo_PsyPlanEstrategico.setValue(selectedPsyObjetivoPlan.getPestCodigo_PsyPlanEstrategico());
        txtPestCodigo_PsyPlanEstrategico.setDisabled(false);
        txtObplCodigo.setValue(selectedPsyObjetivoPlan.getObplCodigo());
        txtObplCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyObjetivoPlan == null) && (entity == null)) {
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
            entity = new PsyObjetivoPlan();

            Long obplCodigo = FacesUtils.checkLong(txtObplCodigo);

            entity.setEstadoObjetivoPlan(FacesUtils.checkString(
                    txtEstadoObjetivoPlan));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setFechaFin(FacesUtils.checkDate(txtFechaFin));
            entity.setFechaInicio(FacesUtils.checkDate(txtFechaInicio));
            entity.setObplCodigo(obplCodigo);
            entity.setPsyPlanEstrategico((FacesUtils.checkLong(
                    txtPestCodigo_PsyPlanEstrategico) != null)
                ? businessDelegatorView.getPsyPlanEstrategico(
                    FacesUtils.checkLong(txtPestCodigo_PsyPlanEstrategico)) : null);
            businessDelegatorView.savePsyObjetivoPlan(entity);
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
                Long obplCodigo = new Long(selectedPsyObjetivoPlan.getObplCodigo());
                entity = businessDelegatorView.getPsyObjetivoPlan(obplCodigo);
            }

            entity.setEstadoObjetivoPlan(FacesUtils.checkString(
                    txtEstadoObjetivoPlan));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setFechaFin(FacesUtils.checkDate(txtFechaFin));
            entity.setFechaInicio(FacesUtils.checkDate(txtFechaInicio));
            entity.setPsyPlanEstrategico((FacesUtils.checkLong(
                    txtPestCodigo_PsyPlanEstrategico) != null)
                ? businessDelegatorView.getPsyPlanEstrategico(
                    FacesUtils.checkLong(txtPestCodigo_PsyPlanEstrategico)) : null);
            businessDelegatorView.updatePsyObjetivoPlan(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyObjetivoPlan = (PsyObjetivoPlanDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedPsyObjetivoPlan"));

            Long obplCodigo = new Long(selectedPsyObjetivoPlan.getObplCodigo());
            entity = businessDelegatorView.getPsyObjetivoPlan(obplCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long obplCodigo = FacesUtils.checkLong(txtObplCodigo);
            entity = businessDelegatorView.getPsyObjetivoPlan(obplCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyObjetivoPlan(entity);
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
            selectedPsyObjetivoPlan = (PsyObjetivoPlanDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedPsyObjetivoPlan"));

            Long obplCodigo = new Long(selectedPsyObjetivoPlan.getObplCodigo());
            entity = businessDelegatorView.getPsyObjetivoPlan(obplCodigo);
            businessDelegatorView.deletePsyObjetivoPlan(entity);
            data.remove(selectedPsyObjetivoPlan);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estadoObjetivoPlan,
        String estadoRegistro, Date fechaFin, Date fechaInicio,
        Long obplCodigo, Long pestCodigo_PsyPlanEstrategico)
        throws Exception {
        try {
            entity.setEstadoObjetivoPlan(FacesUtils.checkString(
                    estadoObjetivoPlan));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFechaFin(FacesUtils.checkDate(fechaFin));
            entity.setFechaInicio(FacesUtils.checkDate(fechaInicio));
            businessDelegatorView.updatePsyObjetivoPlan(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyObjetivoPlanView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEstadoObjetivoPlan() {
        return txtEstadoObjetivoPlan;
    }

    public void setTxtEstadoObjetivoPlan(InputText txtEstadoObjetivoPlan) {
        this.txtEstadoObjetivoPlan = txtEstadoObjetivoPlan;
    }

    public InputText getTxtEstadoRegistro() {
        return txtEstadoRegistro;
    }

    public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
        this.txtEstadoRegistro = txtEstadoRegistro;
    }

    public InputText getTxtPestCodigo_PsyPlanEstrategico() {
        return txtPestCodigo_PsyPlanEstrategico;
    }

    public void setTxtPestCodigo_PsyPlanEstrategico(
        InputText txtPestCodigo_PsyPlanEstrategico) {
        this.txtPestCodigo_PsyPlanEstrategico = txtPestCodigo_PsyPlanEstrategico;
    }

    public Calendar getTxtFechaFin() {
        return txtFechaFin;
    }

    public void setTxtFechaFin(Calendar txtFechaFin) {
        this.txtFechaFin = txtFechaFin;
    }

    public Calendar getTxtFechaInicio() {
        return txtFechaInicio;
    }

    public void setTxtFechaInicio(Calendar txtFechaInicio) {
        this.txtFechaInicio = txtFechaInicio;
    }

    public InputText getTxtObplCodigo() {
        return txtObplCodigo;
    }

    public void setTxtObplCodigo(InputText txtObplCodigo) {
        this.txtObplCodigo = txtObplCodigo;
    }

    public List<PsyObjetivoPlanDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyObjetivoPlan();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyObjetivoPlanDTO> psyObjetivoPlanDTO) {
        this.data = psyObjetivoPlanDTO;
    }

    public PsyObjetivoPlanDTO getSelectedPsyObjetivoPlan() {
        return selectedPsyObjetivoPlan;
    }

    public void setSelectedPsyObjetivoPlan(PsyObjetivoPlanDTO psyObjetivoPlan) {
        this.selectedPsyObjetivoPlan = psyObjetivoPlan;
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
