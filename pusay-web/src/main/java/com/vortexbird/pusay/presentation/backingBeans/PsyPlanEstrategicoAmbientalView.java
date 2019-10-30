package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyPlanEstrategicoAmbientalDTO;
import com.vortexbird.pusay.modelo.dto.PsyPlanEstrategicoDTO;
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
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyPlanEstrategicoAmbientalView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String ESTADO_REGISTRO_ACTIVO = "A";
    private static final String ESTADO_PLAN_ABIERTO = "A";
    private static final String ESTADO_PLAN_INICIADO = "I";
    private static final String ESTADO_PLAN_PRESUPUESTADO = "P";
    private static final String ESTADO_PLAN_CERRADO = "C";
    private static final Logger log = LoggerFactory.getLogger(PsyPlanEstrategicoAmbientalView.class);
    private InputText txtEstadoPlan;
    private InputText txtEstadoRegistro;
    private InputText txtNombre;
    private InputText txtPestCodigo_PsyPlanEstrategico;
    private InputText txtCodigo;
    private Calendar txtFechaFin;
    private Calendar txtFechaInico;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyPlanEstrategicoAmbientalDTO> data;
    private PsyPlanEstrategicoAmbientalDTO selectedPsyPlanEstrategicoAmbiental;
    private PsyPlanEstrategicoAmbiental entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyPlanEstrategicoAmbientalView() {
        super();
    }
    
    public PsyEmpresa getEmpresaIntoSession(){
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);
		return (PsyEmpresa) httpSession.getAttribute("empresa");		
	}

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyPlanEstrategicoAmbientalDTO psyPlanEstrategicoAmbientalDTO = (PsyPlanEstrategicoAmbientalDTO) e.getObject();

            if (txtEstadoPlan == null) {
                txtEstadoPlan = new InputText();
            }

            txtEstadoPlan.setValue(psyPlanEstrategicoAmbientalDTO.getEstadoPlan());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyPlanEstrategicoAmbientalDTO.getEstadoRegistro());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(psyPlanEstrategicoAmbientalDTO.getNombre());

            if (txtPestCodigo_PsyPlanEstrategico == null) {
                txtPestCodigo_PsyPlanEstrategico = new InputText();
            }

            txtPestCodigo_PsyPlanEstrategico.setValue(psyPlanEstrategicoAmbientalDTO.getPestCodigo_PsyPlanEstrategico());

            if (txtCodigo == null) {
                txtCodigo = new InputText();
            }

            txtCodigo.setValue(psyPlanEstrategicoAmbientalDTO.getCodigo());

            if (txtFechaFin == null) {
                txtFechaFin = new Calendar();
            }

            txtFechaFin.setValue(psyPlanEstrategicoAmbientalDTO.getFechaFin());

            if (txtFechaInico == null) {
                txtFechaInico = new Calendar();
            }

            txtFechaInico.setValue(psyPlanEstrategicoAmbientalDTO.getFechaInico());

            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = businessDelegatorView.getPsyPlanEstrategicoAmbiental(codigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
    	try {
    		PsyPlanEstrategico planEstrategico = businessDelegatorView.getPlanEstrategicoActivoByPEA(getEmpresaIntoSession());
    		if(planEstrategico!=null){
    			if(planEstrategico.getEstadoPlan().trim().equals(ESTADO_PLAN_INICIADO) || 
    					planEstrategico.getEstadoPlan().trim().equals(ESTADO_PLAN_PRESUPUESTADO)){
    				txtEstadoPlan.setValue("Abierto");
    				txtPestCodigo_PsyPlanEstrategico.setValue(planEstrategico.getNombre());
    				action_clear();
    				selectedPsyPlanEstrategicoAmbiental = null;
    				
    				Object[] variables = {"psyPlanEstrategico.pestCodigo",false,planEstrategico.getPestCodigo(),"=","estadoRegistro",true,"A","="};
        			
        			List<PsyPlanEstrategicoAmbiental> verificacionPlanes = businessDelegatorView.findByCriteriaInPsyPlanEstrategicoAmbiental(variables, null, null);
        			
        			for (PsyPlanEstrategicoAmbiental planTmp : verificacionPlanes) {
        				if(planTmp.getEstadoPlan().trim().equals("A")){
        					throw new ZMessManager(
        							"Ya existe un plan estrategico ambiental abierto, cierrelo para poder crear uno nuevo.");
        				}
        				if(planTmp.getEstadoPlan().trim().equals("I")){
        					throw new ZMessManager(
        							"Ya existe un plan estrategico ambiental iniciado, cierrelo para poder crear uno nuevo.");
        				}
        				
        			}
        			
        			setShowDialog(true);
        		
    			}else{
    				FacesUtils.addErrorMessage("Para crear planes ambientales debe haber un plan estrategico en estado iniciado o presupuestado");
    			}
    		}else{
    			FacesUtils.addErrorMessage("Para crear planes ambientales debe haber un plan estrategico en estado iniciado o presupuestado");
    		}
    			
    		
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
    	

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyPlanEstrategicoAmbiental = null;

        if (txtEstadoPlan != null) {
            txtEstadoPlan.setDisabled(true);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(false);
        }

        if (txtPestCodigo_PsyPlanEstrategico != null) {
            txtPestCodigo_PsyPlanEstrategico.setDisabled(true);
        }

        if (txtFechaFin != null) {
            txtFechaFin.setValue(null);
            txtFechaFin.setDisabled(false);
        }

        if (txtFechaInico != null) {
            txtFechaInico.setValue(null);
            txtFechaInico.setDisabled(false);
        }

        if (txtCodigo != null) {
            txtCodigo.setValue(null);
            txtCodigo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(false);
        }

        return "";
    }

    public void listener_txtFechaFin() {
        Date inputDate = (Date) txtFechaFin.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Fecha seleccionada " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaInico() {
        Date inputDate = (Date) txtFechaInico.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Fecha seleccionada " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = (codigo != null)
                ? businessDelegatorView.getPsyPlanEstrategicoAmbiental(codigo)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoPlan.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setDisabled(false);
            txtPestCodigo_PsyPlanEstrategico.setDisabled(false);
            txtFechaFin.setDisabled(false);
            txtFechaInico.setDisabled(false);
            txtCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoPlan.setValue(entity.getEstadoPlan());
            txtEstadoPlan.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtFechaFin.setValue(entity.getFechaFin());
            txtFechaFin.setDisabled(false);
            txtFechaInico.setValue(entity.getFechaInico());
            txtFechaInico.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtPestCodigo_PsyPlanEstrategico.setValue(entity.getPsyPlanEstrategico()
                                                            .getPestCodigo());
            txtPestCodigo_PsyPlanEstrategico.setDisabled(false);
            txtCodigo.setValue(entity.getCodigo());
            txtCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
    	try {
        selectedPsyPlanEstrategicoAmbiental = (PsyPlanEstrategicoAmbientalDTO) (evt.getComponent()
                                                                                   .getAttributes()
                                                                                   .get("selectedPsyPlanEstrategicoAmbiental"));
        txtEstadoPlan.setValue(selectedPsyPlanEstrategicoAmbiental.getEstadoPlan());
        txtEstadoPlan.setDisabled(true);
        txtFechaFin.setValue(selectedPsyPlanEstrategicoAmbiental.getFechaFin());
        txtFechaFin.setDisabled(selectedPsyPlanEstrategicoAmbiental.isDisabled());
        txtFechaInico.setValue(selectedPsyPlanEstrategicoAmbiental.getFechaInico());
        txtFechaInico.setDisabled(selectedPsyPlanEstrategicoAmbiental.isDisabled());
        txtNombre.setValue(selectedPsyPlanEstrategicoAmbiental.getNombre());
        txtNombre.setDisabled(selectedPsyPlanEstrategicoAmbiental.isDisabled());
        txtPestCodigo_PsyPlanEstrategico.setValue(selectedPsyPlanEstrategicoAmbiental.getNombrePest());
        txtPestCodigo_PsyPlanEstrategico.setDisabled(true);
        btnSave.setDisabled(selectedPsyPlanEstrategicoAmbiental.isDisabled());
        setShowDialog(true);
    	} catch (Exception e){
    		FacesUtils.addErrorMessage(e.getMessage());
    	}
        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyPlanEstrategicoAmbiental == null) &&
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
            entity = new PsyPlanEstrategicoAmbiental();	
            
            entity.setEstadoPlan(ESTADO_PLAN_ABIERTO);
            entity.setEstadoRegistro(ESTADO_REGISTRO_ACTIVO);
            entity.setFechaFin(FacesUtils.checkDate(txtFechaFin));
            entity.setFechaInico(FacesUtils.checkDate(txtFechaInico));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPsyPlanEstrategico(businessDelegatorView.getPlanEstrategicoActivoByPEA(getEmpresaIntoSession()));
            businessDelegatorView.savePsyPlanEstrategicoAmbiental(entity);
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
                Long codigo = new Long(selectedPsyPlanEstrategicoAmbiental.getCodigo());
                entity = businessDelegatorView.getPsyPlanEstrategicoAmbiental(codigo);
            }

            entity.setEstadoPlan((FacesUtils.checkString(txtEstadoPlan)!=null) ?
            		(FacesUtils.checkString(txtEstadoPlan).equals("Abierto")) ? ESTADO_PLAN_ABIERTO :
            			(FacesUtils.checkString(txtEstadoPlan).equals("Iniciado")) ? ESTADO_PLAN_INICIADO:
            				(FacesUtils.checkString(txtEstadoPlan).equals("Cerrado")) ? ESTADO_PLAN_CERRADO: null : null);
            entity.setFechaFin(FacesUtils.checkDate(txtFechaFin));
            entity.setFechaInico(FacesUtils.checkDate(txtFechaInico));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPsyPlanEstrategico((FacesUtils.checkString(
                    txtPestCodigo_PsyPlanEstrategico) != null)
                ? businessDelegatorView.getPlanEstrategicoByPEA(getEmpresaIntoSession(), FacesUtils.checkString(txtPestCodigo_PsyPlanEstrategico)) : null);
            businessDelegatorView.updatePsyPlanEstrategicoAmbiental(entity);
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
    
    public String definirPlanEstrategicoAmbiental(ActionEvent evt) {
		try {
			selectedPsyPlanEstrategicoAmbiental = (PsyPlanEstrategicoAmbientalDTO) (evt
					.getComponent().getAttributes()
					.get("selectedPsyPlanEstrategicoAmbiental"));

			Long codigo = new Long(
					selectedPsyPlanEstrategicoAmbiental.getCodigo());
			entity = businessDelegatorView.getPsyPlanEstrategicoAmbiental(codigo);
			if(!entity.getEstadoPlan().equals(ESTADO_PLAN_INICIADO)){
				entity.setEstadoPlan(ESTADO_PLAN_INICIADO);
				businessDelegatorView.updatePsyPlanEstrategicoAmbiental(entity);
			}else{
				FacesUtils.addErrorMessage("El plan ya esta en estado: INICIADO");
			}
			data=null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyPlanEstrategicoAmbiental = (PsyPlanEstrategicoAmbientalDTO) (evt.getComponent()
                                                                                       .getAttributes()
                                                                                       .get("selectedPsyPlanEstrategicoAmbiental"));

            Long codigo = new Long(selectedPsyPlanEstrategicoAmbiental.getCodigo());
            entity = businessDelegatorView.getPsyPlanEstrategicoAmbiental(codigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }
    
    public String action_close_plan(ActionEvent evt) {
        try {
            selectedPsyPlanEstrategicoAmbiental = (PsyPlanEstrategicoAmbientalDTO) (evt.getComponent()
                                                                                       .getAttributes()
                                                                                       .get("selectedPsyPlanEstrategicoAmbiental"));

            Long codigo = new Long(selectedPsyPlanEstrategicoAmbiental.getCodigo());
            entity = businessDelegatorView.getPsyPlanEstrategicoAmbiental(codigo);
            entity.setEstadoPlan("C");
            businessDelegatorView.updatePsyPlanEstrategicoAmbiental(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
            action_clear();
			data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = businessDelegatorView.getPsyPlanEstrategicoAmbiental(codigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyPlanEstrategicoAmbiental(entity);
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
            selectedPsyPlanEstrategicoAmbiental = (PsyPlanEstrategicoAmbientalDTO) (evt.getComponent()
                                                                                       .getAttributes()
                                                                                       .get("selectedPsyPlanEstrategicoAmbiental"));

            Long codigo = new Long(selectedPsyPlanEstrategicoAmbiental.getCodigo());
            entity = businessDelegatorView.getPsyPlanEstrategicoAmbiental(codigo);
            businessDelegatorView.deletePsyPlanEstrategicoAmbiental(entity);
            data.remove(selectedPsyPlanEstrategicoAmbiental);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigo, String estadoPlan,
        String estadoRegistro, Date fechaFin, Date fechaInico, String nombre,
        Long pestCodigo_PsyPlanEstrategico) throws Exception {
        try {
            entity.setEstadoPlan(FacesUtils.checkString(estadoPlan));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFechaFin(FacesUtils.checkDate(fechaFin));
            entity.setFechaInico(FacesUtils.checkDate(fechaInico));
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updatePsyPlanEstrategicoAmbiental(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyPlanEstrategicoAmbientalView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEstadoPlan() {
        return txtEstadoPlan;
    }

    public void setTxtEstadoPlan(InputText txtEstadoPlan) {
        this.txtEstadoPlan = txtEstadoPlan;
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

    public InputText getTxtPestCodigo_PsyPlanEstrategico() {
        return txtPestCodigo_PsyPlanEstrategico;
    }

    public void setTxtPestCodigo_PsyPlanEstrategico(
        InputText txtPestCodigo_PsyPlanEstrategico) {
        this.txtPestCodigo_PsyPlanEstrategico = txtPestCodigo_PsyPlanEstrategico;
    }

    public Calendar getTxtFechaFin() {
        return txtFechaFin;
    }

    public void setTxtFechaFin(Calendar txtFechaFin) {
        this.txtFechaFin = txtFechaFin;
    }

    public Calendar getTxtFechaInico() {
        return txtFechaInico;
    }

    public void setTxtFechaInico(Calendar txtFechaInico) {
        this.txtFechaInico = txtFechaInico;
    }

    public InputText getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(InputText txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public List<PsyPlanEstrategicoAmbientalDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyPlanEstrategicoAmbiental(getEmpresaIntoSession());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(
        List<PsyPlanEstrategicoAmbientalDTO> psyPlanEstrategicoAmbientalDTO) {
        this.data = psyPlanEstrategicoAmbientalDTO;
    }

    public PsyPlanEstrategicoAmbientalDTO getSelectedPsyPlanEstrategicoAmbiental() {
        return selectedPsyPlanEstrategicoAmbiental;
    }

    public void setSelectedPsyPlanEstrategicoAmbiental(
        PsyPlanEstrategicoAmbientalDTO psyPlanEstrategicoAmbiental) {
        this.selectedPsyPlanEstrategicoAmbiental = psyPlanEstrategicoAmbiental;
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
