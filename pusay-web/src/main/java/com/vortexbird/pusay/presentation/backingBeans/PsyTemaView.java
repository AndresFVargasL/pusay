package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyTemaDTO;
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
public class PsyTemaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyTemaView.class);
    private static final String ESTADO_REGISTRO_ACTIVO ="A";
    private static final String ESTADO_REGISTRO_INACTIVO = "I";
    private InputText txtDescripcion;
    private InputText txtEstadoRegistro;
    private InputText txtImamCodigo_PsyImpactoAmbiental;
    private InputText txtCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyTemaDTO> data;
    private List<SelectItem> losImpactos;
    private String somImpactoAmbiental;
    private PsyTemaDTO selectedPsyTema;
    private PsyTema entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyTemaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyTemaDTO psyTemaDTO = (PsyTemaDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(psyTemaDTO.getDescripcion());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyTemaDTO.getEstadoRegistro());

            if (txtImamCodigo_PsyImpactoAmbiental == null) {
                txtImamCodigo_PsyImpactoAmbiental = new InputText();
            }

            txtImamCodigo_PsyImpactoAmbiental.setValue(psyTemaDTO.getImamCodigo_PsyImpactoAmbiental());

            if (txtCodigo == null) {
                txtCodigo = new InputText();
            }

            txtCodigo.setValue(psyTemaDTO.getCodigo());

            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = businessDelegatorView.getPsyTema(codigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyTema = null;
        setShowDialog(true);
        txtEstadoRegistro.setValue("Activo");
        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyTema = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            
        }

        if (txtEstadoRegistro != null) {
            
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtImamCodigo_PsyImpactoAmbiental != null) {
            txtImamCodigo_PsyImpactoAmbiental.setValue(null);
            txtImamCodigo_PsyImpactoAmbiental.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(false);
        }
        
        setSomImpactoAmbiental("0");

        return "";
    }

    public void listener_txtId() {
        try {
            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = (codigo != null)
                ? businessDelegatorView.getPsyTema(codigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtImamCodigo_PsyImpactoAmbiental.setDisabled(false);
            txtCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtImamCodigo_PsyImpactoAmbiental.setValue(entity.getPsyImpactoAmbiental()
                                                             .getImamCodigo());
            txtImamCodigo_PsyImpactoAmbiental.setDisabled(false);
            txtCodigo.setValue(entity.getCodigo());
            txtCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyTema = (PsyTemaDTO) (evt.getComponent().getAttributes()
                                           .get("selectedPsyTema"));
        txtDescripcion.setValue(selectedPsyTema.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyTema.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(true);
        setSomImpactoAmbiental((selectedPsyTema.getImamCodigo_PsyImpactoAmbiental()).toString());
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyTema == null) && (entity == null)) {
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
            entity = new PsyTema();

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setPsyImpactoAmbiental((somImpactoAmbiental != null)
                ? businessDelegatorView.getPsyImpactoAmbiental(Long.parseLong(somImpactoAmbiental))
                : null);
            businessDelegatorView.savePsyTema(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
            data=null;
        } catch (Exception e) {
        	setSomImpactoAmbiental("0");
            entity = null;
            data=null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long codigo = new Long(selectedPsyTema.getCodigo());
                entity = businessDelegatorView.getPsyTema(codigo);
            }
            
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setPsyImpactoAmbiental((somImpactoAmbiental != null)
                    ? businessDelegatorView.getPsyImpactoAmbiental(Long.parseLong(somImpactoAmbiental))
                    : null);
            businessDelegatorView.updatePsyTema(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
            action_clear();
            data=null;
        } catch (Exception e) {
        	setSomImpactoAmbiental("0");
            data = null;
            entity=null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyTema = (PsyTemaDTO) (evt.getComponent().getAttributes()
                                               .get("selectedPsyTema"));

            Long codigo = new Long(selectedPsyTema.getCodigo());
            entity = businessDelegatorView.getPsyTema(codigo);
            action_delete();
            data=null;
        } catch (Exception e) {
        	entity=null;
        	data=null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = businessDelegatorView.getPsyTema(codigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyTema(entity);
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
            selectedPsyTema = (PsyTemaDTO) (evt.getComponent().getAttributes()
                                               .get("selectedPsyTema"));

            Long codigo = new Long(selectedPsyTema.getCodigo());
            entity = businessDelegatorView.getPsyTema(codigo);
            businessDelegatorView.deletePsyTema(entity);
            data.remove(selectedPsyTema);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigo, String descripcion,
        String estadoRegistro, Long imamCodigo_PsyImpactoAmbiental)
        throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            businessDelegatorView.updatePsyTema(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyTemaView").requestRender();
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

    public InputText getTxtImamCodigo_PsyImpactoAmbiental() {
        return txtImamCodigo_PsyImpactoAmbiental;
    }

    public void setTxtImamCodigo_PsyImpactoAmbiental(
        InputText txtImamCodigo_PsyImpactoAmbiental) {
        this.txtImamCodigo_PsyImpactoAmbiental = txtImamCodigo_PsyImpactoAmbiental;
    }

    public InputText getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(InputText txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public List<PsyTemaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyTema();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyTemaDTO> psyTemaDTO) {
        this.data = psyTemaDTO;
    }

    public PsyTemaDTO getSelectedPsyTema() {
        return selectedPsyTema;
    }

    public void setSelectedPsyTema(PsyTemaDTO psyTema) {
        this.selectedPsyTema = psyTema;
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

	public List<SelectItem> getLosImpactos() {
		try {
			losImpactos = new ArrayList<SelectItem>();
			List<PsyImpactoAmbiental> losTiposImpactos = businessDelegatorView
					.getPsyImpactoAmbiental();
			for (PsyImpactoAmbiental impactoAmbiental : losTiposImpactos) {
				if (impactoAmbiental.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							impactoAmbiental.getImamCodigo(), impactoAmbiental.getNombre());
					losImpactos.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los temas");
		}
		return losImpactos;
	}

	public void setLosImpactos(List<SelectItem> losImpactos) {
		this.losImpactos = losImpactos;
	}

	public String getSomImpactoAmbiental() {
		return somImpactoAmbiental;
	}

	public void setSomImpactoAmbiental(String somImpactoAmbiental) {
		this.somImpactoAmbiental = somImpactoAmbiental;
	}

	
}
