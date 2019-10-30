package com.vortexbird.pusay.cuestionarios.api.dto;

import java.io.Serializable;

/**
 * 
 * 
 * @author Zathura Code Generator http://code.google.com/p/zathura/ support
 *         Andr�s Mauricio C�rdenas mauriciocardenasp@gmail.com
 */
public class CueNavegacionDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long consecutivo;
	private Long consecutivo_CueCuestionario;
	private Long consecutivo_CueOpcion;
	private Long consecutivo_CuePreguntaOrigen;
	private Long consecutivo_CuePreguntaDestino;

	public Long getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Long getConsecutivo_CueCuestionario() {
		return consecutivo_CueCuestionario;
	}

	public void setConsecutivo_CueCuestionario(Long consecutivo_CueCuestionario) {
		this.consecutivo_CueCuestionario = consecutivo_CueCuestionario;
	}

	public Long getConsecutivo_CueOpcion() {
		return consecutivo_CueOpcion;
	}

	public void setConsecutivo_CueOpcion(Long consecutivo_CueOpcion) {
		this.consecutivo_CueOpcion = consecutivo_CueOpcion;
	}

	public Long getConsecutivo_CuePreguntaOrigen() {
		return consecutivo_CuePreguntaOrigen;
	}

	public void setConsecutivo_CuePreguntaOrigen(
			Long consecutivo_CuePreguntaOrigen) {
		this.consecutivo_CuePreguntaOrigen = consecutivo_CuePreguntaOrigen;
	}

	public Long getConsecutivo_CuePreguntaDestino() {
		return consecutivo_CuePreguntaDestino;
	}

	public void setConsecutivo_CuePreguntaDestino(
			Long consecutivo_CuePreguntaDestino) {
		this.consecutivo_CuePreguntaDestino = consecutivo_CuePreguntaDestino;
	}

}
