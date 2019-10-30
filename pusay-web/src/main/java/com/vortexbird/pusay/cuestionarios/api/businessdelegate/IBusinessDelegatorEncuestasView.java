package com.vortexbird.pusay.cuestionarios.api.businessdelegate;

import java.util.Date;
import java.util.List;

import com.vortexbird.pusay.cuestionarios.api.dto.CueCategoriaDTO;
import com.vortexbird.pusay.cuestionarios.api.dto.CueConfiguracionDTO;
import com.vortexbird.pusay.cuestionarios.api.dto.CueContactoDTO;
import com.vortexbird.pusay.cuestionarios.api.dto.CueCuestionarioDTO;
import com.vortexbird.pusay.cuestionarios.api.dto.CueCuestionarioTipoDTO;
import com.vortexbird.pusay.cuestionarios.api.dto.CueEstadoDTO;
import com.vortexbird.pusay.cuestionarios.api.dto.CueListaContactoDTO;
import com.vortexbird.pusay.cuestionarios.api.dto.CueListaCuestionarioDTO;
import com.vortexbird.pusay.cuestionarios.api.dto.CueListaDTO;
import com.vortexbird.pusay.cuestionarios.api.dto.CueNavegacionDTO;
import com.vortexbird.pusay.cuestionarios.api.dto.CueOpcionDTO;
import com.vortexbird.pusay.cuestionarios.api.dto.CuePreguntaDTO;
import com.vortexbird.pusay.cuestionarios.api.dto.CueResponsableDTO;
import com.vortexbird.pusay.cuestionarios.api.dto.CueRespuestaDTO;
import com.vortexbird.pusay.cuestionarios.model.CueCategoria;
import com.vortexbird.pusay.cuestionarios.model.CueConfiguracion;
import com.vortexbird.pusay.cuestionarios.model.CueContacto;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionario;
import com.vortexbird.pusay.cuestionarios.model.CueCuestionarioTipo;
import com.vortexbird.pusay.cuestionarios.model.CueEstado;
import com.vortexbird.pusay.cuestionarios.model.CueLista;
import com.vortexbird.pusay.cuestionarios.model.CueListaContacto;
import com.vortexbird.pusay.cuestionarios.model.CueListaCuestionario;
import com.vortexbird.pusay.cuestionarios.model.CueNavegacion;
import com.vortexbird.pusay.cuestionarios.model.CueOpcion;
import com.vortexbird.pusay.cuestionarios.model.CuePregunta;
import com.vortexbird.pusay.cuestionarios.model.CueResponsable;
import com.vortexbird.pusay.cuestionarios.model.CueRespuesta;

public interface IBusinessDelegatorEncuestasView {

	public abstract List<CueCategoria> getCueCategoria() throws Exception;

	public abstract CueCategoria findCategoriaByCuestionario(Long cuestionarioId)
			throws Exception;

	public abstract void saveCueCategoria(Long consecutivo, String descripcion,
			Long estado, String nombre, Long consecutivo_CueCuestionario)
			throws Exception;

	public abstract void deleteCueCategoria(Long consecutivo) throws Exception;

	public abstract void updateCueCategoria(Long consecutivo,
			String descripcion, Long estado, String nombre,
			Long consecutivo_CueCuestionario) throws Exception;

	public abstract CueCategoria getCueCategoria(Long consecutivo)
			throws Exception;

