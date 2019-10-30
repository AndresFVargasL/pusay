package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.modelo.SegSistema;


/**
 * A data access object (DAO) providing persistence and search support for
 * SegSistema entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * @see lidis.SegSistema
 *
 */
public class SegSistemaDAO implements ISegSistemaDAO {
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved SegSistema entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SegSistemaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 *
	 * @param entity
	 *            SegSistema entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SegSistema entity) {
		EntityManagerHelper.log("saving SegSistema instance", Level.INFO, null);

		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent SegSistema entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SegSistemaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 *
	 * @param entity
	 *            SegSistema entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SegSistema entity) {
		EntityManagerHelper.log("deleting SegSistema instance", Level.INFO, null);

		try {
			entity = getEntityManager()
					.getReference(SegSistema.class, entity.getSisCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved SegSistema entity and return it or a copy of it
	 * to the sender. A copy of the SegSistema entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = SegSistemaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 *
	 * @param entity
	 *            SegSistema entity to update
	 * @return SegSistema the persisted SegSistema entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SegSistema update(SegSistema entity) {
		EntityManagerHelper.log("updating SegSistema instance", Level.INFO, null);

		try {
			SegSistema result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);

			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public SegSistema findById(Long id) {
		EntityManagerHelper.log("finding SegSistema instance with id: " + id,
				Level.INFO, null);

		try {
			SegSistema instance = getEntityManager().find(SegSistema.class, id);

			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all  SegSistema entities with a specific property value.
	 *
	 * @param propertyName
	 *            the metaData.name of the  SegSistema property to query
	 * @param value
	 *            the property value to match
	 * @return List< SegSistema> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<SegSistema> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding  SegSistema instance with property: " +
				propertyName + ", value: " + value, Level.INFO, null);

		try {
			final String queryString = "select model from  SegSistema model where model." +
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
	 * Find all SegSistema entities with a specific property value.
	 *
	 * @param propertyName
	 *            the name of the SegSistema property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<SegSistema> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<SegSistema> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding SegSistema instance with property: " +
				propertyName + ", value: " + value, Level.INFO, null);

		try {
			final String queryString = "select model from SegSistema model where model." +
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

	public List<SegSistema> findBySisCodigo(Object sisCodigo,
			int... rowStartIdxAndCount) {
		return findByProperty(SISCODIGO, sisCodigo, rowStartIdxAndCount);
	}

	public List<SegSistema> findBySisCodigo(Object sisCodigo) {
		return findByProperty(SISCODIGO, sisCodigo);
	}

	public List<SegSistema> findBySisDescripcion(Object sisDescripcion,
			int... rowStartIdxAndCount) {
		return findByProperty(SISDESCRIPCION, sisDescripcion,
				rowStartIdxAndCount);
	}

	public List<SegSistema> findBySisDescripcion(Object sisDescripcion) {
		return findByProperty(SISDESCRIPCION, sisDescripcion);
	}

	public List<SegSistema> findBySisEstadoRegistro(Object sisEstadoRegistro,
			int... rowStartIdxAndCount) {
		return findByProperty(SISESTADOREGISTRO, sisEstadoRegistro,
				rowStartIdxAndCount);
	}

	public List<SegSistema> findBySisEstadoRegistro(Object sisEstadoRegistro) {
		return findByProperty(SISESTADOREGISTRO, sisEstadoRegistro);
	}

	public List<SegSistema> findBySisNombre(Object sisNombre,
			int... rowStartIdxAndCount) {
		return findByProperty(SISNOMBRE, sisNombre, rowStartIdxAndCount);
	}

	public List<SegSistema> findBySisNombre(Object sisNombre) {
		return findByProperty(SISNOMBRE, sisNombre);
	}

	/**
	 * Find all SegSistema entities.
	 *
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SegSistema> all SegSistema entities
	 */
	@SuppressWarnings("unchecked")
	public List<SegSistema> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all SegSistema instances", Level.INFO,
				null);

		try {
			final String queryString = "select model from SegSistema model";
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

	public List<SegSistema> findByCriteria(String whereCondition) {
		EntityManagerHelper.log("finding SegSistema " + whereCondition,
				Level.INFO, null);

		try {
			String where = ((whereCondition == null) ||
					(whereCondition.length() == 0)) ? "" : ("where " +
							whereCondition);
			final String queryString = "select model from SegSistema model " +
					where;
			Query query = getEntityManager().createQuery(queryString);
			List<SegSistema> entitiesList = query.getResultList();

			return entitiesList;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find By Criteria in SegSistema failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<SegSistema> findPageSegSistema(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults) {
		EntityManagerHelper.log("finding SegSistema findPageSegSistema",
				Level.INFO, null);

		if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
			try {
				String queryString = "select model from SegSistema model order by model." +
						sortColumnName + " " + (sortAscending ? "asc" : "desc");

				return getEntityManager().createQuery(queryString)
						.setFirstResult(startRow).setMaxResults(maxResults)
						.getResultList();
			} catch (RuntimeException re) {
				throw re;
			}
		} else {
			try {
				String queryString = "select model from SegSistema model";

				return getEntityManager().createQuery(queryString)
						.setFirstResult(startRow).setMaxResults(maxResults)
						.getResultList();
			} catch (RuntimeException re) {
				throw re;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Long findTotalNumberSegSistema() {
		EntityManagerHelper.log("finding SegSistema count", Level.INFO, null);

		try {
			String queryString = "select count(*) from SegSistema model";

			return (Long) getEntityManager().createQuery(queryString)
					.getSingleResult();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List<Object[]> findSystemsByUser(Long usuSession, String sistema) {

		try {
			String queryString = "SELECT rol.segSistema.sisCodigo, rol.segSistema.sisNombre " +
					"FROM SegRolUsuario rolUsu " +
					"join rolUsu.segRol rol " +
					"WHERE rolUsu.segUsuarioBySegUsuarioUsuCodigo.usuCodigo =:usuSession AND " +
					"rol.rolNombre like 'ADMIN%' AND " +
					"rol.segSistema.sisCodigo =:sistema";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("usuSession", usuSession);
			query.setParameter("sistema", Long.parseLong(sistema.toString()));

			List<Object[]> codigoSistemasByUsuario =  query.getResultList();

			return codigoSistemasByUsuario;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object[]> findSystemsByUserRol(Long usuSession) {
		try {
			String queryString = "SELECT rol.segSistema.sisCodigo, rol.segSistema.sisNombre " +
					"FROM SegRolUsuario rolUsu " +
					"join rolUsu.segRol rol " +
					"WHERE rolUsu.segUsuarioBySegUsuarioUsuCodigo.usuCodigo =:usuSession AND " +
					"rol.rolNombre like 'ADMIN%'";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("usuSession", usuSession);

			List<Object[]> codigoSistemasByUsuarioRol =  query.getResultList();

			return codigoSistemasByUsuarioRol;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
