package com.vortexbird.seguridad.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.ActionEvent;

import com.vortexbird.seguridad.modelo.SegCambioPass;
import com.vortexbird.seguridad.presentation.backingBeans.SegCambioPassView;


/**
*
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class SegCambioPassDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String capEstado;
    private String capToken;
    private String usuCodigo_SegUsuario;
    private String capCodigo;
    private Date capFechaFin;
    private Date capFechaIni;
    private boolean rowSelected = false;
    private SegCambioPassView segCambioPassView;
    private SegCambioPass segCambioPass;

    public SegCambioPass getSegCambioPass() {
        return segCambioPass;
    }

    public String listener_update(ActionEvent e) {
        try {
            segCambioPassView.action_modifyWitDTO(((capCodigo == null) ||
                capCodigo.equals("")) ? null : new Long(capCodigo),
                ((capEstado == null) || capEstado.equals("")) ? null
                                                              : new String(
                    capEstado),
                ((capFechaFin == null) || capFechaFin.equals("")) ? null
                                                                  : capFechaFin,
                ((capFechaIni == null) || capFechaIni.equals("")) ? null
                                                                  : capFechaIni,
                ((capToken == null) || capToken.equals("")) ? null
                                                            : new String(
                    capToken),
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
        capCodigo = segCambioPass.getCapCodigo().toString();
        capEstado = (segCambioPass.getCapEstado() != null)
            ? segCambioPass.getCapEstado().toString() : null;
        capFechaFin = segCambioPass.getCapFechaFin();
        capFechaIni = segCambioPass.getCapFechaIni();
        capToken = (segCambioPass.getCapToken() != null)
            ? segCambioPass.getCapToken().toString() : null;
        usuCodigo_SegUsuario = (segCambioPass.getSegUsuario().getUsuCodigo() != null)
            ? segCambioPass.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        capCodigo = segCambioPass.getCapCodigo().toString();
        capEstado = (segCambioPass.getCapEstado() != null)
            ? segCambioPass.getCapEstado().toString() : null;
        capFechaFin = segCambioPass.getCapFechaFin();
        capFechaIni = segCambioPass.getCapFechaIni();
        capToken = (segCambioPass.getCapToken() != null)
            ? segCambioPass.getCapToken().toString() : null;
        usuCodigo_SegUsuario = (segCambioPass.getSegUsuario().getUsuCodigo() != null)
            ? segCambioPass.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSegCambioPass(SegCambioPass segCambioPass) {
        this.segCambioPass = segCambioPass;
    }

    public SegCambioPassView getSegCambioPassView() {
        return segCambioPassView;
    }

    public void setSegCambioPassView(SegCambioPassView segCambioPassView) {
        this.segCambioPassView = segCambioPassView;
    }

    public String getCapEstado() {
        return capEstado;
    }

    public void setCapEstado(String capEstado) {
        this.capEstado = capEstado;
    }

    public String getCapToken() {
        return capToken;
    }

    public void setCapToken(String capToken) {
        this.capToken = capToken;
    }

    public String getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(String usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }

    public Date getCapFechaFin() {
        return capFechaFin;
    }

    public void setCapFechaFin(Date capFechaFin) {
        this.capFechaFin = capFechaFin;
    }

    public Date getCapFechaIni() {
        return capFechaIni;
    }

    public void setCapFechaIni(Date capFechaIni) {
        this.capFechaIni = capFechaIni;
    }

    public String getCapCodigo() {
        return capCodigo;
    }

    public void setCapCodigo(String capCodigo) {
        this.capCodigo = capCodigo;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
