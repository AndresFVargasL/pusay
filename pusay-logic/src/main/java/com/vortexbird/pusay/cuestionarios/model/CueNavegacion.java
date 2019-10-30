package com.vortexbird.pusay.cuestionarios.model;

@SuppressWarnings("serial")
public class CueNavegacion implements java.io.Serializable {

	private Long consecutivo = null;
	private CuePregunta cuePreguntaByPreguntaOrigen = null;
	private CueOpcion cueOpcion = null;
	private CuePregunta cuePreguntaByPreguntaDestino = null;
	private CueCuestionario cueCuestionario = null;

	public CueNavegacion() {
	}

	public CueNavegacion(Long consecutivo,
			CuePregunta cuePreguntaByPreguntaOrigen, CueOpcion cueOpcion,
			CuePregunta cuePreguntaByPreguntaDestino,
			CueCuestionario cueCuestionario) {
		this.consecutivo = consecutivo;
		this.cuePreguntaByPreguntaOrigen = cuePreguntaByPreguntaOrigen;
		this.cueOpcion = cueOpcion;
		this.cuePreguntaByPreguntaDestino = cuePreguntaByPreguntaDestino;
		this.cueCuestionario = cueCuestionario;
	}

	public Long getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public CuePregunta getCuePreguntaByPreguntaOrigen() {
		return this.cuePreguntaByPreguntaOrigen;
	}

	public void setCuePreguntaByPreguntaOrigen(
			CuePregunta cuePreguntaByPreguntaOrigen) {
		this.cuePreguntaByPreguntaOrigen = cuePreguntaByPreguntaOrigen;
	}

	public CueOpcion getCueOpcion() {
		return this.cueOpcion;
	}

	public void setCueOpcion(CueOpcion cueOpcion) {
		this.cueOpcion = cueOpcion;
	}

	public CuePregunta getCuePreguntaByPreguntaDestino() {
		return this.cuePreguntaByPreguntaDestino;
	}

	public void setCuePreguntaByPreguntaDestino(
			CuePregunta cuePreguntaByPreguntaDestino) {
		this.cuePreguntaByPreguntaDestino = cuePreguntaByPreguntaDestino;
	}

	public CueCuestionario getCueCuestionario() {
		return this.cueCuestionario;
	}

	public void setCueCuestionario(CueCuestionario cueCuestionario) {
		this.cueCuestionario = cueCuestionario;
	}

}
