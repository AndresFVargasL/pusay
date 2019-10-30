package com.vortexbird.pusay.presentation.backingBeans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vortexbird.pusay.presentation.utilities.FacesUtils;





@ManagedBean(name="loginView")
public class LoginView {

	
	 // properties
    private String userId;

    private String password;
    
    @ManagedProperty(value="#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;

  

    public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String login() {
    	
   	 try {
            Authentication request = new UsernamePasswordAuthenticationToken(this.getUserId(), this.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(result);
            
           
            FacesUtils.getHttpSession(true).setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            
        } catch (AuthenticationException e) {
           FacesUtils.addErrorMessage("No se ha encontrado un usuario con las credenciales ingresadas");
           return "error";
        }
   	 
       return "exito";
   }
 

}
