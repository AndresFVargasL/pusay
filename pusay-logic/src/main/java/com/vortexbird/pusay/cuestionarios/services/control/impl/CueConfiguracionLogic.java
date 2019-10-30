package com.vortexbird.pusay.cuestionarios.services.control.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.pusay.cuestionarios.api.Utilities;
import com.vortexbird.pusay.cuestionarios.api.dto.CueConfiguracionDTO;
import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.api.util.Propiedades;
import com.vortexbird.pusay.cuestionarios.dao.ICueConfiguracionDAO;
import com.vortexbird.pusay.cuestionarios.dao.ICueCuestionarioDAO;
import com.vortexbird.pusay.cuestionarios.model.CueConfiguracion;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionario;
import com.vortexbird.pusay.cuestionarios.services.control.ICueConfiguracionLogic;

@Scope("singleton")
@Service("CueConfiguracionLogic")
public class CueConfiguracionLogic implements ICueConfiguracionLogic {
	/**
	 * DAO injected by Spring that manages CueConfiguracion entities support Andr�s Mauricio C�rdenas
	 * mauriciocardenasp@gmail.com
	 */
	@Autowired
	private ICueConfiguracionDAO cueConfiguracionDAO;

	/**
	 * DAO injected by Spring that manages CueCuestionario entities
	 * 
	 */
	@Autowired
	private ICueCuestionarioDAO cueCuestionarioDAO;

	private String lbl_enunciado = Propiedades.getInstance().getMensaje(
			"lbl_configuracion_multipleRespuesta");
	private String lbl_multipleRespuestaMsj = Propiedades.getInstance().getMensaje(
			"lbl_configuracion_lbl_configuracion_multipleRespuestaMsj");
	private String lbl_retomarCuestionario = Propiedades.getInstance().getMensaje(
			"lbl_configuracion_retomarCuestionario");
	private String lbl_redirigirUrl = Propiedades.getInstance().getMensaje(
			"lbl_configuracion_redirigirUrl");
	private String lbl_redirigirCerrar = Propiedades.getInstance().getMensaje(
			"lbl_configuracion_redirigirCerrar");
	private String lbl_claveAcceso = Propiedades.getInstance().getMensaje(
			"lbl_configuracion_claveAcceso");
	private String lbl_vigenciaInicio = Propiedades.getInstance().getMensaje(
			"lbl_configuracion_vigenciaInicio");
	private String lbl_vigenciaFin = Propiedades.getInstance().getMensaje(
			"lbl_configuracion_vigenciaFin");
	private String lbl_mensajeCierre = Propiedades.getInstance().getMensaje(
			"lbl_configuracion_mensajeCierre");
	private String lbl_mensajeFechaLimite = Propiedades.getInstance().getMensaje(
			"lbl_configuracion_mensajeFechaLimite");
	private String lbl_mensajeRedireccional = Propiedades.getInstance().getMensaje(
			"lbl_configuracion_mensajeRedireccional");
	private String lbl_mensajeMaximoRespuestas = Propiedades.getInstance().getMensaje(
			"lbl_configuracion_mensajeMaximoRespuestas");
	private String lbl_mensajeCuestionarioFinalizad = Propiedades.getInstance().getMensaje(
			"lbl_configuracion_mensajeCuestionarioFinalizad");
	private String lbl_mensajeClaveIncorrecta = Propiedades.getInstance().getMensaje(
			"lbl_configuracion_mensajeClaveIncorrecta");
	private String lbl_abierto = Propiedades.getInstance().getMensaje(
			"lbl_configuracion_abierto");
	private String lbl_puntajeMax = Propiedades.getInstance().getMensaje(
			"lbl_configuracion_puntajeMax");
	private String lbl_header = Propiedades.getInstance().getMensaje(
			"lbl_configuracion_header");
			
