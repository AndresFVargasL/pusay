package com.vortexbird.seguridad.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.vortexbird.seguridad.dataaccess.daoFactory.JPADaoFactory;
import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.exceptions.*;
import com.vortexbird.seguridad.modelo.*;
import com.vortexbird.seguridad.modelo.control.ws.LoginBean;
import com.vortexbird.seguridad.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.seguridad.utilities.Utilities;

/**
 * interface SegPermisoLogic
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SegPermisoLogic implements ISegPermisoLogic {

	private Logger logger = Logger.getLogger(LoginBean.class);

	public List<SegPermiso> getSegPermiso() throws Exception {
		List<SegPermiso> list = new ArrayList<SegPermiso>();

		try {
			list = JPADaoFactory.getInstance().getSegPermisoDAO().findAll(0);
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL +
					"SegPermiso");
		} finally {
		}

		return list;
	}

	public void saveSegPermiso(String perEstadoRegistro,
			Long sicCodigo_SegSistemaCia, Long gruCodigo_SegGrupoOpcion,
			Long opcCodigo_SegOpcion, Long rolCodigo_SegRol,
			Long sucCodigo_SegSucursal, Long usuCodigo_SegUsuario,
			Long usuCodigo_SegUsuario0) throws Exception {
		SegPermiso entity = null;

		try {

			if (perEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"perEstadoRegistro");
			}

			ISegSistemaCiaLogic logicSegSistemaCia1 = new SegSistemaCiaLogic();
			ISegGrupoOpcionLogic logicSegGrupoOpcion2 = new SegGrupoOpcionLogic();
			ISegOpcionLogic logicSegOpcion3 = new SegOpcionLogic();
			ISegRolLogic logicSegRol4 = new SegRolLogic();
			ISegSucursalLogic logicSegSucursal5 = new SegSucursalLogic();
			ISegUsuarioLogic logicSegUsuario6 = new SegUsuarioLogic();
			ISegUsuarioLogic logicSegUsuario7 = new SegUsuarioLogic();

			SegSistemaCia segSistemaCiaClass = null;
			SegGrupoOpcion segGrupoOpcionClass = null;
			SegOpcion segOpcionClass = null;
			SegRol segRolClass = null;
			SegSucursal segSucursalClass = null;
			SegUsuario segUsuarioByModUsuCodigoClass = null;
			SegUsuario segUsuarioByUsuCodigoClass = null;

			if(sicCodigo_SegSistemaCia!=null)
				segSistemaCiaClass = logicSegSistemaCia1.getSegSistemaCia(sicCodigo_SegSistemaCia);

			if(gruCodigo_SegGrupoOpcion != null)
				segGrupoOpcionClass = logicSegGrupoOpcion2.getSegGrupoOpcion(gruCodigo_SegGrupoOpcion);

			if(opcCodigo_SegOpcion!=null)
				segOpcionClass = logicSegOpcion3.getSegOpcion(opcCodigo_SegOpcion);

			if(rolCodigo_SegRol!=null)
				segRolClass = logicSegRol4.getSegRol(rolCodigo_SegRol);

			if(sucCodigo_SegSucursal != null)
				segSucursalClass = logicSegSucursal5.getSegSucursal(sucCodigo_SegSucursal);

			if(usuCodigo_SegUsuario != null)
				segUsuarioByUsuCodigoClass = logicSegUsuario6.getSegUsuario(usuCodigo_SegUsuario);

			if(usuCodigo_SegUsuario0 != null)
				segUsuarioByModUsuCodigoClass = logicSegUsuario7.getSegUsuario(usuCodigo_SegUsuario0);


			entity = new SegPermiso();
			entity.setPerEstadoRegistro(perEstadoRegistro);
			entity.setSegSistemaCia(segSistemaCiaClass);//(segCompaniaClass);
			entity.setSegGrupoOpcion(segGrupoOpcionClass);
			entity.setSegOpcion(segOpcionClass);
			entity.setSegRol(segRolClass);
			entity.setSegSucursal(segSucursalClass);
			entity.setSegUsuarioByModUsuCodigo(segUsuarioByModUsuCodigoClass);
			entity.setSegUsuarioByUsuCodigo(segUsuarioByUsuCodigoClass);
			//			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegPermisoDAO().save(entity);
			//			EntityManagerHelper.commit();
		} catch (Exception e) {
			//			EntityManagerHelper.rollback();
			throw e;
		} finally {
			//			EntityManagerHelper.closeEntityManager();
		}
	}

	public void deleteSegPermiso(Long perCodigo) throws Exception {
		SegPermiso entity = null;

		if (perCodigo == null) {
			throw new ZMessManager().new EmptyFieldException("perCodigo");
		}

		entity = getSegPermiso(perCodigo);

		if (entity == null) {
			throw new ZMessManager().new EmptyFieldException("SegPermiso");
		}

		try {
			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegPermisoDAO().delete(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public void updateSegPermiso(Long perCodigo, String perEstadoRegistro,
			Long sicCodigo_SegSistemaCia, Long gruCodigo_SegGrupoOpcion,
			Long opcCodigo_SegOpcion, Long rolCodigo_SegRol,
			Long sucCodigo_SegSucursal, Long usuCodigo_SegUsuario,
			Long usuCodigo_SegUsuario0) throws Exception {
		SegPermiso entity = null;

		try {
			if (perCodigo == null) {
				throw new ZMessManager().new EmptyFieldException("perCodigo");
			}

			if ((perCodigo != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							perCodigo, 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"perCodigo");
			}

			if (perEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"perEstadoRegistro");
			}

			if ((perEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(perEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"perEstadoRegistro");
			}

			ISegSistemaCiaLogic logicSegSistemaCia1 = new SegSistemaCiaLogic();

			ISegGrupoOpcionLogic logicSegGrupoOpcion2 = new SegGrupoOpcionLogic();

			ISegOpcionLogic logicSegOpcion3 = new SegOpcionLogic();

			ISegRolLogic logicSegRol4 = new SegRolLogic();

			ISegSucursalLogic logicSegSucursal5 = new SegSucursalLogic();

			ISegUsuarioLogic logicSegUsuario6 = new SegUsuarioLogic();

			ISegUsuarioLogic logicSegUsuario7 = new SegUsuarioLogic();

			SegSistemaCia segSistemaCiaClass = null;
			SegGrupoOpcion segGrupoOpcionClass = null;
			SegOpcion segOpcionClass = null;
			SegRol segRolClass = null;
			SegSucursal segSucursalClass = null;
			SegUsuario segUsuarioByModUsuCodigoClass = null;
			SegUsuario segUsuarioByUsuCodigoClass = null;

			if(sicCodigo_SegSistemaCia!=null)
				segSistemaCiaClass = logicSegSistemaCia1.getSegSistemaCia(sicCodigo_SegSistemaCia);

			if(gruCodigo_SegGrupoOpcion != null)
				segGrupoOpcionClass = logicSegGrupoOpcion2.getSegGrupoOpcion(gruCodigo_SegGrupoOpcion);

			if(opcCodigo_SegOpcion!=null)
				segOpcionClass = logicSegOpcion3.getSegOpcion(opcCodigo_SegOpcion);

			if(rolCodigo_SegRol!=null)
				segRolClass = logicSegRol4.getSegRol(rolCodigo_SegRol);

			if(sucCodigo_SegSucursal != null)
				segSucursalClass = logicSegSucursal5.getSegSucursal(sucCodigo_SegSucursal);

			if(usuCodigo_SegUsuario != null)
				segUsuarioByUsuCodigoClass = logicSegUsuario6.getSegUsuario(usuCodigo_SegUsuario);

			if(usuCodigo_SegUsuario0 != null)
				segUsuarioByModUsuCodigoClass = logicSegUsuario7.getSegUsuario(usuCodigo_SegUsuario0);

			entity = getSegPermiso(perCodigo);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setPerCodigo(perCodigo);
			entity.setPerEstadoRegistro(perEstadoRegistro);
			entity.setSegSistemaCia(segSistemaCiaClass);
			entity.setSegGrupoOpcion(segGrupoOpcionClass);
			entity.setSegOpcion(segOpcionClass);
			entity.setSegRol(segRolClass);
			entity.setSegSucursal(segSucursalClass);
			entity.setSegUsuarioByModUsuCodigo(segUsuarioByModUsuCodigoClass);
			entity.setSegUsuarioByUsuCodigo(segUsuarioByUsuCodigoClass);
			EntityManagerHelper.beginTransaction();
			JPADaoFactory.getInstance().getSegPermisoDAO().update(entity);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public SegPermiso getSegPermiso(Long perCodigo) throws Exception {
		SegPermiso entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegPermisoDAO()
					.findById(perCodigo);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegPermiso");
		} finally {
		}

		return entity;
	}

	public List<SegPermiso> findPageSegPermiso(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception {
		List<SegPermiso> entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegPermisoDAO()
					.findPageSegPermiso(sortColumnName,
							sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegPermiso");
		}

		return entity;
	}

	public Long findTotalNumberSegPermiso() throws Exception {
		Long entity = null;

		try {
			entity = JPADaoFactory.getInstance().getSegPermisoDAO()
					.findTotalNumberSegPermiso();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegPermiso Count");
		} finally {
		}

		return entity;
	}


	public List<SegPermiso> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception {
		List<SegPermiso> list = new ArrayList<SegPermiso>();
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
			list = JPADaoFactory.getInstance().getSegPermisoDAO()
					.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	public List<SegPermiso> findPermisosAdmin(Long usuCodigo) throws Exception {

		List<SegPermiso> permisos = new ArrayList<SegPermiso>();

		try {

			List<Object[]> objectsUsers = JPADaoFactory.getInstance().getSegPermisoDAO().getPermisosAdmin(usuCodigo);

			for (int i = 0; i < objectsUsers.size(); i++) {
				Object[] obj = objectsUsers.get(i);

				Long perCodigo = Long.parseLong(obj[0].toString());
				SegPermiso per = BusinessDelegatorView.getSegPermiso(perCodigo);
				permisos.add(per);
			}
			return permisos;			

		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public List<SegOpcion> getOpcionesPorRolInPermisos(Long rolCodigo, Long codigoCompania)throws Exception {

		//		List<SegOpcion> opcionesSeleccionadasPreviamente = new ArrayList<SegOpcion>();
		//		List<SegPermiso> permisosPorRol = new ArrayList<SegPermiso>();
		//		SegOpcionLogic opcionLogic = new SegOpcionLogic();
		//		SegOpcion opcion =null;
		//
		//		try{
		//
		//			if (rolCodigo==null) {
		//				throw new Exception("El codigo del rol es un valor necesario para la consulta");
		//			}
		//
		//			String whereCondition = "segRol.rolCodigo ="+rolCodigo+ " and segCompania.ciaCodigo ="+codigoCompania;//+" and perEstadoRegistro = '1'";
		//			permisosPorRol = JPADaoFactory.getInstance().getSegPermisoDAO().findByCriteria(whereCondition);
		//
		//			if (permisosPorRol.size()>0) {
		//				for (int i = 0; i < permisosPorRol.size(); i++) {
		//					Long codOpcion = permisosPorRol.get(i).getSegOpcion().getOpcCodigo();
		//					opcion = opcionLogic.getSegOpcion(codOpcion);
		//					opcionesSeleccionadasPreviamente.add(opcion);
		//				}
		//			}
		//			return opcionesSeleccionadasPreviamente;
		//
		//		} catch (Exception e) {
		//			throw e;
		//		}
		return null;
	}


	//Pintar Arbol
	@Override
	public Set<SegOpcion> findEstructuraArbol(TreeNode rootNoda)throws Exception {

		Set<SegOpcion> opcionesSeleccionadasSet = new HashSet<SegOpcion>();

		try {
			Enumeration<TreeNode> grupoOpciones = Collections.enumeration(rootNoda.getChildren());
			while (grupoOpciones.hasMoreElements()) {
				TreeNode object = grupoOpciones.nextElement();

				Enumeration<TreeNode> opciones = Collections.enumeration(object.getChildren());
				while (opciones.hasMoreElements()) {

					//hojas
					TreeNode object2 = opciones.nextElement();

					if (object2.isSelected()==true) {

						List<SegOpcion> listOpciones=BusinessDelegatorView.findByCriteriaInSegOpcion(new Object[]{"opcNombre",true,object2.getData().toString(),"="},null, null);
						SegOpcion opcionPrueba = BusinessDelegatorView.getSegOpcion(listOpciones.get(0).getOpcCodigo());
						System.out.println(opcionPrueba.getOpcNombre());

						opcionesSeleccionadasSet.add(opcionPrueba);
					}
				}
			}
			return opcionesSeleccionadasSet;
		} 
		catch (Exception e) {
			throw e;
		}
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public void saveOptionsForRolInPermiso(Long rolCodigo, List<SegOpcion> listOpciones, Long codigoCompania, Long codigoSistema, String codigoUsuario) throws Exception {


		ISegUsuarioLogic usuarioLogic = new SegUsuarioLogic();
		ISegSistemaCiaLogic logicSegSistemaCia = new SegSistemaCiaLogic();
		ISegOpcionLogic logicSegOpcion3 = new SegOpcionLogic();
		ISegRolLogic logicSegRol4 = new SegRolLogic();
		SegSistemaCia segSistemaCiaClass = null;
		SegRol segRolClass = null;
		SegOpcion segOpcionClass = null;
		SegUsuario usuarioClass= null;
		SegPermiso entity = null;
		String estadoRegistro = "1";

		try {

			List<SegSistemaCia> siscias = logicSegSistemaCia.findByCriteria(new Object[]{
					"segCompania",false,codigoCompania.toString(),"=",
					"segSistema",false,codigoSistema.toString(),"="
			},null, null);

			Long sisCiaUnico = siscias.get(0).getSicCodigo();

			if(sisCiaUnico!=null)
				segSistemaCiaClass = logicSegSistemaCia.getSegSistemaCia(sisCiaUnico);

			if(rolCodigo!=null)
				segRolClass = logicSegRol4.getSegRol(rolCodigo);


			EntityManagerHelper.beginTransaction();

			//Si escogio usuarios es porque es un administrador ---> Se debe guardar en permisos por usuario
			if (!codigoUsuario.equals("-1")) {
				usuarioClass= usuarioLogic.getSegUsuario(Long.parseLong(codigoUsuario));

				for (int i = 0; i < listOpciones.size(); i++) {

					if(listOpciones.get(i).getOpcCodigo()!=null){
						segOpcionClass = logicSegOpcion3.getSegOpcion(listOpciones.get(i).getOpcCodigo());

						entity = new SegPermiso();
						entity.setPerEstadoRegistro(estadoRegistro);
						entity.setSegSistemaCia(segSistemaCiaClass);
						entity.setSegGrupoOpcion(null);
						entity.setSegOpcion(segOpcionClass);
						entity.setSegRol(null);
						entity.setSegSucursal(null);
						entity.setSegUsuarioByModUsuCodigo(null);
						entity.setSegUsuarioByUsuCodigo(usuarioClass);
						JPADaoFactory.getInstance().getSegPermisoDAO().save(entity);
					}
				}
				//Es un usuario normal (EJ: Ejecutivo de ventas) --->Se debe guardar en permisos por su Rol
			}else {
				for (int i = 0; i < listOpciones.size(); i++) {

					if(listOpciones.get(i).getOpcCodigo()!=null){
						segOpcionClass = logicSegOpcion3.getSegOpcion(listOpciones.get(i).getOpcCodigo());

						entity = new SegPermiso();
						entity.setPerEstadoRegistro(estadoRegistro);
						entity.setSegSistemaCia(segSistemaCiaClass);
						entity.setSegGrupoOpcion(null);
						entity.setSegOpcion(segOpcionClass);
						entity.setSegRol(segRolClass);
						entity.setSegSucursal(null);
						entity.setSegUsuarioByModUsuCodigo(null);
						entity.setSegUsuarioByUsuCodigo(null);
						JPADaoFactory.getInstance().getSegPermisoDAO().save(entity);
					}
				}
			}

			EntityManagerHelper.commit();

		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		}finally{
			EntityManagerHelper.closeEntityManager();
		}
	}

	@Override
	public void updateOptionsForRolInPermiso(Long rolCodigo,List<SegOpcion> listOpciones, Long codigoCompania, Long codigoSistema, String codigoUsuario) throws Exception {

		SegPermiso entity = null;
		String estadoRegistro = "1";
		ISegUsuarioLogic usuarioLogic = new SegUsuarioLogic();
		SegUsuario usuarioClass= null;
		HashMap<String, String> referenciaOpciones = null;
		List<String> opcionesString = new ArrayList<String>();

		try {
			
			List<SegOpcion> opcionesSave = consultarOpcionesPorRolUnionUserInPermisos(rolCodigo,codigoCompania,codigoUsuario);

			for (int i = 0; i < opcionesSave.size(); i++) {
				String opcCod = opcionesSave.get(i).getOpcCodigo().toString();
				opcionesString.add(opcCod);
			}

			referenciaOpciones=new HashMap<String, String>();

			for (String opcionCod : opcionesString) {
				referenciaOpciones.put(opcionCod, opcionCod);
			}

			ISegSistemaCiaLogic logicSegSistemaCia = new SegSistemaCiaLogic();
			ISegOpcionLogic logicSegOpcion3 = new SegOpcionLogic();
			ISegRolLogic logicSegRol4 = new SegRolLogic();

			SegSistemaCia segSistemaCiaClass = null;
			SegRol segRolClass = null;
			SegOpcion segOpcionClass = null;

			List<SegSistemaCia> siscias = logicSegSistemaCia.findByCriteria(new Object[]{
					"segCompania",false,codigoCompania.toString(),"=",
					"segSistema",false,codigoSistema.toString(),"="
			},null, null);

			Long sisCiaUnico = siscias.get(0).getSicCodigo();

			if(sisCiaUnico!=null)
				segSistemaCiaClass = logicSegSistemaCia.getSegSistemaCia(sisCiaUnico);

			if(rolCodigo!=null)
				segRolClass = logicSegRol4.getSegRol(rolCodigo);

			EntityManagerHelper.beginTransaction();

			if (!codigoUsuario.equals("-1")) {
				usuarioClass= usuarioLogic.getSegUsuario(Long.parseLong(codigoUsuario));

				for (int i = 0; i < listOpciones.size(); i++) {
					SegOpcion opcioSeleccionada = listOpciones.get(i);	

					if (referenciaOpciones.containsKey(opcioSeleccionada.getOpcCodigo().toString())==false) {
						segOpcionClass = logicSegOpcion3.getSegOpcion(listOpciones.get(i).getOpcCodigo());

						entity = new SegPermiso();
						entity.setPerEstadoRegistro(estadoRegistro);
						entity.setSegSistemaCia(segSistemaCiaClass);
						entity.setSegGrupoOpcion(null);
						entity.setSegOpcion(segOpcionClass);
						entity.setSegRol(null);
						entity.setSegSucursal(null);
						entity.setSegUsuarioByModUsuCodigo(null);
						entity.setSegUsuarioByUsuCodigo(usuarioClass);
						JPADaoFactory.getInstance().getSegPermisoDAO().update(entity);
					}
				}

			}else {

				for (int i = 0; i < listOpciones.size(); i++) {
					SegOpcion opcioSeleccionada = listOpciones.get(i);	

					System.out.println(opcioSeleccionada.getOpcCodigo().toString());

					if (referenciaOpciones.containsKey(opcioSeleccionada.getOpcCodigo().toString())==false){

						segOpcionClass = logicSegOpcion3.getSegOpcion(listOpciones.get(i).getOpcCodigo());

						entity = new SegPermiso();
						entity.setPerEstadoRegistro(estadoRegistro);
						entity.setSegSistemaCia(segSistemaCiaClass);
						entity.setSegGrupoOpcion(null);
						entity.setSegOpcion(segOpcionClass);
						entity.setSegRol(segRolClass);
						entity.setSegSucursal(null);
						entity.setSegUsuarioByModUsuCodigo(null);
						entity.setSegUsuarioByUsuCodigo(null);
						JPADaoFactory.getInstance().getSegPermisoDAO().update(entity);
					}
				}
			}

			for (int i = 0; i < opcionesSave.size(); i++) {

				List<SegPermiso> permisosCambiarEstado = new ArrayList<SegPermiso>();
				SegOpcion saveOpc = opcionesSave.get(i);
				if (listOpciones.contains(saveOpc)==false) {

					Long codigoOpcion = saveOpc.getOpcCodigo();

					permisosCambiarEstado = getPermisosCriteria(rolCodigo, codigoCompania, codigoOpcion, codigoUsuario);

					if (permisosCambiarEstado.size()>1) {
						throw new Exception("El mismo registro existe mas de una vez.");
					}

					SegPermiso perm = permisosCambiarEstado.get(0);
					System.out.println(perm.getPerCodigo());
					perm.setPerEstadoRegistro("0");
					JPADaoFactory.getInstance().getSegPermisoDAO().update(perm);
				}
				else {
					Long codigoOpc = saveOpc.getOpcCodigo();

					permisosCambiarEstado = getPermisosCriteria(rolCodigo, codigoCompania, codigoOpc, codigoUsuario);

					if (permisosCambiarEstado.size()>1) {
						throw new Exception("El mismo registro existe mas de una vez.");
					}
					SegPermiso perm = permisosCambiarEstado.get(0);
					System.out.println(perm.getPerCodigo());
					perm.setPerEstadoRegistro("1");
					JPADaoFactory.getInstance().getSegPermisoDAO().update(perm);
				}
			}
			EntityManagerHelper.commit();

		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw e;
		}finally{
			EntityManagerHelper.closeEntityManager();
		}
	}


	//SE LLAMA DESD EL MODIFICAR PERMISOS (FALTA REVISAR)
	public List<SegOpcion> getOpcionesPorRolInPermisosSisCia(Long rolCodigo, Long codigoCompania)throws Exception {

		List<SegOpcion> opcionesSeleccionadasPreviamente = new ArrayList<SegOpcion>();
		ISegSistemaCiaLogic logicSegSistemaCia = new SegSistemaCiaLogic();
		ISegRolLogic rolLogic = new SegRolLogic();
		ISegCompaniaLogic companiaLogic = new SegCompaniaLogic();
		List<SegPermiso> permisosPorRol = new ArrayList<SegPermiso>();
		SegOpcionLogic opcionLogic = new SegOpcionLogic();
		SegOpcion opcion =null;
		SegCompania comp = null;

		try{

			if (rolCodigo==null) {
				throw new Exception("El codigo del rol es un valor necesario para la consulta");
			}

			SegRol rolConSistema = rolLogic.getSegRol(rolCodigo);
			Long codigoSistema = rolConSistema.getSegSistema().getSisCodigo();

			List<SegSistemaCia> siscias = logicSegSistemaCia.findByCriteria(new Object[]{
					"segCompania",false,codigoCompania.toString(),"=",
					"segSistema",false,codigoSistema.toString(),"="
			},null, null);

			if (siscias.size()==0) {
				comp = companiaLogic.getSegCompania(codigoCompania);
				throw new Exception("La compa�ia: " + comp.getCiaNombre() + " no esta parametrizada para el sistema: " + rolConSistema.getSegSistema().getSisNombre());
			}

			String whereCondition = "segRol.rolCodigo ="+rolCodigo+ " and segSistemaCia.sicCodigo ="+siscias.get(0).getSicCodigo();
			permisosPorRol = JPADaoFactory.getInstance().getSegPermisoDAO().findByCriteria(whereCondition);

			if (permisosPorRol.size()>0) {
				for (int i = 0; i < permisosPorRol.size(); i++) {
					Long codOpcion = permisosPorRol.get(i).getSegOpcion().getOpcCodigo();
					opcion = opcionLogic.getSegOpcion(codOpcion);
					opcionesSeleccionadasPreviamente.add(opcion);
				}
			}
			return opcionesSeleccionadasPreviamente;

		} catch (Exception e) {
			throw e;
		}
	}

	//Consulta los permisos existentes por Usuario y sis_cia (DataTable de usuario)
	@Override
	public List<SegPermiso> findPermisosByUsuAndSisCia(Long usuCodigo,String compania, String sistema) throws Exception {

		List<SegPermiso> permisos = new ArrayList<SegPermiso>();
		ISegSistemaCiaLogic segSistemaCiaLogic = new SegSistemaCiaLogic();

		try {

			List<SegSistemaCia> siscias = segSistemaCiaLogic.findByCriteria(new Object[]{"segCompania",false,compania,"=","segSistema",false,sistema,"="},null, null);

			if (siscias!=null && siscias.size()>0) {

				Long sisCiaUnico = siscias.get(0).getSicCodigo();

				List<Object[]> objectsUsers = JPADaoFactory.getInstance().getSegPermisoDAO().getPermisosByUserAndSisCia(usuCodigo,sisCiaUnico);

				for (int i = 0; i < objectsUsers.size(); i++) {
					Object[] obj = objectsUsers.get(i);

					Long perCodigo = Long.parseLong(obj[0].toString());
					SegPermiso per = BusinessDelegatorView.getSegPermiso(perCodigo);
					permisos.add(per);
				}
			}

			return permisos;			

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<SegPermiso> getPermisosCriteria(Long rolCodigo,Long codigoCompania, Long codigoOpcion, String codigoUsuario) throws Exception {

		List<SegPermiso> permisosCambiarEstado = new ArrayList<SegPermiso>();
		ISegRolLogic rolLogic = new SegRolLogic();

		try {

			if (rolCodigo==null) {
				throw new Exception("El codigo del rol es un valor necesario para la consulta");
			}

			SegRol rolConSistema = rolLogic.getSegRol(rolCodigo);
			Long codigoSistema = rolConSistema.getSegSistema().getSisCodigo();

			ISegSistemaCiaLogic logicSegSistemaCia = new SegSistemaCiaLogic();

			List<SegSistemaCia> siscias = logicSegSistemaCia.findByCriteria(new Object[]{
					"segCompania",false,codigoCompania.toString(),"=",
					"segSistema",false,codigoSistema.toString(),"="
			},null, null);

			Long sisCiaUnico = siscias.get(0).getSicCodigo();

			permisosCambiarEstado = JPADaoFactory.getInstance().getSegPermisoDAO().findPermisosRolUnionUserOption(rolCodigo, sisCiaUnico, codigoUsuario, codigoOpcion);

			return permisosCambiarEstado;
		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public List<SegOpcion> consultarOpcionesPorRolUnionUserInPermisos(Long codigoRol, Long codigoCompania, String codigoUsuario) throws Exception {

		List<SegOpcion> opcionesSeleccionadasPreviamente = new ArrayList<SegOpcion>();
		ISegSistemaCiaLogic logicSegSistemaCia = new SegSistemaCiaLogic();
		ISegRolLogic rolLogic = new SegRolLogic();
		ISegCompaniaLogic companiaLogic = new SegCompaniaLogic();
		List<SegPermiso> permisosPorRol = new ArrayList<SegPermiso>();
		SegOpcionLogic opcionLogic = new SegOpcionLogic();
		SegOpcion opcion =null;
		SegCompania comp = null;

		try{

			if (codigoRol==null) {
				throw new Exception("El codigo del rol es un valor necesario para la consulta");
			}

			SegRol rolConSistema = rolLogic.getSegRol(codigoRol);
			Long codigoSistema = rolConSistema.getSegSistema().getSisCodigo();

			List<SegSistemaCia> siscias = logicSegSistemaCia.findByCriteria(new Object[]{
					"segCompania",false,codigoCompania.toString(),"=",
					"segSistema",false,codigoSistema.toString(),"="
			},null, null);

			if (siscias.size()==0) {
				comp = companiaLogic.getSegCompania(codigoCompania);
				throw new Exception("La compa�ia: " + comp.getCiaNombre() + " no esta parametrizada para el sistema: " + rolConSistema.getSegSistema().getSisNombre());
			}

			permisosPorRol = JPADaoFactory.getInstance().getSegPermisoDAO().findPermisosRolUnionUser(codigoRol, siscias.get(0).getSicCodigo(), codigoUsuario);

			if (permisosPorRol.size()>0) {
				for (int i = 0; i < permisosPorRol.size(); i++) {
					Long codOpcion = permisosPorRol.get(i).getSegOpcion()!=null?permisosPorRol.get(i).getSegOpcion().getOpcCodigo():null;
					if (codOpcion!=null) {
						opcion = opcionLogic.getSegOpcion(codOpcion);
						opcionesSeleccionadasPreviamente.add(opcion);
					}
				}
			}
			return opcionesSeleccionadasPreviamente;

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<SegOpcion> consultarOpcionesPorUsuarioEnPermisos(Long rolCodigo, Long codigoCompania, Long usuSession)throws Exception {

		List<SegOpcion> opcionesSeleccionadasPreviamente = new ArrayList<SegOpcion>();
		ISegSistemaCiaLogic logicSegSistemaCia = new SegSistemaCiaLogic();
		ISegRolLogic rolLogic = new SegRolLogic();
		ISegCompaniaLogic companiaLogic = new SegCompaniaLogic();
		List<SegPermiso> permisosPorRol = new ArrayList<SegPermiso>();
		SegOpcionLogic opcionLogic = new SegOpcionLogic();
		SegOpcion opcion =null;
		SegCompania comp = null;

		try{

			if (rolCodigo==null) {
				throw new Exception("El codigo del rol es un valor necesario para la consulta");
			}

			SegRol rolConSistema = rolLogic.getSegRol(rolCodigo);
			Long codigoSistema = rolConSistema.getSegSistema().getSisCodigo();

			List<SegSistemaCia> siscias = logicSegSistemaCia.findByCriteria(new Object[]{
					"segCompania",false,codigoCompania.toString(),"=",
					"segSistema",false,codigoSistema.toString(),"="
			},null, null);

			if (siscias.size()==0) {
				comp = companiaLogic.getSegCompania(codigoCompania);
				throw new Exception("La compa�ia: " + comp.getCiaNombre() + " no esta parametrizada para el sistema: " + rolConSistema.getSegSistema().getSisNombre());
			}

			//String whereCondition = "segUsuarioByUsuCodigo.usuCodigo ="+usuSession+ " and segSistemaCia.sicCodigo ="+siscias.get(0).getSicCodigo();
			String whereCondition = "segSistemaCia.sicCodigo ="+siscias.get(0).getSicCodigo() + " and model.segOpcion.opcCodigo is not null";
			permisosPorRol = JPADaoFactory.getInstance().getSegPermisoDAO().findByCriteria(whereCondition);

			if (permisosPorRol.size()>0) {
				for (int i = 0; i < permisosPorRol.size(); i++) {
					Long codOpcion = permisosPorRol.get(i).getSegOpcion().getOpcCodigo();
					opcion = opcionLogic.getSegOpcion(codOpcion);
					opcionesSeleccionadasPreviamente.add(opcion);
				}
			}
			return opcionesSeleccionadasPreviamente;

		} catch (Exception e) {
			throw e;
		}
	}

}