package com.vortexbird.pusay.presentation.backingBeans;

import java.io.Serializable;
import java.util.List;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.pusay.exceptions.ZMessManager;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyResponsable;
import com.vortexbird.pusay.modelo.dto.PsyResponsableDTO;
import com.vortexbird.pusay.presentation.businessDelegate.IBusinessDelegatorView;
import com.vortexbird.pusay.presentation.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyResponsableView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyResponsableView.class);
    private InputText txtEmail;
    private InputText txtEstadoRegistro;
    private InputText txtNombre;
    private InputText txtEmprCodigo_PsyEmpresa;
    private InputText txtRespCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyResponsableDTO> data;
    private PsyResponsableDTO selectedPsyResponsable;
    private PsyResponsable entity;
    private boolean showDialog;
    private String estadoResponsable;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyResponsableView() {
        super();
    }
    
    public PsyEmpresa getEmpresaIntoSession(){
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);
		return (PsyEmpresa) httpSession.getAttribute("empresa");		
	}

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyResponsableDTO psyResponsableDTO = (PsyResponsableDTO) e.getObject();

            if (txtEmail == null) {
                txtEmail = new InputText();
            }

            txtEmail.setValue(psyResponsableDTO.getEmail());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyResponsableDTO.getEstadoRegistro());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(psyResponsableDTO.getNombre());

            if (txtEmprCodigo_PsyEmpresa == null) {
                txtEmprCodigo_PsyEmpresa = new InputText();
            }

            txtEmprCodigo_PsyEmpresa.setValue(psyResponsableDTO.getEmprCodigo_PsyEmpresa());

            if (txtRespCodigo == null) {
                txtRespCodigo = new InputText();
            }

            txtRespCodigo.setValue(psyResponsableDTO.getRespCodigo());

            Long respCodigo = FacesUtils.checkLong(txtRespCodigo);
            entity = businessDelegatorView.getPsyResponsable(respCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyResponsable = null;
        setShowDialog(true);
        estadoResponsable=null;

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyResponsable = null;

        if (txtEmail != null) {
            txtEmail.setValue(null);
            txtEmail.setDisabled(false);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(false);
        }

        if (txtEmprCodigo_PsyEmpresa != null) {
            txtEmprCodigo_PsyEmpresa.setValue(null);
            txtEmprCodigo_PsyEmpresa.setDisabled(true);
        }

        if (txtRespCodigo != null) {
            txtRespCodigo.setValue(null);
            txtRespCodigo.setDisabled(false);
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
            Long respCodigo = FacesUtils.checkLong(txtRespCodigo);
            entity = (respCodigo != null)
                ? businessDelegatorView.getPsyResponsable(respCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEmail.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setDisabled(false);
            txtEmprCodigo_PsyEmpresa.setDisabled(false);
            txtRespCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEmail.setValue(entity.getEmail());
            txtEmail.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtEmprCodigo_PsyEmpresa.setValue(entity.getPsyEmpresa()
                                                    .getEmprCodigo());
            txtEmprCodigo_PsyEmpresa.setDisabled(false);
            txtRespCodigo.setValue(entity.getRespCodigo());
            txtRespCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyResponsable = (PsyResponsableDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedPsyResponsable"));
        txtEmail.setValue(selectedPsyResponsable.getEmail());
        txtEmail.setDisabled(false);
        setEstadoResponsable((selectedPsyResponsable.getEstadoRegistro().equals("Activo")) ? 
        		"Activo" : (selectedPsyResponsable.getEstadoRegistro().equals("Inactivo")) ? "Inactivo" : null );
        txtNombre.setValue(selectedPsyResponsable.getNombre());
        txtNombre.setDisabled(false);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyResponsable == null) && (entity == null)) {
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
            entity = new PsyResponsable();

            entity.setEmail(FacesUtils.checkString(txtEmail));
            entity.setEstadoRegistro((estadoResponsable.equals("Activo")) ? "A" : "I");
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPsyEmpresa(getEmpresaIntoSession());
            businessDelegatorView.savePsyResponsable(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
			data = null;
			action_closeDialog();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long respCodigo = new Long(selectedPsyResponsable.getRespCodigo());
                entity = businessDelegatorView.getPsyResponsable(respCodigo);
            }

            entity.setEmail(FacesUtils.checkString(txtEmail));
            entity.setEstadoRegistro((estadoResponsable.equals("Activo")) ? "A" : "I");
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPsyEmpresa(getEmpresaIntoSession());
            businessDelegatorView.updatePsyResponsable(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
            action_clear();
			data = null;
			action_closeDialog();
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyResponsable = (PsyResponsableDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedPsyResponsable"));

            Long respCodigo = new Long(selectedPsyResponsable.getRespCodigo());
            entity = businessDelegatorView.getPsyResponsable(respCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long respCodigo = FacesUtils.checkLong(txtRespCodigo);
            entity = businessDelegatorView.getPsyResponsable(respCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyResponsable(entity);
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
            selectedPsyResponsable = (PsyResponsableDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedPsyResponsable"));

            Long respCodigo = new Long(selectedPsyResponsable.getRespCodigo());
            entity = businessDelegatorView.getPsyResponsable(respCodigo);
            businessDelegatorView.deletePsyResponsable(entity);
            data.remove(selectedPsyResponsable);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String email, String estadoRegistro,
        String nombre, Long respCodigo, Long emprCodigo_PsyEmpresa)
        throws Exception {
        try {
            entity.setEmail(FacesUtils.checkString(email));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updatePsyResponsable(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyResponsableView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(InputText txtEmail) {
        this.txtEmail = txtEmail;
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

    public InputText getTxtEmprCodigo_PsyEmpresa() {
        return txtEmprCodigo_PsyEmpresa;
    }

    public void setTxtEmprCodigo_PsyEmpresa(InputText txtEmprCodigo_PsyEmpresa) {
        this.txtEmprCodigo_PsyEmpresa = txtEmprCodigo_PsyEmpresa;
    }

    public InputText getTxtRespCodigo() {
        return txtRespCodigo;
    }

    public void setTxtRespCodigo(InputText txtRespCodigo) {
        this.txtRespCodigo = txtRespCodigo;
    }

    public List<PsyResponsableDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyResponsable(getEmpresaIntoSession());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyResponsableDTO> psyResponsableDTO) {
        this.data = psyResponsableDTO;
    }

    public PsyResponsableDTO getSelectedPsyResponsable() {
        return selectedPsyResponsable;
    }

    public void setSelectedPsyResponsable(PsyResponsableDTO psyResponsable) {
        this.selectedPsyResponsable = psyResponsable;
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

	/**
	 * @return the estadoResponsable
	 */
	public String getEstadoResponsable() {
		return estadoResponsable;
	}

	/**
	 * @param estadoResponsable the estadoResponsable to set
	 */
	public void setEstadoResponsable(String estadoResponsable) {
		this.estadoResponsable = estadoResponsable;
	}
}
