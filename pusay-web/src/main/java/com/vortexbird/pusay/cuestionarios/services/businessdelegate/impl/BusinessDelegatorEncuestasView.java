package com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.vortexbird.pusay.cuestionarios.api.businessdelegate.IBusinessDelegatorEncuestasView;
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
import com.vortexbird.pusay.cuestionarios.services.control.ICueCategoriaLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueConfiguracionLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueContactoLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueCuestionarioLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueCuestionarioTipoLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueEstadoLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueListaContactoLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueListaCuestionarioLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueListaLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueNavegacionLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueOpcionLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICuePreguntaLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueResponsableLogic;
import com.vortexbird.pusay.cuestionarios.services.control.ICueRespuestaLogic;

/**
 * Use a Business Delegate to reduce coupling between presentation-tier clients and business services. The Business
 * Delegate hides the underlying implementation details of the business service, such as lookup and access details of
 * the EJB architecture.
 * 
 * The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides, the
 * implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier
 * clients and the system's business services. Depending on the implementation strategy, the Business Delegate may
 * shield clients from possible volatility in the implementation of the business service API. Potentially, this reduces
 * the number of changes that must be made to the presentation-tier client code when the business service API or its
 * underlying implementation changes.
 * 
 * However, interface methods in the Business Delegate may still require modification if the underlying business service
 * API changes. Admittedly, though, it is more likely that changes will be made to the business service rather than to
 * the Business Delegate.
 * 
 * Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront
 * work in return for future gains. However, using this pattern or its strategies results in only a small amount of
 * additional upfront work and provides considerable benefits. The main benefit is hiding the details of the underlying
 * service. For example, the client can become transparent to naming and lookup services. The Business Delegate also
 * handles the exceptions from the business services, such as java.rmi.Remote exceptions, Java Messages Service (JMS)
 * exceptions and so on. The Business Delegate may intercept such service level exceptions and generate application
 * level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
 * The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a
 * service failure without exposing the client to the problem until it is determined that the problem is not resolvable.
 * These gains present a compelling reason to use the pattern.
 * 
 * Another benefit is that the delegate may cache results and references to remote business services. Caching can
 * significantly improve performance, because it limits unnecessary and potentially costly round trips over the network.
 * 
 * A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the
 * underlying implementation details of the business service lookup code. The Lookup Service may be written as part of
 * the Delegate, but we recommend that it be implemented as a separate component, as outlined in the Service Locator
 * pattern (See "Service Locator" on page 368.)
 * 
 * When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the
 * two. This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate
 * relating to its interaction with multiple business services (creating a one-to-many relationship) will often be
 * factored back into a Session Facade.
 * 
 * Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the
 * presentation and the business tiers.
 */
