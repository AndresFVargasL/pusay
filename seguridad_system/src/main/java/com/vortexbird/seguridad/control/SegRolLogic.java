package com.vortexbird.seguridad.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vortexbird.seguridad.dataaccess.daoFactory.JPADaoFactory;
import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.SegRolDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.Utilities;


public class SegRolLogic implements ISegRolLogic {
	public List<SegRol> getSegRol() throws Exception {
		List<SegRol> list = new ArrayList<SegRol>();

		try {
			list = JPADaoFactory.getInstance().getSegRolDAO().findAll(0);
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL +
					"SegRol");
		} finally {
			EntityManagerHelper.closeEntityManager();
		}

		return list;
	}

	public void saveSegRol(String rolDescripcion,
			Long rolDiasCaducidadPwd, String rolEstadoRegistro, String rolNombre,
			Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario)
					throws Exception {
		SegRol entity = null;

		try {

			if ((rolDescripcion != null) &&
					(Utilities.checkWordAndCheckWithlength(rolDescripcion, 200) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolDescripcion");
			}

			if ((rolDiasCaducidadPwd != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							rolDiasCaducidadPwd, 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolDiasCaducidadPwd");
			}

			if (rolEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"rolEstadoRegistro");
			}

			if ((rolEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(rolEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolEstadoRegistro");
			}

			if (rolNombre == null) {
				throw new ZMessManager().new EmptyFieldException("rolNombre");
			}

			if ((rolNombre != null) &&
					(Utilities.checkWordAndCheckWithlength(rolNombre, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolNombre");
			}

			if (sisCodigo_SegSistema == null) {
				throw new ZMessManager().new EmptyFieldException(
						"sisCodigo_SegSistema");
			}

			if ((sisCodigo_SegSistema != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							sisCodigo_SegSistema, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"sisCodigo_SegSistema");
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

			ISegSistemaLogic logicSegSistema1 = new SegSistemaLogic();
			ISegUsuarioLogic logicSegUsuario2 = new SegUsuarioLogic();
			SegSistema segSistemaClass = logicSegSistema1.getSegSistema(sisCodigo_SegSistema);
			SegUsuario segUsuarioClass = logicSegUsuario2.getSegUsuario(usuCodigo_SegUsuario);

			if (segSistemaClass == null) {
				throw new ZMessManager().new ForeignException("segSistema");
			}

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			entity = new SegRol();
			//entity.setRolCodigo(rolCodigo);
			entity.setRolDescripcion(rolDescripcion);
			entity.setRolDiasCaducidadPwd(rolDiasCaducidadPwd);
			entity.setRolEstadoRegistro(rolEstadoRegistro);
			entity.setRolNombre(rolNombre);
			entity.setSegSistema(segSistemaClass);
			entity.setSegUsuario(segUsuarioClass);
			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegRolDAO().save(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public void deleteSegRol(Long rolCodigo) throws Exception {
		SegRol entity = null;

		if (rolCodigo == null) {
			throw new ZMessManager().new EmptyFieldException("rolCodigo");
		}

		List<SegPermiso> segPermisos = null;
		List<SegRolUsuario> segRolUsuarios = null;
		entity = getSegRol(rolCodigo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException("SegRol");
		}

		try {
			segPermisos = JPADaoFactory.getInstance().getSegPermisoDAO()
					.findByProperty("segRol.rolCodigo",
							rolCodigo, 0);

			if (Utilities.validationsList(segPermisos) == true) {
				throw new ZMessManager().new DeletingException("segPermisos");
			}

			segRolUsuarios = JPADaoFactory.getInstance().getSegRolUsuarioDAO()
					.findByProperty("segRol.rolCodigo",
							rolCodigo, 0);

			if (Utilities.validationsList(segRolUsuarios) == true) {
				throw new ZMessManager().new DeletingException("segRolUsuarios");
			}

			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegRolDAO().delete(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public void updateSegRol(Long rolCodigo, String rolDescripcion,
			Long rolDiasCaducidadPwd, String rolEstadoRegistro, String rolNombre,
			Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario)
					throws Exception {
		SegRol entity = null;

		try {
			if (rolCodigo == null) {
				throw new ZMessManager().new EmptyFieldException("rolCodigo");
			}

			if ((rolCodigo != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							rolCodigo, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolCodigo");
			}

			if ((rolDescripcion != null) &&
					(Utilities.checkWordAndCheckWithlength(rolDescripcion, 200) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolDescripcion");
			}

			if ((rolDiasCaducidadPwd != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							rolDiasCaducidadPwd, 3, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolDiasCaducidadPwd");
			}

			if (rolEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"rolEstadoRegistro");
			}

			if ((rolEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(rolEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolEstadoRegistro");
			}

			if (rolNombre == null) {
				throw new ZMessManager().new EmptyFieldException("rolNombre");
			}

			if ((rolNombre != null) &&
					(Utilities.checkWordAndCheckWithlength(rolNombre, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolNombre");
			}

			if (sisCodigo_SegSistema == null) {
				throw new ZMessManager().new EmptyFieldException(
						"sisCodigo_SegSistema");
			}

			if ((sisCodigo_SegSistema != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							sisCodigo_SegSistema, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"sisCodigo_SegSistema");
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

			ISegSistemaLogic logicSegSistema1 = new SegSistemaLogic();

			ISegUsuarioLogic logicSegUsuario2 = new SegUsuarioLogic();
			SegSistema segSistemaClass = logicSegSistema1.getSegSistema(sisCodigo_SegSistema);
			SegUsuario segUsuarioClass = logicSegUsuario2.getSegUsuario(usuCodigo_SegUsuario);

			if (segSistemaClass == null) {
				throw new ZMessManager().new ForeignException("segSistema");
			}

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			entity = getSegRol(rolCodigo);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setRolCodigo(rolCodigo);
			entity.setRolDescripcion(rolDescripcion);
			entity.setRolDiasCaducidadPwd(rolDiasCaducidadPwd);
			entity.setRolEstadoRegistro(rolEstadoRegistro);
			entity.setRolNombre(rolNombre);
			entity.setSegSistema(segSistemaClass);
			entity.setSegUsuario(segUsuarioClass);
			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegRolDAO().update(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public SegRol getSegRol(Long rolCodigo) throws Exception {
		SegRol entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegRolDAO()
					.findById(rolCodigo);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegRol");
		} finally {
		}

		return entity;
	}

	public List<SegRol> findPageSegRol(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception {
		List<SegRol> entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegRolDAO()
					.findPageSegRol(sortColumnName,
							sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegRol");
		}

		return entity;
	}

	public Long findTotalNumberSegRol() throws Exception {
		Long entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegRolDAO()
					.findTotalNumberSegRol();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegRol Count");
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
	public List<SegRol> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception {
		List<SegRol> list = new ArrayList<SegRol>();
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
			list = JPADaoFactory.getInstance().getSegRolDAO()
					.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	public List<SegRolDTO> getDataSegRol() throws Exception {
		try {
			List<SegRol> segRol = JPADaoFactory.getInstance().getSegRolDAO().findAll(0);

			List<SegRolDTO> segRolDTO = new ArrayList<SegRolDTO>();

			for (SegRol segRolTmp : segRol) {
				SegRolDTO segRolDTO2 = new SegRolDTO();

				segRolDTO2.setRolCodigo(segRolTmp.getRolCodigo().toString());
				segRolDTO2.setRolDescripcion((segRolTmp.getRolDescripcion() != null)? segRolTmp.getRolDescripcion() : null);
				segRolDTO2.setRolDiasCaducidadPwd((segRolTmp.getRolDiasCaducidadPwd().toString() != null)? segRolTmp.getRolDiasCaducidadPwd().toString() : null);
				segRolDTO2.setRolEstadoRegistro((segRolTmp.getRolEstadoRegistro().equals("0"))? "Inactivo" : "Activo");
				segRolDTO2.setRolNombre((segRolTmp.getRolNombre() != null)? segRolTmp.getRolNombre() : null);
				segRolDTO2.setSisCodigo_SegSistema((segRolTmp.getSegSistema().getSisCodigo().toString() != null)? segRolTmp.getSegSistema().getSisNombre().toString() : null);
				segRolDTO2.setUsuCodigo_SegUsuario((segRolTmp.getSegUsuario().getUsuCodigo().toString() != null)? segRolTmp.getSegUsuario().getUsuCodigo().toString() : null);

				segRolDTO.add(segRolDTO2);
			}

			return segRolDTO;
		} catch (Exception e) {
			throw e;
		}finally{
			EntityManagerHelper.closeEntityManager();
		}
	}

	@Override
	public List<SegRolDTO> findRolesDTO(List<SegRol> losRoles) throws Exception {

		List<SegRolDTO> listRolDTO = new ArrayList<SegRolDTO>();

		for (SegRol rol : losRoles) {

			SegRolDTO rolDTO = new SegRolDTO();
			rolDTO.setRolCodigo((rol.getRolCodigo().toString()));
			rolDTO.setRolDescripcion((rol.getRolDescripcion()));
			rolDTO.setRolDiasCaducidadPwd(rol.getRolDiasCaducidadPwd().toString());
			rolDTO.setRolEstadoRegistro(rol.getRolEstadoRegistro());
			rolDTO.setRolEstadoRegistroNombre(rol.getRolEstadoRegistro().equals("0")?"Inactivo":"Activo");
			rolDTO.setRolNombre(rol.getRolNombre());
			rolDTO.setSisCodigo_SegSistema(rol.getSegSistema().getSisNombre().toString());
			listRolDTO.add(rolDTO);
		}
		return listRolDTO;
	}

	@Override
	public List<SegRolDTO> getRolesBySystem(Long usuSession, String sistema) throws Exception {

		try {

			List<SegRolDTO> segRolDTO = new ArrayList<SegRolDTO>();
			SegSistema sis = null;

			if (usuSession==null || usuSession.equals("")) {
				throw new Exception("El usuario debe estar logeado para realizar esta consulta");
			}

			if (sistema ==null || sistema.equals("")) {
				throw new Exception("El usuario debe ser administrador de algun sistema");
			}

			List<Object[]> objectsRol = JPADaoFactory.getInstance().getSegRolDAO().getSegRolesBySystem(usuSession, sistema);

			for (int i = 0; i < objectsRol.size(); i++) {

				Object[] obj = objectsRol.get(i);
				SegRolDTO segRolDTO2 = new SegRolDTO();

				segRolDTO2.setRolCodigo(obj[0].toString());
				segRolDTO2.setRolDescripcion(obj[1] != null? obj[1].toString() : null);
				segRolDTO2.setRolDiasCaducidadPwd(obj[2] != null ? obj[2].toString() : null);
				segRolDTO2.setRolEstadoRegistro(obj[3].equals("0")? "Inactivo" : "Activo");
				segRolDTO2.setRolNombre(obj[4] != null ? obj[4].toString() : null);

				Long codigoSistema = (obj[5]!=null?Long.parseLong(obj[5].toString()):null);

				if (codigoSistema!=null) {
					sis = BusinessDelegatorView.getSegSistema(codigoSistema);
				}
				segRolDTO2.setSisCodigo_SegSistema(sis.getSisCodigo() != null ? sis.getSisNombre().toString() : null);
				segRolDTO.add(segRolDTO2);
			}
			return segRolDTO;

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<SegRol> getRolesBySystemModel(Long usuSession, String sistema) throws Exception {
		
		try {
			List<SegRol> roles = new ArrayList<SegRol>();

			if (usuSession==null || usuSession.equals("")) {
				throw new Exception("El usuario debe estar logeado para realizar esta consulta");
			}

			if (sistema ==null || sistema.equals("")) {
				throw new Exception("El usuario debe ser administrador de algun sistema");
			}

			List<Object[]> objectsRol = JPADaoFactory.getInstance().getSegRolDAO().getSegRolesBySystem(usuSession, sistema);

			for (int i = 0; i < objectsRol.size(); i++) {

				Object[] obj = objectsRol.get(i);
				
				Long codigoRol = Long.parseLong(obj[0].toString());
				SegRol rol = BusinessDelegatorView.getSegRol(codigoRol);
				roles.add(rol);
			}
			
			return roles;

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<SegRol> getRolesBySystemModelNoAdmin(String sistema)throws Exception {
		try {
			List<SegRol> roles = new ArrayList<SegRol>();

			if (sistema ==null || sistema.equals("")) {
				throw new Exception("El usuario debe ser administrador de algun sistema");
			}

			List<Object[]> objectsRol = JPADaoFactory.getInstance().getSegRolDAO().getSegRolesBySystemNoAdmin(sistema);

			for (int i = 0; i < objectsRol.size(); i++) {

				Object[] obj = objectsRol.get(i);
				
				Long codigoRol = Long.parseLong(obj[0].toString());
				SegRol rol = BusinessDelegatorView.getSegRol(codigoRol);
				roles.add(rol);
			}
			
			return roles;

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<SegRol> findAdministradores() throws Exception {
		try {
			List<SegRol> roles = new ArrayList<SegRol>();


			List<Object[]> objectsRolAdmin = JPADaoFactory.getInstance().getSegRolDAO().getSegRolesAdmin();

			for (int i = 0; i < objectsRolAdmin.size(); i++) {

				Object[] obj = objectsRolAdmin.get(i);
				
				Long codigoRol = Long.parseLong(obj[0].toString());
				SegRol rol = BusinessDelegatorView.getSegRol(codigoRol);
				roles.add(rol);
			}
			
			return roles;

		} catch (Exception e) {
			throw e;
		}
	}
}
