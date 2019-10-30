package com.vortexbird.seguridad.presentation.businessDelegate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.vortexbird.seguridad.control.ISegAuditoriaLogic;
import com.vortexbird.seguridad.control.ISegCambioPassLogic;
import com.vortexbird.seguridad.control.ISegCompaniaLogic;
import com.vortexbird.seguridad.control.ISegGrupoOpcionLogic;
import com.vortexbird.seguridad.control.ISegHistorialConstrasenaLogic;
import com.vortexbird.seguridad.control.ISegOpcionLogic;
import com.vortexbird.seguridad.control.ISegParametroLogic;
import com.vortexbird.seguridad.control.ISegPermisoLogic;
import com.vortexbird.seguridad.control.ISegRolLogic;
import com.vortexbird.seguridad.control.ISegRolUsuarioLogic;
import com.vortexbird.seguridad.control.ISegSistemaCiaLogic;
import com.vortexbird.seguridad.control.ISegSistemaLogic;
import com.vortexbird.seguridad.control.ISegSucursalLogic;
import com.vortexbird.seguridad.control.ISegUsuarioLogic;
import com.vortexbird.seguridad.control.SegAuditoriaLogic;
import com.vortexbird.seguridad.control.SegCambioPassLogic;
import com.vortexbird.seguridad.control.SegCompaniaLogic;
import com.vortexbird.seguridad.control.SegGrupoOpcionLogic;
import com.vortexbird.seguridad.control.SegHistorialConstrasenaLogic;
import com.vortexbird.seguridad.control.SegOpcionLogic;
import com.vortexbird.seguridad.control.SegParametroLogic;
import com.vortexbird.seguridad.control.SegPermisoLogic;
import com.vortexbird.seguridad.control.SegRolLogic;
import com.vortexbird.seguridad.control.SegRolUsuarioLogic;
import com.vortexbird.seguridad.control.SegSistemaCiaLogic;
import com.vortexbird.seguridad.control.SegSistemaLogic;
import com.vortexbird.seguridad.control.SegSucursalLogic;
import com.vortexbird.seguridad.control.SegUsuarioLogic;
import com.vortexbird.seguridad.modelo.SegAuditoria;
import com.vortexbird.seguridad.modelo.SegCambioPass;
import com.vortexbird.seguridad.modelo.SegCompania;
import com.vortexbird.seguridad.modelo.SegGrupoOpcion;
import com.vortexbird.seguridad.modelo.SegHistorialConstrasena;
import com.vortexbird.seguridad.modelo.SegOpcion;
import com.vortexbird.seguridad.modelo.SegParametro;
import com.vortexbird.seguridad.modelo.SegPermiso;
import com.vortexbird.seguridad.modelo.SegRol;
import com.vortexbird.seguridad.modelo.SegRolUsuario;
import com.vortexbird.seguridad.modelo.SegSistema;
import com.vortexbird.seguridad.modelo.SegSistemaCia;
import com.vortexbird.seguridad.modelo.SegSucursal;
import com.vortexbird.seguridad.modelo.SegUsuario;
import com.vortexbird.seguridad.modelo.control.ws.ILoginBeanDelegate;
import com.vortexbird.seguridad.modelo.control.ws.impl.LoginBeanDelegate;
import com.vortexbird.seguridad.modelo.dto.GrupoDTO;
import com.vortexbird.seguridad.modelo.dto.SegAuditoriaDTO;
import com.vortexbird.seguridad.modelo.dto.SegCompaniaDTO;
import com.vortexbird.seguridad.modelo.dto.SegGrupoOpcionDTO;
import com.vortexbird.seguridad.modelo.dto.SegOpcionDTO;
import com.vortexbird.seguridad.modelo.dto.SegRolDTO;
import com.vortexbird.seguridad.modelo.dto.SegRolUsuarioDTO;
import com.vortexbird.seguridad.modelo.dto.SegSistemaCiaDTO;
import com.vortexbird.seguridad.modelo.dto.SegSistemaDTO;
import com.vortexbird.seguridad.modelo.dto.SegSucursalDTO;
import com.vortexbird.seguridad.modelo.dto.SegUsuarioDTO;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;


