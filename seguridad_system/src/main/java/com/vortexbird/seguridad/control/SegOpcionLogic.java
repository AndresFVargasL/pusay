package com.vortexbird.seguridad.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import com.vortexbird.seguridad.dataaccess.daoFactory.JPADaoFactory;
import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.GrupoDTO;
import com.vortexbird.seguridad.modelo.dto.OpcionDTO;
import com.vortexbird.seguridad.modelo.dto.SegOpcionDTO;
import com.vortexbird.seguridad.utilities.Utilities;

/**
 * interface SegOpcionLogic
 * 
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SegOpcionLogic implements ISegOpcionLogic {
	
	
	
	public List<SegOpcion> getSegOpcion() throws Exception {
		List<SegOpcion> list = new ArrayList<SegOpcion>();

		try {
			list = JPADaoFactory.getInstance().getSegOpcionDAO().findAll(0);
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL
					+ "SegOpcion");
		} finally {
			EntityManagerHelper.closeEntityManager();
		}

		return list;
	}

	public void saveSegOpcion(Long opcCodigo, String opcDescripcion,
			String opcEstadoRegistro, String opcLlaveAcceso, String opcNombre,
			Long gruCodigo_SegGrupoOpcion, Long usuCodigo_SegUsuario)
			throws Exception {
		SegOpcion entity = null;

		try {

			if ((opcDescripcion != null)
					&& (Utilities.checkWordAndCheckWithlength(opcDescripcion,
							200) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"opcDescripcion");
			}

			if (opcEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"opcEstadoRegistro");
			}

			if ((opcEstadoRegistro != null)
					&& (Utilities.checkWordAndCheckWithlength(
							opcEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"opcEstadoRegistro");
			}

			if ((opcLlaveAcceso != null)
					&& (Utilities.checkWordAndCheckWithlength(opcLlaveAcceso,
							200) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"opcLlaveAcceso");
			}

			if (opcNombre == null) {
				throw new ZMessManager().new EmptyFieldException("opcNombre");
			}

			if ((opcNombre != null)
					&& (Utilities.checkWordAndCheckWithlength(opcNombre, 100) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"opcNombre");
			}

			if (gruCodigo_SegGrupoOpcion == null) {
				throw new ZMessManager().new EmptyFieldException(
						"gruCodigo_SegGrupoOpcion");
			}

			if ((gruCodigo_SegGrupoOpcion != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ gruCodigo_SegGrupoOpcion, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruCodigo_SegGrupoOpcion");
			}

			if (usuCodigo_SegUsuario == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuCodigo_SegUsuario");
			}

			if ((usuCodigo_SegUsuario != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ usuCodigo_SegUsuario, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuCodigo_SegUsuario");
			}

			ISegGrupoOpcionLogic logicSegGrupoOpcion1 = new SegGrupoOpcionLogic();
			ISegUsuarioLogic logicSegUsuario2 = new SegUsuarioLogic();
			SegGrupoOpcion segGrupoOpcionClass = logicSegGrupoOpcion1
					.getSegGrupoOpcion(gruCodigo_SegGrupoOpcion);
			SegUsuario segUsuarioClass = logicSegUsuario2
					.getSegUsuario(usuCodigo_SegUsuario);

			if (segGrupoOpcionClass == null) {
				throw new ZMessManager().new ForeignException("segGrupoOpcion");
			}

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			entity = new SegOpcion();
			entity.setOpcDescripcion(opcDescripcion);
			entity.setOpcEstadoRegistro(opcEstadoRegistro);
			entity.setOpcLlaveAcceso(opcLlaveAcceso);
			entity.setOpcNombre(opcNombre);
			entity.setSegGrupoOpcion(segGrupoOpcionClass);
			entity.setSegUsuario(segUsuarioClass);
			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegOpcionDAO().save(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}
	
	//Metodo para traer los permisos dependiendo del rol
	public List<GrupoDTO> getOpciones(String login) throws Exception {

		List<SegUsuario> res;

		Hashtable<Long, SegOpcion> opciones = new Hashtable<Long, SegOpcion>();

		try {
			SegUsuarioLogic usuarioLogic = new SegUsuarioLogic();

			res = usuarioLogic.findByCriteria(new Object[] { "usuLogin", true,
					login.toUpperCase().trim(), "=", "usuEstadoRegistro", true,
					"1", "=" }, null, null);

			if (res.size() > 0) {

				SegUsuario u = res.get(0);// el login es unico!

				Object[] roles = u.getSegRolUsuariosForSegUsuarioUsuCodigo().toArray();

				for (Object rol : roles) {

					SegRolUsuario s = (SegRolUsuario) rol;

					Object[] permisos = s.getSegRol().getSegPermisos().toArray();

					// Se recorren los permisos del rol
					for (Object opcion : permisos) {

						SegPermiso per = (SegPermiso) opcion;

						if (per.getSegGrupoOpcion() != null) {

							for (SegOpcion opcionGrupo : per.getSegGrupoOpcion().getSegOpcions()) {
								opciones.put(opcionGrupo.getOpcCodigo(),opcionGrupo);
							}
						}

						if (per.getPerEstadoRegistro().equals("0")) {

							if (per.getSegGrupoOpcion() != null) {

								for (SegOpcion opcionGrupo : per.getSegGrupoOpcion().getSegOpcions()) {
									opciones.remove(opcionGrupo.getOpcCodigo());
								}
							}
						}
					}
				}

				List<GrupoDTO> arrayGrupos = new ArrayList<GrupoDTO>();

				while (opciones.size() > 0) {

					Object[] opcionesFinales = (Object[]) (opciones.values().toArray());

					SegOpcion opc = (SegOpcion) opcionesFinales[0];

					GrupoDTO grupo = new GrupoDTO();

					grupo.setGru_codigo(opc.getSegGrupoOpcion().getGruCodigo().longValue()+ "");

					if (opc.getSegGrupoOpcion().getSegGrupoOpcion() != null){
						grupo.setGru_codigo_padre(opc.getSegGrupoOpcion().getSegGrupoOpcion().getGruCodigo().longValue()+ "");
					}else{
						grupo.setGru_codigo_padre("");
					}

					if (opc.getSegGrupoOpcion().getGruDescripcion() != null){
						grupo.setGru_descripcion(opc.getSegGrupoOpcion().getGruDescripcion().toString());
					}else{
						grupo.setGru_descripcion("");
					}

					if (opc.getSegGrupoOpcion().getGruLlaveAcceso() != null){
						grupo.setGru_llave_acceso(opc.getSegGrupoOpcion().getGruLlaveAcceso().toString());
					}else{
						grupo.setGru_llave_acceso("");
					}

					grupo.setGru_nombre(opc.getSegGrupoOpcion().getGruNombre().toString());
					grupo.setSis_codigo(opc.getSegGrupoOpcion().getSegSistema().getSisCodigo().longValue()+ "");
					grupo.setGru_activo(opc.getSegGrupoOpcion().getGruEstadoRegistro().toString());
					grupo.setGru_icono(opc.getSegGrupoOpcion().getGruIcono());

					List<OpcionDTO> arrayOpciones = new ArrayList<OpcionDTO>();

					for (int j = 0; j < opcionesFinales.length; j++) {

						SegOpcion opc2 = (SegOpcion) opcionesFinales[j];

						if ((opc2.getSegGrupoOpcion().getGruCodigo().longValue() + "").equals(grupo.getGru_codigo())) {

							OpcionDTO opcionDTO = new OpcionDTO();

							opcionDTO.setOpc_activo(opc2.getOpcEstadoRegistro().toString());
							opcionDTO.setOpc_codigo(opc2.getOpcCodigo().longValue() + "");

							if (opc2.getOpcDescripcion() != null){
								opcionDTO.setOpc_descripcion(opc2.getOpcDescripcion().toString() + "");
							}else{
								opcionDTO.setOpc_descripcion("");
							}

							if (opc2.getOpcLlaveAcceso() != null){
								opcionDTO.setOpc_llave_acceso(opc2.getOpcLlaveAcceso().toString());
							}else{
								opcionDTO.setOpc_llave_acceso("");
							}

							opcionDTO.setOpc_nombre(opc2.getOpcNombre().toString());
							opcionDTO.setSeg_grupo_opcion_gru_codigo(opc2.getSegGrupoOpcion().getGruCodigo().longValue() + "");
							opciones.remove(opc2.getOpcCodigo());
							arrayOpciones.add(opcionDTO);
						}
					}
					/* - Se ordena la lista por el codigo de la opcion, puesto que los INSERTS se ejecutan ordenados en funcion de éste.
					   - Para que la funcion sort() sea exitosa, la clase OpcionDTO implementa Comparable<OpcionDTO> y se sobreescribe el metodo
					     compareTo() con el atributo que se requiere que se ordene.*/
					Collections.sort(arrayOpciones);
					/*Se setean las opciones al grupo, ordenadas.*/
					grupo.setOpciones(arrayOpciones);
					arrayGrupos.add(grupo);
				}

				if (arrayGrupos.size() > 0){
					return arrayGrupos;
				}else{
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public void deleteSegOpcion(Long opcCodigo) throws Exception {
		SegOpcion entity = null;

		if (opcCodigo == null) {
			throw new ZMessManager().new EmptyFieldException("opcCodigo");
		}

		List<SegPermiso> segPermisos = null;
		entity = getSegOpcion(opcCodigo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException("SegOpcion");
		}

		try {
			segPermisos = JPADaoFactory.getInstance().getSegPermisoDAO()
					.findByProperty("segOpcion.opcCodigo", opcCodigo, 0);

			if (Utilities.validationsList(segPermisos) == true) {
				throw new ZMessManager().new DeletingException("segPermisos");
			}

			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegOpcionDAO().delete(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public void updateSegOpcion(Long opcCodigo, String opcDescripcion,
			String opcEstadoRegistro, String opcLlaveAcceso, String opcNombre,
			Long gruCodigo_SegGrupoOpcion, Long usuCodigo_SegUsuario)
			throws Exception {
		SegOpcion entity = null;

		try {
			if (opcCodigo == null) {
				throw new ZMessManager().new EmptyFieldException("opcCodigo");
			}

			if ((opcCodigo != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ opcCodigo, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"opcCodigo");
			}

			if ((opcDescripcion != null)
					&& (Utilities.checkWordAndCheckWithlength(opcDescripcion,
							200) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"opcDescripcion");
			}

			if (opcEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"opcEstadoRegistro");
			}

			if ((opcEstadoRegistro != null)
					&& (Utilities.checkWordAndCheckWithlength(
							opcEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"opcEstadoRegistro");
			}

			if ((opcLlaveAcceso != null)
					&& (Utilities.checkWordAndCheckWithlength(opcLlaveAcceso,
							200) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"opcLlaveAcceso");
			}

			if (opcNombre == null) {
				throw new ZMessManager().new EmptyFieldException("opcNombre");
			}

			if ((opcNombre != null)
					&& (Utilities.checkWordAndCheckWithlength(opcNombre, 100) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"opcNombre");
			}

			if (gruCodigo_SegGrupoOpcion == null) {
				throw new ZMessManager().new EmptyFieldException(
						"gruCodigo_SegGrupoOpcion");
			}

			if ((gruCodigo_SegGrupoOpcion != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ gruCodigo_SegGrupoOpcion, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruCodigo_SegGrupoOpcion");
			}

			if (usuCodigo_SegUsuario == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuCodigo_SegUsuario");
			}

			if ((usuCodigo_SegUsuario != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ usuCodigo_SegUsuario, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuCodigo_SegUsuario");
			}

			ISegGrupoOpcionLogic logicSegGrupoOpcion1 = new SegGrupoOpcionLogic();

			ISegUsuarioLogic logicSegUsuario2 = new SegUsuarioLogic();
			SegGrupoOpcion segGrupoOpcionClass = logicSegGrupoOpcion1
					.getSegGrupoOpcion(gruCodigo_SegGrupoOpcion);
			SegUsuario segUsuarioClass = logicSegUsuario2
					.getSegUsuario(usuCodigo_SegUsuario);

			if (segGrupoOpcionClass == null) {
				throw new ZMessManager().new ForeignException("segGrupoOpcion");
			}

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			entity = getSegOpcion(opcCodigo);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setOpcCodigo(opcCodigo);
			entity.setOpcDescripcion(opcDescripcion);
			entity.setOpcEstadoRegistro(opcEstadoRegistro);
			entity.setOpcLlaveAcceso(opcLlaveAcceso);
			entity.setOpcNombre(opcNombre);
			entity.setSegGrupoOpcion(segGrupoOpcionClass);
			entity.setSegUsuario(segUsuarioClass);
			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegOpcionDAO().update(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public SegOpcion getSegOpcion(Long opcCodigo) throws Exception {
		SegOpcion entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegOpcionDAO()
					.findById(opcCodigo);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegOpcion");
		} finally {
		}

		return entity;
	}

	public List<SegOpcion> findPageSegOpcion(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		List<SegOpcion> entity = null;

		try {
			entity = JPADaoFactory
					.getInstance()
					.getSegOpcionDAO()
					.findPageSegOpcion(sortColumnName, sortAscending, startRow,
							maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegOpcion");
		}

		return entity;
	}

	public Long findTotalNumberSegOpcion() throws Exception {
		Long entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegOpcionDAO()
					.findTotalNumberSegOpcion();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegOpcion Count");
		} finally {
		}

		return entity;
	}

	/**
	 *
	 * @param varibles
	 *            este arreglo debera tener:
	 *
	 *            [0] = String variable = (String) varibles[i]; representa como
	 *            se llama la variable en el pojo
	 *
	 *            [1] = Boolean booVariable = (Boolean) varibles[i + 1];
	 *            representa si el valor necesita o no ''(comillas simples)usado
	 *            para campos de tipo string
	 *
	 *            [2] = Object value = varibles[i + 2]; representa el valor que
	 *            se va a buscar en la BD
	 *
	 *            [3] = String comparator = (String) varibles[i + 3]; representa
	 *            que tipo de busqueda voy a hacer.., ejemplo: where
	 *            nombre=william o where nombre<>william, en este campo iria el
	 *            tipo de comparador que quiero si es = o <>
	 *
	 *            Se itera de 4 en 4..., entonces 4 registros del arreglo
	 *            representan 1 busqueda en un campo, si se ponen mas pues el
	 *            continuara buscando en lo que se le ingresen en los otros 4
	 *
	 *
	 * @param variablesBetween
	 *
	 *            la diferencia son estas dos posiciones
	 *
	 *            [0] = String variable = (String) varibles[j]; la variable ne
	 *            la BD que va a ser buscada en un rango
	 *
	 *            [1] = Object value = varibles[j + 1]; valor 1 para buscar en
	 *            un rango
	 *
	 *            [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en
	 *            un rango ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria
	 *            value2
	 *
	 *            [3] = String comparator1 = (String) varibles[j + 3];
	 *            comparador 1 ejemplo: a comparator1 1 and a < 5
	 *
	 *            [4] = String comparator2 = (String) varibles[j + 4];
	 *            comparador 2 ejemplo: a comparador1>1 and a comparador2<5 (el
	 *            original: a > 1 and a < 5) *
	 * @param variablesBetweenDates
	 *            (en este caso solo para mysql) [0] = String variable =
	 *            (String) varibles[k]; el nombre de la variable que hace
	 *            referencia a una fecha
	 *
	 *            [1] = Object object1 = varibles[k + 2]; fecha 1 a
	 *            comparar(deben ser dates)
	 *
	 *            [2] = Object object2 = varibles[k + 3]; fecha 2 a
	 *            comparar(deben ser dates)
	 *
	 *            esto hace un between entre las dos fechas.
	 *
	 * @return lista con los objetos que se necesiten
	 * @throws Exception
	 */
	public List<SegOpcion> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		List<SegOpcion> list = new ArrayList<SegOpcion>();
		String where = new String();
		String tempWhere = new String();

		if (variables != null) {
			for (int i = 0; i < variables.length; i++) {
				if ((variables[i] != null) && (variables[i + 1] != null)
						&& (variables[i + 2] != null)
						&& (variables[i + 3] != null)) {
					String variable = (String) variables[i];
					Boolean booVariable = (Boolean) variables[i + 1];
					Object value = variables[i + 2];
					String comparator = (String) variables[i + 3];

					if (booVariable.booleanValue()) {
						tempWhere = (tempWhere.length() == 0) ? ("(model."
								+ variable + " " + comparator + " \'" + value + "\' )")
								: (tempWhere + " AND (model." + variable + " "
										+ comparator + " \'" + value + "\' )");
					} else {
						tempWhere = (tempWhere.length() == 0) ? ("(model."
								+ variable + " " + comparator + " " + value + " )")
								: (tempWhere + " AND (model." + variable + " "
										+ comparator + " " + value + " )");
					}
				}

				i = i + 3;
			}
		}

		if (variablesBetween != null) {
			for (int j = 0; j < variablesBetween.length; j++) {
				if ((variablesBetween[j] != null)
						&& (variablesBetween[j + 1] != null)
						&& (variablesBetween[j + 2] != null)
						&& (variablesBetween[j + 3] != null)
						&& (variablesBetween[j + 4] != null)) {
					String variable = (String) variablesBetween[j];
					Object value = variablesBetween[j + 1];
					Object value2 = variablesBetween[j + 2];
					String comparator1 = (String) variablesBetween[j + 3];
					String comparator2 = (String) variablesBetween[j + 4];
					tempWhere = (tempWhere.length() == 0) ? ("(" + value + " "
							+ comparator1 + " " + variable + " and " + variable
							+ " " + comparator2 + " " + value2 + " )")
							: (tempWhere + " AND (" + value + " " + comparator1
									+ " " + variable + " and " + variable + " "
									+ comparator2 + " " + value2 + " )");
				}

				j = j + 4;
			}
		}

		if (variablesBetweenDates != null) {
			for (int k = 0; k < variablesBetweenDates.length; k++) {
				if ((variablesBetweenDates[k] != null)
						&& (variablesBetweenDates[k + 1] != null)
						&& (variablesBetweenDates[k + 2] != null)) {
					String variable = (String) variablesBetweenDates[k];
					Object object1 = variablesBetweenDates[k + 1];
					Object object2 = variablesBetweenDates[k + 2];
					String value = null;
					String value2 = null;

					try {
						Date date1 = (Date) object1;
						Date date2 = (Date) object2;
						value = Utilities
								.formatDateWithoutTimeInAStringForBetweenWhere(date1);
						value2 = Utilities
								.formatDateWithoutTimeInAStringForBetweenWhere(date2);
					} catch (Exception e) {
						list = null;
						throw e;
					}

					tempWhere = (tempWhere.length() == 0) ? ("(model."
							+ variable + " between \'" + value + "\' and \'"
							+ value2 + "\')") : (tempWhere + " AND (model."
							+ variable + " between \'" + value + "\' and \'"
							+ value2 + "\')");
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
			list = JPADaoFactory.getInstance().getSegOpcionDAO()
					.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	public List<SegOpcionDTO> findSegOpcionDTO(List<SegOpcion> losOpcion)
			throws Exception {

		try {
			List<SegOpcionDTO> opcionesDTO = new ArrayList<SegOpcionDTO>();

			for (SegOpcion opcion : losOpcion) {
				SegOpcionDTO optDTO = new SegOpcionDTO();
				optDTO.setOpcCodigo((opcion.getOpcCodigo().toString()));
				optDTO.setOpcDescripcion((opcion.getOpcDescripcion() != null) ? opcion
						.getOpcDescripcion().toString() : null);

				if (opcion.getOpcEstadoRegistro().equals("0") == true) {
					optDTO.setOpcEstadoRegistro("Inactivo");
				} else {
					optDTO.setOpcEstadoRegistro("Activo");
				}
				optDTO.setOpcLlaveAcceso((opcion.getOpcLlaveAcceso() != null) ? opcion
						.getOpcLlaveAcceso().toString() : null);
				optDTO.setOpcNombre((opcion.getOpcNombre() != null) ? opcion
						.getOpcNombre().toString() : null);
				optDTO.setGruCodigo_SegGrupoOpcion((opcion.getSegGrupoOpcion()
						.getGruCodigo() != null) ? opcion.getSegGrupoOpcion()
						.getGruCodigo().toString() : null);
				optDTO.setGruNombre_SegGrupoOpcion((opcion.getSegGrupoOpcion()
						.getGruNombre() != null) ? opcion.getSegGrupoOpcion()
						.getGruNombre().toString() : null);
				opcionesDTO.add(optDTO);
			}
			return opcionesDTO;

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<SegOpcionDTO> getDataSegOpcion() throws Exception {
		try {
			List<SegOpcion> segOpcion = JPADaoFactory.getInstance()
					.getSegOpcionDAO().findAll(0);

			List<SegOpcionDTO> segOpcionDTO = new ArrayList<SegOpcionDTO>();

			for (SegOpcion segOpcionTmp : segOpcion) {
				SegOpcionDTO segOpcionDTO2 = new SegOpcionDTO();

				segOpcionDTO2.setOpcCodigo(segOpcionTmp.getOpcCodigo()
						.toString());
				segOpcionDTO2.setOpcDescripcion((segOpcionTmp
						.getOpcDescripcion() != null) ? segOpcionTmp
						.getOpcDescripcion() : null);
				segOpcionDTO2.setOpcEstadoRegistro((segOpcionTmp
						.getOpcEstadoRegistro().equals("1")) ? "Activo"
						: "Inactivo");
				segOpcionDTO2.setOpcLlaveAcceso((segOpcionTmp
						.getOpcLlaveAcceso() != null) ? segOpcionTmp
						.getOpcLlaveAcceso() : null);
				segOpcionDTO2
						.setOpcNombre((segOpcionTmp.getOpcNombre() != null) ? segOpcionTmp
								.getOpcNombre() : null);
				segOpcionDTO2
						.setGruCodigo_SegGrupoOpcion((segOpcionTmp
								.getSegGrupoOpcion().getGruCodigo().toString() != null) ? segOpcionTmp
								.getSegGrupoOpcion().getGruCodigo().toString()
								: null);
				segOpcionDTO2
						.setGruNombre_SegGrupoOpcion((segOpcionTmp
								.getSegGrupoOpcion().getGruNombre() != null) ? segOpcionTmp
								.getSegGrupoOpcion().getGruNombre().toString()
								: null);
				segOpcionDTO2
						.setUsuCodigo_SegUsuario((segOpcionTmp.getSegUsuario()
								.getUsuCodigo().toString() != null) ? segOpcionTmp
								.getSegUsuario().getUsuCodigo().toString()
								: null);

				segOpcionDTO.add(segOpcionDTO2);
			}

			return segOpcionDTO;
		} catch (Exception e) {
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	@Override
	public List<SegOpcionDTO> getOpcionesPorSistema(Long usuSession,
			String sistema) throws Exception {

		try {

			List<SegOpcionDTO> segOpcionDTO = new ArrayList<SegOpcionDTO>();

			if (usuSession == null || usuSession.equals("")) {
				throw new Exception(
						"El usuario debe estar logeado para realizar esta consulta");
			}

			if (sistema == null || sistema.equals("")) {
				throw new Exception(
						"El usuario ser administrador de algun sistema");
			}

			List<Object[]> opcionModel = JPADaoFactory.getInstance()
					.getSegOpcionDAO()
					.findOpcionesBySystem(usuSession, sistema);

			for (int i = 0; i < opcionModel.size(); i++) {

				SegOpcionDTO segOpcionDTO2 = new SegOpcionDTO();

				Object[] obj = opcionModel.get(i);
				segOpcionDTO2.setOpcCodigo(obj[0].toString());
				segOpcionDTO2.setOpcNombre(obj[1] != null ? obj[1].toString()
						: null);
				segOpcionDTO2
						.setOpcEstadoRegistro(obj[2].equals("1") ? "Activo"
								: "Inactivo");
				segOpcionDTO2.setOpcLlaveAcceso(obj[3] != null ? obj[3]
						.toString() : null);
				segOpcionDTO2.setOpcDescripcion(obj[4] != null ? obj[4]
						.toString() : null);
				segOpcionDTO2
						.setGruCodigo_SegGrupoOpcion(obj[5] != null ? obj[5]
								.toString() : null);
				segOpcionDTO2
						.setGruNombre_SegGrupoOpcion(obj[6] != null ? obj[6]
								.toString() : null);
				segOpcionDTO2.setUsuCodigo_SegUsuario(obj[7] != null ? obj[7]
						.toString() : null);

				segOpcionDTO.add(segOpcionDTO2);

			}
			return segOpcionDTO;

		} catch (Exception e) {
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}
}
