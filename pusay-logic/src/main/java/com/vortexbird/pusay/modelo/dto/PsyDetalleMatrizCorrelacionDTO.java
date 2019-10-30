package com.vortexbird.pusay.modelo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vortexbird.pusay.modelo.PsyEstrategiaAmbiental;

public class PsyDetalleMatrizCorrelacionDTO implements Serializable{

	private static final long serialVersionUID = 801302012842183211L;
	
	
	 	private Long macoCodigo;
	    private Long esamCodigo;
	    private Long inamCodigo;
	    private Long obesCodigo;
	    private String nombreImpactoAmbiental;
	    private String descripcionImpactoAmbiental;
	    private String nombreObjetivoCorporativo;
	    private String descripcionObjetivoCorporativo;
	    private String colorFondo;
	    private String colorLetra;
	    private String nombreEstrategia;
	    private String descripcionEstrategia;
	    private boolean bloquear;
	    private String trabajar;
	    private String tipo;
	    private List<PsyEstrategiaAmbiental> estrategiasSeleccionadas = new ArrayList<PsyEstrategiaAmbiental>();
		private List<PsyEstrategiaAmbiental> estrategias = new ArrayList<PsyEstrategiaAmbiental>();
		private List<PsyEstrategiaAmbiental> estrategiasDataTable = new ArrayList<PsyEstrategiaAmbiental>();
	    
		public Long getMacoCodigo() {
			return macoCodigo;
		}
		public void setMacoCodigo(Long macoCodigo) {
			this.macoCodigo = macoCodigo;
		}
		public Long getEsamCodigo() {
			return esamCodigo;
		}
		public void setEsamCodigo(Long esamCodigo) {
			this.esamCodigo = esamCodigo;
		}
		public Long getInamCodigo() {
			return inamCodigo;
		}
		public void setInamCodigo(Long inamCodigo) {
			this.inamCodigo = inamCodigo;
		}
		public Long getObesCodigo() {
			return obesCodigo;
		}
		public void setObesCodigo(Long obesCodigo) {
			this.obesCodigo = obesCodigo;
		}
		public String getColorFondo() {
			return colorFondo;
		}
		public void setColorFondo(String colorFondo) {
			this.colorFondo = colorFondo;
		}
		public String getColorLetra() {
			return colorLetra;
		}
		public void setColorLetra(String colorLetra) {
			this.colorLetra = colorLetra;
		}
		public String getNombreImpactoAmbiental() {
			return nombreImpactoAmbiental;
		}
		public void setNombreImpactoAmbiental(String nombreImpactoAmbiental) {
			this.nombreImpactoAmbiental = nombreImpactoAmbiental;
		}
		public String getDescripcionImpactoAmbiental() {
			return descripcionImpactoAmbiental;
		}
		public void setDescripcionImpactoAmbiental(String descripcionImpactoAmbiental) {
			this.descripcionImpactoAmbiental = descripcionImpactoAmbiental;
		}
		public String getNombreObjetivoCorporativo() {
			return nombreObjetivoCorporativo;
		}
		public void setNombreObjetivoCorporativo(String nombreObjetivoCorporativo) {
			this.nombreObjetivoCorporativo = nombreObjetivoCorporativo;
		}
		public String getDescripcionObjetivoCorporativo() {
			return descripcionObjetivoCorporativo;
		}
		public void setDescripcionObjetivoCorporativo(
				String descripcionObjetivoCorporativo) {
			this.descripcionObjetivoCorporativo = descripcionObjetivoCorporativo;
		}
		public String getNombreEstrategia() {
			return nombreEstrategia;
		}
		public void setNombreEstrategia(String nombreEstrategia) {
			this.nombreEstrategia = nombreEstrategia;
		}
		public String getDescripcionEstrategia() {
			return descripcionEstrategia;
		}
		public void setDescripcionEstrategia(String descripcionEstrategia) {
			this.descripcionEstrategia = descripcionEstrategia;
		}
		public boolean isBloquear() {
			return bloquear;
		}
		public void setBloquear(boolean bloquear) {
			this.bloquear = bloquear;
		}
		public String getTrabajar() {
			return trabajar;
		}
		public void setTrabajar(String trabajar) {
			this.trabajar = trabajar;
		}
		public List<PsyEstrategiaAmbiental> getEstrategiasSeleccionadas() {
			return estrategiasSeleccionadas;
		}
		public void setEstrategiasSeleccionadas(
				List<PsyEstrategiaAmbiental> estrategiasSeleccionadas) {
			this.estrategiasSeleccionadas = estrategiasSeleccionadas;
		}
		public List<PsyEstrategiaAmbiental> getEstrategias() {
			return estrategias;
		}
		public void setEstrategias(List<PsyEstrategiaAmbiental> estrategias) {
			this.estrategias = estrategias;
		}
		public String getTipo() {
			return tipo;
		}
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		public List<PsyEstrategiaAmbiental> getEstrategiasDataTable() {
			return estrategiasDataTable;
		}
		public void setEstrategiasDataTable(
				List<PsyEstrategiaAmbiental> estrategiasDataTable) {
			this.estrategiasDataTable = estrategiasDataTable;
		}
	  
	
}