/**
 * Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
 * The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
 *
 * The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
 * the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
 * the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
 * volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
 * presentation-tier client code when the business service API or its underlying implementation changes.
 *
 * However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
 * Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
 *
 * Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
 * for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
 * considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
 * to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
 * exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
 * generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
 * The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
 * exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
 * use the pattern.
 *
 * Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
 * because it limits unnecessary and potentially costly round trips over the network.
 *
 * A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
 * details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
 * implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
 *
 * When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
 * This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
 * with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
 *
 * Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
 * business tiers.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class BusinessDelegatorView {
	private BusinessDelegatorView() {
	}

	public static List<SegAuditoria> getSegAuditoria()
			throws Exception {
		ISegAuditoriaLogic segAuditoriaLogic = new SegAuditoriaLogic();

		return segAuditoriaLogic.getSegAuditoria();
	}

	public static void saveSegAuditoria(Long autCodigo, String campo,
			Long codRegistro, Date fecha, String tabla, String tipo,
			String valorAnterior, String valorNuevo, Long usuCodigo_SegUsuario)
					throws Exception {
		ISegAuditoriaLogic segAuditoriaLogic = new SegAuditoriaLogic();
		segAuditoriaLogic.saveSegAuditoria(autCodigo, campo, codRegistro,
				fecha, tabla, tipo, valorAnterior, valorNuevo, usuCodigo_SegUsuario);
	}

	public static void deleteSegAuditoria(Long autCodigo)
			throws Exception {
		ISegAuditoriaLogic segAuditoriaLogic = new SegAuditoriaLogic();
		segAuditoriaLogic.deleteSegAuditoria(autCodigo);
	}

	public static void updateSegAuditoria(Long autCodigo, String campo,
			Long codRegistro, Date fecha, String tabla, String tipo,
			String valorAnterior, String valorNuevo, Long usuCodigo_SegUsuario)
					throws Exception {
		ISegAuditoriaLogic segAuditoriaLogic = new SegAuditoriaLogic();
		segAuditoriaLogic.updateSegAuditoria(autCodigo, campo, codRegistro,
				fecha, tabla, tipo, valorAnterior, valorNuevo, usuCodigo_SegUsuario);
	}

	public static SegAuditoria getSegAuditoria(Long autCodigo)
			throws Exception {
		ISegAuditoriaLogic segAuditoriaLogic = new SegAuditoriaLogic();
		SegAuditoria segAuditoria = null;

		try {
			segAuditoria = segAuditoriaLogic.getSegAuditoria(autCodigo);
		} catch (Exception e) {
			throw e;
		}

		return segAuditoria;
	}

	public static List<SegAuditoria> findByCriteriaInSegAuditoria(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		ISegAuditoriaLogic segAuditoriaLogic = new SegAuditoriaLogic();

		return segAuditoriaLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public static List<SegAuditoria> findPageSegAuditoria(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		ISegAuditoriaLogic segAuditoriaLogic = new SegAuditoriaLogic();

		return segAuditoriaLogic.findPageSegAuditoria(sortColumnName,
				sortAscending, startRow, maxResults);
	}

	public static Long findTotalNumberSegAuditoria() throws Exception {
		ISegAuditoriaLogic segAuditoriaLogic = new SegAuditoriaLogic();

		return segAuditoriaLogic.findTotalNumberSegAuditoria();
	}

	public static List<SegCompania> getSegCompania() throws Exception {
		ISegCompaniaLogic segCompaniaLogic = new SegCompaniaLogic();

		return segCompaniaLogic.getSegCompania();
	}

	public static void saveSegCompania(Long ciaCodigo, String ciaCodigoInterno,
			String ciaEstadoRegistro, String ciaNombre, Long usuCodigo_SegUsuario)
					throws Exception {
		ISegCompaniaLogic segCompaniaLogic = new SegCompaniaLogic();
		segCompaniaLogic.saveSegCompania(ciaCodigo, ciaCodigoInterno,
				ciaEstadoRegistro, ciaNombre, usuCodigo_SegUsuario);
	}

	public static void deleteSegCompania(Long ciaCodigo)
			throws Exception {
		ISegCompaniaLogic segCompaniaLogic = new SegCompaniaLogic();
		segCompaniaLogic.deleteSegCompania(ciaCodigo);
	}

	public static void updateSegCompania(Long ciaCodigo,
			String ciaCodigoInterno, String ciaEstadoRegistro, String ciaNombre,
			Long usuCodigo_SegUsuario) throws Exception {
		ISegCompaniaLogic segCompaniaLogic = new SegCompaniaLogic();
		segCompaniaLogic.updateSegCompania(ciaCodigo, ciaCodigoInterno,
				ciaEstadoRegistro, ciaNombre, usuCodigo_SegUsuario);
	}

	public static SegCompania getSegCompania(Long ciaCodigo)
			throws Exception {
		ISegCompaniaLogic segCompaniaLogic = new SegCompaniaLogic();
		SegCompania segCompania = null;

		try {
			segCompania = segCompaniaLogic.getSegCompania(ciaCodigo);
		} catch (Exception e) {
			throw e;
		}

		return segCompania;
	}

	public static List<SegCompania> findByCriteriaInSegCompania(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		ISegCompaniaLogic segCompaniaLogic = new SegCompaniaLogic();

		return segCompaniaLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public static List<SegCompania> findPageSegCompania(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception {
		ISegCompaniaLogic segCompaniaLogic = new SegCompaniaLogic();

		return segCompaniaLogic.findPageSegCompania(sortColumnName,
				sortAscending, startRow, maxResults);
	}

	public static Long findTotalNumberSegCompania() throws Exception {
		ISegCompaniaLogic segCompaniaLogic = new SegCompaniaLogic();

		return segCompaniaLogic.findTotalNumberSegCompania();
	}

	public static List<SegGrupoOpcion> getSegGrupoOpcion()
			throws Exception {
		ISegGrupoOpcionLogic segGrupoOpcionLogic = new SegGrupoOpcionLogic();

		return segGrupoOpcionLogic.getSegGrupoOpcion();
	}

	public static void saveSegGrupoOpcion(Long gruCodigo,
			String gruDescripcion, String gruEstadoRegistro, String gruLlaveAcceso,
			String gruNombre, Long gruCodigo_SegGrupoOpcion,
			Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario)
					throws Exception {
		ISegGrupoOpcionLogic segGrupoOpcionLogic = new SegGrupoOpcionLogic();
		segGrupoOpcionLogic.saveSegGrupoOpcion(gruCodigo, gruDescripcion,
				gruEstadoRegistro, gruLlaveAcceso, gruNombre,
				gruCodigo_SegGrupoOpcion, sisCodigo_SegSistema, usuCodigo_SegUsuario);
	}

	public static void deleteSegGrupoOpcion(Long gruCodigo)
			throws Exception {
		ISegGrupoOpcionLogic segGrupoOpcionLogic = new SegGrupoOpcionLogic();
		segGrupoOpcionLogic.deleteSegGrupoOpcion(gruCodigo);
	}

	public static void updateSegGrupoOpcion(Long gruCodigo,
			String gruDescripcion, String gruEstadoRegistro, String gruLlaveAcceso,
			String gruNombre, Long gruCodigo_SegGrupoOpcion,
			Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario)
					throws Exception {
		ISegGrupoOpcionLogic segGrupoOpcionLogic = new SegGrupoOpcionLogic();
		segGrupoOpcionLogic.updateSegGrupoOpcion(gruCodigo, gruDescripcion,
				gruEstadoRegistro, gruLlaveAcceso, gruNombre,
				gruCodigo_SegGrupoOpcion, sisCodigo_SegSistema, usuCodigo_SegUsuario);
	}

	public static SegGrupoOpcion getSegGrupoOpcion(Long gruCodigo)
			throws Exception {
		ISegGrupoOpcionLogic segGrupoOpcionLogic = new SegGrupoOpcionLogic();
		SegGrupoOpcion segGrupoOpcion = null;

		try {
			segGrupoOpcion = segGrupoOpcionLogic.getSegGrupoOpcion(gruCodigo);
		} catch (Exception e) {
			throw e;
		}

		return segGrupoOpcion;
	}

	public static List<SegGrupoOpcion> findByCriteriaInSegGrupoOpcion(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		ISegGrupoOpcionLogic segGrupoOpcionLogic = new SegGrupoOpcionLogic();

		return segGrupoOpcionLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public static List<SegGrupoOpcion> findPageSegGrupoOpcion(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		ISegGrupoOpcionLogic segGrupoOpcionLogic = new SegGrupoOpcionLogic();

		return segGrupoOpcionLogic.findPageSegGrupoOpcion(sortColumnName,
				sortAscending, startRow, maxResults);
	}

	public static Long findTotalNumberSegGrupoOpcion()
			throws Exception {
		ISegGrupoOpcionLogic segGrupoOpcionLogic = new SegGrupoOpcionLogic();

		return segGrupoOpcionLogic.findTotalNumberSegGrupoOpcion();
	}

	public static List<SegHistorialConstrasena> getSegHistorialConstrasena()
			throws Exception {
		ISegHistorialConstrasenaLogic segHistorialConstrasenaLogic = new SegHistorialConstrasenaLogic();

		return segHistorialConstrasenaLogic.getSegHistorialConstrasena();
	}

	public static void saveSegHistorialConstrasena(Long hcoCodigo,
			String hcoConstrasena, String hcoDetalleCambio, Date hcoFechaCambio,
			Long usuCodigo_SegUsuario) throws Exception {
		ISegHistorialConstrasenaLogic segHistorialConstrasenaLogic = new SegHistorialConstrasenaLogic();
		segHistorialConstrasenaLogic.saveSegHistorialConstrasena(hcoCodigo,
				hcoConstrasena, hcoDetalleCambio, hcoFechaCambio,
				usuCodigo_SegUsuario);
	}

	public static void deleteSegHistorialConstrasena(Long hcoCodigo)
			throws Exception {
		ISegHistorialConstrasenaLogic segHistorialConstrasenaLogic = new SegHistorialConstrasenaLogic();
		segHistorialConstrasenaLogic.deleteSegHistorialConstrasena(hcoCodigo);
	}

	public static void updateSegHistorialConstrasena(Long hcoCodigo,
			String hcoConstrasena, String hcoDetalleCambio, Date hcoFechaCambio,
			Long usuCodigo_SegUsuario) throws Exception {
		ISegHistorialConstrasenaLogic segHistorialConstrasenaLogic = new SegHistorialConstrasenaLogic();
		segHistorialConstrasenaLogic.updateSegHistorialConstrasena(hcoCodigo,
				hcoConstrasena, hcoDetalleCambio, hcoFechaCambio,
				usuCodigo_SegUsuario);
	}

	public static SegHistorialConstrasena getSegHistorialConstrasena(
			Long hcoCodigo) throws Exception {
		ISegHistorialConstrasenaLogic segHistorialConstrasenaLogic = new SegHistorialConstrasenaLogic();
		SegHistorialConstrasena segHistorialConstrasena = null;

		try {
			segHistorialConstrasena = segHistorialConstrasenaLogic.getSegHistorialConstrasena(hcoCodigo);
		} catch (Exception e) {
			throw e;
		}

		return segHistorialConstrasena;
	}

	public static List<SegHistorialConstrasena> findByCriteriaInSegHistorialConstrasena(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		ISegHistorialConstrasenaLogic segHistorialConstrasenaLogic = new SegHistorialConstrasenaLogic();

		return segHistorialConstrasenaLogic.findByCriteria(variables,
				variablesBetween, variablesBetweenDates);
	}

	public static List<SegHistorialConstrasena> findPageSegHistorialConstrasena(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		ISegHistorialConstrasenaLogic segHistorialConstrasenaLogic = new SegHistorialConstrasenaLogic();

		return segHistorialConstrasenaLogic.findPageSegHistorialConstrasena(sortColumnName,
				sortAscending, startRow, maxResults);
	}

	public static Long findTotalNumberSegHistorialConstrasena()
			throws Exception {
		ISegHistorialConstrasenaLogic segHistorialConstrasenaLogic = new SegHistorialConstrasenaLogic();

		return segHistorialConstrasenaLogic.findTotalNumberSegHistorialConstrasena();
	}

	public static List<SegOpcion> getSegOpcion() throws Exception {
		ISegOpcionLogic segOpcionLogic = new SegOpcionLogic();

		return segOpcionLogic.getSegOpcion();
	}

	public static void saveSegOpcion(Long opcCodigo, String opcDescripcion,
			String opcEstadoRegistro, String opcLlaveAcceso, String opcNombre,
			Long gruCodigo_SegGrupoOpcion, Long usuCodigo_SegUsuario)
					throws Exception {
		ISegOpcionLogic segOpcionLogic = new SegOpcionLogic();
		segOpcionLogic.saveSegOpcion(opcCodigo, opcDescripcion,
				opcEstadoRegistro, opcLlaveAcceso, opcNombre,
				gruCodigo_SegGrupoOpcion, usuCodigo_SegUsuario);
	}

	public static void deleteSegOpcion(Long opcCodigo)
			throws Exception {
		ISegOpcionLogic segOpcionLogic = new SegOpcionLogic();
		segOpcionLogic.deleteSegOpcion(opcCodigo);
	}

	public static void updateSegOpcion(Long opcCodigo, String opcDescripcion,
			String opcEstadoRegistro, String opcLlaveAcceso, String opcNombre,
			Long gruCodigo_SegGrupoOpcion, Long usuCodigo_SegUsuario)
					throws Exception {
		ISegOpcionLogic segOpcionLogic = new SegOpcionLogic();
		segOpcionLogic.updateSegOpcion(opcCodigo, opcDescripcion,
				opcEstadoRegistro, opcLlaveAcceso, opcNombre,
				gruCodigo_SegGrupoOpcion, usuCodigo_SegUsuario);
	}

	public static SegOpcion getSegOpcion(Long opcCodigo)
			throws Exception {
		ISegOpcionLogic segOpcionLogic = new SegOpcionLogic();
		SegOpcion segOpcion = null;

		try {
			segOpcion = segOpcionLogic.getSegOpcion(opcCodigo);
		} catch (Exception e) {
			throw e;
		}

		return segOpcion;
	}

	public static List<SegOpcion> findByCriteriaInSegOpcion(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		ISegOpcionLogic segOpcionLogic = new SegOpcionLogic();

		return segOpcionLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public static List<SegOpcion> findPageSegOpcion(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception {
		ISegOpcionLogic segOpcionLogic = new SegOpcionLogic();

		return segOpcionLogic.findPageSegOpcion(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public static Long findTotalNumberSegOpcion() throws Exception {
		ISegOpcionLogic segOpcionLogic = new SegOpcionLogic();

		return segOpcionLogic.findTotalNumberSegOpcion();
	}

	public static List<SegParametro> getSegParametro()
			throws Exception {
		ISegParametroLogic segParametroLogic = new SegParametroLogic();

		return segParametroLogic.getSegParametro();
	}

	public static void saveSegParametro(
			String parEstadoRegistro, String parNombre,
			String parValorAlfanumerico, Date parValorFecha,
			Double parValorNumerico, Long usuCodigo_SegUsuario)
					throws Exception {
		ISegParametroLogic segParametroLogic = new SegParametroLogic();
		segParametroLogic.saveSegParametro(parEstadoRegistro,
				parNombre, parValorAlfanumerico, parValorFecha, parValorNumerico,
				usuCodigo_SegUsuario);
	}

	public static void deleteSegParametro(Long parCodigo)
			throws Exception {
		ISegParametroLogic segParametroLogic = new SegParametroLogic();
		segParametroLogic.deleteSegParametro(parCodigo);
	}

	public static void updateSegParametro(Long parCodigo,
			String parEstadoRegistro, String parNombre,
			String parValorAlfanumerico, Date parValorFecha,
			Double parValorNumerico, Long usuCodigo_SegUsuario)
					throws Exception {
		ISegParametroLogic segParametroLogic = new SegParametroLogic();
		segParametroLogic.updateSegParametro(parCodigo, parEstadoRegistro,
				parNombre, parValorAlfanumerico, parValorFecha, parValorNumerico,
				usuCodigo_SegUsuario);
	}

	public static SegParametro getSegParametro(Long parCodigo)
			throws Exception {
		ISegParametroLogic segParametroLogic = new SegParametroLogic();
		SegParametro segParametro = null;

		try {
			segParametro = segParametroLogic.getSegParametro(parCodigo);
		} catch (Exception e) {
			throw e;
		}

		return segParametro;
	}

	public static List<SegParametro> findByCriteriaInSegParametro(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		ISegParametroLogic segParametroLogic = new SegParametroLogic();

		return segParametroLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public static List<SegParametro> findPageSegParametro(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		ISegParametroLogic segParametroLogic = new SegParametroLogic();

		return segParametroLogic.findPageSegParametro(sortColumnName,
				sortAscending, startRow, maxResults);
	}

	public static Long findTotalNumberSegParametro() throws Exception {
		ISegParametroLogic segParametroLogic = new SegParametroLogic();

		return segParametroLogic.findTotalNumberSegParametro();
	}

	public static List<SegPermiso> getSegPermiso() throws Exception {
		ISegPermisoLogic segPermisoLogic = new SegPermisoLogic();

		return segPermisoLogic.getSegPermiso();
	}

	public static void saveSegPermiso(String perEstadoRegistro,
			Long ciaCodigo_SegCompania, Long gruCodigo_SegGrupoOpcion,
			Long opcCodigo_SegOpcion, Long rolCodigo_SegRol,
			Long sucCodigo_SegSucursal, Long usuCodigo_SegUsuario,
			Long usuCodigo_SegUsuario0) throws Exception {
		ISegPermisoLogic segPermisoLogic = new SegPermisoLogic();
		segPermisoLogic.saveSegPermiso(perEstadoRegistro,
				ciaCodigo_SegCompania, gruCodigo_SegGrupoOpcion,
				opcCodigo_SegOpcion, rolCodigo_SegRol, sucCodigo_SegSucursal,
				usuCodigo_SegUsuario,usuCodigo_SegUsuario0);
	}

	public static void deleteSegPermiso(Long perCodigo)
			throws Exception {
		ISegPermisoLogic segPermisoLogic = new SegPermisoLogic();
		segPermisoLogic.deleteSegPermiso(perCodigo);
	}

	public static void updateSegPermiso(Long perCodigo,
			String perEstadoRegistro, Long ciaCodigo_SegCompania,
			Long gruCodigo_SegGrupoOpcion, Long opcCodigo_SegOpcion,
			Long rolCodigo_SegRol, Long sucCodigo_SegSucursal,
			Long usuCodigo_SegUsuario, Long usuCodigo_SegUsuario0)
					throws Exception {
		ISegPermisoLogic segPermisoLogic = new SegPermisoLogic();
		segPermisoLogic.updateSegPermiso(perCodigo, perEstadoRegistro,
				ciaCodigo_SegCompania, gruCodigo_SegGrupoOpcion,
				opcCodigo_SegOpcion, rolCodigo_SegRol, sucCodigo_SegSucursal,
				usuCodigo_SegUsuario,usuCodigo_SegUsuario0);
	}

	public static SegPermiso getSegPermiso(Long perCodigo)
			throws Exception {
		ISegPermisoLogic segPermisoLogic = new SegPermisoLogic();
		SegPermiso segPermiso = null;

		try {
			segPermiso = segPermisoLogic.getSegPermiso(perCodigo);
		} catch (Exception e) {
			throw e;
		}

		return segPermiso;
	}

	public static List<SegPermiso> findByCriteriaInSegPermiso(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		ISegPermisoLogic segPermisoLogic = new SegPermisoLogic();

		return segPermisoLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public static List<SegPermiso> findPageSegPermiso(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception {
		ISegPermisoLogic segPermisoLogic = new SegPermisoLogic();

		return segPermisoLogic.findPageSegPermiso(sortColumnName,
				sortAscending, startRow, maxResults);
	}

	public static Long findTotalNumberSegPermiso() throws Exception {
		ISegPermisoLogic segPermisoLogic = new SegPermisoLogic();

		return segPermisoLogic.findTotalNumberSegPermiso();
	}

	public static List<SegRol> getSegRol() throws Exception {
		ISegRolLogic segRolLogic = new SegRolLogic();

		return segRolLogic.getSegRol();
	}

	public static void saveSegRol(String rolDescripcion,
			Long rolDiasCaducidadPwd, String rolEstadoRegistro, String rolNombre,
			Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario)
					throws Exception {
		ISegRolLogic segRolLogic = new SegRolLogic();
		segRolLogic.saveSegRol(rolDescripcion, rolDiasCaducidadPwd,
				rolEstadoRegistro, rolNombre, sisCodigo_SegSistema,
				usuCodigo_SegUsuario);
	}

	public static void deleteSegRol(Long rolCodigo) throws Exception {
		ISegRolLogic segRolLogic = new SegRolLogic();
		segRolLogic.deleteSegRol(rolCodigo);
	}

	public static void updateSegRol(Long rolCodigo, String rolDescripcion,
			Long rolDiasCaducidadPwd, String rolEstadoRegistro, String rolNombre,
			Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario)
					throws Exception {
		ISegRolLogic segRolLogic = new SegRolLogic();
		segRolLogic.updateSegRol(rolCodigo, rolDescripcion,
				rolDiasCaducidadPwd, rolEstadoRegistro, rolNombre,
				sisCodigo_SegSistema, usuCodigo_SegUsuario);
	}

	public static SegRol getSegRol(Long rolCodigo) throws Exception {
		ISegRolLogic segRolLogic = new SegRolLogic();
		SegRol segRol = null;

		try {
			segRol = segRolLogic.getSegRol(rolCodigo);
		} catch (Exception e) {
			throw e;
		}

		return segRol;
	}

	public static List<SegRol> findByCriteriaInSegRol(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception {
		ISegRolLogic segRolLogic = new SegRolLogic();

		return segRolLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public static List<SegRol> findPageSegRol(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception {
		ISegRolLogic segRolLogic = new SegRolLogic();

		return segRolLogic.findPageSegRol(sortColumnName, sortAscending,
				startRow, maxResults);
	}

	public static Long findTotalNumberSegRol() throws Exception {
		ISegRolLogic segRolLogic = new SegRolLogic();

		return segRolLogic.findTotalNumberSegRol();
	}

	public static List<SegRolUsuario> getSegRolUsuario()
			throws Exception {
		ISegRolUsuarioLogic segRolUsuarioLogic = new SegRolUsuarioLogic();

		return segRolUsuarioLogic.getSegRolUsuario();
	}

	public static void saveSegRolUsuario(
			String rluEstadoRegistro, Long rolCodigo_SegRol,
			Long usuCodigo_SegUsuario, Long usuCodigo_SegUsuario0)
					throws Exception {
		ISegRolUsuarioLogic segRolUsuarioLogic = new SegRolUsuarioLogic();
		segRolUsuarioLogic.saveSegRolUsuario(rluEstadoRegistro,
				rolCodigo_SegRol, usuCodigo_SegUsuario,usuCodigo_SegUsuario0);
	}

	public static void deleteSegRolUsuario(Long rluCodigo)
			throws Exception {
		ISegRolUsuarioLogic segRolUsuarioLogic = new SegRolUsuarioLogic();
		segRolUsuarioLogic.deleteSegRolUsuario(rluCodigo);
	}

	public static void updateSegRolUsuario(Long rluCodigo,
			String rluEstadoRegistro, Long rolCodigo_SegRol,
			Long usuCodigo_SegUsuario, Long usuCodigo_SegUsuario0)
					throws Exception {
		ISegRolUsuarioLogic segRolUsuarioLogic = new SegRolUsuarioLogic();
		segRolUsuarioLogic.updateSegRolUsuario(rluCodigo, rluEstadoRegistro,
				rolCodigo_SegRol, usuCodigo_SegUsuario,usuCodigo_SegUsuario0);
	}

	public static SegRolUsuario getSegRolUsuario(Long rluCodigo)
			throws Exception {
		ISegRolUsuarioLogic segRolUsuarioLogic = new SegRolUsuarioLogic();
		SegRolUsuario segRolUsuario = null;

		try {
			segRolUsuario = segRolUsuarioLogic.getSegRolUsuario(rluCodigo);
		} catch (Exception e) {
			throw e;
		}

		return segRolUsuario;
	}

	public static List<SegRolUsuario> findByCriteriaInSegRolUsuario(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		ISegRolUsuarioLogic segRolUsuarioLogic = new SegRolUsuarioLogic();

		return segRolUsuarioLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public static List<SegRolUsuario> findPageSegRolUsuario(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		ISegRolUsuarioLogic segRolUsuarioLogic = new SegRolUsuarioLogic();

		return segRolUsuarioLogic.findPageSegRolUsuario(sortColumnName,
				sortAscending, startRow, maxResults);
	}

	public static Long findTotalNumberSegRolUsuario() throws Exception {
		ISegRolUsuarioLogic segRolUsuarioLogic = new SegRolUsuarioLogic();

		return segRolUsuarioLogic.findTotalNumberSegRolUsuario();
	}

	public static List<SegSistema> getSegSistema() throws Exception {
		ISegSistemaLogic segSistemaLogic = new SegSistemaLogic();

		return segSistemaLogic.getSegSistema();
	}

	public static void saveSegSistema(Long sisCodigo, String sisDescripcion,
			String sisEstadoRegistro, String sisNombre, Long usuCodigo_SegUsuario)
					throws Exception {
		ISegSistemaLogic segSistemaLogic = new SegSistemaLogic();
		segSistemaLogic.saveSegSistema(sisCodigo, sisDescripcion,
				sisEstadoRegistro, sisNombre, usuCodigo_SegUsuario);
	}

	public static void deleteSegSistema(Long sisCodigo)
			throws Exception {
		ISegSistemaLogic segSistemaLogic = new SegSistemaLogic();
		segSistemaLogic.deleteSegSistema(sisCodigo);
	}

	public static void updateSegSistema(Long sisCodigo, String sisDescripcion,
			String sisEstadoRegistro, String sisNombre, Long usuCodigo_SegUsuario)
					throws Exception {
		ISegSistemaLogic segSistemaLogic = new SegSistemaLogic();
		segSistemaLogic.updateSegSistema(sisCodigo, sisDescripcion,
				sisEstadoRegistro, sisNombre, usuCodigo_SegUsuario);
	}

	public static SegSistema getSegSistema(Long sisCodigo)
			throws Exception {
		ISegSistemaLogic segSistemaLogic = new SegSistemaLogic();
		SegSistema segSistema = null;

		try {
			segSistema = segSistemaLogic.getSegSistema(sisCodigo);
		} catch (Exception e) {
			throw e;
		}

		return segSistema;
	}

	public static List<SegSistema> findByCriteriaInSegSistema(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		ISegSistemaLogic segSistemaLogic = new SegSistemaLogic();

		return segSistemaLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public static List<SegSistema> findPageSegSistema(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception {
		ISegSistemaLogic segSistemaLogic = new SegSistemaLogic();

		return segSistemaLogic.findPageSegSistema(sortColumnName,
				sortAscending, startRow, maxResults);
	}

	public static Long findTotalNumberSegSistema() throws Exception {
		ISegSistemaLogic segSistemaLogic = new SegSistemaLogic();

		return segSistemaLogic.findTotalNumberSegSistema();
	}

	public static List<SegSistemaCia> getSegSistemaCia()
			throws Exception {
		ISegSistemaCiaLogic segSistemaCiaLogic = new SegSistemaCiaLogic();

		return segSistemaCiaLogic.getSegSistemaCia();
	}

	public static void saveSegSistemaCia(Long sicCodigo,
			String sicEstadoRegistro, Long ciaCodigo_SegCompania,
			Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario)
					throws Exception {
		ISegSistemaCiaLogic segSistemaCiaLogic = new SegSistemaCiaLogic();
		segSistemaCiaLogic.saveSegSistemaCia(sicCodigo, sicEstadoRegistro,
				ciaCodigo_SegCompania, sisCodigo_SegSistema, usuCodigo_SegUsuario);
	}

	public static void deleteSegSistemaCia(Long sicCodigo)
			throws Exception {
		ISegSistemaCiaLogic segSistemaCiaLogic = new SegSistemaCiaLogic();
		segSistemaCiaLogic.deleteSegSistemaCia(sicCodigo);
	}

	public static void updateSegSistemaCia(Long sicCodigo,
			String sicEstadoRegistro, Long ciaCodigo_SegCompania,
			Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario)
					throws Exception {
		ISegSistemaCiaLogic segSistemaCiaLogic = new SegSistemaCiaLogic();
		segSistemaCiaLogic.updateSegSistemaCia(sicCodigo, sicEstadoRegistro,
				ciaCodigo_SegCompania, sisCodigo_SegSistema, usuCodigo_SegUsuario);
	}

	public static SegSistemaCia getSegSistemaCia(Long sicCodigo)
			throws Exception {
		ISegSistemaCiaLogic segSistemaCiaLogic = new SegSistemaCiaLogic();
		SegSistemaCia segSistemaCia = null;

		try {
			segSistemaCia = segSistemaCiaLogic.getSegSistemaCia(sicCodigo);
		} catch (Exception e) {
			throw e;
		}

		return segSistemaCia;
	}

	public static List<SegSistemaCia> findByCriteriaInSegSistemaCia(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		ISegSistemaCiaLogic segSistemaCiaLogic = new SegSistemaCiaLogic();

		return segSistemaCiaLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public static List<SegSistemaCia> findPageSegSistemaCia(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		ISegSistemaCiaLogic segSistemaCiaLogic = new SegSistemaCiaLogic();

		return segSistemaCiaLogic.findPageSegSistemaCia(sortColumnName,
				sortAscending, startRow, maxResults);
	}

	public static Long findTotalNumberSegSistemaCia() throws Exception {
		ISegSistemaCiaLogic segSistemaCiaLogic = new SegSistemaCiaLogic();

		return segSistemaCiaLogic.findTotalNumberSegSistemaCia();
	}

	public static List<SegSucursal> getSegSucursal() throws Exception {
		ISegSucursalLogic segSucursalLogic = new SegSucursalLogic();

		return segSucursalLogic.getSegSucursal();
	}

	public static void saveSegSucursal(Long sucCodigo, String sucCodigoInterno,
			String sucEstadoRegistro, String sucNombre, Long ciaCodigo_SegCompania,
			Long usuCodigo_SegUsuario) throws Exception {
		ISegSucursalLogic segSucursalLogic = new SegSucursalLogic();
		segSucursalLogic.saveSegSucursal(sucCodigo, sucCodigoInterno,
				sucEstadoRegistro, sucNombre, ciaCodigo_SegCompania,
				usuCodigo_SegUsuario);
	}

	public static void deleteSegSucursal(Long sucCodigo)
			throws Exception {
		ISegSucursalLogic segSucursalLogic = new SegSucursalLogic();
		segSucursalLogic.deleteSegSucursal(sucCodigo);
	}

	public static void updateSegSucursal(Long sucCodigo,
			String sucCodigoInterno, String sucEstadoRegistro, String sucNombre,
			Long ciaCodigo_SegCompania, Long usuCodigo_SegUsuario)
					throws Exception {
		ISegSucursalLogic segSucursalLogic = new SegSucursalLogic();
		segSucursalLogic.updateSegSucursal(sucCodigo, sucCodigoInterno,
				sucEstadoRegistro, sucNombre, ciaCodigo_SegCompania,
				usuCodigo_SegUsuario);
	}

	public static SegSucursal getSegSucursal(Long sucCodigo)
			throws Exception {
		ISegSucursalLogic segSucursalLogic = new SegSucursalLogic();
		SegSucursal segSucursal = null;

		try {
			segSucursal = segSucursalLogic.getSegSucursal(sucCodigo);
		} catch (Exception e) {
			throw e;
		}

		return segSucursal;
	}

	public static List<SegSucursal> findByCriteriaInSegSucursal(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		ISegSucursalLogic segSucursalLogic = new SegSucursalLogic();

		return segSucursalLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public static List<SegSucursal> findPageSegSucursal(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception {
		ISegSucursalLogic segSucursalLogic = new SegSucursalLogic();

		return segSucursalLogic.findPageSegSucursal(sortColumnName,
				sortAscending, startRow, maxResults);
	}

	public static Long findTotalNumberSegSucursal() throws Exception {
		ISegSucursalLogic segSucursalLogic = new SegSucursalLogic();

		return segSucursalLogic.findTotalNumberSegSucursal();
	}

	public static List<SegUsuario> getSegUsuario() throws Exception {
		ISegUsuarioLogic segUsuarioLogic = new SegUsuarioLogic();

		return segUsuarioLogic.getSegUsuario();
	}

	public static void saveSegUsuario(String usuApellidos,
			String usuCodigoInterno, String usuConstrasena, String usuCorreo,
			String usuEstadoRegistro, Long usuIntentosFallidos, Long ciaCodigo_SegCompania, String usuLogin, String usuNombres, Date usuUltmimaModificacionPass,
			Long usuCodigo_SegUsuario) throws Exception {
		ISegUsuarioLogic segUsuarioLogic = new SegUsuarioLogic();
		segUsuarioLogic.saveSegUsuario(usuApellidos,
				usuCodigoInterno, usuConstrasena, usuCorreo, usuEstadoRegistro, usuIntentosFallidos, ciaCodigo_SegCompania, usuLogin,
				usuNombres, usuUltmimaModificacionPass, usuCodigo_SegUsuario);
	}

	public static void deleteSegUsuario(Long usuCodigo)
			throws Exception {
		ISegUsuarioLogic segUsuarioLogic = new SegUsuarioLogic();
		segUsuarioLogic.deleteSegUsuario(usuCodigo);
	}

	public static void updateSegUsuario(String usuApellidos, Long usuCodigo,
			String usuCodigoInterno, String usuConstrasena, String usuCorreo,
			String usuEstadoRegistro, Long usuIntentosFallidos, Long ciaCodigo_SegCompania, String usuLogin, String usuNombres, Date usuUltmimaModificacionPass,
			Long usuCodigo_SegUsuario) throws Exception {
		ISegUsuarioLogic segUsuarioLogic = new SegUsuarioLogic();
		segUsuarioLogic.updateSegUsuario(usuApellidos, usuCodigo,
				usuCodigoInterno, usuConstrasena, usuCorreo, usuEstadoRegistro, usuIntentosFallidos, ciaCodigo_SegCompania, usuLogin,
				usuNombres, usuUltmimaModificacionPass, usuCodigo_SegUsuario);
	}

	public static SegUsuario getSegUsuario(Long usuCodigo)
			throws Exception {
		ISegUsuarioLogic segUsuarioLogic = new SegUsuarioLogic();
		SegUsuario segUsuario = null;

		try {
			segUsuario = segUsuarioLogic.getSegUsuario(usuCodigo);
		} catch (Exception e) {
			throw e;
		}

		return segUsuario;
	}

	public static List<SegUsuario> findByCriteriaInSegUsuario(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		ISegUsuarioLogic segUsuarioLogic = new SegUsuarioLogic();

		return segUsuarioLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public static List<SegUsuario> findPageSegUsuario(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception {
		ISegUsuarioLogic segUsuarioLogic = new SegUsuarioLogic();

		return segUsuarioLogic.findPageSegUsuario(sortColumnName,
				sortAscending, startRow, maxResults);
	}

	public static Long findTotalNumberSegUsuario() throws Exception {
		ISegUsuarioLogic segUsuarioLogic = new SegUsuarioLogic();

		return segUsuarioLogic.findTotalNumberSegUsuario();
	}

	public static List<SegCompaniaDTO> getSegCompania(List<SegCompania> lasCompanias) throws Exception {
		ISegCompaniaLogic segCompaniaLogic = new SegCompaniaLogic();
		return segCompaniaLogic.findCompaniasDTO(lasCompanias);
	}

	public static List<SegSistemaDTO> getSegSistemas(List<SegSistema> losSistemas) throws Exception{
		ISegSistemaLogic segSistemaLogic = new SegSistemaLogic();
		return segSistemaLogic.findSistemasDTO(losSistemas);
	}

	public static List<SegGrupoOpcionDTO> getSegGrupoOpcion(List<SegGrupoOpcion> losGrupoOpcion)throws Exception {
		ISegGrupoOpcionLogic segGrupoOpcionLogic = new SegGrupoOpcionLogic();
		return segGrupoOpcionLogic.findGrupoOpcionDTO(losGrupoOpcion);
	}

	public static List<SegOpcionDTO> getSegOpcionesDTO(List<SegOpcion> losOpcion)throws Exception {
		ISegOpcionLogic segOpcionLogic = new SegOpcionLogic();
		return segOpcionLogic.findSegOpcionDTO(losOpcion);
	}

	public static List<SegSucursalDTO> getSegSucursalesDTO(List<SegSucursal> lasSucursales) throws Exception {
		ISegSucursalLogic segSucursalLogic = new SegSucursalLogic();
		return segSucursalLogic.findSucursalesDTO(lasSucursales);
	}

	public static List<SegSistemaCiaDTO> getSegSistemaCiaDTO(List<SegSistemaCia> losSistemasCia)throws Exception {
		ISegSistemaCiaLogic sistemaCiaLogic = new SegSistemaCiaLogic();
		return sistemaCiaLogic.findSegSistemaCiaDTO(losSistemasCia);
	}

	public static UsuarioDTO autenticar(String login, String pass) throws Exception{
		ILoginBeanDelegate loginBeanDelegate = new LoginBeanDelegate();
		return loginBeanDelegate.autenticar(login, pass);
	}
	
	
	public static List<GrupoDTO> getOpciones(String login, String sistema, String sucursal, String compania) throws Exception{
		ILoginBeanDelegate loginBeanDelegate = new LoginBeanDelegate();
		return loginBeanDelegate.getOpciones(login, sistema, sucursal, compania);
	}

	public static void saveOpcionesPorRolInPermisos(Long rolCodigo, List<SegOpcion> listOpciones, Long codigoCompania, Long sisCodigo, String codigoUsuario)throws Exception {
		ISegPermisoLogic segPermisoLogic = new SegPermisoLogic();
		segPermisoLogic.saveOptionsForRolInPermiso(rolCodigo,listOpciones,codigoCompania,sisCodigo, codigoUsuario);

	}

	public static List<SegOpcion> consultarOpcionesPorRolInPermisos(Long rolCodigo, Long codigoCompania) throws Exception{
		ISegPermisoLogic segPermisoLogic2 = new SegPermisoLogic();
		return segPermisoLogic2.getOpcionesPorRolInPermisos(rolCodigo,codigoCompania);
	}

	public static void updateOpcionesPorRolInPermisos(Long rolCodigo,List<SegOpcion> listOpciones, Long codigoCompania, Long sisCodigo, String codigoUsuario)throws Exception {
		ISegPermisoLogic segPermisoLogic = new SegPermisoLogic();
		segPermisoLogic.updateOptionsForRolInPermiso(rolCodigo,listOpciones,codigoCompania,sisCodigo, codigoUsuario);
	}

	public static Set<SegOpcion> consultarEstructuraArbol(TreeNode rootNoda)throws Exception {
		ISegPermisoLogic segPermisoLogic = new SegPermisoLogic();
		return segPermisoLogic.findEstructuraArbol(rootNoda);
	}

	public static List<SegPermiso> getPermisosCriteria(Long rolCodigo,Long codigoCompania, Long codigoOpcion, String codigoUsario)throws Exception {
		ISegPermisoLogic segPermisoLogic = new SegPermisoLogic();
		return segPermisoLogic.getPermisosCriteria(rolCodigo,codigoCompania,codigoOpcion, codigoUsario);
	}

	public static List<SegCambioPass> getSegCambioPass()
			throws Exception {
		ISegCambioPassLogic segCambioPassLogic = new SegCambioPassLogic();

		return segCambioPassLogic.getSegCambioPass();
	}

	public static void saveSegCambioPass(Long capCodigo, String capEstado,
			Date capFechaFin, Date capFechaIni, String capToken,
			Long usuCodigo_SegUsuario) throws Exception {
		ISegCambioPassLogic segCambioPassLogic = new SegCambioPassLogic();
		segCambioPassLogic.saveSegCambioPass(capCodigo, capEstado, capFechaFin,
				capFechaIni, capToken, usuCodigo_SegUsuario);
	}

	public static void deleteSegCambioPass(Long capCodigo)
			throws Exception {
		ISegCambioPassLogic segCambioPassLogic = new SegCambioPassLogic();
		segCambioPassLogic.deleteSegCambioPass(capCodigo);
	}

	public static void updateSegCambioPass(Long capCodigo, String capEstado,
			Date capFechaFin, Date capFechaIni, String capToken,
			Long usuCodigo_SegUsuario) throws Exception {
		ISegCambioPassLogic segCambioPassLogic = new SegCambioPassLogic();
		segCambioPassLogic.updateSegCambioPass(capCodigo, capEstado,
				capFechaFin, capFechaIni, capToken, usuCodigo_SegUsuario);
	}

	public static SegCambioPass getSegCambioPass(Long capCodigo)
			throws Exception {
		ISegCambioPassLogic segCambioPassLogic = new SegCambioPassLogic();
		SegCambioPass segCambioPass = null;

		try {
			segCambioPass = segCambioPassLogic.getSegCambioPass(capCodigo);
		} catch (Exception e) {
			throw e;
		}

		return segCambioPass;
	}

	public static List<SegCambioPass> findByCriteriaInSegCambioPass(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		ISegCambioPassLogic segCambioPassLogic = new SegCambioPassLogic();

		return segCambioPassLogic.findByCriteria(variables, variablesBetween,
				variablesBetweenDates);
	}

	public static List<SegCambioPass> findPageSegCambioPass(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		ISegCambioPassLogic segCambioPassLogic = new SegCambioPassLogic();

		return segCambioPassLogic.findPageSegCambioPass(sortColumnName,
				sortAscending, startRow, maxResults);
	}

	public static Long findTotalNumberSegCambioPass() throws Exception {
		ISegCambioPassLogic segCambioPassLogic = new SegCambioPassLogic();

		return segCambioPassLogic.findTotalNumberSegCambioPass();
	}

	public static List<SegUsuarioDTO> getDataSegUsuario()  throws Exception {
		ISegUsuarioLogic segUsuarioLogic = new SegUsuarioLogic();
		return segUsuarioLogic.getDataSegUsuario();

	}

	public static List<SegSucursalDTO> getDataSegSucursal()
			throws Exception {
		ISegSucursalLogic segSucursalLogic = new SegSucursalLogic();

		return segSucursalLogic.getDataSegSucursal();
	}

	public static List<SegUsuarioDTO> getSegUsuarioDTO(List<SegUsuario> losUsuarios) throws Exception {
		ISegUsuarioLogic segUsuarioLogic = new SegUsuarioLogic();
		return segUsuarioLogic.findUsuariosDTO(losUsuarios);
	}

	public static List<SegSistemaDTO> getDataSegSistema() throws Exception{
		ISegSistemaLogic segSistemaLogic = new SegSistemaLogic();
		return segSistemaLogic.getDataSegSistema();
	}

	public static List<SegOpcionDTO> getDataSegOpcion() 
			throws Exception {
		ISegOpcionLogic segOpcionLogic = new SegOpcionLogic();
		return segOpcionLogic.getDataSegOpcion();
	}

	public static List<SegRolDTO> getDataSegRol() throws Exception{
		ISegRolLogic segRolLogic = new SegRolLogic();
		return segRolLogic.getDataSegRol();
	}

	public static List<SegRolDTO> getSegRolDTO(List<SegRol> losRoles) throws Exception{
		ISegRolLogic segRolLogic = new SegRolLogic();
		return segRolLogic.findRolesDTO(losRoles);
	}

	public static List<SegAuditoriaDTO> getDataSegAuditoria() 
			throws Exception {
		ISegAuditoriaLogic segAuditoriaLogic = new SegAuditoriaLogic();
		return segAuditoriaLogic.getDataSegAuditoria();
	}

	public static List<SegGrupoOpcionDTO> getDataSegGrupoOpcion()throws Exception {
		ISegGrupoOpcionLogic segGrupoOpcionLogic = new SegGrupoOpcionLogic();
		return segGrupoOpcionLogic.getDataSegGrupoOpcion();
	}

	public static List<SegRolUsuarioDTO> getDataSegRolUsuario()throws Exception {
		ISegRolUsuarioLogic segRolUsuarioLogic = new SegRolUsuarioLogic();
		return segRolUsuarioLogic.getDataSegRolUsuario();
	}

	public static List<SegRolUsuarioDTO> getSegRolUsuariosDTO(List<SegRolUsuario> losRolUsuarios) throws Exception{
		ISegRolUsuarioLogic segRolUsuarioLogic = new SegRolUsuarioLogic();
		return segRolUsuarioLogic.findUsuariosDTO(losRolUsuarios);
	}

	public static List<SegCompaniaDTO> getDataSegCompania() throws Exception {
		ISegCompaniaLogic segCompaniaLogic = new SegCompaniaLogic();
		return segCompaniaLogic.getSegCompaniaDTO();
	}

	public static List<SegSistemaCiaDTO> getDataSegSistemaCia() throws Exception {
		ISegSistemaCiaLogic segSistemaCiaLogic = new SegSistemaCiaLogic();
		return segSistemaCiaLogic.getDataSegSistemaCia();
	}

	public static List<SegOpcionDTO> getOpcionesPorSistema(Long usuSession, String sistema) throws Exception {
		ISegOpcionLogic opcionLogic = new SegOpcionLogic();
		return opcionLogic.getOpcionesPorSistema(usuSession, sistema);
	}

	public static List<SegGrupoOpcionDTO> getGrupoOpcionesPorSistema(Long usuSession, String sistema) throws Exception{
		ISegGrupoOpcionLogic grupoOpcionLogic = new SegGrupoOpcionLogic();
		return grupoOpcionLogic.getGrupoOpcionesPorSistema(usuSession,sistema);
	}

	public static List<SegSistema> getSistemasDeUsuario(Long usuSession, String sistema) throws Exception{
		ISegSistemaLogic sistemaLogic = new SegSistemaLogic();
		return sistemaLogic.getSistemasDeUsuario(usuSession, sistema);
	}

	public static List<SegSistema> getSistemasDeUsuarioPorRol(Long usuSession) throws Exception {
		ISegSistemaLogic sistemaLogic = new SegSistemaLogic();
		return sistemaLogic.getSistemasDeUsuarioPorRol(usuSession);
	}

	public static List<SegCompania> getCompaniasDeUsuarioPorRol(Long usuSession, long sistema) throws Exception {
		ISegCompaniaLogic companiaLogic = new SegCompaniaLogic();
		return companiaLogic.getCompaniaDeUsuarioPorRol(usuSession, sistema);
	}

	public static List<SegGrupoOpcion> getGrupoOpcionesPorSistemaModel(Long usuSession, String sistema) throws Exception{
		ISegGrupoOpcionLogic grupoOpcionLogic = new SegGrupoOpcionLogic();
		return grupoOpcionLogic.getGrupoOpcionesPorSistemaModel(usuSession, sistema);
	}

	public static List<SegRolDTO> getRolesBySystem(Long usuSession,String sistema) throws Exception{
		ISegRolLogic rolLogic = new SegRolLogic();
		return rolLogic.getRolesBySystem(usuSession,sistema);
	}

	public static List<SegRol> getRolesBySystemModel(Long usuSession, String sistema) throws Exception {
		ISegRolLogic segRolLogic = new SegRolLogic();
		return segRolLogic.getRolesBySystemModel(usuSession, sistema);
	}

	public static List<SegUsuarioDTO> getUsuariosPorSistemaDTO(String sistema, String compania) throws Exception {
		ISegUsuarioLogic usuLogic = new SegUsuarioLogic();
		return usuLogic.getUsuariosPorSistemaDTO(sistema, compania);
	}

	public static List<SegUsuario> getUsuariosPorSistemaYCia(String sistema, String compania)  throws Exception{
		ISegUsuarioLogic usuLogic = new SegUsuarioLogic();
		return usuLogic.getUsersBySystemsAndCias(sistema, compania);
	}

	public static List<SegCompaniaDTO> getCompaniasDeUsuarioPorRolDTO(Long usuLogin, Long sistema) throws Exception{
		ISegCompaniaLogic companiaLogic = new SegCompaniaLogic();
		return companiaLogic.getCompaniasBySystemDTO(usuLogin, sistema);
	}

	public static List<SegSistemaCiaDTO> getSistemaCiaQuery(String sistema, String compania) throws Exception{
		ISegSistemaCiaLogic sistemaCiaLogic = new SegSistemaCiaLogic();
		return sistemaCiaLogic.getSistemasCiaQuery(sistema,compania);
	}

	public static List<SegRolUsuarioDTO> getRolesUsuarioBySystem(Long usuSession, String sistema) throws Exception {
		ISegRolUsuarioLogic rolUsuarioLogic = new SegRolUsuarioLogic();
		return rolUsuarioLogic.getRolesUsuariosBySystem(usuSession, sistema);
	}

	public static void saveSegUsuarioConRoles(String usuApellidos,
			String usuCodigoInterno, String usuConstrasena, String usuCorreo,
			String usuEstadoRegistro, Long usuIntentosFallidos, Long ciaCodigo_SegCompania, String usuLogin,
			String usuNombres, Date usuUltmimaModificacionPass, Long usuSession,
			List<String> rolesAsignados, String compania, String sistema) throws Exception {
		ISegUsuarioLogic segUsuarioLogic = new SegUsuarioLogic();
		segUsuarioLogic.saveSegUsuario(usuApellidos,
				usuCodigoInterno, usuConstrasena, usuCorreo, usuEstadoRegistro, usuIntentosFallidos, ciaCodigo_SegCompania, usuLogin,
				usuNombres, usuUltmimaModificacionPass, usuSession, rolesAsignados,compania, sistema);
	}

	public static void updateSegUsuarioConRoles(String usuApellidos,
			Long usuCodigo, String usuCodigoInterno, String usuConstrasena,
			String usuCorreo, String usuEstadoRegistro, Long usuIntentosFallidos, Long ciaCodigo_SegCompania,
			String usuLogin, String usuNombres, Date usuUltmimaModificacionPass,
			Long usuSession, List<String> rolesAsignados, String compania, String sistema) throws Exception {
		ISegUsuarioLogic segUsuarioLogic = new SegUsuarioLogic();
		segUsuarioLogic.updateSegUsuarioRoles(usuApellidos, usuCodigo,
				usuCodigoInterno, usuConstrasena, usuCorreo, usuEstadoRegistro, usuIntentosFallidos, ciaCodigo_SegCompania, usuLogin,
				usuNombres, usuUltmimaModificacionPass, usuSession, rolesAsignados, compania, sistema);
	}

	public static Object[] getRolesSeleccionadosPreviamente(String usuCodigo) throws Exception{
		ISegRolUsuarioLogic rolUsuLogic = new SegRolUsuarioLogic();
		return rolUsuLogic.getRolesSeleccionadosPreviamente(usuCodigo);
	}

	public static List<SegOpcion> consultarOpcionesPorRolInPermisosSisCia(Long codigoRol, Long codigoCompania) throws Exception {
		ISegPermisoLogic segPermisoLogic2 = new SegPermisoLogic();
		return segPermisoLogic2.getOpcionesPorRolInPermisosSisCia(codigoRol,codigoCompania);
	}

	public static List<SegUsuario> getUsuarioPorRol(Long codigoRoles) throws Exception {
		ISegUsuarioLogic usuarioLogic = new SegUsuarioLogic();
		return usuarioLogic.getUsuariosPorRol(codigoRoles);
	}

	public static List<SegCompania> getCompaniasPorRol(Long codigoRol)throws Exception {
		ISegCompaniaLogic segCompaniaLogic = new SegCompaniaLogic();
		return segCompaniaLogic.getCompaniasPorRol(codigoRol);
	}

	public static List<SegRol> getRolesBySystemModelNoAdmin(String sistema) throws Exception {
		ISegRolLogic segRolLogic = new SegRolLogic();
		return segRolLogic.getRolesBySystemModelNoAdmin(sistema);
	}

	public static List<SegRol> findAdministradores()throws Exception {
		ISegRolLogic rolLogic = new SegRolLogic();
		return rolLogic.findAdministradores();
	}

	public static List<SegUsuarioDTO> getDataUsersAdmin() throws Exception {
		ISegUsuarioLogic usuarioLogic = new SegUsuarioLogic();
		return usuarioLogic.getUsersAdmin();
	}

	public static List<SegOpcion> consultarOpcionesPorRolUnionUserInPermisos(
			Long codigoRol, Long codigoCompania, String codigoUsario)  throws Exception{
		ISegPermisoLogic segPermisoLogic2 = new SegPermisoLogic();
		return segPermisoLogic2.consultarOpcionesPorRolUnionUserInPermisos(codigoRol,codigoCompania, codigoUsario);
	}

	public static List<SegOpcion> consultarOpcionesPorUsuarioInPermisosSisCia(
			Long rolCodigo, Long codigoCompania, Long usuSession) throws Exception{
		ISegPermisoLogic permLogic = new SegPermisoLogic();
		return permLogic.consultarOpcionesPorUsuarioEnPermisos(rolCodigo, codigoCompania, usuSession);
	}
	
	public static List<GrupoDTO> getOpciones(String login) throws Exception {
		ISegOpcionLogic segOpcionLogic = new SegOpcionLogic();

		return segOpcionLogic.getOpciones(login);
	}
}
