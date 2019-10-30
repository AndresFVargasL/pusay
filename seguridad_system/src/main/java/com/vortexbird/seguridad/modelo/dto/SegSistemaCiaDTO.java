package com.vortexbird.seguridad.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.ActionEvent;

import com.vortexbird.seguridad.modelo.SegSistemaCia;
import com.vortexbird.seguridad.presentation.backingBeans.SegSistemaCiaView;


/**
*
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class SegSistemaCiaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sicEstadoRegistro;
    private String ciaCodigo_SegCompania;
    private String ciaNombre_SegCompania;
    private String sisCodigo_SegSistema;
    private String sisNombre_SegSistema;
    private String usuCodigo_SegUsuario;
    private String sicCodigo;
    private boolean rowSelected = false;
    private SegSistemaCiaView segSistemaCiaView;
    private SegSistemaCia segSistemaCia;

    public SegSistemaCia getSegSistemaCia() {
        return segSistemaCia;
    }

    public String listener_update(ActionEvent e) {
        try {
            segSistemaCiaView.action_modifyWitDTO(((sicCodigo == null) ||
                sicCodigo.equals("")) ? null : new Long(sicCodigo),
                ((sicEstadoRegistro == null) || sicEstadoRegistro.equals(""))
                ? null : new String(sicEstadoRegistro),
                ((ciaCodigo_SegCompania == null) ||
                ciaCodigo_SegCompania.equals("")) ? null
                                                  : new Long(
                    ciaCodigo_SegCompania),
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
        }

        return "";
    }

    public void listener_cancel(ActionEvent e) {
        sicCodigo = segSistemaCia.getSicCodigo().toString();
        sicEstadoRegistro = (segSistemaCia.getSicEstadoRegistro() != null)
            ? segSistemaCia.getSicEstadoRegistro().toString() : null;
        ciaCodigo_SegCompania = (segSistemaCia.getSegCompania().getCiaCodigo() != null)
            ? segSistemaCia.getSegCompania().getCiaCodigo().toString() : null;
        sisCodigo_SegSistema = (segSistemaCia.getSegSistema().getSisCodigo() != null)
            ? segSistemaCia.getSegSistema().getSisCodigo().toString() : null;
        usuCodigo_SegUsuario = (segSistemaCia.getSegUsuario().getUsuCodigo() != null)
            ? segSistemaCia.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        sicCodigo = segSistemaCia.getSicCodigo().toString();
        sicEstadoRegistro = (segSistemaCia.getSicEstadoRegistro() != null)
            ? segSistemaCia.getSicEstadoRegistro().toString() : null;
        ciaCodigo_SegCompania = (segSistemaCia.getSegCompania().getCiaCodigo() != null)
            ? segSistemaCia.getSegCompania().getCiaCodigo().toString() : null;
        sisCodigo_SegSistema = (segSistemaCia.getSegSistema().getSisCodigo() != null)
            ? segSistemaCia.getSegSistema().getSisCodigo().toString() : null;
        usuCodigo_SegUsuario = (segSistemaCia.getSegUsuario().getUsuCodigo() != null)
            ? segSistemaCia.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSegSistemaCia(SegSistemaCia segSistemaCia) {
        this.segSistemaCia = segSistemaCia;
    }

    public SegSistemaCiaView getSegSistemaCiaView() {
        return segSistemaCiaView;
    }

    public void setSegSistemaCiaView(SegSistemaCiaView segSistemaCiaView) {
        this.segSistemaCiaView = segSistemaCiaView;
    }

    public String getSicEstadoRegistro() {
        return sicEstadoRegistro;
    }

    public void setSicEstadoRegistro(String sicEstadoRegistro) {
        this.sicEstadoRegistro = sicEstadoRegistro;
    }

    public String getCiaCodigo_SegCompania() {
        return ciaCodigo_SegCompania;
    }

    public void setCiaCodigo_SegCompania(String ciaCodigo_SegCompania) {
        this.ciaCodigo_SegCompania = ciaCodigo_SegCompania;
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

    public String getSicCodigo() {
        return sicCodigo;
    }

    public void setSicCodigo(String sicCodigo) {
        this.sicCodigo = sicCodigo;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }

	public String getCiaNombre_SegCompania() {
		return ciaNombre_SegCompania;
	}

	public void setCiaNombre_SegCompania(String ciaNombreSegCompania) {
		ciaNombre_SegCompania = ciaNombreSegCompania;
	}

	public String getSisNombre_SegSistema() {
		return sisNombre_SegSistema;
	}

	public void setSisNombre_SegSistema(String sisNombreSegSistema) {
		sisNombre_SegSistema = sisNombreSegSistema;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
