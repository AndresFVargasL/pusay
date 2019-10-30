package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.modelo.SegRol;


/**
 * A data access object (DAO) providing persistence and search support for
 * SegRol entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * @see lidis.SegRol
 *
 */
public class SegRolDAO implements ISegRolDAO {
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved SegRol entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SegRolDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 *
	 * @param entity
	 *            SegRol entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SegRol entity) {
		EntityManagerHelper.log("saving SegRol instance", Level.INFO, null);

		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent SegRol entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SegRolDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 *
	 * @param entity
	 *            SegRol entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SegRol entity) {
		EntityManagerHelper.log("deleting SegRol instance", Level.INFO, null);

		try {
			entity = getEntityManager()
					.getReference(SegRol.class, entity.getRolCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved SegRol entity and return it or a copy of it
	 * to the sender. A copy of the SegRol entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = SegRolDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 *
	 * @param entity
	 *            SegRol entity to update
	 * @return SegRol the persisted SegRol entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SegRol update(SegRol entity) {
		EntityManagerHelper.log("updating SegRol instance", Level.INFO, null);

		try {
			SegRol result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);

			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public SegRol findById(Long id) {
		EntityManagerHelper.log("finding SegRol instance with id: " + id,
				Level.INFO, null);

		try {
			SegRol instance = getEntityManager().find(SegRol.class, id);

			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all  SegRol entities with a specific property value.
	 *
	 * @param propertyName
	 *            the metaData.name of the  SegRol property to query
	 * @param value
	 *            the property value to match
	 * @return List< SegRol> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<SegRol> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding  SegRol instance with property: " +
				propertyName + ", value: " + value, Level.INFO, null);

		try {
			final String queryString = "select model from  SegRol model where model." +
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
	 * Find all SegRol entities with a specific property value.
	 *
	 * @param propertyName
	 *            the name of the SegRol property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<SegRol> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<SegRol> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding SegRol instance with property: " +
				propertyName + ", value: " + value, Level.INFO, null);

		try {
			final String queryString = "select model from SegRol model where model." +
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

	public List<SegRol> findByRolCodigo(Object rolCodigo,
			int... rowStartIdxAndCount) {
		return findByProperty(ROLCODIGO, rolCodigo, rowStartIdxAndCount);
	}

	public List<SegRol> findByRolCodigo(Object rolCodigo) {
		return findByProperty(ROLCODIGO, rolCodigo);
	}

	public List<SegRol> findByRolDescripcion(Object rolDescripcion,
			int... rowStartIdxAndCount) {
		return findByProperty(ROLDESCRIPCION, rolDescripcion,
				rowStartIdxAndCount);
	}

	public List<SegRol> findByRolDescripcion(Object rolDescripcion) {
		return findByProperty(ROLDESCRIPCION, rolDescripcion);
	}

	public List<SegRol> findByRolDiasCaducidadPwd(Object rolDiasCaducidadPwd,
			int... rowStartIdxAndCount) {
		return findByProperty(ROLDIASCADUCIDADPWD, rolDiasCaducidadPwd,
				rowStartIdxAndCount);
	}

	public List<SegRol> findByRolDiasCaducidadPwd(Object rolDiasCaducidadPwd) {
		return findByProperty(ROLDIASCADUCIDADPWD, rolDiasCaducidadPwd);
	}

	public List<SegRol> findByRolEstadoRegistro(Object rolEstadoRegistro,
			int... rowStartIdxAndCount) {
		return findByProperty(ROLESTADOREGISTRO, rolEstadoRegistro,
				rowStartIdxAndCount);
	}

	public List<SegRol> findByRolEstadoRegistro(Object rolEstadoRegistro) {
		return findByProperty(ROLESTADOREGISTRO, rolEstadoRegistro);
	}

	public List<SegRol> findByRolNombre(Object rolNombre,
			int... rowStartIdxAndCount) {
		return findByProperty(ROLNOMBRE, rolNombre, rowStartIdxAndCount);
	}

	public List<SegRol> findByRolNombre(Object rolNombre) {
		return findByProperty(ROLNOMBRE, rolNombre);
	}

	/**
	 * Find all SegRol entities.
	 *
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SegRol> all SegRol entities
	 */
	@SuppressWarnings("unchecked")
	public List<SegRol> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all SegRol instances", Level.INFO, null);

		try {
			final String queryString = "select model from SegRol model";
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

	public List<SegRol> findByCriteria(String whereCondition) {
		EntityManagerHelper.log("finding SegRol " + whereCondition, Level.INFO,
				null);

		try {
			String where = ((whereCondition == null) ||
					(whereCondition.length() == 0)) ? "" : ("where " +
							whereCondition);
			final String queryString = "select model from SegRol model " +
					where;
			Query query = getEntityManager().createQuery(queryString);
			List<SegRol> entitiesList = query.getResultList();

			return entitiesList;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find By Criteria in SegRol failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<SegRol> findPageSegRol(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults) {
		EntityManagerHelper.log("finding SegRol findPageSegRol", Level.INFO,
				null);

		if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
			try {
				String queryString = "select model from SegRol model order by model." +
						sortColumnName + " " + (sortAscending ? "asc" : "desc");

				return getEntityManager().createQuery(queryString)
						.setFirstResult(startRow).setMaxResults(maxResults)
						.getResultList();
			} catch (RuntimeException re) {
				throw re;
			}
		} else {
			try {
				String queryString = "select model from SegRol model";

				return getEntityManager().createQuery(queryString)
						.setFirstResult(startRow).setMaxResults(maxResults)
						.getResultList();
			} catch (RuntimeException re) {
				throw re;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Long findTotalNumberSegRol() {
		EntityManagerHelper.log("finding SegRol count", Level.INFO, null);

		try {
			String queryString = "select count(*) from SegRol model";

			return (Long) getEntityManager().createQuery(queryString)
					.getSingleResult();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List<Object[]> getSegRolesBySystem(Long usuSession, String sistema) {
		try {

			String queryString = "SELECT roles.rolCodigo, roles.rolDescripcion, roles.rolDiasCaducidadPwd, roles.rolEstadoRegistro, " +
					"roles.rolNombre, sist.sisCodigo " +
					"FROM SegRol roles " +
					"join roles.segSistema sist " +
					"WHERE sist.sisCodigo in " +
					"(SELECT rol.segSistema.sisCodigo " +
					"FROM SegRolUsuario rolUsu " +
					"join rolUsu.segRol rol " +
					"WHERE rolUsu.segUsuarioBySegUsuarioUsuCodigo.usuCodigo =:usuSession " +
					"AND rol.rolNombre LIKE 'ADMIN%') " +
					"AND sist.sisCodigo =:sistema";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("usuSession", usuSession);
			query.setParameter("sistema", Long.parseLong(sistema.toString()));

			List<Object[]> roles =  query.getResultList();

			return roles;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object[]> getSegRolesBySystemNoAdmin(String sistema) {
		
		try {

			String queryString = "SELECT roles.rolCodigo, roles.rolNombre " +
					"FROM SegRol roles " +
					"join roles.segSistema sist " +
					"WHERE sist.sisCodigo =:sistema " +
					"AND roles.rolNombre NOT LIKE 'ADMIN%'";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("sistema", Long.parseLong(sistema.toString()));

			List<Object[]> rolesNoAdmin =  query.getResultList();

			return rolesNoAdmin;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object[]> getSegRolesAdmin() {
		try {

			String queryString = "SELECT roles.rolCodigo, roles.rolNombre " +
					"FROM SegRol roles " +
					"WHERE roles.rolNombre LIKE 'ADMIN%'";

			Query query = getEntityManager().createQuery(queryString);

			List<Object[]> rolesAdmin =  query.getResultList();

			return rolesAdmin;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
