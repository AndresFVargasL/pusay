package com.vortexbird.seguridad.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.ActionEvent;

import com.vortexbird.seguridad.modelo.SegParametro;
import com.vortexbird.seguridad.presentation.backingBeans.SegParametroView;


/**
*
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class SegParametroDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String parEstadoRegistro;
    private String parNombre;
    private String parValorAlfanumerico;
    private String parValorNumerico;
    private String usuCodigo_SegUsuario;
    private String parCodigo;
    private Date parValorFecha;
    private boolean rowSelected = false;
    private SegParametroView segParametroView;
    private SegParametro segParametro;
    
    private String parEstadoRegistroNombre;

    public SegParametro getSegParametro() {
        return segParametro;
    }

    public String listener_update(ActionEvent e) {
        try {
            segParametroView.action_modifyWitDTO(((parCodigo == null) ||
                parCodigo.equals("")) ? null : new Long(parCodigo),
                ((parEstadoRegistro == null) || parEstadoRegistro.equals(""))
                ? null : new String(parEstadoRegistro),
                ((parNombre == null) || parNombre.equals("")) ? null
                                                              : new String(
                    parNombre),
                ((parValorAlfanumerico == null) ||
                parValorAlfanumerico.equals("")) ? null
                                                 : new String(
                    parValorAlfanumerico),
                ((parValorFecha == null) || parValorFecha.equals("")) ? null
                                                                      : parValorFecha,
                ((parValorNumerico == null) || parValorNumerico.equals(""))
                ? null : new Double(parValorNumerico),
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
        parCodigo = segParametro.getParCodigo().toString();
        parEstadoRegistro = (segParametro.getParEstadoRegistro() != null)
            ? segParametro.getParEstadoRegistro().toString() : null;
        parNombre = (segParametro.getParNombre() != null)
            ? segParametro.getParNombre().toString() : null;
        parValorAlfanumerico = (segParametro.getParValorAlfanumerico() != null)
            ? segParametro.getParValorAlfanumerico().toString() : null;
        parValorFecha = segParametro.getParValorFecha();
        parValorNumerico = (segParametro.getParValorNumerico() != null)
            ? segParametro.getParValorNumerico().toString() : null;
        usuCodigo_SegUsuario = (segParametro.getSegUsuario().getUsuCodigo() != null)
            ? segParametro.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        parCodigo = segParametro.getParCodigo().toString();
        parEstadoRegistro = (segParametro.getParEstadoRegistro() != null)
            ? segParametro.getParEstadoRegistro().toString() : null;
        parNombre = (segParametro.getParNombre() != null)
            ? segParametro.getParNombre().toString() : null;
        parValorAlfanumerico = (segParametro.getParValorAlfanumerico() != null)
            ? segParametro.getParValorAlfanumerico().toString() : null;
        parValorFecha = segParametro.getParValorFecha();
        parValorNumerico = (segParametro.getParValorNumerico() != null)
            ? segParametro.getParValorNumerico().toString() : null;
        usuCodigo_SegUsuario = (segParametro.getSegUsuario().getUsuCodigo() != null)
            ? segParametro.getSegUsuario().getUsuCodigo().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSegParametro(SegParametro segParametro) {
        this.segParametro = segParametro;
    }

    public SegParametroView getSegParametroView() {
        return segParametroView;
    }

    public void setSegParametroView(SegParametroView segParametroView) {
        this.segParametroView = segParametroView;
    }

    public String getParEstadoRegistro() {
        return parEstadoRegistro;
    }

    public void setParEstadoRegistro(String parEstadoRegistro) {
        this.parEstadoRegistro = parEstadoRegistro;
    }
    
    public String getParEstadoRegistroNombre() {
    	
    	if(getParEstadoRegistro().equals("1"))
    		parEstadoRegistroNombre = "Activo";
    	else if(getParEstadoRegistro().equals("0"))
    		parEstadoRegistroNombre = "Inactivo";
    	else
    		parEstadoRegistroNombre = "";
    	
        return parEstadoRegistroNombre;
    }

    public void setRolEstadoRegistroNombre(String rolEstadoRegistroNombre) {
        this.parEstadoRegistroNombre = parEstadoRegistroNombre;
    }

    public String getParNombre() {
        return parNombre;
    }

    public void setParNombre(String parNombre) {
        this.parNombre = parNombre;
    }

    public String getParValorAlfanumerico() {
        return parValorAlfanumerico;
    }

    public void setParValorAlfanumerico(String parValorAlfanumerico) {
        this.parValorAlfanumerico = parValorAlfanumerico;
    }

    public String getParValorNumerico() {
        return parValorNumerico;
    }

    public void setParValorNumerico(String parValorNumerico) {
        this.parValorNumerico = parValorNumerico;
    }

    public String getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(String usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }

    public Date getParValorFecha() {
        return parValorFecha;
    }

    public void setParValorFecha(Date parValorFecha) {
        this.parValorFecha = parValorFecha;
    }

    public String getParCodigo() {
        return parCodigo;
    }

    public void setParCodigo(String parCodigo) {
        this.parCodigo = parCodigo;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
