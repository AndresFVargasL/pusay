package com.vortexbird.seguridad.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.ActionEvent;

import com.vortexbird.seguridad.modelo.SegAuditoria;
import com.vortexbird.seguridad.presentation.backingBeans.SegAuditoriaView;


/**
*
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class SegAuditoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String campo;
    private String codRegistro;
    private String tabla;
    private String tipo;
    private String valorAnterior;
    private String valorNuevo;
    private String usuCodigo_SegUsuario;
    private String autCodigo;
    private Date fecha;
    private boolean rowSelected = false;
    private SegAuditoriaView segAuditoriaView;
    private SegAuditoria segAuditoria;

    public SegAuditoria getSegAuditoria() {
        return segAuditoria;
    }

    public String listener_update(ActionEvent e) {
        try {
            segAuditoriaView.action_modifyWitDTO(((autCodigo == null) ||
                autCodigo.equals("")) ? null : new Long(autCodigo),
                ((campo == null) || campo.equals("")) ? null : new String(campo),
                ((codRegistro == null) || codRegistro.equals("")) ? null
                                                                  : new Long(
                    codRegistro),
                ((fecha == null) || fecha.equals("")) ? null : fecha,
                ((tabla == null) || tabla.equals("")) ? null : new String(tabla),
                ((tipo == null) || tipo.equals("")) ? null : new String(tipo),
                ((valorAnterior == null) || valorAnterior.equals("")) ? null
                                                                      : new String(
                    valorAnterior),
                ((valorNuevo == null) || valorNuevo.equals("")) ? null
                                                                : new String(
                    valorNuevo),
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
        autCodigo = segAuditoria.getAutCodigo().toString();
        campo = (segAuditoria.getCampo() != null)
            ? segAuditoria.getCampo().toString() : null;
        codRegistro = (segAuditoria.getCodRegistro() != null)
            ? segAuditoria.getCodRegistro().toString() : null;
        fecha = segAuditoria.getFecha();
        tabla = (segAuditoria.getTabla() != null)
            ? segAuditoria.getTabla().toString() : null;
        tipo = (segAuditoria.getTipo() != null)
            ? segAuditoria.getTipo().toString() : null;
        valorAnterior = (segAuditoria.getValorAnterior() != null)
            ? segAuditoria.getValorAnterior().toString() : null;
        valorNuevo = (segAuditoria.getValorNuevo() != null)
            ? segAuditoria.getValorNuevo().toString() : null;
        usuCodigo_SegUsuario = (segAuditoria.getSegUsuario().getUsuCodigo() != null)
            ? segAuditoria.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        autCodigo = segAuditoria.getAutCodigo().toString();
        campo = (segAuditoria.getCampo() != null)
            ? segAuditoria.getCampo().toString() : null;
        codRegistro = (segAuditoria.getCodRegistro() != null)
            ? segAuditoria.getCodRegistro().toString() : null;
        fecha = segAuditoria.getFecha();
        tabla = (segAuditoria.getTabla() != null)
            ? segAuditoria.getTabla().toString() : null;
        tipo = (segAuditoria.getTipo() != null)
            ? segAuditoria.getTipo().toString() : null;
        valorAnterior = (segAuditoria.getValorAnterior() != null)
            ? segAuditoria.getValorAnterior().toString() : null;
        valorNuevo = (segAuditoria.getValorNuevo() != null)
            ? segAuditoria.getValorNuevo().toString() : null;
        usuCodigo_SegUsuario = (segAuditoria.getSegUsuario().getUsuCodigo() != null)
            ? segAuditoria.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSegAuditoria(SegAuditoria segAuditoria) {
        this.segAuditoria = segAuditoria;
    }

    public SegAuditoriaView getSegAuditoriaView() {
        return segAuditoriaView;
    }

    public void setSegAuditoriaView(SegAuditoriaView segAuditoriaView) {
        this.segAuditoriaView = segAuditoriaView;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getCodRegistro() {
        return codRegistro;
    }

    public void setCodRegistro(String codRegistro) {
        this.codRegistro = codRegistro;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(String valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public String getValorNuevo() {
        return valorNuevo;
    }

    public void setValorNuevo(String valorNuevo) {
        this.valorNuevo = valorNuevo;
    }

    public String getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(String usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAutCodigo() {
        return autCodigo;
    }

    public void setAutCodigo(String autCodigo) {
        this.autCodigo = autCodigo;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
