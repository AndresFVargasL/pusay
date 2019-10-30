package com.vortexbird.pusay.presentation.backingBeans;


import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyDetalleEstrategiasDTO;
import com.vortexbird.pusay.modelo.dto.PsyPlanAccionDTO;
import com.vortexbird.pusay.modelo.dto.PsyProgramaDTO;
import com.vortexbird.pusay.modelo.dto.PsyTareaDTO;
import com.vortexbird.pusay.presentation.businessDelegate.*;
import com.vortexbird.pusay.presentation.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
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
import javax.faces.context.ResponseWriterWrapper;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import java.util.TimeZone;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyTareaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String ESTADO_REGISTRO_ACTIVO = "A";
    private static final String ESTADO_PENDIENTE = "P";
    private static final String ESTADO_EN_CURSO = "E";
    private static final String ESTADO_FINALIZADO = "F";
    private static final Logger log = LoggerFactory.getLogger(PsyTareaView.class);
    private InputTextarea txtDescripcion;
    private InputText txtEstadoRegistro;
    private InputText txtEstadoTarea;
    private InputText txtSemanaFinPlaneada;
    private InputText txtSemanaFinReal;
    private InputText txtPlacCodigo_PsyPlanAccion;
    private InputText txtRespCodigo_PsyResponsable;
    private InputText txtTareCodigo;
    private Calendar txtFechaFinPlaneada;
    private Calendar txtFechaFinReal;
    private Calendar txtFechaInicio;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyTareaDTO> data;
    private PsyTareaDTO selectedPsyTarea;
    private PsyTarea entity;
    private boolean showDialog;
    private List<SelectItem> losResponsables;
    private List<SelectItem> losPlanes;
    private List<SelectItem> losItemsPlanes;
    private String somResponsable;
    private String estadoTarea;
    private String somPlanAccion;
    private SelectOneMenu selectOneMenuPlanAccion;
    private String somPlanAccionItem;
    private boolean disableBtnNew = true;
    private CommandButton btnNew;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    

    public PsyTareaView() {
        super();
    }
    
    public void listener_txtPlanAccionItem() {
		btnNew = new CommandButton();
		btnNew.setDisabled(true);
		setDisableBtnNew(true);
		if(!getSomPlanAccionItem().trim().equals("")){
			btnNew.setDisabled(false);
			setDisableBtnNew(false);
		}else{
			FacesUtils.refresh();
		}
		data = null;
		getData();
	}

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyTareaDTO psyTareaDTO = (PsyTareaDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputTextarea();
            }

            txtDescripcion.setValue(psyTareaDTO.getDescripcion());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyTareaDTO.getEstadoRegistro());

            if (txtEstadoTarea == null) {
                txtEstadoTarea = new InputText();
            }

            txtEstadoTarea.setValue(psyTareaDTO.getEstadoTarea());

            if (txtSemanaFinPlaneada == null) {
                txtSemanaFinPlaneada = new InputText();
            }

            txtSemanaFinPlaneada.setValue(psyTareaDTO.getSemanaFinPlaneada());

            if (txtSemanaFinReal == null) {
                txtSemanaFinReal = new InputText();
            }

            txtSemanaFinReal.setValue(psyTareaDTO.getSemanaFinReal());

            if (txtPlacCodigo_PsyPlanAccion == null) {
                txtPlacCodigo_PsyPlanAccion = new InputText();
            }

            txtPlacCodigo_PsyPlanAccion.setValue(psyTareaDTO.getPlacCodigo_PsyPlanAccion());

            if (txtRespCodigo_PsyResponsable == null) {
                txtRespCodigo_PsyResponsable = new InputText();
            }

            txtRespCodigo_PsyResponsable.setValue(psyTareaDTO.getRespCodigo_PsyResponsable());

            if (txtTareCodigo == null) {
                txtTareCodigo = new InputText();
            }

            txtTareCodigo.setValue(psyTareaDTO.getTareCodigo());

            if (txtFechaFinPlaneada == null) {
                txtFechaFinPlaneada = new Calendar();
            }

            txtFechaFinPlaneada.setValue(psyTareaDTO.getFechaFinPlaneada());

            if (txtFechaFinReal == null) {
                txtFechaFinReal = new Calendar();
            }

            txtFechaFinReal.setValue(psyTareaDTO.getFechaFinReal());

            if (txtFechaInicio == null) {
                txtFechaInicio = new Calendar();
            }

            txtFechaInicio.setValue(psyTareaDTO.getFechaInicio());

            Long tareCodigo = FacesUtils.checkLong(txtTareCodigo);
            entity = businessDelegatorView.getPsyTarea(tareCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyTarea = null;
        setShowDialog(true);
        setEstadoTarea("Pendiente");
        somResponsable=null;
        txtFechaFinReal.setDisabled(true);
        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyTarea = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            txtDescripcion.setDisabled(false);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtEstadoTarea != null) {
            txtEstadoTarea.setDisabled(true);
        }

        if (txtSemanaFinPlaneada != null) {
            txtSemanaFinPlaneada.setValue(null);
            txtSemanaFinPlaneada.setDisabled(true);
        }

        if (txtSemanaFinReal != null) {
            txtSemanaFinReal.setValue(null);
            txtSemanaFinReal.setDisabled(true);
        }

        if (txtPlacCodigo_PsyPlanAccion != null) {
            txtPlacCodigo_PsyPlanAccion.setDisabled(true);
        }

        if (txtRespCodigo_PsyResponsable != null) {
            txtRespCodigo_PsyResponsable.setDisabled(true);
        }

        if (txtFechaFinPlaneada != null) {
            txtFechaFinPlaneada.setValue(null);
            txtFechaFinPlaneada.setDisabled(false);
        }

        if (txtFechaFinReal != null) {
            txtFechaFinReal.setValue(null);
            txtFechaFinReal.setDisabled(false);
        }

        if (txtFechaInicio != null) {
            txtFechaInicio.setValue(null);
            txtFechaInicio.setDisabled(false);
        }

        if (txtTareCodigo != null) {
            txtTareCodigo.setValue(null);
            txtTareCodigo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(false);
        }

        return "";
    }

    public void listener_txtFechaFinPlaneada() {
        Date inputDate = (Date) txtFechaFinPlaneada.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Fecha seleccionada " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaFinReal() {
    	if(txtFechaFinReal !=null){
        Date inputDate = (Date) txtFechaFinReal.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Fecha seleccionada " + dateFormat.format(inputDate)));
    	}
    }

    public void listener_txtFechaInicio() {
        Date inputDate = (Date) txtFechaInicio.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Fecha seleccionada " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long tareCodigo = FacesUtils.checkLong(txtTareCodigo);
            entity = (tareCodigo != null)
                ? businessDelegatorView.getPsyTarea(tareCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtEstadoTarea.setDisabled(false);
            txtSemanaFinPlaneada.setDisabled(false);
            txtSemanaFinReal.setDisabled(false);
            txtPlacCodigo_PsyPlanAccion.setDisabled(false);
            txtRespCodigo_PsyResponsable.setDisabled(false);
            txtFechaFinPlaneada.setDisabled(false);
            txtFechaFinReal.setDisabled(false);
            txtFechaInicio.setDisabled(false);
            txtTareCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtEstadoTarea.setValue(entity.getEstadoTarea());
            txtEstadoTarea.setDisabled(false);
            txtFechaFinPlaneada.setValue(entity.getFechaFinPlaneada());
            txtFechaFinPlaneada.setDisabled(false);
            txtFechaFinReal.setValue(entity.getFechaFinReal());
            txtFechaFinReal.setDisabled(false);
            txtFechaInicio.setValue(entity.getFechaInicio());
            txtFechaInicio.setDisabled(false);
            txtSemanaFinPlaneada.setValue(entity.getSemanaFinPlaneada());
            txtSemanaFinPlaneada.setDisabled(false);
            txtSemanaFinReal.setValue(entity.getSemanaFinReal());
            txtSemanaFinReal.setDisabled(false);
            txtPlacCodigo_PsyPlanAccion.setValue(entity.getPsyPlanAccion()
                                                       .getPlacCodigo());
            txtPlacCodigo_PsyPlanAccion.setDisabled(false);
            txtRespCodigo_PsyResponsable.setValue(entity.getPsyResponsable()
                                                        .getRespCodigo());
            txtRespCodigo_PsyResponsable.setDisabled(false);
            txtTareCodigo.setValue(entity.getTareCodigo());
            txtTareCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyTarea = (PsyTareaDTO) (evt.getComponent().getAttributes()
                                             .get("selectedPsyTarea"));
        try {
        PsyResponsable responsable = new PsyResponsable();
        
		responsable = businessDelegatorView.
					getPsyResponsable(selectedPsyTarea.getRespCodigo_PsyResponsable());
			
		PsyPlanAccion plan = new PsyPlanAccion();
	    plan = businessDelegatorView.getPsyPlanAccion(selectedPsyTarea.getPlacCodigo_PsyPlanAccion());
		
        txtDescripcion.setValue(selectedPsyTarea.getDescripcion());
        txtDescripcion.setDisabled(false);
        setEstadoTarea((selectedPsyTarea.getEstadoTarea().equals("Pendiente")) ? 
        		"Pendiente" : (selectedPsyTarea.getEstadoTarea().equals("En Curso")) ? "En curso" : "Finalizado");
        setSomResponsable((responsable != null) ? responsable.getRespCodigo().toString() : null);
        txtFechaFinPlaneada.setValue(selectedPsyTarea.getFechaFinPlaneada());
        setSomPlanAccion((plan != null) ? plan.getNombre() : null);
        txtFechaFinPlaneada.setDisabled(false);
        txtFechaFinReal.setValue(selectedPsyTarea.getFechaFinReal());
        txtFechaFinReal.setDisabled(false);
        txtFechaInicio.setValue(selectedPsyTarea.getFechaInicio());
        txtFechaInicio.setDisabled(false);
        btnSave.setDisabled(false);
        setShowDialog(true);
        
        if(selectedPsyTarea.getEstadoTarea().equals("E")){
        	txtDescripcion.setDisabled(true);
        	txtFechaFinPlaneada.setDisabled(true);
        	txtFechaInicio.setDisabled(true);
        	selectOneMenuPlanAccion.setDisabled(true);
        }
        
        } catch (Exception e) {
			FacesUtils.addErrorMessage("Error al cargar los datos en la edicion");
		}
        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyTarea == null) && (entity == null)) {
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
            entity = new PsyTarea();
            
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro(ESTADO_REGISTRO_ACTIVO);
            entity.setEstadoTarea(ESTADO_PENDIENTE);
            entity.setFechaFinPlaneada(FacesUtils.checkDate(txtFechaFinPlaneada));
            entity.setFechaFinReal(FacesUtils.checkDate(txtFechaFinReal));
            entity.setFechaInicio(FacesUtils.checkDate(txtFechaInicio));
            
            entity.setPsyPlanAccion(businessDelegatorView.
            		getPsyPlanAccion(Long.parseLong(somPlanAccionItem)));
            businessDelegatorView.savePsyTarea(entity, somResponsable);
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
                Long tareCodigo = new Long(selectedPsyTarea.getTareCodigo());
                entity = businessDelegatorView.getPsyTarea(tareCodigo);
            }

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setFechaFinPlaneada(FacesUtils.checkDate(txtFechaFinPlaneada));
            entity.setFechaFinReal(FacesUtils.checkDate(txtFechaFinReal));
            entity.setFechaInicio(FacesUtils.checkDate(txtFechaInicio));
            entity.setPsyPlanAccion((somPlanAccion != null) ? businessDelegatorView.getPsyPlanAccion(Long.parseLong(somPlanAccionItem)) : null);
            entity.setPsyResponsable((somResponsable != null || !somResponsable.equals("")) ? 
            		businessDelegatorView.getPsyResponsable(Long.parseLong(somResponsable)) : null);
            businessDelegatorView.updatePsyTarea(entity);
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
            selectedPsyTarea = (PsyTareaDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedPsyTarea"));

            Long tareCodigo = new Long(selectedPsyTarea.getTareCodigo());
            entity = businessDelegatorView.getPsyTarea(tareCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long tareCodigo = FacesUtils.checkLong(txtTareCodigo);
            entity = businessDelegatorView.getPsyTarea(tareCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyTarea(entity);
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
            selectedPsyTarea = (PsyTareaDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedPsyTarea"));

            Long tareCodigo = new Long(selectedPsyTarea.getTareCodigo());
            entity = businessDelegatorView.getPsyTarea(tareCodigo);
            businessDelegatorView.deletePsyTarea(entity);
            data.remove(selectedPsyTarea);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcion,
        String estadoRegistro, String estadoTarea, Date fechaFinPlaneada,
        Date fechaFinReal, Date fechaInicio, Long semanaFinPlaneada,
        Long semanaFinReal, Long tareCodigo, Long placCodigo_PsyPlanAccion,
        Long respCodigo_PsyResponsable) throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setEstadoTarea(FacesUtils.checkString(estadoTarea));
            entity.setFechaFinPlaneada(FacesUtils.checkDate(fechaFinPlaneada));
            entity.setFechaFinReal(FacesUtils.checkDate(fechaFinReal));
            entity.setFechaInicio(FacesUtils.checkDate(fechaInicio));
            entity.setSemanaFinPlaneada(FacesUtils.checkLong(semanaFinPlaneada));
            entity.setSemanaFinReal(FacesUtils.checkLong(semanaFinReal));
            businessDelegatorView.updatePsyTarea(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyTareaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputTextarea getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(InputTextarea txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public InputText getTxtEstadoRegistro() {
        return txtEstadoRegistro;
    }

    public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
        this.txtEstadoRegistro = txtEstadoRegistro;
    }

    public InputText getTxtEstadoTarea() {
        return txtEstadoTarea;
    }

    public void setTxtEstadoTarea(InputText txtEstadoTarea) {
        this.txtEstadoTarea = txtEstadoTarea;
    }

    public InputText getTxtSemanaFinPlaneada() {
        return txtSemanaFinPlaneada;
    }

    public void setTxtSemanaFinPlaneada(InputText txtSemanaFinPlaneada) {
        this.txtSemanaFinPlaneada = txtSemanaFinPlaneada;
    }

    public InputText getTxtSemanaFinReal() {
        return txtSemanaFinReal;
    }

    public void setTxtSemanaFinReal(InputText txtSemanaFinReal) {
        this.txtSemanaFinReal = txtSemanaFinReal;
    }

    public InputText getTxtPlacCodigo_PsyPlanAccion() {
        return txtPlacCodigo_PsyPlanAccion;
    }

    public void setTxtPlacCodigo_PsyPlanAccion(
        InputText txtPlacCodigo_PsyPlanAccion) {
        this.txtPlacCodigo_PsyPlanAccion = txtPlacCodigo_PsyPlanAccion;
    }

    public InputText getTxtRespCodigo_PsyResponsable() {
        return txtRespCodigo_PsyResponsable;
    }

    public void setTxtRespCodigo_PsyResponsable(
        InputText txtRespCodigo_PsyResponsable) {
        this.txtRespCodigo_PsyResponsable = txtRespCodigo_PsyResponsable;
    }

    public Calendar getTxtFechaFinPlaneada() {
        return txtFechaFinPlaneada;
    }

    public void setTxtFechaFinPlaneada(Calendar txtFechaFinPlaneada) {
        this.txtFechaFinPlaneada = txtFechaFinPlaneada;
    }

    public Calendar getTxtFechaFinReal() {
        return txtFechaFinReal;
    }

    public void setTxtFechaFinReal(Calendar txtFechaFinReal) {
        this.txtFechaFinReal = txtFechaFinReal;
    }

    public Calendar getTxtFechaInicio() {
        return txtFechaInicio;
    }

    public void setTxtFechaInicio(Calendar txtFechaInicio) {
        this.txtFechaInicio = txtFechaInicio;
    }

    public InputText getTxtTareCodigo() {
        return txtTareCodigo;
    }

    public void setTxtTareCodigo(InputText txtTareCodigo) {
        this.txtTareCodigo = txtTareCodigo;
    }

    public List<PsyTareaDTO> getData() {
        try {
        	if (data == null && somPlanAccionItem != null && !somPlanAccionItem.trim().equals("")) {
                data = businessDelegatorView.
                		getDataPsyTareaByPsyPlanAccion(Long
                				.parseLong(somPlanAccionItem));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyTareaDTO> psyTareaDTO) {
        this.data = psyTareaDTO;
    }

    public PsyTareaDTO getSelectedPsyTarea() {
        return selectedPsyTarea;
    }

    public void setSelectedPsyTarea(PsyTareaDTO psyTarea) {
        this.selectedPsyTarea = psyTarea;
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
	 * @return the losResponsables
	 */
	public List<SelectItem> getLosResponsables() {
		
		try {
			losResponsables = new ArrayList<SelectItem>();
			List<PsyResponsable> losTiposDeResponsables = businessDelegatorView
					.getPsyResponsableByEmpresa(getEmpresaIntoSession().getEmprCodigo());
			for (PsyResponsable responsable : losTiposDeResponsables) {
				if(responsable.getEstadoRegistro().equals("A")){
					SelectItem selectItem = new SelectItem(
							responsable.getRespCodigo(), responsable.getNombre());
					losResponsables.add(selectItem);
				}
				
			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los responsables activos");
		}
		
		return losResponsables;
	}

	/**
	 * @param losResponsables the losResponsables to set
	 */
	public void setLosResponsables(List<SelectItem> losResponsables) {
		this.losResponsables = losResponsables;
	}

	public String getSomResponsable() {
		return somResponsable;
	}

	public void setSomResponsable(String somResponsable) {
		this.somResponsable = somResponsable;
	}

	/**
	 * @return the estadoTarea
	 */
	public String getEstadoTarea() {
		return estadoTarea;
	}

	/**
	 * @param estadoTarea the estadoTarea to set
	 */
	public void setEstadoTarea(String estadoTarea) {
		this.estadoTarea = estadoTarea;
	}

	public String getSomPlanAccion() {
		return somPlanAccion;
	}

	public void setSomPlanAccion(String somPlanAccion) {
		this.somPlanAccion = somPlanAccion;
	}
	
	

	

	public SelectOneMenu getSelectOneMenuPlanAccion() {
		return selectOneMenuPlanAccion;
	}

	public void setSelectOneMenuPlanAccion(SelectOneMenu selectOneMenuPlanAccion) {
		this.selectOneMenuPlanAccion = selectOneMenuPlanAccion;
	}

	/**
	 * @return the losPlanes
	 */
	public List<SelectItem> getLosPlanes() {
		try {
			losPlanes = new ArrayList<SelectItem>();
			List<PsyPlanAccionDTO> losTiposDePlanes = businessDelegatorView
					.getDataPsyPlanAccion(getEmpresaIntoSession());
			for (PsyPlanAccionDTO plan : losTiposDePlanes) {
				if(plan.getEstadoRegistro().equals("A")){
					SelectItem selectItem = new SelectItem(
							plan.getPlacCodigo(), plan.getNombre());
					losPlanes.add(selectItem);
				}
				
			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los planes activos");
		}
		
		return losPlanes;
	}

	/**
	 * @param losPlanes the losPlanes to set
	 */
	public void setLosPlanes(List<SelectItem> losPlanes) {
		this.losPlanes = losPlanes;
	}

	public String getSomPlanAccionItem() {
		return somPlanAccionItem;
	}

	public void setSomPlanAccionItem(String somPlanAccionItem) {
		this.somPlanAccionItem = somPlanAccionItem;
	}

	public boolean isDisableBtnNew() {
		return disableBtnNew;
	}

	public void setDisableBtnNew(boolean disableBtnNew) {
		this.disableBtnNew = disableBtnNew;
	}

	public CommandButton getBtnNew() {
		return btnNew;
	}

	public void setBtnNew(CommandButton btnNew) {
		this.btnNew = btnNew;
	}

	public List<SelectItem> getLosItemsPlanes() {
		try {
			losItemsPlanes = new ArrayList<SelectItem>();
			
			List<PsyPlanAccion> losTiposDePlanes = businessDelegatorView
					.findPsyPlanAccionByEmpresa(getEmpresaIntoSession());
			for (PsyPlanAccion planAccion : losTiposDePlanes) {
				if (planAccion.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							planAccion.getPlacCodigo(), planAccion.getNombre());
					losItemsPlanes.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los planes de accion");
		}
		
		return losItemsPlanes;
	}

	public void setLosItemsPlanes(List<SelectItem> losItemsPlanes) {
		this.losItemsPlanes = losItemsPlanes;
	}
	
	public PsyEmpresa getEmpresaIntoSession(){
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);
		return (PsyEmpresa) httpSession.getAttribute("empresa");		
	}
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {

		try {
			if(somPlanAccionItem!=null && !somPlanAccionItem.trim().equals("")){
				Long codigoPrograma = businessDelegatorView.getPsyPlanAccion(Long.parseLong(somPlanAccionItem)).getPsyPrograma().getProgCodigo();
				PsyPrograma programaSeleccionado = businessDelegatorView.getPsyPrograma(codigoPrograma);
				PsyProgramaDTO programaTmp = new PsyProgramaDTO();
				PsyDetalleEstrategiasDTO estrategiasDTO = new PsyDetalleEstrategiasDTO();
				
				Document pdf = (Document) document;
				pdf.open();
				pdf.setPageSize(PageSize.LETTER);
				ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
				String logo = servletContext.getRealPath("") + "images" + File.separator + "logoPusay.png";
				Image imageCenter = Image.getInstance(logo);
				imageCenter.setAlignment(Image.MIDDLE);
				pdf.add(imageCenter);
				
				List<PsyProgramaDTO> listProgramas = businessDelegatorView.getDataPsyPrograma(FacesUtils.getEmpresaIntoSession());
				for (PsyProgramaDTO psyProgramaDTO : listProgramas) {
					if(psyProgramaDTO.getProgCodigo()==programaSeleccionado.getProgCodigo()){
						programaTmp = psyProgramaDTO;
					}
				}
				if(programaTmp!=null){
				List<PsyDetalleEstrategiasDTO> laEstrategia = businessDelegatorView.consultaEstrategiasParaPrograma(getEmpresaIntoSession());
				for (PsyDetalleEstrategiasDTO psyDetalleEstrategiasDTO : laEstrategia) {
					if(psyDetalleEstrategiasDTO.getDmaeCodigo()==programaTmp.getDmaeCodigo()){
					estrategiasDTO = psyDetalleEstrategiasDTO;
					}
				}
						pdf.add(new Paragraph("Empresa: "+FacesUtils.getEmpresaIntoSession().getNombre()+"\n"+
								"Plan Estratégico: "+businessDelegatorView.getPlanEstrategicoActivoByPEA(FacesUtils.getEmpresaIntoSession()).getNombre()+"\n"+
								"Nombre del Documento: Plan de acción (cronograma y responsables) | "+businessDelegatorView.getPsyPlanAccion(Long.parseLong(somPlanAccionItem)).getNombre()+" \n"+
								"Programa: "+programaSeleccionado.getNombre()+"\n"+
								"Estrategia: "+estrategiasDTO.getObesNombre()+" | "+
										estrategiasDTO.getImamNombre()+" | "+
										estrategiasDTO.getEsamNombre()+"\n"+
								"Fecha: "+ new Date().toString()+"\n\n"));
				}
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error generando el pdf. El error fue : "+e.getMessage());
		}		

	}
	
	
}
