package com.vortexbird.pusay.presentation.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.pusay.modelo.PsyDetalleObjetivoPlan;
import com.vortexbird.pusay.modelo.PsyEmpresa;
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
public class IpuView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(IpuView.class);
    private static final String TIPO_IPU_TIEMPO = "T";
    private static final String TIPO_IPU_PRESUPEUSTO = "P";
    private static final String ESTADO_ABIERTO = "A";
    private static final String ESTADO_CERRADO = "C";
    private static final String ESTADO_INICIADO = "I";
    private static final String ESTADO_PRESUPUESTADO = "P";
    private Date fechaInforme;
    private Long semanaInforme;
    private List<PsyIpuDTO> lstIpu = new ArrayList<PsyIpuDTO>();
    private PsyIpuDTO ipuSeleccionado;
    private String logrosSignificativos;
    private String logrosNoAlcanzados;
    private String causasDesviacion;
    private String accionesPropuestas;
	private boolean soloLectura;
	private boolean bloqueado;
	private boolean mostrar;
	private boolean mostrarMensaje;
	private boolean continuar;
	private PsyPlanEstrategico planEstrategico = null;  
	private String mensaje;
	private List<SelectItem> lstPlanAccionItem= new ArrayList<SelectItem>();
	private String planAccionSeleccionadoItem;
	private LineChartModel lineModel1;
	
	private List<PsyPlanAccion> lstPlanAccion= new ArrayList<PsyPlanAccion>();
	private PsyPlanAccion planAccionSeleccionado = new PsyPlanAccion();
    
    PsyPlanAccion planAccion = null;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public IpuView() {
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
         		
         		//Se consultan todos los planes de accion en estado iniciado o prespuestado
	           	 lstPlanAccion = businessDelegatorView.consultarPlanesAccion(empresa, planEstrategico,ESTADO_INICIADO, ESTADO_PRESUPUESTADO);
	       		
	           	 if(lstPlanAccion == null || lstPlanAccion.isEmpty()){
	           		mensaje = "NO EXISTE AL MENOS UN PLAN DE ACCIÓN EN ESTADO INICIADO O PRESUPUESTADO";
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
        		//planAccion = lstPlanAccion.get(0);
        		        		 
     			//Se colocalafecha informe con la fecha actual
     			fechaInforme = new Date();
     			
     			     			
        	 }
        	 
        	 
        	 createLineModels();	
    			
    	}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    
    
    private void createLineModels() {
     
    	    	
    	
        lineModel1 = initCategoryModel();
        lineModel1.setTitle("Avance en Tiempo");
        lineModel1.setLegendPosition("e");
        lineModel1.setShowPointLabels(true);
        lineModel1.setAnimate(true);
        lineModel1.getAxes().put(AxisType.X, new CategoryAxis("Periodo"));
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setLabel("Porcentaje de avance (%)");
        yAxis.setMin(0);
        yAxis.setMax(100);
    }
    
       
    
    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
 
        ChartSeries presupuestado = new ChartSeries();
        presupuestado.setLabel("% Avance Ppto");
        
        if(lstIpu == null || lstIpu.isEmpty()){
        	presupuestado.set(0, 0);
        	presupuestado.set(0, 0);
        }
        
        for(PsyIpuDTO itemIpu: lstIpu){
        	presupuestado.set(itemIpu.getPeriodo(), itemIpu.getAvancePresupuestado() * 100);        	
        }
        
 
        ChartSeries real = new ChartSeries();
        real.setLabel("% Avance Real");
        
        Double porcentaje = 0D; 
        
        if(lstIpu == null || lstIpu.isEmpty()){
        	real.set(0, 0);
        	real.set(0, 0);
        }
        
        
        for(PsyIpuDTO itemIpu: lstIpu){
        	
        	porcentaje = itemIpu.getAvanceReal();
        	if(porcentaje == null){
        		real.set(itemIpu.getPeriodo(),0); 
        	}else{
        	real.set(itemIpu.getPeriodo(), itemIpu.getAvanceReal()*100);   
        	}
        }
         
        model.addSeries(presupuestado);
        model.addSeries(real);
         
        return model;
    }
    
    
    public String generarIpu(){
    	
    	try {
    		lstIpu = businessDelegatorView.consultarIpu(planAccionSeleccionado,TIPO_IPU_TIEMPO);  		
    		planAccionSeleccionado = businessDelegatorView.getPsyPlanAccion(Long.parseLong(planAccionSeleccionadoItem));
			lstIpu = businessDelegatorView.generarIpu(planAccionSeleccionado,TIPO_IPU_TIEMPO,fechaInforme,semanaInforme,lstIpu, logrosSignificativos,logrosNoAlcanzados, causasDesviacion,accionesPropuestas, true);
			createLineModels();
			FacesUtils.addInfoMessage("Se ha registrado el ipu satisfactoriamente");			
			
		} catch (Exception e) {			
			FacesUtils.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	
    	return "";
    }
    
     public String generarIpuLogico(){
    	
    	try {
    		    	
    		
    		lstIpu = businessDelegatorView.consultarIpu(planAccionSeleccionado,TIPO_IPU_TIEMPO);
    		planAccionSeleccionado = businessDelegatorView.getPsyPlanAccion(Long.parseLong(planAccionSeleccionadoItem));
			lstIpu = businessDelegatorView.generarIpuLogico(planAccionSeleccionado,TIPO_IPU_TIEMPO,fechaInforme,semanaInforme,lstIpu, logrosSignificativos,logrosNoAlcanzados, causasDesviacion,accionesPropuestas, false);
			createLineModels();			
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
    	

		try {
			
			if(planAccionSeleccionadoItem==null || planAccionSeleccionadoItem.equals("-1")){
				throw new Exception("Debe seleccionar un plan de acción");
			}
			
			planAccionSeleccionado = businessDelegatorView.getPsyPlanAccion(Long.parseLong(planAccionSeleccionadoItem));
	
			//Se calcula la semana en que queda la fecha del informe
			semanaInforme = Utilities.calcularDiferenciaFechas(planAccionSeleccionado.getFechaInicio(), fechaInforme);
						
			if(semanaInforme>lstIpu.size()){
				semanaInforme = 1L;
				fechaInforme = new Date();
				throw new Exception("No puede seleccionar una fecha de informe superior a la semana: "+lstIpu.size());
			}
						
			//se consulta la lista del IPU para el plan de acción seleccionado
			//lstIpu = businessDelegatorView.consultarIpu(planAccionSeleccionado,TIPO_IPU_TIEMPO);
					
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			FacesUtils.addErrorMessage(e1.getMessage());
		}    	
    	
    }

    
 public void listener_plan_accion() {
    	

		try {		
			
			
			if(planAccionSeleccionadoItem!=null || !planAccionSeleccionadoItem.equals("-1")){
				
				planAccionSeleccionado = businessDelegatorView.getPsyPlanAccion(Long.parseLong(planAccionSeleccionadoItem));
				
				fechaInforme = planAccionSeleccionado.getFechaInicio();
				
				//Se calcula la semana en que queda la fecha del informe
				semanaInforme = Utilities.calcularDiferenciaFechas(planAccionSeleccionado.getFechaInicio(), fechaInforme);
				
				//se consulta la lista del IPU para el plan de acción seleccionado
				lstIpu = businessDelegatorView.consultarIpu(planAccionSeleccionado,TIPO_IPU_TIEMPO);	
		
				createLineModels();			
			
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			FacesUtils.addErrorMessage(e1.getMessage());
		}
    	
    	
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

	public PsyPlanAccion getPlanAccion() {
		return planAccion;
	}

	public void setPlanAccion(PsyPlanAccion planAccion) {
		this.planAccion = planAccion;
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

	public LineChartModel getLineModel1() {
		return lineModel1;
	}

	


	

    
    
}
