package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyEvaluacionPeaObjetivoDTO;
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
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyEvaluacionPeaObjetivoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyEvaluacionPeaObjetivoView.class);
    private InputText txtEstadoRegistro;
    private InputText txtPeriodo;
    private InputText txtResultado;
    private InputText txtCodigo_PsyObjetivoAmbiental;
    private InputText txtCodigo_PsyPlanEstrategicoAmbiental;
    private InputText txtCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyEvaluacionPeaObjetivoDTO> data;
    private PsyEvaluacionPeaObjetivoDTO selectedPsyEvaluacionPeaObjetivo;
    private PsyEvaluacionPeaObjetivo entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyEvaluacionPeaObjetivoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyEvaluacionPeaObjetivoDTO psyEvaluacionPeaObjetivoDTO = (PsyEvaluacionPeaObjetivoDTO) e.getObject();

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyEvaluacionPeaObjetivoDTO.getEstadoRegistro());

            if (txtPeriodo == null) {
                txtPeriodo = new InputText();
            }

            txtPeriodo.setValue(psyEvaluacionPeaObjetivoDTO.getPeriodo());

            if (txtResultado == null) {
                txtResultado = new InputText();
            }

            txtResultado.setValue(psyEvaluacionPeaObjetivoDTO.getResultado());

            if (txtCodigo_PsyObjetivoAmbiental == null) {
                txtCodigo_PsyObjetivoAmbiental = new InputText();
            }

            txtCodigo_PsyObjetivoAmbiental.setValue(psyEvaluacionPeaObjetivoDTO.getCodigo_PsyObjetivoAmbiental());

            if (txtCodigo_PsyPlanEstrategicoAmbiental == null) {
                txtCodigo_PsyPlanEstrategicoAmbiental = new InputText();
            }

            txtCodigo_PsyPlanEstrategicoAmbiental.setValue(psyEvaluacionPeaObjetivoDTO.getCodigo_PsyPlanEstrategicoAmbiental());

            if (txtCodigo == null) {
                txtCodigo = new InputText();
            }

            txtCodigo.setValue(psyEvaluacionPeaObjetivoDTO.getCodigo());

            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = businessDelegatorView.getPsyEvaluacionPeaObjetivo(codigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyEvaluacionPeaObjetivo = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyEvaluacionPeaObjetivo = null;

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtPeriodo != null) {
            txtPeriodo.setValue(null);
            txtPeriodo.setDisabled(true);
        }

        if (txtResultado != null) {
            txtResultado.setValue(null);
            txtResultado.setDisabled(true);
        }

        if (txtCodigo_PsyObjetivoAmbiental != null) {
            txtCodigo_PsyObjetivoAmbiental.setValue(null);
            txtCodigo_PsyObjetivoAmbiental.setDisabled(true);
        }

        if (txtCodigo_PsyPlanEstrategicoAmbiental != null) {
            txtCodigo_PsyPlanEstrategicoAmbiental.setValue(null);
            txtCodigo_PsyPlanEstrategicoAmbiental.setDisabled(true);
        }

        if (txtCodigo != null) {
            txtCodigo.setValue(null);
            txtCodigo.setDisabled(false);
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
            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = (codigo != null)
                ? businessDelegatorView.getPsyEvaluacionPeaObjetivo(codigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoRegistro.setDisabled(false);
            txtPeriodo.setDisabled(false);
            txtResultado.setDisabled(false);
            txtCodigo_PsyObjetivoAmbiental.setDisabled(false);
            txtCodigo_PsyPlanEstrategicoAmbiental.setDisabled(false);
            txtCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtPeriodo.setValue(entity.getPeriodo());
            txtPeriodo.setDisabled(false);
            txtResultado.setValue(entity.getResultado());
            txtResultado.setDisabled(false);
            txtCodigo_PsyObjetivoAmbiental.setValue(entity.getPsyObjetivoAmbiental()
                                                          .getCodigo());
            txtCodigo_PsyObjetivoAmbiental.setDisabled(false);
            txtCodigo_PsyPlanEstrategicoAmbiental.setValue(entity.getPsyPlanEstrategicoAmbiental()
                                                                 .getCodigo());
            txtCodigo_PsyPlanEstrategicoAmbiental.setDisabled(false);
            txtCodigo.setValue(entity.getCodigo());
            txtCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyEvaluacionPeaObjetivo = (PsyEvaluacionPeaObjetivoDTO) (evt.getComponent()
                                                                             .getAttributes()
                                                                             .get("selectedPsyEvaluacionPeaObjetivo"));
        txtEstadoRegistro.setValue(selectedPsyEvaluacionPeaObjetivo.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtPeriodo.setValue(selectedPsyEvaluacionPeaObjetivo.getPeriodo());
        txtPeriodo.setDisabled(false);
        txtResultado.setValue(selectedPsyEvaluacionPeaObjetivo.getResultado());
        txtResultado.setDisabled(false);
        txtCodigo_PsyObjetivoAmbiental.setValue(selectedPsyEvaluacionPeaObjetivo.getCodigo_PsyObjetivoAmbiental());
        txtCodigo_PsyObjetivoAmbiental.setDisabled(false);
        txtCodigo_PsyPlanEstrategicoAmbiental.setValue(selectedPsyEvaluacionPeaObjetivo.getCodigo_PsyPlanEstrategicoAmbiental());
        txtCodigo_PsyPlanEstrategicoAmbiental.setDisabled(false);
        txtCodigo.setValue(selectedPsyEvaluacionPeaObjetivo.getCodigo());
        txtCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyEvaluacionPeaObjetivo == null) && (entity == null)) {
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
            entity = new PsyEvaluacionPeaObjetivo();

            Long codigo = FacesUtils.checkLong(txtCodigo);

            entity.setCodigo(codigo);
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setPeriodo(FacesUtils.checkLong(txtPeriodo));
            entity.setResultado(FacesUtils.checkDouble(txtResultado));
            entity.setPsyObjetivoAmbiental((FacesUtils.checkLong(
                    txtCodigo_PsyObjetivoAmbiental) != null)
                ? businessDelegatorView.getPsyObjetivoAmbiental(
                    FacesUtils.checkLong(txtCodigo_PsyObjetivoAmbiental)) : null);
            entity.setPsyPlanEstrategicoAmbiental((FacesUtils.checkLong(
                    txtCodigo_PsyPlanEstrategicoAmbiental) != null)
                ? businessDelegatorView.getPsyPlanEstrategicoAmbiental(
                    FacesUtils.checkLong(txtCodigo_PsyPlanEstrategicoAmbiental))
                : null);
            businessDelegatorView.savePsyEvaluacionPeaObjetivo(entity);
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
                Long codigo = new Long(selectedPsyEvaluacionPeaObjetivo.getCodigo());
                entity = businessDelegatorView.getPsyEvaluacionPeaObjetivo(codigo);
            }

            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setPeriodo(FacesUtils.checkLong(txtPeriodo));
            entity.setResultado(FacesUtils.checkDouble(txtResultado));
            entity.setPsyObjetivoAmbiental((FacesUtils.checkLong(
                    txtCodigo_PsyObjetivoAmbiental) != null)
                ? businessDelegatorView.getPsyObjetivoAmbiental(
                    FacesUtils.checkLong(txtCodigo_PsyObjetivoAmbiental)) : null);
            entity.setPsyPlanEstrategicoAmbiental((FacesUtils.checkLong(
                    txtCodigo_PsyPlanEstrategicoAmbiental) != null)
                ? businessDelegatorView.getPsyPlanEstrategicoAmbiental(
                    FacesUtils.checkLong(txtCodigo_PsyPlanEstrategicoAmbiental))
                : null);
            businessDelegatorView.updatePsyEvaluacionPeaObjetivo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyEvaluacionPeaObjetivo = (PsyEvaluacionPeaObjetivoDTO) (evt.getComponent()
                                                                                 .getAttributes()
                                                                                 .get("selectedPsyEvaluacionPeaObjetivo"));

            Long codigo = new Long(selectedPsyEvaluacionPeaObjetivo.getCodigo());
            entity = businessDelegatorView.getPsyEvaluacionPeaObjetivo(codigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = businessDelegatorView.getPsyEvaluacionPeaObjetivo(codigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyEvaluacionPeaObjetivo(entity);
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
            selectedPsyEvaluacionPeaObjetivo = (PsyEvaluacionPeaObjetivoDTO) (evt.getComponent()
                                                                                 .getAttributes()
                                                                                 .get("selectedPsyEvaluacionPeaObjetivo"));

            Long codigo = new Long(selectedPsyEvaluacionPeaObjetivo.getCodigo());
            entity = businessDelegatorView.getPsyEvaluacionPeaObjetivo(codigo);
            businessDelegatorView.deletePsyEvaluacionPeaObjetivo(entity);
            data.remove(selectedPsyEvaluacionPeaObjetivo);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigo, String estadoRegistro,
        Long periodo, Double resultado, Long codigo_PsyObjetivoAmbiental,
        Long codigo_PsyPlanEstrategicoAmbiental) throws Exception {
        try {
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setPeriodo(FacesUtils.checkLong(periodo));
            entity.setResultado(FacesUtils.checkDouble(resultado));
            businessDelegatorView.updatePsyEvaluacionPeaObjetivo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyEvaluacionPeaObjetivoView").requestRender();
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

    public InputText getTxtPeriodo() {
        return txtPeriodo;
    }

    public void setTxtPeriodo(InputText txtPeriodo) {
        this.txtPeriodo = txtPeriodo;
    }

    public InputText getTxtResultado() {
        return txtResultado;
    }

    public void setTxtResultado(InputText txtResultado) {
        this.txtResultado = txtResultado;
    }

    public InputText getTxtCodigo_PsyObjetivoAmbiental() {
        return txtCodigo_PsyObjetivoAmbiental;
    }

    public void setTxtCodigo_PsyObjetivoAmbiental(
        InputText txtCodigo_PsyObjetivoAmbiental) {
        this.txtCodigo_PsyObjetivoAmbiental = txtCodigo_PsyObjetivoAmbiental;
    }

    public InputText getTxtCodigo_PsyPlanEstrategicoAmbiental() {
        return txtCodigo_PsyPlanEstrategicoAmbiental;
    }

    public void setTxtCodigo_PsyPlanEstrategicoAmbiental(
        InputText txtCodigo_PsyPlanEstrategicoAmbiental) {
        this.txtCodigo_PsyPlanEstrategicoAmbiental = txtCodigo_PsyPlanEstrategicoAmbiental;
    }

    public InputText getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(InputText txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public List<PsyEvaluacionPeaObjetivoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyEvaluacionPeaObjetivo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(
        List<PsyEvaluacionPeaObjetivoDTO> psyEvaluacionPeaObjetivoDTO) {
        this.data = psyEvaluacionPeaObjetivoDTO;
    }

    public PsyEvaluacionPeaObjetivoDTO getSelectedPsyEvaluacionPeaObjetivo() {
        return selectedPsyEvaluacionPeaObjetivo;
    }

    public void setSelectedPsyEvaluacionPeaObjetivo(
        PsyEvaluacionPeaObjetivoDTO psyEvaluacionPeaObjetivo) {
        this.selectedPsyEvaluacionPeaObjetivo = psyEvaluacionPeaObjetivo;
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
