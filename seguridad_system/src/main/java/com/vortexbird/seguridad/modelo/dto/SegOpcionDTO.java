package com.vortexbird.seguridad.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.ActionEvent;

import com.vortexbird.seguridad.modelo.SegOpcion;
import com.vortexbird.seguridad.presentation.backingBeans.SegOpcionView;


/**
*
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class SegOpcionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String opcDescripcion;
    private String opcEstadoRegistro;
    private String opcLlaveAcceso;
    private String opcNombre;
    private String gruCodigo_SegGrupoOpcion;
    private String gruNombre_SegGrupoOpcion;
    private String usuCodigo_SegUsuario;
    private String opcCodigo;
    private boolean rowSelected = false;
    private SegOpcionView segOpcionView;
    private SegOpcion segOpcion;
    private Boolean selectedCheck;

    public SegOpcion getSegOpcion() {
        return segOpcion;
    }

    public String listener_update(ActionEvent e) {
        try {
            segOpcionView.action_modifyWitDTO(((opcCodigo == null) ||
                opcCodigo.equals("")) ? null : new Long(opcCodigo),
                ((opcDescripcion == null) || opcDescripcion.equals("")) ? null
                                                                        : new String(
                    opcDescripcion),
                ((opcEstadoRegistro == null) || opcEstadoRegistro.equals(""))
                ? null : new String(opcEstadoRegistro),
                ((opcLlaveAcceso == null) || opcLlaveAcceso.equals("")) ? null
                                                                        : new String(
                    opcLlaveAcceso),
                ((opcNombre == null) || opcNombre.equals("")) ? null
                                                              : new String(
                    opcNombre),
                ((gruCodigo_SegGrupoOpcion == null) ||
                gruCodigo_SegGrupoOpcion.equals("")) ? null
                                                     : new Long(
                    gruCodigo_SegGrupoOpcion),
                ((usuCodigo_SegUsuario == null) ||
                usuCodigo_SegUsuario.equals("")) ? null
                                                 : new Long(
                    usuCodigo_SegUsuario));
            rowSelected = !rowSelected;
        } catch (Exception ex) {
            return "";
        }

        return "";
    }

    public void listener_cancel(ActionEvent e) {
        opcCodigo = segOpcion.getOpcCodigo().toString();
        opcDescripcion = (segOpcion.getOpcDescripcion() != null)
            ? segOpcion.getOpcDescripcion().toString() : null;
        opcEstadoRegistro = (segOpcion.getOpcEstadoRegistro() != null)
            ? segOpcion.getOpcEstadoRegistro().toString() : null;
        opcLlaveAcceso = (segOpcion.getOpcLlaveAcceso() != null)
            ? segOpcion.getOpcLlaveAcceso().toString() : null;
        opcNombre = (segOpcion.getOpcNombre() != null)
            ? segOpcion.getOpcNombre().toString() : null;
        gruCodigo_SegGrupoOpcion = (segOpcion.getSegGrupoOpcion().getGruCodigo() != null)
            ? segOpcion.getSegGrupoOpcion().getGruCodigo().toString() : null;
        usuCodigo_SegUsuario = (segOpcion.getSegUsuario().getUsuCodigo() != null)
            ? segOpcion.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        opcCodigo = segOpcion.getOpcCodigo().toString();
        opcDescripcion = (segOpcion.getOpcDescripcion() != null)
            ? segOpcion.getOpcDescripcion().toString() : null;
        opcEstadoRegistro = (segOpcion.getOpcEstadoRegistro() != null)
            ? segOpcion.getOpcEstadoRegistro().toString() : null;
        opcLlaveAcceso = (segOpcion.getOpcLlaveAcceso() != null)
            ? segOpcion.getOpcLlaveAcceso().toString() : null;
        opcNombre = (segOpcion.getOpcNombre() != null)
            ? segOpcion.getOpcNombre().toString() : null;
        gruCodigo_SegGrupoOpcion = (segOpcion.getSegGrupoOpcion().getGruCodigo() != null)
            ? segOpcion.getSegGrupoOpcion().getGruCodigo().toString() : null;
        usuCodigo_SegUsuario = (segOpcion.getSegUsuario().getUsuCodigo() != null)
            ? segOpcion.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSegOpcion(SegOpcion segOpcion) {
        this.segOpcion = segOpcion;
    }

    public SegOpcionView getSegOpcionView() {
        return segOpcionView;
    }

    public void setSegOpcionView(SegOpcionView segOpcionView) {
        this.segOpcionView = segOpcionView;
    }

    public String getOpcDescripcion() {
        return opcDescripcion;
    }

    public void setOpcDescripcion(String opcDescripcion) {
        this.opcDescripcion = opcDescripcion;
    }

    public String getOpcEstadoRegistro() {
        return opcEstadoRegistro;
    }

    public void setOpcEstadoRegistro(String opcEstadoRegistro) {
        this.opcEstadoRegistro = opcEstadoRegistro;
    }

    public String getOpcLlaveAcceso() {
        return opcLlaveAcceso;
    }

    public void setOpcLlaveAcceso(String opcLlaveAcceso) {
        this.opcLlaveAcceso = opcLlaveAcceso;
    }

    public String getOpcNombre() {
        return opcNombre;
    }

    public void setOpcNombre(String opcNombre) {
        this.opcNombre = opcNombre;
    }

    public String getGruCodigo_SegGrupoOpcion() {
        return gruCodigo_SegGrupoOpcion;
    }

    public void setGruCodigo_SegGrupoOpcion(String gruCodigo_SegGrupoOpcion) {
        this.gruCodigo_SegGrupoOpcion = gruCodigo_SegGrupoOpcion;
    }

    public String getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(String usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }

    public String getOpcCodigo() {
        return opcCodigo;
    }

    public void setOpcCodigo(String opcCodigo) {
        this.opcCodigo = opcCodigo;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }

	public String getGruNombre_SegGrupoOpcion() {
		return gruNombre_SegGrupoOpcion;
	}

	public void setGruNombre_SegGrupoOpcion(String gruNombreSegGrupoOpcion) {
		gruNombre_SegGrupoOpcion = gruNombreSegGrupoOpcion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getSelectedCheck() {
		return selectedCheck;
	}

	public void setSelectedCheck(Boolean selectedCheck) {
		this.selectedCheck = selectedCheck;
	}
    
}
