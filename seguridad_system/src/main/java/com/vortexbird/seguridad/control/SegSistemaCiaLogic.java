package com.vortexbird.seguridad.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vortexbird.seguridad.dataaccess.daoFactory.JPADaoFactory;
import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.dto.SegSistemaCiaDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.Utilities;

public class SegSistemaCiaLogic implements ISegSistemaCiaLogic {
	public List<SegSistemaCia> getSegSistemaCia() throws Exception {
		List<SegSistemaCia> list = new ArrayList<SegSistemaCia>();

		try {
			list = JPADaoFactory.getInstance().getSegSistemaCiaDAO().findAll(0);
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL +
					"SegSistemaCia");
		} finally {
			EntityManagerHelper.closeEntityManager();
		}

		return list;
	}

	public void saveSegSistemaCia(Long sicCodigo, String sicEstadoRegistro,
			Long ciaCodigo_SegCompania, Long sisCodigo_SegSistema,
			Long usuCodigo_SegUsuario) throws Exception {
		SegSistemaCia entity = null;

		try {

			if ((sicEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(sicEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"sicEstadoRegistro");
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

			ISegCompaniaLogic logicSegCompania1 = new SegCompaniaLogic();
			ISegSistemaLogic logicSegSistema2 = new SegSistemaLogic();
			ISegUsuarioLogic logicSegUsuario3 = new SegUsuarioLogic();
			SegCompania segCompaniaClass = logicSegCompania1.getSegCompania(ciaCodigo_SegCompania);
			SegSistema segSistemaClass = logicSegSistema2.getSegSistema(sisCodigo_SegSistema);
			SegUsuario segUsuarioClass = logicSegUsuario3.getSegUsuario(usuCodigo_SegUsuario);

			if (segCompaniaClass == null) {
				throw new ZMessManager().new ForeignException("segCompania");
			}

			if (segSistemaClass == null) {
				throw new ZMessManager().new ForeignException("segSistema");
			}

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			entity = new SegSistemaCia();
			entity.setSicEstadoRegistro(sicEstadoRegistro);
			entity.setSegCompania(segCompaniaClass);
			entity.setSegSistema(segSistemaClass);
			entity.setSegUsuario(segUsuarioClass);
			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegSistemaCiaDAO().save(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public void deleteSegSistemaCia(Long sicCodigo) throws Exception {
		SegSistemaCia entity = null;

		if (sicCodigo == null) {
			throw new ZMessManager().new EmptyFieldException("sicCodigo");
		}

		entity = getSegSistemaCia(sicCodigo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException("SegSistemaCia");
		}

		try {
			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegSistemaCiaDAO().delete(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public void updateSegSistemaCia(Long sicCodigo, String sicEstadoRegistro,
			Long ciaCodigo_SegCompania, Long sisCodigo_SegSistema,
			Long usuCodigo_SegUsuario) throws Exception {
		SegSistemaCia entity = null;

		try {
			if (sicCodigo == null) {
				throw new ZMessManager().new EmptyFieldException("sicCodigo");
			}

			if ((sicCodigo != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							sicCodigo, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"sicCodigo");
			}

			if ((sicEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(sicEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"sicEstadoRegistro");
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

			ISegCompaniaLogic logicSegCompania1 = new SegCompaniaLogic();

			ISegSistemaLogic logicSegSistema2 = new SegSistemaLogic();

			ISegUsuarioLogic logicSegUsuario3 = new SegUsuarioLogic();
			SegCompania segCompaniaClass = logicSegCompania1.getSegCompania(ciaCodigo_SegCompania);
			SegSistema segSistemaClass = logicSegSistema2.getSegSistema(sisCodigo_SegSistema);
			SegUsuario segUsuarioClass = logicSegUsuario3.getSegUsuario(usuCodigo_SegUsuario);

			if (segCompaniaClass == null) {
				throw new ZMessManager().new ForeignException("segCompania");
			}

			if (segSistemaClass == null) {
				throw new ZMessManager().new ForeignException("segSistema");
			}

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			entity = getSegSistemaCia(sicCodigo);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setSicCodigo(sicCodigo);
			entity.setSicEstadoRegistro(sicEstadoRegistro);
			entity.setSegCompania(segCompaniaClass);
			entity.setSegSistema(segSistemaClass);
			entity.setSegUsuario(segUsuarioClass);
			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegSistemaCiaDAO().update(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public SegSistemaCia getSegSistemaCia(Long sicCodigo)
			throws Exception {
		SegSistemaCia entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegSistemaCiaDAO()
					.findById(sicCodigo);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegSistemaCia");
		} finally {
		}

		return entity;
	}

	public List<SegSistemaCia> findPageSegSistemaCia(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception {
		List<SegSistemaCia> entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegSistemaCiaDAO()
					.findPageSegSistemaCia(sortColumnName,
							sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegSistemaCia");
		}

		return entity;
	}

	public Long findTotalNumberSegSistemaCia() throws Exception {
		Long entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegSistemaCiaDAO()
					.findTotalNumberSegSistemaCia();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegSistemaCia Count");
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
	public List<SegSistemaCia> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception {
		List<SegSistemaCia> list = new ArrayList<SegSistemaCia>();
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
			list = JPADaoFactory.getInstance().getSegSistemaCiaDAO()
					.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	public List<SegSistemaCiaDTO> findSegSistemaCiaDTO(List<SegSistemaCia> losSistemasCia) throws Exception {

		try {
			List<SegSistemaCiaDTO> losSistemasCiaDTO = new ArrayList<SegSistemaCiaDTO>();

			for (SegSistemaCia sistemaCia : losSistemasCia) {
				SegSistemaCiaDTO sistemaCiaDTO = new SegSistemaCiaDTO();
				sistemaCiaDTO.setSicCodigo((sistemaCia.getSicCodigo().toString()));

				if (sistemaCia.getSicEstadoRegistro().equals("0")==true) {
					sistemaCiaDTO.setSicEstadoRegistro("Inactivo");
				}else {
					sistemaCiaDTO.setSicEstadoRegistro("Activo");
				}	
				sistemaCiaDTO.setCiaCodigo_SegCompania(sistemaCia.getSegCompania().getCiaCodigo().toString());
				sistemaCiaDTO.setCiaNombre_SegCompania(sistemaCia.getSegCompania().getCiaNombre().toString());
				sistemaCiaDTO.setSisCodigo_SegSistema(sistemaCia.getSegSistema().getSisCodigo().toString());
				sistemaCiaDTO.setSisNombre_SegSistema(sistemaCia.getSegSistema().getSisNombre().toString());
				sistemaCiaDTO.setUsuCodigo_SegUsuario(sistemaCia.getSegUsuario()!=null?sistemaCia.getSegUsuario().getUsuLogin():null);
				losSistemasCiaDTO.add(sistemaCiaDTO);
			}
			return losSistemasCiaDTO;

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<SegSistemaCiaDTO> getDataSegSistemaCia() throws Exception {
		try {
			List<SegSistemaCia> segSistemaCia = JPADaoFactory.getInstance().getSegSistemaCiaDAO().findAll(0);

			List<SegSistemaCiaDTO> segSistemaCiaDTO = new ArrayList<SegSistemaCiaDTO>();

			for (SegSistemaCia segSistemaCiaTmp : segSistemaCia) {
				SegSistemaCiaDTO segSistemaCiaDTO2 = new SegSistemaCiaDTO();

				segSistemaCiaDTO2.setSicCodigo(segSistemaCiaTmp.getSicCodigo().toString());
				segSistemaCiaDTO2.setSicEstadoRegistro((segSistemaCiaTmp.getSicEstadoRegistro().equals("1"))? "Activo" : "Inactivo");
				segSistemaCiaDTO2.setCiaCodigo_SegCompania((segSistemaCiaTmp.getSegCompania() != null)? segSistemaCiaTmp.getSegCompania().getCiaCodigo().toString() : null);
				segSistemaCiaDTO2.setCiaNombre_SegCompania((segSistemaCiaTmp.getSegCompania()!=null)?segSistemaCiaTmp.getSegCompania().getCiaNombre():null);
				segSistemaCiaDTO2.setSisCodigo_SegSistema((segSistemaCiaTmp.getSegSistema() != null)? segSistemaCiaTmp.getSegSistema().getSisCodigo().toString() : null);
				segSistemaCiaDTO2.setSisNombre_SegSistema((segSistemaCiaTmp.getSegSistema() != null)? segSistemaCiaTmp.getSegSistema().getSisNombre() : null);
				segSistemaCiaDTO2.setUsuCodigo_SegUsuario((segSistemaCiaTmp.getSegUsuario() != null)? segSistemaCiaTmp.getSegUsuario().getUsuLogin() : null);
				segSistemaCiaDTO.add(segSistemaCiaDTO2);
			}

			return segSistemaCiaDTO;

		} catch (Exception e) {
			throw e;
		}finally{
			EntityManagerHelper.closeEntityManager();
		}
	}

	@Override
	public List<SegSistemaCiaDTO> getSistemasCiaQuery(String sistema, String compania) throws Exception {
		try {
			List<SegSistemaCiaDTO> sistemasCiaDTO = new ArrayList<SegSistemaCiaDTO>();
//			SegSistemaCia sistemaCia = null;
			
			if (sistema ==null || sistema.equals("")) {
				throw new Exception("El usuario debe ser administrador de algun sistema");
			}

			if (compania ==null || compania.equals("")) {
				throw new Exception("El usuario debe ser administrador de alguna compa�ia");
			}

			List<Object[]> sisCiasObject = JPADaoFactory.getInstance().getSegSistemaCiaDAO().getSegSistemasCiasQuery(sistema, compania);

			for (int i = 0; i < sisCiasObject.size(); i++) {
				Object[] obj = sisCiasObject.get(i);

				SegSistemaCiaDTO segSistemaCiaDTO2 = new SegSistemaCiaDTO();

//				segSistemaCiaDTO2.setSicCodigo(obj[0].toString());
//				
//				Long sicCodigo = Long.parseLong(obj[0].toString());
//				sistemaCia = BusinessDelegatorView.getSegSistemaCia(sicCodigo);
				segSistemaCiaDTO2.setSicCodigo("");
				segSistemaCiaDTO2.setSicEstadoRegistro(obj[0].equals("1")? "Activo" : "Inactivo");
				segSistemaCiaDTO2.setCiaCodigo_SegCompania(obj[1] != null ? obj[1].toString() : null);
				segSistemaCiaDTO2.setCiaNombre_SegCompania(obj[2]!=null ? obj[2].toString():null);
				segSistemaCiaDTO2.setSisCodigo_SegSistema(obj[3] != null ? obj[3].toString() : null);
				segSistemaCiaDTO2.setSisNombre_SegSistema(obj[4] != null ? obj[4].toString() : null);
				segSistemaCiaDTO2.setUsuCodigo_SegUsuario(null);
				sistemasCiaDTO.add(segSistemaCiaDTO2);
			}
			return sistemasCiaDTO;			

		} catch (Exception e) {
			throw e;
		}
	}
}