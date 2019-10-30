package com.vortexbird.pusay.modelo;
// Generated 19-jun-2015 12:15:03 by Hibernate Tools 4.0.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PsyPlanEstrategicoAmbiental generated by hbm2java
 */
public class PsyPlanEstrategicoAmbiental  implements java.io.Serializable {


     private Long codigo;
     private PsyPlanEstrategico psyPlanEstrategico;
     private String nombre;
     private Date fechaInico;
     private Date fechaFin;
     private String estadoPlan;
     private String estadoRegistro;
     private Set<PsyEvaluacionPeaIndicador> psyEvaluacionPeaIndicadors = new HashSet<PsyEvaluacionPeaIndicador>(0);
     private Set<PsyEvaluacionPeaObjetivo> psyEvaluacionPeaObjetivos = new HashSet<PsyEvaluacionPeaObjetivo>(0);

    public PsyPlanEstrategicoAmbiental() {
    }

	
    public PsyPlanEstrategicoAmbiental(Long codigo, PsyPlanEstrategico psyPlanEstrategico, String nombre, Date fechaInico, String estadoPlan, String estadoRegistro) {
        this.codigo = codigo;
        this.psyPlanEstrategico = psyPlanEstrategico;
        this.nombre = nombre;
        this.fechaInico = fechaInico;
        this.estadoPlan = estadoPlan;
        this.estadoRegistro = estadoRegistro;
    }
    public PsyPlanEstrategicoAmbiental(Long codigo, PsyPlanEstrategico psyPlanEstrategico, String nombre, Date fechaInico, Date fechaFin, String estadoPlan, String estadoRegistro, Set<PsyEvaluacionPeaIndicador> psyEvaluacionPeaIndicadors, Set<PsyEvaluacionPeaObjetivo> psyEvaluacionPeaObjetivos) {
       this.codigo = codigo;
       this.psyPlanEstrategico = psyPlanEstrategico;
       this.nombre = nombre;
       this.fechaInico = fechaInico;
       this.fechaFin = fechaFin;
       this.estadoPlan = estadoPlan;
       this.estadoRegistro = estadoRegistro;
       this.psyEvaluacionPeaIndicadors = psyEvaluacionPeaIndicadors;
       this.psyEvaluacionPeaObjetivos = psyEvaluacionPeaObjetivos;
    }
   
    public Long getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public PsyPlanEstrategico getPsyPlanEstrategico() {
        return this.psyPlanEstrategico;
    }
    
    public void setPsyPlanEstrategico(PsyPlanEstrategico psyPlanEstrategico) {
        this.psyPlanEstrategico = psyPlanEstrategico;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getFechaInico() {
        return this.fechaInico;
    }
    
    public void setFechaInico(Date fechaInico) {
        this.fechaInico = fechaInico;
    }
    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public String getEstadoPlan() {
        return this.estadoPlan;
    }
    
    public void setEstadoPlan(String estadoPlan) {
        this.estadoPlan = estadoPlan;
    }
    public String getEstadoRegistro() {
        return this.estadoRegistro;
    }
    
    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public Set<PsyEvaluacionPeaIndicador> getPsyEvaluacionPeaIndicadors() {
        return this.psyEvaluacionPeaIndicadors;
    }
    
    public void setPsyEvaluacionPeaIndicadors(Set<PsyEvaluacionPeaIndicador> psyEvaluacionPeaIndicadors) {
        this.psyEvaluacionPeaIndicadors = psyEvaluacionPeaIndicadors;
    }
    public Set<PsyEvaluacionPeaObjetivo> getPsyEvaluacionPeaObjetivos() {
        return this.psyEvaluacionPeaObjetivos;
    }
    
    public void setPsyEvaluacionPeaObjetivos(Set<PsyEvaluacionPeaObjetivo> psyEvaluacionPeaObjetivos) {
        this.psyEvaluacionPeaObjetivos = psyEvaluacionPeaObjetivos;
    }




}


