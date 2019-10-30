package com.vortexbird.pusay.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public class PsyEmpresaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyEmpresaDTO.class);
    private String direccionPrincipal;
    private Long emprCodigo;
    private String estadoRegistro;
    private String nit;
    private String nombre;
    private Long telefonoPrincipal;
    private Long ciudCodigo_PsyCiudad;
    private Long persCodigo_PsyPersona;

    public String getDireccionPrincipal() {
        return direccionPrincipal;
    }

    public void setDireccionPrincipal(String direccionPrincipal) {
        this.direccionPrincipal = direccionPrincipal;
    }

    public Long getEmprCodigo() {
        return emprCodigo;
    }

    public void setEmprCodigo(Long emprCodigo) {
        this.emprCodigo = emprCodigo;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getTelefonoPrincipal() {
        return telefonoPrincipal;
    }

    public void setTelefonoPrincipal(Long telefonoPrincipal) {
        this.telefonoPrincipal = telefonoPrincipal;
    }

    public Long getCiudCodigo_PsyCiudad() {
        return ciudCodigo_PsyCiudad;
    }

    public void setCiudCodigo_PsyCiudad(Long ciudCodigo_PsyCiudad) {
        this.ciudCodigo_PsyCiudad = ciudCodigo_PsyCiudad;
    }

    public Long getPersCodigo_PsyPersona() {
        return persCodigo_PsyPersona;
    }

    public void setPersCodigo_PsyPersona(Long persCodigo_PsyPersona) {
        this.persCodigo_PsyPersona = persCodigo_PsyPersona;
    }
}
