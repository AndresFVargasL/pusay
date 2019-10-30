package com.vortexbird.seguridad.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.vortexbird.seguridad.dataaccess.daoFactory.JPADaoFactory;
import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.SegRolDTO;
import com.vortexbird.seguridad.modelo.dto.SegRolUsuarioDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.Utilities;


/**
 * interface SegRolUsuarioLogic
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SegRolUsuarioLogic implements ISegRolUsuarioLogic {
	public List<SegRolUsuario> getSegRolUsuario() throws Exception {
		List<SegRolUsuario> list = new ArrayList<SegRolUsuario>();

		try {
			list = JPADaoFactory.getInstance().getSegRolUsuarioDAO().findAll(0);
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL +
					"SegRolUsuario");
		} finally {
			EntityManagerHelper.closeEntityManager();
		}

		return list;
	}

	public void saveSegRolUsuario(String rluEstadoRegistro,
			Long rolCodigo_SegRol, Long usuCodigo_SegUsuario,
			Long usuCodigo_SegUsuario0) throws Exception {
		SegRolUsuario entity = null;

		try {
			if (rluEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"rluEstadoRegistro");
			}

			if ((rluEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(rluEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rluEstadoRegistro");
			}

			if (rolCodigo_SegRol == null) {
				throw new ZMessManager().new EmptyFieldException(
						"rolCodigo_SegRol");
			}

			if ((rolCodigo_SegRol != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							rolCodigo_SegRol, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolCodigo_SegRol");
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

			ISegRolLogic logicSegRol1 = new SegRolLogic();
			ISegUsuarioLogic logicSegUsuario2 = new SegUsuarioLogic();
			ISegUsuarioLogic logicSegUsuario3 = new SegUsuarioLogic();
			SegRol segRolClass = logicSegRol1.getSegRol(rolCodigo_SegRol);
			SegUsuario segUsuarioByModUsuCodigoClass = logicSegUsuario2.getSegUsuario(usuCodigo_SegUsuario);
			SegUsuario segUsuarioBySegUsuarioUsuCodigoClass = logicSegUsuario3.getSegUsuario(usuCodigo_SegUsuario);

			if (segRolClass == null) {
				throw new ZMessManager().new ForeignException("segRol");
			}

			if (segUsuarioByModUsuCodigoClass == null) {
				throw new ZMessManager().new ForeignException(
						"segUsuarioByModUsuCodigo");
			}

			if (segUsuarioBySegUsuarioUsuCodigoClass == null) {
				throw new ZMessManager().new ForeignException(
						"segUsuarioBySegUsuarioUsuCodigo");
			}

			entity = new SegRolUsuario();
			entity.setRluCodigo(null);
			entity.setRluEstadoRegistro(rluEstadoRegistro);
			entity.setSegRol(segRolClass);
			entity.setSegUsuarioByModUsuCodigo(segUsuarioByModUsuCodigoClass);
			entity.setSegUsuarioBySegUsuarioUsuCodigo(segUsuarioBySegUsuarioUsuCodigoClass);
			//			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegRolUsuarioDAO().save(entity);
			//			EntityManagerHelper.commit();
		} catch (Exception e) {
			//			EntityManagerHelper.rollback();
			throw e;
		}
	}

	public void deleteSegRolUsuario(Long rluCodigo) throws Exception {
		SegRolUsuario entity = null;

		if (rluCodigo == null) {
			throw new ZMessManager().new EmptyFieldException("rluCodigo");
		}

		entity = getSegRolUsuario(rluCodigo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException("SegRolUsuario");
		}

		try {
//			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegRolUsuarioDAO().delete(entity);
			//			EntityManagerHelper.commit();
		} catch (Exception e) {
			//			EntityManagerHelper.rollback();
			throw e;
		} finally {
			//			EntityManagerHelper.closeEntityManager();
		}
	}

	public void updateSegRolUsuario(Long rluCodigo, String rluEstadoRegistro,
			Long rolCodigo_SegRol, Long usuCodigo_SegUsuario,
			Long usuCodigo_SegUsuario0) throws Exception {
		SegRolUsuario entity = null;

		try {
			if (rluCodigo == null) {
				throw new ZMessManager().new EmptyFieldException("rluCodigo");
			}

			if ((rluCodigo != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							rluCodigo, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rluCodigo");
			}

			if (rluEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"rluEstadoRegistro");
			}

			if ((rluEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(rluEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rluEstadoRegistro");
			}

			if (rolCodigo_SegRol == null) {
				throw new ZMessManager().new EmptyFieldException(
						"rolCodigo_SegRol");
			}

			if ((rolCodigo_SegRol != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							rolCodigo_SegRol, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolCodigo_SegRol");
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

			ISegRolLogic logicSegRol1 = new SegRolLogic();

			ISegUsuarioLogic logicSegUsuario2 = new SegUsuarioLogic();

			ISegUsuarioLogic logicSegUsuario3 = new SegUsuarioLogic();
			SegRol segRolClass = logicSegRol1.getSegRol(rolCodigo_SegRol);
			SegUsuario segUsuarioByModUsuCodigoClass = logicSegUsuario2.getSegUsuario(usuCodigo_SegUsuario);
			SegUsuario segUsuarioBySegUsuarioUsuCodigoClass = logicSegUsuario3.getSegUsuario(usuCodigo_SegUsuario);

			if (segRolClass == null) {
				throw new ZMessManager().new ForeignException("segRol");
			}

			if (segUsuarioByModUsuCodigoClass == null) {
				throw new ZMessManager().new ForeignException(
						"segUsuarioByModUsuCodigo");
			}

			if (segUsuarioBySegUsuarioUsuCodigoClass == null) {
				throw new ZMessManager().new ForeignException(
						"segUsuarioBySegUsuarioUsuCodigo");
			}

			entity = getSegRolUsuario(rluCodigo);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setRluCodigo(rluCodigo);
			entity.setRluEstadoRegistro(rluEstadoRegistro);
			entity.setSegRol(segRolClass);
			entity.setSegUsuarioByModUsuCodigo(segUsuarioByModUsuCodigoClass);
			entity.setSegUsuarioBySegUsuarioUsuCodigo(segUsuarioBySegUsuarioUsuCodigoClass);
			//			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegRolUsuarioDAO().update(entity);
			//			EntityManagerHelper.commit();
		} catch (Exception e) {
			//			EntityManagerHelper.rollback();
			throw e;
		} finally {
			//			EntityManagerHelper.closeEntityManager();
		}
	}

	public SegRolUsuario getSegRolUsuario(Long rluCodigo)
			throws Exception {
		SegRolUsuario entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegRolUsuarioDAO()
					.findById(rluCodigo);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegRolUsuario");
		} finally {
		}

		return entity;
	}

	public List<SegRolUsuario> findPageSegRolUsuario(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception {
		List<SegRolUsuario> entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegRolUsuarioDAO()
					.findPageSegRolUsuario(sortColumnName,
							sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegRolUsuario");
		}

		return entity;
	}

	public Long findTotalNumberSegRolUsuario() throws Exception {
		Long entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegRolUsuarioDAO()
					.findTotalNumberSegRolUsuario();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegRolUsuario Count");
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
	public List<SegRolUsuario> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception {
		List<SegRolUsuario> list = new ArrayList<SegRolUsuario>();
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
			list = JPADaoFactory.getInstance().getSegRolUsuarioDAO()
					.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	public List<SegRolUsuarioDTO> getDataSegRolUsuario() throws Exception {

		try {
			List<SegRolUsuario> segRolUsuario = JPADaoFactory.getInstance().getSegRolUsuarioDAO().findAll(0);

			List<SegRolUsuarioDTO> segRolUsuarioDTO = new ArrayList<SegRolUsuarioDTO>();

			for (SegRolUsuario segRolUsuarioTmp : segRolUsuario) {
				SegRolUsuarioDTO segRolUsuarioDTO2 = new SegRolUsuarioDTO();

				segRolUsuarioDTO2.setRluCodigo(segRolUsuarioTmp.getRluCodigo().toString());
				segRolUsuarioDTO2.setRluEstadoRegistro(segRolUsuarioTmp.getRluEstadoRegistro()!= null ? segRolUsuarioTmp.getRluEstadoRegistro().toString():null);
				segRolUsuarioDTO2.setRluEstadoRegistroNombre(segRolUsuarioTmp.getRluEstadoRegistro().equals("1")? "Activo" : "Inactivo");
				segRolUsuarioDTO2.setRolCodigo_SegRol((segRolUsuarioTmp.getSegRol().getRolCodigo() != null)? segRolUsuarioTmp.getSegRol().getRolNombre() : null);
				segRolUsuarioDTO2.setUsuCodigo_SegUsuario((segRolUsuarioTmp.getSegUsuarioBySegUsuarioUsuCodigo()!= null)? segRolUsuarioTmp.getSegUsuarioBySegUsuarioUsuCodigo().getUsuLogin() : null);

				segRolUsuarioDTO.add(segRolUsuarioDTO2);
			}

			return segRolUsuarioDTO;
		} catch (Exception e) {
			throw e;
		}finally{
			EntityManagerHelper.closeEntityManager();
		}

	}

	@Override
	public List<SegRolUsuarioDTO> findUsuariosDTO(List<SegRolUsuario> losRolUsuarios) throws Exception {

		List<SegRolUsuarioDTO> rolUsuariosDTO = new ArrayList<SegRolUsuarioDTO>(losRolUsuarios.size());
		for (SegRolUsuario rolUsuario : losRolUsuarios) {

			SegRolUsuarioDTO rolUsuDTO = new SegRolUsuarioDTO();
			rolUsuDTO.setRluCodigo((rolUsuario.getRluCodigo().toString()));
			rolUsuDTO.setRluEstadoRegistro((rolUsuario.getRluEstadoRegistro()));
			rolUsuDTO.setRluEstadoRegistroNombre(rolUsuario.getRluEstadoRegistro().equals("1")? "Activo" : "Inactivo");
			rolUsuDTO.setRolCodigo_SegRol((rolUsuario.getSegRol()!=null)?rolUsuario.getSegRol().getRolNombre():null);
			rolUsuDTO.setUsuCodigo_SegUsuario((rolUsuario.getSegUsuarioBySegUsuarioUsuCodigo()!=null)?rolUsuario.getSegUsuarioBySegUsuarioUsuCodigo().getUsuLogin():null);
			rolUsuariosDTO.add(rolUsuDTO);
		}
		return rolUsuariosDTO;
	}

	@Override
	public List<SegRolUsuarioDTO> getRolesUsuariosBySystem(Long usuSession, String sistema) throws Exception {

		try {

			List<SegRolUsuarioDTO> segRolUsuarioDTO = new ArrayList<SegRolUsuarioDTO>();
			SegRolUsuario rolU = null;

			if (usuSession==null || usuSession.equals("")) {
				throw new Exception("El usuario debe estar logeado para realizar esta consulta");
			}

			if (sistema ==null || sistema.equals("")) {
				throw new Exception("El usuario debe ser administrador de algun sistema");
			}

			List<Object[]> objectsRolUsuario = JPADaoFactory.getInstance().getSegRolUsuarioDAO().getSegRolesUsuariosBySystem(usuSession, sistema);

			for (int i = 0; i < objectsRolUsuario.size(); i++) {
				Object[] obj = objectsRolUsuario.get(i);

				SegRolUsuarioDTO segRolUsuarioDTO2 = new SegRolUsuarioDTO();
				segRolUsuarioDTO2.setRluCodigo(obj[0].toString());

				Long rolUsusCodigo = Long.parseLong(obj[0].toString());
				rolU = BusinessDelegatorView.getSegRolUsuario(rolUsusCodigo);

				segRolUsuarioDTO2.setRluEstadoRegistro(rolU.getRluEstadoRegistro()!= null ? rolU.getRluEstadoRegistro().toString():null);
				segRolUsuarioDTO2.setRluEstadoRegistroNombre(rolU.getRluEstadoRegistro().equals("1")? "Activo" : "Inactivo");
				segRolUsuarioDTO2.setRolCodigo_SegRol((rolU.getSegRol().getRolCodigo() != null)? rolU.getSegRol().getRolNombre() : null);
				segRolUsuarioDTO2.setUsuCodigo_SegUsuario((rolU.getSegUsuarioBySegUsuarioUsuCodigo()!= null)? rolU.getSegUsuarioBySegUsuarioUsuCodigo().getUsuLogin() : null);

				segRolUsuarioDTO.add(segRolUsuarioDTO2);
			}

			return segRolUsuarioDTO;

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Object[] getRolesSeleccionadosPreviamente(String usuCodigo)
			throws Exception {

		try {

			if (usuCodigo==null || usuCodigo.equals("")) {
				throw new Exception("El usuario debe ser seleccionado para editarse");
			}

			List<Object[]> objectsRolUsuarioPreviamenteSeleccionados = JPADaoFactory.getInstance().getSegRolUsuarioDAO().getSegRolesPreviamenteSeleccionados(usuCodigo);
			Vector<Object> obj = new Vector<Object>(objectsRolUsuarioPreviamenteSeleccionados.size());

			for (int i = 0; i < objectsRolUsuarioPreviamenteSeleccionados.size(); i++) {
				obj.add(objectsRolUsuarioPreviamenteSeleccionados.get(i));
			}

			return obj.toArray();

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public SegRolUsuario getRolUsuariosCambiados(Long usuCodigo, String codigoRol)
			throws Exception {

		SegRolUsuario rolUsuario = null;

		try {
			List<Object[]> objects = JPADaoFactory.getInstance().getSegRolUsuarioDAO().getSegRolesData(usuCodigo, codigoRol);

			if (objects.size()>0) {
				Vector<Object> obj = new Vector<Object>(objects.size());

				for (int i = 0; i < objects.size(); i++) {
					obj.add(objects.get(i));
				}

				if (obj.get(0)!=null) {
					rolUsuario = getSegRolUsuario(Long.parseLong(obj.get(0).toString()));
				}
			}

		} catch (Exception e) {
			throw e;
		}
		return rolUsuario;
	}

	@Override
	public Object[] getRolesDeUsuario(Long usuCodigo) throws Exception {

		try {
			List<Object[]> objects = JPADaoFactory.getInstance().getSegRolUsuarioDAO().getSegRolesUsers(usuCodigo);

			Vector<Object> obj = new Vector<Object>(objects.size());

			for (int i = 0; i < objects.size(); i++) {
				obj.add(objects.get(i));
			}

			return obj.toArray();

		} catch (Exception e) {
			throw e;
		}
	}
}
