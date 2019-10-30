package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyDetalleObjetivoPlanDTO;
import com.vortexbird.pusay.presentation.businessDelegate.*;
import com.vortexbird.pusay.presentation.utilities.*;

import javax.faces.component.UIOutput;

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
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

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
public class PsyDetalleObjetivoPlanView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int CANTIDAD_OBJETIVOS = 5;
    private static final String NOMBRE_VACIO = "Click para agregar un objetivo corporativo";
    private static final String DESCRIPCION_VACIO = "Click para agregar una descripcion";
    private static final Logger log = LoggerFactory.getLogger(PsyDetalleObjetivoPlanView.class);
    private static final String ESTADO_ABIERTO = "A";
    private static final String ESTADO_CERRADO = "C";
    private static final String ESTADO_INICIADO = "I";
    private InputText txtDescripcion;
    private InputText txtEstadoRegistro;
    private InputText txtNombre;
    private InputText txtObesCodigo_PsyObjetivoEstrategico;
    private InputText txtObplCodigo_PsyObjetivoPlan;
    private InputText txtDobpCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyDetalleObjetivoPlanDTO> data;
    private PsyDetalleObjetivoPlanDTO selectedPsyDetalleObjetivoPlan;
    private PsyDetalleObjetivoPlan entity;
    private boolean showDialog;
    private List<PsyDetalleObjetivoPlan> lstObjetivoCorporativo;
    private PsyDetalleObjetivoPlan objetivoCorporativo;
    private String objetivoEstrategico;
    private List<SelectItem> listaObjetivosEstrategicos = new ArrayList<SelectItem>();
    private  PsyObjetivoPlan objetivoPlan = null;
    private List<PsyDetalleObjetivoPlan> lstObjetivoCorporativoRing = new ArrayList<PsyDetalleObjetivoPlan>();
    private PsyDetalleObjetivoPlan objetivoCorporativoRing;
    private boolean bloqueado;
    private boolean mostrar;
    private boolean mostrarMensaje;
    private  boolean continuar;
    private PsyPlanEstrategico planEstrategico = null;  
     
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    
    public PsyDetalleObjetivoPlanView() {
        super();
    }
    
    @PostConstruct
    public void psyDetalleObjetivoPlanView() {
       
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
        	
        	List<PsyObjetivoEstrategico> lstObjetivoEstrategicos = new ArrayList<PsyObjetivoEstrategico>();
        	//Se consulta todos los objetivos estrategicos de la base de datos en estado registro activo
        	lstObjetivoEstrategicos = businessDelegatorView.getPsyObjetivoEstrategico();
        	
        	//Se adiciona a una lista de SelectItem para colocar los objetivos estrategicos en un SelectOneMenu
        	for(PsyObjetivoEstrategico objetivo: lstObjetivoEstrategicos){
        		listaObjetivosEstrategicos.add(new SelectItem(objetivo.getObesCodigo(), objetivo.getNombre()));        		
        	}
        	
	        //Se consulta el plan estrategico para la empresa seleccionada
        	lstPlanEstrategico = businessDelegatorView.consultarPlanEstrategicoEmpresa(empresa, ESTADO_ABIERTO);
        
        	//Se valida si existe un plan estrategico en estado abierto
        	 if(lstPlanEstrategico!=null && !lstPlanEstrategico.isEmpty()){
         		planEstrategico = lstPlanEstrategico.get(0);
         	}else if(lstPlanEstrategico == null || lstPlanEstrategico.isEmpty()){
        		
        		lstPlanEstrategico = businessDelegatorView.consultarPlanEstrategicoEmpresa(empresa, ESTADO_INICIADO);
        		if(lstPlanEstrategico!=null && !lstPlanEstrategico.isEmpty()){
        			planEstrategico = lstPlanEstrategico.get(0);
        			bloqueado =true;
        		}else if(lstPlanEstrategico == null || lstPlanEstrategico.isEmpty()){
        			mostrar = false;
            		mostrarMensaje = true;
            		bloqueado =true;
            		continuar = false;
            		log.info("No existen planes estrategicos en estado abierto o iniciado para la empresa "+ empresa.getEmprCodigo());
            		
        		}
        		        		
        	}
        	
//        	if(planEstrategico!= null && planEstrategico.equals(ESTADO_CERRADO)){
//        		bloqueado = true;
//        	}
        	 //Se verifiac que todo lo anterior terminara satisfactoriamente
        	if(continuar == true){
        	 
        	//Se consulta si existe objetivos para el plan
        	//Si se encuentra objetivos para el plan estrategico activos, los consulto
			//En caso de no encontrar objetivos para el plan activos, creo un nuevo objetivo plan
        	objetivoPlan = businessDelegatorView.consultarObjetivoPlan(planEstrategico, null);
        	
        	//Se inahilita las modificaciones si el estado del plan es finalizado
        	if(objetivoPlan!= null && objetivoPlan.getEstadoObjetivoPlan().equals(ESTADO_CERRADO)){
        		bloqueado = true;
        	}
        	
        	//Se consulta si existen objetivos corporativos para la empresa a traves del plan objetivo
        	//Si se encuentra objetivos corporativos para el plan objetivo, los consulto
        	lstObjetivosCorporativos = businessDelegatorView.consultarDetalleObjetivoPlan(objetivoPlan);
   
        	
        	//Se crean n (n es dado por la constante CANTIDAD_OBJETIVOS) objetivos corporativos
        	if(lstObjetivosCorporativos!= null && !lstObjetivosCorporativos.isEmpty()){
        		lstObjetivoCorporativo = new ArrayList<PsyDetalleObjetivoPlan>();
        		lstObjetivoCorporativo = lstObjetivosCorporativos; // se colocan los objetivos encontrados
        		
        		int cantidadObjetivosActuales = lstObjetivoCorporativo.size();        		
        		PsyDetalleObjetivoPlan detalle = null;
        		
        		for(int i=cantidadObjetivosActuales; i< CANTIDAD_OBJETIVOS; i++){ // Se adicionan objetivos vacios para los espacios restantes
        			detalle = new PsyDetalleObjetivoPlan();
        			detalle.setDescripcion(DESCRIPCION_VACIO);
        			detalle.setNombre(NOMBRE_VACIO);
        			detalle.setEstadoRegistro("A");
        			detalle.setDobpCodigo(Long.parseLong((i*-1)+""));
        			detalle.setCodigoObjetivoEstrategico("-1");
        			detalle.setPsyObjetivoEstrategico(objetivoEstrategico);
        			detalle.setPsyObjetivoPlan(objetivoPlan);
        			lstObjetivoCorporativo.add(detalle);
        			
         		}
        		
        	}else {// si no se encontró ningún objetivo corporativo se adicionan CANTIDAD_OBJETIVOS vacios
        		
        		lstObjetivoCorporativo = new ArrayList<PsyDetalleObjetivoPlan>();
        		        		
        		int cantidadObjetivosActuales = 0;        		
        		PsyDetalleObjetivoPlan detalle = null;
        		
        		for(int i=cantidadObjetivosActuales; i< CANTIDAD_OBJETIVOS; i++){
        			detalle = new PsyDetalleObjetivoPlan();
        			detalle.setEstadoRegistro("A");
        			detalle.setDescripcion(DESCRIPCION_VACIO);
        			detalle.setNombre(NOMBRE_VACIO);
        			detalle.setDobpCodigo(Long.parseLong((i*-1)+""));
        			detalle.setCodigoObjetivoEstrategico("-1");
        			detalle.setPsyObjetivoPlan(objetivoPlan);
        			lstObjetivoCorporativo.add(detalle);
          		}
        		
        	}
        	
        	mostrarConsulta();
        	//lstObjetivoCorporativoRing = lstObjetivoCorporativo;
        	}
        	
        } catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    
    public void mostrarConsulta(){
    	
    	// Se crea una nueva listo con los datos obtenidos, pero esta lista solo servirá de consulta para el componente Ring de primefaces
    	PsyDetalleObjetivoPlan detalle = new PsyDetalleObjetivoPlan();
    	PsyObjetivoEstrategico objetivoEstrategico = new PsyObjetivoEstrategico();
    	
    	try{
    	lstObjetivoCorporativoRing.clear();
    	for(PsyDetalleObjetivoPlan objetivoAnillo : lstObjetivoCorporativo){
    		detalle = new PsyDetalleObjetivoPlan();
    		detalle.setEstadoRegistro("A");
    		//Si el nombre es vacio se coloca NO DEFINIDO
    		if(objetivoAnillo.getNombre().equals(NOMBRE_VACIO)){
    			detalle.setNombre("NO DEFINIDO");
    		}else{
    			detalle.setNombre(objetivoAnillo.getNombre());
    		}
    		//Si la descripcion es vacio se coloca "el objetivo corporativo no ha sido definido"        		
    		if(objetivoAnillo.getDescripcion().equals(DESCRIPCION_VACIO)){
    			detalle.setDescripcion("El objetivo corporativo no ha sido definido");
    		}else{
    			detalle.setDescripcion(objetivoAnillo.getDescripcion());
    		}
	    			
			detalle.setDobpCodigo(objetivoAnillo.getDobpCodigo());
			detalle.setCodigoObjetivoEstrategico(objetivoAnillo.getCodigoObjetivoEstrategico());
						
			objetivoEstrategico = businessDelegatorView.getPsyObjetivoEstrategico(Long.parseLong(objetivoAnillo.getCodigoObjetivoEstrategico()));
			if(objetivoEstrategico!= null){
			detalle.setNombreObjetivoEstrategico(objetivoEstrategico.getNombre());
			}else{
				detalle.setNombreObjetivoEstrategico("NO DEFINIDO");
			}
			
			detalle.setPsyObjetivoPlan(objetivoAnillo.getPsyObjetivoPlan());
			lstObjetivoCorporativoRing.add(detalle);
    	}
    	}catch (Exception ex) {
        }
    }

    public void seleccionarObjetivo(){
    	objetivoEstrategico = objetivoCorporativo.getPsyObjetivoEstrategico().getObesCodigo().toString();
    	
    }
    
    public void seleccionareliminarDetalleObjetivo(){
    	
    	objetivoCorporativo.setDescripcion("Click para agregar una descripción");
		objetivoCorporativo.setNombre("Click para agregar un objetivo corporativo");
		objetivoCorporativo.setCodigoObjetivoEstrategico("-1");
    }
    
    
    public void rowEventListener(RowEditEvent e) {
        try {
            PsyDetalleObjetivoPlanDTO psyDetalleObjetivoPlanDTO = (PsyDetalleObjetivoPlanDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(psyDetalleObjetivoPlanDTO.getDescripcion());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyDetalleObjetivoPlanDTO.getEstadoRegistro());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(psyDetalleObjetivoPlanDTO.getNombre());

            if (txtObesCodigo_PsyObjetivoEstrategico == null) {
                txtObesCodigo_PsyObjetivoEstrategico = new InputText();
            }

            txtObesCodigo_PsyObjetivoEstrategico.setValue(psyDetalleObjetivoPlanDTO.getObesCodigo_PsyObjetivoEstrategico());

            if (txtObplCodigo_PsyObjetivoPlan == null) {
                txtObplCodigo_PsyObjetivoPlan = new InputText();
            }

            txtObplCodigo_PsyObjetivoPlan.setValue(psyDetalleObjetivoPlanDTO.getObplCodigo_PsyObjetivoPlan());

            if (txtDobpCodigo == null) {
                txtDobpCodigo = new InputText();
            }

            txtDobpCodigo.setValue(psyDetalleObjetivoPlanDTO.getDobpCodigo());

            Long dobpCodigo = FacesUtils.checkLong(txtDobpCodigo);
            entity = businessDelegatorView.getPsyDetalleObjetivoPlan(dobpCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyDetalleObjetivoPlan = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyDetalleObjetivoPlan = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            txtDescripcion.setDisabled(true);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtObesCodigo_PsyObjetivoEstrategico != null) {
            txtObesCodigo_PsyObjetivoEstrategico.setValue(null);
            txtObesCodigo_PsyObjetivoEstrategico.setDisabled(true);
        }

        if (txtObplCodigo_PsyObjetivoPlan != null) {
            txtObplCodigo_PsyObjetivoPlan.setValue(null);
            txtObplCodigo_PsyObjetivoPlan.setDisabled(true);
        }

        if (txtDobpCodigo != null) {
            txtDobpCodigo.setValue(null);
            txtDobpCodigo.setDisabled(false);
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
            Long dobpCodigo = FacesUtils.checkLong(txtDobpCodigo);
            entity = (dobpCodigo != null)
                ? businessDelegatorView.getPsyDetalleObjetivoPlan(dobpCodigo)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setDisabled(false);
            txtObesCodigo_PsyObjetivoEstrategico.setDisabled(false);
            txtObplCodigo_PsyObjetivoPlan.setDisabled(false);
            txtDobpCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtObesCodigo_PsyObjetivoEstrategico.setValue(entity.getPsyObjetivoEstrategico()
                                                                .getObesCodigo());
            txtObesCodigo_PsyObjetivoEstrategico.setDisabled(false);
            txtObplCodigo_PsyObjetivoPlan.setValue(entity.getPsyObjetivoPlan()
                                                         .getObplCodigo());
            txtObplCodigo_PsyObjetivoPlan.setDisabled(false);
            txtDobpCodigo.setValue(entity.getDobpCodigo());
            txtDobpCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyDetalleObjetivoPlan = (PsyDetalleObjetivoPlanDTO) (evt.getComponent()
                                                                         .getAttributes()
                                                                         .get("selectedPsyDetalleObjetivoPlan"));
        txtDescripcion.setValue(selectedPsyDetalleObjetivoPlan.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyDetalleObjetivoPlan.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtNombre.setValue(selectedPsyDetalleObjetivoPlan.getNombre());
        txtNombre.setDisabled(false);
        txtObesCodigo_PsyObjetivoEstrategico.setValue(selectedPsyDetalleObjetivoPlan.getObesCodigo_PsyObjetivoEstrategico());
        txtObesCodigo_PsyObjetivoEstrategico.setDisabled(false);
        txtObplCodigo_PsyObjetivoPlan.setValue(selectedPsyDetalleObjetivoPlan.getObplCodigo_PsyObjetivoPlan());
        txtObplCodigo_PsyObjetivoPlan.setDisabled(false);
        txtDobpCodigo.setValue(selectedPsyDetalleObjetivoPlan.getDobpCodigo());
        txtDobpCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
        	//Variable para identificar cuantos campos son vacios en los objetivos corporativos       
        	Integer cantidadVacios = 0;
        	
        	boolean grupoRepetido = businessDelegatorView.validarGrupoRepetido(lstObjetivoCorporativo);
        	
        	if(grupoRepetido == true){
        		throw new Exception("No puede ingresar varios obejtivos corporativos asociados a un mismo grupo");
        	}
        	
        	for(PsyDetalleObjetivoPlan detalle: lstObjetivoCorporativo){
        		
        		if((detalle.getNombre() == null || 	detalle.getDescripcion() == null) || 
        				(detalle.getNombre().trim().equals("") || detalle.getDescripcion().trim().equals("")) ){
        			throw new Exception("No puede ingresar un objetivo corporativo con nombre o descripción vacio");
        		}else
        		if(detalle.getNombre().equals(NOMBRE_VACIO) &&
        				detalle.getDescripcion().equals(DESCRIPCION_VACIO)){
        			cantidadVacios ++;
        		}else{
        			if(detalle.getCodigoObjetivoEstrategico().equals("-1")){
        				throw new Exception("En el objetivo "+ detalle.getNombre()+" no se definió el grupo");
        			}
        		}
        	}
        	
        	if(cantidadVacios == CANTIDAD_OBJETIVOS){
        		throw new Exception("Debe ingresar al menos un objetivo corporativo");
        	}
 
        	List<PsyDetalleObjetivoPlan> lstGuardada = businessDelegatorView.guardarDetalleObjetivoPlan(lstObjetivoCorporativo, objetivoPlan, NOMBRE_VACIO, DESCRIPCION_VACIO);
        	
        	mostrarConsulta();
       	
        	FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        	
        	
        	
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_guardar_definitivo() {
        try {
        	        	
       
        	objetivoPlan.setFechaFin(new Date());
        	objetivoPlan.setEstadoObjetivoPlan(ESTADO_CERRADO);
        	
        	List<PsyDetalleObjetivoPlan> lstDetalle = businessDelegatorView.consultarDetalleObjetivoPlan(objetivoPlan);       	
        	
        	if(lstDetalle != null && !lstDetalle.isEmpty()){
        		businessDelegatorView.updatePsyObjetivoPlan(objetivoPlan);
        		planEstrategico.setEstadoPlan(ESTADO_INICIADO);
        		businessDelegatorView.updatePsyPlanEstrategico(planEstrategico);
        		FacesUtils.addInfoMessage("Se ha guardado los objetivos corporativos definitivos");
        		bloqueado = true;
        	}else if(lstDetalle == null || lstDetalle.isEmpty()){
        		FacesUtils.addErrorMessage("Debe definir al menos un objetivo corporativo");
        	}
        	
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }
   
    public String action_create() {
        try {
            entity = new PsyDetalleObjetivoPlan();

            Long dobpCodigo = FacesUtils.checkLong(txtDobpCodigo);

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setDobpCodigo(dobpCodigo);
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPsyObjetivoEstrategico((FacesUtils.checkLong(
                    txtObesCodigo_PsyObjetivoEstrategico) != null)
                ? businessDelegatorView.getPsyObjetivoEstrategico(
                    FacesUtils.checkLong(txtObesCodigo_PsyObjetivoEstrategico))
                : null);
            entity.setPsyObjetivoPlan((FacesUtils.checkLong(
                    txtObplCodigo_PsyObjetivoPlan) != null)
                ? businessDelegatorView.getPsyObjetivoPlan(FacesUtils.checkLong(
                        txtObplCodigo_PsyObjetivoPlan)) : null);
            businessDelegatorView.savePsyDetalleObjetivoPlan(entity);
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
                Long dobpCodigo = new Long(selectedPsyDetalleObjetivoPlan.getDobpCodigo());
                entity = businessDelegatorView.getPsyDetalleObjetivoPlan(dobpCodigo);
            }

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPsyObjetivoEstrategico((FacesUtils.checkLong(
                    txtObesCodigo_PsyObjetivoEstrategico) != null)
                ? businessDelegatorView.getPsyObjetivoEstrategico(
                    FacesUtils.checkLong(txtObesCodigo_PsyObjetivoEstrategico))
                : null);
            entity.setPsyObjetivoPlan((FacesUtils.checkLong(
                    txtObplCodigo_PsyObjetivoPlan) != null)
                ? businessDelegatorView.getPsyObjetivoPlan(FacesUtils.checkLong(
                        txtObplCodigo_PsyObjetivoPlan)) : null);
            businessDelegatorView.updatePsyDetalleObjetivoPlan(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyDetalleObjetivoPlan = (PsyDetalleObjetivoPlanDTO) (evt.getComponent()
                                                                             .getAttributes()
                                                                             .get("selectedPsyDetalleObjetivoPlan"));

            Long dobpCodigo = new Long(selectedPsyDetalleObjetivoPlan.getDobpCodigo());
            entity = businessDelegatorView.getPsyDetalleObjetivoPlan(dobpCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long dobpCodigo = FacesUtils.checkLong(txtDobpCodigo);
            entity = businessDelegatorView.getPsyDetalleObjetivoPlan(dobpCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyDetalleObjetivoPlan(entity);
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
            selectedPsyDetalleObjetivoPlan = (PsyDetalleObjetivoPlanDTO) (evt.getComponent()
                                                                             .getAttributes()
                                                                             .get("selectedPsyDetalleObjetivoPlan"));

            Long dobpCodigo = new Long(selectedPsyDetalleObjetivoPlan.getDobpCodigo());
            entity = businessDelegatorView.getPsyDetalleObjetivoPlan(dobpCodigo);
            businessDelegatorView.deletePsyDetalleObjetivoPlan(entity);
            data.remove(selectedPsyDetalleObjetivoPlan);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcion, Long dobpCodigo,
        String estadoRegistro, String nombre,
        Long obesCodigo_PsyObjetivoEstrategico, Long obplCodigo_PsyObjetivoPlan)
        throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updatePsyDetalleObjetivoPlan(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyDetalleObjetivoPlanView").requestRender();
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

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtObesCodigo_PsyObjetivoEstrategico() {
        return txtObesCodigo_PsyObjetivoEstrategico;
    }

    public void setTxtObesCodigo_PsyObjetivoEstrategico(
        InputText txtObesCodigo_PsyObjetivoEstrategico) {
        this.txtObesCodigo_PsyObjetivoEstrategico = txtObesCodigo_PsyObjetivoEstrategico;
    }

    public InputText getTxtObplCodigo_PsyObjetivoPlan() {
        return txtObplCodigo_PsyObjetivoPlan;
    }

    public void setTxtObplCodigo_PsyObjetivoPlan(
        InputText txtObplCodigo_PsyObjetivoPlan) {
        this.txtObplCodigo_PsyObjetivoPlan = txtObplCodigo_PsyObjetivoPlan;
    }

    public InputText getTxtDobpCodigo() {
        return txtDobpCodigo;
    }

    public void setTxtDobpCodigo(InputText txtDobpCodigo) {
        this.txtDobpCodigo = txtDobpCodigo;
    }

    public List<PsyDetalleObjetivoPlanDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyDetalleObjetivoPlan();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(
        List<PsyDetalleObjetivoPlanDTO> psyDetalleObjetivoPlanDTO) {
        this.data = psyDetalleObjetivoPlanDTO;
    }

    public PsyDetalleObjetivoPlanDTO getSelectedPsyDetalleObjetivoPlan() {
        return selectedPsyDetalleObjetivoPlan;
    }

    public void setSelectedPsyDetalleObjetivoPlan(
        PsyDetalleObjetivoPlanDTO psyDetalleObjetivoPlan) {
        this.selectedPsyDetalleObjetivoPlan = psyDetalleObjetivoPlan;
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

	public List<PsyDetalleObjetivoPlan> getLstObjetivoCorporativo() {
		return lstObjetivoCorporativo;
	}

	public void setLstObjetivoCorporativo(
			List<PsyDetalleObjetivoPlan> lstObjetivoCorporativo) {
		this.lstObjetivoCorporativo = lstObjetivoCorporativo;
	}

	public PsyDetalleObjetivoPlan getObjetivoCorporativo() {
		return objetivoCorporativo;
	}

	public void setObjetivoCorporativo(PsyDetalleObjetivoPlan objetivoCorporativo) {
		this.objetivoCorporativo = objetivoCorporativo;
	}

	public String getObjetivoEstrategico() {
		return objetivoEstrategico;
	}

	public void setObjetivoEstrategico(String objetivoEstrategico) {
		this.objetivoEstrategico = objetivoEstrategico;
	}

	public List<SelectItem> getListaObjetivosEstrategicos() {
		return listaObjetivosEstrategicos;
	}

	public void setListaObjetivosEstrategicos(
			List<SelectItem> listaObjetivosEstrategicos) {
		this.listaObjetivosEstrategicos = listaObjetivosEstrategicos;
	}

	public PsyObjetivoPlan getObjetivoPlan() {
		return objetivoPlan;
	}

	public void setObjetivoPlan(PsyObjetivoPlan objetivoPlan) {
		this.objetivoPlan = objetivoPlan;
	}

	public List<PsyDetalleObjetivoPlan> getLstObjetivoCorporativoRing() {
		return lstObjetivoCorporativoRing;
	}

	public void setLstObjetivoCorporativoRing(
			List<PsyDetalleObjetivoPlan> lstObjetivoCorporativoRing) {
		this.lstObjetivoCorporativoRing = lstObjetivoCorporativoRing;
	}

	public PsyDetalleObjetivoPlan getObjetivoCorporativoRing() {
		return objetivoCorporativoRing;
	}

	public void setObjetivoCorporativoRing(
			PsyDetalleObjetivoPlan objetivoCorporativoRing) {
		this.objetivoCorporativoRing = objetivoCorporativoRing;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
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

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

	public PsyPlanEstrategico getPlanEstrategico() {
		return planEstrategico;
	}

	public void setPlanEstrategico(PsyPlanEstrategico planEstrategico) {
		this.planEstrategico = planEstrategico;
	}
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		
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
        					  "Nombre del Documento: Alineación Estrategica \n"+
        					  "Fecha: "+ new Date().toString()+"\n\n"));
				
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error generando el pdf. El error fue : "+e.getMessage());
		}		
        
        
        
}



	
    
}
