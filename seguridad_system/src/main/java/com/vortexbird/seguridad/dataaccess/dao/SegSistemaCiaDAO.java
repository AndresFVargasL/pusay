package com.vortexbird.seguridad.dataaccess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vortexbird.seguridad.dataaccess.entityManager.EntityManagerHelper;
import com.vortexbird.seguridad.modelo.SegSistemaCia;


/**
 * A data access object (DAO) providing persistence and search support for
 * SegSistemaCia entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * @see lidis.SegSistemaCia
 *
 */
public class SegSistemaCiaDAO implements ISegSistemaCiaDAO {
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved SegSistemaCia entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SegSistemaCiaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 *
	 * @param entity
	 *            SegSistemaCia entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SegSistemaCia entity) {
		EntityManagerHelper.log("saving SegSistemaCia instance", Level.INFO,
				null);

		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent SegSistemaCia entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SegSistemaCiaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 *
	 * @param entity
	 *            SegSistemaCia entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SegSistemaCia entity) {
		EntityManagerHelper.log("deleting SegSistemaCia instance", Level.INFO,
				null);

		try {
			entity = getEntityManager()
					.getReference(SegSistemaCia.class,
							entity.getSicCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved SegSistemaCia entity and return it or a copy of it
	 * to the sender. A copy of the SegSistemaCia entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = SegSistemaCiaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 *
	 * @param entity
	 *            SegSistemaCia entity to update
	 * @return SegSistemaCia the persisted SegSistemaCia entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SegSistemaCia update(SegSistemaCia entity) {
		EntityManagerHelper.log("updating SegSistemaCia instance", Level.INFO,
				null);

		try {
			SegSistemaCia result = getEntityManager()
					.merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);

			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public SegSistemaCia findById(Long id) {
		EntityManagerHelper.log("finding SegSistemaCia instance with id: " +
				id, Level.INFO, null);

		try {
			SegSistemaCia instance = getEntityManager()
					.find(SegSistemaCia.class, id);

			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all  SegSistemaCia entities with a specific property value.
	 *
	 * @param propertyName
	 *            the metaData.name of the  SegSistemaCia property to query
	 * @param value
	 *            the property value to match
	 * @return List< SegSistemaCia> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<SegSistemaCia> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding  SegSistemaCia instance with property: " + propertyName +
				", value: " + value, Level.INFO, null);

		try {
			final String queryString = "select model from  SegSistemaCia model where model." +
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
	 * Find all SegSistemaCia entities with a specific property value.
	 *
	 * @param propertyName
	 *            the name of the SegSistemaCia property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<SegSistemaCia> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<SegSistemaCia> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding SegSistemaCia instance with property: " + propertyName +
				", value: " + value, Level.INFO, null);

		try {
			final String queryString = "select model from SegSistemaCia model where model." +
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

	public List<SegSistemaCia> findBySicCodigo(Object sicCodigo,
			int... rowStartIdxAndCount) {
		return findByProperty(SICCODIGO, sicCodigo, rowStartIdxAndCount);
	}

	public List<SegSistemaCia> findBySicCodigo(Object sicCodigo) {
		return findByProperty(SICCODIGO, sicCodigo);
	}

	public List<SegSistemaCia> findBySicEstadoRegistro(
			Object sicEstadoRegistro, int... rowStartIdxAndCount) {
		return findByProperty(SICESTADOREGISTRO, sicEstadoRegistro,
				rowStartIdxAndCount);
	}

	public List<SegSistemaCia> findBySicEstadoRegistro(Object sicEstadoRegistro) {
		return findByProperty(SICESTADOREGISTRO, sicEstadoRegistro);
	}

	/**
	 * Find all SegSistemaCia entities.
	 *
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SegSistemaCia> all SegSistemaCia entities
	 */
	@SuppressWarnings("unchecked")
	public List<SegSistemaCia> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all SegSistemaCia instances",
				Level.INFO, null);

		try {
			final String queryString = "select model from SegSistemaCia model";
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

	public List<SegSistemaCia> findByCriteria(String whereCondition) {
		EntityManagerHelper.log("finding SegSistemaCia " + whereCondition,
				Level.INFO, null);

		try {
			String where = ((whereCondition == null) ||
					(whereCondition.length() == 0)) ? "" : ("where " +
							whereCondition);
			final String queryString = "select model from SegSistemaCia model " +
					where;
			Query query = getEntityManager().createQuery(queryString);
			List<SegSistemaCia> entitiesList = query.getResultList();

			return entitiesList;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find By Criteria in SegSistemaCia failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<SegSistemaCia> findPageSegSistemaCia(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults) {
		EntityManagerHelper.log("finding SegSistemaCia findPageSegSistemaCia",
				Level.INFO, null);

		if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
			try {
				String queryString = "select model from SegSistemaCia model order by model." +
						sortColumnName + " " + (sortAscending ? "asc" : "desc");

				return getEntityManager().createQuery(queryString)
						.setFirstResult(startRow).setMaxResults(maxResults)
						.getResultList();
			} catch (RuntimeException re) {
				throw re;
			}
		} else {
			try {
				String queryString = "select model from SegSistemaCia model";

				return getEntityManager().createQuery(queryString)
						.setFirstResult(startRow).setMaxResults(maxResults)
						.getResultList();
			} catch (RuntimeException re) {
				throw re;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Long findTotalNumberSegSistemaCia() {
		EntityManagerHelper.log("finding SegSistemaCia count", Level.INFO, null);

		try {
			String queryString = "select count(*) from SegSistemaCia model";

			return (Long) getEntityManager().createQuery(queryString)
					.getSingleResult();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List<Object[]> getSegSistemasCiasQuery(String sistema,String compania) {

		try {
			String queryString = "SELECT DISTINCT sisCia.sicEstadoRegistro, compania.ciaCodigo, compania.ciaNombre, sistema.sisCodigo, sistema.sisNombre " +
					"FROM SegUsuario usuario, SegRolUsuario rolUsuario, SegRol rol, SegSistema sistema, SegSistemaCia sisCia, SegCompania compania, SegPermiso permiso " +
					"WHERE usuario.usuCodigo = rolUsuario.segUsuarioBySegUsuarioUsuCodigo.usuCodigo " +
					"AND rolUsuario.segRol.rolCodigo = rol.rolCodigo " +
					"AND permiso.segRol.rolCodigo = rol.rolCodigo " +
					"AND permiso.segSistemaCia.segCompania.ciaCodigo = compania.ciaCodigo " +
					"AND sisCia.segCompania.ciaCodigo = compania.ciaCodigo " +
					"AND sisCia.segSistema.sisCodigo = sistema.sisCodigo " +
					"AND sistema.sisCodigo =:sistema " +
					"AND compania.ciaCodigo =:compania";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("sistema", Long.parseLong(sistema.toString()));
			query.setParameter("compania", Long.parseLong(compania.toString()));

			List<Object[]> sisCias = query.getResultList();
			return sisCias;
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
}
