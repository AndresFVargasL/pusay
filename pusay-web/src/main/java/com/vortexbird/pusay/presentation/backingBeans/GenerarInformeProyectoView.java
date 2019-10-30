package com.vortexbird.pusay.presentation.backingBeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.pusay.exceptions.ZMessManager;
import com.vortexbird.pusay.modelo.PsyDetalleObjetivoPlan;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyIpu;
import com.vortexbird.pusay.modelo.PsyObjetivoEstrategico;
import com.vortexbird.pusay.modelo.PsyPlanAccion;
import com.vortexbird.pusay.modelo.PsyPlanEstrategico;
import com.vortexbird.pusay.modelo.dto.PsyIpuDTO;
import com.vortexbird.pusay.presentation.businessDelegate.IBusinessDelegatorView;
import com.vortexbird.pusay.presentation.utilities.FacesUtils;
import com.vortexbird.pusay.utilities.Utilities;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class GenerarInformeProyectoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(GenerarInformeProyectoView.class);
    private static final String TIPO_IPU_TIEMPO = "T";
    private static final String TIPO_IPU_PRESUPUESTO = "P";
    private static final String ESTADO_ABIERTO = "A";
    private static final String ESTADO_CERRADO = "C";
    private static final String ESTADO_INICIADO = "I";
    private static final String ESTADO_PRESUPUESTADO = "P";
    private InputText txtEstadoIpu;
    private InputText txtEstadoRegistro;
    private InputText txtPeriodo;
    private InputText txtPlacCodigo_PsyPlanAccion;
    private InputText txtIpuCodigo;
    private Calendar txtFechaInforme;
    private Calendar txtPeriodoFechaFin;
    private Calendar txtPeriodoFechaInicio;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyIpuDTO> data;
    private PsyIpuDTO selectedPsyIpu;
    private PsyIpu entity;
    private boolean showDialog;
    private Date fechaInforme;
    private Long semanaInforme;
    private List<PsyIpuDTO> lstIpu = new ArrayList<PsyIpuDTO>();
    private List<PsyIpuDTO> lstIpuPresupuesto = new ArrayList<PsyIpuDTO>();    
    private PsyIpuDTO ipuSeleccionado;
    private String logrosSignificativos;
    private String logrosNoAlcanzados;
    private String causasDesviacion;
    private String accionesPropuestas;
    private List<SelectItem> lstPeriodos = new ArrayList<SelectItem>();
    private String periodoSeleccionado;
    private boolean soloLectura;
	private boolean bloqueado;
	private boolean mostrar;
	private boolean mostrarMensaje;
	private boolean continuar;
	private PsyPlanEstrategico planEstrategico = null;  
	private String mensaje;
	private List<SelectItem> lstPlanAccionItem= new ArrayList<SelectItem>();
	private String planAccionSeleccionadoItem;	
	private List<PsyPlanAccion> lstPlanAccion= new ArrayList<PsyPlanAccion>();
	private PsyPlanAccion planAccionSeleccionado = new PsyPlanAccion();	
    PsyPlanAccion planAccion = null;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public GenerarInformeProyectoView() {
        super();
    }
    
    @PostConstruct
    public void cargarIpu(){
    	
    	//Mensaje oculto para mostrar label con error en mapa estategico no iniciado u otros casos
    	mensaje = "";
    	
    	//Datos temporales para desarrollo
        PsyEmpresa empresa = new PsyEmpresa();
        empresa = (PsyEmpresa)FacesUtils.getfromSession("empresa");
        
        //Variables para consultar datos necesarios en la asignacion de objetivos corportativos
        List<PsyDetalleObjetivoPlan> lstObjetivosCorporativos = null;
        PsyObjetivoEstrategico objetivoEstrategico = new PsyObjetivoEstrategico();
        List<PsyPlanEstrategico> lstPlanEstrategico = null;
    	
    	//Se reestablecen las banderas
        bloqueado = false;
        mostrar = true;
    	mostrarMensaje = false;
    	continuar = true;
    	
    	try {
    		
    		lstPlanAccionItem.clear();
    		//Se consulta el plan estrategico para la empresa seleccionada
        	lstPlanEstrategico = businessDelegatorView.consultarPlanEstrategicoEmpresa(empresa, ESTADO_INICIADO);
    		
    		
        	//Se valida si existe un plan estrategico en estado INICIADO
          	 if(lstPlanEstrategico!=null && !lstPlanEstrategico.isEmpty()){
           		planEstrategico = lstPlanEstrategico.get(0);
           		
           		//Se consultan todos los planes de accion en estado prespuestado
   	           	 lstPlanAccion = businessDelegatorView.consultarPlanesAccion(empresa, planEstrategico,ESTADO_PRESUPUESTADO, ESTADO_PRESUPUESTADO);
   	       		
   	           	 if(lstPlanAccion == null || lstPlanAccion.isEmpty()){
   	           		mensaje = "NO EXISTE AL MENOS UN PLAN DE ACCIÓN EN ESTADO PRESUPUESTADO";
   	             		mostrar = false;
   	             		mostrarMensaje = true;
   	             		continuar = false;
   	           	 }else{
   	           		 
   	           		 for(PsyPlanAccion item: lstPlanAccion){
   	           			 
   	           			lstPlanAccionItem.add(new SelectItem(item.getPlacCodigo(), item.getNombre()));
   	           		 }
   	           		
   	           	 }
           		
           	}else {
           		mensaje = "NO EXISTE UN PLAN ESTRATÉGICO EN ESTADO INICIADO";
           		mostrar = false;
           		mostrarMensaje = true;
           		continuar = false;
           	}
           	
          	if(continuar == true){
       		
          		//Se colocalafecha informe con la fecha actual
    			//fechaInforme = new Date();
    			//Se calcula la semana en que queda la fecha del informe
    			//semanaInforme = Utilities.calcularDiferenciaFechas(planAccion.getFechaInicio(), fechaInforme);
    			
//        		//TODO: Cambiar a una consulta donde me traiga la mayor semana
//    			//se consulta la lista del IPU para el plan de acción seleccionado
//    			lstIpu = businessDelegatorView.consultarIpu(planAccion,TIPO_IPU_TIEMPO);
//        		lstIpuPresupuesto = businessDelegatorView.consultarIpu(planAccion,TIPO_IPU_PRESUPUESTO);	
//        		
//        		Integer cantidadPeriodos = 0;
//        		
//        		//Se identifica la cantidad maxima de periodos que se tienen
//        		if(lstIpu.size()>lstIpuPresupuesto.size()){
//        			cantidadPeriodos = lstIpu.size();
//        		}else{
//        			cantidadPeriodos = lstIpuPresupuesto.size();
//        		}
//        		
//        		//Se crea una lista de SelectItems con cada periodo para consultar
//        		for(int i = 1; i<=cantidadPeriodos; i++){
//        			lstPeriodos.add(new SelectItem(i+"",i+""));
//        		}
//        		
//        		lstIpu = null;
//        		lstIpuPresupuesto = null;
    			  			
    			
    			     			
       	 }
        	
        			 
		
    	}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    
public void listener_plan_accion() {
    	
		try {
			
			if(planAccionSeleccionadoItem!=null || !planAccionSeleccionadoItem.equals("-1")){
				
				planAccionSeleccionado = businessDelegatorView.getPsyPlanAccion(Long.parseLong(planAccionSeleccionadoItem));
				
          		//Se colocalafecha informe con la fecha actual
    			fechaInforme = new Date();
    			//Se calcula la semana en que queda la fecha del informe
    			semanaInforme = Utilities.calcularDiferenciaFechas(planAccionSeleccionado.getFechaInicio(), fechaInforme);
    			
//        		//TODO: Cambiar a una consulta donde me traiga la mayor semana
//    			//se consulta la lista del IPU para el plan de acción seleccionado
    			lstIpu = businessDelegatorView.consultarIpu(planAccionSeleccionado,TIPO_IPU_TIEMPO);
        		lstIpuPresupuesto = businessDelegatorView.consultarIpu(planAccionSeleccionado,TIPO_IPU_PRESUPUESTO);	
//        		
        		Integer cantidadPeriodos = 0;
//        		
//        		//Se identifica la cantidad maxima de periodos que se tienen
        		if(lstIpu.size()>lstIpuPresupuesto.size()){
        			cantidadPeriodos = lstIpu.size();
        		}else{
        			cantidadPeriodos = lstIpuPresupuesto.size();
        		}
//        		
//        		//Se crea una lista de SelectItems con cada periodo para consultar
        		for(int i = 1; i<=cantidadPeriodos; i++){
        			lstPeriodos.add(new SelectItem(i+"",i+""));
        		}
//        		
        		lstIpu = null;
        		lstIpuPresupuesto = null;
				
				
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			FacesUtils.addErrorMessage(e1.getMessage());
		}
    	
    }
    
    
    public String action_consultar(){
    	
    	try {
    		
    			if(planAccionSeleccionadoItem!=null || !planAccionSeleccionadoItem.equals("-1")){
				
				planAccionSeleccionado = businessDelegatorView.getPsyPlanAccion(Long.parseLong(planAccionSeleccionadoItem));
				lstIpu = businessDelegatorView.consultarIpuHastaPeriodo(planAccionSeleccionado,TIPO_IPU_TIEMPO, periodoSeleccionado);
	    		lstIpuPresupuesto = businessDelegatorView.consultarIpuHastaPeriodo(planAccionSeleccionado,TIPO_IPU_PRESUPUESTO,periodoSeleccionado);	
				
    			}
    		
		
			
//			RequestContext.getCurrentInstance().execute(
//					"guardarIpuDialog.hide()");
			
		} catch (Exception e) {			
			FacesUtils.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	
    	return "";
    }
    
    public String action_ingresarIpu(){
    	soloLectura = false;
    	return "";
    }
    
 public String seleccionarPeriodo(){
    	
    	try {
    		
    		soloLectura = true;
    		
			logrosNoAlcanzados = ipuSeleccionado.getLogrosNoAlcanzados();
			logrosSignificativos = ipuSeleccionado.getLogrosAlcanzados();
			accionesPropuestas = ipuSeleccionado.getAccionesPropuestas();
			causasDesviacion = ipuSeleccionado.getCausasDesviacion();
		
			
		} catch (Exception e) {			
			FacesUtils.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	
    	return "";
    }

    public void listener_fechaInforme() {
    	semanaInforme = Utilities.calcularDiferenciaFechas(planAccion.getFechaInicio(), fechaInforme);
    }

    public void listener_txtFechaInforme() {
        Date inputDate = (Date) txtFechaInforme.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtPeriodoFechaFin() {
        Date inputDate = (Date) txtPeriodoFechaFin.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtPeriodoFechaInicio() {
        Date inputDate = (Date) txtPeriodoFechaInicio.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long ipuCodigo = FacesUtils.checkLong(txtIpuCodigo);
            entity = (ipuCodigo != null)
                ? businessDelegatorView.getPsyIpu(ipuCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoIpu.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtPeriodo.setDisabled(false);
            txtPlacCodigo_PsyPlanAccion.setDisabled(false);
            txtFechaInforme.setDisabled(false);
            txtPeriodoFechaFin.setDisabled(false);
            txtPeriodoFechaInicio.setDisabled(false);
            txtIpuCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoIpu.setValue(entity.getEstadoIpu());
            txtEstadoIpu.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtFechaInforme.setValue(entity.getFechaInforme());
            txtFechaInforme.setDisabled(false);
            txtPeriodo.setValue(entity.getPeriodo());
            txtPeriodo.setDisabled(false);
            txtPeriodoFechaFin.setValue(entity.getPeriodoFechaFin());
            txtPeriodoFechaFin.setDisabled(false);
            txtPeriodoFechaInicio.setValue(entity.getPeriodoFechaInicio());
            txtPeriodoFechaInicio.setDisabled(false);
            txtPlacCodigo_PsyPlanAccion.setValue(entity.getPsyPlanAccion()
                                                       .getPlacCodigo());
            txtPlacCodigo_PsyPlanAccion.setDisabled(false);
            txtIpuCodigo.setValue(entity.getIpuCodigo());
            txtIpuCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyIpu = (PsyIpuDTO) (evt.getComponent().getAttributes()
                                         .get("selectedPsyIpu"));
        txtEstadoIpu.setValue(selectedPsyIpu.getEstadoIpu());
        txtEstadoIpu.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyIpu.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtFechaInforme.setValue(selectedPsyIpu.getFechaInforme());
        txtFechaInforme.setDisabled(false);
        txtPeriodo.setValue(selectedPsyIpu.getPeriodo());
        txtPeriodo.setDisabled(false);
        txtPeriodoFechaFin.setValue(selectedPsyIpu.getPeriodoFechaFin());
        txtPeriodoFechaFin.setDisabled(false);
        txtPeriodoFechaInicio.setValue(selectedPsyIpu.getPeriodoFechaInicio());
        txtPeriodoFechaInicio.setDisabled(false);
        txtPlacCodigo_PsyPlanAccion.setValue(selectedPsyIpu.getPlacCodigo_PsyPlanAccion());
        txtPlacCodigo_PsyPlanAccion.setDisabled(false);
        txtIpuCodigo.setValue(selectedPsyIpu.getIpuCodigo());
        txtIpuCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyIpu == null) && (entity == null)) {
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
            entity = new PsyIpu();

            Long ipuCodigo = FacesUtils.checkLong(txtIpuCodigo);

            entity.setEstadoIpu(FacesUtils.checkString(txtEstadoIpu));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setFechaInforme(FacesUtils.checkDate(txtFechaInforme));
            entity.setIpuCodigo(ipuCodigo);
            entity.setPeriodo(FacesUtils.checkString(txtPeriodo));
            entity.setPeriodoFechaFin(FacesUtils.checkDate(txtPeriodoFechaFin));
            entity.setPeriodoFechaInicio(FacesUtils.checkDate(
                    txtPeriodoFechaInicio));
            entity.setPsyPlanAccion((FacesUtils.checkLong(
                    txtPlacCodigo_PsyPlanAccion) != null)
                ? businessDelegatorView.getPsyPlanAccion(FacesUtils.checkLong(
                        txtPlacCodigo_PsyPlanAccion)) : null);
            businessDelegatorView.savePsyIpu(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long ipuCodigo = new Long(selectedPsyIpu.getIpuCodigo());
                entity = businessDelegatorView.getPsyIpu(ipuCodigo);
            }

            entity.setEstadoIpu(FacesUtils.checkString(txtEstadoIpu));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setFechaInforme(FacesUtils.checkDate(txtFechaInforme));
            entity.setPeriodo(FacesUtils.checkString(txtPeriodo));
            entity.setPeriodoFechaFin(FacesUtils.checkDate(txtPeriodoFechaFin));
            entity.setPeriodoFechaInicio(FacesUtils.checkDate(
                    txtPeriodoFechaInicio));
            entity.setPsyPlanAccion((FacesUtils.checkLong(
                    txtPlacCodigo_PsyPlanAccion) != null)
                ? businessDelegatorView.getPsyPlanAccion(FacesUtils.checkLong(
                        txtPlacCodigo_PsyPlanAccion)) : null);
            businessDelegatorView.updatePsyIpu(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyIpu = (PsyIpuDTO) (evt.getComponent().getAttributes()
                                             .get("selectedPsyIpu"));

            Long ipuCodigo = new Long(selectedPsyIpu.getIpuCodigo());
            entity = businessDelegatorView.getPsyIpu(ipuCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long ipuCodigo = FacesUtils.checkLong(txtIpuCodigo);
            entity = businessDelegatorView.getPsyIpu(ipuCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyIpu(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);

        return "";
    }

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedPsyIpu = (PsyIpuDTO) (evt.getComponent().getAttributes()
                                             .get("selectedPsyIpu"));

            Long ipuCodigo = new Long(selectedPsyIpu.getIpuCodigo());
            entity = businessDelegatorView.getPsyIpu(ipuCodigo);
            businessDelegatorView.deletePsyIpu(entity);
            data.remove(selectedPsyIpu);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estadoIpu, String estadoRegistro,
        Date fechaInforme, Long ipuCodigo, String periodo,
        Date periodoFechaFin, Date periodoFechaInicio,
        Long placCodigo_PsyPlanAccion) throws Exception {
        try {
            entity.setEstadoIpu(FacesUtils.checkString(estadoIpu));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFechaInforme(FacesUtils.checkDate(fechaInforme));
            entity.setPeriodo(FacesUtils.checkString(periodo));
            entity.setPeriodoFechaFin(FacesUtils.checkDate(periodoFechaFin));
            entity.setPeriodoFechaInicio(FacesUtils.checkDate(
                    periodoFechaInicio));
            businessDelegatorView.updatePsyIpu(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyIpuView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEstadoIpu() {
        return txtEstadoIpu;
    }

    public void setTxtEstadoIpu(InputText txtEstadoIpu) {
        this.txtEstadoIpu = txtEstadoIpu;
    }

    public InputText getTxtEstadoRegistro() {
        return txtEstadoRegistro;
    }

    public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
        this.txtEstadoRegistro = txtEstadoRegistro;
    }

    public InputText getTxtPeriodo() {
        return txtPeriodo;
    }

    public void setTxtPeriodo(InputText txtPeriodo) {
        this.txtPeriodo = txtPeriodo;
    }

    public InputText getTxtPlacCodigo_PsyPlanAccion() {
        return txtPlacCodigo_PsyPlanAccion;
    }

    public void setTxtPlacCodigo_PsyPlanAccion(
        InputText txtPlacCodigo_PsyPlanAccion) {
        this.txtPlacCodigo_PsyPlanAccion = txtPlacCodigo_PsyPlanAccion;
    }

    public Calendar getTxtFechaInforme() {
        return txtFechaInforme;
    }

    public void setTxtFechaInforme(Calendar txtFechaInforme) {
        this.txtFechaInforme = txtFechaInforme;
    }

    public Calendar getTxtPeriodoFechaFin() {
        return txtPeriodoFechaFin;
    }

    public void setTxtPeriodoFechaFin(Calendar txtPeriodoFechaFin) {
        this.txtPeriodoFechaFin = txtPeriodoFechaFin;
    }

    public Calendar getTxtPeriodoFechaInicio() {
        return txtPeriodoFechaInicio;
    }

    public void setTxtPeriodoFechaInicio(Calendar txtPeriodoFechaInicio) {
        this.txtPeriodoFechaInicio = txtPeriodoFechaInicio;
    }

    public InputText getTxtIpuCodigo() {
        return txtIpuCodigo;
    }

    public void setTxtIpuCodigo(InputText txtIpuCodigo) {
        this.txtIpuCodigo = txtIpuCodigo;
    }

    public List<PsyIpuDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyIpu();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyIpuDTO> psyIpuDTO) {
        this.data = psyIpuDTO;
    }

    public PsyIpuDTO getSelectedPsyIpu() {
        return selectedPsyIpu;
    }

    public void setSelectedPsyIpu(PsyIpuDTO psyIpu) {
        this.selectedPsyIpu = psyIpu;
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

	public Date getFechaInforme() {
		return fechaInforme;
	}

	public void setFechaInforme(Date fechaInforme) {
		this.fechaInforme = fechaInforme;
	}

	public Long getSemanaInforme() {
		return semanaInforme;
	}

	public void setSemanaInforme(Long semanaInforme) {
		this.semanaInforme = semanaInforme;
	}

	public List<PsyIpuDTO> getLstIpu() {
		return lstIpu;
	}

	public void setLstIpu(List<PsyIpuDTO> lstIpu) {
		this.lstIpu = lstIpu;
	}

	public String getLogrosSignificativos() {
		return logrosSignificativos;
	}

	public void setLogrosSignificativos(String logrosSignificativos) {
		this.logrosSignificativos = logrosSignificativos;
	}

	public String getLogrosNoAlcanzados() {
		return logrosNoAlcanzados;
	}

	public void setLogrosNoAlcanzados(String logrosNoAlcanzados) {
		this.logrosNoAlcanzados = logrosNoAlcanzados;
	}

	public String getCausasDesviacion() {
		return causasDesviacion;
	}

	public void setCausasDesviacion(String causasDesviacion) {
		this.causasDesviacion = causasDesviacion;
	}

	public String getAccionesPropuestas() {
		return accionesPropuestas;
	}

	public void setAccionesPropuestas(String accionesPropuestas) {
		this.accionesPropuestas = accionesPropuestas;
	}

	public PsyIpuDTO getIpuSeleccionado() {
		return ipuSeleccionado;
	}

	public void setIpuSeleccionado(PsyIpuDTO ipuSeleccionado) {
		this.ipuSeleccionado = ipuSeleccionado;
	}

	public boolean isSoloLectura() {
		return soloLectura;
	}

	public void setSoloLectura(boolean soloLectura) {
		this.soloLectura = soloLectura;
	}

	public List<PsyIpuDTO> getLstIpuPresupuesto() {
		return lstIpuPresupuesto;
	}

	public void setLstIpuPresupuesto(List<PsyIpuDTO> lstIpuPresupuesto) {
		this.lstIpuPresupuesto = lstIpuPresupuesto;
	}

	public List<SelectItem> getLstPeriodos() {
		return lstPeriodos;
	}

	public void setLstPeriodos(List<SelectItem> lstPeriodos) {
		this.lstPeriodos = lstPeriodos;
	}

	public String getPeriodoSeleccionado() {
		return periodoSeleccionado;
	}

	public void setPeriodoSeleccionado(String periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

	public boolean isMostrarMensaje() {
		return mostrarMensaje;
	}

	public void setMostrarMensaje(boolean mostrarMensaje) {
		this.mostrarMensaje = mostrarMensaje;
	}

	public boolean isContinuar() {
		return continuar;
	}

	public void setContinuar(boolean continuar) {
		this.continuar = continuar;
	}

	public PsyPlanEstrategico getPlanEstrategico() {
		return planEstrategico;
	}

	public void setPlanEstrategico(PsyPlanEstrategico planEstrategico) {
		this.planEstrategico = planEstrategico;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<SelectItem> getLstPlanAccionItem() {
		return lstPlanAccionItem;
	}

	public void setLstPlanAccionItem(List<SelectItem> lstPlanAccionItem) {
		this.lstPlanAccionItem = lstPlanAccionItem;
	}

	public String getPlanAccionSeleccionadoItem() {
		return planAccionSeleccionadoItem;
	}

	public void setPlanAccionSeleccionadoItem(String planAccionSeleccionadoItem) {
		this.planAccionSeleccionadoItem = planAccionSeleccionadoItem;
	}

	public List<PsyPlanAccion> getLstPlanAccion() {
		return lstPlanAccion;
	}

	public void setLstPlanAccion(List<PsyPlanAccion> lstPlanAccion) {
		this.lstPlanAccion = lstPlanAccion;
	}

	public PsyPlanAccion getPlanAccionSeleccionado() {
		return planAccionSeleccionado;
	}

	public void setPlanAccionSeleccionado(PsyPlanAccion planAccionSeleccionado) {
		this.planAccionSeleccionado = planAccionSeleccionado;
	}

	public PsyPlanAccion getPlanAccion() {
		return planAccion;
	}

	public void setPlanAccion(PsyPlanAccion planAccion) {
		this.planAccion = planAccion;
	}

    
    
}
