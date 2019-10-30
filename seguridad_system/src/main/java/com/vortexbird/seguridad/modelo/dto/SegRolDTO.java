package com.vortexbird.seguridad.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.ActionEvent;

import com.vortexbird.seguridad.modelo.SegRol;
import com.vortexbird.seguridad.presentation.backingBeans.SegRolView;


/**
*
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class SegRolDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String rolDescripcion;
    private String rolDiasCaducidadPwd;
    private String rolEstadoRegistro;
    private String rolNombre;
    private String sisCodigo_SegSistema;
    private String usuCodigo_SegUsuario;
    private String rolCodigo;
    private boolean rowSelected = false;
    private SegRolView segRolView;
    private SegRol segRol;
    
    private String rolEstadoRegistroNombre;

    public SegRol getSegRol() {
        return segRol;
    }

    public String listener_update(ActionEvent e) {
        /*try {
            segRolView.action_modifyWitDTO(((rolCodigo == null) ||
                rolCodigo.equals("")) ? null : new Long(rolCodigo),
                ((rolDescripcion == null) || rolDescripcion.equals("")) ? null
                                                                        : new String(
                    rolDescripcion),
                ((rolDiasCaducidadPwd == null) ||
                rolDiasCaducidadPwd.equals("")) ? null
                                                : new Long(rolDiasCaducidadPwd),
                ((rolEstadoRegistro == null) || rolEstadoRegistro.equals(""))
                ? null : new String(rolEstadoRegistro),
                ((rolNombre == null) || rolNombre.equals("")) ? null
                                                              : new String(
                    rolNombre),
                ((sisCodigo_SegSistema == null) ||
                sisCodigo_SegSistema.equals("")) ? null
                                                 : new Long(
                    sisCodigo_SegSistema),
                ((usuCodigo_SegUsuario == null) ||
                usuCodigo_SegUsuario.equals("")) ? null
                                                 : new Long(
                    usuCodigo_SegUsuario));
            rowSelected = !rowSelected;
        } catch (Exception ex) {
            return "";
        }*/

        return "";
    }

    public void listener_cancel(ActionEvent e) {
        rolCodigo = segRol.getRolCodigo().toString();
        rolDescripcion = (segRol.getRolDescripcion() != null)
            ? segRol.getRolDescripcion().toString() : null;
        rolDiasCaducidadPwd = (segRol.getRolDiasCaducidadPwd() != null)
            ? segRol.getRolDiasCaducidadPwd().toString() : null;
        rolEstadoRegistro = (segRol.getRolEstadoRegistro() != null)
            ? segRol.getRolEstadoRegistro().toString() : null;
        rolNombre = (segRol.getRolNombre() != null)
            ? segRol.getRolNombre().toString() : null;
        sisCodigo_SegSistema = (segRol.getSegSistema().getSisCodigo() != null)
            ? segRol.getSegSistema().getSisCodigo().toString() : null;
        usuCodigo_SegUsuario = (segRol.getSegUsuario().getUsuCodigo() != null)
            ? segRol.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        rolCodigo = segRol.getRolCodigo().toString();
        rolDescripcion = (segRol.getRolDescripcion() != null)
            ? segRol.getRolDescripcion().toString() : null;
        rolDiasCaducidadPwd = (segRol.getRolDiasCaducidadPwd() != null)
            ? segRol.getRolDiasCaducidadPwd().toString() : null;
        rolEstadoRegistro = (segRol.getRolEstadoRegistro() != null)
            ? segRol.getRolEstadoRegistro().toString() : null;
        rolNombre = (segRol.getRolNombre() != null)
            ? segRol.getRolNombre().toString() : null;
        sisCodigo_SegSistema = (segRol.getSegSistema().getSisCodigo() != null)
            ? segRol.getSegSistema().getSisCodigo().toString() : null;
        usuCodigo_SegUsuario = (segRol.getSegUsuario().getUsuCodigo() != null)
            ? segRol.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSegRol(SegRol segRol) {
        this.segRol = segRol;
    }

    public SegRolView getSegRolView() {
        return segRolView;
    }

    public void setSegRolView(SegRolView segRolView) {
        this.segRolView = segRolView;
    }

    public String getRolDescripcion() {
        return rolDescripcion;
    }

    public void setRolDescripcion(String rolDescripcion) {
        this.rolDescripcion = rolDescripcion;
    }

    public String getRolDiasCaducidadPwd() {
        return rolDiasCaducidadPwd;
    }

    public void setRolDiasCaducidadPwd(String rolDiasCaducidadPwd) {
        this.rolDiasCaducidadPwd = rolDiasCaducidadPwd;
    }

    public String getRolEstadoRegistro() {
        return rolEstadoRegistro;
    }

    public void setRolEstadoRegistro(String rolEstadoRegistro) {
        this.rolEstadoRegistro = rolEstadoRegistro;
    }
    
    public String getRolEstadoRegistroNombre() {
    	
    	if(getRolEstadoRegistro().equals("1"))
    		rolEstadoRegistroNombre = "Activo";
    	else if(getRolEstadoRegistro().equals("0"))
    		rolEstadoRegistroNombre = "Inactivo";
    	else
    		rolEstadoRegistroNombre = "";
    	
        return rolEstadoRegistroNombre;
    }

    public void setRolEstadoRegistroNombre(String rolEstadoRegistroNombre) {
        this.rolEstadoRegistroNombre = rolEstadoRegistroNombre;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public String getSisCodigo_SegSistema() {
        return sisCodigo_SegSistema;
    }

    public void setSisCodigo_SegSistema(String sisCodigo_SegSistema) {
        this.sisCodigo_SegSistema = sisCodigo_SegSistema;
    }

    public String getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(String usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }

    public String getRolCodigo() {
        return rolCodigo;
    }

    public void setRolCodigo(String rolCodigo) {
        this.rolCodigo = rolCodigo;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
