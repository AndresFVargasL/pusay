package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.dataaccess.dao.*;
import com.vortexbird.pusay.exceptions.*;
import com.vortexbird.pusay.modelo.*;
import com.vortexbird.pusay.modelo.dto.PsyPersonaDTO;
import com.vortexbird.pusay.utilities.EnviarCorreo;
import com.vortexbird.pusay.utilities.Utilities;
import com.vortexbird.seguridad.modelo.dto.ResultadoCrearUsuarioDTO;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;
import com.vortexbird.seguridad.rest.LoginRestServiceClient;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.security.auth.callback.ConfirmationCallback;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("PsyPersonaLogic")
public class PsyPersonaLogic implements IPsyPersonaLogic {
	private static Logger log = Logger.getLogger(EnviarCorreo.class);
    private static final String hostServerMail = "smtp.gmail.com";
    private static final String TRUE = "true";
    private static final String FALSE = "false";
    private static final String userPusay = "no.reply.pusay@vortexbird.com";
    private static final String passwordPusay = "3C0V04RT3PVS4Y#<>2015@";

    /**
     * DAO injected by Spring that manages PsyPersona entities
     *
     */
    @Autowired
    private IPsyPersonaDAO psyPersonaDAO;

    /**
    * DAO injected by Spring that manages PsyEmpresa entities
    *
    */
    @Autowired
    private IPsyEmpresaDAO psyEmpresaDAO;

    /**
    * Logic injected by Spring that manages PsyEmpresa entities
    *
    */
    @Autowired
    IPsyEmpresaLogic logicPsyEmpresa1;
    
    @Autowired 
    IPsyParametroLogic psyParametroLogic;

