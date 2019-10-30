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
public class PsyResponsableDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PsyResponsableDTO.class);
    private String email;
    private String estadoRegistro;
    private String nombre;
    private Long respCodigo;
    private Long emprCodigo_PsyEmpresa;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getRespCodigo() {
        return respCodigo;
    }

    public void setRespCodigo(Long respCodigo) {
        this.respCodigo = respCodigo;
    }

    public Long getEmprCodigo_PsyEmpresa() {
        return emprCodigo_PsyEmpresa;
    }

    public void setEmprCodigo_PsyEmpresa(Long emprCodigo_PsyEmpresa) {
        this.emprCodigo_PsyEmpresa = emprCodigo_PsyEmpresa;
    }
}
