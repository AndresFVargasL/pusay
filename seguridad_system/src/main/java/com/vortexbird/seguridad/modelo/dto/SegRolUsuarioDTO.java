package com.vortexbird.seguridad.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.ActionEvent;

import com.vortexbird.seguridad.modelo.SegRolUsuario;
import com.vortexbird.seguridad.presentation.backingBeans.SegRolUsuarioView;


/**
*
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class SegRolUsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String rluEstadoRegistro;
    private String rolCodigo_SegRol;
    private String usuCodigo_SegUsuario;
    private String rluCodigo;
    private boolean rowSelected = false;
    private SegRolUsuarioView segRolUsuarioView;
    private SegRolUsuario segRolUsuario;
    
    private String rluEstadoRegistroNombre;

    public SegRolUsuario getSegRolUsuario() {
        return segRolUsuario;
    }

    public void setSegRolUsuario(SegRolUsuario segRolUsuario) {
        this.segRolUsuario = segRolUsuario;
    }

    public SegRolUsuarioView getSegRolUsuarioView() {
        return segRolUsuarioView;
    }

    public void setSegRolUsuarioView(SegRolUsuarioView segRolUsuarioView) {
        this.segRolUsuarioView = segRolUsuarioView;
    }

    public String getRluEstadoRegistro() {
        return rluEstadoRegistro;
    }

    public void setRluEstadoRegistro(String rluEstadoRegistro) {
        this.rluEstadoRegistro = rluEstadoRegistro;
    }

    public String getRolCodigo_SegRol() {
        return rolCodigo_SegRol;
    }

    public void setRolCodigo_SegRol(String rolCodigo_SegRol) {
        this.rolCodigo_SegRol = rolCodigo_SegRol;
    }

    public String getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(String usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }

    public String getRluCodigo() {
        return rluCodigo;
    }

    public void setRluCodigo(String rluCodigo) {
        this.rluCodigo = rluCodigo;
    }
    
    public String getRluEstadoRegistroNombre() {
    	
    	if(getRluEstadoRegistro().equals("1"))
    		rluEstadoRegistroNombre = "Activo";
    	else if(getRluEstadoRegistro().equals("0"))
    		rluEstadoRegistroNombre = "Inactivo";
    	else
    		rluEstadoRegistroNombre = "";
    	
        return rluEstadoRegistroNombre;
    }

    public void setRluEstadoRegistroNombre(String rluEstadoRegistroNombre) {
        this.rluEstadoRegistroNombre = rluEstadoRegistroNombre;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
