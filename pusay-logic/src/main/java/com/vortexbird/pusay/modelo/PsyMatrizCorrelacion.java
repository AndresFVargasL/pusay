package com.vortexbird.pusay.modelo;
// Generated Mar 20, 2015 10:23:26 AM by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

/**
 * PsyMatrizCorrelacion generated by hbm2java
 */
public class PsyMatrizCorrelacion  implements java.io.Serializable {


     private Long macoCodigo;
     private PsyEstrategiaAmbiental psyEstrategiaAmbiental;
     private PsyImpactoAmbiental psyImpactoAmbiental;
     private PsyObjetivoEstrategico psyObjetivoEstrategico;
     private String estadoRegistro;
     private Set<PsyDetalleMapaEstrategico> psyDetalleMapaEstrategicos = new HashSet<PsyDetalleMapaEstrategico>(0);

    public PsyMatrizCorrelacion() {
    }

	
    public PsyMatrizCorrelacion(Long macoCodigo, PsyEstrategiaAmbiental psyEstrategiaAmbiental, PsyImpactoAmbiental psyImpactoAmbiental, PsyObjetivoEstrategico psyObjetivoEstrategico, String estadoRegistro) {
        this.macoCodigo = macoCodigo;
        this.psyEstrategiaAmbiental = psyEstrategiaAmbiental;
        this.psyImpactoAmbiental = psyImpactoAmbiental;
        this.psyObjetivoEstrategico = psyObjetivoEstrategico;
        this.estadoRegistro = estadoRegistro;
    }
    public PsyMatrizCorrelacion(Long macoCodigo, PsyEstrategiaAmbiental psyEstrategiaAmbiental, PsyImpactoAmbiental psyImpactoAmbiental, PsyObjetivoEstrategico psyObjetivoEstrategico, String estadoRegistro, Set<PsyDetalleMapaEstrategico> psyDetalleMapaEstrategicos) {
       this.macoCodigo = macoCodigo;
       this.psyEstrategiaAmbiental = psyEstrategiaAmbiental;
       this.psyImpactoAmbiental = psyImpactoAmbiental;
       this.psyObjetivoEstrategico = psyObjetivoEstrategico;
       this.estadoRegistro = estadoRegistro;
       this.psyDetalleMapaEstrategicos = psyDetalleMapaEstrategicos;
    }
   
    public Long getMacoCodigo() {
        return this.macoCodigo;
    }
    
    public void setMacoCodigo(Long macoCodigo) {
        this.macoCodigo = macoCodigo;
    }
    public PsyEstrategiaAmbiental getPsyEstrategiaAmbiental() {
        return this.psyEstrategiaAmbiental;
    }
    
    public void setPsyEstrategiaAmbiental(PsyEstrategiaAmbiental psyEstrategiaAmbiental) {
        this.psyEstrategiaAmbiental = psyEstrategiaAmbiental;
    }
    public PsyImpactoAmbiental getPsyImpactoAmbiental() {
        return this.psyImpactoAmbiental;
    }
    
    public void setPsyImpactoAmbiental(PsyImpactoAmbiental psyImpactoAmbiental) {
        this.psyImpactoAmbiental = psyImpactoAmbiental;
    }
    public PsyObjetivoEstrategico getPsyObjetivoEstrategico() {
        return this.psyObjetivoEstrategico;
    }
    
    public void setPsyObjetivoEstrategico(PsyObjetivoEstrategico psyObjetivoEstrategico) {
        this.psyObjetivoEstrategico = psyObjetivoEstrategico;
    }
    public String getEstadoRegistro() {
        return this.estadoRegistro;
    }
    
    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public Set<PsyDetalleMapaEstrategico> getPsyDetalleMapaEstrategicos() {
        return this.psyDetalleMapaEstrategicos;
    }
    
    public void setPsyDetalleMapaEstrategicos(Set<PsyDetalleMapaEstrategico> psyDetalleMapaEstrategicos) {
        this.psyDetalleMapaEstrategicos = psyDetalleMapaEstrategicos;
    }




}


