package com.vortexbird.pusay.modelo.control;

import com.vortexbird.pusay.modelo.PsyCiudad;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.modelo.PsyPais;
import com.vortexbird.pusay.modelo.PsyPersona;
import com.vortexbird.pusay.modelo.PsyProvincia;
import com.vortexbird.pusay.modelo.dto.PsyCiudadDTO;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IRegistrarLogic {

    public void registrar(PsyPersona persona, PsyEmpresa empresa, String nombreResponsable, String apellidoResponsable, String contrasena, String confirmaContrasena) throws Exception;
    
    public void recuperarClave(String correo, UsuarioDTO usuarioDTO) throws Exception;
    
    public void actionRecuperarClave(UsuarioDTO usuarioConsulta, String token, String correoRecuperacion, String clave, String claveRecuperacion) throws Exception;
    
}
