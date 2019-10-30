package com.vortexbird.seguridad.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.ActionEvent;

import com.vortexbird.seguridad.modelo.SegUsuario;
import com.vortexbird.seguridad.presentation.backingBeans.SegUsuarioView;


/**
*
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class SegUsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String usuApellidos;
    private String usuCodigoInterno;
    private String usuCorreo;
    private String usuConstrasena;
    private String usuEstadoRegistro;
    private String usuIntentosFallidos;
    private Date usuUltmimaModificacionPass;
    private String usuLogin;
    private String usuNombres;
    private String usuCodigo_SegUsuario;
    private String usuCodigo;
    private boolean rowSelected = false;
    private SegUsuarioView segUsuarioView;
    private SegUsuario segUsuario;
    
    private String usuEstadoRegistroNombre;

    public SegUsuario getSegUsuario() {
        return segUsuario;
    }

    public String listener_update(ActionEvent e) {
        /*
         
            segUsuarioView.action_modifyWitDTO(((usuApellidos == null) ||
                usuApellidos.equals("")) ? null : new String(usuApellidos),
                ((usuCodigo == null) || usuCodigo.equals("")) ? null
                                                              : new Long(
                    usuCodigo),
                ((usuCodigoInterno == null) || usuCodigoInterno.equals(""))
                ? null : new String(usuCodigoInterno),
                ((usuConstrasena == null) || usuConstrasena.equals("")) ? null
                                                                        : new String(
                    usuConstrasena),
                ((usuEstadoRegistro == null) || usuEstadoRegistro.equals(""))
                ? null : new String(usuEstadoRegistro),
                ((usuLogin == null) || usuLogin.equals("")) ? null
                                                            : new String(
                    usuLogin),
                ((usuNombres == null) || usuNombres.equals("")) ? null
                                                                : new String(
                    usuNombres),
                ((usuCodigo_SegUsuario == null) ||
                usuCodigo_SegUsuario.equals("")) ? null
                                                 : new Long(
                    usuCodigo_SegUsuario));
            rowSelected = !rowSelected;
        } catch (Exception ex) {
            return "";
        }
		*/
        return "";
    }

    public void listener_cancel(ActionEvent e) {
        usuCodigo = segUsuario.getUsuCodigo().toString();
        usuApellidos = (segUsuario.getUsuApellidos() != null)
            ? segUsuario.getUsuApellidos().toString() : null;
        usuCodigoInterno = (segUsuario.getUsuCodigoInterno() != null)
            ? segUsuario.getUsuCodigoInterno().toString() : null;
        usuConstrasena = (segUsuario.getUsuConstrasena() != null)
            ? segUsuario.getUsuConstrasena().toString() : null;
        usuEstadoRegistro = (segUsuario.getUsuEstadoRegistro() != null)
            ? segUsuario.getUsuEstadoRegistro().toString() : null;
        usuLogin = (segUsuario.getUsuLogin() != null)
            ? segUsuario.getUsuLogin().toString() : null;
        usuNombres = (segUsuario.getUsuNombres() != null)
            ? segUsuario.getUsuNombres().toString() : null;
        usuCodigo_SegUsuario = (segUsuario.getSegUsuario().getUsuCodigo() != null)
            ? segUsuario.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        usuCodigo = segUsuario.getUsuCodigo().toString();
        usuApellidos = (segUsuario.getUsuApellidos() != null)
            ? segUsuario.getUsuApellidos().toString() : null;
        usuCodigoInterno = (segUsuario.getUsuCodigoInterno() != null)
            ? segUsuario.getUsuCodigoInterno().toString() : null;
        usuConstrasena = (segUsuario.getUsuConstrasena() != null)
            ? segUsuario.getUsuConstrasena().toString() : null;
        usuEstadoRegistro = (segUsuario.getUsuEstadoRegistro() != null)
            ? segUsuario.getUsuEstadoRegistro().toString() : null;
        usuLogin = (segUsuario.getUsuLogin() != null)
            ? segUsuario.getUsuLogin().toString() : null;
        usuNombres = (segUsuario.getUsuNombres() != null)
            ? segUsuario.getUsuNombres().toString() : null;
        usuCodigo_SegUsuario = (segUsuario.getSegUsuario().getUsuCodigo() != null)
            ? segUsuario.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSegUsuario(SegUsuario segUsuario) {
        this.segUsuario = segUsuario;
    }

    public SegUsuarioView getSegUsuarioView() {
        return segUsuarioView;
    }

    public void setSegUsuarioView(SegUsuarioView segUsuarioView) {
        this.segUsuarioView = segUsuarioView;
    }

    public String getUsuApellidos() {
        return usuApellidos;
    }

    public void setUsuApellidos(String usuApellidos) {
        this.usuApellidos = usuApellidos;
    }

    public String getUsuCodigoInterno() {
        return usuCodigoInterno;
    }

    public void setUsuCodigoInterno(String usuCodigoInterno) {
        this.usuCodigoInterno = usuCodigoInterno;
    }

    public String getUsuConstrasena() {
        return usuConstrasena;
    }

    public void setUsuConstrasena(String usuConstrasena) {
        this.usuConstrasena = usuConstrasena;
    }

    public String getUsuEstadoRegistro() {
        return usuEstadoRegistro;
    }

    public void setUsuEstadoRegistro(String usuEstadoRegistro) {
        this.usuEstadoRegistro = usuEstadoRegistro;
    }
    
    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }
    
    public String getUsuIntentosFallidos() {
        return usuIntentosFallidos;
    }

    public void setUsuIntentosFallidos(String usuIntentosFallidos) {
        this.usuIntentosFallidos = usuIntentosFallidos;
    }
    
    public Date getUsuUltmimaModificacionPass() {
        return usuUltmimaModificacionPass;
    }

    public void setUsuUltmimaModificacionPass(Date usuUltmimaModificacionPass) {
        this.usuUltmimaModificacionPass = usuUltmimaModificacionPass;
    }
    
    public String getUsuEstadoRegistroNombre() {
    	
    	if(getUsuEstadoRegistro().equals("1"))
    		usuEstadoRegistroNombre = "Activo";
    	else if(getUsuEstadoRegistro().equals("0"))
    		usuEstadoRegistroNombre = "Inactivo";
    	else
    		usuEstadoRegistroNombre = "";
    	
        return usuEstadoRegistroNombre;
    }

    public void setUsuEstadoRegistroNombre(String usuEstadoRegistroNombre) {
        this.usuEstadoRegistroNombre = usuEstadoRegistroNombre;
    }

    public String getUsuLogin() {
        return usuLogin;
    }

    public void setUsuLogin(String usuLogin) {
        this.usuLogin = usuLogin;
    }

    public String getUsuNombres() {
        return usuNombres;
    }

    public void setUsuNombres(String usuNombres) {
        this.usuNombres = usuNombres;
    }

    public String getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(String usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }

    public String getUsuCodigo() {
        return usuCodigo;
    }

    public void setUsuCodigo(String usuCodigo) {
        this.usuCodigo = usuCodigo;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }

	
    
}
