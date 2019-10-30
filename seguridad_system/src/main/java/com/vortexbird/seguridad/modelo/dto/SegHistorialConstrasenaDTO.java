package com.vortexbird.seguridad.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.ActionEvent;

import com.vortexbird.seguridad.modelo.SegHistorialConstrasena;
import com.vortexbird.seguridad.presentation.backingBeans.SegHistorialConstrasenaView;


/**
*
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class SegHistorialConstrasenaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String hcoConstrasena;
    private String hcoDetalleCambio;
    private String usuCodigo_SegUsuario;
    private String hcoCodigo;
    private Date hcoFechaCambio;
    private boolean rowSelected = false;
    private SegHistorialConstrasenaView segHistorialConstrasenaView;
    private SegHistorialConstrasena segHistorialConstrasena;

    public SegHistorialConstrasena getSegHistorialConstrasena() {
        return segHistorialConstrasena;
    }

    public String listener_update(ActionEvent e) {
        try {
            segHistorialConstrasenaView.action_modifyWitDTO(((hcoCodigo == null) ||
                hcoCodigo.equals("")) ? null : new Long(hcoCodigo),
                ((hcoConstrasena == null) || hcoConstrasena.equals("")) ? null
                                                                        : new String(
                    hcoConstrasena),
                ((hcoDetalleCambio == null) || hcoDetalleCambio.equals(""))
                ? null : new String(hcoDetalleCambio),
                ((hcoFechaCambio == null) || hcoFechaCambio.equals("")) ? null
                                                                        : hcoFechaCambio,
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
        hcoCodigo = segHistorialConstrasena.getHcoCodigo().toString();
        hcoConstrasena = (segHistorialConstrasena.getHcoConstrasena() != null)
            ? segHistorialConstrasena.getHcoConstrasena().toString() : null;
        hcoDetalleCambio = (segHistorialConstrasena.getHcoDetalleCambio() != null)
            ? segHistorialConstrasena.getHcoDetalleCambio().toString() : null;
        hcoFechaCambio = segHistorialConstrasena.getHcoFechaCambio();
        usuCodigo_SegUsuario = (segHistorialConstrasena.getSegUsuario()
                                                       .getUsuCodigo() != null)
            ? segHistorialConstrasena.getSegUsuario().getUsuCodigo().toString()
            : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        hcoCodigo = segHistorialConstrasena.getHcoCodigo().toString();
        hcoConstrasena = (segHistorialConstrasena.getHcoConstrasena() != null)
            ? segHistorialConstrasena.getHcoConstrasena().toString() : null;
        hcoDetalleCambio = (segHistorialConstrasena.getHcoDetalleCambio() != null)
            ? segHistorialConstrasena.getHcoDetalleCambio().toString() : null;
        hcoFechaCambio = segHistorialConstrasena.getHcoFechaCambio();
        usuCodigo_SegUsuario = (segHistorialConstrasena.getSegUsuario()
                                                       .getUsuCodigo() != null)
            ? segHistorialConstrasena.getSegUsuario().getUsuCodigo().toString()
            : null;
        rowSelected = !rowSelected;
    }

    public void setSegHistorialConstrasena(
        SegHistorialConstrasena segHistorialConstrasena) {
        this.segHistorialConstrasena = segHistorialConstrasena;
    }

    public SegHistorialConstrasenaView getSegHistorialConstrasenaView() {
        return segHistorialConstrasenaView;
    }

    public void setSegHistorialConstrasenaView(
        SegHistorialConstrasenaView segHistorialConstrasenaView) {
        this.segHistorialConstrasenaView = segHistorialConstrasenaView;
    }

    public String getHcoConstrasena() {
        return hcoConstrasena;
    }

    public void setHcoConstrasena(String hcoConstrasena) {
        this.hcoConstrasena = hcoConstrasena;
    }

    public String getHcoDetalleCambio() {
        return hcoDetalleCambio;
    }

    public void setHcoDetalleCambio(String hcoDetalleCambio) {
        this.hcoDetalleCambio = hcoDetalleCambio;
    }

    public String getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(String usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }

    public Date getHcoFechaCambio() {
        return hcoFechaCambio;
    }

    public void setHcoFechaCambio(Date hcoFechaCambio) {
        this.hcoFechaCambio = hcoFechaCambio;
    }

    public String getHcoCodigo() {
        return hcoCodigo;
    }

    public void setHcoCodigo(String hcoCodigo) {
        this.hcoCodigo = hcoCodigo;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
