package com.vortexbird.seguridad.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.ActionEvent;

import com.vortexbird.seguridad.modelo.SegGrupoOpcion;
import com.vortexbird.seguridad.presentation.backingBeans.SegGrupoOpcionView;


/**
*
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class SegGrupoOpcionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String gruDescripcion;
    private String gruEstadoRegistro;
    private String gruLlaveAcceso;
    private String gruNombre;
    private String gruCodigo_SegGrupoOpcion;
    private String segNombre_SegGrupoPadre;
    private String sisCodigo_SegSistema;
    private String sisNombre_SegSistema;
    private String usuCodigo_SegUsuario;
    private String gruCodigo;
    private boolean rowSelected = false;
    private SegGrupoOpcionView segGrupoOpcionView;
    private SegGrupoOpcion segGrupoOpcion;

    public SegGrupoOpcion getSegGrupoOpcion() {
        return segGrupoOpcion;
    }

    public String listener_update(ActionEvent e) {
        try {
            segGrupoOpcionView.action_modifyWitDTO(((gruCodigo == null) ||
                gruCodigo.equals("")) ? null : new Long(gruCodigo),
                ((gruDescripcion == null) || gruDescripcion.equals("")) ? null
                                                                        : new String(
                    gruDescripcion),
                ((gruEstadoRegistro == null) || gruEstadoRegistro.equals(""))
                ? null : new String(gruEstadoRegistro),
                ((gruLlaveAcceso == null) || gruLlaveAcceso.equals("")) ? null
                                                                        : new String(
                    gruLlaveAcceso),
                ((gruNombre == null) || gruNombre.equals("")) ? null
                                                              : new String(
                    gruNombre),
                ((gruCodigo_SegGrupoOpcion == null) ||
                gruCodigo_SegGrupoOpcion.equals("")) ? null
                                                     : new Long(
                    gruCodigo_SegGrupoOpcion),
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
        gruCodigo = segGrupoOpcion.getGruCodigo().toString();
        gruDescripcion = (segGrupoOpcion.getGruDescripcion() != null)
            ? segGrupoOpcion.getGruDescripcion().toString() : null;
        gruEstadoRegistro = (segGrupoOpcion.getGruEstadoRegistro() != null)
            ? segGrupoOpcion.getGruEstadoRegistro().toString() : null;
        gruLlaveAcceso = (segGrupoOpcion.getGruLlaveAcceso() != null)
            ? segGrupoOpcion.getGruLlaveAcceso().toString() : null;
        gruNombre = (segGrupoOpcion.getGruNombre() != null)
            ? segGrupoOpcion.getGruNombre().toString() : null;
        gruCodigo_SegGrupoOpcion = (segGrupoOpcion.getSegGrupoOpcion()
                                                  .getGruCodigo() != null)
            ? segGrupoOpcion.getSegGrupoOpcion().getGruCodigo().toString() : null;
        sisCodigo_SegSistema = (segGrupoOpcion.getSegSistema().getSisCodigo() != null)
            ? segGrupoOpcion.getSegSistema().getSisCodigo().toString() : null;
        usuCodigo_SegUsuario = (segGrupoOpcion.getSegUsuario().getUsuCodigo() != null)
            ? segGrupoOpcion.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        gruCodigo = segGrupoOpcion.getGruCodigo().toString();
        gruDescripcion = (segGrupoOpcion.getGruDescripcion() != null)
            ? segGrupoOpcion.getGruDescripcion().toString() : null;
        gruEstadoRegistro = (segGrupoOpcion.getGruEstadoRegistro() != null)
            ? segGrupoOpcion.getGruEstadoRegistro().toString() : null;
        gruLlaveAcceso = (segGrupoOpcion.getGruLlaveAcceso() != null)
            ? segGrupoOpcion.getGruLlaveAcceso().toString() : null;
        gruNombre = (segGrupoOpcion.getGruNombre() != null)
            ? segGrupoOpcion.getGruNombre().toString() : null;
        gruCodigo_SegGrupoOpcion = (segGrupoOpcion.getSegGrupoOpcion()
                                                  .getGruCodigo() != null)
            ? segGrupoOpcion.getSegGrupoOpcion().getGruCodigo().toString() : null;
        sisCodigo_SegSistema = (segGrupoOpcion.getSegSistema().getSisCodigo() != null)
            ? segGrupoOpcion.getSegSistema().getSisCodigo().toString() : null;
        usuCodigo_SegUsuario = (segGrupoOpcion.getSegUsuario().getUsuCodigo() != null)
            ? segGrupoOpcion.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSegGrupoOpcion(SegGrupoOpcion segGrupoOpcion) {
        this.segGrupoOpcion = segGrupoOpcion;
    }

    public SegGrupoOpcionView getSegGrupoOpcionView() {
        return segGrupoOpcionView;
    }

    public void setSegGrupoOpcionView(SegGrupoOpcionView segGrupoOpcionView) {
        this.segGrupoOpcionView = segGrupoOpcionView;
    }

    public String getGruDescripcion() {
        return gruDescripcion;
    }

    public void setGruDescripcion(String gruDescripcion) {
        this.gruDescripcion = gruDescripcion;
    }

    public String getGruEstadoRegistro() {
        return gruEstadoRegistro;
    }

    public void setGruEstadoRegistro(String gruEstadoRegistro) {
        this.gruEstadoRegistro = gruEstadoRegistro;
    }

    public String getGruLlaveAcceso() {
        return gruLlaveAcceso;
    }

    public void setGruLlaveAcceso(String gruLlaveAcceso) {
        this.gruLlaveAcceso = gruLlaveAcceso;
    }

    public String getGruNombre() {
        return gruNombre;
    }

    public void setGruNombre(String gruNombre) {
        this.gruNombre = gruNombre;
    }

    public String getGruCodigo_SegGrupoOpcion() {
        return gruCodigo_SegGrupoOpcion;
    }

    public void setGruCodigo_SegGrupoOpcion(String gruCodigo_SegGrupoOpcion) {
        this.gruCodigo_SegGrupoOpcion = gruCodigo_SegGrupoOpcion;
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

    public String getGruCodigo() {
        return gruCodigo;
    }

    public void setGruCodigo(String gruCodigo) {
        this.gruCodigo = gruCodigo;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }

	public String getSisNombre_SegSistema() {
		return sisNombre_SegSistema;
	}

	public void setSisNombre_SegSistema(String sisNombreSegSistema) {
		sisNombre_SegSistema = sisNombreSegSistema;
	}

	public String getSegNombre_SegGrupoPadre() {
		return segNombre_SegGrupoPadre;
	}

	public void setSegNombre_SegGrupoPadre(String segNombreSegGrupoPadre) {
		segNombre_SegGrupoPadre = segNombreSegGrupoPadre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