	@Transactional(readOnly = true)
	public List<CueConfiguracion> getCueConfiguracion() throws Exception {
		List<CueConfiguracion> list = new ArrayList<CueConfiguracion>();

		try {
			list = cueConfiguracionDAO.findAll();
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "CueConfiguracion");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCueConfiguracion(CueConfiguracion configuracion) throws Exception {
		CueConfiguracion entity = null;

		try {
			if ((configuracion.getPuntajeMax() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + configuracion.getPuntajeMax(), 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_puntajeMax);
			}
			
			if (configuracion.getAbierto() == null) {
				configuracion.setAbierto(0l);
				//throw new ZMessManager().new EmptyFieldException(lbl_abierto);
			}

			if ((configuracion.getAbierto() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + configuracion.getAbierto(), 2, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_abierto);
			}
			
			if (configuracion.getColorTabla() == null || configuracion.getColorTabla().length() < 1) {
				configuracion.setColorTabla("#FFFFFF");
				//throw new ZMessManager().new EmptyFieldException("colorTabla");
			}

			if ((configuracion.getColorTabla() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getColorTabla(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException("colorTabla");
			}
			
			if (configuracion.getEnunciado() == null) {
				configuracion.setEnunciado("NA");
				//throw new ZMessManager().new EmptyFieldException(lbl_enunciado);
			}
			
			if ((configuracion.getEnunciado() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getColorTabla(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_enunciado);
			}

			if (configuracion.getHeader() == null || configuracion.getHeader().length() < 1) {
				configuracion.setHeader("NA");
				//throw new ZMessManager().new EmptyFieldException(lbl_header);
			}

			if ((configuracion.getHeader() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getHeader(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_header);
			}
			
			if (configuracion.getMultipleRespuesta() == null) {
				configuracion.setMultipleRespuesta(0l);
				//throw new ZMessManager().new EmptyFieldException(lbl_multipleRespuestaMsjMsj);
			}
			
			if (configuracion.getMultipleRespuesta().equals(1L)) {
				if (configuracion.getMultipleRespuestaMsj() == null || configuracion.getMultipleRespuestaMsj().length() < 1) {
					throw new ZMessManager().new EmptyFieldException(lbl_multipleRespuestaMsj);
				}
				
				if ((configuracion.getMultipleRespuestaMsj() != null)
						&& (Utilities.checkWordAndCheckWithlength(configuracion.getMultipleRespuestaMsj(), 512) == false)) {
					throw new ZMessManager().new NotValidFormatException(lbl_multipleRespuestaMsj);
				}
			}

			if (configuracion.getRetomarCuestionario() == null) {
				configuracion.setRetomarCuestionario(0l);
				//throw new ZMessManager().new EmptyFieldException(lbl_retomarCuestionario);
			}

			if ((configuracion.getRetomarCuestionario() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + configuracion.getRetomarCuestionario(),
							1, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_retomarCuestionario);
			}
			
			if (configuracion.getRedirigirCerrar() == null && configuracion.getRedirigirInforme() == null && configuracion.getRedirigirUrl() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_redirigirCerrar);
			}
			
			if (configuracion.getRedirigirUrl() != null && configuracion.getRedirigirUrl().length() < 1) {
				throw new ZMessManager().new NotValidFormatException(lbl_redirigirUrl);
			}
			
			if ((configuracion.getRedirigirUrl() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getRedirigirUrl(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_redirigirUrl);
			}
			
			if (configuracion.getClaveAcceso() != null && configuracion.getClaveAcceso().length() < 1) {
				throw new ZMessManager().new NotValidFormatException(lbl_claveAcceso);
			}
			
			if ((configuracion.getClaveAcceso() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getClaveAcceso(), 20) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_claveAcceso);
			}

			if (configuracion.getVigenciaFin() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_vigenciaFin);
			}

			if (configuracion.getVigenciaInicio() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_vigenciaInicio);
			}

			if (configuracion.getMensajeCierre() == null || configuracion.getMensajeCierre().length() < 1) {
				throw new ZMessManager().new EmptyFieldException(lbl_mensajeCierre);
			}

			if ((configuracion.getMensajeCierre() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getMensajeCierre(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_mensajeCierre);
			}

			if (configuracion.getMensajeClaveIncorrecta() == null || configuracion.getMensajeClaveIncorrecta().length() < 1) {
				throw new ZMessManager().new EmptyFieldException(lbl_mensajeClaveIncorrecta);
			}

			if ((configuracion.getMensajeClaveIncorrecta() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getMensajeClaveIncorrecta(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_mensajeClaveIncorrecta);
			}

			if (configuracion.getMensajeCuestionarioFinalizad() == null || configuracion.getMensajeCuestionarioFinalizad().length() < 1) {
				throw new ZMessManager().new EmptyFieldException(lbl_mensajeCuestionarioFinalizad);
			}

			if ((configuracion.getMensajeCuestionarioFinalizad() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getMensajeCuestionarioFinalizad(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_mensajeCuestionarioFinalizad);
			}

			if (configuracion.getMensajeFechaLimite() == null || configuracion.getMensajeFechaLimite().length() < 1) {
				throw new ZMessManager().new EmptyFieldException(lbl_mensajeFechaLimite);
			}

			if ((configuracion.getMensajeFechaLimite() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getMensajeFechaLimite(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_mensajeFechaLimite);
			}

			if (configuracion.getMensajeMaximoRespuestas() == null || configuracion.getMensajeMaximoRespuestas().length() < 1) {
				throw new ZMessManager().new EmptyFieldException(lbl_mensajeMaximoRespuestas);
			}

			if ((configuracion.getMensajeMaximoRespuestas() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getMensajeMaximoRespuestas(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_mensajeMaximoRespuestas);
			}

			if (configuracion.getMensajeRedireccional() == null || configuracion.getMensajeRedireccional().length() < 1) {
				throw new ZMessManager().new EmptyFieldException(lbl_mensajeRedireccional);
			}

			if ((configuracion.getMensajeRedireccional() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getMensajeRedireccional(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_mensajeRedireccional);
			}

			entity = new CueConfiguracion();
			entity.setEnunciado(configuracion.getEnunciado());
			entity.setAbierto(configuracion.getAbierto());
			entity.setClaveAcceso(configuracion.getClaveAcceso());
			entity.setColorTabla(configuracion.getColorTabla());
			entity.setHeader(configuracion.getHeader());
			entity.setMensajeCierre(configuracion.getMensajeCierre());
			entity.setMensajeClaveIncorrecta(configuracion.getMensajeClaveIncorrecta());
			entity.setMensajeCuestionarioFinalizad(configuracion.getMensajeCuestionarioFinalizad());
			entity.setMensajeFechaLimite(configuracion.getMensajeFechaLimite());
			entity.setMensajeMaximoRespuestas(configuracion.getMensajeMaximoRespuestas());
			entity.setMensajeRedireccional(configuracion.getMensajeRedireccional());
			entity.setMultipleRespuesta(configuracion.getMultipleRespuesta());
			entity.setMultipleRespuestaMsj(configuracion.getMultipleRespuestaMsj());
			entity.setPuntajeMax(configuracion.getPuntajeMax());
			entity.setRedirigirCerrar(configuracion.getRedirigirCerrar());
			entity.setRedirigirInforme(configuracion.getRedirigirInforme());
			entity.setRedirigirUrl(configuracion.getRedirigirUrl());
			entity.setRetomarCuestionario(configuracion.getRetomarCuestionario());
			entity.setVigenciaFin(configuracion.getVigenciaFin());
			entity.setVigenciaInicio(configuracion.getVigenciaInicio());
			this.cueConfiguracionDAO.save(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteCueConfiguracion(Long consecutivo) throws Exception {
		CueConfiguracion entity = null;

		if (consecutivo == null) {
			throw new ZMessManager().new EmptyFieldException("consecutivo");
		}

		List<CueCuestionario> cueCuestionarios = null;
		entity = getCueConfiguracion(consecutivo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException("CueConfiguracion");
		}

		try {
			cueCuestionarios = cueCuestionarioDAO.findByProperty("cueConfiguracion.consecutivo", consecutivo);

			if (Utilities.validationsList(cueCuestionarios) == true) {
				throw new ZMessManager().new DeletingException("cueCuestionarios");
			}

			cueConfiguracionDAO.delete(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCueConfiguracion(CueConfiguracion configuracion) throws Exception {
		CueConfiguracion entity = null;

		try {
			
			if ((configuracion.getPuntajeMax() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + configuracion.getPuntajeMax(), 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_puntajeMax);
			}
			
			if (configuracion.getAbierto() == null) {
				configuracion.setAbierto(0l);
				//throw new ZMessManager().new EmptyFieldException(lbl_abierto);
			}

			if ((configuracion.getAbierto() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + configuracion.getAbierto(), 2, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_abierto);
			}
			
			if (configuracion.getColorTabla() == null || configuracion.getColorTabla().length() < 1) {
				configuracion.setColorTabla("#FFFFFF");
				//throw new ZMessManager().new EmptyFieldException("colorTabla");
			}

			if ((configuracion.getColorTabla() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getColorTabla(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException("colorTabla");
			}
			
			if (configuracion.getEnunciado() == null) {
				configuracion.setEnunciado("NA");
				//throw new ZMessManager().new EmptyFieldException(lbl_enunciado);
			}
			
			if ((configuracion.getEnunciado() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getColorTabla(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_enunciado);
			}

			if (configuracion.getHeader() == null || configuracion.getHeader().length() < 1) {
				configuracion.setHeader("NA");
				//throw new ZMessManager().new EmptyFieldException(lbl_header);
			}

			if ((configuracion.getHeader() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getHeader(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_header);
			}
			
			if (configuracion.getMultipleRespuesta() == null) {
				configuracion.setMultipleRespuesta(0l);
				//throw new ZMessManager().new EmptyFieldException(lbl_multipleRespuestaMsjMsj);
			}
			
			if (configuracion.getMultipleRespuesta().equals(1L)) {
				if (configuracion.getMultipleRespuestaMsj() == null || configuracion.getMultipleRespuestaMsj().length() < 1) {
					throw new ZMessManager().new EmptyFieldException(lbl_multipleRespuestaMsj);
				}
				
				if ((configuracion.getMultipleRespuestaMsj() != null)
						&& (Utilities.checkWordAndCheckWithlength(configuracion.getMultipleRespuestaMsj(), 512) == false)) {
					throw new ZMessManager().new NotValidFormatException(lbl_multipleRespuestaMsj);
				}
			}

			if (configuracion.getRetomarCuestionario() == null) {
				configuracion.setRetomarCuestionario(0l);
				//throw new ZMessManager().new EmptyFieldException(lbl_retomarCuestionario);
			}

			if ((configuracion.getRetomarCuestionario() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + configuracion.getRetomarCuestionario(),
							1, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_retomarCuestionario);
			}
			
			if (configuracion.getRedirigirCerrar() == null && configuracion.getRedirigirInforme() == null && configuracion.getRedirigirUrl() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_redirigirCerrar);
			}
			
			if (configuracion.getRedirigirUrl() != null && configuracion.getRedirigirUrl().length() < 1) {
				throw new ZMessManager().new NotValidFormatException(lbl_redirigirUrl);
			}
			
			if ((configuracion.getRedirigirUrl() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getRedirigirUrl(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_redirigirUrl);
			}
			
			if (configuracion.getClaveAcceso() != null && configuracion.getClaveAcceso().length() < 1) {
				throw new ZMessManager().new NotValidFormatException(lbl_claveAcceso);
			}
			
			if ((configuracion.getClaveAcceso() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getClaveAcceso(), 20) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_claveAcceso);
			}

			if (configuracion.getVigenciaFin() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_vigenciaFin);
			}

			if (configuracion.getVigenciaInicio() == null) {
				throw new ZMessManager().new EmptyFieldException(lbl_vigenciaInicio);
			}

			if (configuracion.getMensajeCierre() == null || configuracion.getMensajeCierre().length() < 1) {
				throw new ZMessManager().new EmptyFieldException(lbl_mensajeCierre);
			}

			if ((configuracion.getMensajeCierre() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getMensajeCierre(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_mensajeCierre);
			}

			if (configuracion.getMensajeClaveIncorrecta() == null || configuracion.getMensajeClaveIncorrecta().length() < 1) {
				throw new ZMessManager().new EmptyFieldException(lbl_mensajeClaveIncorrecta);
			}

			if ((configuracion.getMensajeClaveIncorrecta() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getMensajeClaveIncorrecta(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_mensajeClaveIncorrecta);
			}

			if (configuracion.getMensajeCuestionarioFinalizad() == null || configuracion.getMensajeCuestionarioFinalizad().length() < 1) {
				throw new ZMessManager().new EmptyFieldException(lbl_mensajeCuestionarioFinalizad);
			}

			if ((configuracion.getMensajeCuestionarioFinalizad() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getMensajeCuestionarioFinalizad(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_mensajeCuestionarioFinalizad);
			}

			if (configuracion.getMensajeFechaLimite() == null || configuracion.getMensajeFechaLimite().length() < 1) {
				throw new ZMessManager().new EmptyFieldException(lbl_mensajeFechaLimite);
			}

			if ((configuracion.getMensajeFechaLimite() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getMensajeFechaLimite(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_mensajeFechaLimite);
			}

			if (configuracion.getMensajeMaximoRespuestas() == null || configuracion.getMensajeMaximoRespuestas().length() < 1) {
				throw new ZMessManager().new EmptyFieldException(lbl_mensajeMaximoRespuestas);
			}

			if ((configuracion.getMensajeMaximoRespuestas() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getMensajeMaximoRespuestas(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_mensajeMaximoRespuestas);
			}

			if (configuracion.getMensajeRedireccional() == null || configuracion.getMensajeRedireccional().length() < 1) {
				throw new ZMessManager().new EmptyFieldException(lbl_mensajeRedireccional);
			}

			if ((configuracion.getMensajeRedireccional() != null)
					&& (Utilities.checkWordAndCheckWithlength(configuracion.getMensajeRedireccional(), 512) == false)) {
				throw new ZMessManager().new NotValidFormatException(lbl_mensajeRedireccional);
			}
			
			entity = getCueConfiguracion(configuracion.getConsecutivo());

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setAbierto(configuracion.getAbierto());
			entity.setEnunciado(configuracion.getEnunciado());
			entity.setClaveAcceso(configuracion.getClaveAcceso());
			entity.setColorTabla(configuracion.getColorTabla());
			entity.setHeader(configuracion.getHeader());
			entity.setMensajeCierre(configuracion.getMensajeCierre());
			entity.setMensajeClaveIncorrecta(configuracion.getMensajeClaveIncorrecta());
			entity.setMensajeCuestionarioFinalizad(configuracion.getMensajeCuestionarioFinalizad());
			entity.setMensajeFechaLimite(configuracion.getMensajeFechaLimite());
			entity.setMensajeMaximoRespuestas(configuracion.getMensajeMaximoRespuestas());
			entity.setMensajeRedireccional(configuracion.getMensajeRedireccional());
			entity.setMultipleRespuesta(configuracion.getMultipleRespuesta());
			entity.setMultipleRespuestaMsj(configuracion.getMultipleRespuestaMsj());
			entity.setPuntajeMax(configuracion.getPuntajeMax());
			entity.setRedirigirCerrar(configuracion.getRedirigirCerrar());
			entity.setRedirigirInforme(configuracion.getRedirigirInforme());
			entity.setRedirigirUrl(configuracion.getRedirigirUrl());
			entity.setRetomarCuestionario(configuracion.getRetomarCuestionario());
			entity.setVigenciaFin(configuracion.getVigenciaFin());
			entity.setVigenciaInicio(configuracion.getVigenciaInicio());
			this.cueConfiguracionDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<CueConfiguracionDTO> getDataCueConfiguracion() throws Exception {
		try {
			List<CueConfiguracion> cueConfiguracion = cueConfiguracionDAO.findAll();

			List<CueConfiguracionDTO> cueConfiguracionDTO = new ArrayList<CueConfiguracionDTO>();

			for (CueConfiguracion cueConfiguracionTmp : cueConfiguracion) {
				CueConfiguracionDTO cueConfiguracionDTO2 = new CueConfiguracionDTO();

				cueConfiguracionDTO2.setConsecutivo(cueConfiguracionTmp.getConsecutivo());
				cueConfiguracionDTO2.setAbierto((cueConfiguracionTmp.getAbierto() != null) ? cueConfiguracionTmp
						.getAbierto() : null);
				cueConfiguracionDTO2
						.setClaveAcceso((cueConfiguracionTmp.getClaveAcceso() != null) ? cueConfiguracionTmp
								.getClaveAcceso() : null);
				cueConfiguracionDTO2.setColorTabla((cueConfiguracionTmp.getColorTabla() != null) ? cueConfiguracionTmp
						.getColorTabla() : null);
				cueConfiguracionDTO2.setHeader((cueConfiguracionTmp.getHeader() != null) ? cueConfiguracionTmp
						.getHeader() : null);
				cueConfiguracionDTO2
						.setMensajeCierre((cueConfiguracionTmp.getMensajeCierre() != null) ? cueConfiguracionTmp
								.getMensajeCierre() : null);
				cueConfiguracionDTO2
						.setMensajeClaveIncorrecta((cueConfiguracionTmp.getMensajeClaveIncorrecta() != null) ? cueConfiguracionTmp
								.getMensajeClaveIncorrecta() : null);
				cueConfiguracionDTO2.setMensajeCuestionarioFinalizad((cueConfiguracionTmp
						.getMensajeCuestionarioFinalizad() != null) ? cueConfiguracionTmp
						.getMensajeCuestionarioFinalizad() : null);
				cueConfiguracionDTO2
						.setMensajeFechaLimite((cueConfiguracionTmp.getMensajeFechaLimite() != null) ? cueConfiguracionTmp
								.getMensajeFechaLimite() : null);
				cueConfiguracionDTO2
						.setMensajeMaximoRespuestas((cueConfiguracionTmp.getMensajeMaximoRespuestas() != null) ? cueConfiguracionTmp
								.getMensajeMaximoRespuestas() : null);
				cueConfiguracionDTO2
						.setMensajeRedireccional((cueConfiguracionTmp.getMensajeRedireccional() != null) ? cueConfiguracionTmp
								.getMensajeRedireccional() : null);
				cueConfiguracionDTO2
						.setMultipleRespuesta((cueConfiguracionTmp.getMultipleRespuesta() != null) ? cueConfiguracionTmp
								.getMultipleRespuesta() : null);
				cueConfiguracionDTO2
						.setMultipleRespuestaMsj((cueConfiguracionTmp.getMultipleRespuestaMsj() != null) ? cueConfiguracionTmp
								.getMultipleRespuestaMsj() : null);
				cueConfiguracionDTO2.setPuntajeMax((cueConfiguracionTmp.getPuntajeMax() != null) ? cueConfiguracionTmp
						.getPuntajeMax() : null);
				cueConfiguracionDTO2
						.setRedirigirCerrar((cueConfiguracionTmp.getRedirigirCerrar() != null) ? cueConfiguracionTmp
								.getRedirigirCerrar() : null);
				cueConfiguracionDTO2
						.setRedirigirInforme((cueConfiguracionTmp.getRedirigirInforme() != null) ? cueConfiguracionTmp
								.getRedirigirInforme() : null);
				cueConfiguracionDTO2
						.setRedirigirUrl((cueConfiguracionTmp.getRedirigirUrl() != null) ? cueConfiguracionTmp
								.getRedirigirUrl() : null);
				cueConfiguracionDTO2
						.setRetomarCuestionario((cueConfiguracionTmp.getRetomarCuestionario() != null) ? cueConfiguracionTmp
								.getRetomarCuestionario() : null);
				cueConfiguracionDTO2.setVigenciaFin(cueConfiguracionTmp.getVigenciaFin());
				cueConfiguracionDTO2.setVigenciaInicio(cueConfiguracionTmp.getVigenciaInicio());
				cueConfiguracionDTO.add(cueConfiguracionDTO2);
			}

			return cueConfiguracionDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public CueConfiguracion getCueConfiguracion(Long consecutivo) throws Exception {
		CueConfiguracion entity = null;

		try {
			entity = cueConfiguracionDAO.findById(consecutivo);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueConfiguracion");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<CueConfiguracion> findPageCueConfiguracion(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<CueConfiguracion> entity = null;

		try {
			entity = cueConfiguracionDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueConfiguracion Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberCueConfiguracion() throws Exception {
		Long entity = null;

		try {
			entity = cueConfiguracionDAO.findTotalNumber();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("CueConfiguracion Count");
		} finally {
		}

		return entity;
	}

	/**
	 * 
	 * @param varibles
	 *            este arreglo debera tener:
	 * 
	 *            [0] = String variable = (String) varibles[i]; representa como se llama la variable en el pojo
	 * 
	 *            [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el valor necesita o no
	 *            ''(comillas simples)usado para campos de tipo string
	 * 
	 *            [2] = Object value = varibles[i + 2]; representa el valor que se va a buscar en la BD
	 * 
	 *            [3] = String comparator = (String) varibles[i + 3]; representa que tipo de busqueda voy a hacer..,
	 *            ejemplo: where nombre=william o where nombre<>william, en este campo iria el tipo de comparador que
	 *            quiero si es = o <>
	 * 
	 *            Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1 busqueda en un campo, si se
	 *            ponen mas pues el continuara buscando en lo que se le ingresen en los otros 4
	 * 
	 * 
	 * @param variablesBetween
	 * 
	 *            la diferencia son estas dos posiciones
	 * 
	 *            [0] = String variable = (String) varibles[j]; la variable ne la BD que va a ser buscada en un rango
	 * 
	 *            [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
	 * 
	 *            [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango ejempolo: a > 1 and a < 5 --> 1
	 *            seria value y 5 seria value2
	 * 
	 *            [3] = String comparator1 = (String) varibles[j + 3]; comparador 1 ejemplo: a comparator1 1 and a < 5
	 * 
	 *            [4] = String comparator2 = (String) varibles[j + 4]; comparador 2 ejemplo: a comparador1>1 and a
	 *            comparador2<5 (el original: a > 1 and a < 5) *
	 * @param variablesBetweenDates
	 *            (en este caso solo para mysql) [0] = String variable = (String) varibles[k]; el nombre de la variable
	 *            que hace referencia a una fecha
	 * 
	 *            [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser dates)
	 * 
	 *            [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser dates)
	 * 
	 *            esto hace un between entre las dos fechas.
	 * 
	 * @return lista con los objetos que se necesiten
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<CueConfiguracion> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<CueConfiguracion> list = new ArrayList<CueConfiguracion>();
		String where = new String();
		String tempWhere = new String();

		if (variables != null) {
			for (int i = 0; i < variables.length; i++) {
				if ((variables[i] != null) && (variables[i + 1] != null) && (variables[i + 2] != null)
						&& (variables[i + 3] != null)) {
					String variable = (String) variables[i];
					Boolean booVariable = (Boolean) variables[i + 1];
					Object value = variables[i + 2];
					String comparator = (String) variables[i + 3];

					if (booVariable.booleanValue()) {
						tempWhere = (tempWhere.length() == 0) ? ("(model." + variable + " " + comparator + " \'"
								+ value + "\' )") : (tempWhere + " AND (model." + variable + " " + comparator + " \'"
								+ value + "\' )");
					} else {
						tempWhere = (tempWhere.length() == 0) ? ("(model." + variable + " " + comparator + " " + value + " )")
								: (tempWhere + " AND (model." + variable + " " + comparator + " " + value + " )");
					}
				}

				i = i + 3;
			}
		}

		if (variablesBetween != null) {
			for (int j = 0; j < variablesBetween.length; j++) {
				if ((variablesBetween[j] != null) && (variablesBetween[j + 1] != null)
						&& (variablesBetween[j + 2] != null) && (variablesBetween[j + 3] != null)
						&& (variablesBetween[j + 4] != null)) {
					String variable = (String) variablesBetween[j];
					Object value = variablesBetween[j + 1];
					Object value2 = variablesBetween[j + 2];
					String comparator1 = (String) variablesBetween[j + 3];
					String comparator2 = (String) variablesBetween[j + 4];
					tempWhere = (tempWhere.length() == 0) ? ("(" + value + " " + comparator1 + " " + variable + " and "
							+ variable + " " + comparator2 + " " + value2 + " )")
							: (tempWhere + " AND (" + value + " " + comparator1 + " " + variable + " and " + variable
									+ " " + comparator2 + " " + value2 + " )");
				}

				j = j + 4;
			}
		}

		if (variablesBetweenDates != null) {
			for (int k = 0; k < variablesBetweenDates.length; k++) {
				if ((variablesBetweenDates[k] != null) && (variablesBetweenDates[k + 1] != null)
						&& (variablesBetweenDates[k + 2] != null)) {
					String variable = (String) variablesBetweenDates[k];
					Object object1 = variablesBetweenDates[k + 1];
					Object object2 = variablesBetweenDates[k + 2];
					String value = null;
					String value2 = null;

					try {
						Date date1 = (Date) object1;
						Date date2 = (Date) object2;
						value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
						value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
					} catch (Exception e) {
						list = null;
						throw e;
					}

					tempWhere = (tempWhere.length() == 0) ? ("(model." + variable + " between \'" + value + "\' and \'"
							+ value2 + "\')") : (tempWhere + " AND (model." + variable + " between \'" + value
							+ "\' and \'" + value2 + "\')");
				}

				k = k + 2;
			}
		}

		if (tempWhere.length() == 0) {
			where = null;
		} else {
			where = "(" + tempWhere + ")";
		}

		try {
			list = cueConfiguracionDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}
}
