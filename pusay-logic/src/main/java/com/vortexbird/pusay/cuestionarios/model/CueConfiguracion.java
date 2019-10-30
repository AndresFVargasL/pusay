package com.vortexbird.pusay.cuestionarios.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class CueConfiguracion implements java.io.Serializable {

	private Long consecutivo = null;
	private String enunciado = null;
	private Long multipleRespuesta = null;
	private String multipleRespuestaMsj = null;
	private Long retomarCuestionario = null;
	private String redirigirUrl = null;
	private Long redirigirCerrar = null;
	private Long redirigirInforme = null;
	private String claveAcceso = null;
	private Date vigenciaInicio = null;
	private Date vigenciaFin = null;
	private String mensajeCierre = null;
	private String mensajeFechaLimite = null;
	private String mensajeRedireccional = null;
	private String mensajeMaximoRespuestas = null;
	private String mensajeCuestionarioFinalizad = null;
	private String mensajeClaveIncorrecta = null;
	private Long abierto = null;
	private Long puntajeMax = null;
	private String header = null;
	private String colorTabla = null;
	private Set<CueCuestionario> cueCuestionarios = new HashSet<CueCuestionario>(
			0);

	public CueConfiguracion() {
	}

	public CueConfiguracion(Long consecutivo, String enunciado, Long multipleRespuesta,
			String multipleRespuestaMsj, Long retomarCuestionario,
			Long redirigirCerrar, Long redirigirInforme, Date vigenciaInicio,
			Date vigenciaFin, String mensajeCierre, String mensajeFechaLimite,
			String mensajeRedireccional, String mensajeMaximoRespuestas,
			String mensajeCuestionarioFinalizad, String mensajeClaveIncorrecta,
			Long abierto, String header, String colorTabla) {
		this.consecutivo = consecutivo;
		this.enunciado = enunciado;
		this.multipleRespuesta = multipleRespuesta;
		this.multipleRespuestaMsj = multipleRespuestaMsj;
		this.retomarCuestionario = retomarCuestionario;
		this.redirigirCerrar = redirigirCerrar;
		this.redirigirInforme = redirigirInforme;
		this.vigenciaInicio = vigenciaInicio;
		this.vigenciaFin = vigenciaFin;
		this.mensajeCierre = mensajeCierre;
		this.mensajeFechaLimite = mensajeFechaLimite;
		this.mensajeRedireccional = mensajeRedireccional;
		this.mensajeMaximoRespuestas = mensajeMaximoRespuestas;
		this.mensajeCuestionarioFinalizad = mensajeCuestionarioFinalizad;
		this.mensajeClaveIncorrecta = mensajeClaveIncorrecta;
		this.abierto = abierto;
		this.header = header;
		this.colorTabla = colorTabla;
	}

	public CueConfiguracion(Long consecutivo, String enunciado, Long multipleRespuesta,
			String multipleRespuestaMsj, Long retomarCuestionario,
			String redirigirUrl, Long redirigirCerrar, Long redirigirInforme,
			String claveAcceso, Date vigenciaInicio, Date vigenciaFin,
			String mensajeCierre, String mensajeFechaLimite,
			String mensajeRedireccional, String mensajeMaximoRespuestas,
			String mensajeCuestionarioFinalizad, String mensajeClaveIncorrecta,
			Long abierto, Long puntajeMax, String header, String colorTabla,
			Set<CueCuestionario> cueCuestionarios) {
		this.consecutivo = consecutivo;
		this.enunciado = enunciado;
		this.multipleRespuesta = multipleRespuesta;
		this.multipleRespuestaMsj = multipleRespuestaMsj;
		this.retomarCuestionario = retomarCuestionario;
		this.redirigirUrl = redirigirUrl;
		this.redirigirCerrar = redirigirCerrar;
		this.redirigirInforme = redirigirInforme;
		this.claveAcceso = claveAcceso;
		this.vigenciaInicio = vigenciaInicio;
		this.vigenciaFin = vigenciaFin;
		this.mensajeCierre = mensajeCierre;
		this.mensajeFechaLimite = mensajeFechaLimite;
		this.mensajeRedireccional = mensajeRedireccional;
		this.mensajeMaximoRespuestas = mensajeMaximoRespuestas;
		this.mensajeCuestionarioFinalizad = mensajeCuestionarioFinalizad;
		this.mensajeClaveIncorrecta = mensajeClaveIncorrecta;
		this.abierto = abierto;
		this.puntajeMax = puntajeMax;
		this.header = header;
		this.colorTabla = colorTabla;
		this.cueCuestionarios = cueCuestionarios;
	}

	public Long getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Long getMultipleRespuesta() {
		return this.multipleRespuesta;
	}

	public void setMultipleRespuesta(Long multipleRespuesta) {
		this.multipleRespuesta = multipleRespuesta;
	}

	public String getMultipleRespuestaMsj() {
		return this.multipleRespuestaMsj;
	}

	public void setMultipleRespuestaMsj(String multipleRespuestaMsj) {
		this.multipleRespuestaMsj = multipleRespuestaMsj;
	}

	public Long getRetomarCuestionario() {
		return this.retomarCuestionario;
	}

	public void setRetomarCuestionario(Long retomarCuestionario) {
		this.retomarCuestionario = retomarCuestionario;
	}

	public String getRedirigirUrl() {
		return this.redirigirUrl;
	}

	public void setRedirigirUrl(String redirigirUrl) {
		this.redirigirUrl = redirigirUrl;
	}

	public Long getRedirigirCerrar() {
		return this.redirigirCerrar;
	}

	public void setRedirigirCerrar(Long redirigirCerrar) {
		this.redirigirCerrar = redirigirCerrar;
	}

	public Long getRedirigirInforme() {
		return this.redirigirInforme;
	}

	public void setRedirigirInforme(Long redirigirInforme) {
		this.redirigirInforme = redirigirInforme;
	}

	public String getClaveAcceso() {
		return this.claveAcceso;
	}

	public void setClaveAcceso(String claveAcceso) {
		this.claveAcceso = claveAcceso;
	}

	public Date getVigenciaInicio() {
		return this.vigenciaInicio;
	}

	public void setVigenciaInicio(Date vigenciaInicio) {
		this.vigenciaInicio = vigenciaInicio;
	}

	public Date getVigenciaFin() {
		return this.vigenciaFin;
	}

	public void setVigenciaFin(Date vigenciaFin) {
		this.vigenciaFin = vigenciaFin;
	}

	public String getMensajeCierre() {
		return this.mensajeCierre;
	}

	public void setMensajeCierre(String mensajeCierre) {
		this.mensajeCierre = mensajeCierre;
	}

	public String getMensajeFechaLimite() {
		return this.mensajeFechaLimite;
	}

	public void setMensajeFechaLimite(String mensajeFechaLimite) {
		this.mensajeFechaLimite = mensajeFechaLimite;
	}

	public String getMensajeRedireccional() {
		return this.mensajeRedireccional;
	}

	public void setMensajeRedireccional(String mensajeRedireccional) {
		this.mensajeRedireccional = mensajeRedireccional;
	}

	public String getMensajeMaximoRespuestas() {
		return this.mensajeMaximoRespuestas;
	}

	public void setMensajeMaximoRespuestas(String mensajeMaximoRespuestas) {
		this.mensajeMaximoRespuestas = mensajeMaximoRespuestas;
	}

	public String getMensajeCuestionarioFinalizad() {
		return this.mensajeCuestionarioFinalizad;
	}

	public void setMensajeCuestionarioFinalizad(
			String mensajeCuestionarioFinalizad) {
		this.mensajeCuestionarioFinalizad = mensajeCuestionarioFinalizad;
	}

	public String getMensajeClaveIncorrecta() {
		return this.mensajeClaveIncorrecta;
	}

	public void setMensajeClaveIncorrecta(String mensajeClaveIncorrecta) {
		this.mensajeClaveIncorrecta = mensajeClaveIncorrecta;
	}

	public Long getAbierto() {
		return this.abierto;
	}

	public void setAbierto(Long abierto) {
		this.abierto = abierto;
	}

	public Long getPuntajeMax() {
		return this.puntajeMax;
	}

	public void setPuntajeMax(Long puntajeMax) {
		this.puntajeMax = puntajeMax;
	}

	public String getHeader() {
		return this.header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getColorTabla() {
		return this.colorTabla;
	}

	public void setColorTabla(String colorTabla) {
		this.colorTabla = colorTabla;
	}

	public Set<CueCuestionario> getCueCuestionarios() {
		return this.cueCuestionarios;
	}

	public void setCueCuestionarios(Set<CueCuestionario> cueCuestionarios) {
		this.cueCuestionarios = cueCuestionarios;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
}
