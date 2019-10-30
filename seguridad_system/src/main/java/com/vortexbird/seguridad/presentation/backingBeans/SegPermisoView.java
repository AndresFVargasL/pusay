package com.vortexbird.seguridad.presentation.backingBeans;

import java.util.List;
import java.util.TimeZone;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.RowEditEvent;

import com.vortexbird.seguridad.exceptions.ZMessManager;
import com.vortexbird.seguridad.modelo.dto.SegPermisoDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.FacesUtils;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SegPermisoView {
    private InputText txtPerEstadoRegistro;
    private InputText txtCiaCodigo_SegCompania;
    private InputText txtGruCodigo_SegGrupoOpcion;
    private InputText txtOpcCodigo_SegOpcion;
    private InputText txtRolCodigo_SegRol;
    private InputText txtSucCodigo_SegSucursal;
    private InputText txtUsuCodigo_SegUsuario;
    private InputText txtPerCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SegPermisoDTO> data;
    private SegPermisoDTO selectedSegPermiso;

    public SegPermisoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SegPermisoDTO segPermisoDTO = (SegPermisoDTO) e.getObject();

            if (txtPerEstadoRegistro == null) {
                txtPerEstadoRegistro = new InputText();
            }

            txtPerEstadoRegistro.setValue(segPermisoDTO.getPerEstadoRegistro());

            if (txtCiaCodigo_SegCompania == null) {
                txtCiaCodigo_SegCompania = new InputText();
            }

            txtCiaCodigo_SegCompania.setValue(segPermisoDTO.getCiaCodigo_SegCompania());

            if (txtGruCodigo_SegGrupoOpcion == null) {
                txtGruCodigo_SegGrupoOpcion = new InputText();
            }

            txtGruCodigo_SegGrupoOpcion.setValue(segPermisoDTO.getGruCodigo_SegGrupoOpcion());

            if (txtOpcCodigo_SegOpcion == null) {
                txtOpcCodigo_SegOpcion = new InputText();
            }

            txtOpcCodigo_SegOpcion.setValue(segPermisoDTO.getOpcCodigo_SegOpcion());

            if (txtRolCodigo_SegRol == null) {
                txtRolCodigo_SegRol = new InputText();
            }

            txtRolCodigo_SegRol.setValue(segPermisoDTO.getRolCodigo_SegRol());

            if (txtSucCodigo_SegSucursal == null) {
                txtSucCodigo_SegSucursal = new InputText();
            }

            txtSucCodigo_SegSucursal.setValue(segPermisoDTO.getSucCodigo_SegSucursal());

            if (txtUsuCodigo_SegUsuario == null) {
                txtUsuCodigo_SegUsuario = new InputText();
            }

            txtUsuCodigo_SegUsuario.setValue(segPermisoDTO.getUsuCodigo_SegUsuario());

            if (txtPerCodigo == null) {
                txtPerCodigo = new InputText();
            }

            txtPerCodigo.setValue(segPermisoDTO.getPerCodigo());

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_clear() {
        if (txtPerEstadoRegistro != null) {
            txtPerEstadoRegistro.setValue(null);
            txtPerEstadoRegistro.setDisabled(true);
        }

        if (txtCiaCodigo_SegCompania != null) {
            txtCiaCodigo_SegCompania.setValue(null);
            txtCiaCodigo_SegCompania.setDisabled(true);
        }

        if (txtGruCodigo_SegGrupoOpcion != null) {
            txtGruCodigo_SegGrupoOpcion.setValue(null);
            txtGruCodigo_SegGrupoOpcion.setDisabled(true);
        }

        if (txtOpcCodigo_SegOpcion != null) {
            txtOpcCodigo_SegOpcion.setValue(null);
            txtOpcCodigo_SegOpcion.setDisabled(true);
        }

        if (txtRolCodigo_SegRol != null) {
            txtRolCodigo_SegRol.setValue(null);
            txtRolCodigo_SegRol.setDisabled(true);
        }

        if (txtSucCodigo_SegSucursal != null) {
            txtSucCodigo_SegSucursal.setValue(null);
            txtSucCodigo_SegSucursal.setDisabled(true);
        }

        if (txtUsuCodigo_SegUsuario != null) {
            txtUsuCodigo_SegUsuario.setValue(null);
            txtUsuCodigo_SegUsuario.setDisabled(true);
        }

        if (txtPerCodigo != null) {
            txtPerCodigo.setValue(null);
            txtPerCodigo.setDisabled(false);
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

   
    public String action_save() {
        try {
        	Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
        	
            BusinessDelegatorView.saveSegPermiso(
                FacesUtils.checkString(txtPerEstadoRegistro),
                FacesUtils.checkLong(txtCiaCodigo_SegCompania),
                FacesUtils.checkLong(txtGruCodigo_SegGrupoOpcion),
                FacesUtils.checkLong(txtOpcCodigo_SegOpcion),
                FacesUtils.checkLong(txtRolCodigo_SegRol),
                FacesUtils.checkLong(txtSucCodigo_SegSucursal),
                FacesUtils.checkLong(txtUsuCodigo_SegUsuario),usuSession);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete() {
        try {
            BusinessDelegatorView.deleteSegPermiso(FacesUtils.checkLong(
                    txtPerCodigo));
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
        	Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
        	
            BusinessDelegatorView.updateSegPermiso(FacesUtils.checkLong(
                    txtPerCodigo),
                FacesUtils.checkString(txtPerEstadoRegistro),
                FacesUtils.checkLong(txtCiaCodigo_SegCompania),
                FacesUtils.checkLong(txtGruCodigo_SegGrupoOpcion),
                FacesUtils.checkLong(txtOpcCodigo_SegOpcion),
                FacesUtils.checkLong(txtRolCodigo_SegRol),
                FacesUtils.checkLong(txtSucCodigo_SegSucursal),
                FacesUtils.checkLong(txtUsuCodigo_SegUsuario),usuSession);
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
            if (txtPerCodigo == null) {
                txtPerCodigo = new InputText();
            }

            txtPerCodigo.setValue(selectedSegPermiso.getPerCodigo());

            action_delete();
            data.remove(selectedSegPermiso);
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long perCodigo, String perEstadoRegistro,
        Long ciaCodigo_SegCompania, Long gruCodigo_SegGrupoOpcion,
        Long opcCodigo_SegOpcion, Long rolCodigo_SegRol,
        Long sucCodigo_SegSucursal, Long usuCodigo_SegUsuario,
        Long usuCodigo_SegUsuario0) throws Exception {
        try {
            BusinessDelegatorView.updateSegPermiso(perCodigo,
                perEstadoRegistro, ciaCodigo_SegCompania,
                gruCodigo_SegGrupoOpcion, opcCodigo_SegOpcion,
                rolCodigo_SegRol, sucCodigo_SegSucursal, usuCodigo_SegUsuario,usuCodigo_SegUsuario0);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SegPermisoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtPerEstadoRegistro() {
        return txtPerEstadoRegistro;
    }

    public void setTxtPerEstadoRegistro(InputText txtPerEstadoRegistro) {
        this.txtPerEstadoRegistro = txtPerEstadoRegistro;
    }

    public InputText getTxtCiaCodigo_SegCompania() {
        return txtCiaCodigo_SegCompania;
    }

    public void setTxtCiaCodigo_SegCompania(InputText txtCiaCodigo_SegCompania) {
        this.txtCiaCodigo_SegCompania = txtCiaCodigo_SegCompania;
    }

    public InputText getTxtGruCodigo_SegGrupoOpcion() {
        return txtGruCodigo_SegGrupoOpcion;
    }

    public void setTxtGruCodigo_SegGrupoOpcion(
        InputText txtGruCodigo_SegGrupoOpcion) {
        this.txtGruCodigo_SegGrupoOpcion = txtGruCodigo_SegGrupoOpcion;
    }

    public InputText getTxtOpcCodigo_SegOpcion() {
        return txtOpcCodigo_SegOpcion;
    }

    public void setTxtOpcCodigo_SegOpcion(InputText txtOpcCodigo_SegOpcion) {
        this.txtOpcCodigo_SegOpcion = txtOpcCodigo_SegOpcion;
    }

    public InputText getTxtRolCodigo_SegRol() {
        return txtRolCodigo_SegRol;
    }

    public void setTxtRolCodigo_SegRol(InputText txtRolCodigo_SegRol) {
        this.txtRolCodigo_SegRol = txtRolCodigo_SegRol;
    }

    public InputText getTxtSucCodigo_SegSucursal() {
        return txtSucCodigo_SegSucursal;
    }

    public void setTxtSucCodigo_SegSucursal(InputText txtSucCodigo_SegSucursal) {
        this.txtSucCodigo_SegSucursal = txtSucCodigo_SegSucursal;
    }

    public InputText getTxtUsuCodigo_SegUsuario() {
        return txtUsuCodigo_SegUsuario;
    }

    public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
        this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
    }

    public InputText getTxtPerCodigo() {
        return txtPerCodigo;
    }

    public void setTxtPerCodigo(InputText txtPerCodigo) {
        this.txtPerCodigo = txtPerCodigo;
    }

    public List<SegPermisoDTO> getData() {
        return data;
    }

    public void setData(List<SegPermisoDTO> segPermisoDTO) {
        this.data = segPermisoDTO;
    }

    public SegPermisoDTO getSelectedSegPermiso() {
        return selectedSegPermiso;
    }

    public void setSelectedSegPermiso(SegPermisoDTO segPermiso) {
        this.selectedSegPermiso = segPermiso;
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
