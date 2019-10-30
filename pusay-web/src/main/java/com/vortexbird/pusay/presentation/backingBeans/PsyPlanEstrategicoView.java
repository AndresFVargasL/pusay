package com.vortexbird.pusay.presentation.backingBeans;

import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.model.CueListaCuestionario;
import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyPlanEstrategicoDTO;
import com.vortexbird.pusay.presentation.businessDelegate.*;
import com.vortexbird.pusay.presentation.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PsyPlanEstrategicoView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String ESTADO_PLAN_ABIERTO = "A";
	private static final String ESTADO_PLAN_INICIADO = "I";
	private static final String ESTADO_PLAN_CERRADO = "C";
	private static final String ESTADO_REGISTRO_ACTIVO = "A";
	private static Long emprCodigo = 0L;
	private static final Logger log = LoggerFactory
			.getLogger(PsyPlanEstrategicoView.class);
	private InputTextarea txtDescripcion;
	private InputText txtEstadoPlan;
	private InputText txtEstadoRegistro;
	private InputText txtNombre;
	private InputText txtEmprCodigo;
	private InputText txtPestCodigo;
	private Calendar txtFechaFin;
	private Calendar txtFechaInicio;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<PsyPlanEstrategicoDTO> data;
	private PsyPlanEstrategicoDTO selectedPsyPlanEstrategico;
	private PsyPlanEstrategico entity;
	private boolean showDialog;
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;
	private PsyEmpresa empresaAux;

	public PsyPlanEstrategicoView() {
		super();
	}
	
	public PsyEmpresa getEmpresaIntoSession(){
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);
		return (PsyEmpresa) httpSession.getAttribute("empresa");
		
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			PsyPlanEstrategicoDTO psyPlanEstrategicoDTO = (PsyPlanEstrategicoDTO) e
					.getObject();

			if (txtDescripcion == null) {
				txtDescripcion = new InputTextarea();
			}

			txtDescripcion.setValue(psyPlanEstrategicoDTO.getDescripcion());

			if (txtEstadoPlan == null) {
				txtEstadoPlan = new InputText();
			}

			txtEstadoPlan.setValue(psyPlanEstrategicoDTO.getEstadoPlan());

			if (txtEstadoRegistro == null) {
				txtEstadoRegistro = new InputText();
			}

			txtEstadoRegistro.setValue(psyPlanEstrategicoDTO
					.getEstadoRegistro());

			if (txtNombre == null) {
				txtNombre = new InputText();
			}

			txtNombre.setValue(psyPlanEstrategicoDTO.getNombre());

			if (txtEmprCodigo == null) {
				txtEmprCodigo = new InputText();
			}

			txtEmprCodigo.setValue(psyPlanEstrategicoDTO
					.getEmprCodigo_PsyEmpresa());

			if (txtPestCodigo == null) {
				txtPestCodigo = new InputText();
			}

			txtPestCodigo.setValue(psyPlanEstrategicoDTO.getPestCodigo());

			if (txtFechaFin == null) {
				txtFechaFin = new Calendar();
			}

			txtFechaFin.setValue(psyPlanEstrategicoDTO.getFechaFin());

			if (txtFechaInicio == null) {
				txtFechaInicio = new Calendar();
			}

			txtFechaInicio.setValue(psyPlanEstrategicoDTO.getFechaInicio());

			Long pestCodigo = FacesUtils.checkLong(txtPestCodigo);
			entity = businessDelegatorView.getPsyPlanEstrategico(pestCodigo);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedPsyPlanEstrategico = null;
		entity = null;
		try {
			PsyEmpresa empresa = getEmpresaIntoSession();
			txtEmprCodigo.setValue(empresa.getNombre());
			txtEstadoPlan.setValue("Abierto");
		} catch (Exception e) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							"",
							new FacesMessage(
									"Los datos de la empresa no se han cargado correctamente"));
		}
		try {
			Object[] variables = {"psyEmpresa.emprCodigo",false,getEmpresaIntoSession().getEmprCodigo(),"=","estadoRegistro",true,"A","="};
			
			List<PsyPlanEstrategico> verificacionPlanes = businessDelegatorView.findByCriteriaInPsyPlanEstrategico(variables, null, null);
			
			for (PsyPlanEstrategico planTmp : verificacionPlanes) {
				if(planTmp.getEstadoPlan().trim().equals("A")){
					throw new ZMessManager(
							"Ya existe un plan estrategico abierto, cierrelo para poder crear uno nuevo.");
				}
				if(planTmp.getEstadoPlan().trim().equals("I")){
					throw new ZMessManager(
							"Ya existe un plan estrategico iniciado, cierrelo para poder crear uno nuevo.");
				}
			}
			setShowDialog(true);
		} catch (Exception e) {
			
			FacesUtils.addErrorMessage(e.getMessage());
			
		}
		

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedPsyPlanEstrategico = null;

		if (txtDescripcion != null) {
			txtDescripcion.setValue(null);

		}
		
		if (txtEstadoRegistro != null) {
			txtEstadoRegistro.setValue(null);
			txtEstadoRegistro.setDisabled(true);
		}

		if (txtNombre != null) {
			txtNombre.setValue(null);

		}

		if (txtFechaFin != null) {
			txtFechaFin.setValue(null);

		}

		if (txtFechaInicio != null) {
			txtFechaInicio.setValue(null);

		}

		if (txtPestCodigo != null) {
			txtPestCodigo.setValue(null);

		}

		// if (btnSave != null) {
		// btnSave.setDisabled(true);
		// }
		//
		// if (btnDelete != null) {
		// btnDelete.setDisabled(true);
		// }

		return "";
	}

	public void listener_txtFechaFin() {
		Date inputDate = (Date) txtFechaFin.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage(
				"",
				new FacesMessage("Fecha seleccionada "
						+ dateFormat.format(inputDate)));
	}
	

	public void listener_txtFechaInicio() {
		Date inputDate = (Date) txtFechaInicio.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage(
				"",
				new FacesMessage("Fecha seleccionada "
						+ dateFormat.format(inputDate)));
	}

	public void listener_txtId() {
		try {
			Long pestCodigo = FacesUtils.checkLong(txtPestCodigo);
			entity = (pestCodigo != null) ? businessDelegatorView
					.getPsyPlanEstrategico(pestCodigo) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtDescripcion.setDisabled(false);
			txtEstadoPlan.setDisabled(false);
			txtEstadoRegistro.setDisabled(false);
			txtNombre.setDisabled(false);
			txtEmprCodigo.setDisabled(false);
			txtFechaFin.setDisabled(false);
			txtFechaInicio.setDisabled(false);
			txtPestCodigo.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtDescripcion.setValue(entity.getDescripcion());
			txtDescripcion.setDisabled(false);
			txtEstadoPlan.setValue(entity.getEstadoPlan());
			txtEstadoPlan.setDisabled(false);
			txtEstadoRegistro.setValue(entity.getEstadoRegistro());
			txtEstadoRegistro.setDisabled(false);
			txtFechaFin.setValue(entity.getFechaFin());
			txtFechaFin.setDisabled(false);
			txtFechaInicio.setValue(entity.getFechaInicio());
			txtFechaInicio.setDisabled(false);
			txtNombre.setValue(entity.getNombre());
			txtNombre.setDisabled(false);
			txtEmprCodigo.setValue(entity.getPsyEmpresa().getEmprCodigo());
			txtEmprCodigo.setDisabled(false);
			txtPestCodigo.setValue(entity.getPestCodigo());
			txtPestCodigo.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedPsyPlanEstrategico = (PsyPlanEstrategicoDTO) (evt
				.getComponent().getAttributes()
				.get("selectedPsyPlanEstrategico"));
		try {

			if (selectedPsyPlanEstrategico.getEstadoPlan().trim().equals("Abierto")) {

				txtDescripcion.setValue(selectedPsyPlanEstrategico
						.getDescripcion());
				txtDescripcion.setDisabled(false);
				txtEstadoPlan.setValue(selectedPsyPlanEstrategico.getEstadoPlan());
				txtFechaFin.setValue(selectedPsyPlanEstrategico.getFechaFin());
				txtFechaFin.setDisabled(false);
				txtFechaInicio.setValue(selectedPsyPlanEstrategico
						.getFechaInicio());
				txtFechaInicio.setDisabled(false);
				txtNombre.setValue(selectedPsyPlanEstrategico.getNombre());
				txtNombre.setDisabled(false);
				txtEmprCodigo.setValue(businessDelegatorView.getPsyEmpresa(
						selectedPsyPlanEstrategico.getEmprCodigo_PsyEmpresa())
						.getNombre());
				txtEmprCodigo.setDisabled(true);
				btnSave.setDisabled(false);
				setShowDialog(true);
			}else{
				FacesUtils
				.addInfoMessage("Para editar el plan, debe estar en estado ABIERTO.");
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String action_save() {
		try {
			if ((selectedPsyPlanEstrategico == null) && (entity == null)) {
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
			entity = new PsyPlanEstrategico();

			entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
			entity.setEstadoPlan(ESTADO_PLAN_ABIERTO);
			entity.setEstadoRegistro(ESTADO_REGISTRO_ACTIVO);
			entity.setFechaFin(FacesUtils.checkDate(txtFechaFin));
			entity.setFechaInicio(FacesUtils.checkDate(txtFechaInicio));
			entity.setNombre(FacesUtils.checkString(txtNombre));
			entity.setPsyEmpresa(getEmpresaIntoSession());
			businessDelegatorView.savePsyPlanEstrategico(entity, getEmpresaIntoSession());
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
	
//	public String actionAsociarEncuestas(ActionEvent evt) {
//		try {
//			
//		selectedPsyPlanEstrategico = (PsyPlanEstrategicoDTO) (evt
//				.getComponent().getAttributes()
//				.get("selectedPsyPlanEstrategico"));
//		if(selectedPsyPlanEstrategico.getEstadoPlan().trim().equals("Iniciado") || selectedPsyPlanEstrategico.getEstadoPlan().trim().equals("Presupuestado")){
//		Long pestCodigo = new Long(selectedPsyPlanEstrategico.getPestCodigo());
//		
//		Object[] variables = {"psyPlanEstrategico.pestCodigo",false,pestCodigo,"=","estadoRegistro",true,"A","="};
//		List<PsyMatrizEncuesta> validar = businessDelegatorView.findByCriteriaInPsyMatrizEncuesta(variables, null, null);
//		
//		if(validar==null || validar.size()==0){
//		PsyPlanEstrategico psyPlanEstrategico = new PsyPlanEstrategico();
//		psyPlanEstrategico = businessDelegatorView.getPsyPlanEstrategico(pestCodigo);
//		
//		for (int i = 1; i <= 3; i++) {
//			PsyMatrizEncuesta matrizEncuesta = new PsyMatrizEncuesta();
//			matrizEncuesta.setPsyPlanEstrategico(psyPlanEstrategico);
//			matrizEncuesta.setEstadoRegistro(ESTADO_REGISTRO_ACTIVO);
//			matrizEncuesta.setCodigoEncuesta(String.valueOf(i));
//			businessDelegatorView.savePsyMatrizEncuesta(matrizEncuesta);
//		}
//		
//		FacesUtils.addInfoMessage("Las encuestas se han asociado satisfactoriamente");
//		}else{
//			FacesUtils.addErrorMessage("Las encuestas para este plan ya se han asociado");
//		}
//		}else{
//			FacesUtils.addErrorMessage("Solo puede asociar encuestas a un plan en estado iniciado");
//		}
//		} catch (Exception e) {
//			FacesUtils.addErrorMessage(e.getMessage());
//		}
//		
//		return "";
//	}
	@PostConstruct
	public void init (){
		
		empresaAux = (PsyEmpresa) FacesUtils.getfromSession("empresa");
		emprCodigo = getEmpresaIntoSession().getEmprCodigo();
		
	}
	
	
	public String actionGenerarUrlCuestionario(int cuestionarioId, int codigoLista, PsyEmpresa empresa) {
		String nombre="";
		String apellido="";
		String url="";
		
		
		try {
			if(empresa==null){
				empresa = businessDelegatorView.getPsyEmpresa(emprCodigo);
				FacesUtils.setManagedBeanInSession("empresa", empresa);
			}			
		PsyPlanEstrategico planEstrategicoIniciado = businessDelegatorView.getPlanEstrategicoActivoByPEA(getEmpresaIntoSession());
		if(planEstrategicoIniciado!=null){
		PsyPersona persona = businessDelegatorView.getPsyPersona(empresa.getPsyPersona().getPersCodigo());
		nombre = (persona.getNombre().split("\\s+")[0]!=null) ? persona.getNombre().split("\\s+")[0] : null;
		apellido = (persona.getNombre().split("\\s+")[1]!=null) ? persona.getNombre().split("\\s+")[1] : null;
		url= "/gui/forms/front/cuestionario/diligenciarCuestionario.xhtml?cuestionarioId="+cuestionarioId+"&codigoLista="+codigoLista
				+ "&identificacion="+persona.getPersCodigo()+"&nombre="+nombre+"&apellido="+apellido+"&correo="+persona.getEmail()+"&pestCodigo="+planEstrategicoIniciado.getPestCodigo();
		emprCodigo = empresa.getEmprCodigo();
		}else{
			throw new Exception("No hay un plan estrategico en estado iniciado. No puede responder las encuestas");
		}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return url;
	}
	
	public void redirectDesempenoAmbiental(){
		
		try {
			List<PsyMatrizEncuesta> validacionCuestionarios = businessDelegatorView.consultaAsociacionDeCuestionarios(getEmpresaIntoSession());
			if(validacionCuestionarios.size()>=3){
			String url = actionGenerarUrlCuestionario(1, 1, empresaAux);
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.redirect(context.getRequestContextPath() + url);
			}else{
				ExternalContext context = FacesContext.getCurrentInstance()
						.getExternalContext();
				context.redirect(context.getRequestContextPath()
						+ "/XHTML/AccesoDenegadoACuestionarios.xhtml");
			}
			
		
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		
		
	}
	
	public void redirectGradoDeAfectacion(){
		
		try {
			
			List<PsyMatrizEncuesta> validacionCuestionarios = businessDelegatorView.consultaAsociacionDeCuestionarios(getEmpresaIntoSession());
			if(validacionCuestionarios.size()>=3){
			String url = actionGenerarUrlCuestionario(2, 2, empresaAux);
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.redirect(context.getRequestContextPath() + url);
			}else{
				ExternalContext context = FacesContext.getCurrentInstance()
						.getExternalContext();
				context.redirect(context.getRequestContextPath()
						+ "/XHTML/AccesoDenegadoACuestionarios.xhtml");
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		
		
	}
	
	public void redirectNivelTecnico(){
		
		try {
			
			List<PsyMatrizEncuesta> validacionCuestionarios = businessDelegatorView.consultaAsociacionDeCuestionarios(getEmpresaIntoSession());
			if(validacionCuestionarios.size()>=3){
			String url = actionGenerarUrlCuestionario(3, 3, empresaAux);
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.redirect(context.getRequestContextPath() + url);
			}else{
				ExternalContext context = FacesContext.getCurrentInstance()
						.getExternalContext();
				context.redirect(context.getRequestContextPath()
						+ "/XHTML/AccesoDenegadoACuestionarios.xhtml");
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		
		
	}
	
	
	
	public String getIP() {
		String ipAddress = "";
		 InetAddress ip;
		  try {
			ip = InetAddress.getLocalHost();
			ipAddress =  ip.getCanonicalHostName();
		  } catch (UnknownHostException e) {
			e.printStackTrace();
		  }
		return ipAddress;
	}

	public String getPort() {
		String ipPort = "";
		Integer puerto = null;
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			ipPort = request.getHeader("X-FORWARDED-FOR");
			if (ipPort == null) {
				puerto = request.getLocalPort();
			}
		} catch (Exception e) {
			throw new FacesException(e);
		}
		return puerto != null ? puerto.toString() : "";
	}

	public String action_modify() {
		try {
			if (entity == null) {
				Long pestCodigo = new Long(
						selectedPsyPlanEstrategico.getPestCodigo());
				entity = businessDelegatorView
						.getPsyPlanEstrategico(pestCodigo);
			}
			if (entity.getEstadoPlan().trim().equals("A") || entity.getEstadoPlan().trim().equals("I")) {
				entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
				entity.setEstadoPlan((FacesUtils.checkString(txtEstadoPlan)!=null) ?
						((FacesUtils.checkString(txtEstadoPlan)).equals("Abierto")) ?
								ESTADO_PLAN_ABIERTO : ((FacesUtils.checkString(txtEstadoPlan)).equals("Iniciado")) ?
										ESTADO_PLAN_INICIADO : ESTADO_PLAN_CERRADO : null);
				entity.setFechaFin(FacesUtils.checkDate(txtFechaFin));
				entity.setFechaInicio(FacesUtils.checkDate(txtFechaInicio));
				entity.setNombre(FacesUtils.checkString(txtNombre));
				entity.setPsyEmpresa(getEmpresaIntoSession());
				businessDelegatorView.updatePsyPlanEstrategico(entity);
				FacesUtils
						.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
				action_clear();
				data = null;
				action_closeDialog();
			} else {
				FacesUtils
						.addInfoMessage("El plan esta en estado: cerrado, ya no puede editar la informacion");
			}

		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedPsyPlanEstrategico = (PsyPlanEstrategicoDTO) (evt
					.getComponent().getAttributes()
					.get("selectedPsyPlanEstrategico"));

			Long pestCodigo = new Long(
					selectedPsyPlanEstrategico.getPestCodigo());
			entity = businessDelegatorView.getPsyPlanEstrategico(pestCodigo);
			action_delete();
			data=null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}
	
	public String cerrarPlan(ActionEvent evt) {
		try {
			selectedPsyPlanEstrategico = (PsyPlanEstrategicoDTO) (evt
					.getComponent().getAttributes()
					.get("selectedPsyPlanEstrategico"));

			Long pestCodigo = new Long(
					selectedPsyPlanEstrategico.getPestCodigo());
			entity = businessDelegatorView.getPsyPlanEstrategico(pestCodigo);
			if(!entity.getEstadoPlan().equals(ESTADO_PLAN_CERRADO)){
				entity.setEstadoPlan(ESTADO_PLAN_CERRADO);
				businessDelegatorView.updatePsyPlanEstrategico(entity);
			}else{
				FacesUtils.addErrorMessage("El plan ya esta cerrado");
			}
			data=null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Long pestCodigo = FacesUtils.checkLong(txtPestCodigo);
			entity = businessDelegatorView.getPsyPlanEstrategico(pestCodigo);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deletePsyPlanEstrategico(entity);
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
			selectedPsyPlanEstrategico = (PsyPlanEstrategicoDTO) (evt
					.getComponent().getAttributes()
					.get("selectedPsyPlanEstrategico"));

			Long pestCodigo = new Long(
					selectedPsyPlanEstrategico.getPestCodigo());
			entity = businessDelegatorView.getPsyPlanEstrategico(pestCodigo);
			businessDelegatorView.deletePsyPlanEstrategico(entity);
			data.remove(selectedPsyPlanEstrategico);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(String descripcion, String estadoPlan,
			String estadoRegistro, Date fechaFin, Date fechaInicio,
			String nombre, Long pestCodigo, Long emprCodigo_PsyEmpresa)
			throws Exception {
		try {
			entity.setDescripcion(FacesUtils.checkString(descripcion));
			entity.setEstadoPlan(FacesUtils.checkString(estadoPlan));
			entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
			entity.setFechaFin(FacesUtils.checkDate(fechaFin));
			entity.setFechaInicio(FacesUtils.checkDate(fechaInicio));
			entity.setNombre(FacesUtils.checkString(nombre));
			businessDelegatorView.updatePsyPlanEstrategico(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("PsyPlanEstrategicoView").requestRender();
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

	public InputText gettxtEmprCodigo() {
		return txtEmprCodigo;
	}

	public void settxtEmprCodigo(InputText txtEmprCodigo) {
		this.txtEmprCodigo = txtEmprCodigo;
	}

	public Calendar getTxtFechaFin() {
		return txtFechaFin;
	}

	public void setTxtFechaFin(Calendar txtFechaFin) {
		this.txtFechaFin = txtFechaFin;
	}

	public Calendar getTxtFechaInicio() {
		return txtFechaInicio;
	}

	public void setTxtFechaInicio(Calendar txtFechaInicio) {
		this.txtFechaInicio = txtFechaInicio;
	}

	public InputText getTxtPestCodigo() {
		return txtPestCodigo;
	}

	public void setTxtPestCodigo(InputText txtPestCodigo) {
		this.txtPestCodigo = txtPestCodigo;
	}

	public List<PsyPlanEstrategicoDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataPsyPlanEstrategico(getEmpresaIntoSession());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<PsyPlanEstrategicoDTO> psyPlanEstrategicoDTO) {
		this.data = psyPlanEstrategicoDTO;
	}

	public PsyPlanEstrategicoDTO getSelectedPsyPlanEstrategico() {
		return selectedPsyPlanEstrategico;
	}

	public void setSelectedPsyPlanEstrategico(
			PsyPlanEstrategicoDTO psyPlanEstrategico) {
		this.selectedPsyPlanEstrategico = psyPlanEstrategico;
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
