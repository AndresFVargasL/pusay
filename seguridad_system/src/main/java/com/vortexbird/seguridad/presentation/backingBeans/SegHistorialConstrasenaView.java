package com.vortexbird.seguridad.presentation.backingBeans;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.RowEditEvent;

import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.SegHistorialConstrasenaDTO;
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
public class SegHistorialConstrasenaView {
    private InputText txtHcoConstrasena;
    private InputText txtHcoDetalleCambio;
    private InputText txtUsuCodigo_SegUsuario;
    private InputText txtHcoCodigo;
    private Calendar txtHcoFechaCambio;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SegHistorialConstrasenaDTO> data;
    private SegHistorialConstrasenaDTO selectedSegHistorialConstrasena;

    public SegHistorialConstrasenaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SegHistorialConstrasenaDTO segHistorialConstrasenaDTO = (SegHistorialConstrasenaDTO) e.getObject();

            if (txtHcoConstrasena == null) {
                txtHcoConstrasena = new InputText();
            }

            txtHcoConstrasena.setValue(segHistorialConstrasenaDTO.getHcoConstrasena());

            if (txtHcoDetalleCambio == null) {
                txtHcoDetalleCambio = new InputText();
            }

            txtHcoDetalleCambio.setValue(segHistorialConstrasenaDTO.getHcoDetalleCambio());

            if (txtUsuCodigo_SegUsuario == null) {
                txtUsuCodigo_SegUsuario = new InputText();
            }

            txtUsuCodigo_SegUsuario.setValue(segHistorialConstrasenaDTO.getUsuCodigo_SegUsuario());

            if (txtHcoCodigo == null) {
                txtHcoCodigo = new InputText();
            }

            txtHcoCodigo.setValue(segHistorialConstrasenaDTO.getHcoCodigo());

            if (txtHcoFechaCambio == null) {
                txtHcoFechaCambio = new Calendar();
            }

            txtHcoFechaCambio.setValue(segHistorialConstrasenaDTO.getHcoFechaCambio());

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_clear() {
        if (txtHcoConstrasena != null) {
            txtHcoConstrasena.setValue(null);
            txtHcoConstrasena.setDisabled(true);
        }

        if (txtHcoDetalleCambio != null) {
            txtHcoDetalleCambio.setValue(null);
            txtHcoDetalleCambio.setDisabled(true);
        }

        if (txtUsuCodigo_SegUsuario != null) {
            txtUsuCodigo_SegUsuario.setValue(null);
            txtUsuCodigo_SegUsuario.setDisabled(true);
        }

        if (txtHcoFechaCambio != null) {
            txtHcoFechaCambio.setValue(null);
            txtHcoFechaCambio.setDisabled(true);
        }

        if (txtHcoCodigo != null) {
            txtHcoCodigo.setValue(null);
            txtHcoCodigo.setDisabled(false);
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

    public void listener_txtHcoFechaCambio() {
        Date inputDate = (Date) txtHcoFechaCambio.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       
    }

    public void listener_txtId() {
        SegHistorialConstrasena entity = null;

        try {
            Long hcoCodigo = new Long(txtHcoCodigo.getValue().toString());
            entity = BusinessDelegatorView.getSegHistorialConstrasena(hcoCodigo);
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (entity == null) {
            txtHcoConstrasena.setDisabled(false);
            txtHcoDetalleCambio.setDisabled(false);
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtHcoFechaCambio.setDisabled(false);
            txtHcoCodigo.setDisabled(false);
            btnSave.setDisabled(false);
            btnDelete.setDisabled(true);
            btnModify.setDisabled(true);
            btnClear.setDisabled(false);
        } else {
            txtHcoConstrasena.setValue(entity.getHcoConstrasena());
            txtHcoConstrasena.setDisabled(false);
            txtHcoDetalleCambio.setValue(entity.getHcoDetalleCambio());
            txtHcoDetalleCambio.setDisabled(false);
            txtHcoFechaCambio.setValue(entity.getHcoFechaCambio());
            txtHcoFechaCambio.setDisabled(false);
            txtUsuCodigo_SegUsuario.setValue(entity.getSegUsuario()
                                                   .getUsuCodigo());
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtHcoCodigo.setValue(entity.getHcoCodigo());
            txtHcoCodigo.setDisabled(true);
            btnSave.setDisabled(true);
            btnDelete.setDisabled(false);
            btnModify.setDisabled(false);
            btnClear.setDisabled(false);
        }
    }

    public String action_save() {
        try {
            BusinessDelegatorView.saveSegHistorialConstrasena(FacesUtils.checkLong(
                    txtHcoCodigo), FacesUtils.checkString(txtHcoConstrasena),
                FacesUtils.checkString(txtHcoDetalleCambio),
                FacesUtils.checkDate(txtHcoFechaCambio),
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
            BusinessDelegatorView.deleteSegHistorialConstrasena(FacesUtils.checkLong(
                    txtHcoCodigo));
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            BusinessDelegatorView.updateSegHistorialConstrasena(FacesUtils.checkLong(
                    txtHcoCodigo), FacesUtils.checkString(txtHcoConstrasena),
                FacesUtils.checkString(txtHcoDetalleCambio),
                FacesUtils.checkDate(txtHcoFechaCambio),
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
            if (txtHcoCodigo == null) {
                txtHcoCodigo = new InputText();
            }

            txtHcoCodigo.setValue(selectedSegHistorialConstrasena.getHcoCodigo());

            action_delete();
            data.remove(selectedSegHistorialConstrasena);
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long hcoCodigo, String hcoConstrasena,
        String hcoDetalleCambio, Date hcoFechaCambio, Long usuCodigo_SegUsuario)
        throws Exception {
        try {
            BusinessDelegatorView.updateSegHistorialConstrasena(hcoCodigo,
                hcoConstrasena, hcoDetalleCambio, hcoFechaCambio,
                usuCodigo_SegUsuario);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SegHistorialConstrasenaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtHcoConstrasena() {
        return txtHcoConstrasena;
    }

    public void setTxtHcoConstrasena(InputText txtHcoConstrasena) {
        this.txtHcoConstrasena = txtHcoConstrasena;
    }

    public InputText getTxtHcoDetalleCambio() {
        return txtHcoDetalleCambio;
    }

    public void setTxtHcoDetalleCambio(InputText txtHcoDetalleCambio) {
        this.txtHcoDetalleCambio = txtHcoDetalleCambio;
    }

    public InputText getTxtUsuCodigo_SegUsuario() {
        return txtUsuCodigo_SegUsuario;
    }

    public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
        this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
    }

    public Calendar getTxtHcoFechaCambio() {
        return txtHcoFechaCambio;
    }

    public void setTxtHcoFechaCambio(Calendar txtHcoFechaCambio) {
        this.txtHcoFechaCambio = txtHcoFechaCambio;
    }

    public InputText getTxtHcoCodigo() {
        return txtHcoCodigo;
    }

    public void setTxtHcoCodigo(InputText txtHcoCodigo) {
        this.txtHcoCodigo = txtHcoCodigo;
    }

    public List<SegHistorialConstrasenaDTO> getData() {

        return data;
    }

    public void setData(
        List<SegHistorialConstrasenaDTO> segHistorialConstrasenaDTO) {
        this.data = segHistorialConstrasenaDTO;
    }

    public SegHistorialConstrasenaDTO getSelectedSegHistorialConstrasena() {
        return selectedSegHistorialConstrasena;
    }

    public void setSelectedSegHistorialConstrasena(
        SegHistorialConstrasenaDTO segHistorialConstrasena) {
        this.selectedSegHistorialConstrasena = segHistorialConstrasena;
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
