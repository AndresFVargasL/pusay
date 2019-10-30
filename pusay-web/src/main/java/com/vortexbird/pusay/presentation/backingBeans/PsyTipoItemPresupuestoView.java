package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyTipoItemPresupuestoDTO;
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
public class PsyTipoItemPresupuestoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyTipoItemPresupuestoView.class);
    private static final String ESTADO_REGISTRO_ACTIVO ="A";
    private static final String ESTADO_REGISTRO_INACTIVO = "I";
    private InputText txtDescripcion;
    private InputText txtEstadoRegistro;
    private InputText txtNombre;
    private InputText txtTiipCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyTipoItemPresupuestoDTO> data;
    private PsyTipoItemPresupuestoDTO selectedPsyTipoItemPresupuesto;
    private PsyTipoItemPresupuesto entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyTipoItemPresupuestoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyTipoItemPresupuestoDTO psyTipoItemPresupuestoDTO = (PsyTipoItemPresupuestoDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(psyTipoItemPresupuestoDTO.getDescripcion());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyTipoItemPresupuestoDTO.getEstadoRegistro());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(psyTipoItemPresupuestoDTO.getNombre());

            if (txtTiipCodigo == null) {
                txtTiipCodigo = new InputText();
            }

            txtTiipCodigo.setValue(psyTipoItemPresupuestoDTO.getTiipCodigo());

            Long tiipCodigo = FacesUtils.checkLong(txtTiipCodigo);
            entity = businessDelegatorView.getPsyTipoItemPresupuesto(tiipCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        txtEstadoRegistro.setValue("Activo");
        selectedPsyTipoItemPresupuesto = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyTipoItemPresupuesto = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
        }

        if (txtTiipCodigo != null) {
            txtTiipCodigo.setValue(null);
            txtTiipCodigo.setDisabled(false);
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
            Long tiipCodigo = FacesUtils.checkLong(txtTiipCodigo);
            entity = (tiipCodigo != null)
                ? businessDelegatorView.getPsyTipoItemPresupuesto(tiipCodigo)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setDisabled(false);
            txtTiipCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtTiipCodigo.setValue(entity.getTiipCodigo());
            txtTiipCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyTipoItemPresupuesto = (PsyTipoItemPresupuestoDTO) (evt.getComponent()
                                                                         .getAttributes()
                                                                         .get("selectedPsyTipoItemPresupuesto"));
        txtDescripcion.setValue(selectedPsyTipoItemPresupuesto.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyTipoItemPresupuesto.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(true);
        txtNombre.setValue(selectedPsyTipoItemPresupuesto.getNombre());
        txtNombre.setDisabled(false);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyTipoItemPresupuesto == null) && (entity == null)) {
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
            entity = new PsyTipoItemPresupuesto();

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.savePsyTipoItemPresupuesto(entity);
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
                Long tiipCodigo = new Long(selectedPsyTipoItemPresupuesto.getTiipCodigo());
                entity = businessDelegatorView.getPsyTipoItemPresupuesto(tiipCodigo);
            }

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.updatePsyTipoItemPresupuesto(entity);
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
            selectedPsyTipoItemPresupuesto = (PsyTipoItemPresupuestoDTO) (evt.getComponent()
                                                                             .getAttributes()
                                                                             .get("selectedPsyTipoItemPresupuesto"));

            Long tiipCodigo = new Long(selectedPsyTipoItemPresupuesto.getTiipCodigo());
            entity = businessDelegatorView.getPsyTipoItemPresupuesto(tiipCodigo);
            action_delete();
            data=null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long tiipCodigo = FacesUtils.checkLong(txtTiipCodigo);
            entity = businessDelegatorView.getPsyTipoItemPresupuesto(tiipCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyTipoItemPresupuesto(entity);
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
            selectedPsyTipoItemPresupuesto = (PsyTipoItemPresupuestoDTO) (evt.getComponent()
                                                                             .getAttributes()
                                                                             .get("selectedPsyTipoItemPresupuesto"));

            Long tiipCodigo = new Long(selectedPsyTipoItemPresupuesto.getTiipCodigo());
            entity = businessDelegatorView.getPsyTipoItemPresupuesto(tiipCodigo);
            businessDelegatorView.deletePsyTipoItemPresupuesto(entity);
            data.remove(selectedPsyTipoItemPresupuesto);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcion,
        String estadoRegistro, String nombre, Long tiipCodigo)
        throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updatePsyTipoItemPresupuesto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyTipoItemPresupuestoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(InputText txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
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

    public InputText getTxtTiipCodigo() {
        return txtTiipCodigo;
    }

    public void setTxtTiipCodigo(InputText txtTiipCodigo) {
        this.txtTiipCodigo = txtTiipCodigo;
    }

    public List<PsyTipoItemPresupuestoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyTipoItemPresupuesto();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(
        List<PsyTipoItemPresupuestoDTO> psyTipoItemPresupuestoDTO) {
        this.data = psyTipoItemPresupuestoDTO;
    }

    public PsyTipoItemPresupuestoDTO getSelectedPsyTipoItemPresupuesto() {
        return selectedPsyTipoItemPresupuesto;
    }

    public void setSelectedPsyTipoItemPresupuesto(
        PsyTipoItemPresupuestoDTO psyTipoItemPresupuesto) {
        this.selectedPsyTipoItemPresupuesto = psyTipoItemPresupuesto;
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