	public abstract List<CueCategoria> findByCriteriaInCueCategoria(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public abstract List<CueCategoria> findPageCueCategoria(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public abstract Long findTotalNumberCueCategoria() throws Exception;

	public abstract List<CueCategoriaDTO> getDataCueCategoria()
			throws Exception;

	public abstract List<CueConfiguracion> getCueConfiguracion()
			throws Exception;

	public abstract void saveCueConfiguracion(CueConfiguracion configuracion)
			throws Exception;

	public abstract void deleteCueConfiguracion(Long consecutivo)
			throws Exception;

	public abstract void updateCueConfiguracion(CueConfiguracion configuracion)
			throws Exception;

	public abstract CueConfiguracion getCueConfiguracion(Long consecutivo)
			throws Exception;

	public abstract List<CueConfiguracion> findByCriteriaInCueConfiguracion(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public abstract List<CueConfiguracion> findPageCueConfiguracion(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public abstract Long findTotalNumberCueConfiguracion() throws Exception;

	public abstract List<CueConfiguracionDTO> getDataCueConfiguracion()
			throws Exception;

	public abstract List<CueContacto> getCueContacto() throws Exception;

	public abstract void saveCueContacto(String apellido, String celular,
			String email, String empresa, Long estado, Long identificacion,
			String nombre) throws Exception;

	public abstract void deleteCueContacto(Long identificacion)
			throws Exception;

	public abstract void updateCueContacto(String apellido, String celular,
			String email, String empresa, Long estado, Long identificacion,
			String nombre) throws Exception;

	public abstract CueContacto getCueContacto(Long identificacion)
			throws Exception;

	public abstract List<CueContacto> findByCriteriaInCueContacto(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public abstract List<CueContacto> findPageCueContacto(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public abstract Long findTotalNumberCueContacto() throws Exception;

	public abstract List<CueContactoDTO> getDataCueContacto() throws Exception;

	public abstract List<CueCuestionario> getCueCuestionario() throws Exception;

	public abstract void saveCueCuestionario(CueCuestionario cuestionario)
			throws Exception;

	public abstract void deleteCueCuestionario(Long consecutivo)
			throws Exception;

	public abstract void updateCueCuestionario(CueCuestionario cuestionario)
			throws Exception;

	public abstract CueCuestionario getCueCuestionario(Long consecutivo)
			throws Exception;

	public abstract List<CueCuestionario> findByCriteriaInCueCuestionario(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public abstract List<CueCuestionario> findPageCueCuestionario(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public abstract Long findTotalNumberCueCuestionario() throws Exception;

	public abstract List<CueCuestionarioDTO> getDataCueCuestionario()
			throws Exception;

	public abstract List<CueCuestionarioTipo> getCueCuestionarioTipo()
			throws Exception;

	public abstract void saveCueCuestionarioTipo(
			CueCuestionarioTipo cuestionarioTipo) throws Exception;

	public abstract void deleteCueCuestionarioTipo(Long consecutivo)
			throws Exception;

	public abstract void updateCueCuestionarioTipo(
			CueCuestionarioTipo cuestionarioTipo) throws Exception;

	public abstract CueCuestionarioTipo getCueCuestionarioTipo(Long consecutivo)
			throws Exception;

	public abstract List<CueCuestionarioTipo> findByCriteriaInCueCuestionarioTipo(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public abstract List<CueCuestionarioTipo> findPageCueCuestionarioTipo(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public abstract Long findTotalNumberCueCuestionarioTipo() throws Exception;

	public abstract List<CueCuestionarioTipoDTO> getDataCueCuestionarioTipo()
			throws Exception;

	public abstract List<CueEstado> getCueEstado() throws Exception;

	public abstract void saveCueEstado(CueEstado estado) throws Exception;

	public abstract void deleteCueEstado(Long consecutivo) throws Exception;

	public abstract void updateCueEstado(CueEstado estado) throws Exception;

	public abstract CueEstado getCueEstado(Long consecutivo) throws Exception;

	public abstract List<CueEstado> findByCriteriaInCueEstado(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public abstract List<CueEstado> findPageCueEstado(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public abstract Long findTotalNumberCueEstado() throws Exception;

	public abstract List<CueEstadoDTO> getDataCueEstado() throws Exception;

	public abstract List<CueLista> getCueLista() throws Exception;

	public abstract void saveCueLista(Long consecutivo, String descripcion,
			Long estado, String nombre) throws Exception;

	public abstract void deleteCueLista(Long consecutivo) throws Exception;

	public abstract void updateCueLista(Long consecutivo, String descripcion,
			Long estado, String nombre) throws Exception;

	public abstract CueLista getCueLista(Long consecutivo) throws Exception;

	public abstract List<CueLista> findByCriteriaInCueLista(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public abstract List<CueLista> findPageCueLista(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public abstract Long findTotalNumberCueLista() throws Exception;

	public abstract List<CueListaDTO> getDataCueLista() throws Exception;

	public abstract List<CueListaContacto> getCueListaContacto()
			throws Exception;

	public abstract void saveCueListaContacto(Long consecutivo, Long duracion,
			Long estado, Date fechaHoraAsignacion, Date fechaHoraFinalizacion,
			Long puntajeTotal, Long identificacion_CueContacto,
			Long consecutivo_CueLista) throws Exception;

	public abstract void deleteCueListaContacto(Long consecutivo)
			throws Exception;

	public abstract void updateCueListaContacto(Long consecutivo,
			Long duracion, Long estado, Date fechaHoraAsignacion,
			Date fechaHoraFinalizacion, Long puntajeTotal,
			Long identificacion_CueContacto, Long consecutivo_CueLista)
			throws Exception;

	public abstract CueListaContacto getCueListaContacto(Long consecutivo)
			throws Exception;

	public abstract List<CueListaContacto> findByCriteriaInCueListaContacto(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public abstract List<CueListaContacto> findPageCueListaContacto(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public abstract Long findTotalNumberCueListaContacto() throws Exception;

	public abstract List<CueListaContactoDTO> getDataCueListaContacto()
			throws Exception;

	public abstract List<CueListaCuestionario> getCueListaCuestionario()
			throws Exception;

	public abstract void saveCueListaCuestionario(Long consecutivo,
			Date fechaHoraAsignacion, Long consecutivo_CueCuestionario,
			Long consecutivo_CueLista) throws Exception;

	public abstract void deleteCueListaCuestionario(Long consecutivo)
			throws Exception;

	public abstract void updateCueListaCuestionario(Long consecutivo,
			Date fechaHoraAsignacion, Long consecutivo_CueCuestionario,
			Long consecutivo_CueLista) throws Exception;

	public abstract CueListaCuestionario getCueListaCuestionario(
			Long consecutivo) throws Exception;

	public abstract List<CueListaCuestionario> findByCriteriaInCueListaCuestionario(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public abstract List<CueListaCuestionario> findPageCueListaCuestionario(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public abstract Long findTotalNumberCueListaCuestionario() throws Exception;

	public abstract List<CueListaCuestionarioDTO> getDataCueListaCuestionario()
			throws Exception;

	public abstract List<CueNavegacion> getCueNavegacion() throws Exception;

	public abstract void saveCueNavegacion(Long consecutivo,
			Long consecutivo_CueCuestionario, Long consecutivo_CueOpcion,
			Long consecutivo_CuePreguntaOrigen,
			Long consecutivo_CuePreguntaDestino) throws Exception;

	public abstract void deleteCueNavegacion(Long consecutivo) throws Exception;

	public abstract void updateCueNavegacion(Long consecutivo,
			Long consecutivo_CueCuestionario, Long consecutivo_CueOpcion,
			Long consecutivo_CuePreguntaOrigen,
			Long consecutivo_CuePreguntaDestino) throws Exception;

	public abstract CueNavegacion getCueNavegacion(Long consecutivo)
			throws Exception;

	public abstract List<CueNavegacion> findByCriteriaInCueNavegacion(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public abstract List<CueNavegacion> findPageCueNavegacion(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public abstract Long findTotalNumberCueNavegacion() throws Exception;

	public abstract List<CueNavegacionDTO> getDataCueNavegacion()
			throws Exception;

	public abstract List<CueOpcion> getCueOpcion() throws Exception;

	public abstract void saveCueOpcion(String condicion, Long consecutivo,
			String enunciado, Long estado, Long indicadorCorrecta,
			String labelAmpliacion, Long orden, Long puntaje,
			Long requiereAmpliacion, Long consecutivo_CuePregunta)
			throws Exception;

	public abstract void deleteCueOpcion(Long consecutivo) throws Exception;

	public abstract void updateCueOpcion(String condicion, Long consecutivo,
			String enunciado, Long estado, Long indicadorCorrecta,
			String labelAmpliacion, Long orden, Long puntaje,
			Long requiereAmpliacion, Long consecutivo_CuePregunta)
			throws Exception;

	public abstract CueOpcion getCueOpcion(Long consecutivo) throws Exception;

	public abstract List<CueOpcion> findByCriteriaInCueOpcion(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public abstract List<CueOpcion> findPageCueOpcion(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public abstract Long findTotalNumberCueOpcion() throws Exception;

	public abstract List<CueOpcionDTO> getDataCueOpcion() throws Exception;

	public abstract List<CuePregunta> getCuePregunta() throws Exception;

	public abstract void deleteCuePregunta(Long consecutivo) throws Exception;

	public abstract CuePregunta getCuePregunta(Long consecutivo)
			throws Exception;

	public abstract List<CuePregunta> findByCriteriaInCuePregunta(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public abstract List<CuePregunta> findPageCuePregunta(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public abstract Long findTotalNumberCuePregunta() throws Exception;

	public abstract List<CuePreguntaDTO> getDataCuePregunta() throws Exception;

	public abstract List<CueResponsable> getCueResponsable() throws Exception;

	public abstract void saveCueResponsable(CueResponsable responsable)
			throws Exception;

	public abstract void deleteCueResponsable(Long identificacion)
			throws Exception;

	public abstract void updateCueResponsable(CueResponsable responsable)
			throws Exception;

	public abstract CueResponsable getCueResponsable(Long identificacion)
			throws Exception;

	public abstract List<CueResponsable> findByCriteriaInCueResponsable(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public abstract List<CueResponsable> findPageCueResponsable(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public abstract Long findTotalNumberCueResponsable() throws Exception;

	public abstract List<CueResponsableDTO> getDataCueResponsable()
			throws Exception;

	public abstract List<CueRespuesta> getCueRespuesta() throws Exception;

	public abstract void saveCueRespuesta(CueRespuesta respuesta)
			throws Exception;

	public abstract void deleteCueRespuesta(Long consecutivo) throws Exception;

	public abstract void updateCueRespuesta(CueRespuesta respuesta)
			throws Exception;

	public abstract CueRespuesta getCueRespuesta(Long consecutivo)
			throws Exception;

	public abstract List<CueRespuesta> findByCriteriaInCueRespuesta(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public abstract List<CueRespuesta> findPageCueRespuesta(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public abstract Long findTotalNumberCueRespuesta() throws Exception;

	public abstract List<CueRespuestaDTO> getDataCueRespuesta()
			throws Exception;

	public abstract void saveCueCategoria(CueCategoria cueCategoria)
			throws Exception;

	public abstract void updateCueCategoria(CueCategoria cueCategoria)
			throws Exception;

	public abstract void updateCuePregunta(CuePregunta cuePregunta)
			throws Exception;

	public abstract void saveCuePregunta(CuePregunta cuePregunta)
			throws Exception;

	public abstract void saveCueOpcion(CueOpcion cueOpcion) throws Exception;

	public abstract void updateCueOpcion(CueOpcion cueOpcion) throws Exception;

	public abstract List<CueOpcion> getCueOpcionPregunta(Long consecutivo)
			throws Exception;

	public abstract void saveCueLista(CueLista cueLista) throws Exception;

	public abstract void updateCueLista(CueLista cueLista) throws Exception;

	public abstract List<CueRespuesta> getCueRespuestaPorUsuario();

	public abstract void saveCueContacto(CueContacto cueContacto)
			throws Exception;

	public abstract void updateCueContacto(CueContacto cueContacto)
			throws Exception;

	public abstract List<CueListaContacto> getCueListaContactoLista(
			Long consecutivo) throws Exception;

	public abstract List<CuePregunta> findPreguntasByCategoria(Long consecutivo)
			throws Exception;

	public abstract void saveCueListaContacto(
			CueListaContacto selectedCueListaContacto) throws Exception;

	public abstract List<CueEstado> getCueEstadoActivo() throws Exception;

	public abstract List<CueCategoria> getCueCategoriaCuestionario(
			Long consecutivo) throws Exception;

	public abstract List<CuePregunta> getCuePreguntaCategoria(Long consecutivo)
			throws Exception;

	public abstract void saveCueListaCuestionario(
			CueListaCuestionario listaCuestionario) throws Exception;

	public abstract void updateCueListaCuestionario(
			CueListaCuestionario cueListaCuestionario) throws Exception;

	public abstract List<CueListaCuestionario> getCueListaCuestionarioPorCuestionario(
			Long consecutivo) throws Exception;

	public abstract void action_subir_orden_pregunta(CuePregunta cuePregunta);

	public abstract void action_bajar_orden_pregunta(CuePregunta cuePregunta);

	public abstract void action_subir_orden_opcion(CueOpcion cueOpcion);

	public abstract void action_bajar_orden_opcion(CueOpcion cueOpcion);

	public abstract void updateCueListaContacto(CueListaContacto listaContacto)
			throws Exception;

	public abstract void reiniciarPregunta();

	public abstract void deleteRespuestas(CueListaContacto cuelistaContacto);

	public abstract CueRespuesta getCueRespuesta(Long listaContacto,
			Long codigoOpcion);

	public abstract CueListaContacto getCueListaContacto(Long lista,
			Long identificacion, Long pestCodigo);

	public abstract List<CueContacto> getCueContactosActivos();

	public abstract List<CueCuestionarioTipo> getCueCuestionarioTiposActivos();

	public abstract List<CueLista> getCueListasAtivas();

	public abstract List<CueCategoria> getCueCategoriaCuestionarioActivos(
			Long consecutivoCuestionario);

	public abstract List<CueCuestionario> getCueCuestionariosActivos();

	public abstract List<CuePregunta> findPreguntasActivasByCategoria(
			Long consecutivo);

}