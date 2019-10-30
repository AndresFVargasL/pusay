package com.vortexbird.seguridad.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.ActionEvent;

import com.vortexbird.seguridad.modelo.SegSistema;
import com.vortexbird.seguridad.presentation.backingBeans.SegSistemaView;


/**
*
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class SegSistemaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sisDescripcion;
    private String sisEstadoRegistro;
    private String sisNombre;
    private String usuCodigo_SegUsuario;
    private String sisCodigo;
    private boolean rowSelected = false;
    private SegSistemaView segSistemaView;
    private SegSistema segSistema;

    public SegSistema getSegSistema() {
        return segSistema;
    }

    public String listener_update(ActionEvent e) {
        try {
            segSistemaView.action_modifyWitDTO(((sisCodigo == null) ||
                sisCodigo.equals("")) ? null : new Long(sisCodigo),
                ((sisDescripcion == null) || sisDescripcion.equals("")) ? null
                                                                        : new String(
                    sisDescripcion),
                ((sisEstadoRegistro == null) || sisEstadoRegistro.equals(""))
                ? null : new String(sisEstadoRegistro),
                ((sisNombre == null) || sisNombre.equals("")) ? null
                                                              : new String(
                    sisNombre),
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
        sisCodigo = segSistema.getSisCodigo().toString();
        sisDescripcion = (segSistema.getSisDescripcion() != null)
            ? segSistema.getSisDescripcion().toString() : null;
        sisEstadoRegistro = (segSistema.getSisEstadoRegistro() != null)
            ? segSistema.getSisEstadoRegistro().toString() : null;
        sisNombre = (segSistema.getSisNombre() != null)
            ? segSistema.getSisNombre().toString() : null;
        usuCodigo_SegUsuario = (segSistema.getSegUsuario().getUsuCodigo() != null)
            ? segSistema.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        sisCodigo = segSistema.getSisCodigo().toString();
        sisDescripcion = (segSistema.getSisDescripcion() != null)
            ? segSistema.getSisDescripcion().toString() : null;
        sisEstadoRegistro = (segSistema.getSisEstadoRegistro() != null)
            ? segSistema.getSisEstadoRegistro().toString() : null;
        sisNombre = (segSistema.getSisNombre() != null)
            ? segSistema.getSisNombre().toString() : null;
        usuCodigo_SegUsuario = (segSistema.getSegUsuario().getUsuCodigo() != null)
            ? segSistema.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSegSistema(SegSistema segSistema) {
        this.segSistema = segSistema;
    }

    public SegSistemaView getSegSistemaView() {
        return segSistemaView;
    }

    public void setSegSistemaView(SegSistemaView segSistemaView) {
        this.segSistemaView = segSistemaView;
    }

    public String getSisDescripcion() {
        return sisDescripcion;
    }

    public void setSisDescripcion(String sisDescripcion) {
        this.sisDescripcion = sisDescripcion;
    }

    public String getSisEstadoRegistro() {
        return sisEstadoRegistro;
    }

    public void setSisEstadoRegistro(String sisEstadoRegistro) {
        this.sisEstadoRegistro = sisEstadoRegistro;
    }

    public String getSisNombre() {
        return sisNombre;
    }

    public void setSisNombre(String sisNombre) {
        this.sisNombre = sisNombre;
    }

    public String getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(String usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }

    public String getSisCodigo() {
        return sisCodigo;
    }

    public void setSisCodigo(String sisCodigo) {
        this.sisCodigo = sisCodigo;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
