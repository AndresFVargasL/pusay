package com.vortexbird.pusay.modelo;
// Generated 14-jul-2015 10:11:08 by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

/**
 * PsyObjetivoEstrategico generated by hbm2java
 */
public class PsyObjetivoEstrategico  implements java.io.Serializable {


     private Long obesCodigo;
     private String nombre;
     private String descripcion;
     private String estadoRegistro;
     private Set<PsyObjetivoImpacto> psyObjetivoImpactos = new HashSet<PsyObjetivoImpacto>(0);
     private Set<PsyDetalleObjetivoPlan> psyDetalleObjetivoPlans = new HashSet<PsyDetalleObjetivoPlan>(0);
     private Set<PsyMatrizCorrelacion> psyMatrizCorrelacions = new HashSet<PsyMatrizCorrelacion>(0);

    public PsyObjetivoEstrategico() {
    }

	
    public PsyObjetivoEstrategico(Long obesCodigo, String nombre, String descripcion, String estadoRegistro) {
        this.obesCodigo = obesCodigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estadoRegistro = estadoRegistro;
    }
    public PsyObjetivoEstrategico(Long obesCodigo, String nombre, String descripcion, String estadoRegistro, Set<PsyObjetivoImpacto> psyObjetivoImpactos, Set<PsyDetalleObjetivoPlan> psyDetalleObjetivoPlans, Set<PsyMatrizCorrelacion> psyMatrizCorrelacions) {
       this.obesCodigo = obesCodigo;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.estadoRegistro = estadoRegistro;
       this.psyObjetivoImpactos = psyObjetivoImpactos;
       this.psyDetalleObjetivoPlans = psyDetalleObjetivoPlans;
       this.psyMatrizCorrelacions = psyMatrizCorrelacions;
    }
   
    public Long getObesCodigo() {
        return this.obesCodigo;
    }
    
    public void setObesCodigo(Long obesCodigo) {
        this.obesCodigo = obesCodigo;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getEstadoRegistro() {
        return this.estadoRegistro;
    }
    
    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public Set<PsyObjetivoImpacto> getPsyObjetivoImpactos() {
        return this.psyObjetivoImpactos;
    }
    
    public void setPsyObjetivoImpactos(Set<PsyObjetivoImpacto> psyObjetivoImpactos) {
        this.psyObjetivoImpactos = psyObjetivoImpactos;
    }
    public Set<PsyDetalleObjetivoPlan> getPsyDetalleObjetivoPlans() {
        return this.psyDetalleObjetivoPlans;
    }
    
    public void setPsyDetalleObjetivoPlans(Set<PsyDetalleObjetivoPlan> psyDetalleObjetivoPlans) {
        this.psyDetalleObjetivoPlans = psyDetalleObjetivoPlans;
    }
    public Set<PsyMatrizCorrelacion> getPsyMatrizCorrelacions() {
        return this.psyMatrizCorrelacions;
    }
    
    public void setPsyMatrizCorrelacions(Set<PsyMatrizCorrelacion> psyMatrizCorrelacions) {
        this.psyMatrizCorrelacions = psyMatrizCorrelacions;
    }




}


