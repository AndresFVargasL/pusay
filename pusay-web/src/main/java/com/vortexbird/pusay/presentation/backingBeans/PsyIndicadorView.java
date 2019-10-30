package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyEvaluacionPeaIndicadorDTO;
import com.vortexbird.pusay.modelo.dto.PsyEvaluarIndicadoresDTO;
import com.vortexbird.pusay.modelo.dto.PsyIndicadorDTO;
import com.vortexbird.pusay.modelo.dto.PsyIndicadorGestionDTO;
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

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyIndicadorView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyIndicadorView.class);
    private static final String ESTADO_REGISTRO_ACTIVO ="A";
    private static final String ESTADO_REGISTRO_INACTIVO = "I";
    private static final String TIPO_INDICADOR_MULITPLE ="M";
    private static final String TIPO_INDICADOR_NO_APLICA = "-";
    private String somImpactoAmbiental;
    private List<SelectItem> losImpactosItems;
    private List<SelectItem> losObjetivosAmbientales;
    private List<SelectItem> losTemas;
    private List<SelectItem> losSubtemas;
    private String somObam;
    private String somTema;
    private String somSubtema;
    private String somTipoIndicador;
    private InputText txtDescripcion;
    private InputText txtEstadoRegistro;
    private InputText txtTipoIndicador;
    private InputText txtUnidadMedida;
    private InputText txtCodigo_PsyObjetivoAmbiental;
    private InputText txtCodigo_PsySubtema;
    private InputText txtCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyEvaluarIndicadoresDTO> data;
    private List<PsyEvaluacionPeaIndicadorDTO> dataEvaluados;
    private List<PsyIndicadorGestionDTO> dataGestionIndicadores;
    private PsyIndicadorGestionDTO selectedPsyIndicador;
	private PsyEvaluarIndicadoresDTO selectedPsyIndicadorAEvaluar;
    private PsyIndicador entity;
    private PsyEvaluacionPeaIndicador evaluacionIndicador;
    private PsyEvaluacionPeaObjetivo evaluacionObjetivo;
    private boolean showDialog;
    private boolean showDialogEvaluar;
    private boolean pintar;
    private boolean pintarMensajeError;   
    private InputText txtPeriodo;
    private InputText txtMeta;
    private InputText txtNorma;
    private InputText txtSectorial;
    private InputText txtResultado;
    private List<SelectItem> losPlanesPEA;
    private String somPEA;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PsyIndicadorView() {
        super();
    }
    
    @PostConstruct
    public void psyIndicadorView() {
    	
       try {
		PsyPlanEstrategico planEstrategico = businessDelegatorView.getPlanEstrategicoActivoByPEA(getEmpresaIntoSession());
		if(planEstrategico==null){
			pintar=false;
			pintarMensajeError=true;
		}else{
			pintar=true;
			pintarMensajeError=false;
		}
	} catch (Exception e) {
		FacesUtils.addErrorMessage(e.getMessage());
	}
    }
    
	public PsyEmpresa getEmpresaIntoSession(){
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);
		return (PsyEmpresa) httpSession.getAttribute("empresa");		
	}
	
	public void evaluarIndicador(){
		try {
			
			evaluacionIndicador = new PsyEvaluacionPeaIndicador();
			
	        Long codigo = selectedPsyIndicadorAEvaluar.getEvaCodigo();
	        evaluacionIndicador = (codigo != null)
	                ? businessDelegatorView.getPsyEvaluacionPeaIndicador(codigo) : new PsyEvaluacionPeaIndicador();

            evaluacionIndicador.setEstadoRegistro(ESTADO_REGISTRO_ACTIVO);
            evaluacionIndicador.setMeta((FacesUtils.checkDouble(txtMeta) != null)
            		? new Double(txtMeta.getValue().toString().trim()) : null);
            evaluacionIndicador.setMultiple("-");
            evaluacionIndicador.setNorma((FacesUtils.checkDouble(txtNorma) != null) ? 
            		new Double(txtNorma.getValue().toString().trim()) : null);
            evaluacionIndicador.setPeriodo((FacesUtils.checkLong(txtPeriodo) != null) ? FacesUtils.checkLong(txtPeriodo) : null);
            evaluacionIndicador.setResultado((FacesUtils.checkDouble(txtResultado) != null)
            		? new Double(txtResultado.getValue().toString().trim()) : null);
            evaluacionIndicador.setHistorial((selectedPsyIndicadorAEvaluar.getResultado() != null) ? 
            		selectedPsyIndicadorAEvaluar.getResultado() : null);
            evaluacionIndicador.setSectorial((FacesUtils.checkDouble(txtSectorial) != null)
            		? new Double(txtSectorial.getValue().toString().trim()) : null);
            evaluacionIndicador.setPsyIndicador((selectedPsyIndicadorAEvaluar != null)
                ? businessDelegatorView.getPsyIndicador(selectedPsyIndicadorAEvaluar.getIndiCodigo()) : null);
            evaluacionIndicador.setPsyPlanEstrategicoAmbiental((somPEA != null)
                ? businessDelegatorView.getPsyPlanEstrategicoAmbiental(Long.parseLong(somPEA))
                : null);
            
            evaluacionObjetivo = new PsyEvaluacionPeaObjetivo();
            
            evaluacionObjetivo.setEstadoRegistro(ESTADO_REGISTRO_ACTIVO);
            evaluacionObjetivo.setPeriodo((FacesUtils.checkLong(txtPeriodo) != null) ? FacesUtils.checkLong(txtPeriodo) : null);
            evaluacionObjetivo.setResultado((FacesUtils.checkDouble(txtResultado) != null)
            		? new Double(txtResultado.getValue().toString().trim()) : null);
            evaluacionObjetivo.setHistorial((evaluacionIndicador.getHistorial() != null) ? evaluacionIndicador.getHistorial() : null);
            evaluacionObjetivo.setPsyObjetivoAmbiental((selectedPsyIndicadorAEvaluar != null)
                ? businessDelegatorView.getPsyObjetivoAmbiental(
                		selectedPsyIndicadorAEvaluar.getObamCodigo()) : null);
            evaluacionObjetivo.setPsyPlanEstrategicoAmbiental((somPEA != null)
                    ? businessDelegatorView.getPsyPlanEstrategicoAmbiental(Long.parseLong(somPEA))
                            : null);
            
            businessDelegatorView.evaluarIndicador(evaluacionIndicador, evaluacionObjetivo);
            FacesUtils.addInfoMessage("Indicador Evaluado Exitosamente");
            cleanEvaluacion();
            closeDialogEvaluar();
            data=null;
            
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyIndicadorDTO psyIndicadorDTO = (PsyIndicadorDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(psyIndicadorDTO.getDescripcion());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyIndicadorDTO.getEstadoRegistro());

            if (txtTipoIndicador == null) {
                txtTipoIndicador = new InputText();
            }

            txtTipoIndicador.setValue(psyIndicadorDTO.getTipoIndicador());

            if (txtUnidadMedida == null) {
                txtUnidadMedida = new InputText();
            }

            txtUnidadMedida.setValue(psyIndicadorDTO.getUnidadMedida());

            if (txtCodigo_PsyObjetivoAmbiental == null) {
                txtCodigo_PsyObjetivoAmbiental = new InputText();
            }

            txtCodigo_PsyObjetivoAmbiental.setValue(psyIndicadorDTO.getCodigo_PsyObjetivoAmbiental());

            if (txtCodigo_PsySubtema == null) {
                txtCodigo_PsySubtema = new InputText();
            }

            txtCodigo_PsySubtema.setValue(psyIndicadorDTO.getCodigo_PsySubtema());

            if (txtCodigo == null) {
                txtCodigo = new InputText();
            }

            txtCodigo.setValue(psyIndicadorDTO.getCodigo());

            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = businessDelegatorView.getPsyIndicador(codigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        txtEstadoRegistro.setValue("Activo");
        selectedPsyIndicador = null;
        setShowDialog(true);

        return "";
    }
    

    
    public String action_evaluacionNueva(ActionEvent evt) {
    	selectedPsyIndicadorAEvaluar = null;
    	selectedPsyIndicadorAEvaluar = (PsyEvaluarIndicadoresDTO) (evt.getComponent()
                .getAttributes()
                .get("selectedPsyIndicadorAEvaluar"));
    	setSomPEA((selectedPsyIndicadorAEvaluar.getPeaCodigo() != null) ? selectedPsyIndicadorAEvaluar.getPeaCodigo().toString() : "0");
    	txtPeriodo.setValue((selectedPsyIndicadorAEvaluar.getPeriodo() != null) ? selectedPsyIndicadorAEvaluar.getPeriodo() : null);
    	txtResultado.setValue((selectedPsyIndicadorAEvaluar.getResultado() != null) ? selectedPsyIndicadorAEvaluar.getResultado() : null);;
    	txtMeta.setValue((selectedPsyIndicadorAEvaluar.getMeta() != null) ? selectedPsyIndicadorAEvaluar.getMeta() : null);
    	txtNorma.setValue((selectedPsyIndicadorAEvaluar.getNorma() != null) ? selectedPsyIndicadorAEvaluar.getNorma() : null);
    	txtSectorial.setValue((selectedPsyIndicadorAEvaluar.getSectorial() != null) ? selectedPsyIndicadorAEvaluar.getSectorial() : null);
        setShowDialogEvaluar(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyIndicador = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setDisabled(true);
        }

        
        if (txtUnidadMedida != null) {
            txtUnidadMedida.setValue(null);
        }
        
        setSomObam("0");
        setSomTema("0");
        setSomSubtema("0");
        setSomTipoIndicador("0");

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(false);
        }

        return "";
    }
    
    public String cleanEvaluacion() {
    	
    	evaluacionIndicador = null;
    	evaluacionObjetivo = null;
    	selectedPsyIndicadorAEvaluar = null;
    	
    	if (txtPeriodo != null) {
        	txtPeriodo.setValue(null);
        }

        if (txtResultado != null) {
        	txtResultado.setValue(null);
        }

        
        if (txtMeta != null) {
        	txtMeta.setValue(null);
        }
        
        if (txtNorma != null) {
        	txtNorma.setValue(null);
        }
        
        if (txtSectorial != null) {
        	txtSectorial.setValue(null);
        }
        
        setSomPEA("0");

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = (codigo != null)
                ? businessDelegatorView.getPsyIndicador(codigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtTipoIndicador.setDisabled(false);
            txtUnidadMedida.setDisabled(false);
            txtCodigo_PsyObjetivoAmbiental.setDisabled(false);
            txtCodigo_PsySubtema.setDisabled(false);
            txtCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtTipoIndicador.setValue(entity.getTipoIndicador());
            txtTipoIndicador.setDisabled(false);
            txtUnidadMedida.setValue(entity.getUnidadMedida());
            txtUnidadMedida.setDisabled(false);
            txtCodigo_PsyObjetivoAmbiental.setValue(entity.getPsyObjetivoAmbiental()
                                                          .getCodigo());
            txtCodigo_PsyObjetivoAmbiental.setDisabled(false);
            txtCodigo_PsySubtema.setValue(entity.getPsySubtema().getCodigo());
            txtCodigo_PsySubtema.setDisabled(false);
            txtCodigo.setValue(entity.getCodigo());
            txtCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyIndicador = (PsyIndicadorGestionDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedPsyIndicador"));
        txtDescripcion.setValue(selectedPsyIndicador.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyIndicador.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(true);
        txtUnidadMedida.setValue(selectedPsyIndicador.getUnidadMedida());
        txtUnidadMedida.setDisabled(false);
        setSomTema((selectedPsyIndicador.getCodigoTema()).toString());
        setSomSubtema((selectedPsyIndicador.getCodigo_PsySubtema()).toString());
        setSomObam((selectedPsyIndicador.getCodigo_PsyObjetivoAmbiental()).toString());
        setSomTipoIndicador(((selectedPsyIndicador.getTipoIndicador().trim().equalsIgnoreCase("Multiple")) ? 
        		TIPO_INDICADOR_MULITPLE : TIPO_INDICADOR_NO_APLICA ).toString());
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyIndicador == null) && (entity == null)) {
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
            entity = new PsyIndicador();
            
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setTipoIndicador(somTipoIndicador);
            entity.setUnidadMedida(FacesUtils.checkString(txtUnidadMedida));
            entity.setPsyObjetivoAmbiental((somObam != null)
                ? businessDelegatorView.getPsyObjetivoAmbiental(Long.parseLong(somObam)) : null);
            entity.setPsySubtema((somSubtema != null)
                ? businessDelegatorView.getPsySubtema(Long.parseLong(somSubtema)) : null);
            businessDelegatorView.savePsyIndicador(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
            dataGestionIndicadores=null;
        } catch (Exception e) {
            entity = null;
            dataGestionIndicadores=null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long codigo = new Long(selectedPsyIndicador.getCodigo());
                entity = businessDelegatorView.getPsyIndicador(codigo);
            }

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setTipoIndicador(somTipoIndicador);
            entity.setUnidadMedida(FacesUtils.checkString(txtUnidadMedida));
            entity.setPsyObjetivoAmbiental((somObam != null)
                ? businessDelegatorView.getPsyObjetivoAmbiental(Long.parseLong(somObam)) : null);
            entity.setPsySubtema((somSubtema != null)
                ? businessDelegatorView.getPsySubtema(Long.parseLong(somSubtema)) : null);
            businessDelegatorView.updatePsyIndicador(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
            action_clear();
            dataGestionIndicadores=null;
        } catch (Exception e) {
        	dataGestionIndicadores=null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyIndicador = (PsyIndicadorGestionDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedPsyIndicador"));

            Long codigo = new Long(selectedPsyIndicador.getCodigo());
            entity = businessDelegatorView.getPsyIndicador(codigo);
            action_delete();
            dataGestionIndicadores=null;
        } catch (Exception e) {
        	entity=null;
        	dataGestionIndicadores=null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigo = FacesUtils.checkLong(txtCodigo);
            entity = businessDelegatorView.getPsyIndicador(codigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyIndicador(entity);
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
    
    public String closeDialogEvaluar() {
        setShowDialogEvaluar(false);
        cleanEvaluacion();

        return "";
    }

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedPsyIndicador = (PsyIndicadorGestionDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedPsyIndicador"));

            Long codigo = new Long(selectedPsyIndicador.getCodigo());
            entity = businessDelegatorView.getPsyIndicador(codigo);
            businessDelegatorView.deletePsyIndicador(entity);
            data.remove(selectedPsyIndicador);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigo, String descripcion,
        String estadoRegistro, String tipoIndicador, String unidadMedida,
        Long codigo_PsyObjetivoAmbiental, Long codigo_PsySubtema)
        throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setTipoIndicador(FacesUtils.checkString(tipoIndicador));
            entity.setUnidadMedida(FacesUtils.checkString(unidadMedida));
            businessDelegatorView.updatePsyIndicador(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyIndicadorView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }
    
public void preProcessPDFIndicadores(Object document) throws IOException, BadElementException, DocumentException {
		
		try {
		
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.LETTER);
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String logo = servletContext.getRealPath("") + "images" + File.separator + "logoPusay.png";
        Image imageCenter = Image.getInstance(logo);
        imageCenter.setAlignment(Image.MIDDLE);
        pdf.add(imageCenter);
        pdf.add(new Paragraph("Empresa: "+FacesUtils.getEmpresaIntoSession().getNombre()+"\n"+
        					  "Plan Estratégico: "+businessDelegatorView.getPlanEstrategicoActivoByPEA(FacesUtils.getEmpresaIntoSession()).getNombre()+"\n"+
        					  "Nombre del Documento: Sistema de Indicadores de Gestión \n"+
        					  "Fecha: "+ new Date().toString()+"\n\n"));
				
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error generando el pdf. El error fue : "+e.getMessage());
		}		
        
        
        
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

    public InputText getTxtTipoIndicador() {
        return txtTipoIndicador;
    }

    public void setTxtTipoIndicador(InputText txtTipoIndicador) {
        this.txtTipoIndicador = txtTipoIndicador;
    }

    public InputText getTxtUnidadMedida() {
        return txtUnidadMedida;
    }

    public void setTxtUnidadMedida(InputText txtUnidadMedida) {
        this.txtUnidadMedida = txtUnidadMedida;
    }

    public InputText getTxtCodigo_PsyObjetivoAmbiental() {
        return txtCodigo_PsyObjetivoAmbiental;
    }

    public void setTxtCodigo_PsyObjetivoAmbiental(
        InputText txtCodigo_PsyObjetivoAmbiental) {
        this.txtCodigo_PsyObjetivoAmbiental = txtCodigo_PsyObjetivoAmbiental;
    }

    public InputText getTxtCodigo_PsySubtema() {
        return txtCodigo_PsySubtema;
    }

    public void setTxtCodigo_PsySubtema(InputText txtCodigo_PsySubtema) {
        this.txtCodigo_PsySubtema = txtCodigo_PsySubtema;
    }

    public InputText getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(InputText txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public List<PsyEvaluarIndicadoresDTO> getData() {
        try {
            if (data == null && somImpactoAmbiental != null && !somImpactoAmbiental.trim().equals("")) {
            	Long imamCodigo = Long.parseLong(somImpactoAmbiental);
            	List<PsyPlanEstrategicoAmbiental> planActivo = businessDelegatorView.getPEA(getEmpresaIntoSession());
                data = businessDelegatorView.getDataPsyIndicador(getEmpresaIntoSession(), imamCodigo, planActivo.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyEvaluarIndicadoresDTO> psyIndicadorDTO) {
        this.data = psyIndicadorDTO;
    }

    public PsyIndicadorGestionDTO getSelectedPsyIndicador() {
        return selectedPsyIndicador;
    }

    public void setSelectedPsyIndicador(PsyIndicadorGestionDTO psyIndicador) {
        this.selectedPsyIndicador = psyIndicador;
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

	public String getSomImpactoAmbiental() {
		return somImpactoAmbiental;
	}

	public void setSomImpactoAmbiental(String somImpactoAmbiental) {
		this.somImpactoAmbiental = somImpactoAmbiental;
	}

	public List<SelectItem> getLosImpactosItems() {

		try {
			losImpactosItems = new ArrayList<SelectItem>();
			
			List<PsyImpactoAmbiental> losTiposDeImpactos = businessDelegatorView
					.consultaImpactosAmbientalesSeleccionados(getEmpresaIntoSession());
			for (PsyImpactoAmbiental impactoAmbiental : losTiposDeImpactos) {
				if (impactoAmbiental.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							impactoAmbiental.getImamCodigo(), impactoAmbiental.getDescripcion());
					losImpactosItems.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando impacto ambientales");
		}
	
		return losImpactosItems;
	}

	public void setLosImpactosItems(List<SelectItem> losImpactosItems) {
		this.losImpactosItems = losImpactosItems;
	}
	
	public void listener_txtIndicadorItem() {
		
		data = null;
		getData();
		
	}

	public boolean isPintar() {
		return pintar;
	}

	public void setPintar(boolean pintar) {
		this.pintar = pintar;
	}

	public boolean isPintarMensajeError() {
		return pintarMensajeError;
	}

	public void setPintarMensajeError(boolean pintarMensajeError) {
		this.pintarMensajeError = pintarMensajeError;
	}

	public List<PsyIndicadorGestionDTO> getDataGestionIndicadores() {
		try {
            if (dataGestionIndicadores == null) {
            	dataGestionIndicadores = businessDelegatorView.getDataPsyIndicadorGestion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return dataGestionIndicadores;
	}

	public void setDataGestionIndicadores(
			List<PsyIndicadorGestionDTO> dataGestionIndicadores) {
		this.dataGestionIndicadores = dataGestionIndicadores;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public List<SelectItem> getLosObjetivosAmbientales() {
		try {
			losObjetivosAmbientales = new ArrayList<SelectItem>();
			
			List<PsyObjetivoAmbiental> losTiposdeObjetivos = businessDelegatorView
					.getPsyObjetivoAmbiental();
			for (PsyObjetivoAmbiental objetivoAmbiental : losTiposdeObjetivos) {
				if (objetivoAmbiental.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							objetivoAmbiental.getCodigo(), objetivoAmbiental.getDescripcion());
					losObjetivosAmbientales.add(selectItem);
				}
			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los objetivos ambientales");
		}
		return losObjetivosAmbientales;
	}

	public void setLosObjetivosAmbientales(List<SelectItem> losObjetivosAmbientales) {
		this.losObjetivosAmbientales = losObjetivosAmbientales;
	}

	public List<SelectItem> getLosTemas() {
		try {
			losTemas = new ArrayList<SelectItem>();
			
			List<PsyTema> losTiposDeTemas = businessDelegatorView
					.getPsyTema();
			for (PsyTema tema : losTiposDeTemas) {
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

	public List<SelectItem> getLosSubtemas() {
		try {
			if(somTema!=null){
			if(!somTema.trim().equals("0")){
				losSubtemas = new ArrayList<SelectItem>();
			List<PsySubtema> losTiposProvincias = businessDelegatorView.consultarSubTemasPorTema(Long.parseLong(somTema));
			for (PsySubtema subTema : losTiposProvincias) {
				if (subTema.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							subTema.getCodigo(), subTema.getDescripcion());
					losSubtemas.add(selectItem);
				}

			}
			}
			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los subtemas");
		}
		return losSubtemas;
	}

	public void setLosSubtemas(List<SelectItem> losSubtemas) {
		this.losSubtemas = losSubtemas;
	}

	public String getSomObam() {
		return somObam;
	}

	public void setSomObam(String somObam) {
		this.somObam = somObam;
	}

	public String getSomTema() {
		return somTema;
	}

	public void setSomTema(String somTema) {
		this.somTema = somTema;
	}

	public String getSomSubtema() {
		return somSubtema;
	}

	public void setSomSubtema(String somSubtema) {
		this.somSubtema = somSubtema;
	}

	public String getSomTipoIndicador() {
		return somTipoIndicador;
	}

	public void setSomTipoIndicador(String somTipoIndicador) {
		this.somTipoIndicador = somTipoIndicador;
	}
	
	
	public void listener_SomTema() {
		
		if(somTema.trim().equals("0")){
			losSubtemas=null;
		}
		
		getLosSubtemas();
		
	}

	public InputText getTxtPeriodo() {
		return txtPeriodo;
	}

	public void setTxtPeriodo(InputText txtPeriodo) {
		this.txtPeriodo = txtPeriodo;
	}

	public InputText getTxtMeta() {
		return txtMeta;
	}

	public void setTxtMeta(InputText txtMeta) {
		this.txtMeta = txtMeta;
	}

	public InputText getTxtNorma() {
		return txtNorma;
	}

	public void setTxtNorma(InputText txtNorma) {
		this.txtNorma = txtNorma;
	}

	public InputText getTxtSectorial() {
		return txtSectorial;
	}

	public void setTxtSectorial(InputText txtSectorial) {
		this.txtSectorial = txtSectorial;
	}

	public InputText getTxtResultado() {
		return txtResultado;
	}

	public void setTxtResultado(InputText txtResultado) {
		this.txtResultado = txtResultado;
	}

	public List<SelectItem> getLosPlanesPEA() {
		try {

			losPlanesPEA = new ArrayList<SelectItem>();
			List<PsyPlanEstrategicoAmbiental> losTiposPlanes = businessDelegatorView.getPEA(getEmpresaIntoSession());
			for (PsyPlanEstrategicoAmbiental planEstrategicoAmbiental : losTiposPlanes) {
				if (planEstrategicoAmbiental.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							planEstrategicoAmbiental.getCodigo(), planEstrategicoAmbiental.getNombre());
					losPlanesPEA.add(selectItem);
				}

			}
			
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los planes estrategicos ambientales");
		}
		return losPlanesPEA;
	}

	public void setLosPlanesPEA(List<SelectItem> losPlanesPEA) {
		this.losPlanesPEA = losPlanesPEA;
	}

	public String getSomPEA() {
		return somPEA;
	}

	public void setSomPEA(String somPEA) {
		this.somPEA = somPEA;
	}

	public boolean isShowDialogEvaluar() {
		return showDialogEvaluar;
	}

	public void setShowDialogEvaluar(boolean showDialogEvaluar) {
		this.showDialogEvaluar = showDialogEvaluar;
	}

	public PsyEvaluarIndicadoresDTO getSelectedPsyIndicadorAEvaluar() {
		return selectedPsyIndicadorAEvaluar;
	}

	public void setSelectedPsyIndicadorAEvaluar(
			PsyEvaluarIndicadoresDTO selectedPsyIndicadorAEvaluar) {
		this.selectedPsyIndicadorAEvaluar = selectedPsyIndicadorAEvaluar;
	}

	public List<PsyEvaluacionPeaIndicadorDTO> getDataEvaluados() {
		try {
            if (dataEvaluados == null) {
            	dataEvaluados = businessDelegatorView.getDataPsyEvaluacionPeaIndicador(getEmpresaIntoSession());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return dataEvaluados;
	}

	public void setDataEvaluados(List<PsyEvaluacionPeaIndicadorDTO> dataEvaluados) {
		this.dataEvaluados = dataEvaluados;
	}
	
	public void preProcessPDFMetas(Object document) throws IOException, BadElementException, DocumentException {

		try {

			Document pdf = (Document) document;
			pdf.open();
			pdf.setPageSize(PageSize.LETTER);
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String logo = servletContext.getRealPath("") + "images" + File.separator + "logoPusay.png";
			Image imageCenter = Image.getInstance(logo);
			imageCenter.setAlignment(Image.MIDDLE);
			pdf.add(imageCenter);
			pdf.add(new Paragraph("Empresa: "+FacesUtils.getEmpresaIntoSession().getNombre()+"\n"+
					"Plan Estratégico: "+businessDelegatorView.getPlanEstrategicoActivoByPEA(FacesUtils.getEmpresaIntoSession()).getNombre()+"\n"+
					"Nombre del Documento: Resultados Esperados(Metas) \n"+
					"Fecha: "+ new Date().toString()+"\n\n"));

		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error generando el pdf. El error fue : "+e.getMessage());
		}		



	}

	
}
