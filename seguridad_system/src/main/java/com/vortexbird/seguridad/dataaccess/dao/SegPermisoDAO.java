package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.modelo.SegPermiso;


/**
 * A data access object (DAO) providing persistence and search support for
 * SegPermiso entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * @see lidis.SegPermiso
 *
 */
public class SegPermisoDAO implements ISegPermisoDAO {
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved SegPermiso entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SegPermisoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 *
	 * @param entity
	 *            SegPermiso entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SegPermiso entity) {
		EntityManagerHelper.log("saving SegPermiso instance", Level.INFO, null);

		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent SegPermiso entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SegPermisoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 *
	 * @param entity
	 *            SegPermiso entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SegPermiso entity) {
		EntityManagerHelper.log("deleting SegPermiso instance", Level.INFO, null);

		try {
			entity = getEntityManager()
					.getReference(SegPermiso.class, entity.getPerCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved SegPermiso entity and return it or a copy of it
	 * to the sender. A copy of the SegPermiso entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = SegPermisoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 *
	 * @param entity
	 *            SegPermiso entity to update
	 * @return SegPermiso the persisted SegPermiso entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SegPermiso update(SegPermiso entity) {
		EntityManagerHelper.log("updating SegPermiso instance", Level.INFO, null);

		try {
			SegPermiso result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);

			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public SegPermiso findById(Long id) {
		EntityManagerHelper.log("finding SegPermiso instance with id: " + id,
				Level.INFO, null);

		try {
			SegPermiso instance = getEntityManager().find(SegPermiso.class, id);

			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all  SegPermiso entities with a specific property value.
	 *
	 * @param propertyName
	 *            the metaData.name of the  SegPermiso property to query
	 * @param value
	 *            the property value to match
	 * @return List< SegPermiso> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<SegPermiso> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding  SegPermiso instance with property: " +
				propertyName + ", value: " + value, Level.INFO, null);

		try {
			final String queryString = "select model from  SegPermiso model where model." +
					propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property metaData.name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all SegPermiso entities with a specific property value.
	 *
	 * @param propertyName
	 *            the name of the SegPermiso property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<SegPermiso> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<SegPermiso> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding SegPermiso instance with property: " +
				propertyName + ", value: " + value, Level.INFO, null);

		try {
			final String queryString = "select model from SegPermiso model where model." +
					propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);

			if ((rowStartIdxAndCount != null) &&
					(rowStartIdxAndCount.length > 0)) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);

				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);

					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<SegPermiso> findByPerCodigo(Object perCodigo,
			int... rowStartIdxAndCount) {
		return findByProperty(PERCODIGO, perCodigo, rowStartIdxAndCount);
	}

	public List<SegPermiso> findByPerCodigo(Object perCodigo) {
		return findByProperty(PERCODIGO, perCodigo);
	}

	public List<SegPermiso> findByPerEstadoRegistro(Object perEstadoRegistro,
			int... rowStartIdxAndCount) {
		return findByProperty(PERESTADOREGISTRO, perEstadoRegistro,
				rowStartIdxAndCount);
	}

	public List<SegPermiso> findByPerEstadoRegistro(Object perEstadoRegistro) {
		return findByProperty(PERESTADOREGISTRO, perEstadoRegistro);
	}

	/**
	 * Find all SegPermiso entities.
	 *
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SegPermiso> all SegPermiso entities
	 */
	@SuppressWarnings("unchecked")
	public List<SegPermiso> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all SegPermiso instances", Level.INFO,
				null);

		try {
			final String queryString = "select model from SegPermiso model";
			Query query = getEntityManager().createQuery(queryString);

			if ((rowStartIdxAndCount != null) &&
					(rowStartIdxAndCount.length > 0)) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);

				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);

					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<SegPermiso> findByCriteria(String whereCondition) {
		EntityManagerHelper.log("finding SegPermiso " + whereCondition,
				Level.INFO, null);

		try {
			String where = ((whereCondition == null) ||
					(whereCondition.length() == 0)) ? "" : ("where " +
							whereCondition);
			final String queryString = "select model from SegPermiso model " +
					where;
			Query query = getEntityManager().createQuery(queryString);
			List<SegPermiso> entitiesList = query.getResultList();

			return entitiesList;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find By Criteria in SegPermiso failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<SegPermiso> findPageSegPermiso(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults) {
		EntityManagerHelper.log("finding SegPermiso findPageSegPermiso",
				Level.INFO, null);

		if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
			try {
				String queryString = "select model from SegPermiso model order by model." +
						sortColumnName + " " + (sortAscending ? "asc" : "desc");

				return getEntityManager().createQuery(queryString)
						.setFirstResult(startRow).setMaxResults(maxResults)
						.getResultList();
			} catch (RuntimeException re) {
				throw re;
			}
		} else {
			try {
				String queryString = "select model from SegPermiso model";

				return getEntityManager().createQuery(queryString)
						.setFirstResult(startRow).setMaxResults(maxResults)
						.getResultList();
			} catch (RuntimeException re) {
				throw re;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Long findTotalNumberSegPermiso() {
		EntityManagerHelper.log("finding SegPermiso count", Level.INFO, null);

		try {
			String queryString = "select count(*) from SegPermiso model";

			return (Long) getEntityManager().createQuery(queryString)
					.getSingleResult();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List<Object[]> getPermisosAdmin(Long usuCodigo) {
		try {

			String queryString = "SELECT perm.perCodigo, perm.perEstadoRegistro " +
					"FROM SegPermiso perm " +
					"WHERE perm.segSistemaCia.sicCodigo is null " +
					"AND perm.segUsuarioByUsuCodigo.usuCodigo=:usuCodigo";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("usuCodigo", usuCodigo);

			List<Object[]> perm = query.getResultList();

			return perm;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object[]> getPermisosByUserAndSisCia(Long usuCodigo, Long sisCiaUnico) {

		try {

			String queryString = "SELECT perm.perCodigo, perm.perEstadoRegistro " +
					"FROM SegPermiso perm " +
					"WHERE perm.segSistemaCia.sicCodigo=:sisCiaUnico " +
					"AND perm.segUsuarioByUsuCodigo.usuCodigo=:usuCodigo";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("usuCodigo", usuCodigo);
			query.setParameter("sisCiaUnico", sisCiaUnico);

			List<Object[]> perm = query.getResultList();

			return perm;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//	@Override
	//	public List<Object[]> findPermisosRolUnionUser(Long codigoRol,Long sicCodigo, String codigoUsuario) {
	//		try {
	//			
	//			List<Object[]> permDef= new ArrayList<Object[]>();
	//
	//			String queryString = "SELECT perm.perCodigo, perm.perEstadoRegistro " +
	//					"FROM SegPermiso perm " +
	//					"WHERE perm.segRol.rolCodigo=:codigoRol " +
	//					"AND perm.segSistemaCia.sicCodigo=:sicCodigo";
	////					"UNION " +
	////					"(SELECT perm.perCodigo, perm.perEstadoRegistro " +
	////					"FROM SegPermiso perm " +
	////					"WHERE perm.segUsuarioByUsuCodigo.usuCodigo=:usuCodigo " +
	////					"AND perm.segSistemaCia.sicCodigo=:sicCodigo)";
	//
	//			Query query = getEntityManager().createQuery(queryString);
	//			query.setParameter("sicCodigo", sicCodigo);
	//			query.setParameter("codigoRol", codigoRol);
	//
	//			List<Object[]> perm = query.getResultList();
	//			
	//			
	//			String queryString2 = "SELECT perm.perCodigo, perm.perEstadoRegistro " +
	//					"FROM SegPermiso perm " +
	//					"WHERE perm.segUsuarioByUsuCodigo.usuCodigo=:usuCodigo " +
	//					"AND perm.segSistemaCia.sicCodigo=:sicCodigo)";
	//			
	//			Query query2 = getEntityManager().createQuery(queryString2);
	//			query2.setParameter("usuCodigo", Long.parseLong(codigoUsuario.toString()));
	//			query2.setParameter("sicCodigo", sicCodigo);
	//			
	//			List<Object[]> perm2 = query2.getResultList();
	//			
	//			for (int i = 0; i < perm.size(); i++) {
	//				Object[] obj = perm.get(i);
	//				permDef.add(obj);
	//			}
	//			
	//			for (int i = 0; i < perm2.size(); i++) {
	//				Object[] obj2 = perm2.get(i);
	//				permDef.add(obj2);
	//			}
	//			
	//			
	//			return permDef;
	//
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		return null;
	//	}

	@Override
	public List<SegPermiso> findPermisosRolUnionUser(Long codigoRol,Long sicCodigo, String codigoUsuario) {
		try {

			String queryString = "SELECT * " +
					"FROM seg_permiso " +
					"WHERE seg_permiso.rol_codigo=:codigoRol " +
					"AND seg_permiso.sic_codigo=:sicCodigo " +
					"UNION	" +
					"SELECT * " +
					"FROM seg_permiso " +
					"WHERE seg_permiso.usu_codigo=:usuCodigo " +
					"AND seg_permiso.sic_codigo=:sicCodigo";

			Query query = getEntityManager().createNativeQuery(queryString, SegPermiso.class);
			query.setParameter("sicCodigo", sicCodigo);
			query.setParameter("codigoRol", codigoRol);
			query.setParameter("usuCodigo", Long.parseLong(codigoUsuario.toString()));

			List<SegPermiso> perm = query.getResultList();
			System.out.println(perm);

			return perm;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SegPermiso> findPermisosRolUnionUserOption(Long rolCodigo, Long sisCiaUnico, String codigoUsuario, Long codigoOpcion) {
		
		try {
			
			List<SegPermiso> perm = new ArrayList<SegPermiso>();

			String queryString = "SELECT * " +
					"FROM seg_permiso " +
					"WHERE seg_permiso.rol_codigo=:codigoRol " +
					"AND seg_permiso.sic_codigo=:sicCodigo " +
					"AND seg_permiso.opc_codigo=:codigoOpcion " +
					"UNION	" +
					"SELECT * " +
					"FROM seg_permiso " +
					"WHERE seg_permiso.usu_codigo=:usuCodigo " +
					"AND seg_permiso.sic_codigo=:sicCodigo " +
					"AND seg_permiso.opc_codigo=:codigoOpcion";

			Query query = getEntityManager().createNativeQuery(queryString, SegPermiso.class);
			query.setParameter("sicCodigo", sisCiaUnico);
			query.setParameter("codigoRol", rolCodigo);
			query.setParameter("usuCodigo", Long.parseLong(codigoUsuario.toString()));
			query.setParameter("codigoOpcion", codigoOpcion);

			perm = query.getResultList();

			return perm;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
