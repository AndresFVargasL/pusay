package com.vortexbird.pusay.modelo;
// Generated Mar 20, 2015 10:23:26 AM by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

/**
 * PsyResponsable generated by hbm2java
 */
public class PsyResponsable  implements java.io.Serializable {


     private Long respCodigo;
     private PsyEmpresa psyEmpresa;
     private String nombre;
     private String email;
     private String estadoRegistro;
     private Set<PsyTarea> psyTareas = new HashSet<PsyTarea>(0);

    public PsyResponsable() {
    }

	
    public PsyResponsable(Long respCodigo, PsyEmpresa psyEmpresa, String nombre, String email) {
        this.respCodigo = respCodigo;
        this.psyEmpresa = psyEmpresa;
        this.nombre = nombre;
        this.email = email;
    }
    public PsyResponsable(Long respCodigo, PsyEmpresa psyEmpresa, String nombre, String email, String estadoRegistro, Set<PsyTarea> psyTareas) {
       this.respCodigo = respCodigo;
       this.psyEmpresa = psyEmpresa;
       this.nombre = nombre;
       this.email = email;
       this.estadoRegistro = estadoRegistro;
       this.psyTareas = psyTareas;
    }
   
    public Long getRespCodigo() {
        return this.respCodigo;
    }
    
    public void setRespCodigo(Long respCodigo) {
        this.respCodigo = respCodigo;
    }
    public PsyEmpresa getPsyEmpresa() {
        return this.psyEmpresa;
    }
    
    public void setPsyEmpresa(PsyEmpresa psyEmpresa) {
        this.psyEmpresa = psyEmpresa;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEstadoRegistro() {
        return this.estadoRegistro;
    }
    
    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public Set<PsyTarea> getPsyTareas() {
        return this.psyTareas;
    }
    
    public void setPsyTareas(Set<PsyTarea> psyTareas) {
        this.psyTareas = psyTareas;
    }




}


