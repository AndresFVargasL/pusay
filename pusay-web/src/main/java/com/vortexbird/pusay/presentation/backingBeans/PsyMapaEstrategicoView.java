package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyMapaEstrategicoDTO;
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
public class PsyMapaEstrategicoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyMapaEstrategicoView.class);
    private InputText txtEstadoMapaEstrategico;
    private InputText txtEstadoRegistro;
    private InputText txtPestCodigo_PsyPlanEstrategico;
    private InputText txtMaesCodigo;
    private Calendar txtFechaFin;
    private Calendar txtFechaInicio;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyMapaEstrategicoDTO> data;
    private PsyMapaEstrategicoDTO selectedPsyMapaEstrategico;
    private PsyMapaEstrategico entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyMapaEstrategicoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyMapaEstrategicoDTO psyMapaEstrategicoDTO = (PsyMapaEstrategicoDTO) e.getObject();

            if (txtEstadoMapaEstrategico == null) {
                txtEstadoMapaEstrategico = new InputText();
            }

            txtEstadoMapaEstrategico.setValue(psyMapaEstrategicoDTO.getEstadoMapaEstrategico());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyMapaEstrategicoDTO.getEstadoRegistro());

            if (txtPestCodigo_PsyPlanEstrategico == null) {
                txtPestCodigo_PsyPlanEstrategico = new InputText();
            }

            txtPestCodigo_PsyPlanEstrategico.setValue(psyMapaEstrategicoDTO.getPestCodigo_PsyPlanEstrategico());

            if (txtMaesCodigo == null) {
                txtMaesCodigo = new InputText();
            }

            txtMaesCodigo.setValue(psyMapaEstrategicoDTO.getMaesCodigo());

            if (txtFechaFin == null) {
                txtFechaFin = new Calendar();
            }

            txtFechaFin.setValue(psyMapaEstrategicoDTO.getFechaFin());

            if (txtFechaInicio == null) {
                txtFechaInicio = new Calendar();
            }

            txtFechaInicio.setValue(psyMapaEstrategicoDTO.getFechaInicio());

            Long maesCodigo = FacesUtils.checkLong(txtMaesCodigo);
            entity = businessDelegatorView.getPsyMapaEstrategico(maesCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyMapaEstrategico = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyMapaEstrategico = null;

        if (txtEstadoMapaEstrategico != null) {
            txtEstadoMapaEstrategico.setValue(null);
            txtEstadoMapaEstrategico.setDisabled(true);
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

        if (txtMaesCodigo != null) {
            txtMaesCodigo.setValue(null);
            txtMaesCodigo.setDisabled(false);
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
            Long maesCodigo = FacesUtils.checkLong(txtMaesCodigo);
            entity = (maesCodigo != null)
                ? businessDelegatorView.getPsyMapaEstrategico(maesCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoMapaEstrategico.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtPestCodigo_PsyPlanEstrategico.setDisabled(false);
            txtFechaFin.setDisabled(false);
            txtFechaInicio.setDisabled(false);
            txtMaesCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoMapaEstrategico.setValue(entity.getEstadoMapaEstrategico());
            txtEstadoMapaEstrategico.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtFechaFin.setValue(entity.getFechaFin());
            txtFechaFin.setDisabled(false);
            txtFechaInicio.setValue(entity.getFechaInicio());
            txtFechaInicio.setDisabled(false);
            txtPestCodigo_PsyPlanEstrategico.setValue(entity.getPsyPlanEstrategico()
                                                            .getPestCodigo());
            txtPestCodigo_PsyPlanEstrategico.setDisabled(false);
            txtMaesCodigo.setValue(entity.getMaesCodigo());
            txtMaesCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyMapaEstrategico = (PsyMapaEstrategicoDTO) (evt.getComponent()
                                                                 .getAttributes()
                                                                 .get("selectedPsyMapaEstrategico"));
        txtEstadoMapaEstrategico.setValue(selectedPsyMapaEstrategico.getEstadoMapaEstrategico());
        txtEstadoMapaEstrategico.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyMapaEstrategico.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtFechaFin.setValue(selectedPsyMapaEstrategico.getFechaFin());
        txtFechaFin.setDisabled(false);
        txtFechaInicio.setValue(selectedPsyMapaEstrategico.getFechaInicio());
        txtFechaInicio.setDisabled(false);
        txtPestCodigo_PsyPlanEstrategico.setValue(selectedPsyMapaEstrategico.getPestCodigo_PsyPlanEstrategico());
        txtPestCodigo_PsyPlanEstrategico.setDisabled(false);
        txtMaesCodigo.setValue(selectedPsyMapaEstrategico.getMaesCodigo());
        txtMaesCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyMapaEstrategico == null) && (entity == null)) {
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
            entity = new PsyMapaEstrategico();

            Long maesCodigo = FacesUtils.checkLong(txtMaesCodigo);

            entity.setEstadoMapaEstrategico(FacesUtils.checkString(
                    txtEstadoMapaEstrategico));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setFechaFin(FacesUtils.checkDate(txtFechaFin));
            entity.setFechaInicio(FacesUtils.checkDate(txtFechaInicio));
            entity.setMaesCodigo(maesCodigo);
            entity.setPsyPlanEstrategico((FacesUtils.checkLong(
                    txtPestCodigo_PsyPlanEstrategico) != null)
                ? businessDelegatorView.getPsyPlanEstrategico(
                    FacesUtils.checkLong(txtPestCodigo_PsyPlanEstrategico)) : null);
            businessDelegatorView.savePsyMapaEstrategico(entity);
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
                Long maesCodigo = new Long(selectedPsyMapaEstrategico.getMaesCodigo());
                entity = businessDelegatorView.getPsyMapaEstrategico(maesCodigo);
            }

            entity.setEstadoMapaEstrategico(FacesUtils.checkString(
                    txtEstadoMapaEstrategico));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setFechaFin(FacesUtils.checkDate(txtFechaFin));
            entity.setFechaInicio(FacesUtils.checkDate(txtFechaInicio));
            entity.setPsyPlanEstrategico((FacesUtils.checkLong(
                    txtPestCodigo_PsyPlanEstrategico) != null)
                ? businessDelegatorView.getPsyPlanEstrategico(
                    FacesUtils.checkLong(txtPestCodigo_PsyPlanEstrategico)) : null);
            businessDelegatorView.updatePsyMapaEstrategico(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyMapaEstrategico = (PsyMapaEstrategicoDTO) (evt.getComponent()
                                                                     .getAttributes()
                                                                     .get("selectedPsyMapaEstrategico"));

            Long maesCodigo = new Long(selectedPsyMapaEstrategico.getMaesCodigo());
            entity = businessDelegatorView.getPsyMapaEstrategico(maesCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long maesCodigo = FacesUtils.checkLong(txtMaesCodigo);
            entity = businessDelegatorView.getPsyMapaEstrategico(maesCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyMapaEstrategico(entity);
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
            selectedPsyMapaEstrategico = (PsyMapaEstrategicoDTO) (evt.getComponent()
                                                                     .getAttributes()
                                                                     .get("selectedPsyMapaEstrategico"));

            Long maesCodigo = new Long(selectedPsyMapaEstrategico.getMaesCodigo());
            entity = businessDelegatorView.getPsyMapaEstrategico(maesCodigo);
            businessDelegatorView.deletePsyMapaEstrategico(entity);
            data.remove(selectedPsyMapaEstrategico);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estadoMapaEstrategico,
        String estadoRegistro, Date fechaFin, Date fechaInicio,
        Long maesCodigo, Long pestCodigo_PsyPlanEstrategico)
        throws Exception {
        try {
            entity.setEstadoMapaEstrategico(FacesUtils.checkString(
                    estadoMapaEstrategico));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFechaFin(FacesUtils.checkDate(fechaFin));
            entity.setFechaInicio(FacesUtils.checkDate(fechaInicio));
            businessDelegatorView.updatePsyMapaEstrategico(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyMapaEstrategicoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEstadoMapaEstrategico() {
        return txtEstadoMapaEstrategico;
    }

    public void setTxtEstadoMapaEstrategico(InputText txtEstadoMapaEstrategico) {
        this.txtEstadoMapaEstrategico = txtEstadoMapaEstrategico;
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

    public InputText getTxtMaesCodigo() {
        return txtMaesCodigo;
    }

    public void setTxtMaesCodigo(InputText txtMaesCodigo) {
        this.txtMaesCodigo = txtMaesCodigo;
    }

    public List<PsyMapaEstrategicoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyMapaEstrategico();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyMapaEstrategicoDTO> psyMapaEstrategicoDTO) {
        this.data = psyMapaEstrategicoDTO;
    }

    public PsyMapaEstrategicoDTO getSelectedPsyMapaEstrategico() {
        return selectedPsyMapaEstrategico;
    }

    public void setSelectedPsyMapaEstrategico(
        PsyMapaEstrategicoDTO psyMapaEstrategico) {
        this.selectedPsyMapaEstrategico = psyMapaEstrategico;
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
