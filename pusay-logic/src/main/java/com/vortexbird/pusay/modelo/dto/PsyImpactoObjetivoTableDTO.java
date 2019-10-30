package com.vortexbird.pusay.modelo.dto;

import java.io.Serializable;

public class PsyImpactoObjetivoTableDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long imobCodigo;
	private Long imamCodigo;
	private String imamNombre;
	private Long obamCodigo;
	private String obamDescripcion;
	
	
	
	public PsyImpactoObjetivoTableDTO() {
		super();
	}



	public PsyImpactoObjetivoTableDTO(Long imobCodigo, Long imamCodigo,
			String imamNombre, Long obamCodigo, String obamDescripcion) {
		super();
		this.imobCodigo = imobCodigo;
		this.imamCodigo = imamCodigo;
		this.imamNombre = imamNombre;
		this.obamCodigo = obamCodigo;
		this.obamDescripcion = obamDescripcion;
	}



	public Long getImobCodigo() {
		return imobCodigo;
	}



	public void setImobCodigo(Long imobCodigo) {
		this.imobCodigo = imobCodigo;
	}



	public Long getImamCodigo() {
		return imamCodigo;
	}



	public void setImamCodigo(Long imamCodigo) {
		this.imamCodigo = imamCodigo;
	}



	public String getImamNombre() {
		return imamNombre;
	}



	public void setImamNombre(String imamNombre) {
		this.imamNombre = imamNombre;
	}



	public Long getObamCodigo() {
		return obamCodigo;
	}



	public void setObamCodigo(Long obamCodigo) {
		this.obamCodigo = obamCodigo;
	}



	public String getObamDescripcion() {
		return obamDescripcion;
	}



	public void setObamDescripcion(String obamDescripcion) {
		this.obamDescripcion = obamDescripcion;
	}
	
	
}
