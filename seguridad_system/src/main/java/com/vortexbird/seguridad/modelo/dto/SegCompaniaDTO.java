package com.vortexbird.seguridad.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.ActionEvent;

import com.vortexbird.seguridad.modelo.SegCompania;
import com.vortexbird.seguridad.presentation.backingBeans.SegCompaniaView;


/**
*
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class SegCompaniaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ciaCodigoInterno;
    private String ciaEstadoRegistro;
    private String ciaNombre;
    private String usuCodigo_SegUsuario;
    private String ciaCodigo;
    private boolean rowSelected = false;
    private SegCompaniaView segCompaniaView;
    private SegCompania segCompania;

    public SegCompania getSegCompania() {
        return segCompania;
    }

    public String listener_update(ActionEvent e) {
        try {
            segCompaniaView.action_modifyWitDTO(((ciaCodigo == null) ||
                ciaCodigo.equals("")) ? null : new Long(ciaCodigo),
                ((ciaCodigoInterno == null) || ciaCodigoInterno.equals(""))
                ? null : new String(ciaCodigoInterno),
                ((ciaEstadoRegistro == null) || ciaEstadoRegistro.equals(""))
                ? null : new String(ciaEstadoRegistro),
                ((ciaNombre == null) || ciaNombre.equals("")) ? null
                                                              : new String(
                    ciaNombre),
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
        ciaCodigo = segCompania.getCiaCodigo().toString();
        ciaCodigoInterno = (segCompania.getCiaCodigoInterno() != null)
            ? segCompania.getCiaCodigoInterno().toString() : null;
        ciaEstadoRegistro = (segCompania.getCiaEstadoRegistro() != null)
            ? segCompania.getCiaEstadoRegistro().toString() : null;
        ciaNombre = (segCompania.getCiaNombre() != null)
            ? segCompania.getCiaNombre().toString() : null;
        usuCodigo_SegUsuario = (segCompania.getSegUsuario().getUsuCodigo() != null)
            ? segCompania.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        ciaCodigo = segCompania.getCiaCodigo().toString();
        ciaCodigoInterno = (segCompania.getCiaCodigoInterno() != null)
            ? segCompania.getCiaCodigoInterno().toString() : null;
        ciaEstadoRegistro = (segCompania.getCiaEstadoRegistro() != null)
            ? segCompania.getCiaEstadoRegistro().toString() : null;
        ciaNombre = (segCompania.getCiaNombre() != null)
            ? segCompania.getCiaNombre().toString() : null;
        usuCodigo_SegUsuario = (segCompania.getSegUsuario().getUsuCodigo() != null)
            ? segCompania.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSegCompania(SegCompania segCompania) {
        this.segCompania = segCompania;
    }

    public SegCompaniaView getSegCompaniaView() {
        return segCompaniaView;
    }

    public void setSegCompaniaView(SegCompaniaView segCompaniaView) {
        this.segCompaniaView = segCompaniaView;
    }

    public String getCiaCodigoInterno() {
        return ciaCodigoInterno;
    }

    public void setCiaCodigoInterno(String ciaCodigoInterno) {
        this.ciaCodigoInterno = ciaCodigoInterno;
    }

    public String getCiaEstadoRegistro() {
        return ciaEstadoRegistro;
    }

    public void setCiaEstadoRegistro(String ciaEstadoRegistro) {
        this.ciaEstadoRegistro = ciaEstadoRegistro;
    }

    public String getCiaNombre() {
        return ciaNombre;
    }

    public void setCiaNombre(String ciaNombre) {
        this.ciaNombre = ciaNombre;
    }

    public String getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(String usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }

    public String getCiaCodigo() {
        return ciaCodigo;
    }

    public void setCiaCodigo(String ciaCodigo) {
        this.ciaCodigo = ciaCodigo;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
