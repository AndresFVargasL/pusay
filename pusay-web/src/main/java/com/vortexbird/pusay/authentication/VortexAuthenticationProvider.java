package com.vortexbird.pusay.authentication;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;
import com.vortexbird.pusay.modelo.PsyEmpresa;
import com.vortexbird.pusay.presentation.businessDelegate.IBusinessDelegatorView;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;
import com.vortexbird.seguridad.rest.LoginRestServiceClient;
 
 
@Scope("singleton")
@Component("VortexAuthenticationProvider")
public class VortexAuthenticationProvider implements AuthenticationProvider {
	
	

     
    /**
     * Implementacion de la seguridad propia
     */
	@Autowired
	private IBusinessDelegatorView businessDelegatorView;
	private PsyEmpresa empresa;
	
    @Override
    public Authentication authenticate(Authentication authentication)throws AuthenticationException {
         
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        UsuarioDTO usuarioDTO=null;
		try {
			usuarioDTO = LoginRestServiceClient.autenticar(name, password,businessDelegatorView.getPsyParametro("urlServiciosRest").getValor());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        if(usuarioDTO != null && usuarioDTO.getUsu_correo() != null){
	        // Metodo para verificar la contraseña  - obtenido de el managedbean Login en el metodo action_enter()
	        if (!usuarioDTO.getEstado().equals("1") && !usuarioDTO.getEstado().equals("2")){
				return null;
			}
        }else{
        	throw new AuthenticationServiceException("No se ha encontrado un usuario con las credenciales ingresadas");
        }
        if (usuarioDTO!=null && usuarioDTO.getUsu_login()!=null && usuarioDTO.getUsu_login().equalsIgnoreCase(name)==true) {
            final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            grantedAuths.add(new SimpleGrantedAuthority("Admin"));
            final UserDetails principal = new User(name, password, grantedAuths);
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
            try {
			empresa = businessDelegatorView.getPsyEmpresa(Long.parseLong(usuarioDTO.getUsu_codigo_interno()));
            ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = servletRequestAttributes.getRequest().getSession();
            FacesUtils.setManagedBeanInSession("empresa", empresa);
            FacesUtils.setManagedBeanInSession("usuarioDTO", usuarioDTO);
            } catch (Exception e) {
				e.printStackTrace();
			}
            return auth;
        } else {
            return null;
        }
    }
 
    /**
     * Este metodo se llama primero cuando se autentica un usuario
     */
    @Override
    public boolean supports(Class<?> authentication) {
         return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
 
}