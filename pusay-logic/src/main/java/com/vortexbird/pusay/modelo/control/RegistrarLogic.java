package com.vortexbird.pusay.modelo.control;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.jsf.FacesContextUtils;

import com.sun.faces.application.annotation.FacesComponentUsage;
import com.vortexbird.pusay.exceptions.ZMessManager;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPersona;
import com.vortexbird.pusay.utilities.EnviarCorreo;
import com.vortexbird.pusay.utilities.Utilities;
import com.vortexbird.seguridad.modelo.dto.ResultadoCrearUsuarioDTO;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;
import com.vortexbird.seguridad.rest.LoginRestServiceClient;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("RegistrarLogic")
public class RegistrarLogic implements IRegistrarLogic {
    private static final Logger log = LoggerFactory.getLogger(RegistrarLogic.class);
    private static final String hostServerMail = "smtp.gmail.com";
    private static final String TRUE = "true";
    private static final String FALSE = "false";
    private static final String userPusay = "no.reply.pusay@vortexbird.com";
    private static final String passwordPusay = "3C0V04RT3PVS4Y#<>2015@";
    
    @Autowired
    IPsyEmpresaLogic logicPsyEmpresa1;
    
    @Autowired
    IPsyPersonaLogic logicPsyPersona2;
    
    @Autowired
    private IPsyParametroLogic psyParametroLogic; 

   
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void registrar(PsyPersona persona, PsyEmpresa empresa, String nombreResponsable, String apellidoResponsable, String contrasena, String confirmaContrasena) throws Exception {
        log.debug("Registrando Empresa y Responsable ambiental");

        try {
        	
        	if(persona==null){
        		throw new ZMessManager("Porfavor digite todos los datos del responsable ambiental");
        	}
            if(empresa==null){
            	throw new ZMessManager("Porfavor digite todos los datos de la empresa");
            }
            if(nombreResponsable==null){
            	throw new ZMessManager("Porfavor digite el nombre del responsable");
            }
            if(nombreResponsable.trim().equals("")){
            	throw new ZMessManager("Porfavor digite el nombre del responsable");
            }
            if(apellidoResponsable==null){
            	throw new ZMessManager("Porfavor digite el apellido del responsable");
            }
            if(apellidoResponsable.trim().equals("")){
            	throw new ZMessManager("Porfavor digite el apellido del responsable");
            }
            if(contrasena==null){
            	throw new ZMessManager("Porfavor digite una contraseña para la aplicacion");
            }
            if(contrasena.trim().equals("")){
            	throw new ZMessManager("Porfavor digite una contraseña para la aplicacion");
            }
            if(confirmaContrasena==null){
            	throw new ZMessManager("Porfavor confirme su contraseña");
            }
            if(confirmaContrasena.trim().equals("")){
            	throw new ZMessManager("Porfavor confirme su contraseña");
            }
            if(!contrasena.equals(confirmaContrasena)){
            	throw new ZMessManager("Las contraseñas deben ser iguales");
            }
            if(persona.getTelefono() == null){
            	throw new ZMessManager("Porfavor digite el telefono");
            }
            if(persona.getEmail() == null){
            	throw new ZMessManager("Porfavor digite el e-mail");
            }
            if(persona.getEmail().trim().equals("")){
            	throw new ZMessManager("Porfavor digite el e-mail");
            }
            boolean isNumeric = Utilities.isNumeric(persona.getTelefono().toString());
            
            if(isNumeric == false){
            	throw new ZMessManager("El Telefono debe ser numerico");
            }
            
            boolean emailIsValid = Utilities.validateEmailAddress(persona.getEmail());
            
            if(emailIsValid == false){
            	throw new ZMessManager("E-mail invalido");
            }
            
            logicPsyEmpresa1.savePsyEmpresa(empresa);
            
            Object[] variablesEmpresa = {"nombre",true,empresa.getNombre(),"="};
            List<PsyEmpresa> empresaGuardada = logicPsyEmpresa1.findByCriteria(variablesEmpresa, null, null);
            PsyEmpresa empresaTemporal = empresaGuardada.get(0);
            	
            persona.setPsyEmpresa(empresaTemporal);
            if(persona.getPsyEmpresa()==null){
            	throw new ZMessManager("Porfavor digite todo los datos de la empresa");
            }
            logicPsyPersona2.savePsyPersona(persona);
            
            Object[] variablesPersona = {"email",true,persona.getEmail(),"="};
            List<PsyPersona> personaGuardada = logicPsyPersona2.findByCriteria(variablesPersona, null, null);
            PsyPersona personaTemporal = personaGuardada.get(0);            
            empresa.setPsyPersona(personaTemporal);
            
            logicPsyEmpresa1.updatePsyEmpresa(empresa);
            
            ResultadoCrearUsuarioDTO resultadoCrearUsuarioDTO = LoginRestServiceClient.crearUsuario(
            		apellidoResponsable,
            		empresaTemporal.getEmprCodigo().toString(),
            		contrasena,
            		persona.getEmail(), 
            		"1",
            		0L,
            		1L,
            		persona.getEmail(),
            		nombreResponsable,
            		0L,
            		"2",
            		"1",
            		"2",
            		psyParametroLogic.getPsyParametro("urlServiciosRest").getValor());
            
            if(resultadoCrearUsuarioDTO.getExito()==false){
            	throw new ZMessManager(resultadoCrearUsuarioDTO.getMensaje());
            }
            
            
    		
    		new Thread(){
    			@Override
    			public void run() {
    				super.run();    				
    				try {
    					String[] recipiente = {persona.getEmail()};
    		    		String hostServer = hostServerMail;//"smtp.gmail.com -> para gmail"
    		    		String auth = TRUE;//"true -> cuando es gmail"
    		    		String userServer = userPusay;
    		    		String passServer = passwordPusay;
    		    		String esGmail = TRUE;//"true -> cuando es gmail"
    		    		
    		    		String subject = "Bienvenido a Pusay, "+nombreResponsable;
    		    		String mensaje = "<br/><br/>"
    		    				+ "Usted ha quedado registrado en la empresa "+empresa.getNombre()+".<br/><br/>"
    		    				+ "Recuerde los datos mas importantes: <br/><br/>"
    		    				+ "Nombre del Responsable Ambiental: "+persona.getNombre()+".<br/>"
    		    				+ "Correo electronico: "+persona.getEmail()+".<br/>"
    		    				+ "<br/>"
    		    				+ "<br/>"
    		    				+ "Atentamente,<br/>"
    		    				+ "<br/>"
    		    				+ "<br/>"
    		    				+ "El equipo de Pusay.<br/>";
    		    		File attachment = null;
    					EnviarCorreo.sendMail(hostServer, auth, userServer, passServer, esGmail, recipiente, subject, mensaje, attachment, null);
					} catch (Exception e) {
						log.error("Envio de correo fallido", e.getMessage());
					}
    			}
    		}.start();
    			
    		
    		
    		
    		
    		
            log.debug("Registro Exitoso");
        } catch (Exception e) {
            log.error("Registro Fallido", e);
            throw e;
        } 
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void recuperarClave(String correo, UsuarioDTO usuarioDTO) throws Exception{
    	log.info("###### INICIANDO PROCESO DE ENVIO DE CORREO DE RECUPERACION DE CLAVE ########");
    	try {
    		if(correo == null){
    			throw new ZMessManager("Porfavor ingrese la direccion de correo electronico");
    		}
    		
    		if(correo.trim().equals("")){
    			throw new ZMessManager("Porfavor ingrese la direccion de correo electronico");
    		}
    		
    		if(usuarioDTO == null){
    			throw new ZMessManager("No existe un usuario en la base de datos con el correo : "+correo);
    		}
    		
			String uuid = UUID.randomUUID().toString();
			
			String md5 = Utilities.convertirMD5(uuid);
			
			Object[] variables = {"email",true,correo,"=","estadoRegistro",true,"A","="};
			
			List<PsyPersona> usuarioConsulta = logicPsyPersona2.findByCriteria(variables, null, null);
			
			if(usuarioConsulta.size() == 0){
    			throw new ZMessManager("No existe un usuario en la base de datos con el correo : "+correo);
    		}
			
    		
			logicPsyPersona2.updateUsuarioConsulta(usuarioConsulta.get(0), uuid, uuid, usuarioDTO.getUsu_nombres(), usuarioDTO.getUsu_apellidos());
    		
    		new Thread(){
    			@Override
    			public void run() {
    				super.run();    				
    				try {
    					
    					
    					String[] recipiente = {correo};
    		    		String hostServer = hostServerMail;//"smtp.gmail.com -> para gmail"
    		    		String auth = TRUE;//"true -> cuando es gmail"
    		    		String userServer = userPusay;
    		    		String passServer = passwordPusay;
    		    		String esGmail = TRUE;//"true -> cuando es gmail"
    		    		
    		    		String subject = "Saludos, "+usuarioDTO.getUsu_nombres();
    		    		String mensaje = "<br/><br/>"
    		    				+ "Recientemente has solicitado un cambio de clave .<br/><br/>"
    		    				+ "1. Copia el siguiente codigo: "+md5+"  <br/><br/>"
    		    				+ "2. Dirigete al siguiente link: "
    		    				+ "<a href=\""+psyParametroLogic.getPsyParametro("urlCorreoRecuperacion").getValor()+"\">Recuperacion de clave para PUSAY</a><br/>"
    		    				+ "3. Pega el codigo en el campo llamado : 'Token' <br/>"
    		    				+ "<br/>"
    		    				+ "<br/>"
    		    				+ "Atentamente,<br/>"
    		    				+ "<br/>"
    		    				+ "<br/>"
    		    				+ "El equipo de Pusay.<br/>";
    		    		File attachment = null;
    					EnviarCorreo.sendMail(hostServer, auth, userServer, passServer, esGmail, recipiente, subject, mensaje, attachment, null);
					} catch (Exception e) {
						log.error("Envio de correo fallido", e.getMessage());
					}
    			}
    		}.start();
    		
    		log.info("###### PROCESO DE ENVIO DE CORREO DE RECUPERACION DE CLAVE FINALIZADO EXITOSAMENTE ########");
		} catch (Exception e) {
			log.error(" ####### Envio de correo fallido", e.getMessage()+"#########");
			throw e;
		}
    	
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void actionRecuperarClave(UsuarioDTO usuarioConsulta, String token, String correoRecuperacion, String clave, String claveRecuperacion) throws Exception{
    	
    	log.info("###### INICIANDO PROCESO DE RECUPERACION DE CLAVE ########");
    	
    	try {



    		if(token == null){
    			throw new ZMessManager("Porfavor ingrese el token");
    		}

    		if(token.trim().equals("")){
    			throw new ZMessManager("Porfavor ingrese el token");
    		}

    		if(correoRecuperacion == null){
    			throw new ZMessManager("Porfavor ingrese la direccion de correo electronico");
    		}

    		if(correoRecuperacion.trim().equals("")){
    			throw new ZMessManager("Porfavor ingrese la direccion de correo electronico");
    		}

    		if(clave == null){
    			throw new ZMessManager("Porfavor ingrese la clave");
    		}

    		if(clave.trim().equals("")){
    			throw new ZMessManager("Porfavor ingrese la clave");
    		}

    		if(claveRecuperacion == null){
    			throw new ZMessManager("Porfavor ingrese la confirmacion de la clave");
    		}

    		if(claveRecuperacion.trim().equals("")){
    			throw new ZMessManager("Porfavor ingrese la confirmacion de la clave");
    		}

    		if(usuarioConsulta == null){
    			throw new ZMessManager("No existe un usuario en la base de datos con el correo : "+correoRecuperacion);
    		}
    		
    		if(usuarioConsulta.getUsu_correo() == null){
    			throw new ZMessManager("No existe un usuario en la base de datos con el correo : "+correoRecuperacion);
    		}
    		
    		if(usuarioConsulta.getUsu_correo().trim().equals("")){
    			throw new ZMessManager("No existe un usuario en la base de datos con el correo : "+correoRecuperacion);
    		}
    		
    		Object[] variables = {"email",true,correoRecuperacion,"=","estadoRegistro",true,"A","="};

    		List<PsyPersona> usuario = logicPsyPersona2.findByCriteria(variables, null, null);

    		if(usuario.size() == 0){
    			throw new ZMessManager("No existe un usuario en la base de datos con el correo : "+correoRecuperacion);
    		}

    		if(!clave.trim().equals(claveRecuperacion.trim())){
    			throw new ZMessManager("Las contraseñas deben ser iguales");
    		}
    		
    		if((usuarioConsulta.getContrasenaMD5().trim().equals(token.trim())) == false){
    			throw new ZMessManager("Token inválido");
    		}

    		logicPsyPersona2.updateUsuarioConsulta(usuario.get(0), clave, claveRecuperacion, usuarioConsulta.getUsu_nombres(), usuarioConsulta.getUsu_apellidos());
    		
    		log.info("###### PROCESO DE RECUPERACION DE CLAVE FINALIZADO EXITOSAMENTE ########");
    		
    	} catch (Exception e) {
    		log.error(" ####### Proceso de recuperacion de clave", e.getMessage()+"#########");
			throw e;
		}
    	
    }
    
}