    @Transactional(readOnly = true)
    public List<PsyPersona> getPsyPersona() throws Exception {
        log.debug("finding all PsyPersona instances");

        List<PsyPersona> list = new ArrayList<PsyPersona>();

        try {
            list = psyPersonaDAO.findAll();
        } catch (Exception e) {
            log.error("finding all PsyPersona failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "PsyPersona");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePsyPersona(PsyPersona entity) throws Exception {
        log.debug("saving PsyPersona instance");

        try {
            if (entity.getPsyEmpresa() == null) {
                throw new ZMessManager().new ForeignException("Empresa");
            }

            if (entity.getEmail() == null) {
                throw new ZMessManager().new EmptyFieldException("Email");
            }

            if ((entity.getEmail() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEmail(),
                        100) == false)) {
                throw new ZMessManager().new NotValidFormatException("Email");
            }
            
            if(Utilities.validateEmailAddress(entity.getEmail())==false){
            	throw new ZMessManager("La direccion de correo electronico no es valida");
            }

            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Estado Registro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Estado Registro");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("Nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("Nombre");
            }

            if ((entity.getTelefono() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getTelefono(), 15, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Telefono");
            }

            if (entity.getPsyEmpresa().getEmprCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Empresa");
            }

            psyPersonaDAO.save(entity);

            log.debug("save PsyPersona successful");
        } catch (Exception e) {
            log.error("save PsyPersona failed", e);
            throw e;
        } finally {
        }
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveUsuarioConsulta(PsyPersona entity, String clave, String confimaClave, String nombreUConsulta, String apellidoUConsulta) throws Exception {
        log.debug("saving PsyPersona instance");

        try {
            if (entity.getPsyEmpresa() == null) {
                throw new ZMessManager().new ForeignException("Empresa");
            }

            if (entity.getEmail() == null) {
                throw new ZMessManager().new EmptyFieldException("Email");
            }

            if ((entity.getEmail() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEmail(),
                        100) == false)) {
                throw new ZMessManager().new NotValidFormatException("Email");
            }
            
            if(Utilities.validateEmailAddress(entity.getEmail())==false){
            	throw new ZMessManager("La direccion de correo electronico no es valida");
            }

            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Estado Registro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Estado Registro");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("Nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("Nombre");
            }

            if ((entity.getTelefono() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getTelefono(), 15, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Telefono");
            }

            if (entity.getPsyEmpresa().getEmprCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Empresa");
            }
            
            
            if(clave == null){
            	throw new ZMessManager("Porfavor introduzca la clave");
            }
            
            if(confimaClave == null){
            	throw new ZMessManager("Porfavor introduzca la confirmacion de la clave");
            }
            
            if(clave.trim().equals("")){
            	throw new ZMessManager("Porfavor introduzca la clave");
            }
            
            if(confimaClave.trim().equals("")){
            	throw new ZMessManager("Porfavor introduzca la confirmacion de la clave");
            }
            
            if((clave.trim().equals(confimaClave.trim()))==false){
            	throw new ZMessManager("Las claves no coinciden");
            }

            psyPersonaDAO.save(entity);
            
            ResultadoCrearUsuarioDTO resultadoCrearUsuarioDTO = LoginRestServiceClient.crearUsuario(
            		apellidoUConsulta,
            		entity.getPsyEmpresa().getEmprCodigo().toString(),
            		clave,
            		entity.getEmail(), 
            		"1",
            		0L,
            		1L,
            		entity.getEmail(),
            		nombreUConsulta,
            		0L,
            		"3",
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
    					String[] recipiente = {entity.getEmail()};
    		    		String hostServer = hostServerMail;//"smtp.gmail.com -> para gmail"
    		    		String auth = TRUE;//"true -> cuando es gmail"
    		    		String userServer = userPusay;
    		    		String passServer = passwordPusay;
    		    		String esGmail = TRUE;//"true -> cuando es gmail"
    		    		
    		    		String subject = "Bienvenido a Pusay, "+nombreUConsulta;
    		    		String mensaje = "<br/><br/>"
    		    				+ "Usted ha quedado registrado en la empresa "+entity.getPsyEmpresa().getNombre()+".<br/><br/>"
    		    				+ "Recuerde los datos mas importantes: <br/><br/>"
    		    				+ "Nombre del Usuario Consulta: "+entity.getNombre()+".<br/>"
    		    				+ "Correo electronico: "+entity.getEmail()+".<br/>"
    		    				+ "Contraseña Asignada: "+clave
    		    				+ "<br/>"
    		    				+ "<br/>"
    		    				+ "Atentamente,<br/>"
    		    				+ "<br/>"
    		    				+ "<br/>"
    		    				+ "El equipo de Pusay.<br/>";
    		    		File attachment = null;
    					EnviarCorreo.sendMail(hostServer, auth, userServer, passServer, esGmail, recipiente, subject, mensaje, attachment, null);
					} catch (Exception e) {
						log.error("Envio de correo fallido: "+e.getMessage());
					}
    			}
    		}.start();

            log.debug("save PsyPersona successful");
        } catch (Exception e) {
            log.error("save PsyPersona failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePsyPersona(PsyPersona entity) throws Exception {
        log.debug("deleting PsyPersona instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Persona");
        }

        if (entity.getPersCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("Codigo");
        }

        List<PsyEmpresa> psyEmpresas = null;

        try {
            psyEmpresas = psyEmpresaDAO.findByProperty("psyPersona.persCodigo",
                    entity.getPersCodigo());

            if (Utilities.validationsList(psyEmpresas) == true) {
                throw new ZMessManager().new DeletingException("Empresa");
            }

            psyPersonaDAO.delete(entity);

            log.debug("delete PsyPersona successful");
        } catch (Exception e) {
            log.error("delete PsyPersona failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePsyPersona(PsyPersona entity) throws Exception {
        log.debug("updating PsyPersona instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Persona");
            }

            if (entity.getPsyEmpresa() == null) {
                throw new ZMessManager().new ForeignException("Empresa");
            }

            if (entity.getEmail() == null) {
                throw new ZMessManager().new EmptyFieldException("Email");
            }

            if ((entity.getEmail() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEmail(),
                        100) == false)) {
                throw new ZMessManager().new NotValidFormatException("Email");
            }
            
            if(Utilities.validateEmailAddress(entity.getEmail())==false){
            	throw new ZMessManager("La direccion de correo electronico no es valida");
            }


            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Estado Registro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Estado Registro");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("Nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("Nombre");
            }

           
            if ((entity.getTelefono() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getTelefono(), 15, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Telefono");
            }

            if (entity.getPsyEmpresa().getEmprCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Empresa");
            }

            psyPersonaDAO.update(entity);
            
            
            log.debug("update PsyPersona successful");
        } catch (Exception e) {
            log.error("update PsyPersona failed", e);
            throw e;
        } finally {
        }
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateUsuarioConsulta(PsyPersona entity, String clave, String confimaClave, String nombreUConsulta, String apellidoUConsulta) throws Exception {
        log.debug("updating PsyPersona instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Persona");
            }

