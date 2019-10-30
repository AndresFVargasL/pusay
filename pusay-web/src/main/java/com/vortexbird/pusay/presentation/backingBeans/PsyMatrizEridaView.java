package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyMatrizEridaDTO;
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
public class PsyMatrizEridaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyMatrizEridaView.class);
    private InputText txtEstadoErida;
    private InputText txtEstadoRegistro;
    private InputText txtPestCodigo_PsyPlanEstrategico;
    private InputText txtMaerCodigo;
    private Calendar txtFechaFin;
    private Calendar txtFechaInicio;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyMatrizEridaDTO> data;
    private PsyMatrizEridaDTO selectedPsyMatrizErida;
    private PsyMatrizErida entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyMatrizEridaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyMatrizEridaDTO psyMatrizEridaDTO = (PsyMatrizEridaDTO) e.getObject();

            if (txtEstadoErida == null) {
                txtEstadoErida = new InputText();
            }

            txtEstadoErida.setValue(psyMatrizEridaDTO.getEstadoErida());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyMatrizEridaDTO.getEstadoRegistro());

            if (txtPestCodigo_PsyPlanEstrategico == null) {
                txtPestCodigo_PsyPlanEstrategico = new InputText();
            }

            txtPestCodigo_PsyPlanEstrategico.setValue(psyMatrizEridaDTO.getPestCodigo_PsyPlanEstrategico());

            if (txtMaerCodigo == null) {
                txtMaerCodigo = new InputText();
            }

            txtMaerCodigo.setValue(psyMatrizEridaDTO.getMaerCodigo());

            if (txtFechaFin == null) {
                txtFechaFin = new Calendar();
            }

            txtFechaFin.setValue(psyMatrizEridaDTO.getFechaFin());

            if (txtFechaInicio == null) {
                txtFechaInicio = new Calendar();
            }

            txtFechaInicio.setValue(psyMatrizEridaDTO.getFechaInicio());

            Long maerCodigo = FacesUtils.checkLong(txtMaerCodigo);
            entity = businessDelegatorView.getPsyMatrizErida(maerCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyMatrizErida = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyMatrizErida = null;

        if (txtEstadoErida != null) {
            txtEstadoErida.setValue(null);
            txtEstadoErida.setDisabled(true);
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

        if (txtMaerCodigo != null) {
            txtMaerCodigo.setValue(null);
            txtMaerCodigo.setDisabled(false);
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
            Long maerCodigo = FacesUtils.checkLong(txtMaerCodigo);
            entity = (maerCodigo != null)
                ? businessDelegatorView.getPsyMatrizErida(maerCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoErida.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtPestCodigo_PsyPlanEstrategico.setDisabled(false);
            txtFechaFin.setDisabled(false);
            txtFechaInicio.setDisabled(false);
            txtMaerCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoErida.setValue(entity.getEstadoErida());
            txtEstadoErida.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtFechaFin.setValue(entity.getFechaFin());
            txtFechaFin.setDisabled(false);
            txtFechaInicio.setValue(entity.getFechaInicio());
            txtFechaInicio.setDisabled(false);
            txtPestCodigo_PsyPlanEstrategico.setValue(entity.getPsyPlanEstrategico()
                                                            .getPestCodigo());
            txtPestCodigo_PsyPlanEstrategico.setDisabled(false);
            txtMaerCodigo.setValue(entity.getMaerCodigo());
            txtMaerCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyMatrizErida = (PsyMatrizEridaDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedPsyMatrizErida"));
        txtEstadoErida.setValue(selectedPsyMatrizErida.getEstadoErida());
        txtEstadoErida.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyMatrizErida.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtFechaFin.setValue(selectedPsyMatrizErida.getFechaFin());
        txtFechaFin.setDisabled(false);
        txtFechaInicio.setValue(selectedPsyMatrizErida.getFechaInicio());
        txtFechaInicio.setDisabled(false);
        txtPestCodigo_PsyPlanEstrategico.setValue(selectedPsyMatrizErida.getPestCodigo_PsyPlanEstrategico());
        txtPestCodigo_PsyPlanEstrategico.setDisabled(false);
        txtMaerCodigo.setValue(selectedPsyMatrizErida.getMaerCodigo());
        txtMaerCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyMatrizErida == null) && (entity == null)) {
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
            entity = new PsyMatrizErida();

            Long maerCodigo = FacesUtils.checkLong(txtMaerCodigo);

            entity.setEstadoErida(FacesUtils.checkString(txtEstadoErida));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setFechaFin(FacesUtils.checkDate(txtFechaFin));
            entity.setFechaInicio(FacesUtils.checkDate(txtFechaInicio));
            entity.setMaerCodigo(maerCodigo);
            entity.setPsyPlanEstrategico((FacesUtils.checkLong(
                    txtPestCodigo_PsyPlanEstrategico) != null)
                ? businessDelegatorView.getPsyPlanEstrategico(
                    FacesUtils.checkLong(txtPestCodigo_PsyPlanEstrategico)) : null);
            businessDelegatorView.savePsyMatrizErida(entity);
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
                Long maerCodigo = new Long(selectedPsyMatrizErida.getMaerCodigo());
                entity = businessDelegatorView.getPsyMatrizErida(maerCodigo);
            }

            entity.setEstadoErida(FacesUtils.checkString(txtEstadoErida));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setFechaFin(FacesUtils.checkDate(txtFechaFin));
            entity.setFechaInicio(FacesUtils.checkDate(txtFechaInicio));
            entity.setPsyPlanEstrategico((FacesUtils.checkLong(
                    txtPestCodigo_PsyPlanEstrategico) != null)
                ? businessDelegatorView.getPsyPlanEstrategico(
                    FacesUtils.checkLong(txtPestCodigo_PsyPlanEstrategico)) : null);
            businessDelegatorView.updatePsyMatrizErida(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyMatrizErida = (PsyMatrizEridaDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedPsyMatrizErida"));

            Long maerCodigo = new Long(selectedPsyMatrizErida.getMaerCodigo());
            entity = businessDelegatorView.getPsyMatrizErida(maerCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long maerCodigo = FacesUtils.checkLong(txtMaerCodigo);
            entity = businessDelegatorView.getPsyMatrizErida(maerCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyMatrizErida(entity);
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
            selectedPsyMatrizErida = (PsyMatrizEridaDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedPsyMatrizErida"));

            Long maerCodigo = new Long(selectedPsyMatrizErida.getMaerCodigo());
            entity = businessDelegatorView.getPsyMatrizErida(maerCodigo);
            businessDelegatorView.deletePsyMatrizErida(entity);
            data.remove(selectedPsyMatrizErida);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estadoErida,
        String estadoRegistro, Date fechaFin, Date fechaInicio,
        Long maerCodigo, Long pestCodigo_PsyPlanEstrategico)
        throws Exception {
        try {
            entity.setEstadoErida(FacesUtils.checkString(estadoErida));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFechaFin(FacesUtils.checkDate(fechaFin));
            entity.setFechaInicio(FacesUtils.checkDate(fechaInicio));
            businessDelegatorView.updatePsyMatrizErida(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyMatrizEridaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEstadoErida() {
        return txtEstadoErida;
    }

    public void setTxtEstadoErida(InputText txtEstadoErida) {
        this.txtEstadoErida = txtEstadoErida;
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

    public InputText getTxtMaerCodigo() {
        return txtMaerCodigo;
    }

    public void setTxtMaerCodigo(InputText txtMaerCodigo) {
        this.txtMaerCodigo = txtMaerCodigo;
    }

    public List<PsyMatrizEridaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyMatrizErida();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyMatrizEridaDTO> psyMatrizEridaDTO) {
        this.data = psyMatrizEridaDTO;
    }

    public PsyMatrizEridaDTO getSelectedPsyMatrizErida() {
        return selectedPsyMatrizErida;
    }

    public void setSelectedPsyMatrizErida(PsyMatrizEridaDTO psyMatrizErida) {
        this.selectedPsyMatrizErida = psyMatrizErida;
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
