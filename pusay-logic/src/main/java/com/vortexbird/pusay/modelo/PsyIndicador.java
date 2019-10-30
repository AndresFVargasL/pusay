package com.vortexbird.pusay.modelo;
// Generated 30-abr-2015 0:40:07 by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

/**
 * PsyIndicador generated by hbm2java
 */
public class PsyIndicador  implements java.io.Serializable {


     private Long codigo;
     private PsySubtema psySubtema;
     private PsyObjetivoAmbiental psyObjetivoAmbiental;
     private String descripcion;
     private String unidadMedida;
     private String tipoIndicador;
     private String estadoRegistro;
     private Set<PsyEvaluacionPeaIndicador> psyEvaluacionPeaIndicadors = new HashSet<PsyEvaluacionPeaIndicador>(0);

    public PsyIndicador() {
    }

	
    public PsyIndicador(Long codigo, PsySubtema psySubtema, PsyObjetivoAmbiental psyObjetivoAmbiental, String descripcion, String unidadMedida, String tipoIndicador, String estadoRegistro) {
        this.codigo = codigo;
        this.psySubtema = psySubtema;
        this.psyObjetivoAmbiental = psyObjetivoAmbiental;
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
        this.tipoIndicador = tipoIndicador;
        this.estadoRegistro = estadoRegistro;
    }
    public PsyIndicador(Long codigo, PsySubtema psySubtema, PsyObjetivoAmbiental psyObjetivoAmbiental, String descripcion, String unidadMedida, String tipoIndicador, String estadoRegistro, Set<PsyEvaluacionPeaIndicador> psyEvaluacionPeaIndicadors) {
       this.codigo = codigo;
       this.psySubtema = psySubtema;
       this.psyObjetivoAmbiental = psyObjetivoAmbiental;
       this.descripcion = descripcion;
       this.unidadMedida = unidadMedida;
       this.tipoIndicador = tipoIndicador;
       this.estadoRegistro = estadoRegistro;
       this.psyEvaluacionPeaIndicadors = psyEvaluacionPeaIndicadors;
    }
   
    public Long getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public PsySubtema getPsySubtema() {
        return this.psySubtema;
    }
    
    public void setPsySubtema(PsySubtema psySubtema) {
        this.psySubtema = psySubtema;
    }
    public PsyObjetivoAmbiental getPsyObjetivoAmbiental() {
        return this.psyObjetivoAmbiental;
    }
    
    public void setPsyObjetivoAmbiental(PsyObjetivoAmbiental psyObjetivoAmbiental) {
        this.psyObjetivoAmbiental = psyObjetivoAmbiental;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getUnidadMedida() {
        return this.unidadMedida;
    }
    
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    public String getTipoIndicador() {
        return this.tipoIndicador;
    }
    
    public void setTipoIndicador(String tipoIndicador) {
        this.tipoIndicador = tipoIndicador;
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




}


