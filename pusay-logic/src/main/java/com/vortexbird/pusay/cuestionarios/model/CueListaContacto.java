package com.vortexbird.pusay.cuestionarios.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class CueListaContacto implements java.io.Serializable {

	private Long consecutivo = null;
	private CueLista cueLista = null;
	private CueContacto cueContacto = null;
	private Date fechaHoraAsignacion = null;
	private Date fechaHoraFinalizacion = null;
	private Long puntajeTotal = null;
	private Long duracion = null;
	private Long pestCodigo;
	private Long estado = null;
	private Set<CueRespuesta> cueRespuestas = new HashSet<CueRespuesta>(0);

	public CueListaContacto() {
	}

	public CueListaContacto(Long consecutivo, CueLista cueLista,
			CueContacto cueContacto, Date fechaHoraAsignacion,
			Date fechaHoraFinalizacion, Long duracion, Long estado) {
		this.consecutivo = consecutivo;
		this.cueLista = cueLista;
		this.cueContacto = cueContacto;
		this.fechaHoraAsignacion = fechaHoraAsignacion;
		this.fechaHoraFinalizacion = fechaHoraFinalizacion;
		this.duracion = duracion;
		this.estado = estado;
	}

	public CueListaContacto(Long consecutivo, CueLista cueLista,
			CueContacto cueContacto, Date fechaHoraAsignacion,
			Date fechaHoraFinalizacion, Long puntajeTotal, Long duracion,
			Long estado,Long pestCodigo, Set<CueRespuesta> cueRespuestas) {
		this.consecutivo = consecutivo;
		this.cueLista = cueLista;
		this.cueContacto = cueContacto;
		this.fechaHoraAsignacion = fechaHoraAsignacion;
		this.fechaHoraFinalizacion = fechaHoraFinalizacion;
		this.puntajeTotal = puntajeTotal;
		this.duracion = duracion;
		this.estado = estado;
		this.cueRespuestas = cueRespuestas;
		this.pestCodigo=pestCodigo;
	}

	public Long getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public CueLista getCueLista() {
		return this.cueLista;
	}

	public void setCueLista(CueLista cueLista) {
		this.cueLista = cueLista;
	}

	public CueContacto getCueContacto() {
		return this.cueContacto;
	}

	public void setCueContacto(CueContacto cueContacto) {
		this.cueContacto = cueContacto;
	}

	public Date getFechaHoraAsignacion() {
		return this.fechaHoraAsignacion;
	}

	public void setFechaHoraAsignacion(Date fechaHoraAsignacion) {
		this.fechaHoraAsignacion = fechaHoraAsignacion;
	}

	public Date getFechaHoraFinalizacion() {
		return this.fechaHoraFinalizacion;
	}

	public void setFechaHoraFinalizacion(Date fechaHoraFinalizacion) {
		this.fechaHoraFinalizacion = fechaHoraFinalizacion;
	}

	public Long getPuntajeTotal() {
		return this.puntajeTotal;
	}

	public void setPuntajeTotal(Long puntajeTotal) {
		this.puntajeTotal = puntajeTotal;
	}

	public Long getDuracion() {
		return this.duracion;
	}

	public void setDuracion(Long duracion) {
		this.duracion = duracion;
	}

	public Long getEstado() {
		return this.estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public Set<CueRespuesta> getCueRespuestas() {
		return this.cueRespuestas;
	}

	public void setCueRespuestas(Set<CueRespuesta> cueRespuestas) {
		this.cueRespuestas = cueRespuestas;
	}

	public Long getPestCodigo() {
		return pestCodigo;
	}

	public void setPestCodigo(Long pestCodigo) {
		this.pestCodigo = pestCodigo;
	}
	
	
	

}
