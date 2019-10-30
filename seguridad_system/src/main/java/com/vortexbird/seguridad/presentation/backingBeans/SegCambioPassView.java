package com.vortexbird.seguridad.presentation.backingBeans;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;


import org.primefaces.event.RowEditEvent;

import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.SegCambioPassDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SegCambioPassView {
    private InputText txtCapEstado;
    private InputText txtCapToken;
    private InputText txtUsuCodigo_SegUsuario;
    private InputText txtCapCodigo;
    private Calendar txtCapFechaFin;
    private Calendar txtCapFechaIni;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SegCambioPassDTO> data;
    private SegCambioPassDTO selectedSegCambioPass;

    public SegCambioPassView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SegCambioPassDTO segCambioPassDTO = (SegCambioPassDTO) e.getObject();

            if (txtCapEstado == null) {
                txtCapEstado = new InputText();
            }

            txtCapEstado.setValue(segCambioPassDTO.getCapEstado());

            if (txtCapToken == null) {
                txtCapToken = new InputText();
            }

            txtCapToken.setValue(segCambioPassDTO.getCapToken());

            if (txtUsuCodigo_SegUsuario == null) {
                txtUsuCodigo_SegUsuario = new InputText();
            }

            txtUsuCodigo_SegUsuario.setValue(segCambioPassDTO.getUsuCodigo_SegUsuario());

            if (txtCapCodigo == null) {
                txtCapCodigo = new InputText();
            }

            txtCapCodigo.setValue(segCambioPassDTO.getCapCodigo());

            if (txtCapFechaFin == null) {
                txtCapFechaFin = new Calendar();
            }

            txtCapFechaFin.setValue(segCambioPassDTO.getCapFechaFin());

            if (txtCapFechaIni == null) {
                txtCapFechaIni = new Calendar();
            }

            txtCapFechaIni.setValue(segCambioPassDTO.getCapFechaIni());

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_clear() {
        if (txtCapEstado != null) {
            txtCapEstado.setValue(null);
            txtCapEstado.setDisabled(true);
        }

        if (txtCapToken != null) {
            txtCapToken.setValue(null);
            txtCapToken.setDisabled(true);
        }

        if (txtUsuCodigo_SegUsuario != null) {
            txtUsuCodigo_SegUsuario.setValue(null);
            txtUsuCodigo_SegUsuario.setDisabled(true);
        }

        if (txtCapFechaFin != null) {
            txtCapFechaFin.setValue(null);
            txtCapFechaFin.setDisabled(true);
        }

        if (txtCapFechaIni != null) {
            txtCapFechaIni.setValue(null);
            txtCapFechaIni.setDisabled(true);
        }

        if (txtCapCodigo != null) {
            txtCapCodigo.setValue(null);
            txtCapCodigo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        if (btnModify != null) {
            btnModify.setDisabled(true);
        }

        if (btnClear != null) {
            btnClear.setDisabled(false);
        }

        return "";
    }

    public void listener_txtCapFechaFin() {
        Date inputDate = (Date) txtCapFechaFin.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       
    }

    public void listener_txtCapFechaIni() {
        Date inputDate = (Date) txtCapFechaIni.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      
    }

    public void listener_txtId() {
        SegCambioPass entity = null;

        try {
            Long capCodigo = new Long(txtCapCodigo.getValue().toString());
            entity = BusinessDelegatorView.getSegCambioPass(capCodigo);
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (entity == null) {
            txtCapEstado.setDisabled(false);
            txtCapToken.setDisabled(false);
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtCapFechaFin.setDisabled(false);
            txtCapFechaIni.setDisabled(false);
            txtCapCodigo.setDisabled(false);
            btnSave.setDisabled(false);
            btnDelete.setDisabled(true);
            btnModify.setDisabled(true);
            btnClear.setDisabled(false);
        } else {
            txtCapEstado.setValue(entity.getCapEstado());
            txtCapEstado.setDisabled(false);
            txtCapFechaFin.setValue(entity.getCapFechaFin());
            txtCapFechaFin.setDisabled(false);
            txtCapFechaIni.setValue(entity.getCapFechaIni());
            txtCapFechaIni.setDisabled(false);
            txtCapToken.setValue(entity.getCapToken());
            txtCapToken.setDisabled(false);
            txtUsuCodigo_SegUsuario.setValue(entity.getSegUsuario()
                                                   .getUsuCodigo());
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtCapCodigo.setValue(entity.getCapCodigo());
            txtCapCodigo.setDisabled(true);
            btnSave.setDisabled(true);
            btnDelete.setDisabled(false);
            btnModify.setDisabled(false);
            btnClear.setDisabled(false);
        }
    }

    public String action_save() {
        try {
            BusinessDelegatorView.saveSegCambioPass(FacesUtils.checkLong(
                    txtCapCodigo), FacesUtils.checkString(txtCapEstado),
                FacesUtils.checkDate(txtCapFechaFin),
                FacesUtils.checkDate(txtCapFechaIni),
                FacesUtils.checkString(txtCapToken),
                FacesUtils.checkLong(txtUsuCodigo_SegUsuario));
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete() {
        try {
            BusinessDelegatorView.deleteSegCambioPass(FacesUtils.checkLong(
                    txtCapCodigo));
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            BusinessDelegatorView.updateSegCambioPass(FacesUtils.checkLong(
                    txtCapCodigo), FacesUtils.checkString(txtCapEstado),
                FacesUtils.checkDate(txtCapFechaFin),
                FacesUtils.checkDate(txtCapFechaIni),
                FacesUtils.checkString(txtCapToken),
                FacesUtils.checkLong(txtUsuCodigo_SegUsuario));
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
            action_clear();
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String actionDeleteDataTableEditable() {
        try {
            if (txtCapCodigo == null) {
                txtCapCodigo = new InputText();
            }

            txtCapCodigo.setValue(selectedSegCambioPass.getCapCodigo());

            action_delete();
            data.remove(selectedSegCambioPass);
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long capCodigo, String capEstado,
        Date capFechaFin, Date capFechaIni, String capToken,
        Long usuCodigo_SegUsuario) throws Exception {
        try {
            BusinessDelegatorView.updateSegCambioPass(capCodigo, capEstado,
                capFechaFin, capFechaIni, capToken, usuCodigo_SegUsuario);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SegCambioPassView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCapEstado() {
        return txtCapEstado;
    }

    public void setTxtCapEstado(InputText txtCapEstado) {
        this.txtCapEstado = txtCapEstado;
    }

    public InputText getTxtCapToken() {
        return txtCapToken;
    }

    public void setTxtCapToken(InputText txtCapToken) {
        this.txtCapToken = txtCapToken;
    }

    public InputText getTxtUsuCodigo_SegUsuario() {
        return txtUsuCodigo_SegUsuario;
    }

    public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
        this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
    }

    public Calendar getTxtCapFechaFin() {
        return txtCapFechaFin;
    }

    public void setTxtCapFechaFin(Calendar txtCapFechaFin) {
        this.txtCapFechaFin = txtCapFechaFin;
    }

    public Calendar getTxtCapFechaIni() {
        return txtCapFechaIni;
    }

    public void setTxtCapFechaIni(Calendar txtCapFechaIni) {
        this.txtCapFechaIni = txtCapFechaIni;
    }

    public InputText getTxtCapCodigo() {
        return txtCapCodigo;
    }

    public void setTxtCapCodigo(InputText txtCapCodigo) {
        this.txtCapCodigo = txtCapCodigo;
    }

    public List<SegCambioPassDTO> getData() {

        return data;
    }

    public void setData(List<SegCambioPassDTO> segCambioPassDTO) {
        this.data = segCambioPassDTO;
    }

    public SegCambioPassDTO getSelectedSegCambioPass() {
        return selectedSegCambioPass;
    }

    public void setSelectedSegCambioPass(SegCambioPassDTO segCambioPass) {
        this.selectedSegCambioPass = segCambioPass;
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
}
