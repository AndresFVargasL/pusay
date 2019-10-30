package com.vortexbird.pusay.modelo.dto;

import java.io.Serializable;

public class PsyEvaluarIndicadoresDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long indiCodigo;
	private Long obamCodigo;
	private String obamDescripcion;
	private Long subteCodigo;
	private String subTemaDescripcion;
	private String temaDescripcion;
	private String indiDescripcion;
	private String indiUnidadMedida;
	private String tipoIndicador;
	private String indiEstadoRegistro;
	private Long evaCodigo;
	private Long evaIndiCodigo;
	private Long peaCodigo;
	private String multiple;
	private Long periodo;
	private Double resultado;
	private Double meta;
	private Double historial;
	private Double norma;
	private Double sectorial;
	private String evaEstadoRegistro;
	private String colorEvaluacion;
	private String colorLetra;
	
	
	
	public PsyEvaluarIndicadoresDTO() {
		super();
	}
	
	
	
	public PsyEvaluarIndicadoresDTO(Long indiCodigo, Long obamCodigo,
			String obamDescripcion, Long subteCodigo,
			String subTemaDescripcion, String temaDescripcion,
			String indiDescripcion, String indiUnidadMedida,
			String tipoIndicador, String indiEstadoRegistro, Long evaCodigo,
			Long evaIndiCodigo, Long peaCodigo, String multiple, Long periodo,
			Double resultado, Double meta, Double historial, Double norma,
			Double sectorial, String evaEstadoRegistro, String colorEvaluacion) {
		super();
		this.indiCodigo = indiCodigo;
		this.obamCodigo = obamCodigo;
		this.obamDescripcion = obamDescripcion;
		this.subteCodigo = subteCodigo;
		this.subTemaDescripcion = subTemaDescripcion;
		this.temaDescripcion = temaDescripcion;
		this.indiDescripcion = indiDescripcion;
		this.indiUnidadMedida = indiUnidadMedida;
		this.tipoIndicador = tipoIndicador;
		this.indiEstadoRegistro = indiEstadoRegistro;
		this.evaCodigo = evaCodigo;
		this.evaIndiCodigo = evaIndiCodigo;
		this.peaCodigo = peaCodigo;
		this.multiple = multiple;
		this.periodo = periodo;
		this.resultado = resultado;
		this.meta = meta;
		this.historial = historial;
		this.norma = norma;
		this.sectorial = sectorial;
		this.evaEstadoRegistro = evaEstadoRegistro;
		this.colorEvaluacion = colorEvaluacion;
	}



	public Long getIndiCodigo() {
		return indiCodigo;
	}
	public void setIndiCodigo(Long indiCodigo) {
		this.indiCodigo = indiCodigo;
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
	public Long getSubteCodigo() {
		return subteCodigo;
	}
	public void setSubteCodigo(Long subteCodigo) {
		this.subteCodigo = subteCodigo;
	}
	public String getSubTemaDescripcion() {
		return subTemaDescripcion;
	}
	public void setSubTemaDescripcion(String subTemaDescripcion) {
		this.subTemaDescripcion = subTemaDescripcion;
	}
	public String getTemaDescripcion() {
		return temaDescripcion;
	}
	public void setTemaDescripcion(String temaDescripcion) {
		this.temaDescripcion = temaDescripcion;
	}
	public String getIndiDescripcion() {
		return indiDescripcion;
	}
	public void setIndiDescripcion(String indiDescripcion) {
		this.indiDescripcion = indiDescripcion;
	}
	public String getIndiUnidadMedida() {
		return indiUnidadMedida;
	}
	public void setIndiUnidadMedida(String indiUnidadMedida) {
		this.indiUnidadMedida = indiUnidadMedida;
	}
	public String getTipoIndicador() {
		return tipoIndicador;
	}
	public void setTipoIndicador(String tipoIndicador) {
		this.tipoIndicador = tipoIndicador;
	}
	public String getIndiEstadoRegistro() {
		return indiEstadoRegistro;
	}
	public void setIndiEstadoRegistro(String indiEstadoRegistro) {
		this.indiEstadoRegistro = indiEstadoRegistro;
	}
	public Long getEvaCodigo() {
		return evaCodigo;
	}
	public void setEvaCodigo(Long evaCodigo) {
		this.evaCodigo = evaCodigo;
	}
	public Long getEvaIndiCodigo() {
		return evaIndiCodigo;
	}
	public void setEvaIndiCodigo(Long evaIndiCodigo) {
		this.evaIndiCodigo = evaIndiCodigo;
	}
	public Long getPeaCodigo() {
		return peaCodigo;
	}
	public void setPeaCodigo(Long peaCodigo) {
		this.peaCodigo = peaCodigo;
	}
	public String getMultiple() {
		return multiple;
	}
	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}
	public Long getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Long periodo) {
		this.periodo = periodo;
	}
	public Double getResultado() {
		return resultado;
	}
	public void setResultado(Double resultado) {
		this.resultado = resultado;
	}
	public Double getMeta() {
		return meta;
	}
	public void setMeta(Double meta) {
		this.meta = meta;
	}
	public Double getHistorial() {
		return historial;
	}
	public void setHistorial(Double historial) {
		this.historial = historial;
	}
	public Double getNorma() {
		return norma;
	}
	public void setNorma(Double norma) {
		this.norma = norma;
	}
	public Double getSectorial() {
		return sectorial;
	}
	public void setSectorial(Double sectorial) {
		this.sectorial = sectorial;
	}
	public String getEvaEstadoRegistro() {
		return evaEstadoRegistro;
	}
	public void setEvaEstadoRegistro(String evaEstadoRegistro) {
		this.evaEstadoRegistro = evaEstadoRegistro;
	}



	public String getColorEvaluacion() {
		return colorEvaluacion;
	}



	public void setColorEvaluacion(String colorEvaluacion) {
		this.colorEvaluacion = colorEvaluacion;
	}



	public String getColorLetra() {
		return colorLetra;
	}



	public void setColorLetra(String colorLetra) {
		this.colorLetra = colorLetra;
	}
	
	

}
