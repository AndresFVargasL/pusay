package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsySubtemaDTO;
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
import javax.faces.model.SelectItem;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsySubtemaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsySubtemaView.class);
    private static final String ESTADO_REGISTRO_ACTIVO ="A";
    private static final String ESTADO_REGISTRO_INACTIVO = "I";
    private InputText txtDescripcion;
    private InputText txtEstadoRegistro;
    private InputText txtCodigo_PsyTema;
    private InputText txtCodigo;
    private String somTema;
    private List<SelectItem> losTemas;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsySubtemaDTO> data;
    private PsySubtemaDTO selectedPsySubtema;
    private PsySubtema entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsySubtemaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsySubtemaDTO psySubtemaDTO = (PsySubtemaDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(psySubtemaDTO.getDescripcion());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psySubtemaDTO.getEstadoRegistro());

            if (txtCodigo_PsyTema == null) {
                txtCodigo_PsyTema = new InputText();
            }

            txtCodigo_PsyTema.setValue(psySubtemaDTO.getCodigo_PsyTema());

            if (txtCodigo == null) {
                txtCodigo = new InputText();
            }

            txtCodigo.setValue(psySubtemaDTO.getCodigo());

            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = businessDelegatorView.getPsySubtema(codigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsySubtema = null;
        txtEstadoRegistro.setValue("Activo");
        setSomTema("0");
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsySubtema = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setDisabled(true);
        }

        setSomTema("0");        

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
            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = (codigo != null)
                ? businessDelegatorView.getPsySubtema(codigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtCodigo_PsyTema.setDisabled(false);
            txtCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtCodigo_PsyTema.setValue(entity.getPsyTema().getCodigo());
            txtCodigo_PsyTema.setDisabled(false);
            txtCodigo.setValue(entity.getCodigo());
            txtCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsySubtema = (PsySubtemaDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedPsySubtema"));
        txtDescripcion.setValue(selectedPsySubtema.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsySubtema.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(true);
        setSomTema((selectedPsySubtema.getCodigo_PsyTema()).toString());;
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsySubtema == null) && (entity == null)) {
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
            entity = new PsySubtema();

            Long codigo = FacesUtils.checkLong(txtCodigo);

            entity.setCodigo(codigo);
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setPsyTema((somTema != null)
                ? businessDelegatorView.getPsyTema(Long.parseLong(somTema)) : null);
            businessDelegatorView.savePsySubtema(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
            data=null;
        } catch (Exception e) {
            entity = null;
            data=null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long codigo = new Long(selectedPsySubtema.getCodigo());
                entity = businessDelegatorView.getPsySubtema(codigo);
            }

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setPsyTema((somTema != null)
                ? businessDelegatorView.getPsyTema(Long.parseLong(somTema)) : null);
            businessDelegatorView.updatePsySubtema(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
            action_clear();
            data=null;
        } catch (Exception e) {
        	entity = null;
            data=null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsySubtema = (PsySubtemaDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedPsySubtema"));

            Long codigo = new Long(selectedPsySubtema.getCodigo());
            entity = businessDelegatorView.getPsySubtema(codigo);
            action_delete();
            data=null;
        } catch (Exception e) {
        	entity = null;
            data=null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = businessDelegatorView.getPsySubtema(codigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsySubtema(entity);
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
            selectedPsySubtema = (PsySubtemaDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedPsySubtema"));

            Long codigo = new Long(selectedPsySubtema.getCodigo());
            entity = businessDelegatorView.getPsySubtema(codigo);
            businessDelegatorView.deletePsySubtema(entity);
            data.remove(selectedPsySubtema);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigo, String descripcion,
        String estadoRegistro, Long codigo_PsyTema) throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            businessDelegatorView.updatePsySubtema(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsySubtemaView").requestRender();
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

    public InputText getTxtCodigo_PsyTema() {
        return txtCodigo_PsyTema;
    }

    public void setTxtCodigo_PsyTema(InputText txtCodigo_PsyTema) {
        this.txtCodigo_PsyTema = txtCodigo_PsyTema;
    }

    public InputText getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(InputText txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public List<PsySubtemaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsySubtema();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsySubtemaDTO> psySubtemaDTO) {
        this.data = psySubtemaDTO;
    }

    public PsySubtemaDTO getSelectedPsySubtema() {
        return selectedPsySubtema;
    }

    public void setSelectedPsySubtema(PsySubtemaDTO psySubtema) {
        this.selectedPsySubtema = psySubtema;
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

	public String getSomTema() {
		return somTema;
	}

	public void setSomTema(String somTema) {
		this.somTema = somTema;
	}

	public List<SelectItem> getLosTemas() {
		try {
			losTemas = new ArrayList<SelectItem>();
			List<PsyTema> losTiposTemas = businessDelegatorView
					.getPsyTema();
			for (PsyTema tema : losTiposTemas) {
				if (tema.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							tema.getCodigo(), tema.getDescripcion());
					losTemas.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los temas");
		}
		return losTemas;
	}

	public void setLosTemas(List<SelectItem> losTemas) {
		this.losTemas = losTemas;
	}
}
