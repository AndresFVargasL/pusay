package com.vortexbird.pusay.cuestionarios.api.dto;

import java.io.Serializable;
import java.util.Date;

public class CueConfiguracionDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long abierto;
	private String claveAcceso;
	private String colorTabla;
	private Long consecutivo;
	private String header;
	private String mensajeCierre;
	private String mensajeClaveIncorrecta;
	private String mensajeCuestionarioFinalizad;
	private String mensajeFechaLimite;
	private String mensajeMaximoRespuestas;
	private String mensajeRedireccional;
	private Long multipleRespuesta;
	private String multipleRespuestaMsj;
	private Long puntajeMax;
	private Long redirigirCerrar;
	private Long redirigirInforme;
	private String redirigirUrl;
	private Long retomarCuestionario;
	private Date vigenciaFin;
	private Date vigenciaInicio;

	public Long getAbierto() {
		return abierto;
	}

	public void setAbierto(Long abierto) {
		this.abierto = abierto;
	}

	public String getClaveAcceso() {
		return claveAcceso;
	}

	public void setClaveAcceso(String claveAcceso) {
		this.claveAcceso = claveAcceso;
	}

	public String getColorTabla() {
		return colorTabla;
	}

	public void setColorTabla(String colorTabla) {
		this.colorTabla = colorTabla;
	}

	public Long getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getMensajeCierre() {
		return mensajeCierre;
	}

	public void setMensajeCierre(String mensajeCierre) {
		this.mensajeCierre = mensajeCierre;
	}

	public String getMensajeClaveIncorrecta() {
		return mensajeClaveIncorrecta;
	}

	public void setMensajeClaveIncorrecta(String mensajeClaveIncorrecta) {
		this.mensajeClaveIncorrecta = mensajeClaveIncorrecta;
	}

	public String getMensajeCuestionarioFinalizad() {
		return mensajeCuestionarioFinalizad;
	}

	public void setMensajeCuestionarioFinalizad(
			String mensajeCuestionarioFinalizad) {
		this.mensajeCuestionarioFinalizad = mensajeCuestionarioFinalizad;
	}

	public String getMensajeFechaLimite() {
		return mensajeFechaLimite;
	}

	public void setMensajeFechaLimite(String mensajeFechaLimite) {
		this.mensajeFechaLimite = mensajeFechaLimite;
	}

	public String getMensajeMaximoRespuestas() {
		return mensajeMaximoRespuestas;
	}

	public void setMensajeMaximoRespuestas(String mensajeMaximoRespuestas) {
		this.mensajeMaximoRespuestas = mensajeMaximoRespuestas;
	}

	public String getMensajeRedireccional() {
		return mensajeRedireccional;
	}

	public void setMensajeRedireccional(String mensajeRedireccional) {
		this.mensajeRedireccional = mensajeRedireccional;
	}

	public Long getMultipleRespuesta() {
		return multipleRespuesta;
	}

	public void setMultipleRespuesta(Long multipleRespuesta) {
		this.multipleRespuesta = multipleRespuesta;
	}

	public String getMultipleRespuestaMsj() {
		return multipleRespuestaMsj;
	}

	public void setMultipleRespuestaMsj(String multipleRespuestaMsj) {
		this.multipleRespuestaMsj = multipleRespuestaMsj;
	}

	public Long getPuntajeMax() {
		return puntajeMax;
	}

	public void setPuntajeMax(Long puntajeMax) {
		this.puntajeMax = puntajeMax;
	}

	public Long getRedirigirCerrar() {
		return redirigirCerrar;
	}

	public void setRedirigirCerrar(Long redirigirCerrar) {
		this.redirigirCerrar = redirigirCerrar;
	}

	public Long getRedirigirInforme() {
		return redirigirInforme;
	}

	public void setRedirigirInforme(Long redirigirInforme) {
		this.redirigirInforme = redirigirInforme;
	}

	public String getRedirigirUrl() {
		return redirigirUrl;
	}

	public void setRedirigirUrl(String redirigirUrl) {
		this.redirigirUrl = redirigirUrl;
	}

	public Long getRetomarCuestionario() {
		return retomarCuestionario;
	}

	public void setRetomarCuestionario(Long retomarCuestionario) {
		this.retomarCuestionario = retomarCuestionario;
	}

	public Date getVigenciaFin() {
		return vigenciaFin;
	}

	public void setVigenciaFin(Date vigenciaFin) {
		this.vigenciaFin = vigenciaFin;
	}

	public Date getVigenciaInicio() {
		return vigenciaInicio;
	}

	public void setVigenciaInicio(Date vigenciaInicio) {
		this.vigenciaInicio = vigenciaInicio;
	}
}