            if (entity.getPsyEmpresa() == null) {
                throw new ZMessManager().new ForeignException("Empresa");
            }

            if (entity.getEmail() == null) {
                throw new ZMessManager().new EmptyFieldException("Email");
            }

            if ((entity.getEmail() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEmail(),
                        100) == false)) {
                throw new ZMessManager().new NotValidFormatException("Email");
            }
            
            if(Utilities.validateEmailAddress(entity.getEmail())==false){
            	throw new ZMessManager("La direccion de correo electronico no es valida");
            }


            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Estado Registro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Estado Registro");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("Nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("Nombre");
            }

           
            if ((entity.getTelefono() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getTelefono(), 15, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Telefono");
            }

            if (entity.getPsyEmpresa().getEmprCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Empresa");
            }
            
            if(clave == null){
            	throw new ZMessManager("Porfavor introduzca la clave");
            }
            
            if(confimaClave == null){
            	throw new ZMessManager("Porfavor introduzca la confirmacion de la clave");
            }
            
            if(clave.trim().equals("")){
            	throw new ZMessManager("Porfavor introduzca la clave");
            }
            
            if(confimaClave.trim().equals("")){
            	throw new ZMessManager("Porfavor introduzca la confirmacion de la clave");
            }
            
            if((clave.trim().equals(confimaClave.trim()))==false){
            	throw new ZMessManager("Las claves no coinciden");
            }
            
            psyPersonaDAO.update(entity);
            
            UsuarioDTO usuarioConsulta = LoginRestServiceClient.consultarUsuarioPorLogin(
            		entity.getEmail().toString(), "2", psyParametroLogic.getPsyParametro("urlServiciosRest").getValor());
            
            ResultadoCrearUsuarioDTO resultadoCrearUsuarioDTO = LoginRestServiceClient.actualizarUsuario(
            		apellidoUConsulta,
            		Long.parseLong(usuarioConsulta.getUsu_codigo()),
            		entity.getPsyEmpresa().getEmprCodigo().toString(),
            		clave,
            		entity.getEmail(), 
            		"1",
            		0L,
            		1L,
            		entity.getEmail(),
            		nombreUConsulta,
            		Long.parseLong(usuarioConsulta.getUsu_codigo()),
            		psyParametroLogic.getPsyParametro("urlServiciosRest").getValor());
            
            if(resultadoCrearUsuarioDTO.getExito()==false){
            	throw new ZMessManager(resultadoCrearUsuarioDTO.getMensaje());
            }


            

            log.debug("update PsyPersona successful");
        } catch (Exception e) {
            log.error("update PsyPersona failed", e);
            throw e;
        } finally {
        }
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePerfilUsuario(PsyPersona entity,String claveActual, String clave, String confimaClave, String nombreUConsulta, String apellidoUConsulta) throws Exception {
        log.debug("updating PsyPersona instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Persona");
            }

            if (entity.getPsyEmpresa() == null) {
                throw new ZMessManager().new ForeignException("Empresa");
            }

            if (entity.getEmail() == null) {
                throw new ZMessManager().new EmptyFieldException("Email");
            }

            if ((entity.getEmail() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEmail(),
                        100) == false)) {
                throw new ZMessManager().new NotValidFormatException("Email");
            }
            
            if(Utilities.validateEmailAddress(entity.getEmail())==false){
            	throw new ZMessManager("La direccion de correo electronico no es valida");
            }


            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Estado Registro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Estado Registro");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("Nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("Nombre");
            }

           
            if ((entity.getTelefono() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getTelefono(), 15, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Telefono");
            }

            if (entity.getPsyEmpresa().getEmprCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Empresa");
            }
            if(claveActual == null){
            	throw new ZMessManager("Porfavor introduzca la clave actual");
            }
            
            if(claveActual.trim().equals("")){
            	throw new ZMessManager("Porfavor introduzca la clave actual");
            }
            
           
            
            if(clave!=null && confimaClave!=null && !clave.trim().equals("") && !confimaClave.trim().equals("")){

            	if((clave.trim().equals(confimaClave.trim()))==false){
            		throw new ZMessManager("Las claves no coinciden");
            	}

            }
            
            psyPersonaDAO.update(entity);
            
            UsuarioDTO usuarioConsulta = LoginRestServiceClient.consultarUsuarioPorLogin(
            		entity.getEmail().toString(), "2", psyParametroLogic.getPsyParametro("urlServiciosRest").getValor());
            
            if(claveActual != null && !claveActual.trim().equals("")){
            	String claveActualMD5 = Utilities.convertirMD5(claveActual);
            	
            	if((claveActualMD5.trim().equals(usuarioConsulta.getContrasenaMD5())) == false){
            		throw new ZMessManager("Clave Actual Invalida");
            	}
            }
            
            String claveDefinitiva = (clave != null && !clave.trim().equals("")) ? confimaClave : claveActual;
            
            ResultadoCrearUsuarioDTO resultadoCrearUsuarioDTO = LoginRestServiceClient.actualizarUsuario(
            		apellidoUConsulta,
            		Long.parseLong(usuarioConsulta.getUsu_codigo()),
            		entity.getPsyEmpresa().getEmprCodigo().toString(),
            		claveDefinitiva,
            		entity.getEmail(), 
            		"1",
            		0L,
            		1L,
            		entity.getEmail(),
            		nombreUConsulta,
            		Long.parseLong(usuarioConsulta.getUsu_codigo()),
            		psyParametroLogic.getPsyParametro("urlServiciosRest").getValor());
            
            if(resultadoCrearUsuarioDTO.getExito()==false){
            	throw new ZMessManager(resultadoCrearUsuarioDTO.getMensaje());
            }


            

            log.debug("update PsyPersona successful");
        } catch (Exception e) {
            log.error("update PsyPersona failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PsyPersonaDTO> getDataPsyPersona(PsyEmpresa empresa) throws Exception {
        try {
        	Object[] variables = {"psyEmpresa.emprCodigo",false,empresa.getEmprCodigo(),"="};
        	
            List<PsyPersona> psyPersona = findByCriteria(variables, null, null);

            List<PsyPersonaDTO> psyPersonaDTO = new ArrayList<PsyPersonaDTO>();

            for (PsyPersona psyPersonaTmp : psyPersona) {
                PsyPersonaDTO psyPersonaDTO2 = new PsyPersonaDTO();

                psyPersonaDTO2.setPersCodigo(psyPersonaTmp.getPersCodigo());
                psyPersonaDTO2.setEmail((psyPersonaTmp.getEmail() != null)
                    ? psyPersonaTmp.getEmail() : null);
                psyPersonaDTO2.setEstadoRegistro((psyPersonaTmp.getEstadoRegistro().trim().equals("A"))
                		? "Activo" : "Inactivo");
                psyPersonaDTO2.setNombre((psyPersonaTmp.getNombre() != null)
                    ? psyPersonaTmp.getNombre() : null);
                psyPersonaDTO2.setTelefono((psyPersonaTmp.getTelefono() != null)
                    ? psyPersonaTmp.getTelefono() : null);
                psyPersonaDTO2.setEmprCodigo_PsyEmpresa((psyPersonaTmp.getPsyEmpresa()
                                                                      .getEmprCodigo() != null)
                    ? psyPersonaTmp.getPsyEmpresa().getEmprCodigo() : null);
                
                psyPersonaDTO2.setEmprNombre((psyPersonaTmp.getPsyEmpresa().getEmprCodigo() != null)
                    ? psyPersonaTmp.getPsyEmpresa().getNombre() : null);
                
                if((psyPersonaDTO2.getPersCodigo()==empresa.getPsyPersona().getPersCodigo())==false){
                	psyPersonaDTO.add(psyPersonaDTO2);
                }
            }

            return psyPersonaDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PsyPersona getPsyPersona(Long persCodigo) throws Exception {
        log.debug("getting PsyPersona instance");

        PsyPersona entity = null;

        try {
            entity = psyPersonaDAO.findById(persCodigo);
        } catch (Exception e) {
            log.error("get PsyPersona failed", e);
            throw new ZMessManager().new FindingException("PsyPersona");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<PsyPersona> findPagePsyPersona(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<PsyPersona> entity = null;

        try {
            entity = psyPersonaDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("PsyPersona Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPsyPersona() throws Exception {
        Long entity = null;

        try {
            entity = psyPersonaDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("PsyPersona Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<PsyPersona> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<PsyPersona> list = new ArrayList<PsyPersona>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = psyPersonaDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
