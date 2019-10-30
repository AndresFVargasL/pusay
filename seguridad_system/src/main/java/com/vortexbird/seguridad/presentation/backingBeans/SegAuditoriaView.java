package com.vortexbird.seguridad.presentation.backingBeans;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;


import org.primefaces.event.RowEditEvent;

import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.SegAuditoriaDTO;
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
public class SegAuditoriaView {
    private InputText txtCampo;
    private InputText txtCodRegistro;
    private InputText txtTabla;
    private InputText txtTipo;
    private InputText txtValorAnterior;
    private InputText txtValorNuevo;
    private InputText txtUsuCodigo_SegUsuario;
    private InputText txtAutCodigo;
    private Calendar txtFecha;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SegAuditoriaDTO> data;
    private SegAuditoriaDTO selectedSegAuditoria;

    public SegAuditoriaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SegAuditoriaDTO segAuditoriaDTO = (SegAuditoriaDTO) e.getObject();

            if (txtCampo == null) {
                txtCampo = new InputText();
            }

            txtCampo.setValue(segAuditoriaDTO.getCampo());

            if (txtCodRegistro == null) {
                txtCodRegistro = new InputText();
            }

            txtCodRegistro.setValue(segAuditoriaDTO.getCodRegistro());

            if (txtTabla == null) {
                txtTabla = new InputText();
            }

            txtTabla.setValue(segAuditoriaDTO.getTabla());

            if (txtTipo == null) {
                txtTipo = new InputText();
            }

            txtTipo.setValue(segAuditoriaDTO.getTipo());

            if (txtValorAnterior == null) {
                txtValorAnterior = new InputText();
            }

            txtValorAnterior.setValue(segAuditoriaDTO.getValorAnterior());

            if (txtValorNuevo == null) {
                txtValorNuevo = new InputText();
            }

            txtValorNuevo.setValue(segAuditoriaDTO.getValorNuevo());

            if (txtUsuCodigo_SegUsuario == null) {
                txtUsuCodigo_SegUsuario = new InputText();
            }

            txtUsuCodigo_SegUsuario.setValue(segAuditoriaDTO.getUsuCodigo_SegUsuario());

            if (txtAutCodigo == null) {
                txtAutCodigo = new InputText();
            }

            txtAutCodigo.setValue(segAuditoriaDTO.getAutCodigo());

            if (txtFecha == null) {
                txtFecha = new Calendar();
            }