@Scope("singleton")
@Service("BusinessDelegatorEncuestasView")
public class BusinessDelegatorEncuestasView implements IBusinessDelegatorEncuestasView  {
	@Autowired
	private ICueCategoriaLogic cueCategoriaLogic;
	@Autowired
	private ICueConfiguracionLogic cueConfiguracionLogic;
	@Autowired
	private ICueContactoLogic cueContactoLogic;
	@Autowired
	private ICueCuestionarioLogic cueCuestionarioLogic;
	@Autowired
	private ICueCuestionarioTipoLogic cueCuestionarioTipoLogic;
	@Autowired
	private ICueEstadoLogic cueEstadoLogic;
	@Autowired
	private ICueListaLogic cueListaLogic;
	@Autowired
	private ICueListaContactoLogic cueListaContactoLogic;
	@Autowired
	private ICueListaCuestionarioLogic cueListaCuestionarioLogic;
	@Autowired
	private ICueNavegacionLogic cueNavegacionLogic;
	@Autowired
	private ICueOpcionLogic cueOpcionLogic;
	@Autowired
	private ICuePreguntaLogic cuePreguntaLogic;
	@Autowired
	private ICueResponsableLogic cueResponsableLogic;
	@Autowired
	private ICueRespuestaLogic cueRespuestaLogic;

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueCategoria()
	 */
	@Override
	public List<CueCategoria> getCueCategoria() throws Exception {
		return cueCategoriaLogic.getCueCategoria();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findCategoriaByCuestionario(java.lang.Long)
	 */
	@Override
	public CueCategoria findCategoriaByCuestionario(Long cuestionarioId) throws Exception {
		return this.cueCategoriaLogic.findCategoriaByCuestionario(cuestionarioId);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueCategoria(java.lang.Long, java.lang.String, java.lang.Long, java.lang.String, java.lang.Long)
	 */
	@Override
	public void saveCueCategoria(Long consecutivo, String descripcion, Long estado, String nombre,
			Long consecutivo_CueCuestionario) throws Exception {
		cueCategoriaLogic.saveCueCategoria(consecutivo, descripcion, estado, nombre, consecutivo_CueCuestionario);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#deleteCueCategoria(java.lang.Long)
	 */
	@Override
	public void deleteCueCategoria(Long consecutivo) throws Exception {
		cueCategoriaLogic.deleteCueCategoria(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueCategoria(java.lang.Long, java.lang.String, java.lang.Long, java.lang.String, java.lang.Long)
	 */
	@Override
	public void updateCueCategoria(Long consecutivo, String descripcion, Long estado, String nombre,
			Long consecutivo_CueCuestionario) throws Exception {
		cueCategoriaLogic.updateCueCategoria(consecutivo, descripcion, estado, nombre, consecutivo_CueCuestionario);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueCategoria(java.lang.Long)
	 */
	@Override
	public CueCategoria getCueCategoria(Long consecutivo) throws Exception {
		CueCategoria cueCategoria = null;

		try {
			cueCategoria = cueCategoriaLogic.getCueCategoria(consecutivo);
		} catch (Exception e) {
			throw e;
		}

		return cueCategoria;
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findByCriteriaInCueCategoria(java.lang.Object[], java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public List<CueCategoria> findByCriteriaInCueCategoria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return cueCategoriaLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findPageCueCategoria(java.lang.String, boolean, int, int)
	 */
	@Override
	public List<CueCategoria> findPageCueCategoria(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return cueCategoriaLogic.findPageCueCategoria(sortColumnName, sortAscending, startRow, maxResults);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findTotalNumberCueCategoria()
	 */
	@Override
	public Long findTotalNumberCueCategoria() throws Exception {
		return cueCategoriaLogic.findTotalNumberCueCategoria();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getDataCueCategoria()
	 */
	@Override
	public List<CueCategoriaDTO> getDataCueCategoria() throws Exception {
		return cueCategoriaLogic.getDataCueCategoria();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueConfiguracion()
	 */
	@Override
	public List<CueConfiguracion> getCueConfiguracion() throws Exception {
		return cueConfiguracionLogic.getCueConfiguracion();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueConfiguracion(com.vortexbird.pusay.cuestionarios.model.CueConfiguracion)
	 */
	@Override
	public void saveCueConfiguracion(CueConfiguracion configuracion) throws Exception {
		this.cueConfiguracionLogic.saveCueConfiguracion(configuracion);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#deleteCueConfiguracion(java.lang.Long)
	 */
	@Override
	public void deleteCueConfiguracion(Long consecutivo) throws Exception {
		this.cueConfiguracionLogic.deleteCueConfiguracion(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueConfiguracion(com.vortexbird.pusay.cuestionarios.model.CueConfiguracion)
	 */
	@Override
	public void updateCueConfiguracion(CueConfiguracion configuracion) throws Exception {
		this.cueConfiguracionLogic.updateCueConfiguracion(configuracion);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueConfiguracion(java.lang.Long)
	 */
	@Override
	public CueConfiguracion getCueConfiguracion(Long consecutivo) throws Exception {
		CueConfiguracion cueConfiguracion = null;

		try {
			cueConfiguracion = cueConfiguracionLogic.getCueConfiguracion(consecutivo);
		} catch (Exception e) {
			throw e;
		}

		return cueConfiguracion;
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findByCriteriaInCueConfiguracion(java.lang.Object[], java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public List<CueConfiguracion> findByCriteriaInCueConfiguracion(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return cueConfiguracionLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findPageCueConfiguracion(java.lang.String, boolean, int, int)
	 */
	@Override
	public List<CueConfiguracion> findPageCueConfiguracion(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return cueConfiguracionLogic.findPageCueConfiguracion(sortColumnName, sortAscending, startRow, maxResults);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findTotalNumberCueConfiguracion()
	 */
	@Override
	public Long findTotalNumberCueConfiguracion() throws Exception {
		return cueConfiguracionLogic.findTotalNumberCueConfiguracion();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getDataCueConfiguracion()
	 */
	@Override
	public List<CueConfiguracionDTO> getDataCueConfiguracion() throws Exception {
		return cueConfiguracionLogic.getDataCueConfiguracion();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueContacto()
	 */
	@Override
	public List<CueContacto> getCueContacto() throws Exception {
		return cueContactoLogic.getCueContacto();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueContacto(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String)
	 */
	@Override
	public void saveCueContacto(String apellido, String celular, String email, String empresa, Long estado,
			Long identificacion, String nombre) throws Exception {
		cueContactoLogic.saveCueContacto(apellido, celular, email, empresa, estado, identificacion, nombre);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#deleteCueContacto(java.lang.Long)
	 */
	@Override
	public void deleteCueContacto(Long identificacion) throws Exception {
		cueContactoLogic.deleteCueContacto(identificacion);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueContacto(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String)
	 */
	@Override
	public void updateCueContacto(String apellido, String celular, String email, String empresa, Long estado,
			Long identificacion, String nombre) throws Exception {
		cueContactoLogic.updateCueContacto(apellido, celular, email, empresa, estado, identificacion, nombre);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueContacto(java.lang.Long)
	 */
	@Override
	public CueContacto getCueContacto(Long identificacion) throws Exception {
		CueContacto cueContacto = null;

		try {
			cueContacto = cueContactoLogic.getCueContacto(identificacion);
		} catch (Exception e) {
			throw e;
		}

		return cueContacto;
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findByCriteriaInCueContacto(java.lang.Object[], java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public List<CueContacto> findByCriteriaInCueContacto(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return cueContactoLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findPageCueContacto(java.lang.String, boolean, int, int)
	 */
	@Override
	public List<CueContacto> findPageCueContacto(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return cueContactoLogic.findPageCueContacto(sortColumnName, sortAscending, startRow, maxResults);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findTotalNumberCueContacto()
	 */
	@Override
	public Long findTotalNumberCueContacto() throws Exception {
		return cueContactoLogic.findTotalNumberCueContacto();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getDataCueContacto()
	 */
	@Override
	public List<CueContactoDTO> getDataCueContacto() throws Exception {
		return this.cueContactoLogic.getDataCueContacto();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueCuestionario()
	 */
	@Override
	public List<CueCuestionario> getCueCuestionario() throws Exception {
		return this.cueCuestionarioLogic.getCueCuestionario();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueCuestionario(com.vortexbird.pusay.cuestionarios.model.CueCuestionario)
	 */
	@Override
	public void saveCueCuestionario(CueCuestionario cuestionario) throws Exception {
		this.cueCuestionarioLogic.saveCueCuestionario(cuestionario);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#deleteCueCuestionario(java.lang.Long)
	 */
	@Override
	public void deleteCueCuestionario(Long consecutivo) throws Exception {
		this.cueCuestionarioLogic.deleteCueCuestionario(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueCuestionario(com.vortexbird.pusay.cuestionarios.model.CueCuestionario)
	 */
	@Override
	public void updateCueCuestionario(CueCuestionario cuestionario) throws Exception {
		this.cueCuestionarioLogic.updateCueCuestionario(cuestionario);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueCuestionario(java.lang.Long)
	 */
	@Override
	public CueCuestionario getCueCuestionario(Long consecutivo) throws Exception {
		CueCuestionario cueCuestionario = null;

		try {
			cueCuestionario = cueCuestionarioLogic.getCueCuestionario(consecutivo);

			if (cueCuestionario != null) {
				Hibernate.initialize(cueCuestionario);
				Hibernate.initialize(cueCuestionario.getCueEstado());
			}
		} catch (Exception e) {
			throw e;
		}

		return cueCuestionario;
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findByCriteriaInCueCuestionario(java.lang.Object[], java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public List<CueCuestionario> findByCriteriaInCueCuestionario(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return cueCuestionarioLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findPageCueCuestionario(java.lang.String, boolean, int, int)
	 */
	@Override
	public List<CueCuestionario> findPageCueCuestionario(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return cueCuestionarioLogic.findPageCueCuestionario(sortColumnName, sortAscending, startRow, maxResults);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findTotalNumberCueCuestionario()
	 */
	@Override
	public Long findTotalNumberCueCuestionario() throws Exception {
		return cueCuestionarioLogic.findTotalNumberCueCuestionario();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getDataCueCuestionario()
	 */
	@Override
	public List<CueCuestionarioDTO> getDataCueCuestionario() throws Exception {
		return this.cueCuestionarioLogic.getDataCueCuestionario();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueCuestionarioTipo()
	 */
	@Override
	public List<CueCuestionarioTipo> getCueCuestionarioTipo() throws Exception {
		return this.cueCuestionarioTipoLogic.getCueCuestionarioTipo();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueCuestionarioTipo(com.vortexbird.pusay.cuestionarios.model.CueCuestionarioTipo)
	 */
	@Override
	public void saveCueCuestionarioTipo(CueCuestionarioTipo cuestionarioTipo) throws Exception {
		this.cueCuestionarioTipoLogic.saveCueCuestionarioTipo(cuestionarioTipo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#deleteCueCuestionarioTipo(java.lang.Long)
	 */
	@Override
	public void deleteCueCuestionarioTipo(Long consecutivo) throws Exception {
		this.cueCuestionarioTipoLogic.deleteCueCuestionarioTipo(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueCuestionarioTipo(com.vortexbird.pusay.cuestionarios.model.CueCuestionarioTipo)
	 */
	@Override
	public void updateCueCuestionarioTipo(CueCuestionarioTipo cuestionarioTipo) throws Exception {
		this.cueCuestionarioTipoLogic.updateCueCuestionarioTipo(cuestionarioTipo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueCuestionarioTipo(java.lang.Long)
	 */
	@Override
	public CueCuestionarioTipo getCueCuestionarioTipo(Long consecutivo) throws Exception {
		CueCuestionarioTipo cueCuestionarioTipo = null;

		try {
			cueCuestionarioTipo = this.cueCuestionarioTipoLogic.getCueCuestionarioTipo(consecutivo);
		} catch (Exception e) {
			throw e;
		}

		return cueCuestionarioTipo;
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findByCriteriaInCueCuestionarioTipo(java.lang.Object[], java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public List<CueCuestionarioTipo> findByCriteriaInCueCuestionarioTipo(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return cueCuestionarioTipoLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findPageCueCuestionarioTipo(java.lang.String, boolean, int, int)
	 */
	@Override
	public List<CueCuestionarioTipo> findPageCueCuestionarioTipo(String sortColumnName, boolean sortAscending,
			int startRow, int maxResults) throws Exception {
		return cueCuestionarioTipoLogic
				.findPageCueCuestionarioTipo(sortColumnName, sortAscending, startRow, maxResults);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findTotalNumberCueCuestionarioTipo()
	 */
	@Override
	public Long findTotalNumberCueCuestionarioTipo() throws Exception {
		return cueCuestionarioTipoLogic.findTotalNumberCueCuestionarioTipo();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getDataCueCuestionarioTipo()
	 */
	@Override
	public List<CueCuestionarioTipoDTO> getDataCueCuestionarioTipo() throws Exception {
		return cueCuestionarioTipoLogic.getDataCueCuestionarioTipo();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueEstado()
	 */
	@Override
	public List<CueEstado> getCueEstado() throws Exception {
		return cueEstadoLogic.getCueEstado();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueEstado(com.vortexbird.pusay.cuestionarios.model.CueEstado)
	 */
	@Override
	public void saveCueEstado(CueEstado estado) throws Exception {
		cueEstadoLogic.saveCueEstado(estado);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#deleteCueEstado(java.lang.Long)
	 */
	@Override
	public void deleteCueEstado(Long consecutivo) throws Exception {
		cueEstadoLogic.deleteCueEstado(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueEstado(com.vortexbird.pusay.cuestionarios.model.CueEstado)
	 */
	@Override
	public void updateCueEstado(CueEstado estado) throws Exception {
		cueEstadoLogic.updateCueEstado(estado);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueEstado(java.lang.Long)
	 */
	@Override
	public CueEstado getCueEstado(Long consecutivo) throws Exception {
		CueEstado cueEstado = null;

		try {
			cueEstado = cueEstadoLogic.getCueEstado(consecutivo);
		} catch (Exception e) {
			throw e;
		}

		return cueEstado;
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findByCriteriaInCueEstado(java.lang.Object[], java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public List<CueEstado> findByCriteriaInCueEstado(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return cueEstadoLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findPageCueEstado(java.lang.String, boolean, int, int)
	 */
	@Override
	public List<CueEstado> findPageCueEstado(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return cueEstadoLogic.findPageCueEstado(sortColumnName, sortAscending, startRow, maxResults);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findTotalNumberCueEstado()
	 */
	@Override
	public Long findTotalNumberCueEstado() throws Exception {
		return cueEstadoLogic.findTotalNumberCueEstado();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getDataCueEstado()
	 */
	@Override
	public List<CueEstadoDTO> getDataCueEstado() throws Exception {
		return cueEstadoLogic.getDataCueEstado();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueLista()
	 */
	@Override
	public List<CueLista> getCueLista() throws Exception {
		return cueListaLogic.getCueLista();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueLista(java.lang.Long, java.lang.String, java.lang.Long, java.lang.String)
	 */
	@Override
	public void saveCueLista(Long consecutivo, String descripcion, Long estado, String nombre) throws Exception {
		cueListaLogic.saveCueLista(consecutivo, descripcion, estado, nombre);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#deleteCueLista(java.lang.Long)
	 */
	@Override
	public void deleteCueLista(Long consecutivo) throws Exception {
		cueListaLogic.deleteCueLista(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueLista(java.lang.Long, java.lang.String, java.lang.Long, java.lang.String)
	 */
	@Override
	public void updateCueLista(Long consecutivo, String descripcion, Long estado, String nombre) throws Exception {
		cueListaLogic.updateCueLista(consecutivo, descripcion, estado, nombre);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueLista(java.lang.Long)
	 */
	@Override
	public CueLista getCueLista(Long consecutivo) throws Exception {
		CueLista cueLista = null;

		try {
			cueLista = cueListaLogic.getCueLista(consecutivo);
		} catch (Exception e) {
			throw e;
		}

		return cueLista;
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findByCriteriaInCueLista(java.lang.Object[], java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public List<CueLista> findByCriteriaInCueLista(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return cueListaLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findPageCueLista(java.lang.String, boolean, int, int)
	 */
	@Override
	public List<CueLista> findPageCueLista(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return cueListaLogic.findPageCueLista(sortColumnName, sortAscending, startRow, maxResults);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findTotalNumberCueLista()
	 */
	@Override
	public Long findTotalNumberCueLista() throws Exception {
		return cueListaLogic.findTotalNumberCueLista();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getDataCueLista()
	 */
	@Override
	public List<CueListaDTO> getDataCueLista() throws Exception {
		return cueListaLogic.getDataCueLista();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueListaContacto()
	 */
	@Override
	public List<CueListaContacto> getCueListaContacto() throws Exception {
		return cueListaContactoLogic.getCueListaContacto();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueListaContacto(java.lang.Long, java.lang.Long, java.lang.Long, java.util.Date, java.util.Date, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public void saveCueListaContacto(Long consecutivo, Long duracion, Long estado, Date fechaHoraAsignacion,
			Date fechaHoraFinalizacion, Long puntajeTotal, Long identificacion_CueContacto, Long consecutivo_CueLista)
			throws Exception {
		cueListaContactoLogic.saveCueListaContacto(consecutivo, duracion, estado, fechaHoraAsignacion,
				fechaHoraFinalizacion, puntajeTotal, identificacion_CueContacto, consecutivo_CueLista);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#deleteCueListaContacto(java.lang.Long)
	 */
	@Override
	public void deleteCueListaContacto(Long consecutivo) throws Exception {
		cueListaContactoLogic.deleteCueListaContacto(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueListaContacto(java.lang.Long, java.lang.Long, java.lang.Long, java.util.Date, java.util.Date, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public void updateCueListaContacto(Long consecutivo, Long duracion, Long estado, Date fechaHoraAsignacion,
			Date fechaHoraFinalizacion, Long puntajeTotal, Long identificacion_CueContacto, Long consecutivo_CueLista)
			throws Exception {
		cueListaContactoLogic.updateCueListaContacto(consecutivo, duracion, estado, fechaHoraAsignacion,
				fechaHoraFinalizacion, puntajeTotal, identificacion_CueContacto, consecutivo_CueLista);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueListaContacto(java.lang.Long)
	 */
	@Override
	public CueListaContacto getCueListaContacto(Long consecutivo) throws Exception {
		CueListaContacto cueListaContacto = null;

		try {
			cueListaContacto = cueListaContactoLogic.getCueListaContacto(consecutivo);
		} catch (Exception e) {
			throw e;
		}

		return cueListaContacto;
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findByCriteriaInCueListaContacto(java.lang.Object[], java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public List<CueListaContacto> findByCriteriaInCueListaContacto(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return cueListaContactoLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findPageCueListaContacto(java.lang.String, boolean, int, int)
	 */
	@Override
	public List<CueListaContacto> findPageCueListaContacto(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return cueListaContactoLogic.findPageCueListaContacto(sortColumnName, sortAscending, startRow, maxResults);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findTotalNumberCueListaContacto()
	 */
	@Override
	public Long findTotalNumberCueListaContacto() throws Exception {
		return cueListaContactoLogic.findTotalNumberCueListaContacto();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getDataCueListaContacto()
	 */
	@Override
	public List<CueListaContactoDTO> getDataCueListaContacto() throws Exception {
		return cueListaContactoLogic.getDataCueListaContacto();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueListaCuestionario()
	 */
	@Override
	public List<CueListaCuestionario> getCueListaCuestionario() throws Exception {
		return cueListaCuestionarioLogic.getCueListaCuestionario();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueListaCuestionario(java.lang.Long, java.util.Date, java.lang.Long, java.lang.Long)
	 */
	@Override
	public void saveCueListaCuestionario(Long consecutivo, Date fechaHoraAsignacion, Long consecutivo_CueCuestionario,
			Long consecutivo_CueLista) throws Exception {
		cueListaCuestionarioLogic.saveCueListaCuestionario(consecutivo, fechaHoraAsignacion,
				consecutivo_CueCuestionario, consecutivo_CueLista);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#deleteCueListaCuestionario(java.lang.Long)
	 */
	@Override
	public void deleteCueListaCuestionario(Long consecutivo) throws Exception {
		cueListaCuestionarioLogic.deleteCueListaCuestionario(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueListaCuestionario(java.lang.Long, java.util.Date, java.lang.Long, java.lang.Long)
	 */
	@Override
	public void updateCueListaCuestionario(Long consecutivo, Date fechaHoraAsignacion,
			Long consecutivo_CueCuestionario, Long consecutivo_CueLista) throws Exception {
		cueListaCuestionarioLogic.updateCueListaCuestionario(consecutivo, fechaHoraAsignacion,
				consecutivo_CueCuestionario, consecutivo_CueLista);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueListaCuestionario(java.lang.Long)
	 */
	@Override
	public CueListaCuestionario getCueListaCuestionario(Long consecutivo) throws Exception {
		CueListaCuestionario cueListaCuestionario = null;

		try {
			cueListaCuestionario = cueListaCuestionarioLogic.getCueListaCuestionario(consecutivo);
		} catch (Exception e) {
			throw e;
		}

		return cueListaCuestionario;
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findByCriteriaInCueListaCuestionario(java.lang.Object[], java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public List<CueListaCuestionario> findByCriteriaInCueListaCuestionario(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
		return cueListaCuestionarioLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findPageCueListaCuestionario(java.lang.String, boolean, int, int)
	 */
	@Override
	public List<CueListaCuestionario> findPageCueListaCuestionario(String sortColumnName, boolean sortAscending,
			int startRow, int maxResults) throws Exception {
		return cueListaCuestionarioLogic.findPageCueListaCuestionario(sortColumnName, sortAscending, startRow,
				maxResults);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findTotalNumberCueListaCuestionario()
	 */
	@Override
	public Long findTotalNumberCueListaCuestionario() throws Exception {
		return cueListaCuestionarioLogic.findTotalNumberCueListaCuestionario();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getDataCueListaCuestionario()
	 */
	@Override
	public List<CueListaCuestionarioDTO> getDataCueListaCuestionario() throws Exception {
		return cueListaCuestionarioLogic.getDataCueListaCuestionario();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueNavegacion()
	 */
	@Override
	public List<CueNavegacion> getCueNavegacion() throws Exception {
		return cueNavegacionLogic.getCueNavegacion();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueNavegacion(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public void saveCueNavegacion(Long consecutivo, Long consecutivo_CueCuestionario, Long consecutivo_CueOpcion,
			Long consecutivo_CuePreguntaOrigen, Long consecutivo_CuePreguntaDestino) throws Exception {
		cueNavegacionLogic.saveCueNavegacion(consecutivo, consecutivo_CueCuestionario, consecutivo_CueOpcion,
				consecutivo_CuePreguntaOrigen, consecutivo_CuePreguntaDestino);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#deleteCueNavegacion(java.lang.Long)
	 */
	@Override
	public void deleteCueNavegacion(Long consecutivo) throws Exception {
		cueNavegacionLogic.deleteCueNavegacion(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueNavegacion(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public void updateCueNavegacion(Long consecutivo, Long consecutivo_CueCuestionario, Long consecutivo_CueOpcion,
			Long consecutivo_CuePreguntaOrigen, Long consecutivo_CuePreguntaDestino) throws Exception {
		cueNavegacionLogic.updateCueNavegacion(consecutivo, consecutivo_CueCuestionario, consecutivo_CueOpcion,
				consecutivo_CuePreguntaOrigen, consecutivo_CuePreguntaDestino);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueNavegacion(java.lang.Long)
	 */
	@Override
	public CueNavegacion getCueNavegacion(Long consecutivo) throws Exception {
		CueNavegacion cueNavegacion = null;

		try {
			cueNavegacion = cueNavegacionLogic.getCueNavegacion(consecutivo);
		} catch (Exception e) {
			throw e;
		}

		return cueNavegacion;
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findByCriteriaInCueNavegacion(java.lang.Object[], java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public List<CueNavegacion> findByCriteriaInCueNavegacion(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return cueNavegacionLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findPageCueNavegacion(java.lang.String, boolean, int, int)
	 */
	@Override
	public List<CueNavegacion> findPageCueNavegacion(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return cueNavegacionLogic.findPageCueNavegacion(sortColumnName, sortAscending, startRow, maxResults);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findTotalNumberCueNavegacion()
	 */
	@Override
	public Long findTotalNumberCueNavegacion() throws Exception {
		return cueNavegacionLogic.findTotalNumberCueNavegacion();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getDataCueNavegacion()
	 */
	@Override
	public List<CueNavegacionDTO> getDataCueNavegacion() throws Exception {
		return cueNavegacionLogic.getDataCueNavegacion();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueOpcion()
	 */
	@Override
	public List<CueOpcion> getCueOpcion() throws Exception {
		return cueOpcionLogic.getCueOpcion();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueOpcion(java.lang.String, java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public void saveCueOpcion(String condicion, Long consecutivo, String enunciado, Long estado,
			Long indicadorCorrecta, String labelAmpliacion, Long orden, Long puntaje, Long requiereAmpliacion,
			Long consecutivo_CuePregunta) throws Exception {
		cueOpcionLogic.saveCueOpcion(condicion, consecutivo, enunciado, estado, indicadorCorrecta, labelAmpliacion,
				orden, puntaje, requiereAmpliacion, consecutivo_CuePregunta);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#deleteCueOpcion(java.lang.Long)
	 */
	@Override
	public void deleteCueOpcion(Long consecutivo) throws Exception {
		cueOpcionLogic.deleteCueOpcion(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueOpcion(java.lang.String, java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public void updateCueOpcion(String condicion, Long consecutivo, String enunciado, Long estado,
			Long indicadorCorrecta, String labelAmpliacion, Long orden, Long puntaje, Long requiereAmpliacion,
			Long consecutivo_CuePregunta) throws Exception {
		cueOpcionLogic.updateCueOpcion(condicion, consecutivo, enunciado, estado, indicadorCorrecta, labelAmpliacion,
				orden, puntaje, requiereAmpliacion, consecutivo_CuePregunta);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueOpcion(java.lang.Long)
	 */
	@Override
	public CueOpcion getCueOpcion(Long consecutivo) throws Exception {
		CueOpcion cueOpcion = null;

		try {
			cueOpcion = cueOpcionLogic.getCueOpcion(consecutivo);
		} catch (Exception e) {
			throw e;
		}

		return cueOpcion;
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findByCriteriaInCueOpcion(java.lang.Object[], java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public List<CueOpcion> findByCriteriaInCueOpcion(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return cueOpcionLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findPageCueOpcion(java.lang.String, boolean, int, int)
	 */
	@Override
	public List<CueOpcion> findPageCueOpcion(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return cueOpcionLogic.findPageCueOpcion(sortColumnName, sortAscending, startRow, maxResults);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findTotalNumberCueOpcion()
	 */
	@Override
	public Long findTotalNumberCueOpcion() throws Exception {
		return cueOpcionLogic.findTotalNumberCueOpcion();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getDataCueOpcion()
	 */
	@Override
	public List<CueOpcionDTO> getDataCueOpcion() throws Exception {
		return cueOpcionLogic.getDataCueOpcion();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCuePregunta()
	 */
	@Override
	public List<CuePregunta> getCuePregunta() throws Exception {
		return cuePreguntaLogic.getCuePregunta();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#deleteCuePregunta(java.lang.Long)
	 */
	@Override
	public void deleteCuePregunta(Long consecutivo) throws Exception {
		cuePreguntaLogic.deleteCuePregunta(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCuePregunta(java.lang.Long)
	 */
	@Override
	public CuePregunta getCuePregunta(Long consecutivo) throws Exception {
		CuePregunta cuePregunta = null;

		try {
			cuePregunta = cuePreguntaLogic.getCuePregunta(consecutivo);
		} catch (Exception e) {
			throw e;
		}

		return cuePregunta;
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findByCriteriaInCuePregunta(java.lang.Object[], java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public List<CuePregunta> findByCriteriaInCuePregunta(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return cuePreguntaLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findPageCuePregunta(java.lang.String, boolean, int, int)
	 */
	@Override
	public List<CuePregunta> findPageCuePregunta(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return cuePreguntaLogic.findPageCuePregunta(sortColumnName, sortAscending, startRow, maxResults);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findTotalNumberCuePregunta()
	 */
	@Override
	public Long findTotalNumberCuePregunta() throws Exception {
		return cuePreguntaLogic.findTotalNumberCuePregunta();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getDataCuePregunta()
	 */
	@Override
	public List<CuePreguntaDTO> getDataCuePregunta() throws Exception {
		return cuePreguntaLogic.getDataCuePregunta();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueResponsable()
	 */
	@Override
	public List<CueResponsable> getCueResponsable() throws Exception {
		return cueResponsableLogic.getCueResponsable();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueResponsable(com.vortexbird.pusay.cuestionarios.model.CueResponsable)
	 */
	@Override
	public void saveCueResponsable(CueResponsable responsable) throws Exception {
		this.cueResponsableLogic.saveCueResponsable(responsable);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#deleteCueResponsable(java.lang.Long)
	 */
	@Override
	public void deleteCueResponsable(Long identificacion) throws Exception {
		this.cueResponsableLogic.deleteCueResponsable(identificacion);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueResponsable(com.vortexbird.pusay.cuestionarios.model.CueResponsable)
	 */
	@Override
	public void updateCueResponsable(CueResponsable responsable) throws Exception {
		this.cueResponsableLogic.updateCueResponsable(responsable);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueResponsable(java.lang.Long)
	 */
	@Override
	public CueResponsable getCueResponsable(Long identificacion) throws Exception {
		CueResponsable cueResponsable = null;

		try {
			cueResponsable = cueResponsableLogic.getCueResponsable(identificacion);
		} catch (Exception e) {
			throw e;
		}

		return cueResponsable;
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findByCriteriaInCueResponsable(java.lang.Object[], java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public List<CueResponsable> findByCriteriaInCueResponsable(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return cueResponsableLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findPageCueResponsable(java.lang.String, boolean, int, int)
	 */
	@Override
	public List<CueResponsable> findPageCueResponsable(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return cueResponsableLogic.findPageCueResponsable(sortColumnName, sortAscending, startRow, maxResults);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findTotalNumberCueResponsable()
	 */
	@Override
	public Long findTotalNumberCueResponsable() throws Exception {
		return cueResponsableLogic.findTotalNumberCueResponsable();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getDataCueResponsable()
	 */
	@Override
	public List<CueResponsableDTO> getDataCueResponsable() throws Exception {
		return cueResponsableLogic.getDataCueResponsable();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueRespuesta()
	 */
	@Override
	public List<CueRespuesta> getCueRespuesta() throws Exception {
		return cueRespuestaLogic.getCueRespuesta();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueRespuesta(com.vortexbird.pusay.cuestionarios.model.CueRespuesta)
	 */
	@Override
	public void saveCueRespuesta(CueRespuesta respuesta) throws Exception {
		this.cueRespuestaLogic.saveCueRespuesta(respuesta);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#deleteCueRespuesta(java.lang.Long)
	 */
	@Override
	public void deleteCueRespuesta(Long consecutivo) throws Exception {
		this.cueRespuestaLogic.deleteCueRespuesta(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueRespuesta(com.vortexbird.pusay.cuestionarios.model.CueRespuesta)
	 */
	@Override
	public void updateCueRespuesta(CueRespuesta respuesta) throws Exception {
		this.cueRespuestaLogic.updateCueRespuesta(respuesta);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueRespuesta(java.lang.Long)
	 */
	@Override
	public CueRespuesta getCueRespuesta(Long consecutivo) throws Exception {
		CueRespuesta cueRespuesta = null;

		try {
			cueRespuesta = cueRespuestaLogic.getCueRespuesta(consecutivo);
		} catch (Exception e) {
			throw e;
		}

		return cueRespuesta;
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findByCriteriaInCueRespuesta(java.lang.Object[], java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public List<CueRespuesta> findByCriteriaInCueRespuesta(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return cueRespuestaLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findPageCueRespuesta(java.lang.String, boolean, int, int)
	 */
	@Override
	public List<CueRespuesta> findPageCueRespuesta(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return cueRespuestaLogic.findPageCueRespuesta(sortColumnName, sortAscending, startRow, maxResults);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findTotalNumberCueRespuesta()
	 */
	@Override
	public Long findTotalNumberCueRespuesta() throws Exception {
		return cueRespuestaLogic.findTotalNumberCueRespuesta();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getDataCueRespuesta()
	 */
	@Override
	public List<CueRespuestaDTO> getDataCueRespuesta() throws Exception {
		return cueRespuestaLogic.getDataCueRespuesta();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueCategoria(com.vortexbird.pusay.cuestionarios.model.CueCategoria)
	 */
	@Override
	public void saveCueCategoria(CueCategoria cueCategoria) throws Exception {
		cueCategoriaLogic.saveCueCategoria(cueCategoria);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueCategoria(com.vortexbird.pusay.cuestionarios.model.CueCategoria)
	 */
	@Override
	public void updateCueCategoria(CueCategoria cueCategoria) throws Exception {
		cueCategoriaLogic.updateCueCategoria(cueCategoria);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCuePregunta(com.vortexbird.pusay.cuestionarios.model.CuePregunta)
	 */
	@Override
	public void updateCuePregunta(CuePregunta cuePregunta) throws Exception {
		cuePreguntaLogic.updateCuePregunta(cuePregunta);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCuePregunta(com.vortexbird.pusay.cuestionarios.model.CuePregunta)
	 */
	@Override
	public void saveCuePregunta(CuePregunta cuePregunta) throws Exception {
		cuePreguntaLogic.saveCuePregunta(cuePregunta);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueOpcion(com.vortexbird.pusay.cuestionarios.model.CueOpcion)
	 */
	@Override
	public void saveCueOpcion(CueOpcion cueOpcion) throws Exception {
		cueOpcionLogic.saveCueOpcion(cueOpcion);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueOpcion(com.vortexbird.pusay.cuestionarios.model.CueOpcion)
	 */
	@Override
	public void updateCueOpcion(CueOpcion cueOpcion) throws Exception {
		cueOpcionLogic.updateCueOpcion(cueOpcion);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueOpcionPregunta(java.lang.Long)
	 */
	@Override
	public List<CueOpcion> getCueOpcionPregunta(Long consecutivo) throws Exception {
		return cueOpcionLogic.getCueOpcionPregunta(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueLista(com.vortexbird.pusay.cuestionarios.model.CueLista)
	 */
	@Override
	public void saveCueLista(CueLista cueLista) throws Exception {
		cueListaLogic.saveCueLista(cueLista);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueLista(com.vortexbird.pusay.cuestionarios.model.CueLista)
	 */
	@Override
	public void updateCueLista(CueLista cueLista) throws Exception {
		cueListaLogic.updateCueLista(cueLista);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueRespuestaPorUsuario()
	 */
	@Override
	public List<CueRespuesta> getCueRespuestaPorUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueContacto(com.vortexbird.pusay.cuestionarios.model.CueContacto)
	 */
	@Override
	public void saveCueContacto(CueContacto cueContacto) throws Exception {
		cueContactoLogic.saveCueContacto(cueContacto);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueContacto(com.vortexbird.pusay.cuestionarios.model.CueContacto)
	 */
	@Override
	public void updateCueContacto(CueContacto cueContacto) throws Exception {
		cueContactoLogic.updateCueContacto(cueContacto);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueListaContactoLista(java.lang.Long)
	 */
	@Override
	public List<CueListaContacto> getCueListaContactoLista(Long consecutivo) throws Exception {
		return cueListaLogic.getCueListaContactoLista(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findPreguntasByCategoria(java.lang.Long)
	 */
	@Override
	public List<CuePregunta> findPreguntasByCategoria(Long consecutivo) throws Exception {
		return this.cuePreguntaLogic.findPreguntasByCategoria(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueListaContacto(com.vortexbird.pusay.cuestionarios.model.CueListaContacto)
	 */
	@Override
	public void saveCueListaContacto(CueListaContacto selectedCueListaContacto) throws Exception {
		cueListaContactoLogic.saveCueListaContacto(selectedCueListaContacto);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueEstadoActivo()
	 */
	@Override
	public List<CueEstado> getCueEstadoActivo() throws Exception {
		return cueEstadoLogic.getCueEstadoActivo();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueCategoriaCuestionario(java.lang.Long)
	 */
	@Override
	public List<CueCategoria> getCueCategoriaCuestionario(Long consecutivo) throws Exception {
		return cueCategoriaLogic.getCueCategoriaCuestionario(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCuePreguntaCategoria(java.lang.Long)
	 */
	@Override
	public List<CuePregunta> getCuePreguntaCategoria(Long consecutivo) throws Exception {
		return cuePreguntaLogic.getCuePreguntaCategoria(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#saveCueListaCuestionario(com.vortexbird.pusay.cuestionarios.model.CueListaCuestionario)
	 */
	@Override
	public void saveCueListaCuestionario(CueListaCuestionario listaCuestionario) throws Exception {
		cueListaCuestionarioLogic.saveCueListaCuestionario(listaCuestionario);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueListaCuestionario(com.vortexbird.pusay.cuestionarios.model.CueListaCuestionario)
	 */
	@Override
	public void updateCueListaCuestionario(CueListaCuestionario cueListaCuestionario) throws Exception {
		cueListaCuestionarioLogic.updateCueListaCuestionario(cueListaCuestionario);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueListaCuestionarioPorCuestionario(java.lang.Long)
	 */
	@Override
	public List<CueListaCuestionario> getCueListaCuestionarioPorCuestionario(Long consecutivo) throws Exception {
		return cueListaCuestionarioLogic.getCueListaCuestionarioPorCuestionario(consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#action_subir_orden_pregunta(com.vortexbird.pusay.cuestionarios.model.CuePregunta)
	 */
	@Override
	public void action_subir_orden_pregunta(CuePregunta cuePregunta) {
		cuePreguntaLogic.action_subir_orden_pregunta(cuePregunta);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#action_bajar_orden_pregunta(com.vortexbird.pusay.cuestionarios.model.CuePregunta)
	 */
	@Override
	public void action_bajar_orden_pregunta(CuePregunta cuePregunta) {
		cuePreguntaLogic.action_bajar_orden_pregunta(cuePregunta);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#action_subir_orden_opcion(com.vortexbird.pusay.cuestionarios.model.CueOpcion)
	 */
	@Override
	public void action_subir_orden_opcion(CueOpcion cueOpcion) {
		cueOpcionLogic.action_subir_orden_opcion(cueOpcion);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#action_bajar_orden_opcion(com.vortexbird.pusay.cuestionarios.model.CueOpcion)
	 */
	@Override
	public void action_bajar_orden_opcion(CueOpcion cueOpcion) {
		cueOpcionLogic.action_bajar_orden_opcion(cueOpcion);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#updateCueListaContacto(com.vortexbird.pusay.cuestionarios.model.CueListaContacto)
	 */
	@Override
	public void updateCueListaContacto(CueListaContacto listaContacto) throws Exception {
		cueListaContactoLogic.updateCueListaContacto(listaContacto);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#reiniciarPregunta()
	 */
	@Override
	public void reiniciarPregunta() {
		cueRespuestaLogic.setCodigoPregunta(null);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#deleteRespuestas(com.vortexbird.pusay.cuestionarios.model.CueListaContacto)
	 */
	@Override
	public void deleteRespuestas(CueListaContacto cuelistaContacto) {
		cueRespuestaLogic.deleteRespuestas(cuelistaContacto);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueRespuesta(java.lang.Long, java.lang.Long)
	 */
	@Override
	public CueRespuesta getCueRespuesta(Long listaContacto, Long codigoOpcion) {
		return cueRespuestaLogic.getCueRespuesta(listaContacto, codigoOpcion);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueListaContacto(java.lang.Long, java.lang.Long)
	 */
	@Override
	public CueListaContacto getCueListaContacto(Long lista, Long identificacion, Long pestCodigo) {
		return cueListaContactoLogic.getCueListaContacto(lista, identificacion, pestCodigo);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueContactosActivos()
	 */
	@Override
	public List<CueContacto> getCueContactosActivos() {
		return this.cueContactoLogic.getCueContactosActivos();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueCuestionarioTiposActivos()
	 */
	@Override
	public List<CueCuestionarioTipo> getCueCuestionarioTiposActivos() {
		return this.cueCuestionarioTipoLogic.getCueCuestionarioTiposActivos();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueListasAtivas()
	 */
	@Override
	public List<CueLista> getCueListasAtivas() {
		return this.cueListaLogic.getCueListasAtivas();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueCategoriaCuestionarioActivos(java.lang.Long)
	 */
	@Override
	public List<CueCategoria> getCueCategoriaCuestionarioActivos(Long consecutivoCuestionario) {
		return this.cueCategoriaLogic.getCueCategoriaCuestionarioActivos(consecutivoCuestionario);
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#getCueCuestionariosActivos()
	 */
	@Override
	public List<CueCuestionario> getCueCuestionariosActivos() {
		return this.cueCuestionarioLogic.getCueCuestionariosActivos();
	}

	/* (non-Javadoc)
	 * @see com.vortexbird.pusay.cuestionarios.services.businessdelegate.impl.IBusinessDelegatorEncuestasView#findPreguntasActivasByCategoria(java.lang.Long)
	 */
	@Override
	public List<CuePregunta> findPreguntasActivasByCategoria(Long consecutivo) {
		return this.cuePreguntaLogic.findPreguntasActivasByCategoria(consecutivo);
	}
}