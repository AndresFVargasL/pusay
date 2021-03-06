package com.vortexbird.pusay.modelo;
// Generated Mar 20, 2015 10:23:26 AM by Hibernate Tools 4.0.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PsyObjetivoPlan generated by hbm2java
 */
public class PsyObjetivoPlan  implements java.io.Serializable {


     private Long obplCodigo;
     private PsyPlanEstrategico psyPlanEstrategico;
     private Date fechaInicio;
     private Date fechaFin;
     private String estadoObjetivoPlan;
     private String estadoRegistro;
     private Set<PsyDetalleObjetivoPlan> psyDetalleObjetivoPlans = new HashSet<PsyDetalleObjetivoPlan>(0);

    public PsyObjetivoPlan() {
    }

	
    public PsyObjetivoPlan(Long obplCodigo, PsyPlanEstrategico psyPlanEstrategico, Date fechaInicio, String estadoObjetivoPlan, String estadoRegistro) {
        this.obplCodigo = obplCodigo;
        this.psyPlanEstrategico = psyPlanEstrategico;
        this.fechaInicio = fechaInicio;
        this.estadoObjetivoPlan = estadoObjetivoPlan;
        this.estadoRegistro = estadoRegistro;
    }
    public PsyObjetivoPlan(Long obplCodigo, PsyPlanEstrategico psyPlanEstrategico, Date fechaInicio, Date fechaFin, String estadoObjetivoPlan, String estadoRegistro, Set<PsyDetalleObjetivoPlan> psyDetalleObjetivoPlans) {
       this.obplCodigo = obplCodigo;
       this.psyPlanEstrategico = psyPlanEstrategico;
       this.fechaInicio = fechaInicio;
       this.fechaFin = fechaFin;
       this.estadoObjetivoPlan = estadoObjetivoPlan;
       this.estadoRegistro = estadoRegistro;
       this.psyDetalleObjetivoPlans = psyDetalleObjetivoPlans;
    }
   
    public Long getObplCodigo() {
        return this.obplCodigo;
    }
    
    public void setObplCodigo(Long obplCodigo) {
        this.obplCodigo = obplCodigo;
    }
    public PsyPlanEstrategico getPsyPlanEstrategico() {
        return this.psyPlanEstrategico;
    }
    
    public void setPsyPlanEstrategico(PsyPlanEstrategico psyPlanEstrategico) {
        this.psyPlanEstrategico = psyPlanEstrategico;
    }
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public String getEstadoObjetivoPlan() {
        return this.estadoObjetivoPlan;
    }
    
    public void setEstadoObjetivoPlan(String estadoObjetivoPlan) {
        this.estadoObjetivoPlan = estadoObjetivoPlan;
    }
    public String getEstadoRegistro() {
        return this.estadoRegistro;
    }
    
    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public Set<PsyDetalleObjetivoPlan> getPsyDetalleObjetivoPlans() {
        return this.psyDetalleObjetivoPlans;
    }
    
    public void setPsyDetalleObjetivoPlans(Set<PsyDetalleObjetivoPlan> psyDetalleObjetivoPlans) {
        this.psyDetalleObjetivoPlans = psyDetalleObjetivoPlans;
    }




}


