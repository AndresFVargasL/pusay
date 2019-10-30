package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyEvaluacionPeaIndicadorDTO;
import com.vortexbird.pusay.presentation.businessDelegate.*;
import com.vortexbird.pusay.presentation.utilities.FacesUtils;
import com.vortexbird.pusay.utilities.*;

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
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyEvaluacionPeaIndicadorView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyEvaluacionPeaIndicadorView.class);
    private InputText txtEstadoRegistro;
    private InputText txtMeta;
    private InputText txtMultiple;
    private InputText txtNorma;
    private InputText txtPeriodo;
    private InputText txtResultado;
    private InputText txtSectorial;
    private InputText txtCodigo_PsyIndicador;
    private InputText txtCodigo_PsyPlanEstrategicoAmbiental;
    private InputText txtCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyEvaluacionPeaIndicadorDTO> data;
    private PsyEvaluacionPeaIndicadorDTO selectedPsyEvaluacionPeaIndicador;
    private PsyEvaluacionPeaIndicador entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyEvaluacionPeaIndicadorView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyEvaluacionPeaIndicadorDTO psyEvaluacionPeaIndicadorDTO = (PsyEvaluacionPeaIndicadorDTO) e.getObject();

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyEvaluacionPeaIndicadorDTO.getEstadoRegistro());

            if (txtMeta == null) {
                txtMeta = new InputText();
            }

            txtMeta.setValue(psyEvaluacionPeaIndicadorDTO.getMeta());

            if (txtMultiple == null) {
                txtMultiple = new InputText();
            }

            txtMultiple.setValue(psyEvaluacionPeaIndicadorDTO.getMultiple());

            if (txtNorma == null) {
                txtNorma = new InputText();
            }

            txtNorma.setValue(psyEvaluacionPeaIndicadorDTO.getNorma());

            if (txtPeriodo == null) {
                txtPeriodo = new InputText();
            }

            txtPeriodo.setValue(psyEvaluacionPeaIndicadorDTO.getPeriodo());

            if (txtResultado == null) {
                txtResultado = new InputText();
            }

            txtResultado.setValue(psyEvaluacionPeaIndicadorDTO.getResultado());

            if (txtSectorial == null) {
                txtSectorial = new InputText();
            }

            txtSectorial.setValue(psyEvaluacionPeaIndicadorDTO.getSectorial());

            if (txtCodigo_PsyIndicador == null) {
                txtCodigo_PsyIndicador = new InputText();
            }

            txtCodigo_PsyIndicador.setValue(psyEvaluacionPeaIndicadorDTO.getCodigo_PsyIndicador());

            if (txtCodigo_PsyPlanEstrategicoAmbiental == null) {
                txtCodigo_PsyPlanEstrategicoAmbiental = new InputText();
            }

            txtCodigo_PsyPlanEstrategicoAmbiental.setValue(psyEvaluacionPeaIndicadorDTO.getCodigo_PsyPlanEstrategicoAmbiental());

            if (txtCodigo == null) {
                txtCodigo = new InputText();
            }

            txtCodigo.setValue(psyEvaluacionPeaIndicadorDTO.getCodigo());

            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = businessDelegatorView.getPsyEvaluacionPeaIndicador(codigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyEvaluacionPeaIndicador = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyEvaluacionPeaIndicador = null;

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtMeta != null) {
            txtMeta.setValue(null);
            txtMeta.setDisabled(true);
        }

        if (txtMultiple != null) {
            txtMultiple.setValue(null);
            txtMultiple.setDisabled(true);
        }

        if (txtNorma != null) {
            txtNorma.setValue(null);
            txtNorma.setDisabled(true);
        }

        if (txtPeriodo != null) {
            txtPeriodo.setValue(null);
            txtPeriodo.setDisabled(true);
        }

        if (txtResultado != null) {
            txtResultado.setValue(null);
            txtResultado.setDisabled(true);
        }

        if (txtSectorial != null) {
            txtSectorial.setValue(null);
            txtSectorial.setDisabled(true);
        }

        if (txtCodigo_PsyIndicador != null) {
            txtCodigo_PsyIndicador.setValue(null);
            txtCodigo_PsyIndicador.setDisabled(true);
        }

        if (txtCodigo_PsyPlanEstrategicoAmbiental != null) {
            txtCodigo_PsyPlanEstrategicoAmbiental.setValue(null);
            txtCodigo_PsyPlanEstrategicoAmbiental.setDisabled(true);
        }

        if (txtCodigo != null) {
            txtCodigo.setValue(null);
            txtCodigo.setDisabled(false);
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
            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = (codigo != null)
                ? businessDelegatorView.getPsyEvaluacionPeaIndicador(codigo)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoRegistro.setDisabled(false);
            txtMeta.setDisabled(false);
            txtMultiple.setDisabled(false);
            txtNorma.setDisabled(false);
            txtPeriodo.setDisabled(false);
            txtResultado.setDisabled(false);
            txtSectorial.setDisabled(false);
            txtCodigo_PsyIndicador.setDisabled(false);
            txtCodigo_PsyPlanEstrategicoAmbiental.setDisabled(false);
            txtCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtMeta.setValue(entity.getMeta());
            txtMeta.setDisabled(false);
            txtMultiple.setValue(entity.getMultiple());
            txtMultiple.setDisabled(false);
            txtNorma.setValue(entity.getNorma());
            txtNorma.setDisabled(false);
            txtPeriodo.setValue(entity.getPeriodo());
            txtPeriodo.setDisabled(false);
            txtResultado.setValue(entity.getResultado());
            txtResultado.setDisabled(false);
            txtSectorial.setValue(entity.getSectorial());
            txtSectorial.setDisabled(false);
            txtCodigo_PsyIndicador.setValue(entity.getPsyIndicador().getCodigo());
            txtCodigo_PsyIndicador.setDisabled(false);
            txtCodigo_PsyPlanEstrategicoAmbiental.setValue(entity.getPsyPlanEstrategicoAmbiental()
                                                                 .getCodigo());
            txtCodigo_PsyPlanEstrategicoAmbiental.setDisabled(false);
            txtCodigo.setValue(entity.getCodigo());
            txtCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyEvaluacionPeaIndicador = (PsyEvaluacionPeaIndicadorDTO) (evt.getComponent()
                                                                               .getAttributes()
                                                                               .get("selectedPsyEvaluacionPeaIndicador"));
        txtEstadoRegistro.setValue(selectedPsyEvaluacionPeaIndicador.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtMeta.setValue(selectedPsyEvaluacionPeaIndicador.getMeta());
        txtMeta.setDisabled(false);
        txtMultiple.setValue(selectedPsyEvaluacionPeaIndicador.getMultiple());
        txtMultiple.setDisabled(false);
        txtNorma.setValue(selectedPsyEvaluacionPeaIndicador.getNorma());
        txtNorma.setDisabled(false);
        txtPeriodo.setValue(selectedPsyEvaluacionPeaIndicador.getPeriodo());
        txtPeriodo.setDisabled(false);
        txtResultado.setValue(selectedPsyEvaluacionPeaIndicador.getResultado());
        txtResultado.setDisabled(false);
        txtSectorial.setValue(selectedPsyEvaluacionPeaIndicador.getSectorial());
        txtSectorial.setDisabled(false);
        txtCodigo_PsyIndicador.setValue(selectedPsyEvaluacionPeaIndicador.getCodigo_PsyIndicador());
        txtCodigo_PsyIndicador.setDisabled(false);
        txtCodigo_PsyPlanEstrategicoAmbiental.setValue(selectedPsyEvaluacionPeaIndicador.getCodigo_PsyPlanEstrategicoAmbiental());
        txtCodigo_PsyPlanEstrategicoAmbiental.setDisabled(false);
        txtCodigo.setValue(selectedPsyEvaluacionPeaIndicador.getCodigo());
        txtCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyEvaluacionPeaIndicador == null) &&
                    (entity == null)) {
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
            entity = new PsyEvaluacionPeaIndicador();

            Long codigo = FacesUtils.checkLong(txtCodigo);

            entity.setCodigo(codigo);
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setMeta(FacesUtils.checkDouble(txtMeta));
            entity.setMultiple(FacesUtils.checkString(txtMultiple));
            entity.setNorma(FacesUtils.checkDouble(txtNorma));
            entity.setPeriodo(FacesUtils.checkLong(txtPeriodo));
            entity.setResultado(FacesUtils.checkDouble(txtResultado));
            entity.setSectorial(FacesUtils.checkDouble(txtSectorial));
            entity.setPsyIndicador((FacesUtils.checkLong(txtCodigo_PsyIndicador) != null)
                ? businessDelegatorView.getPsyIndicador(FacesUtils.checkLong(
                        txtCodigo_PsyIndicador)) : null);
            entity.setPsyPlanEstrategicoAmbiental((FacesUtils.checkLong(
                    txtCodigo_PsyPlanEstrategicoAmbiental) != null)
                ? businessDelegatorView.getPsyPlanEstrategicoAmbiental(
                    FacesUtils.checkLong(txtCodigo_PsyPlanEstrategicoAmbiental))
                : null);
            businessDelegatorView.savePsyEvaluacionPeaIndicador(entity);
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
                Long codigo = new Long(selectedPsyEvaluacionPeaIndicador.getCodigo());
                entity = businessDelegatorView.getPsyEvaluacionPeaIndicador(codigo);
            }

            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setMeta(FacesUtils.checkDouble(txtMeta));
            entity.setMultiple(FacesUtils.checkString(txtMultiple));
            entity.setNorma(FacesUtils.checkDouble(txtNorma));
            entity.setPeriodo(FacesUtils.checkLong(txtPeriodo));
            entity.setResultado(FacesUtils.checkDouble(txtResultado));
            entity.setSectorial(FacesUtils.checkDouble(txtSectorial));
            entity.setPsyIndicador((FacesUtils.checkLong(txtCodigo_PsyIndicador) != null)
                ? businessDelegatorView.getPsyIndicador(FacesUtils.checkLong(
                        txtCodigo_PsyIndicador)) : null);
            entity.setPsyPlanEstrategicoAmbiental((FacesUtils.checkLong(
                    txtCodigo_PsyPlanEstrategicoAmbiental) != null)
                ? businessDelegatorView.getPsyPlanEstrategicoAmbiental(
                    FacesUtils.checkLong(txtCodigo_PsyPlanEstrategicoAmbiental))
                : null);
            businessDelegatorView.updatePsyEvaluacionPeaIndicador(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyEvaluacionPeaIndicador = (PsyEvaluacionPeaIndicadorDTO) (evt.getComponent()
                                                                                   .getAttributes()
                                                                                   .get("selectedPsyEvaluacionPeaIndicador"));

            Long codigo = new Long(selectedPsyEvaluacionPeaIndicador.getCodigo());
            entity = businessDelegatorView.getPsyEvaluacionPeaIndicador(codigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = businessDelegatorView.getPsyEvaluacionPeaIndicador(codigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyEvaluacionPeaIndicador(entity);
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
            selectedPsyEvaluacionPeaIndicador = (PsyEvaluacionPeaIndicadorDTO) (evt.getComponent()
                                                                                   .getAttributes()
                                                                                   .get("selectedPsyEvaluacionPeaIndicador"));

            Long codigo = new Long(selectedPsyEvaluacionPeaIndicador.getCodigo());
            entity = businessDelegatorView.getPsyEvaluacionPeaIndicador(codigo);
            businessDelegatorView.deletePsyEvaluacionPeaIndicador(entity);
            data.remove(selectedPsyEvaluacionPeaIndicador);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigo, String estadoRegistro,
        Double meta, String multiple, Double norma, Long periodo,
        Double resultado, Double sectorial, Long codigo_PsyIndicador,
        Long codigo_PsyPlanEstrategicoAmbiental) throws Exception {
        try {
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setMeta(FacesUtils.checkDouble(meta));
            entity.setMultiple(FacesUtils.checkString(multiple));
            entity.setNorma(FacesUtils.checkDouble(norma));
            entity.setPeriodo(FacesUtils.checkLong(periodo));
            entity.setResultado(FacesUtils.checkDouble(resultado));
            entity.setSectorial(FacesUtils.checkDouble(sectorial));
            businessDelegatorView.updatePsyEvaluacionPeaIndicador(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyEvaluacionPeaIndicadorView").requestRender();
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

    public InputText getTxtMeta() {
        return txtMeta;
    }

    public void setTxtMeta(InputText txtMeta) {
        this.txtMeta = txtMeta;
    }

    public InputText getTxtMultiple() {
        return txtMultiple;
    }

    public void setTxtMultiple(InputText txtMultiple) {
        this.txtMultiple = txtMultiple;
    }

    public InputText getTxtNorma() {
        return txtNorma;
    }

    public void setTxtNorma(InputText txtNorma) {
        this.txtNorma = txtNorma;
    }

    public InputText getTxtPeriodo() {
        return txtPeriodo;
    }

    public void setTxtPeriodo(InputText txtPeriodo) {
        this.txtPeriodo = txtPeriodo;
    }

    public InputText getTxtResultado() {
        return txtResultado;
    }

    public void setTxtResultado(InputText txtResultado) {
        this.txtResultado = txtResultado;
    }

    public InputText getTxtSectorial() {
        return txtSectorial;
    }

    public void setTxtSectorial(InputText txtSectorial) {
        this.txtSectorial = txtSectorial;
    }

    public InputText getTxtCodigo_PsyIndicador() {
        return txtCodigo_PsyIndicador;
    }

    public void setTxtCodigo_PsyIndicador(InputText txtCodigo_PsyIndicador) {
        this.txtCodigo_PsyIndicador = txtCodigo_PsyIndicador;
    }

    public InputText getTxtCodigo_PsyPlanEstrategicoAmbiental() {
        return txtCodigo_PsyPlanEstrategicoAmbiental;
    }

    public void setTxtCodigo_PsyPlanEstrategicoAmbiental(
        InputText txtCodigo_PsyPlanEstrategicoAmbiental) {
        this.txtCodigo_PsyPlanEstrategicoAmbiental = txtCodigo_PsyPlanEstrategicoAmbiental;
    }

    public InputText getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(InputText txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public List<PsyEvaluacionPeaIndicadorDTO> getData() {
        

        return data;
    }

    public void setData(
        List<PsyEvaluacionPeaIndicadorDTO> psyEvaluacionPeaIndicadorDTO) {
        this.data = psyEvaluacionPeaIndicadorDTO;
    }

    public PsyEvaluacionPeaIndicadorDTO getSelectedPsyEvaluacionPeaIndicador() {
        return selectedPsyEvaluacionPeaIndicador;
    }

    public void setSelectedPsyEvaluacionPeaIndicador(
        PsyEvaluacionPeaIndicadorDTO psyEvaluacionPeaIndicador) {
        this.selectedPsyEvaluacionPeaIndicador = psyEvaluacionPeaIndicador;
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
