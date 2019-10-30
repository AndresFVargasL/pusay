package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyMatrizEncuestaDTO;
import com.vortexbird.pusay.modelo.dto.RespuestaEncuestasDTO;
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
import javax.servlet.http.HttpSession;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyMatrizEncuestaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyMatrizEncuestaView.class);
    private InputText txtCodigoEncuesta;
    private InputText txtEstadoRegistro;
    private InputText txtPestCodigo_PsyPlanEstrategico;
    private InputText txtMaenCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyMatrizEncuestaDTO> data;
    private PsyMatrizEncuestaDTO selectedPsyMatrizEncuesta;
    private PsyMatrizEncuesta entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    private List<RespuestaEncuestasDTO> resultadoEncuestas;

    public PsyMatrizEncuestaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyMatrizEncuestaDTO psyMatrizEncuestaDTO = (PsyMatrizEncuestaDTO) e.getObject();

            if (txtCodigoEncuesta == null) {
                txtCodigoEncuesta = new InputText();
            }

            txtCodigoEncuesta.setValue(psyMatrizEncuestaDTO.getCodigoEncuesta());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyMatrizEncuestaDTO.getEstadoRegistro());

            if (txtPestCodigo_PsyPlanEstrategico == null) {
                txtPestCodigo_PsyPlanEstrategico = new InputText();
            }

            txtPestCodigo_PsyPlanEstrategico.setValue(psyMatrizEncuestaDTO.getPestCodigo_PsyPlanEstrategico());

            if (txtMaenCodigo == null) {
                txtMaenCodigo = new InputText();
            }

            txtMaenCodigo.setValue(psyMatrizEncuestaDTO.getMaenCodigo());

            Long maenCodigo = FacesUtils.checkLong(txtMaenCodigo);
            entity = businessDelegatorView.getPsyMatrizEncuesta(maenCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyMatrizEncuesta = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyMatrizEncuesta = null;

        if (txtCodigoEncuesta != null) {
            txtCodigoEncuesta.setValue(null);
            txtCodigoEncuesta.setDisabled(true);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtPestCodigo_PsyPlanEstrategico != null) {
            txtPestCodigo_PsyPlanEstrategico.setValue(null);
            txtPestCodigo_PsyPlanEstrategico.setDisabled(true);
        }

        if (txtMaenCodigo != null) {
            txtMaenCodigo.setValue(null);
            txtMaenCodigo.setDisabled(false);
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
            Long maenCodigo = FacesUtils.checkLong(txtMaenCodigo);
            entity = (maenCodigo != null)
                ? businessDelegatorView.getPsyMatrizEncuesta(maenCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCodigoEncuesta.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtPestCodigo_PsyPlanEstrategico.setDisabled(false);
            txtMaenCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCodigoEncuesta.setValue(entity.getCodigoEncuesta());
            txtCodigoEncuesta.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtPestCodigo_PsyPlanEstrategico.setValue(entity.getPsyPlanEstrategico()
                                                            .getPestCodigo());
            txtPestCodigo_PsyPlanEstrategico.setDisabled(false);
            txtMaenCodigo.setValue(entity.getMaenCodigo());
            txtMaenCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyMatrizEncuesta = (PsyMatrizEncuestaDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedPsyMatrizEncuesta"));
        txtCodigoEncuesta.setValue(selectedPsyMatrizEncuesta.getCodigoEncuesta());
        txtCodigoEncuesta.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyMatrizEncuesta.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtPestCodigo_PsyPlanEstrategico.setValue(selectedPsyMatrizEncuesta.getPestCodigo_PsyPlanEstrategico());
        txtPestCodigo_PsyPlanEstrategico.setDisabled(false);
        txtMaenCodigo.setValue(selectedPsyMatrizEncuesta.getMaenCodigo());
        txtMaenCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyMatrizEncuesta == null) && (entity == null)) {
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
            entity = new PsyMatrizEncuesta();

            Long maenCodigo = FacesUtils.checkLong(txtMaenCodigo);

            entity.setCodigoEncuesta(FacesUtils.checkString(txtCodigoEncuesta));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setMaenCodigo(maenCodigo);
            entity.setPsyPlanEstrategico((FacesUtils.checkLong(
                    txtPestCodigo_PsyPlanEstrategico) != null)
                ? businessDelegatorView.getPsyPlanEstrategico(
                    FacesUtils.checkLong(txtPestCodigo_PsyPlanEstrategico)) : null);
            businessDelegatorView.savePsyMatrizEncuesta(entity);
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
                Long maenCodigo = new Long(selectedPsyMatrizEncuesta.getMaenCodigo());
                entity = businessDelegatorView.getPsyMatrizEncuesta(maenCodigo);
            }

            entity.setCodigoEncuesta(FacesUtils.checkString(txtCodigoEncuesta));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setPsyPlanEstrategico((FacesUtils.checkLong(
                    txtPestCodigo_PsyPlanEstrategico) != null)
                ? businessDelegatorView.getPsyPlanEstrategico(
                    FacesUtils.checkLong(txtPestCodigo_PsyPlanEstrategico)) : null);
            businessDelegatorView.updatePsyMatrizEncuesta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyMatrizEncuesta = (PsyMatrizEncuestaDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedPsyMatrizEncuesta"));

            Long maenCodigo = new Long(selectedPsyMatrizEncuesta.getMaenCodigo());
            entity = businessDelegatorView.getPsyMatrizEncuesta(maenCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long maenCodigo = FacesUtils.checkLong(txtMaenCodigo);
            entity = businessDelegatorView.getPsyMatrizEncuesta(maenCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyMatrizEncuesta(entity);
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
            selectedPsyMatrizEncuesta = (PsyMatrizEncuestaDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedPsyMatrizEncuesta"));

            Long maenCodigo = new Long(selectedPsyMatrizEncuesta.getMaenCodigo());
            entity = businessDelegatorView.getPsyMatrizEncuesta(maenCodigo);
            businessDelegatorView.deletePsyMatrizEncuesta(entity);
            data.remove(selectedPsyMatrizEncuesta);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String codigoEncuesta,
        String estadoRegistro, Long maenCodigo,
        Long pestCodigo_PsyPlanEstrategico) throws Exception {
        try {
            entity.setCodigoEncuesta(FacesUtils.checkString(codigoEncuesta));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            businessDelegatorView.updatePsyMatrizEncuesta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyMatrizEncuestaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCodigoEncuesta() {
        return txtCodigoEncuesta;
    }

    public void setTxtCodigoEncuesta(InputText txtCodigoEncuesta) {
        this.txtCodigoEncuesta = txtCodigoEncuesta;
    }

    public InputText getTxtEstadoRegistro() {
        return txtEstadoRegistro;
    }

    public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
        this.txtEstadoRegistro = txtEstadoRegistro;
    }

    public InputText getTxtPestCodigo_PsyPlanEstrategico() {
        return txtPestCodigo_PsyPlanEstrategico;
    }

    public void setTxtPestCodigo_PsyPlanEstrategico(
        InputText txtPestCodigo_PsyPlanEstrategico) {
        this.txtPestCodigo_PsyPlanEstrategico = txtPestCodigo_PsyPlanEstrategico;
    }

    public InputText getTxtMaenCodigo() {
        return txtMaenCodigo;
    }

    public void setTxtMaenCodigo(InputText txtMaenCodigo) {
        this.txtMaenCodigo = txtMaenCodigo;
    }

    public List<PsyMatrizEncuestaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyMatrizEncuesta();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyMatrizEncuestaDTO> psyMatrizEncuestaDTO) {
        this.data = psyMatrizEncuestaDTO;
    }

    public PsyMatrizEncuestaDTO getSelectedPsyMatrizEncuesta() {
        return selectedPsyMatrizEncuesta;
    }

    public void setSelectedPsyMatrizEncuesta(
        PsyMatrizEncuestaDTO psyMatrizEncuesta) {
        this.selectedPsyMatrizEncuesta = psyMatrizEncuesta;
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

	public List<RespuestaEncuestasDTO> getResultadoEncuestas() {
		try {
			resultadoEncuestas = businessDelegatorView.consultarResultadoEncuestas(getEmpresaIntoSession());
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Las encuestas no han sido respondidas");
		}
		return resultadoEncuestas;
	}
	
	public PsyEmpresa getEmpresaIntoSession(){
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);
		return (PsyEmpresa) httpSession.getAttribute("empresa");		
	}

	public void setResultadoEncuestas(List<RespuestaEncuestasDTO> resultadoEncuestas) {
		this.resultadoEncuestas = resultadoEncuestas;
	}
}
