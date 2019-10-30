package com.vortexbird.seguridad.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vortexbird.seguridad.dataaccess.daoFactory.JPADaoFactory;
import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.GrupoDTO;
import com.vortexbird.seguridad.modelo.dto.SegGrupoOpcionDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.Utilities;


/**
 * interface SegGrupoOpcionLogic
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SegGrupoOpcionLogic implements ISegGrupoOpcionLogic {
	public List<SegGrupoOpcion> getSegGrupoOpcion() throws Exception {
		List<SegGrupoOpcion> list = new ArrayList<SegGrupoOpcion>();

		try {
			list = JPADaoFactory.getInstance().getSegGrupoOpcionDAO().findAll(0);
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL +
					"SegGrupoOpcion");
		} finally {
			EntityManagerHelper.closeEntityManager();
		}

		return list;
	}

	public void saveSegGrupoOpcion(Long gruCodigo, String gruDescripcion,
			String gruEstadoRegistro, String gruLlaveAcceso, String gruNombre,
			Long gruCodigo_SegGrupoOpcion, Long sisCodigo_SegSistema,
			Long usuCodigo_SegUsuario) throws Exception {
		SegGrupoOpcion entity = null;

		try {

			if ((gruDescripcion != null) &&
					(Utilities.checkWordAndCheckWithlength(gruDescripcion, 200) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruDescripcion");
			}

			if (gruEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"gruEstadoRegistro");
			}

			if ((gruEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(gruEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruEstadoRegistro");
			}

			if ((gruLlaveAcceso != null) &&
					(Utilities.checkWordAndCheckWithlength(gruLlaveAcceso, 200) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruLlaveAcceso");
			}

			if (gruNombre == null) {
				throw new ZMessManager().new EmptyFieldException("gruNombre");
			}

			if ((gruNombre != null) &&
					(Utilities.checkWordAndCheckWithlength(gruNombre, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruNombre");
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

			ISegGrupoOpcionLogic logicSegGrupoOpcion1 = new SegGrupoOpcionLogic();
			ISegSistemaLogic logicSegSistema2 = new SegSistemaLogic();
			ISegUsuarioLogic logicSegUsuario3 = new SegUsuarioLogic();

			SegGrupoOpcion segGrupoOpcionClass= null;

			if (gruCodigo_SegGrupoOpcion!=null) {
				segGrupoOpcionClass = logicSegGrupoOpcion1.getSegGrupoOpcion(gruCodigo_SegGrupoOpcion);
			}

			SegSistema segSistemaClass = logicSegSistema2.getSegSistema(sisCodigo_SegSistema);
			SegUsuario segUsuarioClass = logicSegUsuario3.getSegUsuario(usuCodigo_SegUsuario);

			//			if (segGrupoOpcionClass == null) {
			//				throw new ZMessManager().new ForeignException("segGrupoOpcion");
			//			}

			if (segSistemaClass == null) {
				throw new ZMessManager().new ForeignException("segSistema");
			}

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			entity = new SegGrupoOpcion();
			//entity.setGruCodigo(gruCodigo);
			entity.setGruDescripcion(gruDescripcion);
			entity.setGruEstadoRegistro(gruEstadoRegistro);
			entity.setGruLlaveAcceso(gruLlaveAcceso);
			entity.setGruNombre(gruNombre);
			entity.setSegGrupoOpcion(segGrupoOpcionClass);
			entity.setSegSistema(segSistemaClass);
			entity.setSegUsuario(segUsuarioClass);
			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegGrupoOpcionDAO().save(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public void deleteSegGrupoOpcion(Long gruCodigo) throws Exception {
		SegGrupoOpcion entity = null;

		if (gruCodigo == null) {
			throw new ZMessManager().new EmptyFieldException("gruCodigo");
		}

		List<SegGrupoOpcion> segGrupoOpcions = null;
		List<SegOpcion> segOpcions = null;
		List<SegPermiso> segPermisos = null;
		entity = getSegGrupoOpcion(gruCodigo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException("SegGrupoOpcion");
		}

		try {
			segGrupoOpcions = JPADaoFactory.getInstance().getSegGrupoOpcionDAO()
					.findByProperty("segGrupoOpcion.gruCodigo",
							gruCodigo, 0);

			if (Utilities.validationsList(segGrupoOpcions) == true) {
				throw new ZMessManager().new DeletingException(
						"segGrupoOpcions");
			}

			segOpcions = JPADaoFactory.getInstance().getSegOpcionDAO()
					.findByProperty("segGrupoOpcion.gruCodigo",
							gruCodigo, 0);

			if (Utilities.validationsList(segOpcions) == true) {
				throw new ZMessManager().new DeletingException("segOpcions");
			}

			segPermisos = JPADaoFactory.getInstance().getSegPermisoDAO()
					.findByProperty("segGrupoOpcion.gruCodigo",
							gruCodigo, 0);

			if (Utilities.validationsList(segPermisos) == true) {
				throw new ZMessManager().new DeletingException("segPermisos");
			}

			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegGrupoOpcionDAO().delete(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public void updateSegGrupoOpcion(Long gruCodigo, String gruDescripcion,
			String gruEstadoRegistro, String gruLlaveAcceso, String gruNombre,
			Long gruCodigo_SegGrupoOpcion, Long sisCodigo_SegSistema,
			Long usuCodigo_SegUsuario) throws Exception {
		SegGrupoOpcion entity = null;

		try {
			if (gruCodigo == null) {
				throw new ZMessManager().new EmptyFieldException("gruCodigo");
			}

			if ((gruCodigo != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							gruCodigo, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruCodigo");
			}

			if ((gruDescripcion != null) &&
					(Utilities.checkWordAndCheckWithlength(gruDescripcion, 200) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruDescripcion");
			}

			if (gruEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"gruEstadoRegistro");
			}

			if ((gruEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(gruEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruEstadoRegistro");
			}

			if ((gruLlaveAcceso != null) &&
					(Utilities.checkWordAndCheckWithlength(gruLlaveAcceso, 200) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruLlaveAcceso");
			}

			if (gruNombre == null) {
				throw new ZMessManager().new EmptyFieldException("gruNombre");
			}

			if ((gruNombre != null) &&
					(Utilities.checkWordAndCheckWithlength(gruNombre, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruNombre");
			}

			//			if (gruCodigo_SegGrupoOpcion == null) {
			//				throw new ZMessManager().new EmptyFieldException(
			//				"gruCodigo_SegGrupoOpcion");
			//			}

			//			if ((gruCodigo_SegGrupoOpcion != null) &&
			//					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
			//							gruCodigo_SegGrupoOpcion, 5, 0) == false)) {
			//				throw new ZMessManager().new NotValidFormatException(
			//				"gruCodigo_SegGrupoOpcion");
			//			}

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

			ISegGrupoOpcionLogic logicSegGrupoOpcion1 = new SegGrupoOpcionLogic();

			ISegSistemaLogic logicSegSistema2 = new SegSistemaLogic();

			ISegUsuarioLogic logicSegUsuario3 = new SegUsuarioLogic();


			SegGrupoOpcion segGrupoOpcionClass= null;

			if (gruCodigo_SegGrupoOpcion!=null) {
				segGrupoOpcionClass = logicSegGrupoOpcion1.getSegGrupoOpcion(gruCodigo_SegGrupoOpcion);
			}

			SegSistema segSistemaClass = logicSegSistema2.getSegSistema(sisCodigo_SegSistema);
			SegUsuario segUsuarioClass = logicSegUsuario3.getSegUsuario(usuCodigo_SegUsuario);

			//			if (segGrupoOpcionClass == null) {
			//				throw new ZMessManager().new ForeignException("segGrupoOpcion");
			//			}

			if (segSistemaClass == null) {
				throw new ZMessManager().new ForeignException("segSistema");
			}

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			entity = getSegGrupoOpcion(gruCodigo);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setGruCodigo(gruCodigo);
			entity.setGruDescripcion(gruDescripcion);
			entity.setGruEstadoRegistro(gruEstadoRegistro);
			entity.setGruLlaveAcceso(gruLlaveAcceso);
			entity.setGruNombre(gruNombre);
			entity.setSegGrupoOpcion(segGrupoOpcionClass);
			entity.setSegSistema(segSistemaClass);
			entity.setSegUsuario(segUsuarioClass);
			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegGrupoOpcionDAO().update(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public SegGrupoOpcion getSegGrupoOpcion(Long gruCodigo)
			throws Exception {
		SegGrupoOpcion entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegGrupoOpcionDAO()
					.findById(gruCodigo);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegGrupoOpcion");
		} finally {
		}

		return entity;
	}

	public List<SegGrupoOpcion> findPageSegGrupoOpcion(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception {
		List<SegGrupoOpcion> entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegGrupoOpcionDAO()
					.findPageSegGrupoOpcion(sortColumnName,
							sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegGrupoOpcion");
		}

		return entity;
	}

	public Long findTotalNumberSegGrupoOpcion() throws Exception {
		Long entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegGrupoOpcionDAO()
					.findTotalNumberSegGrupoOpcion();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException(
					"SegGrupoOpcion Count");
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
	public List<SegGrupoOpcion> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception {
		List<SegGrupoOpcion> list = new ArrayList<SegGrupoOpcion>();
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
			list = JPADaoFactory.getInstance().getSegGrupoOpcionDAO()
					.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	public List<SegGrupoOpcionDTO> findGrupoOpcionDTO(List<SegGrupoOpcion> losGrupoOpcion) throws Exception {
		try {
			List<SegGrupoOpcionDTO> grupoOpcionDTO = new ArrayList<SegGrupoOpcionDTO>();

			for (SegGrupoOpcion grupoOpcion : losGrupoOpcion) {
				SegGrupoOpcionDTO gpDTO = new SegGrupoOpcionDTO();
				gpDTO.setGruCodigo((grupoOpcion.getGruCodigo().toString()));
				gpDTO.setGruDescripcion((grupoOpcion.getGruDescripcion() != null) ? grupoOpcion.getGruDescripcion().toString(): null);

				if (grupoOpcion.getGruEstadoRegistro().equals("0")==true) {
					gpDTO.setGruEstadoRegistro("Inactivo");
				}else {
					gpDTO.setGruEstadoRegistro("Activo");
				}	
				gpDTO.setGruLlaveAcceso((grupoOpcion.getGruLlaveAcceso()!= null)? grupoOpcion.getGruLlaveAcceso().toString() : null);
				gpDTO.setGruNombre((grupoOpcion.getGruNombre() != null)? grupoOpcion.getGruNombre().toString() : null);
				SegGrupoOpcion entity = JPADaoFactory.getInstance().getSegGrupoOpcionDAO().findById(grupoOpcion.getGruCodigo());
				String gruCodigoPadreNombre="";
				if (entity.getSegGrupoOpcion()!=null) {
					gruCodigoPadreNombre=entity.getSegGrupoOpcion().getGruNombre();
				}else{
					gruCodigoPadreNombre = "";
				}
				gpDTO.setSegNombre_SegGrupoPadre(gruCodigoPadreNombre);
				gpDTO.setSisCodigo_SegSistema((grupoOpcion.getSegSistema().getSisCodigo() != null)? grupoOpcion.getSegSistema().getSisCodigo().toString() : null);
				gpDTO.setSisNombre_SegSistema((grupoOpcion.getSegSistema().getSisNombre()!=null)?grupoOpcion.getSegSistema().getSisNombre().toString():null);
				gpDTO.setSegGrupoOpcion(grupoOpcion);
				grupoOpcionDTO.add(gpDTO);
			}
			return grupoOpcionDTO;

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<SegGrupoOpcionDTO> getDataSegGrupoOpcion() throws Exception {
		try {
			List<SegGrupoOpcion> segGrupoOpcion = JPADaoFactory.getInstance().getSegGrupoOpcionDAO().findAll(0);

			List<SegGrupoOpcionDTO> segGrupoOpcionDTO = new ArrayList<SegGrupoOpcionDTO>();

			for (SegGrupoOpcion segGrupoOpcionTmp : segGrupoOpcion) {

				SegGrupoOpcionDTO segGrupoOpcionDTO2 = new SegGrupoOpcionDTO();
				segGrupoOpcionDTO2.setGruCodigo(segGrupoOpcionTmp.getGruCodigo().toString());
				segGrupoOpcionDTO2.setGruDescripcion((segGrupoOpcionTmp.getGruDescripcion() != null)? segGrupoOpcionTmp.getGruDescripcion() : null);
				segGrupoOpcionDTO2.setGruEstadoRegistro((segGrupoOpcionTmp.getGruEstadoRegistro().equals("0"))? "Inactivo" : "Activo");
				segGrupoOpcionDTO2.setGruLlaveAcceso((segGrupoOpcionTmp.getGruLlaveAcceso() != null)? segGrupoOpcionTmp.getGruLlaveAcceso() : null);
				segGrupoOpcionDTO2.setGruNombre((segGrupoOpcionTmp.getGruNombre() != null)? segGrupoOpcionTmp.getGruNombre() : null);
				segGrupoOpcionDTO2.setGruCodigo_SegGrupoOpcion((segGrupoOpcionTmp.getSegGrupoOpcion() != null)? segGrupoOpcionTmp.getSegGrupoOpcion().getGruCodigo().toString() : null);
				segGrupoOpcionDTO2.setSegNombre_SegGrupoPadre((segGrupoOpcionTmp.getSegGrupoOpcion() != null)? segGrupoOpcionTmp.getSegGrupoOpcion().getGruNombre() : null);
				segGrupoOpcionDTO2.setSisCodigo_SegSistema((segGrupoOpcionTmp.getSegSistema().getSisCodigo() != null)? segGrupoOpcionTmp.getSegSistema().getSisCodigo().toString() : null);
				segGrupoOpcionDTO2.setSisNombre_SegSistema((segGrupoOpcionTmp.getSegSistema()!=null)?segGrupoOpcionTmp.getSegSistema().getSisNombre():null);
				segGrupoOpcionDTO2.setUsuCodigo_SegUsuario((segGrupoOpcionTmp.getSegUsuario() != null)? segGrupoOpcionTmp.getSegUsuario().getUsuNombres() : null);

				segGrupoOpcionDTO.add(segGrupoOpcionDTO2);
			}

			return segGrupoOpcionDTO;
		} catch (Exception e) {
			throw e;
		}finally{
			EntityManagerHelper.closeEntityManager();
		}
	}

	@Override
	public List<SegGrupoOpcionDTO> getGrupoOpcionesPorSistema(Long usuSession, String sistema) throws Exception {

		try {
			List<SegGrupoOpcionDTO> segGrupoOpcionDTO = new ArrayList<SegGrupoOpcionDTO>();
			SegGrupoOpcion grupoOpPadre = null;


			if (usuSession==null || usuSession.equals("")) {
				throw new Exception("El usuario debe estar logeado para realizar esta consulta");
			}

			if (sistema ==null || sistema.equals("")) {
				throw new Exception("El usuario debe ser administrador de algun sistema");
			}
			
			List<Object[]> objectsGroup = JPADaoFactory.getInstance().getSegGrupoOpcionDAO().getSegGrupoOpcionesBySystem(usuSession, sistema);


			for (int i = 0; i < objectsGroup.size(); i++) {

				Object[] obj = objectsGroup.get(i);

				SegGrupoOpcionDTO segGrupoOpcionDTO2 = new SegGrupoOpcionDTO();
				segGrupoOpcionDTO2.setGruCodigo(obj[0].toString());
				segGrupoOpcionDTO2.setGruNombre(obj[1] != null ? obj[1].toString()  : null);
				segGrupoOpcionDTO2.setGruEstadoRegistro(obj[2].equals("0")? "Inactivo" : "Activo");
				segGrupoOpcionDTO2.setGruLlaveAcceso(obj[3] != null ? obj[3].toString() : null);
				segGrupoOpcionDTO2.setGruDescripcion(obj[4] != null ? obj[4].toString() : null);
				segGrupoOpcionDTO2.setGruCodigo_SegGrupoOpcion(obj[5] != null ? obj[5].toString() : null);

				Long codigoGrupoPadre = (obj[5]!=null?Long.parseLong(obj[5].toString()):null);

				if (codigoGrupoPadre!=null) {
					grupoOpPadre = BusinessDelegatorView.getSegGrupoOpcion(codigoGrupoPadre);
				}

				segGrupoOpcionDTO2.setSegNombre_SegGrupoPadre(grupoOpPadre != null ? grupoOpPadre.getGruNombre().toString() : null);
				segGrupoOpcionDTO2.setSisCodigo_SegSistema(obj[6] != null? obj[6].toString() : null);
				segGrupoOpcionDTO2.setSisNombre_SegSistema(obj[7]!=null?obj[7].toString():null);

				segGrupoOpcionDTO.add(segGrupoOpcionDTO2);

			}

			return segGrupoOpcionDTO;

		} catch (Exception e) {
			throw e;
		}finally{
			EntityManagerHelper.closeEntityManager();
		}
	}

	@Override
	public List<SegGrupoOpcion> getGrupoOpcionesPorSistemaModel(Long usuSession, String sistema) throws Exception {

		try {
			List<SegGrupoOpcion> grupoOpPadre = new ArrayList<SegGrupoOpcion>();

			if (usuSession==null || usuSession.equals("")) {
				throw new Exception("El usuario debe estar logeado para realizar esta consulta");
			}

			if (sistema ==null || sistema.equals("")) {
				throw new Exception("El usuario debe ser administrador de algun sistema");
			}
			
			List<Object[]> objectsGroup = JPADaoFactory.getInstance().getSegGrupoOpcionDAO().getSegGrupoOpcionesBySystem(usuSession, sistema);


			for (int i = 0; i < objectsGroup.size(); i++) {

				Object[] obj = objectsGroup.get(i);
				
				Long codigoGrupo = Long.parseLong(obj[0].toString());
				SegGrupoOpcion grupo = BusinessDelegatorView.getSegGrupoOpcion(codigoGrupo);
				grupoOpPadre.add(grupo);
				
			}
			
			return grupoOpPadre;
			
		} catch (Exception e) {
			throw e;
		}
	}

}
