package com.vortexbird.seguridad.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.ActionEvent;

import com.vortexbird.seguridad.modelo.SegPermiso;
import com.vortexbird.seguridad.presentation.backingBeans.SegPermisoView;


/**
*
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class SegPermisoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String perEstadoRegistro;
    private String ciaCodigo_SegCompania;
    private String gruCodigo_SegGrupoOpcion;
    private String opcCodigo_SegOpcion;
    private String rolCodigo_SegRol;
    private String sucCodigo_SegSucursal;
    private String usuCodigo_SegUsuario;
    private String perCodigo;
    private boolean rowSelected = false;
    private SegPermisoView segPermisoView;
    private SegPermiso segPermiso;
    
    private String perEstadoRegistroNombre;

    public SegPermiso getSegPermiso() {
        return segPermiso;
    }

    public String listener_update(ActionEvent e) {
        /*try {
            segPermisoView.action_modifyWitDTO(((perCodigo == null) ||
                perCodigo.equals("")) ? null : new Long(perCodigo),
                ((perEstadoRegistro == null) || perEstadoRegistro.equals(""))
                ? null : new String(perEstadoRegistro),
                ((ciaCodigo_SegCompania == null) ||
                ciaCodigo_SegCompania.equals("")) ? null
                                                  : new Long(
                    ciaCodigo_SegCompania),
                ((gruCodigo_SegGrupoOpcion == null) ||
                gruCodigo_SegGrupoOpcion.equals("")) ? null
                                                     : new Long(
                    gruCodigo_SegGrupoOpcion),
                ((opcCodigo_SegOpcion == null) ||
                opcCodigo_SegOpcion.equals("")) ? null
                                                : new Long(opcCodigo_SegOpcion),
                ((rolCodigo_SegRol == null) || rolCodigo_SegRol.equals(""))
                ? null : new Long(rolCodigo_SegRol),
                ((sucCodigo_SegSucursal == null) ||
                sucCodigo_SegSucursal.equals("")) ? null
                                                  : new Long(
                    sucCodigo_SegSucursal),
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
        /*perCodigo = segPermiso.getPerCodigo().toString();
        perEstadoRegistro = (segPermiso.getPerEstadoRegistro() != null)
            ? segPermiso.getPerEstadoRegistro().toString() : null;
        ciaCodigo_SegCompania = (segPermiso.getSegCompania().getCiaCodigo() != null)
            ? segPermiso.getSegCompania().getCiaCodigo().toString() : null;
        gruCodigo_SegGrupoOpcion = (segPermiso.getSegGrupoOpcion().getGruCodigo() != null)
            ? segPermiso.getSegGrupoOpcion().getGruCodigo().toString() : null;
        opcCodigo_SegOpcion = (segPermiso.getSegOpcion().getOpcCodigo() != null)
            ? segPermiso.getSegOpcion().getOpcCodigo().toString() : null;
        rolCodigo_SegRol = (segPermiso.getSegRol().getRolCodigo() != null)
            ? segPermiso.getSegRol().getRolCodigo().toString() : null;
        sucCodigo_SegSucursal = (segPermiso.getSegSucursal().getSucCodigo() != null)
            ? segPermiso.getSegSucursal().getSucCodigo().toString() : null;
        usuCodigo_SegUsuario = (segPermiso.getSegUsuario().getUsuCodigo() != null)
            ? segPermiso.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;*/
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        /*perCodigo = segPermiso.getPerCodigo().toString();
        perEstadoRegistro = (segPermiso.getPerEstadoRegistro() != null)
            ? segPermiso.getPerEstadoRegistro().toString() : null;
        ciaCodigo_SegCompania = (segPermiso.getSegCompania().getCiaCodigo() != null)
            ? segPermiso.getSegCompania().getCiaCodigo().toString() : null;
        gruCodigo_SegGrupoOpcion = (segPermiso.getSegGrupoOpcion().getGruCodigo() != null)
            ? segPermiso.getSegGrupoOpcion().getGruCodigo().toString() : null;
        opcCodigo_SegOpcion = (segPermiso.getSegOpcion().getOpcCodigo() != null)
            ? segPermiso.getSegOpcion().getOpcCodigo().toString() : null;
        rolCodigo_SegRol = (segPermiso.getSegRol().getRolCodigo() != null)
            ? segPermiso.getSegRol().getRolCodigo().toString() : null;
        sucCodigo_SegSucursal = (segPermiso.getSegSucursal().getSucCodigo() != null)
            ? segPermiso.getSegSucursal().getSucCodigo().toString() : null;
        usuCodigo_SegUsuario = (segPermiso.getSegUsuario().getUsuCodigo() != null)
            ? segPermiso.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;*/
    }

    public void setSegPermiso(SegPermiso segPermiso) {
        this.segPermiso = segPermiso;
    }

    public SegPermisoView getSegPermisoView() {
        return segPermisoView;
    }

    public void setSegPermisoView(SegPermisoView segPermisoView) {
        this.segPermisoView = segPermisoView;
    }

    public String getPerEstadoRegistro() {
        return perEstadoRegistro;
    }

    public void setPerEstadoRegistro(String perEstadoRegistro) {
        this.perEstadoRegistro = perEstadoRegistro;
    }

    public String getCiaCodigo_SegCompania() {
        return ciaCodigo_SegCompania;
    }

    public void setCiaCodigo_SegCompania(String ciaCodigo_SegCompania) {
        this.ciaCodigo_SegCompania = ciaCodigo_SegCompania;
    }

    public String getGruCodigo_SegGrupoOpcion() {
        return gruCodigo_SegGrupoOpcion;
    }

    public void setGruCodigo_SegGrupoOpcion(String gruCodigo_SegGrupoOpcion) {
        this.gruCodigo_SegGrupoOpcion = gruCodigo_SegGrupoOpcion;
    }

    public String getOpcCodigo_SegOpcion() {
        return opcCodigo_SegOpcion;
    }

    public void setOpcCodigo_SegOpcion(String opcCodigo_SegOpcion) {
        this.opcCodigo_SegOpcion = opcCodigo_SegOpcion;
    }

    public String getRolCodigo_SegRol() {
        return rolCodigo_SegRol;
    }

    public void setRolCodigo_SegRol(String rolCodigo_SegRol) {
        this.rolCodigo_SegRol = rolCodigo_SegRol;
    }

    public String getSucCodigo_SegSucursal() {
        return sucCodigo_SegSucursal;
    }

    public void setSucCodigo_SegSucursal(String sucCodigo_SegSucursal) {
        this.sucCodigo_SegSucursal = sucCodigo_SegSucursal;
    }

    public String getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(String usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }
    
    public String getPerEstadoRegistroNombre() {
    	
    	if(getPerEstadoRegistro().equals("1"))
    		perEstadoRegistroNombre = "Activo";
    	else if(getPerEstadoRegistro().equals("0"))
    		perEstadoRegistroNombre = "Inactivo";
    	else
    		perEstadoRegistroNombre = "";
    	
        return perEstadoRegistroNombre;
    }
    
    public void setPerEstadoRegistroNombre(String perEstadoRegistroNombre) {
        this.perEstadoRegistroNombre = perEstadoRegistroNombre;
    }

    public String getPerCodigo() {
        return perCodigo;
    }

    public void setPerCodigo(String perCodigo) {
        this.perCodigo = perCodigo;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
