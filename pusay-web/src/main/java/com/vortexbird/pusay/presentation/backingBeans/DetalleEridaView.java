package com.vortexbird.pusay.presentation.backingBeans;



import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyDetalleEridaDTO;
import com.vortexbird.pusay.modelo.dto.PsyMatrizEridaDTO;
import com.vortexbird.pusay.modelo.dto.PsyPriorizacionAsuntoAmbientalDTO;
import com.vortexbird.pusay.modelo.dto.PsyPriorizacionImpactosAmbientalesDTO;
import com.vortexbird.pusay.modelo.dto.PsyTablaEridaDTO;
import com.vortexbird.pusay.presentation.businessDelegate.*;
import com.vortexbird.pusay.presentation.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
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

import javax.annotation.PostConstruct;
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
public class DetalleEridaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String ESTADO_INICIADO = "I";
    private static final Logger log = LoggerFactory.getLogger(DetalleEridaView.class);
    private InputText txtCalificacion;
    private InputText txtEstadoRegistro;
    private InputText txtPeso;
    private InputText txtValoracion;
    private InputText txtAsamCodigo_PsyAsuntoAmbiental;
    private InputText txtImamCodigo_PsyImpactoAmbiental;
    private InputText txtMaerCodigo_PsyMatrizErida;
    private InputText txtDeerCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PsyTablaEridaDTO> dataPsyTablaEridaDTO;
    private List<PsyDetalleEridaDTO> data;
    private PsyDetalleEridaDTO selectedPsyDetalleErida;
    private PsyDetalleErida entity;
    private boolean showDialog;
    private List<SelectItem> dataOneMenuAsuntoAmbiental;
    private SelectOneMenu selectSelectOneMenuAsuntoAmbiental;
    private double txtCalificacionTotal;
    private double txtPesoTotal;
    private double txtValoracionTotal;
    List<PsyPriorizacionAsuntoAmbientalDTO> dataPriorizacionAsuntoAmbiental;
    List<PsyPriorizacionImpactosAmbientalesDTO> dataPriorizacionImpactosAmbientales;
    private List<PsyMatrizEridaDTO> lstMaemaer;
    private List<PsyAsuntoAmbiental> lstAsuntoAmbientalFaltantes;
    private PsyMatrizEridaDTO  selectedMatrizErida;
    private boolean showDialogMatrizErida;
    private boolean showDialogMensajelstAsuntoAmbientalFaltantes;
    private CommandButton btnGuardar;
    private CommandButton btnTermianar;
    private PsyPlanEstrategico planEstrategico = null; 
    private boolean soloLectura;
	private boolean bloqueado;
	private boolean mostrar;
	private boolean mostrarMensaje;
	private boolean continuar;
	private String mensaje;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public DetalleEridaView() {
        super();
    }
    
    @PostConstruct
    public void consultarErida(){
    	
    	//Datos temporales para desarrollo
        PsyEmpresa empresa = new PsyEmpresa();
        empresa = (PsyEmpresa)FacesUtils.getfromSession("empresa");
        
        List<PsyPlanEstrategico> lstPlanEstrategico = null;
        bloqueado = false;
        continuar = true;
    	try {
    		
    		//Se consulta el plan estrategico para la empresa seleccionada
        	lstPlanEstrategico = businessDelegatorView.consultarPlanEstrategicoEmpresa(empresa, ESTADO_INICIADO);
    		        	
        	if(lstPlanEstrategico!=null && !lstPlanEstrategico.isEmpty()){
         		planEstrategico = lstPlanEstrategico.get(0);     
         		mostrarMensaje = false;
        		mostrar = true;
        	}else{
        		
        		mostrarMensaje = true;
        		mostrar = false;
        		continuar = false;
        	}
        	
        	if(continuar == true){
    		lstMaemaer=businessDelegatorView.consultarExisteEridaPlanEstrategico(planEstrategico.getPestCodigo());
    		lstMaemaer=businessDelegatorView.consultarExisteEridaPlanEstrategico(planEstrategico.getPestCodigo());
    		
    		if(lstMaemaer!=null && !lstMaemaer.isEmpty()){
    			selectedMatrizErida = lstMaemaer.get(0);
    			if(selectedMatrizErida.getEstadoErida().equals("A")){
    				bloqueado = false;
    	    		//selectSelectOneMenuAsuntoAmbiental.setDisabled(false);
    	    		//btnGuardar.setDisabled(false);
    	    		//btnTermianar.setDisabled(false);
    	    	}else{
    	    		bloqueado = true;
    	    		//selectSelectOneMenuAsuntoAmbiental.setDisabled(false);
    	    	}
    	    	dataPriorizacionAsuntoAmbiental=null;
    	        dataPriorizacionImpactosAmbientales=null;    		}
        	}
		} catch (Exception e) {
			 FacesUtils.addErrorMessage(e.getMessage());
		}
    }
    
    public void selectedMatrizErida(){
    	if(selectedMatrizErida.getEstadoErida().equals("A")){
    		selectSelectOneMenuAsuntoAmbiental.setDisabled(false);
    		btnGuardar.setDisabled(false);
    		btnTermianar.setDisabled(false);
    	}else{
    		selectSelectOneMenuAsuntoAmbiental.setDisabled(false);
    	}
    	dataPriorizacionAsuntoAmbiental=null;
        dataPriorizacionImpactosAmbientales=null;
    }
    
    
    public void terminarErida(){
    	try {
    		lstAsuntoAmbientalFaltantes=businessDelegatorView.terminarErida(selectedMatrizErida.getMaerCodigo());
         if(lstAsuntoAmbientalFaltantes.size()!=0){
        	 setShowDialogMensajelstAsuntoAmbientalFaltantes(true);
         }else{
        	 btnGuardar.setDisabled(true);
     		btnTermianar.setDisabled(true);
     		
     		lstMaemaer=businessDelegatorView.consultarExisteEridaPlanEstrategico(planEstrategico.getPestCodigo());
     		FacesUtils.addInfoMessage("Matriz ERIDA Finalizada");
         }
         
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
    }
    
    public void cerrarDialogo(){
    	
    	showDialogMensajelstAsuntoAmbientalFaltantes=false;
    	setShowDialogMensajelstAsuntoAmbientalFaltantes(false);
    	
    }
    
    
    public void guardarTablaErida(){
    	try {
    	
    		businessDelegatorView.guardarTablaErida(dataPsyTablaEridaDTO,selectedMatrizErida.getMaerCodigo(),FacesUtils.checkLong(selectSelectOneMenuAsuntoAmbiental));
    		FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
    		cargarTablaErida();
    		dataPsyTablaEridaDTO = null;
            dataPriorizacionAsuntoAmbiental=null;
            dataPriorizacionImpactosAmbientales=null;
		} catch (Exception e) {
			
            FacesUtils.addErrorMessage(e.getMessage());
		}
    }
    
    public void cargarTablaErida(){
    	try {
			
				  dataPsyTablaEridaDTO = businessDelegatorView.consultarTablaErida(selectedMatrizErida.getMaerCodigo(),FacesUtils.checkLong(selectSelectOneMenuAsuntoAmbiental));
				  cargarTotalesTablaErida();
		} catch (Exception e) {
			  FacesUtils.addErrorMessage(e.getMessage());
		}
    }
    
    public void cargarTotalesTablaErida(){
           try {
	  List<Double> resutladoTotalesTablaErida = businessDelegatorView.calcularTotalesTablaErida(dataPsyTablaEridaDTO);
      txtPesoTotal =resutladoTotalesTablaErida.get(0);
      txtCalificacionTotal=resutladoTotalesTablaErida.get(1);
      txtValoracionTotal=resutladoTotalesTablaErida.get(2);
         } catch (Exception e) {
        	 FacesUtils.addErrorMessage(e.getMessage());
          }
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PsyDetalleEridaDTO psyDetalleEridaDTO = (PsyDetalleEridaDTO) e.getObject();

            if (txtCalificacion == null) {
                txtCalificacion = new InputText();
            }

            txtCalificacion.setValue(psyDetalleEridaDTO.getCalificacion());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(psyDetalleEridaDTO.getEstadoRegistro());

            if (txtPeso == null) {
                txtPeso = new InputText();
            }

            txtPeso.setValue(psyDetalleEridaDTO.getPeso());

            if (txtValoracion == null) {
                txtValoracion = new InputText();
            }

            txtValoracion.setValue(psyDetalleEridaDTO.getValoracion());

            if (txtAsamCodigo_PsyAsuntoAmbiental == null) {
                txtAsamCodigo_PsyAsuntoAmbiental = new InputText();
            }

            txtAsamCodigo_PsyAsuntoAmbiental.setValue(psyDetalleEridaDTO.getAsamCodigo_PsyAsuntoAmbiental());

            if (txtImamCodigo_PsyImpactoAmbiental == null) {
                txtImamCodigo_PsyImpactoAmbiental = new InputText();
            }

            txtImamCodigo_PsyImpactoAmbiental.setValue(psyDetalleEridaDTO.getImamCodigo_PsyImpactoAmbiental());

            if (txtMaerCodigo_PsyMatrizErida == null) {
                txtMaerCodigo_PsyMatrizErida = new InputText();
            }

            txtMaerCodigo_PsyMatrizErida.setValue(psyDetalleEridaDTO.getMaerCodigo_PsyMatrizErida());

            if (txtDeerCodigo == null) {
                txtDeerCodigo = new InputText();
            }

            txtDeerCodigo.setValue(psyDetalleEridaDTO.getDeerCodigo());

            Long deerCodigo = FacesUtils.checkLong(txtDeerCodigo);
            entity = businessDelegatorView.getPsyDetalleErida(deerCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPsyDetalleErida = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPsyDetalleErida = null;

        if (txtCalificacion != null) {
            txtCalificacion.setValue(null);
            txtCalificacion.setDisabled(true);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtPeso != null) {
            txtPeso.setValue(null);
            txtPeso.setDisabled(true);
        }

        if (txtValoracion != null) {
            txtValoracion.setValue(null);
            txtValoracion.setDisabled(true);
        }

        if (txtAsamCodigo_PsyAsuntoAmbiental != null) {
            txtAsamCodigo_PsyAsuntoAmbiental.setValue(null);
            txtAsamCodigo_PsyAsuntoAmbiental.setDisabled(true);
        }

        if (txtImamCodigo_PsyImpactoAmbiental != null) {
            txtImamCodigo_PsyImpactoAmbiental.setValue(null);
            txtImamCodigo_PsyImpactoAmbiental.setDisabled(true);
        }

        if (txtMaerCodigo_PsyMatrizErida != null) {
            txtMaerCodigo_PsyMatrizErida.setValue(null);
            txtMaerCodigo_PsyMatrizErida.setDisabled(true);
        }

        if (txtDeerCodigo != null) {
            txtDeerCodigo.setValue(null);
            txtDeerCodigo.setDisabled(false);
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
            Long deerCodigo = FacesUtils.checkLong(txtDeerCodigo);
            entity = (deerCodigo != null)
                ? businessDelegatorView.getPsyDetalleErida(deerCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCalificacion.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtPeso.setDisabled(false);
            txtValoracion.setDisabled(false);
            txtAsamCodigo_PsyAsuntoAmbiental.setDisabled(false);
            txtImamCodigo_PsyImpactoAmbiental.setDisabled(false);
            txtMaerCodigo_PsyMatrizErida.setDisabled(false);
            txtDeerCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCalificacion.setValue(entity.getCalificacion());
            txtCalificacion.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtPeso.setValue(entity.getPeso());
            txtPeso.setDisabled(false);
            txtValoracion.setValue(entity.getValoracion());
            txtValoracion.setDisabled(false);
            txtAsamCodigo_PsyAsuntoAmbiental.setValue(entity.getPsyAsuntoAmbiental()
                                                            .getAsamCodigo());
            txtAsamCodigo_PsyAsuntoAmbiental.setDisabled(false);
            txtImamCodigo_PsyImpactoAmbiental.setValue(entity.getPsyImpactoAmbiental()
                                                             .getImamCodigo());
            txtImamCodigo_PsyImpactoAmbiental.setDisabled(false);
            txtMaerCodigo_PsyMatrizErida.setValue(entity.getPsyMatrizErida()
                                                        .getMaerCodigo());
            txtMaerCodigo_PsyMatrizErida.setDisabled(false);
            txtDeerCodigo.setValue(entity.getDeerCodigo());
            txtDeerCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPsyDetalleErida = (PsyDetalleEridaDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedPsyDetalleErida"));
        txtCalificacion.setValue(selectedPsyDetalleErida.getCalificacion());
        txtCalificacion.setDisabled(false);
        txtEstadoRegistro.setValue(selectedPsyDetalleErida.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtPeso.setValue(selectedPsyDetalleErida.getPeso());
        txtPeso.setDisabled(false);
        txtValoracion.setValue(selectedPsyDetalleErida.getValoracion());
        txtValoracion.setDisabled(false);
        txtAsamCodigo_PsyAsuntoAmbiental.setValue(selectedPsyDetalleErida.getAsamCodigo_PsyAsuntoAmbiental());
        txtAsamCodigo_PsyAsuntoAmbiental.setDisabled(false);
        txtImamCodigo_PsyImpactoAmbiental.setValue(selectedPsyDetalleErida.getImamCodigo_PsyImpactoAmbiental());
        txtImamCodigo_PsyImpactoAmbiental.setDisabled(false);
        txtMaerCodigo_PsyMatrizErida.setValue(selectedPsyDetalleErida.getMaerCodigo_PsyMatrizErida());
        txtMaerCodigo_PsyMatrizErida.setDisabled(false);
        txtDeerCodigo.setValue(selectedPsyDetalleErida.getDeerCodigo());
        txtDeerCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyDetalleErida == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
            dataPriorizacionAsuntoAmbiental=null;
            dataPriorizacionImpactosAmbientales=null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new PsyDetalleErida();

            Long deerCodigo = FacesUtils.checkLong(txtDeerCodigo);

            entity.setCalificacion(FacesUtils.checkLong(txtCalificacion));
            entity.setDeerCodigo(deerCodigo);
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setPeso(FacesUtils.checkDouble(txtPeso));
            entity.setValoracion(FacesUtils.checkDouble(txtValoracion));
            entity.setPsyAsuntoAmbiental((FacesUtils.checkLong(
                    txtAsamCodigo_PsyAsuntoAmbiental) != null)
                ? businessDelegatorView.getPsyAsuntoAmbiental(
                    FacesUtils.checkLong(txtAsamCodigo_PsyAsuntoAmbiental)) : null);
            entity.setPsyImpactoAmbiental((FacesUtils.checkLong(
                    txtImamCodigo_PsyImpactoAmbiental) != null)
                ? businessDelegatorView.getPsyImpactoAmbiental(
                    FacesUtils.checkLong(txtImamCodigo_PsyImpactoAmbiental))
                : null);
            entity.setPsyMatrizErida((FacesUtils.checkLong(
                    txtMaerCodigo_PsyMatrizErida) != null)
                ? businessDelegatorView.getPsyMatrizErida(FacesUtils.checkLong(
                        txtMaerCodigo_PsyMatrizErida)) : null);
            businessDelegatorView.savePsyDetalleErida(entity);
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
                Long deerCodigo = new Long(selectedPsyDetalleErida.getDeerCodigo());
                entity = businessDelegatorView.getPsyDetalleErida(deerCodigo);
            }

            entity.setCalificacion(FacesUtils.checkLong(txtCalificacion));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setPeso(FacesUtils.checkDouble(txtPeso));
            entity.setValoracion(FacesUtils.checkDouble(txtValoracion));
            entity.setPsyAsuntoAmbiental((FacesUtils.checkLong(
                    txtAsamCodigo_PsyAsuntoAmbiental) != null)
                ? businessDelegatorView.getPsyAsuntoAmbiental(
                    FacesUtils.checkLong(txtAsamCodigo_PsyAsuntoAmbiental)) : null);
            entity.setPsyImpactoAmbiental((FacesUtils.checkLong(
                    txtImamCodigo_PsyImpactoAmbiental) != null)
                ? businessDelegatorView.getPsyImpactoAmbiental(
                    FacesUtils.checkLong(txtImamCodigo_PsyImpactoAmbiental))
                : null);
            entity.setPsyMatrizErida((FacesUtils.checkLong(
                    txtMaerCodigo_PsyMatrizErida) != null)
                ? businessDelegatorView.getPsyMatrizErida(FacesUtils.checkLong(
                        txtMaerCodigo_PsyMatrizErida)) : null);
            businessDelegatorView.updatePsyDetalleErida(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyDetalleErida = (PsyDetalleEridaDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedPsyDetalleErida"));

            Long deerCodigo = new Long(selectedPsyDetalleErida.getDeerCodigo());
            entity = businessDelegatorView.getPsyDetalleErida(deerCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long deerCodigo = FacesUtils.checkLong(txtDeerCodigo);
            entity = businessDelegatorView.getPsyDetalleErida(deerCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyDetalleErida(entity);
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
            selectedPsyDetalleErida = (PsyDetalleEridaDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedPsyDetalleErida"));

            Long deerCodigo = new Long(selectedPsyDetalleErida.getDeerCodigo());
            entity = businessDelegatorView.getPsyDetalleErida(deerCodigo);
            businessDelegatorView.deletePsyDetalleErida(entity);
            data.remove(selectedPsyDetalleErida);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long calificacion, Long deerCodigo,
        String estadoRegistro, Double peso, Double valoracion,
        Long asamCodigo_PsyAsuntoAmbiental,
        Long imamCodigo_PsyImpactoAmbiental, Long maerCodigo_PsyMatrizErida)
        throws Exception {
        try {
            entity.setCalificacion(FacesUtils.checkLong(calificacion));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setPeso(FacesUtils.checkDouble(peso));
            entity.setValoracion(FacesUtils.checkDouble(valoracion));
            businessDelegatorView.updatePsyDetalleErida(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PsyDetalleEridaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCalificacion() {
        return txtCalificacion;
    }

    public void setTxtCalificacion(InputText txtCalificacion) {
        this.txtCalificacion = txtCalificacion;
    }

    public InputText getTxtEstadoRegistro() {
        return txtEstadoRegistro;
    }

    public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
        this.txtEstadoRegistro = txtEstadoRegistro;
    }

    public InputText getTxtPeso() {
        return txtPeso;
    }

    public void setTxtPeso(InputText txtPeso) {
        this.txtPeso = txtPeso;
    }

    public InputText getTxtValoracion() {
        return txtValoracion;
    }

    public void setTxtValoracion(InputText txtValoracion) {
        this.txtValoracion = txtValoracion;
    }

    public InputText getTxtAsamCodigo_PsyAsuntoAmbiental() {
        return txtAsamCodigo_PsyAsuntoAmbiental;
    }

    public void setTxtAsamCodigo_PsyAsuntoAmbiental(
        InputText txtAsamCodigo_PsyAsuntoAmbiental) {
        this.txtAsamCodigo_PsyAsuntoAmbiental = txtAsamCodigo_PsyAsuntoAmbiental;
    }

    public InputText getTxtImamCodigo_PsyImpactoAmbiental() {
        return txtImamCodigo_PsyImpactoAmbiental;
    }

    public void setTxtImamCodigo_PsyImpactoAmbiental(
        InputText txtImamCodigo_PsyImpactoAmbiental) {
        this.txtImamCodigo_PsyImpactoAmbiental = txtImamCodigo_PsyImpactoAmbiental;
    }

    public InputText getTxtMaerCodigo_PsyMatrizErida() {
        return txtMaerCodigo_PsyMatrizErida;
    }

    public void setTxtMaerCodigo_PsyMatrizErida(
        InputText txtMaerCodigo_PsyMatrizErida) {
        this.txtMaerCodigo_PsyMatrizErida = txtMaerCodigo_PsyMatrizErida;
    }

    public InputText getTxtDeerCodigo() {
        return txtDeerCodigo;
    }

    public void setTxtDeerCodigo(InputText txtDeerCodigo) {
        this.txtDeerCodigo = txtDeerCodigo;
    }

    public List<PsyDetalleEridaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyDetalleErida();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyDetalleEridaDTO> psyDetalleEridaDTO) {
        this.data = psyDetalleEridaDTO;
    }

    public PsyDetalleEridaDTO getSelectedPsyDetalleErida() {
        return selectedPsyDetalleErida;
    }

    public void setSelectedPsyDetalleErida(PsyDetalleEridaDTO psyDetalleErida) {
        this.selectedPsyDetalleErida = psyDetalleErida;
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
public List<PsyTablaEridaDTO> getDataPsyTablaEridaDTO() {
		
		return dataPsyTablaEridaDTO;
	}

	public void setDataPsyTablaEridaDTO(List<PsyTablaEridaDTO> dataPsyTablaEridaDTO) {
		this.dataPsyTablaEridaDTO = dataPsyTablaEridaDTO;
	}

	public List<SelectItem> getDataOneMenuAsuntoAmbiental() {
		try {
			  if (dataOneMenuAsuntoAmbiental == null) {
				 
				  dataOneMenuAsuntoAmbiental = new ArrayList<SelectItem>();
				 
				  for (PsyAsuntoAmbiental asuntoAmbiental :businessDelegatorView.getPsyAsuntoAmbiental()) {
					  dataOneMenuAsuntoAmbiental.add(new SelectItem(asuntoAmbiental.getAsamCodigo(),asuntoAmbiental.getNombre()));
			        }
	            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataOneMenuAsuntoAmbiental;
	}

	public void setDataOneMenuAsuntoAmbiental(
			List<SelectItem> dataOneMenuAsuntoAmbiental) {
		this.dataOneMenuAsuntoAmbiental = dataOneMenuAsuntoAmbiental;
	}

	public SelectOneMenu getSelectSelectOneMenuAsuntoAmbiental() {
		return selectSelectOneMenuAsuntoAmbiental;
	}

	public void setSelectSelectOneMenuAsuntoAmbiental(
			SelectOneMenu selectSelectOneMenuAsuntoAmbiental) {
		this.selectSelectOneMenuAsuntoAmbiental = selectSelectOneMenuAsuntoAmbiental;
	}

	public double getTxtCalificacionTotal() {
		return txtCalificacionTotal;
	}

	public void setTxtCalificacionTotal(double txtCalificacionTotal) {
		this.txtCalificacionTotal = txtCalificacionTotal;
	}

	public double getTxtPesoTotal() {
		return txtPesoTotal;
	}

	public void setTxtPesoTotal(double txtPesoTotal) {
		this.txtPesoTotal = txtPesoTotal;
	}

	public double getTxtValoracionTotal() {
		return txtValoracionTotal;
	}

	public void setTxtValoracionTotal(double txtValoracionTotal) {
		this.txtValoracionTotal = txtValoracionTotal;
	}

	public List<PsyPriorizacionAsuntoAmbientalDTO> getDataPriorizacionAsuntoAmbiental() {
		try {
			if(dataPriorizacionAsuntoAmbiental==null){
				if(selectedMatrizErida!=null){
					dataPriorizacionAsuntoAmbiental = businessDelegatorView.PriorizacionAsuntoAmbiental(selectedMatrizErida.getMaerCodigo());
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataPriorizacionAsuntoAmbiental;
	}

	public void setDataPriorizacionAsuntoAmbiental(
			List<PsyPriorizacionAsuntoAmbientalDTO> dataPriorizacionAsuntoAmbiental) {
		this.dataPriorizacionAsuntoAmbiental = dataPriorizacionAsuntoAmbiental;
	}

	public List<PsyPriorizacionImpactosAmbientalesDTO> getDataPriorizacionImpactosAmbientales() {
		try {
			if(dataPriorizacionImpactosAmbientales==null){
				if(selectedMatrizErida!=null){
				dataPriorizacionImpactosAmbientales = businessDelegatorView.PriorizacionImpactosAmbientales(selectedMatrizErida.getMaerCodigo());
			
				}}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataPriorizacionImpactosAmbientales;
	}

	public void setDataPriorizacionImpactosAmbientales(
			List<PsyPriorizacionImpactosAmbientalesDTO> dataPriorizacionImpactosAmbientales) {
		this.dataPriorizacionImpactosAmbientales = dataPriorizacionImpactosAmbientales;
	}

	
    

	

	public List<PsyMatrizEridaDTO> getLstMaemaer() {
		return lstMaemaer;
	}

	public void setLstMaemaer(List<PsyMatrizEridaDTO> lstMaemaer) {
		this.lstMaemaer = lstMaemaer;
	}

	public PsyMatrizEridaDTO getSelectedMatrizErida() {
		return selectedMatrizErida;
	}

	public void setSelectedMatrizErida(PsyMatrizEridaDTO selectedMatrizErida) {
		this.selectedMatrizErida = selectedMatrizErida;
	}

	public boolean isShowDialogMatrizErida() {
		return showDialogMatrizErida;
	}

	public void setShowDialogMatrizErida(boolean showDialogMatrizErida) {
		this.showDialogMatrizErida = showDialogMatrizErida;
	}

	public CommandButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(CommandButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public CommandButton getBtnTermianar() {
		return btnTermianar;
	}

	public void setBtnTermianar(CommandButton btnTermianar) {
		this.btnTermianar = btnTermianar;
	}

	public List<PsyAsuntoAmbiental> getLstAsuntoAmbientalFaltantes() {
		return lstAsuntoAmbientalFaltantes;
	}

	public void setLstAsuntoAmbientalFaltantes(
			List<PsyAsuntoAmbiental> lstAsuntoAmbientalFaltantes) {
		this.lstAsuntoAmbientalFaltantes = lstAsuntoAmbientalFaltantes;
	}

	public boolean isShowDialogMensajelstAsuntoAmbientalFaltantes() {
		return showDialogMensajelstAsuntoAmbientalFaltantes;
	}

	public void setShowDialogMensajelstAsuntoAmbientalFaltantes(
			boolean showDialogMensajelstAsuntoAmbientalFaltantes) {
		this.showDialogMensajelstAsuntoAmbientalFaltantes = showDialogMensajelstAsuntoAmbientalFaltantes;
	}

	public PsyPlanEstrategico getPlanEstrategico() {
		return planEstrategico;
	}

	public void setPlanEstrategico(PsyPlanEstrategico planEstrategico) {
		this.planEstrategico = planEstrategico;
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

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	
	
	
}
