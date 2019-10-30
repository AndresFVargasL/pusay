package com.vortexbird.seguridad.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.vortexbird.seguridad.dataaccess.daoFactory.JPADaoFactory;
import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.control.ws.LoginBean;
import com.vortexbird.seguridad.modelo.dto.SegCompaniaDTO;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.Utilities;


/**
 * interface SegCompaniaLogic
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SegCompaniaLogic implements ISegCompaniaLogic {

	private Logger logger = Logger.getLogger(LoginBean.class);

	public List<SegCompania> getSegCompania() throws Exception {
		List<SegCompania> list = new ArrayList<SegCompania>();

		try {
			list = JPADaoFactory.getInstance().getSegCompaniaDAO().findAll(0);
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL +
					"SegCompania");
		} finally {
			EntityManagerHelper.closeEntityManager();
		}

		return list;
	}

	public void saveSegCompania(Long ciaCodigo, String ciaCodigoInterno,
			String ciaEstadoRegistro, String ciaNombre, Long usuCodigo_SegUsuario)
					throws Exception {
		SegCompania entity = null;

		try {

			if (ciaCodigoInterno == null) {
				throw new ZMessManager().new EmptyFieldException(
						"ciaCodigoInterno");
			}

			if ((ciaCodigoInterno != null) &&
					(Utilities.checkWordAndCheckWithlength(ciaCodigoInterno, 15) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"ciaCodigoInterno");
			}

			if (ciaEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"ciaEstadoRegistro");
			}

			if ((ciaEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(ciaEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"ciaEstadoRegistro");
			}

			if (ciaNombre == null) {
				throw new ZMessManager().new EmptyFieldException("ciaNombre");
			}

			if ((ciaNombre != null) &&
					(Utilities.checkWordAndCheckWithlength(ciaNombre, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"ciaNombre");
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

			ISegUsuarioLogic logicSegUsuario1 = new SegUsuarioLogic();
			SegUsuario segUsuarioClass = logicSegUsuario1.getSegUsuario(usuCodigo_SegUsuario);

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			entity = new SegCompania();
			entity.setCiaCodigoInterno(ciaCodigoInterno);
			entity.setCiaEstadoRegistro(ciaEstadoRegistro);
			entity.setCiaNombre(ciaNombre);
			entity.setSegUsuario(segUsuarioClass);
			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegCompaniaDAO().save(entity);
			EntityManagerHelper.commit();
			logger.info("El usuario: " + segUsuarioClass.getUsuNombres() + " ha guardado la siguiente compa�ia: " + ciaNombre);
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public void deleteSegCompania(Long ciaCodigo) throws Exception {
		SegCompania entity = null;

		if (ciaCodigo == null) {
			throw new ZMessManager().new EmptyFieldException("ciaCodigo");
		}

		List<SegPermiso> segPermisos = null;
		List<SegSistemaCia> segSistemaCias = null;
		List<SegSucursal> segSucursals = null;
		entity = getSegCompania(ciaCodigo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException("SegCompania");
		}

		try {
			segPermisos = JPADaoFactory.getInstance().getSegPermisoDAO()
					.findByProperty("segCompania.ciaCodigo",
							ciaCodigo, 0);

			if (Utilities.validationsList(segPermisos) == true) {
				throw new ZMessManager().new DeletingException("segPermisos");
			}

			segSistemaCias = JPADaoFactory.getInstance().getSegSistemaCiaDAO()
					.findByProperty("segCompania.ciaCodigo",
							ciaCodigo, 0);

			if (Utilities.validationsList(segSistemaCias) == true) {
				throw new ZMessManager().new DeletingException("segSistemaCias");
			}

			segSucursals = JPADaoFactory.getInstance().getSegSucursalDAO()
					.findByProperty("segCompania.ciaCodigo",
							ciaCodigo, 0);

			if (Utilities.validationsList(segSucursals) == true) {
				throw new ZMessManager().new DeletingException("segSucursals");
			}

			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegCompaniaDAO().delete(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public void updateSegCompania(Long ciaCodigo, String ciaCodigoInterno,
			String ciaEstadoRegistro, String ciaNombre, Long usuCodigo_SegUsuario)
					throws Exception {
		SegCompania entity = null;

		try {
			if (ciaCodigo == null) {
				throw new ZMessManager().new EmptyFieldException("ciaCodigo");
			}

			if ((ciaCodigo != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							ciaCodigo, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"ciaCodigo");
			}

			if (ciaCodigoInterno == null) {
				throw new ZMessManager().new EmptyFieldException(
						"ciaCodigoInterno");
			}

			if ((ciaCodigoInterno != null) &&
					(Utilities.checkWordAndCheckWithlength(ciaCodigoInterno, 15) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"ciaCodigoInterno");
			}

			if (ciaEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"ciaEstadoRegistro");
			}

			if ((ciaEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(ciaEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"ciaEstadoRegistro");
			}

			if (ciaNombre == null) {
				throw new ZMessManager().new EmptyFieldException("ciaNombre");
			}

			if ((ciaNombre != null) &&
					(Utilities.checkWordAndCheckWithlength(ciaNombre, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"ciaNombre");
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

			ISegUsuarioLogic logicSegUsuario1 = new SegUsuarioLogic();
			SegUsuario segUsuarioClass = logicSegUsuario1.getSegUsuario(usuCodigo_SegUsuario);

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			entity = getSegCompania(ciaCodigo);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setCiaCodigo(ciaCodigo);
			entity.setCiaCodigoInterno(ciaCodigoInterno);
			entity.setCiaEstadoRegistro(ciaEstadoRegistro);
			entity.setCiaNombre(ciaNombre);
			entity.setSegUsuario(segUsuarioClass);
			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegCompaniaDAO().update(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public SegCompania getSegCompania(Long ciaCodigo) throws Exception {
		SegCompania entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegCompaniaDAO()
					.findById(ciaCodigo);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegCompania");
		} finally {
		}

		return entity;
	}

	public List<SegCompania> findPageSegCompania(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception {
		List<SegCompania> entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegCompaniaDAO()
					.findPageSegCompania(sortColumnName,
							sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegCompania");
		}

		return entity;
	}

	public Long findTotalNumberSegCompania() throws Exception {
		Long entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegCompaniaDAO()
					.findTotalNumberSegCompania();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegCompania Count");
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
	public List<SegCompania> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception {
		List<SegCompania> list = new ArrayList<SegCompania>();
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
			list = JPADaoFactory.getInstance().getSegCompaniaDAO()
					.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}
	@Override
	public List<SegCompaniaDTO> findCompaniasDTO(List<SegCompania> listSegCompania) throws Exception {

		try {
			List<SegCompaniaDTO> lasCompaniasDTO = new ArrayList<SegCompaniaDTO>();

			for (SegCompania segCompania : listSegCompania) {
				SegCompaniaDTO companiaDTO = new SegCompaniaDTO();
				companiaDTO.setCiaCodigo((segCompania.getCiaCodigo().toString()));
				companiaDTO.setCiaCodigoInterno(segCompania.getCiaCodigoInterno());

				if (segCompania.getCiaEstadoRegistro().equals("0")==true) {
					companiaDTO.setCiaEstadoRegistro("Inactivo");
				}else {
					companiaDTO.setCiaEstadoRegistro("Activo");
				}	
				companiaDTO.setCiaNombre(segCompania.getCiaNombre());
				lasCompaniasDTO.add(companiaDTO);
			}
			return lasCompaniasDTO;

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<SegCompaniaDTO> getSegCompaniaDTO() throws Exception {
		try {
			List<SegCompania> segCompania = JPADaoFactory.getInstance() .getSegCompaniaDAO().findAll(0);
			List<SegCompaniaDTO> segCompaniaDTO = new ArrayList<SegCompaniaDTO>();

			for (SegCompania segCompaniaTmp : segCompania) {
				SegCompaniaDTO segCompaniaDTO2 = new SegCompaniaDTO();

				segCompaniaDTO2.setCiaCodigo(segCompaniaTmp.getCiaCodigo().toString());
				segCompaniaDTO2.setCiaCodigoInterno((segCompaniaTmp.getCiaCodigoInterno() != null)? segCompaniaTmp.getCiaCodigoInterno() : null);
				segCompaniaDTO2.setCiaEstadoRegistro((segCompaniaTmp.getCiaEstadoRegistro().equals("1")? "Activo" : "Inactivo"));
				segCompaniaDTO2.setCiaNombre((segCompaniaTmp.getCiaNombre() != null)? segCompaniaTmp.getCiaNombre() : null);
				segCompaniaDTO2.setUsuCodigo_SegUsuario((segCompaniaTmp.getSegUsuario().getUsuCodigo() != null)? segCompaniaTmp.getSegUsuario().getUsuLogin() : null);
				segCompaniaDTO.add(segCompaniaDTO2);
			}
			return segCompaniaDTO;

		} catch (Exception e) {
			throw e;
		}finally{
			EntityManagerHelper.closeEntityManager();
		}
	}

	@Override
	public List<SegCompania> getCompaniaDeUsuarioPorRol(Long usuSession, long sistema) throws Exception {

		try {
			List<SegCompania> companias = new ArrayList<SegCompania>();

			if (usuSession==null || usuSession.equals("")) {
				throw new Exception("El usuario debe estar logeado para realizar esta consulta");
			}
			
			List<Object[]> ciaUsuariosObj= JPADaoFactory.getInstance().getSegCompaniaDAO().findCiaByUserRol(usuSession, sistema);
			
			for (int i = 0; i < ciaUsuariosObj.size(); i++) {
				
				Object[] obj = ciaUsuariosObj.get(i);
				Long codigoCia = Long.parseLong(obj[0].toString());
				SegCompania comp = BusinessDelegatorView.getSegCompania(codigoCia);
				companias.add(comp);
			}
			return companias;
			
		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public List<SegCompaniaDTO> getCompaniasBySystemDTO(Long usuSession, long codigoSistema) throws Exception {
		try {
			List<SegCompaniaDTO> companiasDTO = new ArrayList<SegCompaniaDTO>();
			SegCompania companiaConsultada = null;
			
			if (usuSession==null || usuSession.equals("")) {
				throw new Exception("El usuario debe estar logeado para realizar esta consulta");
			}
			
			if (usuSession==null || usuSession.equals("")) {
				throw new Exception("El usuario debe haber elegido un sistema para realizar esta consulta");
			}
			
			List<Object[]> ciaUsuariosBySistemObj= JPADaoFactory.getInstance().getSegCompaniaDAO().findCiaByUserRol(usuSession, codigoSistema);
			
			for (int i = 0; i < ciaUsuariosBySistemObj.size(); i++) {
				
				Object[] obj = ciaUsuariosBySistemObj.get(i);
				SegCompaniaDTO segCompaniaDTO2 = new SegCompaniaDTO();
				companiaConsultada = BusinessDelegatorView.getSegCompania(Long.parseLong(obj[0].toString()));
				segCompaniaDTO2.setCiaCodigo(companiaConsultada.getCiaCodigo().toString());
				segCompaniaDTO2.setCiaCodigoInterno((companiaConsultada.getCiaCodigoInterno() != null)? companiaConsultada.getCiaCodigoInterno() : null);
				segCompaniaDTO2.setCiaEstadoRegistro((companiaConsultada.getCiaEstadoRegistro().equals("1")? "Activo" : "Inactivo"));
				segCompaniaDTO2.setCiaNombre((companiaConsultada.getCiaNombre() != null)? companiaConsultada.getCiaNombre() : null);
				companiasDTO.add(segCompaniaDTO2);
			}
			return companiasDTO;
			
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<SegCompania> getCompaniasPorRol(Long codigoRol)throws Exception {
		
		List<SegCompania> companias = new ArrayList<SegCompania>();
		ISegRolLogic rolLogic = new SegRolLogic();
		SegRol rol = null;
		
		try {
			rol = rolLogic.getSegRol(codigoRol);
			Long sistema = rol.getSegSistema().getSisCodigo();
			
			List<Object[]> ciaUsuariosObj= JPADaoFactory.getInstance().getSegCompaniaDAO().findCiaRol(sistema);
			
			for (int i = 0; i < ciaUsuariosObj.size(); i++) {
				
				Object[] obj = ciaUsuariosObj.get(i);
				Long codigoCia = Long.parseLong(obj[0].toString());
				SegCompania comp = BusinessDelegatorView.getSegCompania(codigoCia);
				companias.add(comp);
			}
			return companias;
			
		} catch (Exception e) {
			throw e;
		}
	}
}
