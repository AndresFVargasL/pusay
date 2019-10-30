package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyPresupuestoDTO;
import com.vortexbird.pusay.modelo.dto.PsyProvinciaDTO;
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
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyProvinciaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyProvinciaView.class);
    private static final String ESTADO_REGISTRO_ACTIVO ="A";
    private static final String ESTADO_REGISTRO_INACTIVO = "I";
    private InputText txtEstadoRegistro;
    private InputText txtNombre;
    private InputText txtPaisCodigo_PsyPais;
    private InputText txtProvCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyProvinciaDTO> data;
    private PsyProvinciaDTO selectedPsyProvincia;
    private PsyProvincia entity;
    private boolean showDialog;
    private String somPais;
    private Long defaultPais = new Long(0);
    private List<SelectItem> losPaises;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyProvinciaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyProvinciaDTO psyProvinciaDTO = (PsyProvinciaDTO) e.getObject();

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyProvinciaDTO.getEstadoRegistro());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(psyProvinciaDTO.getNombre());

            if (txtPaisCodigo_PsyPais == null) {
                txtPaisCodigo_PsyPais = new InputText();
            }

            txtPaisCodigo_PsyPais.setValue(psyProvinciaDTO.getPaisCodigo_PsyPais());

            if (txtProvCodigo == null) {
                txtProvCodigo = new InputText();
            }

            txtProvCodigo.setValue(psyProvinciaDTO.getProvCodigo());

            Long provCodigo = FacesUtils.checkLong(txtProvCodigo);
            entity = businessDelegatorView.getPsyProvincia(provCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        txtEstadoRegistro.setValue("Activo");
        setSomPais(defaultPais.toString());
        selectedPsyProvincia = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyProvincia = null;

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
        }

        if (txtProvCodigo != null) {
            txtProvCodigo.setValue(null);
            txtProvCodigo.setDisabled(false);
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
            Long provCodigo = FacesUtils.checkLong(txtProvCodigo);
            entity = (provCodigo != null)
                ? businessDelegatorView.getPsyProvincia(provCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setDisabled(false);
            txtPaisCodigo_PsyPais.setDisabled(false);
            txtProvCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtPaisCodigo_PsyPais.setValue(entity.getPsyPais().getPaisCodigo());
            txtPaisCodigo_PsyPais.setDisabled(false);
            txtProvCodigo.setValue(entity.getProvCodigo());
            txtProvCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyProvincia = (PsyProvinciaDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedPsyProvincia"));
        txtEstadoRegistro.setValue(selectedPsyProvincia.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(true);
        txtNombre.setValue(selectedPsyProvincia.getNombre());
        txtNombre.setDisabled(false);
        setSomPais((selectedPsyProvincia.getPaisCodigo_PsyPais()).toString());
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyProvincia == null) && (entity == null)) {
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
            entity = new PsyProvincia();

            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPsyPais((somPais != null && !somPais.equals("0"))
                ? businessDelegatorView.getPsyPais(Long.parseLong(somPais)) : null);
            businessDelegatorView.savePsyProvincia(entity);
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
                Long provCodigo = new Long(selectedPsyProvincia.getProvCodigo());
                entity = businessDelegatorView.getPsyProvincia(provCodigo);
            }

            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPsyPais((somPais != null && !somPais.equals("0"))
                    ? businessDelegatorView.getPsyPais(Long.parseLong(somPais)) : null);
            businessDelegatorView.updatePsyProvincia(entity);
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
            selectedPsyProvincia = (PsyProvinciaDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedPsyProvincia"));

            Long provCodigo = new Long(selectedPsyProvincia.getProvCodigo());
            entity = businessDelegatorView.getPsyProvincia(provCodigo);
            action_delete();
            data=null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long provCodigo = FacesUtils.checkLong(txtProvCodigo);
            entity = businessDelegatorView.getPsyProvincia(provCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyProvincia(entity);
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
            selectedPsyProvincia = (PsyProvinciaDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedPsyProvincia"));

            Long provCodigo = new Long(selectedPsyProvincia.getProvCodigo());
            entity = businessDelegatorView.getPsyProvincia(provCodigo);
            businessDelegatorView.deletePsyProvincia(entity);
            data.remove(selectedPsyProvincia);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estadoRegistro, String nombre,
        Long provCodigo, Long paisCodigo_PsyPais) throws Exception {
        try {
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updatePsyProvincia(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyProvinciaView").requestRender();
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

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtPaisCodigo_PsyPais() {
        return txtPaisCodigo_PsyPais;
    }

    public void setTxtPaisCodigo_PsyPais(InputText txtPaisCodigo_PsyPais) {
        this.txtPaisCodigo_PsyPais = txtPaisCodigo_PsyPais;
    }

    public InputText getTxtProvCodigo() {
        return txtProvCodigo;
    }

    public void setTxtProvCodigo(InputText txtProvCodigo) {
        this.txtProvCodigo = txtProvCodigo;
    }

    public List<PsyProvinciaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyProvincia();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyProvinciaDTO> psyProvinciaDTO) {
        this.data = psyProvinciaDTO;
    }

    public PsyProvinciaDTO getSelectedPsyProvincia() {
        return selectedPsyProvincia;
    }

    public void setSelectedPsyProvincia(PsyProvinciaDTO psyProvincia) {
        this.selectedPsyProvincia = psyProvincia;
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

	public String getSomPais() {
		return somPais;
	}

	public void setSomPais(String somPais) {
		this.somPais = somPais;
	}

	public List<SelectItem> getLosPaises() {
		try {
			losPaises = new ArrayList<SelectItem>();
			List<PsyPais> losTiposPaises = businessDelegatorView
					.getPsyPais();
			for (PsyPais pais : losTiposPaises) {
				if (pais.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							pais.getPaisCodigo(), pais.getNombre());
					losPaises.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los paises");
		}
		
		return losPaises;
	}

	public void setLosPaises(List<SelectItem> losPaises) {
		this.losPaises = losPaises;
	}
}
