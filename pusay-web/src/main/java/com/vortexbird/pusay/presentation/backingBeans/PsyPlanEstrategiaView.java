package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyPlanEstrategiaDTO;
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
public class PsyPlanEstrategiaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyPlanEstrategiaView.class);
    private InputText txtEstadoRegistro;
    private InputText txtDmaeCodigo_PsyDetalleMapaEstrategico;
    private InputText txtPlacCodigo_PsyPlanAccion;
    private InputText txtPlesCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyPlanEstrategiaDTO> data;
    private PsyPlanEstrategiaDTO selectedPsyPlanEstrategia;
    private PsyPlanEstrategia entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyPlanEstrategiaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyPlanEstrategiaDTO psyPlanEstrategiaDTO = (PsyPlanEstrategiaDTO) e.getObject();

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyPlanEstrategiaDTO.getEstadoRegistro());

            if (txtDmaeCodigo_PsyDetalleMapaEstrategico == null) {
                txtDmaeCodigo_PsyDetalleMapaEstrategico = new InputText();
            }

            txtDmaeCodigo_PsyDetalleMapaEstrategico.setValue(psyPlanEstrategiaDTO.getDmaeCodigo_PsyDetalleMapaEstrategico());

            if (txtPlacCodigo_PsyPlanAccion == null) {
                txtPlacCodigo_PsyPlanAccion = new InputText();
            }

            txtPlacCodigo_PsyPlanAccion.setValue(psyPlanEstrategiaDTO.getProgCodigo_PsyPrograma());

            if (txtPlesCodigo == null) {
                txtPlesCodigo = new InputText();
            }

            txtPlesCodigo.setValue(psyPlanEstrategiaDTO.getPlesCodigo());

            Long plesCodigo = FacesUtils.checkLong(txtPlesCodigo);
            entity = businessDelegatorView.getPsyPlanEstrategia(plesCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyPlanEstrategia = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyPlanEstrategia = null;

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtDmaeCodigo_PsyDetalleMapaEstrategico != null) {
            txtDmaeCodigo_PsyDetalleMapaEstrategico.setValue(null);
            txtDmaeCodigo_PsyDetalleMapaEstrategico.setDisabled(true);
        }

        if (txtPlacCodigo_PsyPlanAccion != null) {
            txtPlacCodigo_PsyPlanAccion.setValue(null);
            txtPlacCodigo_PsyPlanAccion.setDisabled(true);
        }

        if (txtPlesCodigo != null) {
            txtPlesCodigo.setValue(null);
            txtPlesCodigo.setDisabled(false);
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
            Long plesCodigo = FacesUtils.checkLong(txtPlesCodigo);
            entity = (plesCodigo != null)
                ? businessDelegatorView.getPsyPlanEstrategia(plesCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoRegistro.setDisabled(false);
            txtDmaeCodigo_PsyDetalleMapaEstrategico.setDisabled(false);
            txtPlacCodigo_PsyPlanAccion.setDisabled(false);
            txtPlesCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtDmaeCodigo_PsyDetalleMapaEstrategico.setValue(entity.getPsyDetalleMapaEstrategico()
                                                                   .getDmaeCodigo());
            txtDmaeCodigo_PsyDetalleMapaEstrategico.setDisabled(false);
            txtPlacCodigo_PsyPlanAccion.setValue(entity.getPsyPrograma().getProgCodigo());
            txtPlacCodigo_PsyPlanAccion.setDisabled(false);
            txtPlesCodigo.setValue(entity.getPlesCodigo());
            txtPlesCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyPlanEstrategia = (PsyPlanEstrategiaDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedPsyPlanEstrategia"));
        txtEstadoRegistro.setValue(selectedPsyPlanEstrategia.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtDmaeCodigo_PsyDetalleMapaEstrategico.setValue(selectedPsyPlanEstrategia.getDmaeCodigo_PsyDetalleMapaEstrategico());
        txtDmaeCodigo_PsyDetalleMapaEstrategico.setDisabled(false);
        txtPlacCodigo_PsyPlanAccion.setValue(selectedPsyPlanEstrategia.getProgCodigo_PsyPrograma());
        txtPlacCodigo_PsyPlanAccion.setDisabled(false);
        txtPlesCodigo.setValue(selectedPsyPlanEstrategia.getPlesCodigo());
        txtPlesCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyPlanEstrategia == null) && (entity == null)) {
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
            entity = new PsyPlanEstrategia();

            Long plesCodigo = FacesUtils.checkLong(txtPlesCodigo);

            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setPlesCodigo(plesCodigo);
            entity.setPsyDetalleMapaEstrategico((FacesUtils.checkLong(
                    txtDmaeCodigo_PsyDetalleMapaEstrategico) != null)
                ? businessDelegatorView.getPsyDetalleMapaEstrategico(
                    FacesUtils.checkLong(
                        txtDmaeCodigo_PsyDetalleMapaEstrategico)) : null);
            entity.setPsyPrograma((FacesUtils.checkLong(
                    txtPlacCodigo_PsyPlanAccion) != null)
                ? businessDelegatorView.getPsyPrograma(FacesUtils.checkLong(
                        txtPlacCodigo_PsyPlanAccion)) : null);
            //businessDelegatorView.savePsyPlanEstrategia(entity);
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
                Long plesCodigo = new Long(selectedPsyPlanEstrategia.getPlesCodigo());
                entity = businessDelegatorView.getPsyPlanEstrategia(plesCodigo);
            }

            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setPsyDetalleMapaEstrategico((FacesUtils.checkLong(
                    txtDmaeCodigo_PsyDetalleMapaEstrategico) != null)
                ? businessDelegatorView.getPsyDetalleMapaEstrategico(
                    FacesUtils.checkLong(
                        txtDmaeCodigo_PsyDetalleMapaEstrategico)) : null);
            entity.setPsyPrograma((FacesUtils.checkLong(
                    txtPlacCodigo_PsyPlanAccion) != null)
                ? businessDelegatorView.getPsyPrograma(FacesUtils.checkLong(
                        txtPlacCodigo_PsyPlanAccion)) : null);
            //businessDelegatorView.updatePsyPlanEstrategia(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyPlanEstrategia = (PsyPlanEstrategiaDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedPsyPlanEstrategia"));

            Long plesCodigo = new Long(selectedPsyPlanEstrategia.getPlesCodigo());
            entity = businessDelegatorView.getPsyPlanEstrategia(plesCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long plesCodigo = FacesUtils.checkLong(txtPlesCodigo);
            entity = businessDelegatorView.getPsyPlanEstrategia(plesCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyPlanEstrategia(entity);
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
            selectedPsyPlanEstrategia = (PsyPlanEstrategiaDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedPsyPlanEstrategia"));

            Long plesCodigo = new Long(selectedPsyPlanEstrategia.getPlesCodigo());
            entity = businessDelegatorView.getPsyPlanEstrategia(plesCodigo);
            businessDelegatorView.deletePsyPlanEstrategia(entity);
            data.remove(selectedPsyPlanEstrategia);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estadoRegistro, Long plesCodigo,
        Long dmaeCodigo_PsyDetalleMapaEstrategico, Long placCodigo_PsyPlanAccion)
        throws Exception {
        try {
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            //businessDelegatorView.updatePsyPlanEstrategia(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyPlanEstrategiaView").requestRender();
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

    public InputText getTxtDmaeCodigo_PsyDetalleMapaEstrategico() {
        return txtDmaeCodigo_PsyDetalleMapaEstrategico;
    }

    public void setTxtDmaeCodigo_PsyDetalleMapaEstrategico(
        InputText txtDmaeCodigo_PsyDetalleMapaEstrategico) {
        this.txtDmaeCodigo_PsyDetalleMapaEstrategico = txtDmaeCodigo_PsyDetalleMapaEstrategico;
    }

    public InputText getTxtPlacCodigo_PsyPlanAccion() {
        return txtPlacCodigo_PsyPlanAccion;
    }

    public void setTxtPlacCodigo_PsyPlanAccion(
        InputText txtPlacCodigo_PsyPlanAccion) {
        this.txtPlacCodigo_PsyPlanAccion = txtPlacCodigo_PsyPlanAccion;
    }

    public InputText getTxtPlesCodigo() {
        return txtPlesCodigo;
    }

    public void setTxtPlesCodigo(InputText txtPlesCodigo) {
        this.txtPlesCodigo = txtPlesCodigo;
    }

    public List<PsyPlanEstrategiaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyPlanEstrategia();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyPlanEstrategiaDTO> psyPlanEstrategiaDTO) {
        this.data = psyPlanEstrategiaDTO;
    }

    public PsyPlanEstrategiaDTO getSelectedPsyPlanEstrategia() {
        return selectedPsyPlanEstrategia;
    }

    public void setSelectedPsyPlanEstrategia(
        PsyPlanEstrategiaDTO psyPlanEstrategia) {
        this.selectedPsyPlanEstrategia = psyPlanEstrategia;
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
