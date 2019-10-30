package com.vortexbird.pusay.cuestionarios.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")
public class CuePregunta implements java.io.Serializable {

	private Long consecutivo = null;
	private CueCategoria cueCategoria = null;
	private String enunciado = null;
	private String condicion = null;
	private Long orden = null;
	private Long puntaje = null;
	private String labelAmpliacion = null;
	private Long nroRespuestas = null;
	private Long estado = null;
	private Set<CueNavegacion> cueNavegacionsForPreguntaDestino = new HashSet<CueNavegacion>(0);
	private Set<CueNavegacion> cueNavegacionsForPreguntaOrigen = new HashSet<CueNavegacion>(0);
	private Set<CueOpcion> cueOpcions = new HashSet<CueOpcion>(0);

	// Variables para poder obtener las respuetas
	private List<CueOpcion> listOpciones;
	private Long selectedOpcion;
	private List<Long> selectedOptions;
	private boolean requiereAmpliacion;
	private String respuestaAmpliacion;

	public CuePregunta() {
		this.cueCategoria = new CueCategoria();
	}

	public CuePregunta(Long consecutivo, CueCategoria cueCategoria, String enunciado, String condicion, Long orden,
			Long nroRespuestas, Long estado) {
		this.consecutivo = consecutivo;
		this.cueCategoria = cueCategoria;
		this.enunciado = enunciado;
		this.condicion = condicion;
		this.orden = orden;
		this.nroRespuestas = nroRespuestas;
		this.estado = estado;
	}

	public CuePregunta(Long consecutivo, CueCategoria cueCategoria, String enunciado, String condicion, Long orden,
			Long puntaje, String labelAmpliacion, Long nroRespuestas, Long estado,
			Set<CueNavegacion> cueNavegacionsForPreguntaDestino, Set<CueNavegacion> cueNavegacionsForPreguntaOrigen,
			Set<CueOpcion> cueOpcions) {
		this.consecutivo = consecutivo;
		this.cueCategoria = cueCategoria;
		this.enunciado = enunciado;
		this.condicion = condicion;
		this.orden = orden;
		this.puntaje = puntaje;
		this.labelAmpliacion = labelAmpliacion;
		this.nroRespuestas = nroRespuestas;
		this.estado = estado;
		this.cueNavegacionsForPreguntaDestino = cueNavegacionsForPreguntaDestino;
		this.cueNavegacionsForPreguntaOrigen = cueNavegacionsForPreguntaOrigen;
		this.cueOpcions = cueOpcions;
	}

	public Long getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public CueCategoria getCueCategoria() {
		return this.cueCategoria;
	}

	public void setCueCategoria(CueCategoria cueCategoria) {
		this.cueCategoria = cueCategoria;
	}

	public String getEnunciado() {
		return this.enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String getCondicion() {
		return this.condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public Long getOrden() {
		return this.orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

	public Long getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(Long puntaje) {
		this.puntaje = puntaje;
	}

	public String getLabelAmpliacion() {
		return this.labelAmpliacion;
	}

	public void setLabelAmpliacion(String labelAmpliacion) {
		this.labelAmpliacion = labelAmpliacion;
	}

	public Long getNroRespuestas() {
		return this.nroRespuestas;
	}

	public void setNroRespuestas(Long nroRespuestas) {
		this.nroRespuestas = nroRespuestas;
	}

	public Long getEstado() {
		return this.estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public Set<CueNavegacion> getCueNavegacionsForPreguntaDestino() {
		return this.cueNavegacionsForPreguntaDestino;
	}

	public void setCueNavegacionsForPreguntaDestino(Set<CueNavegacion> cueNavegacionsForPreguntaDestino) {
		this.cueNavegacionsForPreguntaDestino = cueNavegacionsForPreguntaDestino;
	}

	public Set<CueNavegacion> getCueNavegacionsForPreguntaOrigen() {
		return this.cueNavegacionsForPreguntaOrigen;
	}

	public void setCueNavegacionsForPreguntaOrigen(Set<CueNavegacion> cueNavegacionsForPreguntaOrigen) {
		this.cueNavegacionsForPreguntaOrigen = cueNavegacionsForPreguntaOrigen;
	}

	public Set<CueOpcion> getCueOpcions() {
		return this.cueOpcions;
	}

	public void setCueOpcions(Set<CueOpcion> cueOpcions) {
		this.cueOpcions = cueOpcions;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the listOpciones
	 */
	public List<CueOpcion> getListOpciones() {
		return listOpciones;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param listOpciones
	 *            the listOpciones to set
	 */
	public void setListOpciones(List<CueOpcion> listOpciones) {
		this.listOpciones = listOpciones;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 24/07/2013
	 * @return the selectedOpcion
	 */
	public Long getSelectedOpcion() {
		return selectedOpcion;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 24/07/2013
	 * @param selectedOpcion
	 *            the selectedOpcion to set
	 */
	public void setSelectedOpcion(Long selectedOpcion) {
		this.selectedOpcion = selectedOpcion;
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 24/07/2013
	 */
	public void listenerRequiereAmpliacion() {
		for (CueOpcion opcion : this.listOpciones) {
			if (opcion.getConsecutivo().equals(this.selectedOpcion)) {
				this.requiereAmpliacion = opcion.getRequiereAmpliacion().equals(1L) ? true : false;
			}
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 24/07/2013
	 */
	public CueOpcion getOpcionSelected(Long idOpcion) {
		for (CueOpcion opcion : this.listOpciones) {
			if (opcion.getConsecutivo().equals(idOpcion)) {
				return opcion;
			}
		}
		return null;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 24/07/2013
	 * @return the selectedOptions
	 */
	public List<Long> getSelectedOptions() {
		return selectedOptions;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 24/07/2013
	 * @param selectedOptions
	 *            the selectedOptions to set
	 */
	public void setSelectedOptions(List<Long> selectedOptions) {
		this.selectedOptions = selectedOptions;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 24/07/2013
	 * @return the requiereAmpliacion
	 */
	public boolean isRequiereAmpliacion() {
		return requiereAmpliacion;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 24/07/2013
	 * @param requiereAmpliacion
	 *            the requiereAmpliacion to set
	 */
	public void setRequiereAmpliacion(boolean requiereAmpliacion) {
		this.requiereAmpliacion = requiereAmpliacion;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 24/07/2013
	 * @return the respuestaAmpliacion
	 */
	public String getRespuestaAmpliacion() {
		return respuestaAmpliacion;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 24/07/2013
	 * @param respuestaAmpliacion the respuestaAmpliacion to set
	 */
	public void setRespuestaAmpliacion(String respuestaAmpliacion) {
		this.respuestaAmpliacion = respuestaAmpliacion;
	}

}
