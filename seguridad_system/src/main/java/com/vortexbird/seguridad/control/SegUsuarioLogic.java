package com.vortexbird.seguridad.control;

import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.vortexbird.seguridad.dataaccess.daoFactory.JPADaoFactory;
import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.exceptions.ZMessManager.EmptyFieldException;
import com.vortexbird.seguridad.exceptions.ZMessManager.ForeignException;
import com.vortexbird.seguridad.exceptions.ZMessManager.NotValidFormatException;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.SegUsuarioDTO;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.Fechas;
import com.vortexbird.seguridad.utilities.Utilities;


/**
 * interface SegUsuarioLogic
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SegUsuarioLogic implements ISegUsuarioLogic {
	public List<SegUsuario> getSegUsuario() throws Exception {
		List<SegUsuario> list = new ArrayList<SegUsuario>();

		try {
			list = JPADaoFactory.getInstance().getSegUsuarioDAO().findAll(0);
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL +
					"SegUsuario");
		} finally {
			EntityManagerHelper.closeEntityManager();
		}

		return list;
	}

	public void saveSegUsuario(String usuApellidos,
			String usuCodigoInterno, String usuConstrasena, String usuCorreo,
			String usuEstadoRegistro, Long usuIntentosFallidos, Long ciaCodigo_SegCompania, String usuLogin,
			String usuNombres, Date usuUltmimaModificacionPass,
			Long usuCodigo_SegUsuario) throws Exception {
		SegUsuario entity = null;

		try {
			if (usuApellidos == null) {
				throw new ZMessManager().new EmptyFieldException("usuApellidos");
			}

			if ((usuApellidos != null) &&
					(Utilities.checkWordAndCheckWithlength(usuApellidos, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuApellidos");
			}

			if (usuCodigoInterno == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuCodigoInterno");
			}

			if ((usuCodigoInterno != null) &&
					(Utilities.checkWordAndCheckWithlength(usuCodigoInterno, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuCodigoInterno");
			}

			if (usuConstrasena == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuConstrasena");
			}

			if ((usuConstrasena != null) &&
					(Utilities.checkWordAndCheckWithlength(usuConstrasena, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuConstrasena");
			}

			if (usuEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuEstadoRegistro");
			}

			if ((usuEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(usuEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuEstadoRegistro");
			}

			if (usuLogin == null) {
				throw new ZMessManager().new EmptyFieldException("usuLogin");
			}

			if ((usuLogin != null) &&
					(Utilities.checkWordAndCheckWithlength(usuLogin, 30) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuLogin");
			}

			if (usuNombres == null) {
				throw new ZMessManager().new EmptyFieldException("usuNombres");
			}

			if ((usuNombres != null) &&
					(Utilities.checkWordAndCheckWithlength(usuNombres, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuNombres");
			}

			if (usuCodigo_SegUsuario == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuCodigo_SegUsuario");
			}

			if ((usuCodigo_SegUsuario != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							usuCodigo_SegUsuario, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuCodigo_SegUsuario");
			}
			
			
			if (ciaCodigo_SegCompania == null) {
				throw new ZMessManager().new EmptyFieldException(
						"ciaCodigo_SegCompania");
			}
			
			if ((ciaCodigo_SegCompania != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							ciaCodigo_SegCompania, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"ciaCodigo_SegCompania");
			}
			
			ISegCompaniaLogic logicSegCompania1 = new SegCompaniaLogic();
			SegCompania segCompaniaClass = logicSegCompania1.getSegCompania(ciaCodigo_SegCompania);
			
			if (segCompaniaClass == null) {
				throw new ZMessManager().new ForeignException("segCompania");
			}

			ISegUsuarioLogic logicSegUsuario1 = new SegUsuarioLogic();
			SegUsuario segUsuarioClass = logicSegUsuario1.getSegUsuario(usuCodigo_SegUsuario);

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			entity = new SegUsuario();
			entity.setUsuApellidos(usuApellidos);
			//entity.setUsuCodigo(usuCodigo_SegUsuario);
			entity.setUsuCodigoInterno(usuCodigoInterno);
			entity.setUsuConstrasena(usuConstrasena);
			entity.setUsuCorreo(usuCorreo);
			entity.setUsuEstadoRegistro(usuEstadoRegistro);
			entity.setUsuLogin(usuLogin);
			entity.setUsuNombres(usuNombres);
			entity.setUsuIntentosFallidos(0L);
			entity.setUsuCompaniaCiaCodigo(segCompaniaClass);
			entity.setUsuUltmimaModificacionPass(new Date());
			entity.setSegUsuario(segUsuarioClass);
			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegUsuarioDAO().save(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public void deleteSegUsuario(Long usuCodigo) throws Exception {
		SegUsuario entity = null;

		if (usuCodigo == null) {
			throw new ZMessManager().new EmptyFieldException("usuCodigo");
		}

		List<SegAuditoria> segAuditorias = null;
		List<SegCompania> segCompanias = null;
		List<SegGrupoOpcion> segGrupoOpcions = null;
		List<SegHistorialConstrasena> segHistorialConstrasenas = null;
		List<SegOpcion> segOpcions = null;
		List<SegParametro> segParametros = null;
		List<SegPermiso> segPermisosForModUsuCodigo = null;
		List<SegPermiso> segPermisosForUsuCodigo = null;
		List<SegRolUsuario> segRolUsuariosForModUsuCodigo = null;
		List<SegRolUsuario> segRolUsuariosForSegUsuarioUsuCodigo = null;
		List<SegRol> segRols = null;
		List<SegSistemaCia> segSistemaCias = null;
		List<SegSistema> segSistemas = null;
		List<SegSucursal> segSucursals = null;
		List<SegUsuario> segUsuarios = null;
		entity = getSegUsuario(usuCodigo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException("SegUsuario");
		}

		try {
			segAuditorias = JPADaoFactory.getInstance().getSegAuditoriaDAO()
					.findByProperty("segUsuario.usuCodigo",
							usuCodigo, 0);

			if (Utilities.validationsList(segAuditorias) == true) {
				throw new ZMessManager().new DeletingException("segAuditorias");
			}

			segCompanias = JPADaoFactory.getInstance().getSegCompaniaDAO()
					.findByProperty("segUsuario.usuCodigo",
							usuCodigo, 0);

			if (Utilities.validationsList(segCompanias) == true) {
				throw new ZMessManager().new DeletingException("segCompanias");
			}

			segGrupoOpcions = JPADaoFactory.getInstance().getSegGrupoOpcionDAO()
					.findByProperty("segUsuario.usuCodigo",
							usuCodigo, 0);

			if (Utilities.validationsList(segGrupoOpcions) == true) {
				throw new ZMessManager().new DeletingException(
						"segGrupoOpcions");
			}

			segHistorialConstrasenas = JPADaoFactory.getInstance()
					.getSegHistorialConstrasenaDAO()
					.findByProperty("segUsuario.usuCodigo",
							usuCodigo, 0);

			if (Utilities.validationsList(segHistorialConstrasenas) == true) {
				throw new ZMessManager().new DeletingException(
						"segHistorialConstrasenas");
			}

			segOpcions = JPADaoFactory.getInstance().getSegOpcionDAO()
					.findByProperty("segUsuario.usuCodigo",
							usuCodigo, 0);

			if (Utilities.validationsList(segOpcions) == true) {
				throw new ZMessManager().new DeletingException("segOpcions");
			}

			segParametros = JPADaoFactory.getInstance().getSegParametroDAO()
					.findByProperty("segUsuario.usuCodigo",
							usuCodigo, 0);

			if (Utilities.validationsList(segParametros) == true) {
				throw new ZMessManager().new DeletingException("segParametros");
			}

			segPermisosForModUsuCodigo = JPADaoFactory.getInstance()
					.getSegPermisoDAO()
					.findByProperty("segUsuario.usuCodigo",
							usuCodigo, 0);

			if (Utilities.validationsList(segPermisosForModUsuCodigo) == true) {
				throw new ZMessManager().new DeletingException(
						"segPermisosForModUsuCodigo");
			}

			segPermisosForUsuCodigo = JPADaoFactory.getInstance()
					.getSegPermisoDAO()
					.findByProperty("segUsuario.usuCodigo",
							usuCodigo, 0);

			if (Utilities.validationsList(segPermisosForUsuCodigo) == true) {
				throw new ZMessManager().new DeletingException(
						"segPermisosForUsuCodigo");
			}

			segRolUsuariosForModUsuCodigo = JPADaoFactory.getInstance()
					.getSegRolUsuarioDAO()
					.findByProperty("segUsuario.usuCodigo",
							usuCodigo, 0);

			if (Utilities.validationsList(segRolUsuariosForModUsuCodigo) == true) {
				throw new ZMessManager().new DeletingException(
						"segRolUsuariosForModUsuCodigo");
			}

			segRolUsuariosForSegUsuarioUsuCodigo = JPADaoFactory.getInstance()
					.getSegRolUsuarioDAO()
					.findByProperty("segUsuario.usuCodigo",
							usuCodigo, 0);

			if (Utilities.validationsList(segRolUsuariosForSegUsuarioUsuCodigo) == true) {
				throw new ZMessManager().new DeletingException(
						"segRolUsuariosForSegUsuarioUsuCodigo");
			}

			segRols = JPADaoFactory.getInstance().getSegRolDAO()
					.findByProperty("segUsuario.usuCodigo",
							usuCodigo, 0);

			if (Utilities.validationsList(segRols) == true) {
				throw new ZMessManager().new DeletingException("segRols");
			}

			segSistemaCias = JPADaoFactory.getInstance().getSegSistemaCiaDAO()
					.findByProperty("segUsuario.usuCodigo",
							usuCodigo, 0);

			if (Utilities.validationsList(segSistemaCias) == true) {
				throw new ZMessManager().new DeletingException("segSistemaCias");
			}

			segSistemas = JPADaoFactory.getInstance().getSegSistemaDAO()
					.findByProperty("segUsuario.usuCodigo",
							usuCodigo, 0);

			if (Utilities.validationsList(segSistemas) == true) {
				throw new ZMessManager().new DeletingException("segSistemas");
			}

			segSucursals = JPADaoFactory.getInstance().getSegSucursalDAO()
					.findByProperty("segUsuario.usuCodigo",
							usuCodigo, 0);

			if (Utilities.validationsList(segSucursals) == true) {
				throw new ZMessManager().new DeletingException("segSucursals");
			}

			segUsuarios = JPADaoFactory.getInstance().getSegUsuarioDAO()
					.findByProperty("segUsuario.usuCodigo",
							usuCodigo, 0);

			if (Utilities.validationsList(segUsuarios) == true) {
				throw new ZMessManager().new DeletingException("segUsuarios");
			}

			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegUsuarioDAO().delete(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public void updateSegUsuario(String usuApellidos, Long usuCodigo,
			String usuCodigoInterno, String usuConstrasena, String usuCorreo,
			String usuEstadoRegistro, Long usuIntentosFallidos, Long ciaCodigo_SegCompania, String usuLogin,
			String usuNombres, Date usuUltmimaModificacionPass,
			Long usuCodigo_SegUsuario) throws Exception {
		SegUsuario entity = null;

		try {
			if (usuApellidos == null) {
				throw new ZMessManager().new EmptyFieldException("usuApellidos");
			}

			if ((usuApellidos != null) &&
					(Utilities.checkWordAndCheckWithlength(usuApellidos, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuApellidos");
			}

			if (usuCodigo == null) {
				throw new ZMessManager().new EmptyFieldException("usuCodigo");
			}

			if ((usuCodigo != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							usuCodigo, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuCodigo");
			}

			if (usuCodigoInterno == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuCodigoInterno");
			}

			if ((usuCodigoInterno != null) &&
					(Utilities.checkWordAndCheckWithlength(usuCodigoInterno, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuCodigoInterno");
			}

			/*
            if (usuConstrasena == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuConstrasena");
            }

            if ((usuConstrasena != null) &&
                    (Utilities.checkWordAndCheckWithlength(usuConstrasena, 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuConstrasena");
            }
			 */

			if (usuEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuEstadoRegistro");
			}

			if ((usuEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(usuEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuEstadoRegistro");
			}

			if (usuLogin == null) {
				throw new ZMessManager().new EmptyFieldException("usuLogin");
			}

			if ((usuLogin != null) &&
					(Utilities.checkWordAndCheckWithlength(usuLogin, 30) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuLogin");
			}

			if (usuNombres == null) {
				throw new ZMessManager().new EmptyFieldException("usuNombres");
			}

			if ((usuNombres != null) &&
					(Utilities.checkWordAndCheckWithlength(usuNombres, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuNombres");
			}

			if (usuCodigo_SegUsuario == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuCodigo_SegUsuario");
			}
			
			if (ciaCodigo_SegCompania == null) {
				throw new ZMessManager().new EmptyFieldException(
						"ciaCodigo_SegCompania");
			}
			
			if ((ciaCodigo_SegCompania != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							ciaCodigo_SegCompania, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"ciaCodigo_SegCompania");
			}
			
			ISegCompaniaLogic logicSegCompania1 = new SegCompaniaLogic();
			SegCompania segCompaniaClass = logicSegCompania1.getSegCompania(ciaCodigo_SegCompania);
			
			if (segCompaniaClass == null) {
				throw new ZMessManager().new ForeignException("segCompania");
			}

			ISegUsuarioLogic logicSegUsuario1 = new SegUsuarioLogic();
			SegUsuario segUsuarioClass = logicSegUsuario1.getSegUsuario(usuCodigo_SegUsuario);

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			entity = getSegUsuario(usuCodigo);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setUsuApellidos(usuApellidos);
			entity.setUsuCodigo(usuCodigo);
			entity.setUsuCodigoInterno(usuCodigoInterno);
			if(usuConstrasena!=null&&!usuConstrasena.equals(""))
				entity.setUsuConstrasena(usuConstrasena);

			entity.setUsuCorreo(usuCorreo);
			entity.setUsuEstadoRegistro(usuEstadoRegistro);
			entity.setUsuLogin(usuLogin);
			entity.setUsuNombres(usuNombres);
			entity.setUsuIntentosFallidos(usuIntentosFallidos!=null?usuIntentosFallidos:0L);
			entity.setUsuCompaniaCiaCodigo(segCompaniaClass);
			entity.setUsuUltmimaModificacionPass(usuUltmimaModificacionPass!=null?usuUltmimaModificacionPass:new Date());
			entity.setSegUsuario(segUsuarioClass);
			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegUsuarioDAO().update(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public SegUsuario getSegUsuario(Long usuCodigo) throws Exception {
		SegUsuario entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegUsuarioDAO()
					.findById(usuCodigo);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegUsuario");
		} finally {
		}

		return entity;
	}

	public List<SegUsuario> findPageSegUsuario(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception {
		List<SegUsuario> entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegUsuarioDAO()
					.findPageSegUsuario(sortColumnName,
							sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegUsuario");
		}

		return entity;
	}

	public Long findTotalNumberSegUsuario() throws Exception {
		Long entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegUsuarioDAO()
					.findTotalNumberSegUsuario();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegUsuario Count");
		} finally {
		}

		return entity;
	}

	/**
	 *
	 * @param varibles
	 *            este arreglo debera tener:
	 *
	 * [0] = String variable = (String) varibles[i]; representa como se llama la
	 * variable en el pojo
	 *
	 * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
	 * valor necesita o no ''(comillas simples)usado para campos de tipo string
	 *
	 * [2] = Object value = varibles[i + 2]; representa el valor que se va a
	 * buscar en la BD
	 *
	 * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
	 * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
	 * en este campo iria el tipo de comparador que quiero si es = o <>
	 *
	 * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
	 * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
	 * que se le ingresen en los otros 4
	 *
	 *
	 * @param variablesBetween
	 *
	 * la diferencia son estas dos posiciones
	 *
	 * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
	 * a ser buscada en un rango
	 *
	 * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
	 *
	 * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
	 * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
	 *
	 * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
	 * ejemplo: a comparator1 1 and a < 5
	 *
	 * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
	 * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
	 * 5) *
	 * @param variablesBetweenDates(en
	 *            este caso solo para mysql)
	 *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
	 *            una fecha
	 *
	 * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
	 * dates)
	 *
	 * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
	 * dates)
	 *
	 * esto hace un between entre las dos fechas.
	 *
	 * @return lista con los objetos que se necesiten
	 * @throws Exception
	 */
	public List<SegUsuario> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception {
		List<SegUsuario> list = new ArrayList<SegUsuario>();
		String where = new String();
		String tempWhere = new String();

		if (variables != null) {
			for (int i = 0; i < variables.length; i++) {
				if ((variables[i] != null) && (variables[i + 1] != null) &&
						(variables[i + 2] != null) &&
						(variables[i + 3] != null)) {
					String variable = (String) variables[i];
					Boolean booVariable = (Boolean) variables[i + 1];
					Object value = variables[i + 2];
					String comparator = (String) variables[i + 3];

					if (booVariable.booleanValue()) {
						tempWhere = (tempWhere.length() == 0)
								? ("(model." + variable + " " + comparator + " \'" +
										value + "\' )")
										: (tempWhere + " AND (model." + variable + " " +
												comparator + " \'" + value + "\' )");
					} else {
						tempWhere = (tempWhere.length() == 0)
								? ("(model." + variable + " " + comparator + " " +
										value + " )")
										: (tempWhere + " AND (model." + variable + " " +
												comparator + " " + value + " )");
					}
				}

				i = i + 3;
			}
		}

		if (variablesBetween != null) {
			for (int j = 0; j < variablesBetween.length; j++) {
				if ((variablesBetween[j] != null) &&
						(variablesBetween[j + 1] != null) &&
						(variablesBetween[j + 2] != null) &&
						(variablesBetween[j + 3] != null) &&
						(variablesBetween[j + 4] != null)) {
					String variable = (String) variablesBetween[j];
					Object value = variablesBetween[j + 1];
					Object value2 = variablesBetween[j + 2];
					String comparator1 = (String) variablesBetween[j + 3];
					String comparator2 = (String) variablesBetween[j + 4];
					tempWhere = (tempWhere.length() == 0)
							? ("(" + value + " " + comparator1 + " " + variable +
									" and " + variable + " " + comparator2 + " " + value2 +
									" )")
									: (tempWhere + " AND (" + value + " " + comparator1 +
											" " + variable + " and " + variable + " " +
											comparator2 + " " + value2 + " )");
				}

				j = j + 4;
			}
		}

		if (variablesBetweenDates != null) {
			for (int k = 0; k < variablesBetweenDates.length; k++) {
				if ((variablesBetweenDates[k] != null) &&
						(variablesBetweenDates[k + 1] != null) &&
						(variablesBetweenDates[k + 2] != null)) {
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

					tempWhere = (tempWhere.length() == 0)
							? ("(model." + variable + " between \'" + value +
									"\' and \'" + value2 + "\')")
									: (tempWhere + " AND (model." + variable +
											" between \'" + value + "\' and \'" + value2 + "\')");
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
			list = JPADaoFactory.getInstance().getSegUsuarioDAO()
					.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	public List<SegUsuarioDTO> getDataSegUsuario() throws Exception {
		try {

			List<SegUsuario> segUsuario = JPADaoFactory.getInstance().getSegUsuarioDAO().findAll(0);

			List<SegUsuarioDTO> segUsuarioDTO = new ArrayList<SegUsuarioDTO>();

			for (SegUsuario segUsuarioTmp : segUsuario) {
				SegUsuarioDTO segUsuarioDTO2 = new SegUsuarioDTO();

				segUsuarioDTO2.setUsuCodigo(segUsuarioTmp.getUsuCodigo().toString());

				segUsuarioDTO2.setUsuApellidos((segUsuarioTmp.getUsuApellidos() != null)
						? segUsuarioTmp.getUsuApellidos() : null);
				segUsuarioDTO2.setUsuCodigoInterno((segUsuarioTmp.getUsuCodigoInterno() != null)
						? segUsuarioTmp.getUsuCodigoInterno() : null);
				segUsuarioDTO2.setUsuConstrasena((segUsuarioTmp.getUsuConstrasena() != null)
						? segUsuarioTmp.getUsuConstrasena() : null);
				segUsuarioDTO2.setUsuCorreo((segUsuarioTmp.getUsuCorreo() != null)
						? segUsuarioTmp.getUsuCorreo() : null);
				segUsuarioDTO2.setUsuEstadoRegistro((segUsuarioTmp.getUsuEstadoRegistro() != null)
						? segUsuarioTmp.getUsuEstadoRegistro() : null);
				segUsuarioDTO2.setUsuIntentosFallidos(((segUsuarioTmp.getUsuIntentosFallidos()).toString() != null)
						? (segUsuarioTmp.getUsuIntentosFallidos()).toString() : null);
				segUsuarioDTO2.setUsuLogin((segUsuarioTmp.getUsuLogin() != null)
						? segUsuarioTmp.getUsuLogin() : null);
				segUsuarioDTO2.setUsuNombres((segUsuarioTmp.getUsuNombres() != null)
						? segUsuarioTmp.getUsuNombres() : null);
				segUsuarioDTO2.setUsuUltmimaModificacionPass(segUsuarioTmp.getUsuUltmimaModificacionPass());
				segUsuarioDTO2.setUsuCodigo_SegUsuario(((segUsuarioTmp.getSegUsuario().getUsuCodigo()).toString() != null)
						? (segUsuarioTmp.getSegUsuario().getUsuCodigo()).toString() : null);
				segUsuarioDTO.add(segUsuarioDTO2);
			}

			return segUsuarioDTO;
		} catch (Exception e) {
			throw e;
		}finally{
			EntityManagerHelper.closeEntityManager();
		}
	}

	@Override
	public List<SegUsuarioDTO> findUsuariosDTO(List<SegUsuario> losUsuarios)
			throws Exception {

		List<SegUsuarioDTO> usuariosDTO = new ArrayList<SegUsuarioDTO>(losUsuarios.size());
		for (SegUsuario usuario : losUsuarios) {

			SegUsuarioDTO usuDTO = new SegUsuarioDTO();
			usuDTO.setUsuCodigo((usuario.getUsuCodigo().toString()));
			usuDTO.setUsuApellidos((usuario.getUsuApellidos()));
			usuDTO.setUsuCodigoInterno(usuario.getUsuCodigoInterno());
			usuDTO.setUsuConstrasena(usuario.getUsuConstrasena());
			usuDTO.setUsuCorreo(usuario.getUsuCorreo());
			usuDTO.setUsuEstadoRegistro(usuario.getUsuEstadoRegistro());

			if (usuario.getUsuEstadoRegistro().equals("0")==true) {
				usuDTO.setUsuEstadoRegistroNombre("Inactivo");
			}else {
				usuDTO.setUsuEstadoRegistroNombre("Activo");
			}	

			usuDTO.setUsuIntentosFallidos(usuario.getUsuIntentosFallidos().toString());
			usuDTO.setUsuLogin(usuario.getUsuLogin());
			usuDTO.setUsuNombres(usuario.getUsuNombres());
			usuDTO.setUsuUltmimaModificacionPass(usuario.getUsuUltmimaModificacionPass());

			usuariosDTO.add(usuDTO);
		}

		return usuariosDTO;
	}

	@Override
	public List<SegUsuarioDTO> getUsuariosPorSistemaDTO(String sistema,String compania) throws Exception {

		ISegSistemaCiaLogic logicSegSistemaCia = new SegSistemaCiaLogic();
		
		try {
			Fechas fechas = new Fechas();
			String patron = "yyyy-MM-dd HH:mm:ss";

			List<SegUsuarioDTO> segUsuarioDTO = new ArrayList<SegUsuarioDTO>();

			if (sistema ==null || sistema.equals("")) {
				throw new Exception("El usuario debe ser administrador de algun sistema");
			}

			if (compania ==null || compania.equals("")) {
				throw new Exception("El usuario debe ser administrador de alguna compa�ia");
			}
			
			List<SegSistemaCia> siscias = logicSegSistemaCia.findByCriteria(new Object[]{"segCompania",false,compania,"=","segSistema",false,sistema,"="},null, null);
			Long sisCiaUnico = siscias.get(0).getSicCodigo();

			List<Object[]> objectsUsers = JPADaoFactory.getInstance().getSegUsuarioDAO().getSegUsersBySystemAndCias(sisCiaUnico, compania);

			for (int i = 0; i < objectsUsers.size(); i++) {

				Object[] obj = objectsUsers.get(i);

				if (obj[0]!=null) {
					Long usuCodigo = Long.parseLong(obj[0].toString());
					SegUsuario usu = BusinessDelegatorView.getSegUsuario(usuCodigo);
					
					SegUsuarioDTO segUsuarioDTO2 = new SegUsuarioDTO();
					segUsuarioDTO2.setUsuCodigo(usu.getUsuCodigo().toString());
					segUsuarioDTO2.setUsuApellidos((usu.getUsuApellidos()) != null ? usu.getUsuApellidos().toString() : null);
					segUsuarioDTO2.setUsuCodigoInterno((usu.getUsuCodigoInterno() != null)?usu.getUsuCodigoInterno().toString() : null);
					segUsuarioDTO2.setUsuConstrasena((usu.getUsuConstrasena() != null)? usu.getUsuConstrasena().toString() : null);
					segUsuarioDTO2.setUsuCorreo((usu.getUsuCorreo() != null)? usu.getUsuCorreo().toString() : null);
					segUsuarioDTO2.setUsuEstadoRegistro((usu.getUsuEstadoRegistro()!=null) ?usu.getUsuEstadoRegistro().toString() : null);
					segUsuarioDTO2.setUsuIntentosFallidos((usu.getUsuIntentosFallidos() != null)? usu.getUsuIntentosFallidos().toString() : null);
					segUsuarioDTO2.setUsuLogin((usu.getUsuLogin() != null)? usu.getUsuLogin().toString() : null);
					segUsuarioDTO2.setUsuNombres((usu.getUsuNombres() != null)? usu.getUsuNombres().toString() : null);
					segUsuarioDTO2.setUsuUltmimaModificacionPass(usu.getUsuUltmimaModificacionPass()!=null?fechas.strToDate(usu.getUsuUltmimaModificacionPass().toString(), patron):null);

					segUsuarioDTO.add(segUsuarioDTO2);
				}
				
			}

			return segUsuarioDTO;

		} catch (Exception e) {
			throw e;
		}finally{
			EntityManagerHelper.closeEntityManager();	
		}
	}

	@Override
	public List<SegUsuario> getUsersBySystemsAndCias(String sistema, String compania) throws Exception {

		ISegSistemaCiaLogic logicSegSistemaCia = new SegSistemaCiaLogic();
		
		try {

			List<SegUsuario> usuarios = new ArrayList<SegUsuario>();

			if (sistema ==null || sistema.equals("")) {
				throw new Exception("El usuario debe ser administrador de algun sistema");
			}

			if (compania ==null || compania.equals("")) {
				throw new Exception("El usuario debe ser administrador de alguna compa�ia");
			}
			
			List<SegSistemaCia> siscias = logicSegSistemaCia.findByCriteria(new Object[]{"segCompania",false,compania,"=","segSistema",false,sistema,"="},null, null);
			Long sisCiaUnico = siscias.get(0).getSicCodigo();

			List<Object[]> objectsUsers = JPADaoFactory.getInstance().getSegUsuarioDAO().getSegUsersBySystemAndCias(sisCiaUnico, compania);

			for (int i = 0; i < objectsUsers.size(); i++) {
				Object[] obj = objectsUsers.get(i);

				Long usuCodigo = Long.parseLong(obj[0].toString());
				SegUsuario usu = BusinessDelegatorView.getSegUsuario(usuCodigo);
				usuarios.add(usu);
			}
			return usuarios;			

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void saveSegUsuario(String usuApellidos, String usuCodigoInterno,
			String usuConstrasena, String usuCorreo, String usuEstadoRegistro,
			Long usuIntentosFallidos, Long ciaCodigo_SegCompania, String usuLogin, String usuNombres,
			Date usuUltmimaModificacionPass, Long usuSession,
			List<String> rolesAsignados, String compania, String sistema) throws Exception {

		SegUsuario entity = null;

		try {
			if (usuApellidos == null) {
				throw new ZMessManager().new EmptyFieldException("usuApellidos");
			}

			if ((usuApellidos != null) &&
					(Utilities.checkWordAndCheckWithlength(usuApellidos, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuApellidos");
			}

			if (usuCodigoInterno == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuCodigoInterno");
			}

			if ((usuCodigoInterno != null) &&
					(Utilities.checkWordAndCheckWithlength(usuCodigoInterno, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuCodigoInterno");
			}

			if (usuConstrasena == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuConstrasena");
			}

			if ((usuConstrasena != null) &&
					(Utilities.checkWordAndCheckWithlength(usuConstrasena, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuConstrasena");
			}

			if (usuEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuEstadoRegistro");
			}

			if ((usuEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(usuEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuEstadoRegistro");
			}

			if (usuLogin == null) {
				throw new ZMessManager().new EmptyFieldException("usuLogin");
			}

			if ((usuLogin != null) &&
					(Utilities.checkWordAndCheckWithlength(usuLogin, 30) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuLogin");
			}

			if (usuNombres == null) {
				throw new ZMessManager().new EmptyFieldException("usuNombres");
			}

			if ((usuNombres != null) &&
					(Utilities.checkWordAndCheckWithlength(usuNombres, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuNombres");
			}

			if (usuSession == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuCodigo_SegUsuario");
			}

			if ((usuSession != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							usuSession, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuCodigo_SegUsuario");
			}
			
			
			if (ciaCodigo_SegCompania == null) {
				throw new ZMessManager().new EmptyFieldException(
						"ciaCodigo_SegCompania");
			}
			
			if ((ciaCodigo_SegCompania != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							ciaCodigo_SegCompania, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"ciaCodigo_SegCompania");
			}
			
			
			
			ISegCompaniaLogic logicSegCompania1 = new SegCompaniaLogic();
			SegCompania segCompaniaClass = logicSegCompania1.getSegCompania(ciaCodigo_SegCompania);
			
			if (segCompaniaClass == null) {
				throw new ZMessManager().new ForeignException("segCompania");
			}


			ISegUsuarioLogic logicSegUsuario1 = new SegUsuarioLogic();
			SegUsuario segUsuarioClass = logicSegUsuario1.getSegUsuario(usuSession);

			ISegRolUsuarioLogic rolUsuarioLogic = new SegRolUsuarioLogic();
			ISegPermisoLogic permisoLogic = new SegPermisoLogic();
			ISegSistemaCiaLogic logicSegSistemaCia = new SegSistemaCiaLogic();

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}
			//TODO Vargas: Validado que no se crean dos usuarios con el mismo correo electronico
			
			SegUsuario segUsuario=consultarUsuarioPorLogin(usuLogin);
			
			if(segUsuario!=null){
				throw new ZMessManager("Ya existe un usuario con el login "+usuLogin);
			}
			
			

			entity = new SegUsuario();
			entity.setUsuApellidos(usuApellidos);
			entity.setUsuCodigoInterno(usuCodigoInterno);
			entity.setUsuConstrasena(usuConstrasena);
			entity.setUsuCorreo(usuCorreo);
			entity.setUsuEstadoRegistro(usuEstadoRegistro);
			entity.setUsuLogin(usuLogin);
			entity.setUsuNombres(usuNombres);
			entity.setUsuIntentosFallidos(0L);
			entity.setUsuCompaniaCiaCodigo(segCompaniaClass);
			entity.setUsuUltmimaModificacionPass(new Date());
			entity.setSegUsuario(segUsuarioClass);
			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegUsuarioDAO().save(entity);
			EntityManagerHelper.flush();

			for (int i = 0; i < rolesAsignados.size(); i++) {
				rolUsuarioLogic.saveSegRolUsuario("1", Long.parseLong(rolesAsignados.get(i)), entity.getUsuCodigo(), entity.getUsuCodigo());
			}
			if (usuSession==0) {
				permisoLogic.saveSegPermiso("1", null , null, null, null, null, entity.getUsuCodigo(), null);	
			}else {
				List<SegSistemaCia> siscias = logicSegSistemaCia.findByCriteria(new Object[]{"segCompania",false,compania,"=","segSistema",false,sistema,"="},null, null);
				Long sisCiaUnico = siscias.get(0).getSicCodigo();
				permisoLogic.saveSegPermiso("1", sisCiaUnico , null, null, null, null, entity.getUsuCodigo(), null);
			}
			EntityManagerHelper.commit();

		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List<SegUsuario> findByCriteriaSegUsuario(String condicion){
		List<SegUsuario>res=new ArrayList();
		res=JPADaoFactory.getInstance().getSegUsuarioDAO().findByCriteria(condicion);
		return res;
	}

	@Override
	public void updateSegUsuarioRoles(String usuApellidos, Long usuCodigo,
			String usuCodigoInterno, String usuConstrasena, String usuCorreo,
			String usuEstadoRegistro, Long usuIntentosFallidos, Long ciaCodigo_SegCompania,
			String usuLogin, String usuNombres,
			Date usuUltmimaModificacionPass, Long usuSession,
			List<String> rolesAsignados, String compania, String sistema) throws Exception {

		SegUsuario entity = null;

		try {
			if (usuApellidos == null) {
				throw new ZMessManager().new EmptyFieldException("usuApellidos");
			}

			if ((usuApellidos != null) &&
					(Utilities.checkWordAndCheckWithlength(usuApellidos, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuApellidos");
			}

			if (usuCodigo == null) {
				throw new ZMessManager().new EmptyFieldException("usuCodigo");
			}

			if ((usuCodigo != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							usuCodigo, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuCodigo");
			}

			if (usuCodigoInterno == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuCodigoInterno");
			}

			if ((usuCodigoInterno != null) &&
					(Utilities.checkWordAndCheckWithlength(usuCodigoInterno, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuCodigoInterno");
			}

			if (usuEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuEstadoRegistro");
			}

			if ((usuEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(usuEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuEstadoRegistro");
			}

			if (usuLogin == null) {
				throw new ZMessManager().new EmptyFieldException("usuLogin");
			}

			if ((usuLogin != null) &&
					(Utilities.checkWordAndCheckWithlength(usuLogin, 30) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuLogin");
			}

			if (usuNombres == null) {
				throw new ZMessManager().new EmptyFieldException("usuNombres");
			}

			if ((usuNombres != null) &&
					(Utilities.checkWordAndCheckWithlength(usuNombres, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuNombres");
			}

			if (usuSession == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuCodigo_SegUsuario");
			}
			
			if (ciaCodigo_SegCompania == null) {
				throw new ZMessManager().new EmptyFieldException(
						"ciaCodigo_SegCompania");
			}
			
			if ((ciaCodigo_SegCompania != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							ciaCodigo_SegCompania, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"ciaCodigo_SegCompania");
			}
			
			//TODO Vargas: Validado que no se crean dos usuarios con el mismo correo electronico
			
			SegUsuario segUsuario=consultarUsuarioPorLogin(usuLogin);
			
			if(segUsuario!=null){
				throw new ZMessManager("Ya existe un usuario con el login "+usuLogin);
			}
			
			ISegCompaniaLogic logicSegCompania1 = new SegCompaniaLogic();
			SegCompania segCompaniaClass = logicSegCompania1.getSegCompania(ciaCodigo_SegCompania);
			
			if (segCompaniaClass == null) {
				throw new ZMessManager().new ForeignException("segCompania");
			}


			ISegUsuarioLogic logicSegUsuario1 = new SegUsuarioLogic();
			SegUsuario segUsuarioClass = logicSegUsuario1.getSegUsuario(usuSession);

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			entity = getSegUsuario(usuCodigo);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			ISegRolUsuarioLogic rolUsuarioLogic = new SegRolUsuarioLogic();
			ISegPermisoLogic permisoLogic = new SegPermisoLogic();
			ISegSistemaCiaLogic logicSegSistemaCia = new SegSistemaCiaLogic();
			
			Object[] rolsUsers =rolUsuarioLogic.getRolesDeUsuario(usuCodigo);

			List<String> strings = new ArrayList<String>();

			for (int i = 0; i < rolsUsers.length; i++) {
				strings.add(rolsUsers[i].toString());
			}

			entity.setUsuApellidos(usuApellidos);
			entity.setUsuCodigo(usuCodigo);
			entity.setUsuCodigoInterno(usuCodigoInterno);
			if(usuConstrasena!=null&&!usuConstrasena.equals(""))
				entity.setUsuConstrasena(usuConstrasena);
			entity.setUsuCorreo(usuCorreo);
			entity.setUsuEstadoRegistro(usuEstadoRegistro);
			entity.setUsuLogin(usuLogin);
			entity.setUsuNombres(usuNombres);
			entity.setUsuIntentosFallidos(usuIntentosFallidos!=null?usuIntentosFallidos:0L);
			entity.setUsuCompaniaCiaCodigo(segCompaniaClass);
			entity.setUsuUltmimaModificacionPass(usuUltmimaModificacionPass!=null?usuUltmimaModificacionPass:new Date());
			entity.setSegUsuario(segUsuarioClass);

			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegUsuarioDAO().update(entity);
			EntityManagerHelper.flush();

			for (int i = 0; i < rolesAsignados.size(); i++) {

				SegRolUsuario rolUsu = rolUsuarioLogic.getRolUsuariosCambiados(usuCodigo, rolesAsignados.get(i).toString());

				if (rolUsu==null) {
					rolUsuarioLogic.saveSegRolUsuario("1", Long.parseLong(rolesAsignados.get(i).toString()), usuCodigo, usuCodigo);
				}else {
					if (strings.contains(rolUsu.getRluCodigo().toString())) {
						strings.remove(rolUsu.getRluCodigo().toString());
					}
				}
			}

			for (int i = 0; i < strings.size(); i++) {
				System.out.println(Long.parseLong(strings.get(i).toString()));
				rolUsuarioLogic.deleteSegRolUsuario(Long.parseLong(strings.get(i).toString()));
			}
			
			if (usuSession!=0) {
				List<SegPermiso> permisoExistente =  permisoLogic.findPermisosByUsuAndSisCia(usuCodigo, compania, sistema);
				
				//Si el permiso no existe... Lo creo
				if (permisoExistente==null || permisoExistente.size()==0) {
					List<SegSistemaCia> siscias = logicSegSistemaCia.findByCriteria(new Object[]{"segCompania",false,compania,"=","segSistema",false,sistema,"="},null, null);
					Long sisCiaUnico = siscias.get(0).getSicCodigo();
					permisoLogic.saveSegPermiso("1", sisCiaUnico , null, null, null, null, entity.getUsuCodigo(), null);
				}else {
					Long perCodigo = permisoExistente.get(0).getPerCodigo();
					SegPermiso p  = permisoLogic.getSegPermiso(perCodigo);
					System.out.println(p.getSegSistemaCia());
					System.out.println(p.getSegUsuarioByUsuCodigo());
				}
			}

			EntityManagerHelper.commit();

		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	@Override
	public List<SegUsuario> getUsuariosPorRol(Long codigoRoles)
			throws Exception {

		List<SegUsuario> usuarios = new ArrayList<SegUsuario>();

		try {

			List<Object[]> objectsUsers = JPADaoFactory.getInstance().getSegUsuarioDAO().getSegUsersByRol(codigoRoles);

			for (int i = 0; i < objectsUsers.size(); i++) {
				Object[] obj = objectsUsers.get(i);

				Long usuCodigo = Long.parseLong(obj[0].toString());
				SegUsuario usu = BusinessDelegatorView.getSegUsuario(usuCodigo);
				usuarios.add(usu);
			}
			return usuarios;			

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<SegUsuarioDTO> getUsersAdmin() throws Exception {
		try {
			Fechas fechas = new Fechas();
			String patron = "yyyy-MM-dd HH:mm:ss";

			List<SegUsuarioDTO> segUsuarioDTO = new ArrayList<SegUsuarioDTO>();

			List<Object[]> objectsUsers = JPADaoFactory.getInstance().getSegUsuarioDAO().getUserAdmin();

			for (int i = 0; i < objectsUsers.size(); i++) {

				Object[] obj = objectsUsers.get(i);
				Long usuCodigo = Long.parseLong(obj[0].toString());
				SegUsuario usu = BusinessDelegatorView.getSegUsuario(usuCodigo);
				
				SegUsuarioDTO segUsuarioDTO2 = new SegUsuarioDTO();
				segUsuarioDTO2.setUsuCodigo(obj[0].toString());
				segUsuarioDTO2.setUsuApellidos(usu.getUsuApellidos() != null ?usu.getUsuApellidos().toString() : null);
				segUsuarioDTO2.setUsuCodigoInterno(usu.getUsuCodigoInterno() != null ? usu.getUsuCodigoInterno().toString() : null);
				segUsuarioDTO2.setUsuConstrasena(usu.getUsuConstrasena() != null ? usu.getUsuConstrasena().toString() : null);
				segUsuarioDTO2.setUsuCorreo(usu.getUsuCorreo() != null ? usu.getUsuCorreo().toString() : null);
				segUsuarioDTO2.setUsuEstadoRegistro(usu.getUsuEstadoRegistro().equals("1") ? "Activo" : "Inactivo");
				segUsuarioDTO2.setUsuIntentosFallidos(usu.getUsuIntentosFallidos() != null ? usu.getUsuIntentosFallidos().toString() : null);
				segUsuarioDTO2.setUsuLogin(usu.getUsuLogin() != null ? usu.getUsuLogin().toString() : null);
				segUsuarioDTO2.setUsuNombres(usu.getUsuNombres() != null ? usu.getUsuNombres().toString() : null);
				segUsuarioDTO2.setUsuUltmimaModificacionPass(usu.getUsuUltmimaModificacionPass()!=null?fechas.strToDate(usu.getUsuUltmimaModificacionPass().toString(), patron):null);
				
				
				segUsuarioDTO.add(segUsuarioDTO2);

			}

			return segUsuarioDTO;

		} catch (Exception e) {
			throw e;
		}finally{
			EntityManagerHelper.closeEntityManager();
		}
	}
	
	
	public SegUsuario consultarUsuarioPorLogin(String login)throws Exception{
		try {
			List<SegUsuario> listaSegUsuarios=JPADaoFactory.getInstance().getSegUsuarioDAO().consultarUsuarioPorLogin(login);
			if(listaSegUsuarios==null || listaSegUsuarios.size()==0){
				return null;
			}
			if(listaSegUsuarios.size()>0){
				return listaSegUsuarios.get(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
	
	
}
