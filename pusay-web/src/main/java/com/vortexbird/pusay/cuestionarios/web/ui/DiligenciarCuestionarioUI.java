/**
 * 
 */
package com.vortexbird.pusay.cuestionarios.web.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import com.vortexbird.pusay.cuestionarios.api.exceptions.ZMessManager;
import com.vortexbird.pusay.cuestionarios.model.CueCategoria;
import com.vortexbird.pusay.cuestionarios.model.CueContacto;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionario;
import com.vortexbird.pusay.cuestionarios.model.CueLista;
import com.vortexbird.pusay.cuestionarios.model.CueListaContacto;
import com.vortexbird.pusay.cuestionarios.model.CueOpcion;
import com.vortexbird.pusay.cuestionarios.model.CuePregunta;
import com.vortexbird.pusay.cuestionarios.model.CueRespuesta;
import com.vortexbird.pusay.cuestionarios.api.businessdelegate.IBusinessDelegatorEncuestasView;
import com.vortexbird.pusay.cuestionarios.web.paginator.PreguntaDataModel;
import com.vortexbird.pusay.cuestionarios.web.utils.AbstractBaseMessageUI;
import com.vortexbird.pusay.cuestionarios.web.utils.FacesUtils;

/**
 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
 * @project cuestionario-web
 * @class DiligenciarCuestionarioUI
 * @date 23/07/2013
 * 
 */
@ManagedBean
@ViewScoped
public class DiligenciarCuestionarioUI extends AbstractBaseMessageUI {

	private CueCuestionario cuestionario;
	private CueCategoria categoria;
	private CueContacto contacto;
	private CueLista lista;
	@ManagedProperty(value = "#{BusinessDelegatorEncuestasView}")
	private IBusinessDelegatorEncuestasView businessDelegatorView;
	private Long codigoOpcion;
	private Long cuestionarioId;
	private Long listaContacto;
	private Long codigoLista;
	private Long identificacion;
	private String nombre;
	private String apellido;
	private String correo;
	private Long pestCodigo;
	private List<CuePregunta> listPreguntas;
	private PreguntaDataModel dataPreguntaDataModel;
	private CuePregunta[] selectedListPreguntas;
	private CueOpcion[] selectedListOpciones;
	private CueListaContacto cuelistaContacto;
	private String mensajeDiligenciado;
	private boolean cuestionarioIncompleto = false;
	private boolean pintarInfoCuestionario1 = false;
	private boolean pintarInfoCuestionario2 = false;
	private boolean pintarInfoCuestionario3 = false;

	private boolean renderEncuesta = true;

	public DiligenciarCuestionarioUI() {

	}

	@PostConstruct
	public void init() {
		try {
			/**
			 * Se obtienen los datos con la integraci�n del proveedor
			 */
			
			this.cuestionarioId = FacesUtils.getRequestParameter("cuestionarioId") != null ? new Long(
					FacesUtils.getRequestParameter("cuestionarioId")) : null;
			this.listaContacto = FacesUtils.getRequestParameter("listaContacto") != null ? new Long(
					FacesUtils.getRequestParameter("listaContacto")) : null;
			this.codigoLista = FacesUtils.getRequestParameter("codigoLista") != null ? new Long(
					FacesUtils.getRequestParameter("codigoLista")) : null;
			this.identificacion = FacesUtils.getRequestParameter("identificacion") != null ? new Long(
					FacesUtils.getRequestParameter("identificacion")) : null;
			this.nombre = FacesUtils.getRequestParameter("nombre") != null ? new String(
					FacesUtils.getRequestParameter("nombre")) : null;
			this.apellido = FacesUtils.getRequestParameter("apellido") != null ? new String(
					FacesUtils.getRequestParameter("apellido")) : null;
			this.correo = FacesUtils.getRequestParameter("correo") != null ? new String(
					FacesUtils.getRequestParameter("correo")) : null;
			this.pestCodigo = FacesUtils.getRequestParameter("pestCodigo") != null ? new Long(
					FacesUtils.getRequestParameter("pestCodigo")) : null;	
			
			/*Se valida que tipo de cuestionario es para mirar que informacion se renderiza*/		
			if(this.cuestionarioId == 1){
				pintarInfoCuestionario1 = true;
				setPintarInfoCuestionario1(true);
				pintarInfoCuestionario2 = false;
				setPintarInfoCuestionario2(false);
				pintarInfoCuestionario3 = false;
				setPintarInfoCuestionario3(false);
			}else if(this.cuestionarioId == 2){
				pintarInfoCuestionario1 = false;
				setPintarInfoCuestionario1(false);
				pintarInfoCuestionario2 = true;
				setPintarInfoCuestionario2(true);
				pintarInfoCuestionario3 = false;
				setPintarInfoCuestionario3(false);
			}else if(this.cuestionarioId == 3){
				pintarInfoCuestionario1 = false;
				setPintarInfoCuestionario1(false);
				pintarInfoCuestionario2 = false;
				setPintarInfoCuestionario2(false);
				pintarInfoCuestionario3 = true;
				setPintarInfoCuestionario3(true);
			}
			
			action_cargarCuestionario();

		} catch (Exception e) {
			error(e);
		}
	}

