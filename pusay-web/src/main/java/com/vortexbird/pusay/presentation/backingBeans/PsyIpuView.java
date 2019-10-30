package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyIpuDTO;
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
public class PsyIpuView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyIpuView.class);
    private InputText txtEstadoIpu;
    private InputText txtEstadoRegistro;
    private InputText txtPeriodo;
    private InputText txtPlacCodigo_PsyPlanAccion;
    private InputText txtIpuCodigo;
    private Calendar txtFechaInforme;
    private Calendar txtPeriodoFechaFin;
    private Calendar txtPeriodoFechaInicio;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyIpuDTO> data;
    private PsyIpuDTO selectedPsyIpu;
    private PsyIpu entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyIpuView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyIpuDTO psyIpuDTO = (PsyIpuDTO) e.getObject();

            if (txtEstadoIpu == null) {
                txtEstadoIpu = new InputText();
            }

            txtEstadoIpu.setValue(psyIpuDTO.getEstadoIpu());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyIpuDTO.getEstadoRegistro());

            if (txtPeriodo == null) {
                txtPeriodo = new InputText();
            }

            txtPeriodo.setValue(psyIpuDTO.getPeriodo());

            if (txtPlacCodigo_PsyPlanAccion == null) {
                txtPlacCodigo_PsyPlanAccion = new InputText();
            }

            txtPlacCodigo_PsyPlanAccion.setValue(psyIpuDTO.getPlacCodigo_PsyPlanAccion());

            if (txtIpuCodigo == null) {
                txtIpuCodigo = new InputText();
            }

            txtIpuCodigo.setValue(psyIpuDTO.getIpuCodigo());

            if (txtFechaInforme == null) {
                txtFechaInforme = new Calendar();
            }

            txtFechaInforme.setValue(psyIpuDTO.getFechaInforme());

            if (txtPeriodoFechaFin == null) {
                txtPeriodoFechaFin = new Calendar();
            }

            txtPeriodoFechaFin.setValue(psyIpuDTO.getPeriodoFechaFin());

            if (txtPeriodoFechaInicio == null) {
                txtPeriodoFechaInicio = new Calendar();
            }

            txtPeriodoFechaInicio.setValue(psyIpuDTO.getPeriodoFechaInicio());

            Long ipuCodigo = FacesUtils.checkLong(txtIpuCodigo);
            entity = businessDelegatorView.getPsyIpu(ipuCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyIpu = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyIpu = null;

        if (txtEstadoIpu != null) {
            txtEstadoIpu.setValue(null);
            txtEstadoIpu.setDisabled(true);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtPeriodo != null) {
            txtPeriodo.setValue(null);
            txtPeriodo.setDisabled(true);
        }

        if (txtPlacCodigo_PsyPlanAccion != null) {
            txtPlacCodigo_PsyPlanAccion.setValue(null);
            txtPlacCodigo_PsyPlanAccion.setDisabled(true);
        }

        if (txtFechaInforme != null) {
            txtFechaInforme.setValue(null);
            txtFechaInforme.setDisabled(true);
        }

        if (txtPeriodoFechaFin != null) {
            txtPeriodoFechaFin.setValue(null);
            txtPeriodoFechaFin.setDisabled(true);
        }

        if (txtPeriodoFechaInicio != null) {
            txtPeriodoFechaInicio.setValue(null);
            txtPeriodoFechaInicio.setDisabled(true);
        }

        if (txtIpuCodigo != null) {
            txtIpuCodigo.setValue(null);
            txtIpuCodigo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFechaInforme() {
        Date inputDate = (Date) txtFechaInforme.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtPeriodoFechaFin() {
        Date inputDate = (Date) txtPeriodoFechaFin.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtPeriodoFechaInicio() {
        Date inputDate = (Date) txtPeriodoFechaInicio.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long ipuCodigo = FacesUtils.checkLong(txtIpuCodigo);
            entity = (ipuCodigo != null)
                ? businessDelegatorView.getPsyIpu(ipuCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoIpu.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtPeriodo.setDisabled(false);
            txtPlacCodigo_PsyPlanAccion.setDisabled(false);
            txtFechaInforme.setDisabled(false);
            txtPeriodoFechaFin.setDisabled(false);
            txtPeriodoFechaInicio.setDisabled(false);
            txtIpuCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoIpu.setValue(entity.getEstadoIpu());
            txtEstadoIpu.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtFechaInforme.setValue(entity.getFechaInforme());
            txtFechaInforme.setDisabled(false);
            txtPeriodo.setValue(entity.getPeriodo());
            txtPeriodo.setDisabled(false);
            txtPeriodoFechaFin.setValue(entity.getPeriodoFechaFin());
            txtPeriodoFechaFin.setDisabled(false);
            txtPeriodoFechaInicio.setValue(entity.getPeriodoFechaInicio());
            txtPeriodoFechaInicio.setDisabled(false);
            txtPlacCodigo_PsyPlanAccion.setValue(entity.getPsyPlanAccion()
                                                       .getPlacCodigo());
            txtPlacCodigo_PsyPlanAccion.setDisabled(false);
            txtIpuCodigo.setValue(entity.getIpuCodigo());
            txtIpuCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyIpu = (PsyIpuDTO) (evt.getComponent().getAttributes()
                                         .get("selectedPsyIpu"));
        txtEstadoIpu.setValue(selectedPsyIpu.getEstadoIpu());
        txtEstadoIpu.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyIpu.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtFechaInforme.setValue(selectedPsyIpu.getFechaInforme());
        txtFechaInforme.setDisabled(false);
        txtPeriodo.setValue(selectedPsyIpu.getPeriodo());
        txtPeriodo.setDisabled(false);
        txtPeriodoFechaFin.setValue(selectedPsyIpu.getPeriodoFechaFin());
        txtPeriodoFechaFin.setDisabled(false);
        txtPeriodoFechaInicio.setValue(selectedPsyIpu.getPeriodoFechaInicio());
        txtPeriodoFechaInicio.setDisabled(false);
        txtPlacCodigo_PsyPlanAccion.setValue(selectedPsyIpu.getPlacCodigo_PsyPlanAccion());
        txtPlacCodigo_PsyPlanAccion.setDisabled(false);
        txtIpuCodigo.setValue(selectedPsyIpu.getIpuCodigo());
        txtIpuCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyIpu == null) && (entity == null)) {
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
            entity = new PsyIpu();

            Long ipuCodigo = FacesUtils.checkLong(txtIpuCodigo);

            entity.setEstadoIpu(FacesUtils.checkString(txtEstadoIpu));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setFechaInforme(FacesUtils.checkDate(txtFechaInforme));
            entity.setIpuCodigo(ipuCodigo);
            entity.setPeriodo(FacesUtils.checkString(txtPeriodo));
            entity.setPeriodoFechaFin(FacesUtils.checkDate(txtPeriodoFechaFin));
            entity.setPeriodoFechaInicio(FacesUtils.checkDate(
                    txtPeriodoFechaInicio));
            entity.setPsyPlanAccion((FacesUtils.checkLong(
                    txtPlacCodigo_PsyPlanAccion) != null)
                ? businessDelegatorView.getPsyPlanAccion(FacesUtils.checkLong(
                        txtPlacCodigo_PsyPlanAccion)) : null);
            businessDelegatorView.savePsyIpu(entity);
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
                Long ipuCodigo = new Long(selectedPsyIpu.getIpuCodigo());
                entity = businessDelegatorView.getPsyIpu(ipuCodigo);
            }

            entity.setEstadoIpu(FacesUtils.checkString(txtEstadoIpu));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setFechaInforme(FacesUtils.checkDate(txtFechaInforme));
            entity.setPeriodo(FacesUtils.checkString(txtPeriodo));
            entity.setPeriodoFechaFin(FacesUtils.checkDate(txtPeriodoFechaFin));
            entity.setPeriodoFechaInicio(FacesUtils.checkDate(
                    txtPeriodoFechaInicio));
            entity.setPsyPlanAccion((FacesUtils.checkLong(
                    txtPlacCodigo_PsyPlanAccion) != null)
                ? businessDelegatorView.getPsyPlanAccion(FacesUtils.checkLong(
                        txtPlacCodigo_PsyPlanAccion)) : null);
            businessDelegatorView.updatePsyIpu(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyIpu = (PsyIpuDTO) (evt.getComponent().getAttributes()
                                             .get("selectedPsyIpu"));

            Long ipuCodigo = new Long(selectedPsyIpu.getIpuCodigo());
            entity = businessDelegatorView.getPsyIpu(ipuCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long ipuCodigo = FacesUtils.checkLong(txtIpuCodigo);
            entity = businessDelegatorView.getPsyIpu(ipuCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyIpu(entity);
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
            selectedPsyIpu = (PsyIpuDTO) (evt.getComponent().getAttributes()
                                             .get("selectedPsyIpu"));

            Long ipuCodigo = new Long(selectedPsyIpu.getIpuCodigo());
            entity = businessDelegatorView.getPsyIpu(ipuCodigo);
            businessDelegatorView.deletePsyIpu(entity);
            data.remove(selectedPsyIpu);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estadoIpu, String estadoRegistro,
        Date fechaInforme, Long ipuCodigo, String periodo,
        Date periodoFechaFin, Date periodoFechaInicio,
        Long placCodigo_PsyPlanAccion) throws Exception {
        try {
            entity.setEstadoIpu(FacesUtils.checkString(estadoIpu));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFechaInforme(FacesUtils.checkDate(fechaInforme));
            entity.setPeriodo(FacesUtils.checkString(periodo));
            entity.setPeriodoFechaFin(FacesUtils.checkDate(periodoFechaFin));
            entity.setPeriodoFechaInicio(FacesUtils.checkDate(
                    periodoFechaInicio));
            businessDelegatorView.updatePsyIpu(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyIpuView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEstadoIpu() {
        return txtEstadoIpu;
    }

    public void setTxtEstadoIpu(InputText txtEstadoIpu) {
        this.txtEstadoIpu = txtEstadoIpu;
    }

    public InputText getTxtEstadoRegistro() {
        return txtEstadoRegistro;
    }

    public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
        this.txtEstadoRegistro = txtEstadoRegistro;
    }

    public InputText getTxtPeriodo() {
        return txtPeriodo;
    }

    public void setTxtPeriodo(InputText txtPeriodo) {
        this.txtPeriodo = txtPeriodo;
    }

    public InputText getTxtPlacCodigo_PsyPlanAccion() {
        return txtPlacCodigo_PsyPlanAccion;
    }

    public void setTxtPlacCodigo_PsyPlanAccion(
        InputText txtPlacCodigo_PsyPlanAccion) {
        this.txtPlacCodigo_PsyPlanAccion = txtPlacCodigo_PsyPlanAccion;
    }

    public Calendar getTxtFechaInforme() {
        return txtFechaInforme;
    }

    public void setTxtFechaInforme(Calendar txtFechaInforme) {
        this.txtFechaInforme = txtFechaInforme;
    }

    public Calendar getTxtPeriodoFechaFin() {
        return txtPeriodoFechaFin;
    }

    public void setTxtPeriodoFechaFin(Calendar txtPeriodoFechaFin) {
        this.txtPeriodoFechaFin = txtPeriodoFechaFin;
    }

    public Calendar getTxtPeriodoFechaInicio() {
        return txtPeriodoFechaInicio;
    }

    public void setTxtPeriodoFechaInicio(Calendar txtPeriodoFechaInicio) {
        this.txtPeriodoFechaInicio = txtPeriodoFechaInicio;
    }

    public InputText getTxtIpuCodigo() {
        return txtIpuCodigo;
    }

    public void setTxtIpuCodigo(InputText txtIpuCodigo) {
        this.txtIpuCodigo = txtIpuCodigo;
    }

    public List<PsyIpuDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyIpu();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyIpuDTO> psyIpuDTO) {
        this.data = psyIpuDTO;
    }

    public PsyIpuDTO getSelectedPsyIpu() {
        return selectedPsyIpu;
    }

    public void setSelectedPsyIpu(PsyIpuDTO psyIpu) {
        this.selectedPsyIpu = psyIpu;
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
