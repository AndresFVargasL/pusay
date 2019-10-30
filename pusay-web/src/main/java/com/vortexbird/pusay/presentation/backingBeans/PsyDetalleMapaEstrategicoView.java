package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyDetalleMapaEstrategicoDTO;
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
public class PsyDetalleMapaEstrategicoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyDetalleMapaEstrategicoView.class);
    private InputText txtEstadoRegistro;
    private InputText txtMaesCodigo_PsyMapaEstrategico;
    private InputText txtMacoCodigo_PsyMatrizCorrelacion;
    private InputText txtDmaeCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyDetalleMapaEstrategicoDTO> data;
    private PsyDetalleMapaEstrategicoDTO selectedPsyDetalleMapaEstrategico;
    private PsyDetalleMapaEstrategico entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyDetalleMapaEstrategicoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyDetalleMapaEstrategicoDTO psyDetalleMapaEstrategicoDTO = (PsyDetalleMapaEstrategicoDTO) e.getObject();

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyDetalleMapaEstrategicoDTO.getEstadoRegistro());

            if (txtMaesCodigo_PsyMapaEstrategico == null) {
                txtMaesCodigo_PsyMapaEstrategico = new InputText();
            }

            txtMaesCodigo_PsyMapaEstrategico.setValue(psyDetalleMapaEstrategicoDTO.getMaesCodigo_PsyMapaEstrategico());

            if (txtMacoCodigo_PsyMatrizCorrelacion == null) {
                txtMacoCodigo_PsyMatrizCorrelacion = new InputText();
            }

            txtMacoCodigo_PsyMatrizCorrelacion.setValue(psyDetalleMapaEstrategicoDTO.getMacoCodigo_PsyMatrizCorrelacion());

            if (txtDmaeCodigo == null) {
                txtDmaeCodigo = new InputText();
            }

            txtDmaeCodigo.setValue(psyDetalleMapaEstrategicoDTO.getDmaeCodigo());

            Long dmaeCodigo = FacesUtils.checkLong(txtDmaeCodigo);
            entity = businessDelegatorView.getPsyDetalleMapaEstrategico(dmaeCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyDetalleMapaEstrategico = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyDetalleMapaEstrategico = null;

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtMaesCodigo_PsyMapaEstrategico != null) {
            txtMaesCodigo_PsyMapaEstrategico.setValue(null);
            txtMaesCodigo_PsyMapaEstrategico.setDisabled(true);
        }

        if (txtMacoCodigo_PsyMatrizCorrelacion != null) {
            txtMacoCodigo_PsyMatrizCorrelacion.setValue(null);
            txtMacoCodigo_PsyMatrizCorrelacion.setDisabled(true);
        }

        if (txtDmaeCodigo != null) {
            txtDmaeCodigo.setValue(null);
            txtDmaeCodigo.setDisabled(false);
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
            Long dmaeCodigo = FacesUtils.checkLong(txtDmaeCodigo);
            entity = (dmaeCodigo != null)
                ? businessDelegatorView.getPsyDetalleMapaEstrategico(dmaeCodigo)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoRegistro.setDisabled(false);
            txtMaesCodigo_PsyMapaEstrategico.setDisabled(false);
            txtMacoCodigo_PsyMatrizCorrelacion.setDisabled(false);
            txtDmaeCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtMaesCodigo_PsyMapaEstrategico.setValue(entity.getPsyMapaEstrategico()
                                                            .getMaesCodigo());
            txtMaesCodigo_PsyMapaEstrategico.setDisabled(false);
            txtMacoCodigo_PsyMatrizCorrelacion.setValue(entity.getPsyMatrizCorrelacion()
                                                              .getMacoCodigo());
            txtMacoCodigo_PsyMatrizCorrelacion.setDisabled(false);
            txtDmaeCodigo.setValue(entity.getDmaeCodigo());
            txtDmaeCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyDetalleMapaEstrategico = (PsyDetalleMapaEstrategicoDTO) (evt.getComponent()
                                                                               .getAttributes()
                                                                               .get("selectedPsyDetalleMapaEstrategico"));
        txtEstadoRegistro.setValue(selectedPsyDetalleMapaEstrategico.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtMaesCodigo_PsyMapaEstrategico.setValue(selectedPsyDetalleMapaEstrategico.getMaesCodigo_PsyMapaEstrategico());
        txtMaesCodigo_PsyMapaEstrategico.setDisabled(false);
        txtMacoCodigo_PsyMatrizCorrelacion.setValue(selectedPsyDetalleMapaEstrategico.getMacoCodigo_PsyMatrizCorrelacion());
        txtMacoCodigo_PsyMatrizCorrelacion.setDisabled(false);
        txtDmaeCodigo.setValue(selectedPsyDetalleMapaEstrategico.getDmaeCodigo());
        txtDmaeCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyDetalleMapaEstrategico == null) &&
                    (entity == null)) {
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
            entity = new PsyDetalleMapaEstrategico();

            Long dmaeCodigo = FacesUtils.checkLong(txtDmaeCodigo);

            entity.setDmaeCodigo(dmaeCodigo);
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setPsyMapaEstrategico((FacesUtils.checkLong(
                    txtMaesCodigo_PsyMapaEstrategico) != null)
                ? businessDelegatorView.getPsyMapaEstrategico(
                    FacesUtils.checkLong(txtMaesCodigo_PsyMapaEstrategico)) : null);
            entity.setPsyMatrizCorrelacion((FacesUtils.checkLong(
                    txtMacoCodigo_PsyMatrizCorrelacion) != null)
                ? businessDelegatorView.getPsyMatrizCorrelacion(
                    FacesUtils.checkLong(txtMacoCodigo_PsyMatrizCorrelacion))
                : null);
            businessDelegatorView.savePsyDetalleMapaEstrategico(entity);
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
                Long dmaeCodigo = new Long(selectedPsyDetalleMapaEstrategico.getDmaeCodigo());
                entity = businessDelegatorView.getPsyDetalleMapaEstrategico(dmaeCodigo);
            }

            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setPsyMapaEstrategico((FacesUtils.checkLong(
                    txtMaesCodigo_PsyMapaEstrategico) != null)
                ? businessDelegatorView.getPsyMapaEstrategico(
                    FacesUtils.checkLong(txtMaesCodigo_PsyMapaEstrategico)) : null);
            entity.setPsyMatrizCorrelacion((FacesUtils.checkLong(
                    txtMacoCodigo_PsyMatrizCorrelacion) != null)
                ? businessDelegatorView.getPsyMatrizCorrelacion(
                    FacesUtils.checkLong(txtMacoCodigo_PsyMatrizCorrelacion))
                : null);
            businessDelegatorView.updatePsyDetalleMapaEstrategico(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyDetalleMapaEstrategico = (PsyDetalleMapaEstrategicoDTO) (evt.getComponent()
                                                                                   .getAttributes()
                                                                                   .get("selectedPsyDetalleMapaEstrategico"));

            Long dmaeCodigo = new Long(selectedPsyDetalleMapaEstrategico.getDmaeCodigo());
            entity = businessDelegatorView.getPsyDetalleMapaEstrategico(dmaeCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long dmaeCodigo = FacesUtils.checkLong(txtDmaeCodigo);
            entity = businessDelegatorView.getPsyDetalleMapaEstrategico(dmaeCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyDetalleMapaEstrategico(entity);
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
            selectedPsyDetalleMapaEstrategico = (PsyDetalleMapaEstrategicoDTO) (evt.getComponent()
                                                                                   .getAttributes()
                                                                                   .get("selectedPsyDetalleMapaEstrategico"));

            Long dmaeCodigo = new Long(selectedPsyDetalleMapaEstrategico.getDmaeCodigo());
            entity = businessDelegatorView.getPsyDetalleMapaEstrategico(dmaeCodigo);
            businessDelegatorView.deletePsyDetalleMapaEstrategico(entity);
            data.remove(selectedPsyDetalleMapaEstrategico);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long dmaeCodigo, String estadoRegistro,
        Long maesCodigo_PsyMapaEstrategico, Long macoCodigo_PsyMatrizCorrelacion)
        throws Exception {
        try {
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            businessDelegatorView.updatePsyDetalleMapaEstrategico(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyDetalleMapaEstrategicoView").requestRender();
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

    public InputText getTxtMaesCodigo_PsyMapaEstrategico() {
        return txtMaesCodigo_PsyMapaEstrategico;
    }

    public void setTxtMaesCodigo_PsyMapaEstrategico(
        InputText txtMaesCodigo_PsyMapaEstrategico) {
        this.txtMaesCodigo_PsyMapaEstrategico = txtMaesCodigo_PsyMapaEstrategico;
    }

    public InputText getTxtMacoCodigo_PsyMatrizCorrelacion() {
        return txtMacoCodigo_PsyMatrizCorrelacion;
    }

    public void setTxtMacoCodigo_PsyMatrizCorrelacion(
        InputText txtMacoCodigo_PsyMatrizCorrelacion) {
        this.txtMacoCodigo_PsyMatrizCorrelacion = txtMacoCodigo_PsyMatrizCorrelacion;
    }

    public InputText getTxtDmaeCodigo() {
        return txtDmaeCodigo;
    }

    public void setTxtDmaeCodigo(InputText txtDmaeCodigo) {
        this.txtDmaeCodigo = txtDmaeCodigo;
    }

    public List<PsyDetalleMapaEstrategicoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyDetalleMapaEstrategico();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(
        List<PsyDetalleMapaEstrategicoDTO> psyDetalleMapaEstrategicoDTO) {
        this.data = psyDetalleMapaEstrategicoDTO;
    }

    public PsyDetalleMapaEstrategicoDTO getSelectedPsyDetalleMapaEstrategico() {
        return selectedPsyDetalleMapaEstrategico;
    }

    public void setSelectedPsyDetalleMapaEstrategico(
        PsyDetalleMapaEstrategicoDTO psyDetalleMapaEstrategico) {
        this.selectedPsyDetalleMapaEstrategico = psyDetalleMapaEstrategico;
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
