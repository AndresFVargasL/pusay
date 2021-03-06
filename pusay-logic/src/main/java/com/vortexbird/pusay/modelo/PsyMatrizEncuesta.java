package com.vortexbird.pusay.modelo;
// Generated Mar 20, 2015 10:23:26 AM by Hibernate Tools 4.0.0



/**
 * PsyMatrizEncuesta generated by hbm2java
 */
public class PsyMatrizEncuesta  implements java.io.Serializable {


     private Long maenCodigo;
     private PsyPlanEstrategico psyPlanEstrategico;
     private String codigoEncuesta;
     private String estadoRegistro;

    public PsyMatrizEncuesta() {
    }

    public PsyMatrizEncuesta(Long maenCodigo, PsyPlanEstrategico psyPlanEstrategico, String codigoEncuesta, String estadoRegistro) {
       this.maenCodigo = maenCodigo;
       this.psyPlanEstrategico = psyPlanEstrategico;
       this.codigoEncuesta = codigoEncuesta;
       this.estadoRegistro = estadoRegistro;
    }
   
    public Long getMaenCodigo() {
        return this.maenCodigo;
    }
    
    public void setMaenCodigo(Long maenCodigo) {
        this.maenCodigo = maenCodigo;
    }
    public PsyPlanEstrategico getPsyPlanEstrategico() {
        return this.psyPlanEstrategico;
    }
    
    public void setPsyPlanEstrategico(PsyPlanEstrategico psyPlanEstrategico) {
        this.psyPlanEstrategico = psyPlanEstrategico;
    }
    public String getCodigoEncuesta() {
        return this.codigoEncuesta;
    }
    
    public void setCodigoEncuesta(String codigoEncuesta) {
        this.codigoEncuesta = codigoEncuesta;
    }
    public String getEstadoRegistro() {
        return this.estadoRegistro;
    }
    
    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }




}


