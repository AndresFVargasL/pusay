package com.vortexbird.pusay.presentation.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.FlowEvent;

import com.vortexbird.pusay.exceptions.ZMessManager;
import com.vortexbird.pusay.modelo.PsyCiudad;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPais;
import com.vortexbird.pusay.modelo.PsyPersona;
import com.vortexbird.pusay.modelo.PsyProvincia;
import com.vortexbird.pusay.modelo.dto.PsyPersonaDTO;
import com.vortexbird.pusay.presentation.businessDelegate.IBusinessDelegatorView;
import com.vortexbird.pusay.presentation.utilities.FacesUtils;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;
import com.vortexbird.seguridad.rest.LoginRestServiceClient;



/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class RegistrarView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(RegistrarView.class);
    private static final String ESTADO_REGISTRO_ACTIVO ="A";
    private static final String ESTADO_REGISTRO_INACTIVO = "I";
    private InputText txtNitEmpresa;
    private InputText txtNombreEmpresa;
    private InputText txtDireccionEmpresa;
    private InputText txtTelefonoEmpresa;
    private InputText txtNombreResponsable;
    private InputText txtApellidoResponsable;
    private InputText txtEmailResponsable;
    private InputText txtTelefonoResponsable;
    private InputText txtContrasena;
    private InputText txtConfirmaContrasena;
    private InputText txtCorreoRecuperacion;
    private InputText txtTokenRecuperacion;
    private InputText txtClaveRecuperacion;
    private InputText txtConfirmacionClaveRecuperacion;
    private String somPais;
    private String somProvincia;
    private String somCiudad;
    private List<SelectItem> losPaises;
    private List<SelectItem> lasProvincias;
    private List<SelectItem> lasCiudades;
    private CommandButton btnRegistrar;
    private CommandButton btnLimpiar;
    private PsyEmpresa empresaARegistrar;
    private PsyPersona personaARegistrar;
    private InputText txtEmail;
    private InputText txtEstadoRegistro;
    private InputText txtNombre;
    private InputText txtApellido;
    private InputText txtTelefono;
    private InputText txtEmprCodigo_PsyEmpresa;
    private InputText txtPersCodigo;
    private InputText txtContrasenaUsuarioConsulta;
    private InputText txtCompruebaContrasenaUsuarioConsulta;
    private InputText txtPUEmail;
    private InputText txtPUNombre;
    private InputText txtPUApellido;
    private InputText txtPUTelefono;
    private InputText txtPUEmprCodigo_PsyEmpresa;
    private InputText txtPUPersClaveActual;
    private InputText txtPUContrasenaUsuarioConsulta;
    private InputText txtPUCompruebaContrasenaUsuarioConsulta;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private CommandButton btnAceptar;
    private CommandButton btnPUSave;
    private List<PsyPersonaDTO> data;
    private PsyPersonaDTO selectedPsyPersona;
    private PsyPersona entity;
    private PsyPersona perfilUsuario;
    private String correoRecuperacion;
    private String realPath;
    private boolean showDialog;
    private boolean editandoUConsulta=false;
    private boolean renderEmail=false;
    
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

    public RegistrarView() {
        super();
        
    }
    
    @PostConstruct
    public void initIt(){
    	try{
    	UsuarioDTO usuarioDTO = (UsuarioDTO) FacesUtils.getfromSession("usuarioDTO");
    	if(usuarioDTO != null){
    	Object[] variables = {"estadoRegistro",true,"A","=",
    			"email",true,usuarioDTO.getUsu_login().toLowerCase(),"=",
    			"psyEmpresa.emprCodigo",false,FacesUtils.getEmpresaIntoSession().getEmprCodigo(),"="};
    	perfilUsuario = businessDelegatorView.findByCriteriaInPsyPersona(variables, null, null).get(0);
    	
    	txtPUEmail = new InputText();
    	txtPUNombre = new InputText();
    	txtPUApellido = new InputText();
    	txtPUTelefono = new InputText();
    	txtPUEmprCodigo_PsyEmpresa = new InputText();
    	
    	txtPUEmail.setValue(usuarioDTO.getUsu_login());
    	txtPUNombre.setValue(usuarioDTO.getUsu_nombres());
    	txtPUApellido.setValue(usuarioDTO.getUsu_apellidos());
    	txtPUTelefono.setValue(perfilUsuario.getTelefono());
    	txtPUEmprCodigo_PsyEmpresa.setValue(FacesUtils.getEmpresaIntoSession().getNombre());
    	}
    	}catch(Exception e){
    		FacesUtils.addErrorMessage(e.getMessage());
			logger.error("##### Error cargando los datos de la persona, el error fue : "+e.getMessage()+"#########");
    	}
    	
    }
    
    public void listener_SomPais() {
    	

		if(somPais.trim().equals("0")){
			lasProvincias=null;
			lasCiudades=null;
		}
		
    	
    }
    
    public void listener_SomProvincia() {
    	
    	if(somProvincia.trim().equals("0")){
			lasCiudades=null;
		}
    	
    }
    
    public void save(){
    	
    	empresaARegistrar = new PsyEmpresa();
    	personaARegistrar = new PsyPersona();
    	
    	try {
    	//Se hidrata el objeto empresa 	, el responsable ambiental se hidrata en la logica
    	empresaARegistrar.setPsyCiudad((somCiudad != null) ? businessDelegatorView.getPsyCiudad(Long.parseLong(somCiudad)) : null);
    	empresaARegistrar.setPsyPersona(null);
    	empresaARegistrar.setNit((FacesUtils.checkString(txtNitEmpresa) != null) ? FacesUtils.checkString(txtNitEmpresa) : null);
    	empresaARegistrar.setNombre((FacesUtils.checkString(txtNombreEmpresa) != null) ? FacesUtils.checkString(txtNombreEmpresa) : null);
    	empresaARegistrar.setDireccionPrincipal((FacesUtils.checkString(txtDireccionEmpresa) != null) ? FacesUtils.checkString(txtDireccionEmpresa) : null);
    	empresaARegistrar.setTelefonoPrincipal((FacesUtils.checkLong(txtTelefonoEmpresa) != null) ? FacesUtils.checkLong(txtTelefonoEmpresa) : null);
    	empresaARegistrar.setEstadoRegistro(ESTADO_REGISTRO_ACTIVO);
    	
    	//Se hidrata el objeto persona , la empresa a la cual pertenece se hidrata en la logica
    	personaARegistrar.setNombre((FacesUtils.checkString(txtNombreResponsable) != null && FacesUtils.checkString(txtApellidoResponsable)!=null) ?
    				FacesUtils.checkString(txtNombreResponsable)+" "+FacesUtils.checkString(txtApellidoResponsable) : null);
    	personaARegistrar.setEmail((FacesUtils.checkString(txtEmailResponsable) != null) ? FacesUtils.checkString(txtEmailResponsable) : null);
    	personaARegistrar.setTelefono((FacesUtils.checkLong(txtTelefonoResponsable) != null) ? FacesUtils.checkLong(txtTelefonoResponsable) : null);
    	personaARegistrar.setEstadoRegistro(ESTADO_REGISTRO_ACTIVO);
    	
    	//Se obtienen el nombre y el apellido por separado del responsable ambiental para guardarlso en la base de datos de seguridad
    	String nombreResponsable=(FacesUtils.checkString(txtNombreResponsable) != null) ? FacesUtils.checkString(txtNombreResponsable) : null;
    	String apellidoResponsable=(FacesUtils.checkString(txtApellidoResponsable) != null) ? FacesUtils.checkString(txtApellidoResponsable) : null;
    	String contrasena = (FacesUtils.checkString(txtContrasena) != null) ? FacesUtils.checkString(txtContrasena) : null;
    	String confirmaContrasena = (FacesUtils.checkString(txtConfirmaContrasena) != null) ? FacesUtils.checkString(txtConfirmaContrasena) : null;
    	
    	businessDelegatorView.registrar(personaARegistrar, empresaARegistrar, nombreResponsable, apellidoResponsable, contrasena, confirmaContrasena);
    	
    	clean();
    	
    	ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		context.redirect(context.getRequestContextPath()
				+ "/login.xhtml");
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			logger.error("##### Error registrando la empresa y el responsable ambiental, el error fue : "+e.getMessage()+"#########");
		}
    	
    }
    
    public String recuperarClave(){
    	
    	try {
    		
    		String correoUsuarioConsulta = correoRecuperacion;
    		
    		if(correoUsuarioConsulta == null || correoUsuarioConsulta.trim().equals("")){
    			throw new Exception("Porfavor ingrese un correo para recuperar su contraseña.");
    		}

    		UsuarioDTO usuarioConsulta = LoginRestServiceClient.consultarUsuarioPorLogin(
    				correoUsuarioConsulta, "2", businessDelegatorView.getPsyParametro("urlServiciosRest").getValor());

    		businessDelegatorView.recuperarClave(correoUsuarioConsulta, usuarioConsulta);

    		FacesUtils.addInfoMessage("Se ha enviado un correo con la informacion necesaria, porfavor revisa tu bandeja de correo");
    		
    		setCorreoRecuperacion(null);
    		
		} catch (Exception e) {
			
			FacesUtils.addErrorMessage(e.getMessage());
			logger.error("##### Error enviando el correo de recuperacion de contraseña, el error fue : "+e.getMessage()+"#########");
			
		}
    	
    	return "exitoRecuperar";
    }
    
    public String action_modificarPerfil(){
    	try {
    		
    	Object[] variables = {"estadoRegistro",true,"A","=",
    			"email",true,FacesUtils.checkString(txtPUEmail).toLowerCase(),"=",
    			"psyEmpresa.emprCodigo",false,FacesUtils.getEmpresaIntoSession().getEmprCodigo(),"="};
    	
		perfilUsuario = businessDelegatorView.findByCriteriaInPsyPersona(variables, null, null).get(0);
		
		perfilUsuario.setEmail((FacesUtils.checkString(txtPUEmail) != null) ? FacesUtils.checkString(txtPUEmail).toLowerCase() : null);
		perfilUsuario.setNombre((FacesUtils.checkString(txtPUNombre) != null && FacesUtils.checkString(txtPUApellido) != null ) ?
								FacesUtils.checkString(txtPUNombre) + " " + FacesUtils.checkString(txtPUApellido)  : null);
		perfilUsuario.setTelefono((FacesUtils.checkLong(txtPUTelefono) != null) ? FacesUtils.checkLong(txtPUTelefono) : null);
		
		String contrasenaActual = (FacesUtils.checkString(txtPUPersClaveActual) != null) ? FacesUtils.checkString(txtPUPersClaveActual) : null;
		String contrasena = (FacesUtils.checkString(txtPUContrasenaUsuarioConsulta) != null) ? FacesUtils.checkString(txtPUContrasenaUsuarioConsulta) : null;
    	String confirmaContrasena = (FacesUtils.checkString(txtPUCompruebaContrasenaUsuarioConsulta) != null) ? FacesUtils.checkString(txtPUCompruebaContrasenaUsuarioConsulta) : null;
    	String nombreUConsulta =(FacesUtils.checkString(txtPUNombre) != null) ? FacesUtils.checkString(txtPUNombre) : null;
    	String apellidoUConsulta = (FacesUtils.checkString(txtPUApellido) != null) ? FacesUtils.checkString(txtPUApellido) : null;		
    	
     	businessDelegatorView.updatePerfilUsuario(perfilUsuario, contrasenaActual, contrasena, confirmaContrasena, nombreUConsulta, apellidoUConsulta);
		
    	HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
    	
    	session.removeAttribute("usuarioDTO");
    	
    	UsuarioDTO usuarioDTO = LoginRestServiceClient.consultarUsuarioPorLogin(
    			perfilUsuario.getEmail(), "2", businessDelegatorView.getPsyParametro("urlServiciosRest").getValor());
    	
    	FacesUtils.setManagedBeanInSession("usuarioDTO", usuarioDTO);
    	
    	FacesUtils.addInfoMessage("Perfil Actualizado Correctamente");
    	
    	initIt();
    	
    	txtPUPersClaveActual.setValue(null);
    	txtPUContrasenaUsuarioConsulta.setValue(null);
    	txtPUCompruebaContrasenaUsuarioConsulta.setValue(null);
		
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			logger.error("##### Error enviando modificando los datos de perfil, el error fue : "+e.getMessage()+"#########");
		}
    	
    	
    	return "";
    }
    
    public String action_recuperar(){
    	try {
			
		
    	String token = (FacesUtils.checkString(txtTokenRecuperacion) != null) ? FacesUtils.checkString(txtTokenRecuperacion) : null;
    	String correoRecuperacion = (FacesUtils.checkString(txtCorreoRecuperacion) != null) ? FacesUtils.checkString(txtCorreoRecuperacion) : null;
    	String clave = (FacesUtils.checkString(txtClaveRecuperacion) != null) ? FacesUtils.checkString(txtClaveRecuperacion) : null;
    	String claveRecuperacion = (FacesUtils.checkString(txtConfirmacionClaveRecuperacion) != null) ? 
    			FacesUtils.checkString(txtConfirmacionClaveRecuperacion) : null;
    			
		UsuarioDTO usuarioConsulta = LoginRestServiceClient.consultarUsuarioPorLogin(
				correoRecuperacion, "2", businessDelegatorView.getPsyParametro("urlServiciosRest").getValor());
		
		businessDelegatorView.actionRecuperarClave(usuarioConsulta, token, correoRecuperacion, clave, claveRecuperacion);
		
		FacesUtils.addInfoMessage("Clave Recuperada Exitosamente");
		
		clean_recuperar();
		
    	} catch (Exception e) {
    		FacesUtils.addErrorMessage(e.getMessage());
			logger.error("##### Error procesando la recuperacion de contraseña, el error fue : "+e.getMessage()+"#########");
			return "";
		}
    	return "exitoRecuperarClave";
    }
    
    public String clean_recuperar(){
    	
    	if (txtTokenRecuperacion != null) {
    		txtTokenRecuperacion.setValue(null);
    		txtTokenRecuperacion.setDisabled(false);
        }
    	
    	if (txtCorreoRecuperacion != null) {
    		txtCorreoRecuperacion.setValue(null);
    		txtCorreoRecuperacion.setDisabled(false);
        }
    	
    	if (txtClaveRecuperacion != null) {
    		txtClaveRecuperacion.setValue(null);
    		txtClaveRecuperacion.setDisabled(false);
        }
    	
    	if (txtConfirmacionClaveRecuperacion != null) {
    		txtConfirmacionClaveRecuperacion.setValue(null);
    		txtConfirmacionClaveRecuperacion.setDisabled(false);
        }
    	
    	return "";
    }
    
    public String clean(){
    	
    	empresaARegistrar = null;
    	personaARegistrar = null;
    	
    	setSomCiudad("0");
    	setSomPais("0");
    	setSomProvincia("0");
    	
        if (txtNitEmpresa != null) {
        	txtNitEmpresa.setValue(null);
        }
        
        if (txtNombreEmpresa != null) {
        	txtNombreEmpresa.setValue(null);
        }
        
        if (txtDireccionEmpresa != null) {
        	txtDireccionEmpresa.setValue(null);
        }
        
        if (txtTelefonoEmpresa != null) {
        	txtTelefonoEmpresa.setValue(null);
        }
        
        if (txtNombreResponsable != null) {
        	txtNombreResponsable.setValue(null);
        }
        
        
        if (txtApellidoResponsable != null) {
        	txtApellidoResponsable.setValue(null);
        }
        
        
        if (txtEmailResponsable != null) {
        	txtEmailResponsable.setValue(null);
        }
        
        
        if (txtTelefonoResponsable != null) {
        	txtTelefonoResponsable.setValue(null);
        }
             
        if (btnRegistrar != null) {
            btnRegistrar.setDisabled(false);
        }

        if (btnLimpiar != null) {
        	btnLimpiar.setDisabled(false);
        }

        return "";
    	
    }
    
    public String action_new_persona() {
    	txtEstadoRegistro.setValue("Activo");
    	txtEmprCodigo_PsyEmpresa.setValue(FacesUtils.getEmpresaIntoSession().getNombre());
    	setEditandoUConsulta(false);
    	setRenderEmail(false);
        action_clear_persona();
        selectedPsyPersona = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear_persona() {
        entity = null;
        selectedPsyPersona = null;

        if (txtEmail != null) {
            txtEmail.setValue(null);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
        }
        
        if (txtApellido != null) {
        	txtApellido.setValue(null);
        }

        if (txtTelefono != null) {
            txtTelefono.setValue(null);
        }

        if (txtEmprCodigo_PsyEmpresa != null) {
            txtEmprCodigo_PsyEmpresa.setDisabled(true);
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }
        
        if (txtContrasenaUsuarioConsulta != null) {
        	txtContrasenaUsuarioConsulta.setValue(null);
        }
        
        if (txtCompruebaContrasenaUsuarioConsulta!= null) {
        	txtCompruebaContrasenaUsuarioConsulta.setValue(null);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(false);
        }

        return "";
    }
    
    public String action_clear_perfil() {
        entity = null;
        selectedPsyPersona = null;

        if (txtEmail != null) {
            txtEmail.setValue(null);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
        }
        
        if (txtApellido != null) {
        	txtApellido.setValue(null);
        }

        if (txtTelefono != null) {
            txtTelefono.setValue(null);
        }

        if (txtEmprCodigo_PsyEmpresa != null) {
            txtEmprCodigo_PsyEmpresa.setDisabled(true);
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }
        
        if (txtContrasenaUsuarioConsulta != null) {
        	txtContrasenaUsuarioConsulta.setValue(null);
        }
        
        if (txtCompruebaContrasenaUsuarioConsulta!= null) {
        	txtCompruebaContrasenaUsuarioConsulta.setValue(null);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(false);
        }

        return "";
    }
    
    public String action_edit(ActionEvent evt) {
    	action_clear_persona();
    	try {
			
		setEditandoUConsulta(true);
		setRenderEmail(true);
		
        selectedPsyPersona = (PsyPersonaDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedPsyPersona"));
        UsuarioDTO usuarioConsulta = LoginRestServiceClient.consultarUsuarioPorLogin(
        		selectedPsyPersona.getEmail().toString(), "2", businessDelegatorView.getPsyParametro("urlServiciosRest").getValor());
        if(usuarioConsulta != null){
        	txtEmail.setValue(selectedPsyPersona.getEmail());
        	txtEstadoRegistro.setValue(selectedPsyPersona.getEstadoRegistro());
        	txtNombre.setValue(usuarioConsulta.getUsu_nombres());
        	txtNombre.setDisabled(false);
        	txtApellido.setValue(usuarioConsulta.getUsu_apellidos());
        	txtApellido.setDisabled(false);
        	txtTelefono.setValue(selectedPsyPersona.getTelefono());
        	txtTelefono.setDisabled(false);
        	txtEmprCodigo_PsyEmpresa.setValue(businessDelegatorView.getPsyEmpresa(selectedPsyPersona.getEmprCodigo_PsyEmpresa()).getNombre());
        	txtEmprCodigo_PsyEmpresa.setDisabled(true);
        	btnSave.setDisabled(false);
        	setShowDialog(true);
        }
    	} catch (Exception e) {
    		FacesUtils.addErrorMessage(e.getMessage());
			logger.error("##### Error cargando el usuario consulta, el error fue : "+e.getMessage()+"#########");
		}
        return "";
    }

    public String action_save() {
        try {
            if ((selectedPsyPersona == null) && (entity == null)) {
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
            entity = new PsyPersona();
            
            String clave = FacesUtils.checkString(txtContrasenaUsuarioConsulta);
            String confirmaClave = FacesUtils.checkString(txtCompruebaContrasenaUsuarioConsulta);
            String nombreUConsulta = FacesUtils.checkString(txtNombre);
            String apellidoUConsulta = FacesUtils.checkString(txtApellido);
            
            entity.setEmail(FacesUtils.checkString(txtEmail));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre)+" "+FacesUtils.checkString(txtApellido));
            entity.setTelefono(FacesUtils.checkLong(txtTelefono));
            entity.setPsyEmpresa(FacesUtils.getEmpresaIntoSession());
            businessDelegatorView.saveUsuarioConsulta(entity, clave, confirmaClave, nombreUConsulta, apellidoUConsulta);
            
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear_persona();
			data = null;
			action_closeDialog();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
			logger.error("##### Error registrando el usuario consulta, el error fue : "+e.getMessage()+"#########");
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long persCodigo = new Long(selectedPsyPersona.getPersCodigo());
                entity = businessDelegatorView.getPsyPersona(persCodigo);
            }
            
            String clave = FacesUtils.checkString(txtContrasenaUsuarioConsulta);
            String confirmaClave = FacesUtils.checkString(txtCompruebaContrasenaUsuarioConsulta);
            String nombreUConsulta = FacesUtils.checkString(txtNombre);
            String apellidoUConsulta = FacesUtils.checkString(txtApellido);

            entity.setEmail(FacesUtils.checkString(txtEmail));
            entity.setEstadoRegistro((FacesUtils.checkString(txtEstadoRegistro)!=null) ?
            		(FacesUtils.checkString(txtEstadoRegistro).trim().equals("Activo")) ? ESTADO_REGISTRO_ACTIVO : ESTADO_REGISTRO_INACTIVO : null);
            entity.setNombre(FacesUtils.checkString(txtNombre)+" "+FacesUtils.checkString(txtApellido));
            entity.setTelefono(FacesUtils.checkLong(txtTelefono));
            entity.setPsyEmpresa(FacesUtils.getEmpresaIntoSession());
            businessDelegatorView.updateUsuarioConsulta(entity, clave, confirmaClave, nombreUConsulta, apellidoUConsulta);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
            action_clear_persona();
			data = null;
			action_closeDialog();
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
			logger.error("##### Error actualizando el usuario consulta, el error fue : "+e.getMessage()+"#########");
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPsyPersona = (PsyPersonaDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedPsyPersona"));

            Long persCodigo = new Long(selectedPsyPersona.getPersCodigo());
            entity = businessDelegatorView.getPsyPersona(persCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }
    
    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePsyPersona(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear_persona();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear_persona();

        return "";
    }
   

	public InputText getTxtNitEmpresa() {
		return txtNitEmpresa;
	}

	public void setTxtNitEmpresa(InputText txtNitEmpresa) {
		this.txtNitEmpresa = txtNitEmpresa;
	}

	public InputText getTxtNombreEmpresa() {
		return txtNombreEmpresa;
	}

	public void setTxtNombreEmpresa(InputText txtNombreEmpresa) {
		this.txtNombreEmpresa = txtNombreEmpresa;
	}

	public InputText getTxtDireccionEmpresa() {
		return txtDireccionEmpresa;
	}

	public void setTxtDireccionEmpresa(InputText txtDireccionEmpresa) {
		this.txtDireccionEmpresa = txtDireccionEmpresa;
	}

	public InputText getTxtTelefonoEmpresa() {
		return txtTelefonoEmpresa;
	}

	public void setTxtTelefonoEmpresa(InputText txtTelefonoEmpresa) {
		this.txtTelefonoEmpresa = txtTelefonoEmpresa;
	}

	public InputText getTxtNombreResponsable() {
		return txtNombreResponsable;
	}

	public void setTxtNombreResponsable(InputText txtNombreResponsable) {
		this.txtNombreResponsable = txtNombreResponsable;
	}

	public InputText getTxtApellidoResponsable() {
		return txtApellidoResponsable;
	}

	public void setTxtApellidoResponsable(InputText txtApellidoResponsable) {
		this.txtApellidoResponsable = txtApellidoResponsable;
	}

	public InputText getTxtEmailResponsable() {
		return txtEmailResponsable;
	}

	public void setTxtEmailResponsable(InputText txtEmailResponsable) {
		this.txtEmailResponsable = txtEmailResponsable;
	}

	public InputText getTxtTelefonoResponsable() {
		return txtTelefonoResponsable;
	}

	public void setTxtTelefonoResponsable(InputText txtTelefonoResponsable) {
		this.txtTelefonoResponsable = txtTelefonoResponsable;
	}

	public String getSomPais() {
		return somPais;
	}

	public void setSomPais(String somPais) {
		this.somPais = somPais;
	}

	public String getSomProvincia() {
		return somProvincia;
	}

	public void setSomProvincia(String somProvincia) {
		this.somProvincia = somProvincia;
	}

	public String getSomCiudad() {
		return somCiudad;
	}

	public void setSomCiudad(String somCiudad) {
		this.somCiudad = somCiudad;
	}

	public List<SelectItem> getLosPaises() {
		try {
			losPaises = new ArrayList<SelectItem>();
			List<PsyPais> losTiposPaises = businessDelegatorView
					.getPsyPais();
			for (PsyPais pais : losTiposPaises) {
				if (pais.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							pais.getPaisCodigo(), pais.getNombre());
					losPaises.add(selectItem);
				}

			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando los paises");
		}
		
		return losPaises;
	}

	public void setLosPaises(List<SelectItem> losPaises) {
		this.losPaises = losPaises;
	}

	public List<SelectItem> getLasProvincias() {
		try {
			if(somPais!=null){
			if(!somPais.trim().equals("0")){
			lasProvincias = new ArrayList<SelectItem>();
			List<PsyProvincia> losTiposProvincias = businessDelegatorView.consultarProvinciasPorPais(Long.parseLong(somPais.trim()));
			for (PsyProvincia provincia : losTiposProvincias) {
				if (provincia.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							provincia.getProvCodigo(), provincia.getNombre());
					lasProvincias.add(selectItem);
				}

			}
			}
			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando las provincias");
		}
		
		return lasProvincias;
		
	}

	public void setLasProvincias(List<SelectItem> lasProvincias) {
		this.lasProvincias = lasProvincias;
	}

	public List<SelectItem> getLasCiudades() {
		try {
			if(somProvincia!=null){
			if(!somProvincia.trim().equals("0")){
				lasCiudades = new ArrayList<SelectItem>();
			List<PsyCiudad> losTiposCiudades = businessDelegatorView.consultarCiudadesPorProvincia(Long.parseLong(somProvincia.trim()));
			for (PsyCiudad ciudad : losTiposCiudades) {
				if (ciudad.getEstadoRegistro().equals("A")) {
					SelectItem selectItem = new SelectItem(
							ciudad.getCiudCodigo(), ciudad.getNombre());
					lasCiudades.add(selectItem);
				}

			}
			}
			}
		} catch (Exception ex) {
			FacesUtils.addErrorMessage("Error cargando las ciudades");
		}
		return lasCiudades;
	}

	public void setLasCiudades(List<SelectItem> lasCiudades) {
		this.lasCiudades = lasCiudades;
	}

	public CommandButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public void setBtnRegistrar(CommandButton btnRegistrar) {
		this.btnRegistrar = btnRegistrar;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public InputText gettxtContrasena() {
		return txtContrasena;
	}

	public void settxtContrasena(InputText txtContrasena) {
		this.txtContrasena = txtContrasena;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}
	
	
	public String onFlowProcess(FlowEvent event) {
            return event.getNewStep();
    }

	public InputText getTxtContrasena() {
		return txtContrasena;
	}

	public void setTxtContrasena(InputText txtContrasena) {
		this.txtContrasena = txtContrasena;
	}

	public InputText getTxtConfirmaContrasena() {
		return txtConfirmaContrasena;
	}

	public void setTxtConfirmaContrasena(InputText txtConfirmaContrasena) {
		this.txtConfirmaContrasena = txtConfirmaContrasena;
	}
	
	public InputText getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(InputText txtEmail) {
        this.txtEmail = txtEmail;
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

    public InputText getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(InputText txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    public InputText getTxtEmprCodigo_PsyEmpresa() {
        return txtEmprCodigo_PsyEmpresa;
    }

    public void setTxtEmprCodigo_PsyEmpresa(InputText txtEmprCodigo_PsyEmpresa) {
        this.txtEmprCodigo_PsyEmpresa = txtEmprCodigo_PsyEmpresa;
    }

    public InputText getTxtPersCodigo() {
        return txtPersCodigo;
    }

    public void setTxtPersCodigo(InputText txtPersCodigo) {
        this.txtPersCodigo = txtPersCodigo;
    }

    public List<PsyPersonaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPsyPersona(FacesUtils.getEmpresaIntoSession());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PsyPersonaDTO> psyPersonaDTO) {
        this.data = psyPersonaDTO;
    }

    public PsyPersonaDTO getSelectedPsyPersona() {
        return selectedPsyPersona;
    }

    public void setSelectedPsyPersona(PsyPersonaDTO psyPersona) {
        this.selectedPsyPersona = psyPersona;
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

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

	public InputText getTxtApellido() {
		return txtApellido;
	}

	public void setTxtApellido(InputText txtApellido) {
		this.txtApellido = txtApellido;
	}

	public InputText getTxtContrasenaUsuarioConsulta() {
		return txtContrasenaUsuarioConsulta;
	}

	public void setTxtContrasenaUsuarioConsulta(
			InputText txtContrasenaUsuarioConsulta) {
		this.txtContrasenaUsuarioConsulta = txtContrasenaUsuarioConsulta;
	}

	public InputText getTxtCompruebaContrasenaUsuarioConsulta() {
		return txtCompruebaContrasenaUsuarioConsulta;
	}

	public void setTxtCompruebaContrasenaUsuarioConsulta(
			InputText txtCompruebaContrasenaUsuarioConsulta) {
		this.txtCompruebaContrasenaUsuarioConsulta = txtCompruebaContrasenaUsuarioConsulta;
	}

	public boolean isEditandoUConsulta() {
		return editandoUConsulta;
	}

	public void setEditandoUConsulta(boolean editandoUConsulta) {
		this.editandoUConsulta = editandoUConsulta;
	}

	public boolean isRenderEmail() {
		return renderEmail;
	}

	public void setRenderEmail(boolean renderEmail) {
		this.renderEmail = renderEmail;
	}

	public String getCorreoRecuperacion() {
		return correoRecuperacion;
	}

	public void setCorreoRecuperacion(String correoRecuperacion) {
		this.correoRecuperacion = correoRecuperacion;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public InputText getTxtCorreoRecuperacion() {
		return txtCorreoRecuperacion;
	}

	public void setTxtCorreoRecuperacion(InputText txtCorreoRecuperacion) {
		this.txtCorreoRecuperacion = txtCorreoRecuperacion;
	}

	public InputText getTxtTokenRecuperacion() {
		return txtTokenRecuperacion;
	}

	public void setTxtTokenRecuperacion(InputText txtTokenRecuperacion) {
		this.txtTokenRecuperacion = txtTokenRecuperacion;
	}

	public InputText getTxtClaveRecuperacion() {
		return txtClaveRecuperacion;
	}

	public void setTxtClaveRecuperacion(InputText txtClaveRecuperacion) {
		this.txtClaveRecuperacion = txtClaveRecuperacion;
	}

	public InputText getTxtConfirmacionClaveRecuperacion() {
		return txtConfirmacionClaveRecuperacion;
	}

	public void setTxtConfirmacionClaveRecuperacion(
			InputText txtConfirmacionClaveRecuperacion) {
		this.txtConfirmacionClaveRecuperacion = txtConfirmacionClaveRecuperacion;
	}

	public CommandButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(CommandButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public InputText getTxtPUEmail() {
		return txtPUEmail;
	}

	public void setTxtPUEmail(InputText txtPUEmail) {
		this.txtPUEmail = txtPUEmail;
	}

	public InputText getTxtPUNombre() {
		return txtPUNombre;
	}

	public void setTxtPUNombre(InputText txtPUNombre) {
		this.txtPUNombre = txtPUNombre;
	}

	public InputText getTxtPUApellido() {
		return txtPUApellido;
	}

	public void setTxtPUApellido(InputText txtPUApellido) {
		this.txtPUApellido = txtPUApellido;
	}

	public InputText getTxtPUTelefono() {
		return txtPUTelefono;
	}

	public void setTxtPUTelefono(InputText txtPUTelefono) {
		this.txtPUTelefono = txtPUTelefono;
	}

	public InputText getTxtPUEmprCodigo_PsyEmpresa() {
		return txtPUEmprCodigo_PsyEmpresa;
	}

	public void setTxtPUEmprCodigo_PsyEmpresa(InputText txtPUEmprCodigo_PsyEmpresa) {
		this.txtPUEmprCodigo_PsyEmpresa = txtPUEmprCodigo_PsyEmpresa;
	}
	
	public InputText getTxtPUContrasenaUsuarioConsulta() {
		return txtPUContrasenaUsuarioConsulta;
	}

	public void setTxtPUContrasenaUsuarioConsulta(
			InputText txtPUContrasenaUsuarioConsulta) {
		this.txtPUContrasenaUsuarioConsulta = txtPUContrasenaUsuarioConsulta;
	}

	public InputText getTxtPUCompruebaContrasenaUsuarioConsulta() {
		return txtPUCompruebaContrasenaUsuarioConsulta;
	}

	public void setTxtPUCompruebaContrasenaUsuarioConsulta(
			InputText txtPUCompruebaContrasenaUsuarioConsulta) {
		this.txtPUCompruebaContrasenaUsuarioConsulta = txtPUCompruebaContrasenaUsuarioConsulta;
	}

	public CommandButton getBtnPUSave() {
		return btnPUSave;
	}

	public void setBtnPUSave(CommandButton btnPUSave) {
		this.btnPUSave = btnPUSave;
	}

	public InputText getTxtPUPersClaveActual() {
		return txtPUPersClaveActual;
	}

	public void setTxtPUPersClaveActual(InputText txtPUPersClaveActual) {
		this.txtPUPersClaveActual = txtPUPersClaveActual;
	}
    
    
}
