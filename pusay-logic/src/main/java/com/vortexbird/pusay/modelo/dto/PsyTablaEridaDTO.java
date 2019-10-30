package com.vortexbird.pusay.modelo.dto;

import com.vortexbird.pusay.utilities.Utilities;

public class PsyTablaEridaDTO {
 
	private Long deerCodigo;
	private Long imamCodigo;
	private String impactoAmbiental;
	private double peso;
	private Long calificacion;
	private double valoracion;
	
	
	
	
	public Long getDeerCodigo() {
		return deerCodigo;
	}
	public void setDeerCodigo(Long deerCodigo) {
		this.deerCodigo = deerCodigo;
	}
	public Long getImamCodigo() {
		return imamCodigo;
	}
	public void setImamCodigo(Long imamCodigo) {
		this.imamCodigo = imamCodigo;
	}
	public String getImpactoAmbiental() {
		return impactoAmbiental;
	}
	public void setImpactoAmbiental(String impactoAmbiental) {
		this.impactoAmbiental = impactoAmbiental;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public Long getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(Long calificacion) {
		this.calificacion = calificacion;
	}
	public double getValoracion() {
		valoracion= Utilities.redondear(peso *0.01*calificacion, 2) ;
		return valoracion ;
	}
	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}
	
	
	
	
	
}