            txtFecha.setValue(segAuditoriaDTO.getFecha());

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_clear() {
        if (txtCampo != null) {
            txtCampo.setValue(null);
            txtCampo.setDisabled(true);
        }

        if (txtCodRegistro != null) {
            txtCodRegistro.setValue(null);
            txtCodRegistro.setDisabled(true);
        }

        if (txtTabla != null) {
            txtTabla.setValue(null);
            txtTabla.setDisabled(true);
        }

        if (txtTipo != null) {
            txtTipo.setValue(null);
            txtTipo.setDisabled(true);
        }

        if (txtValorAnterior != null) {
            txtValorAnterior.setValue(null);
            txtValorAnterior.setDisabled(true);
        }

        if (txtValorNuevo != null) {
            txtValorNuevo.setValue(null);
            txtValorNuevo.setDisabled(true);
        }

        if (txtUsuCodigo_SegUsuario != null) {
            txtUsuCodigo_SegUsuario.setValue(null);
            txtUsuCodigo_SegUsuario.setDisabled(true);
        }

        if (txtFecha != null) {
            txtFecha.setValue(null);
            txtFecha.setDisabled(true);
        }

        if (txtAutCodigo != null) {
            txtAutCodigo.setValue(null);
            txtAutCodigo.setDisabled(false);
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

    public void listener_txtFecha() {
        Date inputDate = (Date) txtFecha.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
    }

    public void listener_txtId() {
        SegAuditoria entity = null;

        try {
            Long autCodigo = new Long(txtAutCodigo.getValue().toString());
            entity = BusinessDelegatorView.getSegAuditoria(autCodigo);
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (entity == null) {
            txtCampo.setDisabled(false);
            txtCodRegistro.setDisabled(false);
            txtTabla.setDisabled(false);
            txtTipo.setDisabled(false);
            txtValorAnterior.setDisabled(false);
            txtValorNuevo.setDisabled(false);
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtFecha.setDisabled(false);
            txtAutCodigo.setDisabled(false);
            btnSave.setDisabled(false);
            btnDelete.setDisabled(true);
            btnModify.setDisabled(true);
            btnClear.setDisabled(false);
        } else {
            txtCampo.setValue(entity.getCampo());
            txtCampo.setDisabled(false);
            txtCodRegistro.setValue(entity.getCodRegistro());
            txtCodRegistro.setDisabled(false);
            txtFecha.setValue(entity.getFecha());
            txtFecha.setDisabled(false);
            txtTabla.setValue(entity.getTabla());
            txtTabla.setDisabled(false);
            txtTipo.setValue(entity.getTipo());
            txtTipo.setDisabled(false);
            txtValorAnterior.setValue(entity.getValorAnterior());
            txtValorAnterior.setDisabled(false);
            txtValorNuevo.setValue(entity.getValorNuevo());
            txtValorNuevo.setDisabled(false);
            txtUsuCodigo_SegUsuario.setValue(entity.getSegUsuario()
                                                   .getUsuCodigo());
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtAutCodigo.setValue(entity.getAutCodigo());
            txtAutCodigo.setDisabled(true);
            btnSave.setDisabled(true);
            btnDelete.setDisabled(false);
            btnModify.setDisabled(false);
            btnClear.setDisabled(false);
        }
    }

    public String action_save() {
        try {
            BusinessDelegatorView.saveSegAuditoria(FacesUtils.checkLong(
                    txtAutCodigo), FacesUtils.checkString(txtCampo),
                FacesUtils.checkLong(txtCodRegistro),
                FacesUtils.checkDate(txtFecha),
                FacesUtils.checkString(txtTabla),
                FacesUtils.checkString(txtTipo),
                FacesUtils.checkString(txtValorAnterior),
                FacesUtils.checkString(txtValorNuevo),
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
            BusinessDelegatorView.deleteSegAuditoria(FacesUtils.checkLong(
                    txtAutCodigo));
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            BusinessDelegatorView.updateSegAuditoria(FacesUtils.checkLong(
                    txtAutCodigo), FacesUtils.checkString(txtCampo),
                FacesUtils.checkLong(txtCodRegistro),
                FacesUtils.checkDate(txtFecha),
                FacesUtils.checkString(txtTabla),
                FacesUtils.checkString(txtTipo),
                FacesUtils.checkString(txtValorAnterior),
                FacesUtils.checkString(txtValorNuevo),
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
            if (txtAutCodigo == null) {
                txtAutCodigo = new InputText();
            }

            txtAutCodigo.setValue(selectedSegAuditoria.getAutCodigo());

            action_delete();
            data.remove(selectedSegAuditoria);
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long autCodigo, String campo,
        Long codRegistro, Date fecha, String tabla, String tipo,
        String valorAnterior, String valorNuevo, Long usuCodigo_SegUsuario)
        throws Exception {
        try {
            BusinessDelegatorView.updateSegAuditoria(autCodigo, campo,
                codRegistro, fecha, tabla, tipo, valorAnterior, valorNuevo,
                usuCodigo_SegUsuario);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SegAuditoriaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCampo() {
        return txtCampo;
    }

    public void setTxtCampo(InputText txtCampo) {
        this.txtCampo = txtCampo;
    }

    public InputText getTxtCodRegistro() {
        return txtCodRegistro;
    }

    public void setTxtCodRegistro(InputText txtCodRegistro) {
        this.txtCodRegistro = txtCodRegistro;
    }

    public InputText getTxtTabla() {
        return txtTabla;
    }

    public void setTxtTabla(InputText txtTabla) {
        this.txtTabla = txtTabla;
    }

    public InputText getTxtTipo() {
        return txtTipo;
    }

    public void setTxtTipo(InputText txtTipo) {
        this.txtTipo = txtTipo;
    }

    public InputText getTxtValorAnterior() {
        return txtValorAnterior;
    }

    public void setTxtValorAnterior(InputText txtValorAnterior) {
        this.txtValorAnterior = txtValorAnterior;
    }

    public InputText getTxtValorNuevo() {
        return txtValorNuevo;
    }

    public void setTxtValorNuevo(InputText txtValorNuevo) {
        this.txtValorNuevo = txtValorNuevo;
    }

    public InputText getTxtUsuCodigo_SegUsuario() {
        return txtUsuCodigo_SegUsuario;
    }

    public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
        this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
    }

    public Calendar getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(Calendar txtFecha) {
        this.txtFecha = txtFecha;
    }

    public InputText getTxtAutCodigo() {
        return txtAutCodigo;
    }

    public void setTxtAutCodigo(InputText txtAutCodigo) {
        this.txtAutCodigo = txtAutCodigo;
    }

    public List<SegAuditoriaDTO> getData() {
        try {
            if (data == null) {
                data = BusinessDelegatorView.getDataSegAuditoria();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SegAuditoriaDTO> segAuditoriaDTO) {
        this.data = segAuditoriaDTO;
    }

    public SegAuditoriaDTO getSelectedSegAuditoria() {
        return selectedSegAuditoria;
    }

    public void setSelectedSegAuditoria(SegAuditoriaDTO segAuditoria) {
        this.selectedSegAuditoria = segAuditoria;
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