	public void action_cargarCuestionario() {
		try {
			/**
			 * Se debe crear el contacto con los datos enviados por request.
			 */
			contacto = new CueContacto();
			contacto.setIdentificacion(this.identificacion);
			contacto.setNombre(this.nombre);
			contacto.setApellido(this.apellido);
			contacto.setEmail(this.correo);
			contacto.setEstado(1l);
			businessDelegatorView.saveCueContacto(contacto);

			/**
			 * Se debe consultar la lista en donde se encuentre el formulario a diligenciar y luego crear la lista
			 * contacto con la lista y la informaci�n del encuestado
			 */
			if (this.cuestionarioId == null) {
				throw new Exception(getMessage("i18n.messages", "MSG_NO_CUESTIONARIO"));
			}

			this.cuestionario = this.businessDelegatorView.getCueCuestionario(this.cuestionarioId);
			this.categoria = this.businessDelegatorView.findCategoriaByCuestionario(this.cuestionarioId);
			this.listPreguntas = this.businessDelegatorView.findPreguntasActivasByCategoria(this.categoria.getConsecutivo());
			this.lista = this.businessDelegatorView.getCueLista(codigoLista);

			/**
			 * Se debe validar si la encuesta se encuentra en estado activa y que la fecha actual se encuentre dentro
			 * del rango de fechas permitidas para la encuesta.
			 */
			if (!cuestionario.getCueEstado().getNombre().toString().toUpperCase().equals("ACTIVO")) {
				renderEncuesta = false;
				mensajeDiligenciado = ZMessManager.CUESTIONARIO_INACTIVO;
			} else {
				/**
				 * Se obtienen las fechas de vigencia.
				 */
				if (cuestionario.getCueConfiguracion() == null) {
					renderEncuesta = false;
					mensajeDiligenciado = ZMessManager.NO_CONFIGURACION;
				}

				Long fechaActual = new Date().getTime();
				Long fechaInicio = cuestionario.getCueConfiguracion().getVigenciaInicio().getTime();
				Long fechaFin = cuestionario.getCueConfiguracion().getVigenciaFin().getTime();

				if (fechaActual >= fechaInicio && fechaActual <= fechaFin) {

					/**
					 * TODO Se debe validar si la encuesta ya fue diligenciada. Esto se valida en la tabla Lista
					 * Cuestinario donde el campos ESTADO = 2. Estados: 0 Creadra - 1 En Proceso - 2 Finalizada
					 */
					cuelistaContacto = businessDelegatorView.getCueListaContacto(codigoLista, identificacion, pestCodigo);

					if (cuelistaContacto == null) {
						cuelistaContacto = new CueListaContacto();
						cuelistaContacto.setCueContacto(contacto);
						cuelistaContacto.setEstado(1l);
						cuelistaContacto.setFechaHoraAsignacion(new Date());
						cuelistaContacto.setCueLista(lista);
						cuelistaContacto.setPestCodigo(pestCodigo);
						businessDelegatorView.saveCueListaContacto(cuelistaContacto);
					}

					cuelistaContacto = businessDelegatorView.getCueListaContacto(codigoLista, identificacion, pestCodigo);
					listaContacto = cuelistaContacto.getConsecutivo();

					if (cuelistaContacto != null && cuelistaContacto.getEstado().equals(2l)) {
						renderEncuesta = false;
						mensajeDiligenciado = ZMessManager.CUESTIONARIO_DILIGENCIADO;
					} else {
						renderEncuesta = true;
						mensajeDiligenciado = "";
					}

					processOpcionesByPregunta(this.listPreguntas);
				} else {
					renderEncuesta = false;
					mensajeDiligenciado = ZMessManager.CUESTIONARIO_NOVIGENTE;
				}
			}

		} catch (Exception e) {
			error(e);
		}
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param list
	 */
	private void processOpcionesByPregunta(List<CuePregunta> list) {
		List<CueOpcion> listOpciones = null;
		CueRespuesta cueRespuestaTmp = null;
		List<Long> listaOpcionesSeleccionadas = null;

		try {
			for (CuePregunta pregunta : list) {

				/**
				 * Valido si la pregunta es de multiple seleccion
				 */
				if (pregunta != null && pregunta.getNroRespuestas() > 1) {
					listaOpcionesSeleccionadas = new ArrayList<Long>();
				}

				listOpciones = new ArrayList<CueOpcion>();

				for (Iterator<CueOpcion> iterator = pregunta.getCueOpcions().iterator(); iterator.hasNext();) {
					CueOpcion opcion = (CueOpcion) iterator.next();
					if (opcion.getEstado().equals(new Long(1L))) {
						codigoOpcion = opcion.getConsecutivo();
						listOpciones.add(opcion);

						cueRespuestaTmp = businessDelegatorView.getCueRespuesta(listaContacto, codigoOpcion);

						if (cueRespuestaTmp != null && pregunta != null && pregunta.getNroRespuestas() > 1) {
							// listaOpcionesSeleccionadas.add(cueRespuestaTmp.getConsecutivo());
							listaOpcionesSeleccionadas.add(cueRespuestaTmp.getCueOpcion().getConsecutivo());
						} else if (cueRespuestaTmp != null) {
							pregunta.setSelectedOpcion(cueRespuestaTmp.getCueOpcion().getConsecutivo());
						}

						if (opcion.getRequiereAmpliacion().equals(new Long(1)) && cueRespuestaTmp != null
								&& cueRespuestaTmp.getRespuestaAmpliacion() != null) {
							pregunta.setRequiereAmpliacion(true);
							pregunta.setRespuestaAmpliacion(cueRespuestaTmp.getRespuestaAmpliacion());
						}
					}
				}
				pregunta.setSelectedOptions(listaOpcionesSeleccionadas);
				Collections.sort(listOpciones);
				pregunta.setListOpciones(listOpciones);
			}
			this.listPreguntas = list;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date Oct 4, 2013
	 * @param event
	 */
	public void actionListenerValidateAmpliacion(ValueChangeEvent event) {
		if (event.getNewValue() != null && !event.getNewValue().equals(event.getOldValue())) {
			for (CuePregunta pregunta : this.listPreguntas) {
				for (CueOpcion opcion : pregunta.getCueOpcions()) {
					if (opcion.getConsecutivo().equals((Long) event.getNewValue())) {
						pregunta.setRequiereAmpliacion(opcion.getRequiereAmpliacion().equals(1L) ? true : false);
						if (!pregunta.isRequiereAmpliacion()) {
							pregunta.setRespuestaAmpliacion(null);
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 24/07/2013
	 * @return
	 */
	public String actionSave() {
		try {
			if (this.listPreguntas == null || this.listPreguntas.size() < 1) {
				throw new Exception(getMessage("i18n.messages", "MSG_NO_PREGUNTAS"));
			}

			boolean encuestaFinalizada = false;
			boolean guardaRespuesta = false;
			int contadoresRespuestasAlmacenadas = 0;
			Long puntajeTotal = 0l;

			businessDelegatorView.deleteRespuestas(cuelistaContacto);
			List<Long> listaOpcionAnt = null;

			for (CuePregunta pregunta : this.listPreguntas) {
				CueRespuesta respuesta = null;

				/**
				 * Se valida que se hayan seleccionado opciones de respuesta
				 */
				if (pregunta.getSelectedOptions() != null && pregunta.getSelectedOptions().size() > 0) {
					List<Long> listaOpcion = pregunta.getSelectedOptions();

					if (listaOpcionAnt == null || !listaOpcionAnt.equals(listaOpcion)) {
						guardaRespuesta = true;
					}
					listaOpcionAnt = listaOpcion;

					for (int i = 0; i < listaOpcion.size(); i++) {
						respuesta = new CueRespuesta();
						String obj = "" + listaOpcion.get(i);

						respuesta.setCueOpcion(pregunta.getOpcionSelected(new Long(obj)));
						respuesta.setFechaHoraRespuesta(new Date());
						respuesta.setCueListaContacto(cuelistaContacto);
						/**
						 * TODO Asignar IP del encuestado
						 */
						respuesta.setIp(getIP());
						if (pregunta.isRequiereAmpliacion()) {
							respuesta.setRespuestaAmpliacion(pregunta.getRespuestaAmpliacion());
						}

						this.businessDelegatorView.saveCueRespuesta(respuesta);

						/**
						 * Se debe consultar el puntaje de la opción seleccionada.
						 */
						if (respuesta.getCueOpcion() != null) {
							CueOpcion opcion = businessDelegatorView.getCueOpcion(respuesta.getCueOpcion()
									.getConsecutivo());
							if (opcion != null) {
								puntajeTotal = puntajeTotal + opcion.getPuntaje();
							}
						}
					}
				}

				if (pregunta.getSelectedOpcion() != null && !pregunta.getSelectedOpcion().equals(0l)) {
					respuesta = new CueRespuesta();
					respuesta.setCueOpcion(pregunta.getOpcionSelected(pregunta.getSelectedOpcion()));
					respuesta.setFechaHoraRespuesta(new Date());
					respuesta.setCueListaContacto(cuelistaContacto);
					/**
					 * TODO Asignar IP del encuestado
					 */
					respuesta.setIp(getIP());
					if (pregunta.isRequiereAmpliacion()) {
						respuesta.setRespuestaAmpliacion(pregunta.getRespuestaAmpliacion());
					}
					this.businessDelegatorView.saveCueRespuesta(respuesta);
					guardaRespuesta = true;

					/**
					 * Se debe consultar el puntaje de la opción seleccionada.
					 */
					if (respuesta.getCueOpcion() != null) {
						CueOpcion opcion = businessDelegatorView
								.getCueOpcion(respuesta.getCueOpcion().getConsecutivo());
						if (opcion != null) {
							puntajeTotal = puntajeTotal + opcion.getPuntaje();
						}
					}
				}

				if (guardaRespuesta) {
					contadoresRespuestasAlmacenadas++;
				}
				guardaRespuesta = false;

				/**
				 * Se valida el procesamiento del todo el cuestionario
				 */
				if (contadoresRespuestasAlmacenadas == listPreguntas.size()) {
					/**
					 * Se debe realizar el cierre del cuestionario.
					 */
					cuelistaContacto.setFechaHoraFinalizacion(new Date());
					/**
					 * Se le asigna el estado 2 que quiere decir que el registro está finalizado
					 * 
					 * Editado 28/10/2015 : Se cambia el estado del cuestionario de 2 a 1 para
					 * que siempre este activo y pueda editarse a peticion del usuario Edgar Quiñonez.
					 */
					cuelistaContacto.setEstado(1l);
					cuelistaContacto.setPuntajeTotal(puntajeTotal);
					cuelistaContacto.setCueContacto(contacto);
					businessDelegatorView.updateCueListaContacto(cuelistaContacto);
					encuestaFinalizada = true;
				} else {
					cuelistaContacto.setPuntajeTotal(puntajeTotal);
					businessDelegatorView.updateCueListaContacto(cuelistaContacto);
				}
			}
			
			if(contadoresRespuestasAlmacenadas == 0) {
				FacesUtils.addErrorMessage(ZMessManager.CUESTIONARIO_NO_RESPUESTAS_ALMACENADO);
			} else {
				FacesUtils.addInfoMessage(ZMessManager.CUESTIONARIO_SUCCESFULLYSAVED);
				contadoresRespuestasAlmacenadas = 0;
			}

			if (encuestaFinalizada) {
				renderEncuesta = false;
				return "goFinalizado";
			}

		} catch (Exception e) {
			e.printStackTrace();
			error(e);
		}
		return "";
	}

	public String getIP() {
		String ipAddress = "";
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
		} catch (Exception e) {
			throw new FacesException(e);
		}
		return ipAddress;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the cuestionario
	 */
	public CueCuestionario getCuestionario() {
		return cuestionario;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param cuestionario
	 *            the cuestionario to set
	 */
	public void setCuestionario(CueCuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the businessDelegatorView
	 */
	public IBusinessDelegatorEncuestasView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param businessDelegatorView
	 *            the businessDelegatorView to set
	 */
	public void setBusinessDelegatorView(IBusinessDelegatorEncuestasView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the listPreguntas
	 */
	public List<CuePregunta> getListPreguntas() {
		return listPreguntas;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param listPreguntas
	 *            the listPreguntas to set
	 */
	public void setListPreguntas(List<CuePregunta> listPreguntas) {
		this.listPreguntas = listPreguntas;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the dataPreguntaDataModel
	 */
	public PreguntaDataModel getDataPreguntaDataModel() {
		if (this.dataPreguntaDataModel == null) {
			this.dataPreguntaDataModel = new PreguntaDataModel(this.listPreguntas);
		}
		return this.dataPreguntaDataModel;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param dataPreguntaDataModel
	 *            the dataPreguntaDataModel to set
	 */
	public void setDataPreguntaDataModel(PreguntaDataModel dataPreguntaDataModel) {
		this.dataPreguntaDataModel = dataPreguntaDataModel;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @return the selectedListPreguntas
	 */
	public CuePregunta[] getSelectedListPreguntas() {
		return selectedListPreguntas;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 23/07/2013
	 * @param selectedListPreguntas
	 *            the selectedListPreguntas to set
	 */
	public void setSelectedListPreguntas(CuePregunta[] selectedListPreguntas) {
		this.selectedListPreguntas = selectedListPreguntas;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 24/07/2013
	 * @return the selectedListOpciones
	 */
	public CueOpcion[] getSelectedListOpciones() {
		return selectedListOpciones;
	}

	/**
	 * @author <a href="mailto:mgalvez@vortexbird.com">Manuel Alejandro Galvez</a>
	 * @date 24/07/2013
	 * @param selectedListOpciones
	 *            the selectedListOpciones to set
	 */
	public void setSelectedListOpciones(CueOpcion[] selectedListOpciones) {
		this.selectedListOpciones = selectedListOpciones;
	}

	public boolean isRenderEncuesta() {
		return renderEncuesta;
	}

	public void setRenderEncuesta(boolean renderEncuesta) {
		this.renderEncuesta = renderEncuesta;
	}

	public String getMensajeDiligenciado() {
		return mensajeDiligenciado;
	}

	public void setMensajeDiligenciado(String mensajeDiligenciado) {
		this.mensajeDiligenciado = mensajeDiligenciado;
	}

	public CueContacto getContacto() {
		return contacto;
	}

	public void setContacto(CueContacto contacto) {
		this.contacto = contacto;
	}

	public Long getPestCodigo() {
		return pestCodigo;
	}

	public void setPestCodigo(Long pestCodigo) {
		this.pestCodigo = pestCodigo;
	}

	public boolean isPintarInfoCuestionario1() {
		return pintarInfoCuestionario1;
	}

	public void setPintarInfoCuestionario1(boolean pintarInfoCuestionario1) {
		this.pintarInfoCuestionario1 = pintarInfoCuestionario1;
	}

	public boolean isPintarInfoCuestionario2() {
		return pintarInfoCuestionario2;
	}

	public void setPintarInfoCuestionario2(boolean pintarInfoCuestionario2) {
		this.pintarInfoCuestionario2 = pintarInfoCuestionario2;
	}

	public boolean isPintarInfoCuestionario3() {
		return pintarInfoCuestionario3;
	}

	public void setPintarInfoCuestionario3(boolean pintarInfoCuestionario3) {
		this.pintarInfoCuestionario3 = pintarInfoCuestionario3;
	}

}